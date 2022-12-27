/**
 * 
 */
package ai.rnt.main.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Keshav Varma
 *
 */
public class DBConnectionHandlerUtil {

	private DBConnectionHandlerUtil() {
		// nothing do to here
	}

	public static void closeDatabaseResource(ResultSet rs, Statement statement, Connection connection) {
		closeResource(rs, statement, connection);
	}
	
	private static void closeResource(ResultSet rs, Statement statement, Connection connection) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// Nothing to do here
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// Nothing to do here
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// Nothing to do here
			}
		}
	}

	public static void closeDatabaseResource(Statement statement, Connection connection) {
		closeDatabaseResource(null, statement, connection);
	}

	public static void closeDatabaseResource(Connection connection) {
		closeDatabaseResource(null, null, connection);
	}
}
