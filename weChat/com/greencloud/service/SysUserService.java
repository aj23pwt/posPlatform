package com.greencloud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.greencloud.config.BusinessException;
import com.greencloud.dao.SysUserMapper;
import com.greencloud.dao.vo.SysUser;

/** 
 */

@Service
public class SysUserService {
	
	private static Logger logger = LoggerFactory.getLogger(SysUserService.class);
	
	@Autowired
	SysUserMapper sysUserMapper;

	@Cacheable(value = "sysUser",key = "#sysUser.account + #sysUser.password")
	public SysUser selectByAccountAndPassword(SysUser sysUser) {
		sysUser = sysUserMapper.selectByAccountAndPassword(sysUser);
		if(null == sysUser){
			logger.debug("账号或密码错误！");
			throw new BusinessException("账号或密码错误！");
		}
		return sysUser;
	}

	@Transactional
	@CacheEvict(value = "sysUser",allEntries = true)
	public boolean updateSysUser(SysUser sysUser) {
		if(sysUserMapper.updateByPrimaryKeySelective(sysUser) < 1){
			throw new BusinessException("账户信息修改失败！");
		}
		return true;
	}

	/**
	 * 锁定用户信息（锁定） 锁定标志(1:锁定;0:激活)
	 * @param sysUser
	 * @return
	 */
	@Transactional
	@CacheEvict(value = "sysUser",allEntries = true)
	public boolean lockSysUser(SysUser sysUser) {
		sysUser.setLocked("1");
		if(sysUserMapper.updateByPrimaryKeySelective(sysUser) < 1){
			throw new BusinessException("账户信息锁定失败！");
		}
		return true;
	}

	@Transactional
	public boolean addSysUser(SysUser sysUser) {
		if(sysUserMapper.insertSelective(sysUser) < 1){
			throw new BusinessException("账户信息添加失败！");
		}
		return true;
	}

	/**
	 * 解除锁定用户信息    锁定标志(1:锁定;0:激活)
	 * @param sysUser
	 * @return
	 */
	@Transactional
	@CacheEvict(value = "sysUser",allEntries = true)
	public boolean unLockSysUser(SysUser sysUser) {
		sysUser.setLocked("0");
		if(sysUserMapper.updateByPrimaryKeySelective(sysUser) < 1){
			throw new BusinessException("账户信息解除锁定失败！");
		}
		return true;
	}

}
