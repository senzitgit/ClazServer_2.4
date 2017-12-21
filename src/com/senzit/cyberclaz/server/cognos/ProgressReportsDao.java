package com.senzit.cyberclaz.server.cognos;

public interface ProgressReportsDao {

	Integer saveprogressReportsInDb(ProgressReports pr);

	Integer getProgressIdFromDB(String userId, String term, String subId);

	Integer updateMarksInDB(String userId, String term, String subId,
			String mark);

}
