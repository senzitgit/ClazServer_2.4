package com.senzit.cyberclaz.server.model;

import java.util.List;

public interface DayDao {

	Integer saveDayDetailsInDb(Day d);

	List<String> getDayDetails();

	

}
