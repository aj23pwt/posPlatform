package com.greencloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
//@RestController注解等价于@Controller+@ResponseBody的结合，使用这个注解的类里面的方法都以json格式输出。
@SpringBootApplication
@ComponentScan
@ServletComponentScan   //扫描Servlet 
@Configuration
public class PosPlatformApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PosPlatformApplication.class);
	}
	@RequestMapping("/")
	String home() {
		return "Hello PosPlatform World!" ;
	}

	public static void main(String[] args) {
		SpringApplication.run(PosPlatformApplication.class, args);
	}
	
}
