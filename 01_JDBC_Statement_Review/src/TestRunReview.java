import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestRunReview {
	
	Scanner sc = new Scanner(System.in);
//	"oracle.jdbc.driver.OracleDriver"
//	"jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC"
//	String sql = "SELECT TNO, TNAME, TDATE FROM TEST"
	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		
		String sql = "SELECT TNO, TNAME, TDATE FROM TEST";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 등록");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			System.out.println("커넥션 객체 생성");
			
			stmt = conn.createStatement();
			
			rset = stmt.executeQuery(sql);
			
			
			while(rset.next()) {
				int tNo = rset.getInt("TNO");
				String tName = rset.getString("TNAME");
				Date tDate = rset.getDate("TDATE");
				System.out.println(rset);
				
			}
			
			
			
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
