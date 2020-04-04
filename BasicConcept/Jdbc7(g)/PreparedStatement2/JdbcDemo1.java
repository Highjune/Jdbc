import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//member테이블 먼저 만들어 놔야 됨. (member.sql파일로 오라클에 넣기)
//preparedstatement쓸 때는 이렇게 loop 돌면서 insert할 때가 제일 편함.

public class JdbcDemo1 {
	public static void main(String[] args) {
		String [] useridArray = {"aaa", "bbb", "ccc", "ddd", "eee"};
		String [] usernameArray = {"한지민", "박지민", "홍지민", "이지민", "김지민"};
		int [] ageArray = {23, 24, 25, 26, 27};
		String [] cityArray = {"서울", "부산", "광주", "대전", "대구"};
		Connection conn = DBConnection.getConnection("oracle.properties");  //3.DBClose와 DBConnection 파일을 들고와야됨.
		String sql = "INSERT INTO Member(userid, username, userage, usercity) " +
					"VALUES(?,?,?,?) "; //이 sql문을 여러 번 우려먹기! 
		//5번의 문법검사, 개체검사를 할 필요없고 이 sql의 문법검사, 개체검사 1번만 하기.
		 
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement(sql); //4.
			for(int i = 0; i<5 ; i++) { //한개의 statement를 통해 5번 우려먹을 수 있다!
				//Batch를 만든다.  마치 Buffer에 넣어서 한번에 flush 하듯이
				pstmt.setString(1, useridArray[i]);
				pstmt.setString(2, usernameArray[i]);
				pstmt.setInt(3, ageArray[i]);
				pstmt.setString(4, cityArray[i]);
				pstmt.addBatch(); //Batch에 하나씩 담음.
			}
			int[] rowArray = pstmt.executeBatch(); //담은 Batch 파일을 만들어서 한번에 실행. 여러개를 담아서 실행했기에 리턴은 int배열
			if(rowArray.length == 5) //레코드 insert한 개수. 만약 5가 아니면 덜 들어갔다는 말이니까. 
				System.out.println("Insert Success"); 
			else System.out.println("Insert Failure"); //6.
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
}
