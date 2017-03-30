import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DBConnectionManager {
	  
	public DBConnectionManager(){
		
		
		
		
		
	}
	public static Connection getRemoteConnection() {
	  	  System.out.println("Here1");
	      
	          try {
	        	  System.out.println("Here");
	          Class.forName("org.postgresql.Driver");
	          String dbName = "postgres";
	          String userName = "postgres";
	          String password ="postgres";
	          String hostname = "ec2-52-56-213-74.eu-west-2.compute.amazonaws.com";
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
	
	
}
