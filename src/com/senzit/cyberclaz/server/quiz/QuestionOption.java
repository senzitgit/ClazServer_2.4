package com.senzit.cyberclaz.server.quiz;

public class QuestionOption {
	
	private int optionId;
	private String option;
	private QuizQuestion question;
	private boolean answerFlag;
	
	public QuestionOption(){}

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

	public QuizQuestion getQuestion() {
		return question;
	}

	public void setQuestion(QuizQuestion question) {
		this.question = question;
	}

	public boolean isAnswerFlag() {
		return answerFlag;
	}

	public void setAnswerFlag(boolean answerFlag) {
		this.answerFlag = answerFlag;
	}
	
}
