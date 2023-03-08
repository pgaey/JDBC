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
	
	public void selectAll() {
		
		// 1) Connection 객체 생성
		Connection conn = /*JDBCTemplate.*/getConnection();
		
		// 2) DAO호출해서 반환 받기
		// 만약에 Controller에서 넘겨준 값이 있다면 Connection 객체와 함게 넘겨줘야함
		ArrayList<Member> list = new MemberDao().selectAll(conn);
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getAddress())
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
