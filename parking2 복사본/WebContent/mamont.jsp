<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%
   session.getAttribute("ID");
   session.getAttribute("Password");

	String mamont = (String)request.getParameter("mamont");
	int mamont2 = Integer.parseInt(mamont);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="refresh" content="3;url=parking.jsp">
<title>jaewoo park</title>
</head>
<body>	


<% 
int mamont1 = mamont2;

%>

<%application.setAttribute("mamont", mamont1);%>  

<h1> 분당 금액 변경중입니다.</h1>

		
</html>