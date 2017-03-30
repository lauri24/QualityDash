

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
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
 * Servlet implementation class GetUser
 */
@WebServlet("/GetUser")
public class GetUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private static Connection getRemoteConnection() {
  	  System.out.println("Here1");
      
          try {
        	  System.out.println("Here");
          Class.forName("org.postgresql.Driver");
          String dbName = "postgres";
          String userName = "postgres";
          String password ="postgres";
          String hostname = "ec2-52-56-206-102.eu-west-2.compute.amazonaws.com";
          String port = "5432";
          String jdbcUrl = "jdbc:postgresql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
          System.out.println("Getting remote connection with connection string from environment variables.");
          Connection con = DriverManager.getConnection(jdbcUrl);
          System.out.println("Remote connection successful.");
          return con;
        }
        catch (ClassNotFoundException e) { System.out.println(e.toString());}
        catch (SQLException e) { System.out.println(e.toString());}
        
        return null;
      }
   
    public static String getMeasureText(Connection connection) throws SQLException{
    
    	Statement stmt = null;
    	String jsonInString="";
    	stmt = connection.createStatement();
    	Gson gson = new GsonBuilder().create();
    	ResultSet rs = stmt.executeQuery( "SELECT * FROM mesa.PL_MEASURE_FACT_PRT LIMIT 1 OFFSET 100;" );
    	while(rs.next()) {
    	  //int numColumns = rs.getColumnCount();
    
    	 // for (int i=1; i<=numColumns; i++) {
    	   // String column_name = rsmd.getColumnName(i);
    	  
    		CompletnessModel model=new CompletnessModel();
    		model.setCompletness(rs.getInt("measure_text"));
    		//String  name = rs.getString("measure_text");
    		//System.out.println(name);
    	  
    	    jsonInString = gson.toJson(model);
    	 // }

    	}
    	
        rs.close();
        stmt.close();
        System.out.println(jsonInString);
        return jsonInString;
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//getDatabaseConnection();
		Connection connA=getRemoteConnection();
		String json="";
		try {
			json=getMeasureText(connA);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		String userName = request.getParameter("userName").trim();
		if(userName == null || "".equals(userName)){
			userName = "Guest";
		}
		
		String greetings = json;
		
		response.setContentType("text/plain");
		response.getWriter().write(json);
	}

}
