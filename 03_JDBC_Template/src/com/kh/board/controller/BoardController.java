package com.kh.board.controller;

import java.util.ArrayList;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.statement.view.MemberView;

public class BoardController {
	
	
	
	public ArrayList<Board> selectAll() {
//		ArrayList<Board> list = new BoardService().selectAll();
		return new BoardService().selectAll();
	}
	
	public Board selectBoard(int boardNo) {
		 Board b = new BoardService().selectBoard(boardNo);
		 return b;
	}
	
	public void insertBoard(String userNo, String title, String content) {
		
		int count = new BoardService().selectUser(userNo);
		
		if(count == 0) {
			
			new MemberView().displayFail("");
			return;
		}
			
		Board b = new Board();
		b.setWriter(userNo);
		b.setTitle(title);
		b.setContent(content);
		int result = new BoardService().insertBoard(b);

		if(result > 0) {
			new MemberView().displaySuccess("게시글 작성");
		} else {
			new MemberView().displayFail("게시글 작성");
		}
	}
	
	public void deleteBoard(int bNum) {
		
		if(selectBoard(bNum) == null) {
			new MemberView().displayFail("");
			return;
		}
		
		if(new BoardService().deleteBoard(bNum) > 0 ) {
			new MemberView().displaySuccess("게시글 삭제");
		} else {
			new MemberView().displayFail("게시글 삭제");
		}
		
		
		/*   위에 만들어놨음  ;;;;
		int count = new BoardService().selectBoardNo(bNum);
		
		if(count == 1) {
			new BoardService().deleteBoard(bNum);
		} else {
			new MemberView().displayFail("");
			return;
		}
		*/
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
