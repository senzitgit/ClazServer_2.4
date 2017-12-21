package com.senzit.cyberclaz.server.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.senzit.cyberclaz.server.cognos.MaximumHours;
import com.senzit.cyberclaz.server.cognos.TopPerformer;
import com.senzit.cyberclaz.server.cognos.TopPerformerDao;
import com.senzit.cyberclaz.server.controller.UserController;
import com.senzit.cyberclaz.server.model.AttachmentDao;
import com.senzit.cyberclaz.server.model.ClassEventDetail;
import com.senzit.cyberclaz.server.model.ClazEventDetailDao;
import com.senzit.cyberclaz.server.model.Note;
import com.senzit.cyberclaz.server.model.NoteDao;
import com.senzit.cyberclaz.server.model.Notification;
import com.senzit.cyberclaz.server.model.NotificationDao;
import com.senzit.cyberclaz.server.model.SubjectTeacherDao;
import com.senzit.cyberclaz.server.model.TopSession;
import com.senzit.cyberclaz.server.model.TopSessionDao;
import com.senzit.cyberclaz.server.model.User;
import com.senzit.cyberclaz.server.model.UserDao;
import com.senzit.cyberclaz.server.model.Viewers;
import com.senzit.cyberclaz.server.model.ViewersDao;
import com.senzit.cyberclaz.server.subservice.CyberService;

public class PlayerServiceImp implements PlayerService
{

	public PlayerServiceImp() {}

	static Logger log = Logger.getLogger(UserController.class.getName());

	private ClazEventDetailDao clazEventdetailDao;
	private AttachmentDao attachmentDao;
	private NoteDao noteDao;
	private SubjectTeacherDao subjectTeacherDao;
	private NotificationDao notificationDao;
	private UserDao userDao;
	private ViewersDao viewersDao;
	private TopSessionDao topSessionDao;
	private TopPerformerDao topPerformerDao;


	public void setTopPerformerDao(TopPerformerDao topPerformerDao) {
		this.topPerformerDao = topPerformerDao;
	}

	public void setTopSessionDao(TopSessionDao topSessionDao) {
		this.topSessionDao = topSessionDao;
	}

	public void setViewersDao(ViewersDao viewersDao) {
		this.viewersDao = viewersDao;
	}

	public void setNotificationDao(NotificationDao notificationDao) {
		this.notificationDao = notificationDao;
	}

	public void setSubjectTeacherDao(SubjectTeacherDao subjectTeacherDao) {
		this.subjectTeacherDao = subjectTeacherDao;
	}

	public void setClazEventDetailDao(ClazEventDetailDao clazEventdetailDao) {

		this.clazEventdetailDao = clazEventdetailDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void setAttachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}

	public void setNoteDao(NoteDao noteDao) {
		this.noteDao = noteDao;
	}

