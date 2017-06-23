package com.jx372.mysite.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jx372.mysite.service.GuestBookService;
import com.jx372.mysite.vo.GuestBookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {
	
	@Autowired
	private GuestBookService guestBookService;
	
	@RequestMapping("/list")
	public String list(Model model){
		
		model.addAttribute("list", guestBookService.list());
		
		return "/guestbook/list";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public String list(@ModelAttribute GuestBookVo vo){
		guestBookService.insert(vo);
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping("/deleteform")
	public String deleteform(@RequestParam("no") String no){
		return "/guestbook/deleteform";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("no") String no){
		
		guestBookService.delete(no);
		
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping("/ajax")
	public String ajax(Model model){
		model.addAttribute("list", guestBookService.list());
		return "/guestbook/index-ajax";
	}
	
	@RequestMapping(value="/ajax", method=RequestMethod.POST)
	public String ajax(@ModelAttribute GuestBookVo vo){
		guestBookService.insert(vo);
		return "redirect:/guestbook/ajax";
	}
	
	
	

}
