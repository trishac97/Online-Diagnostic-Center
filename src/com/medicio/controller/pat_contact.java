package com.medicio.controller;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.medicio.dao.CustomerDAO;
import com.medicio.model.AppHistoryDetails;
import com.medicio.model.DoctorDetails;
import com.medicio.model.FeedbackDetails;
import com.medicio.model.PatContactDetails;

@WebServlet("/pat_contact")
public class pat_contact extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public pat_contact() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	PatContactDetails patconobj=new PatContactDetails();
	Random r=new Random();
    int rand_int1 =r.nextInt(90000);
    String rand=Integer.toString(rand_int1);
    patconobj.setReference_no(rand);
		patconobj.setFirst_name(request.getParameter("first_name"));
		
		patconobj.setP_email(request.getParameter("p_email"));
		patconobj.setMessage(request.getParameter("message"));

		
 	   CustomerDAO cdaoobj=new CustomerDAO();
 	   boolean replyfromdao=cdaoobj.patContact(patconobj);
 	   String msg="";
 	   
 	   
 	   if(replyfromdao) {
 		   msg="We will get back to you withing 5-6 business days! Reference number: "+rand;
 	   }
 	   else {
 		   msg="Oops! Something went wrong.";
 	   }
 	  
 	   request.setAttribute("servermsg", msg);
 	   RequestDispatcher rd=request.getRequestDispatcher("/pat_contact.jsp");
 	   rd.forward(request, response);
 	   

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
