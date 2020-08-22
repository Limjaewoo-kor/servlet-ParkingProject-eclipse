<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
   session.getAttribute("ID");
   session.getAttribute("Password");

	String Mamont = (String)request.getParameter("Mamont");
	int Mamont2 = Integer.parseInt(Mamont);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="refresh" content="3;url=parking.jsp">
<title>jaewoo park</title>
</head>
<body>	


<% 
int Mamont1 = Mamont2;

%>

<%application.setAttribute("Mamont", Mamont1);%>


<h1> 정기권 금액 변경중입니다.</h1>

		
</html>