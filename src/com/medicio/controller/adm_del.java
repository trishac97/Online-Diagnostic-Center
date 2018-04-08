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
import com.medicio.model.DoctorDetails;

@WebServlet("/adm_del")
public class adm_del extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public adm_del() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String d_email = request.getParameter("d_email");
	      
	      //CREATE OBJECT OF DAO CLASS
   	    CustomerDAO cdaoobj = new CustomerDAO();
   	    
	      		
	      //CALL DAO METHOD
   	    boolean replyfromdao=cdaoobj.deleteDoctor(d_email);
	    String msg="";
	    if(replyfromdao) {
	        msg="The Doctor Account for email: "+d_email+" has been deleted!";
	    }
	    else {
	    	msg="Oops! Something went wrong.";
	    }
	  
	  
	   request.setAttribute("servermsg", msg);
	   RequestDispatcher rd=request.getRequestDispatcher("/adm_welcome.jsp");
	   rd.forward(request, response);
	   
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
