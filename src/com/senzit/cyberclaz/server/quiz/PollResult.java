package com.senzit.cyberclaz.server.quiz;

import com.senzit.cyberclaz.server.model.User;

public class PollResult {
	
	private int pollResultId;
	private PollOption pollOption;
	private User user;
	
	public PollResult(){}

	public int getPollResultId() {
		return pollResultId;
	}

	public void setPollResultId(int pollResultId) {
		this.pollResultId = pollResultId;
	}

	public PollOption getPollOption() {
		return pollOption;
	}

	public void setPollOption(PollOption pollOption) {
		this.pollOption = pollOption;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
