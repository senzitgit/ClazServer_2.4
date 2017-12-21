package com.senzit.cyberclaz.server.model;

import java.util.List;

import com.senzit.cyberclaz.server.quiz.*;

public interface QuizDao {
	
	Integer insertQuiz(Quiz q);
	Integer insertQuizQn(QuizQuestion q);
	Integer insertQnOption(QuestionOption option);
	
	int deleteQnOptions(int quizId);
	int deleteQuizQns(int quizId);
	int deleteQuiz(int quizId);
	
	List<Quiz> getQuiz(String subject);
	List<QuizQuestion> getQuizQuestion(int quizId);
	List<QuestionOption> getQuestionOption(int questionId);
	
	Integer insertPoll(Poll p);
	Integer insertPollOption(PollOption option);
	Integer insertPollResult(PollResult pollResult);
	
	List<Poll> getAllPolls();
	List<Object[]> getPollOptions(int pollId);
	List<Object[]> getPollOptions(Poll poll);
	long getVotes(int optionId);
	
	long checkPolled(String userName,int pollId);
	
	Integer insertSurvey(Survey s);
	Integer insertSurveyQuestion(SurveyQuestion sq);
	Integer insertSurveyOption(SurveyOption so);
	Integer insertSurveyUser(SurveyUser su);
	
	List<Survey> getAllSurvey(String userName);
	List<SurveyQuestion> getSurveyQuestion(int surveyId);
	List<SurveyOption> getSurveyOption(int questionId);
	
	List<Survey> getAllSurveyCreatedBy(String userName);
	
	Long getSurveyOptionCount(int optionId);

}
