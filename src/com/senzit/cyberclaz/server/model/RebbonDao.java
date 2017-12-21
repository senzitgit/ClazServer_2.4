package com.senzit.cyberclaz.server.model;

import java.util.List;

public interface RebbonDao {
	
	Rebbon getRebbonLink(String rebbonId);

	List<Object[]> getTeacherInClaz(String clazRoom);
	
	String getRebbonIdFromUser(String userId);

	

}
