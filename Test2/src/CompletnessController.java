import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class CompletnessController
 */
@WebServlet("/CompletnessController")
public class CompletnessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompletnessController() {
        super();
        // TODO Auto-generated constructor stub
    }

 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
  
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//getDatabaseConnection();
	  
		   System.out.println("IS HERE");
		DBConnectionManager dbConnection=new DBConnectionManager();
		Connection conn=dbConnection.getRemoteConnection();
		MesaResource resource=new MesaResource(conn);
		String jsonResponse="";
		try {
			
			
			jsonResponse=resource.getKPIQueryForMap(String.valueOf(request.getParameter("metric_categ")), String.valueOf(request.getParameter("service_group_name")),String.valueOf(request.getParameter("country")),String.valueOf(request.getParameter("metric_name")), String.valueOf(request.getParameter("date1")),String.valueOf(request.getParameter("date2")));
			   System.out.println("IS VALUE");
			   System.out.println(jsonResponse);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		response.setContentType("text/plain");
		response.getWriter().write(jsonResponse);
		
	
	  
   }

}
