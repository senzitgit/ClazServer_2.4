package com.senzit.cyberclaz.server.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.senzit.cyberclaz.server.model.QuizDao;
import com.senzit.cyberclaz.server.model.User;
import com.senzit.cyberclaz.server.quiz.*;

public class QuizServiceImp implements QuizService{
	
	private QuizDao quizDao;

	public void setQuizDao(QuizDao dao) {
		this.quizDao = dao;
	}

	@Override
	public boolean insertNewQuiz(String userName, String jsonString) throws JSONException {
		
		JSONObject jsonData = new JSONObject(jsonString);
		Quiz quizObj = new Quiz();
		quizObj.setSubject( jsonData.getString("subject") );
		quizObj.setDescription( jsonData.getString("description")  );
		User userObj = new User();
		userObj.setUserId(userName);
		quizObj.setCreatedBy(userObj);
		Integer quizId = quizDao.insertQuiz(quizObj);
		if(quizId == null)
			return false;
		
		JSONArray qnArray = jsonData.getJSONArray("questions");
		int length = qnArray.length();
		for(int i=0;i<length;i++) {
			
			JSONObject eachQn = qnArray.getJSONObject(i);			
			QuizQuestion qnObj = new QuizQuestion();			
			qnObj.setQn(eachQn.getString("question"));
			qnObj.setQuiz(quizObj);
			Integer qnId = quizDao.insertQuizQn(qnObj);
			if(qnId == null)
				return false;
			
			JSONArray optionArray = eachQn.getJSONArray("options");
			int length2 = optionArray.length();
			for(int j=0;j<length2;j++) {
				
				JSONObject eachOption = optionArray.getJSONObject(j);
				QuestionOption optionObj = new QuestionOption();
				
				optionObj.setQuestion(qnObj);
				optionObj.setOption(eachOption.getString("option"));
				optionObj.setAnswerFlag(eachOption.getBoolean("flag"));
				if( (quizDao.insertQnOption(optionObj)) == null)
					return false;
			}
			
		}
		return true;
	}

	@Override
	public List<Hashtable<String, String>> getQuiz(String subject) {
		
		List<Quiz> quizList = quizDao.getQuiz(subject);
		ArrayList<Hashtable<String, String>> returnList = new ArrayList<Hashtable<String, String>>();
		for(Quiz quiz : quizList){
			
			Hashtable<String, String> table = new Hashtable<String, String>();
			table.put("quizId", ((Integer)quiz.getQuizId()).toString());
			table.put("subject", quiz.getSubject());
			
			String desc = quiz.getDescription();
			desc = (desc==null)?"":desc;
			
			table.put("description", desc );
			table.put("createdBy", quiz.getCreatedBy().getUserId());
			returnList.add(table);
		}
		return returnList;
	}

	@Override
	public List<Hashtable<String, Object>> getQuiz(int quizId) {
		
		ArrayList<Hashtable<String, Object>> returnList = new ArrayList<Hashtable<String, Object>>();
		List<QuizQuestion> qnList = quizDao.getQuizQuestion(quizId);
		for(QuizQuestion qn : qnList){
			
			Hashtable<String, Object> eachQn = new Hashtable<String, Object>();
			eachQn.put("question", qn.getQn());
			
			List<QuestionOption> optionList = quizDao.getQuestionOption(qn.getQnId());
			ArrayList<Hashtable<String, Object>> optionJsonList = new ArrayList<Hashtable<String, Object>>();
			for(QuestionOption option : optionList){
				
				Hashtable<String, Object> eachOption = new Hashtable<String, Object>();
				eachOption.put("option", option.getOption());
				eachOption.put("flag", option.isAnswerFlag());
				
				optionJsonList.add(eachOption);
			}
			eachQn.put("options", optionJsonList);
			
			returnList.add(eachQn);
		}
		return returnList;
	}
	
