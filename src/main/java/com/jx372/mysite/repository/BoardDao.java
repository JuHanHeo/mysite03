package com.jx372.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jx372.mysite.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	SqlSession sqlsession;
	
	
	private Connection getConnection() throws SQLException{

		Connection conn = null;


		//1. 드라이버 로딩
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//2. Connection 하기
			String url ="jdbc:mysql://192.168.1.37:3306/webdb?useUnicode=true&characterEncoding=utf8";
			conn = DriverManager.getConnection(url,"webdb","webdb");
			return conn;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 

	}
	public int getCount(String kwd){
		if(kwd==null){
			kwd="%";
		}else{
			kwd = "%"+kwd+"%";
		}
		return sqlsession.selectOne("board.getCount", kwd);
	}
	
	public List<BoardVo> getList(int start, String kwd){
		Map<String, Object> map = new HashMap<String, Object>();
		if(kwd==null){
			kwd="%";
		}else{
			kwd = "%"+kwd+"%";
		}
		start=(start-1)*5;
		map.put("kwd", kwd);
		map.put("start", start);
		List<BoardVo> list = sqlsession.selectList("board.getList", map);
 		return list;
	}

	public BoardVo get(int no){

		return sqlsession.selectOne("board.getByNo", no);
	}

	public boolean update(BoardVo vo){
		int count = sqlsession.update("board.update", vo);
		return (count==1);
	}

	public boolean addHit(int no){
		int count=sqlsession.update("board.addHit", no);
		return (count==1);
	}
	public int lastInsert(){
		return sqlsession.selectOne("board.lastInsertId");
	}

	public boolean insert(BoardVo vo){
		int count = sqlsession.insert("board.insert", vo);
		return (count==1);
	}

	public boolean updateOrderNo(BoardVo vo){

		int count = sqlsession.update("board.updateOrderNo", vo);
		return (count==1);
	}

	public boolean delete(int no){
		int count = sqlsession.update("board.delete", no);
		return (count==1);
	}

	public List<BoardVo> search(String kwd){
		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			conn = getConnection();

			sql = "select b.no, b.title, b.content, b.reg_date, b.hit, b.group_no, b.order_no, b.depth, b.user_no, m.name from board b, member m where m.no = b.user_no and ( b.title like ? or b.content like ? )  order by b.group_no desc, order_no  ";

			pstmt = conn.prepareStatement(sql);

			kwd = "%" + kwd + "%";
			pstmt.setString(1, kwd);
			pstmt.setString(2, kwd);
			rs = pstmt.executeQuery();

			while(rs.next()){
				int no = rs.getInt(1);	
				String title = rs.getString(2);
				String content = rs.getString(3);
				String regDate = rs.getString(4);
				int hit = rs.getInt(5);
				int groupNo = rs.getInt(6);
				int orderNo = rs.getInt(7);
				int depth = rs.getInt(8);
				int userNo = rs.getInt(9);
				String userName = rs.getString(10);


				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setRegDate(regDate);
				vo.setHit(hit);
				vo.setGroupNo(groupNo);
				vo.setOrderNo(orderNo);
				vo.setDepth(depth);
				vo.setUserNo(userNo);
				vo.setUserName(userName);
				list.add(vo);
			}

			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
