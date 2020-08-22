<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
  session.getAttribute("ID");
  session.getAttribute("Password");
	String ID1 = (String)request.getParameter("ID1");

%>
<%@ page import="java.io.PrintWriter" %>

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
<%if(ID1 != null) {%>
<h3>관리자 ${ID1}님 환영합니다.</h3> 
 <a href="sessionLogout.jsp">로그아웃</a>
 <%} %>
	<div class="header" style="text-align:center;">
		<div class="title">
			공영 주차장 프로젝트
		</div>
	</div>
	<div class="container">
		<div class="content">
			<div class = "manager">
				<div class="column">
					관리자 로그인
					<form action = "managerAction.lim" method="post">
						<input type="text" placeholder="아이디" name="ID" maxlength="20">	
						<input type="password" placeholder="비밀번호" name="Password" maxlength="20">
						<input type= "submit"  value="로그인">
					</form>						
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
	
	
	</div>
</body>
</html>