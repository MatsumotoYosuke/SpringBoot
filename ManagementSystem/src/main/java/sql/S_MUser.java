package sql;

import java.util.ArrayList;
import java.util.Hashtable;
import common.DatabaseConnection;

/**
 * Loginボタン押下後に呼ばれるclass
 */
public class S_MUser {
	/**
	 * Loginボタン押下後に呼ばれるメソッド
	 * @param list 検索条件
	 * @return ArrayList 検索結果を格納したリスト
	 */
	public static ArrayList<Hashtable<String, String>> LoginUserMaster(ArrayList<String> SearchCriteriaList) {
		// 検索結果のデータ格納
		ArrayList<Hashtable<String, String>> ResultList
		   = new ArrayList<Hashtable<String, String>>();
		// SQL作成
		String StrSql = StrSqlLogin();
		// 検索実行
		ResultList = DatabaseConnection.SqlRun(StrSql,SearchCriteriaList);
		// 検索結果を返す
		return ResultList;
		
	}
	
	/**
	 * Login時の検索用SQL生成メソッド
	 * @return String 検索用SQL
	 */
	private static String StrSqlLogin() {
		
		// Login時に使用するSQL構成
		StringBuilder sb = new StringBuilder();
		sb.append("select ");
		sb.append("  USER_NAME ");
		sb.append("  , USER_ID ");
		sb.append("  , USER_PASS  ");
		sb.append("from ");
		sb.append("  M_USER  ");
		sb.append("where ");
		sb.append("  USER_ID = ?  ");
		sb.append("  and USER_PASS = ? ");
			
		return sb.toString();
	}
}
