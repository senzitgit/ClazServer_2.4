
package com.senzit.cyberclaz.server.controller;
	
	import java.text.ParseException;
	
	import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
	
	import java.util.Properties;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
	
	import com.senzit.cyberclaz.server.service.CognosService;
import com.senzit.cyberclaz.server.service.PortalService;
import com.senzit.cyberclaz.server.subservice.JsonParser;
	
	
	@RestController
	public class CognosController {
		
		static Logger log = Logger.getLogger(CognosController.class.getName());
		int resultCode=0;
		String message="";
		
		@Autowired
		private CognosService cognosService;
		@Autowired
		private PortalService portalService;
	
		
		public void setPortalService(PortalService portalService) {
			this.portalService = portalService;
		}
		public void setCognosService(CognosService cognosService) {
			this.cognosService = cognosService;
		}
		@RequestMapping(value ="/addAssignment", method = RequestMethod.POST)
		public String assignmentTask(HttpSession sessionObj,HttpServletResponse response,@RequestParam("subject") String subject,
				@RequestParam("topic") String topic,
				@RequestParam("sessionID") String sessionID){
			
			System.out.println("subjectId"+subject);
			System.out.println("topic"+topic);
			
			
		    Hashtable<String,Object> result=new Hashtable<String,Object>();
		
			String subId=cognosService.getSubjectNameFromSubjectId(subject);
			System.out.println("SubjectID"+subId);
			if(subId!=null)
			{
			Integer assignId=cognosService.getAssignmentIdFromSubject(subId,topic);
			System.out.println("assssssssssssssssssss"+assignId);
			if(assignId==0)
			{	
			try
			{
		    Integer value=cognosService.saveAssignAssignmentTasksInDb(subId,topic);
	        if(value!=0)
	        {
	        
	        	
				System.out.println("Assignment details saved");
				resultCode=1;
				message="success";
	        	
				
	        }
	        else
	        {
	        	System.out.println("Assignment details not saved");
				resultCode=0;
				message="failure";
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
				System.out.println("Already exist subject,topic");
				resultCode=0;
				message="Already exist topic";
			}
			}
			else
			{
				System.out.println("SubjectId null");
				resultCode=0;
				message="Invalid subjectName";
			}
		
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("addAssignment",resultCode,message,sessionID,"assignmentTaskResult",result);
			return jsonResponse.JsonResponseText();		
		
	}
		
		
		
		///////////////////////////////////////////////////////////////////////////////
		@RequestMapping(value ="/assignToAll", method = RequestMethod.POST)
		public String assignToAll(HttpSession sessionObj,HttpServletResponse response,@RequestParam("classId") String classRoomNo,
				@RequestParam("subject") String subject,@RequestParam("topic") String topic,
				@RequestParam("submitDate") String submitDate,
				@RequestParam("sessionID") String sessionID){

			
			String result="";
			
			cognosService.assignTaskToAll(classRoomNo, subject, topic);
			
			JsonParser<String,String> jsonResponse = new JsonParser<String, String>("assignToAll",1,"Success",sessionID,"result",result);
			return jsonResponse.JsonResponseText();	
			
		}
		@RequestMapping(value ="/assignToGroup", method = RequestMethod.POST)
		public String assignToGroup(HttpSession sessionObj,HttpServletResponse response,@RequestParam("subject") String subject,
				@RequestParam("submitDate") String submitDate,@RequestParam("assignJson") String assignJson,
				@RequestParam("sessionID") String sessionID){
			String result="";
			
			System.out.println("assignJson : "+assignJson);
			
			cognosService.assignTaskToGroup(subject, assignJson);
			
			JsonParser<String,String> jsonResponse = new JsonParser<String, String>("assignToGroup",1,"Success",sessionID,"result",result);
			return jsonResponse.JsonResponseText();	
			
		}
		
		//////////////////////////////////////////////////////////////////////////////
		
		
		
	
		@RequestMapping(value ="/assignAssignment", method = RequestMethod.POST)
		public String assignmentTask(HttpSession sessionObj,HttpServletResponse response,@RequestParam("userId") String userId,@RequestParam("subject") String subject,
				@RequestParam("topic") String topic,@RequestParam("submitDate") String submitDate,
				@RequestParam("sessionID") String sessionID){
			
			System.out.println("userId"+userId);
			System.out.println("subjectId"+subject);
			System.out.println("topic"+topic);
			
			
		    Hashtable<String,Object> result=new Hashtable<String,Object>();
		
			String subId=cognosService.getSubjectNameFromSubjectId(subject);
			System.out.println("SubjectID"+subId);
			if(subId!=null)
			{
			Integer taskId=cognosService.getTaskIdFromSubjectTopic(userId,subId,topic);
			if(taskId==0)
			{
			try
			{
			Integer value=cognosService.saveAssignmentTasksInDb(userId,subId,topic);
	        if(value!=0)
	        {
	        	
				System.out.println("Assignment details saved");
				resultCode=1;
				message="success";
	        }
	        else
	        {
	        	System.out.println("Assignment details not saved");
				resultCode=0;
				message="failure";
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
				System.out.println("Already exist entry");
				resultCode=0;
				message="Already exist entry";
			}
		    }
			else
			{
				System.out.println("SubjectId null");
				resultCode=0;
				message="Invalid subjectName";
			}
		
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("assignAssignment",resultCode,message,sessionID,"assignmentTaskResult",result);
			return jsonResponse.JsonResponseText();
			
		
	}
		@RequestMapping(value ="/getAssignmentTaskList", method = RequestMethod.POST)
		public String getAssignmentTaskList(HttpSession sessionObj,HttpServletResponse response,@RequestParam("subject") String subject,
				@RequestParam("sessionID") String sessionID){
			
		    Hashtable<String,Object> result=new Hashtable<String,Object>();
			try
			{
			List<Properties> getAssignmentTaskList=cognosService.getAssignedAssignmentTask(subject);
			if(!getAssignmentTaskList.isEmpty())
			{
			 result.put("assignmentList", getAssignmentTaskList);
		    resultCode=1;
		    message="success";
			}
	        else
	        {
	        	System.out.println("assignmentList empty");
				resultCode=0;
				message="assignmentList empty";
	        }
			}
			catch(Exception e)
			{
				e.printStackTrace();
				message = "Server Exception : "+e.getCause();
				resultCode = 2;
			}
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("getAssignmentTaskList",resultCode,message,sessionID,"assignmentListResult",result);
			return jsonResponse.JsonResponseText();
			
		
	}
		@RequestMapping(value ="/assignmentStatusUpdation", method = RequestMethod.POST)
		public String assignmentStatusUpdation(HttpSession sessionObj,HttpServletResponse response,@RequestParam("userId") String userId,@RequestParam("subject") String subject,@RequestParam("status") String status,
				@RequestParam("sessionID") String sessionID){
			
			response.setHeader("Access-Control-Allow-Origin", "http://192.168.0.210:10080");
			System.out.println("userId"+userId);
			System.out.println("status"+status);
			System.out.println("subject"+subject);
			
			
		    Hashtable<String,Object> result=new Hashtable<String,Object>();
		    String subId=cognosService.getSubjectNameFromSubjectId(subject);
			System.out.println("SubjectID"+subId);
			if(subId!=null)
			{
			try
			{
			boolean value=cognosService.updateAssignmentTaskStatus(userId,subId,status);
	//        if(value==true)
	//        {
				System.out.println("Assignment status saved");
				resultCode=1;
				message="success";
	//        }
	//        else
	//        {
	//        	System.out.println("Assignment status not saved");
	//			resultCode=0;
	//			message="failure";
	//        }
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
				System.out.println("SubjectId null");
				resultCode=0;
				message="Invalid subjectName";
			}
			
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("assignmentStatusUpdation",resultCode,message,sessionID,"assignmentStatusUpdationResult",result);
			return jsonResponse.JsonResponseText();
			
		
	}
		
		@RequestMapping(value ="/subjectList", method = RequestMethod.POST)
		public String subjectList(HttpSession sessionObj,HttpServletResponse response,
				@RequestParam("sessionID") String sessionID){
			
		    Hashtable<String,Object> result=new Hashtable<String,Object>();
			
			List<Properties> subjectDetails=portalService.getSubjectDetails();
			if(!subjectDetails.isEmpty())
			{
			 result.put("subjectsDetails", subjectDetails);
		    resultCode=1;
		    message="success";
			}
	        else
	        {
	        	System.out.println("subjectsDetails empty");
				resultCode=0;
				message="subjectsDetails empty";
	        }
		
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("subjectList",resultCode,message,sessionID,"subjectListResult",result);
			return jsonResponse.JsonResponseText();
			
		
	}
		@RequestMapping(value ="/getClassRoom", method = RequestMethod.POST)
		public String getClassRoom(HttpSession sessionObj,HttpServletResponse response,
				@RequestParam("sessionID") String sessionID){
			
		    Hashtable<String,Object> result=new Hashtable<String,Object>();
	
			 List<Properties> classRoomDetails=portalService.getClassRoomDetails();
			 if(!classRoomDetails.isEmpty())
			 {
			 result.put("classRoomDetails", classRoomDetails);
			 resultCode=1;
			 message="success";
			 }
	        else
	        {
	        	System.out.println("classRoomDetails empty");
				resultCode=0;
				message="classRoomDetails empty";
	        }
		
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("getClassRoom",resultCode,message,sessionID,"getClassRoomResult",result);
			return jsonResponse.JsonResponseText();
			
		
	}
		@RequestMapping(value ="/subjectPerformance", method = RequestMethod.POST)
		public String subjectPerformance(HttpSession sessionObj,HttpServletResponse response,@RequestParam("userId") String userId,@RequestParam("subject") String subject,
				@RequestParam("rating") String rating,
				@RequestParam("sessionID") String sessionID){
			
			
			System.out.println("userId"+userId);
			System.out.println("subjectId"+subject);
			System.out.println("rating"+rating);
			
		    Hashtable<String,Object> result=new Hashtable<String,Object>();
		
			String subId=cognosService.getSubjectNameFromSubjectId(subject);
			System.out.println("SubjectID"+subId);
			if(subId!=null)
			{
			try
			{
			boolean value=cognosService.saveSubjectPerformanceDetailsInDb(userId,subId,rating);
	        if(value==true)
	        {
				System.out.println("Subjectwise Performance of student saved");
				resultCode=1;
				message="success";
	        }
	        else
	        {
	        	System.out.println("Subjectwise Performance of student not saved");
				resultCode=0;
				message="failure";
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
				System.out.println("SubjectId null");
				resultCode=0;
				message="Invalid subjectName";
			}
		
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("subjectPerformance",resultCode,message,sessionID,"subjectPerformanceResult",result);
			return jsonResponse.JsonResponseText();
			
		
	}
		@RequestMapping(value ="/subjectPerformanceUpdation", method = RequestMethod.POST)
		public String subjectPerformanceUpdation(HttpSession sessionObj,HttpServletResponse response,@RequestParam("userId") String userId,
				@RequestParam("subjectId") String subjectId,@RequestParam("rating") String rating,
				@RequestParam("sessionID") String sessionID){
			
			System.out.println("userId"+userId);
			System.out.println("subjectId"+subjectId);
			System.out.println("rating"+rating);
			
			
		    Hashtable<String,Object> result=new Hashtable<String,Object>();
			int resultCode = 0;
			String message;
		
			boolean value=cognosService.subjectPerformanceDetailsUpdation(userId,subjectId,rating);
	        if(value==true)
	        {
				System.out.println("Subjectwise Performance rating updated");
				resultCode=1;
				message="success";
	        }
	        else
	        {
	        	System.out.println("Subjectwise Performance rating not updated");
				resultCode=0;
				message="failure";
	        }
		  
		
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("subjectPerformanceUpdation",resultCode,message,sessionID,"subjectPerformanceResult",result);
			return jsonResponse.JsonResponseText();
			
		
	}
		@RequestMapping(value ="/targetAttentancePercentage", method = RequestMethod.POST)
		public String targetAttentancePercentage(HttpSession sessionObj,HttpServletResponse response,@RequestParam("teacherId") String teacherId,@RequestParam("classRoomNo") String classRoomNo,
				@RequestParam("attendancePercentage") String attendancePercentage,
				@RequestParam("sessionID") String sessionID){
			
				System.out.println("teacherId"+teacherId);
				System.out.println("classRoomNo"+classRoomNo);
				System.out.println("attendancePercentage"+attendancePercentage);
				
				
			    Hashtable<String,Object> result=new Hashtable<String,Object>();
				
			    Integer cBatchId=cognosService.getCourseBatchFromClassRoom(classRoomNo);
			    if(cBatchId!=0)
			    {
			   try
			   {
				 cognosService.saveAttendancePassPercentageDetailsInDb(teacherId,cBatchId,attendancePercentage);
		         System.out.println("targetAttendancePercentage details saved");
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
			    	System.out.println("courseBatchId null");
					resultCode=0;
					message="failure";
			    }
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("targetAttentancePercentage",resultCode,message,sessionID,"targetAttendancePercentageResult",result);
			return jsonResponse.JsonResponseText();
			
		
	}
		@RequestMapping(value ="/targetPassPercentage", method = RequestMethod.POST)
		public String targetPassPercentage(HttpSession sessionObj,HttpServletResponse response,@RequestParam("teacherId") String teacherId,@RequestParam("classRoomNo") String classRoomNo,
				@RequestParam("passPercentage") String passPercentage,
				@RequestParam("sessionID") String sessionID){
			
			System.out.println("teacherId"+teacherId);
			System.out.println("classRoomNo"+classRoomNo);
			System.out.println("passPercentage"+passPercentage);
			
			
		    Hashtable<String,Object> result=new Hashtable<String,Object>();
			
		    Integer cBatchId=cognosService.getCourseBatchFromClassRoom(classRoomNo);
		    if(cBatchId!=0)
		    {
		   try
		   {
			  cognosService.savetargetPassPercentageDetailsInDb(teacherId,cBatchId,passPercentage);
	
				System.out.println("targetPassPercentage details saved");
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
		    	System.out.println("courseBatchId null");
				resultCode=0;
				message="failure";
		    }
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("targetPassPercentage",resultCode,sessionID,message,"targetPassPercentageResult",result);
			return jsonResponse.JsonResponseText();
			
		
	}
		@RequestMapping(value ="/progressReports", method = RequestMethod.POST)
		public String progressReports(HttpSession sessionObj,HttpServletResponse response,@RequestParam("userId") String userId,@RequestParam("term") String term,
				@RequestParam("subject") String subject,@RequestParam("mark") String mark,
				@RequestParam("sessionID") String sessionID){
			
			System.out.println("userId"+userId);
			System.out.println("subjectId"+subject);
			System.out.println("term"+term);
			System.out.println("mark"+mark);
			
		    Hashtable<String,Object> result=new Hashtable<String,Object>();
		
			String subId=cognosService.getSubjectNameFromSubjectId(subject);
			System.out.println("SubjectID"+subId);
			if(subId!=null)
			{
				try
				{
			    cognosService.saveprogressReportsInDb(userId,term,subId,mark);
	
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
				System.out.println("SubjectId null");
				resultCode=0;
				message="Invalid subjectName";
			}
		
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("progressReports",resultCode,message,sessionID,"progressReportsResult",result);
			return jsonResponse.JsonResponseText();
			
		
	}
		@RequestMapping(value ="/teacherRecommendation", method = RequestMethod.POST)
		public String teacherRecommendation(HttpSession sessionObj,HttpServletResponse response,@RequestParam("userId") String userId,@RequestParam("term") String term,
				@RequestParam("subject") String subject,@RequestParam("rating") String rating,
				@RequestParam("sessionID") String sessionID){
			
			
			System.out.println("userId"+userId);
			System.out.println("term"+term);
	        System.out.println("subjectId"+subject);
			System.out.println("rating"+rating);
	
			response.setHeader("Access-Control-Allow-Origin", "http://192.168.0.210:10080");
		    Hashtable<String,Object> result=new Hashtable<String,Object>();
		
			String subId=cognosService.getSubjectNameFromSubjectId(subject);
			System.out.println("SubjectID"+subId);
			if(subId!=null)
			{
				try
				{
			    cognosService.saveteacherRecommendationInDb(userId,subId,term,rating);
	
				System.out.println("Teacher reports about subject saved");
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
				System.out.println("SubjectId null");
				resultCode=0;
				message="Invalid subjectName";
			}
		
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("teacherRecommendation",resultCode,message,sessionID,"teacherRecommendationResult",result);
			return jsonResponse.JsonResponseText();
			
		
	}
	//	@RequestMapping(value ="/attendanceReports", method = RequestMethod.POST)
	//	public String attendanceReports(HttpSession sessionObj,@RequestParam("userId") String userId,@RequestParam("month") String month,
	//			@RequestParam("subject") String subject){
	//		
	//		
	//		System.out.println("userId"+userId);
	//		System.out.println("subjectId"+subject);
	//		System.out.println("month"+month);
	//		
	//		
	//	    Hashtable<String,Object> result=new Hashtable<String,Object>();
	//		
	//		String subId=cognosService.getSubjectNameFromSubjectId(subject);
	//		System.out.println("SubjectID"+subId);
	//		if(subId!=null)
	//		{
	//		
	//		
	//		  List<Properties> prop=cognosService.getAttendanceDetails(userId,month,subId);
	//		  if(!prop.isEmpty())
	//		  {
	//		    result.put("attendanceDetails", prop);
	//
	//			resultCode=1;
	//			message="success";
	//		  }
	//		  else
	//		  {
	//			result.put("attendanceDetails", "");  
	//		    resultCode=0;
	//			message="Attendance list is empty";
	//		  }
	//
	//	    }
	//		else
	//		{
	//			System.out.println("SubjectId null");
	//			resultCode=0;
	//			message="Invalid subjectName";
	//		}
	//	
	//
	//		JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("attendanceReports",resultCode,message,"attendanceReportsResult",result);
	//		return jsonResponse.JsonResponseText();
	//		
	//	
	//}
		@RequestMapping(value ="/getStudentNameFromClass", method = RequestMethod.POST)
		public String getStudentNameFromClass(HttpSession sessionObj,HttpServletResponse response,@RequestParam("classRoomNo") String classRoomNo,
				@RequestParam("sessionID") String sessionID){
			
			System.out.println("classRoomNo"+classRoomNo);
	
			
			
		    Hashtable<String,Object> result=new Hashtable<String,Object>();
		
			
			  List<Properties> prop=cognosService.getStudentNameFromClass(classRoomNo);
			  if(!prop.isEmpty())
			  {
			    result.put("studentList", prop);
	
				resultCode=1;
				message="success";
			  }
			  else
			  {
				result.put("studentList", "");  
			    resultCode=0;
				message="studentList is empty";
			  }
	
		   
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("getStudentNameFromClass",resultCode,message,sessionID,"studentNameListResult",result);
			return jsonResponse.JsonResponseText();
			
		
	}
		@RequestMapping(value ="/getTeacherList", method = RequestMethod.POST)
		public String getTeacherList(HttpSession sessionObj,HttpServletResponse response,
				@RequestParam("sessionID") String sessionID){
			
			
		    Hashtable<String,Object> result=new Hashtable<String,Object>();	
			List<Properties> prop=cognosService.getTeacherList();
			  if(!prop.isEmpty())
			  {
			    result.put("teacherList", prop);
	
				resultCode=1;
				message="success";
			  }
			  else
			  {
				result.put("teacherList", "");  
			    resultCode=0;
				message="teacherList is empty";
			  }
	
		   
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("getTeacherList",resultCode,message,sessionID,"teacherListResult",result);
			return jsonResponse.JsonResponseText();
			
		
	}
		@RequestMapping(value ="/futureGoals", method = RequestMethod.POST)
		public String progressReports(HttpSession sessionObj,HttpServletResponse response,@RequestParam("userId") String userId,@RequestParam("goal") String goal,
				@RequestParam("classRoomNo") String classRoomNo,
				@RequestParam("sessionID") String sessionID){
			
			response.setHeader("Access-Control-Allow-Origin", "http://192.168.0.210:10080");
			System.out.println("userId"+userId);
			System.out.println("classRoomNo"+classRoomNo);
			System.out.println("goal"+goal);
			
		    Hashtable<String,Object> result=new Hashtable<String,Object>();	
			if(goal!=null)
			{
			Integer cBatchId=cognosService.getCourseBatchFromClassRoom(classRoomNo);
			try
			{
			  cognosService.saveFutureGoalsInDb(userId,cBatchId,goal);
	
				System.out.println("futureGoals details saved");
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
				System.out.println("goal input empty");
				resultCode=0;
				message="goal input empty";
			}
		
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("futureGoals",resultCode,message,sessionID,"futureGoalsResult",result);
			return jsonResponse.JsonResponseText();
			
		
	}
		@RequestMapping(value = "/askaDoubtTest", method = RequestMethod.POST)
		public String takeAttendance(HttpSession sessionObj,HttpServletResponse response,
				@RequestParam("askDoubtText") String doubtText,
				@RequestParam("sessionID") String sessionID){

			Hashtable<String,Object> result=new Hashtable<String,Object>();
			String userId=(String)sessionObj.getAttribute("userId");
			String sessionId = (String) sessionObj.getAttribute("sessionId");
	        String doubt="doubt";
			////////////////////////////////////////
		 
			log.debug("askDoubtText:"+doubtText);
	
			  String decoded = new String(DatatypeConverter.parseBase64Binary(doubtText));
			  System.out.println("decoded value is \t" + decoded);
	          result.put("decorded value", decoded);
		        
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("askaDoubtTest",resultCode,message,sessionID,"askaDoubtResult",result);
			return jsonResponse.JsonResponseText();
	
		}
		
		
	}
