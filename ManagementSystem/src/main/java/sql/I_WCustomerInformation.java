package sql;

import java.util.ArrayList;
import common.DatabaseConnection;

/**
 * Loginボタン押下後に呼ばれるclass
 */
public class I_WCustomerInformation {
	/**
	 * Loginボタン押下後に呼ばれるメソッド
	 * @param list 検索条件
	 * @return ArrayList 検索結果を格納したリスト
	 */
	public static void RegisterCustomerInformation(ArrayList<String> SearchCriteriaList) {
		// SQL作成
		String StrSql = StrSqlRegister();
		// 登録実行
		DatabaseConnection.InsertSqlRun(StrSql,SearchCriteriaList);
	}
	
	/**
	 * Login時の検索用SQL生成メソッド
	 * @return String 検索用SQL
	 */
	private static String StrSqlRegister() {
		
		// Login時に使用するSQL構成
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT  ");
		sb.append("INTO W_CUSTOMER_INFORMATION(  ");
		sb.append("  customer_name ");
		sb.append("  , customer_name_kana ");
		sb.append("  , customer_no ");
		sb.append("  , postal_code ");
		sb.append("  , street_address ");
		sb.append("  , street_address_kana ");
		sb.append("  , insert_ymd ");
		sb.append("  , update_ymd ");
		sb.append("  , update_count ");
		sb.append("  , delete_flag ");
		sb.append(")  ");
		sb.append("VALUES (  ");
		sb.append("  ? ");
		sb.append("  , ? ");
		sb.append("  , ? ");
		sb.append("  , ? ");
		sb.append("  , ? ");
		sb.append("  , ? ");
		sb.append("  , sysdate ");
		sb.append("  , null ");
		sb.append("  , '0' ");
		sb.append("  , '0' ");
		sb.append(")  ");
			
		return sb.toString();
	}
}
