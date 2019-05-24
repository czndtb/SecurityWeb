package com.wl.web.user.dao;

import org.apache.ibatis.annotations.Param;

import com.wl.web.user.model.WlBRoleAccount;

public interface WlBRoleAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WlBRoleAccount record);

    int insertSelective(WlBRoleAccount record);

    WlBRoleAccount selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WlBRoleAccount record);

    int updateByPrimaryKey(WlBRoleAccount record);
    
    WlBRoleAccount selectUserWithRoles(@Param("username")String username);
}