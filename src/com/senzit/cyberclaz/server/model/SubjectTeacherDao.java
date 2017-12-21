package com.senzit.cyberclaz.server.model;

import java.util.List;

public interface SubjectTeacherDao {

	List<String> teacherIdForClazEventDetailId(int clazEventDetailId);

	int getSubTeacherId(String teacherId);

	Integer saveSubjectTeacherDetailsInDb(SubjectTeacher st);

	List<Object[]> getSubjectTeacherDetails();
	

}
