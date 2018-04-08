package com.medicio.dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import com.medicio.model.AppHistoryDetails;
import com.medicio.model.DiagnosticTestDetails;
import com.medicio.model.DoctorDetails;
import com.medicio.model.FeedbackDetails;
import com.medicio.model.PatContactDetails;
import com.medicio.model.PatientDetails;

import javafx.util.converter.LocalDateStringConverter;

import com.medicio.model.DoctorDetails;
import com.medicio.dao.ConnectionByStaticMethod;



public class CustomerDAO 
{
	public boolean userRegistration(DoctorDetails docobj) 
	{
		Connection connectionobject = null;
		PreparedStatement pst = null;
		boolean f = false;
		try
		{
	         connectionobject = ConnectionByStaticMethod.getMySQLConnection();
		     
	        //connectionobject = ConnectionFactoryBySingletonClass.getConnection();
	      	 
	         //JDBC STEP 3
	         //CREATE QUERY
	         pst = connectionobject.prepareStatement("insert into doctor values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		     
		     pst.setString(1, docobj.getEmail());
		     pst.setString(2, docobj.getPassword());
		     pst.setString(3, docobj.getFirst_name());
		     pst.setString(4, docobj.getLast_name());
		     pst.setString(5, docobj.getPhone());
		     pst.setString(6, docobj.getDesignation());
		     pst.setString(7, docobj.getAddress());
		     pst.setString(8, docobj.getDate());
		     pst.setString(9, docobj.getTime());
		     pst.setString(10, docobj.getZip());
		     pst.setString(11, docobj.getState());
		     pst.setString(12, docobj.getDepartment());
		     pst.setString(13, docobj.getSalary());
		     pst.setString(14, "false");
		     //JDBC STEP 4
		     //EXECUTE QUERY
		     int i = pst.executeUpdate();		     
		     if(i > 0 )
		    	 f = true;
           
	    }catch(SQLException e){System.out.print(e.toString());}
	     finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
	       ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return f;
	}
	
	public boolean patUpdate(PatientDetails patobj) {
		Connection connectionobject=null;
		PreparedStatement pst=null;
		 
		boolean f=false;
		try {
			connectionobject=ConnectionByStaticMethod.getMySQLConnection();
			pst=connectionobject.prepareStatement("update patient set last_name=?,phone=?,address=?,state=? where email=?");
			pst.setString(1, patobj.getLast_name());
			pst.setString(2, patobj.getPhone());
			pst.setString(3, patobj.getAddress());
			pst.setString(4, patobj.getState());
			pst.setString(5, patobj.getEmail());
			
			int i=pst.executeUpdate();
			if(i>0)
				f=true;
		}
		catch(SQLException e){System.out.print(e.toString());}
	     finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
	       ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return f;
	}

	
	
	
	public boolean docUpdate(DoctorDetails docobj) {
		Connection connectionobject=null;
		PreparedStatement pst=null;
		 
		boolean f=false;
		try {
			connectionobject=ConnectionByStaticMethod.getMySQLConnection();
			pst=connectionobject.prepareStatement("update doctor set last_name=?,phone=?,address=?,salary=? where email=?");
			pst.setString(1, docobj.getLast_name());
			pst.setString(2, docobj.getPhone());
			pst.setString(3, docobj.getAddress());
			pst.setString(4, docobj.getSalary());
			pst.setString(5, docobj.getEmail());
			
			int i=pst.executeUpdate();
			if(i>0)
				f=true;
		}
		catch(SQLException e){System.out.print(e.toString());}
	     finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
	       ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return f;
	}
	
	
	public boolean patBooking(AppHistoryDetails appobj) {
		Connection connectionobject=null;
		PreparedStatement pst=null;
		boolean f=false;
		try {
			connectionobject=ConnectionByStaticMethod.getMySQLConnection();
			pst=connectionobject.prepareStatement("insert into appointment_history values(?,?,?,?,?,?,?,?,?)");
			pst.setString(1, appobj.getBooking_ref());
			pst.setString(2, appobj.getP_email());
			pst.setString(3, appobj.getFirst_name());
			pst.setString(4, appobj.getLast_name());
			pst.setString(5, appobj.getD_email());
			pst.setString(6, appobj.getAppointment_date());
			pst.setString(7, appobj.getAppointment_reason());
			pst.setString(8, appobj.getFlag());
			pst.setString(9, appobj.getSalary());
			
			int i=pst.executeUpdate();
			if(i>0)
				f=true;
		}
		catch(SQLException e){System.out.print(e.toString());}
	     finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
	       ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return f;
	}
	

	public boolean patFeedback(FeedbackDetails feedobj) {
		Connection connectionobject=null;
		PreparedStatement pst=null;
		PreparedStatement pst1=null;
		String booking_ref= feedobj.getBooking_ref();
		boolean f=false;
		int j;
		try {
			connectionobject=ConnectionByStaticMethod.getMySQLConnection();
			pst1=connectionobject.prepareStatement("select * from feedback where booking_ref=?");
			pst1.setString(1,feedobj.getBooking_ref());
			ResultSet rs=pst1.executeQuery();
			if(rs.next()) {
				f=false;
			}
			else {
			pst=connectionobject.prepareStatement("insert into feedback values(?,?,?,?,?)");
			pst.setString(1, feedobj.getBooking_ref());
			pst.setString(2, feedobj.getP_email());
			pst.setString(3, feedobj.getD_email());
			pst.setString(4, feedobj.getMessage());
			pst.setString(5, feedobj.getFlag());

			int i=pst.executeUpdate();
			if(i>0)
				f=true;
		}
			
			
		}
		
		catch(SQLException e){System.out.print(e.toString());}
	     finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);

		   ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return f;
	}
	
	

