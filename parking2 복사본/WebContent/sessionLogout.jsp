<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
 <% session.invalidate(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="Stylesheet" href="css/bootstrap.css">
<link rel="Stylesheet" href="css/custom.css">
<title>jaewoo park</title>
 <style>
      body {
     	font-size:15px;
        font-weight:bolder;
      }
      div{
      display:flex;
      }
	.title{
		display:flex;
		height:30vh;
		justify-content:center;
		padding-top:100px;
		font-size:30px;
		font-weight:bolder;
	}
	.title2{
	justify-content:center;
	font-size:20px;
	margin-bottom:20px;
	margin-top:20px;
	
    </style>
</head>
<body>
	<h2>로그아웃 되었습니다.</h2>
	<a href = "parking.jsp">메인페이지로..</a>
	
</body>
</html>