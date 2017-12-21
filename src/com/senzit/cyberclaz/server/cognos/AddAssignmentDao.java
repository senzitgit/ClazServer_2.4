package com.senzit.cyberclaz.server.cognos;

import java.util.List;

public interface AddAssignmentDao {

	Integer saveAssignAssignmentTasksInDb(AddAssignment at);

	List<String> getAssignedAssignmentTask(String subject);

	Integer getAssignmentIdFromSubjectTopic(String subId, String topic);

}
