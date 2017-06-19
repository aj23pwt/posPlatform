package com.greencloud.annotation;
/** 
 * 日志类型
 */

public enum LogType {
	新增(0),修改(1),刪除(2),查詢(3),登录(4),退出(5),锁定(6),解除锁定(7);
	
	private final Integer value;
	
	private LogType(Integer value) {
		this.value = value;
	}
	
	public Integer value() {
		return this.value;
	}
}
