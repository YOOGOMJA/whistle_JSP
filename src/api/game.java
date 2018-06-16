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
@WebServlet("/API/game")
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
    
    public JSONObject insert (JSONObject params) throws ClassNotFoundException , SQLException{
        JSONObject ret = new JSONObject();
        
        String userSeq = (String) params.get("userSeq");
        String homeTeamSeq = (String) params.get("homeTeamSeq");
        
        String query = String.format("insert tb_game (homeTeamSeq , awayTeamSeq , gameCreateUserSeq) values(%s, NULL, %s);", userSeq, homeTeamSeq);
        
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
    
    public JSONObject getCurrentGame(JSONObject params) throws ClassNotFoundException, SQLException{
        JSONObject ret = new JSONObject();
        
        String userSeq = (String) params.get("userSeq");
        
        DBConnector dc = new DBConnector();
        JSONArray dbJSON = new JSONArray();
        
        String query = String.format("select * from tb_game where gameState = 1 and gameCreateUserSeq = %s", userSeq);
        
        try {
            dbJSON = dc.excute(query);
        }
        catch(SQLException e) {
            e.printStackTrace(System.out);
            ret.put("METHOD_RESULT_CD", -1);
            ret.put("METHOD_ERR_MSG", "통신 중 오류가 발생했습니다");
            ret.put("METHOD_ERR_LOCALE_MSG", e.getLocalizedMessage());
        }
        
        ret.put("METHOD_RESULT_DATA", dbJSON);
        
        return ret;
    }
    
    public JSONObject insertTrack(JSONObject params) throws ClassNotFoundException, SQLException {
        JSONObject ret = new JSONObject();
        
//        insert TB_GameTrack(trkScored, trkPlayerSeq, trkGameSeq, trkIsHome) values( 2, 1,  1, 1);
        String userSeq = (String) params.get("userSeq");
        String plyrSeq = (String) params.get("plyrSeq");
        String isHome = (String) params.get("trkIsHome");
        String gameSeq = (String) params.get("gameSeq");
        
        String query = String.format("insert TB_GameTrack(trkScored, trkPlayerSeq, trkGameSeq, trkIsHome) values(%s, %s, %s, %s)", userSeq, plyrSeq, isHome, gameSeq);
        
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
        
        ret.put("METHOD_RESULT_DATA", dbJSON);
        
        return ret;
    }
    
    public JSONObject getScore(JSONObject params) throws ClassNotFoundException, SQLException{
        JSONObject ret = new JSONObject();
        
        String gameSeq = (String) params.get("userSeq");
        
        String query = "select gameSeq, gameStartedDT, SUM(trkScored) as score, trkIsHome ";
        query += "from tb_game as g, tb_gametrack as t ";
        query += String.format("where g.gameSeq = t.trkGameSeq and g.gameSeq = %s ", gameSeq);
        query += "group by trkIsHome, gameSeq, gameStartedDT ORDER BY trkIsHome DESC";
        
        DBConnector dc = new DBConnector();
        JSONArray dbJSON = new JSONArray();
        
        try {
            dbJSON = dc.excute(query);
        }
        catch(SQLException e) {
            e.printStackTrace(System.out);
            ret.put("METHOD_RESULT_CD", -1);
            ret.put("METHOD_ERR_MSG", "통신 중 오류가 발생했습니다");
            ret.put("METHOD_ERR_LOCALE_MSG", e.getLocalizedMessage());
        }
        
        ret.put("METHOD_RESULT_DATA", dbJSON);
        
        return ret;
    }
}
