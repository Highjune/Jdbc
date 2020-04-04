import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import oracle.jdbc.driver.OracleDriver;

public class DriverLoadingWays {
	public static void main(String[] args) throws SQLException {
		//Driver Loading하는 1번째 방법 
		//Class.forname()
		//ClassNotFoundException 처리해야됨 (SQLException이 아님) 
		//Class.forName("oracle.jdbc.driver.OracleDriver");
			
		//2번째 방법(SQLException). 
		//DriverManager.registerDriver(new OracleDriver());
		//이 방법의 장점은 다른 것들이 초래하는 SQLECeption과 동일하므로 하나의 예외에 다 묶을 수 있다.
//		try {		 //cf) 해제하는 deregisterDriver도 있음.
//			DriverManager.registerDriver(new OracleDriver()); //2. 	위에서 import oracle.jdbc.driver.OracleDriver;해줘야 됨. 아니면 new  oracle.jdbc.driver.OracleDriver()을 넣어야 한다.
//		}catch(SQLException ex) {
//		
//		}
		
		//3번쨰 방법 (거의 안함)
		//Connection과 DriverManager로.
//		try {	
//			DriverManager.registerDriver(new OracleDriver()); //
//			DriverManager.getConnection(url, user, password);  //
//		}catch(SQLException ex) {
//		
//		}	
		
		//4번째 방법 (가장 추천)
		//oracle.properties로 따로 파일로 만들고 클래스(DBConnection)를 만들어서.
		//ex) 아래
		
		//preparedStatement의 효용성은 : loop돌면서 insert할 때 가장 크게 역할. 빠름.		
		Connection conn = DBConnection.getConnection("oracle.properties"); 
		String sql = "SELECT employee_id, first_name, last_name FROM Employees " +
				"WHERE employee_id = ?" ; //완전한 쿼리가 아니라 ?를 사용함.
		PreparedStatement pstmt = conn.prepareStatement(sql);   //4.
		pstmt.setInt(1, 108);  // 위의 employee_id = 108 이 들어간다  //1대신에 컬럼(EMPLOYEE_ID)이름써도 된다.
		pstmt.executeQuery(); //위의 preparesatement안에 이미 sql 넣었기 때문에 executeQuery안에는 아무것도 안 써도 된다.
		ResultSet rs = pstmt.executeQuery(); //5.
		rs.next(); //현재 resultSet안에 employee_id, first_name, last_name  3개 담겨있는 상태
		System.out.println("사번 : " + rs.getInt(1));
		System.out.println("이름 : " + rs.getString("first_name"));
		System.out.println("성 : " + rs.getString(3));
		
		DBClose.close(conn, pstmt, rs);
	}
}
