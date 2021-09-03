package BookingPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookingServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String emailId = request.getParameter("Email");
		String empId = request.getParameter("EmpId");
		String phoneNo = request.getParameter("PhoneNo");
		
	PrintWriter out = response.getWriter();
	out.println("this is booking page!!");


	
	
	}
}
