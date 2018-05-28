
/**
 * @author saigopal
 * Connecting JDBC and JAVA Servlets 
 *
 * Change the class name in the web.xml file under <servlet-class> tag to MyServlet2 if you wanna use this servlet program.
 * */


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.egiants.util.ServletUtil;
import com.mysql.jdbc.PreparedStatement; 
/*if the compiler is unable to identify the package then we need to give the class path for that particular jar file.
	now, we need to identify  which jar will be having the javax.servlet package import the jar file.

 */

public class MyServlet4 extends HttpServlet
{
	//implementing httpServelt class
	//@Override
	//public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException //implementing doPost method from the 
	{
		String Name = req.getParameter("Name");//request from the data entered in the html page to the server
		String Address = req.getParameter("Address");
		String Country = req.getParameter("Country");

		ServletContext /*context*/ application = req.getServletContext();//servlet context object will be for the entire application.
		//generally the naming convention for the servlet context object will be marked as "application".

		this.getServletConfig();//servlet config object will only with in the servlet.
		Connection conn = null;

		if(application.getAttribute("conn")!= null)//getting the parament "conn" object.
		{
			conn = (Connection) application.getAttribute("conn");
		}else//if it is equal to null means if there's no connection object in the servlet then we need to create our own connection and then we have to keep it in the "servlet context".
		{
			conn = ServletUtil.getConnection();
			application.setAttribute("conn", conn);
		}

		boolean redirect = true;

		try{
			java.sql.PreparedStatement pstmt = conn.prepareStatement("insert into JDBCDemo values(?,?,?)");
			pstmt.setString(1, Name);
			pstmt.setString(2, Address);
			pstmt.setString(3, Country);
			pstmt.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();//to track the error is in which line.
			resp.sendRedirect("./Error.html");
			redirect =false;

		}

		System.out.println("Name:" +Name+ ", Address:" +Address+ ", Country:" +Country);
		resp.setContentType("text/html");
		OutputStream os = resp.getOutputStream();//resp.getOutputStream is used to print data in the browser page.

		PrintStream out = new PrintStream(os);

		out.println("Hi, " +Name);

		out.println("<h1>Registration Successfull!! :)</h1>");//we can see this print statement in the server side console.
		/*	To let the browser know that we are using html tags we need to set the content using setter methods,
		 * 	then browser will try to find if there any html tags in the response.
		 * 		if there are any html tags it will try to execute them.
		 *I need to set the content type, we have to use response object & we need to set the content type of response.
		 *we need to go for setter methods. Why because we need to set a property "resp.setContentType("text/html");"
		 *
		 *Using java servlets we may be able handle both front end and back end operations. 
		 *But servlet is not meant for any kind of front end operation.It is only meant for back end operation. 
		 *If there's any background operation or manipulation something like that if we need to do only for that purpose we use java servlets.
		 *If we want to display something on the browser then we have to go for JSP's.
		 *JSP's are used to generate dynamic webpages. If it is static webpages we have to go for HTML.
		 *If it is dynamic pages then we have to go for JSP's
		 *
		 */
		System.out.println("Inside Service()");
		
		if(redirect )
		{	
			resp.sendRedirect("./Success.html");
		}
	}

}

