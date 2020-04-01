Properties써서 쉽게 JDBC하는 방법(DBConnection, DBClose 파일 분리까지)

(애초에 프로젝트 만들 때 oracle, mariadb driver(.jar파일) 넣고 시작)
oracle.properties 파일을 만들어 놓으면 계속 불러와서 활용할 수 있음. 고정적인 driver를 제외하고는 url, id, passwd 다 변할 경우에는 이 파일만 바꾸면 됨.

https://youtu.be/NvcWPkowzFU 34분부터
https://youtu.be/NVGpzxhw81Y 28분까지



D:\app\June\virtual\product\12.2.0\dbhome_2\network\admin 에 들어가보면 listener.ora파일이 있는데 안에 ip(localhos랑 ip) , port번호가 다 나와있다.
만약 ip 번호가 바뀐다면 여기서 수정을 해줘야지 오라클이 에러가 나지 않는다. tnsnames.ora안에는 service_name=orcl 이 나와있다. 만약 이것도 수정한다면 오라클을 중지 했다가 다시 실행해야 된다.
즉 둘 다 수정될 때는 여기서 수정하고 오라클을 중지 후 재가동해야 된다. 그런데 Express Edition은 이런 과정이 필요없고, enterprise나 standard 버전에서만 이런 것 수정해야 된다.


