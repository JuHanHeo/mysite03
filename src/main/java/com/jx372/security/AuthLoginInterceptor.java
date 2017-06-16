package com.jx372.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jx372.mysite.service.UserService;
import com.jx372.mysite.vo.UserVo;

public class AuthLoginInterceptor extends HandlerInterceptorAdapter{

	//인터셉터도 웹어플리케이션컨텍스트에 있어서 바로 주입 가능
	@Autowired
	private UserService  userService;
	
	private static final Log LOG = LogFactory.getLog( AuthLoginInterceptor.class );
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		//웹어플리케이션컨텍스트에서 루트어플리케이션컨텍스트에 있는 빈 가져오기
//		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
//		UserService userService = ac.getBean(UserService.class);
		
		String email = request.getParameter("email");
		String password = request.getParameter("passwd");
		UserVo logVo = new UserVo();
		logVo.setEmail(email);
		logVo.setPasswd(password);
		
		UserVo userVo = userService.getUser(logVo);
		
		//로그인 실패
		if(userVo == null){
			response.sendRedirect(request.getContextPath()+"/user/login?result=fail");
			return false;
		}
		
		//로그인 처리
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", userVo);
		LOG.info("LOGIN - "+userVo.toString());
		
		response.sendRedirect(request.getContextPath()+"/main");
		
		
		
		return false;
	}
	
	
}
