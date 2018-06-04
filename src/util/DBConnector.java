package util;

import java.sql.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class DBConnector {
	private CommonVariable _cv;
	
	public DBConnector() 
	{
		super();
		_cv = CommonVariable.getInstance();
	}
	
	private Connection connect() throws ClassNotFoundException, SQLException {
		Class.forName(_cv.getDBInfo(CommonVariable.CV_DB_VARIABLE.DRIVER_NAME));
		String sConn = _cv.getDBInfo(CommonVariable.CV_DB_VARIABLE.CONNECTION_STRING);
		String sAcc = _cv.getDBInfo(CommonVariable.CV_DB_VARIABLE.USER_ACCOUNT);
		String sPw = _cv.getDBInfo(CommonVariable.CV_DB_VARIABLE.USER_PASSWORD);
		Connection conn = DriverManager.getConnection(sConn, sAcc, sPw);
		return conn;
	}
	
	public JSONObject update(String query) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Statement stmt = null;
		int affectedRowCnt= 0;
		JSONObject ret = new JSONObject();
		try {
			conn = connect();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			affectedRowCnt = stmt.executeUpdate(query);
			
			conn.commit();
		}
		catch(SQLException e) {
			conn.rollback();
		}
		finally {
			if(stmt != null) try { stmt.close(); } catch(SQLException e){ e.printStackTrace(); }
			if(conn != null) try { conn.close(); } catch(SQLException e){ e.printStackTrace(); }
			
			ret.put("affectedRow", affectedRowCnt);
		}
		
		return ret;
	}
	
	public JSONObject excute(String query) throws ClassNotFoundException, SQLException {
		Connection conn = connect();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		
		JSONObject result = new JSONObject();
		int colCnt = rs.getMetaData().getColumnCount();
		while(rs.next()) {
			JSONObject item = new JSONObject();
			for(int i = 1; i < colCnt; i++) {
				item.put(rs.getMetaData().getColumnName(i), rs.getString(i+1));
				
			}
			result.put(rs.getString(1),item);
		}
		
		if(stmt != null) try { stmt.close(); } catch(SQLException e){ e.printStackTrace(); }
		if(conn != null) try { conn.close(); } catch(SQLException e){ e.printStackTrace(); }
		if(rs != null) try { rs.close(); } catch(SQLException e){ e.printStackTrace(); }
		
		return result;
	}
}
