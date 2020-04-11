https://youtu.be/OIg3kWs_QHE
https://youtu.be/5Gsge5okse0  5분대에 emp_copy 테이블 간단히 만들어서 작업.

당연히 build path 로 오라클 jdbc 드라이버 연결해야됨 (ojdbc8.jar)

ScrollableStatement - next만 하는 것이 아니라 위, 아래 어디 방향으로든 갈 수 있다. 즉 커서를 내가 원하는 위치에 놔둘 수 있다는 것
사실 객체이름이 ScrollableStatement인 것은 없다. 그냥 scroll 이 가능한 statement를 만드는 것임

이전의 방식들은 요청하는 쿼리를 보낼때마다 독립적으로 connection(빨대)를 만들어서 소통하는 방식이었다.
ex) 한국 -> 미국으로 자동차가 필요하다고 날리면 미국에서는 공장세우고 다 만들어서 결과를 resultset에 담아서 보내주고 그 CONNECTION은 없앰
그리고 새로운 요청이 있으면 이 PROCESS반복(즉 네트워크를 이용해서 데이터를 보내고 받는 것이 오래걸리는 것이 아니라 connection이라는 빨대를 만들어서 연결하는 것 자체가 오래걸림) 
==> 병목현상이 생긴다.
그러나 scrollable은 처음에 연결했을 때 해당하는 테이블 자체를 다 들고오는 방식. 다 직접(복사본) 들고와서 여기서 필요한 것들(커서를 이용해)을 select해서 들고오는 방식이다.
그러다가 원본에 수정할 부분이 생긴다면 복사본도 업데이트 해야 되기 때문에 그 순간에만 자동씽크 (왜냐하면 우리가 보내는 query의 95%는 select이기 때문)
ResultSet.TYPE_SCROLL_SENSITIVE의 ResultSet.CONSUR_UPDATE는 원본과 동기화씽크(select, update, delete 등 다 하는 경우), 즉 들고온 복사본에 update, delete등을 했을 경우 원본에도 적용한다는 말
ResultSet.TYPE_SCROLL_INSENSITIVE의 ResultSet.CONCUR_READ_ONLY는 보통 select만 하는 경우.

cf) 나중에는 커넥션 풀이라는 것을 배우게 된다.