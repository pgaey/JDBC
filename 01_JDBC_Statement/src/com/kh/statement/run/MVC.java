package com.kh.statement.run;

public class MVC {
	
	
	/*
	 * 1. MVC 패턴
	 * 
	 * Model : 데이터와 관련된 역할(데이터를 담는다거나, DB에 접근해서 데이터 입/출력)
	 * 
	 * View : 사용자가 보게될 시각적인 요소  / 화면(입력, 출력)
	 * 
	 * Controller : 사용자의 요청을 받아서 처리 후 응답화면을 지정하는 역할
	 * 
	 * * View단에서만 출력(System.out.println())을 사용할 것 !
	 * * Model의 DAO(Data Access Object)에서만 DB에 직접적으로 접근한 후 해당 SQL문을 실행한 결과를 받는다.
	 * 
	 * 
	 * 2. ojdbc6.jar
	 * 
	 * 프로젝트 선택 후 마우스 오른쪽 클릭 -> Properties -> JavaBuildPath -> Libraries -> Add external Jars
	 * -> C:\dev\ojdbc6.jar 선택 -> Apply -> Apply and Close
	 * 
	 * 요 작업을 진행하지 않으면 ClassNotFound Exception발생!
	 * 
	 * 
	 * 
	 * 3. JDBC의 역할
	 * 
	 * 1) Java코드를 통해 DB서버에 접속
	 * 2) SQL문을 구성하고 DB에서 실행
	 * 3) DB서버에서 처리한 결과를 받아오기 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 4. JDBC 사용 객체
	 * 
	 * - DriverManager
	 * 
	 * JDBC드라이버를 통해서 커넥션을 만드는 역할
	 * Class.forName() 메소드를 호출해서 생성하며, 반드시 예외처리를 진행 해야함
	 * getConnection() 메소드를 호출하여 Connection객체를 반환
	 * 
	 * - Connection
	 * 특정 데이터 원본과 연결된 객체
	 * Statement 객체를 생성할 때도 Connection 객체를 이용해야함
	 * 
	 * - Statement
	 * Connection객체에 의해 프로그램에 구현되는 일종의 메소드 집합
	 * Connection클래스에 createStatement()를 호출해서 객체 생성
	 * Statement객체로 SQL문을 String객체로 담아 인자값으로 DBMS로 전달하여 질의를 수행하고 결과를 반환받음
	 * 
	 * - ResultSet
	 * SELECT문을 사용한 질의 성공 시 반환되는 객체
	 * SQL질의에 해당하는 결과를 담고 잇으며 '커서(cursor)'를 이용하여 특정 행에 대한 참조를 조작
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 패키지 및 클래스
	 * 
	 * 
	 * 
	 * 
	 * com.kh.statement.run.Run
	 * com.kh.statement.view.MemberView
	 * com.kh.statement.controller.MemberController
	 * com.kh.statement.model.vo.Member
	 * com.kh.statement.model.dao.MemberDao
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
}