	public boolean patContact(PatContactDetails patconobj) {
		Connection connectionobject=null;
		PreparedStatement pst=null;
		boolean f=false;
		int j;
		try {
			connectionobject=ConnectionByStaticMethod.getMySQLConnection();
			pst=connectionobject.prepareStatement("insert into pat_contact values(?,?,?,?)");
			pst.setString(1, patconobj.getFirst_name());
			pst.setString(2, patconobj.getP_email());
			pst.setString(3, patconobj.getMessage());
			pst.setString(4, patconobj.getReference_no());


			int i=pst.executeUpdate();
			if(i>0)
				f=true;
		}
			
			
		
		catch(SQLException e){System.out.print(e.toString());}
	     finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);

		   ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return f;
	}
	
	public boolean docTestAssign(DiagnosticTestDetails diagobj) {
		Connection connectionobject=null;
		PreparedStatement pst=null;
		boolean f=false;
		int j;
		try {
			connectionobject=ConnectionByStaticMethod.getMySQLConnection();
			pst=connectionobject.prepareStatement("insert into diagnostic_test values(?,?,?,?,?,?,?,?,?)");
			pst.setString(1, diagobj.getTest_id());
			pst.setString(2, diagobj.getBooking_ref());
			pst.setString(3, diagobj.getP_email());
			pst.setString(4, diagobj.getD_email());
			pst.setString(5, diagobj.getTest_name());
			pst.setString(6, diagobj.getTest_price());
			pst.setString(7, diagobj.getFlag());
			pst.setString(8, diagobj.getTest_date());
			pst.setString(9, diagobj.getPayment_date());


			int i=pst.executeUpdate();
			if(i>0)
				f=true;
		}
			
			
		
		catch(SQLException e){System.out.print(e.toString());}
	     finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);

		   ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return f;
	}

	public String patTest(DiagnosticTestDetails diagobj) {
		Connection connectionobject=null;
		PreparedStatement pst=null;
		String f="false";
		LocalDate localDate = LocalDate.now();//For reference
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String today = localDate.format(formatter);
		int j;
		try {
			connectionobject=ConnectionByStaticMethod.getMySQLConnection();
			System.out.println("U Entered inside"+diagobj.getTest_date()+""+diagobj.getTest_id()+" "+diagobj.getFlag());

			if(diagobj.getFlag().equals("true")) {
			pst=connectionobject.prepareStatement("update diagnostic_test set flag='false',test_date=?,payment_date=? where test_id=?");
			pst.setString(1, diagobj.getTest_date());
			pst.setString(2,today);
			pst.setString(3,diagobj.getTest_id() );
			System.out.println("U Entered"+diagobj.getTest_date()+""+diagobj.getTest_id()+""+today);


			int i=pst.executeUpdate();

			if(i>0)
				f="true";
		}
			else {
				f="The Bill is being already generated";
			}
		}
		catch(SQLException e){System.out.print(e.toString());}
	     finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);

		   ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return f;
	}
	
			
		public String patAlreadyBookedTest(DiagnosticTestDetails diagobj) {
			Connection connectionobject=null;
			PreparedStatement pst=null;
			String f="false";
			ResultSet rs=null;
			String payment_date=null;
			
			int j;
			try {
				connectionobject=ConnectionByStaticMethod.getMySQLConnection();
				pst=connectionobject.prepareStatement("select payment_date from diagnostic_test where test_id=?");
				pst.setString(1, diagobj.getTest_id()); 
				System.out.println("U Entered"+diagobj.getTest_id());


				rs=pst.executeQuery();
				while(rs.next()) {
					payment_date=rs.getString(1);
				}

			}
		
		
		catch(SQLException e){System.out.print(e.toString());}
	     finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);

		   ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return payment_date;
	}
	
	
	public boolean userRegistration2(PatientDetails patobj) 
	{
		Connection connectionobject = null;
		PreparedStatement pst = null;
		boolean f = false;
		try
		{
	         connectionobject = ConnectionByStaticMethod.getMySQLConnection();
		     
	        //connectionobject = ConnectionFactoryBySingletonClass.getConnection();
	      	 
	         //JDBC STEP 3
	         //CREATE QUERY
	         pst = connectionobject.prepareStatement("insert into patient values(?,?,?,?,?,?,?,?)");
		     
		     pst.setString(1, patobj.getEmail());
		     pst.setString(2, patobj.getPassword());
		     pst.setString(3, patobj.getFirst_name());
		     pst.setString(4, patobj.getLast_name());
		     pst.setString(5, patobj.getPhone());
		     pst.setString(6, patobj.getAddress());
		     pst.setString(7, patobj.getState());
		     pst.setString(8, patobj.getZip());

		     
		     //JDBC STEP 4
		     //EXECUTE QUERY
		     int i = pst.executeUpdate();		     
		     if(i > 0 )
		    	 f = true;
           
	    }catch(SQLException e){System.out.print(e.toString());}
	     finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
	       ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return f;
	}
	
	
	public String userLogin(String email,String password) 
	{
		Connection connectionobject = null;
		PreparedStatement pst = null;
		ResultSet rs =null;
		String first_name = null,last_name=null;
		try
		{
	         connectionobject = ConnectionByStaticMethod.getMySQLConnection();
		     
	        // connectionobject = ConnectionFactoryBySingletonClass.getConnection();
	      	         
	         pst = connectionobject.prepareStatement("select * from doctor where email= ? and password =?");
		     
		     pst.setString(1, email);
		     pst.setString(2, password);
		    	     
		     rs = pst.executeQuery();
		     
		     if(rs.next())
		     {
		    	 if(rs.getString(14).equals("true")) {
		    	 first_name = rs.getString(3);
		    	 last_name=rs.getString(4);
		    	 }
		     }
	    }catch(SQLException e){System.out.print(e.toString());}
	     finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
		   ConnectionByStaticMethod.closeMySQLResulsetConnection(rs);
	       ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
	    return first_name;
	}

	public boolean docFeedbackEnable(String booking_ref) 
	{
		Connection connectionobject = null;
		PreparedStatement pst = null;
		ResultSet rs =null;
		boolean f=false;

		try
		{
	         connectionobject = ConnectionByStaticMethod.getMySQLConnection();
		     
	        // connectionobject = ConnectionFactoryBySingletonClass.getConnection();
	      	         
	         pst = connectionobject.prepareStatement("update appointment_history set flag='false' where booking_ref=?");
		     
		     pst.setString(1, booking_ref);
		    	     
		     int i=pst.executeUpdate();
				if(i>0)
					f=true;
		
		     
		    
		     
	    }catch(SQLException e){System.out.print(e.toString());}
	     finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
		   ConnectionByStaticMethod.closeMySQLResulsetConnection(rs);
	       ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return f;
	}
	
	public boolean docEnable(String d_email) 
	{
		Connection connectionobject = null;
		PreparedStatement pst = null;
		ResultSet rs =null;
		boolean f=false;

		try
		{
	         connectionobject = ConnectionByStaticMethod.getMySQLConnection();
		     
	        // connectionobject = ConnectionFactoryBySingletonClass.getConnection();
	      	         
	         pst = connectionobject.prepareStatement("update doctor set flag='true' where email=?");
		     
		     pst.setString(1, d_email);
		    	     
		     int i=pst.executeUpdate();
				if(i>0)
					f=true;
		
		     
		    
		     
	    }catch(SQLException e){System.out.print(e.toString());}
	     finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
		   ConnectionByStaticMethod.closeMySQLResulsetConnection(rs);
	       ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return f;
	}
	
	
	public DoctorDetails searchUser(String emailid) 
	{
		Connection connectionobject = null;
		PreparedStatement pst = null;
		ResultSet rs =null;
		DoctorDetails docobj = null;
		try
		{
	        connectionobject = ConnectionByStaticMethod.getMySQLConnection();
		     
	        // connectionobject = ConnectionFactoryBySingletonClass.getConnection();
	      	         
	         pst = connectionobject.prepareStatement("select * from custreg where email= ?");
		     
		     pst.setString(1, emailid);
		    		    	     
		     rs = pst.executeQuery();
		     
		     if(rs.next())
		     {
		    	 docobj = new DoctorDetails();
		    	 
		    	docobj.setEmail(rs.getString(1));
		    	
		    	docobj.setFirst_name(rs.getString(3));
		    	docobj.setLast_name(rs.getString(4));
		    	docobj.setPhone(rs.getString(5));
		    	docobj.setDesignation(rs.getString(6));
		    	docobj.setAddress(rs.getString(7));
		    	docobj.setDate(rs.getString(8));
		    	docobj.setTime(rs.getString(9));
		    	docobj.setDepartment(rs.getString(12));
		    	docobj.setZip(rs.getString(10));
		    	docobj.setState(rs.getString(11));
		    	
		 		
		    	
			     
		 		
		     }
	    }catch(SQLException e){System.out.print(e.toString());}
		 finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
		   ConnectionByStaticMethod.closeMySQLResulsetConnection(rs);
	       ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return docobj;
	}
	
	public int count(AppHistoryDetails appobj) {
		Connection con = null;
		int c1 = 0;
		PreparedStatement pst1;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();

			pst1 = con.prepareStatement("select count(*) from appointment_history where d_email=? and appointment_date=?");
		 
	     pst1.setString(1, appobj.getD_email());
	     pst1.setString(2,appobj.getAppointment_date());
	     
	     ResultSet rs1 = pst1.executeQuery();
	     while(rs1.next()) {
	    	 String count1=rs1.getString(1);
	    	   c1 = Integer.parseInt(count1);
	     }
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c1;
	}

	
	public int income_year(AppHistoryDetails appobj) {
		Connection con = null;
		int c1 = 0;
		int sum=0;
		PreparedStatement pst1;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();

			pst1 = con.prepareStatement("select * from appointment_history");
			
			
	     
	     ResultSet rs = pst1.executeQuery();
	     while(rs.next()) {
		     
		    	 String str = rs.getString(6);
		         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		         LocalDate dateTime = LocalDate.parse(str, formatter);
		         int year1 = dateTime.getYear();
			    	String y1= Integer.toString(year1);

			    	DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			         LocalDate dateTime1 = LocalDate.parse(appobj.getAppointment_date(), formatter);
			         int year = dateTime.getYear();
				    	String y= Integer.toString(year);
			    	


		    	 if(y.equals(y1) ){
	    	 String count1=rs.getString(9);
	    	   c1 = Integer.parseInt(count1);
	    	   sum+= c1;
	     }
		}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum;
	}
	
	public boolean deleteUser(String booking_ref) 
	{
		Connection connectionobject = null;
		PreparedStatement pst = null;
		boolean f = false;
		try
		{
	         connectionobject = ConnectionByStaticMethod.getMySQLConnection();
		     
	        // connectionobject = ConnectionFactoryBySingletonClass.getConnection();
	      	         
	         pst = connectionobject.prepareStatement("delete from appointment_history where booking_ref= ?");
		     
		     pst.setString(1, booking_ref);
		    		    	     
		     int noofrecorddeleted = pst.executeUpdate();
		     
		     if(noofrecorddeleted > 0)
		     {
		    	 f = true;
		     }
	    }catch(SQLException e){System.out.print(e.toString());}
		finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
		   ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return f;
	}
	
	public boolean deleteDoctor(String emailid) 
	{
		Connection connectionobject = null;
		PreparedStatement pst = null;
		boolean f = false;
		try
		{
	         connectionobject = ConnectionByStaticMethod.getMySQLConnection();
		     
	        // connectionobject = ConnectionFactoryBySingletonClass.getConnection();
	      	         
	         pst = connectionobject.prepareStatement("delete from doctor where email= ?");
		     
		     pst.setString(1, emailid);
		    		    	     
		     int noofrecorddeleted = pst.executeUpdate();
		     
		     if(noofrecorddeleted > 0)
		     {
		    	 f = true;
		     }
	    }catch(SQLException e){System.out.print(e.toString());}
		finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
		   ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return f;
	}
	
	


