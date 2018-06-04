package util;

public class CommonVariable {
	private static CommonVariable _instance = new CommonVariable();
	private String jdbcDriverClassName;
	private String jdbcConnectionString;
	private String jdbcUserAccount;
	private String jdbcUserPassword;
	
	public enum CV_DB_VARIABLE {
		DRIVER_NAME,
		CONNECTION_STRING,
		USER_ACCOUNT,
		USER_PASSWORD
	}
	
	private CommonVariable() {
		jdbcDriverClassName = "com.mysql.jdbc.Driver";
		jdbcConnectionString = "jdbc:mysql://localhost:3306/whistle";
		jdbcUserAccount = "root";
		jdbcUserPassword = "1111";
	}
	
	public static synchronized CommonVariable getInstance() {
		return _instance;
	}
	
	public String getDBInfo(CV_DB_VARIABLE type) {
		String ret;
		switch(type) {
			case DRIVER_NAME :
				ret = jdbcDriverClassName;
			break;
			case CONNECTION_STRING:
				ret = jdbcConnectionString;
			break;
			case USER_ACCOUNT:
				ret = jdbcUserAccount;
			break;
			case USER_PASSWORD:
				ret = jdbcUserPassword;
			break;
			default : 
				ret = "";
			break;
		}
		
		return ret;
	}
}