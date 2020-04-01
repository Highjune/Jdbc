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
		                 "ORDER BY employee_id ASC"; //stored procedure�� ���� �̰ͺ��� �ξ� �����ϰ�, ���ȼ��� ����(���̺� ���� �������� �����Ƿ�)
		//4. 
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {  //���� �� �������� �ϳ��� try�ȿ� ���� ������ ������ ���� �߻��ϴ� ���� �����ϱ� �ѹ��� ��������.
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
