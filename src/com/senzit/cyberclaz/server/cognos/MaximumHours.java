package com.senzit.cyberclaz.server.cognos;


import com.senzit.cyberclaz.server.model.CourseBatch;
import com.senzit.cyberclaz.server.model.User;

public class MaximumHours {
	
	private int maxHourId;
	private int minutes;
	private User user;
	private CourseBatch courseBatch;
	
	public MaximumHours(){}

	
	public CourseBatch getCourseBatch() {
		return courseBatch;
	}


	public void setCourseBatch(CourseBatch courseBatch) {
		this.courseBatch = courseBatch;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	public int getMaxHourId() {
		return maxHourId;
	}



	public void setMaxHourId(int maxHourId) {
		this.maxHourId = maxHourId;
	}

//	public String getUserId() {
//		return userId;
//	}
//
//	public void setUserId(String userId) {
//		this.userId = userId;
//	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

//	public String getCurrentDay() {
//		return currentDay;
//	}
//
//	public void setCurrentDay(String currentDay) {
//		this.currentDay = currentDay;
//	}




}
