package com.senzit.cyberclaz.server.model;

import java.util.List;

public interface LogDao {
	
	
	Log getUserStatus(String userId);
	void setLogStatus(Log logObj);
	void updateLoginStatus(Log logObj);
	Log isRebbonExist(String rebbonId);
	List<Object[]> getTeacherFromBatch(int courseBatchId);
	List<String> getRebbonUser(String rebbonId);
	Log getUserLog(String userId);
	String getSession(String userId);

}
