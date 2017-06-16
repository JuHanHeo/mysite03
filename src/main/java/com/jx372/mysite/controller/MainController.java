package com.jx372.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx372.mysite.service.SiteService;

@Controller
public class MainController {
	
	@Autowired
	SiteService siteService;
	
	// "/" 으로 맵핑 해서 "/mysite03/ 뒤에 뭐가 오든 여기서 처리한다  
	// defuatservlet 으로 갈수 있게 핸들러는 만들어줘야한다
	@RequestMapping({"/main", "/"})
	public String index(Model model){
		
		model.addAttribute("siteVo", siteService.get());
		
		//뷰 리졸버를 등록해서 간단해짐 (기존 : "/WEB-INF/views/main/index.jsp")
		return "/main/index";
	}
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello(){
		return "안녕하세요";
	}

}
