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

public class QuestionSaver {
	
	private ConnectionManager myCM;
	
	public QuestionSaver() throws IOException {
		myCM = new ConnectionManager(R.Strings.SQL_DATABASE_URL);
		saveToDB();
	}

	private void saveToDB() throws IOException {
		Scanner scanner = new Scanner(Paths.get(R.Strings.FILE_LOCATION
                    					  + R.Strings.QUESTION_FILE));
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
		
		
//		while (scanner.hasNextLine()) {
//			Connection c = null;
//			Statement s = null;
//			int ID = 1;
//			String type = scanner.nextLine();
//			String question = scanner.nextLine();
//			String options = scanner.nextLine();
//			String correctAnswers = scanner.nextLine();
//			String hint = scanner.nextLine();
//			try {
//				c = myCM.getConnection();
//				s = c.createStatement();
//				String sql = "";
//				sql = "INSERT INTO Questions (ID,Type,Question,CorrectAnswer,Hint,Options) VALUES (" + ID + ",'" + type + "','" + question + "','" + correctAnswers + "','" + hint + "','" + options + "');";
//				int rv = s.executeUpdate(sql);
//				System.out.println( "executeUpdate() returned " + rv );
//			} catch (Exception e) {
//				System.out.println("After sql insert: " + e.getMessage());
//			} finally {
//				if (s != null) {
//					try {
//						s.close();
//					} catch (SQLException e) {
//						System.out.println("After sql insert and close statement: " + e.getMessage());
//					}
//				}
//				if (c != null) {
//					try {
//						c.close();
//					} catch (SQLException e) {
//						System.out.println("After sql insert and close connection: " + e.getMessage());
//					}
//				}
//			}
//			ID++;
//		}
		scanner.close();
	}
	
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
//		Connection c = null;
//		Statement s = null;
//		try {
//			c = myCM.getConnection();
//			s = c.createStatement();
//			String sql = "SELECT MAX(ID) AS MaxID FROM Questions";
//			ResultSet rs = s.executeQuery(sql);
//			return rs.getInt("MaxID") + 1;
//		} catch (Exception e) {
//			System.out.println("In get new ID: " + e.getMessage());
//		} finally {
//			if (s != null) {
//				try {
//					s.close();
//				} catch (SQLException e) {
//					System.out.println("Get new ID and close statement: " + e.getMessage());
//				}
//			}
//			if (c != null) {
//				try {
//					c.close();
//				} catch (SQLException e) {
//
//					System.out.println("Get new ID and close connection: " + e.getMessage());
//				}
//			}
//		}
//		return 1;
	}
}
