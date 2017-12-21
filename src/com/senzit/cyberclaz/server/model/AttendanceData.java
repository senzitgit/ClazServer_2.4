package com.senzit.cyberclaz.server.model;

import java.util.Properties;

public class AttendanceData {
	
	private static Properties attendanceData=new Properties();
	
	public void putNewData(int batchId,boolean flag){
		attendanceData.put(batchId, flag);
	}
	public void removeData(int batchId){
		attendanceData.remove(batchId);
	}
	public boolean checkData (int batchId){
		return attendanceData.containsKey(batchId);
	}

}
