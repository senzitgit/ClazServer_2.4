package com.senzit.cyberclaz.server.service;

import java.util.ArrayList;



import java.util.List;

import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.senzit.cyberclaz.server.cognos.AssignedTaskDao;

import com.senzit.cyberclaz.server.cognos.AddAssignment;
import com.senzit.cyberclaz.server.cognos.AddAssignmentDao;
import com.senzit.cyberclaz.server.cognos.AssignedTask;
import com.senzit.cyberclaz.server.cognos.AttendanceReportDao;
import com.senzit.cyberclaz.server.cognos.FutureGoals;
import com.senzit.cyberclaz.server.cognos.FutureGoalsDao;
import com.senzit.cyberclaz.server.cognos.MaximumHoursDao;
import com.senzit.cyberclaz.server.cognos.ProgressReports;
import com.senzit.cyberclaz.server.cognos.ProgressReportsDao;
import com.senzit.cyberclaz.server.cognos.SubjectPerformance;
import com.senzit.cyberclaz.server.cognos.SubjectPerformanceDao;
import com.senzit.cyberclaz.server.cognos.TargetAttendancePercentage;
import com.senzit.cyberclaz.server.cognos.TargetAttendancePercentageDao;
import com.senzit.cyberclaz.server.cognos.TargetPassPercentage;
import com.senzit.cyberclaz.server.cognos.TargetPassPercentageDao;
import com.senzit.cyberclaz.server.cognos.TargetProgressDao;
import com.senzit.cyberclaz.server.cognos.TeacherRecommendation;
import com.senzit.cyberclaz.server.cognos.TeacherRecommendationDao;
import com.senzit.cyberclaz.server.cognos.TopPerformer;
import com.senzit.cyberclaz.server.cognos.TopPerformerDao;
import com.senzit.cyberclaz.server.model.TopSessionDao;
import com.senzit.cyberclaz.server.model.AttendanceDao;
import com.senzit.cyberclaz.server.model.ClassEventDetail;
import com.senzit.cyberclaz.server.model.CourseBatch;
import com.senzit.cyberclaz.server.model.CourseBatchDao;
import com.senzit.cyberclaz.server.model.StudentBatchDao;
import com.senzit.cyberclaz.server.model.Subject;
import com.senzit.cyberclaz.server.model.SubjectDao;
import com.senzit.cyberclaz.server.model.User;
import com.senzit.cyberclaz.server.model.UserDao;


public class CognosServiceImp implements CognosService
{ 
	private AddAssignmentDao addAssignmentDao;
	private AttendanceReportDao attendanceReportDao;
	private FutureGoalsDao futureGoalsDao;
	private MaximumHoursDao maximumHoursDao;
	private ProgressReportsDao progressReportsDao;
	private TargetProgressDao targetProgressDao;
	private TeacherRecommendationDao teacherRecommendationDao;
	private SubjectDao subjectDao;
	private AttendanceDao attendanceDao;
	private TopPerformerDao topPerformerDao;
	private TopSessionDao topSessionDao;
	private StudentBatchDao studentBatchDao;
	private UserDao userDao;
	private SubjectPerformanceDao subjectPerformanceDao;
	private TargetPassPercentageDao targetPassPercentageDao;
	private TargetAttendancePercentageDao targetAttendancePercentageDao;
	private CourseBatchDao courseBatchDao;
	private AssignedTaskDao assignedTaskDao;



	public void setAddAssignmentDao(AddAssignmentDao addAssignmentDao) {
		this.addAssignmentDao = addAssignmentDao;
	}


	public void setTargetAttendancePercentageDao(
			TargetAttendancePercentageDao targetAttendancePercentageDao) {
		this.targetAttendancePercentageDao = targetAttendancePercentageDao;
	}


	public void setCourseBatchDao(CourseBatchDao courseBatchDao) {
		this.courseBatchDao = courseBatchDao;
	}


