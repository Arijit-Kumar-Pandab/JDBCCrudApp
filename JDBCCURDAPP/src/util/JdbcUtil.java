package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	private JdbcUtil() {

	}

	public static Connection connection = null;

	public static Connection getConnection() throws FileNotFoundException, SQLException, IOException {
		if (connection == null) {
			FileInputStream fis = new FileInputStream(
					"D:\\servletpgms\\JDBCCURDAPP\\src\\properties\\application.properties");
			Properties p = new Properties();
			p.load(fis);
			String url = p.getProperty("url");
			String user = p.getProperty("user");
			String password = p.getProperty("password");
			connection = DriverManager.getConnection(url, user, password);
		}
		return connection;
	}
}
