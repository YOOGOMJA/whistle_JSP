package api;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.*;

import org.json.simple.*;
import org.json.simple.parser.*;

/**
 * Servlet implementation class users
 */
@WebServlet("/API/users")
public class users extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static ReflectionMethod rm;
    private CommonVariable cv;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public users() {
        super();
        // TODO Auto-generated constructor stub
        // rm = new ReflectionMethod(this.getClass().getName());
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
        // response.getWriter().append("Served at: ").append(request.getContextPath());
        // JSONObject o = (JSONObject)
        // JSONSerializer.toJSON(request.getParameter("WHATEVER"));
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
        // doGet(request, response);
        JSONObject jsonObj = rm.callAndGetResult(request.getParameter("fn"), null);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(jsonObj.toJSONString());
    }

    // ================================================================================
    // Sample
    // ================================================================================

    int refMethod(JSONObject params) {
        return 1;
    }

    public JSONObject refMethod2(JSONObject params) {
        JSONObject ret = new JSONObject();

        // System.out.println(params.get("a"));

        long a = (long) params.get("a");
        long b = (long) params.get("b");

        ret.put("ret", (a + b));

        return ret;
    }

    // ================================================================================
    // LOGIN METHODS
    // ================================================================================

    public JSONObject get(JSONObject params) throws ClassNotFoundException, SQLException {

        DBConnector dc = new DBConnector();
        // JSONObject selected = dc.excute("select * from tb_user");
        JSONObject upt = dc.update(
                "insert into TB_User (userMail, userName, userCity, userPw) values('a2@b.com', 'test' , 0, '1234');");
        JSONObject ret = new JSONObject();
        // System.out.println(selected);
        // ret.put("selected" , selected );
        ret.put("result", upt);
        // Class.forName("com.mysql.jdbc.Driver");
        // Connection conn =
        // DriverManager.getConnection("jdbc:mysql://localhost:3306/whistle" , "root",
        // "1111");
        // Statement stmt = conn.createStatement();
        // ResultSet rs = stmt.executeQuery("select * from tb_user");
        // while(rs.next()) {
        // System.out.println(rs.getString(2));
        // }
        // rs.close();
        // stmt.close();
        // conn.close();

        return ret;
    }

}
