import lombok.Data;

//lombok.jar
//TestŬ������ ����.


/*
@Getter
@Setter
@ToString 
@Data - ���� get, set, tostring�� �ѹ��� ���ִ� �� //���� ���� ��
@AllArgsConstructor - ��� ������ ���� �����ڱ���.
@NoArgsConstructor - �⺻������~

@Data
public class StudentVO {
	private String hakbun, name;
	private int kor, eng, mat, tot;
	private char grade;
	private double avg;
}
*/


//�Ʒ��� ���� ������� Ŭ������ �����θ� ������ �л��� 1��(���� �ּ�) ���������.�׷��� TestŬ���������� ������ �ϳ��� student�� ��������� �� ���� �ּҴ� �Ȱ���.
public class StudentVO {
	private StudentVO() {}  //new�� �� ���� �ϱ� ���ؼ� �⺻�����ڸ� private����. new�� �� ���� �ϴ� ���
	
	//factory pattern
	private static class StudentVOFactory{ //���� Ŭ���� cf) Ŭ������ private, static�Ƿ��� ����Ŭ�������� �ȴ�. 
		public static final StudentVO INSTANCE = new StudentVO();
	}
	
	public static StudentVO getInstance() {  //��ġ gregorian calendaró�� new������ �ϰ� getInstance�� ��ü ���� �� �ֵ��� ����
		return StudentVOFactory.INSTANCE;
	}
}

