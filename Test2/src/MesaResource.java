import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Random;
public class MesaResource {

 private Connection connection;
	
 public MesaResource(Connection connection){
	 
	 this.connection=connection;
 }
	
 
 
	
 public String getCompletness(String parameter1, String parameter2) throws SQLException{
	    
    	Statement stmt = null;
    	String jsonInString="";
    	stmt = this.connection.createStatement();
    	Gson gson = new GsonBuilder().create();
    	
    	Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(10000);
        
    	ResultSet rs = stmt.executeQuery( "SELECT * FROM mesa.PL_MEASURE_FACT_PRT LIMIT 1 OFFSET "+ randomInt+";" );
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
 
 public String getAccuraccy(String parameter1, String parameter2) throws SQLException{
	    
 	Statement stmt = null;
 	String jsonInString="";
 	stmt = this.connection.createStatement();
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
 
 public String getCountryCodes(String parameter1, String parameter2) throws SQLException{
	    
	 	Statement stmt = null;
	 	String jsonInString="";
	 	stmt = this.connection.createStatement();
	 	Gson gson = new GsonBuilder().create();
	 	ResultSet rs = stmt.executeQuery("SELECT DISTINCT run_country_shortname FROM mesa.PL_MEASURE_FACT_PRT;" );
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
	
}