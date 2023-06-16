package User.User;

import Question.Question;

import java.util.List;
import java.util.Map;

public class User {
	private String userID;
	private String userPassword;
	private List<Question> wrongQuestionsList;

	public List<Question> getWrongQuestionsList() {
		return wrongQuestionsList;
	}

	public void setWrongQuestionsList(List<Question> wrongQuestionsList) {
		this.wrongQuestionsList = wrongQuestionsList;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
}
