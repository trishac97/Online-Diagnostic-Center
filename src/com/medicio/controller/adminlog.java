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

@WebServlet("/adminlog")
public class adminlog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public adminlog() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("U entered:"+email+" "+password);
        
       if(email.equals("admin")&&password.equals("admin")) {
     
    	   HttpSession session = request.getSession(true);
    	   session.setAttribute("email", email);
    	   
           response.sendRedirect("adm_welcome.jsp");
        }
        else
        {
        	request.setAttribute("errormsg", "INVALID USERID OR PASSWORD");
        	RequestDispatcher rd = request.getRequestDispatcher("/adminlog.jsp");
        	rd.forward(request, response);
        }
	
	
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
