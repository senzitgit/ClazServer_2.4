package com.senzit.cyberclaz.server.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.senzit.cyberclaz.server.cognos.MaximumHours;
import com.senzit.cyberclaz.server.cognos.MaximumHoursDao;
import com.senzit.cyberclaz.server.controller.UserController;
import com.senzit.cyberclaz.server.model.Attachment;
import com.senzit.cyberclaz.server.model.AttachmentDao;
import com.senzit.cyberclaz.server.model.Attendance;
import com.senzit.cyberclaz.server.model.AttendanceDao;
import com.senzit.cyberclaz.server.model.AttendanceData;
import com.senzit.cyberclaz.server.model.ClassEventDetail;
import com.senzit.cyberclaz.server.model.ClazEventDetailDao;
import com.senzit.cyberclaz.server.model.CourseBatch;
import com.senzit.cyberclaz.server.model.CourseSubject;
import com.senzit.cyberclaz.server.model.CourseSubjectDao;
import com.senzit.cyberclaz.server.model.CyberInit;
import com.senzit.cyberclaz.server.model.Log;
import com.senzit.cyberclaz.server.model.LogDao;
import com.senzit.cyberclaz.server.model.MultimediaDao;
import com.senzit.cyberclaz.server.model.Note;
import com.senzit.cyberclaz.server.model.NoteDao;
import com.senzit.cyberclaz.server.model.Notification;
import com.senzit.cyberclaz.server.model.NotificationDao;
import com.senzit.cyberclaz.server.model.Rebbon;
import com.senzit.cyberclaz.server.model.RebbonDao;
import com.senzit.cyberclaz.server.model.Schedule;
import com.senzit.cyberclaz.server.model.SchedulerDao;
import com.senzit.cyberclaz.server.model.StudentBatchDao;
import com.senzit.cyberclaz.server.model.User;
import com.senzit.cyberclaz.server.model.UserDao;
import com.senzit.cyberclaz.server.subservice.CyberService;



public class RecordServiceImp implements RecordService{

	static Logger log = Logger.getLogger(UserController.class.getName());

	private RebbonDao rebbonDao;
	private ClazEventDetailDao clazEventdetailDao;
	private SchedulerDao schedulerDao;
	private UserDao userDao;
	private LogDao logDao;
	private NoteDao noteDao;
	private AttachmentDao attachmentDao;
	private CourseSubjectDao courseSubjectDao;
	private NotificationDao notificationDao;
	private AttendanceDao attendanceDao;
	private StudentBatchDao studentBatchDao;
	private MultimediaDao multimediaDao;
	private MaximumHoursDao maximumHoursDao;

	public void setMultimediaDao(MultimediaDao multimediaDao) {
		this.multimediaDao = multimediaDao;
	}

	public void setAttendanceDao(AttendanceDao attendanceDao) {
		this.attendanceDao = attendanceDao;
	}

	public void setNotificationDao(NotificationDao notificationDao) {
		this.notificationDao = notificationDao;
	}

	public void setCourseSubjectDao(CourseSubjectDao courseSubjectDao) {
		this.courseSubjectDao = courseSubjectDao;
	}

	public void setAttachmentDao(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}

	public void setNoteDao(NoteDao noteDao) {
		this.noteDao = noteDao;
	}

	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setSchedulerDao(SchedulerDao schedulerDao) {
		this.schedulerDao = schedulerDao;
	}

	public void setRebbonDao(RebbonDao rebbonDao) {

		this.rebbonDao = rebbonDao;
	}

	public void setClazEventDetailDao(ClazEventDetailDao clazEventdetailDao) {

		this.clazEventdetailDao = clazEventdetailDao;
	}

	public void setStudentBatchDao(StudentBatchDao studentBatchDao) {
		this.studentBatchDao = studentBatchDao;
	}

	public void setMaximumHoursDao(MaximumHoursDao maximumHoursDao) {
		this.maximumHoursDao = maximumHoursDao;
	}

