import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


//resultset�� ���������� resultsetmetadata�� ���� �� �ִ�.
public class ResultSetMetadataDemo {
	public static void main(String[] args) throws SQLException {
		Connection conn = DBConnection.getConnection("config/oracle.properties"); //3.
		Statement stmt = conn.createStatement();   //4.
		//String sql = "SELECT ID as \"���þ��̵�\", Name as \"�����̸�\" FROM city";
		//String sql = "SELECT * FROM city";
		String sql = "SELECT * FROM emp";
		ResultSet rs = stmt.executeQuery(sql);   //5.
		ResultSetMetaData rsmd = rs.getMetaData(); //resultsetmetadata�� ���� �� �ִ� ������ ���
		//System.out.println(rsmd.getColumnCount()); //�÷��� ������ �� �� �ִ�.(erd�� �� �� ����) 
		for(int i = 1 ; i <= rsmd.getColumnCount() ;  i++) {  //1 ~ 5 (jdbc�� index�� 1����)
			//System.out.print(rsmd.getCatalogName(i) + "\t"); //�����ͺ��̽�(��Ű��) �̸�
			//System.out.print(rsmd.getColumnLabel(i) + "\t");  //�÷��� ��Ī(��Ī�� ���� ���� �ʾҴٸ� �̸��� ����)
			System.out.print(rsmd.getColumnName(i) + "\t"); //�÷��� �̸�
			//System.out.print(rsmd.getColumnDisplaySize(i) + "\t");
			if(rsmd.getColumnTypeName(i).equals("NUMBER")) {  
				//type�� ���ؼ� ���� ���� number��� �ߴٸ�(����Ŭ������ int, double ���� �����Ƿ�) �������� �Ǽ����� �ٽ� �˾Ƴ��� �� ��Ȯ. �׷��� precision�� scale�� ���� �˾Ƴ��� �ȴ�.
				//(7, 2) ���� 7�� precision. 2�� scale.
				System.out.print("(" + rsmd.getPrecision(i) + ", " + rsmd.getScale(i) + ")\t"); //ex. (4, 0) ���ڸ��� scale�� scale�� 0�̶�� ���� �������̶� ��. ���� (7, 2)�� �Ҽ��� 2�ڸ����� �̹Ƿ� �Ҽ��� ��. �� double�� �о�� ��.
			}else {  
				System.out.print(rsmd.getColumnDisplaySize(i) + "\t");
			}
			System.out.print(rsmd.getColumnTypeName(i) + "\n");
		}
		
		DBClose.close(conn, stmt, rs); //7.
	}
}
