package com.kh.statement.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.statement.controller.MemberController;
import com.kh.statement.model.vo.Member;

public class MemberView {
	
	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	
	public void mainMenu() {
		while(true) {
			System.out.println("메인 메뉴\n");
			System.out.println("1. 회원추가");
			System.out.println("2. 회원 전체 조회");
			System.out.println("3. 회원 아이디로 조회");
			System.out.println("4. 회원 이름 키워드로 조회");
			System.out.println("5. 회원 정보 변경");
			System.out.println("6. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");
			System.out.print("선택하세요 > ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1 : insertMember(); break;
			case 2 : selectAll(); break;
			case 3 :  break;
			case 4 :  break;
			case 5 :  break;
			case 6 :  break;
			case 0 :  return;
			default : break;
			}
			
		}
	}
	
	public void insertMember() {
		System.out.println("-- 회원 추가 메뉴 --\n");
		System.out.println("아이디 > ");
		String userId = sc.nextLine();
		System.out.println("비번 > ");
		String userPwd = sc.nextLine();
		System.out.println("이름 > ");
		String userName = sc.nextLine();
		System.out.println("성별 > ");
		String gender = sc.nextLine().toUpperCase();
		System.out.println("나이 > ");
		int age  = sc.nextInt();
		sc.nextLine();
		System.out.println("이메일 > ");
		String email = sc.nextLine();
		System.out.println("번호 > ");
		String phone = sc.nextLine();
		System.out.println("주소 > ");
		String address = sc.nextLine();
		System.out.println("취미 > ");
		String hobby = sc.nextLine();
		
		mc.insertMember(userId, userPwd, userName, gender, age, email, phone, address, hobby);
		
	}
	
	public void selectAll() {
		System.out.println("회원 전체 조회\n");
		
		mc.selectAll();
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	----------------------------------------------------------------
	public void displayFail() {
		System.out.println("\n실패");
	}
	public void displaySuccess() {
		System.out.println("\n성공");
	}
	
	public void displayList(ArrayList<Member> list) {
		for(Object obj : list) {
			System.out.println(obj);
		}
	}
	
	public void displayNoData() {
		System.out.println("조회된 데이터가 없음.");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
