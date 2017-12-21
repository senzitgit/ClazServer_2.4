package com.senzit.cyberclaz.server.model;

import com.senzit.cyberclaz.server.model.ClassEventDetail;

public class TopSession {
	
	private int topSessionId;
	private ClassEventDetail classEventDetail;
    private int like;
    private int disLike;
    public TopSession()
    {
    	
    }
	public int getTopSessionId() {
		return topSessionId;
	}
	public void setTopSessionId(int topSessionId) {
		this.topSessionId = topSessionId;
	}
	public ClassEventDetail getClassEventDetail() {
		return classEventDetail;
	}
	public void setClassEventDetail(ClassEventDetail classEventDetail) {
		this.classEventDetail = classEventDetail;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getDisLike() {
		return disLike;
	}
	public void setDisLike(int disLike) {
		this.disLike = disLike;
	}
    
    
}
