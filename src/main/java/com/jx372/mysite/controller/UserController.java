package com.jx372.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx372.mysite.service.UserService;
import com.jx372.mysite.vo.UserVo;
import com.jx372.security.Auth;
import com.jx372.security.AuthUser;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping( value="/join", method=RequestMethod.GET )
	public String join(@ModelAttribute UserVo userVo){
		return "user/joinform";
	}

	@RequestMapping( value="/join", method=RequestMethod.POST )
	public String join( @ModelAttribute @Valid UserVo userVo, BindingResult result, Model model ){
		if(result.hasErrors()){
			
//			List<ObjectError> list = result.getAllErrors();
//			for (ObjectError e : list) {
//				System.out.println(" ObjectError : " + e );
//			}
			model.addAttribute(result.getModel());
			return "user/joinform";

		}
		//		userService.join( userVo );
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping( value="/login", method=RequestMethod.GET )
	public String login() {
		return "user/loginform";
	}

	//	인터셉터 만들어서 필요 없어졌음
	//	@RequestMapping( value="/login", method=RequestMethod.POST )
	//	public String login(HttpSession session,Model model,@RequestParam( value="email", required=true, defaultValue="" ) String email,@RequestParam( value="passwd", required=true, defaultValue="" ) String password) {
	//		
	//		UserVo userVo = userService.getUser( email, password );
	//		if( userVo == null ) {
	//			model.addAttribute( "result", "fail" );
	//			return "user/loginform";
	//		}
	//		
	//		//인증
	//		session.setAttribute( "authUser", userVo );
	//		return "redirect:/";
	//	}	

	//	@RequestMapping( "/logout" )
	//	public String logout( HttpSession session ) {
	//		session.removeAttribute( "authUser" );
	//		session.invalidate();
	//		return "redirect:/";
	//	}

	@RequestMapping( "/joinsuccess" )
	public String joinsuccess(){
		return "user/joinsuccess";
	}

	@Auth
	@RequestMapping( value="/modify", method=RequestMethod.GET )
	public String modify( @AuthUser UserVo authUser , Model model){
		// 인증여부 체크(접근제한)
		//		if( authUser == null ) {
		//			return "redirect:/user/login";
		//		}
		//		UserVo authUser = (UserVo)session.getAttribute( "authUser" );
		model.addAttribute("user", userService.getUser(authUser.getNo()));

		return "user/modifyform";
	}

	@RequestMapping( value="/modify", method=RequestMethod.POST )
	public String modify(@ModelAttribute UserVo vo){
		userService.update(vo);

		return "redirect:/";
	}

	//	@ExceptionHandler( Exception.class )
	//	public String handleUserDaoException(){
	//		System.out.println("에러발생");
	//		//1. 로깅
	//		//2. 사과 페이지로 안내
	//		return "error/exception";
	//	}
	//	@ResponseBody
	//	@RequestMapping( value="/join", method=RequestMethod.POST )
	//	public String join2( @RequestBody String requestBody ){
	//		return requestBody;
	//	}



}
