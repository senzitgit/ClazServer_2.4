package com.senzit.cyberclaz.server.service;

import java.io.IOException;
import java.util.List;

import javax.websocket.Session;

public interface WebSocketService {
	

	void writeTextmessage(List<String> userIdList,String msg,boolean recordFlag) throws IOException;
	void setWebSocket(String userId,Session wsSession);
	void removeWebSocket(String userId);
	void setInClassFlag(String userId);
	void resetInClassFlag(String userId);
	boolean getWebSocketTeacher(String userId);

}
