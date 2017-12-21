package com.senzit.cyberclaz.server.quiz;

public class SurveyOption {
	
	private int optionId;
	private String option;
	private SurveyQuestion question;
	
	public SurveyOption(){}

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public SurveyQuestion getQuestion() {
		return question;
	}

	public void setQuestion(SurveyQuestion question) {
		this.question = question;
	}

}
