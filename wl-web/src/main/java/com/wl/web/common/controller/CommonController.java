package com.wl.web.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wl.fms.common.entity.RestResult;
import com.wl.web.common.model.WlBCountries;
import com.wl.web.common.service.CommonService;

@RestController
@RequestMapping("/common")
public class CommonController {

	@Autowired
	private CommonService commonService;

	@GetMapping("/countries")
	public RestResult<List<WlBCountries>> countries(){
		return RestResult.ok(commonService.getCountries());
	}
}
