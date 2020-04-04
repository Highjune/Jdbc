import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import oracle.jdbc.driver.OracleDriver;

public class JdbcDemo {
	public static void main(String[] args) throws SQLException {
		
		//preparedStatement�� interface�̹Ƿ� new�� �� �����, connection�� ���ؼ� ���������.
		//preparedStatement�� ȿ�뼺�� : loop���鼭 insert�� �� ���� ũ�� Ȱ��	
		Connection conn = DBConnection.getConnection("oracle.properties"); 
		String sql = "SELECT employee_id, first_name, last_name FROM Employees " +
				"WHERE employee_id = ?" ; //������ ������ �ƴ϶� ?�� �����.
		PreparedStatement pstmt = conn.prepareStatement(sql);   //4. connection�� preparestatement�� ���ؼ� preparedStatement�� ����
		pstmt.setInt(1, 108);  // ���� employee_id = 108 �� ����  //1��ſ� �÷�(EMPLOYEE_ID)�̸��ᵵ �ȴ�.
		pstmt.executeQuery(); //���� preparesatement�ȿ� �̹� sql �־��� ������ executeQuery�ȿ��� �ƹ��͵� �� �ᵵ �ȴ�.
		ResultSet rs = pstmt.executeQuery(); //5. select�� �׻� ResultSet�̶�� �ٱ��Ͽ� ��ƾ� �Ѵ�.
		rs.next(); //���� resultSet�ȿ� employee_id, first_name, last_name  3�� ����ִ� ����, next()�� �ؾ��� �ݵ�� �д´�.
		System.out.println("��� : " + rs.getInt(1)); //1, 2, 3�� ������ ������ select�� ����.
		System.out.println("�̸� : " + rs.getString("first_name")); //index�� �ص� �ǰ� �̷��� �÷� �̸� �ᵵ �ǰ�.
		System.out.println("�� : " + rs.getString(3));
		
		DBClose.close(conn, pstmt, rs);
	}
}
