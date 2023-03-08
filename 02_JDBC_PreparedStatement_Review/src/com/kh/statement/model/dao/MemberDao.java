package com.kh.statement.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.statement.model.vo.Member;

/*
 * DAO(Data Access Object)
 * 
 * 데이터베이스 관련된 작업(CRUD)을 전문적으로 담당하는 객체
 * DAO안에 모든 메소드를 데이터베이스와 관련된 작업으로 만들 것
 * 
 * 	CRUD - CREATE, READ, UPDATE, DELETE		영속성작업.
 * 
 * SQL
 * SELECT / DML
 * 
 * Controller를 통해서 호출된 기능을 수행
 * DB에 직접적으로 접근한 후 해당 SQL문을 실행 및 결과 받기(JDBC)
 * 
 */


public class MemberDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String gcon = "jdbc:oracle:thin:@localhost:1521:xe";
	
	public int insertMember(Member m) {
		
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO MEMBER "
				   + "VALUES (SEQ_USERNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);     
			pstmt.setString(1, m.getUserId());		// 1
			pstmt.setString(2, m.getUserPwd());		// 2
			pstmt.setString(3, m.getUserName());	// 3
			pstmt.setString(4, m.getGender()); 		// 4
			pstmt.setInt(5, m.getAge());			// 5
			pstmt.setString(6, m.getEmail()); 		// 6
			pstmt.setString(7, m.getPhone());		// 7
			pstmt.setString(8, m.getAddress()); 	// 8
			pstmt.setString(9, m.getHobby());		// 9
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	public ArrayList<Member> selectAll(){
		
		ArrayList<Member> list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM MEMBER";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(gcon, "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Member m = new Member();
				
				m.setUserNo(rset.getInt("USERNO"));
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("USERPWD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setenrollDate(rset.getDate("ENROLLDATE"));
				
				list.add(m);
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	public Member selectByUserId(String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = new Member();
		
		String sql = "SELECT * FROM MEMBER WHERE USERID = ?";
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(gcon, "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				m.setUserNo(rset.getInt("USERNO"));
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("USERPWD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setenrollDate(rset.getDate("ENROLLDATE"));
			} 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return m;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
