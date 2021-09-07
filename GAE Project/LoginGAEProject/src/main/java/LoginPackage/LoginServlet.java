package LoginPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\r\n" + "<html><body>");

		String name = null , sessionPassword = null;

		String password = request.getParameter("password");

		HttpSession session = request.getSession(false);
	     System.out.println(session);
		if (!session.equals(null)) {
			name = (String) session.getAttribute("name");
			sessionPassword = (String) session.getAttribute("password");
		} else {
			response.sendRedirect("index.html");
		}

		try {
			if ((name != "" || !name.equals(null)) && password.equals(sessionPassword)) {

				out.println("<h3>Hai " + name + "</h3><br>");
				out.println("<a>display details!<a>"
						+ "<a href=\"display\"><input type = \"submit\" value = \"Display Detail\"></a><br><br>");
				out.println("<a>logout!<a>" + "<a href=\"logout\"><input type = \"submit\" value = \"Logout\"></a>");

			} else {

				out.println("your password/name is incorrect, Please enter correctly or signup new!!");
				request.getRequestDispatcher("index.html").include(request, response);

			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			out.println("</body></html>");
			out.close();
		}
	}
}
