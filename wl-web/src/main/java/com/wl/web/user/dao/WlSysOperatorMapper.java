package com.wl.web.user.dao;

import java.util.List;

import com.wl.web.user.model.WlSysOperator;

import io.lettuce.core.dynamic.annotation.Param;

public interface WlSysOperatorMapper {
    int deleteByPrimaryKey(Integer operatorId);

    int insert(WlSysOperator record);

    int insertSelective(WlSysOperator record);

    WlSysOperator selectByPrimaryKey(Integer operatorId);

    int updateByPrimaryKeySelective(WlSysOperator record);

    int updateByPrimaryKey(WlSysOperator record);
    
    List<WlSysOperator> getOperatorsByEmpId(@Param("empId")Integer empId);
}