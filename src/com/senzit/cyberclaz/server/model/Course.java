package com.senzit.cyberclaz.server.model;

public class Course {

	private String courseId;
	private String courseName;
	private String courseDescription;
	private String courseCategory;
	private String courseDuration;
	private String currentScheme;
	private String semOrYear;
	private String department;
	private String distantOrRegular;
	public Course(){}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public String getCourseCategory() {
		return courseCategory;
	}

	public void setCourseCategory(String courseCategory) {
		this.courseCategory = courseCategory;
	}

	public String getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}

	public String getCurrentScheme() {
		return currentScheme;
	}
	public void setCurrentScheme(String currentScheme) {
		this.currentScheme = currentScheme;
	}

	public String getSemOrYear() {
		return semOrYear;
	}

	public void setSemOrYear(String semOrYear) {
		this.semOrYear = semOrYear;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDistantOrRegular() {
		return distantOrRegular;
	}

	public void setDistantOrRegular(String distantOrRegular) {
		this.distantOrRegular = distantOrRegular;
	}

}
