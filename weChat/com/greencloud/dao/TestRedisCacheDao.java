package com.greencloud.dao;

import org.springframework.cache.annotation.Cacheable;

import com.greencloud.dto.PosResDto;

public class TestRedisCacheDao {

	//自动根据方法生成缓存
	@Cacheable(value="accnts")
	public PosResDto test(PosResDto posResDto ) {
		PosResDto posResDtoCache = posResDto;
	    System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");  
	    return posResDtoCache;
	}
}
