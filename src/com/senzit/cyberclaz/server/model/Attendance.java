package com.senzit.cyberclaz.server.model;

public class Attendance {
	
	private String userId;
	private User user;
	private ClassEventDetail classEventDetail;
	private CourseSubject courseSubject;
	private int attendedSession;
	private int totalNumberOfSession;
	private String currentMonth;
	
	public Attendance(){}
	
	
	public String getCurrentMonth() {
		return currentMonth;
	}


	public void setCurrentMonth(String month) {
		this.currentMonth = month;
	}


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
	
	public CourseSubject getCourseSubject() {
		return courseSubject;
	}

	public void setCourseSubject(CourseSubject courseSubject) {
		this.courseSubject = courseSubject;
	}

	public ClassEventDetail getClassEventDetail() {
		return classEventDetail;
	}

	public void setClassEventDetail(ClassEventDetail classEventDetail) {
		this.classEventDetail = classEventDetail;
	}

	public int getAttendedSession() {
		return attendedSession;
	}

	public void setAttendedSession(int attendedSession) {
		this.attendedSession = attendedSession;
	}

	public int getTotalNumberOfSession() {
		return totalNumberOfSession;
	}

	public void setTotalNumberOfSession(int totalNumberOfSession) {
		this.totalNumberOfSession = totalNumberOfSession;
	}

	

}
