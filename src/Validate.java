import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class Validate
 */
@WebServlet("/Validate")
public class Validate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;PreparedStatement st=null;
    public void init()
    {
 	   try {
 		   Class.forName("com.mysql.jdbc.Driver");
 		   con=DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb","root","root");
 		   st=con.prepareStatement("select * from user where username=? and password=?");
 	   }
 	   catch(Exception e) {}
    } 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Validate() {
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
		 try {
		 st.setString(1,uname);
		 st.setString(2,pword);
		 ResultSet rs=st.executeQuery(); 
		 if(rs.next())
		 {
			 Cookie ck1=new Cookie("uname",uname);
			 Cookie ck2=new Cookie("pword",pword);
			 response.addCookie(ck1);response.addCookie(ck2);
			 RequestDispatcher rd=request.getRequestDispatcher("home.html");
			 rd.include(request,response);
		 }
		 else
		 {
			 RequestDispatcher rd=request.getRequestDispatcher("login.html");
			 rd.include(request,response);
			 pw.println("<div style='color:red;text-align:center'>Invalid User!</div>");
		 }
		 }catch(Exception e) {}

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