	@Override
	public String getRebbonLink(String rebbonId) {

		Rebbon rebObj = rebbonDao.getRebbonLink(rebbonId);
		String rebbonLink = rebObj.getRebbonLink();
		return rebbonLink;
	}

//	@Override
//	public Properties checkForLiveSession(int courseBatchId) {
//
//		List<Object[]> liveList = clazEventdetailDao.checkForLive(courseBatchId);
//		Properties prop=new Properties();
//		System.out.println("liveListSize"+liveList.size());
//		if(!(liveList.isEmpty()))
//		{
//			Object[] ob = liveList.get(0);
//
//			if(ob[8]==null)
//			{
//				Timestamp s=(Timestamp) ob[6];
//				
//			   SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//
//				prop.put("TeacherId",ob[0]);
//				prop.put("TeacherName", ob[1]);
//				prop.put("ProfilePicURL",ob[2]);
//				prop.put("Subject", ob[3]);
//				prop.put("CurrentScheduleId",ob[4]);
//				
//				prop.put("ClazEventDetailId", ob[5]);
//				prop.put("StartTime",sdf.format(s.getTime()));
//				prop.put("Topicname", ob[7]);
//				prop.put("Chaptername", ob[9]);
//				
//				System.out.println("classeventId " +ob[5]);
//			
//			}
//		}		
//
//		return prop;
//
//	}
	@Override
	public Properties checkForLiveSession(int courseBatchId) {

		List<Object[]> liveList = clazEventdetailDao.checkForLive(courseBatchId);
		Properties prop=new Properties();
		System.out.println("liveListSize"+liveList.size());
		
		
		
//		if(!(liveList.isEmpty()))
//		{
//			Object[] ob = liveList.get(0);
			for(int i=0;i<liveList.size();i++)
			{
				 Object[] objArray=liveList.get(i);
			 
				Timestamp s=(Timestamp) objArray[6];
				
			   SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

				prop.put("TeacherId",objArray[0]);
				prop.put("TeacherName", objArray[1]);
				prop.put("ProfilePicURL",objArray[2]);
				prop.put("Subject", objArray[3]);
				prop.put("CurrentScheduleId",objArray[4]);
				
				prop.put("ClazEventDetailId", objArray[5]);
				prop.put("StartTime",sdf.format(s.getTime()));
				prop.put("Topicname", objArray[7]);
				prop.put("Chaptername", objArray[8]);
				
				System.out.println("classeventId " +objArray[5]);
				System.out.println("Live CLass "+ prop);
			
		}		

		return prop;

	}
	
	
	
	
	@Override
	public Properties checkForLiveSessionManual() {

		List<Object[]> liveList = clazEventdetailDao.checkForLiveManual();
		Properties prop=new Properties();
		

		System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
		System.out.println(liveList);
		return prop;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public List<String> getTeacherInClass(String rebbonId) {

		List<Object[]> teacherDetails = rebbonDao.getTeacherInClaz(rebbonId);
		List<String> teacherData = new ArrayList<String>();
		for(int i=0;i<teacherDetails.size();i++)
		{
			Object ob[] = teacherDetails.get(i);

			teacherData.add(ob[1].toString());

		}

		return teacherData;

	}
//	List<String> getTeacherNameForBatch(int batchId)
//	{
//		List<Object[]> teacherDetails = rebbonDao.getTeacherInClaz(rebbonId);
//		List<String> teacherData = new ArrayList<String>();
//		for(int i=0;i<teacherDetails.size();i++)
//		{
//			Object ob[] = teacherDetails.get(i);
//
//			teacherData.add(ob[1].toString());
//
//		}
//
//		return teacherData;
//
//	}

	@Override
	public int createNewClassEvent(int scheduleId, String chapter,
			String topic,String scheduleFlag, String subjectName,
			String courseName,String teacherName,String teacherId, String profilePic) {

		ClassEventDetail classEvent=new ClassEventDetail();
		Schedule schedule = new Schedule();
		schedule.setScheduleId(scheduleId);
		classEvent.setSchedule(schedule);
	
		classEvent.setChapterName(chapter);
		classEvent.setTopicName(topic);
		classEvent.setStartTime(new Timestamp(System.currentTimeMillis()));
		classEvent.setAttachmentFlag(false);
		classEvent.setScheduleFlag(scheduleFlag);
		classEvent.setSubjectName(subjectName);
		classEvent.setCourseName(courseName);
		classEvent.setTeacherName(teacherName);
		classEvent.setTeacherId(teacherId);
		classEvent.setTeacherPic(profilePic);
		classEvent.setClazFlag(0);
		return clazEventdetailDao.saveNewClassEvent(classEvent);


	}

	@Override
	//////////////////////need to edit if needed,coursebatchid from rebbon/////////////////
//	public Properties getAttendance(int scheduleId) {
		public Properties getAttendance(String rebbonId) {
		Properties prop=new Properties();
		
		ArrayList<Properties> localList=new ArrayList<Properties>();
		ArrayList<Properties> remoteList=new ArrayList<Properties>();
		ArrayList<Properties> offlineList=new ArrayList<Properties>();

		//List<Integer> courseBatchIdList = schedulerDao.getCourseBatch(scheduleId);
		List<Integer> courseBatchIdList =schedulerDao.getCourseBatchIdForTeacher(rebbonId);
		if(!(courseBatchIdList.isEmpty()))
		{
			int batchId=courseBatchIdList.get(0);
			AttendanceData a=new AttendanceData();
			a.putNewData(batchId, true);

			CourseBatch batch=new CourseBatch();
			batch.setCourseBatchId(batchId);
			List<Object[]> FullStudentList=userDao.getUserNameFromBatch(batch);
			int allStudentNo=FullStudentList.size();
			Log logObj = null;
			for(int i=0;i<allStudentNo;i++)
			{
				Properties propIn= new Properties();
				Object[] ob = FullStudentList.get(i);
				List<String> userNameList=userDao.getUserName(ob[0].toString());
				//String name=userNameList.get(0)
				if(userNameList.isEmpty())
					propIn.put("name", "");
				else
				
						propIn.put("name", userNameList.get(0));
						String userId=ob[0].toString();
						propIn.put("proPic", ob[1].toString());
						propIn.put("userId", userId);

						logObj=logDao.getUserStatus(userId);
						if(logObj==null)
							offlineList.add(propIn);
						else if(logObj.isLoginStatus()==true)
							localList.add(propIn);
						else if(logObj.isLoginStatus()==false)
							remoteList.add(propIn);
				
					
					
				

			}

			prop.put("local", localList);
			prop.put("remote", remoteList);
			prop.put("offline", offlineList);


		}


		return prop;
	}

	@Override
	public List<String> getTeacherFromBatch(int courseBatchId) {
		
		
		
		
	System.err.println("InsideRaisehandRecordService");
	System.err.println(courseBatchId);
		

		//String temp ="2001";
		List<Object[]> teacherName=logDao.getTeacherFromBatch(courseBatchId);
		List<String> teacher = new ArrayList<String>();
		if(!(teacherName.isEmpty()))
		{
			Object ob[] = teacherName.get(0);
			String  temp=ob[0].toString();
			teacher.add(temp);
			
		}
//		teacher.add(temp);

		return teacher;

	}


	public int updateAttachmentFlag(int classEventDetailId) {
		int i=0;
		ClassEventDetail eventObj = new ClassEventDetail();
		eventObj = clazEventdetailDao.getEventDetials(classEventDetailId);
		if(!(eventObj==null))
		{
			//			eventObj.setAttachmentFlag(true);
			i=clazEventdetailDao.setAttachmentFlag(true,classEventDetailId);
		}

		return i;
	}

	@Override
	public boolean updateDB(int classEventDetailId, String generalLog,int totalAttendees) 
	{
		
		System.out.println("Saving values in DB Generallog:::::"+generalLog);

		int status=clazEventdetailDao.updateDB(classEventDetailId, generalLog,totalAttendees);

		if(status>0)
			return true;

		return false;

	}

	@Override
	public boolean insertClazNote(int classEventId, String userId,
			String clazNoteJson) {
		
		
		if(!clazNoteJson.isEmpty())
		{
	    ClassEventDetail eventObj=new ClassEventDetail();
		eventObj.setClassEventDetailId(classEventId);

		User userObj=new User();
		userObj.setUserId(userId);

		Note noteObj= new Note();
		noteObj.setClassEventDetail(eventObj);
		noteObj.setUser(userObj);
		noteObj.setNotes(clazNoteJson);
		Integer i=noteDao.insertStudentClazNote(noteObj);
		if(i!=0)
		{
			return true;
		}
		
		else
		{
			return false;
		}
		}
		else
		{
			return false;
		}
     }



	@Override
	public void SaveAttachmentDetails(int classEventDetailId,
			String attachmentName, String attachmentLink,
			String attachmentType, Timestamp createdOn,byte att) 
	{
		//System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+att);
		
		
		
		ClassEventDetail eventObj = new ClassEventDetail();
		eventObj.setClassEventDetailId(classEventDetailId);
		Attachment attachmentObj = new Attachment();
		attachmentObj.setAttachmentName(attachmentName);
		attachmentObj.setAttachmentLink(attachmentLink);
		attachmentObj.setAttachmentType(attachmentType);
		attachmentObj.setCreatedOn(createdOn);
		attachmentObj.setClassEventDetail(eventObj);
		attachmentObj.setType(att);
		int value = attachmentDao.saveAttachment(attachmentObj);
		if(value!=0)
		{
			log.debug("AttachmetDetails Saved");
		}
		else
		{
			log.debug("Save AttachmetDetails Failed");
		}

	}

	@Override
	public void saveRaiseHandDetails(String from, String teacherId,
			String content,int classEventDetailId,Timestamp createdOn) 
	{
		
		
		
		ClassEventDetail eventObj = new ClassEventDetail();
		eventObj.setClassEventDetailId(classEventDetailId);

		Notification notification = new Notification();
		notification.setCreatedOn(createdOn);
		notification.setNotification(content);
		notification.setFromUserId(from);
		notification.setToUserId(teacherId);
		notification.setClassEventDetail(eventObj);
		notification.setReminderOrRaiseHandFlag("RH");

		int raiseValue = notificationDao.saveReminderAsNotification(notification);	
		if(raiseValue!=0)
		{
			log.debug("RaiseHand Saved Successfully");
//			return raiseValue;
		}
		else
		{
			log.debug("RaiseHand error");
//			return 0;
		}
	

	}
	

	@Override
	public void saveReminderNote(String teacherId, String tempRnote,
			int classEventDetailId, Timestamp reminderTime) {

		System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSREMINDERNOTESSSSSSSSSSSSSSSSS");
		ClassEventDetail eventObj = new ClassEventDetail();
		eventObj.setClassEventDetailId(classEventDetailId);
		String ss="";
		Notification notification = new Notification();
		notification.setCreatedOn(reminderTime);
		notification.setNotification(tempRnote);
		notification.setFromUserId(teacherId);
		notification.setToUserId(teacherId);
		notification.setClassEventDetail(eventObj);
		notification.setReminderOrRaiseHandFlag("RN");

		int notificationValue = notificationDao.saveReminderAsNotification(notification);
		
		
		////////////////////////////////  Edited but commented on 19-may.Reminder note not getting during playback
//		User user = new User();
//		user.setUserId(teacherId);
//		Note obj = new Note();
//		obj.setClassEventDetail(eventObj);
//		obj.setNotes(tempRnote);
//		obj.setUser(user);		
//		noteDao.insertStudentClazNote(obj);
		////////////////////////////////
		
		
		if(notificationValue!=0)
		{
			log.debug("Notification Saved Succesfully");
		}
		else
		{
			log.debug("Notification Error!!");
		}
	}

	@Override
	public int getCourseSubjectId(int scheduleId)
	{
		int courseSubjectId=courseSubjectDao.getCourseSubjectId(scheduleId);
		return courseSubjectId;
	}

	@Override
	public void saveAttendanceDetails(String userId,int courseSubId,int classEventDetailId,
			int attendedSession, int totalNumberOfSession,String month) {

		User userObj=new User();
		userObj.setUserId(userId);
		
		CourseSubject cs=new CourseSubject();
		cs.setCourseSubjectId(courseSubId);

		ClassEventDetail csObj = new ClassEventDetail();
		csObj.setClassEventDetailId(classEventDetailId);

		Attendance attendance = attendanceDao.getAttendance(userId);

		if(attendance==null)
		{
			Attendance atObj = new Attendance();
			atObj.setAttendedSession(attendedSession);
			atObj.setTotalNumberOfSession(totalNumberOfSession);
			atObj.setCourseSubject(cs);
			atObj.setCurrentMonth(month);
			atObj.setUser(userObj);

			attendanceDao.saveAttendance(atObj);
		}
		else
		{
			int atSession = attendance.getAttendedSession();
			attendance.setAttendedSession(atSession+1);

			attendanceDao.updateAttendance(attendance);
		}



	}

	@Override
	public int getChangedSchedule(String teacherId, String subjectId,int CourseBatchId)
	{
		List<Integer> schedule =  schedulerDao.getNewSchedule(teacherId,subjectId,CourseBatchId);
		//		Object[] ob = schedule.get(0);
		//		Integer scheduleId = (Integer) ob[0];
		Integer scheduleId = schedule.get(0);
		return scheduleId;

	}

	public boolean saveUploadedImage(MultipartFile file,String fileName,
			 int classEventDetailId,byte imageType,String name,String time,String type) {
	
//		
//		file1 = file1.replaceAll(" ", "+");
//		 String[] parts = file1.split("\\,");
//		 
//		 String beforeFirstDot = parts[1];
//		byte[] imageByteArray = Base64.decodeBase64(beforeFirstDot);
////		FileOutputStream imageOutFile = new FileOutputStream(CyberInit.webAppFolder+File.separatorChar+"ProfilePic"+File.separatorChar+userId+"."+"jpg");
//		try {
//			imageOutFile.write(imageByteArray);
//			 webLink = CyberInit.ipAddress+"/ProfilePic/"+userId+"."+"jpg";
//			 userDao.updateProPic(userId, webLink);	
//		
		File savedFile=new File(CyberService.getWorkingFolderPath( "Recorder",classEventDetailId)
				+File.separatorChar+fileName);
	
	try {
	 file.transferTo(savedFile);

		} catch (IllegalStateException | IOException e) {
			
			e.printStackTrace();
			return false;
		}
	    System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+savedFile);
		
		Integer i=attachmentDao.saveImageDetails(fileName,classEventDetailId,imageType,name,time,type);
		if(i!=0)
		{
			
			log.debug("image details saved successfully");
			return true;
		}
		else
		{
		
			log.debug("error");
			return false;
		}
		
	
	}

