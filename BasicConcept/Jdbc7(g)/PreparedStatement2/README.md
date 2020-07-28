## prepared statement (oracle DB사용) - 자주 쓰이므로 중요  
cf) stored procedure가 없는 데이터베이스(ex. SQL lite, derby) 는 callable statement를 못 쓰니까 prepared statement가 제일 빠른 jdbc방법임.  

[강의](https://youtu.be/4hTkrm64Y9E) -  Driver Loading하는 방법 4가지. + preparedstatment (32분에 local oracle에서 member테이블 간단하게 만들기. preparedstatement는 loop돌면서 insert할 때 효옹성이 가장 높은데, 그것 실험하기 위해서)
[강의](https://youtu.be/0-7-u40qBPk) - 10분까지


### (참고, Driver Loading하는 방법) (DriverLoadingWays.java파일 참고)
1) Class.forName - 80%이상 이것을 쓴다. 클래스 이름을 string으로 쓸 수 있으니까.
ex) Class.forName("oracle.jdbc.driver.OracleDriver");

2) DriverManager.registerDriver - 파라미터가 driver형이라서 new로 써야된다. 그러나 예외의 종류가 SQL이므로 다른 것들과 같아서 하나의 예외에 묶을 수가 있다. 20%로 정도
ex) DriverManager.registerDriver(new OracleDriver());
a
3) Connection과 DriverManager로 (거의 안함)
ex) 
Connection conn = DBConnection.getConnection("oracle.properties");
DriverManager.getConnection(url, user, password);

4) oracle.properties로 따로 파일로 만들고 클래스(DBConnection)를 만들어서.

