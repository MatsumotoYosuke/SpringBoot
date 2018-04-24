package sql;


import java.sql.ResultSet;
import java.util.ArrayList;
import common.DatabaseConnection;

public class S_User_Master {
	public static ResultSet LoginUserMaster(ArrayList<String> list) {
		
		String StrSql = StrSqlLogin();
		ResultSet rs = null;
		rs = DatabaseConnection.SqlRun(StrSql,list);
		return rs;
	}
	
	public static String StrSqlLogin() {
		
		// Login時に使用するSQL構成
		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		sb.append("  USER_NAME ");
		sb.append("  , USER_ID ");
		sb.append("  , USER_PASS  ");
		sb.append("from ");
		sb.append("  USER_MASTER  ");
		sb.append("where ");
		sb.append("  USER_ID = ?  ");
		sb.append("  and USER_PASS = ? ");
			
		return sb.toString();
	}
}
