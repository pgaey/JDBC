package com.kh.text;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestRun {

	public static void main(String[] args) {
		/*
		// 사용자에게 값을 입력받아서 DBMS로 전달 테이블에 INSERT
		
		Scanner sc = new Scanner(System.in);
		System.out.println("번호를 입력해주세요");
		int num = sc.nextInt();
		sc.nextLine();
		System.out.println("이름을 입력해주세요");
		String name = sc.nextLine();
		
		
		
		// JDBC맛보기!!
		// 필요한 변수 먼저 셋팅!
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		// 첫 번째 단계 끝!!
		
		// 실행 SQL문 ("완성형태로" 만들기)
		// INSERT INTO TEST(테이블명) VALUES(1, '이승철', SYSDATE);
		
		//String sql = "INSERT INTO TEST VALUES(1, '이승철', SYSDATE)";    // SQL문에 세미콜론 적지 않음.
		String sql = "INSERT INTO TEST VALUES(" + num + ", '" + name + "', SYSDATE)";
		// 두 번째 단계 끝!!
		
		try {
			// 1) JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("driver 등록 성공!!");
			
			// 2) Connection 객체 생성
			// DB에 연결(url, 계정명, 비밀번호)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");		// localhost 대신 127.0.0.1 가능
			System.out.println("Connection 성공!!");
			
			// 3) Statement 객체 생성
			stmt = conn.createStatement();
			System.out.println("Statement 객체 생성!!");
			
			// 4) SQL쿼리문을 날려서 실행 후 결과받기(처리된 행 수)
			result = stmt.executeUpdate(sql);
			
			
			// 내가 실행할 SQL문이 DML문(INSERT, UPDATE, DELETE)일 경우
			// => stmt.executeUpdate("DML문") : int
			
			// 5) 트랜잭션 처리
			if(result > 0) {				// 성공했을 경우
				conn.commit();
			} else {						// 실패했을 경우
				conn.rollback();
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(result > 0) {
			System.out.println("insert 성공!");
		} else {
			System.out.println("insert 실패!");
		}
		
		*/
		
		// 2. 내 로컬 PC DBMS상 JDBC계정이 가지고 있는  TEST테이블에 모든 행 조회해보기
		// SELECT문
		// => ResultSet(조회된 데이터들 행들의 집합) 받기
		// => ResultSet으로부터 데이터 뽑기
		
		// 필요한 변수들 셋팅
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		// 1단계 끝
		
		String sql = "SELECT TNO, TNAME, TDATE FROM TEST";
		
		
		try {
			// 1) JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 3) Statement 객체 생성
			stmt = conn.createStatement();
			
			// 4) SQL문을 전달해서 실행 후 결과받기(ResultSet)
			rset = stmt.executeQuery(sql);
			
			// 내가 실행할 SQL문이 SELECT문일 경우
			// stmt.executeQuery("SELECT문") : ResultSet
			
			// 5) 
			while(rset.next()) {
				// rset.next 커서를 움직여주는 역할
				// 해당 행이 존재한다면 true / 없으면 false
				
				int tNo = rset.getInt("TNO");
				String tName = rset.getString("TNAME");
				Date date = rset.getDate("TDATE");
				
				System.out.println(tNo + ", " + tName + ", " + date);
			}
			
			
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 등록 오타");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("커넥션에 문제있대 ~ ");
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
