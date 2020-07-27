import lombok.Data;

//lombok.jar
//Test클래스와 같이.


/*
@Getter
@Setter
@ToString 
@Data - 위의 get, set, tostring을 한번에 해주는 것 //제일 많이 씀
@AllArgsConstructor - 모든 변수에 대한 생성자까지.
@NoArgsConstructor - 기본생성자~

@Data
public class StudentVO {
	private String hakbun, name;
	private int kor, eng, mat, tot;
	private char grade;
	private double avg;
}
*/


//아래와 같은 방법으로 클래스를 만들어두면 무조건 학생은 1명만(같은 주소) 만들어진다.그래서 Test클래스에서는 무조건 하나의 student만 만들어지고 그 둘의 주소는 똑같다.
public class StudentVO {
	private StudentVO() {}  //new를 못 쓰게 하기 위해서 기본생성자를 private으로. new를 못 쓰게 하는 방법
	
	//factory pattern
	private static class StudentVOFactory{ //내부 클래스 cf) 클래스가 private, static되려면 내부클래스여야 된다. 
		public static final StudentVO INSTANCE = new StudentVO();
	}
	
	public static StudentVO getInstance() {  //마치 gregorian calendar처럼 new못쓰게 하고 getInstance로 객체 만들 수 있도록 세팅
		return StudentVOFactory.INSTANCE;
	}
}

