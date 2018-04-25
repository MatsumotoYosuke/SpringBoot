package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
/**
 * DB接続の際、必要となる情報の集合共通class
 */
public class DatabaseConnection {
	/**
	 * DB接続時に使用する共通メソッド
	 * @return Connection　コネクション
	 */
	private static Connection DbConnection() {
		Connection con = null;
		// DBに接続処理
		try {
			// JDBCドライバのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 接続するデータベースの指定
			// 引数の1つ目はデータベースのURL、２つ目がユーザ名、３つ目がパスワード
			con = DriverManager.getConnection("jdbc:oracle:oci8:@" + ConstGlobal.ｇｓｔｒDbHostName,
					ConstGlobal.ｇｓｔｒDbUserName, ConstGlobal.ｇｓｔｒDbUserPass);

			// エラー処理
		} catch (Exception e) {

		}
		// コネクションを返す
		return con;
	}
	
	/**
	 * SQL（select）を実行する共通メソッド
	 * 
	 * @param strSql
	 *            検索時に使用するSQL
	 * @param list
	 *            SQLのWhere区に渡すパラメータ
	 * @return ArrayList<Hashtable<String, String>> 検索結果を格納したリスト
	 */
	public static ArrayList<Hashtable<String, String>> SelectSqlRun(String strSql, ArrayList<String> list) {

		// コネクション取得
		Connection con = DbConnection();
		// 実行するSQL取得
		String sql = strSql;
		// 検索結果を格納する変数
		ResultSet rs = null;
		// フィールド名を格納する変数
		ResultSetMetaData rsmd = null;
		//データ格納
		ArrayList<Hashtable<String, String>> returnList
		   = new ArrayList<Hashtable<String, String>>();

		// SQL をプリコンパイル
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);

			// SQLに引数を入れる
			int index = 1;
			for (String Value : list) {
				pstmt.setString(index++, Value);
			}

			// SQL 実行
			rs = pstmt.executeQuery();
			// フィールド名取得
			rsmd = rs.getMetaData();
			
			while (rs.next()) {
				// 1件分のデータ(連想配列)
				Hashtable<String, String> hdata = new Hashtable<String, String>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					// フィールド名
					String field = rsmd.getColumnName(i);
					// フィールド名に対するデータ
					String getdata = rs.getString(field);
					if (getdata == null) {
						getdata = "";
					}
					// データ格納(フィールド名, データ)
					hdata.put(field, getdata);
				}
				// 1件分のデータを格納
				returnList.add(hdata);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 接続
				if (con != null) {
					// 接続をクローズ
					con.close();
				}
				// 接続
				if (rs != null) {
					// 結果セットをクローズ
					rs.close();
				}
				// ステートメントをクローズ
				if (pstmt != null) {
					// 結果セットをクローズ
					pstmt.close();
				}

			} catch (SQLException e) {
				// 例外処理
			}
		}
		// 検索結果を返す
		return returnList;
	}
	
	/**
	 * SQL（insert）を実行する共通メソッド
	 * 
	 * @param strSql
	 *            登録時に使用するSQL
	 * @param list
	 *            SQLのバインド値に渡すパラメータ
	 */
	public static void InsertSqlRun(String strSql, ArrayList<String> list) {

		// コネクション取得
		Connection con = DbConnection();
		// 実行するSQL取得
		String sql = strSql;
		// 検索結果を格納する変数
		ResultSet rs = null;
		// SQL をプリコンパイル
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);

			// SQLに引数を入れる
			int index = 1;
			for (String Value : list) {
				pstmt.setString(index++, Value);
			}

			// SQL 実行
			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 接続
				if (con != null) {
					// 接続をクローズ
					con.close();
				}
				// 接続
				if (rs != null) {
					// 結果セットをクローズ
					rs.close();
				}
				// ステートメントをクローズ
				if (pstmt != null) {
					// 結果セットをクローズ
					pstmt.close();
				}

			} catch (SQLException e) {
				// 例外処理
			}
		}
	}
}
