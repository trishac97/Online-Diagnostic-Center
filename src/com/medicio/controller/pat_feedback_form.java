package com.medicio.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.medicio.dao.CustomerDAO;
import com.medicio.model.AppHistoryDetails;
import com.medicio.model.DoctorDetails;
import com.medicio.model.FeedbackDetails;

@WebServlet("/pat_feedback_form")
public class pat_feedback_form extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public pat_feedback_form() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	FeedbackDetails feedobj=new FeedbackDetails();
	String booking_ref=request.getParameter("booking_ref");
		feedobj.setBooking_ref(request.getParameter("booking_ref"));
		
		feedobj.setD_email(request.getParameter("d_email"));
		feedobj.setP_email(request.getParameter("p_email"));
		feedobj.setMessage(request.getParameter("message"));
		feedobj.setFlag("true");

		
 	   CustomerDAO cdaoobj=new CustomerDAO();
 	   boolean replyfromdao=cdaoobj.patFeedback(feedobj);
 	   String msg="";
 	   
 	   
 	   if(replyfromdao) {
 		   msg="Feedback Submitted for Reference number: "+booking_ref;
 	   }
 	   else {
 		   msg="Feedback Already been submitted!";
 	   }
 	  
 	   request.setAttribute("servermsg", msg);
 	   RequestDispatcher rd=request.getRequestDispatcher("/pat_feedback.jsp");
 	   rd.forward(request, response);
 	   

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
