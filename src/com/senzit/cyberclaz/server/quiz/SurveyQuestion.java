package com.senzit.cyberclaz.server.quiz;

public class SurveyQuestion {
	
	private int questionId;
	private String question;
	private Survey survey;
	
	public SurveyQuestion(){}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

}
