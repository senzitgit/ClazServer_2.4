package com.senzit.cyberclaz.server.model;

import java.sql.Timestamp;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class ClazEventDetailDaoImp implements ClazEventDetailDao{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> checkForLive(int courseBatchId) {

	
			String hql="select E.teacherId, E.teacherName, E.teacherPic, E.subjectName,E.clazFlag, E.classEventDetailId, E.startTime,E.topicName,E.chapterName from ClassEventDetail E where E.clazFlag = :clazFlag";
		
//
//			return sessionFactory.getCurrentSession()				
//					.createQuery(hql)// LIMIT 1
//					.setParameter("courseBatchId", courseBatchId)
//					.setMaxResults(1)
//					.list();
			System.out.println(sessionFactory.getCurrentSession()				
					.createQuery(hql)// LIMIT 1
					.setParameter("clazFlag", 0)	
					.list());
			
			return sessionFactory.getCurrentSession()			
					.createQuery(hql)// LIMIT 1
					.setParameter("clazFlag", 0)	
					.list();
			
	}
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> checkForLiveManual() {

	
			String hql="select E.classEventDetailId," +
					"E.startTime,E.topicName,E.endTime,E.chapterName from ClassEventDetail E ORDER BY E.startTime DESC";
		
//
//			return sessionFactory.getCurrentSession()				
//					.createQuery(hql)// LIMIT 1
//					.setParameter("courseBatchId", courseBatchId)
//					.setMaxResults(1)
//					.list();
			System.out.println(sessionFactory.getCurrentSession()				
					.createQuery(hql)// LIMIT 1				
					.list());
			
			return sessionFactory.getCurrentSession()			
					.createQuery(hql)// LIMIT 1
					.list();
			
	}
	
	
	
	
	
	
	
	
	

//	@Override
//	public Integer checkForLiveAfterCrash(Integer batchId)
//	{
//		String hql="select E.classEventDetailId from ClassEventDetail E,Schedule s,CourseBatch cb where E.schedule=s " +
//				"and s.courseBatch=cb and cb.courseBatchId =:courseBatchId ORDER BY E.startTime DESC";
//		return (Integer) sessionFactory.getCurrentSession()			
//				.createQuery(hql)// LIMIT 1
//				.setParameter("courseBatchId", batchId).list().get(0);
//		
//	}
	@Override
	public int saveNewClassEvent(ClassEventDetail classEvent) {

		return (Integer)sessionFactory.getCurrentSession().save(classEvent);
	}



	@Override
	public int setAttachmentFlag(boolean flag,int classEventDetailId) {

		Query query = sessionFactory.getCurrentSession().createQuery("update ClassEventDetail E set E.attachmentFlag=:flag where classEventDetailId=:classEventDetailId");
		query.setParameter("classEventDetailId", classEventDetailId);
		query.setParameter("flag", flag);
		return query.executeUpdate();

	}

	@Override
	public ClassEventDetail getEventDetials(int classEventDetailId) {
		return (ClassEventDetail)sessionFactory.getCurrentSession().get(ClassEventDetail.class, classEventDetailId);

	}

	@Override
	public int updateDB(int classEventDetailId, String generalLog,int totalAttendees) 
	{
		
//		byte[] buff = generalLog.getBytes();
//		sessionFactory.getCurrentSession().getLobHelper().createBlob(buff)
		System.out.println("General Log DB saving query:::::"+generalLog);
		
		Query query = sessionFactory.getCurrentSession().createQuery("update ClassEventDetail E set E.generalLog=:generalLog,E.endTime=:endTime,E.totalAttendees=:totalAttendees,E.clazFlag=:clazFlag where classEventDetailId=:classEventDetailId");
		query.setParameter("classEventDetailId", classEventDetailId);
		query.setParameter("generalLog", generalLog);
		query.setParameter("endTime", new Timestamp(System.currentTimeMillis()));
		query.setParameter("totalAttendees", totalAttendees);
		query.setParameter("clazFlag", 1);

		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getDateOfEvent(String subjectId) 
	{

//		String hql="select E.startTime,E.classEventDetailId,E.topicName, from ClassEventDetail E,Schedule s," +
//				"SubjectTeacher st,CourseSubject cs,Subject sub where st.courseSubject=cs and cs.subject=sub " +
//				"and sub.subjectId=:subjectId and s.subjectTeacher=st and E.schedule=s" ;
	
		

			String hql="select E.startTime,E.classEventDetailId,E.courseName,E.topicName,E.teacherName,E.subjectName from ClassEventDetail E" +
					" where E.subjectName=:subjectId" ;
			return sessionFactory.getCurrentSession()				
					.createQuery(hql)
					.setParameter("subjectId", subjectId)
					.list();
	
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> checkForEndTime(Integer scheduleId) {
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY-MM-dd");
		String CurrentDate = sdf1.format(cal.getTime());
		
		String hql="select E.startTime,E.classEventDetailId,E.endTime from ClassEventDetail E,Schedule s where E.schedule=s " +
				"and s.scheduleId=:scheduleId and str(E.startTime) LIKE :currentDate ORDER BY E.startTime DESC" ;

		return sessionFactory.getCurrentSession()				
				.createQuery(hql)
				.setParameter("scheduleId", scheduleId)
				.setParameter("currentDate", CurrentDate+'%')
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getDateOfEvent(String subjectId, Integer batchId) {
		

		
	
			String hql="select E.startTime,E.classEventDetailId,C.courseName,E.topicName,U.firstName,sub.subjectName from Course C,ClassEventDetail E,Schedule s," +
					"User U,SubjectTeacher st,CourseSubject cs,Subject sub,CourseBatch cb where cs.course=cb.course and st.teacher=U and st.courseSubject=cs and cs.subject=sub " +
					"and sub.subjectId=:subjectId and s.subjectTeacher=st and E.schedule=s and cs.course=cb.course " +
					"and cs.semester =cb.semester and cb.courseBatchId=:batchId " ;
			return sessionFactory.getCurrentSession()				
					.createQuery(hql)
					.setParameter("subjectId", subjectId)
					.setParameter("batchId", batchId)
					.list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getClassDetails(int clazEventDetailId) {
		
		String hql="select s.scheduleId,c.generalLog,c.chapterName,c.topicName," +
				"c.ftpLocation,c.attachmentFlag,c.startTime,c.endTime,c.totalAttendees from Schedule s," +
				"ClassEventDetail c where c.schedule=s and c.classEventDetailId=:classEventDetailId";

			  return sessionFactory.getCurrentSession()    
			    .createQuery(hql)
			    .setParameter("classEventDetailId", clazEventDetailId)
			    .list();
	}
///////////////////////////////////////////////////////////////////
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getClassEventDetail(String date,int batchId) {
		
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		  Date date1=null;
		  try {
		   if(!date.isEmpty())date1= formatter.parse(date);
		  } catch (ParseException e) {
		   e.printStackTrace();
		  }
		  
		  Calendar cal=Calendar.getInstance();
		  cal.setTime(date1);
		  cal.add(Calendar.DATE, 1);
		  Date date2=cal.getTime();
	  
		
			  String hql="select c.classEventDetailId,cr.courseName,u.firstName,sub.subjectName,c.topicName,c.startTime from"
						 +" User u,Course cr, ClassEventDetail c,Schedule s, Subject sub,SubjectTeacher st,CourseSubject cs, CourseBatch cb"
						    + " where cs.course=cb.course and st.teacher=u and c.schedule=s and s.subjectTeacher=st and st.courseSubject=cs and cs.subject=sub and" +
						    " c.startTime>=:date and c.endTime<:date2  and " +
						    " s.courseBatch=cb and cb.courseBatchId=:batchId";
						  return sessionFactory.getCurrentSession()    
						       .createQuery(hql)
						       .setParameter("date", date1)
						       .setParameter("date2", date2)
						       .setParameter("batchId", batchId)
						       .list();
		  
	}
	////////////////////////////////////////////////////////////

//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Object[]> getClassEventDetailFromTopic(String topic, int batchId) {
//		
//		String hql="select c.classEventDetailId,u.firstName,sub.subject,c.startTime from ClassEventDetail c,User u,Schedule s,"
//  +" Subject sub ,SubjectTeacher st ,CourseSubject cs,CourseBatch cb where c.schedule=s and s.subjectTeacher=st and st.courseSubject=cs" +
//  " and cs.subject=sub and st.user=u and c.topicName=:topicName and cb.courseBatchId=:batchId";
//		return sessionFactory.getCurrentSession()    
//			       .createQuery(hql)
//			       .setParameter("topicName", topic)
//			       .setParameter("batchId", batchId)
//			       .list();
//	}
	
	@SuppressWarnings("unchecked")
	 public List<Object[]> getClassEventDetailFromTopic(String topicName,String topicName1,int batchId) {
	  
//	  String hql="select c.classEventDetailId,sub.subjectName,c.topicName,c.startTime from"
//	    +" ClassEventDetail c,Schedule s, Subject sub,SubjectTeacher st,CourseSubject cs, CourseBatch cb"
//	    + " where c.schedule=s and s.subjectTeacher=st and st.courseSubject=cs and cs.subject=sub and"
//	    +" s.courseBatch=cb and cb.courseBatchId=:batchId and str(c.topicName) LIKE :topic";
	  
	  String hql="select c.classEventDetailId,u.firstName,cr.courseName,c.scheduleFlag.subjectName,c.topicName,c.startTime from"
			    +" User u,Course cr,ClassEventDetail c,Schedule s, Subject sub,SubjectTeacher st,CourseSubject cs, CourseBatch cb"
			    + " where cs.course=cb.course and st.teacher=u and c.schedule=s and s.subjectTeacher=st and st.courseSubject=cs and cs.subject=sub and"
			    +" s.courseBatch=cb and cb.courseBatchId=:batchId and str(c.topicName) LIKE :topic  and str(c.topicName) LIKE :topic1 ";
	  
	  return sessionFactory.getCurrentSession()    
	       .createQuery(hql)
	       .setParameter("batchId",batchId)
	       .setParameter("topic", "%"+topicName+"%")
	      .setParameter("topic1", "%"+topicName1+"%")
	       .list();
	 }
	 
	 @SuppressWarnings("unchecked")
	public List<Object[]> getClassEventDetailForTopic(String topic,int batchId)
	
	 {
		 
		
			  String hql="select c.classEventDetailId,u.firstName,cr.courseName,c.scheduleFlag,c.topicName,c.startTime from"
					    +" User u,Course cr,ClassEventDetail c,Schedule s, Subject sub,SubjectTeacher st,CourseSubject cs, CourseBatch cb"
					    + " where cs.course=cb.course and st.teacher=u and c.schedule=s and s.subjectTeacher=st and st.courseSubject=cs and cs.subject=sub and"
					    +" s.courseBatch=cb and cb.courseBatchId=:batchId and str(c.topicName) LIKE :topic ";
			  
			  return sessionFactory.getCurrentSession()    
			       .createQuery(hql)
			       .setParameter("batchId",batchId)
			       .setParameter("topic", "%"+topic+"%")
			       .list();
		
	 }
	 
	 @SuppressWarnings("unchecked")
	 @Override
	 public List<Object[]> getClassEventDetailFromDateSubject(String date, String subjectId,int batchId) {
	  
	
			 String hql="select c.classEventDetailId,cr.courseName,u.firstName,c.topicName,c.startTime,sub.subjectName from"
					    +" Course cr,ClassEventDetail c,User u,Schedule s, Subject sub,SubjectTeacher st,CourseSubject cs, CourseBatch cb"
					    + " where cs.course=cb.course and st.teacher=u and c.schedule=s and s.subjectTeacher=st and st.courseSubject=cs and cs.subject=sub and"
					    +" s.courseBatch=cb and cb.courseBatchId=:batchId and sub.subjectId=:subId and str(c.startTime) like :date";
					  return sessionFactory.getCurrentSession()    
					       .createQuery(hql)
					       .setParameter("batchId",batchId)
					       .setParameter("subId",subjectId)
					       .setParameter("date", "%"+date+"%")
					       .list();
		 
	 }
	 
	 @SuppressWarnings("unchecked")
	 @Override
	 public List<Object[]> getClassEventDetailFromSubjectTopic(String subjectId, String topic,int batchId) {
	  

			  String hql="select c.classEventDetailId,cr.courseName,u.firstName,c.topicName,c.startTime,sub.subjectName from"
					    +" Course cr,ClassEventDetail c,User u,Schedule s, Subject sub,SubjectTeacher st,CourseSubject cs, CourseBatch cb"
					    + " where cs.course=cb.course and st.teacher=u and c.schedule=s and s.subjectTeacher=st and st.courseSubject=cs and cs.subject=sub and"
					    +" s.courseBatch=cb and cb.courseBatchId=:batchId and str(c.topicName) LIKE :topic and sub.subjectId=:subId";
			  return sessionFactory.getCurrentSession()    
			       .createQuery(hql)
			       .setParameter("batchId",batchId)
			       .setParameter("subId",subjectId)
			       .setParameter("topic", "%"+topic+"%")
			       .list();
		
	 }
	 
	 
	 @SuppressWarnings("unchecked")
	 @Override
	 public List<Object[]> getClassEventDetailFromDateTopic(String date, String topic,int batchId) {
	  
	  String hql="select c.classEventDetailId,cr.courseName,u.firstName,sub.subjectName,c.topicName,c.startTime from"
	    +" Course cr,ClassEventDetail c,User u,Schedule s, Subject sub,SubjectTeacher st,CourseSubject cs, CourseBatch cb"
	    + " where cs.course=cb.course and st.teacher=u and c.schedule=s and s.subjectTeacher=st and st.courseSubject=cs and cs.subject=sub and"
	    +" s.courseBatch=cb and cb.courseBatchId=:batchId and str(c.topicName) like :topic and str(c.startTime) like :date";
	  return sessionFactory.getCurrentSession()    
	       .createQuery(hql)
	       .setParameter("batchId",batchId)
	       .setParameter("topic", "%"+topic+"%")
	       .setParameter("date", "%"+date+"%")
	       .list();
	 }
	
	 @SuppressWarnings("unchecked")
	 @Override
	 public List<Object[]> getClassEventDetailFromSDT(String date, String subjectId,
	   String topic,int batchId) {
	  
	  String hql="select c.classEventDetailId,u.firstName,cr.courseName,c.topicName,c.startTime,sub.subjectName from"
	    +" Course cr ,ClassEventDetail c,User u,Schedule s, Subject sub,SubjectTeacher st,CourseSubject cs, CourseBatch cb"
	    + " where cs.course=cb.course and st.teacher=u and c.schedule=s and s.subjectTeacher=st and st.courseSubject=cs and cs.subject=sub and"
	    +" s.courseBatch=cb and cb.courseBatchId=:batchId and sub.subjectId=:subId and str(c.topicName) like :topic"
	    + " and str(c.startTime) like :date";
	  return sessionFactory.getCurrentSession()    
	       .createQuery(hql)
	       .setParameter("batchId",batchId)
	       .setParameter("subId",subjectId)
	       .setParameter("topic", "%"+topic+"%")
	       .setParameter("date", "%"+date+"%")
	       .list();
	 }

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getClassEventDetail(String date) {
		
//		String hql="select c.classEventDetailId,sub.subjectName,c.topicName,c.startTime from"
//			    +" ClassEventDetail c,Schedule s, Subject sub,SubjectTeacher st,CourseSubject cs"
//			    + " where c.schedule=s and s.subjectTeacher=st and st.courseSubject=cs and cs.subject=sub and"
//			    +" str(c.startTime) like :date";

			  
	
		
		
		
		
		String hql="select E.classEventDetailId,E.courseName,E.subjectName,E.topicName,E.teacherName,E.startTime from ClassEventDetail E" +
						" where str(E.startTime) like :date" ;
					  return sessionFactory.getCurrentSession()    
					       .createQuery(hql)
					       .setParameter("date", "%"+date+"%")
					       .list();
		
	}

	@SuppressWarnings("unchecked")

	public List<Object[]> getClassEventDetailFromTopic(String topicName) {
		
		String hql="select E.classEventDetailId,E.subjectName,E.topicName,E.startTime,E.teacherName from ClassEventDetail E" +
				" where str(E.topicName) like :topic" ;

					  return sessionFactory.getCurrentSession()    
					       .createQuery(hql)
					       .setParameter("topic", "%"+topicName+"%")
					        
					       .list(); 
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getClassEventDetailFromDateSubject(String date,
			String subjectId) {
		
	
			 String hql="select E.classEventDetailId,E.courseName,E.topicName,E.startTime,E.teacherName,E.subjectName from ClassEventDetail E where E.subjectName=:subId and str(E.startTime) like :date";
					
			 
			 return sessionFactory.getCurrentSession()    
					       .createQuery(hql)
					       .setParameter("subId",subjectId)
					       .setParameter("date", "%"+date+"%")
					       .list();
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getClassEventDetailFromSubjectTopic(String subjectId,
			String topic) {
		
			 String hql="select E.classEventDetailId,E.courseName,E.topicName,E.startTime,E.teacherName,E.subjectName from ClassEventDetail E "
					    +"where str(E.topicName) LIKE :topic and E.subjectName=:subId";
			  return sessionFactory.getCurrentSession()    
			       .createQuery(hql)
			       .setParameter("subId",subjectId)
			       .setParameter("topic", "%"+topic+"%")
			       .list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getClassEventDetailFromDateTopic(String date,
			String topic) {
		
		String hql="select E.classEventDetailId,E.courseName,E.subjectName,E.topicName,E.startTime,E.teacherName from ClassEventDetail E where"
			    +" str(E.topicName) like :topic and str(E.startTime) like :date";
			  return sessionFactory.getCurrentSession()    
			       .createQuery(hql)
			       .setParameter("topic", "%"+topic+"%")
			       .setParameter("date", "%"+date+"%")
			       .list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getClassEventDetailFromSDT(String date,
			String subjectId, String topic) {
		
		String hql="select E.classEventDetailId,E.courseName,E.topicName,E.teacherName,E.startTime,E.subjectName from ClassEventDetail E where"
			    +" E.subjectName=:subId and str(E.topicName) like :topic and str(E.startTime) like :date";
			  
			  return sessionFactory.getCurrentSession()    
			       .createQuery(hql)
			       .setParameter("subId",subjectId)
			       .setParameter("topic", "%"+topic+"%")
			       .setParameter("date", "%"+date+"%")
			       .list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getClassStatus(int classEventDetailId) {
		
		String hql="select c.startTime,c.endTime from ClassEventDetail c where c.classEventDetailId=:classEventDetailId";

			  return sessionFactory.getCurrentSession()    
			    .createQuery(hql)
			    .setParameter("classEventDetailId", classEventDetailId)
			    .list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getDateOfEventForTempSchedule(String subjectId) {
		
		String hql="select E.startTime,E.classEventDetailId,cr.courseName,E.topicName,U.firstName,sub.subjectName from User U,ClassEventDetail E,TempSchedule s,Course cr," +
				"SubjectTeacher st,CourseSubject cs,Subject sub,CourseBatch cb where cb.course= cs.course and st.teacher=U and st.courseSubject=cs and cs.subject=sub " +
			"and sub.subjectId=:subjectId and s.subjectTeacher=st and E.schedule=s" ;
		return sessionFactory.getCurrentSession()				
				.createQuery(hql)
				.setParameter("subjectId", subjectId)
				.list();


	}
////////////////////////for manual recording//////////////////////////
	@Override
	public String getScheduleFlagForEventId(int classEventDetailId ) {
		
		String hql="select c.scheduleFlag from ClassEventDetail c where c.classEventDetailId=:classEventDetailId";

		  return (String) sessionFactory.getCurrentSession()    
		    .createQuery(hql)
		    .setParameter("classEventDetailId", classEventDetailId)
		    .list().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTeacherNameForTempSchedule(int eventId) {
		
		String hql="select cr.courseName,U.firstName,sub.subjectName from User U,ClassEventDetail E,TempSchedule s,Course cr," +
				"SubjectTeacher st,CourseSubject cs,Subject sub,CourseBatch cb where cb.course= cs.course and st.teacher=U and st.courseSubject=cs and cs.subject=sub " +
			"and sub.subjectId=:subjectId and s.subjectTeacher=st and E.schedule=s" ;
		return sessionFactory.getCurrentSession()				
				.createQuery(hql)
				.setParameter("classEventDetailId", eventId)
				.list();
	}
	

//	@Override
//	public Integer getNoOfTime(int clazEventDetailId) {
//		String hql="SELECT ce.noOfTimes FROM ClassEventDetail ce where ce.classEventDetailId=:classEventDetailId";
//
//				return (int)sessionFactory.getCurrentSession()
//						.createQuery(hql)
//						.setParameter("classEventDetailId", clazEventDetailId)
//		            	.list().size();
//	}
//
//	@Override
//	public Integer updateNoOfTimesInDB(int clazEventDetailId, Integer value) {
//		
//		Query query = sessionFactory.getCurrentSession().createQuery("update ClassEventDetail ce set ce.noOfTimes=:value where ce.classEventDetailId=:clazEventDetailId");
//		  query.setParameter("clazEventDetailId", clazEventDetailId);
//			query.setParameter("value", value);
//			return query.executeUpdate();
//		
//	
//	}


//	@Override
//	public int getCurrentEvent(int classEventDetailId) {
//		
//		String hql="SELECT ce.startTime FROM ClassEventDetail ce where ce.classEventDetailId=:classEventDetailId";
//
//		return (int)sessionFactory.getCurrentSession()
//				.createQuery(hql)
//				.setParameter("classEventDetailId", classEventDetailId)
//            	.list().get(0);
//
//		
//	}


}
