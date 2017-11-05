<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html lang="ko">
<head>
<title><decorator:title /></title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="No-Cache">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<decorator:head />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

</head>


<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/main/all/0">  SajuNow 사주나우</a>
			</div>
			
			<c:choose>
    <c:when test="${not empty user}">
       <ul class="nav navbar-nav navbar-right">
				<li><a href="/user/profile"><span class="glyphicon glyphicon-user"></span>
						Profile</a></li>
				<li><a href="/sign/out"><span class="glyphicon glyphicon-log-in"></span>
						Log-out</a></li>
					
			</ul>
    </c:when>
             <c:otherwise>
			<ul class="nav navbar-nav navbar-right">
				
				<li><a href="/sign/up"><span class="glyphicon glyphicon-user"></span>
						가입하기</a></li>
				<li><a href="/sign/in"><span class="glyphicon glyphicon-log-in"></span>
						로그인</a></li>
					
			</ul>
			
			 </c:otherwise>
</c:choose>
			
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-left">

					<li><a href="/main/about">About</a></li>
					<li><a href="/sign/agreement"> 이용약관 </a></li>
					

				</ul>

			</div>

			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->


	</nav>

	<!--   <li role="presentation"><a href="/companys">역술인 목록</a></li>
  <li role="presentation"><a href="/users/login">로그인</a></li>
  <li role="presentation"><a href="/users/register">회원가입</a></li>
  <li role="presentation"><a href="/notices">공지사항</a></li>
  <li role="presentation"><a href="/faq">FAQ</a></li>
</ul> -->
	<div style="width: 100%; height: 30px; border: none; "></div>
	<div id="wrap">
		<decorator:body />
	</div>
	
	
    <!-- Footer -->
    
   
        
       <!--    <footer class="footer">
      <div class="container" align="center">
       <img alt="" width="350px" height="70px" src=" http://oursoccer.co.kr/study/reserve/img/sajunow_footer.PNG">
        
      </div>
    </footer> -->
     <div id=footer style="background-color:#E6E6E6;">
<center>
<br> 
<table width="950" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="91%" align="left"><h3> 사주나우
      </h3>
      상호 : 나우랩 &nbsp;&nbsp; | &nbsp;대표 : 정재환    &nbsp;&nbsp; 사업자등록번호 : 329-10-00327 &nbsp;&nbsp; 이메일 : chungjaehwan@naver.com
      <br />
      주소 : 서울특별시 서대문구 포방터10길 33, 105동 1504호  &nbsp;&nbsp;   전화번호 : 02-1800-5169 &nbsp;&nbsp; © 2016 NOWLAB All Rights Reserved.
      <br />
      <br />
      <br />
     
  </tr>
</table>
</center>
</div>

</div>
</body>
</html>

