package com.senzit.cyberclaz.server.service;

import java.util.Hashtable;
import java.util.List;

import org.json.JSONException;

public interface QuizService {
	
	boolean insertNewQuiz(String userName, String jsonString) throws JSONException;
	List<Hashtable<String,String>> getQuiz(String subject);
	List<Hashtable<String,Object>> getQuiz(int quizId);
	
	boolean insertNewPoll(String userName, String jsonString) throws JSONException;
	List<Hashtable<String,Object>> getAllPolls(String userName);
	Integer poll(int optionId,int pollId,String userName);
	
	boolean insertNewSurvey(String userName,String jsonString) throws JSONException;
	List<Hashtable<String,String>> getAllSurveys(String userName);
	List<Hashtable<String,Object>> getSurvey(int surveyId);
	
	boolean submitNewSurvey(String userName,String surveyJson) throws JSONException;
	
	List<Hashtable<String,String>> getAllSurveysCreatedBy(String userName);
	
	List<Hashtable<String,Object>> getSurveyResult(int surveyId);

}
