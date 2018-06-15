package api;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

/**
 * Servlet implementation class game
 */
@WebServlet("/game")
public class game extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static ReflectionMethod rm;
    private CommonVariable cv;

    private HttpSession _session = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public game() {
        super();
        // TODO Auto-generated constructor stub
        rm = new ReflectionMethod(this);
        cv = CommonVariable.getInstance();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        rm = new ReflectionMethod(this);
        _session = request.getSession();

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
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        rm = new ReflectionMethod(this);
        _session = request.getSession();

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
    
    
    

}
