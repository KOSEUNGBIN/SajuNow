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
				<a class="navbar-brand" href="/main"> 사주나우 관리자</a>
			</div>
			
       <ul class="nav navbar-nav navbar-right">
				<li><a href="/main/logout"><span class="glyphicon glyphicon-log-in"></span>
						Log-out</a></li>
					
			</ul>
            
			
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-left">

					<li><a href="/user/1">사용자관리</a></li>
					<li><a href="/company/1"> 역술인관리</a></li>
					<li><a href="/pay/exchange/list"> 수익관리</a></li>
					<li><a href="/ad"> 광고 관리</a></li>
					<li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">공지사항 관리 <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="/inform/user">사용자 공지사항 관리</a>
                            </li>
                            <li>
                               <a href="/inform/company">역술인 공지사항 관리</a>
                            </li>          
                        </ul>
                    </li>
					<li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">FAQ 관리 <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="/faq/user">사용자 FAQ 관리</a>
                            </li>
                            <li>
                               <a href="/faq/company">역술인 FAQ 관리</a>
                            </li>          
                        </ul>
                    </li>
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
    
   
        
          <footer class="footer">
      <div class="container" align="center">
       <img alt="" width="350px" height="70px" src=" http://oursoccer.co.kr/study/reserve/img/sajunow_footer.PNG">
        
      </div>
    </footer>
</body>
</html>

