import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import javax.servlet.annotation.*;

@WebServlet(value = "/eshoporder")
public class EshopOrderServlet extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// set the MIME type for the response message
		response.setContentType("text/html");

		// Get the output writer to write the response message into the network socket
		PrintWriter out = response.getWriter();

		out.println("<html><head><title>Order Details</title></head><body>");
		out.println("<h2>Your order received successfully!</h2>");
		out.println("<p><b>Order details: </b></p>");
		out.println("<p>Book Id: " + request.getParameter("id") + "</p>");
		out.println("</body></html>");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// just redirecting to doGet() method
		doGet(request, response);
	}
}