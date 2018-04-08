package com.medicio.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.medicio.dao.CustomerDAO;
import com.medicio.model.AppHistoryDetails;
import com.medicio.model.DoctorDetails;
import com.medicio.model.PatientDetails;

@WebServlet("/pat_booking")
public class pat_booking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public pat_booking() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		

		
		AppHistoryDetails appobj=new AppHistoryDetails();
		PrintWriter out = response.getWriter();
		Random r=new Random();
        int rand_int1 =r.nextInt(90000);
        String rand=Integer.toString(rand_int1);
        appobj.setBooking_ref(rand);
        
		appobj.setP_email(request.getParameter("p_email"));
		appobj.setFirst_name(request.getParameter("first_name"));
		appobj.setLast_name(request.getParameter("last_name"));
		appobj.setD_email(request.getParameter("d_email"));
		appobj.setAppointment_date(request.getParameter("appointment_date"));
		appobj.setAppointment_reason(request.getParameter("appointment_reason"));
		appobj.setSalary(request.getParameter("salary"));
		appobj.setFlag("true");
		out.println("Enterd values:"+appobj.getP_email()+""+appobj.getAppointment_date());
 	   CustomerDAO cdaoobj=new CustomerDAO();
 	   String reply=cdaoobj.previousBookingCheck(appobj);
 	   if(reply=="false") {
 	   boolean replyfromdao=cdaoobj.patBooking(appobj);
 	   String msg="";
 	   
 	   
 	   if(replyfromdao) {
 		   msg="The doctor has been booked!";
 	   }
 	   else {
 		   msg="Oops! Something went wrong!";
 	   }
 	   
 	   request.setAttribute("servermsg", msg);
 	   RequestDispatcher rd=request.getRequestDispatcher("/pat_appointment.jsp");
 	   rd.forward(request, response);
 	   }
	
 	   else if(reply=="Not Available") {
 		  request.setAttribute("servermsg", "Maximum number of patient reached for the day! Try for other date");
		   RequestDispatcher rd=request.getRequestDispatcher("/pat_appointment.jsp");
		   rd.forward(request, response);
 	   }
 	   else {
 		   request.setAttribute("servermsg", "Already booked!");
 		   RequestDispatcher rd=request.getRequestDispatcher("/pat_appointment.jsp");
 		   rd.forward(request, response);
 	   }
		
	    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}	
}
