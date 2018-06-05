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
    
    /*
     * API, 회원가입용 함수. SNS 계정과 상관없음 
     * 
     * */
    public JSONObject signup (JSONObject params) throws ClassNotFoundException, SQLException {
        JSONObject ret = new JSONObject();
        int ret_cd = 1;
        String err_msg = "";
        
        // # Properties 
        // userName
        // userMail
        // userPW
        // userPW2
        // userCity : optional
        String userMail = (String)params.get("userMail");
        String userName = (String)params.get("userName");
        String userPW = (String) params.get("userPW");
        String userPW2 = (String) params.get("userPW2");
        long userCity = (long) params.get("userCity");
        
        if(userMail == null || userMail.isEmpty()) { 
            ret_cd = 0;
            err_msg = "이메일을 입력해주세요";
        }
        else if(userName == null || userName.isEmpty()) {
            ret_cd = 0;
            err_msg = "이름을 입력해주세요";
        }
        else if(userPW == null || userPW.isEmpty()) {
            ret_cd = 0;
            err_msg = "비밀번호를 입력해주세요";
        }
        else if(userPW2 == null || userPW2.isEmpty()) {
            ret_cd = 0;
            err_msg = "확인 비밀번호를 입력해주세요";
        }
        else if(userPW.equals(userPW2)) {
            ret_cd = 0;
            err_msg = "비밀번호가 일치하지 않습니다";
        }

        String query = "";
        query += "insert into TB_User (userMail, userName, userPW, userCity) ";
        query += "values (";
        query += String.format("'%s' , '%s' , '%s' , %d", userMail, userName, userPW, userCity);
        query += ")";
        
        DBConnector dc = new DBConnector();
        JSONObject dbJSON = dc.update(query);
        
        ret.put("METHOD_RESULT_CD", ret_cd);
        if(ret_cd <= 0) { ret.put("METHOD_ERR_MSG" , err_msg);}
        ret.put("METHOD_RESULT_DATA", dbJSON.get("affectedRow"));
        // hello
        return ret;
    }
    
    public JSONObject signupWithSNS(JSONObject params) throws ClassNotFoundException, SQLException{
        JSONObject ret = new JSONObject();
        
        return ret;
    }
}
