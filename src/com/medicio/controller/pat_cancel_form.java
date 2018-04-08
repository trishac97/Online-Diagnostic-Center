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
import com.medicio.model.DoctorDetails;
import com.medicio.model.PatientDetails;

@WebServlet("/pat_cancel_form")
public class pat_cancel_form extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public pat_cancel_form() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String booking_ref = request.getParameter("booking_ref");
	      
	      //CREATE OBJECT OF DAO CLASS
     	 CustomerDAO cdaoobj = new CustomerDAO();
	      		
	      //CALL DAO METHOD
	     boolean replyfromdao = cdaoobj.deleteUser(booking_ref);
	     String msg ="";
	        
	       if(replyfromdao)
	       {
	    	  msg ="Appointment Cancelled for booking reference: "+booking_ref;
	        }
	        else
	        {
	        	msg ="Oops! Something went wrong.";
	        	
	        }
	      
	       request.setAttribute("servermsg", msg);
	       RequestDispatcher rd = request.getRequestDispatcher("/pat_cancel.jsp");
     	   rd.forward(request, response);
		
	    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	
}
