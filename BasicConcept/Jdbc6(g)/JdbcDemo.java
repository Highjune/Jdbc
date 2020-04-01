//1.
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcDemo {
	public static void main(String[] args) { 
		String sql = "SELECT employee_id, last_name, email, hire_date, " + 
		                 "department_name, city " + 
				         "FROM employees e INNER JOIN departments d " +
		                 "ON e.department_id = d.department_id " +
				         "INNER JOIN locations lo " +
		                 "ON d.location_id = lo.location_id " + 
				         "WHERE e.department_id < 50 " +
		                 "ORDER BY employee_id ASC"; //stored procedure를 쓰면 이것보다 훨씬 간편하고, 보안성도 높다(테이블 등을 노출하지 않으므로)
		//4. 
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {  //위의 몇 과정들을 하나의 try안에 넣은 이유는 어차피 에러 발생하는 것이 같으니까 한번에 묶으려고.
			conn = DBConnection.getConnection("oracle.properties");
			stmt = conn.createStatement();  //4. 
			rs = stmt.executeQuery(sql);   //5.
			while(rs.next()) {  //6.
				System.out.printf("%d\t%s\t%s\t%s\t%s\t%s\n", 
						rs.getInt("employee_id"), rs.getString("last_name"), 
						rs.getString("email"), rs.getDate("hire_date"), 
						rs.getString("department_name"), rs.getString("city"));
			}
		} catch (SQLException e) {
			System.out.println(e);
		} //7.  
		finally {
			DBClose.close(conn, stmt, rs);
		}
	}
}
