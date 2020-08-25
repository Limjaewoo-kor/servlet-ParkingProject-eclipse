<%@page import="memberlog.parkinglog"%>
<%@page import="memberlog.memberlog"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*"%>
    <%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String ID1 = (String)request.getAttribute("ID1");
	String Password1 = (String)request.getAttribute("Password1");
	String Mamont = (String)request.getAttribute("Mamont");
	String mamont = (String)request.getAttribute("mamont");
 	List<memberlog> list = (List<memberlog>)request.getAttribute("memlist");  
 	List<parkinglog> listp = (List<parkinglog>)request.getAttribute("parklist"); 

%>
<%session.setAttribute("ID", request.getAttribute("ID1"));%>
<%session.setAttribute("Password", request.getAttribute("Password1"));%>
<%@ page import="java.io.PrintWriter" %>

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
       	background-color:black;
       color:white;
      }
      div{
      display:flex;
      }
	.title{

		display:flex;
		height:30vh;
		justify-content:center;
		padding-top:20px;
		font-size:30px;
		font-weight:bolder;
	}
	.title2{
	justify-content:center;
	font-size:20px;
	margin-bottom:20px;
	margin-top:20px;
	}
	.nav{
	display:flex;
	justify-content:center;
	}
	.btn {
border:1x solid #ff0080;    /*---테두리 정의---*/
background-Color:#ffe6f2;   /*--백그라운드 정의---*/
font:12px 굴림;      /*--폰트 정의---*/
font-weight:bold;   /*--폰트 굵기---*/
color:#ff0080;    /*--폰트 색깔---*/
width:130px;
height:50px;  /*--버튼 크기---*/
}
.btn1{
color:black;
}
.mamont input{
color:black;
}


    </style>
</head>
<%if(ID1 == null){
	PrintWriter script = response.getWriter();
	   script.println("<script>");
	   script.println("alert('접근 권한이 없습니다.')");
	   script.println("location.href = 'parking.jsp'");
	   script.println("</script>");  
}
	%>
<body>

<h3>관리자 ${ID1}님 환영합니다.</h3> 
 
<form method="post" action="parking.jsp">
	<input type= submit value="메인페이지로" class="btn1">
	 <input type= hidden name ="Mamont" value="<%=Mamont%>"> 
	<input type= hidden name ="mamont" value="<%=mamont%>">
	<input type= hidden name ="ID1" value="${ID1}">
	<input type= hidden name ="Password1" value="${Password1}">
</form>
<form method="post" action="sessionLogout.jsp">
 <input type= hidden name ="Mamont" value="<%=Mamont%>"> 
	<input type= hidden name ="mamont" value="<%=mamont%>">
	<input type= submit value="로그아웃" class="btn1">
	</form>



		<div class="title" style="text-align:center;">
			공영 주차장 프로젝트<br>
			- 관리자 페이지 -
		</div>
		현재 10분당 금액 :<%=mamont%>  월 금액 :<%=Mamont%>
		<div class="nav">
		<form method="post" action="mamont.ma" style="margin-left:20px;" class="mamont">
		   		<label>10분당 금액 : </label><input type="text"  placeholder="초기값 1000원" name="mamont" maxlength="7" pattern="^[0-9]*$" style="margin-left:20px;">		
				<input type= hidden name ="Mamont" value="<%=Mamont%>"> 
				<input type= hidden name ="ID1" value="<%=ID1%>">
				<input type= hidden name ="Password1" value="<%=Password1%>">
				<input type= submit class="btn" value="금액 변경" style="margin-left:10px;">
			
			</form>
			<form method="post" action="mamont2.ma" style="margin-left:20px;" class="mamont">
		   		<label>월 금액 : </label><input type="text" placeholder="초기값 100000원" name="Mamont" maxlength="7" pattern="^[0-9]*$" style="margin-left:20px;">
				<input type= hidden name ="mamont" value="<%=mamont%>">
			
				<input type= hidden name ="ID1" value="<%=ID1%>">
				<input type= hidden name ="Password1" value="<%=Password1%>">
				<input type= submit class="btn" value="금액 변경" style="margin-left:10px;">
			</form>
		</div>
	
		   <div class="title2" >
		  <div class="title2_name" style="margin-top:10px;">-회원 정보- </div>
		   <form method="post" action="memberLog.man" style="margin-left:20px;">
		   		<label>mem_id : </label><input type="text"  placeholder="mem_id" name="mem_id" maxlength="25" style="margin-left:20px;">
				<input type= submit class="btn" value="특정 로그 삭제" style="margin-left:20px;">
				<input type= hidden name ="Mamont" value="<%=Mamont%>"> 
				<input type= hidden name ="mamont" value="<%=mamont%>">
				<input type= hidden name ="ID1" value="<%=ID1%>">
				<input type= hidden name ="Password1" value="<%=Password1%>">
			</form>
		   </div>
      <table style="margin-left:10%" width = "80%" border = "1">
      <tr>
            <td>mem_id</td>
            <td>member_time</td>
            <td>member_car</td>
            <td>hybird_car</td>
            <td>reguler_car</td>
      </tr>
 

<% 	for(memberlog m : list) {
		pageContext.setAttribute("m", m);
%>  
<%-- <c:forEach var = "m" items="${list}">  --%>
      <tr>
            <td>${m.mem_id}</td>
            <td>${m.member_time}</td>
            <td>${m.member_car}</td>
            <td>${m.hybird_car}</td>
            <td>${m.reguler_car}</td>
      </tr>
    <%--  </c:forEach>  --%>
  <%} %> 
      </table>
      
      <div class="title2" >
		   <div class="title2_name" style="margin-top:10px;">-주차장 이용 로그- </div>
		    <form method="post" action="parking2Log.man" style="margin-left:20px;">
		   		<label>parking_id : </label><input type="text"  placeholder="parking_id" name="parking_id" maxlength="25" style="margin-left:20px;">
				<input type= submit value="특정 로그 삭제" class="btn" style="margin-left:20px;">
				<input type= hidden name ="Mamont" value="<%=Mamont%>"> 
				<input type= hidden name ="mamont" value="<%=mamont%>">
				<input type= hidden name ="ID1" value="${ID1}">
				<input type= hidden name ="Password1" value="${Password1}">
			</form>
		   <form method="post" action="parkingDel.man">
				<input type= submit value="주자창 로그 전체 삭제" class="btn" style="margin-left:20px;">
				<input type= hidden name ="Mamont" value="<%=Mamont%>"> 
				<input type= hidden name ="mamont" value="<%=mamont%>">
				<input type= hidden name ="ID1" value="${ID1}">
				<input type= hidden name ="Password1" value="${Password1}">
			</form>
		   </div>
        <table style="margin-left:10%" width = "80%" border = "1">
      <tr>
            <td>parking_id</td>
            <td>car_number</td>
            <td>in_time</td>
            <td>out_time</td>
            <td>mem_id</td>
      </tr>
 
 <% 	for(parkinglog p : listp) {
		pageContext.setAttribute("p", p);
	
%>  
	<%-- <c:forEach var = "p" items="${listp}"> --%>
      <tr>
            <td>${p.parking_id}</td>
            <td>${p.car_number}</td>
            <td>${p.in_time}</td>
            <td>${p.out_time}</td>
            <td>${p.mem_id}</td>
      </tr>
     <%--  </c:forEach> --%>
 <%} %> 
  
      </table>
		

	
</body>
</html>