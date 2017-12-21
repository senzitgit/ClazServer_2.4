package com.senzit.cyberclaz.server.model;

import java.util.ArrayList;
import java.util.Properties;

public class AttachmentData {

	private static Properties AttachmentData=new Properties();

	@SuppressWarnings("unchecked")
	public void putNewData(int CourseBatchId,String attachmentName,String attachmentLink,String duration,String timestamp,String type)
	{

		Properties prop=new Properties();
		prop.put("timestamp", timestamp);
		prop.put("duration", duration);
		prop.put("attachmentName", attachmentName);
		prop.put("attachmentLink", attachmentLink);
		prop.put("documentType", type);
		
		ArrayList<Properties> list =new ArrayList<Properties>();
		if(!AttachmentData.isEmpty())
			list=(ArrayList<Properties>)AttachmentData.get(CourseBatchId);
		list.add(prop);
		AttachmentData.put(CourseBatchId, list);

	}

	public void removeData(int CourseBatchId)
	{
		AttachmentData.remove(CourseBatchId);
	}

	public boolean checkData (int CourseBatchId)
	{
		return AttachmentData.containsKey(CourseBatchId);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Properties> getData(int CourseBatchId)
	{
		return (ArrayList<Properties>)AttachmentData.get(CourseBatchId);
	}
}
