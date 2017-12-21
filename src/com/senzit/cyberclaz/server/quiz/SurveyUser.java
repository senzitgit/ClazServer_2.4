package com.senzit.cyberclaz.server.quiz;

import com.senzit.cyberclaz.server.model.User;

public class SurveyUser {
	
	private int surveyUserId;
	private SurveyOption option;
	private User user;
	
	public SurveyUser(){}

	public int getSurveyUserId() {
		return surveyUserId;
	}

	public void setSurveyUserId(int surveyUserId) {
		this.surveyUserId = surveyUserId;
	}

	public SurveyOption getOption() {
		return option;
	}

	public void setOption(SurveyOption option) {
		this.option = option;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
