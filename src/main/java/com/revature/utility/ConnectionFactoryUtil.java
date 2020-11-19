package com.revature.utility;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionFactoryUtil {
	private static Connection conn;

	public static Connection getConnection() {
		try {
			InitialContext cxt = new InitialContext();
			if (cxt == null) {
				throw new Exception("Uh oh -- no context!");
			}

			DataSource ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/postgres");

			if (ds == null) {
				throw new Exception("Data source not found!");
			}
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;

	}
}