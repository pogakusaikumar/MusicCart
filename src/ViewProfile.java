import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewProfile
 */
@WebServlet("/ViewProfile")
public class ViewProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;PreparedStatement st=null;
    public void init()
    {
 	   try {
 		   Class.forName("com.mysql.jdbc.Driver");
 		   con=DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb","root","root");
 		   st=con.prepareStatement("select * from user where username=?");
 	   }
 	   catch(Exception e) {}
    } 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter(); 
		Cookie[] ck=request.getCookies();
		 if(ck==null)
		 {
			 RequestDispatcher rd=request.getRequestDispatcher("login.html");
			 rd.include(request,response);
			 pw.println("<div style='color:red;text-align:center'>please login first!</div>");
		 }
		 else
		 {
			 RequestDispatcher rd=request.getRequestDispatcher("profilebackground.html");
			 rd.include(request,response);
			 String uname=ck[0].getValue();
			 try {
				 st.setString(1,uname);
				ResultSet rs=st.executeQuery();
				if(rs.next()) {
					pw.println("<center><img src='"+rs.getString(13)+"' width='80vw' height='80vh' style='border-radius:30px;'><br>");
					pw.println("<span class='table table-bordered table-striped table-hover'><table style='font-weight:bold;'><tr><td>UserName</td><td>"+rs.getString(1)+"</td></tr><tr><td>FullName</td><td>"+rs.getString(3)+" "+rs.getString(4)+"</td></tr><tr><td>PhoneNumber</td><td>"+rs.getString(5)+"</td></tr><tr><td>Email</td><td>"+rs.getString(6)+"</td></tr><tr><td>Gender</td><td>"+rs.getString(7)+"</td></tr><tr><td>DateOfBirth</td><td>"+rs.getString(8)+"</td></tr><tr><td>Country</td><td>"+rs.getString(9).toUpperCase()+"</td></tr><tr><td>Home Address</td><td>"+rs.getString(11)+"</td></tr></table></span></center>");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