	@Override
	public List<Properties> getDateFromSubject(String subjectId) 
	{
		List<Properties> tempDate = new ArrayList<Properties>();
		List<Object[]> dateList = clazEventdetailDao.getDateOfEvent(subjectId);
		for(int i=0;i<dateList.size();i++)
		{
			Object[] ob = dateList.get(i);
			Properties prop = new Properties();
			prop.put("date",ob[0].toString());
			prop.put("clazEventDetailId",ob[1].toString());
			prop.put("courseName",ob[2].toString());
		    prop.put("topicName",ob[3].toString());
			prop.put("teacherName",ob[4].toString());
			prop.put("subject",ob[5].toString());
			tempDate.add(prop);
			//System.out.println(tempDate.toString());
		}
		return tempDate;
	}
//	@Override
//	public List<Properties> getDateFromSubject(String subjectId) 
//	{
//		List<Properties> tempDate = new ArrayList<Properties>();
//		
//		List<Object[]> dateList = clazEventdetailDao.getDateOfEvent(subjectId);
//		
//		for(int i=0;i<dateList.size();i++)
//		{
//			Object[] ob = dateList.get(i);
//			Properties prop = new Properties();
//			int eventId=(int) ob[1];
//			prop.put("date",ob[0].toString());
//			prop.put("clazEventDetailId",ob[1].toString());
//			prop.put("topicName",ob[3].toString());
//			String flag=clazEventdetailDao.getScheduleFlagForEventId(eventId);
//			
//		
//			if("TempSchedule"==flag)
//			{
//				List<String> details = clazEventdetailDao.getTeacherNameForTempSchedule(eventId);
//				prop.put("courseName",details.get(0));
//				prop.put("teacherName",details.get(1));
//				prop.put("subject",details.get(2));
//				
//			}
//			else
//			{
//				
//				prop.put("courseName",ob[2].toString());
//				prop.put("teacherName",ob[4].toString());
//				prop.put("subject",ob[5].toString());
//				
//			}
//					
//			tempDate.add(prop);
//	}
//		
//		return tempDate;
//	}
	@Override
	public List<Properties> getDateFromSubject(String subjectId, Integer batchId) {
		System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
		List<Properties> tempDate = new ArrayList<Properties>();
		List<Object[]> dateList = clazEventdetailDao.getDateOfEvent(subjectId,batchId);
		for(int i=0;i<dateList.size();i++)
		{
		
			Object[] ob = dateList.get(i);
			Properties prop = new Properties();
			prop.put("date",ob[0].toString());
			prop.put("clazEventDetailId",ob[1].toString());
			prop.put("courseName",ob[2].toString());
			prop.put("topicName",ob[3].toString());
			prop.put("teacherName",ob[4].toString());
			prop.put("subject",ob[5].toString());
			tempDate.add(prop);
			
			System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+tempDate.toString());
		}
		return tempDate;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap> getClassDetail(int clazEventDetailId) {

		List<HashMap> details = new ArrayList<HashMap>();
		List<HashMap> generallog = new ArrayList<HashMap>();
		List<Object[]> clazDetails = clazEventdetailDao.getClassDetails(clazEventDetailId);
		
		for(int i=0;i<clazDetails.size();i++)
		{
			Object[] objArray = clazDetails.get(i);
		
			//Properties classProp = new Properties();
			HashMap classProp = new HashMap();
			classProp.put("scheduleId",objArray[0]);
			/////////////////////////////////
			System.out.println("ScheduleId :::"+objArray[0]);
			System.out.println("general log ::: "+objArray[1]);
			////////////////////////////////
			if(objArray[1]!=null)
			{
			String gLog = objArray[1].toString();
			
			try {
				JSONArray jarray = new JSONArray(gLog);
				for(int j=0;j<jarray.length();j++)
				{
					JSONObject jTemp = new JSONObject();
					jTemp = jarray.getJSONObject(j);
					HashMap gProp = new HashMap();
					gProp.put("duration",jTemp.get("duration"));
					gProp.put("raiseHandAnswer",jTemp.get("raiseHandAnswer"));
					gProp.put("profilePic",jTemp.get("profilePic"));
					gProp.put("raiseHandText",jTemp.get("raiseHandText"));
					gProp.put("timestamp",jTemp.get("timestamp"));
					gProp.put("logText",jTemp.get("logText"));
					generallog.add(gProp);
					
				}
			
			}
			 catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			else
			{
				System.out.println("no values for general log  ");
			}
			/////////////////////////////////////////////////
			System.out.println("startTime :::"+objArray[6]);
			System.out.println("endTime ::: "+objArray[7]);
			
			////////////////////////////////////////////////////
	
			Timestamp s=(Timestamp) objArray[6];
			Timestamp e=(Timestamp) objArray[7];
			
		   SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		   
		
			classProp.put("generalLog",generallog);
			classProp.put("chapterName",objArray[2]);
			classProp.put("topicName",objArray[3]);
			classProp.put("ftpLocation",objArray[4]);
			classProp.put("attachmentFlag",objArray[5]);
			classProp.put("startTime",sdf.format(s.getTime()));
			classProp.put("endTime",sdf.format(e.getTime()));
			classProp.put("totalAttendees",objArray[8]);

			details.add(classProp);
			System.out.println(details.toString());

		}
		
		return details;
	}
	

	@Override
	public List<Properties> getAttachmentLink(int clazEventDetailId) {

		List<Properties> Attachmentdetails = new ArrayList<Properties>();
		List<Object[]> attachmentLink=attachmentDao.getAttachmentLink(clazEventDetailId);
		
//		System.out.println("clazeventdetailId"+clazEventDetailId);
//		String profilePic=userDao.getProfilePicFromEventId(clazEventDetailId);
//		System.out.println("PPPPPPPPPPPPPPPPPRRRRRfilepic"+profilePic);
		
		int length=attachmentLink.size();

		for(int i=0;i<length;i++){

			Object[] objArray=attachmentLink.get(i);
			Properties AttachmentProp = new Properties();
			String link="";
			// NEED TO EDIT. BRING ATTACHMENT FROM REBBON
			if((byte)objArray[3]==1){
				
				link=CyberService.getWorkingFolderWebPath("Recorder", clazEventDetailId)+"/"+objArray[0].toString();
				
			}
			else link=objArray[0].toString();
			AttachmentProp.put("attachmentLink",link);
			AttachmentProp.put("attachmentName",objArray[1].toString());
			AttachmentProp.put("attachmentType",objArray[2].toString());
			Attachmentdetails.add(AttachmentProp);

		}
		return Attachmentdetails;
	}

	@Override
	public List<String> getClazNotes(int clazEventDetailId, String userId) {

		System.out.println("CLAZZZZNOTESSSSSSSSSSSSSSSSSSSSSSSSSSS");

		List<String> notes = noteDao.getClazNotes(userId,clazEventDetailId);
		return notes;
	}
////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public List<Properties> getClassEventDetail(String date,int batchId) {
		 List<Object[]> objList=clazEventdetailDao.getClassEventDetail(date,batchId);
		  ArrayList<Properties> propList=new ArrayList<Properties>();
		  for(Object[] obj : objList ){
			  Timestamp s=(Timestamp) obj[5];
		      SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss"); 
		   
		   Properties prop=new Properties();
		   
		   prop.put("eventId", obj[0]);
		   prop.put("courseName", obj[1]);
		   prop.put("teacherName",obj[2]);
		   prop.put("subject",obj[3]);
		   prop.put("topic", obj[4]);
		   prop.put("date",sdf.format(s.getTime()));
		  
		   propList.add(prop);
		  }
		  return propList;
	}

//	@Override
//	public List<Properties> getClassEventDetailFromTopic(String topic,
//			int batchId) {
//		
//		 List<Object[]> objList=clazEventdetailDao.getClassEventDetailFromTopic(topic, batchId);
//		  ArrayList<Properties> propList=new ArrayList<Properties>();
//		  for(Object[] obj : objList ){
//		   
//		   Properties prop=new Properties();
//		   prop.put("eventId", obj[0]);
//		   prop.put("teacher", obj[1]);
//		   prop.put("subject",obj[2]);
//		   prop.put("starttime", obj[3]);
//		   propList.add(prop);
//		  }
//		  return propList;
//	}
	
