package com.jx372.mysite.controller.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jx372.mysite.dto.JSONResult;
import com.jx372.mysite.service.GuestBookService;
import com.jx372.mysite.vo.GuestBookVo;

@Controller("guestbookAPIController")
@RequestMapping("/guestbook/api")
public class GuestBookController {
	
	@Autowired
	GuestBookService guestBookService;
	
	
	@RequestMapping("/list")
	@ResponseBody
	public JSONResult list(@RequestParam(value="sno", required=true, defaultValue="0") int startNo){
		List<GuestBookVo> list = guestBookService.list(startNo);
		return JSONResult.success(list);
	}
	
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public JSONResult list(@RequestBody GuestBookVo vo){
		guestBookService.insert(vo);
		return JSONResult.success(vo);
	}
//	@ResponseBody
//	@RequestMapping(value="/add", method=RequestMethod.POST)
//	public JSONResult list(@ModelAttribute GuestBookVo vo){
//		guestBookService.insert(vo);
//		return JSONResult.success(vo);
//	}
	
	@ResponseBody
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public JSONResult delete(@ModelAttribute GuestBookVo vo){
		
		boolean  bool = guestBookService.delete(vo);
		System.out.println(vo);
		return JSONResult.success(bool?vo.getNo():-1);
	}
	
	

}
