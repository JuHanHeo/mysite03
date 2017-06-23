package com.jx372.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jx372.mysite.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	SqlSession sqlsession;

	public boolean insert(GalleryVo vo) {
		int count = sqlsession.insert("gallery.insert", vo);
		return (count==1);
	}

	public List<GalleryVo> list() {
		return sqlsession.selectList("gallery.list");
	}

	public boolean delete(int no) {
		int count = sqlsession.delete("gallery.delete", no);
		return (count==1);
	}
}
