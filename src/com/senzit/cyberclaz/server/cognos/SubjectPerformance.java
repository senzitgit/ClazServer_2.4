package com.senzit.cyberclaz.server.cognos;

import com.senzit.cyberclaz.server.model.Subject;
import com.senzit.cyberclaz.server.model.User;

public class SubjectPerformance {
	
	private int subPerformanceId;
	private User user;
	private Subject subject;
	private String rating;
	
	public SubjectPerformance(){}

	public int getSubPerformanceId() {
		return subPerformanceId;
	}

	public void setSubPerformanceId(int subPerformanceId) {
		this.subPerformanceId = subPerformanceId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	
	

}
