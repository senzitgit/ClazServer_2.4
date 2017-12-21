package com.senzit.cyberclaz.server.model;

import java.sql.Timestamp;

public class Notification {

	private int notificationId;
	private String notification;
	private String notificationAnswer;
	private String fromUserId;
	private String toUserId;
	private ClassEventDetail classEventDetail;
	private Timestamp createdOn;
	private String reminderOrRaiseHandFlag;
	private String notificationFlag;

	public Notification() {}

	
	public String getNotificationFlag() {
		return notificationFlag;
	}


	public void setNotificationFlag(String notificationFlag) {
		this.notificationFlag = notificationFlag;
	}

	public String getNotificationAnswer() {
		return notificationAnswer;
	}

	public void setNotificationAnswer(String notificationAnswer) {
		this.notificationAnswer = notificationAnswer;
	}

	public int getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(int notificationId) {
		this.notificationId = notificationId;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}


	public String getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public ClassEventDetail getClassEventDetail() {
		return classEventDetail;
	}

	public void setClassEventDetail(ClassEventDetail classEventDetail) {
		this.classEventDetail = classEventDetail;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public String getReminderOrRaiseHandFlag() {
		return reminderOrRaiseHandFlag;
	}

	public void setReminderOrRaiseHandFlag(String reminderOrRaiseHandFlag) {
		this.reminderOrRaiseHandFlag = reminderOrRaiseHandFlag;
	}
	


}
