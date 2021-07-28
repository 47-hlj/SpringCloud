package com.pd.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.tags.EscapeBodyTag;

import com.pd.common.ExceptionHandler;

public class PdStoreException implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("exception");
		String message;
		if (ex instanceof StoreException) {
			message = ex.getMessage();
		} else {
			message = "未知错误";
		}
		ExceptionHandler.handle(ex);
		modelAndView.addObject("message", message);
		return modelAndView;
	}

}
