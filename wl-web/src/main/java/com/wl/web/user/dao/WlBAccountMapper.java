package com.wl.web.user.dao;

import org.apache.ibatis.annotations.Param;

import com.wl.web.user.model.User;
import com.wl.web.user.model.WlBAccount;

public interface WlBAccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WlBAccount record);

    int insertSelective(WlBAccount record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WlBAccount record);

    int updateByPrimaryKey(WlBAccount record);
    
    User selectByUsername(@Param("username")String username);
}