package com.kh.statement.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.statement.controller.MemberController;
import com.kh.statement.model.vo.Member;

// View : 사용자가 보게 될 시각적인 요소(화면)(입력 및 출력)
public class MemberView {
	// 전역에서 전부 다 사용이 가능하도록 Scanner를 필드로 선언
	private Scanner sc = new Scanner(System.in);
	
	// 전역에서 전부 바로바로 MemberController를 요청할 수 있게끔 객체 생성
	private MemberController mc = new MemberController();
	
	
	/**	 주석 실행 Alt + Shift + J
	 * 사용자가 보게 될 첫 화면(메인화면)	
	 */
	public void mainMenu() {
		while(true) {
			System.out.println("------------------- 회원 관리 프로그램 -------------------");
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 전체 조회");
			System.out.println("3. 회원 아이디로 조회");
			System.out.println("4. 회원 이름 키워드로 조회");
			System.out.println("5. 회원 정보 변경");
			System.out.println("6. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");
			System.out.println("이용할 메뉴를 선택해주세요");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1 : insertMember(); break;
			case 2 : selectAll(); break;
			case 3 : selectByUserId(); break;
			case 4 : selectByUserName(); break;
			case 5 : updateMember(); break;
			case 6 : deleteMember(); break;
			case 0 : System.out.println("프로그램을 종료합니다 ~ "); return;
			default : System.out.println("잘못 누르셨습니다 다시 선택해주세요 ~ !");
			}
		}
	}
	
	/**
	 *  회원 추가용 화면
	 *  추가하고자 하는 회원의 정보를 입력받아서 추가 요청을 할 수 있는 화면
	 */
	private void insertMember() {
		System.out.println("----- 회원 추가 -----"); 
		System.out.print("아이디 > ");
		String userId = sc.nextLine();
		System.out.print("비밀번호 > ");
		String userPwd = sc.nextLine();
		System.out.print("이름 > ");
		String userName = sc.nextLine();
		System.out.print("성별(M/F) > ");
		String gender = sc.nextLine().toUpperCase();
		System.out.print("나이 > ");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.print("이메일 > ");
		String email = sc.nextLine();
		System.out.print("전화번호(숫자만) > ");
		String phone = sc.nextLine();
		System.out.print("주소 > ");
		String address = sc.nextLine();
		System.out.print("취미(,로 공백없이 나열) > ");
		String hobby = sc.nextLine();
		
		// 컨트롤러로 요청 처리!
		mc.insertMember(userId, userPwd, userName, gender, age, email, phone, address, hobby);
	}
	
	
	/**
	 * 회원 전체 조회를 할 수 있는 화면
	 */
	private void selectAll() {
		System.out.println("---- 회원 전체 조회 ----");
		
		// 모든 회원들의 정보를 보여줘!!
		mc.selectAll();
		
	}
	
	
	/**
	 * 사용자에게 검색할 회원의 아이디를 입력받은 후 조회 요청하는 메소드
	 */
	private void selectByUserId() {
		
		System.out.println("---- 회원 아이디로 검색 ! ----");
		
		System.out.print("아이디를 입력해주세요 > ");
		String userId = sc.nextLine();
		
		mc.selectByUserId(userId);
		
	}
	
	/**
	 * 사용자에게 검색할 회원의 이름을 입력받은 후 조회 요청하는 메소드
	 */
	private void selectByUserName() {
		
		System.out.println("---- 회원 이름을 입력! ----");
		
		System.out.print("이름을 입력하세요 >");
		String keyword = sc.nextLine();
		
		mc.selectByUserName(keyword);
		
	}
	
	/**
	 * 사용자에게 변경할 회원의 아이디와 변경할 정보들(비밀번호, 이메일, 휴대폰번호, 주소)
	 * 입력받은 후 변경 요청을 하는 메소드
	 */
	private void updateMember() {
		
		System.out.println("---- 회원 정보 변경 ----");
		
		// 변경할 회원의 아이디
		System.out.print("변경할 회원의 아이디를 입력해주세요 > ");
		String userId = sc.nextLine();
		
		// 변경할 정보들
		System.out.print("새 비밀번호를 입력해주세요 >");
		String newPwd = sc.nextLine();
		System.out.print("새 이메일을 입력해주세요 > ");
		String newEmail = sc.nextLine();
		System.out.print("새 핸드폰 번호를 입력해주세요 > ");
		String newPhone = sc.nextLine();
		System.out.print("새 주소를 입력해주세요 > ");
		String newAddress = sc.nextLine();
		
		mc.updateMember(userId, newPwd, newEmail, newPhone, newAddress);
		
		
	}
	
	/**
	 * 
	 */
	private void deleteMember() {
		
		System.out.println("회원 탈퇴 할래???");
		
		System.out.println("탈퇴할 회원 아이디를 입력해주세요 > ");
		String userId = sc.nextLine();
		
		mc.deleteMember(userId);
		
	}
	
	
	

	
	
	
	
	
	
	// -----------------------------------------------------------------------------------------------
	// 서비스 요청 어리 후 사용자가 보게 될 응답화면
	
	public void displaySuccess(String service) {
		System.out.println(service + " 서비스 요청 성공!!");
	}
	
	public void displayFail(String service) {
		System.out.println(service + " 서비스 요청 실패 ㅜㅜ");
	}
	
	public void displayNoData() {
		System.out.println("조회 결과가 존재하지 않습니다.");
	}
	
	
	public void displayList(ArrayList<Member> list) {
		System.out.println("\n조회된 회원의 수는 " + list.size() + "명 입니다.");
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println((i + 1) + "번 째 회원 : " + list.get(i));
		}
	}
	
	public void displayOne(Member m) {
		System.out.println("\n조회된 결과는 다음과 같습니다.");
		System.out.println(m);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