	public void setTargetPassPercentageDao(
			TargetPassPercentageDao targetPassPercentageDao) {
		this.targetPassPercentageDao = targetPassPercentageDao;
	}


	public void setSubjectPerformanceDao(SubjectPerformanceDao subjectPerformanceDao) {
		this.subjectPerformanceDao = subjectPerformanceDao;
	}


	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	public void setStudentBatchDao(StudentBatchDao studentBatchDao) {
		this.studentBatchDao = studentBatchDao;
	}


	public void setTopPerformerDao(TopPerformerDao topPerformerDao) {
		this.topPerformerDao = topPerformerDao;
	}


	public void setTopSessionDao(TopSessionDao topSessionDao) {
		this.topSessionDao = topSessionDao;
	}


	public void setAttendanceDao(AttendanceDao attendanceDao) {
		this.attendanceDao = attendanceDao;
	}

	public void setSubjectDao(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}

	public void setAssignedTaskDao(AssignedTaskDao assignedTaskDao) {
		this.assignedTaskDao = assignedTaskDao;
	}
	
	public void setAttendanceReportDao(AttendanceReportDao attendanceReportDao) {
		this.attendanceReportDao = attendanceReportDao;
	}
	
	public void setFutureGoalsDao(FutureGoalsDao futureGoalsDao) {
		this.futureGoalsDao = futureGoalsDao;
	}
	
	public void setMaximumHoursDao(MaximumHoursDao maximumHoursDao) {
		this.maximumHoursDao = maximumHoursDao;
	}

	public void setProgressReportsDao(ProgressReportsDao progressReportsDao) {
		this.progressReportsDao = progressReportsDao;
	}
	
	public void setTargetProgressDao(TargetProgressDao targetProgressDao) {
		this.targetProgressDao = targetProgressDao;
	}
	
	public void setTeacherRecommendationDao(
			TeacherRecommendationDao teacherRecommendationDao) {
		this.teacherRecommendationDao = teacherRecommendationDao;
	}

	@Override
	public Integer saveAssignmentTasksInDb(String userId, String subjectId,
			String topic) {
		
		User u=new User();
		u.setUserId(userId);
		Subject sub=new Subject();
		sub.setSubjectId(subjectId);
		
		AssignedTask at=new AssignedTask();
		at.setUser(u);
		at.setSubject(sub);
		at.setAssignedTopic(topic);
	//	at.setStatus(status);
		
		Integer value=assignedTaskDao.saveAssignmentTasksInDb(at);
		if(value!=0)
		{
			return value;
		}
		else
		{
			return 0;
		}
	}

	@Override
	public String getSubjectNameFromSubjectId(String subject) {
		String subId=subjectDao.getSubjectNameFromSubjectId(subject);
		if(subId!=null)
		{
		return subId;
	    }
	    else
	    {
		return null;
	   }
	
}
	
	
	/////////////////////////////////////////////////////////////
	
	public void assignTaskToAll(String classRoomNo,String subject,String topic){
		
		List<User> studentList = studentBatchDao.getStudentFromStudentBatch(classRoomNo);
		
		Subject sub=new Subject();
		sub.setSubjectId(subjectDao.getSubjectNameFromSubjectId(subject));
		
		for(User user : studentList){		
			
			AssignedTask at=new AssignedTask();
			at.setSubject(sub);
			at.setAssignedTopic(topic);
			at.setUser(user);
			assignedTaskDao.saveAssignmentTasksInDb(at);
		}
		
	}
	
