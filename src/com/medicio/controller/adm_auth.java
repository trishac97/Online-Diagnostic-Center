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

@WebServlet("/adm_auth")
public class adm_auth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public adm_auth() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String d_email = request.getParameter("d_email");
	      
	      //CREATE OBJECT OF DAO CLASS
   	    CustomerDAO cdaoobj = new CustomerDAO();
   	    
	      		
	      //CALL DAO METHOD
   	   boolean replyfromdao = cdaoobj.docEnable(d_email);
	    String nextviewpage="";
	    String msg="Doctor enabled for email id:" +d_email;
	    if( replyfromdao)
	    {
	    	request.setAttribute("servermsg", msg);
	    	nextviewpage ="adm_auth_doc.jsp";
	    }
	    else
	    {
	    	request.setAttribute("servermsg", "Oops! Something went wrong");
	    	nextviewpage ="adm_auth_doc.jsp";
	    }
	          
	    RequestDispatcher rd = request.getRequestDispatcher(nextviewpage);
   	rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
