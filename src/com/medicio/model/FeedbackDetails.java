package com.medicio.model;

public class FeedbackDetails {
	private String booking_ref;
	private String d_email;
	private String p_email;
	private String message;
	private String flag;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getBooking_ref() {
		return booking_ref;
	}
	public void setBooking_ref(String booking_ref) {
		this.booking_ref = booking_ref;
	}
	public String getD_email() {
		return d_email;
	}
	public void setD_email(String d_email) {
		this.d_email = d_email;
	}
	public String getP_email() {
		return p_email;
	}
	public void setP_email(String p_email) {
		this.p_email = p_email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
