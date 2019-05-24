package com.wl.web.config.jwt;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.wl.fms.common.entity.ErrorCode;
import com.wl.fms.common.entity.RestResult;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // This is invoked when user tries to access a secured REST resource without supplying any credentials
        // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    	response.setCharacterEncoding("UTF-8");
//    	response.getWriter().println(JSONObject.toJSONString(RestResult.error(ErrorCode.ERROR_TOKEN_ERROR)));
    	
    	response.setContentType("application/json");
    	response.setHeader("Content-type", "application/json;charset=UTF-8");  
        OutputStream ps = response.getOutputStream();  
        //这句话的意思，使得放入流的数据是utf8格式  
        ps.write(JSONObject.toJSONString(RestResult.error(ErrorCode.ERROR_TOKEN_ERROR)).getBytes("UTF-8"));
    	
    }
}