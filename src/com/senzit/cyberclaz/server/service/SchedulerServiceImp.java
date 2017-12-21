package com.senzit.cyberclaz.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.senzit.cyberclaz.server.model.ClassEventDetail;
import com.senzit.cyberclaz.server.model.ClazEventDetailDao;
import com.senzit.cyberclaz.server.model.CourseBatch;
import com.senzit.cyberclaz.server.model.Day;
import com.senzit.cyberclaz.server.model.Notification;
import com.senzit.cyberclaz.server.model.Period;
import com.senzit.cyberclaz.server.model.Schedule;
import com.senzit.cyberclaz.server.model.SchedulerDao;
import com.senzit.cyberclaz.server.model.SubjectTeacher;
import com.senzit.cyberclaz.server.model.SubjectTeacherDao;
import com.senzit.cyberclaz.server.model.TempSchedule;
import com.senzit.cyberclaz.server.model.TempScheduleDao;
import com.senzit.cyberclaz.server.model.User;

public class SchedulerServiceImp implements SchedulerService{

	static Logger log = Logger.getLogger(UserServicesImp.class.getName());
	private SchedulerDao schedulerDao;
	private ClazEventDetailDao clazEventdetailDao;

	private TempScheduleDao tempScheduleDao;
	


	public void setTempScheduleDao(TempScheduleDao tempScheduleDao) {
		this.tempScheduleDao = tempScheduleDao;
	}

	public void setSchedulerDao(SchedulerDao schedulerDao) {
		this.schedulerDao = schedulerDao;
	}
	
	public void setClazEventDetailDao(ClazEventDetailDao clazEventdetailDao) {

		this.clazEventdetailDao = clazEventdetailDao;
	}

	
	@Override
	public List<Properties> getFullSchedule(String userId) {
		

		List<Object[]> fullSchedule = schedulerDao.getFullScheduleTeacher(userId);
		
		List<Properties> fullScheduleTr = new ArrayList<Properties>();
		List<Properties> scheduleMon = new ArrayList<Properties>();
		List<Properties> scheduleTue = new ArrayList<Properties>();
		List<Properties> scheduleWed = new ArrayList<Properties>();
		List<Properties> scheduleThu = new ArrayList<Properties>();
		List<Properties> scheduleFri = new ArrayList<Properties>();
		List<Properties> scheduleSat = new ArrayList<Properties>();
		List<Properties> scheduleSun = new ArrayList<Properties>();
		
		System.out.println("TTTTTTttttttttttttttttttttttttt"+fullSchedule.size());
	
		for(int i=0;i<fullSchedule.size();i++)
		{
			Object[] ob = fullSchedule.get(i);
			
			Properties fullScheduleProp = new Properties();
			
			fullScheduleProp.put("scheduleId", ob[0]);
		//	fullScheduleProp.put("dayName", ob[1]);
			fullScheduleProp.put("period", ob[2]);
			fullScheduleProp.put("startTime", ob[3]);
			fullScheduleProp.put("endTime", ob[4]);
			fullScheduleProp.put("courseName", ob[5]);
			fullScheduleProp.put("semester", ob[6]);
			fullScheduleProp.put("batch", ob[7]);
			fullScheduleProp.put("subject", ob[8]);
			fullScheduleProp.put("classRoomNo", ob[9]);
			fullScheduleProp.put("subjectId", ob[10]);
			if(ob[1].equals("Mon"))
			{
				scheduleMon.add(fullScheduleProp);
			}
			if(ob[1].equals("Tue"))
			{
				scheduleTue.add(fullScheduleProp);
			}
			if(ob[1].equals("Wed"))
			{
				scheduleWed.add(fullScheduleProp);
			}
			if(ob[1].equals("Thu"))
			{
				scheduleThu.add(fullScheduleProp);
			}
			if(ob[1].equals("Fri"))
			{
				scheduleFri.add(fullScheduleProp);
			}
			if(ob[1].equals("Sat"))
			{
				scheduleSat.add(fullScheduleProp);
			}
			if(ob[1].equals("Sun"))
			{
				scheduleSun.add(fullScheduleProp);
			}
		
		}

		Properties DayScheduleProp = new Properties();
		DayScheduleProp.put("Mon", scheduleMon);
		DayScheduleProp.put("Tue", scheduleTue);
		DayScheduleProp.put("Wed", scheduleWed);
		DayScheduleProp.put("Thu", scheduleThu);
		DayScheduleProp.put("Fri", scheduleFri);
		DayScheduleProp.put("Sat", scheduleSat);
		DayScheduleProp.put("Sun", scheduleSun);
		fullScheduleTr.add( DayScheduleProp);

		return fullScheduleTr;


	}

