package com.senzit.cyberclaz.server.model;

import java.util.List;

public interface AttendanceDao {

	Attendance getAttendance(String userId);

	void saveAttendance(Attendance atObj);

	void updateAttendance(Attendance attendance);

	List<Object[]> getAttendanceDetails(String userId, String month, String subId);

}
