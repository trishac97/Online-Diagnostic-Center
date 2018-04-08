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

@WebServlet("/docreg")
public class docreg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public docreg() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//CREATE POJO CLASS OBJECT
        DoctorDetails docobj = new DoctorDetails();
		
        //RECEIVED FROM HTML PAGE & STORE VALUES WITHIN OBJECT
		docobj.setEmail(request.getParameter("email"));
		docobj.setPassword(request.getParameter("password"));
		docobj.setFirst_name(request.getParameter("first_name"));
		docobj.setLast_name(request.getParameter("last_name"));
		docobj.setPhone(request.getParameter("phone"));
		docobj.setDesignation(request.getParameter("designation"));
		docobj.setAddress(request.getParameter("address"));
		docobj.setDate(request.getParameter("date"));
		docobj.setTime(request.getParameter("time"));
		docobj.setZip(request.getParameter("zip"));
		docobj.setState(request.getParameter("state"));
		docobj.setDepartment(request.getParameter("department"));
		docobj.setSalary(request.getParameter("salary"));

		
		
	
		//CREATE OBJECT OF DAO CLASS
		CustomerDAO cdaoobj = new CustomerDAO();
		
		//CALL DAO METHOD
		boolean replyfromdao = cdaoobj.userRegistration(docobj);
		
		String msg="";
		String nextpage="";
		if(replyfromdao)
		{
			msg ="Please wait for the Administrator to authenticate you email address!";
			nextpage="doclog.jsp";
		}
		else
	    {
			msg ="Oops! Something went wrong!";
			nextpage="docreg.jsp";
	    }
		
		request.setAttribute("errormsg", msg);
		RequestDispatcher rd = request.getRequestDispatcher(nextpage);
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}

