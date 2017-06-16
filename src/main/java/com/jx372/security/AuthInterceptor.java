package com.jx372.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jx372.mysite.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//1.핸들러 종류
		
		if(handler instanceof HandlerMethod == false){
			//디폴트 핸들러라는 뜻
			return true;
		}
		
		//2. 메소드에 @Auth가 붙어 있는지 확인
		Auth auth = ((HandlerMethod)handler).getMethodAnnotation(Auth.class);
		
		//3. 메소드에 @Auth가 붙어 있지 않으면
		if(auth == null){
			//4 클래스에 @Auth가 붙어 있는지 확인
			auth =  ((HandlerMethod)handler).getMethod().getDeclaringClass().getAnnotation(Auth.class);
			if(auth==null){
				return true;
			}
		}
		

		
		
		//5. 접근제어
		HttpSession session = request.getSession();
		if(session == null || session.getAttribute("authUser")==null){
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		//6.인증된 사용자 - 롤 체크
		Auth.Role role = auth.role();
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(role==Auth.Role.ADMIN && authUser.getRole().equals("ADMIN")==false){
			return false;
		}
		
		return true;
	}

	
}
