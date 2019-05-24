package com.wl.web.user.dao;

import com.wl.web.user.model.WlBRoleUrl;

public interface WlBRoleUrlMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WlBRoleUrl record);

    int insertSelective(WlBRoleUrl record);

    WlBRoleUrl selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WlBRoleUrl record);

    int updateByPrimaryKey(WlBRoleUrl record);
}