package LoginPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(
	    name = "DisplayServlet",
	    urlPatterns = {"/display"}
	)
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
					+ "</head>"
					+ "<style>  \r\n"
					+ "table {  \r\n"
					+ "   border: 2px solid blueviolet;  \r\n"
					+ "   padding: 10px;  \r\n"
					+ "}  \r\n"
					+"td {  \r\n"
					+ "   border: 2px solid maroon;  \r\n"
					+ "   padding: 10px;  \r\n"
					+ "} \r\n"
					+ "td,th{\r\n"
					+ "padding-bottom: 10px;\r\n"
					+ "}\r\n"
					+ "</style><body>");
			out.println("<h3 align = \"center\" style=\"margin-bottom: 0\";>Display details</h3><br>"
					+ "<div align =\"center\"><table><tr>\r\n"
					+ "<th>Name</th> <td>"+name+"</td></tr><tr>\r\n"
					+ "<th>Email id</th> <td>"+emailId+"</td></tr><tr>\r\n"
					+ "<th>Employee id</th> <td>"+empId+"</td></tr><tr>\r\n"
					+ "<th>Phone No</th> <td>"+phoneNo+"</td></tr>"
					+ "<th>Booking</th> <td>"+booking+"</td></tr>"
					+ "</table></div>");
			
			if(rememberMe.equals("notRemember")) {
				session.invalidate();
				out.println("<div align = \"center\" style=\"margin-top: 10px\"><a href = \"./index.html\" ><input type=\"submit\" name=\"home\"></a><div>");
			}
			out.println("</body></html>");
			out.close();
		
	}
}
