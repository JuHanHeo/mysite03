package com.jx372.mysite.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx372.mysite.service.GuestBookService;

@Controller("guestbookAPIController")
@RequestMapping("/guestbook/api")
public class GuestBookController {
	
	@Autowired
	GuestBookService guestBookService;
	
	
	@RequestMapping("/list")
	@ResponseBody
	public Object list(){
		return guestBookService.list();
	}
	
	

}