	@Override
	public List<String> getStudentList(int scheduleId,int clazEventDetailId) {
		
		
		return studentBatchDao.getStudentList(scheduleId,clazEventDetailId);

	}

	@Override
	public String getRebbonIdFromUser(String userId) {
		return rebbonDao.getRebbonIdFromUser(userId);
	}

	@Override
	public int getClazEventDetailId(String question,String fromUser) {
		
		return notificationDao.getClazEventDetailId(question,fromUser);
		
	}

	@Override
	public void updateAnswerForRaiseHandandDoubtinDb(
			  int notificationId,String answer,String answeredQuestion) 
	{

		int value=notificationDao.updateAnswerForRaiseHandandDoubtinDb(notificationId,answer,answeredQuestion);
		if(value!=0)
	    {
		log.debug(" answer Saved Successfully");
        }
	  else
	   {
		log.debug(" answer Saving error");
	    }
		
//	ClassEventDetail eventObj = new ClassEventDetail();
//	eventObj.setClassEventDetailId(classEventId);
//	Notification notification = new Notification();
//	notification.setNotificationAnswer(answer);
//	notification.setNotificationFlag(flag);
//	notification.setClassEventDetail(eventObj);
//	
//	notificationDao.updateAnswerForRaiseHandandDoubtinDb(notification);	
//
//   log.debug(" answer Saved Successfully");

		
	}

