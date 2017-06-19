package com.greencloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 */

@Controller
@RequestMapping(value = "auth")
public class AuthController {

	private static Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@RequestMapping(value = "unauthorized")
	public String unauthorized() {
		return null;
	}
}
