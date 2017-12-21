package com.senzit.cyberclaz.server.cognos;


import com.senzit.cyberclaz.server.model.User;

public class TargetProgress {
	
	private String userId;
	private User user;
	private String target;
	private String status;
	
	public TargetProgress()
	{}

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

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
