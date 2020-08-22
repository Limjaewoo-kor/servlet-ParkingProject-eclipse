<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
      	border:0;
      	padding:0;
        background-image: url( "images/imgj.jpeg" );
        height: 100%;
      	width:100%;
        background-size:cover;
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
	.container{
	margin-left:25%;
	}
    </style>
</head>
<body>
	<div class="title" style="text-align:center;">
		공영 주차장 프로젝트
	</div>
	<form method="post" action="hybirdJoin.lim">
		<h3 style="text-align: center;">차량 가입 화면</h3>
		<div class= "aa" style="margin-left:40%; margin-top:50px;">
		<div>
			<input type="text" placeholder="차 번호 " name="car_number" maxlength="20">
		</div>
		<input type= "submit"  value="회원가입">
		</div>
	</form>
</body>
</html>