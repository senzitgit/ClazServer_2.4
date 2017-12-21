	package com.senzit.cyberclaz.server.controller;
	
	import java.util.Hashtable;
	
	import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
	
	import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
	
	import com.senzit.cyberclaz.server.service.PortalService;
import com.senzit.cyberclaz.server.service.RecordService;
import com.senzit.cyberclaz.server.subservice.JsonParser;
	
	@RestController
	public class PortalController {
	
		static Logger log = Logger.getLogger(PortalController.class.getName());
		  int resultCode=0;
		  String message="";
				
		@Autowired
		private PortalService portalService;
	
		
		public void setPortalService(PortalService portalService) {
			this.portalService = portalService;
		}
		
	
		@RequestMapping(value ="/courseDetails", method = RequestMethod.POST)
		public String courseDetailsViaPortal(HttpSession sessionObj,HttpServletResponse response,@RequestParam("courseName") String courseName,
				@RequestParam("courseDescription") String courseDesc,@RequestParam("courseCategory") String courseCategory,
				@RequestParam("courseDuration") String courseDuration,@RequestParam("currentScheme") String currentScheme,
				@RequestParam("semOrYear") String semOrYear,@RequestParam("department") String department,
				@RequestParam("distantOrRegular") String distantOrRegular,
				@RequestParam("sessionID") String sessionID){
			
			log.debug("courseName="+courseName);
			log.debug("courseDescription="+courseDesc);
			log.debug("courseCategory="+courseCategory);
			
			log.debug("courseDuration="+courseDuration);
			log.debug("currentScheme="+currentScheme);
			log.debug("semOrYear="+semOrYear);
			
			log.debug("department="+department);
			log.debug("distantOrRegular="+distantOrRegular);
	
			
		    Hashtable<String,Object> result=new Hashtable<String,Object>();
		  
			if(courseName!=null && courseDesc!=null && courseCategory!=null && courseDuration!=null && currentScheme!=null && semOrYear!=null && department!=null && distantOrRegular!=null)
			{
			boolean value=portalService.saveCourseDetailsInDb(courseName,courseDesc,courseCategory,courseDuration,currentScheme,semOrYear,department,distantOrRegular);
			if(value==true)
			{
	    
				System.out.println(" course details saved");
				
				resultCode=1;
				message="success";
			}
			else
			{
				System.out.println("error while saving");
				resultCode=0;
				message="failure";
			}
		
			}
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("courseDetails",resultCode,message,sessionID,"courseDetailsResult",result);
			return jsonResponse.JsonResponseText();
			
		
	}
		
		@RequestMapping(value ="/semDetails", method = RequestMethod.POST)
		public String semDetailsViaPortal(HttpSession sessionObj,HttpServletResponse response,@RequestParam("semName") String semName,
				@RequestParam("sessionID") String sessionID){
			
			
			log.debug("semName="+semName);
			 Hashtable<String,Object> result=new Hashtable<String,Object>();
			
			if(semName!=null)
			{
			
			boolean value=portalService.saveSemDetailsInDb(semName);
			if(value==true)
			{
	    
				System.out.println(" semester details saved");
				resultCode=1;
				message="success";
			}
			else
			{
				System.out.println("error");
				resultCode=0;
				message="failure";
			}
		
			}
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("semDetails",resultCode,message,sessionID,"semDetailsResult",result);
			return jsonResponse.JsonResponseText();
			
		
	}
		
		@RequestMapping(value ="/batchDetails", method = RequestMethod.POST)
		public String batchDetailsViaPortal(HttpSession sessionObj,HttpServletResponse response,@RequestParam("batchName") String batchName,
				@RequestParam("sessionID") String sessionID){
			
			
			log.debug("batchName="+batchName);
			 Hashtable<String,Object> result=new Hashtable<String,Object>();
			
			if(batchName!=null)
			{
			
			boolean value=portalService.saveBatchDetailsInDb(batchName);
			if(value==true)
			{
	    
				System.out.println(" Batch details saved");
				resultCode=1;
				message="success";
			}
			else
			{
				System.out.println("error");
				resultCode=0;
				message="failure";
			}
		
			}
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("batchDetails",resultCode,message,sessionID,"batchDetailsResult",result);
			return jsonResponse.JsonResponseText();
			
		}
		@RequestMapping(value ="/classRoomDetails", method = RequestMethod.POST)
		public String classRoomDetailsViaPortal(HttpSession sessionObj,HttpServletResponse response,@RequestParam("classRoonNo") String classRoonNo,
				@RequestParam("sessionID") String sessionID){
			
			log.debug("classRoonNo="+classRoonNo);
			 Hashtable<String,Object> result=new Hashtable<String,Object>();
			
			if(classRoonNo!=null)
			{
			
			boolean value=portalService.saveClassRoomDetailsInDb(classRoonNo);
			if(value==true)
			{
	    
				System.out.println(" classroom details saved");
				resultCode=1;
				message="success";
			}
			else
			{
				System.out.println("error");
				resultCode=0;
				message="failure";
			}
		
			}
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("classRoomDetails",resultCode,message,sessionID,"classRoomDetailsResult",result);
			return jsonResponse.JsonResponseText();
			
		}
		@RequestMapping(value ="/subjectDetails", method = RequestMethod.POST)
		public String subjectDetailsViaPortal(HttpSession sessionObj,HttpServletResponse response,@RequestParam("subjectName") String subjectName,
				@RequestParam("sessionID") String sessionID){
			
			log.debug("subjectName="+subjectName);
			 Hashtable<String,Object> result=new Hashtable<String,Object>();
			
			if(subjectName!=null)
			{
			
			boolean value=portalService.saveSubjectDetailsInDb(subjectName);
			if(value==true)
			{
	    
				System.out.println(" subject details saved");
				resultCode=1;
				message="success";
			}
			else
			{
				System.out.println("error");
				resultCode=0;
				message="failure";
			}
		
			}
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("subjectDetails",resultCode,message,sessionID,"subjectDetailsResult",result);
			return jsonResponse.JsonResponseText();
			
		}	
		@RequestMapping(value ="/dayDetails", method = RequestMethod.POST)
		public String dayDetailsViaPortal(HttpSession sessionObj,HttpServletResponse response,@RequestParam("dayName") String dayName,
				@RequestParam("sessionID") String sessionID){
			
			log.debug("dayName="+dayName);
			 Hashtable<String,Object> result=new Hashtable<String,Object>();
			
			if(dayName!=null)
			{
			
			boolean value=portalService.saveDayDetailsInDb(dayName);
			if(value==true)
			{
	    
				System.out.println(" day details saved");
				resultCode=1;
				message="success";
			}
			else
			{
				System.out.println("error");
				resultCode=0;
				message="failure";
			}
		
			}
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("dayDetails",resultCode,message,sessionID,"dayDetailsResult",result);
			return jsonResponse.JsonResponseText();
			
		}	
		@RequestMapping(value ="/periodDetails", method = RequestMethod.POST)
		public String periodDetailsViaPortal(HttpSession sessionObj,HttpServletResponse response,@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime,
				@RequestParam("sessionID") String sessionID){
			
			log.debug("startTime="+startTime);
			log.debug("endTime="+endTime);
			 Hashtable<String,Object> result=new Hashtable<String,Object>();
			
			if(startTime!=null && endTime!=null)
			{
			
			boolean value=portalService.savePeriodDetailsInDb(startTime,endTime);
			if(value==true)
			{
	    
				System.out.println(" period details saved");
				resultCode=1;
				message="success";
			}
			else
			{
				System.out.println("error");
				resultCode=0;
				message="failure";
			}
		
			}
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("periodDetails",resultCode,message,sessionID,"periodDetailsResult",result);
			return jsonResponse.JsonResponseText();
			
		}	
			@RequestMapping(value ="/courseSubjectDetails", method = RequestMethod.POST)
			public String courseSubjectDetailsDetailsViaPortal(HttpSession sessionObj,HttpServletResponse response,@RequestParam("courseName") String cName,@RequestParam("semName") String sName
					,@RequestParam("subjectName") String subName,
					@RequestParam("sessionID") String sessionID){
				
				log.debug("courseName="+cName);
				log.debug("semName="+sName);
				log.debug("subjectName="+subName);
				 Hashtable<String,Object> result=new Hashtable<String,Object>();
				
				if(cName!=null && sName!=null && subName!=null)
				{
				
				List<String> coursesemSubId=portalService.getCourseSemSubIdz(cName,sName,subName);
					if(!coursesemSubId.isEmpty())
					{
						String courseId=coursesemSubId.get(0);
						String semId=coursesemSubId.get(1);
						String subId=coursesemSubId.get(2);
					  boolean value=portalService.saveCourseSubjectDetailsInDb(courseId,semId,subId);
						if(value==true)
						{
				    
							System.out.println(" course subject details saved");
							resultCode=1;
							message="success";
						}
						
						else
						{
							System.out.println("error");
							resultCode=0;
							message="saving failure";
						}
						}
					else
					{
						System.out.println("No such a course,sem or subject");
						resultCode=0;
						message="No such a course,sem or subject";
					}
				  }
				else
				{
					System.out.println("null input from client");
					resultCode=0;
					message="null input from client";
				}
				
				JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("courseSubjectDetails",resultCode,message,sessionID,"courseSubjectDetailsResult",result);
				return jsonResponse.JsonResponseText();
				
			}	
			@RequestMapping(value ="/courseBatchDetails", method = RequestMethod.POST)
			public String courseBatchDetailsViaPortal(HttpSession sessionObj,HttpServletResponse response,@RequestParam("courseName") String cName,@RequestParam("semName") String sName
					,@RequestParam("batchName") String bName,@RequestParam("classRoomNo") String cRoomNo,
					@RequestParam("sessionID") String sessionID){
				
				log.debug("courseName="+cName);
				log.debug("semName="+sName);
				log.debug("batchName="+bName);
				log.debug("classRoomNo="+cRoomNo);
				
				 Hashtable<String,Object> result=new Hashtable<String,Object>();
				
				if(cName!=null && sName!=null && bName!=null && cRoomNo!=null)
				{
				
					List<String> courseSemBatchClassId=portalService.getCourseSemBatchandClassRoomIdz(cName,sName,bName,cRoomNo);
					if(!courseSemBatchClassId.isEmpty())
					{
						String courseId=courseSemBatchClassId.get(0);
						String semId=courseSemBatchClassId.get(1);
						String batchId=courseSemBatchClassId.get(2);
						String classRoomId=courseSemBatchClassId.get(3);
						
				    boolean value=portalService.saveCourseBatchDetailsInDb(courseId,semId,batchId,classRoomId);
				    if(value==true)
						{
				    
							System.out.println(" course batch details saved");
							resultCode=1;
							message="success";
						}
						else
						{
							System.out.println("error");
							resultCode=0;
							message="saving failure";
						}
				    
					}
					else
					{
						System.out.println("No such a course,sem,batch or classroom");
						resultCode=0;
						message="No such a course,sem,batch or classroom";
					}
				}
				else
				{
					System.out.println("null input from client");
					resultCode=0;
					message="null input from client";
				}
				
				JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("courseBatchDetails",resultCode,message,sessionID,"periodDetailsResult",result);
				return jsonResponse.JsonResponseText();
				
			}	
			@RequestMapping(value ="/studentBatchDetails", method = RequestMethod.POST)
			public String studentBatchDetailsDetailsViaPortal(HttpSession sessionObj,HttpServletResponse response,@RequestParam("courseName") String cName,@RequestParam("semName") String sName
					,@RequestParam("batchName") String bName,@RequestParam("classRoomNo") String cRoomNo,@RequestParam("userId") String studentId,
					@RequestParam("sessionID") String sessionID){
				
				log.debug("courseName="+cName);
				log.debug("semName="+sName);
				log.debug("batchName="+bName);
				log.debug("classRoomNo="+cRoomNo);
				
				 Hashtable<String,Object> result=new Hashtable<String,Object>();
				
				if(studentId!=null && cName!=null && sName!=null && bName!=null && cRoomNo!=null)
				{
					List<String> courseSemBatchClassId=portalService.getCourseSemBatchandClassRoomIdz(cName,sName,bName,cRoomNo);
					if(!courseSemBatchClassId.isEmpty())
					{
						String courseId=courseSemBatchClassId.get(0);
						String semId=courseSemBatchClassId.get(1);
						String batchId=courseSemBatchClassId.get(2);
						String classRoomId=courseSemBatchClassId.get(3);
						int courseBatchId=portalService.getCourseBatchId(courseId,semId,batchId,classRoomId);
					
				
				     boolean value=portalService.saveStudentBatchDetailsInDb(studentId,courseBatchId);
				     if(value==true)
						{
				    
							System.out.println(" student batch details saved");
							resultCode=1;
							message="success";
						}
						else
						{
							System.out.println("error");
							resultCode=0;
							message="saving failure";
						}
					}
					else
					{
						System.out.println("No such a course,sem,batch or classroom");
						resultCode=0;
						message="No such a course,sem,batch or classroom";
					
					}
				}
				else
				{
				System.out.println("null input from client");
				resultCode=0;
				message="null input from client";
				}
		
			
				
				JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("studentBatchDetails",resultCode,message,sessionID,"studentBatchDetailsResult",result);
				return jsonResponse.JsonResponseText();
				
			}	
		@RequestMapping(value ="/subjectTeacherDetails", method = RequestMethod.POST)
		public String studentTeacherDetailsDetailsViaPortal(HttpSession sessionObj,HttpServletResponse response,@RequestParam("userId") String teacherId,@RequestParam("courseName") String cName,@RequestParam("semName") String sName
				,@RequestParam("batchName") String bName,@RequestParam("subjectName") String subName,
				@RequestParam("sessionID") String sessionID){
			
			log.debug("teacherId="+teacherId);
			log.debug("courseName="+cName);
			log.debug("semName="+sName);
			log.debug("batchName="+bName);
			log.debug("subjectName="+subName);
			
			 Hashtable<String,Object> result=new Hashtable<String,Object>();
			
			if(teacherId!=null && cName!=null && sName!=null && bName!=null && subName!=null)
			{
				List<String> coursesemSubId=portalService.getCourseSemSubIdz(cName,sName,subName);
				if(!coursesemSubId.isEmpty())
				{
					String courseId=coursesemSubId.get(0);
					String semId=coursesemSubId.get(1);
					String subId=coursesemSubId.get(2);
					String batchId=portalService.getbatchIdFromBatchName(bName);
					log.debug("batchId="+batchId);
					int courseSubjectId=portalService.getCourseSubjecIdForInputs(courseId,semId,subId);
					log.debug("courseSubjectId="+courseSubjectId);
					
			      boolean value=portalService.saveSubjectTeacherDetailsInDb(teacherId,courseSubjectId,batchId);
					if(value==true)
					{
			    
						System.out.println(" course batch details saved");
						resultCode=1;
						message="success";
					}
					else
					{
						System.out.println("error");
						resultCode=0;
						message="failure";
					}
		       }
				else
				{
					System.out.println("No such a course,sem,batch or classroom");
					resultCode=0;
					message="No such a course,sem,batch,subject or teacherId";
				
				}
			}
			else
			{
			System.out.println("null input from client");
			resultCode=0;
			message="null input from client";
			}
	
				
			
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("subjectTeacherDetails",resultCode,message,sessionID,"studentBatchDetailsResult",result);
			return jsonResponse.JsonResponseText();
			
		}	
		 
	}
	
