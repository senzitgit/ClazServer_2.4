	package com.senzit.cyberclaz.server.controller;
	
	import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
	
	import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
	
	import com.senzit.cyberclaz.server.model.AttachmentData;
import com.senzit.cyberclaz.server.model.AttentionData;
import com.senzit.cyberclaz.server.model.GeneralLog;
import com.senzit.cyberclaz.server.model.ScheduleData;
import com.senzit.cyberclaz.server.model.SwitchVideoData;
import com.senzit.cyberclaz.server.model.WebSocketData;
import com.senzit.cyberclaz.server.service.DashService;
import com.senzit.cyberclaz.server.service.RecordService;
import com.senzit.cyberclaz.server.service.SchedulerService;
import com.senzit.cyberclaz.server.service.UserServices;
import com.senzit.cyberclaz.server.subservice.JsonParser;
import com.senzit.cyberclaz.server.subservice.RebbonService;
	
	@RestController
	public class DashController 
	{
		static Logger log = Logger.getLogger(DashController.class.getName());
		int resultCode=0;
		String message="";
		
		@Autowired
		private DashService dashService;
		@Autowired
		private RecordService recordService;
		@Autowired
		private UserServices userService;
		@Autowired
		private SchedulerService schedulerService;
	
		public void setDashService(DashService dashService) {
			this.dashService = dashService;
		}
	
		public void setRecordService(RecordService recordService) {
			this.recordService = recordService;
		}
	
		public void setUserService(UserServices userService) {
			this.userService = userService;
		}
	
	
		@RequestMapping(value ="/ratingVideo", method = RequestMethod.POST)
		public String ratingAVideo(HttpSession sessionObj,HttpServletResponse response,@RequestParam("ratingvalue") String ratingvalue,
				@RequestParam("sessionID") String sessionID){
			
			System.out.println("in");
			System.out.println("rating"+ratingvalue);
			
		    Hashtable<String,Object> result=new Hashtable<String,Object>();
			int resultCode = 0;
			String message;
			
			boolean value=userService.saveExcellentRatingDetailsInDb(ratingvalue);
	//		if(value==true)
	//		{
	    
				System.out.println(" rating details saved");
				//List<Integer> resultNew=userService.getCountValue(ratingvalue);
				//result.put("ratingCountOfaVideo", resultNew.size());
				resultCode=1;
				message="success";
	//		}
	//		else
	//		{
	//			System.out.println("error");
	//			resultCode=0;
	//			message="failure";
	//		}
	//	
	//		
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("ratingVideo",resultCode,message,sessionID,"videoRatingResult",result);
			return jsonResponse.JsonResponseText();
			
		
	}
			
		
		@RequestMapping(value = "/getTime", method = RequestMethod.POST)
		public String getTime(HttpSession sessionObj,HttpServletResponse response,
				@RequestParam("role") String roleName,
				@RequestParam("sessionID") String sessionID,
				@RequestParam("userId") String userId)
		{
			Hashtable<String,Object> result=new Hashtable<String,Object>();
			List<HashMap> notificationResult=new ArrayList<HashMap>();
			List<Properties> nullList = new ArrayList<Properties>();

			
			String savedSessionID = userService.geUserSession(userId);
			
			
			
			
			
			String rebbonId = (String)sessionObj.getAttribute("rebbonId");
			//////////////////////
			
			log.debug("UserId="+userId);
		
			log.debug("role="+roleName);
			//////////////////////
	
			if(userId!=null && sessionID.equals(savedSessionID))
			{
				log.debug("Valid Session");
	
				ArrayList<Integer> List=new ArrayList<Integer>();
			    ArrayList<Integer> List1=new ArrayList<Integer>();
			    ScheduleData ob = new ScheduleData();
				
	
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("E,dd-MM-YYYY,HH:mm");
				log.debug( sdf.format(cal.getTime()) );
	
				String CurrentDate = sdf.format(cal.getTime());
				String[] temp= CurrentDate.split(",");
	
				String day = temp[0];
				String date =temp[1];
				String time = temp[2];
	            //String newTime=time.split(":");
				result.put("day",day);
				result.put("date",date);
				result.put("time",time);
				
				try
				{
				if(sessionObj.getAttribute("role").equals("Teacher"))
				{
					
		         String rebbonLink = recordService.getRebbonLink(rebbonId);
			     System.out.println("rebbonId"+rebbonId);
		    	try
			  {
				//RebbonService.startRebbon(rebbonLink);
			  }
			   catch(Exception e)
			    {
			 		e.printStackTrace();
					message = "Server Exception : Response from Rebbon null";
					resultCode = 2;	
				}
				
				ArrayList<Integer> scheduleList=ob.getData(userId);
				if(scheduleList!=null)
					List=scheduleList;
				
				result.put("newScheduleList", List);
				log.debug("Rebbon Started");
				notificationResult = dashService.getAllNotification(userId);
				result.put("teacherNotifications", notificationResult);
			
	        	Integer CourseBatchId = schedulerService.getCourseBatchIdForTeacher(rebbonId);
	        	List<HashMap> CurrentliveDetails = recordService.classDetailsAfterCrash(CourseBatchId,time);
	        	if(!(CurrentliveDetails.isEmpty())) 
					
				{
					result.put("liveClass", 1);
					result.put("classDetailsAfterCrash",CurrentliveDetails);
					//List.add(Integer.parseInt(CurrentliveDetails.get(0).get("CurrentScheduleId").toString()));
					
				  
				}
				else
				{
					  result.put("liveClass", 0);
					  result.put("classDetailsAfterCrash",nullList);
					  
				}
	  	
	   			
				}
				else if(sessionObj.getAttribute("role").equals("Student"))
				{
				
				
					
					Integer batchId=Integer.parseInt((String) sessionObj.getAttribute("CourseBatchId"));
					ArrayList<Integer> scheduleListOne=ob.getDataone(batchId);
					System.out.println("getTimeScheduleList"+scheduleListOne);
					
					if(scheduleListOne!=null)
						List1=scheduleListOne;
					System.out.println("CourseBatchid "+batchId);
					System.out.println("getTimeScheduleList111111111111111"+List1);
					notificationResult = dashService.getAllNotificationForStudent(userId);
					result.put("studentNotifications", notificationResult);
					result.put("newScheduleList", List1);
					
				}
				
				message="Success";
				resultCode=1;
				log.debug("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS"+WebSocketData.wsData);
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
				message="Invalid session";
				resultCode=0;
			}
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("getTime",resultCode,message,sessionID,"getTimeResult",result);
			return jsonResponse.JsonResponseText();
		}
		@RequestMapping(value="/getUserDetails", method=RequestMethod.POST)
		public void userDetails(HttpSession sessionObj,HttpServletResponse response,
				@RequestParam("sessionID") String sessionID)
		{
			Hashtable<String,Object> result=new Hashtable<String,Object>();
			
			System.out.println("userDetails");
			
		}
		
		@RequestMapping(value ="/dummyTest", method = RequestMethod.POST)
		public String dummyTest(HttpSession sessionObj,HttpServletResponse response,@RequestParam("pollJson") String jsonString,
				@RequestParam("sessionID") String sessionID){
			
			System.out.println("in");
			System.out.println(jsonString);
		    Hashtable<String,Object> result=new Hashtable<String,Object>();
			int resultCode = 1;
			String message="success";
			
			try {
				boolean value=userService.dummyTestValues(jsonString);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("dummyTest",resultCode,message,sessionID,"dummyTestResult",result);
			return jsonResponse.JsonResponseText();
			
		
	}
		
	}
