package com.senzit.cyberclaz.server.model;

import java.util.List;

public interface ClassRoomDao {

	Integer saveClassRoomDetailsInDb(ClassRoom cr);

	List<String> getClassRoomDetails();

}
