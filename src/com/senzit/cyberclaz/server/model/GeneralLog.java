package com.senzit.cyberclaz.server.model;

import java.util.ArrayList;
import java.util.Properties;

public class GeneralLog {

	private static Properties generalLogData=new Properties();
	@SuppressWarnings("unchecked")
	public void putNewData(String profilePic,int CourseBatchId,String log,String duration,String timestamp,String RaiseHandQuestion,String raiseHandAnswer){

		System.out.println("###############################################################");
		System.out.println(log);
		
		Properties prop=new Properties();
		prop.put("timestamp", timestamp);
		
		prop.put("duration", duration);
		prop.put("logText", log);
		if(profilePic!=null)
		{
     	
		prop.put("profilePic", profilePic);
		}
		else
		{
			prop.put("profilePic", "");
		}
		if(RaiseHandQuestion!=null)
		{
     	
			prop.put("raiseHandText", RaiseHandQuestion);
		}
		else
		{
			System.out.println("##############################inside raisehand");
			prop.put("raiseHandText", "");
		}
		if(raiseHandAnswer!=null)
			prop.put("raiseHandAnswer", raiseHandAnswer);
		else
			prop.put("raiseHandAnswer", "");
		ArrayList<Properties> templist = (ArrayList<Properties>) generalLogData.get(CourseBatchId);
		if(templist!=null)
		{
			templist.add(prop);
			generalLogData.put(CourseBatchId, templist);
			
		}
		else
		{
			ArrayList<Properties> list =new ArrayList<Properties>();
			list.add(prop);
			generalLogData.put(CourseBatchId, list);
		}
		System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
		System.out.println("General log"+generalLogData.toString());
		//		ArrayList<String> list=new ArrayList<String>();
		//		if(!generalLogData.isEmpty())
		//			list=(ArrayList<String>)generalLogData.get(batchId);
		//		list.add(log);
		//		generalLogData.put(batchId, list);

	}
	public void removeData(int CourseBatchId){
		generalLogData.remove(CourseBatchId);
	}
	public boolean checkData (int CourseBatchId){
		return generalLogData.containsKey(CourseBatchId);
	}
	@SuppressWarnings("unchecked")
	public ArrayList<Properties> getData(int CourseBatchId){
		return (ArrayList<Properties>)generalLogData.get(CourseBatchId);
	}

}
