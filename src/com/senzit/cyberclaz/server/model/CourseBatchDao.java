package com.senzit.cyberclaz.server.model;

import java.util.List;

public interface CourseBatchDao {

	Integer saveCourseBatchDetailsInDb(CourseBatch cb);

	int getCourseBatchId(String courseId, String semId, String batchId,
			String classRoomId);

	List<Object[]> getCourseBatchDetails(int cBatchId);

	List<Object[]> getcourseBatchDetails();

	Integer getCourseBatchFromClassRoom(String classRoomNo);

}
