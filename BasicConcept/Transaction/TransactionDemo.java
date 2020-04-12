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
			//System.out.println("사원수 : " + rs.getInt(1)); //1번째라고 순서로 받는 이유는 count(empno)라고 조회했기 때문에 컬럼의 이름이 없어서다.
			//String sql = "INSERT INTO emp(empno, ename) VALUES(9998, 'javasoft')";
			//int row = stmt.executeUpdate(sql);
			//if(row == 1) System.out.println("Insert Success");
			String sql = "INSERT INTO city(Name, CountryCode) VALUES('강남', 'KOR')";
			int row = stmt.executeUpdate(sql);
			if(row == 1) System.out.println("Insert Success");
			conn.commit(); //이상이 없으면 commit
		}catch(SQLException ex) {
			try {
				conn.rollback(); //이상이 있으면 rollback, rollback(Savepoint savepoint)도 있어서 savepoint를 만들어두면 원하는 지점으로 회귀 가능.
			} catch (SQLException e) {
				System.out.println("Rollback Failure.");
			}
			System.out.println(ex);
		}
		//DBClose.close(conn, stmt, rs);  //7.
		DBClose.close(conn, stmt);
	}
}
