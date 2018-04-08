package com.medicio.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

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

@WebServlet("/pat_feedback")
public class pat_feedback extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public pat_feedback() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
 	   HttpSession session = request.getSession(true);
       String email=(String)session.getAttribute("email");
       

	      //CREATE OBJECT OF DAO CLASS
   	    CustomerDAO cdaoobj = new CustomerDAO();
   	    
	      		
	      //CALL DAO METHOD
   	    List<AppHistoryDetails> applist = cdaoobj.fetchAllRecord3(email);
	    String nextviewpage="";
	    
	    if(applist.size() > 0)
	    {
	    	request.setAttribute("custinformationlist", applist);
	    	nextviewpage ="pat_feedback.jsp";
	    }
	    else
	    {
	    	request.setAttribute("servermsg", "NO RECORDS FOUND");
	    	nextviewpage ="pat_feedback.jsp";
	    }
	    
	    RequestDispatcher rd = request.getRequestDispatcher(nextviewpage);
   	rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
