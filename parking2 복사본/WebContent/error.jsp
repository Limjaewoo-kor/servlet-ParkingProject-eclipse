<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8");%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jaewoo park</title>
</head>
<body>	
 
 <%
	 
	   PrintWriter script = response.getWriter();
	   script.println("<script>");
	   script.println("alert('이미 입차중 이거나 출차가 완료 되었습니다.')");
	   script.println("location.href = 'parking.jsp'");
	   script.println("</script>");  
	  
 %>
 
	
</body>
</html>