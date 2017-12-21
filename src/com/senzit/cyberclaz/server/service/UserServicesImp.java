package com.senzit.cyberclaz.server.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.PrintStream;


import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.internet.AddressException;



import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.messaging.MessagingException;
import org.springframework.web.multipart.MultipartFile;

import com.senzit.cyberclaz.server.model.Attendance;
import com.senzit.cyberclaz.server.model.CourseBatch;
import com.senzit.cyberclaz.server.model.CourseSubjectDao;
import com.senzit.cyberclaz.server.model.CyberInit;
import com.senzit.cyberclaz.server.model.Log;
import com.senzit.cyberclaz.server.model.LogDao;
import com.senzit.cyberclaz.server.model.ManagerSettingsDao;
import com.senzit.cyberclaz.server.model.NotificationDao;
import com.senzit.cyberclaz.server.model.Rebbon;
import com.senzit.cyberclaz.server.model.Role;
import com.senzit.cyberclaz.server.model.SchedulerDao;
import com.senzit.cyberclaz.server.model.SubjectTeacherDao;
import com.senzit.cyberclaz.server.model.UserLogin;
import com.senzit.cyberclaz.server.model.UserRole;
import com.senzit.cyberclaz.server.model.VideoRatingDao;

import com.senzit.cyberclaz.server.model.StudentBatchDao;
import com.senzit.cyberclaz.server.model.User;
import com.senzit.cyberclaz.server.model.UserDao;
import com.senzit.cyberclaz.server.quiz.Poll;
import com.senzit.cyberclaz.server.quiz.PollOption;
import com.senzit.cyberclaz.server.subservice.CyberService;
import com.senzit.cyberclaz.server.subservice.EmailSms;
import com.senzit.cyberclaz.server.model.CyberInit;




public class UserServicesImp implements UserServices 
{

	static Logger log = Logger.getLogger(UserServicesImp.class.getName());
	private UserDao userDao;
	private LogDao logDao;
	private StudentBatchDao studentBatchDao;
	private CourseSubjectDao courseSubjectDao;
	private VideoRatingDao videoRating;
	private SchedulerDao scheduleDao;
	private SubjectTeacherDao subjectTeacherDao;
	private ManagerSettingsDao managerSettingsDao;
	



	public void setManagerSettingsDao(ManagerSettingsDao managerSettingsDao) {
		this.managerSettingsDao = managerSettingsDao;
	}


	public void setSubjectTeacherDao(SubjectTeacherDao subjectTeacherDao) {
		this.subjectTeacherDao = subjectTeacherDao;
	}


	public void setScheduleDao(SchedulerDao scheduleDao) {
		this.scheduleDao = scheduleDao;
	}


