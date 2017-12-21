package com.senzit.cyberclaz.server.cognos;

public interface TeacherRecommendationDao {

	Integer saveteacherRecommendationInDb(TeacherRecommendation tr);

	Integer getTeacherRecomentationId(String userId, String subId, String term);

	Integer updateTeacherRecommendation(String userId, String subId,
			String term, String rating);

}
