package com.senzit.cyberclaz.server.quiz;

import com.senzit.cyberclaz.server.model.User;

public class Survey {
	
	private int surveyId;
	private String description;
	private User createdBy;
	
	public Survey(){}

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

}
