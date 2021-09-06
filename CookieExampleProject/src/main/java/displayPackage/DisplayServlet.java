package displayPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisplayServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		request.getRequestDispatcher("activity.html").include(request, response);

		Cookie cookie[] = request.getCookies();

		
		if (cookie != null) {
			
			String keyName = cookie[0].getName();
			String name = cookie[0].getValue();
			System.out.println(keyName+", "+name);

			if (!name.equals("") || name != null && keyName.equals("name")) {
				out.print("<b>Display the details</b>");
				out.print("<br>Welcome, " + name + "!!");
			}
		} else {
			out.print("Please login first");
			request.getRequestDispatcher("index.html").include(request, response);
		}
		out.close();

	}
}
