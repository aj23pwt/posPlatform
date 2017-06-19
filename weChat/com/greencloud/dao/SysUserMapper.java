package com.greencloud.dao;

import org.apache.ibatis.annotations.Mapper;

import com.greencloud.dao.vo.SysUser;
@Mapper
public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

	SysUser selectByAccountAndPassword(SysUser sysUser);
}