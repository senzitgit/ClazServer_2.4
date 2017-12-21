package com.senzit.cyberclaz.server.cognos;

import java.util.List;

public interface AssignedTaskDao {

	Integer saveAssignmentTasksInDb(AssignedTask at);

	Integer updateAssignmentTaskStatus(String userId,String subId,String status);

	List<Object[]> getAssignedAssignmentTask();

	Integer getTaskIdFromSubjectTopic(String userId, String subId, String topic);

}
