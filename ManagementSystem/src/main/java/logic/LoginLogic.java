package logic;

import java.util.ArrayList;

import common.ConstGlobal;

public class LoginLogic {
	public static ArrayList<String> DetermineLogin(String id,String pass) {
		
		ArrayList<String> list = new ArrayList<String>();
		int flag;
		
		// DBに接続処理
		
		
		
		// 判定処理
//		if (id != pass) {
//			flag = 1;
//		} else {
//			flag = 0;
//		}
		
		list.add(ConstGlobal.gstrMain);
		list.add(ConstGlobal.gstrMainScreen);
		return list;
	}
}
