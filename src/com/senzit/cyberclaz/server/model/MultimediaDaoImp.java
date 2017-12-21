package com.senzit.cyberclaz.server.model;

import java.util.List;

import org.hibernate.SessionFactory;

public class MultimediaDaoImp implements MultimediaDao{
	
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAttachmentDetailsUploadedViaPortal(String teacherId) {
	
//		String hql="select l.userId,u.firstName from User u,Log l,Rebbon rb,UserRole ur,Role r," +
//				"CourseBatch cb,ClassRoom cr where l.userId=u.userId and l.rebbon=rb and ur.role=r" +
//				" and r.roleName=:role and ur.user=u and rb.classRoom=cb.classRoom and cb.courseBatchId=:courseBatchId ";
		
		String hql="select m.mediaName,m.FTPpath,m.mediaDescription,m.uploadedOn,m.type,m.mediaId from MultimediaLibrary m,User u" +
				" where m.user=u and u.userId=:teacherId ";
		
		
		return sessionFactory.getCurrentSession()
				.createQuery(hql)
				.setParameter("teacherId", teacherId)
				.list();
		
	}

	@Override
	public Integer saveAttachmentDetailsUploadFromPortal(MultimediaLibrary ml) {
		return (Integer)sessionFactory.getCurrentSession().save(ml);
	}

	@Override
	public void deleteAttachmentDetailsUploadFromPortal(MultimediaLibrary ml) {
		sessionFactory.getCurrentSession().delete(ml);
		
	}


}
