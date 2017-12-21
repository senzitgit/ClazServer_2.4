package com.senzit.cyberclaz.server.cognos;

import com.senzit.cyberclaz.server.model.Subject;
import com.senzit.cyberclaz.server.model.User;

public class TeacherRecommendation {
	
	
	private int teacherRId;
	private User user;
	private String term;
	private Subject subject;
	private String rating;
	
	public TeacherRecommendation()
	{}



	public int getTeacherRId() {
		return teacherRId;
	}



	public void setTeacherRId(int teacherRId) {
		this.teacherRId = teacherRId;
	}



	public String getTerm() {
		return term;
	}



	public void setTerm(String term) {
		this.term = term;
	}




	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	
}
