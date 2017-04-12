import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Random;
public class MesaResource {

 private Connection connection;
	
 public MesaResource(Connection connection){
	 
	 this.connection=connection;
 }
	
 public String getKPIQueryForMap(String categ_metric,String service_group_name,String country_shortname,String metric_type,String date1, String date2) throws SQLException{
		ArrayList responseArray = new ArrayList();
		Statement stmt = null;
	 	String jsonResult="";
	 	stmt = this.connection.createStatement();
	 	Gson gson = new GsonBuilder().create();
	 	     
	    String queryString="SELECT AVG(f.measure_amt) as Average_amt,f.country_shortname,dim2.validation_rule_name,dim2.validation_rule_comment,f.measure_fact_date, dim2.quality_metric_type_comment,dim2.quality_metric_categ_shortname,dim1.service_main_group_name,dim2.quality_metric_type_name FROM mesa.PL_MEASURE_FACT_PRT f "
					  +"INNER JOIN mesa.PL_SERVICE_PRT dim1 ON (f.validation_service_shortname = dim1.Service_Component_ShortName) "
					  +"INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 ON (f.Validation_Rule_Id = dim2.Validation_Rule_Id) WHERE f.country_shortname IN ('EE','LT','LV')"
					  +"AND dim2.quality_metric_categ_shortname='"+categ_metric+"' AND dim1.service_main_group_name='"+service_group_name+"' AND dim2.quality_metric_type_name='"+metric_type+"' AND f.measure_fact_date BETWEEN '"+date1+"' and'"+date2+"' Group BY f.country_shortname,dim2.validation_rule_name,f.measure_fact_date, dim2.quality_metric_type_comment,dim2.quality_metric_categ_shortname,dim1.service_main_group_name,dim2.quality_metric_type_name,dim2.validation_rule_comment ORDER BY dim2.validation_rule_comment";
	    
	     System.out.println("IS HERE");
	     System.out.println(queryString);
	 	ResultSet rs = stmt.executeQuery(queryString);
	 	while(rs.next()) {
	 	  //int numColumns = rs.getColumnCount();
	 
	 	 // for (int i=1; i<=numColumns; i++) {
	 	   // String column_name = rsmd.getColumnName(i);
	 	  
	 		QualityModel model=new QualityModel();
	 		model.setMeasureAmt(rs.getInt("average_amt"));
	 		model.setServiceMainGroupName(rs.getString("service_main_group_name"));
	 		model.setDate(rs.getString("measure_fact_date"));
	 		String country=rs.getString("country_shortname");
	 		if(country.equals("EE")){
	 			country="EST";
	 		}
	 		if(country.equals("LV")){
	 			country="LVA";
	 		}
	 		if(country.equals("LT")){
	 		  country="LTU";
	 		}
	 	
	 		model.setCountry(country);
	 		model.setQualityMetricTypeComment(rs.getString("validation_rule_name").replaceAll("(.{50})", "$1<br>"));
	 		//jsonResult = gson.toJson(model);
	 	 // }
	 		responseArray.add(model);
	 	}
	 	 jsonResult = gson.toJson(responseArray);
	     rs.close();
	     stmt.close();
	     
	     return jsonResult;
		 
	 }
 public String getMetricsCountsOnDates(String categ_metric,String service_group_name,String country_shortname,String metric_type,String date1, String date2) throws SQLException{
	 
	Statement stmt = null;
 	String jsonResult="";
 	stmt = this.connection.createStatement();
 	Gson gson = new GsonBuilder().create();
 	ArrayList responseArray = new ArrayList();
    String queryString="SELECT DISTINCT f.measure_cnt,f.measure_fact_date,dim2.quality_metric_type_name,dim2.quality_metric_type_comment,"
    		+"dim2.quality_metric_categ_shortname,dim1.service_main_group_name FROM mesa.pl_measure_fact_prt"  
    		+" f  INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 ON"
 			+"(f.Validation_Rule_Id = dim2.Validation_Rule_Id)"
            +"INNER JOIN mesa.PL_SERVICE_PRT dim1 ON (f.validation_service_shortname = dim1.Service_Component_ShortName) "
            +"WHERE f.country_shortname='GR' AND dim2.quality_metric_type_name='UNKNOWN QUALITY METRIC'"
			+"AND dim2.quality_metric_categ_shortname='NA'  AND  dim1.service_main_group_name='Performance Management' ORDER BY f.measure_cnt ASC"; 
    
     System.out.println("IS HERE");
     System.out.println(queryString);
 	ResultSet rs = stmt.executeQuery(queryString);
 	while(rs.next()) {
 	  //int numColumns = rs.getColumnCount();
 
 	 // for (int i=1; i<=numColumns; i++) {
 	   // String column_name = rsmd.getColumnName(i);
 	 	
 		QualityModel model=new QualityModel();
 		model.setMeasureAmt(rs.getInt("measure_cnt"));
 		model.setServiceMainGroupName(rs.getString("service_main_group_name"));
 		model.setDate(rs.getString("measure_fact_date"));
 		model.setQualityMetricTypeComment(rs.getString("quality_metric_type_comment").replaceAll("(.{50})", "$1<br>"));
 		jsonResult = gson.toJson(model);
 	 // }
 		responseArray.add(model);
 	}
	 jsonResult = gson.toJson(responseArray);
     rs.close();
     stmt.close();
     
     return jsonResult;
	 
 }
 
