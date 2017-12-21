package com.senzit.cyberclaz.server.model;

import java.util.ArrayList;
import java.util.Properties;

public class UserAttendanceTime {
	private static Properties attendanceTime=new Properties();
	//	@SuppressWarnings("unchecked")
	public void putNewData(String userId,String startTime,int batchId)
	{

		Properties prop=new Properties();
		prop.put("startTime", startTime);
		prop.put("batchId", batchId);

		ArrayList<Properties> list =new ArrayList<Properties>();
		if(attendanceTime.get(userId)!=null)
			removeData(userId);
		list.add(prop);
		attendanceTime.put(userId, list);

	}

	public void removeData(String userId)
	{
		attendanceTime.remove(userId);
	}

	public boolean checkData (String userId)
	{
		return attendanceTime.containsKey(userId);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Properties> getData(String userId)
	{
		return (ArrayList<Properties>)attendanceTime.get(userId);
	}

}
