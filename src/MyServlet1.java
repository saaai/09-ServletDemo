/*
 *  Change the class name in the web.xml file under <servlet-class> tag to MyServlet2 if you wanna use this servlet program.
 *  */


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet; 
/*if the compiler is unable to identify the package then we need to give the class path for that particular jar file.
	now, we need to identify  which jar will be having the javax.servlet package import the jar file.
  
	*/

public class MyServlet1 implements Servlet/* extends HttpServlet*/{
	ServletConfig config;

  public void init(javax.servlet.ServletConfig config)//initializing inside the server and it will used by all the other applications.
  {
  	this.config = config;
  	System.out.println("inside init()");
  }
  public javax.servlet.ServletConfig getServletConfig()
  {
  	System.out.println("inside getServletInfo()");
  	return null;
  }
  public void service(ServletRequest request, ServletResponse response) 
  {
  		System.out.println("Inside service()");
  }
  public java.lang.String getServletInfo()
  {
  	  		System.out.println("Inside sgetServletInfo()");

  		//expecting return value as a string
  	return "Servlet Info";
  }
  public void destroy()
  {
  		  		System.out.println("Inside destry()");

  }


	
	
	
}