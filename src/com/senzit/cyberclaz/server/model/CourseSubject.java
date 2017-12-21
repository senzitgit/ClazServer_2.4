package com.senzit.cyberclaz.server.model;


public class CourseSubject {
	
	private int courseSubjectId;
	private Course course;
	private Subject subject;
	private Semester semester;
	
	public CourseSubject(){}

	
	public int getCourseSubjectId() {
		return courseSubjectId;
	}


	public void setCourseSubjectId(int courseSubjectId) {
		this.courseSubjectId = courseSubjectId;
	}


	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

}
