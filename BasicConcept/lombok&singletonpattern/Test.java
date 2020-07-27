import java.sql.Connection;

//lombok.jar

//String은 하나의 주소를 같이 쓴다.
//StudentVO클래스랑 같이 공부~

public class Test {
	public static void main(String[] args) {
//		StudentVO chulsu = new StudentVO();
//		StudentVO younghee = new StudentVO();
//		if(chulsu == younghee) System.out.println("Equals");
//		else System.out.println("Different");
//		System.out.println(chulsu.hashCode()); //이렇게 hashcode로 메모리 주소 찍을 수 있다.
//		System.out.println(younghee.hashCode()); //chulsu의 메모리 주소와 다른 것을 알 수 있다.
	
		String str = "Hello";
		String str1 = "Hello";
//		String str2 = "Hello1"; ==> 물론 이렇게 하면 다르다. str = str1 은 같지만 str2는 다름
		if(str == str1) System.out.println("Equals");
		else System.out.println("Different");
		System.out.println(str.hashCode());
		System.out.println(str1.hashCode()); //String은 주소가 같음.

	//Singleton Design Pattern
//메모리에 만들어지는 객체는 한개만 만들어져야 된다. 공동, 재활용 개념. 새로운 객체를 만들지 않는다. 메모리를 효율적으로 쓰기 때문에
//속도가 빠르다. 
//한번 connection 만들면 계속 똑같이 활용하기 때문에. data와 관련된 것 할 떄 많이 이용
//단점은 쓰레드의 경우 문제가 된다.
		
	}
}	