	//////////////////////////////////////////////////////////////////////////////////
	@Override
	 public List<Properties> getClassEventDetailFromTopic(String topicName,String topicName1,int batchId) {
	  List<Object[]> objList=clazEventdetailDao.getClassEventDetailFromTopic(topicName,topicName1,batchId);
	  ArrayList<Properties> propList=new ArrayList<Properties>();
	  for(Object[] obj : objList ){
	   
	   Properties prop=new Properties();
	   prop.put("eventId", obj[0]);
	   prop.put("teacherName",obj[1]);
	   prop.put("courseName",obj[2]);
	   prop.put("subject",obj[3]);
	   prop.put("topic", obj[4]);
	   prop.put("date", obj[5]);
	   propList.add(prop);
	  }
	  return propList;
	 }
	public List<Properties> getClassEventDetailForTopic(String topic,int batchId)
	{
	
		
	      List<Object[]> objList=clazEventdetailDao.getClassEventDetailForTopic(topic,batchId);
		  ArrayList<Properties> propList=new ArrayList<Properties>();
		  for(Object[] obj : objList ){
			  Timestamp s=(Timestamp) obj[5];
		      SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		   
		   Properties prop=new Properties();
		   prop.put("eventId", obj[0]);
		   prop.put("teacherName",obj[1]);
		   prop.put("courseName",obj[2]);
		   prop.put("subject",obj[3]);
		   prop.put("topic", obj[4]);
		   prop.put("date", sdf.format(s.getTime()));
		   propList.add(prop);
		  }
		  return propList;
	}
	@Override
	 public List<Properties> getClassEventDetailFromDateSubject(String date, String subjectId, int batchId) {
	  
	  List<Object[]> objList=clazEventdetailDao.getClassEventDetailFromDateSubject(date, subjectId, batchId);
	  ArrayList<Properties> propList=new ArrayList<Properties>();
	  for(Object[] obj : objList ){
		  Timestamp s=(Timestamp) obj[4];
	      SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
	    
	   
	   Properties prop=new Properties();
	   prop.put("eventId", obj[0]);
	   prop.put("courseName", obj[1]);
       prop.put("teacherName", obj[2]); 
	   prop.put("topic", obj[3]);
	   prop.put("date", sdf.format(s.getTime()));
	   prop.put("subject",obj[5]);
	   propList.add(prop);
	  }
	  return propList;
	 }
	
