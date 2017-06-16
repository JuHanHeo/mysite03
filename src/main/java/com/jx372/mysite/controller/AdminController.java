package com.jx372.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jx372.mysite.service.FileUploadService;
import com.jx372.mysite.service.SiteService;
import com.jx372.mysite.service.UserService;
import com.jx372.mysite.vo.SiteVo;
import com.jx372.security.Auth;

@Auth(role=Auth.Role.ADMIN)
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserService userService;
	@Autowired
	SiteService siteService;
	@Autowired
	private FileUploadService fileuploadService;
	
	@RequestMapping(value={"/main","/"}, method=RequestMethod.GET)
	public String main(Model model){
		
		model.addAttribute("siteVo", siteService.get());
		
		return "admin/main";
	}
	
	@RequestMapping(value={"/main","/"}, method=RequestMethod.POST)
	public String main(SiteVo siteVo, @RequestParam(value="file1") MultipartFile file1, Model model){
		
		String url1 = fileuploadService.restore(file1);
		siteVo.setProfileImg(url1);
		siteService.update(siteVo);
		return "redirect:/admin/main";
	}
	
	@RequestMapping("/board")
	public String board(){
		
		return "admin/board";
	}
	
	@RequestMapping("/guestbook")
	public String guestBook(){
		
		return "admin/guestbook";
	}
	
	
	@RequestMapping("/user")
	public String user(Model model){
		model.addAttribute("list", userService.getList());
		return "admin/user";
	}
}
