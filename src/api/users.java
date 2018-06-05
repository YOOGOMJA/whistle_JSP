package api;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;

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

    private HttpSession _session = null;

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
        // doGet(request, response);
        JSONParser parser = new JSONParser();
        JSONObject prms = null;
        try {
            prms = (JSONObject) parser.parse(request.getParameter("params"));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        _session = request.getSession();

        JSONObject jsonObj = rm.callAndGetResult(request.getParameter("fn"), prms);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(jsonObj.toJSONString());

    }

    // ================================================================================
    // Sample
    // ================================================================================

    /*
     * 예시 함수 
     * 조회를 시행해야 하는 경우.
     * 메소드 리플렉션과 데이터베이스 요청을 포함하기 때문에 예외처리 항목에 
     * ClassNotFoundException / SQLException 항목이 포함되어야 한다. 
     * NOTE : 로그인을 예제로 사용함  
     * */
    public JSONObject mockup_select (JSONObject params) throws ClassNotFoundException, SQLException{
        JSONObject ret = new JSONObject();
           
        
        /*
         * JSONObject params에 아래와 같은 변수들이 들어있는 경우 
         * */
        String mail = (String) params.get("userMail");
        String pw = (String) params.get("userPW");
        
        // 데이터 베이스 요청을 위한 문자열 
        // 잦은 String append는 성능과 가독성에 좋지 않아 String.format을 사용하도록 함 
        String query = String.format("select * from tb_user where userMail = '' and userPw = '' ", mail , pw);
        
        // DB에 연결하기 위함 클래스를 초기화한다.
        // 본 클래스는 util 패키지에 들어있다.
        DBConnector dc = new DBConnector();
        
        // 셀렉트 결과를 가져온다. 
        // 셀렉트시에는 반드시 execute함수를 사용해야 한다.
        // 아래 함수는 insert , update , delete시 사용하지 않도록 조심해야한다.
        // DB 연결 함수들은 항상 try catch문으로 감싸두어야 한다. 오류 검출을 위
        JSONArray db_result = null;
        try {
            db_result = dc.excute(query); 
        }
        catch(SQLException e) {
            // 실제 에러가 발생한 부분을 서버 콘솔상에 출력한다. 
            e.printStackTrace(System.out);
            
            // 실패한 결과에는 반드시 ret JSON에 아래 키들을 포함해야한다. 
            /*
             * METHOD_RESULT_CD         integer             : 결과 코드이다. 실패한 경우 0보다 작은 값을 순서대로 넣는다. 
             * METHOD_ERR_MSG           String              : 요구 사항에 따라 직접 메시지를 삽입.
             * METHOD_ERR_LOCALE_MSG    String              : 예외처리를 통해 오류가 발생한 경우에만 삽입한다. 
             * */
            ret.put("METHOD_RESULT_CD" , -1);
            ret.put("METHOD_ERR_LOCALE_MSG", e.getLocalizedMessage());
        }
        
        // JSONArray 객체는 배열이 아니라 리스트 클래스를 상속받는다.
        // 배열처럼 사용하기 위해서는 먼저 변환해주어야 한다. 
        Object[] items = db_result.toArray();
        
        if(items.length > 0) {
            // 하나 이상 값이 조회되었을 경우 1보다 갯수가 크다. 
            // 로그인이 성공한 것으로 판단하고 데이터를 돌려보낸다.
            
            // 성공한 결과에는 반드시 ret JSON에 아래 키들을 포함해야한다. 
            /*
             * METHOD_RESULT_CD     integer                 : 결과 코드이다. 성공한 경우 1을 출력한다. 
             *                                              만약 실패 한다면 0보다 작은 값으로 코드를 변경한다. (ex : -1)
             * METHOD_RESULT_DATA   JSONObject/JSONArray    : 조회된 결과 데이터이다. JSONObject 혹은 JSONArray를 넣어주도록 한다.
             * */
            ret.put("METHOD_RESULT_CD", 1);
            ret.put("METHOD_RESULT_DATA", db_result);
        }
        else {
            // 로그인이 실패한 경우이다. 
            // 이 경우에는 직접 메시지를 넣어주면된다. 서버 오류로 인한 예외가 아니라 사용자 입력에 따른 예외기 떄
            ret.put("METHOD_RESULT_CD" , -2);
            ret.put("METHOD_ERR_MSG", "아이디 혹은 비밀번호를 확인해주세요");   
        }
        
        
        return ret;
    }
    
    /*
     * 예시 함수 
     * 업데이트를 시행해야 하는 경우.
     * 메소드 리플렉션과 데이터베이스 요청을 포함하기 때문에 예외처리 항목에 
     * ClassNotFoundException / SQLException 항목이 포함되어야 한다. 
     * NOTE : 사용자 이름 변경하는 예제. 아래의 내용은 insert / update / delete에 공통으로 사용 
     * */
    public JSONObject mockup_update(JSONObject params) throws ClassNotFoundException, SQLException{
        JSONObject ret = new JSONObject();
        // Db와 통신 후 확인할 객체이다. 
        JSONObject dbJSON = null;
       
        // 사용할 값을 가져온다. 
        String userName = (String) params.get("userName");
        int userSeq = (int) params.get("userSeq");
        
        // 항목이 null 혹은 공백문자가 아닌지 확인 
        // 일부러 비워두도록 업데이트하는것이 아니라면 항상 확인해야한다. 
        // insert / update / delete 항목들은 되도록 엄격하게 유효성 검사를 거쳐야 한다. 
        if(userName == null || userName.isEmpty()) {
            ret.put("METHOD_RESULT_CD", -1);
            ret.put("METHOD_ERR_MSG", "이름을 입력해주세요");
        }
        else {
            // 업데이트에 필요한 쿼리문을 작성 
            String query = String.format("update TB_User set userName = '%s' where userSeq = '%s'", userName , userSeq);
            
            // 데이터 베이스 연결용 클래스를 초기화해준다. 
            DBConnector dc = new DBConnector();
            
            try {
                // 위와 동일하다 
                // insert / update / delete를 사용할때는 아래 함수를 사용하도록 한다. 
                // 아래 함수는 오류가 발생하면 다시 디비를 이전 상태로 복구한다. 
                dbJSON = dc.update(query);
            }
            catch(SQLException e) {
                e.printStackTrace(System.out);
                ret.put("METHOD_RESULT_CD", -1);
                ret.put("METHOD_ERR_MSG", "통신 중 오류가 발생했습니다");
                ret.put("METHOD_ERR_LOCALE_MSG", e.getLocalizedMessage());
            }
            
            // 실제 결과를 담아둔다. 
            // 이 데이터에는 실제로 영향을 받은 데이터들의 row count가 들어간다. 
            // 한건의 업데이트만 발생하면 1만 넘어간다. 아무것도 영향받지 못하면 0이 넘어간다. 
            ret.put("METHOD_RESULT_DATA", dbJSON.get("affectedRow"));
        }
        
        return ret;
    }

    // ================================================================================
    // LOGIN METHODS
    // ================================================================================

    /*
     * API, 회원가입용 함수. SNS 계정과 상관없음
     * 
     */
    public JSONObject signup(JSONObject params) throws ClassNotFoundException, SQLException {
        JSONObject ret = new JSONObject();
        JSONObject dbJSON = null;
        int ret_cd = 1;
        String err_msg = "";
        
        // # Properties
        // userName
        // userMail
        // userPW
        // userPW2
        // userCity : optional
        String userMail = (String) params.get("userMail");
        String userName = (String) params.get("userName");
        String userPW = (String) params.get("userPW");
        String userPW2 = (String) params.get("userPW2");
        long userCity = (long) params.get("userCity");

        if (userMail == null || userMail.isEmpty()) {
            ret_cd = -1;
            err_msg = "이메일을 입력해주세요";
        } else if (userName == null || userName.isEmpty()) {
            ret_cd = -2;
            err_msg = "이름을 입력해주세요";
        } else if (userPW == null || userPW.isEmpty()) {
            ret_cd = -3;
            err_msg = "비밀번호를 입력해주세요";
        } else if (userPW2 == null || userPW2.isEmpty()) {
            ret_cd = -4;
            err_msg = "확인 비밀번호를 입력해주세요";
        } else if (!userPW.equals(userPW2)) {
            ret_cd = -5;
            err_msg = "비밀번호가 일치하지 않습니다";
        }
        else {
            // 1. 이미 계정이 존재하는지 확인
            DBConnector dc = new DBConnector();
            
            String query_valid = String.format("select userSeq from tb_user where userMail = '%s' ", userMail);
            JSONArray jsonSelected = null;
            
            try {
                jsonSelected = dc.excute(query_valid);
            }
            catch(SQLException e) { 
                e.printStackTrace(System.out); 
                ret_cd = -12;
                err_msg = e.getLocalizedMessage();
            }
            

            if(jsonSelected.toArray().length > 0) {
                ret_cd = -6;
                err_msg = "이미 존재하는 이메일입니다.";
                
            }
            else {
                String query = "";
                query += "insert into TB_User (userMail, userName, userPW, userCity) ";
                query += "values (";
                query += String.format("'%s' , '%s' , '%s' , %d", userMail, userName, userPW, userCity);
                query += ")";
                
                try {
                    dbJSON = dc.update(query);        
                }
                catch(ClassNotFoundException e) { 
                    e.printStackTrace(System.out);
                    ret_cd = -10;
                    err_msg = e.getLocalizedMessage();
                }
                catch(SQLException e) { 
                    e.printStackTrace(System.out);
                    ret_cd = -11;
                    err_msg = e.getLocalizedMessage();
                }
                
                ret.put("METHOD_RESULT_DATA", dbJSON.get("affectedRow"));
            }
        }
                
        ret.put("METHOD_RESULT_CD", ret_cd);
        if (ret_cd <= 0) {
            ret.put("METHOD_ERR_MSG", err_msg);
        }
        
        return ret;
    }
    
    /*
     * API, 로그인 함수 
     * */
    public JSONObject login(JSONObject params) throws ClassNotFoundException, SQLException {
        JSONObject ret = new JSONObject();
        
        String userMail = (String) params.get("userMail");
        String userPW = (String) params.get("userPW");
        String query = "";
        query += "select * from tb_user ";
        query += String.format("where userMail = '%s' and userPw = '%s'", userMail, userPW);
        
        System.out.println(query);
        DBConnector conn = new DBConnector();
        JSONArray dbjson = null;
        try {
            dbjson = conn.excute(query);
        }
        catch(ClassNotFoundException e) { e.printStackTrace(System.out); }
        catch(SQLException e) { e.printStackTrace(System.out); }
        
        System.out.println(dbjson);

        ret.put("METHOD_RESULT_CD", dbjson.toArray().length);
        ret.put("METHOD_RESULT_DATA", dbjson);
        
        if(dbjson.toArray().length > 0) {
            Object[] js = dbjson.toArray();
            JSONObject item = (JSONObject) js[0];
            _session.setAttribute("userSeq", item.get("userSeq"));
            _session.setAttribute("userMail", item.get("userMail"));
        }

        return ret;
    }

    /*
     * API , 로그아웃 함수 
     * */
    public JSONObject logout (JSONObject params) throws ClassNotFoundException, SQLException{
        JSONObject ret = new JSONObject();
        
        _session.removeAttribute("userSeq");
        _session.removeAttribute("userMail");
        
        ret.put("METHOD_RESULT_CD", 1);
        
        return ret;
    }
}
