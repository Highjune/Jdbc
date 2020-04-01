import java.sql.*;

//1


/* JDBC
 * select's 7step (select를 실행하기 위한 7가지 단계) insert, delete 등의 다른 query는 6번까지만 해도 된다.
 * 1. import
 * 2. Database Driver Loading
 * 3. Database Connection
 * 4. Statement Object 생성
 * 5. Query 실행
 * 6. ResultSet 실행 
 * 7. Close
 */

public class Jdbc1 {
   public static void main(String[] args) {
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver"); //2
         System.out.println("Driver Loading Success");
      } catch (ClassNotFoundException e) {
         System.out.println("Driver Not Found"); 
      } //메모리에 oracle driver가 올라온다. (이 oracle driver는 driver manager가 관리한다)
      
      //3
      final String USER = "scott";
      final String PASSWORD = "tiger";
      //아래의 URL은 RDBMS마다 다 다르다. (마리아db는 또 다 다르게 해야 한다.)
      //프로토콜 : jdbc:(모든 JDBC의 URL은 이렇게 시작), 제품명 : oracle, 드라이버 : thin(oracle은 드라이버가 2개임, OCID와 THIN)
      //머신명(ip) : localhost, port: 1521, SID : ORCL
      final String URL = "jdbc:oracle:thin:@localhost:1521:ORCL";  
      Connection conn = null;
      try {
         conn = DriverManager.getConnection(URL, USER, PASSWORD); // getConnection은 3가지가 overloading되어있는데 상황에 따라 다르게.
         System.out.println("Conn Success");
      } catch (SQLException e) {
         System.out.println("Conn Fail");
      }
      //4
      Statement stmt = null; 
      try {
         stmt = conn.createStatement();
      } catch (SQLException e) {
         System.out.println(e);
      }
      
      //5, 6
      String sql = "select empno, ename, sal, deptno from emp"; //어떤 query문이든 간에 개체검사, 문법검사를 한다. 개체검사는 emp테이블이 존재?와 같은 것이고 문법검사는 말 그대로 from절의 위치 등을 말한다.
      ResultSet rs = null; //데이터베이스로부터 query를 던진 결과를 받은 바구니 = Resultset
      try {
         rs = stmt.executeQuery(sql);  //select만 executeQuery. 나머지는 다 executeUpdate.
      } catch (SQLException e) { 
         System.out.println(e);
      }
      try {
         while (rs.next()) { //rs는 커서. ResultSet에 담긴 n개의 레코드의 제일 위에 가리키고 있는데 거기서부터 하나씩 next()로 읽음. next()가 있다면 true, 없다면 false.
        	//한 레코드에 대해서 각각의 컬럼값들을 들고 오는데 DB에 맞는 java의 데이터형으로 읽어와야 한다.(driver가 번역해줌) 하나씩 읽어오는 순서는 테이블의 진짜 순서가 아니라 우리가 select한 컬럼의 순서다.
        	int empno = rs.getInt(1);   //JDBC에서는 index가 1부터 시작한다. 첫번째 값인데 index가 0이 아니다. 
            String ename = rs.getString("ename");  //rs.getString(2);로 해도 되고 컬럼의 이름으로 받아도 되고. varchar2(ename)은 String으로 
            double sal = rs.getDouble("sal");
            int deptno = rs.getInt(4);
            System.out.printf("%d\t %s\t %.2f\t %d\n", empno, ename, sal, deptno);
         }
      } catch (SQLException e) {
         System.out.println(e);
      }
      
      
      try { //7
         if (rs != null) rs.close();
         if (stmt != null) stmt.close();
         if (conn != null) conn.close();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}

