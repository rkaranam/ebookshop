import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.Properties;

public class QueryServlet extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// set the MIME type for the response message
		response.setContentType("text/html");

		// Get the output writer to write the response message into the network socket
		PrintWriter out = response.getWriter();

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties prop = new Properties();

		Connection connection = null;
		Statement statement = null;

		try {
			
			prop.load(classLoader.getResourceAsStream("database.properties"));
			
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/ebookshop", 
				prop.getProperty("username"), 
				prop.getProperty("password"));

			System.out.println("MySQL Connection Successful!");

		} catch(SQLException ex) {
			System.out.println("********** SQL Exception Occured ***********");
			ex.printStackTrace();
		} catch(ClassNotFoundException ex) {
			System.out.println("********** Class Not Found Exception Occured ***********");
			ex.printStackTrace();
		} catch(IOException ex) {
			System.out.println("********** IO Exception Occured ***********");
			ex.printStackTrace();
		} catch(Exception ex) {
			System.out.println("********** Exception Occured ***********");
			ex.printStackTrace();
		} finally {
			out.close();
			try {
				if(statement != null) statement.close();
				if(connection != null) connection.close();
			} catch(Exception ex) {
				ex.printStackTrace(); 
			}
		}

	}
}