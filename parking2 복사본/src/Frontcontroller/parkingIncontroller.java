package Frontcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import member.*;
import parking.Parking;
import parking.ParkingDAO;


@WebServlet("*.lim")
public class parkingIncontroller extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String uri = request.getRequestURI();
		String command = uri.substring(uri.lastIndexOf("/") +1, uri.lastIndexOf(".lim"));
		
		if(command != null && command.trim().equals("parkingCheck")) {
		 	String mamont = (String)request.getParameter("mamont");
			String Mamont = (String)request.getParameter("Mamont");
			String member_car = request.getParameter("member_car");
			if(member_car == null || member_car == "") {
				request.setAttribute("mamont",mamont);
				request.setAttribute("Mamont",Mamont);
				request.setAttribute("member_car",member_car);
				request.getRequestDispatcher("error2.jsp").forward(request, response);  //
			}else {
			Parking parkingDTO = new Parking();
			parkingDTO.setCar_number(member_car);
		
			ParkingDAO parkingDAO = new ParkingDAO();
			int result = parkingDAO.parking_check(parkingDTO);
			
			
			if(result == 1){
				request.setAttribute("mamont",mamont);
				request.setAttribute("Mamont",Mamont);
				request.setAttribute("member_car",member_car);
				request.getRequestDispatcher("memberCheckAction.lim").forward(request, response);  //입차가능
				
		  }
			else if(result == -1){
				request.setAttribute("mamont",mamont);
				request.setAttribute("Mamont",Mamont);
				request.setAttribute("member_car",member_car);
				request.getRequestDispatcher("error.jsp").forward(request, response);  //이미 입차중
	
		  }
		  
		  if(result == -2) {
		 System.out.println("에러");
		 response.sendRedirect("history.back()");
		  }}
		  
		}else if(command.trim().equals("memberCheckAction")) {
		 	String mamont = (String)request.getAttribute("mamont");
			String Mamont = (String)request.getAttribute("Mamont");
			String member_car = (String)request.getAttribute("member_car");
			
			Member memberDTO = new Member();
			memberDTO.setMember_car(member_car);
		
			MemberDAO memberDAO = new MemberDAO();
			int result = memberDAO.member_Check(memberDTO);
			
			
			if(result == 1){
					request.setAttribute("mamont",mamont);
					request.setAttribute("Mamont",Mamont);
				request.setAttribute("member_car",member_car);
				request.getRequestDispatcher("hybird.lim").forward(request, response);  //회원
				
		  }
			else if(result == -1){		 	
			request.setAttribute("mamont",mamont);
			request.setAttribute("Mamont",Mamont);
				request.setAttribute("member_car",member_car);
				request.getRequestDispatcher("hybird.lim").forward(request, response);  //비회원
	
		  }
		  
		  if(result == -2) {
		 System.out.println("에러");
		 response.sendRedirect("history.back()");
		  }
		
			
		}else if(command.trim().equals("hybird")){
		 	String mamont = (String)request.getAttribute("mamont");
				String Mamont = (String)request.getAttribute("Mamont");
			String member_car = (String)request.getAttribute("member_car");
			
			Member memberDTO = new Member();
			memberDTO.setMember_car(member_car);
			
			MemberDAO memberDAO = new MemberDAO();
			int result = memberDAO.member_Check(memberDTO);
			
			   if(result == 1){

					request.setAttribute("mamont",mamont);
					request.setAttribute("Mamont",Mamont);
					request.setAttribute("member_car",member_car);
					request.getRequestDispatcher("parkingIn.lim").forward(request, response); 
			 //전기차
			 		
			   }

			   if(result == -1){

					request.setAttribute("mamont",mamont);
					request.setAttribute("Mamont",Mamont);
					request.setAttribute("member_car",member_car);
					request.getRequestDispatcher("parkingIn.lim").forward(request, response); 
				//전기차아님
			   }
			   
			   if(result == -2){
			    System.out.println("에러");
			    response.sendRedirect("history.back()");
			   }
			
		}else if(command.trim().equals("parkingIn")) {
		 	String mamont = (String)request.getAttribute("mamont");
				String Mamont = (String)request.getAttribute("Mamont");
			
			String member_car = (String)request.getAttribute("member_car");
			
			Member memberDTO = new Member();
			Parking parkingDTO = new Parking();
			memberDTO.setMember_car(member_car);
			parkingDTO.setCar_number(member_car);
			
			MemberDAO memberDAO = new MemberDAO();
			int result = memberDAO.member_Check(memberDTO);
			
			if(result == 1){
			  //회원
				memberDTO.setMember_car(member_car);
				int result1 = memberDAO.notmemberin1(memberDTO);
				
				  if(result1 == -1){
					  response.sendRedirect("history.back()");
					  System.out.println("에러");
				  }
				  else {

						request.setAttribute("mamont",mamont);
						request.setAttribute("Mamont",Mamont);
				
						request.getRequestDispatcher("completeIn.jsp").forward(request, response); 
				  }
			}
			else if(result == -1){
				 //비회원
			
				memberDTO.setMember_car(member_car);
				int result2 = memberDAO.notmemberin(memberDTO);
				  if(result2 == -1){
					  response.sendRedirect("history.back()");
					  System.out.println("에러");
				  }
				  else {

						request.setAttribute("mamont",mamont);
						request.setAttribute("Mamont",Mamont);
					 
						request.getRequestDispatcher("completeIn.jsp").forward(request, response); 
				  }
		  }
			if(result == -2) {
				response.sendRedirect("history.back()");
				  System.out.println("에러");
		  }
			
	
		}
	}
}