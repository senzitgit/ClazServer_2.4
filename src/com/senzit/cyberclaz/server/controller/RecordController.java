	package com.senzit.cyberclaz.server.controller;
	
	import java.io.File;
	
	
	
	
	
	import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
	
	import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
	
	import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
	
	import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
	
	import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
	
	import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
	
	import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
	
	import com.senzit.cyberclaz.server.model.AttachmentData;
import com.senzit.cyberclaz.server.model.AttendanceData;
import com.senzit.cyberclaz.server.model.AttentionData;
import com.senzit.cyberclaz.server.model.CyberInit;
import com.senzit.cyberclaz.server.model.GeneralLog;
import com.senzit.cyberclaz.server.model.RebbonData;
import com.senzit.cyberclaz.server.model.ScheduleData;
import com.senzit.cyberclaz.server.model.SwitchVideoData;
import com.senzit.cyberclaz.server.model.UserAttendanceTime;
import com.senzit.cyberclaz.server.service.CognosService;
import com.senzit.cyberclaz.server.service.DashService;
import com.senzit.cyberclaz.server.service.PlayerService;
import com.senzit.cyberclaz.server.service.PortalService;
import com.senzit.cyberclaz.server.service.RecordService;
import com.senzit.cyberclaz.server.service.SchedulerService;
import com.senzit.cyberclaz.server.service.UserServices;
import com.senzit.cyberclaz.server.service.WebSocketServiceImp;
import com.senzit.cyberclaz.server.subservice.CyberProperty;
import com.senzit.cyberclaz.server.subservice.CyberService;
import com.senzit.cyberclaz.server.subservice.JsonParser;
import com.senzit.cyberclaz.server.subservice.RaiseHandQueue;
import com.senzit.cyberclaz.server.subservice.RebbonService;
	