 public String getKPIQuery1(String categ_metric,String service_group_name,String country_shortname,String metric_type,String date1, String date2) throws SQLException{
	ArrayList responseArray = new ArrayList();
	Statement stmt = null;
 	String jsonResult="";
 	stmt = this.connection.createStatement();
 	Gson gson = new GsonBuilder().create();
 	     
    String queryString="SELECT AVG(f.measure_amt) as Average_amt,dim2.validation_rule_name,f.measure_fact_date, dim2.quality_metric_type_comment,dim2.quality_metric_categ_shortname,dim1.service_main_group_name,dim2.quality_metric_type_name FROM mesa.PL_MEASURE_FACT_PRT f "
				  +"INNER JOIN mesa.PL_SERVICE_PRT dim1 ON (f.validation_service_shortname = dim1.Service_Component_ShortName) "
				  +"INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 ON (f.Validation_Rule_Id = dim2.Validation_Rule_Id) WHERE f.country_shortname='EE'"
				  +"AND dim2.quality_metric_categ_shortname='"+categ_metric+"' AND dim1.service_main_group_name='"+service_group_name+"' AND dim2.quality_metric_type_name='"+metric_type+"' AND f.measure_fact_date BETWEEN '"+date1+"' and'"+date2+"' Group BY dim2.quality_metric_type_comment,dim2.quality_metric_categ_shortname,dim1.service_main_group_name,dim2.quality_metric_type_name,f.measure_fact_date,dim2.validation_rule_name";
    
     System.out.println("IS HERE");
     System.out.println(queryString);
 	ResultSet rs = stmt.executeQuery(queryString);
 	while(rs.next()) {
 	  //int numColumns = rs.getColumnCount();
 
 	 // for (int i=1; i<=numColumns; i++) {
 	   // String column_name = rsmd.getColumnName(i);
 	  
 		QualityModel model=new QualityModel();
 		model.setMeasureAmt(rs.getInt("average_amt"));
 		model.setServiceMainGroupName(rs.getString("service_main_group_name"));
 		model.setDate(rs.getString("measure_fact_date"));
 		model.setQualityMetricTypeComment(rs.getString("validation_rule_name").replaceAll("(.{50})", "$1<br>"));
 		//jsonResult = gson.toJson(model);
 	 // }
 		responseArray.add(model);
 	}
 	 jsonResult = gson.toJson(responseArray);
     rs.close();
     stmt.close();
     
     return jsonResult;
	 
 }
	
