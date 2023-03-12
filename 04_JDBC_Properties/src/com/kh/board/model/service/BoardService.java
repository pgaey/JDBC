package com.kh.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Board;
import com.kh.statement.common.JDBCTemplate;

public class BoardService {
	
	public ArrayList<Board> selectAll() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> list = new BoardDao().selectAll(conn);
		
		JDBCTemplate.close(conn);

		return list;
	}
	
	
	public Board selectBoard(int boardNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Board b = new BoardDao().selectBoard(conn, boardNo);
		
		JDBCTemplate.close(conn);
		
		return b;
	}
	
	public int selectUser(String userNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int count = new BoardDao().selectUser(conn, userNo);
		
		JDBCTemplate.close(conn);
		
		return count;
	}
	
	public int insertBoard(Board b) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().insertBoard(conn, b);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn); 	// 반납하기 전에 트랜잭션 !!!
		
		return result;
	}
	/*	 위에 만들어놨음  ;;;;
	public int selectBoardNo(int bNum) {
		
		Connection conn = JDBCTemplate.getConnection();
		int count = new BoardDao().selectBoardNo(conn, bNum);
		
		JDBCTemplate.close(conn);
		return count;
	}
	*/
	
	public int deleteBoard(int bNum) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().deleteBoard(conn, bNum);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
