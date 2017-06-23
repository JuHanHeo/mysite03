package com.jx372.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jx372.mysite.repository.GalleryDao;
import com.jx372.mysite.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	GalleryDao galleryDao;

	public boolean insert(GalleryVo vo) {
		return galleryDao.insert(vo);
	}

	public List<GalleryVo> list() {
		
		return galleryDao.list();
	}

	public boolean delete(int no) {
		return galleryDao.delete(no);
		// TODO Auto-generated method stub
		
	}

}
