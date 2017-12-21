package com.senzit.cyberclaz.server.subservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;

import com.senzit.cyberclaz.server.service.WebSocketServiceImp;

public class RaiseHandQueue {
	
	private static Properties raiseHand = new Properties();
	
	public static void addToQueue(String userId, String json){
		
		//System.out.println("AddToQ : "+raiseHand);
		
		ArrayList<String> list = (ArrayList<String>) raiseHand.get(userId);
		if(list==null){
			
			ArrayList<String> list1 = new ArrayList<String>();
			list1.add(json);
			raiseHand.put(userId, list1);
		}
		else
			list.add(json);
		
		//System.out.println("AddToQ : "+raiseHand);
		
	}
	public static void deleteFromQueue(String userId, String question,String studentId) throws JSONException, IOException{
		
		//System.out.println("DeleteFromQ : "+raiseHand);
		
		ArrayList<String> list = (ArrayList<String>) raiseHand.get(userId);
		int i=0;
		if(list!=null){
			for(String jsonString : list){
				
				JSONObject json = new JSONObject(jsonString);
				json = json.getJSONObject("response").getJSONObject("raiseHandResult");
				String studentId1 = json.getString("userId");
				String question1 = json.getString("raiseHandText");
				if(studentId1.equals(studentId) && question1.equals(question)){
					
					list.remove(i);
					raiseHand.put(userId, list);//May not be needed
					break;
				}
				i++;
			}
			
			sendNext(userId);
		}
		
		//System.out.println("DeleteFromQ : "+raiseHand);
		
	}
	public static int getQueueSize(String userId){
		
		//System.out.println("GetQSize : "+raiseHand);
		
		ArrayList<String> list =  (ArrayList<String>) raiseHand.get(userId);
		if(list==null || list.isEmpty())
			return 0;
		return list.size();
		
	}
	public static void sendNext(String userId) throws IOException{
		
		//System.out.println("SendNext : "+raiseHand);
		
		ArrayList<String> list = (ArrayList<String>) raiseHand.get(userId);
		if(list!=null && !list.isEmpty()){
			
			ArrayList<String> teacherList = new ArrayList<String>();
			teacherList.add(userId);
			WebSocketServiceImp wsObj=new WebSocketServiceImp();
			wsObj.writeTextmessage(teacherList, list.get(0),false);		
			
		}
		
		//System.out.println("SendNext : "+raiseHand);
	}
	public static void clearQueue(String userId){
		
		//System.out.println("ClearQ : "+raiseHand);
		
		if(raiseHand.containsKey(userId))
			raiseHand.remove(userId);
		
		//System.out.println("ClearQ : "+raiseHand);
	}
	public static String getNext(String userId){
		
		ArrayList<String> list = (ArrayList<String>) raiseHand.get(userId);
		if(list!=null && !list.isEmpty())
			return list.get(0);
		return "";
		
	}

}
