package com.senzit.cyberclaz.server.model;

import java.util.Date;

public class Holiday {
	
	private int holidayId;
	private Date date;
	private String description;
	
	Holiday(){}

	public int getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(int holidayId) {
		this.holidayId = holidayId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
