<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    session.getAttribute("ID");
    session.getAttribute("Password");
	String ID = (String)request.getParameter("ID1");
 	String mamont = (String)request.getParameter("mamont");
	String Mamont = (String)request.getParameter("Mamont");
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="Stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<title>jaewoo park</title>
 <style>
 @media only screen and (max-width: 1715px){

	form{
		display:flex;
		align-items:center;
	}
	label{
	display:flex;
	align-items:center;
	}

}  

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
	}
	.header-logo{
	margin-bottom:10px;
  max-width:230px;
  max-height:95px;
  width: auto;
  height: auto;
	}
	.container{
	background-image:url("images/qq.jpg") ;
background-size: 100% 350px;
	 display: flex;
    flex-direction: cloumn;
    flex-wrap: wrap;
    width: 90%;
    min-width: 500px;
    height:750px;
   justify-content:center;
      margin-left:5%; 
      align-items:center;
	}
	.content{
	color:black;
	align-items:center;

	}
	.content2{
	background-image:url("images/cc.jpg") ;
background-size: 100% 350px;

	  display: flex;
    flex-direction: cloumn;
    flex-wrap: wrap;
    width: 100%;
    min-width: 500px;
    height:350px;
   justify-content:center;
	align-items:center;
	}
	.content2:hover{
    opacity: 1.2;
    transform: scale(1.2);
    transition: transform 0.5s, opacity 0.5s;
    
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
	
	.footer-box{
	display: flex;
	justify-content:center;
	width:100%;
	height:100px;
	background-color: black;
	align-items:center;
	}
.content2-logo{
margin-top:150px;
width:130px;
background-color:white;
}



    </style>
</head>
<body>
<%if(ID != null) {%>
<h3>관리자 ${ID}님 환영합니다.</h3> 
<form method="post" action="managerAction.lim">
	<input type= submit value="관리자페이지로">
	 <input type= hidden name ="Mamont" value="<%=Mamont%>"> 
	<input type= hidden name ="mamont" value="<%=mamont%>">
	<input type= hidden name ="ID" value="${ID}">
	<input type= hidden name ="Password" value="${Password}">
</form>
<form method="post" action="sessionLogout.jsp">
 <input type= hidden name ="Mamont" value="<%=Mamont%>"> 
	<input type= hidden name ="mamont" value="<%=mamont%>">
	<input type= submit value="로그아웃">

</form>

 <%} %>
	<div class="header" style="text-align:center;">
		<div class="title">
		<img src="images/jj.jpeg" class="header-logo">
			구공 공영 주차장 프로젝트<br>
					현재 분당 금액 :<%=mamont %>  월 금액 :<%=Mamont %> 
		
		</div>
	</div>
	<div class="container">
		<div class="content">
			<div class="in">
				<div class="column">			
					<form action="parkingCheck.lim" method="post">
						<label>입차 번호 </label><input type="text" style="width:200px; height:50px;"  placeholder="차량번호" name="member_car" pattern=".{6,8}"  pattern="(\d?)(\d{2})([가-힣])(\d{4})" >
							 <input type= hidden name ="Mamont" value="<%=Mamont%>"> 
						 	<input type= hidden name ="mamont" value="<%=mamont%>">
						<input type= submit name ="member_car" class="btn" value="입차">
					</form>		
				</div>
			</div>
			<div class="out">
				<div class="column">
						<form action="parkingCheck1.lim" method="post">
							<label>출차 번호 </label><input type="text" style="width:200px; height:50px;" placeholder="차량번호" name="car_number" pattern=".{6,8}"  pattern="(\d?)(\d{2})([가-힣])(\d{4})">
						 	<input type= hidden name ="Mamont" value="<%=Mamont%>"> 
						 	<input type= hidden name ="mamont" value="<%=mamont%>">
							<input type= "submit" name ="car_number" class="btn" value="출차">
						</form>
				</div>
			</div>
		</div>
		<div>
		
		</div>
	
	<div class="content2">
		<a href="http://90factory.com/"><img src="images/90.png" class="content2-logo"></a>
	</div>
	<div class="content3">
	<div class = "join">
				<div class ="column">
				<div class = "manager">
				<div class="column">
					<form action = "managerLogin.jsp" method="post">
					 	<input type= hidden name ="Mamont" value="<%=Mamont%>"> 
						 	<input type= hidden name ="mamont" value="<%=mamont%>">
						<input type= "submit" class="btn"  value="관리자 로그인">
					</form>						
				</div>
			</div>
					<form action = "regulerJoinAction.jsp" method="post">
						<input type= hidden name ="Mamont" value="<%=Mamont%>"> 
						<input type= hidden name ="mamont" value="<%=mamont%>">
						<input type="submit" class="btn" value="정기권 회원가입" style="margin-left:50px;">
					</form>
				</div>
				<div class ="column">
					<form action = "hybirdJoin.jsp" method="post">
						 	<input type= hidden name ="Mamont" value="<%=Mamont%>"> 
						 	<input type= hidden name ="mamont" value="<%=mamont%>">
						<input type="submit"  class="btn" value="하이브리드 등록" style="margin-left:50px;">
					</form>	
				</div>
			</div>
	
	</div>
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