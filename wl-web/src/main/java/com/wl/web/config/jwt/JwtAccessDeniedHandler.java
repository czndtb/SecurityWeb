package com.wl.web.config.jwt;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.wl.fms.common.entity.ErrorCode;
import com.wl.fms.common.entity.RestResult;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AccessDeniedException e) throws IOException, ServletException {
		
		httpServletResponse.setCharacterEncoding("UTF-8");
    	
		httpServletResponse.setContentType("application/json");
		httpServletResponse.setHeader("Content-type", "application/json;charset=UTF-8");  
        OutputStream ps = httpServletResponse.getOutputStream();  
        //这句话的意思，使得放入流的数据是utf8格式  
        ps.write(JSONObject.toJSONString(RestResult.error(ErrorCode.ACCESS_DENIED)).getBytes("UTF-8"));
		
	}
}
