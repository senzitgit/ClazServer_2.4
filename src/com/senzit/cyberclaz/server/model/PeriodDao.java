package com.senzit.cyberclaz.server.model;

import java.util.List;

public interface PeriodDao {

	Integer savePeriodDetailsInDb(Period p);

	List<Object[]> getPeriodDetails();

}
