package com.wl.web.config.security;

public class AuthenticationException extends RuntimeException {
    
	/**
	 * @Fields serialVersionUID : TODO（用一句话描述这个变量表示什么）
	 */
	
	private static final long serialVersionUID = -7066567694246047679L;

	public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
