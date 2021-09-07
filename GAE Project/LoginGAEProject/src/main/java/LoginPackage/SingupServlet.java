package LoginPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.runner.Request;

@WebServlet(name = "SingupServlet", urlPatterns = { "/signup" })
public class SingupServlet extends HttpServlet {
	String emailId, name, empId, phoneNo, password, conformPassword;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\r\n" + "<html><body>");
		String rememberMe = null;

		emailId = request.getParameter("Email");
		name = request.getParameter("Name");
		empId = request.getParameter("EmpId");
		phoneNo = request.getParameter("PhoneNo");
		password = request.getParameter("password");
		conformPassword = request.getParameter("conformPassword");

		if (request.getParameter("RememberMe") != null) {
			rememberMe = (String) request.getParameter("RememberMe");
		} else {
			rememberMe = "notRemember";
			out.println("your choose not remember");
		}

//		System.out.println("validate = " + validate());
		try {
			if (validate() && password.equals(conformPassword)) {

				HttpSession session = request.getSession();
				session.setAttribute("name", name);
				session.setAttribute("emailId", emailId);
				session.setAttribute("empId", empId);
				session.setAttribute("phoneNo", phoneNo);
				session.setAttribute("rememberMe", rememberMe);
				session.setAttribute("password", password);
				session.setAttribute("conformPassword", conformPassword);
				out.println("<p align=\"center\">Signup successfully!!</p>");
				request.getRequestDispatcher("index.html").include(request, response);

			} else if (!password.equals(conformPassword)) {
				out.println("<p align=\"center\">your password and confrom password not match please retry!!</p>");
				request.getRequestDispatcher("SignUpPage.html").include(request, response);

//				response.sendRedirect("index.html");
			} else {
				out.println("<p align=\"center\">Please enter all details!!</p>");
				request.getRequestDispatcher("SignUpPage.html").include(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			out.println("</body></html>");
			out.close();
		}

	}

	boolean validate() {

		if (!name.isEmpty() && !emailId.isEmpty() && !empId.isEmpty() && !phoneNo.isEmpty() && !password.isEmpty()
				&& !conformPassword.isEmpty()) {
			return true;
		} else {
			return false;
		}

	}
}
