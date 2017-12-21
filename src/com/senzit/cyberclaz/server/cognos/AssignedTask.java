package com.senzit.cyberclaz.server.cognos;

import com.senzit.cyberclaz.server.model.Subject;
import com.senzit.cyberclaz.server.model.User;

public class AssignedTask {
	
	private int taskId;
	private User user;
	private Subject subject;
	private String assignedTopic;
	private String status;
	
	
	public AssignedTask(){}

	
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}



	public int getTaskId() {
		return taskId;
	}


	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}


	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAssignedTopic() {
		return assignedTopic;
	}
	public void setAssignedTopic(String assignedTopic) {
		this.assignedTopic = assignedTopic;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
