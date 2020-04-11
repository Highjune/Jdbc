import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//ScrollableStatement
//���� ���ϴ� ��ġ�� Ŀ���� ������.

public class ScrollableStatementDemo {
	public static void main(String[] args) throws SQLException {
		Connection conn = 
				DBConnection.getConnection("config/oracle.properties");  //3.
		Statement stmt = conn.createStatement(
				//select�� �ϱ� ���ؼ� INSENSITIVE�� CONSUR_READ_ONLY
				ResultSet.TYPE_SCROLL_INSENSITIVE,  //ResultSet.TYPE_SCROLL_SENSITIVE�� ResultSet.CONSUR_UPDATE�� ������ ����ȭ��ũ.
				ResultSet.CONCUR_READ_ONLY);  //4.
		String sql = "SELECT * FROM emp";
		ResultSet rs = stmt.executeQuery(sql);   //5.emp���̺� ��ü�� �����ؼ� �� ������ ����. (Ŀ���� ���� �� ���ڵ� �ٷ� ���� ��ġ���ִ�)
		rs.afterLast(); //Ŀ���� ���� ���������� �̵�. last�����̶�� ��.(���� ������ ���ڵ� ����)
//		while(rs.previous()) {} //afterLast()�� Ŀ�� ���� ���������� �̵���Ų �� previous()(next�� �ݴ�)�� �Ųٷ� �ö����		
		rs.previous(); // ���� ��ĭ ����(afterlast�� ���� ������ �ű� �� ���� �� ĭ ���� ����)
		System.out.println("name = " + rs.getString("ename")); //���� �������� A�ε�, ���� ���������� ���� �� ĭ �÷��� ������ A.
		
		rs.beforeFirst(); //Ŀ���� ���� ���� �ű�. First�� ��.
		rs.next(); //���� ���� �ű� �� ��ĭ�Ʒ���(next) �д� ����.
		System.out.println("name = " + rs.getString("ename"));
		
		rs.relative(2); //�����ġ. ���� ��ġ�κ��� 2ĭ �Ʒ��� �ִ� �� ����.
		System.out.println("name = " + rs.getString("ename"));
		
		rs.absolute(7); // ������. 7��°. ex) ���������� ������ �� ���ϴ� ������ ���� �� �ִ�.
		System.out.println("name = " + rs.getString("ename"));
		DBClose.close(conn);  //7.
		
	}
}
