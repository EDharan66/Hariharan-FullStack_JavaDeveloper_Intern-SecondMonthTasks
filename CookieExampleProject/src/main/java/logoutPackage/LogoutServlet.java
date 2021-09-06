package logoutPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
	     PrintWriter out=response.getWriter();  
	       
	       
	     request.getRequestDispatcher("activity.html").include(request, response);  
	       
	     Cookie cookie=new Cookie("name","");  
	     cookie.setMaxAge(0);  
	     response.addCookie(cookie);  
	       
	     out.print("you are successfully logged out!");  
		 out.close();
	}
	 

}
