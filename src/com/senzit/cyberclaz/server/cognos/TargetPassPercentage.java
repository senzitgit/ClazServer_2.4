package com.senzit.cyberclaz.server.cognos;

import com.senzit.cyberclaz.server.model.CourseBatch;
import com.senzit.cyberclaz.server.model.User;

public class TargetPassPercentage {	
	
	private int targetId;
	private User user;
	private CourseBatch courseBatch;
	private String target;
	
	public TargetPassPercentage(){}
	
	public int getTargetId() {
		return targetId;
	}
	public void setTargetId(int targetId) {
		this.targetId = targetId;
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
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	
	

}
