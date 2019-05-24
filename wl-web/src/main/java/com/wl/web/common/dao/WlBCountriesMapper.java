package com.wl.web.common.dao;

import java.util.List;

import com.wl.web.common.model.WlBCountries;

public interface WlBCountriesMapper {
    int deleteByPrimaryKey(Integer countriesId);

    int insert(WlBCountries record);

    int insertSelective(WlBCountries record);

    WlBCountries selectByPrimaryKey(Integer countriesId);

    int updateByPrimaryKeySelective(WlBCountries record);

    int updateByPrimaryKey(WlBCountries record);
    
    List<WlBCountries> selectAll();
}