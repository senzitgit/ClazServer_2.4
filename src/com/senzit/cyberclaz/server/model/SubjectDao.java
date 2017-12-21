package com.senzit.cyberclaz.server.model;

import java.util.List;
import java.util.Properties;

public interface SubjectDao {

	Integer saveSubjectDetailsDetailsInDb(Subject sub);

	List<String>  getFullSubjectList();

	String getSubjectNameFromSubjectId(String subject);



}
