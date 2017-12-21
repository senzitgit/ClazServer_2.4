package com.senzit.cyberclaz.server.cognos;

import com.senzit.cyberclaz.server.model.Subject;
import com.senzit.cyberclaz.server.model.User;

public class ProgressReports {
	
	private int progressId;
	private User user;
	private Subject subject;
	private String term;
	private String marks;
	
	public ProgressReports()
	{}


	public int getProgressId() {
		return progressId;
	}


	public void setProgressId(int progressId) {
		this.progressId = progressId;
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
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	
	

}
