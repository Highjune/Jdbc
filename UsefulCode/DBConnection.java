import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection { //Connection�� �������ֱ� ������ �̷��� �����θ� Ȱ�뵵 ����.
	public static Connection getConnection(String filename) { //������ �̸��� ����(oracle.properties / maria.properties � ���� �� �̰����θ� �� Ȱ���� �� �ִ�
		Connection conn = null;
		try {
			Properties info = new Properties();
			info.load(new FileInputStream(new File(filename))); //properties���� �̷��� ������ �ҷ����� load�� �ִ�.
			Class.forName(info.getProperty("db.driver"));
		    conn = DriverManager.getConnection(
					info.getProperty("db.url"), info.getProperty("db.user"),
					info.getProperty("db.password"));
		}catch(IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
