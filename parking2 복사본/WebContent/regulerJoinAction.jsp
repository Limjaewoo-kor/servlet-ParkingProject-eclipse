<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="parking.ParkingDAO" %>
    <%@ page import="member.MemberDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8");%>

<%String Mamont1 = (String)request.getParameter("Mamont"); 
	int Mamont = Integer.parseInt(Mamont1);%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jaewoo park</title>
</head>
<body>	
 
 

 1개월 금액은 <%=Mamont%>원 입니다.<br><br>
 결제 방식을 선택해주세요.<br>
	<form method="post" action="regulerJoinCash.lim">
	<label>차량 번호: </label><input type="text" style="margin-top:20px;" placeholder="차량번호" name="car_number" maxlength="25"><br>
	<label>현금 입력 : </label><input type="text"  style="margin-top:20px;" placeholder="금액" name="cash" maxlength="25">
		<input type= "hidden" name="result" value="<%=Mamont%>">
		<input type= "submit" class="cash1" value="결제">
	</form>
	
	
	<form method="post" action="regulerJoinCard.lim">
		<input type= "submit" style="margin-top:20px;" class="card" value="카드">
	</form>
</body>
</html>