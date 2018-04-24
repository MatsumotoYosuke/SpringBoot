package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * DB接続の際、必要となる情報の集合共通class
 */
public class DatabaseConnection {
	/**
	 * DB接続時に使用する共通メソッド
	 * @return Connection　コネクション
	 */
	public static Connection DbConnection() {
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
	 * SQLを実行する共通メソッド
	 * @param strSql 検索時に使用するSQL
	 * @param list SQLのWhere区に渡すパラメータ
	 * @return ResultSet　検索結果
	 */
	public static ResultSet SqlRun(String strSql, ArrayList<String> list) {

		// DBに接続処理
		Connection con = DbConnection();
		String sql = strSql;
		ResultSet rs = null;

		// SQL をプリコンパイル
		PreparedStatement pstmt;
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
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				// 例外処理
				System.err.println(e.getMessage());
			}
		}
		// 検索結果を返す
		return rs;
	}
}
