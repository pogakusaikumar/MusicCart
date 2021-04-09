import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;

/**
 * Servlet implementation class ServletRegister
 */
@WebServlet("/ServletRegister")
public class ServletRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;PreparedStatement st=null;
       public void init()
       {
    	   try {
    		   Class.forName("com.mysql.jdbc.Driver");
    		   con=DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb","root","root");
    		   st=con.prepareStatement("insert into user values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
    	   }
    	   catch(Exception e) {}
       }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegister() {	
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter pw=response.getWriter();
		 String uname=request.getParameter("uname");
		 String pword=request.getParameter("pword");
		 String fname=request.getParameter("fname");
		 String lname=request.getParameter("lname");
		 String email=request.getParameter("email");
		 String gender=request.getParameter("gender");
		 String dob=request.getParameter("dob");
		 String country=request.getParameter("country");
		 String companyname=request.getParameter("companyname");
		 String homeAddress=request.getParameter("HomeAddress");
		 String officeAddress=request.getParameter("officeAddress");
		 String phonenumber=request.getParameter("phonenumber");
		 String profilepic=request.getParameter("profilepic");
		 int i=0;
		 try {
		 st.setString(1,uname);
		 st.setString(2,pword);
		 st.setString(3,fname);
		 st.setString(4,lname);
		 st.setString(5,phonenumber);
		 st.setString(6,email);
		 st.setString(7,gender);
		 st.setString(8,dob);
		 st.setString(9,country);
		 st.setString(10,companyname);
		 st.setString(11,homeAddress);
		 st.setString(12,officeAddress);
		 st.setString(13,profilepic);
		 i=st.executeUpdate();
		 if(i==1)
		 {
			 Cookie ck1=new Cookie("uname",uname);
			 Cookie ck2=new Cookie("pword",pword);
			 response.addCookie(ck1);response.addCookie(ck2);
			 RequestDispatcher rd=request.getRequestDispatcher("home.html");
			 rd.include(request,response);
		 }
		 if(i==0)
		 {
			 pw.println("Registration unsuccessfull");
			 RequestDispatcher rd=request.getRequestDispatcher("registration.html");
			 rd.include(request,response);
		 }
		 }catch(Exception e) {
		 }
	}
	public void destroy()
	{
		try {
		con.close();}catch(Exception e) {}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
