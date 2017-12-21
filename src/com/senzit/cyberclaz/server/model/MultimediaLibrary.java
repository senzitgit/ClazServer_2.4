package com.senzit.cyberclaz.server.model;

import java.sql.Timestamp;

public class MultimediaLibrary {

	private int mediaId;
	private String mediaName;
	private String mediaDescription;
	private String FTPpath;
	private Timestamp uploadedOn;
	private String type;
	
	private User user;
	
	public MultimediaLibrary(){}

	
	
	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public int getMediaId() {
		return mediaId;
	}

	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getMediaDescription() {
		return mediaDescription;
	}

	public void setMediaDescription(String mediaDescription) {
		this.mediaDescription = mediaDescription;
	}

	public String getFTPpath() {
		return FTPpath;
	}

	public void setFTPpath(String fTPpath) {
		FTPpath = fTPpath;
	}

	public Timestamp getUploadedOn() {
		return uploadedOn;
	}

	public void setUploadedOn(Timestamp uploadedOn) {
		this.uploadedOn = uploadedOn;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
