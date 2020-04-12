import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


//resultset을 만들어야지만 resultsetmetadata를 얻을 수 있다.
public class ResultSetMetadataDemo {
	public static void main(String[] args) throws SQLException {
		Connection conn = DBConnection.getConnection("config/oracle.properties"); //3.
		Statement stmt = conn.createStatement();   //4.
		//String sql = "SELECT ID as \"도시아이디\", Name as \"도시이름\" FROM city";
		//String sql = "SELECT * FROM city";
		String sql = "SELECT * FROM emp";
		ResultSet rs = stmt.executeQuery(sql);   //5.
		ResultSetMetaData rsmd = rs.getMetaData(); //resultsetmetadata를 만들 수 있는 유일한 방법
		//System.out.println(rsmd.getColumnCount()); //컬럼의 갯수를 알 수 있다.(erd를 못 본 상태) 
		for(int i = 1 ; i <= rsmd.getColumnCount() ;  i++) {  //1 ~ 5 (jdbc는 index가 1부터)
			//System.out.print(rsmd.getCatalogName(i) + "\t"); //데이터베이스(스키마) 이름
			//System.out.print(rsmd.getColumnLabel(i) + "\t");  //컬럼의 별칭(별칭을 따로 쓰지 않았다면 이름과 같다)
			System.out.print(rsmd.getColumnName(i) + "\t"); //컬럼의 이름
			//System.out.print(rsmd.getColumnDisplaySize(i) + "\t");
			if(rsmd.getColumnTypeName(i).equals("NUMBER")) {  
				//type을 구해서 나온 것이 number라고 했다면(오라클에서는 int, double 등이 없으므로) 정수인지 실수인지 다시 알아내야 더 정확. 그래서 precision과 scale을 같이 알아내야 된다.
				//(7, 2) 에서 7이 precision. 2가 scale.
				System.out.print("(" + rsmd.getPrecision(i) + ", " + rsmd.getScale(i) + ")\t"); //ex. (4, 0) 뒷자리가 scale임 scale이 0이라는 것은 정수형이란 말. 만약 (7, 2)면 소수점 2자리까지 이므로 소수란 말. 즉 double로 읽어야 됨.
			}else {  
				System.out.print(rsmd.getColumnDisplaySize(i) + "\t");
			}
			System.out.print(rsmd.getColumnTypeName(i) + "\n");
		}
		
		DBClose.close(conn, stmt, rs); //7.
	}
}
