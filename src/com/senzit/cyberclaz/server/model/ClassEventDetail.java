package com.senzit.cyberclaz.server.model;

import java.sql.Blob;

import java.sql.Timestamp;

public class ClassEventDetail {
	
	private int classEventDetailId;
	private Schedule schedule;
	//private TempSchedule tempSchedule;
	private Note note;
	private String generalLog;
	private String chapterName;
	private String topicName;
	private String ftpLocation;
	private boolean attachmentFlag;
	private Timestamp startTime;
	private Timestamp endTime;
	private int totalAttendees;

	private String scheduleFlag;
	private String subjectName;
	private String courseName;
	private String teacherName;
	private String teacherId;
	private String teacherPic;
	private int clazFlag;
	
	
	public ClassEventDetail(){}

//	public TempSchedule getTempSchedule() {
//		return tempSchedule;
//	}
//
//	public void setTempSchedule(TempSchedule tempSchedule) {
//		this.tempSchedule = tempSchedule;
//	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	
	
	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	
	
	

	public String getTeacherPic() {
		return teacherPic;
	}

	public void setTeacherPic(String teacherPic) {
		this.teacherPic = teacherPic;
	}
	 
	
	
	
	
	
	public String getScheduleFlag() {
		return scheduleFlag;
	}

	public void setScheduleFlag(String scheduleFlag) {
		this.scheduleFlag = scheduleFlag;
	}

	
	
	
	
	
	
	

	public int getClassEventDetailId() {
		return classEventDetailId;
	} 

	public void setClassEventDetailId(int classEventDetailId) {
		this.classEventDetailId = classEventDetailId;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public String getGeneralLog() {
		return generalLog;
	}

	public void setGeneralLog(String generalLog) {
		this.generalLog = generalLog;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getFtpLocation() {
		return ftpLocation;
	}

	public void setFtpLocation(String ftpLocation) {
		this.ftpLocation = ftpLocation;
	}

	public boolean getAttachmentFlag() {
		return attachmentFlag;
	}

	public void setAttachmentFlag(boolean attachmentFlag) {
		this.attachmentFlag = attachmentFlag;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public int getTotalAttendees() {
		return totalAttendees;
	}

	public void setTotalAttendees(int totalAttendees) {
		this.totalAttendees = totalAttendees;
	}
	
	

	
	
	
	
	public int getClazFlag() {
		return clazFlag;
	}

	public void setClazFlag(int clazFlag) {
		this.clazFlag = clazFlag;
	}
	
	
	
	
	
	
	
	
	


}
