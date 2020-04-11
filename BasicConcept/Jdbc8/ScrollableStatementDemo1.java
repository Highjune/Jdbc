import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScrollableStatementDemo1 {
	public static void main(String[] args) throws SQLException {
		Connection conn = 
				DBConnection.getConnection("config/oracle.properties");  //3.
		String sql = "SELECT empno, ename, sal FROM emp_copy";
		PreparedStatement pstmt = 
				conn.prepareStatement(sql,       //
				ResultSet.TYPE_SCROLL_SENSITIVE,  //UPDATABLE - ���� ���̺��� ���� ��ġ�� ��.
				ResultSet.CONCUR_UPDATABLE);  //4.
		
		ResultSet rs = pstmt.executeQuery(sql);
		//rs.absolute(7); //�̷��� ���� ��ġ�� ������� rs.updateŸ��(�÷�����, ������ ��); ���� �ϸ� ����
		//rs.deleteRow(); //�̷��� Ŀ���� ������� delete�� �ϸ� ������ �ȴ�. �� �������� ���� �ȴ�. �׸��� ������ ���� ������ ��ũ�� �ȴ�. 
		
		//SCOTT�� �̸��� TIGER�� �ٲٱ�
		//rs.absolute(7); 7��°�� scott�� ������ 7��°�� Ŀ�� ����.
		//rs.updateString(2, "TIGER"); //scott�� tiger�� �̸� �ٲ�
		//rs.updateRow(); //update�������� �ϴ� ����. "������ �ִ� ���� �ٳ�Ͷ�!"��� �ǹ���. �� ����ȭ(��ũ)
		
		rs.moveToInsertRow(); //insert�� row�� �̵��ϴ� ��. ���� ���������� �̵�.
		rs.updateInt(1, 9999);// empno
		rs.updateString(2, "������"); // ename 
		rs.updateDouble(3, 10000); // sal
		rs.insertRow(); //update�������� �ϴ� ����.
		DBClose.close(conn, pstmt, rs);  //7.
	}
}
