## lombok.jar
- tostring get set 등을 자동으로 만들어주는 VO에서 자주 쓰이는 라이브러리
lombok.jar를 이클립스에 설치하는 것임 
cmd를 관리자로 로그인해서 설치

[강의1](https://youtu.be/W1EqDfm6d6I) - 15분까지 lombok, 15분부터는 singleton pattern  
[강의2](https://youtu.be/lBiGSvF1WuI)   

(설치과정 cmd창에서)

```
java -jar lombok.jar
```

lombok은 멤버변수만 선언해서 @Data(제일 많이 씀)만 해주면 기본생성자, get, set, tostring다 만들어줌.

## singletonpattern - 동시사용자가 생기는 경우 고민해야 된다. 동시에 2명이상이 들어오는 경우 connection은 user의 갯수만큼 만들어지니까.
connection을 만들고 소멸할 때 시간을 최소화 하는 것이 관건인데 그 중에서 가장 쉬운 방법이 singletonpattern이다.

### Singleton Design Pattern

메모리에 만들어지는 객체는 한개만 만들어져야 된다.(최초) 공동, 재활용 개념. 새로운 객체를 만들지 않는다. 메모리를 효율적으로 쓰기 때문에 (마치 string처럼)
모든 객체가 전역처럼 사용되어서 공용
속도가 빠르다. 
한번 connection 만들면 계속 똑같이 활용하기 때문에. data와 관련된 것 할 떄 많이 이용
단점은 쓰레드의 경우 문제가 된다. 두 개의 cpu가 각각 1개씩 만들게 되면 문제가 발생할 수 있다. 그래서 thread처리가 된 singletonpattern을 사용해야 된다.
