package com.contactapp.profile.page;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cantactapp.service.ContactEntity;
import com.cantactapp.service.OfyService;
import com.contactapp.home.controller.SignupEntity;

@WebServlet(name = "ProfilePageServlet", urlPatterns = { "/profilepage" })
public class ProfilePageServlet extends HttpServlet {

	String empId = null,message;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		message = request.getParameter("message");
		
		
		HttpSession session = request.getSession(false);
		
		if(session!=null) {
			empId = (String) session.getAttribute("empId");
			
		}
		
		request.getRequestDispatcher("header.html").include(request, response);
		
//		out.println("home page");
		
		

		SignupEntity entity = (SignupEntity) OfyService.ofy().load().type(SignupEntity.class).id(Long.parseLong(empId))
				.now();
		System.out.println(entity);
		
		
		out.println("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "<title>Home page</title>\r\n" 
				+ "<link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">"+ "</head>"
				+ "<style>" + 
				"div.card {\r\n" + "  width: 50%;\r\n"
				+ "  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2),"
				+ " 0 6px 20px 0 rgba(0, 0, 0, 0.19);\r\n"
				+ "	margin: auto;\r\n" 
				+ " margin-top: 20px;\r\n" 
				+ " padding: 40px;"
				+ "}"
				+ "a.hint {\r\n"
				+ "	margin: 0;\r\n"
				+ "}"
				+ "i.material-icons {"
				+ "font-size: 17px;\r\n"
				+ "    padding-right: 6px;}" 
				+ "div.icon{"
				+ "display: flex;\r\n"
				+ "    float: right;\r\n"
				+ "    padding-top:15px;}"
				+ "table.detail{"
				+ "    padding-top: 20px;}"
				+ "</style>");

		out.println("<div class = \"card\" >" 
				+ "<font size=\"6\"><b>Welcome to Profile page</b></font>"
				+ "<div class = \"icon\">"
				
				+ "<form action=\"profilepage\" method=\"post\">"
				+"<input type=\"hidden\" name=\"message\" value=\"edit\">"
				+ "<button style=\"background: none; cursor: pointer; border: none\"> Edit </button><i class=\"material-icons\">mode_edit </i>"
				
			
				+ "</form> |"
				+ "<form action=\"profilepage\" method=\"post\">"
				+"<input type=\"hidden\" name=\"message\" value=\"deleteprofile\">"
				+ "<button style=\"background: none; cursor: pointer; border: none\"> Delete </button><i class=\"material-icons\">delete </i> "
			
				+ "</form>"
				+ "</div >");
				
		if(message.equals("updated")) {
			out.println( "<table class= \"detail\">\r\n"
					+ "				<tr>\r\n"
					+ "					<td><b>Name : </b></td>\r\n"
					+ "					<td>"+entity.getName()+"</td>\r\n"
					+ "				</tr>\r\n"
					+ "				<tr>\r\n"
					+ "					<td><b>Employee Id : </b></td>\r\n"
					+ "					<td>"+entity.getEmpId()+"</td>\r\n"
					+ "				</tr>\r\n"
					+ "				<tr>\r\n"
					+ "					<td><b>Email Id : </b></td>\r\n"
					+ "					<td>"+entity.getEmailId()+"</td>\r\n"
					+ "				</tr>\r\n"
					+ "				<tr>\r\n"
					+ "					<td><b>phone No : </b></td>\r\n"
					+ "					<td>"+entity.getPhoneNo()+"</td>\r\n"
					+ "				</tr>\r\n"
					+ "			</table>\r\n"		
					+ "</div>");
		}else if(message.equals("edit")){
			request.getRequestDispatcher("editprofile.html").include(request, response);
		}else if(message.equals("deleteprofile")){
			
			OfyService.ofy().delete().type(SignupEntity.class).id(Long.parseLong(empId)).now();
			response.sendRedirect("index.html");
	}
		

		out.println("</body>\r\n" + "</html>");
	}
}
