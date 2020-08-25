package memberlog;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.Member;


@WebServlet("*.log")
public class managerLogcontroller extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String uri = request.getRequestURI();
		String command = uri.substring(uri.lastIndexOf("/") +1, uri.lastIndexOf(".log"));
		
		
		List<memberlog> memlist = new ArrayList<>();
		List<parkinglog> parklist = new ArrayList<>();
	  
	      Connection conn = null; 
	      PreparedStatement pstmt = null; 
	      ResultSet rs = null; 
	  
	      try
	      {
	    	  	String dbURL = "jdbc:mysql://localhost:3306/PARK?useSSL=false&serverTimezone=Asia/Seoul";
	            String dbID = "root";
	            String dbPassword = "2104";
	            Class.forName("com.mysql.jdbc.Driver");
	  		  	conn = DriverManager.getConnection(dbURL, dbID, dbPassword);

	            String SQL = "SELECT * FROM Member";
	   
	  			 pstmt= conn.prepareStatement(SQL);
	  			 pstmt = conn.prepareStatement(SQL); 
	  			 rs = pstmt.executeQuery();
	  			 
	            while(rs.next())
	            {
	
	            int mem_id = rs.getInt("mem_id");
	            String member_time = rs.getString("member_time"); 
	            String member_car = rs.getString("member_car"); 
	            Boolean hybird_car = rs.getBoolean("hybird_car"); 
	            Boolean reguler_car = rs.getBoolean("reguler_car");
	            
	            
	            memberlog member = new memberlog(mem_id, member_time, member_car, hybird_car, reguler_car);
	            
	            memlist.add(member);
	            }
	           
	      }catch(Exception e){
	          
	            e.printStackTrace();
	      }
	      
	      
	      try
	      {
	    	  	String dbURL = "jdbc:mysql://localhost:3306/PARK?useSSL=false&serverTimezone=Asia/Seoul";
	            String dbID = "root";
	            String dbPassword = "2104";
	          
	  		  	conn = DriverManager.getConnection(dbURL, dbID, dbPassword);

	            String SQL = "SELECT * FROM Parking";
	   
	  			 pstmt= conn.prepareStatement(SQL);
	  			 pstmt = conn.prepareStatement(SQL); 
	  			 rs = pstmt.executeQuery();
	            while(rs.next())
	            {
	            	int parking_id = rs.getInt("parking_id"); 
	            	String car_number = rs.getString("car_number"); 
	            	String in_time = rs.getString("in_time"); 
	            	String out_time = rs.getString("out_time"); 
	            	int mem_id = rs.getInt("mem_id"); 

	            	parkinglog parking = new parkinglog(parking_id, car_number, in_time, out_time, mem_id);
		            
		            parklist.add(parking);
	            }
	            
	      }catch(Exception e){
	           
	            e.printStackTrace();
	      }finally{
	           
	            if(rs != null) try { rs.close(); } catch(Exception e) {}
	            if(pstmt != null) try { pstmt.close(); } catch(Exception e) {}
	            if(conn != null) try { conn.close(); } catch(Exception e) {}
	      }
	      
	      
	      
	      
			if(command != null && command.trim().equals("managerAction")) {
			 	String mamont = (String)request.getParameter("mamont");
				String Mamont = (String)request.getParameter("Mamont");
				String ID = "jaewoo";
				String Password = "1234";
				String ID1 = request.getParameter("ID");
				String Password1 = request.getParameter("Password");
				
				if(mamont==null)
					mamont="1000";
				if(Mamont==null)
					Mamont="100000";
				 if(ID1 == null || Password1 == null) {
						request.setAttribute("mamont",mamont);
						request.setAttribute("Mamont",Mamont);
						request.getRequestDispatcher("/WEB-INF/view/managererror.jsp").forward(request, response);
				}
				if(ID.equals(ID1) && Password.equals(Password1)) {
					request.setAttribute("memlist",memlist);
					request.setAttribute("parklist",parklist);
					request.setAttribute("mamont",mamont);
					request.setAttribute("Mamont",Mamont);
					request.setAttribute("ID1",ID1);
					request.setAttribute("Password1",Password1);
					request.getRequestDispatcher("/WEB-INF/view/manager.jsp").forward(request, response);
					
				  }else{
					  request.setAttribute("mamont",mamont);
						request.setAttribute("Mamont",Mamont);
						request.getRequestDispatcher("/WEB-INF/view/managererror.jsp").forward(request, response);
				  }
			}else if(command.trim().equals("managerAction1")) {
			 	String mamont = (String)request.getAttribute("mamont");
				String Mamont = (String)request.getAttribute("Mamont");
				String ID = "jaewoo";
				String Password = "1234";
				String ID1 = (String)request.getAttribute("ID1");
				String Password1 = (String)request.getAttribute("Password1");

				
				if(ID1 == null || Password1 == null) {
					request.setAttribute("mamont",mamont);
					request.setAttribute("Mamont",Mamont);
					request.getRequestDispatcher("/WEB-INF/view/managererror.jsp").forward(request, response);
			}
			if(ID.equals(ID1) && Password.equals(Password1)) {
				request.setAttribute("memlist",memlist);
				request.setAttribute("parklist",parklist);
				request.setAttribute("mamont",mamont);
				request.setAttribute("Mamont",Mamont);
				request.setAttribute("ID1",ID1);
				request.setAttribute("Password1",Password1);
				request.getRequestDispatcher("/WEB-INF/view/manager.jsp").forward(request, response);
				
			  }else{
				  request.setAttribute("mamont",mamont);
					request.setAttribute("Mamont",Mamont);
					request.getRequestDispatcher("/WEB-INF/view/managererror.jsp").forward(request, response);
			  }
					
				
					
				 
			}
			

			
	}
}