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
		
		// 1. View에서 전달받은 데이터들을 Member객체에 담기 => 가공처리
		Member m = new Member();
		
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setUserName(userName);
		m.setGender(gender);
		m.setAge(age);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(address);
		m.setHobby(hobby);
		
		// 2. DAO의 insertMember(멤버객체) 호출
		int result = new MemberDao().insertMember(m);
		
		// 3. 결과값에 따라서 사용자가 보게 될 화면 지정
		String service = "회원추가";
		if(result > 0) {	// 성공했을 경우
			new MemberView().displaySuccess(service);
		} else {		// 실패했을 경우
			new MemberView().displayFail(service);
		}
	}
	
	public void selectAll() {
		// SELECT -> ResultSet(Member) -> ArrayList<Member>
		ArrayList<Member> list = new MemberDao().selectAll();
		
		// 조회결과가 있는지 없는지 판단 후 사용자가 보게 될 View화면 지정
		if(list.isEmpty()) {
			new MemberView().displayNoData();
		} else {
			new MemberView().displayList(list);
		}
	}
	
	/**
	 * 사용자의 아이디로 검색요청을 처리해주는 메소드
	 * 
	 * @parma userId : 사용자가 입력한 검색하고자 하는 아이디
	 */
	public void selectByUserId(String userId) {
		
		
		Member m = new MemberDao().selectByUserId(userId);
		
		if(m.getUserId() == null) {
			new MemberView().displayNoData();
		} else {
			new MemberView().displayOne(m);
		}
	}
	
	public void selectByUserName(String keyword) {
		
		ArrayList<Member> list = new MemberDao().selectByUserName(keyword);
		
		if(list.isEmpty()) {
			new MemberView().displayNoData();
		} else {
			new MemberView().displayList(list);
		}
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
		// 나는 데이터 가공을 해야함
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(newPwd);
		m.setEmail(newEmail);
		m.setPhone(newPhone);
		m.setAddress(newAddress);
		
		int result = new MemberDao().updateMember(m);
		String service = "회원 정보 수정";
		if(result > 0) {
			new MemberView().displaySuccess(service);
			selectByUserId(userId);
		} else {
			new MemberView().displayFail(service);
		}
		
	}
	
	public void deleteMember(String userId) {
		
		int result = new MemberDao().deleteMember(userId);
		
		String service = "회원탈퇴";
		if(result > 0) {
			new MemberView().displaySuccess(service);
		} else {
			new MemberView().displayFail(service);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
