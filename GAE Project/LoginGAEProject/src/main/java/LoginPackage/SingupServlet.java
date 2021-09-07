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

@WebServlet(
	    name = "SingupServlet",
	    urlPatterns = {"/signup"}
	)
public class SingupServlet  extends HttpServlet {

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
		String password = request.getParameter("password");
		String conformPassword = request.getParameter("conformPassword");
		

		if (request.getParameter("RememberMe") != null) {
			rememberMe = (String) request.getParameter("RememberMe");
		}else{
			rememberMe = "notRemember";
			out.println("your choose not remember");
		}
		
		if(password.equals(conformPassword)) {
			HttpSession session = request.getSession(true);
			session.setAttribute("name", name);
			session.setAttribute("emailId", emailId);
			session.setAttribute("empId", empId);
			session.setAttribute("phoneNo", phoneNo);
			session.setAttribute("rememberMe", rememberMe);
			session.setAttribute("password", password);
			session.setAttribute("conformPassword", conformPassword);
			
		}
		
		
		try {
			if ((name != "" || !name.equals(null)) && password.equals(conformPassword)) {

				
				request.getRequestDispatcher("login").include(request, response);
			

			} else {

//				request.getRequestDispatcher("index.html").include(request, response);
				
				response.sendRedirect("index.html");
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			out.println("</body></html>");
			out.close();
		}

	}
}
