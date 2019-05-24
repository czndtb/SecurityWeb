package com.wl.web.user.dao;

import com.wl.web.user.model.WlBRole;

public interface WlBRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WlBRole record);

    int insertSelective(WlBRole record);

    WlBRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WlBRole record);

    int updateByPrimaryKey(WlBRole record);
}