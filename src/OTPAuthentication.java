

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OTPAuthentication
 */
@WebServlet("/OTPAuthentication")
public class OTPAuthentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;PreparedStatement st2=null;
	public void init()
    {
 	   try {
 		   Class.forName("com.mysql.jdbc.Driver");
 		   con=DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb","root","root");
 		   st2=con.prepareStatement("select username,password from user where email=?");
 	   }
 	   catch(Exception e) {}
    }  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OTPAuthentication() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String userotp=request.getParameter("userotp");
		 String adminotp=request.getParameter("adminotp");
		 String to=request.getParameter("to");
		 if(userotp.equals(adminotp))
		 {
			 try {
					st2.setString(1,to);
					ResultSet rs=st2.executeQuery();
					 if(rs.next())
					 {
						 Cookie ck1=new Cookie("uname",rs.getString(1));
						 Cookie ck2=new Cookie("pword",rs.getString(2));
						 response.addCookie(ck1);response.addCookie(ck2);
						 RequestDispatcher rd=request.getRequestDispatcher("home.html");
						 rd.include(request,response);
					 }
					}catch(Exception e) {}
		 }
		 else {
			 response.getWriter().println("<center>You Entered Wrong OTP!</center>");
			 RequestDispatcher rd=request.getRequestDispatcher("verify.html");
			 rd.include(request,response);
		 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public void destroy()
	{
		try {
		con.close();}catch(Exception e) {}
	}
}
