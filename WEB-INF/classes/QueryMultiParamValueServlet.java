import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import javax.servlet.annotation.*;

@WebServlet(value = "/querymvp")
public class QueryMultiParamValueServlet extends HttpServlet{

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
			
			connection = DbUtils.getConnection();

			statement = connection.createStatement();

			String[] authors = request.getParameterValues("author");

			if(null == authors){
				out.println("<h2>Go back and choose author.</h2>");
				return;
			}

			String queryString = "select * from books where author IN('" + authors[0];

			for(int i = 1; i < authors.length; i++){
				queryString += "', '" + authors[i];
			}

			queryString += "') and price < " + request.getParameter("price");

			System.out.println(queryString);

			out.println("<html><head><title>Query Results</title></head><body>");
			out.println("<h2>Thank you for your query.</h2>");
			out.println("<p>Your query is <b>" + queryString + "</b></p>");

			ResultSet rs = statement.executeQuery(queryString);

			int count = 0;
			while(rs.next()){
				out.println("<p>Title: " + rs.getString("title") +
							", Author: " + rs.getString("author") +
							", Price: $" + rs.getDouble("price") + "</p>");
				count++;
			}

			out.println("<p>" + count + " records found.</p>");
			out.println("</body></html>");

		} catch(Exception ex) {
			System.out.println("********** Exception Occured ************");
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