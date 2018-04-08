package com.medicio.controller;

import java.io.IOException;
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
import com.medicio.model.FeedbackDetails;

@WebServlet("/doc_feedback")
public class doc_feedback extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public doc_feedback() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
 	   HttpSession session = request.getSession(true);
	       String email=(String)session.getAttribute("email");

	      //CREATE OBJECT OF DAO CLASS
   	    CustomerDAO cdaoobj = new CustomerDAO();
   	    
	      		
	      //CALL DAO METHOD
   	    List<FeedbackDetails> feedlist = cdaoobj.fetchAllRecordfeedback(email);
	    String nextviewpage="";
	    
	    if(feedlist.size() > 0)
	    {
	    	request.setAttribute("custinformationlist", feedlist);
	    	nextviewpage ="doc_feedback.jsp";
	    }
	    else
	    {
	    	request.setAttribute("servermsg", "NO FEEDBACK FOUND");
	    	nextviewpage ="doc_feedback.jsp";
	    }
	          
	    RequestDispatcher rd = request.getRequestDispatcher(nextviewpage);
   	rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