 public String getKPI(String parameter1, String parameter2,String parameter3,String parameter4) throws SQLException{
	    
    	Statement stmt = null;
    	String jsonInString="";
    	stmt = this.connection.createStatement();
    	Gson gson = new GsonBuilder().create();
    	    
        if(parameter1 == null && parameter1.isEmpty()){
        	
        	parameter1="EE";
        }
         
		  if(parameter2 == null && parameter2.isEmpty()){
		        	
		        	parameter2="DQKPI";
		   }
        
        String queryString="SELECT DISTINCT dim2.expected_result_cnt,dim2.validation_rule_comment,dim2.quality_metric_type_name,"
				  +"f.fact_row_no,dim1.service_main_group_name,f.fact_col_no,dim2.expected_result_amt,f.measure_amt,dim1.service_domain_name"
				  +", dim2.since201702_metric_cnt, dim2.expected_result_cnt,dim1.service_domain_name,f.measure_fact_date "
				  +"FROM mesa.PL_MEASURE_FACT_PRT f INNER JOIN mesa.PL_SERVICE_PRT dim1 ON (f.validation_service_shortname = dim1.Service_Component_ShortName) "
				  +"INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 ON (f.Validation_Rule_Id = dim2.Validation_Rule_Id) WHERE f.country_shortname='"+parameter1+"'"
				  +"AND dim2.quality_metric_categ_shortname='"+parameter2+"' AND f.fact_row_no='"+parameter3+"' AND f.fact_col_no='"+parameter4+"';";
       
        System.out.println("IS HERE");
        System.out.println(queryString);
    	ResultSet rs = stmt.executeQuery(queryString);
    	while(rs.next()) {
    	  //int numColumns = rs.getColumnCount();
    
    	 // for (int i=1; i<=numColumns; i++) {
    	   // String column_name = rsmd.getColumnName(i);
    	  
    		Format_conformacy model=new Format_conformacy();
    		model.setKPI(rs.getInt("measure_amt"));
    		model.setServiceName(rs.getString("service_main_group_name"));
    		model.setValidationRule(rs.getString("validation_rule_comment"));
    		//String  name = rs.getString("measure_text");
    		//System.out.println(name);
    	  
    	    jsonInString = gson.toJson(model);
    	 // }

    	}
   
        rs.close();
        stmt.close();
        
        return jsonInString;
    	
    }


public String getAllMeasuresCnt(String country, String service, String factrow, String factcol, String date1, String date2) throws SQLException{
	    
 	Statement stmt = null;
 	String jsonInString="";
 	stmt = this.connection.createStatement();
 	Gson gson = new GsonBuilder().create();
 	ArrayList responseArray = new ArrayList();
 	date1="2017-02-03 01:32:27";
 	date2="2017-02-03 01:32:27";
 	String queryString="SELECT DISTINCT f.validation_rule_id,dim2.validation_rule_comment,dim2.quality_metric_type_name,f.fact_row_no,dim1.service_main_group_name,"
		+ "f.fact_col_no,dim2.expected_result_amt,f.measure_amt,dim1.service_domain_name, dim2.expected_result_cnt,f.run_dttm "
		+ "FROM mesa.PL_MEASURE_FACT_PRT f INNER JOIN mesa.PL_SERVICE_PRT dim1 ON (f.validation_service_shortname = dim1.Service_Component_ShortName)"
		+ " INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 ON (f.Validation_Rule_Id = dim2.Validation_Rule_Id) "
		+ "WHERE f.country_shortname='"+country+"' AND dim1.service_main_group_name='"+service+"' and f.fact_row_no='"+factrow+"' "
		+ "AND f.validation_rule_id IN ('2803','2260','2300','101') AND  f.run_dttm between '"+date1+"' and '"+date2+"';";
 	ResultSet rs = stmt.executeQuery(queryString);
 	while(rs.next()) {
 	  //int numColumns = rs.getColumnCount();
 
 	 // for (int i=1; i<=numColumns; i++) {
 	   // String column_name = rsmd.getColumnName(i);
 	  
 		CompletenessModel model=new CompletenessModel();
 		model.setMeasureAmt(rs.getInt("avg"));
 		model.setDate(rs.getString("run_dttm"));
 		model.setQualityMetricType(rs.getString("quality_metric_type_name"));
 		model.setValidationRuleComment(rs.getString("validation_rule_comment"));
 	
 		//Date d1 = new Date(rs.getDate("date_time").getTime());
 		//String  name = rs.getString("measure_text");
 		//System.out.println(name);
 	   
 	    responseArray.add(model);
 	 // }

 	}
 	  System.out.println("IS HERE");
      System.out.println(queryString);
 	 jsonInString = gson.toJson(responseArray);
     rs.close();
     stmt.close();
     
     return jsonInString;
 	
 }


public String getCompletness(String parameter1, String parameter2) throws SQLException{
    
 	Statement stmt = null;
 	String jsonInString="";
 	stmt = this.connection.createStatement();
 	Gson gson = new GsonBuilder().create();
 	ArrayList responseArray = new ArrayList();
 	ResultSet rs = stmt.executeQuery( "SELECT DISTINCT f.validation_rule_id,dim2.validation_rule_comment,"
 			+ "dim2.quality_metric_type_name,f.fact_row_no,dim1.service_main_group_name,f.fact_col_no,dim2.expected_result_amt,"
 			+ "f.measure_amt,dim1.service_domain_name, dim2.expected_result_cnt,f.run_dttm "
 			+ "FROM mesa.PL_MEASURE_FACT_PRT f INNER JOIN mesa.PL_SERVICE_PRT dim1 ON (f.validation_service_shortname = dim1.Service_Component_ShortName) INNER JOIN mesa.PL_VALIDATION_RULE_EXT dim2 ON "
 			+ "(f.Validation_Rule_Id = dim2.Validation_Rule_Id) "
 			+ "WHERE f.country_shortname='EE' AND dim1.service_main_group_name='BB Data Warehouse' and f.fact_row_no=1 "
 			+ "AND dim2.quality_metric_type_name='COMPLETENESS' ORDER BY f.run_dttm ;" );
 	while(rs.next()) {
 	  //int numColumns = rs.getColumnCount();
 
 	 // for (int i=1; i<=numColumns; i++) {
 	   // String column_name = rsmd.getColumnName(i);
 	  
 		CompletenessModel model=new CompletenessModel();
 		model.setMeasureAmt(rs.getInt("measure_amt"));
 		model.setDate(rs.getString("run_dttm"));
 		model.setQualityMetricType(rs.getString("quality_metric_type_name"));
 		//Date d1 = new Date(rs.getDate("date_time").getTime());
 		//String  name = rs.getString("measure_text");
 		//System.out.println(name);
 	   
 	    responseArray.add(model);
 	 // }

 	}
 	 jsonInString = gson.toJson(responseArray);
     rs.close();
     stmt.close();
     
     return jsonInString;
 	
 }
}
/* public String getCountryCodes(String parameter1, String parameter2) throws SQLException{
	    
	 	Statement stmt = null;
	 	String jsonInString="";
	 	stmt = this.connection.createStatement();
	 	Gson gson = new GsonBuilder().create();
	 	ResultSet rs = stmt.executeQuery("SELECT DISTINCT run_country_shortname FROM mesa.PL_MEASURE_FACT_PRT;" );
	 	while(rs.next()) {
	 	  //int numColumns = rs.getColumnCount();
	 
	 	 // for (int i=1; i<=numColumns; i++) {
	 	   // String column_name = rsmd.getColumnName(i);
	 	  
	 		Format_conformacy model=new Format_conformacy();
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
	
}*/
