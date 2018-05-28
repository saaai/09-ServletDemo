
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
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.egiants.util.ServletUtil;
import com.mysql.jdbc.PreparedStatement; 
/*if the compiler is unable to identify the package then we need to give the class path for that particular jar file.
	now, we need to identify  which jar will be having the javax.servlet package import the jar file.

 */

public class MyServlet5 extends HttpServlet
{
	//implementing httpServelt class
	//@Override
	//public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException //implementing doPost method from the 
	{
		String User_Name = req.getParameter("User_Name");//request from the data entered in the html page to the server
		String Password = req.getParameter("Password");
		//String Country = req.getParameter("Country");
		
		HttpSession session = req.getSession();
		/*Http Session is used to handle the session 
		 * A Session is nothing but a complete sequence of operation,
		 * 	a set of transactions which are happened from a single browser in a single sequence.
		 * 
		 * Now, coming to Http Session. This particular session object will be available only during the session only.
		 * so, for each and every session it will create a new Http Session object for u internally.
		 * 		ex: if u open the same link in safari then it will create a new http session object and if u open the same link in chrome it will create a new http session obejct.
		 * so , when you open all the above two sessions from the example how many servlet contexts are available only "one" ServletUtil.
		 * so , if we want to add something related to the entire application then we will keep that in the servlet context object.
		 * If we want to add something related only to the particular session, then we need to put it in the Http Session.
		 * For, attenuation purpose we need to use HttpSession only. if the person is logging in to application from mozilla then we are not supposed to allow him in the entire application.
		 * The activities should only be limited to mozilla browser only, if he access the same session in chrome his session has to start form the beginning, ex: going to login page again.
		 * 
		 */

		
		ServletContext /*context*/ application = req.getServletContext();//servlet context object will be for the entire application.
		//generally the naming convention for the servlet context object will be marked as "application".

		if(User_Name.equals("User_Name") && Password.equals("Password"))
		{
			application.setAttribute("User_Name", User_Name);
			application.setAttribute("Password", Password);
			session.setAttribute("User_Name", User_Name);
			session.setAttribute("Password", Password);
		}
		
		//To display the values of servlet context,
		Enumeration<String> attributes = application.getAttributeNames();
		//we calling application.getAttributeNames() it will be displaying what are all the names that are available in the application/servlet context & getAttributes returns enumeration of strings.
		while(attributes.hasMoreElements())//while loop for the enumeration, what we are doing here is we are just iterating all the number of elements available in the servlet context object.
			
		{
			resp.setContentType("text/html");
			OutputStream os = resp.getOutputStream();//resp.getOutputStream is used to print data in the browser page.

			PrintStream out = new PrintStream(os);
			out.println("<h3>Data form the Servlet Context</h3>");
			out.println("<h3>Hi, " +User_Name);
			String key = attributes.nextElement();
			Object value = (String) application.getAttribute(key);
			//if any elements are available then it will be trying to fetch the key from that and it will be trying to fetch the value from that particular servlet context object.
			out.println("<b>key: User_Name:, value:" +session.getAttribute("User_Name") + "</b>");
			out.println("<br>");//break for coming to new line
			out.println("<b>key: Password, value:" + application.getAttribute("Password")+"</b>" );
			out.println("<br>");
			out.println("<br>");
			
			out.println("<h3>Data form the Http Session</h3>");
			out.println("<b>key: User_Name:, value:" +session.getAttribute("User_Name") + "</b>");
			out.println("<br>");//break for coming to new line
			out.println("<b>key: Password, value:" + session.getAttribute("Password")+"</b>" );

		
		}
		/*this.getServletConfig();//servlet config object will only with in the servlet.
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
			To let the browser know that we are using html tags we need to set the content using setter methods,
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
		 
		System.out.println("Inside Service()");
		
		if(redirect )
		{	
			resp.sendRedirect("./Success.html");
		}*/
	}

}

