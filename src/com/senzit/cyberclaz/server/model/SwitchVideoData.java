package com.senzit.cyberclaz.server.model;

import java.util.Properties;

public class SwitchVideoData {
	
	private static Properties SwitchData=new Properties();
	
////////////////////////////Change all the condition with 0 instead of null checking////////////////
	
	@SuppressWarnings("unchecked")
	public void putNewData(Integer CourseBatchId,Integer switchFlag)
	{
		//Integer templist =  AttentionData.get(value);
		Integer templist = (Integer) SwitchData.get(CourseBatchId);
		System.out.println("templistputNewDataSwitch"+templist);
		if(templist!=null)
		//if(templist!=0)
		{
			removeData(CourseBatchId);
		
		}
		System.out.println("camFeedValue"+switchFlag);
		SwitchData.put(CourseBatchId, switchFlag);
	}

	public void removeData(Integer CourseBatchId)
	{
		//System.out.println("attentionModeinremoveData"+value);
		Integer templist =  (Integer) SwitchData.get(CourseBatchId);
		if(templist!=null)
		//if(templist!=0)
		{
			SwitchData.remove(templist);
		}
	}
	public void removeDataNew(Integer CourseBatchId)
	{	
			SwitchData.remove(CourseBatchId);
		
	}
	public boolean checkData (Integer CourseBatchId,Integer switchFlag)
	{
		Integer templist = (Integer) SwitchData.get(CourseBatchId);
	
		System.out.println("attentionModeValueinCheckData"+switchFlag);
		
		if(switchFlag==templist)
		{
		return true;
		}
		else
		{
		return false;
		}
	}
	public Integer getData(Integer CourseBatchId)
	{
		Integer templist= (Integer) SwitchData.get(CourseBatchId);
        if(templist==null)return 0;
		return templist;
	}


}
