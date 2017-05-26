package com.jx372.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jx372.mysite.vo.GuestBookVo;

@Repository
public class GuestBookDao {
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
			return null;
		} 

	}
	
	public boolean insert(GuestBookVo vo){
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn=getConnection();

			String sql = "insert into guestbook values(null, ?, ?, now(), ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getMessage());
			pstmt.setString(3, vo.getPasswd());

			int count = pstmt.executeUpdate();
			return (count==1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public List<GuestBookVo> getList(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		try{
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "select no, name, message, date_format(reg_date,'%Y-%m-%d') from guestbook order by 1 desc";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				int no = rs.getInt(1);	
				String name = rs.getString(2);
				String message = rs.getString(3);
				String date = rs.getString(4);
				
				GuestBookVo vo = new GuestBookVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setMessage(message);
				vo.setDate(date);
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(rs!=null){
					rs.close();
				}
				if(stmt!=null){
					stmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public boolean delete(String no){
		Connection conn = null;
		PreparedStatement pstmt = null;
		System.out.println("삭제할꺼" + no);
		int dno = Integer.parseInt(no);

		try {

			conn=getConnection();

			String sql = "delete from guestbook where no = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dno);
			int count = pstmt.executeUpdate();
			return (count==1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

}
