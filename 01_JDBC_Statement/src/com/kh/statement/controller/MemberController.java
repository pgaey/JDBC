package com.kh.statement.controller;

import java.util.ArrayList;

import com.kh.statement.model.dao.MemberDao;
import com.kh.statement.model.vo.Member;
import com.kh.statement.view.MemberView;

// Controller : View를 통해서 온 요청 기능을 처리하는 담당
// 해당 메서드로 전달된 데이터를 가공처리 한 후 DAO메소드를 호출
// DAO로부터 반환받은 결과에 따라서 사용자가 보게 될 View(응답화면)을 결정(View에서도 호출)
public class MemberController {
	
//	private MemberView mv = new MemberView(); => StackOverFlow
//											  => MemberView와 MemberController가 서로를 무한으로 호출생성을 반복한다
	
	/**
	 * 사용자의 회원 추가 요청을 처리해준 메소드
	 * 
	 * @param userId : 첫 번째 인자 사용자에게 입력받을 아이디 : String
	 * @param userPwd : 두 번째 인자 사용자에게 입력받을 비밀번호  : String
	 * @param userName : 세 번째 인자 사용자에게 입력받을 이름 : String
	 * @param gender : 네 번째 인자 사용자에게 입력받을 성별 : String
	 * @param age : 다섯 번째 인자 사용자에게 입력받을 나이 : int
	 * @param email : 여섯 번째 인자 사용자에게 입력받을 이메일 : String
	 * @param phone : 일곱 번째 인자 사용자에게 입력받을 전화번호 : String
	 * @param address : 여덟 번째 인자 사용자에게 입력받을 주소 : String
	 * @param hobby : 아홉 번째 인자 사용자에게 입력받을 취미 : String
	 */
	public void insertMember(String userId, String userPwd, String userName,
							String gender,int age, String email,
							String phone, String address, String hobby) {
		
		// 전달된 데이터들을 Member 객체에 담기
		Member m = new Member(userId, userPwd, userName,
					     	  gender, age, email,
					     	  phone, address, hobby);
		
		int result = new MemberDao().insertMember(m);
		
		if(result > 0) {	// 성공했을 때
			new MemberView().displaySuccess();
		} else { 			// 실패했을 때
			new MemberView().displayFail();
		}
	}
	
	
	public void selectAll() {
		
		ArrayList<Member> list = new MemberDao().selectAll();
		
		// 조회결과 있는지 없는지 판단 후 사용자가 보게 될 View화면 지정
		if(list.isEmpty()) {	// 텅 빈 리스트  => 조회결과가 없다.
			new MemberView().displayNoData();
		} else {	// 요소가 존재한다  => 조회결과 있음
			new MemberView().displayList(list);
		}
	}
	
	
	/**
	 * 사용자의 아이디로 검색요청을 처리해주는 메소드
	 * 
	 * @parma userId : 사용자가 입력한 검색하고자 하는 아이디
	 */
	public void selectByUserId(String userId) {
		// SELECT => ResultSet(1행) => Member
		Member m = new MemberDao().selectByUserId(userId);
		
		// 조회결과가 있는지 없는지 판단 후 사용자가 보게 될 View 지정
		if(m == null) {	// 조회결과가 없다는 뜻
			new MemberView().displayNoData();
		} else { 		// 조회결과가 있다는 뜻
			new MemberView().displayOne(m);
		}
	}
	
	public void selectByUserName(String keyword) {
		// 결과값이 나중에 어떻게 돌아올까???
		// SELECT -> ResultSet -> ArrayList
		
		ArrayList<Member> list = new MemberDao().selectByUserName(keyword);
		
		
		// 조회결과가 있을지 없을지 모름
		if(list.isEmpty()) {	// 검색결과가 없을 경우
			new MemberView().displayFail();
		} else {				// 검색결과가 있을 경우 
			new MemberView().displayList(list);
		}
		/*     내가 한 거 
		ArrayList<Member> list1 = new MemberDao().selectByUserName();
		ArrayList<Member> list = new ArrayList();
		
		if(!list1.isEmpty()) {
			for(int i = 0; i < list1.size(); i++) {
				if(list1.get(i).getUserName().contains(keyword)) {
					list.add(list1.get(i));
				}
			}
		} else {
			new MemberView().displayNoData();
		}
		
		new MemberView().displayName(list);
		*/
	}

	/**
	 * 사용자의 회원 정보 수정 요청시 처리해주는 메소드
	 * @param userId : WHERE절의 조건으로 사용될 사용자가 입력한 아이디
	 * @param newPwd : USERPWD컬럼에 갱신될 사용자가 입력한 비밀번호
	 * @param newEamil : EMAIL컬럼에 갱신될 사용자가 입력한 이메일
	 * @param newPhone : PHONE컬럼에 갱신될 사용자가 입력한 핸드폰번호
	 * @param newAddress : ADDRESS 컬럼에 갱신될 사용자가 입력한 주소
	 */
	public void updateMember(String userId, String newPwd, String newEmail, String newPhone, String newAddress) {
		
		// 1. 데이터 가공 처리
		// -> VO객체에 입력받은 값들 담기
		Member m = new Member();
		
		m.setUserId(userId);
		m.setUserPwd(newPwd);
		m.setEmail(newEmail);
		m.setPhone(newPhone);
		m.setAddress(newAddress);
		
		int result = new MemberDao().updateMember(m);
		
		// 2. 응답화면 지정
		if(result > 0) {
			new MemberView().displaySuccess();
		} else {
			new MemberView().displayFail();
		}
	}
	
	public void deleteMember(String userId) {
		int result = new MemberDao().deleteMember(userId);
		if(result > 0) {
			new MemberView().displaySuccess();
		} else new MemberView().displayFail();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
