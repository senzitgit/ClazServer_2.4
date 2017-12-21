package com.senzit.cyberclaz.server.model;


public class Viewers {

	private int viewerId;
	private ClassEventDetail classEventDetail;
	private User user;
	private int userRateCount;
	private String teacherName;
	

	
	
	public Viewers(){}


	public String getTeacherName() {
		return teacherName;
	}


	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}


	public int getUserRateCount() {
		return userRateCount;
	}

	public void setUserRateCount(int userRateCount) {
		this.userRateCount = userRateCount;
	}



	public int getViewerId() {
		return viewerId;
	}


	public void setViewerId(int viewerId) {
		this.viewerId = viewerId;
	}


	public ClassEventDetail getClassEventDetail() {
		return classEventDetail;
	}


	public void setClassEventDetail(ClassEventDetail classEventDetail) {
		this.classEventDetail = classEventDetail;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}



	
}
