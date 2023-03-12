package com.kh.statement.model.vo;

import java.sql.Date;

/*
 * VO(Value Object)
 * = DB테이블의 한 행에 대한 데이터를 기록할 수 있는 저장용 객체
 * 
 * 유사
 * DTO(Data Transfer Object)
 * DO(Domain Object)
 * Entity
 * bean
 * 
 * VO 조건
 * 1. 반드시 캡슐화를 적용
 * 2. 모든 필드에 대한 getter/setter 메소드를 작성할 것
 * 3. 기본생성자 및 개배변수 생성자를 작성할 것
 */

/*
 * MEMBER테이블의 컬럼들과 유사하게 필드를 구성
 * SEQUENCE와 DEFAULT등의 조건으로 값이 들어가는 경우
 * 해당 필드를 뺀 생성자를 추가 해줌!!!
 */

public class Member {
	
	// 필드부
	// 필드부는 DB의 컬럼정보와 유사하게 작업
	private int userNo; // USERNO NUMBER
	private String userId; // USERID VARCHAR2(15 BYTE)
	private String userPwd; // USERPWD VARCHAR2(20 BYTE)
	private String userName; // USERNAME VARCHAR2(15 BYTE)
	private String gender; // GENDER CHAR(1 BYTE)
	private int age; // AGE	NUMBER
	private String email; // EMAIL	VARCHAR2(30 BYTE)
	private String phone; // PHONE	CHAR(11 BYTE)
	private String address; // ADDRESS	VARCHAR2(100 BYTE)
	private String hobby; // HOBBY	VARCHAR2(50 BYTE)
	private Date enrollDate; // enrollDate	DATE

	// 생성자부
	public Member() {
		super();
	}
	
	// 회원가입용 매개변수 생성자
	public Member(String userId, String userPwd, String userName, String gender, int age, String email, String phone,
			String address, String hobby) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
	}
	public Member(int userNo, String userId, String userPwd, String userName, String gender, int age, String email,
			String phone, String address, String hobby, Date enrollDate) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
		this.enrollDate = enrollDate;
	}

	// 메서드부
	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Date getenrollDate() {
		return enrollDate;
	}

	public void setenrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", gender=" + gender + ", age=" + age + ", email=" + email + ", phone=" + phone + ", address="
				+ address + ", hobby=" + hobby + ", enrollDate=" + enrollDate + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

