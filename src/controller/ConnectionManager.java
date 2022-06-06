package controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Statement;

import org.sqlite.SQLiteDataSource;

public class ConnectionManager implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Connection myC = null;
	
	private SQLiteDataSource ds;
	
	public ConnectionManager(String theURL) {
		ds = null;
		try {
            ds = new SQLiteDataSource();
            ds.setUrl(theURL);
        } catch (Exception e) {
            System.out.println("In First DataSource connection: " + e.getMessage());
            System.exit(0);
        }
        System.out.println( "Opened datasource successfully" );

        String query = "CREATE TABLE IF NOT EXISTS Questions ( " +
                         "ID INTEGER PRIMARY KEY UNIQUE, " +
                         "Type TEXT NOT NULL, " + 
                         "Question TEXT NOT NULL, " +
                         "CorrectAnswer TEXT NOT NULL, " +
                         "Hint TEXT NOT NULL, " + 
                         "Options TEXT )";

        try {
        	myC = ds.getConnection();
            Statement stmt = myC.createStatement(); 
            stmt.executeUpdate( query );
        } catch (Exception e) {
        	System.out.println("In create table: " + e.getMessage());
            System.exit( 0 );
        }
	}
	
	public Connection getConnection() {
		if (myC == null) {
			try {
				Class.forName("org.sqlite.JDBC");
				myC = ds.getConnection();
			} catch (Exception e) {
				System.out.println("In ConnectionManager getConnection: " + e.getMessage());
			}
		}
		return myC;
	}
}