	@Override
	public boolean insertNewPoll(String userName, String jsonString) throws JSONException {
		
		JSONObject jsonData = new JSONObject(jsonString);
//		JSONObject attendance = jsonData.getJSONObject("attendanceList");
//		JSONArray onlineList =attendance.getJSONArray("onlineList");
		Poll pollObj = new Poll();
		pollObj.setPollQuestion(jsonData.getString("question"));
		User userObj = new User();
		userObj.setUserId(userName);
		pollObj.setCreatedBy(userObj);
		
		Integer pollId = quizDao.insertPoll(pollObj);
		
		if(pollId == null)
			return false;
		
		JSONArray opList = (JSONArray) jsonData.get("options");
		int l = opList.length();
		for(int i=0;i<l;i++){
			
			PollOption obj = new PollOption();
			obj.setOption(opList.getString(i));
			obj.setPoll(pollObj);
			quizDao.insertPollOption(obj);
		}
		return true;
	}

	@Override
	public List<Hashtable<String, Object>> getAllPolls(String userName) {
		
		List<Poll> pollList = quizDao.getAllPolls();
		ArrayList<Hashtable<String, Object>> list = new ArrayList<Hashtable<String, Object>>();
		for(Poll pollObj : pollList){
			
			/*if(dao.checkPolled(userName, pollObj.getPollId())>0)
				continue;*/

			Hashtable<String,Object> table = new Hashtable<String,Object>();
			
			List<Object[]> pollOptionList = quizDao.getPollOptions(pollObj.getPollId());
			
			table.put("pollId", pollObj.getPollId());
			table.put("question", pollObj.getPollQuestion());
			table.put("createdBy", pollObj.getCreatedBy().getUserId());
			table.put("polled", quizDao.checkPolled(userName, pollObj.getPollId())>0);
			
			ArrayList<Hashtable<String,String>> optList = new ArrayList<Hashtable<String,String>>();
			
			for(Object[] objArray : pollOptionList){
				
				Hashtable<String,String> optionTab = new Hashtable<String,String>();
				optionTab.put("optionId", objArray[0].toString());
				optionTab.put("option", objArray[1].toString());
				optionTab.put("votes", ((Long)quizDao.getVotes((int)objArray[0])).toString());
				
				optList.add(optionTab);
			}
			table.put("options", optList);			
			list.add(table);
		}
		return list;
	}

