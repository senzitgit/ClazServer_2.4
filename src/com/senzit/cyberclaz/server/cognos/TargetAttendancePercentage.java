package com.senzit.cyberclaz.server.cognos;

import com.senzit.cyberclaz.server.model.CourseBatch;
import com.senzit.cyberclaz.server.model.User;

public class TargetAttendancePercentage {
	
	
	private int attendanceId;
	private User user;
	private CourseBatch courseBatch;
	private String targetAttendance;
	
	public TargetAttendancePercentage(){}

	public int getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(int attendanceId) {
		this.attendanceId = attendanceId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CourseBatch getCourseBatch() {
		return courseBatch;
	}

	public void setCourseBatch(CourseBatch courseBatch) {
		this.courseBatch = courseBatch;
	}

	public String getTargetAttendance() {
		return targetAttendance;
	}

	public void setTargetAttendance(String targetAttendance) {
		this.targetAttendance = targetAttendance;
	}
	
	

}
