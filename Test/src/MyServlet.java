import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.json.JSONException;
import org.json.JSONObject;

public class MyServlet extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		  //create Json Object
		  JSONObject json = new JSONObject();

		    // put some value pairs into the JSON object .
		    try {
				json.put("Mobile", 777);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    try {
				json.put("Name", "ManojSarnaik");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    // finally output the json string       
		    out.print(json.toString());
	}

}