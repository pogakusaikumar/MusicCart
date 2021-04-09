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
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con=null;PreparedStatement st=null,st1=null,st2=null,st3=null;
    public void init()
    {
 	   try {
 		   Class.forName("com.mysql.jdbc.Driver");
 		   con=DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb","root","root");
 		   st=con.prepareStatement("insert into cart values(?,?,?)");
 		   st3=con.prepareStatement("select * from cart where username=? and playlist=?");
 	   }
 	   catch(Exception e) {}
    } 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] aname=request.getParameterValues("song"); 
		Cookie ck[]=request.getCookies();
		if(ck!=null)
		{
			RequestDispatcher rd=request.getRequestDispatcher("home.html");
			rd.include(request,response);
			String uname=ck[0].getValue();
			try {
				for(int i=0;i<aname.length;i++)
				{
					String url="C:\\Users\\pogak\\Music\\"+aname[i]+".mp3";
					String a="<audio controls src=\""+url+"\"></audio>";
					st3.setString(1,uname);
					st3.setString(2,a);
					ResultSet rs=st3.executeQuery();
					if(!rs.next())
					{
						st.setString(1,uname);
						st.setString(2,a);
						st.setString(3,aname[i]);
						st.executeUpdate();
					}
				}
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
