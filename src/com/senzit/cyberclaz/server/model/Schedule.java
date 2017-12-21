package com.senzit.cyberclaz.server.model;

public class Schedule {
	
	private int scheduleId;
	private Day day;
	private Period period;
	private SubjectTeacher subjectTeacher;
	private CourseBatch courseBatch;
	
	public Schedule(){}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public SubjectTeacher getSubjectTeacher() {
		return subjectTeacher;
	}

	public void setSubjectTeacher(SubjectTeacher subjectTeacher) {
		this.subjectTeacher = subjectTeacher;
	}

	public CourseBatch getCourseBatch() {
		return courseBatch;
	}

	public void setCourseBatch(CourseBatch courseBatch) {
		this.courseBatch = courseBatch;
	}

}
