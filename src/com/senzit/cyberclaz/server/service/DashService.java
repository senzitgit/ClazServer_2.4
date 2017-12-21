package com.senzit.cyberclaz.server.service;

import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public interface DashService {
	
	List<HashMap> getAllNotification(String userId);

	List<HashMap> getAllNotificationForStudent(String userId);
	
	List<HashMap> getAllNotificationForStudentinClick(String userId);

	List<String> getUserIdofStudent(int notificationId);

	String getQuestionFromId(int notificationId);

}
