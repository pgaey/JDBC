package com.kh.statement.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.statement.common.JDBCTemplate;
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
	
	public int insertMember(Connection conn, Member m) {
		// INSERT문 => 처리된 행의 수 => 트랜잭션처리
		// 0) 필요한 변수들 먼저 셋팅
		int result = 0; // 처리된 결과(행의 개수) 를 담아줄 변수
		PreparedStatement pstmt = null; // SQL문 실행 후 결과를 받기 위한 변수
		String sql = "INSERT INTO MEMBER "
				   + "VALUES(SEQ_USERNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
		
		try {
			// 3_1) PreparedStatement객체 생성(SQL문 미리 넘기기!)
			pstmt = conn.prepareStatement(sql);
			
			// 3_2) 미완성된 SQL문일 경우 완성시켜주기
			// pstmt.setXXX(?의 위치, 실제값);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			
			// 4, 5) DB에 완성된 SQL문을 실행 후 결과(int) 받기
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		// 8) 결과반환
		return result;
	}
	
	public ArrayList<Member> selectAll(Connection conn){
		
		// 0) 필요한 변수들 셋팅
		// 조회된 결과를 뽑아서 담아줄 변수 => ArrayList(여러 회원의 정보)
		ArrayList<Member> list = new ArrayList();
		
		// PreparedStatement, ResultSet
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 수행문
		String sql = "SELECT "
				          + "USERNO, "
				          + "USERID, "
				          + "USERPWD, "
				          + "USERNAME, "
				          + "GENDER, "
				          + "AGE, "
				          + "EMAIL, "
				          + "PHONE, "
				          + "ADDRESS, "
				          + "HOBBY, "
				          + "ENROLLDATE "
				     + "FROM "
				          + "MEMBER "
				    + "ORDER BY USERNAME ASC";
		
		try {
			// 3_1) PreparedStatement 객체 생성(SQL문을 미리 같이 넘겨줘야함!!)
			pstmt = conn.prepareStatement(sql);
			
			// 3_2) 미완성된 SQL문이라면 완성시켜주기!! => 스킵!!
			
			// 4, 5) SQL문(SELECT)을 실행 후 결과(ResultSet)받기
			rset = pstmt.executeQuery();
			
			// 6_1) 현재 조회결과 담긴 ResultSet에서 한 행씩 뽑아서 VO객체에 담기
			// rset.next() : 커서를 내린다 행이 있으면 true / 없으면 false
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7) 다 쓴 JDBC용 객체 반납(생성된 순서의 역순)
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		// 8) 결과반환
		return list;
	}
	
	public Member selectByUserId(Connection conn, String userId) {
		
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT "
			     	      + "USERNO, "
			     	      + "USERID, "
			     	      + "USERPWD, "
			     	      + "USERNAME, "
			     	      + "GENDER, "
			     	      + "AGE, "
			     	      + "EMAIL, "
			     	      + "PHONE, "
			     	      + "ADDRESS, "
			     	      + "HOBBY, "
			     	      + "ENROLLDATE "
			         + "FROM "
			     	      + "MEMBER "
			        + "WHERE USERID = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member();
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}
	
	public ArrayList selectByUserName(Connection conn, String keyword) {
		
		ArrayList<Member> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT "
				          + "USERNO, "
				          + "USERID, "
				          + "USERPWD, "
				          + "USERNAME, "
				          + "GENDER, "
				          + "AGE, "
				          + "EMAIL, "
				          + "PHONE, "
				          + "ADDRESS, "
				          + "HOBBY, "
				          + "ENROLLDATE "
				     + "FROM "
				          + "MEMBER "
				          + "WHERE USERNAME LIKE ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
		    pstmt.setString(1, "%" + keyword + "%");
		    rset = pstmt.executeQuery();
			
		    while(rset.next()){
		    	// 쌤이 한거
		    	list.add(new Member(rset.getInt("USERNO"),
		    						rset.getString("USERID"),
		    						rset.getString("USERPWD"),
		    						rset.getString("USERNAME"),
		    						rset.getString("GENDER"),
		    						rset.getInt("AGE"),
		    						rset.getString("EMAIL"),
		    						rset.getString("PHONE"),
		    						rset.getString("ADDRESS"),
		    						rset.getString("HOBBY"),
		    						rset.getDate("ENROLLDATE")));
		    	/*			내가 한거
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
		    	*/
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	
	public int updateMember(Connection conn, Member m) {
		
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE "
				          + "MEMBER "
				      + "SET "
				          + "USERPWD = ?,"
				          + "EMAIL = ?, "
				          + "PHONE = ?, "
				          + "ADDRESS = ? "
				    + "WHERE USERID = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getUserId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public int deleteMember(Connection conn, String userId) {
		
		// 0) 필요한 변수 세팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		// 실행할 SQL문
		String sql = "DELETE FROM MEMBER "
				    + "WHERE "
				          + "USERID = ?";
		
		try {
			// 3_1) PreparedStatement생성 후 미완성 SQL문을 전달
			pstmt = conn.prepareStatement(sql);
			
			// 3_2) PreparedStatement SQL완성
			pstmt.setString(1, userId);
			
			// 4, 5) executeUpdate메소드로 SQL문을 실행하고 결과 값 받기
			result = pstmt.executeUpdate();
			
			// 6) 트랜잭션은 MemberService에서
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7) 자원 반납 :  PreparedStatement 반납
			JDBCTemplate.close(pstmt);
		}
		// 8) 결과 반환
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
