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
				ResultSet.TYPE_SCROLL_SENSITIVE,  //UPDATABLE - 원본 테이블에도 영향 끼치는 것.
				ResultSet.CONCUR_UPDATABLE);  //4.
		
		ResultSet rs = pstmt.executeQuery(sql);
		//rs.absolute(7); //이렇게 절대 위치로 맞춰놓고 rs.update타입(컬럼차례, 수정할 값); 으로 하면 편함
		//rs.deleteRow(); //이렇게 커서만 맞춰놓고 delete만 하면 삭제가 된다. 즉 쿼리문을 몰라도 된다. 그리고 삭제된 것은 원본과 씽크가 된다. 
		
		//SCOTT의 이름을 TIGER로 바꾸기
		//rs.absolute(7); 7번째에 scott가 있으니 7번째에 커서 맞춤.
		//rs.updateString(2, "TIGER"); //scott을 tiger로 이름 바꿈
		//rs.updateRow(); //update최종으로 하는 것임. "원본이 있는 곳에 다녀와라!"라는 의미임. 즉 동기화(씽크)
		
		rs.moveToInsertRow(); //insert할 row로 이동하는 것. 제일 마지막으로 이동.
		rs.updateInt(1, 9999);// empno
		rs.updateString(2, "한지민"); // ename 
		rs.updateDouble(3, 10000); // sal
		rs.insertRow(); //update최종으로 하는 것임.
		DBClose.close(conn, pstmt, rs);  //7.
	}
}
