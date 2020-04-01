import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc4 {
	private final String DRIVER; //��������
	private final String URL;
	private final String USER;
	private final String PASSWORD;
	private Jdbc4() { //������ �ʱ�ȭ ����
		this.DRIVER = "oracle.jdbc.driver.OracleDriver";
		this.URL = "jdbc:oracle:thin:@localhost:1521:ORCL";
		this.USER = "scott";
		this.PASSWORD = "tiger"; // => �׷��� �̷� ������� ���߿� �� file�� ���� ���ϰ� �� ����.
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException { //���� ���ܴ� ������ �ȵ����� ���ǻ�
		Jdbc4 jd = new Jdbc4(); //4�� ��� ���õǾ�����
		//���, �̸�, �Ի糯¥, �ٹ��μ���, �ٹ���, �ٹ���(DALLAS), �μ���ȣ ==> �� �ҷ��;� �Ǵϱ� innerjoin��� �Ǵ±���~!
		Class.forName(jd.DRIVER);
		Connection conn = DriverManager.getConnection(
				jd.URL, jd.USER, jd.PASSWORD);
		
		Statement stmt = conn.createStatement(); //4������ �� ����
		String sql = "SELECT empno, ename, TO_CHAR(hiredate, 'yyyy-mm-dd'), " +
				"dname, loc, dept.deptno " + 
				"FROM emp INNER JOIN dept " +
				"ON emp.deptno = dept.deptno " +
				"WHERE dept.loc = 'DALLAS'";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){ //���� ���ڵ尡 ���� ����.
			System.out.printf("%d\t%s\t%s\t%s\t%s\t%d\n",
					rs.getInt("empno"), rs.getString("ename"), rs.getString(3), //column�� index�� �ص� �ǰ� �̸����� �ص� �ȴ�. �׷��� �̸����� �ϴ� ���� ���� �� �� ��. ���� to_char()������ ��ȭ���� �� ���� �ִٸ� �÷� ���� �� ����.
					rs.getString("dname"), rs.getString("loc"), rs.getInt(6));  
		}		
		if(rs != null) rs.close();	if(stmt != null) stmt.close(); if(conn != null) conn.close();
	}
}