	public void setVideoRating(VideoRatingDao videoRating) {
		this.videoRating = videoRating;
	}


	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}

	public void setStudentBatchDao(StudentBatchDao studentBatchDao) {
		this.studentBatchDao = studentBatchDao;
	}

	public void setCourseSubjectDao(CourseSubjectDao courseSubjectDao) {
		this.courseSubjectDao = courseSubjectDao;
	}
	@Override
	public boolean validateEmail(String userId, String emailId) {
			
			return emailId.equals(userDao.getPrimaryEmailId(userId));
		}
	@Override
	public int generateRandomCode()
	{
		return (int)Math.round(Math.random()* 89999) + 10000;
	}
	
	public void sendToken(String userId, int token, byte smsOrEmail)
			{
		
		if(smsOrEmail==1){//Send mail
			
			List<String> to=userDao.getPrimaryEmailId(userId);
			String userTo=to.get(0);
			String subject			 = "Token";
			String body   			 = "Verification Code:- " + token;
			

			try {
				EmailSms.sendMail(userTo, subject, body);
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (javax.mail.MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		else if(smsOrEmail==0){//Send sms
			
		}
	}
	@Override
	public int updatePassword(String userId, String password) {
		
		return userDao.updatePassword(userId, password);
	}


	@Override
	public boolean saveDetailsInDB(String userId,String firstName,String lastName, String middleName,String emailId,String mobileNumber,String registered, String dob, String address)
	{
		
	   String result=userDao.saveDetailsInDB(userId,firstName,middleName,lastName,emailId,mobileNumber,registered,dob,address);
	   if(result!=null)
	   {
	    System.out.println("values saved in DB");
	    return true;
	   }
	   else
	   {
		   System.out.println("saving error");
		   return false; 
	   }
	  
	}

	@Override
	public int authenticateUser(String userId, String password)
	{
		int AuthValue = userDao.getAuthUSer(userId, password);
		System.out.println("AuthVAlue="+AuthValue);
		if(AuthValue==0)
		{
		log.debug("user "+AuthValue);	
		return AuthValue;
		}
		else
		{
		return AuthValue;
		}
	}

	@Override
	public List<Object[]> getUserPrivilege(String userId) 
	{
		List<Object[]> privilege = userDao.getUserRolePrivilege(userId);

		return privilege;
	}

	@Override
	public void setUserStatus(String userId, String sessionId, String loginStatus, String rebbonId) 
	{
		
		
		System.err.println("In UserImp");
		
		
		
		System.out.println(userId);
		System.out.println(sessionId);
		System.out.println(loginStatus);
		System.out.println(rebbonId);
		
		User userObj=new User();
		userObj.setUserId(userId);
		Log logObj=logDao.getUserStatus(userId);
		//Log logObj = new Log();
		Rebbon rebObj = new Rebbon();
		rebObj.setRebbonId(rebbonId);

		if(logObj!=null)
		{
        	logObj.setLoginStatus(true);
			logObj.setSessionId(sessionId);
			logDao.updateLoginStatus(logObj);
		}	
		else
		{
		
			logObj = new Log();
			logObj.setLoginStatus(true);
			
			if(rebbonId!=null)
			logObj.setRebbon(rebObj);
			logObj.setSessionId(sessionId);
			logObj.setUser(userObj);
			
            System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+logObj);

			logDao.setLogStatus(logObj);
		
		}
	}
/////////////////////////////////  remote user login time,use below code ///////////////////////
//	@Override
//	public void setUserStatus(String userId, String sessionId, String loginStatus,
//			String rebbonId) 
//	{
//		
//		System.out.println(userId);
//		System.out.println(sessionId);
//		System.out.println(loginStatus);
//		System.out.println(rebbonId);
//		
//		User userObj=new User();
//		userObj.setUserId(userId);
//		Log logObj=logDao.getUserStatus(userId);
//		//Log logObj = new Log();
//		Rebbon rebObj = new Rebbon();
//		rebObj.setRebbonId(rebbonId);
//
//		if(logObj!=null)
//		{
//        	logObj.setLoginStatus(true);
//			logObj.setSessionId(sessionId);
//			logDao.updateLoginStatus(logObj);
//		}	
//		else
//		{
//		
//			logObj = new Log();
//			
//			
//			if(rebbonId!=null)
//			{
//			logObj.setLoginStatus(true);
//			logObj.setRebbon(rebObj);
//			logObj.setSessionId(sessionId);
//			logObj.setUser(userObj);
//			
//            System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+logObj);
//
//			logDao.setLogStatus(logObj);
//		}
//		else
//		{
//			logObj.setSessionId(sessionId);
//			logObj.setUser(userObj);
//			logObj.setLoginStatus(false);
//			logDao.setLogStatus(logObj);
//		}
//		}
//	}

	@Override
	public boolean isRebbonIdExist(String rebbonId) {

		Log logObj=logDao.isRebbonExist(rebbonId);

		if(logObj!=null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean isCorrectUser(String userId,String role) {
		List<String> roleList=userDao.getRole(userId);
		if(roleList.isEmpty())
			return false;
		if(roleList.get(0).equals(role))
			return true;
		return false;

	}

	@Override
	public List<String> getStudentBatchId(String userId) {

		List<Object[]> studDetails = studentBatchDao.getStudBatchId(userId);
		List<String> courseData = new ArrayList<String>();
		Object[] ob = studDetails.get(0);
		for(int i=0;i<ob.length;i++)
		{
		
			courseData.add(ob[i].toString());
		}
		return courseData;
	}

	@Override
	public String getUserName(String userId) {
		List<String> uName = userDao.getUserName(userId);
		String userName = uName.get(0);
		return userName;
	}

	@Override
	public boolean deletelogData(String userId) {
		
		System.out.println(userId);
		Log log = logDao.getUserLog(userId);
		if(log==null)
		{
		System.out.println("user Log status in the logout time"+log);
		return false;
		}
		else
		{
		Log logObj = new Log();
		logObj.setUserId(userId);
		userDao.deleteLog(logObj);
		return true;
		}

	}

	@Override
	public List<String> getAllStudentInClass(Integer courseBatchId) {

		CourseBatch batch=new CourseBatch();
		batch.setCourseBatchId(courseBatchId);
		List<Object[]> userNameList=userDao.getUserNameFromBatch(batch);
		
		List<String>userName = new ArrayList<String>();
	
		for(int i=0;i<userNameList.size();i++)
		{
			Object[] ob = userNameList.get(i);
			userName.add((String)ob[0]);
		
		}
		return userName;
	}
	@Override
	public String getLogStatus(String userId) {
		String status="";
		Log LogObj=logDao.getUserStatus(userId);
		if(LogObj==null)
			return null;
		else
		{
			if( LogObj.isLoginStatus())
			{
				status="Local";
			}
			else
			{
				status="Remote";
			}

			return status;
		}


	}

	@Override
	public List<Properties> getSubjectList(String rebbonId) 
	{
		List<Properties> subjectList = new ArrayList<Properties>();
		List<Object[]> subjects = courseSubjectDao.getSubjectList(rebbonId);
		for(int i=0;i<subjects.size();i++)
		{
			Object[] ob = subjects.get(i);
			Properties prop = new Properties();
			prop.put("subjectId", ob[0]);
			prop.put("subjectName", ob[1]);
			subjectList.add(prop);
		}
		return subjectList;
	}
	public List<Properties> getSubjectListForRemaoteUser(Integer batchId)
	{
		List<Properties> subjectList = new ArrayList<Properties>();
		List<Object[]> subjects = courseSubjectDao.getSubjectListForRemoteUser(batchId);
		for(int i=0;i<subjects.size();i++)
		{
			Object[] ob = subjects.get(i);
			Properties prop = new Properties();
			prop.put("subjectId", ob[0]);
			prop.put("subjectName", ob[1]);
			subjectList.add(prop);
		}
		return subjectList;
	}
	@Override
	public Hashtable<String, Object> getLoginResult(String userId) {
		
		Hashtable<String,Object> result=new Hashtable<String,Object>();
		Object[] obj = userDao.getUserLoginResults(userId);
		if(obj!=null){
			
			result.put("class", obj[1]+" "+obj[2]+" "+obj[3]);
			result.put("proPic", (obj[0]==null)?"":obj[0]);
			result.put("firstName", obj[4]);
			result.put("email", (obj[5]==null)?"":obj[5]);
		}
		return result;
	}
	@Override
	public String getProfilePic(String userId)
	{
		//String result = null;
     System.out.println("userId"+userId);
     String obj = userDao.getProfilePic(userId);
	    
	    System.out.println("TTTtttttttttttttttttttttttttttttttttttttttttttttttttttttttttZSSSSSSSSSSSSSSSSSSSSSSS"+obj);
	    if(obj!=null)
	    {
		return obj;
	    }
	    else
	    {
	     return null;
	    }
		
   
		}
			
	@Override
	public Hashtable<String, Object> getTeacherLoginResult(String userId) {
		
		Hashtable<String,Object> result=new Hashtable<String,Object>();
		Object[] obj = userDao.getTeacherLoginResults(userId);
		if(obj!=null){
			
			result.put("proPic", (obj[0]==null)?"":obj[0]);
			result.put("firstName", obj[1]);
			result.put("email", (obj[2]==null)?"":obj[2]);
		}
		return result;
	}

	@Override
	public boolean savePasswordForUser(String userId, String password)
	{
		
		String resultNew=userDao.savePasswordForUser(userId,password);
		if(resultNew!=null)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	   
		}

	@Override
	public String getRoleId(String userRoleName) {
		
		List<String> roleId = userDao.getRoleId(userRoleName);
		String roleIdForUser = roleId.get(0);	
		return roleIdForUser;
	}

	@Override
	public boolean saveUserRoleForUser(String userId,String userroleId) {
		

		String value=userDao.saveUserRoleForUser(userId,userroleId);
		if(value!=null)
		{
		return true;
		}
		else
		{
		return false;
		}

	}
	@Override
	public boolean isSamePassword(String userId, String newPassword) {
		
		return newPassword.equals(userDao.getPassword(userId));
	}


	@Override
	public boolean saveExcellentRatingDetailsInDb(String ratingvalue) {
		
		int value=videoRating.saveExcellentRatingDetailsInDb(ratingvalue);
		if(value!=0)
		{
			return true;
		}
		else
		{
			return false;
		}

	}


	@Override
	public List<Integer> getCountValue(String ratingvalue) {
		
		List<Integer> value=videoRating.getCountValue(ratingvalue);
		return value;
		
	}


	@Override
	public List<Properties> getSubjectDetailsofUser(String userId) {
		List<Properties> subjectList = new ArrayList<Properties>();
		
		List<Object[]> subjects = courseSubjectDao.getSubjectDetailsofUser(userId);
		for(int i=0;i<subjects.size();i++)
		{
			Object[] ob = subjects.get(i);
			Properties prop = new Properties();
			prop.put("subjectName", ob[0]);
			prop.put("subjectId", ob[1]);
			subjectList.add(prop);
		}
		return subjectList;
	}


	@Override
	public List<Properties> getStudentProfileDetails(String userId) {
		
		List<Object[]> objList=userDao.getStudentProfileDetails(userId);
		 ArrayList<Properties> propList=new ArrayList<Properties>();
	
		 for(int i=0;i<objList.size();i++)
			{
				Object obj[] = objList.get(i);
		  
		   Properties prop=new Properties();
		 
		   prop.put("firstName",obj[0].toString());
		   prop.put("dob",(obj[1]==null || obj[1]=="0")?"null":obj[1].toString());
		   prop.put("mobileNumber",obj[2].toString());
		   prop.put("emailId",obj[3].toString());
		   prop.put("address",(obj[4]==null || obj[4]=="0")?"null":obj[4].toString());
		   prop.put("courseName",obj[5].toString());
		   prop.put("semName",obj[6].toString());
		   prop.put("classRoomNo",obj[7].toString());
		   prop.put("batchname",obj[8].toString());
		   prop.put("registrationStatus",obj[9].toString());
		   prop.put("middleName",(obj[10]==null || obj[10]=="0")?"null":obj[10].toString());
		   propList.add(prop);
		    }
		  
		   
		  return propList;
		
	}


	@Override
	public List<Properties> teacherProfileDetails(String userId) {
		
		List<Object[]> objList=userDao.teacherProfileDetails(userId);
		List<Object[]> subjects = courseSubjectDao.getSubjectDetailsofUser(userId);
		 ArrayList<Properties> propList=new ArrayList<Properties>();
		 for(int i=0;i<objList.size();i++)
			{
				Object obj[] = objList.get(i);
		  
		  
		   Properties prop=new Properties();
		 
		   prop.put("firstName",obj[0].toString());
		   prop.put("dob",(obj[1]==null || obj[1]=="0")?"null":obj[1].toString());
		   prop.put("mobileNumber",obj[2].toString());
		   prop.put("emailId",obj[3].toString());
		   prop.put("address",(obj[4]==null || obj[4]=="0")?"null":obj[4].toString());
		   prop.put("courseName",obj[5].toString());
		   prop.put("semName",obj[6].toString());
		   prop.put("batchname",obj[7].toString());
		   prop.put("registrationStatus",obj[8].toString());
		   prop.put("middleName",(obj[9]==null || obj[9]=="0")?"null":obj[9].toString());
		   prop.put("classRoomNo","101");
		   propList.add(prop);
		    }
//		  for(int i=0;i<subjects.size();i++)
//			{
//				Object objNew[] = subjects.get(i);
//		  
//				 
//				   Properties propNew=new Properties();
//				 
//				   propNew.put("subjectName", objNew[0]);
//				   propNew.put("subjectId", objNew[1]);
//				   propList.add(propNew);
//			}
	  return propList;
		
	}


	@Override
	public void updateRegistrationDetailsinDb(String userId,String firstName,String middleName,
			String lastName, String dob,String emailId,String mobileNum,String address,String approvalFlag) {
//	
//	public void updateRegistrationDetailsinDb(String userId,String middleName,
//			String lastName, String dob,String address,String approvalFlag) {

		int value=userDao.updateRegistrationDetailsinDb(userId,firstName,middleName,lastName,dob,emailId,mobileNum,address,approvalFlag);
		//int value=userDao.updateRegistrationDetailsinDb(userId,middleName,lastName,dob,address,approvalFlag);
		if(value!=0)
	    {
		log.debug(" Saved Successfully");
        }
	  else
	   {
		log.debug(" Saving error");
	    }
		
	}


	@Override
	public List<Properties> getUserProfileDetails(String userId) {
		
		List<Object[]> objList=userDao.getUserProfileDetails(userId);
		 ArrayList<Properties> propList=new ArrayList<Properties>();
		 for(int i=0;i<objList.size();i++)
			{
				Object obj[] = objList.get(i);
				
		   Properties prop=new Properties();
		 
		   
	String profilePicPath    = CyberService.getProfilePicPath();
		   
		   
		   
		   prop.put("firstName",obj[0].toString());
		   prop.put("lastName",obj[1].toString());
		   prop.put("profilePic",obj[2].toString());
		   prop.put("dob",obj[3].toString());
		   prop.put("emailId",obj[4].toString());
		   prop.put("mobileNumber",obj[5].toString());
		   prop.put("address",obj[6].toString());
		   prop.put("middleName",obj[7].toString());
		  
		   propList.add(prop);
		    }
		  
		   
		  return propList;
	}


	@Override
	public boolean saveCourseBatchDetailsinDb(String userId, int cbatchId) {
		
		String value=studentBatchDao.saveCourseBatchDetailsinDb(userId,cbatchId);
		if(value!=null)
		{
			return true;
		}
		else
		{
			return false;
		}

	}


	@Override
	public boolean saveCourseSubjectDetailsinDb(String userId, int i,
			String batchId) {
		
		int value=studentBatchDao.saveCourseSubjectDetailsinDb(userId,i,batchId);
		if(value!=0)
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
		
	}


	@Override
	public String getRegistrationStatus(String userId) {
	String regStatus=userDao.getRegistrationStatus(userId);
		return regStatus;
	}


	@Override
	public boolean saveScheduleDetails(String i, String j, String userId, int k) {
		int value=scheduleDao.saveScheduleDetails(i,j,userId,k);
		if(value!=0)
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}


	@Override
	public String getUserPassword(String userId) {
	  String userPasswrd=userDao.getUserPassword(userId);
	  return userPasswrd;
	}


	@Override
	public int getSubTeacherId(String teacherId) {
		int subTeacherId=subjectTeacherDao.getSubTeacherId(teacherId);
		return subTeacherId;
	}


	@Override
	public boolean getUserLoginStatus(String userId,String rebbonId) {
		User userObj=new User();
		userObj.setUserId(userId);
		Rebbon reb=new Rebbon();
		reb.setRebbonId(rebbonId);
		Log log=new Log();
		log.setUser(userObj);
		log.setRebbon(reb);
		Log logObj=logDao.getUserStatus(userId);
		if(logObj!=null)
		{
		
			System.out.println("user already login "+logObj);
			System.out.println(userId);
			
			Log logOb = new Log();
			logOb.setUserId(userId);
			userDao.deleteLog(logObj);
            return false ;
		}
		return true;
	}

	public boolean getUserLogStatus(String userId)
	{
		
		User userObj=new User();
		userObj.setUserId(userId);
		
		Log log=new Log();
		log.setUser(userObj);
	
		Log logObj=logDao.getUserStatus(userId);
		if(logObj!=null)
		{
			System.out.println("user already login "+logObj);
			System.out.println(userId);
			

            return false ;
		}
		else
		{
		return true;
		}
	}

		
	
	@Override
	public boolean getRebbonUser(String rebbonId) {
	
		List<String> teacherIdz=logDao.getRebbonUser(rebbonId);
		if(!teacherIdz.isEmpty())
		{
		for(int i=0;i<teacherIdz.size();i++)
	    {
	
			
			String userId=teacherIdz.get(i);
			Log logObj = new Log();
			logObj.setUserId(userId);
			userDao.deleteLog(logObj);
			
		}
		}
		return true;
	
		
	}


//	@Override
//	public String changeProPic(String userId, MultipartFile picFile) {
//		
////		if(picFile==null)
////			return userDao.updateProPic(userId, null);
//		String webLink = null;
//		if(picFile!=null)
//		{
//		
//		File file =  new File(CyberInit.webAppFolder+File.separatorChar+"ProfilePic"+File.separatorChar+picFile.getOriginalFilename());
//		try {
//			picFile.transferTo(file);
//			 webLink = CyberInit.ipAddress+"/ProfilePic/"+picFile.getOriginalFilename();
//			 userDao.updateProPic(userId, webLink);
//		}
//		catch (IllegalStateException | IOException e) {
//			e.printStackTrace();
//		}
//		
//	
//	}
//		return webLink;
//	}

	@Override
	public String changeProPic(String userId, String picFile) throws FileNotFoundException {
		
//		if(picFile==null)
//			return userDao.updateProPic(userId, null);
		
		String webLink = null;
		if(picFile!=null)
		{
			
			 picFile = picFile.replaceAll(" ", "+");
			 String[] parts = picFile.split("\\,");
			 
			 String beforeFirstDot = parts[1];
			byte[] imageByteArray = Base64.decodeBase64(beforeFirstDot);
			FileOutputStream imageOutFile = new FileOutputStream(CyberInit.webAppFolder+File.separatorChar+"ProfilePic"+File.separatorChar+userId+"."+"jpg");
		try {
			imageOutFile.write(imageByteArray);
			 webLink = CyberInit.ipAddress+"/ProfilePic/"+userId+"."+"jpg";
			 userDao.updateProPic(userId, webLink);
		}
		catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
	
	}
		return webLink;
	}

	@Override
	public void deleteUserLogData(String userId) {
		System.out.println(userId);
		
		Log logObj = new Log();
		logObj.setUserId(userId);
		userDao.deleteLog(logObj);
		
		}


	@Override
	public String getCurrentPasswordOfUser(String userId) {
		return userDao.getPassword(userId);
	}


	@Override
	public List<Properties> getManagerProfileDetails(String userId) {
		
		List<Object[]> objList=userDao.getManagerProfileDetails(userId);
		 ArrayList<Properties> propList=new ArrayList<Properties>();
	
		 for(int i=0;i<objList.size();i++)
			{
				Object obj[] = objList.get(i);
		  
		   Properties prop=new Properties();
		 
		   prop.put("firstName",obj[0].toString());
		   prop.put("dob",(obj[1]==null || obj[1]=="0")?"null":obj[1].toString());
		   prop.put("mobileNumber",obj[2].toString());
		   prop.put("emailId",obj[3].toString());
		   prop.put("address",(obj[4]==null || obj[4]=="0")?"null":obj[4].toString());
		   prop.put("registrationStatus",obj[5].toString());
		   prop.put("middleName",(obj[6]==null || obj[6]=="0")?"null":obj[6].toString());
		   propList.add(prop);
		    }
		  
		   
		  return propList;	
	}

	@Override
	public String getNameForDummy() {
			String value=userDao.getNameForDummy();
			return value;
		}





	@Override
	public boolean dummyTestValues(String jsonString) throws JSONException {
		//return false;
//		JSONObject jsonData = new JSONObject(jsonString);

//		Poll pollObj = new Poll();
//		pollObj.setPollQuestion(jsonData.getString("question"));
//		User userObj = new User();
//		userObj.setUserId(userName);
//		pollObj.setCreatedBy(userObj);
//		
//		Integer pollId = quizDao.insertPoll(pollObj);
//		
//		if(pollId == null)
//			return false;
//		
//		JSONArray opList = (JSONArray) jsonData.get("options");
//		int l = opList.length();
//		for(int i=0;i<l;i++){
//			
//			PollOption obj = new PollOption();
//			obj.setOption(opList.getString(i));
//			obj.setPoll(pollObj);
//			quizDao.insertPollOption(obj);
//		}
//		return true;
		
		JSONObject json=new JSONObject(jsonString);
		json.get("question");
		System.out.println(json.get("question"));
		JSONArray list=(JSONArray)json.get("options");
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.get(2));
		return true;
		
	}


	@Override
	public String geUserSession(String userId) {

	
		
		String userSession = logDao.getSession(userId);
		System.out.println("User Session : "+userSession);
		
		
		
		return userSession;
	}


	@Override
	public void insertUserDetails(String userId, String password) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	}
		
	

	

