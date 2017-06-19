package com.greencloud.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greencloud.config.HttpCode;
import com.greencloud.config.ResultData;
import com.greencloud.config.TestConfiguration;
import com.greencloud.dto.PosResDto;

@Controller
@RequestMapping("test")
public class TestController {
	private static Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Value("${pos.uuid}")
	private String uuid;

	@Autowired
	TestConfiguration testConfiguration;
	@Autowired
	RedisTemplate<String, String> redisTemplate;
	@Autowired
	DataSource dataSource;

	// 默认配置当前controller的访问资源情况是否可用
	@RequestMapping("test1")
	public ResultData testController() {
		ResultData resultData = new ResultData();
		String msg = uuid + " posPlatform testController resource ";
		resultData.setHttpCode(HttpCode.OK.value());
		resultData.setMsg(HttpCode.OK.msg());
		resultData.setResult(msg);
		return resultData;
	}

	@RequestMapping("test2")
	public String testControllerTest() {
		return "Hello PosPlatform World!" + testConfiguration.getName() + testConfiguration.getWant() + uuid;
	}

	@RequestMapping(value = "/getWechatTest")
	public ModelAndView getPosWxPluNote(@RequestParam(value = "hotelGroupId", required = true) String hotelGroupId,
			@RequestParam(value = "hotelId", required = false) String hotelId,
			@RequestParam(value = "pccode", required = true) String pccode, HttpServletResponse response)
			throws Exception {
		ResultData resultData = new ResultData();
		try {
			String msg = "request信息  集团ID-" + hotelGroupId +" 酒店ID-"+ hotelId  + " 营业点："+pccode  ;
			resultData.setHttpCode(HttpCode.OK.value());
			resultData.setMsg(HttpCode.OK.msg());
			resultData.setResult(msg);
			resultData.setExtendField("posPlatform testController resource ");
			resultData.setSignature(uuid);
		} catch (Exception e) {
            e.printStackTrace();
		} finally {
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			String json = new ObjectMapper().writeValueAsString(resultData);
			out.print(json);
			out.close();
		}
		return null;
	}
	@RequestMapping(value = "/addWechatTest")
	public ModelAndView addPosWxResMaster(HttpServletRequest request,HttpServletResponse response){
		ResultData resultData = new ResultData();
		try {
			dataSource.getConnection().nativeSQL(" select 1 from user ");
			// 读取请求内容
			BufferedReader br = new BufferedReader(new InputStreamReader(
					request.getInputStream(),"UTF-8"));
			String line = null;
			StringBuilder sb = new StringBuilder();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			PosResDto posResDto = new ObjectMapper().readValue(sb.toString(), PosResDto.class);
			logger.info("request信息  " + sb.toString());
			if(posResDto != null){
				resultData.setHttpCode(HttpCode.OK.value());
				resultData.setMsg(HttpCode.OK.msg());
				resultData.setResult( new ObjectMapper().writeValueAsString(posResDto));
			}
			resultData.setHttpCode(HttpCode.OK.value());
			resultData.setMsg(HttpCode.OK.msg());
			resultData.setExtendField("posPlatform testController resource ");
			resultData.setSignature(uuid);
			//redis 存储
			posResDto.setAccnt("gc");
			redisTemplate.opsForValue().set("gc", new ObjectMapper().writeValueAsString(posResDto));
			  boolean exists=redisTemplate.hasKey("gc");
		        if(exists){
		            System.out.println("gc is true");
		        }else{
		            System.out.println("gc is false");
		        }
		} catch (Exception e) {
            e.printStackTrace();
		} finally {
			
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				String json = new ObjectMapper().writeValueAsString(resultData);
				out.print(json);
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
}
