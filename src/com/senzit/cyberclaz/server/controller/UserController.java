	package com.senzit.cyberclaz.server.controller;
	
	import java.io.FileInputStream;
	
	
	
	import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
	
	import javax.mail.SendFailedException;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;
	
	import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
	
	import com.senzit.cyberclaz.server.model.AttendanceData;
import com.senzit.cyberclaz.server.model.ScheduleData;
import com.senzit.cyberclaz.server.service.RecordService;
import com.senzit.cyberclaz.server.service.SchedulerService;
import com.senzit.cyberclaz.server.service.UserServices;
import com.senzit.cyberclaz.server.service.WebSocketServiceImp;
import com.senzit.cyberclaz.server.subservice.CyberService;
import com.senzit.cyberclaz.server.subservice.JsonParser;
import com.senzit.cyberclaz.server.subservice.RebbonService;
	
	@RestController
	public class UserController {
	
		static Logger log = Logger.getLogger(UserController.class.getName());
		int resultCode=0;
		String message="";
	
	
		@Autowired
		private UserServices userService;
		@Autowired
		private RecordService recordService;
		@Autowired
		private SchedulerService schedulerService;
		
	
		UserController(){}
	
		public void setUserService(UserServices userService) {
			this.userService = userService;
		}
	
		public void setRecordService(RecordService recordService) {
			this.recordService = recordService;
		}
	
		public void setSchedulerService(SchedulerService schedulerService) {
			this.schedulerService = schedulerService;
		}
	
	  
		
		
		
	/*	
		
		@RequestMapping(value = "/registration", method = RequestMethod.POST)
		public String registrationThirdStep(HttpSession sessionObj,
				@RequestParam("userId") String userId,
				@RequestParam("firstName") String firstName,
				@RequestParam("middleName") String middleName,
				@RequestParam("lastName") String lastName,
				@RequestParam("dob") String dob,
				@RequestParam("address") String address,
				@RequestParam("mobileNumber") String mobileNo,
				@RequestParam("emailId") String emailId){
			
			Hashtable<String,Object> result=new Hashtable<String,Object>();
			
			
			System.out.println("userId"+userId);
			log.debug("firstName"+firstName);
			log.debug("middleName"+middleName);
			log.debug("lastName"+lastName);
			log.debug("dob"+dob);
			log.debug("address"+address);
			log.debug("mobileno"+mobileNo);
			log.debug("emailId"+emailId);
			
		
			
			
	         boolean regValue=userService.saveDetailsInDB(userId,firstName,emailId,mobileNumber,registered,"http://81.95.159.34:8080/ProfilePic/a10.png");

	         boolean resultNew=userService.savePasswordForUser(userId,password);		
			
	         boolean setCourseBatchForStudent=userService.saveCourseBatchDetailsinDb(userId,101);	
			
			
			
			
			
			
			
			String approvalFlag="approved";
			
            try
            {
			  userService.updateRegistrationDetailsinDb(userId,firstName,middleName,lastName,dob,emailId,mobileNo,address,approvalFlag);
			  resultCode=1;
			  message="SUCCESS";
				 
			}
		   catch(Exception e)
		   {
			   e.printStackTrace();
			   message = "Server Exception : "+e.getCause();
			   resultCode = 2;   
		   }
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("registrationThirdStep",resultCode,message,"sessionID","RegistrationDetailsResult",result);
			return jsonResponse.JsonResponseText();
		}
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		*/
		
		
		
		
		
		
		
		
		
		    @RequestMapping(value = "/registration", method = RequestMethod.POST)
			public String login(HttpSession sessionObj,@RequestParam("userId") String userId,
					@RequestParam("firstName") String firstName,
					@RequestParam("middleName") String middleName,
					@RequestParam("lastName") String lastName,
					@RequestParam("emailId") String emailId,
					@RequestParam("password") String password,
		            @RequestParam("studentBatch") int studentBatch,
		            @RequestParam("dob") String dob,
		            @RequestParam("address") String address,
		            @RequestParam("mobileNumber") String mobileNumber,
		            @RequestParam("userRole") String userRoleName)
			{
		
				Hashtable<String,Object> result=new Hashtable<String,Object>();
				
				String sessionID = "";
			    log.debug(userId);
				log.debug(firstName);
				log.debug(emailId);
		        log.debug(password);
				log.debug(mobileNumber);
				log.debug(userRoleName);
		
				String registered="approved";
				String userPassword=userService.getUserPassword(userId);
				log.debug(userPassword);
	
				int LogValue=userService.authenticateUser(userId, password);
				
				if(LogValue==0)
				{
				boolean regValue=userService.saveDetailsInDB(userId,firstName,lastName,middleName,emailId,mobileNumber,registered,dob,address);

					
			    boolean resultNew=userService.savePasswordForUser(userId,password);
			    
				if(resultNew==true)
					{
					   try
					    {
						String userroleId=userService.getRoleId(userRoleName);
						
						boolean resultOfUserRole=userService.saveUserRoleForUser(userId,userroleId);
					
						  if(resultOfUserRole==true && userRoleName.equals("Student")) 
						    {
							  boolean setCourseBatchForStudent=userService.saveCourseBatchDetailsinDb(userId,studentBatch);
							   
					    	}
							 else if(resultOfUserRole==true && userRoleName.equals("Teacher"))
						   {
							   boolean setCourseSubjectForTeacher=userService.saveCourseSubjectDetailsinDb(userId,8,"101");
	
							   
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
							message="Registration failure";
							resultCode = 0;
						}
	
				
				}
				else
				{
						message="User already exist!!";
						resultCode = 0;
				}
	
				JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("signupInPortal",resultCode,message,sessionID,"signupResult",result);
				return jsonResponse.JsonResponseText();
		
			}
			
	
		
		@RequestMapping(value = "/viewProfile", method = RequestMethod.POST)
			public String userProfileView(HttpSession sessionObj,@RequestParam("sessionID") String sessionID,@RequestParam("userId") String userId){
				
			String savedSessionID = userService.geUserSession(userId);
			Hashtable<String,Object> result=new Hashtable<String,Object>();
	        String sessionId = sessionID;
			
				
				 log.debug("Session Valid");
				 try
				 {
				 List<Properties> userDetails=userService.getUserProfileDetails(userId);
				 result.put("userProfileDetails", userDetails);
				 resultCode=1;
				 message="SUCCESS";
				 }
				 catch(Exception e)
				  {
					   e.printStackTrace();
					   message = "Server Exception : "+e.getCause();
					   resultCode = 2;   
				  }
				
			
				JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("viewProfile",resultCode,message,sessionID,"tokenForRegistrationResult",result);
				return jsonResponse.JsonResponseText();
			
			}
		
		
		
		
		
		@RequestMapping(value = "/updateRegistrationDetails", method = RequestMethod.POST)
		public String registrationFinalStep(HttpSession sessionObj,
				@RequestParam("firstName") String firstName,@RequestParam("middleName") String middleName,@RequestParam("lastName") String lastName,
				@RequestParam("dob") String dob,@RequestParam("address") String address,@RequestParam("mobileNumber") String mobileNo,@RequestParam("emailId") String emailId,@RequestParam("sessionID") String sessionID,@RequestParam("userId") String userId){
			
			
			String savedSessionID = userService.geUserSession(userId);
			Hashtable<String,Object> result=new Hashtable<String,Object>();
	      
			log.debug("firstName"+firstName);
			log.debug("middleName"+middleName);
			log.debug("lastName"+lastName);
			log.debug("dob"+dob);
			log.debug("address"+address);
			log.debug("mobileno"+mobileNo);
			log.debug("emailId"+emailId);
			
			
			String approvalFlag="approved";


			
			
			if(userId!=null && sessionID.equals(savedSessionID))
			{ 
			
			
			log.debug("Session Valid");
            try
            {
			 userService.updateRegistrationDetailsinDb(userId,firstName,middleName,lastName,dob,emailId,mobileNo,address,approvalFlag);
			 resultCode=1;
			 message="SUCCESS";
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
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("updateRegistrationDetails",resultCode,message,sessionID,"updateRegistrationDetailsResult",result);
			return jsonResponse.JsonResponseText();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
			
				@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
				public String registrationSecondStep(HttpSession sessionObj,
						@RequestParam("currentPassword") String currentPassword,@RequestParam("newPassword") String newPassword,@RequestParam("sessionID") String sessionID,@RequestParam("userId") String userId){
					
					Hashtable<String,Object> result=new Hashtable<String,Object>();
					String savedSessionID = userService.geUserSession(userId);
					
					if(userId!=null && sessionID.equals(savedSessionID))
					{ 
					
					log.debug("Session Valid");
					String userCurrentPassword=userService.getCurrentPasswordOfUser(userId);
				    if(currentPassword.equals(userCurrentPassword))
				    {
						boolean value=userService.isSamePassword(userId, newPassword);
						if(value!=true)
						{
							try
							{
							int updateResult=userService.updatePassword(userId, newPassword);
							if(updateResult!=0)
							{
								resultCode=1;
								message="Success";
							}
							else
							{
								resultCode=0;
								message="password updation failure";
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
		
							resultCode=0;
							message="Your new password is same as current password...";
						}
					
					 }
				     else
					 {
					 log.debug("please enter valid Password");
					 message="please enter valid Password";
					 resultCode=0;
				 }
				 }
					else
				 {
				 log.debug("Invalid Session");
				 message="Invalid Session";
			     resultCode=0;
			    }
					
			
		         JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("changePassword",resultCode,message,sessionID,"changePasswordResult",result);
					return jsonResponse.JsonResponseText();
				}
		
		
		
		
				/*
		
		
		
		
		
		
		
		@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
		public String forgotPassword(HttpSession sessionObj,
				@RequestParam("userId") String userId,@RequestParam("eMail") String emailId){
			
			Byte resultCode=0;
			String message="";
			Properties result=new Properties();
			
			///////////////////////
			log.debug("forgotPassword");
			log.debug("userId:"+userId);
			log.debug("Emailid:"+emailId);
			//////////////////////
			try {
				if(userService.validateEmail(userId, emailId)){
					
					int token=userService.generateRandomCode();
					if(token!=0)
					{
					log.debug(token);
					///////////////
				    byte smsOrEmail=1;	
					//userService.sendToken(userId, token, smsOrEmail);					
					sessionObj.setAttribute("token", token);
					sessionObj.setAttribute("forgotUserName", userId);
						resultCode=1;
						message="Success";
					}
					else
					{
						resultCode=0;
						message="Invalid token ";
					}
				}
				else
					resultCode=0;
					message="Invalid userId or emailid";
			} catch (Exception e) {
				
				e.printStackTrace();
				message="Server Exception: "+e.getCause();
				resultCode=2;
			}
			
			JsonParser<String, Properties> jsonResponse = new JsonParser<String, Properties>("forgotPassword",resultCode,message,"ForgotPasswordResult",result);
			return jsonResponse.JsonResponseText();
		}
	
		@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
		public String resetPassword(HttpSession sessionObj,
				@RequestParam("token") int token,
				@RequestParam("newPassword") String newPassword){
			
			Byte resultCode=0;
			String message="";
			String result="";
			
			String userIdForUser=(String)sessionObj.getAttribute("forgotUserName");
			Integer validToken=(Integer)sessionObj.getAttribute("token");
			///////////////////////
			log.debug("resetPassword");
			log.debug("Username:"+userIdForUser);
			log.debug("Token:"+token);
			log.debug("Validtoken"+validToken);
			log.debug(newPassword);
			//////////////////////
			if(userIdForUser==null || validToken==null)
				message="Invalid session";
			else if(token==validToken){
				
				try {
					if(userService.isSamePassword(userIdForUser, newPassword))
						message="Try another password";
					else if(userService.updatePassword(userIdForUser, newPassword)>0){
						
						resultCode=1;
						message="Success";
						sessionObj.invalidate();
					}
					else
						message="Server error";
				} catch (Exception e) {
					
					e.printStackTrace();
					message="Server Exception: "+e.getCause();
					resultCode=2;
				}
			}
			else
				message="Wrong token";
			
			JsonParser<String, String> jsonResponse = new JsonParser<String, String>("resetPassword",resultCode,message,"ForgotPasswordResult",result);
			return jsonResponse.JsonResponseText();
		}
	
	
	*/
	
		@RequestMapping(value = "/localUser", method = RequestMethod.POST)
		public String setLocalStatus(HttpSession sessionObj,
				@RequestParam("rebbonId") String rebbonId,		@RequestParam("sessionID") String sessionID,
				@RequestParam("userId") String userId)
		{
			
		
			
			System.err.println("InLocalUSer");
			System.err.println(rebbonId);
			System.err.println(userId);
			System.err.println(sessionID);
			
			
			
			
			
			String savedSessionID = userService.geUserSession(userId);
			Hashtable<String,Object> result=new Hashtable<String,Object>();
	        String sessionId = sessionID;
			sessionObj.setAttribute("rebbonId",rebbonId);
	
			if(userId!=null && sessionID.equals(savedSessionID))
			{ 
				log.debug("Session Valid");
				//boolean userStatus=userService.getUserLoginStatus(userId,rebbonId);
			
				List<Properties> subjects = userService.getSubjectList(rebbonId);
					
				
				System.err.println(sessionObj.getAttribute("role"));
							
					if(sessionObj.getAttribute("role").equals("Teacher"))		
					{
						try
						{
						boolean rebbonTeacher=userService.getRebbonUser(rebbonId);
						if(rebbonTeacher==true)
						{
						
						userService.setUserStatus(userId,sessionObj.getId(),"Local",rebbonId);
						String rebbonLink = recordService.getRebbonLink(rebbonId);
						sessionObj.setAttribute("rebbonLink",rebbonLink);
						log.debug("rebbonLink"+rebbonLink);
						
						if(userService.isRebbonIdExist(rebbonId))
						{
							message="Rebbon already in use ";
							resultCode=0;
						}
						else
						{
							 
							log.debug("RebbonLink:"+rebbonLink);
							if(rebbonLink!=null)
							{
		//						if(RebbonService.startRebbon(rebbonLink))
		//						{
		
								//	log.debug("Rebbon Started");
									List<Properties> TrSchedule = schedulerService.getFullSchedule(userId);
									List<Integer>TrScheduleIdList = schedulerService.getScheduleIdOnly(userId);
									ScheduleData schedule = new ScheduleData();
									schedule.putNewData(userId, TrScheduleIdList);
									System.out.println("ScheduleListin localuser"+TrScheduleIdList);
									
									result.clear();
																
									result.put("schedule", TrSchedule);
									result.put("scheduleIdList", TrScheduleIdList);
									
									result.put("subjectList", subjects);
									message="Success";
									resultCode=1;
		
								//}
							}
							else
							{
								message="Invalid RebbonId";
								resultCode=0;
		
							}
						}
					}
					}
						catch (Exception e) {
							
							e.printStackTrace();
							message = "Server Exception : "+e.getCause();
							resultCode = 2;
					}
				}
				else if(sessionObj.getAttribute("role").equals("Student"))
				{
					try
					{
					userService.setUserStatus(userId,sessionObj.getId(),"Local",rebbonId);
					String rebbonLink = recordService.getRebbonLink(rebbonId);
					sessionObj.setAttribute("rebbonLink",rebbonLink);
					log.debug("rebbonLink"+rebbonLink);
					
					if(rebbonId.equals(recordService.getRebbonIdFromUser(userId))){
						
						AttendanceData obj=new AttendanceData();
						Integer batchId=Integer.parseInt((String) sessionObj.getAttribute("CourseBatchId"));
						List<Properties> StSchedule = schedulerService.getFullTimeTable(batchId);
						List<Integer>StScheduleIdList = schedulerService.getStudentScheduleIdOnly(batchId);
	//
	//					ScheduleData schedule = new ScheduleData();
	//					schedule.putNewData(userId, StScheduleIdList);
						ScheduleData schedule = new ScheduleData();
						schedule.putNewDataStud(batchId, StScheduleIdList);
						if(obj.checkData(batchId))
						{
							String studentName=userService.getUserName(userId);
							List<String> TeacherList = recordService.getTeacherInClass(rebbonId);
							
							WebSocketServiceImp wsObj=new WebSocketServiceImp();
							result.put("userId", userId);
							result.put("status", "local");
							
							JsonParser<String, Hashtable<String,Object>> wsJson = new JsonParser<String, Hashtable<String,Object>>("addToAttendance",1,"Please add "+studentName,message,"result",result);
							String a=wsJson.JsonResponseText();
							
							
							
							
							
							
							
							
							
							
							
							
							try {
								wsObj.writeTextmessage(TeacherList, a,false);
							} catch (IOException e) {
								e.printStackTrace();
							}
	
							result.clear();
	
						}
						
						result.put("studentTimeTable", StSchedule);		
						result.put("scheduleIdList", StScheduleIdList);
	
						result.put("subjectList", subjects);
	
						message="Success";
						resultCode=1;
				
			    }
				}
					catch (Exception e) {
						
						e.printStackTrace();
						message = "Server Exception : "+e.getCause();
						resultCode = 2;
					}
				}
					else if(sessionObj.getAttribute("role").equals("Manager"))
					{
						try
						{
						userService.setUserStatus(userId,sessionObj.getId(),"Local",rebbonId);
						ArrayList sujects11 = new ArrayList();
					
							result.put("subjectList",sujects11);
	
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
						
						resultCode=0;
						message="failure";
					}
				
						
				}
			
	
	
			else
			{
				message="Invalid session";
				resultCode=0;
	
			}
	//		}
	//		else
	//			{
	//				resultCode = 0;
	//				message="User already login,please exit and login again";
	//				userService.deleteUserLogData(userId);
	//
	//			}
	
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("localUser",resultCode,message,sessionID,"statusResult",result);
			return jsonResponse.JsonResponseText();
	
		}
		
		
		
		
		
		
		
		
	
		@RequestMapping(value = "/login", method = RequestMethod.POST)
		public String login(HttpSession sessionObj,//ServletContext context,
				@RequestParam("userId") String userId,@RequestParam("password") String password) throws UnsupportedEncodingException
				//@RequestParam("userId") byte[] userId,@RequestParam("password") String password) throws UnsupportedEncodingException
		{
	
			Hashtable<String,Object> result=new Hashtable<String,Object>();
			List<Properties> nullList = new ArrayList<Properties>();
			String Role ="";
			///////////////////////////
			log.debug(userId);
			log.debug(password);
			//////////////////////////
			
			
			
			String sessionID = sessionObj.getId();
			
			
			
			
			
			
			//String userId1;
			//byte[] bytes = userId1.getBytes("UTF-8");
			//String userId2 = new String(userId, "UTF-8");
			
			
			
			/////
	//        boolean logStatus=userService.getUserLogStatus(userId);
	//		if(logStatus==true)
	//		{
	//		 String decodedUserId = new String(DatatypeConverter.parseBase64Binary(userId));
	//		 String decodedPassword = new String(DatatypeConverter.parseBase64Binary(password));
	//		 log.debug("decodedUserId"+decodedUserId);
	//		 log.debug("decodedPassword"+decodedPassword);
		
			//userService.insertUserDetails(userId,password);
			int LogValue=userService.authenticateUser(userId,password);
			
			
			
			System.out.println("User Existing???"+LogValue);
			
			
			
			
			
			
			
			//int LogValue1=userService.authenticateUser(userId,password );
	//		String dummyUser=userService.getNameForDummy();
	//		System.out.println("dummyUser"+dummyUser);
			
			//String regFlag=userService.getRegistrationStatus(decodedUserId);
			if(LogValue!=0)
			{
				userService.setUserStatus(userId,sessionID,"Local","r101");
				
				log.debug("User Authenticated and admin approved the registration");
				String myIpAddress=CyberService.getIpAddress();
				String serverPort=CyberService.getServerPort();
				result.put("serverIpAddress", myIpAddress);
				result.put("serverPort", serverPort);
				if(userService.isCorrectUser(userId,"Teacher"))
				{
					try
					{
					List<Object[]> rolePrivilege = userService.getUserPrivilege(userId);
					List<Properties> subjectList=userService.getSubjectDetailsofUser(userId);
	
						for(int i=0;i<rolePrivilege.size();i++)
						{
							Object[] ob =rolePrivilege.get(i);
							Role = ob[0].toString();
							result.put("privilege"+i,ob[1].toString());
	
						}
	
						sessionObj.setAttribute("userId",userId);
						sessionObj.setAttribute("role",Role);
						sessionObj.setAttribute("sessionId",sessionObj.getId());
						log.debug(sessionObj.getId());
						
					
						
						List<Properties> teacherProfileDetails = userService.teacherProfileDetails(userId);
						result.put("studentProfileDetails", teacherProfileDetails);
						
						result.put("role", Role);
						result.put("SubjectList", subjectList);
						
						result.putAll(userService.getTeacherLoginResult(userId));
	
						message="Success";
						resultCode=1;
					}
					catch (Exception e) {
						
						e.printStackTrace();
						message = "Server Exception : "+e.getCause();
						resultCode = 2;
					}
					}
					else if(userService.isCorrectUser(userId,"Student"))
	
					{
						try
						{
						List<Object[]> rolePrivilegeStudent = userService.getUserPrivilege(userId);
	//
	//					if(!(rolePrivilegeStudent.isEmpty()))
	//					{
						String rebbonLink=CyberService.getglobalIpofRebbon();
	 				    System.out.println("REBBONLINK"+rebbonLink);
	 				  String rebbonPort= CyberService.getServerPortRebbon();
							for(int i=0;i<rolePrivilegeStudent.size();i++)
							{
								Object[] ob =rolePrivilegeStudent.get(i);
								Role = ob[0].toString();
								result.put("privilege"+i,ob[1].toString());
								//////////////////////////////////////////////
								log.debug("Privilege"+i+"="+ob[1].toString());
								//////////////////////////////////////////////
	
							}
							log.debug("UserRole="+Role);
							
							sessionObj.setAttribute("role",Role);
							List<String> studDetails = userService.getStudentBatchId(userId);
	
	
							for(int i=0;i<studDetails.size();i++)
							{
								log.debug(studDetails.get(i));
							}
							///////////////////////////////////////
							int courseBatchId =Integer.parseInt(studDetails.get(4));
	                        System.out.println("courseBatchId"+courseBatchId);
							Properties CurrentliveDetails = recordService.checkForLiveSession(courseBatchId);
							if(!(CurrentliveDetails.isEmpty())) 
								
							{
								result.put("liveSessionFlag", 1);
								result.put("liveSessionDetails",CurrentliveDetails);
								
							  
							}
							else
							{
								  result.put("liveSessionFlag", 0);
								  result.put("liveSessionDetails",nullList);
								  
							}
							
							List<Properties> studentProfileDetails = userService.getStudentProfileDetails(userId);
							List<Properties> attachmentListStudent=recordService.getAttachmentDetailsUploadedViaPortal(userId);
							
						    result.put("studentProfileDetails",studentProfileDetails);
						    result.put("portalAttachmentList",attachmentListStudent);
							log.debug("studentProfileDetails"+studentProfileDetails);
							
							sessionObj.setAttribute("CourseBatchId", studDetails.get(4));
							sessionObj.setAttribute("Course", studDetails.get(0));
							sessionObj.setAttribute("Batch", studDetails.get(1));
							sessionObj.setAttribute("Semester", studDetails.get(2));
							sessionObj.setAttribute("ClassRoom", studDetails.get(3));
							sessionObj.setAttribute("userId",userId);
							sessionObj.setAttribute("role",Role);
							sessionObj.setAttribute("sessionId",sessionObj.getId());
						
		               /////////////////////////////////////////////////////
	
							result.put("role", Role);
							result.put("rebbonLink", rebbonLink);
							result.put("rebbonPort", rebbonPort);
				
							result.putAll(userService.getLoginResult(userId));
						
							message="Success";
							resultCode=1;
						}
						catch (Exception e) {
							
							e.printStackTrace();
							message = "Server Exception : "+e.getCause();
							resultCode = 2;
						}
						
				}
					else if(userService.isCorrectUser(userId,"Manager"))
	
					{
						try
						{
						List<Object[]> rolePrivilegeManager = userService.getUserPrivilege(userId);
	//
	//					if(!(rolePrivilegeStudent.isEmpty()))
	//					{
						String rebbonLink=CyberService.getglobalIpofRebbon();
	 				    System.out.println("REBBONLINK"+rebbonLink);
	 				  String rebbonPort= CyberService.getServerPortRebbon();
							for(int i=0;i<rolePrivilegeManager.size();i++)
							{
								Object[] ob =rolePrivilegeManager.get(i);
								Role = ob[0].toString();
								result.put("privilege"+i,ob[1].toString());
								//////////////////////////////////////////////
								log.debug("Privilege"+i+"="+ob[1].toString());
								//////////////////////////////////////////////
	
							}
							log.debug("UserRole="+Role);
	//						List<String> studDetails = userService.getStudentBatchId("manager");
	//
	//
	//						for(int i=0;i<studDetails.size();i++)
	//						{
	//							log.debug(studDetails.get(i));
	//						}
	//						///////////////////////////////////////
							
							List<Properties> managerProfileDetails = userService.getManagerProfileDetails(userId);
						    result.put("managerProfileDetails",managerProfileDetails);
						    result.putAll(userService.getTeacherLoginResult(userId));
						//	log.debug("managerProfileDetails"+managerProfileDetails);
							Properties CurrentliveDetails = recordService.checkForLiveSession(101);
							if(!(CurrentliveDetails.isEmpty())) 
								
							{
								result.put("liveSessionFlag", 1);
								result.put("liveSessionDetails",CurrentliveDetails);
								
							  
							}
							else
							{
								  result.put("liveSessionFlag", 0);
								  result.put("liveSessionDetails",nullList);
								  
							}
							
							sessionObj.setAttribute("userId",userId);
							sessionObj.setAttribute("role",Role);
							sessionObj.setAttribute("sessionId",sessionObj.getId());
							
	
		                  /////////////////////////////////////////////////////
	
							result.put("role", Role);
							result.put("rebbonLink", rebbonLink);
							result.put("rebbonPort", rebbonPort);
	
							
							
							result.putAll(userService.getLoginResult(userId));
							
							//////////////////////////////////////////
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
					message="You can't login";
					resultCode = 0;
				}
		
			}
			else
			{
				resultCode = 0;
				message="Invalid Username or Password...";
			}
			
			
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("login",resultCode,message,sessionID,"loginResult",result);
			return jsonResponse.JsonResponseText();
		
		}
	
	
		@RequestMapping(value = "/remoteUser", method = RequestMethod.POST)
		public String setRemoteStatus(HttpSession sessionObj,HttpServletResponse response,
			@RequestParam("sessionID") String sessionID,
			@RequestParam("userId") String userId){
			
			
			Hashtable<String,Object> result=new Hashtable<String,Object>();

			
			String savedSessionID = userService.geUserSession(userId);
			
			String rebbonId =null;
		
			
			log.debug("UserId="+userId);
			log.debug("RebbonId"+rebbonId);
			//////////////////////
	
			if(userId!=null && sessionID.equals(savedSessionID))
			{ 
				log.debug("Session Valid");
				try
				{
			
				if(sessionObj.getAttribute("role").equals("Student"))
				{
					AttendanceData obj=new AttendanceData();
					int batchId=Integer.parseInt((String)sessionObj.getAttribute("CourseBatchId"));
						
				    	List<Properties> subjects = userService.getSubjectListForRemaoteUser(batchId);
						List<Properties> StSchedule = schedulerService.getFullTimeTable(batchId);
						List<Integer>StScheduleIdList = schedulerService.getStudentScheduleIdOnly(batchId);
	
						ScheduleData schedule = new ScheduleData();
	     				schedule.putNewData(userId, StScheduleIdList);
	     				
	     				
						
				  if(obj.checkData(batchId))
					{
					String studentName=userService.getUserName(userId);
					List<String> TeacherList = recordService.getTeacherFromBatch(batchId);
					WebSocketServiceImp wsObj=new WebSocketServiceImp();
					System.out.println("teacherlist remote user "+TeacherList);
					
					result.put("userId", userId);
					result.put("status", "remote");
					JsonParser<String, Hashtable<String,Object>> wsJson = new JsonParser<String, Hashtable<String,Object>>("addToAttendance",1,"Please add "+studentName,sessionID,"result",result);
					String a=wsJson.JsonResponseText();
						try {
							wsObj.writeTextmessage(TeacherList,a,false);
						} catch (IOException e) {
							e.printStackTrace();
						}
	
						result.clear();
					   
				
					}
	
					result.put("studentTimeTable", StSchedule);		
					result.put("scheduleIdList", StScheduleIdList);
			
					result.put("subjectList", subjects);
					
					 message="Success";
					    resultCode=1;
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
				log.debug("Session Valid");
				message="Invalid session";
				resultCode=0;
			}
			
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("remoteUser",resultCode,message,sessionID,"statusResult",result);
			return jsonResponse.JsonResponseText();
			
		}
	
	
		@RequestMapping(value = "/logout", method = RequestMethod.POST)
		public String logout(HttpSession sessionObj,HttpServletResponse response,
				@RequestParam("sessionID") String sessionID,
				@RequestParam("userId") String userId){
				Hashtable<String,Object> result=new Hashtable<String,Object>();
	
				
				String savedSessionID = userService.geUserSession(userId);
			String rebbonId = (String)sessionObj.getAttribute("rebbonId");
			//////////////////////
			log.debug("UserId="+userId);

			log.debug("RebbonId="+rebbonId);
			//////////////////////
	
			if(userId!=null && sessionID.equals(savedSessionID))
			{
				
				sessionObj.removeAttribute("userId");
				sessionObj.removeAttribute("sessionId");
			    
				if(userService.deletelogData(userId)==true)
				{

	
				if(sessionObj.getAttribute("role").equals("Student"))
				{
	
					AttendanceData obj=new AttendanceData();
					int batchId=Integer.parseInt((String)sessionObj.getAttribute("CourseBatchId"));
					
				if(obj.checkData(batchId))
				{
						String studentName=userService.getUserName(userId);
							
						List<String> teacherName=recordService.getTeacherFromBatch(batchId);
						WebSocketServiceImp wsObj=new WebSocketServiceImp();
						result.put("userId", userId);
	
						JsonParser<String, Hashtable<String,Object>> wsJson = new JsonParser<String, Hashtable<String,Object>>("removeFromAttendance",1,"Please remove "+studentName,sessionID,"result",result);
						String a=wsJson.JsonResponseText();
						try {
							wsObj.writeTextmessage(teacherName, a,false);
						} catch (IOException e) {
							e.printStackTrace();
						}
	
						result.clear();
					}
	
				}
				message="Success";
				resultCode=1;
				}
			
			else
			{
				message="session has expired,Please login to continue";
				resultCode=0;
			}
			}
			else
			{
				message="Invalid session";
				resultCode=0;
			}
			JsonParser<String, Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("logout",resultCode,message,sessionID,"logoutResult",result);
			return jsonResponse.JsonResponseText();
	
		}
		
		@RequestMapping(value = "/changeProPic", method = RequestMethod.POST)
		public String changeProPic(HttpSession sessionObj,HttpServletResponse response,@RequestParam("file") String picFile,@RequestParam("userId") String userId,
				@RequestParam("sessionID") String sessionID){
	

			Hashtable<String,Object> result=new Hashtable<String,Object>();
			try
			{
					System.out.println("sssssssssssssss"+picFile);
				String profileLink = userService.changeProPic(userId, picFile);
				if(profileLink!=null)
				{
				    resultCode=1;
					message="Success";
					result.put("profilePicLink",profileLink);
				}
				else
				{
					resultCode=0;
					message="error";
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				message = "Server Exception : "+e.getCause();
				resultCode = 2;
			}
	
				JsonParser<String,Hashtable<String,Object>> jsonResponse = new JsonParser<String, Hashtable<String,Object>>("changeProPic",resultCode,message,sessionID,"changeProPicResult",result);
			return jsonResponse.JsonResponseText();
	
		}
		
		
		

		
		
	
			
	}