	@Override
	public Integer poll(int optionId,int pollId,String userName) {
		
		if(quizDao.checkPolled(userName, pollId)>0)
			return null;
//		User userObj = new User();
//		userObj.setUserId(userName);	
//		Poll result=new Poll();
//		result.setPollId(pollId);
//		PollOption opt = new PollOption();
//		opt.setOptionId(optionId);
//		PollResult obj = new PollResult();
//		obj.setPollOption(opt);
//		obj.setUser(userObj);
		User u=new User();
		u.setUserId(userName);
		Poll p=new Poll();
		p.setPollId(pollId);
		PollOption op=new PollOption();
		op.setOptionId(optionId);
		op.setPoll(p);
		PollResult obj=new PollResult();
		obj.setUser(u);
		obj.setPollOption(op);
		int value=quizDao.insertPollResult(obj);
		System.out.println("valueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+value);
		if(value!=0)
		{
			return value;
		}
		else
		{
			return value;
		}
	
	}

	@Override
	public boolean insertNewSurvey(String userName, String jsonString) throws JSONException {
		
		JSONObject jsonData = new JSONObject(jsonString);
		Survey surveyObj = new Survey();
		surveyObj.setDescription(jsonData.getString("description")  );
		User userObj = new User();
		userObj.setUserId(userName);
		surveyObj.setCreatedBy(userObj);
		Integer surveyId = quizDao.insertSurvey(surveyObj);
		if(surveyId == null)
			return false;
		
		JSONArray qnArray = jsonData.getJSONArray("questions");
		int length = qnArray.length();
		for(int i=0;i<length;i++) {
			
			JSONObject eachQn = qnArray.getJSONObject(i);			
			SurveyQuestion qnObj = new SurveyQuestion();			
			qnObj.setQuestion(eachQn.getString("question"));
			qnObj.setSurvey(surveyObj);
			Integer qnId = quizDao.insertSurveyQuestion(qnObj);
			if(qnId == null)
				return false;
			
			JSONArray optionArray = eachQn.getJSONArray("options");
			int length2 = optionArray.length();
			for(int j=0;j<length2;j++) {
				
				//JSONObject eachOption = optionArray.getJSONObject(j);
				SurveyOption optionObj = new SurveyOption();
				
				optionObj.setQuestion(qnObj);
				optionObj.setOption(optionArray.getString(j));
				//optionObj.setOption(eachOption.getString("option"));
				if( (quizDao.insertSurveyOption(optionObj)) == null)
					return false;
			}
			
		}
		return true;
	}

	@Override
	public List<Hashtable<String, String>> getAllSurveys(String userName) {
		
		List<Survey> surveyList = quizDao.getAllSurvey(userName);
		ArrayList<Hashtable<String, String>> list = new ArrayList<Hashtable<String, String>>();
		for(Survey surveyObj : surveyList){
		
			Hashtable<String,String> table = new Hashtable<String,String>();
						
			table.put("surveyId", ((Integer)surveyObj.getSurveyId()).toString());
			table.put("description", surveyObj.getDescription());
			table.put("createdBy", surveyObj.getCreatedBy().getUserId());
			list.add(table);
		}
		return list;
	}

	@Override
	public List<Hashtable<String, Object>> getSurvey(int surveyId) {
		
		ArrayList<Hashtable<String, Object>> returnList = new ArrayList<Hashtable<String, Object>>();
		List<SurveyQuestion> qnList = quizDao.getSurveyQuestion(surveyId);
		for(SurveyQuestion qn : qnList){
			
			Hashtable<String, Object> eachQn = new Hashtable<String, Object>();
			eachQn.put("question", qn.getQuestion());
			
			List<SurveyOption> optionList = quizDao.getSurveyOption(qn.getQuestionId());
			ArrayList<Hashtable<String, Object>> optionJsonList = new ArrayList<Hashtable<String, Object>>();
			for(SurveyOption option : optionList){
								
				Hashtable<String, Object> eachOption = new Hashtable<String, Object>();
				eachOption.put("option", option.getOption());
				eachOption.put("optionId", option.getOptionId());
				
				optionJsonList.add(eachOption);
			}
			eachQn.put("options", optionJsonList);
			
			returnList.add(eachQn);
		}
		return returnList;
	}

	@Override
	public boolean submitNewSurvey(String userName, String surveyJson) throws JSONException{
		
		JSONArray json = new JSONArray(surveyJson);
		for(int i=0;i<json.length();i++){
			
			SurveyUser obj = new SurveyUser();
			User userObj = new User();
			userObj.setUserId(userName);
			SurveyOption op = new SurveyOption();
			op.setOptionId(json.getInt(i));
			obj.setUser(userObj);
			obj.setOption(op);
			quizDao.insertSurveyUser(obj);
		}
		return true;
	}
	
	@Override
	public List<Hashtable<String, String>> getAllSurveysCreatedBy(String userName) {
		
		List<Survey> surveyList = quizDao.getAllSurveyCreatedBy(userName);
		ArrayList<Hashtable<String, String>> list = new ArrayList<Hashtable<String, String>>();
		for(Survey surveyObj : surveyList){
		
			Hashtable<String,String> table = new Hashtable<String,String>();
						
			table.put("surveyId", ((Integer)surveyObj.getSurveyId()).toString());
			table.put("description", surveyObj.getDescription());
			list.add(table);
		}
		return list;
	}

	@Override
	public List<Hashtable<String, Object>> getSurveyResult(int surveyId) {
		
		ArrayList<Hashtable<String, Object>> returnList = new ArrayList<Hashtable<String, Object>>();
		List<SurveyQuestion> qnList = quizDao.getSurveyQuestion(surveyId);
		for(SurveyQuestion eachQn : qnList){
			
			Hashtable<String, Object> eachQnTable = new Hashtable<String, Object>();
			
			ArrayList<Hashtable<String, String>> resultList = new ArrayList<Hashtable<String, String>>();
			List<SurveyOption> opList = quizDao.getSurveyOption(eachQn.getQuestionId());
			for(SurveyOption eachOpt : opList){
				
				Hashtable<String, String> result = new Hashtable<String, String>();
				result.put("option", eachOpt.getOption());
				result.put("noOfVotes", (quizDao.getSurveyOptionCount(eachOpt.getOptionId()).toString()));
				
				resultList.add(result);
			}
			eachQnTable.put("question", eachQn.getQuestion());
			eachQnTable.put("options", resultList);
			
			returnList.add(eachQnTable);
		}
		return returnList;
	}

}
