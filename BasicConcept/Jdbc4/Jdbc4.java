import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc4 {
	private final String DRIVER; //멤버상수들
	private final String URL;
	private final String USER;
	private final String PASSWORD;
	private Jdbc4() { //멤버상수 초기화 위해
		this.DRIVER = "oracle.jdbc.driver.OracleDriver";
		this.URL = "jdbc:oracle:thin:@localhost:1521:ORCL";
		this.USER = "scott";
		this.PASSWORD = "tiger"; // => 그런데 이런 상수들은 나중에 다 file로 빼서 편하게 할 예정.
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException { //원래 예외는 던지면 안되지만 편의상
		Jdbc4 jd = new Jdbc4(); //4개 상수 세팅되어있음
		//사번, 이름, 입사날짜, 근무부서명, 근무지, 근무지(DALLAS), 부서번호 ==> 다 불러와야 되니까 innerjoin써야 되는구나~!
		Class.forName(jd.DRIVER);
		Connection conn = DriverManager.getConnection(
				jd.URL, jd.USER, jd.PASSWORD);
		
		Statement stmt = conn.createStatement(); //4번까지 한 것임
		String sql = "SELECT empno, ename, TO_CHAR(hiredate, 'yyyy-mm-dd'), " +
				"dname, loc, dept.deptno " + 
				"FROM emp INNER JOIN dept " +
				"ON emp.deptno = dept.deptno " +
				"WHERE dept.loc = 'DALLAS'";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){ //다음 레코드가 있을 동안.
			System.out.printf("%d\t%s\t%s\t%s\t%s\t%d\n",
					rs.getInt("empno"), rs.getString("ename"), rs.getString(3), //column의 index로 해도 되고 이름으로 해도 된다. 그런데 이름으로 하는 것이 이해 더 잘 됨. 만약 to_char()등으로 변화값을 준 것이 있다면 컬럼 값이 더 편함.
					rs.getString("dname"), rs.getString("loc"), rs.getInt(6));  
		}		
		if(rs != null) rs.close();	if(stmt != null) stmt.close(); if(conn != null) conn.close();
	}
}














