package com.senzit.cyberclaz.server.model;

import java.util.List;

import org.hibernate.SessionFactory;

import com.senzit.cyberclaz.server.quiz.*;

public class QuizDaoImp implements QuizDao{
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public Integer insertQuiz(Quiz q) {
		
		return (Integer)sessionFactory.getCurrentSession().save(q);
	}

	@Override
	public Integer insertQuizQn(QuizQuestion q) {
		
		return (Integer)sessionFactory.getCurrentSession().save(q);
	}

	@Override
	public Integer insertQnOption(QuestionOption option) {
		
		return (Integer)sessionFactory.getCurrentSession().save(option);
	}

	@Override
	public int deleteQnOptions(int quizId) {
		
		return 0;
	}

	@Override
	public int deleteQuizQns(int quizId) {
		return 0;
	}

	@Override
	public int deleteQuiz(int quizId) {
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Quiz> getQuiz(String subject) {
		
		String hql = "from Quiz where lower(subject) like :sub";
		return (List<Quiz>)sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("sub", "%"+subject.toLowerCase()+"%")
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuizQuestion> getQuizQuestion(int quizId) {
		
		String hql = "select Qq from QuizQuestion Qq,Quiz Q where Q.quizId = :quizId and Qq.quiz = Q";
		return (List<QuizQuestion>)sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("quizId", quizId)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionOption> getQuestionOption(int questionId) {
		
		String hql = "select Qo from QuizQuestion Qq,QuestionOption Qo where Qq.qnId = :qnId and Qo.question = Qq";
		return (List<QuestionOption>)sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("qnId", questionId)
				.list();
	}

	@Override
	public Integer insertPoll(Poll p) {
		
		return (Integer)sessionFactory.getCurrentSession().save(p);
	}

	@Override
	public Integer insertPollOption(PollOption option) {
		
		return (Integer)sessionFactory.getCurrentSession().save(option);
	}

	@Override
	public Integer insertPollResult(PollResult pollResult) {
		
		return (Integer)sessionFactory.getCurrentSession().save(pollResult);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Poll> getAllPolls() {
		
		String hql = "from Poll";
		return (List<Poll>)sessionFactory.getCurrentSession().createQuery(hql)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getPollOptions(int pollId) {
		
		String hql = "select PO.optionId,PO.option from Poll P,PollOption PO where P.pollId = :pollId and PO.poll = P";
		return (List<Object[]>)sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("pollId", pollId)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getPollOptions(Poll poll) {
		
		String hql = "select optionId,option where poll = :poll";
		return (List<Object[]>)sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("poll", poll)
				.list();
	}

	@Override
	public long checkPolled(String userName, int pollId) {
		
		String hql = "select count(*) from PollResult PR, Poll P, PollOption PO, User U where U.userId=:userName and"
				+ " P.pollId=:pollId and PR.user=U and PR.pollOption=PO and PO.poll=P";
		return (long) sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("userName", userName)
				.setParameter("pollId",pollId)
				.uniqueResult();
	}

	@Override
	public long getVotes(int optionId) {
		
		String hql = "select count(*) from PollResult PR,PollOption PO where PO.optionId=:optionId and"
				+ " PR.pollOption=PO";
		return (long) sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("optionId", optionId)
				.uniqueResult();
	}

	@Override
	public Integer insertSurvey(Survey s) {
		
		return (Integer) sessionFactory.getCurrentSession().save(s);
	}

	@Override
	public Integer insertSurveyQuestion(SurveyQuestion sq) {
		
		return (Integer) sessionFactory.getCurrentSession().save(sq);
	}

	@Override
	public Integer insertSurveyOption(SurveyOption so) {
		
		return (Integer) sessionFactory.getCurrentSession().save(so);
	}

	@Override
	public Integer insertSurveyUser(SurveyUser su) {
		
		return (Integer) sessionFactory.getCurrentSession().save(su);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Survey> getAllSurvey(String userName) {
		
		String hql = "select S from Survey S where S not in (select S from Survey S,"
				+ "SurveyQuestion SQ,SurveyOption SO,SurveyUser SU,User U where "
				+ "U.userId=:userId and SU.user=U and SU.option=SO and SO.question=SQ and "
				+ "SQ.survey=S)";
		
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("userId", userName)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SurveyQuestion> getSurveyQuestion(int surveyId) {
		
		String hql = "select Sq from SurveyQuestion Sq,Survey S where S.surveyId = :surveyId"
				+ " and Sq.survey = S";
		return (List<SurveyQuestion>)sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("surveyId", surveyId)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SurveyOption> getSurveyOption(int questionId) {
		
		String hql = "select So from SurveyQuestion Sq,SurveyOption So where Sq.questionId = :questionId"
				+ " and So.question = Sq";
		return (List<SurveyOption>)sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("questionId", questionId)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Survey> getAllSurveyCreatedBy(String userName) {
		
		String hql = "select S from Survey S,User U where U.userId=:userName and S.createdBy=U";
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("userName", userName)
				.list();
	}

	@Override
	public Long getSurveyOptionCount(int optionId) {
		
		String hql = "select count(*) from SurveyUser SU where SU.option in "
				+ "(from SurveyOption where optionId=:optionId)";
		return (long) sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("optionId", optionId)
				.uniqueResult();
	}

}
