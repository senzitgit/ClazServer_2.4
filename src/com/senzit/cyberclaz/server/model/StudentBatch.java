package com.senzit.cyberclaz.server.model;

public class StudentBatch {
	
	private String userId;
	private User user;
	private CourseBatch courseBatch;
	
	public StudentBatch(){}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CourseBatch getCourseBatch() {
		return courseBatch;
	}

	public void setCourseBatch(CourseBatch courseBatch) {
		this.courseBatch = courseBatch;
	}

}
