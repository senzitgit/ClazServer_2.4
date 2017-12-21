package com.senzit.cyberclaz.server.model;

public class CourseBatch {
	
	private int courseBatchId;
	private Course course;
	private Semester semester;
	private Batch batch;
	private ClassRoom classRoom;
	
	public CourseBatch(){}

	public int getCourseBatchId() {
		return courseBatchId;
	}

	public void setCourseBatchId(int courseBatchId) {
		this.courseBatchId = courseBatchId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Semester getSemester() {
		return semester;
	}

	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}

}
