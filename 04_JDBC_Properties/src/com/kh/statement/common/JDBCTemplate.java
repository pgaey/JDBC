package com.kh.statement.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	
	
	// JDBC 과정 중 반복적으로 스이는 구문들을 각각의 메소드로 정의해둘 곳
	// "재사용할 목적"으로 공통 템플릿 작업 진행
	
	// 이클래스에서 모든 메소드들 전부 다 static 메소드로 만들 것!
	
	// 공통적인 부분 뽑아내기!
	// 1. DB와 접속된 Connection객체를 생성해서 반환시켜주는 메소드
	public static Connection getConnection() {
		
		// Connection객체를 담을 그릇 만들기
		Connection conn = null;
		/*
		 * 기존의 방식 : JDBC Driver구문, 내가 접속할 DB의 url정보, 계정명, 비밀번호
		 * 				자바소스코드 내부에 명시적으로 작성함 => 정적코딩방식(하드코딩)
		 * 
		 * - 문제접 : DBMS가 변경되었을 경우 / 접속할 url, 계정명, 비밀번호가 변경되었을경우
		 * 			=> 자바소스코드를 수정해야 함
		 * 			수정한 코드를 적용하려고 하면 프로그램을 재구동해야함
		 * 			+ 유지보수가 불편함
		 * 
		 * - 해결방법 : DB관련된 정보들을 별도로 관리하는 외부파일(driver.properties)을 만들어서 관리
		 * 				외부파일에 key에 대한 value로 읽어들여서 반영시킬 것 => 동적코딩방식
		 *  
		 * 
		 */
		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("resources/driver.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			// 1, 2) => 연결시키기
//			Class.forName("oracle.jdbc.driver.OracleDriver");			
			Class.forName(prop.getProperty("driver"));
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			conn = DriverManager.getConnection(prop.getProperty("url"), 
											   prop.getProperty("usernamd"), 
											   prop.getProperty("JDBC"));
			conn.setAutoCommit(false); // 예외가 발생할 때 자동으로 커밋해주는 메소드. 끔
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	// 2. Connection객체를 트랜잭션처리를 해주는 메소드
	// 2_1) 전달받은 Connection 객체를 가지고 COMMIT 시켜주는 메소드
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 2_2) 전달받은 Connection 객체를 가지고 ROLLBACK 시켜주는 메소드
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// 3) JDBC용 객체를 반납시켜주는 메소드(각 객체별로)
	// 3_1) Connection객체를 전달받아서 반납시켜주는 메소드
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 3_2) Statement 객체를 전달받아서 반납시켜주는 메소드(오버로딩)
	// => 다형성 적용으로 인해 PreparedStatement 객체도 Statement타입으로 받을 수 있음
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 3_3) ResultSet객체를 전달받아서 반납시켜주는 메소드(오버로딩)
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
