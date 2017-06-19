package com.greencloud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "pos")
@PropertySource("classpath:config/test.properties")
public class TestConfiguration {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWant() {
		return want;
	}

	public void setWant(String want) {
		this.want = want;
	}

	private String name;
	private String want;
	// 省略getter和setter
}
