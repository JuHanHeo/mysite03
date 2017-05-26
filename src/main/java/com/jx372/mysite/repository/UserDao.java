package com.jx372.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.jx372.mysite.exception.UserDaoException;
import com.jx372.mysite.vo.UserVo;

@Repository
public class UserDao {
	private Connection getConnection() throws SQLException{

		Connection conn = null;


		//1. 드라이버 로딩
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//2. Connection 하기
			String url ="jdbc:mysql://localhost:3306/webdb?useUnicode=true&characterEncoding=utf8";
			conn = DriverManager.getConnection(url,"webdb","webdb");
			return conn;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return conn;
	}

	public boolean insert(UserVo vo){

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn=getConnection();
			String sql = "insert into member values(null, ?, ?, password(?), ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPasswd());
			pstmt.setString(4, vo.getGender());

			int count = pstmt.executeUpdate();
			return (count==1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(conn!=null){
					conn.close();
				}
			} catch (Exception e2) {
			}
		}

		return false;
	}
	//	수정폼
	public UserVo get(int no){
		Connection conn = null;
		PreparedStatement pstmt = null;
		UserVo vo = null;
		ResultSet rs = null;

		try {

			conn = getConnection();
			String sql = "select no, name, email, gender from member where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, no);

			rs = pstmt.executeQuery();

			if(rs.next()){
				vo = new UserVo();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setEmail(rs.getString(3));
				vo.setGender(rs.getString(4));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null){
					rs.close();
				}
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return vo;

	}

	//	로그인처리
	public UserVo get(String email, String passwd) throws UserDaoException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		UserVo vo = null;
		ResultSet rs = null;

		try {

			conn = getConnection();
			String sql = "select no, name from member where email = ? and passwd = password(?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, email);
			pstmt.setString(2, passwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				vo = new UserVo();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
			}
		} catch (SQLException e) {
			throw new UserDaoException("유저없음");
		} finally {
			try {
				if(rs!=null){
					rs.close();
				}
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return vo;
	}

	//비밀번호도 같이 수정
	public boolean update(UserVo vo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql;

		try {
			conn=getConnection();

			if(vo.getPasswd() == null){
				sql = "update member set name = ?, gender = ? where no = ?";
			} else{
				sql = "update member set name = ?, gender = ?, passwd = password(?) where no = ?";
			}


			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getGender());

			if(vo.getPasswd() != null){
				pstmt.setString(3, vo.getPasswd());
				pstmt.setInt(4, vo.getNo());
			}else{
				pstmt.setInt(3, vo.getNo());
			}

			int count = pstmt.executeUpdate();
			return (count==1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(conn!=null){
					conn.close();
				}
			} catch (Exception e2) {
			}
		}

		return false;
	}

}
