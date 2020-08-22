package member;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import parking.Parking;


public class MemberDAO {

	 private Connection conn;
	 private PreparedStatement pstmt;
	 private ResultSet rs;

	 public MemberDAO() {
	  try {
		  String dbURL = "jdbc:mysql://localhost:3306/PARK?useSSL=false&serverTimezone=Asia/Seoul";
		  String dbID = "root";
		  String dbPassword = "2104";
		  Class.forName("com.mysql.jdbc.Driver");
		  conn = DriverManager.getConnection(dbURL, dbID, dbPassword);

	  } catch (Exception e) {
	   e.printStackTrace();   //오류발생
	  }
	 }

	 public int member_Check(Member member) {
		 
		  String SQL = "SELECT member_time FROM Member WHERE member_car=?";
		  try {
			  PreparedStatement pstmt = conn.prepareStatement(SQL);
			  pstmt = conn.prepareStatement(SQL);
			  pstmt.setString(1, member.getMember_car());
			  rs = pstmt.executeQuery();
			  
			 while(rs.next()) {
				 SimpleDateFormat SimpleDateFormat = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
					String currentTime = SimpleDateFormat.format(new Date());
					String res = SimpleDateFormat.format(rs.getDate(1));
					SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date endday = f.parse(res);
					Date today = f.parse(currentTime);
					long calDate = today.getTime() - endday.getTime();
					long calDateDays = calDate / (24*60*60*1000);
				
				
			    if(calDateDays<=0) {
					return 1;  // 회원
			    }else {
			    	return -1;
			    }
			 }
			
			    	return -1;  // 비회원
			  
			    
		  }catch(Exception e) {
		   e.printStackTrace();
		  }
		  return -2; // 디비오류
			}
	 
	
	 
	 
	 
	 public int notmemberin(Member member) {

			 String SQL = "INSERT INTO Parking(car_number, in_time, out_time, mem_id) VALUES (?, NOW() ,null, 1)";
			 try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);	
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, member.getMember_car());
				return pstmt.executeUpdate();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			       return -1;
			       }
	
	 
		public int notmemberin1(Member member) {
			 String SQL = "INSERT INTO Parking(car_number, in_time, out_time, mem_id) VALUES (?, NOW() ,null, 2)";
			 try {
				PreparedStatement pstmt = conn.prepareStatement(SQL);	
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, member.getMember_car());
				return pstmt.executeUpdate();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return -1; 
			}
		
		
		
	 public int hybird(Member member) {
		  String SQL = "SELECT hybird_car FROM Member WHERE member_car=?";
		  try {
			  PreparedStatement pstmt = conn.prepareStatement(SQL);
			  pstmt = conn.prepareStatement(SQL);
			  pstmt.setString(1, member.getMember_car());
		   rs = pstmt.executeQuery();
		  
		   if(rs.next()) {
			   byte res = rs.getByte(1);
		    if(res == 1) {
		    	return 1;  // 전기차
		     
		   }else 
		   return -1;  // 전기차아님
		   }return -1;
		  }catch(Exception e) {
		   e.printStackTrace();
		  }
		  return -2; // 디비오류
		 }
	 
	 
	 public int memberJoin(Member member) {
		 String SQL = "INSERT INTO Member(member_time, member_car, hybird_car, reguler_car) VALUES (DATE_ADD(NOW(), INTERVAL 1 MONTH), ?, ?, 1)";
		 try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);	
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, member.getMember_car());
			pstmt.setBoolean(2, member.getHybird_car());
			return pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	 		return -1; 
	 	
	 }
	 public int hybirdJoin(Member member) {
		 String SQL = "INSERT INTO Member(member_time, member_car, hybird_car, reguler_car) VALUES (DATE_ADD(NOW(), INTERVAL 1 MONTH), ?, 1, 0)";
		 try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);	
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, member.getMember_car());
			return pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	 		return -1; 
	 	
	 }
	 
	 public int regulerJoin(Member member) {
		 int mamont = 100000;
		 return mamont;
	 }
	 
	 
	 public int memberLog(Member member) {
	 		String SQL = "DELETE FROM Member WHERE mem_id=?;";
	 		try {
	 			PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, member.getMem_id());
				return pstmt.executeUpdate();
				
				  }catch(Exception e) {
				   e.printStackTrace();
				  }
				  return -2; // 디비오류
				 }
}
	
