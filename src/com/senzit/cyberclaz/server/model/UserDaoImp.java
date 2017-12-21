package com.senzit.cyberclaz.server.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class UserDaoImp implements UserDao 
{
	
	private SessionFactory sessionFactory;	

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public int getAuthUSer(String userId, String password) {
		
		String hql="select l.userId from User u,UserLogin l where"
				+" l.userId=u and u.userId=:userId and l.password=:password";
		
		System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSS"+password.length());
		System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT"+userId.length());
		List<String> s = sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("userId", userId)
				.setParameter("password",password)
				.list();
		if(s.isEmpty())
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getUserRolePrivilege(String userId) {
		
		String hql="select r.roleName,p.privilegeName from Role r,UserRole u,Privilege p,"
				+"RolePrivilege rp where u.role=r and rp.privilege = p and rp.role = r and u.userId=:userId";
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("userId", userId)
				.list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getRole(String userId) {
		String hql="select r.roleName from Role r,UserRole u"
				+" where u.role=r and u.userId=:userId";
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("userId", userId)
				.list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getUserName(String userId) {
		
		String hql="select u.firstName from User u where u.userId=:userId";
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("userId", userId)
				.list();
	}
	@SuppressWarnings("unchecked")
	public List<String> getPrimaryEmailId(String userId)
	{
		String hql="select u.emailId from User u where u.userId=:userId";
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("userId", userId)
				.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public String getPassword(String userId) {
		
		return (String)sessionFactory.getCurrentSession().createCriteria(UserLogin.class)
				.add(Restrictions.eq("userId", userId))
				.setProjection(Projections.property("password"))
				.uniqueResult();
	}
	@Override
	public int updatePassword(String userId, String newPassword) {
		
		return sessionFactory.getCurrentSession()
				.createQuery("update UserLogin set password=:newPassword where userId=:userId")
				.setParameter("newPassword",newPassword)
				.setParameter("userId",userId)
				.executeUpdate();
	}


	@Override
	public void deleteLog(Log logObj) {
		
		sessionFactory.getCurrentSession().delete(logObj);
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getUserNameFromBatch(CourseBatch batch) {
		
		String hql="select u.userId,u.profilePic,u.firstName from User u where u.roleType=:roleType";
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("roleType","2")
				.list();
	}

	@Override
	public Object[] getUserLoginResults(String userId) {
		
		String hql="select U.profilePic,C.courseName,S.semName,B.batchName,U.firstName,U.emailId from User U,Course C,Semester S,Batch B," +
				"CourseBatch CB,StudentBatch SB where U.userId=:userId and U=SB.user and SB.courseBatch=CB and " +
				"CB.course=C and CB.batch=B and CB.semester=S";
		return (Object[]) sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("userId", userId)
				.uniqueResult();
	}

	@Override
	public Object[] getTeacherLoginResults(String userId) {
		
		String hql="select U.profilePic,U.firstName,U.emailId from User U where U.userId=:userId";
		return (Object[]) sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("userId", userId)
				.uniqueResult();
	}
	public String getProfilePic(String userId) {
		
		String hql="select U.profilePic from User U where U.userId=:userId";
		return (String) sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("userId", userId)
				.uniqueResult();
		
	}
	@Override
	public String saveDetailsInDB(String userId,String firstName,String lastName, String middleName,String emailId,String mobileNumber,String registered, String dob, String address) {
		
		User u=new User();
		u.setUserId(userId);
		u.setFirstName(firstName);
		u.setLastName(lastName);
		u.setMiddleName(middleName);
		u.setEmailId(emailId);
		u.setMobileNo(mobileNumber);
		u.setDob(dob);
		u.setAddress(address);
		u.setRegFlag(registered);
		u.setProfilePic("a10.png");
		
		
		
		
		return (String) sessionFactory.getCurrentSession().save(u); 
	
		
	}
	public String savePasswordForUser(String userId,String password)
	{
		
		User u=new User();
		u.setUserId(userId);
		UserLogin ur=new UserLogin();
		ur.setUser(u);
		ur.setPassword(password);
		return (String) sessionFactory.getCurrentSession().save(ur); 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getRoleId(String roleName) {
		
		String hql="SELECT r.roleId FROM Role r WHERE r.roleName=:roleName";

		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("roleName", roleName)
				.list();

}
	@Override
	public String saveUserRoleForUser(String userId,String userroleId) {
	
		User u=new User();
		u.setUserId(userId);
		Role r=new Role();
		r.setRoleId(userroleId);
		UserRole ur=new UserRole();
		ur.setRole(r);
		ur.setUser(u);
		return (String) sessionFactory.getCurrentSession().save(ur); 
	}

	@Override
	public String getProfilePicFromEventId(int clazEventDetailId) 
	{
		
	
			 String hql="select u.profilePic from User u,Schedule s,SubjectTeacher st,ClassEventDetail e" +
						" where e.schedule= s and s.subjectTeacher=st and st.teacher=u and e.classEventDetailId=:classEventDetailId";
				

				return (String) sessionFactory.getCurrentSession().createQuery(hql)
						.setParameter("classEventDetailId", clazEventDetailId)
						.uniqueResult();
		
	}
	public String getTeacherIdFromClazEventId(int clazEventDetailId)
	{
		String hql="select u.userId from User u,Schedule s,SubjectTeacher st,ClassEventDetail e" +
				" where e.schedule= s and s.subjectTeacher=st and st.teacher=u and e.classEventDetailId=:classEventDetailId";
		

		return (String) sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("classEventDetailId", clazEventDetailId)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getStudentProfileDetails(String userId) {
		
		String hql="select u.firstName,u.dob,u.mobileNo,u.emailId,u.address,c.courseName,s.semName,cr.classRoomNo,b.batchName,u.regFlag,u.middleName" +
				" from User u,Course c,Semester s,CourseBatch cb,StudentBatch sb,Batch b,ClassRoom cr" +
				" where cb.course=c and cb.semester=s and cb.batch=b and cb.classRoom=cr and sb.courseBatch=cb " +
				"and sb.user=u and u.userId=:userId";
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("userId", userId)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> teacherProfileDetails(String userId) {
		
		String hql="select u.firstName,u.dob,u.mobileNo,u.emailId,u.address,c.courseName,s.semName,b.batchName,u.regFlag,u.middleName " +
				"from User u,Course c,Semester s,SubjectTeacher st,CourseSubject cs,Batch b,ClassRoom cr " +
				"where st.courseSubject=cs and cs.course=c and cs.semester=s and st.batch=b and st.teacher=u and u.userId=:userId";
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("userId", userId)
				.list();
	}

	@Override
	public int updateRegistrationDetailsinDb(String userId,String firstName,
			 String middleName, String lastName, String dob,
			 String emailId,String mobileNum,String address, String approvalFlag) {
		
		Query query = sessionFactory.getCurrentSession().createQuery("update User u set u.firstName=:firstName,u.middleName=:mName,u.lastName=:lName,u.dob=:Dob,u.mobileNo=:mobileNum,u.emailId=:emailId,u.address=:address," +
				"u.regFlag=:approvalFlag where u.userId=:userId");
		query.setParameter("firstName", firstName);
		query.setParameter("mName", middleName);
		query.setParameter("lName", lastName);
		query.setParameter("Dob", dob);
		query.setParameter("mobileNum", mobileNum);
		query.setParameter("emailId", emailId);
		query.setParameter("address", address);
		query.setParameter("approvalFlag", approvalFlag);
		query.setParameter("userId", userId);
		return query.executeUpdate();
	
	}
//	@Override
//	public int updateRegistrationDetailsinDb(String userId,
//			 String middleName, String lastName, String dob,
//			String address, String approvalFlag) {
//		
//		Query query = sessionFactory.getCurrentSession().createQuery("update User u set u.middleName=:mName,u.lastName=:lName,u.dob=:Dob,u.address=:address," +
//				"u.regFlag=:approvalFlag where u.userId=:userId");
//		query.setParameter("mName", middleName);
//		query.setParameter("lName", lastName);
//		query.setParameter("Dob", dob);
//	
//		query.setParameter("address", address);
//		query.setParameter("approvalFlag", approvalFlag);
//		query.setParameter("userId", userId);
//		return query.executeUpdate();
//	
//	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getUserProfileDetails(String userId) {
		
		String hql="select u.firstName,u.lastName,u.profilePic,u.dob,u.emailId,u.mobileNo,u.address,u.middleName from User u where u.userId=:userId";
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("userId", userId)
				.list();
	}

	@Override
	public String getRegistrationStatus(String userId) {
		
		String hql="select U.regFlag from User U where U.userId=:userId";
		return (String) sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("userId", userId)
				.uniqueResult();
		
	}

	@Override
	public String getUserPassword(String userId) {
		
		String hql="select U.password from UserLogin U,User ur where U.userId=ur and ur.userId=:userId";
		return (String) sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("userId", userId)
				.uniqueResult();
	}

	@Override
	public int updateProPic(String userId, String picLink) {
		
		String hql = "update User set profilePic=:pic where userId=:userId";
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("pic", picLink)
				.setParameter("userId", userId)
				.executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTeacherList() {
		String hql = "select u.userId FROM User u,Role r,UserRole ur where ur.role=r and ur.user=u and r.roleName='Teacher'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List results = query.list();
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getManagerProfileDetails(String userId) {
		
		String hql="select u.firstName,u.dob,u.mobileNo,u.emailId,u.address,u.regFlag,u.middleName" +
				" from User u,CourseBatch cb,ManagerSettings ms" +
				" where ms.courseBatch=cb " +
				"and ms.user=u and u.userId=:userId";
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("userId", userId)
				.list();
		
	}

	@Override
	public String getNameForDummy() {
		
		String hql="select U.userId from User U where U.mobileNo=:mobileNo";
		return (String) sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("mobileNo", "5893")
				.uniqueResult();
	}





}