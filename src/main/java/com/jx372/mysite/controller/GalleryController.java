package com.jx372.mysite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.jx372.mysite.service.FileUploadService;
import com.jx372.mysite.service.GalleryService;
import com.jx372.mysite.vo.GalleryVo;

@Controller
@RequestMapping("/gallery")
public class GalleryController {
	
	
	@Autowired
	GalleryService galleryService;
	@Autowired
	FileUploadService fileUploadService;
	
	@RequestMapping("")
	public String index(Model model){
		model.addAttribute("list", galleryService.list());
		return "gallery/index";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String imgUpload(MultipartFile file, GalleryVo vo){
		String url = fileUploadService.restore(file);
		vo.setUrl(url);
		galleryService.insert(vo);
		
		
		return "redirect:/gallery";
	}
	
	@RequestMapping("/delete/{no}")
	public String delete(@PathVariable("no") int no){
		galleryService.delete(no);
		return "redirect:/gallery";
	}
	
	
	
}
