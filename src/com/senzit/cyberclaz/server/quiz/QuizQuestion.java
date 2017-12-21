package com.senzit.cyberclaz.server.quiz;

public class QuizQuestion {
	
	private int qnId;
	private String qn;
	private Quiz quiz;
	
	public QuizQuestion(){}

	public int getQnId() {
		return qnId;
	}

	public void setQnId(int qnId) {
		this.qnId = qnId;
	}

	public String getQn() {
		return qn;
	}

	public void setQn(String qn) {
		this.qn = qn;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}	

}
