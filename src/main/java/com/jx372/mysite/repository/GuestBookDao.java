package com.jx372.mysite.repository;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.jx372.mysite.vo.GuestBookVo;

@Repository
public class GuestBookDao {
	@Autowired
	SqlSession sqlSession;
//	@Autowired
//	DataSource dataSource;
	
	public boolean insert(GuestBookVo vo){
		int count = sqlSession.insert("guestbook.insert", vo);
		return (count==1);
	}
	
	public List<GuestBookVo> getList(){
		List<GuestBookVo> list = sqlSession.selectList("guestbook.getlist");
		return list;
	}
	
	public int delete(String no){
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("no", no);
		int ino = Integer.parseInt(no);
		int count = sqlSession.update("guestbook.delete", ino);
		return count;
	}

}
