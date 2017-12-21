package com.senzit.cyberclaz.server.model;


import java.util.Properties;

public class AttentionData {
	
	private static Properties AttentionData=new Properties();

	@SuppressWarnings("unchecked")
	public void putNewData(Integer CourseBatchId,int value)
	{
		//Integer templist =  AttentionData.get(value);
		Integer templist = (Integer) AttentionData.get(CourseBatchId);
		System.out.println("templistputNewDataattention"+templist);
		if(templist!=null)
		{
			removeData(CourseBatchId);
		
		}
		System.out.println("attentionModeValue"+value);
		AttentionData.put(CourseBatchId, value);
	}
	

	public void removeData(Integer CourseBatchId)
	{
		//System.out.println("attentionModeinremoveData"+value);
		Integer templist =  (Integer) AttentionData.get(CourseBatchId);
		if(templist!=null)
		{
		AttentionData.remove(templist);
		}
	}
	public void removeDataNew(Integer CourseBatchId)
	{
		AttentionData.remove(CourseBatchId);
		
	}
	
	public boolean checkData (Integer CourseBatchId,int value)
	{
		Integer templist = (Integer) AttentionData.get(CourseBatchId);
		System.out.println("attentionModeValueinCheckData"+value);
		
		if(value==templist)
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
		//return (Integer)AttentionData.get(CourseBatchId);
		Integer templist = (Integer) AttentionData.get(CourseBatchId);
		if(templist==null)return 0;
		return templist;
		
	}

}
