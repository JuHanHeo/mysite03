package com.jx372.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//주로 HandlerInterceptorAdapter을 상속/오버라이드 해서 인터셉터 사용
public class MyInterceptor02 extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MyInterceptor02.preHandle()");
		return true;
	}
	

}
