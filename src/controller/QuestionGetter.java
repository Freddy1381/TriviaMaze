/*
 * TCSS 360 Trivia Maze
 */

package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;

import model.MCQuestion;
import model.Question;
import res.R;

/**
 * A controller object that is in charge of getting questions from the database.
 * 
 * @author Zhaoyu Xu
 * @version 2.1
 */
public class QuestionGetter {

	/** The connection. */
	private ConnectionManager myCM;

	/** A queue stored with all questions. */
	private Queue<Question> myQuestions;

	/**
	 * Initialize the questiongetter object. Setup the connection and requests
	 * questions from database.
	 * 
	 * @throws Exception
	 */
	public QuestionGetter() throws Exception {
		myCM = new ConnectionManager(R.Strings.SQL_DATABASE_URL);
		myQuestions = requestQuestions();
	}

	/**
	 * Request questions from the database and store in a queue. Close all resources
	 * when completed.
	 * 
	 * @return result the queue with all the questions.
	 */
	private Queue<Question> requestQuestions() {
		// TODO Auto-generated method stub
		Queue<Question> result = new LinkedList<>();
		Question q = null;
		Connection c = myCM.getConnection();
		Statement s = null;
		ResultSet rs = null;

		try {
			s = c.createStatement();
			String sql = "SELECT * FROM Questions";
			rs = s.executeQuery(sql);
			while (rs.next()) {
				String type = rs.getString("Type");
				String question = rs.getString("Question");
				String correctAnswer = rs.getString("CorrectAnswer");
				String hint = rs.getString("Hint");
				String options = rs.getString("Options");
				switch (type) {
				case R.Strings.QUESTION_TYPE_SA:
					q = new Question(type, question, correctAnswer, hint);
				default:
					q = new MCQuestion(type, question, correctAnswer, hint, options);
				}
				result.add(q);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (s != null) {
				try {
					s.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (c != null) {
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	/**
	 * Getter for the question queue.
	 * 
	 * @return the questions queue
	 */
	public Queue<Question> getQuestionList() {
		return myQuestions;
	}
}
