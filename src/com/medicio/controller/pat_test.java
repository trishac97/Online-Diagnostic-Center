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
import com.medicio.model.DiagnosticTestDetails;
import com.medicio.model.DoctorDetails;

@WebServlet("/pat_test")
public class pat_test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public pat_test() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		 HttpSession session = request.getSession(true);
	       String email=(String)session.getAttribute("email");
	      
	      //CREATE OBJECT OF DAO CLASS
   	    CustomerDAO cdaoobj = new CustomerDAO();
   	    
	      		
	      //CALL DAO METHOD
   	    List<DiagnosticTestDetails> diaglist = cdaoobj.fetchAllRecordtest(email);
	    String nextviewpage="";
	    
	    if(diaglist.size() > 0)
	    {
	    	request.setAttribute("custinformationlist", diaglist);
	    	nextviewpage ="pat_test.jsp";
	    }
	    else
	    {
	    	request.setAttribute("servermsg", "No records found!");
	    	nextviewpage ="pat_test.jsp";
	    }
	          
	    RequestDispatcher rd = request.getRequestDispatcher(nextviewpage);
   	rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
