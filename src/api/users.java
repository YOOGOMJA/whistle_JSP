package api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ReflectionMethod;

import org.json.simple.*;
import org.json.simple.parser.*;;


/**
 * Servlet implementation class users
 */
@WebServlet("/API/users")
public class users extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ReflectionMethod rm;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public users() {
        super();
        // TODO Auto-generated constructor stub
        //rm = new ReflectionMethod(this.getClass().getName());
        rm = new ReflectionMethod(this);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//JSONObject o = (JSONObject) JSONSerializer.toJSON(request.getParameter("WHATEVER"));
		JSONParser parser = new JSONParser();
		JSONObject prms = null;
		try {
			prms = (JSONObject) parser.parse(request.getParameter("params"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONObject jsonObj = rm.callAndGetResult(request.getParameter("fn"), prms);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(jsonObj.toJSONString());
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		JSONObject jsonObj = rm.callAndGetResult(request.getParameter("fn"), null);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(jsonObj.toJSONString());
	}
	
	//================================================================================
    // Sample
    //================================================================================
	
	int refMethod(JSONObject params) {
		return 1;
	}
	
	public JSONObject refMethod2(JSONObject params) {
		JSONObject ret = new JSONObject();
		
		//System.out.println(params.get("a"));
		
		long a = (long) params.get("a");
		long b = (long) params.get("b");
		
		ret.put("ret", (a+b));
		
		return ret;
	}
	
	
	//================================================================================
    // LOGIN METHODS
    //================================================================================
	
	public JSONObject login(JSONObject params) {
		
		
		
		return null;
	}
	
	
}
