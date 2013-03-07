package com.scottmcmaster365.weatherapp.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// TODO: Make this hit a database on the disk.
public class DatabaseConnectionManager {

	public Connection getConnection() throws SQLException {
		String url = "jdbc:hsqldb:mem:weatherusers";
		String user = "sa";
		String password = "";
		Connection conn = DriverManager.getConnection(url, user, password);
		initDatabase(conn);
		return conn;
	}

	private void initDatabase(Connection conn) throws SQLException {
		Statement dropTable = conn.createStatement();
		dropTable.executeUpdate("DROP TABLE Users IF EXISTS");
		Statement createTable = conn.createStatement();
		createTable.executeUpdate("CREATE TABLE Users (name VARCHAR(265), city VARCHAR(256), country VARCHAR(256))");
		Statement insert1 = conn.createStatement();
		insert1.executeUpdate("INSERT INTO Users (name, city, country) VALUES ('scott', 'beijing', 'china')");
		Statement insert2 = conn.createStatement();
		insert2.executeUpdate("INSERT INTO Users (name, city, country) VALUES ('marissa', 'seattle', 'united states')");
	}
}
