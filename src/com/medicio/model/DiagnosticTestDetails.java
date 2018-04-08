package com.medicio.model;

public class DiagnosticTestDetails {
	private static final long serialVersionUID = 1L;

	private String test_id;
	private String booking_ref;
	private String p_email;
	private String d_email;
	private String test_name;
	private String payment_date;
	public String getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}
	public String getTest_date() {
		return test_date;
	}
	public void setTest_date(String test_date) {
		this.test_date = test_date;
	}
	private String test_date;
	
	private String test_price;
	private String flag;
	public String getTest_id() {
		return test_id;
	}
	public void setTest_id(String test_id) {
		this.test_id = test_id;
	}
	public String getBooking_ref() {
		return booking_ref;
	}
	public void setBooking_ref(String booking_ref) {
		this.booking_ref = booking_ref;
	}
	public String getP_email() {
		return p_email;
	}
	public void setP_email(String p_email) {
		this.p_email = p_email;
	}
	public String getD_email() {
		return d_email;
	}
	public void setD_email(String d_email) {
		this.d_email = d_email;
	}
	public String getTest_name() {
		return test_name;
	}
	public void setTest_name(String test_name) {
		this.test_name = test_name;
	}
	public String getTest_price() {
		return test_price;
	}
	public void setTest_price(String test_price) {
		this.test_price = test_price;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
