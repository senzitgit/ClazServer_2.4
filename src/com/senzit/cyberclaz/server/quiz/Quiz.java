package com.senzit.cyberclaz.server.quiz;

import com.senzit.cyberclaz.server.model.User;

public class Quiz {
	
	private int quizId;
	private String subject;
	private String description;
	private User createdBy;
	
	public Quiz(){}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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
