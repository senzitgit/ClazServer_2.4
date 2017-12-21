package com.senzit.cyberclaz.server.cognos;

import com.senzit.cyberclaz.server.model.CourseBatch;
import com.senzit.cyberclaz.server.model.User;

public class FutureGoals {
	
	private int goalId;
	private String goal;
	private User user;
	private CourseBatch courseBatch;
	
	public FutureGoals(){}

	
	
	public CourseBatch getCourseBatch() {
		return courseBatch;
	}

	public void setCourseBatch(CourseBatch courseBatch) {
		this.courseBatch = courseBatch;
	}

	public int getGoalId() {
		return goalId;
	}

	public void setGoalId(int goalId) {
		this.goalId = goalId;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

}
