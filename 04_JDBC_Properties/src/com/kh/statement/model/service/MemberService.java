package com.kh.statement.model.service;

import static com.kh.statement.common.JDBCTemplate.close;
import static com.kh.statement.common.JDBCTemplate.commit;
import static com.kh.statement.common.JDBCTemplate.getConnection;
import static com.kh.statement.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.statement.model.dao.MemberDao;
import com.kh.statement.model.vo.Member;

/*
 * Service : 기존의 DAO의 역할을 분담
 * 			Controller에서 Service호출(Connection 객체 생성) 후 서비스를 거쳐서 DAO로 넘어갈 것
 * 			DAO호출 시 Connection객체랑 기존에 넘기고자 했던 인자값을 같이 넘겨줌
 * 			DAO처리가 끝나면 서비스단에서 결과에 따른 트랜잭션 처리도 같이 해줄 것
 * 			=> Service단을 추가함으로써 DAO는 순수하게 SQL문을 처리하는 부분만 남길 것
 * 
 * 주된 업무 커넥션.
 */

public class MemberService {
	
	public int insertMember(Member m) {
		
		// Connection 객체 생성
		Connection conn = /*JDBCTemplate.*/getConnection();
		
		// DAO호출 시 Connection객체와 기존에 넘기고자했던 Member객체를 같이 넘김
		int result = new MemberDao().insertMember(conn, m);
		
		if(result > 0) {
			/*JDBCTemplate.*/commit(conn);
		} else {
			/*JDBCTemplate.*/rollback(conn);
		}
		
		// Connection 객체 반납
		/*JDBCTemplate.*/close(conn);
		
		return result;
	}
	
	public ArrayList<Member> selectAll() {
		
		// 1) Connection 객체 생성
		Connection conn = /*JDBCTemplate.*/getConnection();
		
		// 2) DAO호출해서 반환 받기
		// 만약에 Controller에서 넘겨준 값이 있다면 Connection 객체와 함게 넘겨줘야함
		ArrayList<Member> list = new MemberDao().selectAll(conn);
		
		// 3) 자원 반납
		close(conn);
		
		// 4) Controller로 돌아감
		
		return list;
		
	}
	
	
	public Member selectByUserId(String userId) {
		
		// 1) 연결하기 : Connection 객체 받아오기
		Connection conn = getConnection();
		
		// 2) DAO호출!(내가 만든 Connection, Controller가 넘겨준 무언가)
		Member m = new MemberDao().selectByUserId(conn, userId);
		
		// 3) Connection 자원반납
		close(conn);
		
		// 4) Controller로 결과 반납
		return m;
	}
	
	
	public ArrayList selectByUserName(String keyword) {
		
		// 1) 연결 : Connection 객체 받아오기
		Connection conn = getConnection();
		
		// 2) DAO호출!
		ArrayList<Member> list = new MemberDao().selectByUserName(conn, keyword);
		
		// 3) 연결 끝 ! Connection 자원 반납
		close(conn);
		
		// 4) Controller로 결과 반납
		return list;
	}
	
	public int updateMember(Member m) {
		
		// 1) 연결 !!! Connection 객체 생성
		Connection conn = getConnection();
		
		// 2) 매개변수로 전달받은 값과 커넥션 객체를 같이 DAO메소드를 호출하면서 전달! 
		int result = new MemberDao().updateMember(conn, m);
		
		// 3) 트랜잭션처리
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		// 4) 자원 반납 
		close(conn);
		
		// 5) 결과 반환
		return result;
	}
	
	public int deleteMember(String userId) {
		
		// 1) 연결 ! Connection 객체 생성
		Connection conn = getConnection();
		
		// 2) 매개변수로 전달받은 값과 Connection 객체를 DAO호출하면서 전달
		int result = new MemberDao().deleteMember(conn, userId);
		
		// 3) 트랜잭션처리
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		// 4) 커넥션 객체 반납
		close(conn);
		
		// 5) 결과 반환
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
