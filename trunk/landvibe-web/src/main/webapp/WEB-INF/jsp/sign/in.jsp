<!-- All the files that are required -->
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href='http://fonts.googleapis.com/css?family=Varela+Round'
	rel='stylesheet' type='text/css'>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<link href="<c:url value="/resources/css/sign.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/sign.js" />"></script>


<!-- Where all the magic happens -->
<!-- LOGIN FORM -->
<div class="text-center" style="padding: 50px 0">
	<div class="logo">사주나우 로그인</div>
	<!-- Main Form -->
	<c:if test="${requestScope.error_message!=null}">
<font color="red">${requestScope.error_message}</font> 
</c:if><br>
	<div class="login-form-1">
		<form name="login_form" class="text-left" method="post" action="/sign/in/submit" onsubmit="return loginCheck()">
			<div class="login-form-main-message"></div>
			<div class="main-login-form">
				<div class="login-group">
					<div class="form-group">
						<label for="email" class="sr-only">Email</label> 
						<input type="text" class="form-control"  name="email"
							placeholder="이메일">
					</div>
					<div class="form-group">
						<label for="password" class="sr-only">Password</label> <input
							type="password" class="form-control" id="password"
							name="password" placeholder="비밀번호">
					</div>
					<div class="form-group login-group-checkbox">
						<input type="checkbox" id="lg_remember" name="lg_remember">
						<label for="lg_remember">아이디 기억하기</label>
					</div>
				</div>
				<button type="submit" class="login-button" id="submit">
					<i class="fa fa-chevron-right"></i>
				</button>
			</div>
			<div class="etc-login-form">
				<p>
					비밀번호를 잊으셨나요? <a href="forget">이동</a>
				</p>
				<p>
					아이디가 없으신가요? <a href="up">이동</a>
				</p>
			</div>
		</form>
	</div>
	<!-- end:Main Form -->
</div>


<script type="text/javascript">
function loginCheck(){
 var email = document.login_form.email;
 var pwd = document.login_form.password;

 if(email.value==""){
  alert("아이디를 입력해 주세요.");
  email.focus();
  return false;
 }
 if(pwd.value==""){
  alert("비밀번호를 입력해 주세요.");
  pwd.focus();
  return false;
 }

}
</script>