	@Override
	public int deleteQuestionFromDB(String raiseHandAskaDoubtQuestion,String fromUserId) {
		
		Notification n=new Notification();
		n.setNotification(raiseHandAskaDoubtQuestion);
		n.setFromUserId(fromUserId);
		int value=notificationDao.deleteQuestionFromDB(n);
		if(value!=0)
		{
			return 1;
		}
		else
		{
			return 0;
		}
		
		
	}

	@Override
	public void updateNotificationFlag(int notificationId,
			 String answerClick) {
	
		
		int result=notificationDao.updateNotificationFlag(notificationId,answerClick);	
		if(result!=0)
	    {
		log.debug(" updated flag Successfully");
        }
	  else
	   {
		log.debug(" error in updation");
	    }
		
	}

	@Override
	public  List<Properties> getAttachmentDetailsUploadedViaPortal(String teacherId) {
		
		List<Object[]> attachmentDetails=multimediaDao.getAttachmentDetailsUploadedViaPortal(teacherId);
		List<Properties> attachments = new ArrayList<Properties>();
		for(int i=0;i<attachmentDetails.size();i++)
		{
			Object ob[] = attachmentDetails.get(i);
			Properties prop = new Properties();
			String  attachmentName=ob[0].toString();
			String  attachmentLink=ob[1].toString();
			String  attachmentType=ob[4].toString();
			prop.put("attachmentName", attachmentName);
			prop.put("attachmentLink", attachmentLink);
			prop.put("documentType", attachmentType);
			attachments.add(prop);
		}
//		teacher.add(temp);

		return attachments;	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap> getRaiseHandQuestionAndAnswer(int clazEventId,String userId)
	{
		
		List<HashMap> allNotificationForStud=new ArrayList<HashMap>();
		List<Object[]> notification=notificationDao.getAllNotificationForCurrentClass(clazEventId,userId);
		int length=notification.size();
	
		for(int i=0;i<length;i++)
		{
			 Object[] objArray=notification.get(i);
			 String obj=(String) objArray[4];
			
			 String teacherId=(String) objArray[1];
			 Timestamp s=(Timestamp) objArray[3];
		      SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			 HashMap result=new HashMap();
			 
			 if((!"RHQueue".equals(obj)) && (!"Answeredquestion".equals(obj)) && (!"Reminder".equals(obj)) && (!"doubt".equals(obj)))
				 ////////////////////////websocket issue/////////////////
			// if((!"Answeredquestion".equals(obj)) && (!"Reminder".equals(obj)) && (!"doubt".equals(obj)))
				{
			result.put("notificationId", (Integer) objArray[0]);
			result.put("FromUser", teacherId);
			result.put("notificationQuestion", (String) objArray[2]);
			result.put("createdOn", sdf.format(s.getTime()));
	
			
	    	allNotificationForStud.add(result);
			}
		}
		
		return allNotificationForStud;	
		
		
		
		
	}

	@Override
	public void saveAttendanceForUser(String userId, int classEventDetailId) {
		

		User userObj=new User();
		userObj.setUserId(userId);
		
	
		ClassEventDetail csObj = new ClassEventDetail();
		csObj.setClassEventDetailId(classEventDetailId);

		Attendance att=new Attendance();
		att.setUser(userObj);
		att.setClassEventDetail(csObj);
		
		attendanceDao.saveAttendance(att);
		
		
		
	}

	@Override
	public void saveRaiseHandQueuedDetailsInDb(String raiseHandText, String raiseHandAnswer,
			int classEventDetailId) {
		
//		
//		ClassEventDetail eventObj = new ClassEventDetail();
//    	eventObj.setClassEventDetailId(classEventDetailId);
//		Notification notification = new Notification();
//		Calendar calendar = Calendar.getInstance();
//        java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTime().getTime());
//		notification.setCreatedOn(timestamp);
//		notification.setNotification(raiseHandText);
//		notification.setNotificationAnswer(raiseHandAnswer);
//		notification.setFromUserId(userId);
//		notification.setToUserId(userId1);
//		notification.setClassEventDetail(eventObj);
//		notification.setReminderOrRaiseHandFlag("RH");
//		notification.setNotificationFlag("RHQanswer");
//		notificationDao.updateRHQueuedQuestionAnswer(notification);
//		
//		log.debug("RaiseHand Saved Successfully");
	
//	
		int value=notificationDao.saveRaiseHandQueuedDetailsInDb(raiseHandText,raiseHandAnswer,classEventDetailId);
		if(value!=0)
	    {
		log.debug(" RaiseHandqueue answer Saved Successfully");
        }
	  else
	   {
		log.debug(" answer Saving error");
	    }
//		
	}
//	@Override
//	public void saveRaiseHandQueuedDetailsInDb(int raiseHandId, String raiseHandAnswer) {
//		
//		
//		ClassEventDetail eventObj = new ClassEventDetail();
//    	eventObj.setClassEventDetailId(classEventDetailId);
//		Notification notification = new Notification();
//		Calendar calendar = Calendar.getInstance();
//        java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTime().getTime());
//		notification.setCreatedOn(timestamp);
//		notification.setNotification(raiseHandText);
//		notification.setNotificationAnswer(raiseHandAnswer);
//		notification.setFromUserId(userId);
//		notification.setToUserId(userId1);
//		notification.setClassEventDetail(eventObj);
//		notification.setReminderOrRaiseHandFlag("RH");
//		notification.setNotificationFlag("RHQanswer");
//		notificationDao.updateRHQueuedQuestionAnswer(notification);
//		
//		log.debug("RaiseHand Saved Successfully");
	
//	
//		int value=notificationDao.saveRaiseHandQueuedDetailsInDb(raiseHandId,raiseHandAnswer);
//		if(value!=0)
//	    {
//		log.debug(" RaiseHandqueue answer Saved Successfully");
//        }
//	  else
//	   {
//		log.debug(" answer Saving error");
//	    }
////		
//	}
	public void updateRaiseHandFlag(int notificationId) {
		
		int result=notificationDao.updateRaiseHandFlag(notificationId);	
		if(result!=0)
	    {
		log.debug(" updated flag Successfully");
        }
	  else
	   {
		log.debug(" error in updation");
	    }
	}
	
	@Override
	public Properties getAttendance(int scheduleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getClassStatus(int classEventDetailId) {
		

		List<Object[]> liveList = clazEventdetailDao.getClassStatus(classEventDetailId);
		Properties prop=new Properties();
	
		if(!liveList.isEmpty())
		{
			Object[] ob = liveList.get(0);
			Timestamp s=(Timestamp) ob[0];
			
			 Timestamp s1=(Timestamp) ob[1];
			 System.out.println("StartTime"+s);
			 System.out.println("EndTime"+s1);
			
			 if(ob[0]!=null && ob[1]==null)
			{
				 System.out.println("class already started");
				 return 1;
			}
			 else if(ob[0]!=null && ob[1]!=null)
			 {
				
				 System.out.println("class ended");
				 return 0;
			 }
			 else
			 {
				 return 0;
			 }
			
		}
		
		else
		{
			return 0;
		}

	}


	@Override
	public void saveWorkingHoursinDB(String teacherId, int time1,int batchId) {
		

				
		
		
		User u=new User();
        u.setUserId(teacherId);
        CourseBatch cb=new CourseBatch();
        cb.setCourseBatchId(batchId);
		MaximumHours mh=new MaximumHours();
			
		
		Integer maximumObj=maximumHoursDao.getMaximumHoursStatus(teacherId);
		System.out.println("maximumObj"+maximumObj);
		
	    List<Integer> value=maximumHoursDao.getTeacherWorkingHoursTillNow(teacherId);
	    System.out.println("working hours"+value);
	    if(maximumObj==0)
	    {
	    	mh.setUser(u);	
	    	mh.setCourseBatch(cb);
	    	mh.setMinutes(time1);
	        maximumHoursDao.saveWorkingHoursinDB(mh);
	
	    }
	    else
	    {
	      
	    	int workMinutes=(value.get(0))+time1;
	    	//Integer workMinutes111= mh.getMinutes();
	    	//mh.setMinutes(workMinutes);
	        
	    	System.out.println("workMinutes"+workMinutes);
	        maximumHoursDao.updateTeacherAttendance(teacherId,workMinutes,batchId);
	    	
	    }
	}

//	@Override
//	public Properties classDetailsAfterCrash(Integer batchId) {
//		
//		List<Object[]> liveList = clazEventdetailDao.checkForLive(batchId);
//		Properties prop=new Properties();
//	
//			Object[] ob = liveList.get(0);
//			for(int i=0;i<liveList.size();i++)
//			{
//				 Object[] objArray=liveList.get(i);
//			    if(objArray[8]==null)
//			   {
//				Timestamp s=(Timestamp) objArray[6];
//				
//			   SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//			
//				prop.put("ClazEventDetailId", objArray[5]);
//	
//				System.out.println("classeventId" +objArray[5]);
//			
//			}
//			    
//		}		
//
//		return prop;
//		}
//	

	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HashMap> classDetailsAfterCrash(Integer batchId,String time) {
		
		List<Object[]> liveList = clazEventdetailDao.checkForLive(batchId);
		//Properties prop=new Properties();
		  List<HashMap> prop=new ArrayList<HashMap>();
			
			for(int i=0;i<liveList.size();i++)
			{
				 Object[] objArray=liveList.get(i);
			    if(objArray[8]==null)
			   {
				Timestamp s=(Timestamp) objArray[6];
				
			   SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			   HashMap result=new HashMap();
			   System.out.println("timeFromDB"+s);
			   System.out.println("timeinput"+time);
			   String CurrentTime = sdf.format(s);
			   String[] temp= CurrentTime.split(":");
        	   String[] timeFromDb= time.split(":");
        	   String value=temp[0];
        	   String value1=timeFromDb[0];
			   System.out.println("timeFromDBafterSplitting"+value);
			   if(value1.equals(value))
			   {
			   result.put("CurrentScheduleId",objArray[4]);
			   result.put("ClazEventDetailId", objArray[5]);
			   prop.add(result);
			   System.out.println("classeventId" +objArray[5]);
		 }
			
			}
			    
		}		
		return prop;
		}

	@Override
	public boolean saveUploadedImageFromPortal(String file, String name,
			int classEventDetailId, byte imageType, String time,
			String type) {
System.out.println("------------"+file.length());
System.out.println(file);
		if(file!=null)
		{
			
			file = file.replaceAll(" ", "+");
			 String[] parts = file.split("\\,");
			 
			 String beforeFirstDot = parts[1];
			byte[] imageByteArray = Base64.decodeBase64(beforeFirstDot);
			FileOutputStream imageOutFile;
			try {
				imageOutFile = new FileOutputStream(CyberInit.webAppFolder+File.separatorChar
						+CyberInit.cyberProperty.getProperty("defaultWorkingFolder")+File.separatorChar
					+"Recorder"+File.separatorChar
					+classEventDetailId+File.separatorChar+name+".jpg");
					
					try {
						imageOutFile.write(imageByteArray);
						System.out.println(imageOutFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
		
	
	}
		
		String link=name+"."+"jpg";
		Integer i=attachmentDao.saveImageDetailsFromPortal(name,classEventDetailId,imageType,link,time,type);
		if(i!=0)
		{
			
			log.debug("image details saved successfully");
			return true;
		}
		else
		{
		
			log.debug("error");
			return false;
		}
		
	}
	
	@Override
	public boolean saveUploadedImageFromAndroid(String file, String name,
			int classEventDetailId, byte imageType, String time,
			String type) {
System.out.println("------------"+file.length());
System.out.println(file);
		if(file!=null)
		{
			
			
			byte[] imageByteArray = Base64.decodeBase64(file);
			FileOutputStream imageOutFile;
			try {
				imageOutFile = new FileOutputStream(CyberInit.webAppFolder+File.separatorChar
						+CyberInit.cyberProperty.getProperty("defaultWorkingFolder")+File.separatorChar
					+"Recorder"+File.separatorChar
					+classEventDetailId+File.separatorChar+name+".jpg");
					
					try {
						imageOutFile.write(imageByteArray);
						System.out.println(imageOutFile);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
		
	
	}
		
		String link=name+"."+"jpg";
		Integer i=attachmentDao.saveImageDetailsFromPortal(name,classEventDetailId,imageType,link,time,type);
		if(i!=0)
		{
			
			log.debug("image details saved successfully");
			return true;
		}
		else
		{
		
			log.debug("error");
			return false;
		}
		
	}

	
}
	
	
	