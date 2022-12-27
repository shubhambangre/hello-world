/**********************************************************************************************************************
** Copyright 2017-18 RnT.AI. All rights reserved.
**
** No Part of this file should be copied or distributed without the permission of RnT.AI.
** Application : LEAVE MANAGEMENT SYSTEM
** Module : LMS.war
** Version : 1.0
** File : DBConnect.java
** Description : The java Class DBConnect is a class that creates a connection with the underlying database.
** Author : Jayesh Patil
** Created Date : Friday October 06, 2017
**********************************************************************************************************************
** Change History Header:
**********************************************************************************************************************
** Date Author Version Description:
** ------- -------- -------- ------------
** 06/10/2017 Jayesh Patil 1.0 Created
*********************************************************************************************************************/
package ai.rnt.main.dao;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.CommonDataSource;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import ai.rnt.rms.app.config.PropertyListner;

public class DBConnect {

	public static DataSource dataSource = null;
	//private static final Logger log = LogManager.getLogger(DBConnect.class);

	public static CommonDataSource getDataSource() throws PropertyVetoException {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		
		PropertyListner propertyListner = new PropertyListner();
		Properties properties = propertyListner.getProperties();
		cpds.setDriverClass(properties.getProperty("mysql.driver"));
		  cpds.setJdbcUrl(properties.getProperty("mysql.url")); 
		  cpds.setUser(properties.getProperty("mysql.user")); 
		  cpds.setPassword(properties.getProperty("mysql.password"));
		 
		 
		/*
		 * cpds.setJdbcUrl("jdbc:mysql://localhost:3306/rntdbuat");
		 * cpds.setUser("root"); cpds.setPassword("root");
		 */
	
		
			/*
			 * cpds.setJdbcUrl(
			 * "jdbc:mysql://rntdbuatprod.cywt4ngax3hu.us-east-2.rds.amazonaws.com:3306/rntproddb"
			 * ); cpds.setUser("rnt_user_prod"); cpds.setPassword("pr0du$erpwd");
			 */
	 
		// Optional Settings
		cpds.setInitialPoolSize(10);
		cpds.setMinPoolSize(5);
		cpds.setAcquireIncrement(5);
		cpds.setMaxPoolSize(20);
		cpds.setMaxStatements(100);
		cpds.setMaxIdleTime(2);
		dataSource = cpds;
		return cpds;

	}

	public static Connection getConnection() throws SQLException, PropertyVetoException {
		// ComboPooledDataSource dataSource=(ComboPooledDataSource)
		// DBConnect.getDataSource();

		if (dataSource == null) {
			//dataSource = (ComboPooledDataSource) DBConnect.getDataSource();
			dataSource = DBConnect.getDataSoruceFromContext();
		}
		Connection connection = dataSource.getConnection();
		connection.setAutoCommit(true);
		return connection;
	}
	private static DataSource getDataSoruceFromContext() {
		DataSource dataSource2 = null;
		Context contextoInicial = null;
		try {
			contextoInicial = new InitialContext();
			Context contexto = (Context) contextoInicial.lookup("java:comp/env");
			dataSource2 = (DataSource) contexto.lookup("rnt_uat_mysql_db");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return dataSource2;
	}

	/*
	 * public static Connection getConnection() throws SQLServerException {
	 *
	 * SQLServerDataSource ds = new SQLServerDataSource();
	 * ds.setUser("masterrntuser"); ds.setPassword("rnt27012345");
	 * ds.setServerName("rntdbinstance.cywt4ngax3hu.us-east-2.rds.amazonaws.com");
	 * ds.setPortNumber(1433); ds.setDatabaseName("test_db");
	 *
	 * Connection connection2 = ds.getConnection(); return connection2; }
	 */
}