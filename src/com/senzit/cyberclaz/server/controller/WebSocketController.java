                                                                                                                                                                                                        package com.senzit.cyberclaz.server.controller;

import java.io.IOException;

import java.util.Hashtable;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.senzit.cyberclaz.server.model.WebSocketData;
import com.senzit.cyberclaz.server.service.UserServices;
import com.senzit.cyberclaz.server.service.UserServicesImp;
import com.senzit.cyberclaz.server.service.WebSocketServiceImp;
import com.senzit.cyberclaz.server.subservice.JsonParser;

//@RestController
@ServerEndpoint("/socket/{userId}")
public class WebSocketController{
	int resultCode=0;
	String message="";

	static Logger log = Logger.getLogger(WebSocketController.class.getName());
	private WebSocketServiceImp wsService=new WebSocketServiceImp();

	@OnOpen
	public void open(Session wsSession, EndpointConfig config, @PathParam("userId") String userId)
			throws IOException {

		///////////////////////
		
		log.debug("TTTTTTTTTTTTTTTTTTTTTTTTTTOpening Websocket");
		log.debug(userId);
		
		//////////////////////////
		try
		{
		wsSession.getUserProperties().put("userId", userId);
		wsService.setWebSocket(userId, wsSession);
		}
		 catch (Exception e) 
			{

					e.printStackTrace();
					message = "Server Exception : "+e.getCause();
					resultCode = 2;
			}

	}
	
	@OnMessage
	public  void echoTextMessage(Session session, String msg, boolean last) throws IOException {

		String sessionID="";
		
		//session.getBasicRemote().sendText("Successful");
		//response.setHeader("Access-Control-Allow-Origin", "*");
		int resultCode=1;
		String message="Successful";
	try
	{
			String result;
			JsonParser<String, String> jsonResponse = new JsonParser<String,String>("WebsocketMessage",resultCode,message,sessionID,"websocketResult","success");
			session.getBasicRemote().sendText(jsonResponse.JsonResponseText());
		System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTT  Message");
		System.out.println(msg);
	}
	 catch (Exception e) 
		{

				e.printStackTrace();
				message = "Server Exception : "+e.getCause();
				resultCode = 2;
		}
	//////////////edited on 21/9/2015/////////////
	
	if("hi".equals(msg))
		  for(int i=0;i<1000;i++){
		   session.getBasicRemote().sendText("{\"origin\" : \"hi\",\"response\" : {\"hiResult\" : { },\"resultcode\" : 1,\"message\" : \""+i+"\" }}");
		   try {
		    Thread.sleep(4000);
		   } catch (InterruptedException e) {
		    e.printStackTrace();
		   }
		  }
	
		
	}
	@OnClose
	public void close(Session wsSession,CloseReason reason){

	//	response.setHeader("Access-Control-Allow-Origin", "*");
		String userId=(String)wsSession.getUserProperties().get("userId");
		///////////////////////
		log.debug("TTTTTTTTTTTTTTTTTTTTClosing Websocket");
		log.debug(userId);
		//////////////////////////
		//		Properties result=new Properties();
		//		List<String> userNameList=new ArrayList<String>();
		//		userNameList.add("senzit");
		//		result.put("name", userName);
		//		JsonParser<String, Hashtable> wsJson = new JsonParser<String, Hashtable>("removeFromAttendance",1,"Please remove "+userName,"result",result);
		//		String a=wsJson.JsonResponseText();
		//		WebSocketServiceImp wsObj=new WebSocketServiceImp();
		//		try {
		//			wsObj.writeTextmessage(userNameList, a);
		//		} catch (IOException e) {k
		//			e.printStackTrace();
		//		}
		////////////////////////////////
		try
		{
		wsService.removeWebSocket(userId);
		}
		 catch (Exception e) 
			{

					e.printStackTrace();
					message = "Server Exception : "+e.getCause();
					resultCode = 2;
			}
	
		//UserServicesImp us=new UserServicesImp();
	   // us.deletelogData(userId);
	}
	
	

}
