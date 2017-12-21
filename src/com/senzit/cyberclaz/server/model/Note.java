package com.senzit.cyberclaz.server.model;

public class Note {
	
	private int noteId;
	private User user;
	private ClassEventDetail classEventDetail;
	private String notes;
	
	public Note(){}

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ClassEventDetail getClassEventDetail() {
		return classEventDetail;
	}

	public void setClassEventDetail(ClassEventDetail classEventDetail) {
		this.classEventDetail = classEventDetail;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}
