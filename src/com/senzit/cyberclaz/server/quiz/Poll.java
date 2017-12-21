package com.senzit.cyberclaz.server.quiz;

import com.senzit.cyberclaz.server.model.User;

public class Poll {
	
	private int pollId;
	private String pollQuestion;
	private User createdBy;
	
	public Poll(){}

	public int getPollId() {
		return pollId;
	}

	public void setPollId(int pollId) {
		this.pollId = pollId;
	}

	public String getPollQuestion() {
		return pollQuestion;
	}

	public void setPollQuestion(String pollQuestion) {
		this.pollQuestion = pollQuestion;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

}
