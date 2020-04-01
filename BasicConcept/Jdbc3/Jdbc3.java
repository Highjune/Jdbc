import java.sql.*; //1단계(import)


/*자발개발자는 CREATE, DROP는 쓰지도 못한다~!
 * 물론 아래것들도 못하지만 그냥 배우는 것. 실제로는 stored procedure만 쓰게 될 것이다.
 * INSERT / UPDATE / DELETE 
 * 1. import
 * 2. Database Driver Loading
 * 3. Database Connection
 * 4. Statement Object 생성
 * 5. Query 실행
 * 6. Close 실행
*/


public class Jdbc3 {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //2
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Not Found"); 
		} 
		
		//아래것들이 다 인터페이스라서 new를 안 쓴다.
		Connection conn = null; //3
		Statement stmt = null; //4
		try { 
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", //이 방식의 단점은 누군가 이 코드를 열었을 때 비밀번호가 다 보인다. => 사실 이 방법은 절대 안 쓴다.
					"scott", "tiger");
			stmt = conn.createStatement();
			String sql = "INSERT INTO dept(deptno, dname, loc) ";
			sql += "VALUES(70, 'DESIGN', 'SEOUL') ";
			
			//5
			int row = stmt.executeUpdate(sql); //select제외 나머지 모든 SQL, insert한 row의 갯수
			if(row == 1) System.out.println("Insert Success."); //row==0이란 말은 insert가 안됐다는 말, 2개 이상도 말이 안됨(1개 레코드만 넣었으니)
			
			//6
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) { //어차피 3, 4의 exception 둘 다 같은 종류라서 하나의 try안에 묶었다.
			System.out.println(e);
		} 
		
	}	
	
}
