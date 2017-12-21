package com.senzit.cyberclaz.server.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.senzit.cyberclaz.server.model.NotificationDao;
import com.senzit.cyberclaz.server.model.SchedulerDao;
import com.senzit.cyberclaz.server.model.UserDao;

public class DashServiceImp implements DashService
{
	private NotificationDao notificationDao;
	private UserDao userDao;

	
	public void setNotificationDao(NotificationDao notificationDao) {
		this.notificationDao = notificationDao;
	}
   public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap> getAllNotification(String userId) {


		List<HashMap> allNotification=new ArrayList<HashMap>();
		List<HashMap> nullList = new ArrayList<HashMap>();
		
		List<Object[]> notification=notificationDao.getAllNotification(userId);
//		for(int i=0;i<notification.size();i++)
//		{
		if(!notification.isEmpty())
		{
			for(int i=0;i<notification.size();i++)
			{
			  Object[] objArray = notification.get(i);
		
			  String studId=(String) objArray[1];
				 
				 String profilePic= userDao.getProfilePic(studId);
				 System.out.println("profilePic"+profilePic);
				 
				if(objArray[4]==null)
				{
					HashMap result=new HashMap();
					result.put("NotificationId", (Integer) objArray[0]);
					result.put("FromUser", studId);
					result.put("Notification", (String) objArray[2]);
					result.put("RaisehandOrRemindernote", (String) objArray[3]);
					result.put("StudentProfilePic", profilePic);
					allNotification.add(result);

		        }
		       
	         }
			return allNotification;
		}
		return nullList;		
				
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap> getAllNotificationForStudent(String userId) {
		
		List<HashMap> allNotificationForStud=new ArrayList<HashMap>();
		List<HashMap> nullList = new ArrayList<HashMap>();
		List<Object[]> notification=notificationDao.getAllNotificationForStudent(userId);
		int length=notification.size();
	
		for(int i=0;i<length;i++)
		{
			 Object[] objArray=notification.get(i);
			 String obj=(String) objArray[5];
			 String teacherId=(String) objArray[1];
			 System.out.println("teacherName"+teacherId);
			 
			 String profilePic= userDao.getProfilePic(teacherId);
			 System.out.println("profilePic"+profilePic);
			 
   		    if("Answeredquestion".equals(obj))
			{
				
			HashMap result=new HashMap();
	
			result.put("notificationId", (Integer) objArray[0]);
			result.put("FromUser", teacherId);
			result.put("notificationQuestion", (String) objArray[2]);
			result.put("notificationAnswer", (String) objArray[3]);
			result.put("RaisehandOrRemindernoteOrDoubt", (String) objArray[4]);
			
	
			result.put("teacherprofilePic", profilePic);
			allNotificationForStud.add(result);

		     }
			 else if("".equals(obj))
			 {
				 System.out.println("some questionsnot yet replied");
			 }
		
		}
		
		return allNotificationForStud;	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap> getAllNotificationForStudentinClick(String userId) {
		
		List<HashMap> allNotificationForStudNew=new ArrayList<HashMap>();
		List<Object[]> notification=notificationDao.getAllNotificationForStudent(userId);
		int length=notification.size();
		for(int i=0;i<length;i++)
		{
			 Object[] objArray=notification.get(i);
			 
			 if(objArray[5]!="Reminder" && objArray[5]!="Answeredquestion")
			{
				
			HashMap result=new HashMap();
	
			result.put("notificationId", (Integer) objArray[0]);
			result.put("FromUser", (String) objArray[1]);
			result.put("notificationQuestion", (String) objArray[2]);
			result.put("notificationAnswer", (String) objArray[3]);
			result.put("RaisehandOrRemindernoteOrDoubt", (String) objArray[4]);
			
			
			allNotificationForStudNew.add(result);

		     }
		}
		return allNotificationForStudNew;
		
		
	}
	@Override
	public List<String> getUserIdofStudent(int notificationId) {
		
		
		 List<String> userList=notificationDao.getUserIdofStudent(notificationId);
     	return userList;
		 
		 }
	@Override
	public String getQuestionFromId(int notificationId) {
		
		 String studQuestion=notificationDao.getQuestionFromId(notificationId);
	     	return studQuestion;
			 
	}


}