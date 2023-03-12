package com.kh.statement.run;

import java.sql.Connection;

import com.kh.statement.common.JDBCTemplate;
import com.kh.statement.view.MemberView;

// 실행하면 내 역할은 끝~
public class Run {

	public static void main(String[] args) {

		Connection conn = JDBCTemplate.getConnection();
		new MemberView().mainMenu();
	
	}

}
