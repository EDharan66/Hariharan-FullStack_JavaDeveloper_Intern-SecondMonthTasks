package GenericServletPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class GenericServletExample extends GenericServlet{

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		
		  
		String title = "Hello this is GenericServlet program";
		PrintWriter out=arg1.getWriter();  
		out.print("<html><body>");  
		out.print("<b></b>");  
		out.print("<h1 align = \"center\" style=\"margin-top: 5%;\">" + title + "</h1>\n" +
	               "<ul style=\"margin-left: 25%; margin-top: 3%;margin-right: 25%;\">\n" +
	                  "  <li><b>1.	public void init(ServletConfig config)</b> <br>is used to initialize the servlet."+
	                  "  <li><b>2.	public abstract void service(ServletRequest request, ServletResponse response)</b> <br>provides service for the incoming request. It is invoked at each time when user requests for a servlet."+ 
	                  "  <li><b>3.	public void destroy()</b> <br>is invoked only once throughout the life cycle and indicates that servlet is being destroyed."+
	                  "  <li><b>4.	public ServletConfig getServletConfig()</b> <br>returns the object of ServletConfig."+
	                  "  <li><b>5.	public String getServletInfo()</b> <br>returns information about servlet such as writer, copyright, version etc."+
	                  "  <li><b>6.	public void init()</b> <br>it is a convenient method for the servlet programmers, now there is no need to call super.init(config)"+
	                  "  <li><b>7.	public ServletContext getServletContext()</b> <br>returns the object of ServletContext."+ 
	                  "  <li><b>8.	public String getInitParameter(String name)</b> <br>returns the parameter value for the given parameter name."+
	                  "  <li><b>9.	public Enumeration getInitParameterNames()</b> <br>returns all the parameters defined in the web.xml file."+
	                  "  <li><b>10. public String getServletName(</b>) <br>returns the name of the servlet object."+
	                  "  <li><b>11. public void log(String msg)</b> <br>writes the given message in the servlet log file."+
	                  "  <li><b>12. public void log(String msg,Throwable t)</b> <br>writes the explanatory message in the servlet log file and a stack trace."+
	                 
	               "</ul>\n" );  
		
		out.print("</body></html>");  
		
	}

}
