package com.senzit.cyberclaz.server.cognos;

import java.util.List;

public interface TopPerformerDao {


	Integer getTopPerformerIdTeacher(int classEventId);

	Integer saveUserRatingAboutTeacher(TopPerformer tp);

	Integer getTeacherRateCount(int classEventId);

	int updateTheRateCount(int classEventId, Integer rateResult);

}
