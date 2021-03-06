package DisplayPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DisplayServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
	
	
			String emailId = (String) session.getAttribute("emailId");
			String name = (String) session.getAttribute("name");
			String empId = (String) session.getAttribute("empId");
			String phoneNo = (String) session.getAttribute("phoneNo");
			String booking = (String) session.getAttribute("booking");
			String rememberMe = (String) session.getAttribute("rememberMe");
			if(booking == null) {
				booking = "no booking Yet";
			}
			
			response.setContentType("text/html");
			
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<head>\r\n"
					+ "<meta charset=\"ISO-8859-1\">\r\n"
					+ "<title align=\"center\">this display page!!</title>\r\n"
					+ "</head><body>");
			out.println("<table><tr>\r\n"
					+ "<th>Name</th> <td>"+name+"</td></tr><tr>\r\n"
					+ "<th>Email id</th> <td>"+emailId+"</td></tr><tr>\r\n"
					+ "<th>Employee id</th> <td>"+empId+"</td></tr><tr>\r\n"
					+ "<th>Phone No</th> <td>"+phoneNo+"</td></tr>"
					+ "<th>Booking</th> <td>"+booking+"</td></tr>"
					+ "</table>");
			
			if(rememberMe.equals("notRemember")) {
				session.invalidate();
				out.println("<a href = \"./index.html\"><input type=\"submit\" name=\"home\"></a>");
			}
			out.println("</body></html>");
			out.close();
		
		
		
		
		
	}
}
