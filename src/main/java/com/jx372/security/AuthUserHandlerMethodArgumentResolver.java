package com.jx372.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.jx372.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavVontainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		// TODO Auto-generated method stub
		if(supportsParameter(parameter)==false){
			//해석 대상이 아니면 ??(다른 어노테이션 다른 어노테이션리졸버에게 해석하라고 패스)
			return WebArgumentResolver.UNRESOLVED;
		}
		
		//@AuthUser가 붙어 이고, 파라미터 타입이 UserVo
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		HttpSession session = request.getSession();
		if(session == null){
			return null;
		}
		
		return session.getAttribute("authUser");
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// TODO Auto-generated method stub
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		
		//@AuthUser 안붙어있음
		if(authUser==null){
			return false;
		}
		//파라미터 타입이 UserVo아님
		if(parameter.getParameterType().equals(UserVo.class)==false){
			return false;
		}
		return true;
	}

}
