package LoginPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\r\n" + "<html><body>");
		String rememberMe = null;
		

		String emailId = request.getParameter("Email");
		String name = request.getParameter("Name");
		String empId = request.getParameter("EmpId");
		String phoneNo = request.getParameter("PhoneNo");
		

		if (request.getParameter("RememberMe") != null) {
			rememberMe = (String) request.getParameter("RememberMe");
		}else{
			rememberMe = "notRemember";
			out.println("your choose not remember");
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("name", name);
		session.setAttribute("emailId", emailId);
		session.setAttribute("empId", empId);
		session.setAttribute("phoneNo", phoneNo);
		session.setAttribute("rememberMe", rememberMe);
		
		
		try {
			if (name != "" || name.equals(null)) {

				out.println("<h3>Hai " + name + "</h3><br>");
				out.println("<a>booking out slot!<a>"
						+ "<a href=\"booking\"><input type = \"submit\" value = \"Booking\"></a><br><br>");
				out.println("<a>display details!<a>"
						+ "<a href=\"display\"><input type = \"submit\" value = \"Display Detail\"></a><br><br>");
				out.println("<a>logout!<a>" + "<a href=\"logout\"><input type = \"submit\" value = \"Logout\"></a>");

			} else {

				request.getRequestDispatcher("./index.html").forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			out.println("</body></html>");
			out.close();
		}

	}
}
