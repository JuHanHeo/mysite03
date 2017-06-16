package com.jx372.mysite.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx372.mysite.service.UserService;

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
	public Map<String, Object> checkEmail(@RequestParam(value="email", required=true, defaultValue="") String email){
		
		boolean exist = userService.existEamil(email);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", exist);
		return map;
	}
}
