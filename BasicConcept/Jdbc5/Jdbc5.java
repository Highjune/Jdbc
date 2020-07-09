import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Jdbc5 {

	private Jdbc5() {
		Properties info = new Properties();
	}
		
		public static void main(String[] args) throws ClassNotFoundException, SQLException  {
		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Properties info = new Properties(); //Properties는 Map계열 중 1개.
			info.setProperty("user", "scott"); //집어넣을 때는 setProperty, 뺄 때는 getProperty
			info.setProperty("password", "tiger");
			
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", info);
			
			Statement stmt = conn.createStatement(); //4번까지 한 것임
			String sql = "SELECT empno, ename, TO_CHAR(hiredate, 'yyyy-mm-dd'), " +
					"dname, loc, dept.deptno " + 
					"FROM emp INNER JOIN dept " +
					"ON emp.deptno = dept.deptno " +
					"WHERE dept.loc = 'DALLAS'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				System.out.printf("%d\t%s\t%s\t%s\t%s\t%d\n",
						rs.getInt("empno"), rs.getString("ename"), rs.getString(3),
						rs.getString("dname"), rs.getString("loc"), rs.getInt(6));  
		}
		
		if(rs != null) rs.close();	if(stmt != null) stmt.close(); if(conn != null) conn.close();
	}
}












