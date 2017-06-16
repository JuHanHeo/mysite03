package com.jx372.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jx372.mysite.repository.BoardDao;
import com.jx372.mysite.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	BoardDao boardDao;
	
	public List<BoardVo> getList(int start, String kwd){
		
		return boardDao.getList(start, kwd);
		
	}
	public int getCount(String kwd){
		return boardDao.getCount(kwd);
	}
	
	public BoardVo getByNo(int no){
		return boardDao.get(no);
	}
	
	public boolean update(BoardVo vo){
		return boardDao.update(vo);
	}
	public int lastInsert(){
		return boardDao.lastInsert();
	}
	
	public boolean insert(BoardVo vo){
		if(vo.getGroupNo()!=-1){
			boardDao.updateOrderNo(vo);
		}
		return boardDao.insert(vo);
	}
	
	public boolean delete(int no){
		return boardDao.delete(no);
	}
	
	public boolean addHit(int no){
		return boardDao.addHit(no);
	}
}
