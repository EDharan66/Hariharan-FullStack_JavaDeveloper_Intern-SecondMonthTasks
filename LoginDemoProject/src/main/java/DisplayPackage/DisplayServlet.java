package DisplayPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisplayServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailId = request.getParameter("Email");
		String name = request.getParameter("Name");
		String empId = request.getParameter("EmpId");
		String phoneNo = request.getParameter("PhoneNo");
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"ISO-8859-1\">\r\n"
				+ "<title align=\"center\"><h1>this display page!!</h1></title>\r\n"
				+ "</head><body>");
		out.println("<table><tr>\r\n"
				+ "<th>Name</th> <td>"+name+"</td></tr><tr>\r\n"
				+ "<th>Email id</th> <td>"+emailId+"</td></tr><tr>\r\n"
				+ "<th>Employee id</th> <td>"+empId+"</td></tr><tr>\r\n"
				+ "<th>Phone No</th> <td>"+phoneNo+"</td></tr>"
				+ "</table>");
		out.println("</body></html>");
	}
}
