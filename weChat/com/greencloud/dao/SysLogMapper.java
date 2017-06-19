package com.greencloud.dao;

import org.apache.ibatis.annotations.Mapper;

import com.greencloud.dao.vo.SysLog;
@Mapper
public interface SysLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
}