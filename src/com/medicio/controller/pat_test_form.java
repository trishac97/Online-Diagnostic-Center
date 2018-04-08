package com.medicio.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.medicio.dao.CustomerDAO;
import com.medicio.model.AppHistoryDetails;
import com.medicio.model.DiagnosticTestDetails;
import com.medicio.model.DoctorDetails;
import com.medicio.model.PatientDetails;

@WebServlet("/pat_test_form")
public class pat_test_form extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public pat_test_form() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
     	DiagnosticTestDetails diagobj=new DiagnosticTestDetails();
       
		diagobj.setTest_id(request.getParameter("test_id"));
		diagobj.setBooking_ref(request.getParameter("booking_ref"));
		diagobj.setP_email(request.getParameter("p_email"));
		diagobj.setD_email(request.getParameter("d_email"));
		diagobj.setTest_name(request.getParameter("test_name"));
		diagobj.setTest_name(request.getParameter("test_price"));
		
		diagobj.setTest_date(request.getParameter("test_date"));
		diagobj.setFlag(request.getParameter("flag"));

		System.out.println("U Entere"+diagobj.getTest_date()+""+diagobj.getTest_id()+""+diagobj.getFlag());

 	   CustomerDAO cdaoobj=new CustomerDAO();
 	   String reply=cdaoobj.patTest(diagobj);
 	   String msg="";
 	 
 	   if(reply=="true") {
 		   msg="Thank You for the payment against Test Id: "+diagobj.getTest_id();
 	   }
 	   else if(reply=="The Bill is being already generated"){
 	 	   String reply1=cdaoobj.patAlreadyBookedTest(diagobj);
 	 	   msg="The Bill is being already generated on "+reply1;
 	   }
 	   else {
 		   msg="Oops! Something went wrong";
 	   }
 	   
 	   request.setAttribute("servermsg", msg);
 	   RequestDispatcher rd=request.getRequestDispatcher("/pat_test.jsp");
 	   rd.forward(request, response);
 	   }
	
 	   
	    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	
}
