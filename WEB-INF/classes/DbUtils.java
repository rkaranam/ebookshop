import java.sql.*;
import java.util.Properties;

public class DbUtils {
	
	public static Connection getConnection() {

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties prop = new Properties();

		Connection connection = null;

		try {

			prop.load(classLoader.getResourceAsStream("database.properties"));
				
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/ebookshop", 
				prop.getProperty("username"), 
				prop.getProperty("password"));

			System.out.println("MySQL Connection Successful!");

		} catch(Exception ex){
			System.out.println("************* Oops, Exception Occured! ***************");
			ex.printStackTrace();
		}

		return connection;
	}
}