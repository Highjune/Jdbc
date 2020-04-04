import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import oracle.jdbc.driver.OracleDriver;

public class JdbcDemo {
	public static void main(String[] args) throws SQLException {
		
		//preparedStatement는 interface이므로 new를 못 만들고, connection을 통해서 만들어진다.
		//preparedStatement의 효용성은 : loop돌면서 insert할 때 가장 크게 활약	
		Connection conn = DBConnection.getConnection("oracle.properties"); 
		String sql = "SELECT employee_id, first_name, last_name FROM Employees " +
				"WHERE employee_id = ?" ; //완전한 쿼리가 아니라 ?를 사용함.
		PreparedStatement pstmt = conn.prepareStatement(sql);   //4. connection의 preparestatement를 통해서 preparedStatement를 만듬
		pstmt.setInt(1, 108);  // 위의 employee_id = 108 이 들어간다  //1대신에 컬럼(EMPLOYEE_ID)이름써도 된다.
		pstmt.executeQuery(); //위의 preparesatement안에 이미 sql 넣었기 때문에 executeQuery안에는 아무것도 안 써도 된다.
		ResultSet rs = pstmt.executeQuery(); //5. select는 항상 ResultSet이라는 바구니에 담아야 한다.
		rs.next(); //현재 resultSet안에 employee_id, first_name, last_name  3개 담겨있는 상태, next()를 해야지 반드시 읽는다.
		System.out.println("사번 : " + rs.getInt(1)); //1, 2, 3의 순서는 위에서 select한 순서.
		System.out.println("이름 : " + rs.getString("first_name")); //index로 해도 되고 이렇게 컬럼 이름 써도 되고.
		System.out.println("성 : " + rs.getString(3));
		
		DBClose.close(conn, pstmt, rs);
	}
}
