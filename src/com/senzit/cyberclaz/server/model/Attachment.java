package com.senzit.cyberclaz.server.model;

import java.sql.Timestamp;

public class Attachment 
{
	
	private int attachmentId;
	private ClassEventDetail classEventDetail;
	private String attachmentName;
	private String attachmentLink;
	private String attachmentType;
	private Timestamp createdOn;
	private byte type;
	
	public Attachment(){}
	
	
	public byte getType() {
		return type;
	}
	public void setType(byte type) {
		this.type = type;
	}

	public int getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
	}

	public ClassEventDetail getClassEventDetail() {
		return classEventDetail;
	}

	public void setClassEventDetail(ClassEventDetail classEventDetail) {
		this.classEventDetail = classEventDetail;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getAttachmentLink() {
		return attachmentLink;
	}

	public void setAttachmentLink(String attachmentLink) {
		this.attachmentLink = attachmentLink;
	}

	public String getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	
	

}
