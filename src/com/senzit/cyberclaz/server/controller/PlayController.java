	package com.senzit.cyberclaz.server.controller;
	
	import java.io.IOException;
	
	import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
	
	import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
	
import com.senzit.cyberclaz.server.model.CyberInit;
	import com.senzit.cyberclaz.server.service.CognosService;
import com.senzit.cyberclaz.server.service.PlayerService;
import com.senzit.cyberclaz.server.service.UserServices;
import com.senzit.cyberclaz.server.service.RecordService;
import com.senzit.cyberclaz.server.service.WebSocketServiceImp;
import com.senzit.cyberclaz.server.subservice.CyberService;
import com.senzit.cyberclaz.server.subservice.JsonParser;
import com.senzit.cyberclaz.server.subservice.RebbonService;
	
	@RestController
	public class PlayController 
	{
		static Logger log = Logger.getLogger(PlayController.class.getName());
	
		public PlayController() {}
	
		@Autowired 
	
		private RecordService recordService;
		@Autowired
		private PlayerService playerService;
		@Autowired
		private UserServices userService;
		@Autowired
		private CognosService cognosService;
	
		public void setCognosService(CognosService cognosService) {
			this.cognosService = cognosService;
		}
	
		public void setPlayerService(PlayerService playerService) {
			this.playerService = playerService;
		}
	
		public void setUserService(UserServices userService) {
			this.userService = userService;
		}
		
	
		public void setRecordService(RecordService recordService) {
			this.recordService = recordService;
		}
	
		int resultCode=0;
		String message="";
	
		@SuppressWarnings("unused")
		@RequestMapping(value = "/advancedSearch", method = RequestMethod.POST)
		public String advancedSearch(HttpSession sessionObj,HttpServletResponse response,
				@RequestParam("subject") String subjectId,@RequestParam("date") String date,@RequestParam("topic") String topic,
				@RequestParam("sessionID") String sessionID,
				@RequestParam("userId") String userId)
		{
			Hashtable<String,Object> result=new Hashtable<String,Object>();
			List<Properties> nullList = new ArrayList<Properties>();
			
			
			
			String savedSessionID = userService.geUserSession(userId);
			
			////////////////////////////////////////
			log.debug("Subject:"+subjectId);
			log.debug("Date:"+date);
			log.debug("Topic:"+topic);
			////////////////////////////////////////
				
			if(userId!=null && sessionID.equals(savedSessionID))
			{ 
				result.put("subjectList", nullList);
				result.put("dateList", nullList);
				result.put("topicList", nullList);
				result.put("dateSubjectList", nullList);
				result.put("classList", nullList);
				result.put("subjTopicList", nullList);
				result.put("fullSearchList", nullList);
				
				try
				{
	
				if(subjectId!=null && date.isEmpty()&& topic.isEmpty())
				{
	
					
						List<Properties> datelistTr = playerService.getDateFromSubject(subjectId);
						
						
						
						System.err.println("Final"+datelistTr);
						
						result.put("subjectList", datelistTr);
						
				
				}
				/////////////////////////////////////////////////////////////////////////////////////////
				else if(subjectId.isEmpty() && !date.isEmpty()&& topic.isEmpty())
				{
	
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					Date date1=null;
					try {
						if(!date.isEmpty())date1= formatter.parse(date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				
				      List<Properties> datelistTr = playerService.getClassEventDetail(date);
						result.put("dateList", datelistTr);
						log.debug("dateList:"+datelistTr.size());
					
					
				}
							
				/////////////////////////////////////////////////////////////////////////////////////////
				else if(subjectId.isEmpty() && date.isEmpty()&& !topic.isEmpty())
				{
						
				 
					 List<Properties> datelistTr = playerService.getClassEventDetailFromTopic(topic);
				     result.put("topicList", datelistTr);
				     log.debug("TeacherList:"+datelistTr.size());
				 
		
					
				}
	
				else if(!subjectId.isEmpty() && !date.isEmpty()&& topic.isEmpty())
				{
	
				  
				     List<Properties> datelistTr = playerService.getClassEventDetailFromDateSubject(date, subjectId);
				     result.put("dateSubjectList", datelistTr);
				     log.debug("TeacherList:"+datelistTr.size());
				    
				    
				}
	
				else if(!subjectId.isEmpty() && date.isEmpty()&& !topic.isEmpty())
				{
				   
				     List<Properties> datelistTr = playerService.getClassEventDetailfromSubjectTopic(subjectId, topic);
				     result.put("subjTopicList", datelistTr);
				     log.debug("TeacherList:"+datelistTr.size());
				    
				}
	
				else if(subjectId.isEmpty() && !date.isEmpty()&& !topic.isEmpty())
				{
				   
				     List<Properties> datelistTr = playerService.getClassEventDetailFromDateTopic(date, topic);
				     result.put("classList", datelistTr);
				     log.debug("TeacherList:"+datelistTr.size());
				    
				  
				}
	
				else if(!subjectId.isEmpty() && !date.isEmpty()&& !topic.isEmpty())
				{
				   
				     List<Properties> datelistTr = playerService.getClassEventDetailFromSDT(date, subjectId, topic);
				     result.put("fullSearchList", datelistTr);
				     log.debug("TeacherList:"+datelistTr.size());
				   
				
				}
				resultCode =1;
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
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("advancedSearch",resultCode,message,sessionID,"SearchResult",result);
			return jsonResponse.JsonResponseText();
		}
	
		@RequestMapping(value = "/getPlayerInfo", method = RequestMethod.POST)
		public String getPlayerInfo(HttpSession sessionObj,HttpServletResponse response,
				@RequestParam("clazEventDetailId") int clazEventDetailId,
				@RequestParam("sessionID") String sessionID,
				@RequestParam("userId") String userId) throws JSONException
			{
			Hashtable<String,Object> result=new Hashtable<String,Object>();
			List<Properties> nullList = new ArrayList<Properties>();
			
			//String rebbonLink1=(String)sessionObj.getAttribute("rebbonLink");
			
			
			String rebbonLink    = CyberInit.cyberProperty.getProperty("rebbonIp");
			
			
			String savedSessionID = userService.geUserSession(userId);
			
			/////////////////////////////////////////
			log.debug("ClazEventDetailId:"+clazEventDetailId);
			log.debug("RebbonLink:"+rebbonLink);
			////////////////////////////////////////
			if(userId!=null && sessionID.equals(savedSessionID))
			{
				//String avLink =null;
				try
				{
				String teacherId=playerService.getTeacherIdFromClazEventId(clazEventDetailId);
				cognosService.saveViewersCount(clazEventDetailId);
				
				boolean value=playerService.saveViewersDetailsInDb(userId,clazEventDetailId,teacherId);
	
				
				Properties avResult = new Properties();
				try {
					String avLink = RebbonService.getAvFilesFromRebbon(rebbonLink, clazEventDetailId,userId);
					
					JSONObject jsn = new JSONObject(avLink);
	
					JSONObject jsonObjresp = null;
					jsonObjresp = jsn.getJSONObject("response");
	
					JSONObject camResp = null;
					camResp	=jsonObjresp.getJSONObject("RecordedFeeds");
	
					avResult.put("videoFeed1", (String) camResp.get("VideoFeed1"));
					avResult.put("videoFeed2", (String) camResp.get("VideoFeed2"));
					avResult.put("videoFeed3", (String) camResp.get("VideoFeed3"));
					avResult.put("videoFeed4", (String) camResp.get("VideoFeed4"));
					avResult.put("audioFeed1", (String) camResp.get("AudioFeed1"));
					avResult.put("audioFeed2", (String) camResp.get("AudioFeed2"));
					avResult.put("audioFeed3", (String) camResp.get("AudioFeed3"));
					avResult.put("audioFeed4", (String) camResp.get("AudioFeed4"));
				
	
				
					
					log.debug("AVLink:"+avResult);
					
				} catch (IOException e) {
	
					e.printStackTrace();
					message = "Server Exception : AVLInk from Rebbon Null";
					resultCode = 2;
				}
			
				result.put("avDetails",avResult);
				
				List<String> viewerList=playerService.getViewersList(clazEventDetailId)	;
				Properties viewerratingCount=playerService.getViewerRatingCount(clazEventDetailId)	;
				
				 result.put("viewersList", viewerList);
				 result.put("viewersRating", viewerratingCount);
				 result.put("viewersCount", viewerList.size());
				
				 List<Properties> attachmentList=recordService.getAttachmentDetailsUploadedViaPortal(userId);
				 result.put("portalAttachmentList", attachmentList);
				
				 List<Properties> raiseHandAskaDoubtAnswer= playerService.getAnswerForRaiseHandDoubt(clazEventDetailId);
				 result.put("raiseHandAskaDoubtAnswer", raiseHandAskaDoubtAnswer);
				
				 List<HashMap> classEventDetails= playerService.getClassDetail(clazEventDetailId);
		         result.put("classEventDetails", classEventDetails);
			
				List<Properties>  attachmentLink = playerService.getAttachmentLink(clazEventDetailId);
				log.debug("Attachments:"+attachmentLink);
				result.put("AttachmentInfo",attachmentLink);
			
			    String profilePic=playerService.getProfilePicFromEventId(clazEventDetailId);
				System.out.println("PPPPPPPPPPPPPPPPPRRRRRfilepic"+profilePic);
				result.put("ProfilePic", profilePic);
				
			try
			{
				List<HashMap> clazNotes = new ArrayList<HashMap>();
				List<String> notes = playerService.getClazNotes(clazEventDetailId,userId);
				if(!(notes.isEmpty()))
				{
					
					String profilePicStud=userService.getProfilePic(userId);
					
					for(int i=0;i<notes.size();i++)
					{
					String tempNote = notes.get(i);
					JSONArray jarray = new JSONArray(tempNote);
					
					for(int j=0;j<jarray.length();j++)
					{
						JSONObject jTemp = new JSONObject();
						jTemp = jarray.getJSONObject(j);
						HashMap classProp = new HashMap();
						classProp.put("notes",jTemp.get("notes"));
						classProp.put("time",jTemp.get("time"));
						clazNotes.add(classProp);
	
					}
					result.put("Notes", clazNotes);
					}
					result.put("ProfilePicStudentCls", profilePicStud);
				}
				else
				{
					result.put("Notes", nullList);
					//result.put("ProfilePicStudentCls", "");
				}
				
				resultCode =1;
				message="Success";
			
				}
				
			     catch (Exception e) {
							
							e.printStackTrace();
							message = "Server Exception : ClazNote Null";
							resultCode = 2;
			}
					
			}	
	       catch (Exception e) {
					
					e.printStackTrace();
					message = "Server Exception : GeneralLog Empty";
					resultCode = 2;
				}
			}
			
			else
			{
				log.debug("Invalid Session");                      
				message="Invalid Session";
				resultCode=0;
			}
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("getPlayerInfo",resultCode,message,sessionID,"PlayerInfoResult",result);
			String temp = jsonResponse.JsonResponseText();
			String json = temp.replaceAll("\\\\", "");
			return json;
		}
	
		@RequestMapping(value ="/userLikerating", method = RequestMethod.POST)
		public String ratingAVideo(HttpSession sessionObj,HttpServletResponse response,@RequestParam("clazEventDetailId") int clazEventDetailId,
				@RequestParam("sessionID") String sessionID,
				@RequestParam("userId") String userId){
			
			log.debug("ClazEventDetailId:"+clazEventDetailId);
		
			
			
			String savedSessionID = userService.geUserSession(userId);
		    Hashtable<String,Object> result=new Hashtable<String,Object>();
			int resultCode = 0;
			String message;
			
	        try
	        {
			playerService.saveRatingDetailsInDb(clazEventDetailId);
			playerService.updateInViewerTable(clazEventDetailId);
	//		if(value==true)
	//		{
	    
				System.out.println(" rating details saved");
	//			int mostPopularEventId=playerService.getUserCountValue();
	//			result.put("mostPopularEventId", mostPopularEventId);
	//			
				//cognosService.saveUserRatingAboutTeacher(teacherId,ratingvalue);
				resultCode=1;
				message="success";
	        }
	        catch(Exception e)
	        {
	        	resultCode=2;
	        	message="exception";
	        }
	
			
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("userLikerating",resultCode,message,sessionID,"videoRatingResult",result);
			return jsonResponse.JsonResponseText();
			
		
	}
		@RequestMapping(value ="/userDislikeRating", method = RequestMethod.POST)
		public String userDislikeRating(HttpSession sessionObj,HttpServletResponse response,@RequestParam("clazEventDetailId") int clazEventDetailId,
				@RequestParam("sessionID") String sessionID,
				@RequestParam("userId") String userId){
			
			log.debug("ClazEventDetailId:"+clazEventDetailId);
		
			
			
			String savedSessionID = userService.geUserSession(userId);
		    Hashtable<String,Object> result=new Hashtable<String,Object>();
			int resultCode = 0;
			String message;
			
	
			try
	        {
			   playerService.saveDislikeRatingDetailsInDb(clazEventDetailId);
	
				System.out.println(" rating details saved");
	
				resultCode=1;
				message="success";
	           }
			 catch(Exception e)
		        {
		        	resultCode=2;
		        	message="exception";
		        }
			
		
			
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("userDislikeRating",resultCode,message,sessionID,"videoRatingResult",result);
			return jsonResponse.JsonResponseText();
			
		
	}
		@RequestMapping(value = "/askaDoubt", method = RequestMethod.POST)
		public String takeAttendance(HttpSession sessionObj,HttpServletResponse response,
				@RequestParam("classEventDetailId") int clazEventDetailId,@RequestParam("askDoubtText") String doubtText,
				@RequestParam("sessionID") String sessionID,
				@RequestParam("userId") String userId){

			
			Hashtable<String,Object> result=new Hashtable<String,Object>();
			
			String savedSessionID = userService.geUserSession(userId);
	        String doubt="doubt";
			////////////////////////////////////////
		    log.debug("clazEventDetailId:"+clazEventDetailId);
			log.debug("askDoubtText:"+doubtText);
	
		
			if(userId!=null && sessionID.equals(savedSessionID))	
			{ 
			log.debug("Session Valid");
			try
			{
			List<String> name =playerService.teacherIdForClazEventDetailId(clazEventDetailId);
			String teacherName=name.get(0);
			if(teacherName!=null)
			{
				
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("E,dd-MM-YYYY,HH:mm");
				String CurrentDate = sdf.format(cal.getTime());
				
				Date RDate = null;
				try {
					RDate = sdf.parse(CurrentDate);
				} catch (ParseException e) {
			
					e.printStackTrace();
				}
				java.sql.Timestamp  ReminderTime  = new java.sql.Timestamp(RDate.getTime());
				  String decoded = new String(DatatypeConverter.parseBase64Binary(doubtText));
				  System.out.println("decoded value is \t" + decoded);
				int notificationId=playerService.saveAskDoubtsInDb(userId,teacherName,decoded,clazEventDetailId,ReminderTime,doubt);
	
				String profilePicStud=userService.getProfilePic(userId);
				WebSocketServiceImp wsObj=new WebSocketServiceImp();
				
				result.put("RaisehandOrRemindernote","doubt");
				result.put("NotificationId",notificationId);
				result.put("profilePic", profilePicStud);
				result.put("studentId", userId);
				result.put("doubt", decoded);
			
					JsonParser<String, Hashtable<String,Object>> wsJson = new JsonParser<String, Hashtable<String,Object>>("studentDoubt",1,userId+" asked a question",sessionID,"studentDoubtResult",result);
					String a= wsJson.JsonResponseText();	
					
					try {
						
						wsObj.writeTextmessage(name, a,false);
						
					} catch (IOException e) {
						e.printStackTrace();
					
					}
				
	
		    	result.clear();
				resultCode=1;
				message="save doubt successfully";
						
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
				message="Invalid RebbonId";
				resultCode=0;
			}
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("askaDoubt",resultCode,message,sessionID,"askaDoubtResult",result);
			return jsonResponse.JsonResponseText();
	
		}
		
	}
	
	
	
	
	
