package cookiePackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String MYPASSWORD = "EDharan";
		String name = request.getParameter("name");
		String Password = request.getParameter("password");
		
		 	response.setContentType("text/html");  
	        PrintWriter out=response.getWriter();  

		
		if(Password.equals(MYPASSWORD)) {
			
		     request.getRequestDispatcher("activity.html").include(request, response);  
			out.println("<h1>Hi "+name+", welcome to cookie example page!<h1>");
			
		Cookie cookie = new Cookie("name",name);	
		response.addCookie(cookie);
		
		}else {
			out.println("your password is wrong, please enter correct!!<br><br>");
			request.getRequestDispatcher("index.html").include(request, response);
		}
		
	}
}
