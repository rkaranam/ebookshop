import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class QueryServlet extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// set the MIME type for the response message
		response.setContentType("text/html");

		// Get the output writer to write the response message into the network socket
		PrintWriter out = response.getWriter();

		Connection connection = null;
		Statement statement = null;

		try {

			// loads the mysql Driver class into memory
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/ebookshop", "root", "mysql");

			System.out.println("MySQL Connection Successful!");

		} catch(SQLException ex) {
			ex.printStackTrace();
		} catch(ClassNotFoundException ex) {
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