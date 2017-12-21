package com.senzit.cyberclaz.server.service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.senzit.cyberclaz.server.controller.UserController;

import com.senzit.cyberclaz.server.model.Batch;
import com.senzit.cyberclaz.server.model.BatchDao;
import com.senzit.cyberclaz.server.model.ClassRoom;
import com.senzit.cyberclaz.server.model.ClassRoomDao;
import com.senzit.cyberclaz.server.model.Course;
import com.senzit.cyberclaz.server.model.CourseBatch;
import com.senzit.cyberclaz.server.model.CourseBatchDao;
import com.senzit.cyberclaz.server.model.CourseDao;
import com.senzit.cyberclaz.server.model.CourseSubject;
import com.senzit.cyberclaz.server.model.CourseSubjectDao;
import com.senzit.cyberclaz.server.model.CyberInit;
import com.senzit.cyberclaz.server.model.Day;
import com.senzit.cyberclaz.server.model.DayDao;
import com.senzit.cyberclaz.server.model.MultimediaDao;
import com.senzit.cyberclaz.server.model.MultimediaLibrary;
import com.senzit.cyberclaz.server.model.Period;
import com.senzit.cyberclaz.server.model.PeriodDao;
import com.senzit.cyberclaz.server.model.SchedulerDao;
import com.senzit.cyberclaz.server.model.Semester;
import com.senzit.cyberclaz.server.model.SemesterDao;
import com.senzit.cyberclaz.server.model.StudentBatch;
import com.senzit.cyberclaz.server.model.StudentBatchDao;
import com.senzit.cyberclaz.server.model.Subject;
import com.senzit.cyberclaz.server.model.SubjectDao;
import com.senzit.cyberclaz.server.model.SubjectTeacher;
import com.senzit.cyberclaz.server.model.SubjectTeacherDao;
import com.senzit.cyberclaz.server.model.User;

public class PortalServiceImp implements PortalService {
	
	public PortalServiceImp() {}

	static Logger log = Logger.getLogger(UserController.class.getName());

	private CourseDao courseDao;
	private SemesterDao semesterDao;
	private BatchDao batchDao;
	private ClassRoomDao classRoomDao;
	private SubjectDao subjectDao;
	private DayDao dayDao;
	private PeriodDao periodDao;
	private CourseSubjectDao courseSubjectDao;
	private CourseBatchDao courseBatchDao;
	private StudentBatchDao studentBatchDao;
	private SubjectTeacherDao subjectTeacherDao;
	private MultimediaDao multimediaDao;
	private SchedulerDao schedulerDao;


	public void setSchedulerDao(SchedulerDao schedulerDao) {
		this.schedulerDao = schedulerDao;
	}

	public void setMultimediaDao(MultimediaDao multimediaDao) {
		this.multimediaDao = multimediaDao;
	}

	public void setSubjectTeacherDao(SubjectTeacherDao subjectTeacherDao) {
		this.subjectTeacherDao = subjectTeacherDao;
	}
	public void setStudentBatchDao(StudentBatchDao studentBatchDao) {
		this.studentBatchDao = studentBatchDao;
	}
    public void setCourseBatchDao(CourseBatchDao courseBatchDao) {
		this.courseBatchDao = courseBatchDao;
	}
    public void setCourseSubjectDao(CourseSubjectDao courseSubjectDao) {
		this.courseSubjectDao = courseSubjectDao;
	}
	public void setPeriodDao(PeriodDao periodDao) {
		this.periodDao = periodDao;
	}

	public void setDayDao(DayDao dayDao) {
		this.dayDao = dayDao;
	}

	public void setSubjectDao(SubjectDao subjectDao) {
		this.subjectDao = subjectDao;
	}

	public void setClassRoomDao(ClassRoomDao classRoomDao) {
		this.classRoomDao = classRoomDao;
	}

	public void setBatchDao(BatchDao batchDao) {
		this.batchDao = batchDao;
	}

	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	public void setSemesterDao(SemesterDao semesterDao) {
		this.semesterDao = semesterDao;
	}


