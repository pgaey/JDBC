package com.kh.statement.controller;

import com.kh.statement.model.service.MemberService;
import com.kh.statement.model.vo.Member;
import com.kh.statement.view.MemberView;

// Controller : View를 통해서 온 요청 기능을 처리하는 담당
// 해당 메서드로 전달된 데이터를 가공처리 한 후 DAO메소드를 호출
// DAO로부터 반환받은 결과에 따라서 사용자가 보게 될 View(응답화면)을 결정(View에서도 호출)
public class MemberController {
	
//	private MemberView mv = new MemberView(); => StackOverFlow
//											  => MemberView와 MemberController가 서로를 무한으로 호출생성을 반복한다
	
	public void insertMember(String userId, String userPwd, String userName,
							String gender,int age, String email,
							String phone, String address, String hobby) {
		// 1. View에서 전달된 데이터들을 가공처리! => VO객체 필드에 담기!
		
		Member m = new Member(userId, userPwd, userName, gender, age, email, phone, address, hobby);
		
		// 2. Service!!의 insertMember()호출
		int result = new MemberService().insertMember(m);
		
		String service = "회원추가";
		if(result > 0) {
			new MemberView().displaySuccess(service);
		} else {
			new MemberView().displayFail(service);
		}
	}
	
	public void selectAll() {
		new MemberService().selectAll();
	}
	
	public void selectByUserId(String userId) {
		
	}
	
	public void selectByUserName(String keyword) {
		
	}

	public void updateMember(String userId, String newPwd, String newEmail, String newPhone, String newAddress) {
		
	}
	
	public void deleteMember(String userId) {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
