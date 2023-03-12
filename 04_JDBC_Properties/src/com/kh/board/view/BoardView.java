package com.kh.board.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.board.controller.BoardController;
import com.kh.board.model.vo.Board;

public class BoardView {
	
	private Scanner sc = new Scanner(System.in);
	private BoardController bc = new BoardController();
	
	// 메인화면 
	public void mainMenu() {
		while(true) {
			System.out.println("------ 게시판 ------");
			// 게시글 목록
			selectAll();
			
			System.out.println("\n----------------------");
			System.out.println("1. 게시글 조회하기");
			System.out.println("2. 게시글 작성하기");
			System.out.println("3. 게시글 삭제하기");
			System.out.println("9. 게시판 종료");
			System.out.print("메뉴 입력 > ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1: selectBoard(); break;
			case 2: insertBoard(); break;
			case 3: deleteBoard(); break;
			case 9 : System.out.println("게시판을 종료합니다"); return;
			default : System.out.println("없는 메뉴입니다 ~ 다시 입력해주세요 ~");
			}
			
			
			
		}
	}
	
	
	//------------- 요청 화면 ---------------
	
	// 게시글 전체 조회 화면
	private void selectAll() {
		
		ArrayList<Board> list = bc.selectAll();
		
		if(list.isEmpty()) {
			System.out.println("게시글이 존재하지 않습니다.");
		} else {
			for(Board b : list) {
				System.out.print("게시글 번호 : " + b.getBoardNo());
				System.out.print("\t게시글 제목 : " + b.getTitle());
				System.out.print("\t작성자 : " + b.getWriter());
				System.out.print("\t작성일 : " + b.getCreateDate());
				System.out.println();
			}
		}
	}
	
	// 단일 게시글 조회 화면
	private void selectBoard() {
		
		System.out.println("게시글 조회하기 !");
		System.out.println("조회할 게시글 번호를 입력해주세요");
		int boardNo= sc.nextInt();
		sc.nextLine();
		
		Board b = bc.selectBoard(boardNo);
		
		if(b == null) {
			System.out.println("게시글이 존재하지 않습니다.");
		} else {
			System.out.print("\t게시글 제목 : " + b.getTitle());
			System.out.print("\t게시글 작성자 : " + b.getWriter());
			System.out.print("\t\t작성일 : " + b.getCreateDate());
			System.out.print("\t게시글 내용 : " + b.getContent());
			System.out.println();
		}
		
		while(true) {
			System.out.print("글 목록으로 돌아가시려면 돌아가기를 입력해주세요 > ");
			String keyword = sc.nextLine();
			if(keyword.equals("돌아가기")) return;
		}
		
	}
	
	private void insertBoard() {
		System.out.println("---- 게시글 작성하기 ----");
		System.out.println("작성자 번호를 입력헤주세요");
		String userNo = sc.nextLine();
		System.out.println("\n게시글 제목을 입력해주세요");
		String title = sc.nextLine();
		System.out.println("내용을 입력해주세요");
		String content = sc.nextLine();
		
		bc.insertBoard(userNo, title, content);
		
	}
	
	private void deleteBoard() {
		System.out.println("---- 게시글 삭제하기 ----");
		System.out.println("게시글 번호를 입력해주세요");
		int bNum = sc.nextInt();
		
		bc.deleteBoard(bNum);
		
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
