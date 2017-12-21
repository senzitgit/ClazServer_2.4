package com.senzit.cyberclaz.server.model;

import java.sql.Timestamp;

public class Log {
	
	private String userId;
	private User user;
	private String sessionId;
	private String deviceIp;
	private Timestamp loginTime;
	private Timestamp lastReqTime;
	private Rebbon rebbon;
	private boolean loginStatus;	//true:local	false:remote
	
	public Log(){}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}

	public Timestamp getLastReqTime() {
		return lastReqTime;
	}

	public void setLastReqTime(Timestamp lastReqTime) {
		this.lastReqTime = lastReqTime;
	}

	public Rebbon getRebbon() {
		return rebbon;
	}

	public void setRebbon(Rebbon rebbon) {
		this.rebbon = rebbon;
	}

	public boolean isLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	
	

}
