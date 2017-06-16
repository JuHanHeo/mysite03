package com.jx372.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthLogoutInterceptor extends HandlerInterceptorAdapter {

	private static final Log LOG = LogFactory.getLog( AuthLogoutInterceptor.class );
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		if(session==null || session.getAttribute("authUser")==null){
			response.sendRedirect(request.getContextPath()+"/main");
			return false;
		}
		
		
		LOG.info("LOGOUT - " + session.getAttribute("authUser").toString());
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		response.sendRedirect(request.getContextPath()+"/main");
		return false;
	}

}
