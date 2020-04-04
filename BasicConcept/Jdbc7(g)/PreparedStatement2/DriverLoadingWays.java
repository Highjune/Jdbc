import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import oracle.jdbc.driver.OracleDriver;

public class DriverLoadingWays {
	public static void main(String[] args) throws SQLException {
		//Driver Loading�ϴ� 1��° ��� 
		//Class.forname()
		//ClassNotFoundException ó���ؾߵ� (SQLException�� �ƴ�) 
		//Class.forName("oracle.jdbc.driver.OracleDriver");
			
		//2��° ���(SQLException). 
		//DriverManager.registerDriver(new OracleDriver());
		//�� ����� ������ �ٸ� �͵��� �ʷ��ϴ� SQLECeption�� �����ϹǷ� �ϳ��� ���ܿ� �� ���� �� �ִ�.
//		try {		 //cf) �����ϴ� deregisterDriver�� ����.
//			DriverManager.registerDriver(new OracleDriver()); //2. 	������ import oracle.jdbc.driver.OracleDriver;����� ��. �ƴϸ� new  oracle.jdbc.driver.OracleDriver()�� �־�� �Ѵ�.
//		}catch(SQLException ex) {
//		
//		}
		
		//3���� ��� (���� ����)
		//Connection�� DriverManager��.
//		try {	
//			DriverManager.registerDriver(new OracleDriver()); //
//			DriverManager.getConnection(url, user, password);  //
//		}catch(SQLException ex) {
//		
//		}	
		
		//4��° ��� (���� ��õ)
		//oracle.properties�� ���� ���Ϸ� ����� Ŭ����(DBConnection)�� ����.
		//ex) �Ʒ�
		
		//preparedStatement�� ȿ�뼺�� : loop���鼭 insert�� �� ���� ũ�� ����. ����.		
		Connection conn = DBConnection.getConnection("oracle.properties"); 
		String sql = "SELECT employee_id, first_name, last_name FROM Employees " +
				"WHERE employee_id = ?" ; //������ ������ �ƴ϶� ?�� �����.
		PreparedStatement pstmt = conn.prepareStatement(sql);   //4.
		pstmt.setInt(1, 108);  // ���� employee_id = 108 �� ����  //1��ſ� �÷�(EMPLOYEE_ID)�̸��ᵵ �ȴ�.
		pstmt.executeQuery(); //���� preparesatement�ȿ� �̹� sql �־��� ������ executeQuery�ȿ��� �ƹ��͵� �� �ᵵ �ȴ�.
		ResultSet rs = pstmt.executeQuery(); //5.
		rs.next(); //���� resultSet�ȿ� employee_id, first_name, last_name  3�� ����ִ� ����
		System.out.println("��� : " + rs.getInt(1));
		System.out.println("�̸� : " + rs.getString("first_name"));
		System.out.println("�� : " + rs.getString(3));
		
		DBClose.close(conn, pstmt, rs);
	}
}
