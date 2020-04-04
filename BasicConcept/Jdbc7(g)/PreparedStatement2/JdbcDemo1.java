import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//member���̺� ���� ����� ���� ��. (member.sql���Ϸ� ����Ŭ�� �ֱ�)
//preparedstatement�� ���� �̷��� loop ���鼭 insert�� ���� ���� ����.

public class JdbcDemo1 {
	public static void main(String[] args) {
		String [] useridArray = {"aaa", "bbb", "ccc", "ddd", "eee"};
		String [] usernameArray = {"������", "������", "ȫ����", "������", "������"};
		int [] ageArray = {23, 24, 25, 26, 27};
		String [] cityArray = {"����", "�λ�", "����", "����", "�뱸"};
		Connection conn = DBConnection.getConnection("oracle.properties");  //3.DBClose�� DBConnection ������ ���;ߵ�.
		String sql = "INSERT INTO Member(userid, username, userage, usercity) " +
					"VALUES(?,?,?,?) "; //�� sql���� ���� �� ����Ա�! 
		//5���� �����˻�, ��ü�˻縦 �� �ʿ���� �� sql�� �����˻�, ��ü�˻� 1���� �ϱ�.
		 
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement(sql); //4.
			for(int i = 0; i<5 ; i++) { //�Ѱ��� statement�� ���� 5�� ������� �� �ִ�!
				//Batch�� �����.  ��ġ Buffer�� �־ �ѹ��� flush �ϵ���
				pstmt.setString(1, useridArray[i]);
				pstmt.setString(2, usernameArray[i]);
				pstmt.setInt(3, ageArray[i]);
				pstmt.setString(4, cityArray[i]);
				pstmt.addBatch(); //Batch�� �ϳ��� ����.
			}
			int[] rowArray = pstmt.executeBatch(); //���� Batch ������ ���� �ѹ��� ����. �������� ��Ƽ� �����߱⿡ ������ int�迭
			if(rowArray.length == 5) //���ڵ� insert�� ����. ���� 5�� �ƴϸ� �� ���ٴ� ���̴ϱ�. 
				System.out.println("Insert Success"); 
			else System.out.println("Insert Failure"); //6.
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
}
