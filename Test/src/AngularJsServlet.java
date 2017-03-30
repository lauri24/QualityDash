

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class AngularJsServlet
 */
@WebServlet("/AngularJsServlet")
public class AngularJsServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

     public AngularJsServlet() {
             super();
     }

     protected void doGet(HttpServletRequest request,
         HttpServletResponse response) throws ServletException, IOException {
             PersonData personData = new PersonData();
             personData.setFirstName("Mohaideen");
             personData.setLastName("Jamil");

             String json = new Gson().toJson(personData);
             response.setContentType("application/json");
             response.getWriter().write(json);
     }
}
