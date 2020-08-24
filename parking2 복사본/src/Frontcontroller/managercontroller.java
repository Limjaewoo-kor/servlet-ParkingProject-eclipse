package Frontcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.Member;
import member.MemberDAO;
import parking.Parking;
import parking.ParkingDAO;


@WebServlet("*.man")
public class managercontroller extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String uri = request.getRequestURI();
		String command = uri.substring(uri.lastIndexOf("/") +1, uri.lastIndexOf(".man"));
		
		
	
	
			  if(command.trim().equals("parkingDel")) {
					String Mamont = (String)request.getParameter("Mamont");
					String mamont = (String)request.getParameter("mamont");
					String ID = request.getParameter("ID1");
					String Password = request.getParameter("Password1");
					
					Parking parkingDTO = new Parking();

					ParkingDAO ParkingDAO = new ParkingDAO();
					int result = ParkingDAO.parkingLog(parkingDTO);
					if(result == -2){
						response.sendRedirect("history.back()");
						  System.out.println("파킹 삭제 에러");
						  }else {
								request.setAttribute("mamont",mamont);
								request.setAttribute("Mamont",Mamont);
							  	request.setAttribute("ID1",ID);
								request.setAttribute("Password1",Password);
								request.getRequestDispatcher("managerAction1.log").forward(request, response);
						  }
						 
	 }else if(command.trim().equals("memberLog")) {
			String Mamont = (String)request.getParameter("Mamont");
			String mamont = (String)request.getParameter("mamont");
		String ID = request.getParameter("ID1");
		String Password = request.getParameter("Password1");
		String mem_id1 = request.getParameter("mem_id");
		int mem_id = Integer.parseInt(mem_id1);
		
		Member memberDTO = new Member();
		memberDTO.setMem_id(mem_id);

		MemberDAO memberDAO = new MemberDAO();
		int result = memberDAO.memberLog(memberDTO);
		if(result == -2){
			response.sendRedirect("history.back()");
			  System.out.println("멤버 삭제 에러");
			  }else {
					request.setAttribute("mamont",mamont);
					request.setAttribute("Mamont",Mamont);
				  	request.setAttribute("ID1",ID);
					request.setAttribute("Password1",Password);
					request.getRequestDispatcher("managerAction1.log").forward(request, response);
			  }
		
	 }else if(command.trim().equals("parking2Log")) {
			String Mamont = (String)request.getParameter("Mamont");
			String mamont = (String)request.getParameter("mamont");
			String ID = request.getParameter("ID1");
			String Password = request.getParameter("Password1");
			String parking_id1 = request.getParameter("parking_id");
			int parking_id = Integer.parseInt(parking_id1);
			
			Parking parkingDTO = new Parking();
			parkingDTO.setparking_id(parking_id);

			ParkingDAO parkingDAO = new ParkingDAO();
			int result = parkingDAO.parking2Log(parkingDTO);
			if(result == -2){
				response.sendRedirect("history.back()");
				  System.out.println("파킹 삭제 에러");
				  }else {
					  request.setAttribute("mamont",mamont);
						request.setAttribute("Mamont",Mamont);
					  	request.setAttribute("ID1",ID);
						request.setAttribute("Password1",Password);
						request.getRequestDispatcher("managerAction1.log").forward(request, response);
				  }
	 
	 }
	}
}
	
