package api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ReflectionMethod;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

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
		
		JSONObject jsonObj = rm.callAndGetResult(request.getParameter("fn"), null);
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

	public int refMethod(JSONObject params) {
		
		return 1;
	}
}