	@Override
	 public List<Properties> getClassEventDetailfromSubjectTopic(String subjectId, String topic,int batchId) {
		
	  List<Object[]> objList=clazEventdetailDao.getClassEventDetailFromSubjectTopic(subjectId, topic, batchId);
	  ArrayList<Properties> propList=new ArrayList<Properties>();
	  for(Object[] obj : objList ){
		 Timestamp s=(Timestamp) obj[4];
         SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
         
	   Properties prop=new Properties();
	   prop.put("eventId", obj[0]);
	   prop.put("courseName", obj[1]);
	   prop.put("teacherName", obj[2]);
	   prop.put("topic", obj[3]);
	   prop.put("date", sdf.format(s.getTime()));
	   prop.put("subject",obj[5]);
	   propList.add(prop);
	  }
	  return propList;
	 }
	
	@Override
	 public List<Properties> getClassEventDetailFromDateTopic(String date, String topic,int batchId) {
	  List<Object[]> objList=clazEventdetailDao.getClassEventDetailFromDateTopic(date, topic, batchId);
	  ArrayList<Properties> propList=new ArrayList<Properties>();
	  for(Object[] obj : objList ){
		  Timestamp s=(Timestamp) obj[5];
	       SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		  
	   Properties prop=new Properties();
	   prop.put("eventId", obj[0]);
	   prop.put("courseName", obj[1]);
	   prop.put("teacherName", obj[2]);
	   prop.put("subject", obj[3]);
	   prop.put("topic", obj[4]);
	   prop.put("date",  sdf.format(s.getTime()));
	   propList.add(prop);
	  }
	  return propList;
	 }
	
	@Override
	 public List<Properties> getClassEventDetailFromSDT(String date, String subjectId,
	   String topic,int batchId) {
	  
	  List<Object[]> objList=clazEventdetailDao.getClassEventDetailFromSDT(date, subjectId, topic, batchId);
	  ArrayList<Properties> propList=new ArrayList<Properties>();
	  for(Object[] obj : objList ){

   Timestamp s=(Timestamp) obj[4];
	      SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

	   Properties prop=new Properties();
	   prop.put("eventId", obj[0]);
	   prop.put("teacherName", obj[1]);
	   prop.put("courseName", obj[2]);
	   prop.put("topic", obj[3]);
	   prop.put("date",sdf.format(s.getTime()));
	   prop.put("subject",obj[5]);
	   propList.add(prop);
	  }
	  return propList;
	 }
	
	
	//Teacher

