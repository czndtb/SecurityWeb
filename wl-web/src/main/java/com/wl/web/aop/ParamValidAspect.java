package com.wl.web.aop;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.wl.fms.common.entity.ErrorCode;
import com.wl.fms.common.entity.RestResult;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ParamValidAspect {
@Around("execution(* com.wl.web.*.controller.*.*(..)) && args(..,bindingResult)")
    public Object validateParam(ProceedingJoinPoint pjp, BindingResult bindingResult) throws Throwable {
        Object retVal;
        log.debug("进入AOP-ParamValidAspect-validateParam进行参数有效性校验....");
        if (bindingResult.hasErrors()) {
	        String errorInfo = getErrorsSplitNewLine(bindingResult);
	        log.info("AOP-ParamValidAspect-validateParam进行参数校验出错, 出错信息如下：{}", errorInfo);
      
            retVal = RestResult.error(ErrorCode.ERROR_PARAMS_ERROR.getCode(),errorInfo);
        } else {
        //执行目标方法
            retVal = pjp.proceed();
        }
        return retVal;
    }
	/*
	 * 此校验错误信息转化为字符，多个错误信息通过参数[splitChars]进行分隔
	 */
	private String getErrors(BindingResult br, String splitChars) {
		if(splitChars == null) {
			splitChars = "";
		}
		StringBuilder result = new StringBuilder();
		List<ObjectError> errors = br.getAllErrors();
		for (ObjectError vError : errors) {
			result.append(vError.getDefaultMessage());
			result.append(splitChars);
		}
		if(result.length() > 0) {
			result.delete(result.length() - splitChars.length(), result.length());
		}
		return result.toString();
	}
	
	/*
	 * 此校验错误信息转化为字符，多个错误信息通过<br>进行分隔
	 */
	private String getErrorsSplitNewLine(BindingResult br) {
		return getErrors(br, "<br>");
	}
}