public int income_month(AppHistoryDetails appobj) {
	Connection con = null;
	int c1 = 0;
	int sum=0;
	PreparedStatement pst1;
	try {
		con = ConnectionByStaticMethod.getMySQLConnection();

		pst1 = con.prepareStatement("select * from appointment_history");
		
		
     
     ResultSet rs = pst1.executeQuery();
     while(rs.next()) {
	     
	    	 String str = rs.getString(6);
	         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	         YearMonth dateTime = YearMonth.parse(str, formatter);
	         int month1 = dateTime.getMonthValue();
	         
		    	String m1= Integer.toString(month1);
		    	
		    	DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM");
		        YearMonth dateTime1 = YearMonth.parse(appobj.getAppointment_date(), formatter1);
		    int month = dateTime1.getMonthValue();
		    String m= Integer.toString(month);
		    	


	    	 if(m.equals(m1) ){
    	 String count1=rs.getString(9);
    	   c1 = Integer.parseInt(count1);
    	   sum+= c1;
     }
	}
	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return sum;
}

	public int income_day(AppHistoryDetails appobj) {
		Connection con = null;
		int c1 = 0;
		PreparedStatement pst1;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();

			pst1 = con.prepareStatement("select sum(salary) from appointment_history where appointment_date=?");
			
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	         LocalDate dateTime1 = LocalDate.parse(appobj.getAppointment_date(), formatter1);	   
	    	 String formattedString = dateTime1.format(formatter1);
	    	  pst1.setString(1, formattedString);
	     
	     ResultSet rs1 = pst1.executeQuery();
	     while(rs1.next()) {
	    	 String count1=rs1.getString(1);
	    	   c1 = Integer.parseInt(count1);
	     }
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c1;
	}


public int income_diag_month(AppHistoryDetails appobj) {
	Connection con = null;
	int c1 = 0;
	int sum=0;
	PreparedStatement pst1;
	try {
		con = ConnectionByStaticMethod.getMySQLConnection();

		pst1 = con.prepareStatement("select * from diagnostic_test");
		
		
     
     ResultSet rs = pst1.executeQuery();
     while(rs.next()) {
    	 System.out.println("U: "+rs.getString(7)+" "+rs.getString(8));
	         if(rs.getString(7).equals("false")) {
	        	 System.out.println("U: "+rs.getString(7)+" "+rs.getString(8));
	    	 String str = rs.getString(8);
	         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	         YearMonth dateTime = YearMonth.parse(str, formatter);
	         int month1 = dateTime.getMonthValue();
	         
		    	String m1= Integer.toString(month1);
		    	
		    	DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM");
		        YearMonth dateTime1 = YearMonth.parse(appobj.getAppointment_date(), formatter1);
		    int month = dateTime1.getMonthValue();
		    String m= Integer.toString(month);
		    	


	    	 if(m.equals(m1) ){
    	 String count1=rs.getString(6);
    	   c1 = Integer.parseInt(count1);
    	   sum+= c1;
     }
	}
	}
	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return sum;
}
	

public int income_diag_year(AppHistoryDetails appobj) {
	Connection con = null;
	int c1 = 0;
	int sum=0;
	PreparedStatement pst1;
	try {
		con = ConnectionByStaticMethod.getMySQLConnection();

		pst1 = con.prepareStatement("select * from diagnostic_test");
		
		
     
     ResultSet rs = pst1.executeQuery();
     while(rs.next()) {
	         if(rs.getString(7).equals("false")) {
	        	 String str = rs.getString(8);
		         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		         LocalDate dateTime = LocalDate.parse(str, formatter);
		         int year1 = dateTime.getYear();
			    	String y1= Integer.toString(year1);
	         
		    	String y= appobj.getAppointment_date();
		    	
		    	
		    	


	    	 if(y.equals(y1) ){
    	 String count1=rs.getString(6);
    	   c1 = Integer.parseInt(count1);
    	   sum+= c1;
     }
	}
	}
	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return sum;
}
	


	public int income(AppHistoryDetails appobj) {
		Connection con = null;
		int c1 = 0;
		PreparedStatement pst1;
		try {
			con = ConnectionByStaticMethod.getMySQLConnection();

			pst1 = con.prepareStatement("select salary from doctor in(select *  from appointment_history where appointment_date=?)");
			LocalDate localDate = LocalDate.now();//For reference
	    	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    	 String formattedString = localDate.format(formatter);
	     pst1.setString(1, formattedString);
	     
	     ResultSet rs1 = pst1.executeQuery();
	     while(rs1.next()) {
	    	 String count1=rs1.getString(1);
	    	   c1 = Integer.parseInt(count1);
	     }
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c1;
	}
	

	public String previousBookingCheck(AppHistoryDetails appobj) {
		Connection connectionobject=null;
		PreparedStatement pst=null;
		String f="false";
		ResultSet rs=null;
		int c = 0;
		try {
			connectionobject = ConnectionByStaticMethod.getMySQLConnection();
			c=count(appobj);
		     
			
	      	         
	         pst = connectionobject.prepareStatement("select * from appointment_history where d_email=? and appointment_date= ?");
		     pst.setString(1, appobj.getD_email());
		     pst.setString(2, appobj.getAppointment_date());
		    		    	     
		     rs = pst.executeQuery();
		     

		     if(c<3) {
		      while(rs.next())
		     {
		    	 AppHistoryDetails appobj1 = new AppHistoryDetails();
		    	 
		    	appobj1.setBooking_ref(rs.getString(1));
		    	appobj1.setP_email(rs.getString(2));
		    	appobj1.setFirst_name(rs.getString(3));
		    	appobj1.setLast_name(rs.getString(4));
		    	appobj1.setD_email(rs.getString(5));
		    	appobj1.setAppointment_date(rs.getString(6));
		    	appobj1.setAppointment_reason(rs.getString(7));
		    	appobj1.setFlag(rs.getString(8));
		    	if(appobj1.getAppointment_date().equals(appobj.getAppointment_date())&&appobj1.getP_email().equals(appobj.getP_email())) {
		    		f="true";
		    	}
		    	else {
		    		f="false";
		    	}
		    	
		    	}
		     }
		
		
		else {
			f="Not Available";
		}
	
		}
		     
		     
	    catch(SQLException e){System.out.print(e.toString());}
		 finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
		   ConnectionByStaticMethod.closeMySQLResulsetConnection(rs);
	       ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		return f;
	}
	
	public String userLogin2(String email,String password) 
	{
		Connection connectionobject = null;
		PreparedStatement pst = null;
		ResultSet rs =null;
		String first_name=null,last_name=null;
		try
		{
	         connectionobject = ConnectionByStaticMethod.getMySQLConnection();
		     
	        // connectionobject = ConnectionFactoryBySingletonClass.getConnection();
	      	         
	         pst = connectionobject.prepareStatement("select * from patient where email= ? and password =?");
		     
		     pst.setString(1, email);
		     pst.setString(2, password);
		    	     
		     rs = pst.executeQuery();
		     
		     if(rs.next())
		     {
		    	 first_name = rs.getString(3);
		    	 last_name=rs.getString(4);
		     }
	    }catch(SQLException e){System.out.print(e.toString());}
	     finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
		   ConnectionByStaticMethod.closeMySQLResulsetConnection(rs);
	       ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
	    return first_name;
	}
	
	public List<PatientDetails> otherDate(String p_email) {
		Connection connectionobject=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		List<PatientDetails> pactlist= new ArrayList<PatientDetails>();

		try {
			connectionobject=ConnectionByStaticMethod.getMySQLConnection();
			pst=connectionobject.prepareStatement("select appointment_date from appointment_history where p_email=?");
			pst.setString(2, p_email);
			rs=pst.executeQuery();
			
			if(rs.next()) {
				PatientDetails patobj=new PatientDetails();
				patobj.setAppointment_date(rs.getString(6));
				pactlist.add(patobj);
			}
			
		}
		catch(SQLException e){System.out.print(e.toString());}
		finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
		   ConnectionByStaticMethod.closeMySQLResulsetConnection(rs);
	       ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return pactlist;

		
	}
	public List<DoctorDetails> fetchAllRecord(String department) 
	{
		Connection connectionobject = null;
		PreparedStatement pst = null;
		ResultSet rs =null;
		List<DoctorDetails> doctlist= new ArrayList<DoctorDetails>();
		try
		{
			
	         connectionobject = ConnectionByStaticMethod.getMySQLConnection();
		     
	        // Connection connectionobject = ConnectionFactoryBySingletonClass.getConnection();
	      	 	         
	         if (department.length()>0)
	         {
	            pst = connectionobject.prepareStatement("select * from doctor where department= ?");
		        pst.setString(1, department);
	         }
	         else
	         {
	        	 pst = connectionobject.prepareStatement("select * from doctor");
	         }
		     
	         rs = pst.executeQuery();
		     
		     while(rs.next())
		     {
		    	 //CREATE CUSTOMER DETAIL OBJECT
		    	 DoctorDetails docobj = new DoctorDetails();
		    	 
		    	 //FETCH FROM RESULTSET & STORE VALUE WITIN OBJECT
		    	docobj.setFirst_name(rs.getString(3));
		 		
		 		docobj.setLast_name(rs.getString(4));
		 		docobj.setEmail(rs.getString(1));
		 		docobj.setPhone(rs.getString(5));
		 		docobj.setDesignation(rs.getString(6));
		 		docobj.setAddress(rs.getString(7));
		 		docobj.setDate(rs.getString(8));
		 		docobj.setTime(rs.getString(9));
		 		docobj.setZip(rs.getString(10));
		 		docobj.setState(rs.getString(11));
				docobj.setDepartment(rs.getString(12));
				docobj.setSalary(rs.getString(13));
				docobj.setFlag(rs.getString(14));
				
				//ADDED TO THE ARRAYLIST
				doctlist.add(docobj);
				
		     }
	    }catch(SQLException e){System.out.print(e.toString());}
		finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
		   ConnectionByStaticMethod.closeMySQLResulsetConnection(rs);
	       ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return doctlist;
	}
	
	public List<FeedbackDetails> fetchAllRecordfeedback(String d_email) 
	{
		Connection connectionobject = null;
		PreparedStatement pst = null;
		ResultSet rs =null;
		List<FeedbackDetails> feedlist= new ArrayList<FeedbackDetails>();
		try
		{
			
	         connectionobject = ConnectionByStaticMethod.getMySQLConnection();
		     
	        // Connection connectionobject = ConnectionFactoryBySingletonClass.getConnection();
	      	 	         
	         if (d_email.length()>0)
	         {
	            pst = connectionobject.prepareStatement("select * from feedback where d_email= ?");
		        pst.setString(1, d_email);
	         }
	         
	         rs = pst.executeQuery();
		     
		     while(rs.next())
		     {
		    	 //CREATE CUSTOMER DETAIL OBJECT
		    	 FeedbackDetails feedobj = new FeedbackDetails();
		    	 
		    	 //FETCH FROM RESULTSET & STORE VALUE WITIN OBJECT
		    	feedobj.setBooking_ref(rs.getString(1));
		 		
		 		feedobj.setP_email(rs.getString(2));
		 		feedobj.setD_email(rs.getString(3));
		 		feedobj.setMessage(rs.getString(4));
		 		
				
				//ADDED TO THE ARRAYLIST
				feedlist.add(feedobj);
				
		     }
	    }catch(SQLException e){System.out.print(e.toString());}
		finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
		   ConnectionByStaticMethod.closeMySQLResulsetConnection(rs);
	       ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return feedlist;
	}
	
	
	
	
	public List<AppHistoryDetails> fetchAllRecord3(String emailid) 
	{
		Connection connectionobject = null;
		PreparedStatement pst = null;
		ResultSet rs =null;
		List<AppHistoryDetails> applist= new ArrayList<AppHistoryDetails>();
		try
		{
			
	         connectionobject = ConnectionByStaticMethod.getMySQLConnection();
		     
	        // Connection connectionobject = ConnectionFactoryBySingletonClass.getConnection();
	      	 	         
	         if (emailid!="null")
	         {
	            pst = connectionobject.prepareStatement("select * from appointment_history where p_email=? ");
		        pst.setString(1, emailid);
	         }
	         
	         
	         rs = pst.executeQuery();	 
	        
		     while(rs.next())
		     {
		    	 
		    	
		    	
		    	 //CREATE CUSTOMER DETAIL OBJECT
		    	 AppHistoryDetails appobj = new AppHistoryDetails();
		    	 
		    	 //FETCH FROM RESULTSET & STORE VALUE WITIN OBJECT
		    	appobj.setBooking_ref(rs.getString(1));
		 		
		 		appobj.setP_email(rs.getString(2));
		 		appobj.setFirst_name(rs.getString(3));
		 		appobj.setLast_name(rs.getString(4));
		 		appobj.setD_email(rs.getString(5));
		 		appobj.setAppointment_date(rs.getString(6));
		 		appobj.setAppointment_reason(rs.getString(7));
		 		appobj.setFlag(rs.getString(8));
		 		
				//ADDED TO THE ARRAYLIST
				applist.add(appobj);
				
		     
		     
		     }
	    }catch(SQLException e){System.out.print(e.toString());}
		finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
		   ConnectionByStaticMethod.closeMySQLResulsetConnection(rs);
	       ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return applist;
	}

	public List<AppHistoryDetails> fetchAllRecord2(String emailid) 
	{
		Connection connectionobject = null;
		PreparedStatement pst = null;
		ResultSet rs =null;
		List<AppHistoryDetails> applist= new ArrayList<AppHistoryDetails>();
		try
		{
			
	         connectionobject = ConnectionByStaticMethod.getMySQLConnection();
		     
	        // Connection connectionobject = ConnectionFactoryBySingletonClass.getConnection();
	      	 	         
	         if (emailid!="null")
	         {
	            pst = connectionobject.prepareStatement("select * from appointment_history where p_email=? ");
		        pst.setString(1, emailid);
	         }
	         
	         
	         rs = pst.executeQuery();	 
	        
		     while(rs.next())
		     {
		    	 
		    	 String str = rs.getString(6);
		         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		         LocalDate dateTime = LocalDate.parse(str, formatter);
		    	 if(LocalDate.now().isBefore(dateTime) ){
		    	
		    	 //CREATE CUSTOMER DETAIL OBJECT
		    	 AppHistoryDetails appobj = new AppHistoryDetails();
		    	 
		    	 //FETCH FROM RESULTSET & STORE VALUE WITIN OBJECT
		    	appobj.setBooking_ref(rs.getString(1));
		 		
		 		appobj.setP_email(rs.getString(2));
		 		appobj.setFirst_name(rs.getString(3));
		 		appobj.setLast_name(rs.getString(4));
		 		appobj.setD_email(rs.getString(5));
		 		appobj.setAppointment_date(rs.getString(6));
		 		appobj.setAppointment_reason(rs.getString(7));
		 		appobj.setFlag(rs.getString(8));
		 		
				//ADDED TO THE ARRAYLIST
				applist.add(appobj);
				
		     
		     }
		     }
	    }catch(SQLException e){System.out.print(e.toString());}
		finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
		   ConnectionByStaticMethod.closeMySQLResulsetConnection(rs);
	       ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return applist;
	}

	
	public List<AppHistoryDetails> fetchAllRecordmonth(String appointment_date,String d_email) 
	{
		Connection connectionobject = null;
		PreparedStatement pst = null;
		ResultSet rs =null;
		List<AppHistoryDetails> applist= new ArrayList<AppHistoryDetails>();

		try
		{
			
	         connectionobject = ConnectionByStaticMethod.getMySQLConnection();
		     
	        // Connection connectionobject = ConnectionFactoryBySingletonClass.getConnection();
	      	 	         
	         if (d_email!="null")
	         {
	            pst = connectionobject.prepareStatement("select * from appointment_history where d_email=? ");
		        pst.setString(1, d_email);
		        
	         }
	        
	         
	         rs = pst.executeQuery();	 
	        
		     while(rs.next())
		     {

		    	 String str = rs.getString(6);
		         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		         LocalDate dateTime = LocalDate.parse(str, formatter);
		         int month1 = dateTime.getMonthValue();
			    	String m1= Integer.toString(month1);


			    	DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM");
			         YearMonth dateTime1 = YearMonth.parse(appointment_date, formatter1);
		    	 int month = dateTime1.getMonthValue();
		    	String m= Integer.toString(month);


		    	 if(m.equals(m1) ){
		    	
		    	 //CREATE CUSTOMER DETAIL OBJECT
		    	 AppHistoryDetails appobj = new AppHistoryDetails();
		    	 
		    	 //FETCH FROM RESULTSET & STORE VALUE WITIN OBJECT
		    	appobj.setBooking_ref(rs.getString(1));
		 		
		 		appobj.setP_email(rs.getString(2));
		 		appobj.setFirst_name(rs.getString(3));
		 		appobj.setLast_name(rs.getString(4));
		 		appobj.setD_email(rs.getString(5));
		 		appobj.setAppointment_date(rs.getString(6));
		 		appobj.setAppointment_reason(rs.getString(7));
		 		appobj.setFlag(rs.getString(8));
		 		
				//ADDED TO THE ARRAYLIST
				applist.add(appobj);
		    	 }
		     
		     
		     }
	    }catch(SQLException e){System.out.print(e.toString());}
		finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
		   ConnectionByStaticMethod.closeMySQLResulsetConnection(rs);
	       ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return applist;
	}
	

	public List<AppHistoryDetails> fetchAllRecordyear(String appointment_date,String d_email) 
	{
		Connection connectionobject = null;
		PreparedStatement pst = null;
		ResultSet rs =null;
		List<AppHistoryDetails> applist= new ArrayList<AppHistoryDetails>();

		try
		{
			
	         connectionobject = ConnectionByStaticMethod.getMySQLConnection();
		     
	        // Connection connectionobject = ConnectionFactoryBySingletonClass.getConnection();
	      	 	         
	         if (d_email!="null")
	         {
	            pst = connectionobject.prepareStatement("select * from appointment_history where d_email=? ");
		        pst.setString(1, d_email);
		        
	         }
	        
	         
	         rs = pst.executeQuery();	 
	        
		     while(rs.next())
		     {

		    	 String str = rs.getString(6);
		         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		         LocalDate dateTime = LocalDate.parse(str, formatter);
		         int year1 = dateTime.getYear();
			    	String y1= Integer.toString(year1);


			    	


		    	 if(appointment_date.equals(y1) ){
		    	
		    	 //CREATE CUSTOMER DETAIL OBJECT
		    	 AppHistoryDetails appobj = new AppHistoryDetails();
		    	 
		    	 //FETCH FROM RESULTSET & STORE VALUE WITIN OBJECT
		    	appobj.setBooking_ref(rs.getString(1));
		 		
		 		appobj.setP_email(rs.getString(2));
		 		appobj.setFirst_name(rs.getString(3));
		 		appobj.setLast_name(rs.getString(4));
		 		appobj.setD_email(rs.getString(5));
		 		appobj.setAppointment_date(rs.getString(6));
		 		appobj.setAppointment_reason(rs.getString(7));
		 		appobj.setFlag(rs.getString(8));
		 		
				//ADDED TO THE ARRAYLIST
				applist.add(appobj);
		    	 }
		     
		     
		     }
	    }catch(SQLException e){System.out.print(e.toString());}
		finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
		   ConnectionByStaticMethod.closeMySQLResulsetConnection(rs);
	       ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return applist;
	}
	
	public List<AppHistoryDetails> fetchAllRecorddate(String appointment_date,String d_email) 
	{
		Connection connectionobject = null;
		PreparedStatement pst = null;
		ResultSet rs =null;
		List<AppHistoryDetails> applist= new ArrayList<AppHistoryDetails>();

		try
		{
			
	         connectionobject = ConnectionByStaticMethod.getMySQLConnection();
		     
	        // Connection connectionobject = ConnectionFactoryBySingletonClass.getConnection();
	      	 	         
	         if (d_email!="null")
	         {
	            pst = connectionobject.prepareStatement("select * from appointment_history where d_email=? ");
		        pst.setString(1, d_email);
		        
	         }
	        
	         
	         rs = pst.executeQuery();	 
	        
		     while(rs.next())
		     {

		    	 String str = rs.getString(6);
		         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		         LocalDate dateTime = LocalDate.parse(str, formatter);

		         DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		         LocalDate dateTime1 = LocalDate.parse(appointment_date, formatter1);
			    	


		    	 if(dateTime1.equals(dateTime) ){
		    	
		    	 //CREATE CUSTOMER DETAIL OBJECT
		    	 AppHistoryDetails appobj = new AppHistoryDetails();
		    	 
		    	 //FETCH FROM RESULTSET & STORE VALUE WITIN OBJECT
		    	appobj.setBooking_ref(rs.getString(1));
		 		
		 		appobj.setP_email(rs.getString(2));
		 		appobj.setFirst_name(rs.getString(3));
		 		appobj.setLast_name(rs.getString(4));
		 		appobj.setD_email(rs.getString(5));
		 		appobj.setAppointment_date(rs.getString(6));
		 		appobj.setAppointment_reason(rs.getString(7));
		 		appobj.setFlag(rs.getString(8));
		 		appobj.setSalary(rs.getString(9));

		 		
				//ADDED TO THE ARRAYLIST
				applist.add(appobj);
		    	 }
		     
		     
		     }
	    }catch(SQLException e){System.out.print(e.toString());}
		finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
		   ConnectionByStaticMethod.closeMySQLResulsetConnection(rs);
	       ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return applist;
	}
	
	public List<AppHistoryDetails> fetchAllRecordtoday(AppHistoryDetails appobj) 
	{
		Connection connectionobject = null;
		PreparedStatement pst = null;
		ResultSet rs =null;
		List<AppHistoryDetails> applist= new ArrayList<AppHistoryDetails>();

		try
		{
			
	         connectionobject = ConnectionByStaticMethod.getMySQLConnection();
		     
	        // Connection connectionobject = ConnectionFactoryBySingletonClass.getConnection();
	      	 	         
	        
	            pst = connectionobject.prepareStatement("select * from appointment_history");
		        

	        
	         
	         rs = pst.executeQuery();	 
	        
		     while(rs.next())
		     {

		    	 String str = rs.getString(6);
		    	 DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		         LocalDate dateTime1 = LocalDate.parse(appobj.getAppointment_date(), formatter1);	   
		    	 String formattedString = dateTime1.format(formatter1);
		    	

		    	 if(formattedString.equals(str) ){
		    	
		    	 //CREATE CUSTOMER DETAIL OBJECT
		    	 
		    	 //FETCH FROM RESULTSET & STORE VALUE WITIN OBJECT
		    	appobj.setBooking_ref(rs.getString(1));
		 		
		 		appobj.setP_email(rs.getString(2));
		 		appobj.setFirst_name(rs.getString(3));
		 		appobj.setLast_name(rs.getString(4));
		 		appobj.setD_email(rs.getString(5));
		 		appobj.setAppointment_date(rs.getString(6));
		 		appobj.setAppointment_reason(rs.getString(7));
		 		appobj.setFlag(rs.getString(8));
		 		
				//ADDED TO THE ARRAYLIST
				applist.add(appobj);
		    	 }
		     
		     
		     }
	    }catch(SQLException e){System.out.print(e.toString());}
		finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
		   ConnectionByStaticMethod.closeMySQLResulsetConnection(rs);
	       ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return applist;
	}
	
	
	public List<AppHistoryDetails> fetchAllRecordtoday2() 
	{
		Connection connectionobject = null;
		PreparedStatement pst = null;
		ResultSet rs =null;
		List<AppHistoryDetails> applist= new ArrayList<AppHistoryDetails>();
		AppHistoryDetails appobj=new AppHistoryDetails();
		try
		{
			
	         connectionobject = ConnectionByStaticMethod.getMySQLConnection();
		     
	        // Connection connectionobject = ConnectionFactoryBySingletonClass.getConnection();
	      	 	         
	        
	            pst = connectionobject.prepareStatement("select * from appointment_history");
		        

	        
	         
	         rs = pst.executeQuery();	 
	        
		     while(rs.next())
		     {
		    	 LocalDate localDate = LocalDate.now();//For reference
		    	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    	 String formattedString = localDate.format(formatter);
		    	

		    	 if(formattedString.equals(rs.getString(6)) ){
		    	
		    	 //CREATE CUSTOMER DETAIL OBJECT
		    	 
		    	 //FETCH FROM RESULTSET & STORE VALUE WITIN OBJECT
		    	appobj.setBooking_ref(rs.getString(1));
		 		
		 		appobj.setP_email(rs.getString(2));
		 		appobj.setFirst_name(rs.getString(3));
		 		appobj.setLast_name(rs.getString(4));
		 		appobj.setD_email(rs.getString(5));
		 		appobj.setAppointment_date(rs.getString(6));
		 		appobj.setAppointment_reason(rs.getString(7));
		 		appobj.setFlag(rs.getString(8));
		 		
				//ADDED TO THE ARRAYLIST
				applist.add(appobj);
		    	 }
		     
		     
		     }
	    }catch(SQLException e){System.out.print(e.toString());}
		finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
		   ConnectionByStaticMethod.closeMySQLResulsetConnection(rs);
	       ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return applist;
	}
	
	public List<AppHistoryDetails> fetchAllRecordtestassign(String d_email) 
	{
		Connection connectionobject = null;
		PreparedStatement pst = null;
		ResultSet rs =null;
		List<AppHistoryDetails> applist= new ArrayList<AppHistoryDetails>();

		try
		{
			
	         connectionobject = ConnectionByStaticMethod.getMySQLConnection();
		     
	        // Connection connectionobject = ConnectionFactoryBySingletonClass.getConnection();
	      	 	         
	         if (d_email!="null")
	         {
	            pst = connectionobject.prepareStatement("select * from appointment_history where d_email=? ");
		        pst.setString(1, d_email);
		        
	         }
	        
	         
	         rs = pst.executeQuery();	 
	        
		     while(rs.next())
		     {

		    	 String str = rs.getString(6);
		         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		         LocalDate dateTime = LocalDate.parse(str, formatter);

		         


		    	 if(dateTime.isBefore(LocalDate.now()) ){
		    	
		    	 //CREATE CUSTOMER DETAIL OBJECT
		    	 AppHistoryDetails appobj = new AppHistoryDetails();
		    	 
		    	 //FETCH FROM RESULTSET & STORE VALUE WITIN OBJECT
		    	 appobj.setBooking_ref(rs.getString(1));
			 		
			 		appobj.setP_email(rs.getString(2));
			 		appobj.setFirst_name(rs.getString(3));
			 		appobj.setLast_name(rs.getString(4));
			 		appobj.setD_email(rs.getString(5));
			 		appobj.setAppointment_date(rs.getString(6));
			 		appobj.setAppointment_reason(rs.getString(7));
			 		appobj.setFlag(rs.getString(8));
		 		
				//ADDED TO THE ARRAYLIST
				applist.add(appobj);
		    	 }
		     
		     
		     }
	    }catch(SQLException e){System.out.print(e.toString());}
		finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
		   ConnectionByStaticMethod.closeMySQLResulsetConnection(rs);
	       ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return applist;
	}
	
	
	public List<DiagnosticTestDetails> fetchAllRecordtest(String p_email) 
	{
		Connection connectionobject = null;
		PreparedStatement pst = null;
		ResultSet rs =null;
		List<DiagnosticTestDetails> diaglist= new ArrayList<DiagnosticTestDetails>();

		try
		{
			
	         connectionobject = ConnectionByStaticMethod.getMySQLConnection();
		     
	        // Connection connectionobject = ConnectionFactoryBySingletonClass.getConnection();
	      	 	         
	         if (p_email!="null")
	         {
	            pst = connectionobject.prepareStatement("select * from diagnostic_test where p_email=? ");
		        pst.setString(1, p_email);
		        
	         }
	        
	         
	         rs = pst.executeQuery();	 
	        
		     while(rs.next())
		     {

		    	 //CREATE CUSTOMER DETAIL OBJECT
		    	 DiagnosticTestDetails diagobj = new DiagnosticTestDetails();
		    	 
		    	 //FETCH FROM RESULTSET & STORE VALUE WITIN OBJECT
		    	 diagobj.setTest_id(rs.getString(1));
			 		
			 		diagobj.setBooking_ref(rs.getString(2));
			 		diagobj.setP_email(rs.getString(3));
			 		diagobj.setD_email(rs.getString(4));
			 		diagobj.setTest_name(rs.getString(5));
			 		diagobj.setTest_price(rs.getString(6));
			 		diagobj.setFlag(rs.getString(7));
		 		
				//ADDED TO THE ARRAYLIST
				diaglist.add(diagobj);
		    	 
		     
		     
		     }
	    }catch(SQLException e){System.out.print(e.toString());}
		finally 
	     {
		   ConnectionByStaticMethod.closeMySQLPreaparedStatementConnection(pst);
		   ConnectionByStaticMethod.closeMySQLResulsetConnection(rs);
	       ConnectionByStaticMethod.closeMySQLConnection(connectionobject);
		 }
		
	    return diaglist;
	}
	
	
	
}
