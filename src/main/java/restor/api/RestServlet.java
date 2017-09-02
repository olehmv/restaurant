package restor.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import restor.dto.item.Item;

/**
 * Servlet implementation class RestServlet
 */
@WebServlet(description = "RestServlet", urlPatterns = { "/*" })
public class RestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter w = response.getWriter();
		 w.println("GET request handling");
		    w.println(request.getPathInfo());
		    w.println(request.getParameterMap());
		    try {
		      RestRequest resourceValues = new RestRequest(request.getPathInfo());
		     w.println(resourceValues.getId());
		    } catch (ServletException e) {
		      response.setStatus(400);
		      response.resetBuffer();
		      e.printStackTrace();
		      w.println(e.toString());
		    }
		System.out.println(request.getPathInfo());
		ObjectMapper mapper = new ObjectMapper();
		Item item = new Item();
		item.setDescription("first item");
		item.setPrice(12.12);
		String jsonString = mapper.writeValueAsString(item);
		w.print(jsonString);
		w.flush();
		w.close();

	}
}

//			    PrintWriter out = response.getWriter();
//			 
//PrintWriter w = response.getWriter();
//JsonObjectBuilder b = Json.createObjectBuilder();
//Item item = new Item();
//item.setDescription("first item");
//item.setPrice(12.12);
//JsonObject build = b.add("price", item.getPrice()).add("description", item.getDescription()).build();
//w.print(build);
//			    out.println("GET request handling");
//			    out.println(request.getPathInfo());
//			    out.println(request.getParameterMap());
//			    try {
//			      RestRequest resourceValues = new RestRequest(request.getPathInfo());
//			      out.println(resourceValues.getId());
//			    } catch (ServletException e) {
//			      response.setStatus(400);
//			      response.resetBuffer();
//			      e.printStackTrace();
//			      out.println(e.toString());
//			    }
//			    out.close();
//			  }