	@Override
	public Integer getCourseBatchId(int scheduleId) {
		List<Integer> courseBatchId = schedulerDao.getCourseBatch(scheduleId);
		Integer courseBatch = 101;//Need to Change
		return courseBatch;
	}

	@Override
	public List<Properties> getFullTimeTable(Integer batchId) {
		
		List<Object[]> fullSchedule = schedulerDao.getFullScheduleStudent(batchId);
		List<Properties> fullScheduleSt = new ArrayList<Properties>();
		List<Properties> scheduleMon = new ArrayList<Properties>();
		List<Properties> scheduleTue = new ArrayList<Properties>();
		List<Properties> scheduleWed = new ArrayList<Properties>();
		List<Properties> scheduleThu = new ArrayList<Properties>();
		List<Properties> scheduleFri = new ArrayList<Properties>();
		List<Properties> scheduleSat = new ArrayList<Properties>();
		List<Properties> scheduleSun = new ArrayList<Properties>();
		for(int i=0;i<fullSchedule.size();i++)
		{
			Object[] ob = fullSchedule.get(i);

			Properties fullScheduleProp = new Properties();
			fullScheduleProp.put("teacherId", ob[0]);
			
			fullScheduleProp.put("teacherName", ob[1]);
			fullScheduleProp.put("scheduleId", ob[2]);
			//fullScheduleProp.put("day", ob[3]);
			fullScheduleProp.put("period", ob[4]);
			fullScheduleProp.put("startTime", ob[5]);
			fullScheduleProp.put("endTime", ob[6]);
			fullScheduleProp.put("subject", ob[7]);
			fullScheduleProp.put("profilePic", ob[8]);
			if(ob[3].equals("Mon"))
			{
				scheduleMon.add(fullScheduleProp);
			}
			if(ob[3].equals("Tue"))
			{
				scheduleTue.add(fullScheduleProp);
			}
			if(ob[3].equals("Wed"))
			{
				scheduleWed.add(fullScheduleProp);
			}
			if(ob[3].equals("Thu"))
			{
				scheduleThu.add(fullScheduleProp);
			}
			if(ob[3].equals("Fri"))
			{
				scheduleFri.add(fullScheduleProp);
			}
			if(ob[3].equals("Sat"))
			{
				scheduleSat.add(fullScheduleProp);
			}
			if(ob[3].equals("Sun"))
			{
				scheduleSun.add(fullScheduleProp);
			}

		}
		
		Properties DayScheduleProp = new Properties();
		DayScheduleProp.put("Mon", scheduleMon);
		DayScheduleProp.put("Tue", scheduleTue);
		DayScheduleProp.put("Wed", scheduleWed);
		DayScheduleProp.put("Thu", scheduleThu);
		DayScheduleProp.put("Fri", scheduleFri);
		DayScheduleProp.put("Sat", scheduleSat);
		DayScheduleProp.put("Sun", scheduleSun);
		fullScheduleSt.add( DayScheduleProp);

		return fullScheduleSt;

	}

	@Override
	public List<Integer> getScheduleIdOnly(String userId) {
		
		List<Object[]> fullSchedule = schedulerDao.getTodaysScheduleTeacher(userId);
		List<Integer> fullSchedulelist = new ArrayList<Integer>();
		for(int i=0;i<fullSchedule.size();i++)
		{
			Object[] ob = fullSchedule.get(i);
			int scheduleId =(Integer)ob[0];
			List<Object[]> endTime = clazEventdetailDao.checkForEndTime(scheduleId);
			
			if(endTime.isEmpty())
			{
				fullSchedulelist.add((Integer) (ob[0]));
		
			}
			
		}
			
		return fullSchedulelist;
	}

	@Override
	public List<Integer> getStudentScheduleIdOnly(int batchId) {
		
		List<Object[]> fullSchedule = schedulerDao.getTodaysScheduleStudent(batchId);
		List<Integer> fullSchedulelist = new ArrayList<Integer>();
		for(int i=0;i<fullSchedule.size();i++)
		{
			Object[] ob = fullSchedule.get(i);
			fullSchedulelist.add((Integer) ob[0]);
		}
			
		return fullSchedulelist;
	}

