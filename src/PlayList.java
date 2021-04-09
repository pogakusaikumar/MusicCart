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
 * Servlet implementation class PlayList
 */
@WebServlet("/PlayList")
public class PlayList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;PreparedStatement st2=null;
	public void init()
    {
 	   try {
 		   Class.forName("com.mysql.jdbc.Driver");
 		   con=DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb","root","root");
 		   st2=con.prepareStatement("select * from cart where username=?");
 	   }
 	   catch(Exception e) {}
    } 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlayList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw=response.getWriter(); 
		Cookie ck[]=request.getCookies();
		if(ck!=null)
		{
			String uname=ck[0].getValue();
			RequestDispatcher rd=request.getRequestDispatcher("background.html");
			 rd.include(request,response);
		try {
			st2.setString(1,uname);
			ResultSet rs=st2.executeQuery();
			pw.println("<form><table>");
			while(rs.next())
			{
				pw.println("<tr><td>"+rs.getString(3)+"</td>"+"<td>:</td>"+"<td>"+rs.getString(2)+"</td><td><input type='checkbox' name='song' value="+rs.getString(3)+"></td></tr>"); 
			}
			pw.println("<tr><td colspan='2'><input style='background-color:red;border-radius:10px;width:100px;height:30px;color:white;font-size:12px' type='submit' value='REMOVE' formaction='./RemoveCart'></td><td><input style='background-color:red;border-radius:10px;width:120px;height:30px;color:white;font-size:12px' type='submit' value='REMOVEALL' formaction='./ClearCart'></td></table></form>");
			}catch(Exception e) {}
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
