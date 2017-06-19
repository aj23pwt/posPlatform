package com.greencloud.config;

/** 
 */
public enum HttpCode {
	/** 200请求成功 */
	OK(200),
	/** 400请求参数出错 */
	BAD_REQUEST(400),
	/** 401没有登录 */
	UNAUTHORIZED(401),
	/** 403没有权限 */
	FORBIDDEN(403),
	/** 404找不到页面 */
	NOT_FOUND(404),
	/** 408请求超时 */
	REQUEST_TIMEOUT(408),
	/** 409发生冲突 */
	CONFLICT(409),
	/** 410已被删除 */
	GONE(410),
	/** 423已被锁定 */
	LOCKED(423),
	/** 500服务器出错 */
	INTERNAL_SERVER_ERROR(500);

	private final Integer value;

	private HttpCode(Integer value) {
		this.value = value;
	}

	/**
	 * Return the integer value of this status code.
	 */
	public Integer value() {
		return this.value;
	}
	
	public String msg() {
		String msg = "";
		switch (this.value) {
		case 200:
			msg = "请求成功!";
			break;
		case 400:
			msg = "请求参数出错!";
			break;
		case 401:
			msg = "没有登录!";
			break;
		case 403:
			msg = "没有权限!";
			break;
		case 404:
			msg = "请求无法到达!";
			break;
		case 408:
			msg = "请求超时!";
			break;
		case 409:
			msg = "发生冲突!";
			break;
		case 410:
			msg = "已被删除!";
			break;
		case 423:
			msg = "已被锁定!";
			break;
		case 500:
			msg = "服务器内部错误!";
			break;
		default:
			msg = "未知异常!";
			break;
		}
		return msg;
	}

	public String toString() {
		return this.value.toString();
	}
}
