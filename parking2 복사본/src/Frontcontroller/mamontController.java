package Frontcontroller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memberlog.memberlog;
import memberlog.parkinglog;


@WebServlet("*.ma")
public class mamontController extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String uri = request.getRequestURI();
		String command = uri.substring(uri.lastIndexOf("/") +1, uri.lastIndexOf(".ma"));

		
		if(command != null && command.trim().equals("mamont")) {
			
				String Mamont = (String)request.getParameter("Mamont");
				String mamont = (String)request.getParameter("mamont");
				String ID1 = (String)request.getParameter("ID1");
				String Password1 = (String)request.getParameter("Password1");
				
				if((String)request.getParameter("Mamont") == null){
					
					Mamont ="100000";
					
					
					request.setAttribute("mamont",mamont);
					request.setAttribute("Mamont",Mamont);
					request.setAttribute("ID1",ID1);
					request.setAttribute("Password1",Password1);
					request.getRequestDispatcher("mamont.jsp").forward(request, response);
				
				}else {
					
					
					request.setAttribute("mamont",mamont);
					request.setAttribute("Mamont",Mamont);
					request.setAttribute("ID1",ID1);
					request.setAttribute("Password1",Password1);
					request.getRequestDispatcher("mamont.jsp").forward(request, response);
				}
		}else if(command.trim().equals("mamont2")) {
			
			String Mamont = (String)request.getParameter("Mamont");
			String mamont = (String)request.getParameter("mamont");
			String ID1 = (String)request.getParameter("ID1");
			String Password1 = (String)request.getParameter("Password1");
			
			if((String)request.getParameter("mamont") == null){
				
				mamont = "1000";
			
				
				
				request.setAttribute("mamont",mamont);
				request.setAttribute("Mamont",Mamont);
				request.setAttribute("ID1",ID1);
				request.setAttribute("Password1",Password1);
				request.getRequestDispatcher("mamont2.jsp").forward(request, response);
			
			}else {
				
				
				request.setAttribute("mamont",mamont);
				request.setAttribute("Mamont",Mamont);
				request.setAttribute("ID1",ID1);
				request.setAttribute("Password1",Password1);
				request.getRequestDispatcher("mamont2.jsp").forward(request, response);
			}
	}
}
}