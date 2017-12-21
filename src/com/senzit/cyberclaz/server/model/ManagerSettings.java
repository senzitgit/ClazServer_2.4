package com.senzit.cyberclaz.server.model;

public class ManagerSettings {
	
	private int managerId;
	private User user;
	private CourseBatch courseBatch;
	
	public ManagerSettings(){}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
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
