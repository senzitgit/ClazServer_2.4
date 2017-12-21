package com.senzit.cyberclaz.server.model;

import java.util.List;

public interface BatchDao {

	Integer saveBatchDetailsInDb(Batch batch);

	String getbatchIdFromBatchName(String bName);

	List<String> getBatchDetails();


	
	

}
