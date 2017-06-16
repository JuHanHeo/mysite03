package com.jx372.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor01 implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		System.out.println("MyInterceptor01.preHandle()");
		return true;
		//false를 리턴하면 컨트롤러로 안넘어 간다
	}
	
	
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("MyInterceptor01.postHandle()");
		//컨트롤러가 실행되고 실행
	}
	

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("MyInterceptor01.afterCompletion()");
		//jsp가지 다 부르고 실행
	}



	

}
