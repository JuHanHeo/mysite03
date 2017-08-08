package com.jx372.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jx372.mysite.repository.GuestBookDao;
import com.jx372.mysite.vo.GuestBookVo;

@Service
public class GuestBookService {
	
	@Autowired
	private GuestBookDao guestBookDao;
	
	public List<GuestBookVo> list(){
		List<GuestBookVo> list = guestBookDao.getList();
		return list;
	}
	public boolean insert(GuestBookVo vo){
		boolean result = guestBookDao.insert(vo);
		return result;
	}
	
	public boolean delete(GuestBookVo vo){
		boolean bool = guestBookDao.delete(vo);
		return bool;
	}
	public List<GuestBookVo> list(int startNo) {
		List<GuestBookVo> list = guestBookDao.getList(startNo);
		return list;
	}

}
