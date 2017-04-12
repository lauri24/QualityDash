

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestController
 */
@WebServlet("/DonutController")
public class DonutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DonutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
			
			DBConnectionManager dbConnection=new DBConnectionManager();
			Connection conn=dbConnection.getRemoteConnection();
			MesaResource resource=new MesaResource(conn);
			String jsonResponse="";
			try {
				jsonResponse=resource.getKPIQuery1(String.valueOf(request.getParameter("metric_categ")), String.valueOf(request.getParameter("service_group_name")),String.valueOf(request.getParameter("country")),String.valueOf(request.getParameter("metric_name")), String.valueOf(request.getParameter("date1")),String.valueOf(request.getParameter("date2")));
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    System.out.println(jsonResponse);
			response.setContentType("text/plain");
			response.getWriter().write(jsonResponse);
			
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}
	

}
