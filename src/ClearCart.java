

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ClearCart
 */
@WebServlet("/ClearCart")
public class ClearCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;PreparedStatement st1=null;
    public void init()
    {
 	   try {
 		   Class.forName("com.mysql.jdbc.Driver");
 		   con=DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb","root","root");
 		   st1=con.prepareStatement("delete from cart where username=?");
 	   }
 	   catch(Exception e) {}
    }  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClearCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cookie ck[]=request.getCookies();
		if(ck!=null)
		{
			String uname=ck[0].getValue();
			try {
				st1.setString(1,uname);
				st1.executeUpdate();
				}catch(Exception e) {}
			RequestDispatcher rd=request.getRequestDispatcher("./PlayList");
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
