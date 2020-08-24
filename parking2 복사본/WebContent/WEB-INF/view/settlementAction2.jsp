<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="parking.ParkingDAO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8");%>

<% 
	String Mamont = (String)request.getAttribute("Mamont");
	String mamont = (String)request.getAttribute("mamont");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="Stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<title>jaewoo park</title>
 <style>
 <style>
 @media only screen and (max-width: 1715px){

	form{
		display:flex;
		align-items:center;
	}}

body {	
	background-color:black;
     	font-size:20px;
      	border:0;
      	padding:0;
       
        height: 100%;
      	width:100%;
        background-size:cover;
        font-weight:bolder;
      }
	div{
	display:flex;
	}
	.header{
	color:white;
		display:flex;
		height:15vh;
		justify-content:center;
		padding-top:50px;
		font-size:25px;
		font-weight:bolder;
		border-bottom:solid 1px white;
	}
	.header-logo{
	margin-bottom:10px;
  max-width:230px;
  max-height:95px;
  width: auto;
  height: auto;
	}
.row{
display:flex;
height:200px;

justify-content:center;
align-items:center;

color:white;
}
	.btn {
border:1x solid #ff0080;    /*---테두리 정의---*/
background-Color:#ffe6f2;   /*--백그라운드 정의---*/
font:18px 굴림;      /*--폰트 정의---*/
font-weight:bold;   /*--폰트 굵기---*/
color:#ff0080;    /*--폰트 색깔---*/
width:130px;
height:50px;  /*--버튼 크기---*/
}
	.footer-box{
	display: flex;
	justify-content:center;
	width:100%;
	height:100px;
	background-color: black;
	align-items:center;
	}

.form1{
height:750px;
	display: flex;
	justify-content:center;
		align-items:center;
		color:white;
}


    </style>
</head>
<body>	
 
 <%
	 String car_number = (String)request.getAttribute("car_number");
	 String result = String.valueOf(request.getAttribute("result"));
 %>
 <div class="header" style="text-align:center;">
		<div class="title">
		<img src="images/jj.jpeg" class="header-logo">
			구공 공영 주차장 프로젝트<br>
			현재 분당 금액 :<%=mamont %>  월 금액 :<%=Mamont %> 
		</div>
	</div>
 
 
 
 <div class="row">
 차량번호는 ${car_number}입니다.<br><br>
 금액은 ${result}원 입니다.<br><br>
 결제 방식 및 금액을 투입해주세요.<br>
 </div>
 <div class="row">
	<form method="post" action="50cash.jae">
	<label>현금 입력 : </label><input type="text"  style="margin-top:20px;" placeholder="금액" name="cash" pattern=".{1,10}">
		<input type= "hidden" name="car_number" value="${car_number}">
		<input type= "hidden" name="result" value="${result}">
				 	<input type= hidden name ="Mamont" value="<%=Mamont%>"> 
						 	<input type= hidden name ="mamont" value="<%=mamont%>">
		<input type= "submit" class="btn" value="결제" style="margin-left:30px;">
	</form>
	</div>
	
	  <div class="row">
	<form method="post" action="50card.jae">
			 	<input type= hidden name ="Mamont" value="<%=Mamont%>"> 
						 	<input type= hidden name ="mamont" value="<%=mamont%>">
		<input type= "hidden" name="car_number" value="${car_number}">
		<input type= "hidden" name="result" value="${result}">
		<input type= "submit" class="btn" style="margin-top:20px; margin-left:30px;" class="card" value="카드 결제">
	</form>
	 </div>
	 <footer>
        <div class="column">
                <div class="footer-box">
                    <div class="footer">
                        <br><br>서울 금천구 가산디지털1로 
                    </div>
                </div>
            </div>
    </footer>
</body>
</html>