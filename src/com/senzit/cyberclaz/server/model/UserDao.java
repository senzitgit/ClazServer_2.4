package com.senzit.cyberclaz.server.model;

import java.util.Date;
import java.util.List;


public interface UserDao {

	int getAuthUSer(String userId,String password);
	List<Object[]>getUserRolePrivilege(String userId);
	List<String> getRole(String userId);
	List<String> getUserName(String userId);
	void deleteLog(Log logObj);
	List<Object[]> getUserNameFromBatch(CourseBatch batch);
	Object[] getUserLoginResults(String userId);
	Object[] getTeacherLoginResults(String userId);
	String saveDetailsInDB(String userId,String firstName,String lastName, String middleName,String emailId,String mobileNumber,String registered, String dob, String address);
	String savePasswordForUser(String userId,String password);
	List<String> getRoleId(String userRoleName);
	String saveUserRoleForUser(String userId,String userroleId);
	List<String> getPrimaryEmailId(String userId);
	String getPassword(String userId);
	int updatePassword(String userId,String password);
	String getProfilePic(String userId);
	String getProfilePicFromEventId(int clazEventDetailId);
	List<Object[]> getStudentProfileDetails(String userId);
	List<Object[]> teacherProfileDetails(String userId);
	int updateRegistrationDetailsinDb(String userId,String firstName, 
			 String middleName, String lastName, String dob,
			 String emailId,String mobileNum,String address, String approvalFlag);
//	int updateRegistrationDetailsinDb(String userId, 
//			 String middleName, String lastName, String dob,
//			 String address, String approvalFlag);
	List<Object[]> getUserProfileDetails(String userId);
	String getRegistrationStatus(String userId);
	String getUserPassword(String userId);
	
	int updateProPic(String userId,String picLink);
	String getTeacherIdFromClazEventId(int clazEventDetailId);
	List<String> getTeacherList();
	List<Object[]> getManagerProfileDetails(String userId);
	String getNameForDummy();

	
}
