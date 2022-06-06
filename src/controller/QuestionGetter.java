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

public class QuestionGetter {
	
	private ConnectionManager myCM;
	private Queue<Question> myQuestions;
	
	public QuestionGetter() throws Exception {
		myCM = new ConnectionManager(R.Strings.SQL_DATABASE_URL);
		myQuestions = requestQuestions();
	}

	private Queue<Question> requestQuestions() {
		// TODO Auto-generated method stub
		Queue<Question> result = new LinkedList<Question>();
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
				case "Short Answer": 
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
	
	public Queue<Question> getQuestionList() {
		return myQuestions;
	}
}
