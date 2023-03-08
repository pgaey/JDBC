package com.kh.statement.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	/*
	 * JDBC용 객체
	 * 
	 * - Connection : DB의 연결정보를 담고 있는 객체(ip주소, port번호, 계정명, 비밀번호)
	 * - (Prepared)Statement : Connection객체가 가지고 있는 DB에 SQL문을 전달하고 실행하고 결과도 받아내는 객체
	 * - ResultSet : 실행한 SQL문이 SELECT문일 경우 조회된 결과들이 처음 반환되는 객체
	 * 
	 * ** Statement(부모)와  PreparedStatement(자식) 관계
	 * 
	 * ** PreparedStatement 특징 : SQL문을 바로 실행하지 않고 잠시 보관하는 개념
	 * 							  미완성된 SQL문을 먼저 전달하고 실행하기 전에 완성 형태로 만든 후 실행만 함
	 * 							  미완성된 SQL문에 사용자가 입력한 값들이 들어갈 수 있는 공간을
	 * 							 ?(위치홀더)로 확보 // 각 위치홀더에 맞는 값들을 셋팅
	 * 
	 * 차이점
	 * 
	 * 1) Statement는 완성된 SQL문, PreparedStatement는 미완성된 SQL문
	 * 
	 * 2) Statement 객체 생성 시			stmt = conn.createStatement();
	 * 	  PreparedStatement 객체 생성시 pstmt = conn.prepareStatement(sql);
	 * 
	 * 3) Statement SQL문 실행 시 			결과 = stmt.executeXXX(sql);
	 * 	  PreparedStatement SQL문 실행 시 
	 * 	  ? 빈 공간을 실제 값으로 채워준 뒤 실행한다.
	 * 
	 *    pstmt.setString(?의 위치, 실제값);
	 *    pstmt.setInt(?의 위치, 실제값);
	 *    결과 = pstmt.executeXXX();
	 *    
	 *    
	 *    
	 *
	 *** JDBC 처리순서
	 *
	 * 1) JDBC Driver등록 : 해당 DBMS가 제공하는 클랙스 등록
	 * 2) Connection 객체 생성 : 접속하고자 하는 DB의 정보를 입력해서 DB에 접속하면서 생성(url, 계정명, 계정비밀번호)
	 * 3_1) PreparedStatement 객체 생성 : Connection객체를 이용해서 생성 (미완성된 SQL문을 담아서)
	 * 3_2) 현재 미완성된 SQL문을 완성형태로 채우기
	 * 			-> 미완성된 경우에만 해당 / 완성된 경우에는 생략이 가능
	 * 4) SQL문 실행 : executeXXX() => SQL인자값을 전달하지 않음!
	 * 			  > SELECT문일 경우 : executeQuery();
	 * 			  > DML문일 경우 : executeUpdate();
	 * 5) 결과받기 :
	 * 			  > SELECT문의 경우 : ResultSet!객체!(조회된 데이터들이 담겨있음)로 받기 => 6_1)
	 * 			  > DML문의 경우 : int형(처리된 행의 개수)으로 받기					=> 6_2)
	 * 6_1) ResultSet에 담겨있는 데이터들을 하나씩 뽑아서 VO객체에 담기(행이 많을 경우 ArrayList로 관리)
	 * 6_2) 트랜잭션처리(성공하면 COMMIT, 실패하면 ROLLBACK)
	 * 7) 사용이 끝난 JDBC용 객체들은 반드시 자원 반납(close()) => 생성된 순서의 역순으로
	 * 8) 결과반환(Controller)
	 * 			> SELECT문의 경우 6_1에서 만들어진 결과
	 * 			> 나머지 DML문의 경우 처리된 행의 개수
	 * 
	 * 
	 */
	
	public int insertMember(Member m) {
		// INSERT문 => 처리된 행 수 => 트랜잭션 처리
		
		// 0) 필요한 변수들 먼저 셋팅
		int result = 0; // 처리된 결과 (처리된 행의 개수)를 담아줄 변수
		Connection conn = null; // 접속된 DB의 연결정보를 담아줄 변수
		PreparedStatement pstmt = null; // SQL문을 실행 후 결과를 받기위한 변수
		
		// + 필요한변수 : SQL문
		// 
		// INSERT INTO MEMEBER
		// VALUES (SEQ_USERNO.NEXTVAL, 'xxx', 'xxx', 'xxx', 'xxx', ... DEFAULT);
		// 
		String sql = "INSERT INTO MEMBER "
				   + "VALUES(SEQ_USERNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";

		try {
			// 1) JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection객체 생성(DB와 연결하겠다!!! --> url, 계정명, 비밀번호)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 3_1) PreparedStatement 객체 생성(SQL문을 미리 넘겨준다!)
			pstmt = conn.prepareStatement(sql);
			
			// 3_2) 미완성된 SQL문일 경우 완성시켜주기
			// pstmt.setXXX(?위치, 실제값);
			// pstmt.setString(홀더순번, 대체할값); => '(홑따옴표)가 setString으로 넣으면 알아서 들어감 (양 옆에 홑따옴표를 감싸준상태로 알아서 들어감)
			
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			
			// PreparedStatement 단점
			// -> SQL문을 확인할 수 없음
			
			// 4, 5) DB에 전달된 SQL문을 실행하고 결과(처리된 행 개수)받기
			result = pstmt.executeUpdate();			// ★★  SQL문을 위에 이미 전달했기 때문에 execute메소드에 SQL전달 안함 ★★
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			// 6_2) 트랜잭션처리
			if(result > 0) {	// 1개 이상의 행이 INSERT되었다면 == 성공했을 경우 == 커밋
				try {
					if(conn != null && !conn.isClosed()) {
						conn.commit();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				try {
					if(conn != null && !conn.isClosed()) {
						conn.rollback();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			try {
				// 7) 다 쓴 JDBC용 객체 자원 반납 => 생성된 역순으로 close()
				if(pstmt != null && !pstmt.isClosed()) {
					pstmt.close();
				}
				if(conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 8) 컨트롤러에게 처리된 행의 개수 반환
		return result;
	}
	
	public ArrayList<Member> selectAll() {
		// Member만 들어갈 수 있는 리스트를 생성
		ArrayList<Member> list = new ArrayList();
		
		// 처음 필요한 JDBC객체들 선언만!!
		Connection conn = null;
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
				           + "MEMBER";
		
		try {
			// 1) JDBC Driver등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 3) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 4, 5) SQL(SELECT)를 실행 후 결과(ResultSet)받기
			rset = pstmt.executeQuery();
			
			// 6_1) 현재 조회 결과가 담긴 ResultSet에서 한 행씩 뽑아내서 데이터를 VO객체에 담기
			// rset.next() : 커서를 한 줄 아래로 옮겨주고 해당 행이 존재할 경우 true / 아니면 false 반환 
			
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
		// Member m = null;
		Member m = new Member();
		
		// 0) 필요한 변수들 셋팅
		Connection conn = null;
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
				    + "WHERE "
				           + "USERID = ?";
		
		try {
			// 1) JDBC Driver등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
			// 3) PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 3_2) 위치홀더 채우기
			pstmt.setString(1, userId);
			
			// 4, 5) SQL문 실행 결과 받기
			rset = pstmt.executeQuery();
			
			// 6) rset에 담겨온 데이터를 확인 후 VO에 옮겨담기
			if(rset.next()) {
				// 1. setter()
				// 2. constructor()
				
				m = new Member(rset.getInt("USERNO"),
							   rset.getString("USERID"),
							   rset.getString("USERPWD"),
							   rset.getString("USERNAME"),
							   rset.getString("GENDER"),
							   rset.getInt("AGE"),
							   rset.getString("EMAIL"),
							   rset.getString("PHONE"),
							   rset.getString("ADDRESS"),
							   rset.getString("HOBBY"),
							   rset.getDate("ENROLLDATE"));
				/*
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
				*/
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 7) 사용이 끝난 JDBC객체 반납
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 8) 결과가 담긴 Member객체 Controller로  반환
		return m;
	}
	
	public ArrayList<Member> selectByUserName(String keyword) {
		
		// 0) 필요한 변수들 셋팅
		ArrayList<Member> list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		// 실행할 SQL문(미완성된)
		// SELECT * FROM MEMBER WHERE USERNAME '%keyword%';
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
				    + "WHERE "
				           + "USERNAME LIKE ?";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			// 방법 1.   SQL에서 해결 방법
			// String sql = SELECT * FROM MEMBER WHERE USERNAME '%'||?||'%';
			
			// 방법 2.   JAVA에서 해결 방법.  내가 한 방법
			pstmt.setString(1, "%" + keyword + "%");
			
			
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
	
	
	public int updateMember(Member m) {
		
		// 나의 할 일은 DB가서 SQL문 실행하고 결과 받아오기
		
		// 0)
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		// 실행할 SQL문
		// UPDATE MEMBER
		//    SET USERPWD = 'xxx',
		//        EMAIL = 'xxx',
		//		  PHONE = 'xxx',
		//		  ADDRESS = 'xxx',
		//  WHERE USERID = 'xxx';
		String sql = "UPDATE "
					  +     "MEMBER "
			      	  + "SET "
			      	  +     "USERPWD = ?, "
			      	  +     "EMAIL = ?, "
			      	  +     "PHONE = ?, "
			      	  +     "ADDRESS = ? "
			      	  + "WHERE "
			      	  +     "USERID = ?";
		
		try {
			// 1)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "JDBC", "JDBC");
			
			// 3)
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getUserId());
			
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
	
	
	public int deleteMember(String userId) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM MEMBER "
				    + "WHERE "
				    +       "USERID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
