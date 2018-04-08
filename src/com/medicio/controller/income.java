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

@WebServlet("/income")
public class income extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public income() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	AppHistoryDetails appobj=new AppHistoryDetails();
	appobj.setAppointment_date(request.getParameter("appointment_date"));	
	System.out.println("U enter:"+request.getParameter("appointment_date"));
 	   CustomerDAO cdaoobj=new CustomerDAO();
 	   int replyfromdao=cdaoobj.income_day(appobj);
 	   String msg="";
 	  
 	 Integer intInstance = new Integer(replyfromdao);      
 	 String number = intInstance.toString();
 	  
 	   request.setAttribute("servermsg", number);
 	   RequestDispatcher rd=request.getRequestDispatcher("/income.jsp");
 	   rd.forward(request, response);
 	   

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
