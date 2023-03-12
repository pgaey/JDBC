package com.kh.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.board.model.vo.Board;
import com.kh.statement.common.JDBCTemplate;

public class BoardDao {
	
	public ArrayList<Board> selectAll(Connection conn) {
		
		ArrayList<Board> list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT BNO, TITLE, USERID , CREATE_DATE "
			         + "FROM BOARD "
			         + "JOIN MEMBER ON (WRITER = USERNO) "
			        + "WHERE DELETE_YN = 'N' "
			        + "ORDER BY CREATE_DATE DESC";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Board b = new Board();
				
				b.setBoardNo(rset.getInt("BNO"));
				b.setTitle(rset.getString("TITLE"));
				b.setWriter(rset.getString("USERID"));
				b.setCreateDate(rset.getDate("CREATE_DATE"));
				
				list.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}
	
	public Board selectBoard(Connection conn, int boardNo) {
		
		Board b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT "
				          + "BNO, "
				          + "TITLE, "
				          + "CONTENT, "
				          + "USERID, "
				          + "CREATE_DATE "
				     + "FROM "
				          + "BOARD, MEMBER "
				    + "WHERE "
				          + "WRITER = USERNO "
				      + "AND DELETE_YN = 'N' "
				      + "AND BNO = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b= new Board();
				b.setBoardNo(rset.getInt("BNO"));
				b.setTitle(rset.getString("TITLE"));
				b.setContent(rset.getString("CONTENT"));
				b.setWriter(rset.getString("USERID"));
				b.setCreateDate(rset.getDate("CREATE_DATE"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return b;
	}
	
	public int selectUser(Connection conn, String userNo) {
		// SELECT USERNO FROM MEMBER WHERE USERNO = ?
		
		int count = 0;
		PreparedStatement pstmt = null;
		
		String sql = "SELECT USERNO FROM MEMBER WHERE USERNO = ?";
	
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(userNo));
			count = pstmt.executeQuery().next() ? 1 : 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return count;
	}
	
	public int insertBoard(Connection conn, Board b) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT INTO BOARD "
				   + "VALUES (SEQ_BOARD.NEXTVAL, ?, ?, DEFAULT, ?, DEFAULT)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.setInt(3, Integer.parseInt(b.getWriter()));
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	/* 위에 만들어놨음
	public int selectBoardNo(Connection conn, int bNum) {
		
		int count = 0;
		PreparedStatement pstmt = null;
		
		String sql = "SELECT BNO FROM BOARD WHERE BNO = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNum);
			count = pstmt.executeQuery().next()? 1 : 0;
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return count;
	}
	*/
	public int deleteBoard(Connection conn, int bNum) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE BOARD SET DELETE_YN = 'Y' WHERE BNO = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bNum);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
