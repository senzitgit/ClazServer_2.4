package com.senzit.cyberclaz.server.model;

import java.util.Properties;

public class RebbonData {
	
private static Properties rebbonData=new Properties();
	
	public void putNewData(int batchId,String rebbonLinkJson){
		
		rebbonData.put(batchId,rebbonLinkJson);
		
		
		System.out.println("rebbondataclass"+rebbonData);
	}
	public void removeData(String userName){
		
		rebbonData.remove(userName);
		
		System.out.println("rebbondataclass"+rebbonData);
	}
	public String getRebbonLink(int batchId){
		
		
		System.out.println("rebbondataclass"+rebbonData);
		return (String)rebbonData.get(batchId);
		
	}
	public static void remove(int courseBatchId) {
		
		rebbonData.remove(courseBatchId);
			
	}

}
