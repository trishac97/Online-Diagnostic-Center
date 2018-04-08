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

@WebServlet("/doc_futureyear")
public class doc_futureyear extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public doc_futureyear() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
 	   HttpSession session = request.getSession(true);
		String appointment_date = request.getParameter("appointment_date");
	       String email=(String)session.getAttribute("email");

	      //CREATE OBJECT OF DAO CLASS
   	    CustomerDAO cdaoobj = new CustomerDAO();
   	    
	      		
	      //CALL DAO METHOD
   	    List<AppHistoryDetails> applist = cdaoobj.fetchAllRecordyear(appointment_date,email);
	    String nextviewpage="";
	    
	    if(applist.size() > 0)
	    {
	    	request.setAttribute("custinformationlist", applist);
	    	nextviewpage ="doc_futureyear.jsp";
	    }
	    else
	    {
	    	request.setAttribute("servermsg", "NO RECORDS FOUND");
	    	nextviewpage ="doc_futureyear.jsp";
	    }
	          
	    RequestDispatcher rd = request.getRequestDispatcher(nextviewpage);
   	rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