	public void assignTaskToGroup(String subject,String assignJson){
		
		Subject sub=new Subject();
		sub.setSubjectId(subjectDao.getSubjectNameFromSubjectId(subject));
		try {
			JSONArray jsonArray = new JSONArray(assignJson);
			int len = jsonArray.length();
			for(int i=0;i<len;i++){
				
				JSONObject obj = jsonArray.getJSONObject(i);
				String topic = obj.getString("topic");
				JSONArray array = obj.getJSONArray("userIdList");
				int len2 = array.length();
				for(int j=0;j<len2;j++){
					
					String userId = array.getString(j);
					AssignedTask at=new AssignedTask();
					at.setSubject(sub);
					at.setAssignedTopic(topic);
					User user = new User();
					user.setUserId(userId);
					at.setUser(user);
					assignedTaskDao.saveAssignmentTasksInDb(at);
					
				}				
			}
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	///////////////////////////////////////////////////////////
	
	
	

	@Override
	public void saveprogressReportsInDb(String userId, String term,
			String subId, String mark) {
		
		User u=new User();
		u.setUserId(userId);
		Subject sub=new Subject();
		sub.setSubjectId(subId);
		ProgressReports pr=new ProgressReports();
		Integer progressId=progressReportsDao.getProgressIdFromDB(userId,term,subId);
		if(progressId==0)
		{
		pr.setUser(u);
		pr.setSubject(sub);
		pr.setTerm(term);
		pr.setMarks(mark);
		Integer value=progressReportsDao.saveprogressReportsInDb(pr);
//		if(value!=0)
//		{
//			return true;
//		}
//		else
//		{
//			return false;
//		}
		}
		else
		{
			Integer updateValue=progressReportsDao.updateMarksInDB(userId,term,subId,mark);
		}
		

	}

	@Override
	public void saveteacherRecommendationInDb(String userId, String subId,String term,
			String rating) {
		
		User u=new User();
		u.setUserId(userId);
		Subject sub=new Subject();
		sub.setSubjectId(subId);
		TeacherRecommendation tr=new TeacherRecommendation();
		
		
		Integer value=teacherRecommendationDao.getTeacherRecomentationId(userId,subId,term);
		
		System.out.println("VAlue"+value);
		
		if(value==0)
		{
			
			tr.setUser(u);
			tr.setSubject(sub);
			tr.setTerm(term);
			tr.setRating(rating);
	
		  Integer result=teacherRecommendationDao.saveteacherRecommendationInDb(tr);
		  if(result==1)
		  {
			  System.out.println("successfully inserted");
		  }
		  else
		  {
			  System.out.println("can't inserted");
		  }
		
		}
//		else
//		{
//			Integer updateResult=teacherRecommendationDao.updateTeacherRecommendation(userId,subId,term,rating);
//		}
	}

	@Override
	public 	List<Properties> getAttendanceDetails(String userId, String month,
			String subId) {
		
		List<Object[]> attendanceDetails=attendanceDao.getAttendanceDetails(userId,month,subId);
		Properties attDetails = new Properties();
	  List<Properties> tempDate = new ArrayList<Properties>();
	
		if(!(attendanceDetails.isEmpty()))
		{
			Object ob[] = attendanceDetails.get(0);
			Integer  attendedSession=(Integer) ob[0];
			Integer  totalSession=(Integer) ob[1];
			attDetails.put("attendedSession", attendedSession);
			attDetails.put("totalSession", totalSession);
			tempDate.add(attDetails);
		}
		return tempDate;
	

	}
	@Override
	public void saveFutureGoalsInDb(String userId,Integer cBatchId,String goal)
	{
		User u=new User();
		u.setUserId(userId);
		CourseBatch cb=new CourseBatch();
		cb.setCourseBatchId(cBatchId);
		FutureGoals pr=new FutureGoals();
		Integer goalId=futureGoalsDao.getGoalIdForUser(userId,cBatchId);
		if(goalId==0)
		{
		pr.setUser(u);
		pr.setCourseBatch(cb);
	    pr.setGoal(goal);
		Integer value=futureGoalsDao.saveFutureGoalsInDb(pr);
//		if(value!=0)
//		{
//			return true;
//		}
//		else
//		{
//			return false;
//		}
		}
		else
		{
			Integer goalUpdation=futureGoalsDao.updateGoalInDB(userId,cBatchId,goal);
		}
		
	}


//	@Override
//	public void saveUserRatingAboutTeacher(String teacherId) {
//		
//	   User u=new User();
//	   u.setUserId(teacherId);
//		TopPerformer tp=new TopPerformer();
//		Integer value=topPerformerDao.getTopPerformerIdTeacher(teacherId);
//		List<Integer> rateCount=topPerformerDao.getTeacherRateCount(teacherId);
//		if(value==0)
//		{
//			tp.setUser(u);
//			tp.setLikes(1);
//			Integer Tperformance=topPerformerDao.saveUserRatingAboutTeacher(tp);
//		}
//		else
//		{
//			Integer rateResult=(rateCount.get(0))+1;
//			topPerformerDao.updateTheRateCount(teacherId,rateResult);
//			
//			
//		}
//		
//	}


	@Override
	public List<Properties> getStudentNameFromClass(String classRoomNo) {
		List<Object[]> studentList=studentBatchDao.getStudentNameFromClass(classRoomNo);
		List<Properties> SList = new ArrayList<Properties>();
		for(int i=0;i<studentList.size();i++)
		{
			//Object ob[] = studentList.get(i);
			Properties prop = new Properties();
			Object[]  userInfo=studentList.get(i);

			prop.put("studentId", userInfo[0]);
			prop.put("firstName", userInfo[1]==null?"":userInfo[1]);
			prop.put("middleName", userInfo[2]==null?"":userInfo[2]);
			prop.put("lastName", userInfo[3]==null?"":userInfo[3]);
			prop.put("profilePic", userInfo[4]==null?"":userInfo[4]);
		
			SList.add(prop);
		}
//		teacher.add(temp);

		return SList;	
	}


	@Override
	public List<Properties> getTeacherList() {
		
		List<String> teacherList=userDao.getTeacherList();
		List<Properties> TList = new ArrayList<Properties>();
		for(int i=0;i<teacherList.size();i++)
		{
			//Object ob[] = studentList.get(i);
			Properties prop = new Properties();
			String  userId=teacherList.get(i);

			prop.put("teacherId", userId);
		
			TList.add(prop);
		}
//		teacher.add(temp);

		return TList;	
	}


	@Override
	public boolean updateAssignmentTaskStatus(String userId,String subId,String status) {
	
		Integer value=assignedTaskDao.updateAssignmentTaskStatus(userId,subId,status);
		if(value==1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}


	@Override
	public boolean saveSubjectPerformanceDetailsInDb(String userId,
			String subId, String rating) {
		
		User u=new User();
		u.setUserId(userId);
		Subject sub=new Subject();
		sub.setSubjectId(subId);
		SubjectPerformance sp=new SubjectPerformance();
		sp.setUser(u);
		sp.setSubject(sub);
		sp.setRating(rating);
		Integer value=subjectPerformanceDao.saveSubjectPerformanceDetailsInDb(sp);
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
	public boolean subjectPerformanceDetailsUpdation(String userId,String subjectId,String rating)
	{
		int value=subjectPerformanceDao.subjectPerformanceDetailsUpdation(userId,subjectId,rating);
		if(value==1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}


	@Override
	public Integer getCourseBatchFromClassRoom(String classRoomNo) {
	
		Integer cBatchId=courseBatchDao.getCourseBatchFromClassRoom(classRoomNo);
	
			return cBatchId;
	}


	@Override
	public void savetargetPassPercentageDetailsInDb(String teacherId,
			Integer cBatchId, String passPercentage) {
		
		User u=new User();
		u.setUserId(teacherId);
		CourseBatch cb=new CourseBatch();
		cb.setCourseBatchId(cBatchId);
		TargetPassPercentage tp=new TargetPassPercentage();
		 
		  Integer targetId=targetPassPercentageDao.getTargetIdFromTeacherCourseBatch(teacherId,cBatchId);
		  if(targetId==0)
		  {	  
			  tp.setUser(u);
			  tp.setCourseBatch(cb);
			  tp.setTarget(passPercentage);
		  
		   Integer value=targetPassPercentageDao.savetargetPassPercentageDetailsInDb(tp);

		  }
		  else
		  {
			  Integer passPercentageUpdation=targetPassPercentageDao.updatePassPercentageUpdation(teacherId,cBatchId,passPercentage);
			  
		  }
		
	}


	@Override
	public void saveViewersCount(int clazEventDetailId) {
		
	    	ClassEventDetail ce=new ClassEventDetail();
	    	ce.setClassEventDetailId(clazEventDetailId);
			TopPerformer tp=new TopPerformer();
			Integer value=topPerformerDao.getTopPerformerIdTeacher(clazEventDetailId);
			
			if(value==0)
			{
				tp.setClassEventDetail(ce);
				tp.setLikes(1);
				Integer Tperformance=topPerformerDao.saveUserRatingAboutTeacher(tp);
			}
			else
			{
				Integer rateCount=topPerformerDao.getTeacherRateCount(clazEventDetailId);
				Integer rateResult=(rateCount)+1;
				topPerformerDao.updateTheRateCount(clazEventDetailId,rateResult);
				
				
			}
	}


	@Override
	public void saveAttendancePassPercentageDetailsInDb(String teacherId,
			Integer cBatchId, String attendancePercentage) {
		
		User u=new User();
		u.setUserId(teacherId);
		CourseBatch cb=new CourseBatch();
		cb.setCourseBatchId(cBatchId);
		TargetAttendancePercentage tp=new TargetAttendancePercentage();
		 
		  Integer targetId=targetAttendancePercentageDao.getAttendanceIdFromTeacherCourseBatch(teacherId,cBatchId);
		  if(targetId==0)
		  {	  
			  tp.setUser(u);
			  tp.setCourseBatch(cb);
			  tp.setTargetAttendance(attendancePercentage);
		  
		   Integer value=targetAttendancePercentageDao.savetargetAttendancePercentageDetailsInDb(tp);

		  }
		  else
		  {
			  Integer passPercentageUpdation=targetAttendancePercentageDao.updateAttendancePercentageUpdation(teacherId,cBatchId,attendancePercentage);
			  
		  }
		
	}


	@Override
	public List<Properties> getAssignedAssignmentTask(String subject) {
		
		List<String> assignmentDetails=addAssignmentDao.getAssignedAssignmentTask(subject);
		List<Properties> assignmentList = new ArrayList<Properties>();
		for(int i=0;i<assignmentDetails.size();i++)
		{
			
			Properties prop = new Properties();
			
			String topic=assignmentDetails.get(i);

			//prop.put("assignmenttaskId", taskId);
			prop.put("assignmentTopic", topic);
			
			assignmentList.add(prop);
		}
//		teacher.add(temp);

		return assignmentList;	

	}


	@Override
	public Integer saveAssignAssignmentTasksInDb(String subId, String topic) {
		
		
		Subject sub=new Subject();
		sub.setSubjectId(subId);
		
		AddAssignment at=new AddAssignment();	
		
	//	at.setStatus(status);
//		Integer assignId=addAssignmentDao.getAssignmentIdFromSubject(subId,topic);
//		if(assignId==0)
//		{
		at.setSubject(sub);
		at.setTopic(topic);
		Integer value=addAssignmentDao.saveAssignAssignmentTasksInDb(at);
		
		if(value!=0)
		{
			return value;
		}
		else
		{
			return 0;
		}
	
		
		
		
	}

	@Override
	public Integer getAssignmentIdFromSubject(String subId, String topic) {
		Integer value=addAssignmentDao.getAssignmentIdFromSubjectTopic(subId,topic);
		return value;
		 
	}


	@Override
	public Integer getTaskIdFromSubjectTopic(String userId, String subId,
			String topic) {
		Integer value=assignedTaskDao.getTaskIdFromSubjectTopic(userId,subId,topic);
		return value;
		 
	}


	


	}

	

