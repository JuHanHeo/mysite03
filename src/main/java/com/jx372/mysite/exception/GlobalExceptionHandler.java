package com.jx372.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
	
//	모든 예외를 여기서 다 받는다
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception e){
		//1.로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		
		//2. 안내페이지 가지
		ModelAndView mav=new ModelAndView();
		mav.setViewName("error/exception");
		mav.addObject("exception", errors.toString());
		System.out.println(e);
		
		return mav;
	}
}
