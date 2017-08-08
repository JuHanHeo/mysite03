package com.jx372.mysite.exception;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jx372.mysite.dto.JSONResult;

@ControllerAdvice
public class GlobalExceptionHandler {

	//	모든 예외를 여기서 다 받는다
	@ExceptionHandler(Exception.class)
	public void handlerException(HttpServletRequest request, HttpServletResponse response,Exception e) throws ServletException, IOException{
		//1.로깅
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));

		//2.요청 종류 알아내기 (웹/ajax)
		String accept = request.getHeader("accept");
		System.out.println(accept);

		//3.응답
		if(accept.matches(".*application/json.*")==true){
			//json 요청
			System.out.println("json error~~~~~~~~~~~~~~");
			response.setStatus( HttpServletResponse.SC_OK );

			OutputStream out = response.getOutputStream();

			out.write( new ObjectMapper().writeValueAsString( JSONResult.error( errors.toString() ) ).getBytes() );
			out.flush();
			out.close();
		} else{	
			//web 요청
			//안내페이지
			//			ModelAndView mav=new ModelAndView();
			//			mav.setViewName("error/exception");
			//			mav.addObject("exception", errors.toString());
			//			System.out.println(e);
			request.setAttribute( "url" , request.getRequestURI() );
			request.setAttribute( "exception", errors.toString() );
			request.getRequestDispatcher( "/WEB-INF/views/error/exception.jsp" ).forward( request, response );
		}

		//2. 안내페이지 가지


	}
}
