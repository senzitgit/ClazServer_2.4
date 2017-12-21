package com.senzit.cyberclaz.server.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.senzit.cyberclaz.server.service.UserServicesImp;

public class SchedulerDaoImp implements SchedulerDao{

	static Logger log = Logger.getLogger(UserServicesImp.class.getName());
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getFullScheduleTeacher(String userId) 
	{

		//		String hql="SELECT s.scheduleId,d.dayName,p.periodId,p.startTime,p.endTime,c.courseName,sem.semName,b.batchName," +
		//				"sub.subjectName,cr.classRoomNo FROM User u,Day d,Period p,Batch b,Schedule s,SubjectTeacher st," +
		//				"CourseSubject cs,Subject sub,Course c,Semester sem,ClassRoom cr,CourseBatch cb WHERE st.batch=b " +
		//				"and s.day=d and s.period=p and cs.semester=sem and cs.course=c and cs.subject=sub " +
		//				" and s.courseBatch=cb and s.subjectTeacher=st and  st.courseSubject=cs and st.teacher=u and u.userId=:userId";

//		String hql="SELECT s.scheduleId,d.dayName,p.periodId,p.startTime,p.endTime,c.courseName,sem.semName,b.batchName," +
//				"sub.subjectName,cr.classRoomNo FROM User u,Day d,Period p,Batch b,Schedule s,SubjectTeacher st," +
//				"CourseSubject cs,Subject sub,Course c,Semester sem,ClassRoom cr,CourseBatch cb WHERE st.teacher=u " +
//				"and u.userId=:userId and s.subjectTeacher=st and s.day=d and s.period=p and st.courseSubject=cs " +
//				"and cs.semester=sem and cs.course=c and cs.subject=sub and st.batch=b and s.courseBatch=cb and cb.classRoom=cr ";
		
		String hql="SELECT s.scheduleId,d.dayName,p.periodId,p.startTime,p.endTime,c.courseName,sem.semName,b.batchName," +
				"sub.subjectName,cr.classRoomNo,sub.subjectId FROM User u,Day d,Period p,Batch b,Schedule s,SubjectTeacher st," +
				"CourseSubject cs,Subject sub,Course c,Semester sem,ClassRoom cr,CourseBatch cb WHERE st.teacher=u " +
				"and u.userId=:userId and s.subjectTeacher=st and s.day=d and s.period=p and st.courseSubject=cs " +
				"and cs.semester=sem and cs.course=c and cs.subject=sub and st.batch=b and s.courseBatch=cb and cb.classRoom=cr ";
		
//  List mylist =  sessionFactory.getCurrentSession()
//			.createQuery(hql)
//			.setParameter("userId", userId)
//			.list();
//		
//
//   @SuppressWarnings("unchecked")
//Iterator<String> iterator = mylist.iterator();
//      
//   System.out.print("no elementdTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+mylist.size());
//   while (iterator.hasNext()) {
//	System.out.println("List Iterator : " +iterator.next()); 
//   }
//		
//      
//      
//      return mylist;
		
		return sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("userId", userId)
				.list();
     
		

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getCourseBatch(int scheduleId) {

		String hql="select cb.courseBatchId from CourseBatch cb,Schedule s where s.courseBatch=cb and s.scheduleId=:scheduleId ";
	//	String hql="select cb.courseBatchId from CourseBatch cb,Schedule s,TempSchedule ts where s.courseBatch=ts.courseBatch and ts.schedule=s and s.scheduleId=:scheduleId ";
		return sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("scheduleId", scheduleId)
				.list();



	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getFullScheduleStudent(Integer batchId) {


		String hql="SELECT u.userId,u.firstName,s.scheduleId,d.dayName,p.periodId,p.startTime,p.endTime," +
				"sub.subjectName,u.profilePic FROM User u,Day d,Period p,Schedule s,SubjectTeacher st," +
				"CourseSubject cs,Subject sub,CourseBatch cb WHERE s.day=d and s.period=p and cs.subject=sub " +
				" and s.courseBatch=cb and s.subjectTeacher=st and st.teacher=u and s.courseBatch=cb and st.courseSubject=cs " +
				"and cb.courseBatchId=:courseBatchId ";
//		
//		
//		String hql="SELECT u.userId,u.firstName,s.scheduleId,d.dayName,p.periodId,p.startTime,p.endTime," +
//				"sub.subjectName FROM User u,Day d,Period p,Schedule s,SubjectTeacher st," +
//				"CourseSubject cs,Subject sub,CourseBatch cb WHERE s.day=d and s.period=p and cs.subject=sub " +
//				" and s.courseBatch=cb and s.subjectTeacher=st and st.teacher=u and s.courseBatch=cb and st.courseSubject=cs " +
//				"and ORDER BY p.periodId ASC and cb.courseBatchId=:courseBatchId ";
//		
		return sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("courseBatchId", batchId)
				.list();


	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getTodaysScheduleTeacher(String userId) {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("E");
		String Currentday = sdf1.format(cal.getTime());


		String hql="SELECT s.scheduleId,d.dayName,p.periodId,p.startTime,p.endTime FROM User u,Day d,Period p,Schedule s,SubjectTeacher st WHERE st.teacher=u and u.userId=:userId and s.subjectTeacher=st and s.day=d and d.dayName=:dayName and s.period=p";

		return sessionFactory.getCurrentSession() 
				.createQuery(hql)
				.setParameter("userId", userId)
				.setParameter("dayName",Currentday)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getTodaysScheduleStudent(int batchId) {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("E");
		String Currentday = sdf1.format(cal.getTime());

		String hql="SELECT s.scheduleId,d.dayName,p.periodId FROM Day d,Period p,Schedule s," +
				"CourseBatch cb WHERE s.day=d and d.dayName=:dayName and s.period=p and s.courseBatch=cb " +
				"and cb.courseBatchId=:courseBatchId ";
		return sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("courseBatchId", batchId)
				.setParameter("dayName",Currentday)
				.list();
	}

//	@SuppressWarnings("unchecked")

//public int getNewSchedule(String teacherId, Integer subjectId,int CourseBatchId) {
//
//		String hql="SELECT s.scheduleId FROM Schedule s,CourseSubject cs,SubjectTeacher st,Subject sub,User u,CourseBatch cb WHERE s.subjectTeacher=st and st.courseSubject=cs and st.teacher=u and u.userId=:teacherId and cs.subject=sub and sub.subjectId=:subjectId and s.courseBatch = cb and cb.courseBatchId=:batchId";
/////////////////////////
//		int scheduleId=(int) sessionFactory.getCurrentSession()
//				.createQuery(hql)
//				.setParameter("teacherId", teacherId)
//				.setParameter("subjectId", subjectId.toString())
//				.setParameter("batchId", CourseBatchId)
//				.list().get(0);
//		return scheduleId;
//		/////////////////////
//
//	}
@SuppressWarnings("unchecked")
public List<Integer> getNewSchedule(String teacherId, String subjectId,Integer CourseBatchId) {
	
	System.out.println("#####################################################");
	System.out.println(teacherId+" "+subjectId+" "+CourseBatchId);
	
	/*String hql="SELECT s.scheduleId FROM Schedule s,CourseSubject cs,SubjectTeacher st,Subject sub,User u,CourseBatch cb WHERE" +
			" s.subjectTeacher=st and st.courseSubject=cs and st.teacher=u and u.userId=:teacherId and" +
			" cs.subject=sub and sub.subjectId=:subjectId and s.courseBatch = cb and cb.courseBatchId=:batchId";*/
	/*String hql="SELECT s.scheduleId FROM Schedule s,CourseSubject cs,SubjectTeacher st,Subject sub,User u,CourseBatch cb,Batch b WHERE" +
			" s.subjectTeacher=st and st.courseSubject=cs and st.teacher=u and u.userId=:teacherId and" +
			" cs.subject=sub and sub.subjectId=:subjectId and s.courseBatch = cb and cb.courseBatchId=:batchId";*/
	
	
	String hql="SELECT s.scheduleId FROM Schedule s,CourseBatch cb,SubjectTeacher st,CourseSubject cs,Subject sub,User u"
				+" WHERE cb.courseBatchId=:batchId and sub.subjectId=:subjectId and u.userId=:teacherId and"
				+" st.teacher=u and s.subjectTeacher=st and st.courseSubject=cs and cs.subject=sub and s.courseBatch=cb";
	
	System.out.println(sessionFactory.getCurrentSession()
			.createQuery(hql)
			.setParameter("teacherId", teacherId)
			.setParameter("subjectId", subjectId)
			.setParameter("batchId", CourseBatchId)
			.list());
	
	return sessionFactory.getCurrentSession()
			.createQuery(hql)
			.setParameter("teacherId", teacherId)
			.setParameter("subjectId", subjectId)
			.setParameter("batchId", CourseBatchId)
			.list();
	
}


@Override
public Integer saveScheduleDetails(String i, String j,String userId, int k) {
	
	User u=new User();
	u.setUserId(userId);
	Day d=new Day();
	d.setDayId(i);
	Period p=new Period();
	p.setPeriodId(j);
    SubjectTeacher st=new SubjectTeacher();
    st.setTeacher(u);
    CourseBatch cb=new CourseBatch();
	cb.setCourseBatchId(k);
	Schedule s=new Schedule();
    s.setCourseBatch(cb);
    s.setDay(d);
    s.setPeriod(p);
    s.setSubjectTeacher(st);
    return (Integer) sessionFactory.getCurrentSession().save(s); 


}


@SuppressWarnings("unchecked")
@Override
public List<Object[]> getTodaysScheduleDetails(int scheduleId) {
	
	String hql="SELECT d.dayId,p.periodId,st.subjectTeacherId FROM Day d,Period p,Schedule s," +
			"CourseBatch cb,SubjectTeacher st WHERE s.day=d and s.period=p and s.courseBatch=cb and s.subjectTeacher=st" +
			"and s.scheduleId=:scheduleId";
	return sessionFactory.getCurrentSession()
			.createQuery(hql)
			.setParameter("scheduleId", scheduleId)	
			.list();
}


@SuppressWarnings("unchecked")
@Override
public List<Integer> getCourseBatchIdForTeacher(String rebbonId) {
	
	
	String hql="select cb.courseBatchId from CourseBatch cb,ClassRoom cr,Rebbon r where cb.classRoom=r.classRoom and r.rebbonId=:rebbonId";
	
		return sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("rebbonId", rebbonId)
				.list();
}


@SuppressWarnings("unchecked")
@Override
public List<String> getDayandPeriod(String dayName) {
	
	//String hql="select d.dayId from Schedule s,Day d,Period p where s.day=d and s.period=p and s.scheduleId=:scheduleId";
	String hql="select d.dayId from Day d where d.dayName=:dayName";
	return sessionFactory.getCurrentSession()
			.createQuery(hql)
			.setParameter("dayName", dayName)
			.list();
}


@SuppressWarnings("unchecked")
@Override
public List<Integer> getSubTeacherId(String subjectId) {

	String hql="select st.subjectTeacherId from SubjectTeacher st,CourseSubject cs,Subject s,User u where" +
			" cs.subject=s and st.courseSubject=cs and st.teacher=u and s.subjectId=:subjectId";
	
	return sessionFactory.getCurrentSession()
			.createQuery(hql)
			.setParameter("subjectId", subjectId)
			.list();
}


@SuppressWarnings("unchecked")
@Override
public List<String> getDayandPeriodSchedule(String currentMinute) {
	
	System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+currentMinute);
	
String hql="select p.periodId from Period p where p.startTime<=:currentMinute and endTime>=:currentMinute";
	
	return sessionFactory.getCurrentSession()
			.createQuery(hql)
			.setParameter("currentMinute", currentMinute)
			.list();
}


@Override
public List<Object[]> getScheduleDetails() {
	 
	String hql="select d.dayName,p.startTime,p.endTime,u.userId,b.batchName,c.courseName,sem.semName,sub.subjectName,cr.classRoomNo " +
			"from Day d,Period p,User u,Batch b,Course c,Semester sem,Subject sub,ClassRoom cr,Schedule s,CourseBatch cb,SubjectTeacher st,CourseSubject cs where " +
			"s.day=d and s.period=p and s.subjectTeacher=st and st.teacher=u and st.courseSubject=cs and st.batch=cb.batch and cs.course=cb.course and cs.semester=cb.semester and cs.subject=sub and s.courseBatch=cb and cb.classRoom=cr";

	Query query = sessionFactory.getCurrentSession().createQuery(hql);
	List results = query.list();
	
	return results;	
}
}

