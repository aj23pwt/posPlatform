package com.greencloud.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greencloud.annotation.LogInfo;
import com.greencloud.config.BusinessException;
import com.greencloud.config.Constants;
import com.greencloud.config.HttpCode;
import com.greencloud.config.ResultData;
import com.greencloud.dao.vo.SysLog;
import com.greencloud.service.SysLogService;


/** 
 */
@Component
@Aspect
public class ControllerAop {
	
	private static Logger logger = LoggerFactory.getLogger(ControllerAop.class);
	
	@Autowired
	SysLogService sysLogService;

	@Pointcut("execution(* com.greencloud.controller..*.*(..))")
	public void init() {

	}
	
	@Before(value = "init()")
	public void before(JoinPoint jp) {
		//Object[] args = jp.getArgs();
		logger.info("---------方法执行前执行.....");
	}

	@AfterReturning(value = "init()")
	public void afterReturning() {
		logger.info("---------方法执行完执行.....");
	}

	@AfterThrowing(value = "init()")
	public void throwss() {
		logger.info("--------方法异常时执行.....");
	}

	@After(value = "init()")
	public void after() {
		logger.info("-------方法最后执行.....");
	}

	/**
	 * 記錄日誌、全局異常處理
	 * @param pjp
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@Around(value = "init()")
	public Object around(ProceedingJoinPoint pjp) throws Exception {
		boolean isLog = false;//是否記錄日誌
		boolean logException = false;//是否記錄異常日誌
		SysLog sysLog = new SysLog();
		LogInfo logInfo = null;
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		Object[] params = pjp.getArgs();
		if(params.length > 0){
			for (int i = 0; i < params.length; i++) {
				map.put("params"+i, params[i]);
			}
		}
		String targetName = pjp.getTarget().getClass().getName();    
        String methodName = pjp.getSignature().getName();    
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info("-------【方法 " + targetName + "." + methodName + "() 执行开始】.....");
        Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods(); 
        for (int i = 0; i < methods.length; i++) {
			if(methodName.equals(methods[i].getName())){
				logInfo = methods[i].getAnnotation(LogInfo.class);
				if(null != logInfo){
					isLog = true;
					logger.info("记录日志:" + logInfo.operationContent());
					sysLog.setContent(logInfo.operationContent());
					sysLog.setTypeKey(logInfo.logType().value());
					sysLog.setTypeValue(logInfo.logType().name());
					sysLog.setIp(request.getRemoteAddr());
					sysLog.setParams(mapper.writeValueAsString(map));
				}
			}
		}
		Object o = null;
		try {
			o = pjp.proceed();
		} catch (Throwable e) {
			if(isLog){
				logException = true;
				logger.info("记录异常日志:" + e.getMessage());
				if (e instanceof BusinessException) {
					sysLog.setFailureCause(e.getMessage());
				}else if(e instanceof IllegalArgumentException){
					sysLog.setFailureCause(e.getMessage());
				}else{
					sysLog.setFailureCause("服务器内部错误!");
				}
				sysLog.setResult("0");
			}
			exceptionHandler(response,e);
		}
		if(isLog){
			if(!logException){
				logger.info("记录成功日志:" + logInfo.operationContent() + "-----成功！");
				sysLog.setResult("1");
			}
			sysLog.setOperator(2);
			sysLogService.addSysLog(sysLog);
		}
		logger.info("-------【方法 " + targetName + "." + methodName + "() 执行结束】.....");
		return o;
	}
	
	/**
	 * 全局异常处理
	 * @param response
	 * @param e
	 * @throws Exception
	 */
	public void exceptionHandler(HttpServletResponse response, Throwable e) throws Exception {
		logger.error(Constants.Exception_Head, e);
		ResultData resultData = new ResultData();
		if (e instanceof IllegalArgumentException) {
			if (StringUtils.isNotBlank(e.getMessage())) {
				resultData.setHttpCode(HttpCode.BAD_REQUEST.value());
				resultData.setMsg(e.getMessage());
			} else {
				resultData.setHttpCode(HttpCode.BAD_REQUEST.value());
				resultData.setMsg(HttpCode.BAD_REQUEST.msg());
			}
		} else if (e instanceof BusinessException) {
			if (StringUtils.isNotBlank(e.getMessage())) {
				resultData.setHttpCode(HttpCode.CONFLICT.value());
				resultData.setMsg(e.getMessage());
			} else {
				resultData.setHttpCode(HttpCode.CONFLICT.value());
				resultData.setMsg(HttpCode.CONFLICT.msg());
			}
		} else {
			resultData.setHttpCode(HttpCode.INTERNAL_SERVER_ERROR.value());
			resultData.setMsg(HttpCode.INTERNAL_SERVER_ERROR.msg());
		}
		response.setContentType("application/json;charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		byte[] bytes = mapper.writeValueAsBytes(resultData);
		response.getOutputStream().write(bytes);
	}
}