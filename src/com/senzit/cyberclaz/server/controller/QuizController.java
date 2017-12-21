	package com.senzit.cyberclaz.server.controller;
	
	import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
	
	import javax.servlet.http.HttpSession;
	
	import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
	
	import com.senzit.cyberclaz.server.service.QuizService;
import com.senzit.cyberclaz.server.subservice.JsonParser;
	
	
	@RestController
	public class QuizController {
		
		static Logger log = Logger.getLogger(QuizController.class.getName());
		
		@Autowired
		private QuizService quizService;
		
		@RequestMapping(value = "/newQuiz", method = RequestMethod.POST)
		public String newQuiz(HttpSession sessionObj,
				@RequestParam("quizJson") String quizJson,
				@RequestParam("sessionID") String sessionID){
			
			Byte resultCode=0;
			String message="";
			String result="";
			String userName = (String)sessionObj.getAttribute("userId");
			///////////////////////////
			log.debug("newQuiz");
			log.debug("Username:"+userName);
			log.debug("qnJson:"+quizJson);
			//////////////////////////
			if(userName == null){
				
				message = "Invalid session";
			}
			else {
				
				try {
					if(quizService.insertNewQuiz(userName, quizJson)) {
						
						resultCode = 1;
						message = "Success";
					}
					else {
						
						resultCode = 0;
						message = "Server error";
					}
				} catch (JSONException e) {
					
					e.printStackTrace();
					message = "Server Exception : "+e.getCause();
					resultCode = 2;
				}
			}
			
			JsonParser<String, String> jsonResponse = new JsonParser<String, String>("newQuiz",resultCode,message,sessionID,"result",result);
			return jsonResponse.JsonResponseText();
	
			
		}
		
		@RequestMapping(value = "/searchQuiz", method = RequestMethod.POST)
		public String searchQuiz(HttpSession sessionObj,
				@RequestParam("subject") String subject,
				@RequestParam("sessionID") String sessionID){
			
			Byte resultCode=0;
			String message="";
			List<Hashtable<String, String>> result = new ArrayList<Hashtable<String, String>>();
			String userName = (String)sessionObj.getAttribute("userId");
			///////////////////////////
			log.debug("searchQuiz");
			log.debug("Username:"+userName);
			log.debug("subject:"+subject);
			//////////////////////////
			if(userName == null){
				
				message = "Invalid session";
			}
			else {
				
				try {
					result = quizService.getQuiz(subject);
					resultCode = 1;
					message = "Success";
				} catch (Exception e) {
					
					e.printStackTrace();
					message = "Server Exception : "+e.getCause();
					resultCode = 2;
				}
			}
			
			JsonParser<String, List<Hashtable<String, String>>> jsonResponse = new JsonParser<String, List<Hashtable<String, String>>>("searchQuiz",resultCode,message,sessionID,"result",result);
			return jsonResponse.JsonResponseText();
		}
		
		@RequestMapping(value = "/getQuiz", method = RequestMethod.POST)
		public String getQuiz(HttpSession sessionObj,
				@RequestParam("quizId") Integer quizId,
				@RequestParam("sessionID") String sessionID){
			
			Byte resultCode=0;
			String message="";
			List<Hashtable<String, Object>> result = new ArrayList<Hashtable<String, Object>>();
			String userName = (String)sessionObj.getAttribute("userId");
			///////////////////////////
			log.debug("getQuiz");
			log.debug("Username:"+userName);
			log.debug("quizId:"+quizId);
			//////////////////////////
			if(userName == null){
				
				message = "Invalid session";
			}
			else {
				
				try {
					result = quizService.getQuiz(quizId);
					resultCode = 1;
					message = "Success";
				} catch (Exception e) {
					
					e.printStackTrace();
					message = "Server Exception : "+e.getCause();
					resultCode = 2;
				}
			}
			JsonParser<String, List<Hashtable<String, Object>>> jsonResponse = new JsonParser<String, List<Hashtable<String, Object>>>("getQuiz",resultCode,message,sessionID,"result",result);
			return jsonResponse.JsonResponseText();
		}
		
		@RequestMapping(value = "/newPoll", method = RequestMethod.POST)
		public String newPoll(HttpSession sessionObj,
				@RequestParam("pollJson") String pollJson,
				@RequestParam("sessionID") String sessionID){
			
			Byte resultCode=0;
			String message="";
			String result="";
			String userName = (String)sessionObj.getAttribute("userId");
			///////////////////////////
			log.debug("newPoll");
			log.debug("Username:"+userName);
			log.debug("pollJson:"+pollJson);
			//////////////////////////
			if(userName == null){
				
				message = "Invalid session";
			}
			else {
				
				try {
					if(quizService.insertNewPoll(userName, pollJson)) {
						
						resultCode = 1;
						message = "Success";
					}
					else {
						
						resultCode = 0;
						message = "Server error";
					}
				} catch (JSONException e) {
					
					e.printStackTrace();
					message = "Server Exception : "+e.getCause();
					resultCode = 2;
				}
			}
			
			JsonParser<String, String> jsonResponse = new JsonParser<String, String>("newPoll",resultCode,message,sessionID,"result",result);
			return jsonResponse.JsonResponseText();
		}
		
		@RequestMapping(value = "/getAllPolls", method = RequestMethod.POST)
		public String getAllPolls(HttpSession sessionObj,
				@RequestParam("sessionID") String sessionID){
			
			Byte resultCode=0;
			String message="";
			List<Hashtable<String, Object>> result = new ArrayList<Hashtable<String, Object>>();
			String userName = (String)sessionObj.getAttribute("userId");
			///////////////////////////
			log.debug("getAllPolls");
			log.debug("Username:"+userName);
			//////////////////////////
			if(userName == null){
				
				message = "Invalid session";
			}
			else {
				
				try {
					result = quizService.getAllPolls(userName);
					resultCode = 1;
					message = "Success";
				} catch (Exception e) {
					
					e.printStackTrace();
					message = "Server Exception : "+e.getCause();
					resultCode = 2;
				}
			}
			
			JsonParser<String, List<Hashtable<String, Object>>> jsonResponse = new JsonParser<String, List<Hashtable<String, Object>>>("getAllPolls",resultCode,message,sessionID,"result",result);
			return jsonResponse.JsonResponseText();
		}
		
		@RequestMapping(value = "/poll", method = RequestMethod.POST)
		public String getAllPolls(HttpSession sessionObj,@RequestParam("optionId") int optionId,@RequestParam("pollId") int pollId,
				@RequestParam("sessionID") String sessionID){
		
			
			Byte resultCode=0;
			String message="";
			String result = "";
			String userName = (String)sessionObj.getAttribute("userId");
			///////////////////////////
			log.debug("poll");
			log.debug("Username:"+userName);
			log.debug("optionId:"+optionId);
			log.debug("pollId:"+pollId);
			//////////////////////////
			if(userName == null){
				
				message = "Invalid session";
			}
			else {
				
				try {
					if(quizService.poll(optionId,pollId,userName)!=null){
						
						resultCode = 1;
						message = "Success";
					}
					else
						message = "Can't poll";
				} catch (Exception e) {
					
					e.printStackTrace();
					message = "Server Exception : "+e.getCause();
					resultCode = 2;
				}
			}
			
			JsonParser<String, String> jsonResponse = new JsonParser<String, String>("poll",resultCode,message,sessionID,"result",result);
			return jsonResponse.JsonResponseText();
		}
		
		@RequestMapping(value = "/newSurvey", method = RequestMethod.POST)
		public String newSurvey(HttpSession sessionObj,
				@RequestParam("surveyJson") String surveyJson,
				@RequestParam("sessionID") String sessionID){
			
			Byte resultCode=0;
			String message="";
			String result="";
			String userName = (String)sessionObj.getAttribute("userId");
			///////////////////////////
			log.debug("newSurvey");
			log.debug("Username:"+userName);
			log.debug("surveyJson:"+surveyJson);
			//////////////////////////
			if(userName == null){
				
				message = "Invalid session";
			}
			else {
				
				try {
					if(quizService.insertNewSurvey(userName, surveyJson)) {
						
						resultCode = 1;
						message = "Success";
					}
					else {
						
						resultCode = 0;
						message = "Server error";
					}
				} catch (JSONException e) {
					
					e.printStackTrace();
					message = "Server Exception : "+e.getCause();
					resultCode = 2;
				}
			}
			
			JsonParser<String, String> jsonResponse = new JsonParser<String, String>("newSurvey",resultCode,message,sessionID,"result",result);
			return jsonResponse.JsonResponseText();
		}
		
		@RequestMapping(value = "/getAllSurveys", method = RequestMethod.POST)
		public String getAllSurveys(HttpSession sessionObj,
				@RequestParam("sessionID") String sessionID){
			
			Byte resultCode=0;
			String message="";
			List<Hashtable<String, String>> result = new ArrayList<Hashtable<String, String>>();
			String userName = (String)sessionObj.getAttribute("userId");
			///////////////////////////
			log.debug("getAllSurveys");
			log.debug("Username:"+userName);
			//////////////////////////
			if(userName == null){
				
				message = "Invalid session";
			}
			else {
				
				try {
					result = quizService.getAllSurveys(userName);
					resultCode = 1;
					message = "Success";
				} catch (Exception e) {
					
					e.printStackTrace();
					message = "Server Exception : "+e.getCause();
					resultCode = 2;
				}
			}
			
			JsonParser<String, List<Hashtable<String, String>>> jsonResponse = new JsonParser<String, List<Hashtable<String, String>>>("getAllSurveys",resultCode,message,sessionID,"result",result);
			return jsonResponse.JsonResponseText();
		}
		
		@RequestMapping(value = "/getSurvey", method = RequestMethod.POST)
		public String getSurvey(HttpSession sessionObj,
				@RequestParam("surveyId") Integer surveyId,
				@RequestParam("sessionID") String sessionID){
			
			Byte resultCode=0;
			String message="";
			List<Hashtable<String, Object>> result = new ArrayList<Hashtable<String, Object>>();
			String userName = (String)sessionObj.getAttribute("userId");
			///////////////////////////
			log.debug("getSurvey");
			log.debug("Username:"+userName);
			log.debug("surveyId:"+surveyId);
			//////////////////////////
			if(userName == null){
				
				message = "Invalid session";
			}
			else {
				
				try {
					result = quizService.getSurvey(surveyId);
					resultCode = 1;
					message = "Success";
				} catch (Exception e) {
					
					e.printStackTrace();
					message = "Server Exception : "+e.getCause();
					resultCode = 2;
				}
			}
			
			JsonParser<String, List<Hashtable<String, Object>>> jsonResponse = new JsonParser<String, List<Hashtable<String, Object>>>("getSurvey",resultCode,message,sessionID,"result",result);
			return jsonResponse.JsonResponseText();
		}
		
		@RequestMapping(value = "/submitSurvey", method = RequestMethod.POST)
		public String submitSurvey(HttpSession sessionObj,
				@RequestParam("surveyJson") String surveyJson,
				@RequestParam("sessionID") String sessionID){
			
			Byte resultCode=0;
			String message="";
			String result="";
			String userName = (String)sessionObj.getAttribute("userId");
			///////////////////////////
			log.debug("submitSurvey");
			log.debug("Username:"+userName);
			log.debug("surveyJson:"+surveyJson);
			//////////////////////////
			if(userName == null){
				
				message = "Invalid session";
			}
			else {
				
				try {
					if(quizService.submitNewSurvey(userName, surveyJson)) {
						
						resultCode = 1;
						message = "Success";
					}
					else {
						
						resultCode = 0;
						message = "Server error";
					}
				} catch (JSONException e) {
					
					e.printStackTrace();
					message = "Server Exception : "+e.getCause();
					resultCode = 2;
				}
			}
			
			JsonParser<String, String> jsonResponse = new JsonParser<String, String>("submitSurvey",resultCode,message,sessionID,"result",result);
			return jsonResponse.JsonResponseText();
		}
		
		@RequestMapping(value = "/getAllMySurveys", method = RequestMethod.POST)
		public String getAllMySurveys(HttpSession sessionObj,
				@RequestParam("sessionID") String sessionID){
			
			Byte resultCode=0;
			String message="";
			List<Hashtable<String, String>> result = new ArrayList<Hashtable<String, String>>();
			String userName = (String)sessionObj.getAttribute("userId");
			///////////////////////////
			log.debug("getAllMySurveys");
			log.debug("Username:"+userName);
			//////////////////////////
			if(userName == null){
				
				message = "Invalid session";
			}
			else {
				
				try {
					result = quizService.getAllSurveysCreatedBy(userName);
					resultCode = 1;
					message = "Success";
				} catch (Exception e) {
					
					e.printStackTrace();
					message = "Server Exception : "+e.getCause();
					resultCode = 2;
				}
			}
			
			JsonParser<String, List<Hashtable<String, String>>> jsonResponse = new JsonParser<String, List<Hashtable<String, String>>>("getAllMySurveys",resultCode,message,sessionID,"result",result);
			return jsonResponse.JsonResponseText();
		}
		
		@RequestMapping(value = "/getSurveyResult", method = RequestMethod.POST)
		public String getSurveyResult(HttpSession sessionObj,@RequestParam("surveyId") Integer surveyId,
				@RequestParam("sessionID") String sessionID){
			
			Byte resultCode=0;
			String message="";
			List<Hashtable<String, Object>> result = new ArrayList<Hashtable<String, Object>>();
			String userName = (String)sessionObj.getAttribute("userId");
			///////////////////////////
			log.debug("getSurveyResult");
			log.debug("Username:"+userName);
			log.debug("Username:"+userName);
			//////////////////////////
			if(userName == null){
				
				message = "Invalid session";
			}
			else {
				
				try {
					result = quizService.getSurveyResult(surveyId);
					resultCode = 1;
					message = "Success";
				} catch (Exception e) {
					
					e.printStackTrace();
					message = "Server Exception : "+e.getCause();
					resultCode = 2;
				}
			}
			
			JsonParser<String, List<Hashtable<String, Object>>> jsonResponse = new JsonParser<String, List<Hashtable<String, Object>>>("getSurveyResult",resultCode,message,sessionID,"result",result);
			return jsonResponse.JsonResponseText();
		}
	
	}
