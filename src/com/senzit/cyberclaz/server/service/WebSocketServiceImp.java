package com.senzit.cyberclaz.server.service;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.websocket.Session;

import com.senzit.cyberclaz.server.model.WebSocketData;

public class WebSocketServiceImp implements WebSocketService {

	private static WebSocketData wsObj=new WebSocketData();

	@Override
	public void writeTextmessage(List<String> userIdList, String msg,boolean recordFlag)
			throws IOException {

System.out.println(WebSocketData.wsData);
		//////////////////////////////////////////////
		System.err.println("Websocket Message is "+msg);
		/////////////////////////////////////////////////////
		for(String userId:userIdList){

			Properties wsProp=wsObj.getSession(userId);
			if(wsProp!=null){
				
				if(recordFlag){
					
					

						Session wsSession = (Session) wsProp.get("session");
						wsSession.getBasicRemote().sendText(msg);

						///////////////////////////////////////////////////////////////
						System.err.println("Websocket Message sent to "+userId);
						////////////////////////////////////////////////////////
					
				}
			
				//Session wsSession = (Session) wsProp.get("session");
				//wsSession.getBasicRemote().sendText(msg);

			}
		}
	}
	
	@Override
	public void setWebSocket(String userId, Session wsSession) {

		wsObj.putNewData(userId, wsSession);

	}

	@Override
	public void removeWebSocket(String userId) {

		wsObj.removeData(userId);

	}

	@Override
	public void setInClassFlag(String userId) {
		
		Properties wsProp=wsObj.getSession(userId);
		//wsProp.remove("inClass");
		wsProp.put("inClass", true);
		
	}
	@Override
	public void resetInClassFlag(String userId) {
		
		Properties wsProp=wsObj.getSession(userId);
		wsProp.remove("inClass");
		wsProp.put("inClass", true);
		
	}
	@Override
	public boolean getWebSocketTeacher(String userId) {

		Properties wsProp=wsObj.getSession(userId);
		if(wsProp.isEmpty())
		return false;
		else
			return true;

	}

}
