package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * 資料庫連線
 * @author shawn
 *
 */
public class Conn {
	
	/**
	 * 結束連線
	 * @param ps
	 * @param con
	 * @throws java.sql.SQLException
	 */
	public static void DisConDB(PreparedStatement ps, Connection con) throws java.sql.SQLException {
		if (ps != null)ps.close();
		if (con != null)con.close();
		ps = null;
		con = null;
	}
	
	/**
	 * 開啟連線
	 * @param uri
	 * @param user
	 * @param pass
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection(String uri, String user, String pass) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {

		}
		Connection con = java.sql.DriverManager.getConnection(uri, user, pass);
		return con;
	}

}
