import java.sql.Connection;

//lombok.jar

//String�� �ϳ��� �ּҸ� ���� ����.
//StudentVOŬ������ ���� ����~

public class Test {
	public static void main(String[] args) {
//		StudentVO chulsu = new StudentVO();
//		StudentVO younghee = new StudentVO();
//		if(chulsu == younghee) System.out.println("Equals");
//		else System.out.println("Different");
//		System.out.println(chulsu.hashCode()); //�̷��� hashcode�� �޸� �ּ� ���� �� �ִ�.
//		System.out.println(younghee.hashCode()); //chulsu�� �޸� �ּҿ� �ٸ� ���� �� �� �ִ�.
	
		String str = "Hello";
		String str1 = "Hello";
//		String str2 = "Hello1"; ==> ���� �̷��� �ϸ� �ٸ���. str = str1 �� ������ str2�� �ٸ�
		if(str == str1) System.out.println("Equals");
		else System.out.println("Different");
		System.out.println(str.hashCode());
		System.out.println(str1.hashCode()); //String�� �ּҰ� ����.

	//Singleton Design Pattern
//�޸𸮿� ��������� ��ü�� �Ѱ��� ��������� �ȴ�. ����, ��Ȱ�� ����. ���ο� ��ü�� ������ �ʴ´�. �޸𸮸� ȿ�������� ���� ������
//�ӵ��� ������. 
//�ѹ� connection ����� ��� �Ȱ��� Ȱ���ϱ� ������. data�� ���õ� �� �� �� ���� �̿�
//������ �������� ��� ������ �ȴ�.
		
	}
}	





