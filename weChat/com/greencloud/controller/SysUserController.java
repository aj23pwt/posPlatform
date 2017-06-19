package com.greencloud.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greencloud.annotation.LogInfo;
import com.greencloud.annotation.LogType;
import com.greencloud.config.HttpCode;
import com.greencloud.config.ResultData;
import com.greencloud.dao.vo.SysUser;
import com.greencloud.service.SysUserService;

/**
 */

@Controller
public class SysUserController {

	private static Logger logger = LoggerFactory.getLogger(SysUserController.class);

	@Autowired
	SysUserService sysUserService;

	// @RequestMapping(value="/{user}/customers", method=RequestMethod.GET)
	// List<Customer> getUserCustomers(@PathVariable Long user) {
	// ...
	// }
	@LogInfo(logType = LogType.登录, operationContent = "登录系统")
	@RequestMapping(value = "login")
	@ResponseBody
	public ResultData login(SysUser sysUser) {
		ResultData resultData = new ResultData();
		Assert.notNull(sysUser.getAccount(), "账户不能为空！");
		Assert.notNull(sysUser.getPassword(), "密码不能为空！");
		/*
		 * sysUser = sysUserService.selectByAccountAndPassword(sysUser);
		 * resultData.setHttpCode(HttpCode.OK.value());
		 * resultData.setMsg(HttpCode.OK.msg()); resultData.setResult(sysUser);
		 * return resultData;
		 */
		String msg = "";
		String username = sysUser.getAccount();
		UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getAccount(), sysUser.getPassword());
		// 获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();
		try {
			// 在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
			// 每个Realm都能在必要时对提交的AuthenticationTokens作出反应
			// 所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
			logger.info("对用户[" + username + "]进行登录验证..验证开始");
			currentUser.login(token);
			logger.info("对用户[" + username + "]进行登录验证..验证通过");
		} catch (UnknownAccountException uae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
			msg = "未知账户";
			resultData.setHttpCode(HttpCode.UNAUTHORIZED.value());
			resultData.setMsg(msg);
			return resultData;
		} catch (IncorrectCredentialsException ice) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
			msg = "密码不正确";
			resultData.setHttpCode(HttpCode.UNAUTHORIZED.value());
			resultData.setMsg(msg);
			return resultData;
		} catch (LockedAccountException lae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
			msg = "账户已锁定";
			resultData.setHttpCode(HttpCode.UNAUTHORIZED.value());
			resultData.setMsg(msg);
			return resultData;
		} catch (ExcessiveAttemptsException eae) {
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
			msg = "用户名或密码错误次数过多";
			resultData.setHttpCode(HttpCode.UNAUTHORIZED.value());
			resultData.setMsg(msg);
			return resultData;
		} catch (AuthenticationException ae) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			logger.info("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
			msg = "用户名或密码不正确";
			resultData.setHttpCode(HttpCode.UNAUTHORIZED.value());
			resultData.setMsg(msg);
			return resultData;
		}
		// 验证是否登录成功
		if (currentUser.isAuthenticated()) {
			logger.info("用户[" + username + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
			sysUser = sysUserService.selectByAccountAndPassword(sysUser);
			resultData.setHttpCode(HttpCode.OK.value());
			resultData.setMsg(HttpCode.OK.msg());
			resultData.setResult(sysUser);
		}else {
			token.clear();
//		    sysUser = sysUserService.selectByAccountAndPassword(sysUser);
			resultData.setHttpCode(HttpCode.OK.value());
			resultData.setMsg(msg);
			resultData.setResult(sysUser);
		}
		return resultData;
	}

	@LogInfo(logType = LogType.修改, operationContent = "账户信息修改")
	@RequestMapping(value = "updateSysUser")
	@ResponseBody
	public ResultData updateSysUser(SysUser sysUser) {
		ResultData resultData = new ResultData();
		sysUserService.updateSysUser(sysUser);
		resultData.setHttpCode(HttpCode.OK.value());
		resultData.setMsg("账户信息修改成功");
		return resultData;
	}

	@LogInfo(logType = LogType.锁定, operationContent = "账户信息锁定")
	@RequestMapping(value = "lockSysUser")
	@ResponseBody
	public ResultData lockSysUser(SysUser sysUser) {
		ResultData resultData = new ResultData();
		sysUserService.lockSysUser(sysUser);
		resultData.setHttpCode(HttpCode.OK.value());
		resultData.setMsg("账户信息锁定成功");
		return resultData;
	}

	@LogInfo(logType = LogType.解除锁定, operationContent = "账户信息解除锁定")
	@RequestMapping(value = "unLockSysUser")
	@ResponseBody
	public ResultData unLockSysUser(SysUser sysUser) {
		ResultData resultData = new ResultData();
		sysUserService.unLockSysUser(sysUser);
		resultData.setHttpCode(HttpCode.OK.value());
		resultData.setMsg("账户信息解除锁定成功");
		return resultData;
	}

	@LogInfo(logType = LogType.新增, operationContent = "账户信息添加")
	@RequestMapping(value = "addSysUser")
	@ResponseBody
	public ResultData addSysUser(SysUser sysUser) {
		ResultData resultData = new ResultData();
		sysUserService.addSysUser(sysUser);
		resultData.setHttpCode(HttpCode.OK.value());
		resultData.setMsg("账户信息添加成功");
		return resultData;
	}
}
