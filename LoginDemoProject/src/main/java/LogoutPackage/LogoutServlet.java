package LogoutPackage;

import java.awt.desktop.SystemSleepEvent;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet{
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		session.invalidate();
		out.println("sucessfully logout!!");
		try {
			Thread.sleep(2000);
			request.getRequestDispatcher("./index.html").forward(request, response);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

	

	
	
}
