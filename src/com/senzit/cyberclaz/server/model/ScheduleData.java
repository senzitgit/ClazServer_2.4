package com.senzit.cyberclaz.server.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ScheduleData {

	private static Properties scheduleData=new Properties();
	@SuppressWarnings("unchecked")
	public void putNewData(String userId,List<Integer> scheduleId)
	{

		//		Properties prop=new Properties();

		//		prop.put("scheduleId", scheduleId);

		//		ArrayList<Properties> templist = (ArrayList<Properties>) scheduleData.get(userId);;

		ArrayList<Integer> templist = (ArrayList<Integer>) scheduleData.get(userId);

		if(templist!=null)
		{
			removeData(userId);
		
		}
		
		scheduleData.put(userId, scheduleId);
		//		}

		System.out.println("SSSSSSSSSTTTTTTTTTTTTTTTTTeacher "+scheduleData.toString());

	}
	@SuppressWarnings("unchecked")
	public void putNewDataStud(Integer courseBatchId,List<Integer> scheduleId)
	{
      
		
		ArrayList<Integer> templist = (ArrayList<Integer>) scheduleData.get(courseBatchId);
		
		if(templist != null ){//|| templist.isEmpty()
			removeDataStud(courseBatchId);
			//scheduleData.put(courseBatchId, scheduleId);
		}

//		if(templist!=null )
//		{
//			//scheduleData.remove(courseBatchId);
//			//removeDataStud(courseBatchId);
//			
//			//removeData(courseBatchId);
//			//			templist.add(prop);
//			//			scheduleData.put(userId, templist);
//			//			templist.addAll(scheduleId);
//			//			scheduleData.put(userId, templist);
//
//		}
//		//		else
//		//		{
//		//			ArrayList<Properties> list =new ArrayList<Properties>();
//		//			list.add(prop);
//		//			scheduleData.put(userId, list);
		scheduleData.put(courseBatchId, scheduleId);
//		//		}
		System.out.println("SSSSSSSSSTTTTTTTTTTTTTTTTStudent "+scheduleData.toString());
		
		

	}
	@SuppressWarnings("unchecked")
	public void removescheduleIdStud(Integer scheduleId,Integer courseBatchId)
	{
		ArrayList<Integer> newList = (ArrayList<Integer>)scheduleData.get(courseBatchId);
		
		////////////////////////////
		System.out.println("ScheduleData : "+scheduleData.toString());
		System.out.println("scheduledataStudent"+newList);
		
		////////////////////////////
               if(!(newList==null)){
				for(int i=0;i<newList.size();i++)
				{
					Integer listSchedule =newList.get(i);

					if(listSchedule==scheduleId)
					{
						newList.remove(i);
				
				        System.out.println("removing from student.....");
                	}

              }
				System.out.println("newlist"+newList);
				
				
				putNewDataStud(courseBatchId,newList);
				

				System.out.println("NewScheduleData : "+scheduleData.toString());

               }   


	}
	public void removeData(String userId)
	{
		scheduleData.remove(userId);
	}
	public void removeDataStud(Integer courseBatchId)
	{
		scheduleData.remove(courseBatchId);
	}

	public boolean checkData (String userId)
	{
		return scheduleData.containsKey(userId);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Integer> getData(String userId)
	{
		return (ArrayList<Integer>)scheduleData.get(userId);
		
	}
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> getDataone(Integer courseBatchId)
	{
		//return (ArrayList<Integer>)scheduleData.get(courseBatchId);
		ArrayList<Integer> templist = (ArrayList<Integer>) scheduleData.get(courseBatchId);
		System.out.println("scheduleDataDuring Get req "+scheduleData.toString());
		System.out.println("scheduleDataDuring Get req "+templist);
		return templist;
	}
	@SuppressWarnings("unchecked")
	public void removescheduleId(Integer scheduleId,String userId){

		ArrayList<Integer> newList = (ArrayList<Integer>)scheduleData.get(userId);

       System.out.println("scheduledataTeacher"+newList);
		for(int i=0;i<newList.size();i++)
		{
			Integer listSchedule =newList.get(i);

			if(listSchedule==scheduleId)
			{
				newList.remove(i);
				System.out.println("removing from teacher.....");
			}

		}
		//
		//removeData(userId);
		//
		System.out.println("newlist"+newList);
		putNewData(userId,newList);


	}
	

}
