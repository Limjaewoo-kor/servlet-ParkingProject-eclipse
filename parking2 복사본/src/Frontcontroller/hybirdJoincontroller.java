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


@WebServlet("*.hy")
public class hybirdJoincontroller extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String uri = request.getRequestURI();
		String command = uri.substring(uri.lastIndexOf("/") +1, uri.lastIndexOf(".hy"));
		
		
		if(command != null && command.trim().equals("joinCheck")) {
			String car_number = request.getParameter("car_number");
			String Mamont = (String)request.getParameter("Mamont");
			String mamont = (String)request.getParameter("mamont");
			
			if(car_number==null || car_number == "") {	
				request.setAttribute("mamont",mamont);
				request.setAttribute("Mamont",Mamont);
				request.getRequestDispatcher("/WEB-INF/view/error2.jsp").forward(request, response);
				
			}else {
				Member memberDTO = new Member();
				memberDTO.setMember_car(car_number); 
				MemberDAO memberDAO = new MemberDAO();
				  int result = memberDAO.joinCheck(memberDTO);
				  if(result == -1){  //중복
					  	request.setAttribute("mamont",mamont);
						request.setAttribute("Mamont",Mamont);
						request.getRequestDispatcher("/WEB-INF/view/errorfor.jsp").forward(request, response); 
			}else { //중복아님
				request.setAttribute("mamont",mamont);
				request.setAttribute("Mamont",Mamont);
				request.setAttribute("car_number",car_number);
				request.getRequestDispatcher("hybirdJoin.hy").forward(request, response); 
			}
			}
			
		}else if(command.trim().equals("hybirdJoin")) {
		String car_number = (String)request.getAttribute("car_number");
		String Mamont = (String)request.getAttribute("Mamont");
		String mamont = (String)request.getAttribute("mamont");
		
			
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
					request.setAttribute("mamont",mamont);
					request.setAttribute("Mamont",Mamont);
					request.getRequestDispatcher("/WEB-INF/view/memberjoinSt.jsp").forward(request, response); 
			  }
		}}
}
