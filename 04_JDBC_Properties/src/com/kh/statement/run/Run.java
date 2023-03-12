package com.kh.statement.run;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.kh.statement.view.MemberView;

// 실행하면 내 역할은 끝~
public class Run {

	public static void main(String[] args) {
		
		Properties prop = new Properties();
		
		prop.setProperty("A", "B");
		
		try {
			prop.store(new FileOutputStream("driver.properties"), "driver.properties");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		new MemberView().mainMenu();
	
	}

}
