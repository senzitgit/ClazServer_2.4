package com.senzit.cyberclaz.server.model;

import java.util.List;

public interface ViewersDao {

	Integer saveViewersDetailsInDb(Viewers v);

	List<String> getViewersList(int clazEventDetailId);

	Integer saveRatingDetailsInDb(Viewers v);

	List<Integer> getUserCountValue();

	 Integer getViewersStatus(int clazEventDetailId);

	 int updateViewRateofVideo(int clazEventDetailId, int rateCount);


	List<Integer> getViewerRateCountTillNow(int clazEventDetailId);

	Integer getViewersIdofThisClassEvent(String userId, int clazEventDetailId);

	//Integer updateRatingDetailsInDb(int clazEventDetailId,int rateCount);

	Integer updateInViewerTable(int clazEventDetailId, int rateValue);



	//List<Integer> getUserRateCountTillNow(int clazEventDetailId);
	


}