	@Override
	public boolean saveCourseDetailsInDb(String courseName, String courseDesc,
			String courseCategory, String courseDuration, String currentScheme,
			String semOrYear, String department, String distantOrRegular) {
	
		Course course=new Course();
		course.setCourseName(courseName);
		course.setCourseDescription(courseDesc);
		course.setCourseCategory(courseCategory);
		course.setCourseDuration(courseDuration);
		course.setCurrentScheme(currentScheme);
		course.setDepartment(department);
		course.setSemOrYear(semOrYear);
		course.setDistantOrRegular(distantOrRegular);
		
		Integer value = courseDao.saveCourseDetailsInDb(course);
		if(value!=0)
		{
			log.debug("course details Saved");
			return true;
		}
		else
		{
			log.debug("error while saving the course details");
			return false;
		}	
		
		
		
	}

	@Override
	public boolean saveSemDetailsInDb(String semName) {
		
		Semester sem=new Semester();
		sem.setSemName(semName);
		Integer value = semesterDao.saveSemDetailsInDb(semName);
		if(value!=0)
		{
			log.debug("semester details Saved");
			return true;
		}
		else
		{
			log.debug("error while saving the course details");
			return false;
		}	
		
		
	}

	@Override
	public boolean saveBatchDetailsInDb(String batchName) {
		
		Batch batch=new Batch();
		batch.setBatchName(batchName);
		Integer value = batchDao.saveBatchDetailsInDb(batch);
		if(value!=0)
		{
			log.debug("batch details Saved");
			return true;
		}
		else
		{
			log.debug("error while saving the batch details");
			return false;
		}	
	}

	@Override
	public boolean saveClassRoomDetailsInDb(String classRoomNo) {
		
		ClassRoom cr=new ClassRoom();
		cr.setClassRoomNo(classRoomNo);
		Integer value = classRoomDao.saveClassRoomDetailsInDb(cr);
		if(value!=0)
		{
			log.debug("classroom details Saved");
			return true;
		}
		else
		{
			log.debug("error while saving the classroom details");
			return false;
		}	
	}

	@Override
	public boolean saveSubjectDetailsInDb(String subjectName) {
		
		Subject sub=new Subject();
		sub.setSubjectName(subjectName);
		Integer value = subjectDao.saveSubjectDetailsDetailsInDb(sub);
		if(value!=0)
		{
			log.debug("subject details Saved");
			return true;
		}
		else
		{
			log.debug("error while saving the subject details");
			return false;
		}	
		
	}

	@Override
	public boolean saveDayDetailsInDb(String dayName) {
		
		Day d=new Day();
		d.setDayName(dayName);
		
		Integer value = dayDao.saveDayDetailsInDb(d);
		
		if(value!=0)
		{
			log.debug("day details Saved");
			return true;
		}
		else
		{
			log.debug("error while saving the day details");
			return false;
		}	
		
	}

	@Override
	public boolean savePeriodDetailsInDb(String startTime, String endTime) {
		
		Period p=new Period();
		p.setStartTime(startTime);
		p.setEndTime(endTime);
        Integer value = periodDao.savePeriodDetailsInDb(p);
		
		if(value!=0)
		{
			log.debug("period details Saved");
			return true;
		}
		else
		{
			log.debug("error while saving the day details");
			return false;
		}	
	}

	@Override
	public boolean saveCourseSubjectDetailsInDb(String courseId, String semId,
			String subId) {
		
	Course cs=new Course();
	cs.setCourseName(courseId);
	Semester sem=new Semester();
	sem.setSemName(semId);
	Subject sub=new Subject();
	sub.setSubjectName(subId);
	CourseSubject cSubject=new CourseSubject();
	cSubject.setCourse(cs);
	cSubject.setSemester(sem);
	cSubject.setSubject(sub);
	
	Integer value = courseSubjectDao.saveCourseSubjectDetailsInDb(cSubject);	
	if(value!=0)
	{
		log.debug("course subject details Saved");
		return true;
	}
	else
	{
		log.debug("error while saving the course subject details");
		return false;
	}		
		
	}
	@Override
	public boolean saveCourseBatchDetailsInDb(String courseId, String semId,
			String batchId, String cRoomId) {
		Course cs=new Course();
		cs.setCourseName(courseId);
		Semester sem=new Semester();
		sem.setSemName(semId);
		Batch b=new Batch();
		b.setBatchName(batchId);
		ClassRoom cr=new ClassRoom();
		cr.setClassRoomNo(cRoomId);
		CourseBatch cb=new CourseBatch();
		cb.setCourse(cs);
		cb.setSemester(sem);
		cb.setBatch(b);
		cb.setClassRoom(cr);
		Integer value = courseBatchDao.saveCourseBatchDetailsInDb(cb);	
		if(value!=0)
		{
			log.debug("course batch details Saved");
			return true;
		}
		else
		{
			log.debug("error while saving the course batch details");
			return false;
		}		
	

}
	
