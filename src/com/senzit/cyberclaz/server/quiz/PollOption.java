package com.senzit.cyberclaz.server.quiz;

public class PollOption {
	
	private int optionId;
	private String option;
	private Poll poll;
	
	public PollOption(){}

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

	public Poll getPoll() {
		return poll;
	}

	public void setPoll(Poll poll) {
		this.poll = poll;
	}

}
