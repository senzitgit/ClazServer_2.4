package com.senzit.cyberclaz.server.model;

import java.util.Properties;


import javax.websocket.Session;

public class WebSocketData {
	
	public static Properties wsData=new Properties();
	
	public void putNewData(String userId,Session wsSession){
		
		Properties innerProp = new Properties();
		innerProp.put("session", wsSession);
		innerProp.put("inClass", false);
		wsData.put(userId, innerProp);
	}
	public void removeData(String userId){
		
		wsData.remove(userId);
	}
	public Properties getSession(String userId){
		
		return (Properties)wsData.get(userId);
	}

}
