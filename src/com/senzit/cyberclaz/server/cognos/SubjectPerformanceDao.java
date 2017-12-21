package com.senzit.cyberclaz.server.cognos;

public interface SubjectPerformanceDao {

	Integer saveSubjectPerformanceDetailsInDb(SubjectPerformance sp);

	int subjectPerformanceDetailsUpdation(String userId,String subjectId,String rating);

}