	@Override
	public List<String> getCourseSemSubIdz(String cName, String sName,
			String subName) {
		
		List<String> idValues=courseDao.getCourseSemSubIdz(cName,sName,subName);
		
		return idValues;
	}
	@Override
	public List<String> getCourseSemBatchandClassRoomIdz(String cName,
			String sName, String bName, String cRoomNo) {
		
    List<String> idValuesForInputs=courseDao.getCourseSemBatchandClassRoomIdz(cName,sName,bName,cRoomNo);
		
		return idValuesForInputs;
		
	}
	@Override
	public int getCourseBatchId(String courseId, String semId, String batchId,
			String classRoomId) {
		//int courseBatchId=
	  return courseBatchDao.getCourseBatchId(courseId,semId,batchId,classRoomId);
		
		
	}
	@Override
	public boolean saveStudentBatchDetailsInDb(String studentId,
			int courseBatchId) {
		
		User u=new User();
		u.setUserId(studentId);
		CourseBatch cb=new CourseBatch();
		cb.setCourseBatchId(courseBatchId);
		StudentBatch sb=new StudentBatch();
		sb.setCourseBatch(cb);
		sb.setUser(u);
		Integer value = studentBatchDao.saveStudentBatchDetailsInDb(sb);	
		if(value!=0)
		{
			log.debug("student batch details Saved");
			return true;
		}
		else
		{
			log.debug("error while saving the student batch details");
			return false;
		}		
	

	}
	@Override
	public String getbatchIdFromBatchName(String bName) {
		
		String batchId= batchDao.getbatchIdFromBatchName(bName) ;
		 return batchId;
	}
	@Override
	public int getCourseSubjecIdForInputs(String courseId, String semId,
			String subId) {

      int courseSubjectId=courseSubjectDao.getCourseSubjecIdForInputs(courseId, semId, subId);
		return courseSubjectId;
	}
	@Override
	public boolean saveSubjectTeacherDetailsInDb(String teacherId,
			int courseSubjectId, String batchId) {
	
		User u=new User();
		u.setUserId(teacherId);
		CourseSubject cs=new CourseSubject();
		cs.setCourseSubjectId(courseSubjectId);
		Batch b=new Batch();
		b.setBatchId(batchId);
		SubjectTeacher st=new SubjectTeacher();
		st.setTeacher(u);
		st.setCourseSubject(cs);
		st.setBatch(b);
		Integer value = subjectTeacherDao.saveSubjectTeacherDetailsInDb(st);	
		if(value!=0)
		{
			log.debug("Subject teacher details Saved");
			return true;
		}
		else
		{
			log.debug("error while saving the Subject teacher details");
			return false;
		}		
	
	}
	@Override
	public List<Properties> getSubjectDetails() {
		
		List<String> subjects=subjectDao.getFullSubjectList();
		List<Properties> nullList = new ArrayList<Properties>();
		List<Properties> tempDate = new ArrayList<Properties>();
		if(!subjects.isEmpty())
		{
		for(int i=0;i<subjects.size();i++)
		{
			
			Properties prop=new Properties();
			prop.put("subjectName",subjects.get(i));
			tempDate.add(prop);
		}
		return tempDate;
		}
		else
		{
			return nullList;
		}
	}
	@Override

