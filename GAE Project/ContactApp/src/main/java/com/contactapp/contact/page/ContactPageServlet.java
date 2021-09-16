package com.contactapp.contact.page;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cantactapp.service.OfyService;
import com.contactapp.home.controller.SignupEntity;

@WebServlet(name = "ContactPageServlet", urlPatterns = { "/contactpage" })
public class ContactPageServlet extends HttpServlet {

	String empId = null;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);

		if (session != null) {
			empId = (String) session.getAttribute("empId");

		}

		request.getRequestDispatcher("header.html").include(request, response);

//		out.println("home page");

		SignupEntity entity = (SignupEntity) OfyService.ofy().load().type(SignupEntity.class).id(Long.parseLong(empId))
				.now();
		System.out.println(entity);

		out.println("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<title>Contact page</title>\r\n"
				+ "<link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">"
				+ "</head>");
		out.println("<style>\r\n" 
				+ "div.card {\r\n" 
				+ "	padding-left:20px;\r\n"
				+ "    align-items: baseline;" + "}\r\n"
						+ "spam.icon{"
						+ " float: right;"
						+ "padding-right: 20px;\r\n"
						+ "padding-top: 20px;}" 
				+ "i.material-icons{"
				+ "font-size: 16px;"
				+ "padding-right: 10px;}"
				+ "div.icon{"
				+ "display: flex;\r\n"
				+ "    float: right;\r\n"
				+ "    padding: 20px;}"
				+ "</style>");

		out.println("<div class = \"card\" >" + "<font class = heading size = \"12\"><b>" + entity.getName()
				+ "</b></font>" + "<font size = \"6.5\">, contacts</font>"
					
						+ "<div class = \"icon\">"
						+ "<form action=\"searchpage\" method=\"post\">"
						+" <input type=\"hidden\" name=\"message\" value=\"search\">"
						+ "<button style=\"background: none; cursor: pointer; border: none\"> Search </button><i class=\"material-icons\">search </i>"
						+ "</form> |"
						
						+ "<form action=\"updatepage\" method=\"post\">"
						+"<input type=\"hidden\" name=\"message\" value=\"edit\">"
						+ "<button style=\"background: none; cursor: pointer; border: none\"> Edit </button><i class=\"material-icons\">mode_edit </i>"
						
						+ "</form> |"
						+ "<form action=\"createpage\" method=\"post\">"
						+"<input type=\"hidden\" name=\"message\" value=\"create\">"
						+ "<button style=\"background: none; cursor: pointer; border: none\"> Add </button><i class=\"material-icons\">add_circle </i>"
						
						+ "</form> |"
						+ "<form action=\"deletepage\" method=\"post\">"
						+"<input type=\"hidden\" name=\"message\" value=\"delete\">"
						+ "<button style=\"background: none; cursor: pointer; border: none\"> Delete </button><i class=\"material-icons\">delete </i> "
					
						+ "</form></div>"
						+ "</div>");

		out.println("");
		out.println("</body>\r\n" + "</html>");
	}
}
