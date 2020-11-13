<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <% 
	String Mamont = (String)request.getParameter("Mamont");
	String mamont = (String)request.getParameter("mamont");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="Stylesheet" href="css/bootstrap.css">
<link rel="Stylesheet" href="css/custom.css">
<title>jaewoo park</title>
  <style>
 

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
height:700px;

justify-content:center;
align-items:center;

color:gray;
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
		color:gray;
}

@media only screen and (max-width: 1715px){

	form{
		display:flex;
		flex-direction:column;
		align-items:center;
	}
	label{
	display:flex;
	align-items:center;
	}
	
	}
 
@media only screen and (max-width: 1000px) {

	form{
		display:flex;
		flex-direction:column;
		align-items:center;
	}
	label{
	display:flex;
	align-items:center;
	}
body{
background-color:#f8363662;;;
color:black;
}
.header{
color:black;
}
.footer-box{
background-color:#f8363662;;;}
	}
    </style>
</head>
<body>	
<div class="header" style="text-align:center;">
		<div class="title">
		<img src="images/jj.jpeg" class="header-logo">
			구공 공영 주차장 프로젝트<br>
			현재 10분당 금액 :<fmt:formatNumber value="${mamont}"/>   월 금액 :<fmt:formatNumber value="${Mamont}"/> 
		</div>
	</div>
	 <div class="row">
	<form method="post" action="joinCheck.hy" class="form1">
		<h3 style="text-align: center;">차량 가입 화면</h3>
			<input type="text" placeholder="00가0000" name="car_number" maxlength="20" style="margin-left:30px;" pattern="(\d?)(\d{2})([가-힣])(\d{4})" >
			<input type= hidden name ="Mamont" value="<%=Mamont%>"> 
			<input type= hidden name ="mamont" value="<%=mamont%>">
			<input type= "submit" class="btn" value="회원 가입" style="margin-left:30px;">
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
