package com.senzit.cyberclaz.server.model;

import java.util.List;

public interface VideoRatingDao {

	int saveExcellentRatingDetailsInDb(String ratingvalue);

	List<Integer> getCountValue(String ratingvalue);

	
	

}
