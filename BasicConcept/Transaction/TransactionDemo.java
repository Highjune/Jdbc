import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionDemo {
	public static void main(String[] args) {
		Connection conn = DBConnection.getConnection("config/mariadb.properties");  //3
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn.setAutoCommit(false); 
			stmt = conn.createStatement();   //4.
			//String sql = "SELECT COUNT(empno) FROM emp";
			//rs = stmt.executeQuery(sql);  //5.
			//rs.next();
			//System.out.println("����� : " + rs.getInt(1)); //1��°��� ������ �޴� ������ count(empno)��� ��ȸ�߱� ������ �÷��� �̸��� �����.
			//String sql = "INSERT INTO emp(empno, ename) VALUES(9998, 'javasoft')";
			//int row = stmt.executeUpdate(sql);
			//if(row == 1) System.out.println("Insert Success");
			String sql = "INSERT INTO city(Name, CountryCode) VALUES('����', 'KOR')";
			int row = stmt.executeUpdate(sql);
			if(row == 1) System.out.println("Insert Success");
			conn.commit(); //�̻��� ������ commit
		}catch(SQLException ex) {
			try {
				conn.rollback(); //�̻��� ������ rollback, rollback(Savepoint savepoint)�� �־ savepoint�� �����θ� ���ϴ� �������� ȸ�� ����.
			} catch (SQLException e) {
				System.out.println("Rollback Failure.");
			}
			System.out.println(ex);
		}
		//DBClose.close(conn, stmt, rs);  //7.
		DBClose.close(conn, stmt);
	}
}
