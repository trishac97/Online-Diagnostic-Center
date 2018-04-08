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

@WebServlet("/patlog")
public class patlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public patlog() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        String email = request.getParameter("email");
        String password = request.getParameter("password");
      
      //CREATE OBJECT OF DAO CLASS
      	CustomerDAO cdaoobj = new CustomerDAO();
      		
      //CALL DAO METHOD
       String first_name = cdaoobj.userLogin2(email, password);
        
       if(first_name!= null)
       {
    	   HttpSession session = request.getSession(true);
    	   session.setAttribute("first_name",first_name);
    	   session.setAttribute("email", email);
    	   
           response.sendRedirect("pat_welcome.jsp");
        }
        else
        {
        	request.setAttribute("errormsg", "INVALID USERID OR PASSWORD");
        	RequestDispatcher rd = request.getRequestDispatcher("/patlog.jsp");
        	rd.forward(request, response);
        }
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
