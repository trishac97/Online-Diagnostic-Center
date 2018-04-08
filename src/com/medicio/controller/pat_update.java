package com.medicio.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.medicio.dao.CustomerDAO;
import com.medicio.model.DoctorDetails;
import com.medicio.model.PatientDetails;

@WebServlet("/pat_update")
public class pat_update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public pat_update() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String last_name = request.getParameter("last_name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String state = request.getParameter("state");

		

		HttpSession session = request.getSession(true);
	       String email=(String)session.getAttribute("email");
	      
	      //CREATE OBJECT OF DAO CLASS
     	 CustomerDAO cdaoobj = new CustomerDAO();
	      PatientDetails patobj=new PatientDetails();
	      patobj.setLast_name(last_name);
	      patobj.setPhone(phone);
	      patobj.setAddress(address);
	      patobj.setEmail(email);
	      patobj.setState(state);
	      //CALL DAO METHOD
    	   boolean replyfromdao=cdaoobj.patUpdate(patobj);
	    String nextviewpage="";
	    String reply="Information successfully stored for"+email;
	    if(replyfromdao)
	    {
	    	request.setAttribute("servermsg", reply);
	    	nextviewpage ="pat_update.jsp";
	    }
	    else
	    {
	    	request.setAttribute("servermsg", "Oops! Something went wrong.");
	    	nextviewpage ="pat_update.jsp";
	    }
	          
	    RequestDispatcher rd = request.getRequestDispatcher(nextviewpage);
     	rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
