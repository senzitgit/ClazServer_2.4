package com.senzit.cyberclaz.server.model;

public class TempSchedule {
	

	private int tempScheduleId;
	private Schedule schedule;
	private Day day;
	private Period period;
	private SubjectTeacher subjectTeacher;
	private CourseBatch courseBatch;
	
	public TempSchedule(){}

	
	public Schedule getSchedule() {
		return schedule;
	}


	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}


	public int getTempScheduleId() {
		return tempScheduleId;
	}

	public void setTempScheduleId(int tempScheduleId) {
		this.tempScheduleId = tempScheduleId;
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
