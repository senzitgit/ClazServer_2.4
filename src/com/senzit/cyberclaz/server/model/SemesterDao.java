package com.senzit.cyberclaz.server.model;

import java.util.List;

public interface SemesterDao {

	Integer saveSemDetailsInDb(String semName);

	List<String> getFullSemesterList();

}
