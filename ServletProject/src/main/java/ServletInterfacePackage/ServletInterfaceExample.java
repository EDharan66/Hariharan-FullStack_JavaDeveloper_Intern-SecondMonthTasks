package ServletInterfacePackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ServletInterfaceExample implements Servlet{
	ServletConfig config=null;  
	
	@Override
	public void destroy() {
		System.out.println("destroy method call");
		
	}

	@Override
	public ServletConfig getServletConfig() {
		
		return config;
	}

	@Override
	public String getServletInfo() {
		
		return "copyright EDharan";
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		
		this.config = arg0;
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		String title = "Hello this is servlet Interface implementing program";
		PrintWriter out=arg1.getWriter();  
		out.print("<html><body>");  
		out.print("<b></b>");  
		out.print("<h1 align = \"center\" style=\"margin-top: 5%;\">" + title + "</h1>\n" +
	               "<ul style=\"margin-left: 25%; margin-top: 3%;\">\n" +
	                  "  <li>public void init(ServletConfig arg0) throws ServletException {}"
	                  +
	                  "  <li>public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {}"
	                  + 
	                  "  <li>public ServletConfig getServletConfig() {}"
	                  +
	                  "  <li>public String getServletInfo() {}"
	                  +
	                  "  <li>public void destroy() {}"
	                  +
	               "</ul>\n" );  
		
		out.print("</body></html>"); 
		arg1.setContentType("text/html");  
		
	}

}
