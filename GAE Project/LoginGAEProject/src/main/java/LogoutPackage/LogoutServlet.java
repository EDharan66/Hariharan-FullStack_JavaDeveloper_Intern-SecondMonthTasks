package LogoutPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(
	    name = "LogoutServlet",
	    urlPatterns = {"/logout"}
	)
public class LogoutServlet extends HttpServlet{
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		session.invalidate();
		out.println("sucessfully logout!!");
		try {
			request.getRequestDispatcher("index.html").include(request, response);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}
}
