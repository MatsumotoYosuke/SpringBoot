package sql;


import java.sql.ResultSet;
import java.util.ArrayList;
import common.DatabaseConnection;

/**
 * Loginボタン押下後に呼ばれるclass
 */
public class S_User_Master {
	/**
	 * Loginボタン押下後に呼ばれるメソッド
	 * @param list 検索条件
	 * @return ResultSet 検索結果
	 */
	public static ResultSet LoginUserMaster(ArrayList<String> list) {
		
		// SQL作成
		String StrSql = StrSqlLogin();
		// 検索実行
		ResultSet rs = DatabaseConnection.SqlRun(StrSql,list);
		// 検索結果を返す
		return rs;
	}
	
	/**
	 * Login時の検索用SQL生成メソッド
	 * @return String 検索用SQL
	 */
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
