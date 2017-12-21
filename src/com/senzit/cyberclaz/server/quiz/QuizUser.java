package com.senzit.cyberclaz.server.quiz;

import com.senzit.cyberclaz.server.model.User;

public class QuizUser {
	
	private int quizUserId;
	private User user;
	private Quiz quiz;
	private int noOfrightAnswers;
	
	public QuizUser(){}

	public int getQuizUserId() {
		return quizUserId;
	}

	public void setQuizUserId(int quizUserId) {
		this.quizUserId = quizUserId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public int getNoOfrightAnswers() {
		return noOfrightAnswers;
	}

	public void setNoOfrightAnswers(int noOfrightAnswers) {
		this.noOfrightAnswers = noOfrightAnswers;
	}	

}
