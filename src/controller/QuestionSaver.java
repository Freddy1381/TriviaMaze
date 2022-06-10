/*
 * TCSS 360 Trivia Maze
 */

package controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import res.R;

/**
 * A controller object that is in charge of saving questions from a txt file
 * into database.
 * 
 * @author Zhaoyu Xu
 * @version 2.1
 */
public class QuestionSaver {

	/** The connection. */
	private ConnectionManager myCM;

	/**
	 * Initializes the saver object.
	 * 
	 * @throws IOException
	 */
	public QuestionSaver() throws IOException {
		myCM = new ConnectionManager(R.Strings.SQL_DATABASE_URL);
		saveToDB();
	}

	/**
	 * Reads question data from txt file and store them inside a prepared statement.
	 * Adding batched into prepared statement to avoid multiple connections
	 * conflicts. Closes the resource when task is completed.
	 * 
	 * @throws IOException
	 */
	private void saveToDB() throws IOException {
		Scanner scanner = new Scanner(Paths.get(R.Strings.FILE_LOCATION + R.Strings.QUESTION_FILE));
		try {
			Connection c = myCM.getConnection();
			c.setAutoCommit(false);
			Statement s = c.createStatement();
			String INSERT_RECORD = R.Strings.SQL_DATABASE_INSERT_RECORD;
			PreparedStatement ps = c.prepareStatement(INSERT_RECORD);
			int ID = 1;

			while (scanner.hasNextLine()) {
				String type = scanner.nextLine();
				String question = scanner.nextLine();
				String options = scanner.nextLine();
				String correctAnswers = scanner.nextLine();
				String hint = scanner.nextLine();

				ps.setInt(1, ID);
				ps.setString(2, type);
				ps.setString(3, question);
				ps.setString(4, correctAnswers);
				ps.setString(5, hint);
				ps.setString(6, options);
				ps.addBatch();
				ID++;
			}

			ps.executeBatch();
			c.commit();
			s.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		scanner.close();
	}

	/**
	 * Checks the database for Max ID because IDs are unique.
	 * 
	 * @return the maximum available ID for a question entry.
	 */
	public int getNewID() {
		try {
			Connection con = myCM.getConnection();
			Statement s;
			s = con.createStatement();
			String sql = "SELECT MAX(ID) AS MaxID FROM Questions";
			ResultSet rs = s.executeQuery(sql);
			return rs.getInt("MaxID") + 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
}
