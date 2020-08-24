package parking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import member.*;



public class ParkingDAO {

	 private Connection conn;
	 private PreparedStatement pstmt;
	 private ResultSet rs;

	 public ParkingDAO() {
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

		
	 
	 public int parking_check(Parking parking) {
		 
		  String SQL = "SELECT car_number FROM Parking WHERE out_time IS null AND car_number=?";
		  try {
			  PreparedStatement pstmt = conn.prepareStatement(SQL);
			  pstmt = conn.prepareStatement(SQL);
			  pstmt.setString(1, parking.getCar_number());
			  rs = pstmt.executeQuery();
			  
			 if(rs.next()) 
				 return -1;  // 이미 입차중  //  출차가능
			 else 
				 return 1; // 입차가능  // 출차불가능
			    
			    
		  }catch(Exception e) {
		   e.printStackTrace();
		  }
		  return -2; // 디비오류
			}

	public int settlement(Parking parking) { 
		String SQL = "SELECT in_time FROM Parking WHERE car_number = ? ORDER BY in_time DESC LIMIT 1";
		  try {
			  PreparedStatement pstmt = conn.prepareStatement(SQL);
			  pstmt = conn.prepareStatement(SQL);
			  pstmt.setString(1, parking.getCar_number());
			  rs = pstmt.executeQuery();
		
			  if(rs.next()) {
				Timestamp c_date = rs.getTimestamp(1);		
				 SimpleDateFormat SimpleDateFormat = new SimpleDateFormat ("ddHHmm");
				
					String currentTime = SimpleDateFormat.format(new Date());
					String res = SimpleDateFormat.format(c_date);
					String enddays = currentTime.substring(0,2);
					String currentTimes = currentTime.substring(2,6); 
					String indays = res.substring(0,2);
					String intimes = res.substring(2,6);
					int endday = Integer.parseInt(enddays);
					int inday = Integer.parseInt(indays);
					int endtime = Integer.parseInt(currentTimes);
					int intime = Integer.parseInt(intimes);
					int day = endday-inday;
					int hour = (endtime/100) - (intime/100);
					int min = (endtime%100) - (intime%100);
					
					int to = (day*1440)+(hour*60)+min;
					
					int rate = (to/10);
					
					return rate;
			 }
		  }catch(Exception e) {
		   e.printStackTrace();
		  }
		  return -2; // 디비오류
			}
	
	public int settlement2(Parking parking) { 
		String SQL = "SELECT in_time FROM Parking WHERE car_number = ? ORDER BY in_time DESC LIMIT 1";
		  try {
			  PreparedStatement pstmt = conn.prepareStatement(SQL);
			  pstmt = conn.prepareStatement(SQL);
			  pstmt.setString(1, parking.getCar_number());
			  rs = pstmt.executeQuery();
			
		
			  if(rs.next()) {
					Timestamp c_date = rs.getTimestamp(1);		
					 SimpleDateFormat SimpleDateFormat = new SimpleDateFormat ("ddHHmm");
					
						String currentTime = SimpleDateFormat.format(new Date());
						String res = SimpleDateFormat.format(c_date);
						String enddays = currentTime.substring(0,2);
						String currentTimes = currentTime.substring(2,6); 
						String indays = res.substring(0,2);
						String intimes = res.substring(2,6);
						int endday = Integer.parseInt(enddays);
						int inday = Integer.parseInt(indays);
						int endtime = Integer.parseInt(currentTimes);
						int intime = Integer.parseInt(intimes);
						int day = endday-inday;
						int hour = (endtime/100) - (intime/100);
						int min = (endtime%100) - (intime%100);
						
						int to = (day*1440)+(hour*60)+min;
						
						int rate = (to/10)/2;
						
						return rate;
				 }
			  }catch(Exception e) {
			   e.printStackTrace();
			  }
			  return -2; // 디비오류
				}
	

	 public int parkingOut(Parking parking) {
	 		String SQL = "UPDATE Parking SET out_time = now() WHERE ? = car_number ORDER BY in_time DESC LIMIT 1";
	 		try {
	 			PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, parking.getCar_number());
				return pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	 		return -1; 
	 	}
	 
	 public int parkingMemberOut(Parking parking) {
	 		String SQL = "SELECT reguler_car FROM Member JOIN Parking ON (member_car = ?) ORDER BY reguler_car DESC LIMIT 1";
	 		try {
	 			PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, parking.getCar_number());
				rs = pstmt.executeQuery();
				  if(rs.next()) {
					   byte res = rs.getByte(1);
				    if(res == 1) {
				    	return 1;  // 회원
				     
				   }else 
				   return -1;  // 회원아님
				   }return -1;
				  }catch(Exception e) {
				   e.printStackTrace();
				  }
				  return -2; // 디비오류
				 }
	 
	 public int parkingHybirdOut(Parking parking) {
	 		String SQL = "SELECT hybird_car FROM Member JOIN Parking ON (member_car = ?) ORDER BY hybird_car DESC LIMIT 1";
	 		try {
	 			PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, parking.getCar_number());
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
	 
	 public int parkingLog(Parking parking) {
	 		String SQL = "truncate TABLE parking";
	 		try {
	 			PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt = conn.prepareStatement(SQL);
				return pstmt.executeUpdate();
				
				  }catch(Exception e) {
				   e.printStackTrace();
				  }
				  return -2; // 디비오류
				 }
	 public int parking2Log(Parking parking) {
	 		String SQL = "DELETE FROM Parking WHERE parking_id=?;";
	 		try {
	 			PreparedStatement pstmt = conn.prepareStatement(SQL);
				pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, parking.getparking_id());
				return pstmt.executeUpdate();
				
				  }catch(Exception e) {
				   e.printStackTrace();
				  }
				  return -2; // 디비오류
				 }
	
}
