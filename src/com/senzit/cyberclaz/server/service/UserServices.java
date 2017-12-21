package com.senzit.cyberclaz.server.service;

import java.io.FileNotFoundException;
import java.util.Date;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import org.json.JSONException;
import org.springframework.web.multipart.MultipartFile;

public interface UserServices {
	
	int authenticateUser(String userId,String password);
	List<Object[]>getUserPrivilege(String UserId);
	String geUserSession(String userId);
	
    void setUserStatus(String userId, String Sessionid, String localStatus, String rebbonID);
	boolean isRebbonIdExist(String rebbonId);
	boolean isCorrectUser(String userId,String role);
	List<String> getStudentBatchId(String userId);
	String getUserName(String userId);
	boolean deletelogData(String userId);
	List<String> getAllStudentInClass(Integer courseBatchId);
	String getLogStatus(String userId);
	String getProfilePic(String userId);
	List<Properties> getSubjectList(String rebbonId);
	Hashtable<String,Object> getLoginResult(String userId);
	Hashtable<String,Object> getTeacherLoginResult(String userId);
	List<Properties> getSubjectListForRemaoteUser(Integer batchId);
	boolean saveDetailsInDB(String userId,String firstName,String lastName, String middleName,String emailId,String mobileNumber,String registered, String dob, String address);
	boolean savePasswordForUser(String userId,String password);
	String getRoleId(String userRoleName);
	boolean saveUserRoleForUser(String userId,String userroleId);
	boolean validateEmail(String userId, String emailId);
	int generateRandomCode();
	void sendToken(String userName, int token, byte smsOrEmail);
	boolean isSamePassword(String userId,String newPassword);
	int updatePassword(String userId, String password);
	boolean saveExcellentRatingDetailsInDb(String ratingvalue);
	List<Integer> getCountValue(String ratingvalue);
	List<Properties> getSubjectDetailsofUser(String userId);
	List<Properties> getStudentProfileDetails(String userId);
	List<Properties> teacherProfileDetails(String userId);
	void updateRegistrationDetailsinDb(String userId,String firstName,
			 String middleName, String lastName, String dob,
			String emailId,String mobileNum,String address, String approvalFlag);
//	void updateRegistrationDetailsinDb(String userId,
//			 String middleName, String lastName, String dob,
//			String address, String approvalFlag);
	List<Properties> getUserProfileDetails(String userId);
	boolean saveCourseBatchDetailsinDb(String userId, int cbatchId);
	boolean saveCourseSubjectDetailsinDb(String userId, int i, String string);
	String getRegistrationStatus(String userId);
	boolean saveScheduleDetails(String i, String j, String userId, int k);
	String getUserPassword(String userId);
	int getSubTeacherId(String teacherId);
	boolean getUserLoginStatus(String userId,String rebbonId);
	boolean getRebbonUser(String rebbonId);
	String changeProPic(String userId,String picFile) throws FileNotFoundException;
	//String changeProPic(String userId,MultipartFile picFile);
	boolean getUserLogStatus(String userId);
	void deleteUserLogData(String userId);
	String getCurrentPasswordOfUser(String userId);
	List<Properties> getManagerProfileDetails(String userId);
	String getNameForDummy();
	void insertUserDetails(String userId,String password);
	boolean dummyTestValues(String jsonString) throws JSONException;


	
}
