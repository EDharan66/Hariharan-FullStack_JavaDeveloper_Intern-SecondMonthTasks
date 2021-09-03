package LoginPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		String name = request.getParameter("Name");
		
		
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\r\n"
				+ "<html><body>");
		
		out.println("<h3>Hai "+name+"</h3><br>");
		out.println("<form action=\"booking\" method=\"post\">"
				+ "<a>booking out slot!<a><br>"
				+ "<input type = \"submit\" value = \"Booking\">"
				+ "</form>");
		out.println("<form action=\"display\" method=\"post\">"
				+ "<a>display details!<a><br>"
				+ "<input type = \"submit\" value = \"Display Detail\">"
				+ "</form>");
		out.println("</body></html>");
		
//		request.getRequestDispatcher("booking").forward(request, response);
		
	}
}
