package com.senzit.cyberclaz.server.model;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

public class AttachmentDaoImp implements AttachmentDao 
{

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Integer saveAttachment(Attachment attachmentObj) {
		
		return (Integer)sessionFactory.getCurrentSession().save(attachmentObj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAttachmentLink(int clazEventDetailId) {
		
		String hql="select A.attachmentLink,A.attachmentName,A.attachmentType,A.type from Attachment A,ClassEventDetail E where A.classEventDetail=E and E.classEventDetailId=:clazEventDetailId";

		  return sessionFactory.getCurrentSession()    
		    .createQuery(hql)
		    .setParameter("clazEventDetailId", clazEventDetailId)
		    .list();
	}
	
	public Integer saveImageDetails(String fileName,int classEventDetailId,byte imageType,String name,String time,String type)
	{
	
	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY  hh:mm:ss");
		Date parsedDate;
		java.sql.Timestamp  timestamp = null ;
		try {
			parsedDate = dateFormat.parse(time);
			  timestamp  = new java.sql.Timestamp(parsedDate.getTime());
			System.out.println("parseedTime:"+timestamp);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}

	  ClassEventDetail ce=new ClassEventDetail();
	  ce.setClassEventDetailId(classEventDetailId);
	  Attachment att=new Attachment();
	  att.setAttachmentName(name);
	   att.setCreatedOn(timestamp);
      att.setAttachmentType(type);
	  att.setAttachmentLink(fileName);
	  att.setType(imageType);
	  att.setClassEventDetail(ce);
	  return (Integer)sessionFactory.getCurrentSession().save(att);
		
	}

	@Override
	public Integer saveImageDetailsFromPortal(String name,
			int classEventDetailId, byte imageType,String link,String time,
			String type) {
		
		 
		 Timestamp ts = Timestamp.valueOf(time);
		  ClassEventDetail ce=new ClassEventDetail();
		  ce.setClassEventDetailId(classEventDetailId);
		  Attachment att=new Attachment();
		  att.setAttachmentName(name);
		  att.setCreatedOn(ts);
	      att.setAttachmentType(type);
		  att.setAttachmentLink(link);
		  att.setType(imageType);
		  att.setClassEventDetail(ce);
		  return (Integer)sessionFactory.getCurrentSession().save(att);
	}
}
