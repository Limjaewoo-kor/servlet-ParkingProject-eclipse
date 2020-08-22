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

import com.mysql.cj.Session;

import member.*;
import parking.Parking;
import parking.ParkingDAO;


@WebServlet("/parkingCheck")
public class frontcontroller extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String uri = request.getRequestURI();
		String command = uri.substring(uri.lastIndexOf("/") +1, uri.lastIndexOf(".lim"));
		
		if(command != null && command.trim().equals("parkingCheck")) {
			String member_car = request.getParameter("member_car");
			
			Parking parkingDTO = new Parking();
			parkingDTO.setCar_number(member_car);
		
			ParkingDAO parkingDAO = new ParkingDAO();
			int result = parkingDAO.parking_check(parkingDTO);
			
			
			if(result == 1){
				
				request.setAttribute("member_car",member_car);
				request.getRequestDispatcher("memberCheckAction.lim").forward(request, response);  //입차가능
				
		  }
			else if(result == -1){
				request.setAttribute("member_car",member_car);
				request.getRequestDispatcher("error.jsp").forward(request, response);  //이미 입차중
	
		  }
		  
		  if(result == -2) {
		 System.out.println("에러");
		 response.sendRedirect("history.back()");
		  }
		  
		}else if(command.trim().equals("memberCheckAction")) {
			String member_car = (String)request.getAttribute("member_car");
			
			Member memberDTO = new Member();
			memberDTO.setMember_car(member_car);
		
			MemberDAO memberDAO = new MemberDAO();
			int result = memberDAO.member_Check(memberDTO);
			
			
			if(result == 1){
				
				request.setAttribute("member_car",member_car);
				request.getRequestDispatcher("hybird.lim").forward(request, response);  //회원
				
		  }
			else if(result == -1){
				request.setAttribute("member_car",member_car);
				request.getRequestDispatcher("hybird.lim").forward(request, response);  //비회원
	
		  }
		  
		  if(result == -2) {
		 System.out.println("에러");
		 response.sendRedirect("history.back()");
		  }
		
			
		}else if(command.trim().equals("hybird")){
			String member_car = (String)request.getAttribute("member_car");
			
			Member memberDTO = new Member();
			memberDTO.setMember_car(member_car);
			
			MemberDAO memberDAO = new MemberDAO();
			int result = memberDAO.member_Check(memberDTO);
			
			   if(result == 1){
					request.setAttribute("member_car",member_car);
					request.getRequestDispatcher("parkingIn.lim").forward(request, response); 
			 //전기차
			 		
			   }

			   if(result == -1){
					request.setAttribute("member_car",member_car);
					request.getRequestDispatcher("parkingIn.lim").forward(request, response); 
				//전기차아님
			   }
			   
			   if(result == -2){
			    System.out.println("에러");
			    response.sendRedirect("history.back()");
			   }
			
		}else if(command.trim().equals("parkingIn")) {
			
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
					  response.sendRedirect("completeIn.jsp");
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
					  response.sendRedirect("completeIn.jsp");
				  }
		  }
			if(result == -2) {
				response.sendRedirect("history.back()");
				  System.out.println("에러");
		  }
			
		}else if(command.trim().equals("parkingCheck1")) {
				String car_number = request.getParameter("car_number");
				String mamont = request.getParameter("mamont");
		
				Parking parkingDTO = new Parking();
				parkingDTO.setCar_number(car_number);
			
				ParkingDAO parkingDAO = new ParkingDAO();
				int result = parkingDAO.parking_check(parkingDTO);
				
				
				if(result == 1){
					request.setAttribute("car_number",car_number);
					request.getRequestDispatcher("error.jsp").forward(request, response);  //출차불가능
					
			  }
				else if(result == -1){
					request.setAttribute("car_number",car_number);
					request.setAttribute("mamont",mamont);
					request.getRequestDispatcher("memberOutCheckAction.lim").forward(request, response);  //출차가능
			  }
			  
			  if(result == -2) {
			 System.out.println("출차체크에러");
			 response.sendRedirect("history.back()");
			  }
			
			
		}else if(command.trim().equals("memberOutCheckAction")){
			String car_number = (String)request.getAttribute("car_number");
			String mamont = request.getParameter("mamont");

			Parking parkingDTO = new Parking();
			parkingDTO.setCar_number(car_number);
			
			ParkingDAO parkingDAO = new ParkingDAO();
			int result = parkingDAO.parkingMemberOut(parkingDTO);
			
			   if(result == 1){
			
					request.setAttribute("mamont",mamont);
				   request.setAttribute("car_number",car_number);   //회원
					request.getRequestDispatcher("parkingOut.lim").forward(request, response); 
			   }  

			  if(result == -1) {
		
					request.setAttribute("mamont",mamont);
			   request.setAttribute("car_number",car_number);  //비회원
				request.getRequestDispatcher("hybirdOutAction.lim").forward(request, response); 
			  }		  
			  if(result == -2){
				  response.sendRedirect("history.back()");
				  System.out.println("멤버아웃 에러");
			  }
		}else if(command.trim().equals("hybirdOutAction")) {
			String car_number = (String)request.getAttribute("car_number");
			String mamont = request.getParameter("mamont");
	
			Parking parkingDTO = new Parking();
			parkingDTO.setCar_number(car_number);
			
			ParkingDAO parkingDAO = new ParkingDAO();
			int result = parkingDAO.parkingHybirdOut(parkingDTO);
			
			 if(result == 1){				

					request.setAttribute("mamont",mamont);
				request.setAttribute("car_number",car_number);
				request.getRequestDispatcher("settlementAction2.lim").forward(request, response);  //하이브리드
			   }

			   if(result == -1){

					request.setAttribute("mamont",mamont);
				request.setAttribute("car_number",car_number);
				request.getRequestDispatcher("settlementAction.lim").forward(request, response);  //하이브리드아님
			   }
			   
			   if(result == -2){
				   response.sendRedirect("history.back()");
					  System.out.println("하이브리드 아웃에러");
			   }
			   
		}else if(command.trim().equals("settlementAction")) {
			String car_number = (String)request.getAttribute("car_number");
			String mamont = request.getParameter("mamont");
			int mamont1 = Integer.parseInt(mamont);
			
			Parking parkingDTO = new Parking();
			parkingDTO.setCar_number(car_number);
		
			ParkingDAO parkingDAO = new ParkingDAO();
			int result1 = parkingDAO.settlement(parkingDTO);
	
			int rates = mamont1;
			int result = result1*rates;
			 if(result != -2){
				request.setAttribute("car_number",car_number);
				request.setAttribute("result",result);
				request.getRequestDispatcher("settlementAction.jsp").forward(request, response);
			 } if(result == -2){
				 response.sendRedirect("history.back()");
				  System.out.println("정산 에러");
			 }
		}else if(command.trim().equals("settlementAction2")) {
		String car_number = (String)request.getAttribute("car_number");
		String mamont = request.getParameter("mamont");
		int mamont1 = Integer.parseInt(mamont);
		
		Parking parkingDTO = new Parking();
		parkingDTO.setCar_number(car_number);
	
		ParkingDAO parkingDAO = new ParkingDAO();
		int result1 = parkingDAO.settlement2(parkingDTO);
	

		int rates = mamont1;
		int result = result1*rates;
		 if(result != -2){
				
			request.setAttribute("car_number",car_number);
			request.setAttribute("result",result);
			request.getRequestDispatcher("settlementAction2.jsp").forward(request, response);
		 } if(result == -2){
			 response.sendRedirect("history.back()");
			  System.out.println("정산2 에러");
		 }
	}else if(command.trim().equals("50cash")) {
		String car_number = request.getParameter("car_number");
		String result = request.getParameter("result");
		String cash = request.getParameter("cash");
		int cashs = Integer.parseInt(cash);
		int results = Integer.parseInt(result);
		
		int to = cashs-results;
		
		if(results == cashs){
			request.setAttribute("car_number",car_number);
			request.getRequestDispatcher("card.lim").forward(request, response);
		  }else if(results > cashs){
			  request.setAttribute("car_number",car_number);
				request.setAttribute("result",results);
				request.getRequestDispatcher("settlementAction2.jsp").forward(request, response);
		  }else if(results < cashs){
			  request.setAttribute("car_number",car_number);
				request.setAttribute("to",to);
				request.getRequestDispatcher("change.jsp").forward(request, response);
		  }
	}else if(command.trim().equals("cash")) {
		String car_number = request.getParameter("car_number");
		String result = request.getParameter("result");
		String cash = request.getParameter("cash");
		int cashs = Integer.parseInt(cash);
		int results = Integer.parseInt(result);
		
		int to = cashs-results;
		
		if(results == cashs){
			request.setAttribute("car_number",car_number);
			request.getRequestDispatcher("card.lim").forward(request, response);
		  }else if(results > cashs){
			  request.setAttribute("car_number",car_number);
				request.setAttribute("result",result);
				request.getRequestDispatcher("settlementAction.jsp").forward(request, response);
		  }else if(results < cashs){
			  request.setAttribute("car_number",car_number);
				request.setAttribute("to",to);
				request.getRequestDispatcher("change.jsp").forward(request, response);
		  }
	}else if(command.trim().equals("card")) {
		String car_number = request.getParameter("car_number");
		String result = request.getParameter("result");
		request.setAttribute("car_number",car_number);
		request.setAttribute("result",result);
		request.getRequestDispatcher("card.jsp").forward(request, response);
		
	}else if(command.trim().equals("50card")) {
		String car_number = request.getParameter("car_number");
		String result = request.getParameter("result");
		request.setAttribute("car_number",car_number);
		request.setAttribute("result",result);
		request.getRequestDispatcher("card.jsp").forward(request, response);
		
	}else if(command.trim().equals("parkingOut")) {
		String car_number = (String)request.getAttribute("car_number");
		
		Parking parkingDTO = new Parking();
		parkingDTO.setCar_number(car_number);
	
		ParkingDAO ParkingDAO = new ParkingDAO();
		int result = ParkingDAO.parkingOut(parkingDTO);
		if(result == -1){
			response.sendRedirect("history.back()");
			  System.out.println("파킹아웃 에러");
			  }
			  else {  
				  request.setAttribute("car_number",car_number);
					request.getRequestDispatcher("completeOut.jsp").forward(request, response);
				
			  } 
	}else if(command.trim().equals("parkingOut1")) {
		String car_number = request.getParameter("car_number");
		
		Parking parkingDTO = new Parking();
		parkingDTO.setCar_number(car_number);
	
		ParkingDAO ParkingDAO = new ParkingDAO();
		int result = ParkingDAO.parkingOut(parkingDTO);
		if(result == -1){
			response.sendRedirect("history.back()");
			  System.out.println("파킹아웃1 에러");
			  }
			  else {  
				  request.setAttribute("car_number",car_number);
					request.getRequestDispatcher("completeOut.jsp").forward(request, response);
				
			  } 
	}else if(command.trim().equals("managerAction")) {
		String ID = "jaewoo";
		 String Password = "1234";
		 String ID1 = request.getParameter("ID");
		 String Password1 = request.getParameter("Password");

		 
		 if(ID1 == null || Password1 == null) {
			 PrintWriter script = response.getWriter();
			   script.println("<script>");
			   script.println("alert('아이디와 비밀번호는 빈칸이 아닙니다.')");
			   script.println("history.back()");
			   script.println("</script>");   
		}
		if(ID.equals(ID1) && Password.equals(Password1)) {
			request.setAttribute("ID1",ID1);
			request.setAttribute("Password1",Password1);
			request.getRequestDispatcher("manager.jsp").forward(request, response);
			
		  }else{
				 PrintWriter script = response.getWriter();
				   script.println("<script>");
				   script.println("alert('아이디와 비밀번호를 확인하세요')");
				   script.println("history.back()");
				   script.println("</script>"); 
		  }
	}else if(command.trim().equals("regulerJoinCash")) {
		String car_number = request.getParameter("car_number");
		String Mamont = request.getParameter("Mamont");
		String cash = request.getParameter("cash");
		int cashs = Integer.parseInt(cash);
		int results = Integer.parseInt(Mamont);
		
		int to = cashs-results;
		
		if(results == cashs){
			
			request.getRequestDispatcher("memberJoin.jsp").forward(request, response); 
		  }else if(results > cashs){
			  request.setAttribute("car_number",car_number);
				request.setAttribute("Mamont",Mamont);
				request.getRequestDispatcher("regulerJoinAction.jsp").forward(request, response);
		  }else if(results < cashs){
			  request.setAttribute("car_number",car_number);
				request.setAttribute("to",to);
				request.getRequestDispatcher("memberJoinchange.jsp").forward(request, response);
		  }
		 
	}else if(command.trim().equals("regulerJoinCard")) {
		request.getRequestDispatcher("memberJoin.jsp").forward(request, response); 
	
	}else if(command.trim().equals("regulerJoinComplete")) {
		String car_number = request.getParameter("car_number");
		Boolean hybird_car = Boolean.valueOf(request.getParameter("hybird_car"));
		
		
		Member memberDTO = new Member();
		memberDTO.setMember_car(car_number); 
		memberDTO.setHybird_car(hybird_car); 
		
			  MemberDAO memberDAO = new MemberDAO();
			  int result = memberDAO.memberJoin(memberDTO);
			  if(result == -1){
				  PrintWriter script = response.getWriter();
				   script.println("<script>");
				   script.println("alert('에러가 발생했습니다.')");
				   script.println("history.back()");
				   script.println("</script>");   
			  }
			  else {
			   PrintWriter script = response.getWriter();
			   script.println("<script>");
			   script.println("alert('가입이 완료되었습니다.')");
			   script.println("location.href = 'parking.jsp'");
			   script.println("</script>");  
			  }
	}else if(command.trim().equals("hybirdJoin")) {
		String car_number = request.getParameter("car_number");
		
		Member memberDTO = new Member();
		memberDTO.setMember_car(car_number); 

			  MemberDAO memberDAO = new MemberDAO();
			  int result = memberDAO.hybirdJoin(memberDTO);
			  if(result == -1){
				  PrintWriter script = response.getWriter();
				   script.println("<script>");
				   script.println("alert('에러가 발생했습니다.')");
				   script.println("history.back()");
				   script.println("</script>");   
			  }
			  else {
			   PrintWriter script = response.getWriter();
			   script.println("<script>");
			   script.println("alert('가입이 완료되었습니다.')");
			   script.println("location.href = 'parking.jsp'");
			   script.println("</script>");  
			  }
			  }else if(command.trim().equals("parkingDel")) {

					String ID = request.getParameter("ID1");
					String Password = request.getParameter("Password1");
					
					Parking parkingDTO = new Parking();

					ParkingDAO ParkingDAO = new ParkingDAO();
					int result = ParkingDAO.parkingLog(parkingDTO);
					if(result == -2){
						response.sendRedirect("history.back()");
						  System.out.println("파킹 삭제 에러");
						  }else {
							  	request.setAttribute("ID1",ID);
								request.setAttribute("Password1",Password);
								request.getRequestDispatcher("manager.jsp").forward(request, response);
						  }
						 
	 }else if(command.trim().equals("memberLog")) {

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
				  	request.setAttribute("ID1",ID);
					request.setAttribute("Password1",Password);
					request.getRequestDispatcher("manager.jsp").forward(request, response);
			  }
		
	 }else if(command.trim().equals("parking2Log")) {

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
					  	request.setAttribute("ID1",ID);
						request.setAttribute("Password1",Password);
						request.getRequestDispatcher("manager.jsp").forward(request, response);
				  }
	 
	 }
	}
}
