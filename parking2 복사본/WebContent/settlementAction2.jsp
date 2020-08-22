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
	 String result = String.valueOf(request.getAttribute("result"));
	 
 %>
 차량번호는 ${car_number}입니다.<br><br>
 금액은 ${result}원 입니다.<br><br>
 결제 방식 및 금액을 투입해주세요.<br>
	<form method="post" action="50cash.lim">
	<label>현금 입력 : </label><input type="text"  style="margin-top:20px;" placeholder="금액" name="cash" maxlength="25">
		<input type= "hidden" name="car_number" value="${car_number}">
		<input type= "hidden" name="result" value="${result}">
		<input type= "submit" class="cash1" value="결제">
	</form>
	
	
	
	<form method="post" action="50card.lim">
		<input type= "hidden" name="car_number" value="${car_number}">
		<input type= "hidden" name="result" value="${result}">
		<input type= "submit" style="margin-top:20px;" class="card" value="카드">
	</form>
</body>
</html>