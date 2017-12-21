package com.senzit.cyberclaz.server.model;

public class VideoRating {

	private int ratingId;
	private String videoRate;
	private ClassEventDetail classEventDetail;
	
	VideoRating(){}
	
	
	
	public ClassEventDetail getClassEventDetail() {
		return classEventDetail;
	}



	public void setClassEventDetail(ClassEventDetail classEventDetail) {
		this.classEventDetail = classEventDetail;
	}



	public int getRatingId() {
		return ratingId;
	}
	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}



	public String getVideoRate() {
		return videoRate;
	}



	public void setVideoRate(String videoRate) {
		this.videoRate = videoRate;
	}

	
}
