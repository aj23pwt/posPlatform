package com.greencloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greencloud.dao.SysLogMapper;
import com.greencloud.dao.vo.SysLog;
/** 
 */
@Service
public class SysLogService {

	@Autowired
	SysLogMapper sysLogMapper;

	public boolean addSysLog(SysLog sysLog) {
		return sysLogMapper.insertSelective(sysLog) < 1 ? false : true;
	}
	
}
