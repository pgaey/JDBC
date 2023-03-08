package com.kh.statement.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	
	
	
	// JDBC 과정 중 반복적으로 스이는 구문들을 각각의 메소드로 정의해둘 곳
	// "재사용할 목적"으로 공통 템플릿 작업 진행
	
	// 이클래스에서 모든 메소드들 전부 다 static 메소드로 만들 것!
	
	// 공통적인 부분 뽑아내기!
	// 1. DB와 접속된 Connection객체를 생성해서 반환시켜주는 메소드
	public static Connection getConnection() {
		
		// Connection객체를 담을 그릇 만들기
		Connection conn =null;
		
		try {
			// 1, 2) => 연결시키기
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
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
