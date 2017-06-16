package com.jx372.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jx372.mysite.vo.SiteVo;

@Repository
public class SiteDao {
	@Autowired
	SqlSession sqlsession;
	
	public SiteVo get(){
		SiteVo siteVo = new SiteVo();
		
		siteVo = sqlsession.selectOne("site.get");
		
		return siteVo;
	}

	public boolean update(SiteVo siteVo) {
		// TODO Auto-generated method stub
		int count = sqlsession.update("site.update", siteVo);
		return (count==1);
	}
}
