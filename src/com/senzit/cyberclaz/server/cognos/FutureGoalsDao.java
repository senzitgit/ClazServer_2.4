package com.senzit.cyberclaz.server.cognos;

public interface FutureGoalsDao {

	Integer saveFutureGoalsInDb(FutureGoals pr);

	Integer getGoalIdForUser(String userId, Integer cBatchId);

	Integer updateGoalInDB(String userId, Integer cBatchId, String goal);

}
