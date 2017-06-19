package com.greencloud.config;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

/** 
 */
@Component
public class JsonToMapper extends ObjectMapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1119785987361174791L;

	public JsonToMapper() {
		super();
		// 空值处理为空串
		this.getSerializerProvider().setNullValueSerializer(
				new JsonSerializer<Object>() {
					@Override
					public void serialize(Object value, JsonGenerator jg,
							SerializerProvider sp) throws IOException,
							JsonProcessingException {
						jg.writeString("");
					}
				});
		 //设置日期转换yyyy-MM-dd HH:mm:ss  
        this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));  
	}
}

