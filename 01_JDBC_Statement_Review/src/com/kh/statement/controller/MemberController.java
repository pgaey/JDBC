package com.kh.statement.controller;

import java.util.ArrayList;

import com.kh.statement.model.dao.MemberDao;
import com.kh.statement.model.vo.Member;
import com.kh.statement.view.MemberView;

public class MemberController {
	
	public void insertMember(String userId, String userPwd, String userName
				           , String gender, int age, String email, String phone
				           , String address, String hobby) {
		
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
		
		if(result > 0) {
			new MemberView().displaySuccess();
		} else {
			new MemberView().displayFail();
		}
	}
	
	public void selectAll() {
		
		ArrayList<Member> list = new ArrayList();
		list = new MemberDao().selectAll();
		
		if(list.isEmpty()) {
			new MemberView().displayNoData();
		} else {
			new MemberView().displayList(list);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