	@Override
	public List<Properties> getClassEventDetail(String date) {
	
		List<Object[]> objList=clazEventdetailDao.getClassEventDetail(date);
		  ArrayList<Properties> propList=new ArrayList<Properties>();
		 
		  
		  
		   System.err.println("playerView"+objList.toString());
		  
		 for(Object[] obj : objList ){
			  Timestamp s=(Timestamp) obj[5];
		      SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			  
		   Properties prop=new Properties();
		   prop.put("eventId", obj[0]);
		   prop.put("courseName", obj[1]);
		   prop.put("subject",obj[2]);
		   prop.put("topic", obj[3]);
		   prop.put("teacherName", obj[4]);
		   prop.put("date",sdf.format(s.getTime()));
		   System.out.println("playerView"+prop.toString());
		   
		   propList.add(prop);
		  }
		  return propList;
	}

	@Override
	public List<Properties> getClassEventDetailFromTopic(String topicName) {
		
		 List<Object[]> objList=clazEventdetailDao.getClassEventDetailFromTopic(topicName);
		  ArrayList<Properties> propList=new ArrayList<Properties>();
		  for(Object[] obj : objList ){
			  Timestamp s=(Timestamp) obj[3];
		      SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		      
		   Properties prop=new Properties();
		   prop.put("eventId", obj[0]);
		  // prop.put("courseName", obj[1]);
		   prop.put("subject",obj[1]);
		   prop.put("topic", obj[2]);
		   prop.put("date",sdf.format(s.getTime()));
		   prop.put("teacherName", obj[4]);
		   
		  // prop.put("startTime", obj[3]);
		   propList.add(prop);
		  }
		  return propList;
	}

	@Override
	public List<Properties> getClassEventDetailFromDateSubject(String date,
			String subjectId) {
		
		List<Object[]> objList=clazEventdetailDao.getClassEventDetailFromDateSubject(date, subjectId);
		  ArrayList<Properties> propList=new ArrayList<Properties>();
		  for(Object[] obj : objList ){
			  Timestamp s=(Timestamp) obj[3];
		      SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			  
		   Properties prop=new Properties();
		   prop.put("eventId", obj[0]);
		   prop.put("courseName", obj[1]);
		   prop.put("topic", obj[2]);
		   prop.put("date",  sdf.format(s.getTime()));
		   prop.put("teacherName", obj[4]);
		   prop.put("subject",obj[5]);
		   propList.add(prop);
		  }
		  return propList;
	}

	@Override
	public List<Properties> getClassEventDetailfromSubjectTopic(
			String subjectId, String topic) {
		
		 List<Object[]> objList=clazEventdetailDao.getClassEventDetailFromSubjectTopic(subjectId, topic);
		  ArrayList<Properties> propList=new ArrayList<Properties>();
		  for(Object[] obj : objList ){
			  
			  Timestamp s=(Timestamp) obj[3];
		       SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		   Properties prop=new Properties();
		   prop.put("eventId", obj[0]);
		   prop.put("courseName", obj[1]);
		   prop.put("topic", obj[2]);
		   prop.put("date",  sdf.format(s.getTime()));
		   prop.put("teacherName", obj[4]);
		   prop.put("subject",obj[5]);
		   propList.add(prop);
		  }
		  return propList;
	}

	@Override
	public List<Properties> getClassEventDetailFromDateTopic(String date,
			String topic) {
		List<Object[]> objList=clazEventdetailDao.getClassEventDetailFromDateTopic(date, topic);
		  ArrayList<Properties> propList=new ArrayList<Properties>();
		  for(Object[] obj : objList ){
			  Timestamp s=(Timestamp) obj[4];
		       SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		   Properties prop=new Properties();
		   prop.put("eventId", obj[0]);
		   prop.put("courseName", obj[1]);
		   prop.put("subject", obj[2]);
		   prop.put("topic", obj[3]);
		   prop.put("date",  sdf.format(s.getTime()));
		   prop.put("teacherName", obj[5]);
		   propList.add(prop);
		  }
		  return propList;
	}

	@Override
	public List<Properties> getClassEventDetailFromSDT(String date,
			String subjectId, String topic) {
		
		List<Object[]> objList=clazEventdetailDao.getClassEventDetailFromSDT(date, subjectId, topic);
		  ArrayList<Properties> propList=new ArrayList<Properties>();
		  for(Object[] obj : objList ){
			  Timestamp s=(Timestamp) obj[4];
		      SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

			  
		   Properties prop=new Properties();
		   prop.put("eventId", obj[0]);
		   prop.put("courseName", obj[1]);
		   prop.put("topic", obj[2]);
		   prop.put("teacherName", obj[3]);
		   prop.put("date",sdf.format(s.getTime()));
		   prop.put("subject",obj[5]);
		   propList.add(prop);
		  }
		  return propList;
	}