	@Override
	public List<Object[]> getTodaysScheduleDetails(int scheduleId) {
		
		List<Object[]> scheduleDetails=schedulerDao.getTodaysScheduleDetails(scheduleId);
		return scheduleDetails;
	}

//	@Override
//	public int saveTempScheduleDetails(String dayId, String periodId,
//			int subjectTeacherId, Integer courseBatchId, int scheduleId) {
//		
//		 Day day=new Day();
//		day.setDayId(dayId);
//	    Period p=new Period();
//	    p.setPeriodId(periodId);
//	    SubjectTeacher st=new SubjectTeacher();
//	    st.setSubjectTeacherId(subjectTeacherId);
//	    CourseBatch cb=new CourseBatch();
//	    cb.setCourseBatchId(courseBatchId);
//	    Schedule s=new Schedule();
//	    s.setScheduleId(scheduleId);
//	    TempSchedule ts=new TempSchedule();
//	    ts.setDay(day);
//	    ts.setPeriod(p);
//	    ts.setSubjectTeacher(st);
//	    ts.setCourseBatch(cb);
//	    ts.setSchedule(s);
//	    Integer tempScheduleId = tempScheduleDao.saveTempScheduleDetails(ts);
//		return tempScheduleId;
//		
//	}

	@Override
	public Integer getCourseBatchIdForTeacher(String rebbonId) {
		List<Integer> courseBatchId = schedulerDao.getCourseBatchIdForTeacher(rebbonId);
		Integer courseBatch = courseBatchId.get(0);
		return courseBatch;
	
	}

	@Override
	public String getDayandPeriod(String dayName) {
		
		List<String> idForCurrentDayandPeriod=schedulerDao.getDayandPeriod(dayName);
		String dayId=idForCurrentDayandPeriod.get(0);
		
		return dayId;
	}

//	@Override
//	public Integer saveTempScheduleDetails(String dayId, String periodId,
//			Integer subTeacherId, Integer courseBatchId, int scheduleId) {
	public Integer saveTempScheduleDetails(String dayId, String periodId,
			Integer subTeacherId, Integer courseBatchId) {	
		Day d=new Day();
		d.setDayId(dayId);
		Period p=new Period();
		p.setPeriodId(periodId);
		
		SubjectTeacher st=new SubjectTeacher();
		st.setSubjectTeacherId(subTeacherId);
		
		CourseBatch cb=new CourseBatch();
		cb.setCourseBatchId(courseBatchId);
//		Schedule s=new Schedule();
//		s.setScheduleId(0);
		TempSchedule ts=new TempSchedule();
		ts.setDay(d);
		ts.setPeriod(p);
		ts.setCourseBatch(cb);
		
		ts.setSubjectTeacher(st);
		Integer tempScheduleId = tempScheduleDao.saveTempScheduleDetails(ts);
		if(tempScheduleId!=0)
		{
			
			log.debug("temporary schedule details Saved Succesfully");
			return tempScheduleId;
		}
		else
		{
			log.debug("ERROR while saving tempschedule");
		   return 0;
		}	
		
	
	}

	@Override
	public Integer getSubTeacherId(String subjectId) {
		List<Integer> subTeacherId = schedulerDao.getSubTeacherId(subjectId);
		Integer subTeacher = subTeacherId.get(0);
		return subTeacher;
	}

	@Override
	public String getDayandPeriodSchedule(String currentMinute) {
		List<String> idForCurrentDayandPeriod=schedulerDao.getDayandPeriodSchedule(currentMinute);
		String periodId=idForCurrentDayandPeriod.get(0);
		
		return periodId;
	}


//		
//	    Day day=new Day();
//	    day.setDayId(dayId);
//	    Period p=new Period();
//	    p.setPeriodId(periodId);
//	    SubjectTeacher st=new SubjectTeacher();
//	    st.setSubjectTeacherId(teacherId);
//	    
//		ClassEventDetail eventObj = new ClassEventDetail();
//		eventObj.setClassEventDetailId(classEventDetailId);
//		String ss="";
//		Notification notification = new Notification();
//		notification.setCreatedOn(reminderTime);
//		notification.setNotification(tempRnote);
//		notification.setFromUserId(teacherId);
//		notification.setToUserId(teacherId);
//		notification.setClassEventDetail(eventObj);
//		notification.setReminderOrRaiseHandFlag("RN");
//
//		int notificationValue = notificationDao.saveReminderAsNotification(notification);
//		if(notificationValue!=0)
//		{
//			log.debug("Notification Saved Succesfully");
//		}
//		else
//		{
//			log.debug("Notification Error!!");
//		}
//	}

	
//    
	
//	



}
