package com.jx372.mysite.repository;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.jx372.mysite.exception.UserDaoException;
import com.jx372.mysite.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	public UserVo get(String email){
		return sqlSession.selectOne("user.getByEmail", email);
	}
	
	public boolean insert(UserVo vo){
		int count = sqlSession.insert("user.insert", vo);
		return (count==1);
	}
	public UserVo get(int no){
		
		//쿼리 결과를 담을 vo가 없는경우 다음과 같이 사용 
//		Map map = sqlSession.selectOne("user.getByNo", no);
//		List<Map> list = sqlSession.selectList("user.getByNo", no);
//		System.out.println(map.get("no"));
		UserVo vo = sqlSession.selectOne("user.getByNo", no);
		return vo;

	}

	//	로그인처리
	public UserVo get(UserVo vo) throws UserDaoException{
		UserVo user = sqlSession.selectOne("user.get", vo);

		return user;
	}

	//비밀번호도 같이 수정
	public boolean update(UserVo vo){
		int count = sqlSession.update("user.update", vo);
		return (count==1);
	}

	public List<UserVo> getList() {
		// TODO Auto-generated method stub
		List<UserVo> list = sqlSession.selectList("user.getList");
		return list;
	}

}
