package com.jx372.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jx372.mysite.repository.UserDao;
import com.jx372.mysite.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public boolean existEamil(String email){
		UserVo userVo = userDao.get(email);
		return userVo!=null;
		
	}
	
	public void join( UserVo userVo ) {
		//1.DB에 사용정보 저장
		userDao.insert( userVo );
		
		//2. 인증 메일 보내기
	}
	
	public UserVo getUser( UserVo vo ) {
		return userDao.get( vo );
	}
	
	public UserVo getUser( int no ) {
		return userDao.get( no );
	}
	
	public boolean update(UserVo vo){
		return userDao.update(vo);
		
	}
	public List<UserVo> getList(){
		List<UserVo> list = userDao.getList();
		
		return list;
	}
}