import com.senzit.cyberclaz.server.subservice.Convert;
	
	@RestController
	public class RecordController {
	
		static Logger log = Logger.getLogger(RecordController.class.getName());
		@Autowired
		private RecordService recordService;
		@Autowired
		private UserServices userService;
		@Autowired
		private ServletContext context;
		@Autowired
		private SchedulerService schedulerService;
		@Autowired
		private DashService dashService;
		@Autowired
		private PortalService portalService;
		@Autowired
		private CognosService cognosService;
		
	
		
	
		public void setCognosService(CognosService cognosService) {
			this.cognosService = cognosService;
		}
	
		public PortalService getPortalService() {
			return portalService;
		}
	
		public void setPortalService(PortalService portalService) {
			this.portalService = portalService;
		}
	
		public void setRecordService(RecordService recordService) {
			this.recordService = recordService;
		}
	
		public void setUserService(UserServices userService) {
			this.userService = userService;
		}
	
		public void setSchedulerService(SchedulerService schedulerService) {
			this.schedulerService = schedulerService;
		}
	
		public void setDashService(DashService dashService) {
			this.dashService = dashService;
		}
	
		RecordController(){}
	
		int resultCode=0;
		String message="";
	
	
		@RequestMapping(value = "/takeAttendance", method = RequestMethod.POST)
		public String takeAttendance(HttpSession sessionObj,HttpServletResponse response,
				@RequestParam("scheduleId") int scheduleId,
				@RequestParam("sessionID") String sessionID){

			Hashtable result=new Hashtable();
			log.debug("ScheduleId:"+scheduleId);
			String rebbonId = (String)sessionObj.getAttribute("rebbonId");
			String teacherId=(String)sessionObj.getAttribute("userId");
			
			String savedSessionID = userService.geUserSession(teacherId);
			
			
			if(teacherId!=null && sessionID.equals(savedSessionID))
			{ 
				log.debug("Session Valid");
				try
				{
				result=recordService.getAttendance(rebbonId);
				if(result.isEmpty()){
					message="No data found";
				}
				else
				{
					resultCode=1;
					message="Success";
				}	
				}
				catch(Exception e)
				{
					e.printStackTrace();
					message = "Server Exception : "+e.getCause();
					resultCode = 2;
					
				}
			}
			else
			{
				log.debug("Invalid Session");
				message="Invalid RebbonId";
				resultCode=0;
			}
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("takeAttendance",resultCode,message,sessionID,"attendanceResult",result);
			return jsonResponse.JsonResponseText();
	
		}
		
		@RequestMapping(value = "/inClass", method = RequestMethod.POST)
		public String inClass(HttpSession sessionObj,HttpServletResponse response,
				@RequestParam("sessionID") String sessionID,
				@RequestParam("userId") String userId){
			resultCode=0;
			if(userId==null)
				message="Invalid session";
			else{
				try
				{
				WebSocketServiceImp wsObj=new WebSocketServiceImp();
				wsObj.setInClassFlag(userId);
				resultCode=1;
				message="Success";
				}
				catch(NullPointerException e)
				{
					e.printStackTrace();
					message = "Server Exception : Websocket is closed";
					resultCode = 2;
				}
			}
	
			JsonParser<String, String> jsonResponse = new JsonParser<String, String>("inClass",resultCode,message,sessionID,"inClass","");
			return jsonResponse.JsonResponseText();
	
		}
			@RequestMapping(value = "/startRecord", method = RequestMethod.POST)
			public String startRecord(HttpSession sessionObj,HttpServletResponse response,
					@RequestParam("scheduleId") int scheduleId,@RequestParam("chapterName") String chapter,
					@RequestParam("topicName") String topic,@RequestParam("timestamp") String time,
					@RequestParam("subjectName") String subjectName,@RequestParam("courseName") String courseName,
					@RequestParam("duration") String duration,@RequestParam("subjectId") String subjectId,
					@RequestParam("sessionID") String sessionID)
			{  
				
				
				
				
				
				
				
				Hashtable<String,Object> result=new Hashtable<String,Object> ();
				Properties prop=new Properties();
				Properties propRTSP=new Properties();
				
		
				String teacherId=(String)sessionObj.getAttribute("userId");		
				String savedSessionID = userService.geUserSession(teacherId);
				
				Integer tempScheduleId = null;
				int classEventId = 0;
				if(teacherId!=null && sessionID.equals(savedSessionID))
				{ 
					log.debug("Session Valid");
		
					try
					{
					String rebbonId = (String)sessionObj.getAttribute("rebbonId");
					String rebbonLink=recordService.getRebbonLink(rebbonId);
	
					Integer CourseBatchId = schedulerService.getCourseBatchIdForTeacher(rebbonId);
		  		    List<String> userIdList=userService.getAllStudentInClass(CourseBatchId);

							
					/*Start Rebbon*/
					
					
					String mediaLinkJson=RebbonService.getMediaUrls(rebbonLink,false,"");
					String mediaLinkJsonRTSP=RebbonService.getMediaUrlsRTSP(rebbonLink,false,"");
					//////////////////////////////////////////
					log.debug("CurrentRebbonLink:"+rebbonLink);
					log.debug("MediaLinks"+mediaLinkJson);
					//////////////////////////////////////////
		
					
					
					System.err.println(mediaLinkJsonRTSP);
					try 
					{
						JSONObject jsonData = new JSONObject(mediaLinkJson);
						prop=RebbonService.getMediaLinkFromJson(jsonData);
						
						JSONObject jsonDataRTSP = new JSONObject(mediaLinkJsonRTSP);
						propRTSP=RebbonService.getMediaLinkFromJsonRTSP(jsonDataRTSP);
						System.err.println(propRTSP);
						
						
					} 
					catch (JSONException e) 
					{
						e.printStackTrace();
					
					}	
		
				
					
					UserAttendanceTime timeObj = new UserAttendanceTime();
				
		//			Integer CourseBatchId = schedulerService.getCourseBatchIdForTeacher(rebbonId);
		//			List<String> userIdList=userService.getAllStudentInClass(CourseBatchId);
					String teacherName=userService.getUserName(teacherId);
					String profilePic=userService.getProfilePic(teacherId);
					 Calendar cal = Calendar.getInstance();
				    	cal.getTime();
				    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				    	System.out.println(sdf.format(cal.getTime()));
				    	String timeClass=sdf.format(cal.getTime());
				    	 String[] time1=timeClass.split(":");
				    	 int currentMinute = Integer.parseInt(time1[0])*60+Integer.parseInt(time1[1]);
				    	String value= String.valueOf(currentMinute);
				    	System.out.println("currentMinute"+value);
				    	int currntTime=currentMinute/60;
				    	String value1= String.valueOf(currntTime);
				    	System.out.println("HOURRRRRRRRRRRRRRRRRRRRRRRR"+currntTime);
				    	
						timeObj.putNewData(teacherId, timeClass, CourseBatchId);
				   //////////////////manual recording///////////////// 	
						
					 classEventId=recordService.createNewClassEvent(scheduleId,chapter,topic,"normalSchedule",subjectName,courseName,teacherName,teacherId,profilePic);
		             String folderPath=CyberService.createWorkingFolder(classEventId, "Recorder");
					
					AttendanceData obj=new AttendanceData();
					obj.removeData(CourseBatchId);
		
					sessionObj.setAttribute("classEventId", classEventId);
		
					 sessionObj.setAttribute("scheduleId", scheduleId);
			
					sessionObj.setAttribute("courseBatchId", CourseBatchId);
					sessionObj.setAttribute("rebbonLink", rebbonLink);
					
		
					//////////////////////////////////////
					GeneralLog gLog = new GeneralLog();
					gLog.removeData(CourseBatchId);
					AttachmentData AtObj = new AttachmentData();
					AtObj.removeData(CourseBatchId);
				    AttentionData attentionData=new AttentionData();		
					 attentionData.removeDataNew(CourseBatchId);
					 SwitchVideoData switchVideo=new SwitchVideoData();
				      switchVideo.removeDataNew(CourseBatchId);
				    // RaiseHandQueue.clearQueue(teacherId);
		
					////////////////////////////////////////
					
				      Properties CurrentliveDetails = new  Properties();
				      
				      if(scheduleId==101){
				    	  
				    	  
				    	//  CurrentliveDetails = recordService.checkForLiveSessionManual();
				    	  CurrentliveDetails.put("TeacherId",teacherId);
				    	  CurrentliveDetails.put("TeacherName", teacherName);
				    	  CurrentliveDetails.put("ProfilePicURL",profilePic);
				    	  CurrentliveDetails.put("Subject", subjectName);
				    	  CurrentliveDetails.put("CurrentScheduleId",101);
							
				    	  CurrentliveDetails.put("ClazEventDetailId",classEventId);
				    	  CurrentliveDetails.put("StartTime",timeClass);
				    	  CurrentliveDetails.put("Topicname",topic);
				    	  CurrentliveDetails.put("Chaptername",chapter);
				    	  
				    	
							
				    	  
				    	  
				      }else {
				    	   CurrentliveDetails = recordService.checkForLiveSession(CourseBatchId);
				      }
		           
					
		            
		            
		            
		            
		            
		            
		            
		            
		            
		            
		            
		            WebSocketServiceImp wsObj=new WebSocketServiceImp();
				   
					
					result.put("profilePic", profilePic);
					
					result.put("timestamp", time);
					result.put("liveCamDetails", prop);
					result.put("liveCamRTSP", propRTSP);
		            result.put("liveSessionDetails",CurrentliveDetails);
		            result.put("teacherName", teacherName);
		            
		//			
					
					if(Integer.parseInt(subjectId)!=0)
						result.put("scheduleChange", true);
					else
						result.put("scheduleChange", false);
		
		
					try {
		
						JsonParser<String, Hashtable<String,Object>> wsJson = new JsonParser<String, Hashtable<String,Object>>("startRecord",1,"Recording Started",sessionID,"startRecordResult",result);
						String a= wsJson.JsonResponseText();
						///////////////////////
						log.debug(userIdList);
						log.debug(a);
						////////////////////////
						wsObj.writeTextmessage(userIdList, a,false);
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
						message="WebSocket Failed";
					}
		
					result.clear();
		
					result.put("liveCamDetails", prop);
					result.put("classEventId", classEventId);
					result.put("liveCamRTSP", propRTSP);
					List<Properties> attachmentListTeacher=recordService.getAttachmentDetailsUploadedViaPortal(teacherId);
					result.put("attachmentList", attachmentListTeacher);
					RebbonService.storeRebbonLink(CourseBatchId, mediaLinkJson);
		
					message="Success";
					resultCode=1;                        
					}
					catch (Exception e) {
							
						e.printStackTrace();
						message = "Server Exception : "+e.getCause();
						resultCode = 2;
					}
				}
				else
				{
					log.debug("Invalid Session");
					message="Invalid RebbonId";
					resultCode=0;
				}
		
				JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("startRecord",resultCode,message,sessionID,"startRecordResult",result);
				return jsonResponse.JsonResponseText();
		
			}
			
			
			
			
			
			@RequestMapping(value = "/startRecordManager", method = RequestMethod.POST)
			public String startRecordManager(HttpSession sessionObj,HttpServletResponse response)
			{  
				
				
				
				
				
				
				
				Hashtable<String,Object> result=new Hashtable<String,Object> ();
				Properties prop=new Properties();
				Properties propRTSP=new Properties();
				
		
				
				
		
					try
					{
					String rebbonId = "r101";
					String rebbonLink=recordService.getRebbonLink(rebbonId);
	
	
							
					/*Start Rebbon*/
					
					
					String mediaLinkJson=RebbonService.getMediaUrls(rebbonLink,false,"");
					String mediaLinkJsonRTSP=RebbonService.getMediaUrlsRTSP(rebbonLink,false,"");
					//////////////////////////////////////////
					log.debug("CurrentRebbonLink:"+rebbonLink);
					log.debug("MediaLinks"+mediaLinkJson);
					//////////////////////////////////////////
		
					
					
					System.err.println(mediaLinkJsonRTSP);
					try 
					{
						JSONObject jsonData = new JSONObject(mediaLinkJson);
						prop=RebbonService.getMediaLinkFromJson(jsonData);
						
						JSONObject jsonDataRTSP = new JSONObject(mediaLinkJsonRTSP);
						propRTSP=RebbonService.getMediaLinkFromJsonRTSP(jsonDataRTSP);
						System.err.println(propRTSP);
						
						
					} 
					catch (JSONException e) 
					{
						e.printStackTrace();
					
					}	
		
				
					
				
		            
		            
		            
		            
		            
		          
					
					result.put("liveCamDetails", prop);
					result.put("liveCamRTSP", propRTSP);
		           
		            
					result.clear();
		
					result.put("liveCamDetails", prop);
					result.put("liveCamRTSP", propRTSP);
				
			
		
					message="Success";
					resultCode=1;                        
					}
					catch (Exception e) {
							
						e.printStackTrace();
						message = "Server Exception : "+e.getCause();
						resultCode = 2;
					}
				
					String sessionID="";
		
				JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("startRecord",resultCode,message,sessionID,"startRecordResult",result);
				return jsonResponse.JsonResponseText();
		
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
	
			
			
			
			
			
		
			
	  @RequestMapping(value = "/startRecordAfterCrash", method = RequestMethod.POST)
		public String startRecordAfterCrash(HttpSession sessionObj,HttpServletResponse response,@RequestParam("classEventId_crash") int eventId,
				@RequestParam("sessionID") String sessionID,
				@RequestParam("userId") String userId)
		{
			Hashtable<String,Object> result=new Hashtable<String,Object> ();
			Properties prop=new Properties();
	
				
			String teacherId=userId;
			String savedSessionID = userService.geUserSession(teacherId);
			
			
			String sessionId = (String) sessionObj.getAttribute("sessionId");
			
			int classEventId = 0;
			if(teacherId!=null && sessionID.equals(savedSessionID))
			{ 
				log.debug("Session Valid");
	
				try
				{
				String rebbonId = (String)sessionObj.getAttribute("rebbonId");
				String rebbonLink=recordService.getRebbonLink(rebbonId);
				/////////////////////////////////////////////////need to edit////////////////////////
				Integer courseBatchId = schedulerService.getCourseBatchIdForTeacher(rebbonId);
	  		    List<String> userIdList=userService.getAllStudentInClass(courseBatchId);
	            //////////////////////////////////////////////////////////////
	        	String mediaLinkJson=RebbonService.getMediaUrls(rebbonLink,true,"");
				//////////////////////////////////////////
				log.debug("CurrentRebbonLink:"+rebbonLink);
				log.debug("MediaLinks"+mediaLinkJson);
				//////////////////////////////////////////
	
				try 
				{
					JSONObject jsonData = new JSONObject(mediaLinkJson);
					prop=RebbonService.getMediaLinkFromJson(jsonData);
				} 
				catch (JSONException e) 
				{
					e.printStackTrace();
				
				}	
	
	
				AttentionData attentionData =new AttentionData();
				Integer attData=attentionData.getData(courseBatchId);
				
				System.out.println("AttentionInAttendClass"+attData);
				result.put("attentionMode",attData);
				
				SwitchVideoData switchVideo=new SwitchVideoData();
				Integer svValue=switchVideo.getData(courseBatchId);
				result.put("switchVideo",svValue);
	     		ArrayList<Properties> logList=new ArrayList<Properties>();
					ArrayList<Properties> AttachmentList=new ArrayList<Properties>();
			        GeneralLog gLog=new GeneralLog();
					ArrayList<Properties> lgList =gLog.getData(courseBatchId);
					if(lgList!=null)
						logList=lgList;
					 
					AttachmentData AtObj = new AttachmentData();
					ArrayList<Properties> atList=AtObj.getData(courseBatchId);
					if(atList!=null)
					{
						AttachmentList=atList;
						
					}
					else
					{
						log.debug(" attachment list is empty");
						
					}
		     		List<HashMap> notificationResult=recordService.getRaiseHandQuestionAndAnswer(eventId,teacherId);
					result.put("raiseHandQueueQuestionandAnswer", notificationResult);
					
				 
					result.put("generalLog", logList);
					result.put("attachmentList", AttachmentList);
	           // String folderPath=CyberService.createWorkingFolder(classEventId, "Recorder");
	            sessionObj.setAttribute("classEventId", classEventId);
	
	//			AttendanceData obj=new AttendanceData();
	//			obj.removeData(courseBatchId);
	
		
				sessionObj.setAttribute("courseBatchId", courseBatchId);
				sessionObj.setAttribute("rebbonLink", rebbonLink);
				result.put("liveCamDetails", prop);
				result.put("classEventId", classEventId);
				
				List<Properties> attachmentListTeacher=recordService.getAttachmentDetailsUploadedViaPortal(teacherId);
				result.put("attachmentList", attachmentListTeacher);
				RebbonService.storeRebbonLink(courseBatchId, mediaLinkJson);
	
				message="Success";
				resultCode=1;                        
				}
				catch (Exception e) {
						
					e.printStackTrace();
					message = "Server Exception : "+e.getCause();
					resultCode = 2;
				}
			}
			else
			{
				log.debug("Invalid Session");
				message="Invalid RebbonId";
				resultCode=0;
			}
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("startRecordAfterCrash",resultCode,message,sessionID,"startRecordAfterCrashResult",result);
			return jsonResponse.JsonResponseText();
	
		}
	
	
		@RequestMapping(value = "/startSession", method = RequestMethod.POST)
		public String startStudentSession(HttpSession sessionObj,HttpServletResponse response,
				@RequestParam("sessionID") String sessionID)
		{  
			Hashtable<String,Object> result=new Hashtable<String,Object>();
	
			
			String userId=(String)sessionObj.getAttribute("userId");		
			String savedSessionID = userService.geUserSession(userId);
	
			if(userId!=null && sessionID.equals(savedSessionID))
			{ 
				log.debug("Session Valid");
	         try
	         {
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				String startTime = sdf.format(cal.getTime()) ;
				int batchId=Integer.parseInt((String)sessionObj.getAttribute("CourseBatchId"));	
				List<Properties> attachmentListStudent=recordService.getAttachmentDetailsUploadedViaPortal(userId);
				List<String> teacherName=recordService.getTeacherFromBatch(batchId);
				String teacherName1=teacherName.get(0);
				String studentName=userService.getUserName(userId);
				UserAttendanceTime timeObj = new UserAttendanceTime();
				timeObj.putNewData(userId, startTime, batchId);
				WebSocketServiceImp wsObj=new WebSocketServiceImp();
				wsObj.setInClassFlag(userId);
				result.put("startTime", startTime);
				
				 try {
					  JsonParser<String, Hashtable<String,Object>> wsJson = new JsonParser<String, Hashtable<String,Object>>("startSession",1,studentName+" is attending the class",sessionID,"startSessionResult",result);
				      String a=wsJson.JsonResponseText();
	                 
					    wsObj.writeTextmessage(teacherName, a,false);
					    
					   } 
				 catch (IOException e) {
					    e.printStackTrace();
					    message="WebSocket Failed";
					   }
				result.put("portalAttachmentList", attachmentListStudent);
				resultCode=1;
				message="success";
	         }
	         catch(Exception e)
	 		{
	 	 		e.printStackTrace();
	 			message = "Server Exception : "+e.getCause();
	 			resultCode = 2;	
	 		}

			}
			else
			{
				log.debug("Invalid Session");
	
				message="Invalid RebbonId";
				resultCode=0;
			}
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("startSession",resultCode,message,sessionID,"startSessionResult",result);
			return jsonResponse.JsonResponseText();
	
		}
				@RequestMapping(value ="/attentionMode", method = RequestMethod.POST)
				public String attentionModeRequest(HttpSession sessionObj,HttpServletResponse response,
						@RequestParam("scheduleId") int scheduleId,@RequestParam("attention") int value,
						@RequestParam("sessionID") String sessionID,
						@RequestParam("userId") String userId){
					
					Hashtable result=new Hashtable();
					log.debug("attentionFlag:"+value);
					log.debug("ScheduleId:"+scheduleId);
					String teacherId=userId;
					String savedSessionID = userService.geUserSession(teacherId);
					int classEventDetailId=(Integer) sessionObj.getAttribute("classEventId");
					String rebbonId = (String)sessionObj.getAttribute("rebbonId");
			
					if(teacherId!=null && sessionID.equals(savedSessionID))
					{ 
						log.debug("Session Valid");
				      try
				      {
						Integer CourseBatchId = schedulerService.getCourseBatchIdForTeacher(rebbonId);
						List<String> studDetails=userService.getAllStudentInClass(CourseBatchId);
						////////////////////////////////////////////////////////////////////////
						WebSocketServiceImp wsObj=new WebSocketServiceImp();
						System.out.println("students in class"+studDetails);
						
						 try {
								   JsonParser<String, Hashtable<String,Object>> wsJson = new JsonParser<String, Hashtable<String,Object>>("attentionMode",1," attention please",sessionID,"attentionModeResultResult",result);
								    String a= wsJson.JsonResponseText();
			
								    wsObj.writeTextmessage(studDetails, a,true);
								    
								   } 
						     catch (IOException e) {
								    e.printStackTrace();
								    message="WebSocket Failed";
								   }
			
								   result.clear();
								   AttentionData attentionData=new AttentionData();		
							    
								   attentionData.putNewData(CourseBatchId,value);
			
								   resultCode=1;
								   message="Success";
							}
					    
					       catch(Exception e)
						   {
					 		e.printStackTrace();
							message = "Server Exception : "+e.getCause();
							resultCode = 2;	
						    }
					       }
								  else
								  {
								   log.debug("Invalid Session");
								   message="Invalid Session";
								   resultCode=0;
							  }
			
								  JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("attentionMode",resultCode,message,sessionID,"attentionModeResultResult",result);
								  return jsonResponse.JsonResponseText();
			
					 }
			@RequestMapping(value ="/switchVideo", method = RequestMethod.POST)
			public String switchVideo(HttpSession sessionObj,HttpServletResponse response,@RequestParam("switchFlag") int switchFlag,
					@RequestParam("sessionID") String sessionID){
			
			
				
				log.debug("switchFlag:"+switchFlag);
				Hashtable result=new Hashtable();
				String teacherId=(String)sessionObj.getAttribute("userId");		
				String savedSessionID = userService.geUserSession(teacherId);
				//int classEventDetailId=(Integer) sessionObj.getAttribute("classEventId");
				String rebbonId = (String)sessionObj.getAttribute("rebbonId");
				
				if(teacherId!=null && sessionID.equals(savedSessionID))
				{ 
					log.debug("Session Valid");
					try
					{
					Integer CourseBatchId = schedulerService.getCourseBatchIdForTeacher(rebbonId);
					List<String> studDetails=userService.getAllStudentInClass(CourseBatchId);
					
					////////////////////////////////////////////////////////////////////////
					WebSocketServiceImp wsObj=new WebSocketServiceImp();
					System.out.println("students in class"+studDetails);
					
					 try {
							   JsonParser<String, Hashtable<String,Object>> wsJson = new JsonParser<String, Hashtable<String,Object>>("switchVideo",1," Blackboard/presentation video switching",sessionID,"switchVideoResult",result);
							    String a= wsJson.JsonResponseText();
		
							    wsObj.writeTextmessage(studDetails, a,true);
							   } catch (IOException e) {
							    e.printStackTrace();
							   
							    message="WebSocket Failed";
							   }
		
							   result.clear();
							   SwitchVideoData switchVideo=new SwitchVideoData();
						       switchVideo.putNewData(CourseBatchId, switchFlag);
		
							   resultCode=1;
							   message="Success";
							  }
				   
				 catch(Exception e)
					{
				 		e.printStackTrace();
						message = "Server Exception : "+e.getCause();
						resultCode = 2;	
					}
				}
							  else
							  {
							   log.debug("Invalid Session");
							   message="Invalid Session";
							   resultCode=0;
						  }
		
							  JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("switchVideo",resultCode,message,sessionID,"switchVideoResult",result);
							  return jsonResponse.JsonResponseText();
		
				 }
	
			@RequestMapping(value = "/generalLog", method = RequestMethod.POST)
			public String getGeneralLog(HttpSession sessionObj,HttpServletResponse response,@RequestParam("userId") String userId,
			@RequestParam("logText") String logText,@RequestParam("timestamp") String time,@RequestParam("duration") String duration,
			@RequestParam("raiseHandText") String raiseHandText,@RequestParam("raiseHandAnswer") String raiseHandAnswer,
			@RequestParam("RHflag") String flag,
			@RequestParam("sessionID") String sessionID,
			@RequestParam("studentId") String studentId,
			@RequestParam("clazId") String clazId)
					
			{  
				Hashtable<String,Object> result=new Hashtable<String,Object>();
		
				////////////////////////////////////
				
				String userIdForStud=studentId;
				
				log.debug("userId:"+userIdForStud);
				log.debug("GeneralLogText:"+logText);
				log.debug("Time:"+time);
				log.debug("LogDuration:"+duration);
				log.debug("RaiseHandText:"+raiseHandText);
				log.debug("RaiseHandAnswer:"+raiseHandAnswer);
				log.debug("RHflag:"+flag);
				////////////////////////////////////
		
						
				String savedSessionID = userService.geUserSession(userId);
				Integer scheduleId = 101;
				String rebbonId = (String)sessionObj.getAttribute("rebbonId");
				//String obj=flag;
				int classEventDetailId=Integer.parseInt(clazId);
				if(userId!=null && sessionID.equals(savedSessionID))
				{ 
					log.debug("Session Valid");
					try
					{
					Integer CourseBatchId = schedulerService.getCourseBatchIdForTeacher(rebbonId);
					List<String> userIdList=userService.getAllStudentInClass(CourseBatchId);
		
				    String profilePic=userService.getProfilePic(userIdForStud);
					if(profilePic!=null)
					{
				    System.out.print("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");	
				    result.put("studentId", userId);
					result.put("profilePic", profilePic);
					result.put("timestamp", time);
					result.put("duration", duration);
					result.put("logText", logText);
					result.put("raiseHandText", raiseHandText);
					result.put("raiseHandAnswer", raiseHandAnswer);
					}
			
					else
					{
						System.out.print("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
						result.put("studentId", userId);
						result.put("timestamp", time);
						result.put("duration", duration);
						result.put("logText", logText);
						result.put("raiseHandText", raiseHandText);
						result.put("raiseHandAnswer", raiseHandAnswer);
							
					}
			
					WebSocketServiceImp wsObj=new WebSocketServiceImp();
					boolean retval = logText.contains("file shared");
					if(!("Recording Started".equals(logText)) && !("Recording Stopped".equals(logText)) && !("Switch video".equals(logText)) && !(retval==true))
				   {
						
						
					try {
						JsonParser<String, Hashtable<String,Object>> wsJson = new JsonParser<String, Hashtable<String,Object>>("generalLog",1,"New generallog",sessionID,"generalLogResult",result);
						String a= wsJson.JsonResponseText();
						///////////////////////
						log.debug("GENERAL LOG");
						log.debug(userIdList);
						log.debug(a);
						////////////////////////
						wsObj.writeTextmessage(userIdList, a,true);
						
						
							
					
						
						
						
						
						
						
						
						
					} catch (IOException e) 
					{
						e.printStackTrace();
						message="WebSocket Failed";
					}
					}
				   
					result.clear();
		
					GeneralLog gLog=new GeneralLog();
					gLog.putNewData(profilePic,CourseBatchId, logText,duration,time,raiseHandText,raiseHandAnswer);
					
					if("RHQueueAnswer".equals(flag))
					{
						  recordService.saveRaiseHandQueuedDetailsInDb(raiseHandText,raiseHandAnswer,classEventDetailId); 
						  log.debug("RHQueuedquestion answer saved successfully");
						//  RaiseHandQueue.deleteFromQueue(userId1, raiseHandText, userIdForStud);
					}
		
					message="Success";
					resultCode=1;
				}
			    catch(Exception e)
					{
					 		e.printStackTrace();
							message = "Server Exception : "+e.getCause();
							resultCode = 2;	
					}
				}
				else
				{
					log.debug("Invalid Session");
					message="Invalid Session";
					resultCode=0;
				}
		
				JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("generalLog",resultCode,message,sessionID,"generalLogResult",result);
				return jsonResponse.JsonResponseText();
			}
	
		@RequestMapping(value ="/attendClass", method = RequestMethod.POST)
		public String attendClass(HttpSession sessionObj,HttpServletResponse response,@RequestParam("userId") String teacherId
		,@RequestParam("classEventDetailIdRec") int classEventDetailId,
		@RequestParam("sessionID") String sessionID)
		{
			
			
			Hashtable<String,Object> result=new Hashtable<String,Object>();
			int courseBatchId=Integer.parseInt((String)sessionObj.getAttribute("CourseBatchId"));
			String userId=teacherId;
	
			log.debug("StudentId"+userId);
			log.debug(teacherId);
			log.debug(courseBatchId);
			log.debug("classEventId"+classEventDetailId);
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			String startTime = sdf.format(cal.getTime());
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			   Date date = new Date();
			   
			 java.sql.Timestamp ReminderTime = new java.sql.Timestamp(date.getTime());
			 
			int value=recordService.getClassStatus(classEventDetailId);
			if(value!=0)
			{
			try
			{
			UserAttendanceTime timeObj = new UserAttendanceTime();
			timeObj.putNewData(userId, startTime, courseBatchId);
			timeObj.checkData(userId);
			
			String studentName=userService.getUserName(userId);
			List<String> teacherName=recordService.getTeacherFromBatch(courseBatchId);
			
			log.debug("StudentIdTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+userId);
			String profilePicStud=userService.getProfilePic(userId);	
			
			WebSocketServiceImp wsObj=new WebSocketServiceImp();
			
			result.put("userId", userId);
			result.put("serverTime", startTime);
			result.put("ProfilePic", profilePicStud);
			result.put("studentName", studentName);
			result.put("status", userService.getLogStatus(userId));
	
			JsonParser<String, Hashtable<String,Object>> wsJson = new JsonParser<String, Hashtable<String,Object>>("attendClass",1,studentName+" is attending the class",sessionID,"attendClassResult",result);
			String a=wsJson.JsonResponseText();
			try {
			wsObj.setInClassFlag(userId);
			wsObj.writeTextmessage(teacherName, a,false);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			result.clear();
				
		
		
			AttentionData attentionData =new AttentionData();
			Integer attData=attentionData.getData(courseBatchId);
			
			System.out.println("AttentionInAttendClass"+attData);
			result.put("attentionMode",attData);
			
			SwitchVideoData switchVideo=new SwitchVideoData();
			Integer svValue=switchVideo.getData(courseBatchId);
			result.put("switchVideo",svValue);
		
			
				ArrayList<Properties> logList=new ArrayList<Properties>();
				ArrayList<Properties> AttachmentList=new ArrayList<Properties>();
		        GeneralLog gLog=new GeneralLog();
				ArrayList<Properties> lgList =gLog.getData(courseBatchId);
				if(lgList!=null)
					logList=lgList;
				 
				AttachmentData AtObj = new AttachmentData();
				ArrayList<Properties> atList=AtObj.getData(courseBatchId);
				if(atList!=null)
				{
					AttachmentList=atList;
					
				}
				else
				{
					log.debug(" attachment list is empty");
					
				}
	     		List<HashMap> notificationResult=recordService.getRaiseHandQuestionAndAnswer(classEventDetailId,userId);
				result.put("raiseHandQueueQuestionandAnswer", notificationResult);
				result.put("teacherName", teacherName);
			 
				result.put("generalLog", logList);
				result.put("attachmentList", AttachmentList);
				sessionObj.setAttribute("classEventDetailId",classEventDetailId);
	
				resultCode=1;
				message="Success";
				
			try
			{
					
					result.put("liveCamDetails", RebbonService.getRebbonLink(courseBatchId));
					List<Properties> attachmentList=recordService.getAttachmentDetailsUploadedViaPortal(userId);
					result.put("portalAttachmentList", attachmentList);
					
				
			}
			catch(NullPointerException e)
			{
				
				e.printStackTrace();
				message = "Server Exception :class session is not stop properly ";
				resultCode = 2;
			}	
	        }
			catch(Exception e)
			{
				
				e.printStackTrace();
				message = "Server Exception : "+e.getCause();
				resultCode = 2;
			}	
		    }
			else
			{
				log.debug("class not yet started");
				message="class not yet started";
				resultCode=0;
			}
			 
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("attendClass",resultCode,message,sessionID,"attendClassResult",result);
			return jsonResponse.JsonResponseText();
	
		}
	
		@RequestMapping(value ="/attendLiveSession", method = RequestMethod.POST)
		public String attendLiveSession(HttpSession sessionObj,HttpServletResponse response,@RequestParam("userId") String teacherId
		,@RequestParam("classEventDetailIdRec") int classEventDetailId,
		@RequestParam("sessionID") String sessionID)
		{
			
			response.setHeader("Access-Control-Allow-Origin", "http://192.168.0.210:10080");
			Hashtable<String,Object> result=new Hashtable<String,Object>();
			int courseBatchId=Integer.parseInt((String)sessionObj.getAttribute("CourseBatchId"));
			String userId=(String)sessionObj.getAttribute("userId");
			
			log.debug("StudentId"+userId);
			log.debug(teacherId);
			log.debug(courseBatchId);
			log.debug("classEventId"+classEventDetailId);
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			String startTime = sdf.format(cal.getTime());
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			   Date date = new Date();
			   
			 java.sql.Timestamp ReminderTime = new java.sql.Timestamp(date.getTime());
			int value=recordService.getClassStatus(classEventDetailId);
			if(value!=0)
			{
			UserAttendanceTime timeObj = new UserAttendanceTime();
			timeObj.putNewData(userId, startTime, courseBatchId);
			timeObj.checkData(userId);
			
			String studentName=userService.getUserName(userId);
	
			List<String> teacherName=recordService.getTeacherFromBatch(courseBatchId);
			
			log.debug("StudentId"+userId);
			String profilePicStud=userService.getProfilePic(userId);	
	
			
				result.put("liveCamDetails", RebbonService.getRebbonLink(courseBatchId));
				ArrayList<Properties> logList=new ArrayList<Properties>();
				ArrayList<Properties> AttachmentList=new ArrayList<Properties>();
		        GeneralLog gLog=new GeneralLog();
				ArrayList<Properties> lgList =gLog.getData(courseBatchId);
				if(lgList!=null)
					logList=lgList;
				 
				AttachmentData AtObj = new AttachmentData();
				ArrayList<Properties> atList=AtObj.getData(courseBatchId);
				if(atList!=null)
				{
					AttachmentList=atList;
					
				}
				else
				{
					log.debug(" attachment list is empty");
					
				}
	
				
				List<HashMap> notificationResult=recordService.getRaiseHandQuestionAndAnswer(classEventDetailId,userId);
				result.put("raiseHandQueueQuestionandAnswer", notificationResult);
				result.put("teacherName", teacherName);
			    List<Properties> attachmentList=recordService.getAttachmentDetailsUploadedViaPortal(userId);
				result.put("portalAttachmentList", attachmentList);
				result.put("generalLog", logList);
				result.put("attachmentList", AttachmentList);
	
				resultCode=1;
				message="Success";
			}
			else
			{
				log.debug("class not yet started");
				message="class not yet started";
				resultCode=0;
			}
			
			
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("attendLiveSession",resultCode,message,sessionID,"attendLiveSessionResult",result);
			return jsonResponse.JsonResponseText();
	
		}
		
	
			@RequestMapping(value = "/shareAttachment", method = RequestMethod.POST)
			public String shareAttachment(HttpSession sessionObj,HttpServletResponse response,@RequestParam("timestamp") String time,
					@RequestParam("duration") String duration,@RequestParam("link") String attachmentLink,
					@RequestParam("name") String attachmentName,@RequestParam("docType") String type,
					@RequestParam("sessionID") String sessionID)
			{  
				
				Hashtable<String,Object> result=new Hashtable<String,Object>();
		
				//////////////////////////////////////////
				log.debug("<----------SHARE ATTACHMENT----------->");
				log.debug("AttachmentLink:"+attachmentLink);
				log.debug("AttachmentName"+attachmentName);
				log.debug("Time:"+time);
				log.debug("Duration:"+duration);
				///////////////////////////////////////////
		        try
		        {
				//String type="pdf";
				result.put("link", attachmentLink);
				result.put("name", attachmentName);
				result.put("timestamp", time);
				result.put("duration", duration);
				result.put("type", type);
				
				int classEventDetailId=(Integer) sessionObj.getAttribute("classEventId");
				
				System.out.println("CLASSSSSEVENT"+classEventDetailId);
				
				int batchId=(Integer)sessionObj.getAttribute("courseBatchId");
				String teacherId=(String)sessionObj.getAttribute("userId");
		
				String teacherName=userService.getUserName(teacherId);
				List<String> userIdList=userService.getAllStudentInClass(batchId);
		
				JsonParser<String, Hashtable<String,Object>> wsJson = new JsonParser<String, Hashtable<String,Object>>("shareAttachment",1,teacherName+" shared Atachment",sessionID,"shareAttachmentResult",result);
				String a=wsJson.JsonResponseText();
				WebSocketServiceImp wsObj=new WebSocketServiceImp();
				try {
					wsObj.writeTextmessage(userIdList, a,true);
				} catch (IOException e) {
					e.printStackTrace();
				}
				result.clear();
		
				int i=recordService.updateAttachmentFlag(classEventDetailId);
				if(i>0)
				{
					log.debug("Attachment Flag Updated");
				}
				else
				{
					log.debug("Attachment flag updation error!");
				}
		
				AttachmentData attachmentObj=new AttachmentData();
				attachmentObj.putNewData(batchId,attachmentName, attachmentLink,duration,time,type);
		
				message="Success";
				resultCode=1;
		        }
		        catch (Exception e) {
					
					e.printStackTrace();
					message = "Server Exception : "+e.getCause();
					resultCode = 2;
				}
		
				JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("shareAttachment",1,message,sessionID,"shareAttachmentResult",result);
				return jsonResponse.JsonResponseText();
		
			}
	
			@SuppressWarnings("unchecked")
			@RequestMapping(value = "/raiseHand", method = RequestMethod.POST)
			public String getRaiseHand(HttpSession sessionObj,HttpServletResponse response,@RequestParam("raiseHandText") String raiseHandText,
					@RequestParam("timestamp") String time,
					@RequestParam("duration") String duration,@RequestParam("classEventDetailId") int classEventDetailId,
					@RequestParam("sessionID") String sessionID,
					@RequestParam("userId") String userId)
			
			{
				
				Hashtable<String,Object> result=new Hashtable<String,Object>();
		
				////////////////////////////////////
				log.debug("RaiseHandText:"+raiseHandText);
				log.debug("Time:"+time);
				log.debug("Duration:"+duration);
				////////////////////////////////////
				
				String savedSessionID = userService.geUserSession(userId);
				
				if(userId!=null && sessionID.equals(savedSessionID))
				{ 
					log.debug("Session Valid");
					try
					{
					List<String> studDetails=userService.getStudentBatchId(userId);
					int courseBatchId =Integer.parseInt( studDetails.get(4));
					
					
					
				
				//List<String> teacherName= new ArrayList<String>(Arrays.asList("senzit".split(",")));
					
				List<String> teacherName= recordService.getTeacherFromBatch(courseBatchId);
					
				
				
				
				
				System.err.println("Teacher In Class : "+teacherName);
					
					String studentName=userService.getUserName(userId);
				     String profilePic=userService.getProfilePic(userId);
				     
					System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+courseBatchId);
			
					System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+teacherName);
					
					System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+studentName);
					Date date = new Date();
					java.sql.Timestamp ReminderTime = new java.sql.Timestamp(date.getTime());
					
					
					recordService.saveRaiseHandDetails(userId,teacherName.get(0),raiseHandText,classEventDetailId,ReminderTime); 
			
					WebSocketServiceImp wsObj=new WebSocketServiceImp();
					
					result.put("profilePic", profilePic);
					result.put("studentName", studentName);
					result.put("raiseHandText", raiseHandText);
					result.put("userId", userId);
					result.put("timestamp", time);
					result.put("duration", duration);
				
					JsonParser<String, Hashtable<String,Object>> wsJson = new JsonParser<String, Hashtable<String,Object>>("raiseHand",1,studentName+" asked a question",sessionID,"raiseHandResult",result);
					String a= wsJson.JsonResponseText();	
						
						try {
							   
	//						if(RaiseHandQueue.getQueueSize(teacherName.get(0))==0){
								wsObj.writeTextmessage(teacherName, a,true);
	//							RaiseHandQueue.addToQueue(teacherName.get(0), a);
								  
	//						}
	//						else{
	//							RaiseHandQueue.addToQueue(teacherName.get(0), a);
	//
	//						}
							
						} catch (IOException e) {
							e.printStackTrace();
						
						}
	
		
			    	result.clear();
		
					resultCode=1;
					message="Success";
				   }
					catch (Exception e) {
							
							e.printStackTrace();
							message = "Server Exception : "+e.getCause();
							resultCode = 2;
					}
				}
				
				else
				{
					log.debug("Invalid Session");
					message="Invalid Session";
					resultCode=0;
				}
		
				JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("raiseHand",resultCode,message,sessionID,"raiseHandResult",result);
				return jsonResponse.JsonResponseText();
		
			}
		
		@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
		public String uploadImage(HttpSession sessionObj,HttpServletResponse response,@RequestParam("file") MultipartFile file1,@RequestParam("classEventDetailId") int classEventDetailId,
			@RequestParam("scheduleId") int scheduleId,@RequestParam("userId") String userId,@RequestParam("imageName") String name,@RequestParam("duration") String duration,@RequestParam("timestamp") String time,
			@RequestParam("type") String type,
			@RequestParam("sessionID") String sessionID){
			
			
			
				Hashtable<String,Object> result=new Hashtable<String,Object>();
			;
				if(!(file1.isEmpty()))
					{
						log.debug("FileSize:"+file1.getSize());
						log.debug(scheduleId);
						log.debug(name);
						log.debug(duration);
						log.debug(time);
						log.debug(type);
						
	                    String imageName=System.currentTimeMillis()+file1.getOriginalFilename();
	                    /////////////////////////////////////////
	                    /////need to edit/////
	                    ////////////////////////////////////
	                    Integer CourseBatchId = schedulerService.getCourseBatchId(scheduleId);//////
	                    //////////////////////
	                	String rebbonId = (String)sessionObj.getAttribute("rebbonId");
	                	//Integer CourseBatchId = schedulerService.getCourseBatchIdForTeacher(rebbonId);
					   /////////////////////////////////////
						System.out.println(CourseBatchId);
						System.out.println(userId);
						
						String teacherName=userService.getUserName(userId);
						List<String> userIdList=userService.getAllStudentInClass(CourseBatchId);
						System.out.println(teacherName);
						System.out.println(userIdList);
						
						byte imageType=1;
						log.debug("FileName:"+file1.getOriginalFilename());
						try
						{
						boolean uploadResult=recordService.saveUploadedImage(file1,imageName,classEventDetailId,imageType,name,time,type);
								if(uploadResult==true)
								{
								String link=CyberService.getWorkingFolderWebPath("Recorder", classEventDetailId)+"/"+imageName;
							
								result.put("link", link);
								result.put("name", name);
								result.put("timestamp", time);
								result.put("duration", duration);
								result.put("type",type );
								
								JsonParser<String, Hashtable<String,Object>> wsJson = new JsonParser<String, Hashtable<String,Object>>("shareAttachment",1,teacherName+" shared Atachment",sessionID,"shareAttachmentResult",result);
								String a=wsJson.JsonResponseText();
								WebSocketServiceImp wsObj=new WebSocketServiceImp();
								try {
									wsObj.writeTextmessage(userIdList, a,true);
								} catch (IOException e) {
									e.printStackTrace();
								}
	
								AttachmentData attachmentObj=new AttachmentData();
								attachmentObj.putNewData(CourseBatchId,name, link,duration,time,type);
								resultCode=1;
								message="Success";
								}
						
								else
								{
									resultCode=0;
									message="upload failed";
								}
								
								result.clear();
						}
						catch (Exception e) {
							
							e.printStackTrace();
							message = "Server Exception : "+e.getCause();
							resultCode = 2;
						}
					}		
					JsonParser<String, String> jsonResponse = new JsonParser<String, String>("uploadImage",resultCode,message,sessionID,"uploadimageResult","success");
					return jsonResponse.JsonResponseText();
			
			}
		
		
	
		@RequestMapping(value = "/uploadImageFromPortal", method = RequestMethod.POST)
		public String uploadImage(HttpSession sessionObj,HttpServletResponse response,@RequestParam("file") String file,@RequestParam("classEventDetailId") int classEventDetailId,
			@RequestParam("scheduleId") int scheduleId,@RequestParam("userId") String userId,@RequestParam("imageName") String name,@RequestParam("duration") String duration,@RequestParam("timestamp") String time,
			@RequestParam("type") String type,
			@RequestParam("sessionID") String sessionID){
			
			
				Hashtable<String,Object> result=new Hashtable<String,Object>();
			;
				if(!(file.isEmpty()))
					{
						//log.debug("FileSize:"+file1.getSize());
						log.debug(scheduleId);
						log.debug(name);
						log.debug(duration);
						log.debug(time);
						log.debug(type);
						
	                   
	                    Integer CourseBatchId = schedulerService.getCourseBatchId(scheduleId);//////
	                    //////////////////////
	                	String rebbonId = (String)sessionObj.getAttribute("rebbonId");
	                	//Integer CourseBatchId = schedulerService.getCourseBatchIdForTeacher(rebbonId);
					   /////////////////////////////////////
						System.out.println(CourseBatchId);
						System.out.println(userId);
						
						String teacherName=userService.getUserName(userId);
						List<String> userIdList=userService.getAllStudentInClass(CourseBatchId);
						System.out.println(teacherName);
						System.out.println(userIdList);
						
						byte imageType=1;
						//log.debug("FileName:"+file1.getOriginalFilename());
						try
						{
						boolean uploadResult=recordService.saveUploadedImageFromPortal(file,name,classEventDetailId,imageType,time,type);
								if(uploadResult==true)
								{
								String link=CyberService.getWorkingFolderWebPath("Recorder", classEventDetailId)+"/"+name+"."+"jpg";
							
								result.put("link", link);
								result.put("name", name);
								result.put("timestamp", time);
								result.put("duration", duration);
								result.put("type",type );
								
								JsonParser<String, Hashtable<String,Object>> wsJson = new JsonParser<String, Hashtable<String,Object>>("shareAttachment",1,teacherName+" shared Atachment",sessionID,"shareAttachmentResult",result);
								String a=wsJson.JsonResponseText();
								WebSocketServiceImp wsObj=new WebSocketServiceImp();
								try {
									wsObj.writeTextmessage(userIdList, a,true);
								} catch (IOException e) {
									e.printStackTrace();
								}
	
								AttachmentData attachmentObj=new AttachmentData();
								attachmentObj.putNewData(CourseBatchId,name, link,duration,time,type);
								resultCode=1;
								message="Success";
								}
						
								else
								{
									resultCode=0;
									message="upload failed";
								}
								
								result.clear();
						}
						catch (Exception e) {
							
							e.printStackTrace();
							message = "Server Exception : "+e.getCause();
							resultCode = 2;
						}
					}		
					JsonParser<String, String> jsonResponse = new JsonParser<String, String>("uploadImageFromPortal",resultCode,message,sessionID,"uploadImageFromPortalResult","success");
					return jsonResponse.JsonResponseText();
			
			}
		
		@RequestMapping(value = "/uploadImageFromAndroid", method = RequestMethod.POST)
		public String uploadImageFromAndroid(HttpSession sessionObj,HttpServletResponse response,@RequestParam("file") String file,@RequestParam("classEventDetailId") int classEventDetailId,
			@RequestParam("scheduleId") int scheduleId,@RequestParam("userId") String userId,@RequestParam("imageName") String name,@RequestParam("duration") String duration,@RequestParam("timestamp") String time,
			@RequestParam("type") String type,
			@RequestParam("sessionID") String sessionID){
			
			
				Hashtable<String,Object> result=new Hashtable<String,Object>();
			;
				if(!(file.isEmpty()))
					{
						//log.debug("FileSize:"+file1.getSize());
						log.debug(scheduleId);
						log.debug(name);
						log.debug(duration);
						log.debug(time);
						log.debug(type);
						
	                   
	                    Integer CourseBatchId = schedulerService.getCourseBatchId(scheduleId);//////
	                    //////////////////////
	                	String rebbonId = (String)sessionObj.getAttribute("rebbonId");
	                	//Integer CourseBatchId = schedulerService.getCourseBatchIdForTeacher(rebbonId);
					   /////////////////////////////////////
						System.out.println(CourseBatchId);
						System.out.println(userId);
						
						String teacherName=userService.getUserName(userId);
						List<String> userIdList=userService.getAllStudentInClass(CourseBatchId);
						System.out.println(teacherName);
						System.out.println(userIdList);
						
						byte imageType=1;
						//log.debug("FileName:"+file1.getOriginalFilename());
						try
						{
						boolean uploadResult=recordService.saveUploadedImageFromAndroid(file,name,classEventDetailId,imageType,time,type);
								if(uploadResult==true)
								{
								String link=CyberService.getWorkingFolderWebPath("Recorder", classEventDetailId)+"/"+name+"."+"jpg";
							
								result.put("link", link);
								result.put("name", name);
								result.put("timestamp", time);
								result.put("duration", duration);
								result.put("type",type );
								
								JsonParser<String, Hashtable<String,Object>> wsJson = new JsonParser<String, Hashtable<String,Object>>("shareAttachment",1,teacherName+" shared Atachment",sessionID,"shareAttachmentResult",result);
								String a=wsJson.JsonResponseText();
								WebSocketServiceImp wsObj=new WebSocketServiceImp();
								try {
									wsObj.writeTextmessage(userIdList, a,true);
								} catch (IOException e) {
									e.printStackTrace();
								}
	
								AttachmentData attachmentObj=new AttachmentData();
								attachmentObj.putNewData(CourseBatchId,name, link,duration,time,type);
								resultCode=1;
								message="Success";
								}
						
								else
								{
									resultCode=0;
									message="upload failed";
								}
								
								result.clear();
						}
						catch (Exception e) {
							
							e.printStackTrace();
							message = "Server Exception : "+e.getCause();
							resultCode = 2;
						}
					}		
					JsonParser<String, String> jsonResponse = new JsonParser<String, String>("uploadImageFromAndroid",resultCode,message,sessionID,"uploadImageFromPortalResult","success");
					return jsonResponse.JsonResponseText();
			
			}
		
		@RequestMapping(value = "/stopRecord", method = RequestMethod.POST)
		public String stopRecord(HttpSession sessionObj,HttpServletResponse response,@RequestParam("timestamp") String time,
				@RequestParam("duration") String duration,@RequestParam("stopRecordJson") String stopRecordJson,
				@RequestParam("sessionID") String sessionID,@RequestParam("scheduleId") int scheduleId) throws ParseException
		{ 
			
			
			Hashtable<String,Object> result=new Hashtable<String,Object>();
	
			String rebbonLink=(String)sessionObj.getAttribute("rebbonLink");
			int classEventDetailId=(Integer) sessionObj.getAttribute("classEventId");
			int batchId=(Integer)sessionObj.getAttribute("courseBatchId"); 
			String teacherId=(String)sessionObj.getAttribute("userId");
			String rebbonId = (String)sessionObj.getAttribute("rebbonId");
		
			int totalAttendees=0;
			String month=null;
			////////////////////////////////////////////
			log.debug("Json:"+stopRecordJson);
			log.debug("Stop Record Time:"+time);
			log.debug("StoprRecord Duration:"+duration);
			log.debug("UserId:"+teacherId);
			log.debug("RebbonLInk"+rebbonLink);
			log.debug("CourseBatchId:"+batchId);
			log.debug("ClassEventDetailId:"+classEventDetailId);
			////////////////////////////////////////////////
	
			JSONObject jsonData;
			try {
	
				jsonData = new JSONObject(stopRecordJson);
	
				JSONArray RmNoteArray = jsonData.getJSONArray("reminderNotes");
				Calendar cal = Calendar.getInstance();
	
				//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				   Date date = new Date();
				   
				   java.sql.Timestamp ReminderTime = new java.sql.Timestamp(date.getTime());
				   String NoteJson = RmNoteArray.toString();
				   try
				   {
				   boolean isInsert = recordService.insertClazNote(classEventDetailId,teacherId,NoteJson);
				   }
				   catch (Exception e) 
					{
	
							e.printStackTrace();
							message = "Server Exception : "+e.getCause();
							resultCode = 2;
					}
				   
				for(int i=0;i<RmNoteArray.length();i++)
				{
					JSONObject temp = RmNoteArray.getJSONObject(i);
					
					String tempRnote = temp.getString("notes");
					
					log.debug("ReminderNote"+i+":"+tempRnote);
					recordService.saveReminderNote(teacherId,tempRnote,classEventDetailId,ReminderTime);
				}
	
			   log.debug("ATTENDANCE FOR STUDENT");
				
				JSONObject attendance = jsonData.getJSONObject("attendanceList");
				JSONArray onlineList =attendance.getJSONArray("onlineList");
				//JSONArray offlineList = attendance.getJSONArray("offlineList");
	
				UserAttendanceTime timeObj=new UserAttendanceTime();
				List<String> userList = new ArrayList<String>();
	
				SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
				String CurrentTime = sdf1.format(cal.getTime());
	
				String[] t= CurrentTime.split(":");
				int currentMinute = Integer.parseInt(t[0])*60+Integer.parseInt(t[1]);
				System.out.println("currentminute "+currentMinute);
				
				for(int i=0;i<onlineList.length();i++)
				{
					JSONObject temp = onlineList.getJSONObject(i);
					userList.add(temp.getString("userId"));
				}
	
				for(int i=0;i<userList.size();i++)
				{
	
					String userId = (String) userList.get(i);
					ArrayList<Properties> atList =timeObj.getData(userId);
					if(atList!=null)
					{
						Properties attendanceProp = new Properties();
						attendanceProp = atList.get(0);
						String startTime =attendanceProp.getProperty("startTime");
						System.out.println("student attended the class at"+startTime);
						String[] tempTime=startTime.split(":");
						int minute = Integer.parseInt(tempTime[0])*60+Integer.parseInt(tempTime[1]);
						////////////////////need to edit(attendance log)//////////////////
						/////////////////////////////////////////////////
						/////////////////////////////////////////
						//if((currentMinute-minute)<30)
						if((currentMinute-minute)<2)
						{
							userList.remove(i);
						}
					}
					else
					{
						//userList.remove(i);
						log.debug("no user attended the session");
						
					}
	
				}
				totalAttendees=userList.size();
				System.out.println("SSSSSSSSSSSSHGHHHHHHHHHHHHHHHHHHHHHHHHHHH"+totalAttendees);
				Calendar c = Calendar.getInstance();
				for(int i=0;i<totalAttendees;i++)
				{
	
					String userId = (String) userList.get(i);
	
					int courseSubId= recordService.getCourseSubjectId(scheduleId);
			
					log.debug("CourseSubjectId" +courseSubId);
					
					month= c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH );
					System.out.println(month);
					String month1=month.toString();
					int attendedSession=1;
					int totalNumberOfSession=50;
	     			recordService.saveAttendanceDetails(userId,courseSubId,classEventDetailId,attendedSession,totalNumberOfSession,month1);
				
					log.debug("attendanceDetails save successfully");
	
					
				}
				////////////////////////////////////////
				 log.debug("ATTENDANCE FOR TEACHER");
				 
				 ArrayList<Properties> teacherList =timeObj.getData(teacherId);
				 int minute1 = 0;
				 if(teacherList!=null)
					{
						Properties Prop = new Properties();
						Prop = teacherList.get(0);
						String startTime =Prop.getProperty("startTime");
						System.out.println("StartTime"+startTime);
						String[] tempTime=startTime.split(":");
						 minute1 = Integer.parseInt(tempTime[0])*60+Integer.parseInt(tempTime[1]);
					}
				   int time1=currentMinute-minute1;
				   System.out.println("currentTime"+currentMinute);
				   System.out.println("recordingStarttime"+minute1);
				   System.out.println("workinghours"+time1);
	//				month= c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH );
					
				   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			
				   //System.out.println(dateFormat.format(date)); don't print it, but save it!
				   String yourDate = dateFormat.format(date); 
	//			   recordService.saveWorkingHoursinDB(teacherId,time1,yourDate);
				   recordService.saveWorkingHoursinDB(teacherId,time1,batchId);
				   
				   //////////////////////////////////////
				   try
				   {
				  ScheduleData scheduleObj = new ScheduleData();
		          scheduleObj.removescheduleId(scheduleId, teacherId);
		          System.out.println("currentScheduleId"+scheduleId);
				  scheduleObj.removescheduleIdStud(scheduleId,batchId);
				   }
				   catch (Exception e) 
				{
	
						e.printStackTrace();
						message = "Server Exception";
						resultCode = 2;
				}
	
			} 
			
			catch (JSONException e) 
			{
	
				e.printStackTrace();
				message = "Server Exception : "+e.getCause();
				resultCode = 2;
			}
	
		
			List<String> userIdList=userService.getAllStudentInClass(batchId);
	
			result.put("timestamp", time);
			result.put("duration", duration);
	
			JsonParser<String, Hashtable<String,Object>> wsJson = new JsonParser<String, Hashtable<String,Object>>("stopRecord",1,"Recording Stopped",sessionID,"stopRecordResult",result);
			String a=wsJson.JsonResponseText();
			WebSocketServiceImp wsObj=new WebSocketServiceImp();
			try {
				wsObj.writeTextmessage(userIdList, a,true);
			} catch (IOException e) {
				e.printStackTrace();
			}
	
			result.clear();
	
			ArrayList<Properties> logList=new ArrayList<Properties>();
			ArrayList<Properties> attachmentList=new ArrayList<Properties>();
			
			System.out.println("GENERALLOG IN STOPRECORD");
			
			GeneralLog gLog=new GeneralLog();
			ArrayList<Properties> lgList =gLog.getData(batchId);
			System.out.println("######################################################");
			System.out.println("General logSSSSSSSSSSSSSSSSSSSSSS"+lgList);
			if(lgList!=null)
				logList=lgList;
			System.out.println("######################################################");
			System.out.println("General logMMMMMMMMMMMMMMMMMMMMMMMMM"+logList);
	
			JSONArray logArray = new JSONArray();
			try
			{
	
				for(int i=0;i<logList.size();i++)
				{
					Properties logProp = new Properties();
					logProp = logList.get(i);
					JSONObject tempLog = new JSONObject();
					tempLog.put("timestamp", logProp.get("timestamp"));
					tempLog.put("duration", logProp.get("duration"));
					tempLog.put("profilePic", logProp.get("profilePic"));
					tempLog.put("logText", logProp.get("logText"));
					tempLog.put("raiseHandText", logProp.get("raiseHandText"));
					tempLog.put("raiseHandAnswer", logProp.get("raiseHandAnswer"));
						
					logArray.put(tempLog);
					
				}
	
	//			generalLog.append("GeneralLog", logArray);
	
			}
	
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Exception"+e.getCause());
			}
	
			String grlog =  logArray.toString();
			
			if(recordService.updateDB(classEventDetailId, grlog,totalAttendees))
			{
				System.out.println("GeneralLog DB Savinggggggggggggggggggggggggggggggggggg:"+grlog);
				log.debug("generalLog Saved");
				message="Success";
				resultCode=1;
			}
			AttachmentData AtObj = new AttachmentData();
			ArrayList<Properties> atList=AtObj.getData(batchId);
			if(atList!=null)
				attachmentList=atList;
	
			for(int i=0;i<attachmentList.size();i++)
			{
				Properties AtProp = new Properties();
				AtProp = attachmentList.get(i);
	
				String createdOn =  (String) AtProp.get("timestamp");
				log.debug("AttachmentTime:"+createdOn);
				try
				{
					
					Calendar calendar = Calendar.getInstance();
				    java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTime().getTime());
					log.debug("parsedTime:"+timestamp);
					byte att=0;
					String attachmentName=(String) AtProp.get("attachmentName");
					String attachmentLink=(String) AtProp.get("attachmentLink");
					String attachmentType=(String) AtProp.get("documentType");
	
					recordService.SaveAttachmentDetails(classEventDetailId,attachmentName,attachmentLink,attachmentType,timestamp,att);
				}
				catch(Exception e)
				{
					e.printStackTrace();
					log.debug("Exception");
				}
			}
	
			gLog.removeData(batchId);
	
			AtObj.removeData(batchId);
	 
			RebbonService.stopRebbon(rebbonLink, classEventDetailId);
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("stopRecord",resultCode,message,sessionID,"stopRecordResult",result);
			return jsonResponse.JsonResponseText();
	
		}
		
		
		@RequestMapping(value = "/forceStop", method = RequestMethod.POST)
		public String forceStop(HttpSession sessionObj,HttpServletResponse response,@RequestParam("timestamp") String time,
				@RequestParam("duration") String duration,@RequestParam("classEventId_crash") int classEventDetailId,@RequestParam("scheduleId") int scheduleId,
				@RequestParam("sessionID") String sessionID)
		
		{ 
			response.setHeader("Access-Control-Allow-Origin", "http://192.168.0.210:10080");
			Hashtable<String,Object> result=new Hashtable<String,Object>();
	
			String rebbonLink=(String)sessionObj.getAttribute("rebbonLink");
			String teacherId=(String)sessionObj.getAttribute("userId");
			String rebbonId = (String)sessionObj.getAttribute("rebbonId");
			Integer batchId = schedulerService.getCourseBatchIdForTeacher(rebbonId);
			int totalAttendees=0;
			
			////////////////////////////////////////////
		
	
			log.debug("UserId:"+teacherId);
			log.debug("RebbonLInk"+rebbonLink);
			log.debug("CourseBatchId:"+batchId);
			log.debug("ClassEventDetailId:"+classEventDetailId);
			////////////////////////////////////////////////
	
			JSONObject jsonData;
			
			Calendar cal = Calendar.getInstance();
				Date date = new Date();
	
	
	
				List<String> userIdList=userService.getAllStudentInClass(batchId);
				 ScheduleData scheduleObj = new ScheduleData();
		         scheduleObj.removescheduleId(scheduleId, teacherId);
		         System.out.println("currentScheduleId"+scheduleId);
				  scheduleObj.removescheduleIdStud(scheduleId,batchId);
				  
				result.put("timestamp", time);
				result.put("duration", duration);
	
				JsonParser<String, Hashtable<String,Object>> wsJson = new JsonParser<String, Hashtable<String,Object>>("stopRecord",1,"Recording Stopped",sessionID,"stopRecordResult",result);
				String a=wsJson.JsonResponseText();
				WebSocketServiceImp wsObj=new WebSocketServiceImp();
				try {
					wsObj.writeTextmessage(userIdList, a,true);
				} catch (IOException e) {
					e.printStackTrace();
				}
	
				result.clear();
				
	
			ArrayList<Properties> logList=new ArrayList<Properties>();
			ArrayList<Properties> attachmentList=new ArrayList<Properties>();
			
			System.out.println("GENERALLOG IN STOPRECORD");
			
			GeneralLog gLog=new GeneralLog();
			ArrayList<Properties> lgList =gLog.getData(batchId);
			System.out.println("######################################################");
			System.out.println("General logSSSSSSSSSSSSSSSSSSSSSS"+lgList);
			if(lgList!=null)
				logList=lgList;
			System.out.println("######################################################");
			System.out.println("General logMMMMMMMMMMMMMMMMMMMMMMMMM"+logList);
	
			JSONArray logArray = new JSONArray();
			try
			{
	
				for(int i=0;i<logList.size();i++)
				{
					Properties logProp = new Properties();
					logProp = logList.get(i);
					JSONObject tempLog = new JSONObject();
					tempLog.put("timestamp", logProp.get("timestamp"));
					tempLog.put("duration", logProp.get("duration"));
					tempLog.put("profilePic", logProp.get("profilePic"));
					tempLog.put("logText", logProp.get("logText"));
					tempLog.put("raiseHandText", logProp.get("raiseHandText"));
					tempLog.put("raiseHandAnswer", logProp.get("raiseHandAnswer"));
						
					logArray.put(tempLog);
					
				}
	
	//			generalLog.append("GeneralLog", logArray);
	
			}
	
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Exception"+e.getCause());
			}
	
			String grlog =  logArray.toString();
			try
			{
			if(recordService.updateDB(classEventDetailId, grlog,totalAttendees))
			{
				System.out.println("GeneralLog DB Savinggggggggggggggggggggggggggggggggggg:"+grlog);
				log.debug("generalLog Saved");
				message="Success";
				resultCode=1;
			}
			AttachmentData AtObj = new AttachmentData();
			ArrayList<Properties> atList=AtObj.getData(batchId);
			if(atList!=null)
				attachmentList=atList;
	
			for(int i=0;i<attachmentList.size();i++)
			{
				Properties AtProp = new Properties();
				AtProp = attachmentList.get(i);
	
				String createdOn =  (String) AtProp.get("timestamp");
				log.debug("AttachmentTime:"+createdOn);
				try
				{
					
					Calendar calendar = Calendar.getInstance();
				    java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTime().getTime());
					log.debug("parsedTime:"+timestamp);
					byte att=0;
					String attachmentName=(String) AtProp.get("attachmentName");
					String attachmentLink=(String) AtProp.get("attachmentLink");
					String attachmentType=(String) AtProp.get("documentType");
	
					recordService.SaveAttachmentDetails(classEventDetailId,attachmentName,attachmentLink,attachmentType,timestamp,att);
				}
				catch(Exception e)
				{
					e.printStackTrace();
					log.debug("Exception");
				}
			}
			
	    	RebbonService.stopRebbon(rebbonLink, classEventDetailId);
			}
			catch (Exception e) {
				
				e.printStackTrace();
				message = "Server Exception : "+e.getCause();
				resultCode = 2;
			}
			
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("forceStop",resultCode,message,sessionID,"forceStopResult",result);
			return jsonResponse.JsonResponseText();
	
		}
		@RequestMapping(value = "/ClassStopAfterCrash", method = RequestMethod.POST)
		public String ClassStopAfterCrash(HttpSession sessionObj,HttpServletResponse response,@RequestParam("classEventId_crash") int classEventDetailId,
				@RequestParam("sessionID") String sessionID)
		
		{  
			Hashtable<String,Object> result=new Hashtable<String,Object>();
	
			String rebbonLink=(String)sessionObj.getAttribute("rebbonLink");
			
			
			String teacherId=(String)sessionObj.getAttribute("userId");
			
			String rebbonId = (String)sessionObj.getAttribute("rebbonId");
			Integer batchId = schedulerService.getCourseBatchIdForTeacher(rebbonId);
			int totalAttendees=0;
			
			////////////////////////////////////////////
			log.debug("UserId:"+teacherId);
			log.debug("RebbonLInk"+rebbonLink);
			log.debug("CourseBatchId:"+batchId);
			log.debug("ClassEventDetailId:"+classEventDetailId);
			////////////////////////////////////////////////
	
			   JSONObject jsonData;
			   Calendar cal = Calendar.getInstance();
				Date date = new Date();
				List<String> userIdList=userService.getAllStudentInClass(batchId);
				JsonParser<String, Hashtable<String,Object>> wsJson = new JsonParser<String, Hashtable<String,Object>>("stopRecord",1,"Recording Stopped",sessionID,"stopRecordResult",result);
				String a=wsJson.JsonResponseText();
				WebSocketServiceImp wsObj=new WebSocketServiceImp();
				try {
					wsObj.writeTextmessage(userIdList, a,true);
				} catch (IOException e) {
					e.printStackTrace();
				}
	
				result.clear();
				
	
			ArrayList<Properties> logList=new ArrayList<Properties>();
			ArrayList<Properties> attachmentList=new ArrayList<Properties>();
			
			System.out.println("GENERALLOG IN STOPRECORD");
			
			GeneralLog gLog=new GeneralLog();
			ArrayList<Properties> lgList =gLog.getData(batchId);
			System.out.println("######################################################");
			System.out.println("General logSSSSSSSSSSSSSSSSSSSSSS"+lgList);
			if(lgList!=null)
				logList=lgList;
			System.out.println("######################################################");
			System.out.println("General logMMMMMMMMMMMMMMMMMMMMMMMMM"+logList);
	
			JSONArray logArray = new JSONArray();
			try
			{
	
				for(int i=0;i<logList.size();i++)
				{
					Properties logProp = new Properties();
					logProp = logList.get(i);
					JSONObject tempLog = new JSONObject();
					tempLog.put("timestamp", logProp.get("timestamp"));
					tempLog.put("duration", logProp.get("duration"));
					tempLog.put("profilePic", logProp.get("profilePic"));
					tempLog.put("logText", logProp.get("logText"));
					tempLog.put("raiseHandText", logProp.get("raiseHandText"));
					tempLog.put("raiseHandAnswer", logProp.get("raiseHandAnswer"));
						
					logArray.put(tempLog);
					
				}
	
	//			generalLog.append("GeneralLog", logArray);
	
			}
	
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Exception"+e.getCause());
			}
	
			String grlog =  logArray.toString();
			try
			{
			if(recordService.updateDB(classEventDetailId, grlog,totalAttendees))
			{
				System.out.println("GeneralLog DB Savinggggggggggggggggggggggggggggggggggg:"+grlog);
				log.debug("generalLog Saved");
				message="Success";
				resultCode=1;
			}
			AttachmentData AtObj = new AttachmentData();
			ArrayList<Properties> atList=AtObj.getData(batchId);
			if(atList!=null)
				attachmentList=atList;
	
			for(int i=0;i<attachmentList.size();i++)
			{
				Properties AtProp = new Properties();
				AtProp = attachmentList.get(i);
	
				String createdOn =  (String) AtProp.get("timestamp");
				log.debug("AttachmentTime:"+createdOn);
				try
				{
					
					Calendar calendar = Calendar.getInstance();
				    java.sql.Timestamp timestamp = new java.sql.Timestamp(calendar.getTime().getTime());
					log.debug("parsedTime:"+timestamp);
					byte att=0;
					String attachmentName=(String) AtProp.get("attachmentName");
					String attachmentLink=(String) AtProp.get("attachmentLink");
					String attachmentType=(String) AtProp.get("documentType");
	
					recordService.SaveAttachmentDetails(classEventDetailId,attachmentName,attachmentLink,attachmentType,timestamp,att);
				}
				catch(Exception e)
				{
					e.printStackTrace();
					log.debug("Exception");
				}
			}
			
	    	RebbonService.stopRebbon(rebbonLink, classEventDetailId);
			}
			catch (Exception e) {
				
				e.printStackTrace();
				message = "Server Exception : "+e.getCause();
				resultCode = 2;
			}
			
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("ClassStopAfterCrash",resultCode,message,sessionID,"ClassStopAfterCrashResult",result);
			return jsonResponse.JsonResponseText();
	
		}
		
		@RequestMapping(value = "/exitSession", method = RequestMethod.POST)
		public String exitSession(HttpSession sessionObj,HttpServletResponse response,@RequestParam("clazNoteJson") String clazNote,@RequestParam("clazEventId") int classEventId,
				@RequestParam("sessionID") String sessionID,
				@RequestParam("userId") String userId)
		{
			
			Hashtable<String,Object> result=new Hashtable<String,Object>();
	
			
			String savedSessionID = userService.geUserSession(userId);
			int courseBatchId=Integer.parseInt((String)sessionObj.getAttribute("CourseBatchId"));
			log.debug("ClazNoteJson:"+clazNote);
			log.debug("ClazEventId:"+classEventId);
	
			//int courseBatchId=(Integer)sessionObj.getAttribute("courseBatchId"); 
			log.debug("courseBatchId"+courseBatchId);
			
			if(userId!=null && sessionID.equals(savedSessionID))
			{ 
				log.debug("Session Valid");
				String studentName=userService.getUserName(userId);
				List<String> teacherName=recordService.getTeacherFromBatch(courseBatchId);
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("E,dd-MM-YYYY,HH:mm");
				log.debug( sdf.format(cal.getTime()) );
	
				String CurrentDate = sdf.format(cal.getTime());
				String[] temp= CurrentDate.split(",");
	
			
				String time = temp[2];
	
				WebSocketServiceImp wsObj=new WebSocketServiceImp();
				result.put("userId", userId);
				result.put("ServerTime", time);
				result.put("studentName", studentName);
	
				JsonParser<String, Hashtable<String,Object>> wsJson = new JsonParser<String, Hashtable<String,Object>>("removeAttendanceFromRecorder",1,"Please remove "+studentName,sessionID,"result",result);
				String a=wsJson.JsonResponseText();
				wsObj.resetInClassFlag(userId);
				try {
					wsObj.writeTextmessage(teacherName, a,false);
				} catch (IOException e) {
					e.printStackTrace();
				}
				result.clear();
	
				UserAttendanceTime timeObj = new UserAttendanceTime();
				timeObj.removeData(userId);	
				try
				{
	              
	               
					JSONObject jsonData = new JSONObject(clazNote);
					
					JSONArray clazNoteArray = jsonData.getJSONArray("classNotes");
					String clazNoteJson = clazNoteArray.toString();
					if(!clazNoteJson.isEmpty())
					{
					try
					{
					boolean isInsert = recordService.insertClazNote(classEventId,userId,clazNoteJson);
					if(isInsert==true)
					{
						log.debug("ClazNoteInsertedSuccessfully");
						message="Success";
						resultCode=1;
					}
					else
					{
						message="ClazNoteInsertionFailure";
						resultCode=0;
					}
					}
					catch(Exception e)
					{
						e.printStackTrace();
						message = "Server Exception : "+e.getCause();
						resultCode = 2;
					}
	
			    	} 
	              
				}
	
				catch (JSONException e) 
				{
					e.printStackTrace();
					message="Exception";
					resultCode=0;
				}
			}
			else
			{
				log.debug("Invalid Session");
				message="Invalid Session";
				resultCode=0;
			}
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("exitSession",resultCode,message,sessionID,"exitSessionResult",result);
			return jsonResponse.JsonResponseText();
	
		}
	
		@RequestMapping(value = "/raiseHandQueueNotification", method = RequestMethod.POST)
		 public String getQueueNotification(HttpSession sessionObj,HttpServletResponse response,@RequestParam("userId")  List<String> userId,
		   @RequestParam("raiseHandText") String raiseHandText,
			@RequestParam("sessionID") String sessionID)
		 {
			
		 
		  Hashtable<String,Object> result=new Hashtable<String,Object>();
		  String teacherId=(String)sessionObj.getAttribute("userId");
		  int classEventDetailId=(Integer) sessionObj.getAttribute("classEventId");
		  String rebbonId = (String)sessionObj.getAttribute("rebbonId");
		  ////////////////////////////////////
		  log.debug("TeacherId:"+teacherId);
		  log.debug("StudentId:"+userId);
		  log.debug("RaiseHandText:"+raiseHandText);
		 
		  List<String> userIdList=userId;
		  ////////////////////////////////////
			String savedSessionID = userService.geUserSession(teacherId);
		  
		  if(teacherId!=null && sessionID.equals(savedSessionID))
		  { 
		   log.debug("Session Valid");
		   String teacherName=userService.getUserName(teacherId);
		   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		   Date date = new Date();
		   
		   java.sql.Timestamp ReminderTime = new java.sql.Timestamp(date.getTime());
		   try
		   {
			Integer CourseBatchId = schedulerService.getCourseBatchIdForTeacher(rebbonId);
				
		   List<String> studentList=userService.getAllStudentInClass(CourseBatchId);
		  // recordService.saveRaiseHandDetails(userIdList.get(0),teacherId,raiseHandText,classEventDetailId,ReminderTime); 
		   
		   WebSocketServiceImp wsObj1=new WebSocketServiceImp();
	
		   result.put("queuedQuestion", raiseHandText);
		   //result.put("userId", userId);
		   try {
		    JsonParser<String, Hashtable<String,Object>> wsJson = new JsonParser<String, Hashtable<String,Object>>("raiseHandQueueNotification",1,teacherName+" queued your question",sessionID,"queueNotificationResult",result);
		    String a= wsJson.JsonResponseText();
		    
		    wsObj1.writeTextmessage(studentList, a,true);
		    
		    	    
		   // RaiseHandQueue.deleteFromQueue(teacherId, raiseHandText, userId.get(0));
	 	  
		   } catch (IOException e) {
		    e.printStackTrace();
		    message="WebSocket Failed";
		   }
	
		   result.put("profilePic", userService.getProfilePic(userId.get(0)));
	
		   resultCode=1;
		   message="Success";
		  }
		   catch (Exception e) {
				
				e.printStackTrace();
				message = "Server Exception : "+e.getCause();
				resultCode = 2;
			}
		  }
		  else
		  {
		   log.debug("Invalid Session");
		   message="Invalid Session";
		   resultCode=0;
		  }
		  
	
		  JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("raiseHand",resultCode,message,sessionID,"raiseHandResult",result);
		  return jsonResponse.JsonResponseText();
	
		 }
	
		@RequestMapping(value = "/raiseHandAskADoubtAnswer", method = RequestMethod.POST)
		public String raiseHandAskADoubtAnswer(HttpSession sessionObj,HttpServletResponse response,@RequestParam("notificationId") int notificationId,
				@RequestParam("notificationFlag") String flag,@RequestParam("answer") String answer,
				@RequestParam("sessionID") String sessionID)
			
		{
			
			Hashtable<String,Object> result=new Hashtable<String,Object>();
			String userId=(String)sessionObj.getAttribute("userId");		
			String savedSessionID = userService.geUserSession(userId);
			List<HashMap> notificationResult=new ArrayList<HashMap>();
			String answeredQuestion="Answeredquestion";
		    String reminderFlag="Reminder";
			
			////////////////////////////////////////
			log.debug("flag:"+flag);
			log.debug("answer:"+answer);
			log.debug("notificationId:"+notificationId);
	
		  
			if(userId!=null && sessionID.equals(savedSessionID))	
			{ 
			log.debug("Session Valid");
			
			  List<String> userIdList=dashService.getUserIdofStudent(notificationId);
			  //String question=userIdList.get(1);
			  String question=dashService.getQuestionFromId(notificationId);
			  String profilePic=userService.getProfilePic(userId);
			  
			  WebSocketServiceImp wsObj=new WebSocketServiceImp();
			  System.out.println("userTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+userIdList);
			  
			  
			   result.put("notificationId", notificationId);
			   result.put("question", question);
			   result.put("answer", answer);
			   result.put("raiseHandOrDoubt", flag);
			   result.put("teacherProfilePic", profilePic);
			   
			  
			   try {
				    JsonParser<String, Hashtable<String,Object>> wsJson = new JsonParser<String, Hashtable<String,Object>>("raiseHandAskADoubtAnswer",1,"answers of raiseHand and doubts",sessionID,"raiseHandAskADoubtAnswerResult",result);
				    String a= wsJson.JsonResponseText();
	
				    wsObj.writeTextmessage(userIdList, a,false);
				   } catch (IOException e) {
				    e.printStackTrace();
				    message="WebSocket Failed";
				   }
			   
			   result.clear();
			   
			if(flag.equals("RH")||flag.equals("doubt"))
			{
			recordService.updateAnswerForRaiseHandandDoubtinDb(notificationId,answer,answeredQuestion);
			resultCode=1;
			message="save raiseHand answer successfully";
			notificationResult = dashService.getAllNotification(userId);
			result.put("teacherNotifications", notificationResult);
			}
			else 
			{
				recordService.updateAnswerForRaiseHandandDoubtinDb(notificationId,answer,reminderFlag);
				resultCode=1;
				message="save raiseHand answer successfully";
				notificationResult = dashService.getAllNotification(userId);
				result.put("teacherNotifications", notificationResult);
			}
			
			
			   resultCode=1;
			   message="Success";
			}
			else
			{
				log.debug("Invalid Session");
				message="Invalid Session";
				resultCode=0;
			}
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("raiseHandAskADoubtAnswer",resultCode,message,sessionID,"raiseHandAskADoubtAnswerResult",result);
			return jsonResponse.JsonResponseText();
	
		} 
	
		@RequestMapping(value = "/raiseHandQueuedQuestionAnswer", method = RequestMethod.POST)
		public String raiseHandQueuedQuestionAnswerFromTeacher(HttpSession sessionObj,HttpServletResponse response,@RequestParam("notificationId") int notificationId,
			@RequestParam("answer") String answer,
			@RequestParam("sessionID") String sessionID)
			
		{
			Hashtable<String,Object> result=new Hashtable<String,Object>();
			String userId=(String)sessionObj.getAttribute("userId");		
			String savedSessionID = userService.geUserSession(userId);
			List<HashMap> notificationResult=new ArrayList<HashMap>();
			String answeredQuestion="RHQueuedAnswer";
		  
			
			////////////////////////////////////////
	
			log.debug("answer:"+answer);
			log.debug("notificationId:"+notificationId);
	
		  
			if(userId!=null && sessionID.equals(savedSessionID))	
			{ 
			log.debug("Session Valid");
			  
			try
			{
			recordService.updateAnswerForRaiseHandandDoubtinDb(notificationId,answer,answeredQuestion);
	//		resultCode=1;
	//		message="save raiseHand answer successfully";
			notificationResult = dashService.getAllNotification(userId);
			result.put("teacherNotifications", notificationResult);
			
	//			recordService.updateAnswerForRaiseHandandDoubtinDb(notificationId,answer,answeredQuestion);
	//			resultCode=1;
	//			message="save raiseHandqueued answer successfully";
			
			   resultCode=1;
			   message="Success";
			}
			catch (Exception e) {
				
				e.printStackTrace();
				message = "Server Exception : "+e.getCause();
				resultCode = 2;
			}
			}
			else
			{
				log.debug("Invalid Session");
				message="Invalid Session";
				resultCode=0;
			}
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("raiseHandQueuedQuestionAnswer",resultCode,message,sessionID,"raiseHandQueuedQuestionAnswerResult",result);
			return jsonResponse.JsonResponseText();
	
		} 
		  @RequestMapping(value = "/questionRemoval", method = RequestMethod.POST)
			 public String getRaiseHandNotification(HttpSession sessionObj,HttpServletResponse response,@RequestParam("fromUserId") String fromUserId,
			   @RequestParam("raiseHandAskaDoubtQuestion") String raiseHandAskaDoubtQuestion,
				@RequestParam("sessionID") String sessionID)
			 {
			  Hashtable<String,Object> result=new Hashtable<String,Object>();
				List<HashMap> notificationResult=new ArrayList<HashMap>();
				
				String userId=(String)sessionObj.getAttribute("userId");		
				String savedSessionID = userService.geUserSession(userId);
		       
			  log.debug("fromUserId:"+fromUserId);
					
			  log.debug("raiseHandAskaDoubtQuestion:"+raiseHandAskaDoubtQuestion);
		
			  
			  if(userId!=null && sessionID.equals(savedSessionID))
			  { 
			   log.debug("Session Valid");
			 
			   WebSocketServiceImp wsObj=new WebSocketServiceImp();
			   try
			   {
			   int value=recordService.deleteQuestionFromDB(raiseHandAskaDoubtQuestion,fromUserId);
	           if(value!=0)
	           {
	        	notificationResult = dashService.getAllNotification(userId);
	   			result.put("teacherNotifications", notificationResult);
	  	  
			   resultCode=1;
			   message="Success";
			  }
			   }
			   catch (Exception e) {
					
					e.printStackTrace();
					message = "Server Exception : "+e.getCause();
					resultCode = 2;
				}
			  }
			  else
			  {
			   log.debug("Invalid Session");
			   message="Invalid Session";
			   resultCode=0;
			  }
			  
			  JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("questionRemoval",resultCode,message,sessionID,"questionRemovalResult",result);
			  return jsonResponse.JsonResponseText();
	
			 }
		  @RequestMapping(value = "/studentNotificationClick", method = RequestMethod.POST)
			 public String studentNotification(HttpSession sessionObj,HttpServletResponse response,@RequestParam("notificationId") int notificationId,
						@RequestParam("sessionID") String sessionID
			   )
			 {
			  Hashtable<String,Object> result=new Hashtable<String,Object>();
				
			  String userId=(String)sessionObj.getAttribute("userId");		
				String savedSessionID = userService.geUserSession(userId);
		        String questionClick="Answerclick";
			    log.debug("notificationId:"+notificationId);
					
			  if(userId!=null && sessionID.equals(savedSessionID))
			  { 
			   log.debug("Session Valid");
			   
				 try
				 {
				    recordService.updateNotificationFlag(notificationId,questionClick);
		          
				    List<HashMap> notificationResult = dashService.getAllNotificationForStudent(userId);
					result.put("studentNotifications", notificationResult);
					 resultCode=1;
					 message="Success";
				  }
				 catch (Exception e) {
						
						e.printStackTrace();
						message = "Server Exception : "+e.getCause();
						resultCode = 2;
					}
			  }
			  else
			  {
			   log.debug("Invalid Session");
			   message="Invalid Session";
			   resultCode=0;
			  }
			  
			  JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("studentNotificationClick",resultCode,message,sessionID,"studentNotificationClickResult",result);
			  return jsonResponse.JsonResponseText();
	
			 }
		 @RequestMapping(value ="/uploadAttachmentViaPortal", method = RequestMethod.POST)
			public String uploadAttachmentViaPortal(HttpSession sessionObj,HttpServletResponse response,@RequestParam("fileName") MultipartFile file1,
					@RequestParam("attName") String name,@RequestParam("attDescription") String description,
					@RequestParam("userId") String userId,
					@RequestParam("sessionID") String sessionID) {
				
				  Hashtable<String,Object> result=new Hashtable<String,Object>();
				  log.debug("File :"+file1);
			      log.debug("name :"+name);
			      log.debug("description :"+description);
				  
				    String attachmentName=file1.getOriginalFilename();
				    String ext = FilenameUtils.getExtension(attachmentName);
				    
				    System.err.println("extention"+ext);
				    try
				    {		 
					portalService.saveAttachmentDetailsUploadFromPortal(userId,name,description,file1,ext);
					resultCode=1;
					message="success";
				    }
				    catch (Exception e) {
						
						e.printStackTrace();
						message = "Server Exception : "+e.getCause();
						resultCode = 2;
					}
				
	     JsonParser<String,String> jsonResponse = new JsonParser<String,String>("uploadAttachmentViaPortal",resultCode,message,sessionID,"uploadAttachmentViaPortalResult","success");
		 return jsonResponse.JsonResponseText();
				
			
		}
		 @RequestMapping(value ="/attachmentDeletion", method = RequestMethod.POST)
			public String attachmentDeletion(HttpSession sessionObj,HttpServletResponse response,@RequestParam("mediaId") int mediaId,
					@RequestParam("sessionID") String sessionID) {
				
				 Hashtable<String,Object> result=new Hashtable<String,Object>();
				
			      log.debug("mediaId :"+mediaId);
	
					try
					{
					boolean value=portalService.deleteAttachmentDetailsUploadFromPortal(mediaId);
					if(value==true)
					{
					resultCode=1;
					message="success";
					}
					else
					{
						resultCode=0;
						message="failure";
					}
					}
					catch (Exception e) {
						
						e.printStackTrace();
						message = "Server Exception : "+e.getCause();
						resultCode = 2;
					}
	  JsonParser<String,String> jsonResponse = new JsonParser<String,String>("attachmentDeletion",resultCode,message,sessionID,"attachmentDeletionResult","success");
		 return jsonResponse.JsonResponseText();
				
			
		}
		 
		 @RequestMapping(value ="/portalAttachment", method = RequestMethod.POST)
			public String attachmentFromPortal(HttpSession sessionObj,HttpServletResponse response,
					@RequestParam("sessionID") String sessionID){
				
				 Hashtable<String,Object> result=new Hashtable<String,Object>();
				 
				 String userId=(String)sessionObj.getAttribute("userId");		
					String savedSessionID = userService.geUserSession(userId);
					if(userId!=null && sessionID.equals(savedSessionID))	
					{ 
					 log.debug("Session Valid");
					 try
					 {
					List<Properties> attachmentList=portalService.getAttachmentDetailsUploadedViaPortalForDisplay(userId);
						if(!attachmentList.isEmpty())
						{
			    		result.put("attachmentList", attachmentList);
						resultCode=1;
						message="success";
						}
						else
						{
							System.out.println("no attachments for this user");
							resultCode=0;
							message="failure";
						}
					}
					catch (Exception e) {
						
						e.printStackTrace();
						message = "Server Exception : "+e.getCause();
						resultCode = 2;
					}
					}
					else
					{
						   log.debug("Invalid Session");
						   message="Invalid Session";
						   resultCode=0;
							
					}
	
			
				
				JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("portalAttachment",resultCode,message,sessionID,"portalAttachmentResult",result);
				return jsonResponse.JsonResponseText();
				
			
		}
			
				 @RequestMapping(value ="/getAllClassDetails", method = RequestMethod.POST)
					public String getAllClassDetails(HttpSession sessionObj,HttpServletResponse response,
							@RequestParam("sessionID") String sessionID){
						
						 Hashtable<String,Object> result=new Hashtable<String,Object>();
						 
		
							 List<Properties> courseDetails=portalService.getDetailsAboutCourse();
							 if(!courseDetails.isEmpty())
							 {
								System.out.println(("CourseDetails"+courseDetails.size()));  
								result.put("courseDetails", courseDetails);
								List<Properties> subjectDetails=portalService.getSubjectDetails();
								if(!subjectDetails.isEmpty())
								{
									System.out.println(("CourseDetails"+courseDetails.size()));  
									result.put("subjectsDetails", subjectDetails);
									 List<Properties> semDetails=portalService.getSemDetails();
									 if(!semDetails.isEmpty())
									 {
										 result.put("semDetails", semDetails);
										 List<Properties> batchDetails=portalService.getBatchDetails();
										 if(!batchDetails.isEmpty())
										 {
												result.put("batchDetails", batchDetails); 
												 List<Properties> classRoomDetails=portalService.getClassRoomDetails();
												 if(!classRoomDetails.isEmpty())
												 {
														 result.put("classRoomDetails", classRoomDetails);
														 List<Properties> dayDetails=portalService.getDayDetails();
														 if(!classRoomDetails.isEmpty())
														 {
																result.put("dayDetails", dayDetails);
																 List<Properties> periodDetails=portalService.getPeriodDetails();
																 if(!periodDetails.isEmpty())	 
																 {
																		result.put("periodDetails", periodDetails);
																		 List<Properties> studentBatchDetails=portalService.getStudentBatchDetails();
																		 if(!studentBatchDetails.isEmpty())	 
																		 {
																		 result.put("studentBatchDetails",studentBatchDetails); 
																	    List<Properties> courseBatchDetails=portalService.getcourseBatchDetails();
																		 if(!courseBatchDetails.isEmpty())
																		 {
																		 result.put("courseBatchDetails", courseBatchDetails);
																		 List<Properties> courseSubjectDetails=portalService.getcourseSubjectDetailsFromDb();
																		 if(!courseSubjectDetails.isEmpty())
																		 {
		                                                                  result.put("courseSubjectDetails", courseSubjectDetails); 
																		  List<Properties> subjectTeacherDetails=portalService.getSubjectTeacherDetails();
																		 if(!subjectTeacherDetails.isEmpty())
																		 {
																		 result.put("subjectTeacherDetails", subjectTeacherDetails);
																		 List<Properties> scheduleDetails=portalService.getScheduleDetails();
																		 if(!scheduleDetails.isEmpty())
																		 {
																		 result.put("scheduleDetails", scheduleDetails);
																		 resultCode=1;
																		 message="success";
																		 }
																		 else
																		 {
																			 log.debug("scheduleDetails are not yet set");
																			  message="scheduleDetails are not yet set";
																			  resultCode=0;    
																		 }
																		 }
																		 else
																		 {
																			  log.debug("subjectTeacherDetails are not yet set");
																			  message="subjectTeacherDetails are not yet set";
																			  resultCode=0;   
																			 
																		 }
																		 }
																		 else
																		 {
																			  log.debug("courseSubjectDetails are not yet set");
																			  message="courseSubjectDetails are not yet set";
																			  resultCode=0;   
																			 
																		 }
																		 }
																		 else
																		 {
																			  log.debug("courseBatch details are not yet set");
																			   message="courseBatch details are not yet set";
																			   resultCode=0;   
																			 
																		 }
																		 }
																		 else
																		 {
																			  log.debug("StudentBatch details are not yet set");
																			   message="StudentBatch details are not yet set";
																			   resultCode=0;  
																		 }
																 }
																 else
																 {
																	 log.debug("No period details found");
																	   message="No period details found";
																	   resultCode=0;  
																 }
														 }
														 else
														 {
															 log.debug("No day details found");
															   message="No day details found";
															   resultCode=0; 
														 }
												 }
												 else
												 {
													 log.debug("No classroom details found");
													   message="No classroom details found";
													   resultCode=0;
												 }
										 }
										 else
										 {
											  log.debug("No batch details found");
											   message="No batch details found";
											   resultCode=0;
										 }
									 }
									 else
									 {
										 log.debug("No semester details found");
										   message="No semester details found";
										   resultCode=0;
									 }
								}
								else
								{
									  log.debug("No Subject found");
									   message="No Subject found";
									   resultCode=0;
								}
								
							}
			
							 else
								{
									   log.debug("No courses found");
									   message="No courses found";
									   resultCode=0;
										
								}
			            	
						
						JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("getAllClassDetails",resultCode,message,sessionID,"getAllClassDetailsResult",result);
						return jsonResponse.JsonResponseText();
						
					
				}
	//    @RequestMapping(value ="/crashTest", method = RequestMethod.POST)
	//		public String crashTest(HttpSession sessionObj){
	//					
	//					
	//				    Hashtable<String,Object> result=new Hashtable<String,Object>();
	//				    String rebbonId = (String)sessionObj.getAttribute("rebbonId");
	//				    String userId = (String)sessionObj.getAttribute("userId");
	//				
	//					if(userId == null){
	//						
	//						message = "Invalid session";
	//					}
	//					else
	//					{
	//					List<String> TeacherList = recordService.getTeacherInClass(rebbonId);
	//					String teacherId=TeacherList.get(0);
	//					WebSocketServiceImp wsObj=new WebSocketServiceImp();
	//					wsObj.getWebSocketTeacher(teacherId);
	//					if(wsObj.getWebSocketTeacher(teacherId)==true)
	//					{
	//						
	//						result.put("TeacherAppCrashed","yes");
	//					}
	//					else
	//					{
	//						result.put("TeacherAppCrashed","no");
	//					}
	//					}
	//					
	//
	//					JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("crashTest",resultCode,message,"crashTestResult",result);
	//					return jsonResponse.JsonResponseText();
	//					
	//				
	//			}
	
	}
