package com.kh.statement.controller;

import java.util.ArrayList;

import com.kh.statement.model.dao.MemberDao;
import com.kh.statement.model.vo.Member;
import com.kh.statement.view.MemberView;

// Controller : View를 통해서 온 요청 기능을 처리하는 담당
// 해당 메서드로 전달된 데이터를 가공처리 한 후 DAO메소드를 호출
// DAO로부터 반환받은 결과에 따라서 사용자가 보게 될 View(응답화면)을 결정(View에서도 호출)
public class MemberController {
	
	public void insertMember(String userId, String userPwd, String userName, String gender, int age, String email, String phone, String address, String hobby) {
		
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
		
		int result = new MemberDao().insertMember(m);
		String service = "회원등록";
		if(result > 0) new MemberView().displaySuccess(service);
		else new MemberView().displayFail(service);
		
	}
	
	public void selectAll() {
		
		
		ArrayList<Member> list = new MemberDao().selectAll();
		
		if(list.isEmpty()) {
			new MemberView().displayNoData();
		} else {
			new MemberView().displayList(list);
		}
		
	}
	
	public void selectByUserId(String userId) {
		
		Member m = new MemberDao().selectByUserId(userId);
		if(m.getUserId() == null) {
			new MemberView().displayNoData();
		} else {
			new MemberView().displayOne(m);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
