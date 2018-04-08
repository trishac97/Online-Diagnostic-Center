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

@WebServlet("/doc_test_form")
public class doc_test_form extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public doc_test_form() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		

		
		DiagnosticTestDetails diagobj=new DiagnosticTestDetails();
		Random r=new Random();
        int rand_int1 =r.nextInt(90000);
        String rand=Integer.toString(rand_int1);
        diagobj.setTest_id(rand);
        String booking_ref=request.getParameter("booking_ref");
		diagobj.setBooking_ref(booking_ref);
		diagobj.setP_email(request.getParameter("p_email"));
		diagobj.setD_email(request.getParameter("d_email"));
		diagobj.setTest_name(request.getParameter("test_name"));
		diagobj.setTest_price(request.getParameter("test_price"));
		diagobj.setFlag("true");
 	   CustomerDAO cdaoobj=new CustomerDAO();
 	   boolean reply=cdaoobj.docTestAssign(diagobj);
 	   String msg="";
 	 
 	   if(reply) {
 		   msg="A diagnostic test is assigned for Booking Reference: "+booking_ref+" with Test Id: "+rand;
 	   }
 	   else {
 		   msg="Oops! Something went wrong!";
 	   }
 	   
 	   request.setAttribute("servermsg", msg);
 	   RequestDispatcher rd=request.getRequestDispatcher("/doc_test.jsp");
 	   rd.forward(request, response);
 	   }
	
 	   
	    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	
}
