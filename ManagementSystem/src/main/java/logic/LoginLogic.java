package logic;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import common.ConstGlobal;
import sql.S_MUser;

/**
 * Loginボタン押下後に呼ばれるclass
 */
public class LoginLogic {
	
	/**
	 * Loginボタン押下後に呼ばれるメソッド<br>
	 * 画面遷移の制御
	 * @param id ユーザID
	 * @param pass ユーザパスワード
	 * @return ArrayList 画面遷移情報
	 */
	public static ArrayList<String> DetermineLogin(String id,String pass) {
		
		// SQL検索条件を格納するリスト
		ArrayList<String> listValue = new ArrayList<String>();
		// 画面遷移先を格納するリスト
		ArrayList<String> returnList = new ArrayList<String>();
		// 画面遷移情報
		boolean ScreenFlag = false;
		
		try {
			// 検索項目をlistValueに格納
			listValue.add(id);
			listValue.add(pass);
			
			// 検索条件を引数に渡す
			ArrayList<Hashtable<String, String>> ResultList = 
					S_MUser.LoginUserMaster(listValue);

			// データの存在チェック
			for (Hashtable<String, String> list : ResultList) {
				
				// データindex件目のフィールド名リストを取得
				Enumeration<String> keyList = list.keys();
				while (keyList.hasMoreElements()) {
					// フィールド名取得
					String key = (String) keyList.nextElement();
					// データ出力
					System.out.println(key + ":" + list.get(key));
					// データが検索出来た為、flagを１に変更
					ScreenFlag = true;
				}
			}
		
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		if(ScreenFlag) {
			// メニュー画面へ遷移
			returnList.add(ConstGlobal.gstrMain);
			// 表示メッセージ
			returnList.add(ConstGlobal.gstrMainScreen);
		} else {
			// ログイン画面へ遷移
			returnList.add(ConstGlobal.gstrLogin);
			// 表示メッセージ
			returnList.add(ConstGlobal.gstrLoginScreen);
		}
		// ログインフラグ
		returnList.add(String.valueOf(ScreenFlag));
		return returnList;
	}
}
