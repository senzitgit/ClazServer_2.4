package com.senzit.cyberclaz.server.cognos;

import com.senzit.cyberclaz.server.model.ClassEventDetail;
import com.senzit.cyberclaz.server.model.User;

public class TopPerformer {
	
	private int topPerformerId;
	private ClassEventDetail classEventDetail;
    private int likes;
    
    public TopPerformer(){}

	public int getTopPerformerId() {
		return topPerformerId;
	}

	public void setTopPerformerId(int topPerformerId) {
		this.topPerformerId = topPerformerId;
	}


	public ClassEventDetail getClassEventDetail() {
		return classEventDetail;
	}

	public void setClassEventDetail(ClassEventDetail classEventDetail) {
		this.classEventDetail = classEventDetail;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}
    


}