		public List<Properties> getAttachmentDetailsUploadedViaPortalForDisplay(
				String userId)
				{
			List<Object[]> attachmentDetails=multimediaDao.getAttachmentDetailsUploadedViaPortal(userId);
			List<Properties> attachments = new ArrayList<Properties>();
			for(int i=0;i<attachmentDetails.size();i++)
			{
				Object ob[] = attachmentDetails.get(i);
				Properties prop = new Properties();
				String  attachmentName=ob[0].toString();
				String  attachmentLink=ob[1].toString();
				String  attachmentDesc=ob[2].toString();
				String  attachmentType=ob[4].toString();
				String  attachmentId=ob[5].toString();
				Timestamp s=(Timestamp) ob[3];
		         SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd,HH:mm:ss");
				
				prop.put("attachmentName", attachmentName);
				prop.put("attachmentLink", attachmentLink);
				prop.put("attachmentDescription", attachmentDesc);
				prop.put("uploadedOn", sdf.format(s.getTime()));
				prop.put("attachmentType", attachmentType);
				prop.put("attachmentId", attachmentId);
				attachments.add(prop);
			}
//			teacher.add(temp);

			return attachments;	
				}
	@Override
	public void saveAttachmentDetailsUploadFromPortal(String userId, String name,String description, MultipartFile file1, String type) 
	{
	
		
		String webLink = null;
		if(file1!=null)
		{
		
		File file =  new File(CyberInit.webAppFolder+File.separatorChar+"ATTACHMENT"+File.separatorChar+file1.getOriginalFilename());

			try {
				file1.transferTo(file);
			} catch (IllegalStateException e) {
			
				e.printStackTrace();
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			 webLink = CyberInit.ipAddress+"/ATTACHMENT/"+file1.getOriginalFilename();
			
	
		
		User u=new User();
		u.setUserId(userId);
		MultimediaLibrary ml=new MultimediaLibrary();
		ml.setMediaName(name);
		ml.setMediaDescription(description);
		ml.setUploadedOn(new Timestamp(System.currentTimeMillis()));
		ml.setUser(u);
		ml.setFTPpath(webLink);
		ml.setType(type);
		
		Integer value = multimediaDao.saveAttachmentDetailsUploadFromPortal(ml);	
		if(value!=0)
		{
			log.debug("portal attachment details Saved");
		}
		else
		{
			log.debug("ERROR while saving portal attachment details");
		}
		
	
		}
	}
	@Override
	public boolean deleteAttachmentDetailsUploadFromPortal(int mediaId) {
		
		MultimediaLibrary ml=new MultimediaLibrary();
		ml.setMediaId(mediaId);
		multimediaDao.deleteAttachmentDetailsUploadFromPortal(ml);
		log.debug("deleted portal attachment details ");
		return true;
		
		
	}
	@Override
	public List<Properties> getDetailsAboutCourse() {
		
		List<Object[]> courseDetails=courseDao.getDetailsAboutCourse();
		List<Properties> nullList = new ArrayList<Properties>();
		List<Properties> courseDetailsofInstitute = new ArrayList<Properties>();
		if(!courseDetails.isEmpty())
		{
		for(int i=0;i<courseDetails.size();i++)
		{
			Object ob[] = courseDetails.get(i);
			Properties prop = new Properties();
			String  courseName=ob[0].toString();
			String  courseDescription=ob[1].toString();
			String  courseCategory=ob[2].toString();
			String  courseDuration=ob[3].toString();
			String  currentScheme=ob[4].toString();
			String  semOrYear=ob[5].toString();
			String  department=ob[6].toString();
			String  distantOrRegular=ob[7].toString();
			
			
			
			prop.put("courseName", courseName);
			prop.put("courseDescription", courseDescription);
			prop.put("courseCategory", courseCategory);
			prop.put("courseDuration", courseDuration);
			prop.put("currentScheme", currentScheme);
			prop.put("semOrYear", semOrYear);
			prop.put("department", department);
			prop.put("distantOrRegular", distantOrRegular);
			courseDetailsofInstitute.add(prop);
		}
//		teacher.add(temp);

		return courseDetailsofInstitute;	
	}
		else
		{
			return nullList;
		}
}
	@Override
	public List<Properties> getSemDetails() {
		
		List<String> sem=semesterDao.getFullSemesterList();
		List<Properties> nullList = new ArrayList<Properties>();
		List<Properties> tempDate = new ArrayList<Properties>();
		if(!sem.isEmpty())
		{
		for(int i=0;i<sem.size();i++)
		{
			
			Properties prop=new Properties();
			prop.put("semName",sem.get(i));
			tempDate.add(prop);
		}
		return tempDate;
		}
		else
		{
			return nullList;
		}
	}
	@Override
	public List<Properties> getBatchDetails() {
		
		List<String> batch=batchDao.getBatchDetails();
		List<Properties> nullList = new ArrayList<Properties>();
		List<Properties> tempDate = new ArrayList<Properties>();
		if(!batch.isEmpty())
		{
		for(int i=0;i<batch.size();i++)
		{
			
			Properties prop=new Properties();
			prop.put("batchName",batch.get(i));
			tempDate.add(prop);
		}
		return tempDate;
		}
		else
		{
			return nullList;
		}
	}
	@Override
	public List<Properties> getClassRoomDetails() {
		
		List<String> classRoom=classRoomDao.getClassRoomDetails();
		List<Properties> nullList = new ArrayList<Properties>();
		List<Properties> tempDate = new ArrayList<Properties>();
		if(!classRoom.isEmpty())
		{
		for(int i=0;i<classRoom.size();i++)
		{
			
			Properties prop=new Properties();
			prop.put("classRoomNo",classRoom.get(i));
			tempDate.add(prop);
		}
		return tempDate;
		}
		else
		{
			return nullList;
		}
	}
	@Override
	public List<Properties> getDayDetails() {
		
		List<String> dayValue=dayDao.getDayDetails();
		List<Properties> nullList = new ArrayList<Properties>();
		List<Properties> tempDate = new ArrayList<Properties>();
		if(!dayValue.isEmpty())
		{
		for(int i=0;i<dayValue.size();i++)
		{
			
			Properties prop=new Properties();
			prop.put("dayName",dayValue.get(i));
			tempDate.add(prop);
		}
		return tempDate;
		}
		else
		{
			return nullList;
		}
	}
	@Override
	public List<Properties> getPeriodDetails() {
		
		List<Object[]> periodValue=periodDao.getPeriodDetails();
		List<Properties> nullList = new ArrayList<Properties>();
		List<Properties> tempDate = new ArrayList<Properties>();
		if(!periodValue.isEmpty())
		{
		for(int i=0;i<periodValue.size();i++)
		{
			Object ob[] = periodValue.get(i);
			Properties prop=new Properties();
			prop.put("startTime",ob[0]);
			prop.put("endTime",ob[1]);
			tempDate.add(prop);
		}
		return tempDate;
		}
		else
		{
			return nullList;
		}
	}
	@Override
	public List<Properties> getStudentBatchDetails() {
		
		
		List<Object[]> studentBatch=studentBatchDao.getStudentBatchDetails();
		List<Properties> nullList = new ArrayList<Properties>();
		List<Properties> tempDate = new ArrayList<Properties>();
		if(!studentBatch.isEmpty())
		{
		for(int i=0;i<studentBatch.size();i++)
		{
			Object ob[] = studentBatch.get(i);
			Properties prop=new Properties();
			prop.put("studentId",ob[0]);
			int cbId=(int) ob[1];
			System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+cbId);
			List<Object[]> courseBatchDetails=courseBatchDao.getCourseBatchDetails(cbId);
			
			System.out.println("arraySize"+courseBatchDetails.size());
			if(!courseBatchDetails.isEmpty())
			{	
				System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTSSSSSSSSSSSSSSSSSSSSSSSSSSSS");
			
			for(int j=0;j<courseBatchDetails.size();j++)
			{
				
			Object ob1[] = courseBatchDetails.get(j);	
			String courseName=ob1[0].toString();
			String semName=ob1[1].toString();
			String batchName=ob1[2].toString();
			String classRoom=ob1[3].toString();
			//System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTSSSSSSSSSSSSSSSSSSSSSSSSSSSS"+courseName);
			prop.put("courseName",courseName);
			prop.put("semName",semName);
			prop.put("batchName",batchName);
			prop.put("classRoom",classRoom);
			
			}
			}
			tempDate.add(prop);
		}
		return tempDate;
		}
		else
		{
			return nullList;
		}
	}
	@Override
	public List<Properties> getcourseBatchDetails() {
		
		List<Object[]> courseBatch=courseBatchDao.getcourseBatchDetails();
		List<Properties> nullList = new ArrayList<Properties>();
		List<Properties> tempDate = new ArrayList<Properties>();
		if(!(courseBatch.isEmpty()))
		{
			for(int i=0;i<courseBatch.size();i++)
			{
				Object ob[] = courseBatch.get(i);
				Properties prop=new Properties();
				String courseName=ob[0].toString();
				String semName=ob[1].toString();
				String batchName=ob[2].toString();
				String classRoomNo=ob[3].toString();
				prop.put("courseName",courseName);
				prop.put("semName",semName);
				prop.put("batchName",batchName);
				prop.put("classRoomNo",classRoomNo);
				tempDate.add(prop);
			}
			return tempDate;
		}
			else
			{
				return nullList;
			}
		
	}
	@Override
	public List<Properties> getSubjectTeacherDetails() {
		
		List<Object[]> subjectTeacherDetails=subjectTeacherDao.getSubjectTeacherDetails();
		List<Properties> nullList = new ArrayList<Properties>();
		List<Properties> tempDate = new ArrayList<Properties>();
		if(!(subjectTeacherDetails.isEmpty()))
		{
			for(int i=0;i<subjectTeacherDetails.size();i++)
			{
	
				Object ob[] = subjectTeacherDetails.get(i);
				Properties prop=new Properties();
				
				String userId=ob[0].toString();
				String courseName=ob[1].toString();
				String semName=ob[2].toString();
				String subjectName=ob[3].toString();
				String batchName=ob[4].toString();
			
				prop.put("TeacherId",userId);
				prop.put("courseName",courseName);
				prop.put("semName",semName);
				prop.put("subjectName",subjectName);
				prop.put("batchName",batchName);
				
				tempDate.add(prop);
			}
			return tempDate;
		}
			else
			{
				return nullList;
			}
	}
	@Override
	public List<Properties> getcourseSubjectDetailsFromDb() {
		
		System.out.println("COURSESUBJECTDETAILSSSSSSSSSSSSSSSSSSSS");
		
		List<Object[]> courseSubjectDetails=courseSubjectDao.getcourseSubjectDetailsFromDb();
		List<Properties> nullList = new ArrayList<Properties>();
		List<Properties> tempDate = new ArrayList<Properties>();
		if(!(courseSubjectDetails.isEmpty()))
		{
			for(int i=0;i<courseSubjectDetails.size();i++)
			{
	
				Object ob[] = courseSubjectDetails.get(i);
				Properties prop=new Properties();
		
				String courseName=ob[0].toString();
				String semName=ob[1].toString();
				String subjectName=ob[2].toString();
		
			
				prop.put("courseName",courseName);
				prop.put("semName",semName);
				prop.put("subjectName",subjectName);
			
				
				tempDate.add(prop);
			}
			return tempDate;
		}
			else
			{
				return nullList;
			}
	}
	@Override
	public List<Properties> getScheduleDetails() {
		
		List<Object[]> scheduleDetails=schedulerDao.getScheduleDetails();
		
		List<Properties> nullList = new ArrayList<Properties>();
		List<Properties> tempDate = new ArrayList<Properties>();
		if(!(scheduleDetails.isEmpty()))
		{
			for(int i=0;i<scheduleDetails.size();i++)
			{
				Object ob[] = scheduleDetails.get(i);
				Properties prop=new Properties();
		
				String dayName=ob[0].toString();
				String startTime=ob[1].toString();
				String endTime=ob[2].toString();
				String userId=ob[3].toString();
				String batchName=ob[4].toString();
				String courseName=ob[5].toString();
				
				String semName=ob[6].toString();
				String subjectname=ob[7].toString();
				String classRoomNo=ob[8].toString();
				
				prop.put("dayName",dayName);
				prop.put("startTime",startTime);
				prop.put("endTime",endTime);
				
				prop.put("teacherId",userId);
				prop.put("course",courseName);
				prop.put("semester",semName);
				
				prop.put("batch",batchName);
				prop.put("classRoom",classRoomNo);
				prop.put("subject",subjectname);
				
				tempDate.add(prop);
			}
			return tempDate;
		}
			else
			{
				return nullList;
			}
	}
	
}

