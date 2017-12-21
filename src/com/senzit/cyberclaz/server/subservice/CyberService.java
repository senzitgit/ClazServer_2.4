package com.senzit.cyberclaz.server.subservice;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.web.multipart.MultipartFile;

import com.senzit.cyberclaz.server.model.CyberInit;


public class CyberService {
public static String jsonForWorklight(String json){
		
		String tempJson = json.substring(1, (json.length()-1));
		return tempJson.replaceAll("\\\\", "");
	}
	
	public static String  createWorkingFolder(int classEventDetailId,String componentName){
		
		char separator=File.separatorChar;
		
		String workingFolderPath=CyberInit.webAppFolder+separator
				+CyberInit.cyberProperty.getProperty("defaultWorkingFolder");
		File workingFolder 	= new File(workingFolderPath);
		if(!workingFolder.exists())
			workingFolder.mkdir();
		
		
		String componentFolderPath=workingFolderPath + separator + componentName;
		File componentFolder = new File(componentFolderPath);
		if(!componentFolder.exists())
			componentFolder.mkdir();
		
//		String userFolderPath=componentFolderPath + separator + batchId;
//		File userFolder = new File(userFolderPath);
//		if(!userFolder.exists())
//			userFolder.mkdir();
		
		String clazFolderPath=componentFolderPath + separator + classEventDetailId;
		File clazDetailsFolder = new File(clazFolderPath);
		if(!clazDetailsFolder.exists())
			clazDetailsFolder.mkdir();
		
		return clazFolderPath;

	}
	
	public static String getWorkingFolderWebPath(String componentName,int classEventDetailId){
		
		return CyberInit.ipAddress+"/"
	    		+CyberInit.cyberProperty.getProperty("defaultWorkingFolder")+"/"
	    		
	    		+componentName+"/"+classEventDetailId;
	}
	
	public static String getWorkingFolderPath(String componentName,int classEventDetailId){
		
		char separator=File.separatorChar;
		return CyberInit.webAppFolder+separator
	    		+CyberInit.cyberProperty.getProperty("defaultWorkingFolder")+separator
	    		+componentName+separator+classEventDetailId;
	}
	
	public static String formatTime(Timestamp timestamp){
		
		SimpleDateFormat sdf = new SimpleDateFormat("E,dd-MM-YYYY,HH:mm:ss");
		return sdf.format(timestamp.getTime());
	}
	
	public static String getIpAddress(){
		
		return "192.168.0.117";
	}
   public static String getServerPort(){
		
		return "";
	}
   public static String getglobalIpofRebbon(){
		
		return "192.168.0.117";
	}
   public static String getServerPortRebbon(){
		
		return "8080";
	}

public static String getProfilePicPath() {
	// TODO Auto-generated method stub
	return null;
}
}
