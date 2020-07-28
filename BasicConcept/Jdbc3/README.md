oracle이 연결되어 있어야 한다.

[강의1](https://youtu.be/pFt-Xl9F3vw) - 30분부터   
[강의2](https://youtu.be/bhEe4fAGTKg)  
  
## INSERT / UPDATE / DELETE (이 3가지는 select와 달리 6단계만 하면 된다)
1. import
2. Database Driver Loading
3. Database Connectio
4. Statement Object 생성 
5. Query 실행
executeUpdate(sql);   //select제외 나머지 모든 SQL. cf) select인 경우에는 executeQuery(sql); 임 
6. Close 실행
