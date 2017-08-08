package com.jx372.mysite.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx372.mysite.dto.JSONResult;
import com.jx372.mysite.service.UserService;
import com.jx372.mysite.vo.UserVo;

// user에 대한 요청을 json으로 응답하는 controller
// @controller("bean id명") 설정 ..없으면 앞글자 소문자로 한 클래스명으로 id를  쓴다
// mysite.controller에 있는 usercontroller와 클래스 명이 같아서 bean객체 생성시 중복 에러 난다.
@Controller("userApiController")
@RequestMapping("/user/api")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@ResponseBody
	@RequestMapping("/checkemail")
	public JSONResult checkEmail(@RequestParam(value="email", required=true, defaultValue="") String email){
		
		boolean exist = userService.existEamil(email);
		return JSONResult.success(exist);
	}
	@ResponseBody
	@RequestMapping( value="/join", method=RequestMethod.POST )
	public JSONResult join( @ModelAttribute @Valid UserVo userVo, BindingResult result, Model model ){
		return JSONResult.success(userService.join( userVo ));
	}
}
