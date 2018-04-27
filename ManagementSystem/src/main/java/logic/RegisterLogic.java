package logic;

import java.util.ArrayList;
import sql.I_WCustomerInformation;

/**
 * Loginボタン押下後に呼ばれるclass
 */
public class RegisterLogic {
	
	/**
	 * 登録ボタン押下後に呼ばれるメソッド<br>
	 * 画面遷移の制御
	 * @param id ユーザID
	 * @param pass ユーザパスワード
	 * @return ArrayList 画面遷移情報
	 */
	public static ArrayList<String> DetermineRegister(ArrayList<String> list) {
		
		// SQL検索条件を格納するリスト
		ArrayList<String> listValue = list;
		// 画面遷移先を格納するリスト
		ArrayList<String> returnList = new ArrayList<String>();
		
		try {
			// 検索条件を引数に渡す
			I_WCustomerInformation.RegisterCustomerInformation(listValue);
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return returnList;
	}
}
