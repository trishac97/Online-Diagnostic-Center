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

@WebServlet("/doc_enablefeedback")
public class doc_enablefeedback extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public doc_enablefeedback() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
 	   HttpSession session = request.getSession(true);
		String booking_ref = request.getParameter("booking_ref");
	       String email=(String)session.getAttribute("email");

	      //CREATE OBJECT OF DAO CLASS
   	    CustomerDAO cdaoobj = new CustomerDAO();
   	    
  	   boolean replyfromdao=cdaoobj.docFeedbackEnable(booking_ref);
 		
	      //CALL DAO METHOD
	    String nextviewpage="";
	    String reply="Feedback enabled for reference number:"+booking_ref;
	    if(replyfromdao)
	    {
	    	request.setAttribute("servermsg",reply );
	    	nextviewpage ="doc_futuremonth.jsp";
	    }
	    else
	    {
	    	request.setAttribute("servermsg", "NO RECORDS FOUND");
	    	nextviewpage ="doc_futuremonth.jsp";
	    }
	          
	    RequestDispatcher rd = request.getRequestDispatcher(nextviewpage);
   	rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
