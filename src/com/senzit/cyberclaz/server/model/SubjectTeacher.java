package com.senzit.cyberclaz.server.model;

public class SubjectTeacher {

	private int subjectTeacherId;
	private User teacher;
	private CourseSubject courseSubject;
	private Batch batch;

	public SubjectTeacher(){}


	public int getSubjectTeacherId() {
		return subjectTeacherId;
	}


	public void setSubjectTeacherId(int subjectTeacherId) {
		this.subjectTeacherId = subjectTeacherId;
	}


	public User getTeacher() {
		return teacher;
	}

	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}

	public CourseSubject getCourseSubject() {
		return courseSubject;
	}

	public void setCourseSubject(CourseSubject courseSubject) {
		this.courseSubject = courseSubject;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

}
