Jdbc1 에서 조건 검색하는 것 추가
oracle이 연결되어 있어야 한다.

[강의](https://youtu.be/pFt-Xl9F3vw) - 17분부터 30분까지.  

***

## <select's 7step> (select를 실행하기 위한 7가지 단계)  cf) insert, delete 등의 다른 query는 6번까지만 해도 된다.
1. import
2. Database Driver Loading
3. Database Connection
4. Statement Object 생성
5. Query 실행
6. ResultSet 실행 
7. Close


# SELECT의 경우임(다른 QUERY는 6번까지만)
1. import
import java.sql.*   원래 *로 다 들고올 필요는 없다. 
import javax.sql.*

2. Database Driver Loading
오라클 DATABASE에 연결하려면 연결을 위한 driver가 필요해서 loading해서 들고 와야 한다.

  2.1 Class.forName("oracle.jdbc.driver.OracleDriver");   ==> 외울 필요 없다
  cf) Class클래스로들고온다. 메모리에 올릴 driver의 이름을 string으로 쓴다.
  2.2 해당하는 패키지의 build path로 ojdbc8.jar추가  (자바는 class path, 이클립스는 build path)
  D:\app\June\virtual\product\12.2.0\dbhome_1\jdbc\lib 여기에서 ojdbc8.jar 라는 파일이 있다. 
  이클립스는 ojdbc8.jar 파일의 위치를 모르니까, C:\Program Files\Java\jdk1.8.0_231\jre\lib\ext 이곳에 ojdbc8.jar 를 갖다놔야 한다.
  cf) 자바 설치 후 필요한 jar들은 다 이곳에 옮겨놔야 하는데 단점은 모든 패키지에 다 포함이 된다. 
  그래서 해당 프로젝트 폴더 우클릭 - build path - configure build path - library탭 - add External jars - ojdbc8 포함
  cf)ojdbc8을 열어보면 패키지(=폴더) oracle/jdbc/driver에 들어가보면 OracleDriver.class 파일이 있는데 이것이 오라클 드라이버다.

3. Database Connection

4. Statement Object 생성
자바는 sql라는 문장을 돌리기 때문에 문장 객체를 만들어야 한다. Statement 객체

5. Query 실행

6. ResultSet 실행 

7. Close
-닫을 때는 거꾸로 닫아야 한다.
