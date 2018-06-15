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
 * Servlet implementation class team
 */
@WebServlet("/API/team")
public class team extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReflectionMethod rm;
    private CommonVariable cv;

    private HttpSession _session = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public team() {
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
        rm = new ReflectionMethod(this);
        // TODO Auto-generated method stub
        System.out.println("TEAM GET CALLED");
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
        System.out.println("get-1-start");
        System.out.println(jsonObj);
        System.out.println("get-1-end");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonObj.toJSONString());
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
        response.getWriter().write(jsonObj.toJSONString());
    }
    
    // ================================================================================
    // TEAM METHODS
    // ================================================================================

    public JSONObject info(JSONObject params) throws ClassNotFoundException, SQLException{
        JSONObject ret = new JSONObject();
        DBConnector dc = new DBConnector();
        
        System.out.println("SELECT CALLED");
        
        // 1. 팀 기본 정보 조회 
        
        String qTeam = "select * from tb_team where teamMng = " + _session.getAttribute("userSeq");
        
        System.out.println(qTeam);
        
        JSONArray jTeam = null;
        try {
            jTeam = dc.excute(qTeam);
        }
        catch(SQLException e) {
            e.printStackTrace(System.out);
            ret.put("METHOD_RESULT_CD", -1);
            ret.put("METHOD_ERR_MSG" , "팀 정보 조회중 오류가 발생했습니다.");
            ret.put("METHOD_ERR_LOCALE_MSG" , e.getLocalizedMessage());
            return ret;
        }
        System.out.print(jTeam);
        ret.put("METHOD_RESULT_CD", 1);
        ret.put("METHOD_RESULT_DATA", jTeam);
        
        return ret;
    }
    

}
