package Frontcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import parking.Parking;
import parking.ParkingDAO;

@WebServlet("*.jae")
public class parkingOutcontroller extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String uri = request.getRequestURI();
		String command = uri.substring(uri.lastIndexOf("/") +1, uri.lastIndexOf(".jae"));
		
		
	if(command != null && command.trim().equals("parkingCheck1")) {
	 	String mamont = (String)request.getParameter("mamont");
			String Mamont = (String)request.getParameter("Mamont");
			String car_number = request.getParameter("car_number");

			if(car_number == null || car_number == "") {
				request.setAttribute("mamont",mamont);
				request.setAttribute("Mamont",Mamont);
				request.setAttribute("car_number",car_number);
				request.getRequestDispatcher("/WEB-INF/view/error2.jsp").forward(request, response);  
			}else {
			Parking parkingDTO = new Parking();
			parkingDTO.setCar_number(car_number);
		
			ParkingDAO parkingDAO = new ParkingDAO();
			int result = parkingDAO.parking_check(parkingDTO);
			
			
			if(result == 1){

				request.setAttribute("mamont",mamont);
				request.setAttribute("Mamont",Mamont);
				request.setAttribute("car_number",car_number);
				request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);  //출차불가능
				
		  }
			else if(result == -1){

	
				request.setAttribute("Mamont",Mamont);
				request.setAttribute("car_number",car_number);
				request.setAttribute("mamont",mamont);
				request.getRequestDispatcher("memberOutCheckAction.jae").forward(request, response);  //출차가능
		  }
		  
		  if(result == -2) {
		 System.out.println("출차체크에러");
		 response.sendRedirect("history.back()");
		  }
			}
		
	}else if(command.trim().equals("memberOutCheckAction")){

			String Mamont = (String)request.getAttribute("Mamont");
		String car_number = (String)request.getAttribute("car_number");
		String mamont = request.getParameter("mamont");

		Parking parkingDTO = new Parking();
		parkingDTO.setCar_number(car_number);
		
		ParkingDAO parkingDAO = new ParkingDAO();
		int result = parkingDAO.parkingMemberOut(parkingDTO);
		
		   if(result == 1){

				request.setAttribute("Mamont",Mamont);
				request.setAttribute("mamont",mamont);
			   request.setAttribute("car_number",car_number);   //회원
				request.getRequestDispatcher("parkingOut.jae").forward(request, response); 
		   }  

		  if(result == -1) {

				request.setAttribute("Mamont",Mamont);
				request.setAttribute("mamont",mamont);
		   request.setAttribute("car_number",car_number);  //비회원
			request.getRequestDispatcher("hybirdOutAction.jae").forward(request, response); 
		  }		  
		  if(result == -2){
			  response.sendRedirect("history.back()");
			  System.out.println("멤버아웃 에러");
		  }
	}else if(command.trim().equals("hybirdOutAction")) {

			String Mamont = (String)request.getAttribute("Mamont");
		String car_number = (String)request.getAttribute("car_number");
		String mamont = request.getParameter("mamont");

		Parking parkingDTO = new Parking();
		parkingDTO.setCar_number(car_number);
		
		ParkingDAO parkingDAO = new ParkingDAO();
		int result = parkingDAO.parkingHybirdOut(parkingDTO);
		
		 if(result == 1){					


				request.setAttribute("Mamont",Mamont);
				request.setAttribute("mamont",mamont);
			request.setAttribute("car_number",car_number);
			request.getRequestDispatcher("settlementAction2.jae").forward(request, response);  //하이브리드
		   }

		   if(result == -1){

				request.setAttribute("Mamont",Mamont);
				request.setAttribute("mamont",mamont);
			request.setAttribute("car_number",car_number);
			request.getRequestDispatcher("settlementAction.jae").forward(request, response);  //하이브리드아님
		   }
		   
		   if(result == -2){
			   response.sendRedirect("history.back()");
				  System.out.println("하이브리드 아웃에러");
		   }
		   
	}else if(command.trim().equals("settlementAction")) {

			String Mamont = (String)request.getAttribute("Mamont");

		String car_number = (String)request.getAttribute("car_number");
		String mamont = request.getParameter("mamont");
		int mamont1 = Integer.parseInt(mamont);
		
		Parking parkingDTO = new Parking();
		parkingDTO.setCar_number(car_number);
	
		ParkingDAO parkingDAO = new ParkingDAO();
		int result1 = parkingDAO.settlement(parkingDTO);
		if(result1<1) {
			result1=1;
		}
		
		int rates = mamont1;
		int result = result1*rates;
		 if(result != -2){

				request.setAttribute("mamont",mamont);
				request.setAttribute("Mamont",Mamont);
			request.setAttribute("car_number",car_number);
			request.setAttribute("result",result);
			request.getRequestDispatcher("/WEB-INF/view/settlementAction.jsp").forward(request, response);
		 } if(result == -2){
			 response.sendRedirect("history.back()");
			  System.out.println("정산 에러");
		 }
	}else if(command.trim().equals("settlementAction2")) {

			String Mamont = (String)request.getAttribute("Mamont");

	String car_number = (String)request.getAttribute("car_number");
	String mamont = request.getParameter("mamont");
	int mamont1 = Integer.parseInt(mamont);
	
	Parking parkingDTO = new Parking();
	parkingDTO.setCar_number(car_number);

	ParkingDAO parkingDAO = new ParkingDAO();
	int result1 = parkingDAO.settlement2(parkingDTO);
	if(result1<1) {
		result1=1;
	}

	int rates = mamont1;
	int result = result1*rates;
	 if(result != -2){

				request.setAttribute("mamont",mamont);
				request.setAttribute("Mamont",Mamont);
		request.setAttribute("car_number",car_number);
		request.setAttribute("result",result);
		request.getRequestDispatcher("/WEB-INF/view/settlementAction2.jsp").forward(request, response);
	 } if(result == -2){
		 response.sendRedirect("history.back()");
		  System.out.println("정산2 에러");
	 }
}else if(command.trim().equals("50cash")) {
 	String mamont = (String)request.getParameter("mamont");
		String Mamont = (String)request.getParameter("Mamont");
	String car_number = request.getParameter("car_number");
	String result = request.getParameter("result");
	String cash = request.getParameter("cash");
	if(cash==null || cash == "") {	
		request.setAttribute("mamont",mamont);
		request.setAttribute("Mamont",Mamont);
		request.setAttribute("car_number",car_number);
		request.setAttribute("result",result);
		request.getRequestDispatcher("/WEB-INF/view/settlementAction.jsp").forward(request, response);
		
	}else {
	int cashs = Integer.parseInt(cash);
	int results = Integer.parseInt(result);
	
	int to = cashs-results;
	
	if(results == cashs){

			request.setAttribute("mamont",mamont);
			request.setAttribute("Mamont",Mamont);
		request.setAttribute("car_number",car_number);
		request.getRequestDispatcher("/WEB-INF/view/card.jsp").forward(request, response);
	  }else if(results > cashs){

				request.setAttribute("mamont",mamont);
				request.setAttribute("Mamont",Mamont);
		  request.setAttribute("car_number",car_number);
			request.setAttribute("result",results);
			request.getRequestDispatcher("/WEB-INF/view/settlementAction2.jsp").forward(request, response);
	  }else if(results < cashs){
	
				request.setAttribute("mamont",mamont);
				request.setAttribute("Mamont",Mamont);
		  request.setAttribute("car_number",car_number);
			request.setAttribute("to",to);
			request.getRequestDispatcher("/WEB-INF/view/change.jsp").forward(request, response);
	  }else if(cash == null || cash == "") {
	
				request.setAttribute("mamont",mamont);
				request.setAttribute("Mamont",Mamont);
		  request.setAttribute("car_number",car_number);
			request.setAttribute("result",results);
			request.getRequestDispatcher("/WEB-INF/view/settlementAction2.jsp").forward(request, response);
	  }}
}else if(command.trim().equals("cash")) {
 	String mamont = (String)request.getParameter("mamont");
		String Mamont = (String)request.getParameter("Mamont");

	String car_number = request.getParameter("car_number");
	String result = request.getParameter("result");
	
	String cash = request.getParameter("cash");
	if(cash==null || cash == "") {	
		request.setAttribute("mamont",mamont);
		request.setAttribute("Mamont",Mamont);
		request.setAttribute("car_number",car_number);
		request.setAttribute("result",result);
		request.getRequestDispatcher("/WEB-INF/view/settlementAction.jsp").forward(request, response);
		
	}else {
	int cashs = Integer.parseInt(cash);
	
	int results = Integer.parseInt(result);
	
	int to = cashs-results;
	
	if(results == cashs){

			request.setAttribute("mamont",mamont);
			request.setAttribute("Mamont",Mamont);
		request.setAttribute("car_number",car_number);
		request.getRequestDispatcher("/WEB-INF/view/card.jsp").forward(request, response);
	  }else if(results > cashs){
		
				request.setAttribute("mamont",mamont);
				request.setAttribute("Mamont",Mamont);
		  request.setAttribute("car_number",car_number);
			request.setAttribute("result",result);
			request.getRequestDispatcher("/WEB-INF/view/settlementAction.jsp").forward(request, response);
	  }else if(results < cashs){

				request.setAttribute("mamont",mamont);
				request.setAttribute("Mamont",Mamont);
		  request.setAttribute("car_number",car_number);
			request.setAttribute("to",to);
			request.getRequestDispatcher("/WEB-INF/view/change.jsp").forward(request, response);
	  }else if(cash == null || cash == "") {

				request.setAttribute("mamont",mamont);
				request.setAttribute("Mamont",Mamont);
		  request.setAttribute("car_number",car_number);
			request.setAttribute("result",results);
			request.getRequestDispatcher("/WEB-INF/view/settlementAction2.jsp").forward(request, response);
	  }}
}else if(command.trim().equals("card")) {
 	String mamont = (String)request.getParameter("mamont");
		String Mamont = (String)request.getParameter("Mamont");
	String car_number = request.getParameter("car_number");
	String result = request.getParameter("result");

		request.setAttribute("mamont",mamont);
		request.setAttribute("Mamont",Mamont);
	request.setAttribute("car_number",car_number);
	request.setAttribute("result",result);
	request.getRequestDispatcher("/WEB-INF/view/card.jsp").forward(request, response);
	
}else if(command.trim().equals("50card")) {
 	String mamont = (String)request.getParameter("mamont");
		String Mamont = (String)request.getParameter("Mamont");
	
	String car_number = request.getParameter("car_number");
	String result = request.getParameter("result");
		request.setAttribute("mamont",mamont);
		request.setAttribute("Mamont",Mamont);
	
	request.setAttribute("car_number",car_number);
	request.setAttribute("result",result);
	request.getRequestDispatcher("/WEB-INF/view/card.jsp").forward(request, response);
	
}else if(command.trim().equals("parkingOut")) {
	String car_number = (String)request.getAttribute("car_number");
 	String mamont = (String)request.getAttribute("mamont");
		String Mamont = (String)request.getAttribute("Mamont");
	
	Parking parkingDTO = new Parking();
	parkingDTO.setCar_number(car_number);

	ParkingDAO ParkingDAO = new ParkingDAO();
	int result = ParkingDAO.parkingOut(parkingDTO);
	if(result == -1){
		response.sendRedirect("history.back()");
		  System.out.println("파킹아웃 에러");
		  }
		  else {  
		
				request.setAttribute("mamont",mamont);
				request.setAttribute("Mamont",Mamont);
			  request.setAttribute("car_number",car_number);
				request.getRequestDispatcher("/WEB-INF/view/completeOut.jsp").forward(request, response);
			
		  } 
}else if(command.trim().equals("parkingOut1")) {
 	String mamont = (String)request.getParameter("mamont");
		String Mamont = (String)request.getParameter("Mamont");
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
		
				request.setAttribute("mamont",mamont);
				request.setAttribute("Mamont",Mamont);
			  request.setAttribute("car_number",car_number);
				request.getRequestDispatcher("/WEB-INF/view/completeOut.jsp").forward(request, response);
			
		  } 
		
		
	}
}
}
