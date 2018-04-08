package com.medicio.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.medicio.dao.CustomerDAO;
import com.medicio.model.DoctorDetails;
import com.medicio.model.PatientDetails;

@WebServlet("/patreg")
public class patreg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public patreg() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//CREATE POJO CLASS OBJECT
        PatientDetails patobj = new PatientDetails();
		
        //RECEIVED FROM HTML PAGE & STORE VALUES WITHIN OBJECT
		patobj.setEmail(request.getParameter("email"));
		patobj.setPassword(request.getParameter("password"));
		patobj.setFirst_name(request.getParameter("first_name"));
		patobj.setLast_name(request.getParameter("last_name"));
		patobj.setPhone(request.getParameter("phone"));
		patobj.setAddress(request.getParameter("address"));
		patobj.setZip(request.getParameter("zip"));
		patobj.setState(request.getParameter("state"));

		
		
	
		//CREATE OBJECT OF DAO CLASS
		CustomerDAO cdaoobj = new CustomerDAO();
		
		//CALL DAO METHOD
		boolean replyfromdao = cdaoobj.userRegistration2(patobj);
		
		String msg="";
		if(replyfromdao)
		{
			msg ="Please Login";
		}
		else
	    {
			msg ="Opps! Something went wrong. Please try after few moment";
	    }
		
		request.setAttribute("displaymsg", msg);
		RequestDispatcher rd = request.getRequestDispatcher("Afterpatlog.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}

