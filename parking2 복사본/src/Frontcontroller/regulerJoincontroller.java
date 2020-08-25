package Frontcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.Member;
import member.MemberDAO;


@WebServlet("*.woo")
public class regulerJoincontroller extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String uri = request.getRequestURI();
		String command = uri.substring(uri.lastIndexOf("/") +1, uri.lastIndexOf(".woo"));
		
		
	if(command != null && command.trim().equals("regulerJoinCash")) {
		String car_number = request.getParameter("car_number");
		String Mamont = request.getParameter("Mamont");
		String mamont = request.getParameter("mamont");
		String cash = request.getParameter("cash");
		if(cash==null || cash == "") {	
			request.setAttribute("mamont",mamont);
			request.setAttribute("Mamont",Mamont);
			request.setAttribute("car_number",car_number);
			request.getRequestDispatcher("/WEB-INF/view/regulerJoinError.jsp").forward(request, response);
			
		}else {
		int cashs = Integer.parseInt(cash);
		int results = Integer.parseInt(Mamont);
		
		int to = cashs-results;
		
		if(results == cashs){
			request.setAttribute("mamont",mamont);
			request.setAttribute("Mamont",Mamont);
			request.getRequestDispatcher("/WEB-INF/view/memberJoin.jsp").forward(request, response); 
		  }else if(results > cashs){
			  request.setAttribute("mamont",mamont);
			  request.setAttribute("car_number",car_number);
				request.setAttribute("Mamont",Mamont);
				request.getRequestDispatcher("/WEB-INF/view/regulerJoinError.jsp").forward(request, response);
		  }else if(results < cashs){
			  request.setAttribute("mamont",mamont);
				request.setAttribute("Mamont",Mamont);
			  request.setAttribute("car_number",car_number);
				request.setAttribute("to",to);
				request.getRequestDispatcher("/WEB-INF/view/memberJoinchange.jsp").forward(request, response);
		  }else if(cash == null || cash == "") {
			  request.setAttribute("mamont",mamont);
				request.setAttribute("Mamont",Mamont);
			  request.setAttribute("car_number",car_number);
				request.setAttribute("result",results);
				request.getRequestDispatcher("/WEB-INF/view/regulerJoinError.jsp").forward(request, response);
		  }}
	}else if(command.trim().equals("regulerJointo")) {
		String car_number = (String)request.getParameter("car_number");
		String Mamont = (String)request.getParameter("Mamont");
		String mamont = (String)request.getParameter("mamont");
		request.setAttribute("mamont",mamont);
		request.setAttribute("car_number",car_number);
		request.setAttribute("Mamont",Mamont);
		request.getRequestDispatcher("/WEB-INF/view/memberJoin.jsp").forward(request, response); 
	
	}else if(command.trim().equals("regulerJoinCard")) {
		String Mamont = (String)request.getParameter("Mamont");
		String mamont = (String)request.getParameter("mamont");
		request.setAttribute("mamont",mamont);
		request.setAttribute("Mamont",Mamont);
		request.getRequestDispatcher("/WEB-INF/view/memberJoin.jsp").forward(request, response); 
	
	}else if(command.trim().equals("regulerJoinComplete")) {
		String Mamont = (String)request.getParameter("Mamont");
		String mamont = (String)request.getParameter("mamont");
		String car_number = request.getParameter("car_number");
		
		if(car_number == null || car_number == "") {
			request.setAttribute("mamont",mamont);
			request.setAttribute("Mamont",Mamont);
			request.setAttribute("member_car",car_number);
			request.getRequestDispatcher("regulerJoinAction.jsp").forward(request, response);  //
		}
		
		
		Member memberDTO = new Member();
		memberDTO.setMember_car(car_number); 

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
					request.setAttribute("mamont",mamont);
					request.setAttribute("Mamont",Mamont);
					request.getRequestDispatcher("/WEB-INF/view/memberjoinSt.jsp").forward(request, response); 

			  }
	}
}
}