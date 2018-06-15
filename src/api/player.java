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
 * Servlet implementation class player
 */
@WebServlet("/API/player")
public class player extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ReflectionMethod rm;
    private CommonVariable cv;

    private HttpSession _session = null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public player() {
        super();
        // TODO Auto-generated constructor stub
        rm = new ReflectionMethod(this);
        cv = CommonVariable.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    // ================================================================================
    // PLAYER METHODS
    // ================================================================================

    public JSONObject select (JSONObject params) throws ClassNotFoundException, SQLException{
        JSONObject ret = new JSONObject();
        
        DBConnector dc = new DBConnector();
        
        // 1. 팀 기본 정보 조회 
        
        String qplayer = "select * from tb_player where plyrTeamSeq = " + params.get("teamSeq");
        JSONArray jPlayer = null;
        try {
            jPlayer = dc.excute(qplayer);
        }
        catch(SQLException e) {
            e.printStackTrace(System.out);
            ret.put("METHOD_RESULT_CD", -1);
            ret.put("METHOD_ERR_MSG" , "팀 정보 조회중 오류가 발생했습니다.");
            ret.put("METHOD_ERR_LOCALE_MSG" , e.getLocalizedMessage());
            
            return ret;
        }
        
        ret.put("METHOD_RESULT_CD", 1);
        ret.put("METHOD_RESULT_DATA", jPlayer);
        
        return ret;
    }
    
    public JSONObject selectByPriority(JSONObject params) throws ClassNotFoundException, SQLException{
        JSONObject ret = new JSONObject();
        
        String teamSeq = (String) params.get("teamSeq");
        long type = (long) params.get("type");
        
        String query ="";
        
        if(type == 0) {
            // 전체 
            query = String.format("select * from tb_player where plyrTeamSeq=%s order by plyrTeamPriority ASC", teamSeq);
        }
        else if(type == 1) {
            // 스타터만
            query = String.format("select * from tb_player where plyrTeamSeq=%s and plyrTeamPriority < 99 LIMIT 5", teamSeq);
        }
        else if(type == 2) {
            // 벤치
            query = String.format("select * from tb_player where plyrTeamSeq=%s and plyrTeamPriority > 4", teamSeq);
        }
        else {
            ret.put("METHOD_RESULT_CD", -1);
            ret.put("METHOD_ERR_MSG", "올바른 조회 조건을 넣어주세");
            
            return ret;
        }
        
        DBConnector dc= new DBConnector();
        JSONArray jTeam = null;
        
        try{
            jTeam = dc.excute(query);
        }
        catch(SQLException e) {
            e.printStackTrace(System.out);
            ret.put("RESULT_METHOD_CD", -1);
            ret.put("RESULT_ERR_MSG", "조회중 오류가 발생했습니다. 잠시 후 다시 시도 해주세요 ");
            ret.put("RESULT_ERR_LOCALE_MSG" , e.getLocalizedMessage());
        }
        
        System.out.println(jTeam);
        
        ret.put("RESULT_METHOD_CD", 1);
        ret.put("RESULT_METHOD_DATA" , jTeam);
        
        return ret;
    }
    
    public JSONObject updatePriority (JSONObject params) throws ClassNotFoundException, SQLException{
        JSONObject ret = new JSONObject();
        
        String teamSeq = (String) params.get("teamSeq");
        String plyrSeq = (String) params.get("plyrSeq");
        String type = (String) params.get("type");
        String query = "";
        if(type.equals("STARTER")) {
            query = String.format("update TB_PLAYER set plyrTeamPriority = 1 where plyrTeamSeq = %s and plyrSeq = %s", teamSeq, plyrSeq);
        }
        else if(type.equals("BENCH")){
            query = String.format("update TB_PLAYER set plyrTeamPriority = 99 where plyrTeamSeq = %s and plyrSeq = %s", teamSeq, plyrSeq);
        }

        // 데이터 베이스 연결용 클래스를 초기화해준다. 
        DBConnector dc = new DBConnector();
        JSONObject dbJSON = new JSONObject();
        try {
            dbJSON = dc.update(query);
        }
        catch(SQLException e) {
            e.printStackTrace(System.out);
            ret.put("METHOD_RESULT_CD", -1);
            ret.put("METHOD_ERR_MSG", "통신 중 오류가 발생했습니다");
            ret.put("METHOD_ERR_LOCALE_MSG", e.getLocalizedMessage());
        }
        
        ret.put("METHOD_RESULT_DATA", dbJSON.get("affectedRow"));

        return ret;
    
    }
    public JSONObject insertAtTeam (JSONObject params) throws ClassNotFoundException, SQLException{
        JSONObject ret = new JSONObject();
        
        String teamSeq = (String) params.get("teamSeq");
        String name = (String) params.get("name");
        String backNumber = (String) params.get("backNumber");
        String position = (String) params.get("position");
        String query = String.format("INSERT TB_PLAYER (plyrName, plyrPosition,plyrBackNumber, plyrTeamSeq) VALUES ('%s' , %s , %s , %s);", name, position,backNumber,teamSeq);

        // 데이터 베이스 연결용 클래스를 초기화해준다. 
        DBConnector dc = new DBConnector();
        JSONObject dbJSON = new JSONObject();
        try {
            dbJSON = dc.update(query);
        }
        catch(SQLException e) {
            e.printStackTrace(System.out);
            ret.put("METHOD_RESULT_CD", -1);
            ret.put("METHOD_ERR_MSG", "통신 중 오류가 발생했습니다");
            ret.put("METHOD_ERR_LOCALE_MSG", e.getLocalizedMessage());
        }
        
        System.out.println(query);
        
        ret.put("METHOD_RESULT_DATA", dbJSON.get("affectedRow"));

        return ret;
    
    }

}