	@Override
	public List<String> teacherIdForClazEventDetailId(int clazEventDetailId) {
	
		return subjectTeacherDao.teacherIdForClazEventDetailId(clazEventDetailId);
		
	
	}

	@Override
	public int saveAskDoubtsInDb(String userId, String teacherName,
			String doubtText, int clazEventDetailId, Timestamp reminderTime,
			String doubt) {
		ClassEventDetail eventObj = new ClassEventDetail();
		eventObj.setClassEventDetailId(clazEventDetailId);

		Notification notification = new Notification();
		notification.setCreatedOn(reminderTime);
		notification.setNotification(doubtText);
		notification.setFromUserId(userId);
		notification.setToUserId(teacherName);
		notification.setClassEventDetail(eventObj);
		notification.setReminderOrRaiseHandFlag(doubt);

		int raiseValue = notificationDao.saveReminderAsNotification(notification);	
		if(raiseValue!=0)
		{
			log.debug("student's doubt Saved Successfully");
			return raiseValue;
		}
		else
		{
			log.debug("ask a doubt error");
			return raiseValue;
		}

		
	}

	@Override
	public String getProfilePicFromEventId(int clazEventDetailId) {
		
		String profilePic=userDao.getProfilePicFromEventId(clazEventDetailId);
		return profilePic;
	}

	@Override
	public List<Properties> getAnswerForRaiseHandDoubt(int clazEventDetailId) {
		
		 List<Object[]> objList=notificationDao.getAnswerForRaiseHandDoubt(clazEventDetailId);
		 ArrayList<Properties> propList=new ArrayList<Properties>();
		 List<Properties> nullList = new ArrayList<Properties>();
		 if(objList.size()!=0)
		 {
			
		  for(Object[] obj : objList )
		  {
		  if(obj[3].equals("RH") || obj[3].equals("doubt"))
		   {
		   Properties prop=new Properties();
		   String studentName=(String) obj[0];
		   String profilePic= userDao.getProfilePic(studentName);
		   prop.put("fromUser", studentName);
		   prop.put("notificationQuestion", obj[1]);
		   prop.put("notificationAnswer", (obj[2]==null || obj[2]=="0")?"Unanswered":obj[2]);
		   prop.put("raiseHandOrDoubt",obj[3]);
		   prop.put("profilePic",profilePic);
		   propList.add(prop);
		    }
		  
		   }
		  return propList;
		 }
		 else
		 {
			log.debug("awaiting for reply"); 
			return nullList;
		 }
		
	}



	@Override
	public boolean saveViewersDetailsInDb(String userId,int clazEventDetailId,String teacherId) {
		
		ClassEventDetail eventObj=new ClassEventDetail();
		eventObj.setClassEventDetailId(clazEventDetailId);

		User userObj=new User();
		userObj.setUserId(userId);
		Integer value=viewersDao.getViewersIdofThisClassEvent(userId,clazEventDetailId);
        Viewers v=new Viewers();
        if(value==0)
        {
        v.setClassEventDetail(eventObj);
		v.setUser(userObj);
		v.setTeacherName(teacherId);
		
		Integer i=viewersDao.saveViewersDetailsInDb(v);
		if(i!=0)
		{
			System.out.println("viewers details saved");
		}
		
		else
		{
			System.out.println("viewers details can't saved");
		}
		return true;
        }
        else
        {
        	return false;
        }
	
	}

	@Override
	public List<String> getViewersList(int clazEventDetailId) {
		
		List<String> viewersList = viewersDao.getViewersList(clazEventDetailId);
		List<String> viewer = new ArrayList<String>();
		for(int i=0;i<viewersList.size();i++)
		{
//			Object ob[] = viewersList.get(i);
			String userId=viewersList.get(i);

			viewer.add(userId);

		}

		return viewer;

	
		
		
	}

