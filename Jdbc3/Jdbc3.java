import java.sql.*; //1�ܰ�(import)


/*�ڹ߰����ڴ� CREATE, DROP�� ������ ���Ѵ�~!
 * ���� �Ʒ��͵鵵 �������� �׳� ���� ��. �����δ� stored procedure�� ���� �� ���̴�.
 * INSERT / UPDATE / DELETE 
 * 1. import
 * 2. Database Driver Loading
 * 3. Database Connection
 * 4. Statement Object ����
 * 5. Query ����
 * 6. Close ����
*/


public class Jdbc3 {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //2
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Not Found"); 
		} 
		
		//�Ʒ��͵��� �� �������̽��� new�� �� ����.
		Connection conn = null; //3
		Statement stmt = null; //4
		try { 
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", //�� ����� ������ ������ �� �ڵ带 ������ �� ��й�ȣ�� �� ���δ�. => ��� �� ����� ���� �� ����.
					"scott", "tiger");
			stmt = conn.createStatement();
			String sql = "INSERT INTO dept(deptno, dname, loc) ";
			sql += "VALUES(70, 'DESIGN', 'SEOUL') ";
			
			//5
			int row = stmt.executeUpdate(sql); //select���� ������ ��� SQL, insert�� row�� ����
			if(row == 1) System.out.println("Insert Success."); //row==0�̶� ���� insert�� �ȵƴٴ� ��, 2�� �̻� ���� �ȵ�(1�� ���ڵ常 �־�����)
			
			//6
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) { //������ 3, 4�� exception �� �� ���� ������ �ϳ��� try�ȿ� ������.
			System.out.println(e);
		} 
		
	}	
	
}
