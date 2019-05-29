package com.wl.web.user.dao;

import com.wl.web.user.model.WlBAccess;

public interface WlBAccessMapper {
	int deleteByPrimaryKey(Long id);

	int insert(WlBAccess record);

	int insertSelective(WlBAccess record);

	WlBAccess selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(WlBAccess record);

	int updateByPrimaryKey(WlBAccess record);
}