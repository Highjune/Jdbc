transaction
JDBC의 마지막 문법 중 1개(resultsetMetadata, transaction)(실수령액관리프로그램 이후 수업) 
cf) 사실 javax.sql의 Datasource도 배워야 한다. 이것이 바로 connection pool. 

local의 오라클과도 연동, 192.168.56.2의 mariadb와도 연동

https://youtu.be/-E-nqF2dxNo 30분까지.

transaction을 처리하려면 우리는 db에서 commit이 일어나면 안되기 때문에 자바쪽에서 commit을 막아야 된다.
그래서 setAutocommit(false)
이렇게 해놔야지 필요한 경우 따로 commit명령어를 줄 때만 commit이 된다



cf) 드라이버타입
우리는 드라이버 타입 4를 쓰는데, 사실 타입에 대해서 크게 연연x
타입4는 자바로 만든 드라이버를 쓰는 것.