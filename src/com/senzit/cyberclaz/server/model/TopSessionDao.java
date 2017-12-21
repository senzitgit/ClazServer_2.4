package com.senzit.cyberclaz.server.model;

import java.util.List;

public interface TopSessionDao {

	Integer getTopSessionIdFromEventId(int clazEventDetailId);

	Integer saveRatingDetailsInDb(TopSession ts);

	List<Integer> getLikeCountTillNow(int clazEventDetailId);

	Integer updateViewerLikeCount(int clazEventDetailId, int rateCount);

	Integer updateViewerDisLikeCount(int clazEventDetailId, int rateCount);

	List<Object[]> getLikeCountNow(int clazEventDetailId);

	List<Integer> getDisLikeCountTillNow(int clazEventDetailId);

	
	

}
