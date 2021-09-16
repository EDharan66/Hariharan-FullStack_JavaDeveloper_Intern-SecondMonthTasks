package com.contactapp.home.page;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.contactapp.home.controller.SignupEntity;
import com.contactapp.service.OfyService;


@WebServlet(name = "HomePageServlet", urlPatterns = { "/homepage" })
public class HomePageServlet extends HttpServlet {

	String empId = null;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		
		if(session!=null) {
			empId = (String) session.getAttribute("empId");
			
		}
		
		request.getRequestDispatcher("header.html").include(request, response);
		
//		out.println("home page");
		
		

		SignupEntity entity = (SignupEntity) OfyService.ofy().load().type(SignupEntity.class).id(Long.parseLong(empId))
				.now();
		System.out.println(entity);
		
		
		out.println("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<title>Home page</title>\r\n" + "</head>"
				+ "<style>" + 
				"div.card {\r\n" + "  width: 50%;\r\n"
				+ "  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2),"
				+ " 0 6px 20px 0 rgba(0, 0, 0, 0.19);\r\n"
				+ "  text-align: center;\r\n"
				+ "	margin: auto;\r\n" 
				+ " margin-top: 20px;\r\n" 
				+ " padding: 20px;"
				+ "}"
				+ "a.hint {\r\n"
				+ "	margin: 0;\r\n"
				+ "}"
				+ "form.home-page{"
				+ "padding-left: 5px;\r\n"
				+ "padding-top: 10px;}" 
				+ "</style>");

		out.println("<div class = \"card\" >" 
		+ "<h2><b>Hi " + entity.getName() + " welcome to contact app</b></h2>"
				+ "<a class=\"hint\"> "+ entity.getEmailId() + "</a><br>");

		
		out.println("<div style=\"display: flex;\r\n"
				+ "    padding-top: 10px;\r\n"
				+ "    justify-content: center;\">\r\n"
				+ "		<form class = \"home-page\" action=\"homepage\" method=\"post\">\r\n"
				+ "			<button>Home\r\n"
				+ "			</button>\r\n"
				+ "		</form>\r\n"
				+ "		\r\n"
				+ "		<form class = \"home-page\" action=\"contactpage\" method=\"post\">\r\n"
				+ "			<button >Contact\r\n"
				+ "				</button>\r\n"
				+ "		</form>\r\n"
				+ "		\r\n"
				+ "		<form class = \"home-page\" action=\"profilepage\" method=\"post\">\r\n"
				+ "			<button >Profile\r\n"
				+ "				</button>\r\n"
				+ "				<input type=\"hidden\" name=\"message\" value=\"updated\">\r\n"
				+ "		</form>"
				+ "	</div></div>");
		out.println("</body>\r\n" + "</html>");
	}
}
