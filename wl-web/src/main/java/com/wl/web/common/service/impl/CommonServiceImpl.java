package com.wl.web.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wl.web.common.dao.WlBCountriesMapper;
import com.wl.web.common.model.WlBCountries;
import com.wl.web.common.service.CommonService;

@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private WlBCountriesMapper wlBCountriesMapper;

	@Override
	public List<WlBCountries> getCountries() {
//		List<WlBCountries> list = wlBCountriesMapper.selectAll();
//		
//		list.forEach(c->{
////			c.setCountriesImgPath(c.getCountriesImgPath().replace("", newChar));
//			wlBCountriesMapper.updateByPrimaryKeySelective(c);
//		});
		
		
		return wlBCountriesMapper.selectAll();
	}

}
