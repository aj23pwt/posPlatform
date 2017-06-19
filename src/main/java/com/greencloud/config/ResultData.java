package com.greencloud.config;


/** 
 */
public class ResultData {

	/**
	 * 返回状态
	 */
	Integer httpCode = HttpCode.OK.value();
	/**
	 * 返回消息
	 */
	String msg = "请求成功！";
	/**
	 * 返回数据体
	 */
	Object result = "";
	/**
	 * 保留字段
	 */
	Object extendField = "";
	/**
	 * 签名
	 */
	String signature = "";
	public Integer getHttpCode() {
		return httpCode;
	}
	public void setHttpCode(Integer httpCode) {
		this.httpCode = httpCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public Object getExtendField() {
		return extendField;
	}
	public void setExtendField(Object extendField) {
		this.extendField = extendField;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	
}
