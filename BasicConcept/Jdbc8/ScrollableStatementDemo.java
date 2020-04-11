import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//ScrollableStatement
//내가 원하는 위치로 커서를 움직임.

public class ScrollableStatementDemo {
	public static void main(String[] args) throws SQLException {
		Connection conn = 
				DBConnection.getConnection("config/oracle.properties");  //3.
		Statement stmt = conn.createStatement(
				//select만 하기 위해서 INSENSITIVE의 CONSUR_READ_ONLY
				ResultSet.TYPE_SCROLL_INSENSITIVE,  //ResultSet.TYPE_SCROLL_SENSITIVE의 ResultSet.CONSUR_UPDATE는 원본과 동기화씽크.
				ResultSet.CONCUR_READ_ONLY);  //4.
		String sql = "SELECT * FROM emp";
		ResultSet rs = stmt.executeQuery(sql);   //5.emp테이블 전체를 복사해서 다 들고오는 것임. (커서는 제일 위 레코드 바로 위에 위치해있다)
		rs.afterLast(); //커서를 제일 마지막으로 이동. last다음이라는 말.(제일 마지막 레코드 다음)
//		while(rs.previous()) {} //afterLast()로 커서 제일 마지막으로 이동시킨 후 previous()(next의 반대)로 거꾸로 올라오기		
		rs.previous(); // 위로 한칸 읽음(afterlast로 제일 밑으로 옮긴 후 위로 한 칸 읽은 것임)
		System.out.println("name = " + rs.getString("ename")); //제일 마지막은 A인데, 제일 마지막에서 위로 한 칸 올려서 읽으니 A.
		
		rs.beforeFirst(); //커서를 제일 위로 옮김. First의 전.
		rs.next(); //제일 위로 옮긴 후 한칸아래로(next) 읽는 것임.
		System.out.println("name = " + rs.getString("ename"));
		
		rs.relative(2); //상대위치. 현재 위치로부터 2칸 아래에 있는 것 읽음.
		System.out.println("name = " + rs.getString("ename"));
		
		rs.absolute(7); // 절대경로. 7번째. ex) 연봉순으로 나열한 후 원하는 순위를 구할 수 있다.
		System.out.println("name = " + rs.getString("ename"));
		DBClose.close(conn);  //7.
		
	}
}
