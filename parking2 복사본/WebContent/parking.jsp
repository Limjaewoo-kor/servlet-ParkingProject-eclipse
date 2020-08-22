<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    session.getAttribute("ID");
    session.getAttribute("Password");
	String ID = (String)request.getParameter("ID1");
 	String mamont = String.valueOf(application.getAttribute("mamont"));
	String Mamont = String.valueOf(application.getAttribute("Mamont"));
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="Stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<title>jaewoo park</title>
 <style>
 @media only screen and (max-width: 1700px){
      .content{   
    display: flex;
    flex-direction: cloumn;
    flex-wrap: wrap;
    width: 90%;
    min-width: 500px;
    height:600px;
    }
    
	.manager{
	      justify-content:center;
	margin-top:30px;
	width: 100%;
	}
	.join {
	      justify-content:center;
	margin-top:30px;
	width: 100%;
	}
	.in{
	      justify-content:center;
	margin-top:30px;
	width: 100%;
	}
	.out{
	      justify-content:center;
	margin-top:30px;
	width: 100%;
	}
}  

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
	.header{
		display:flex;
		height:30vh;
		justify-content:center;
		padding-top:100px;
		font-size:30px;
		font-weight:bolder;
	}
	.container{
	 display: flex;
    flex-direction: cloumn;
    flex-wrap: wrap;
    width: 90%;
    min-width: 500px;
    height:510px;
   justify-content:center;
	}
	
	.manager{
	margin-left:30px;
	}
	.join {
margin-left:30px;
	}
	.in{
margin-left:30px;
	}
	.out{
margin-left:30px;
	}
	
	.footer{
	height:200px;
	background-color: black;
	}


    </style>
</head>
<body>
<%if(ID != null) {%>
<h3>관리자 ${ID}님 환영합니다.</h3> 
<form method="post" action="managerAction.lim">
	<input type= submit value="관리자페이지로">
	<input type= hidden name ="ID" value="${ID}">
	<input type= hidden name ="Password" value="${Password}">
</form>
 <a href="sessionLogout.jsp">로그아웃</a>
 <%} %>
	<div class="header" style="text-align:center;">
		<div class="title">
			공영 주차장 프로젝트
			
		</div>
	</div>
	<div class="container">
		<div class="content">
			<div class="in">
				<div class="column">			
					<form action="parkingCheck.lim" method="post">
						<label>입차번호 : </label><input type="text"  placeholder="차량번호" name="member_car" maxlength="8">
						<input type= submit name ="member_car" value="입차">
					</form>		
				</div>
			</div>
			<div class="out">
				<div class="column">
						<form action="parkingCheck1.lim" method="post">
							<label>출차번호: </label><input type="text"  placeholder="차량번호" name="car_number" maxlength="8">
						 	<input type= hidden name ="mamont" value="<%=mamont%>">

							<input type= "submit" name ="car_number" value="출차">
						</form>
				</div>
			</div>
			<div class = "manager">
				<div class="column">
					<form action = "managerLogin.jsp" method="post">
						<input type= "submit"  value="관리자 로그인">
					</form>						
				</div>
			</div>
			<div class = "join">
				<div class ="column">
					<form action = "regulerJoinAction.jsp" method="post">
						<input type= hidden name ="Mamont" value="<%=Mamont%>"> 
						<input type="submit" value="정기권 회원가입" style="margin-left:30px;">
					</form>
				</div>
				<div class ="column">
					<form action = "hybirdJoin.jsp" method="post">
						<input type="submit" value="하이브리드 등록" style="margin-left:30px;">
					</form>	
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
	
	
	</div>
</body>
</html>