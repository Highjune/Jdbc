import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner; 
//1(import)


/* JDBC
 * select's 7step (select�� �����ϱ� ���� 7���� �ܰ�) insert, delete ���� �ٸ� query�� 6�������� �ص� �ȴ�.
 * 1. import
 * 2. Database Driver Loading
 * 3. Database Connection
 * 4. Statement Object ����
 * 5. Query ����
 * 6. ResultSet ���� 
 * 7. Close
 */

public class Jdbc2 {
   public static void main(String[] args) {
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver"); //2
         System.out.println("Driver Loading Success");
      } catch (ClassNotFoundException e) {
         System.out.println("Driver Not Found"); 
      } //�޸𸮿� oracle driver�� �ö�´�. (�� oracle driver�� driver manager�� �����Ѵ�)
      
      //3
      final String USER = "scott";
      final String PASSWORD = "tiger";
      //�Ʒ��� URL�� RDBMS���� �� �ٸ���. (������db�� �� �� �ٸ��� �ؾ� �Ѵ�.)
      //�������� : jdbc:(��� JDBC�� URL�� �̷��� ����), ��ǰ�� : oracle, ����̹� : thin(oracle�� ����̹��� 2����, OCID�� THIN)
      //�ӽŸ�(ip) : localhost, port: 1521, SID : ORCL
      final String URL = "jdbc:oracle:thin:@localhost:1521:ORCL";  
      Connection conn = null;
      try {
         conn = DriverManager.getConnection(URL, USER, PASSWORD); // getConnection�� 3������ overloading�Ǿ��ִµ� ��Ȳ�� ���� �ٸ���.
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
      Scanner scan = new Scanner(System.in);
      System.out.print("����� �̸� : ");
      String name = scan.next(); //scott -> SCOTT�� �ٲٴ� ���� �ڹٿ��� �ٲ�� �Ѵ�.(DB���� �ٲ㵵 �Ǳ� �ϴ�)
      name = name.toUpperCase(); //query���� ��ҹ��� ������������ ������ ��ü�� ��ҹ��ڸ� �����ؾ� �Ǳ⿡ �빮�� SCOTT�� �ٲ�� �Ѵ�.
      
      //5, 6
      String sql = "select empno, ename, sal, deptno from emp "; //� query���̵� ���� ��ü�˻�, �����˻縦 �Ѵ�. ��ü�˻�� emp���̺��� ����?�� ���� ���̰� �����˻�� �� �״�� from���� ��ġ ���� ���Ѵ�.
      sql += "WHERE ename = '" + name + "' "; //"WHERE ename = 'SCOTT'";   //oracle���� ""��ߵǴ� ��� : ��Ī(as)���� Ư�����ڳ� �ѱ� �� ��.
      ResultSet rs = null; //�����ͺ��̽��κ��� query�� ���� ����� ���� �ٱ��� = Resultset
      try {
         rs = stmt.executeQuery(sql); 
      } catch (SQLException e) {
         System.out.println(e);
      }
      try {
         while (rs.next()) { //rs�� Ŀ��. ResultSet�� ��� n���� ���ڵ��� ���� ���� ����Ű�� �ִµ� �ű⼭���� �ϳ��� next()�� ����. next()�� �ִٸ� true, ���ٸ� false.
        	//�� ���ڵ忡 ���ؼ� ������ �÷������� ��� ���µ� DB�� �´� java�� ������������ �о�;� �Ѵ�.(driver�� ��������) �ϳ��� �о���� ������ ���̺��� ��¥ ������ �ƴ϶� �츮�� select�� �÷��� ������.
        	int empno = rs.getInt(1);   //JDBC������ index�� 1���� �����Ѵ�. ù��° ���ε� index�� 0�� �ƴϴ�. 
            String ename = rs.getString("ename");  //rs.getString(2);�� �ص� �ǰ� �÷��� �̸����� �޾Ƶ� �ǰ�. varchar2(ename)�� String���� 
            double sal = rs.getDouble("sal");
            int deptno = rs.getInt(4);
            System.out.printf("%d\t %s\t %.1f\t %d\n", empno, ename, sal, deptno);
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

