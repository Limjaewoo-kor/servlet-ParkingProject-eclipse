<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="parking.ParkingDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8");%>

<jsp:useBean id="parking" class="parking.Parking" scope="page" />
<jsp:setProperty name="parking" property="parking_id" />
<jsp:setProperty name="parking" property="car_number" />
<jsp:setProperty name="parking" property="in_time" />
<jsp:setProperty name="parking" property="out_time" />
<jsp:setProperty name="parking" property="mem_id" />


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jaewoo park</title>
</head>
<body>	
 
 <%
 String car_number = (String)request.getAttribute("car_number");
 String to = String.valueOf(request.getAttribute("to"));
 %>
 차량번호는 ${car_number}입니다.<br><br>
 잔돈은 ${to}원 입니다.<br><br>
 <form method="post" action="memberJoin.jsp">
 	<input type= "hidden" name="car_number" value="${car_number}">
	<input type= "submit" class="cash1" value="받기">
</form>
	
</body>
</html>