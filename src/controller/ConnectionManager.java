/*
 * TCSS 360 Trivia Maze
 */

package controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Statement;

import org.sqlite.SQLiteDataSource;

/**
 * Controller for connections to the sqlite database.
 * 
 * @author Zhaoyu Xu
 * @version 2.1
 */
public class ConnectionManager implements Serializable {

	/** The Serialization ID. */
	private static final long serialVersionUID = 1L;

	/** The actual Connection. */
	private static Connection myC = null;

	/** The object for SQLite data source. */
	private SQLiteDataSource ds;

	/**
	 * Constructor. Setup the connection to the database and create a table if not
	 * exists.
	 * 
	 * @param theURL URL to the database file.
	 */
	public ConnectionManager(String theURL) {
		ds = null;
		try {
			ds = new SQLiteDataSource();
			ds.setUrl(theURL);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("Opened datasource successfully");

		String query = "CREATE TABLE IF NOT EXISTS Questions ( " + "ID INTEGER PRIMARY KEY UNIQUE, "
				+ "Type TEXT NOT NULL, " + "Question TEXT NOT NULL, " + "CorrectAnswer TEXT NOT NULL, "
				+ "Hint TEXT NOT NULL, " + "Options TEXT )";

		try {
			myC = ds.getConnection();
			Statement stmt = myC.createStatement();
			stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	/**
	 * Getter for the connection. Checks if the connection is null.
	 * 
	 * @return myC returns the connection to the database.
	 */
	public Connection getConnection() {
		if (myC == null) {
			try {
				Class.forName("org.sqlite.JDBC");
				myC = ds.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return myC;
	}
}
