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

	String sessionName, sessionPassword;
	String name;
	String password;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\r\n" + "<html>" + "<style>  \r\n" + "table {  \r\n"
				+ "   border: 2px solid darksalmon;  \r\n" + "   padding: 10px;  \r\n" + "}  \r\n" + "td,th{\r\n"
				+ "padding-bottom: 10px;\r\n" + "}\r\n" + "</style><body>");

		name = request.getParameter("username");
		password = request.getParameter("password");

		HttpSession session = request.getSession(false);
		System.out.println(session);

		if (session != null) {
			sessionName = (String) session.getAttribute("name");
			sessionPassword = (String) session.getAttribute("password");
		} else {
			out.println("<p align = \"center\">No session is created, please signup!!<p>");
			request.getRequestDispatcher("index.html").include(request, response);
//			response.sendRedirect("index.html");
		}
		System.out.println(
				name + ", " + password + ", " + sessionName + ", " + sessionPassword + ", validate" + validate());
		try {
			if (!validate()) {
				response.sendRedirect("index.html");
			} else if (validate() && name.equals(sessionName) && password.equals(sessionPassword)) {

				out.println("<h3 align = \"center\" style=\"margin-bottom: 0\";>Hai " + sessionName + "</h3><br>");
				out.println("<div align = \"center\"><table>\r\n" + "				<tr>\r\n"
						+ "					<td><b><a>Display details<a></b></td>\r\n"
						+ "					<td><a href=\"display\"><input type = \"submit\" value = \"Display Detail\"></a></td>\r\n"
						+ "				\r\n" + "				</tr>\r\n" + "				\r\n"
						+ "				<tr>\r\n" + "					<td><b><a>Logout<a></b></td>\r\n"
						+ "					<td><a href=\"logout\"><input type = \"submit\" value = \"Logout\"></a></td>\r\n"
						+ "				</tr>\r\n" + "			</table></div>");

			} else if (session != null) {
				out.println(
						"<p align=\"center\">your password/name is incorrect, Please enter correctly or signup new!!<p>");
				request.getRequestDispatcher("index.html").include(request, response);

			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			out.println("</body></html>");
			out.close();
		}
	}

	boolean validate() {

		if (!name.isEmpty() && !password.isEmpty()) {
			return true;
		} else {
			return false;
		}

	}
}
