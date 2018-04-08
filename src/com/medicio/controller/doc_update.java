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

@WebServlet("/doc_update")
public class doc_update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public doc_update() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String last_name = request.getParameter("last_name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String salary = request.getParameter("salary");
		

		HttpSession session = request.getSession(true);
	       String email=(String)session.getAttribute("email");
	      
	      //CREATE OBJECT OF DAO CLASS
     	 CustomerDAO cdaoobj = new CustomerDAO();
	      DoctorDetails docobj=new DoctorDetails();
	      docobj.setLast_name(last_name);
	      docobj.setPhone(phone);
	      docobj.setAddress(address);
	      docobj.setSalary(salary);
	      docobj.setEmail(email);
	      //CALL DAO METHOD
    	   boolean replyfromdao=cdaoobj.docUpdate(docobj);
	    String nextviewpage="";
	    String reply="Information successfully stored for"+" "+email;
	    if(replyfromdao)
	    {
	    	request.setAttribute("servermsg", reply);
	    	nextviewpage ="doc_update.jsp";
	    }
	    else
	    {
	    	request.setAttribute("servermsg", "Oops! Something went wrong.");
	    	nextviewpage ="doc_update.jsp";
	    }
	          
	    RequestDispatcher rd = request.getRequestDispatcher(nextviewpage);
     	rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
