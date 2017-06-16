package com.jx372.mysite.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jx372.mysite.service.BoardService;
import com.jx372.mysite.vo.BoardVo;
import com.jx372.security.Auth;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping("/list")
	public String list(@RequestParam(value="p", required=true ,defaultValue="1") int p, @RequestParam(value="kwd", required=true, defaultValue="%") String kwd ,Model model){
		
		model.addAttribute("count", boardService.getCount(kwd));
		model.addAttribute("list", boardService.getList(p, kwd));
		model.addAttribute("kwd", kwd);
//		model.addAttribute("p", p);
		
		return "/board/list";
	}
	
	@RequestMapping("/view")
	public String view(@RequestParam("no") int no, Model model){
		boardService.addHit(no);
		model.addAttribute("vo", boardService.getByNo(no));
		
		return "/board/view";
	}
	@Auth
	@RequestMapping(value ="/modify", method=RequestMethod.GET)
	public String modify(@RequestParam("no") int no, Model model){
		
		model.addAttribute("vo", boardService.getByNo(no));
		
		return "/board/modify";
	}
	
	@Auth
	@RequestMapping(value ="/modify", method=RequestMethod.POST)
	public String modify(@ModelAttribute BoardVo vo){
		boardService.update(vo);
		int p = vo.getNo();
		return "redirect:/board/view?no="+p;
	}
	
	@Auth
	@RequestMapping(value = "/write", method=RequestMethod.GET)
	public String writefrom(@RequestParam(value="pgno", required=true, defaultValue="-1") int pgno, @RequestParam(value="pono", required=true, defaultValue="-1") int pono, @RequestParam(value="pdepth", required=true, defaultValue="-1") int pdepth){
		return "/board/write";
	}
	@Auth
	@RequestMapping(value = "/write", method=RequestMethod.POST)
	public String writefrom(@ModelAttribute BoardVo vo, Model model){
		
		boardService.insert(vo);
		int v = boardService.lastInsert();
		
		model.addAttribute("vo", boardService.getByNo(v));
		return "/board/view";
	}
	@Auth
	@RequestMapping(value = "/delete")
	public String delete(@RequestParam("no") int no){
		boardService.delete(no);
		return "redirect:/board/list";
	}

}
