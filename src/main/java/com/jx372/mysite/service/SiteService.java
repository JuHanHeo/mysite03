package com.jx372.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jx372.mysite.repository.SiteDao;
import com.jx372.mysite.vo.SiteVo;

@Service
public class SiteService {
	
	@Autowired
	SiteDao siteDao;
	public SiteVo get(){
		SiteVo siteVo = siteDao.get();
		return siteVo;
	}
	public boolean update(SiteVo siteVo) {
		return siteDao.update(siteVo);
	}

}
