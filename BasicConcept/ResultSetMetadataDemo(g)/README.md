## resultsetmetadata  

JDBC의 마지막 문법 중 1개(resultsetMetadata, transaction)(실수령액관리프로그램 이후 수업)   

cf) oracle드라이브 연결하고 사용(ojdbc8.jar)  

[강의](https://youtu.be/oYAek_4Q1p8) - 5분부터

resultsetmedata는 dba가 스키마의 구조 등에 대해서 알려주지 않는 경우 이를 통해 알아낼 수 있다.(사실 이 목적은 아니다)

metadata는 파일의 내용과는 관심이 없고, 파일의 권한, 이름, 경로, 사이즈 등에 관한 것.
그래서 resultsetmetadata는 resultset의 내용과는 전혀 무관하다. 그래서 테이블 이름, 컬럼 이름, 데이터 타입 등에 관한 것.
테이블의 스키마를 모르고 있는 상황에서 스키마에 대해서 알아내는 경우. 데이터와는 무관하고 그 껍데기에 관한 것.
resultsetmetadata는 인터페이스기 때문에 new를 못 쓰고 resultset의 getmetadata()를 통해서만 얻을 수 있다.
getmetadata()는 resultset의 결과를 통해서 resultsetmetadata를 뽑는다.
resultsetmetadata의 getColumcount(), getColumnType 등을 통해서 컬럼갯수, 컬럼의 타입 등을 알 수 있다.
