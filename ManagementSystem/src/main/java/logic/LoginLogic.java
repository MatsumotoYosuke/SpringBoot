package logic;

import java.sql.ResultSet;
import java.util.ArrayList;
import common.ConstGlobal;
import sql.S_User_Master;

public class LoginLogic {
	public static ArrayList<String> DetermineLogin(String id,String pass) {
		
		// SQL検索条件を格納するリスト
		ArrayList<String> listValue = new ArrayList<String>();
		// 画面遷移先を格納するリスト
		ArrayList<String> list = new ArrayList<String>();
		// 画面遷移情報
		int ScreenFlag = 0;
		
		try {
			// 検索項目をlistValueに格納
			listValue.add(id);
			listValue.add(pass);
			
			// 検索条件を引数に渡す
			ResultSet result = S_User_Master.LoginUserMaster(listValue);

			// データの存在チェック
			while (result.next()) {
				System.out.println(result.getString("USER_NAME") + "," + result.getString("USER_ID") + ","
						+ result.getString("USER_PASS"));
				
				// データが検索出来た為、flagを１に変更
				ScreenFlag = 1;
			}
		
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		if(ScreenFlag == 1) {
			// メニュー画面へ遷移
			list.add(ConstGlobal.gstrMain);
			list.add(ConstGlobal.gstrMainScreen);
		} else {
			// ログイン画面へ遷移
			list.add(ConstGlobal.gstrLogin);
			list.add(ConstGlobal.gstrLoginScreen); // 表示メッセージ

		}
		return list;
	}
}