	@Override
	public void saveRatingDetailsInDb(int clazEventDetailId)
	{

			     ClassEventDetail ce=new ClassEventDetail();
				 ce.setClassEventDetailId(clazEventDetailId);
			  TopSession ts=new TopSession();
			 Integer topSessionId=topSessionDao.getTopSessionIdFromEventId(clazEventDetailId);
		      System.out.println("topSessionId"+topSessionId);
		      if(topSessionId==0)
			  {
		    	 ts.setClassEventDetail(ce);
		    	 ts.setLike(1);
				 Integer i=topSessionDao.saveRatingDetailsInDb(ts);
				
			 }
		     else
		     {
		 		List<Integer> viewerRateCount=topSessionDao.getLikeCountTillNow(clazEventDetailId);
			    System.out.println("LikeCount"+viewerRateCount);
		    	int rateCount=(viewerRateCount.get(0))+1;
		    	
		    	Integer count=topSessionDao.updateViewerLikeCount(clazEventDetailId,rateCount);
		     }
//				 
					
	
	}

	@Override
	public int getUserCountValue() {
		
	List<Integer> value=viewersDao.getUserCountValue();

		int count=value.get(0);
	
	
		return count;
	}

	@Override
	public String getTeacherIdFromClazEventId(int clazEventDetailId) {
		return userDao.getTeacherIdFromClazEventId(clazEventDetailId); 
		
	}

	@Override
	public void saveDislikeRatingDetailsInDb(int clazEventDetailId) {
		 ClassEventDetail ce=new ClassEventDetail();
		 ce.setClassEventDetailId(clazEventDetailId);
	      TopSession ts=new TopSession();
	 	 Integer topSessionId=topSessionDao.getTopSessionIdFromEventId(clazEventDetailId);
	 	 if(topSessionId==0)
	 	 {
	 		 
	 		 ts.setClassEventDetail(ce);
	    	 ts.setDisLike(1);
			 Integer i=topSessionDao.saveRatingDetailsInDb(ts); 
	 		 
	 	 }
	 	 else
	 	 {
	 		List<Integer> viewerRateCount=topSessionDao.getDisLikeCountTillNow(clazEventDetailId);
		    System.out.println("LikeCount"+viewerRateCount);
	    	int rateCount=(viewerRateCount.get(0))+1;
	    	
	    	Integer count=topSessionDao.updateViewerDisLikeCount(clazEventDetailId,rateCount);
	 	 }
	 	 
	      
	}

	@Override
	public Properties getViewerRatingCount(int clazEventDetailId) {
		
	
		List<Object[]> viewersRating = topSessionDao.getLikeCountNow(clazEventDetailId);
		Properties viewer = new Properties();
		if(!viewersRating.isEmpty())
		{
			Object ob[] = viewersRating.get(0);
			int like=(int) ob[0];
			int disLike=(int) ob[1];
			viewer.put("like", like);
			viewer.put("disLike", disLike);
			
		}
		else
		{
			viewer.put("like", 0);
			viewer.put("disLike", 0);
		}

		return viewer;

	
	}

	@Override
	public void updateInViewerTable(int clazEventDetailId) {
		
//		ClassEventDetail ce=new ClassEventDetail();
//		ce.setClassEventDetailId(clazEventDetailId);
		Viewers v=new Viewers();
		 Integer viewerId=viewersDao.getViewersStatus(clazEventDetailId);
	 	 if(viewerId==0)
	 	 {
	 		 int rateValue=1;		 
			 Integer i=viewersDao.updateInViewerTable(clazEventDetailId,rateValue); 
	 		 
	 	 }
	 	 else
	 	 {
	 		List<Integer> viewerRateCount=viewersDao.getViewerRateCountTillNow(clazEventDetailId);
		    System.out.println("LikeCount"+viewerRateCount);
	    	int rateCount=(viewerRateCount.get(0))+1;
	    	
	    	Integer count=viewersDao.updateInViewerTable(clazEventDetailId,rateCount);
	 	 }
		 
	}


}
