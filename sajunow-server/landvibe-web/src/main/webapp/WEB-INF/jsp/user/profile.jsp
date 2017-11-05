<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- All the files that are required -->
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href='http://fonts.googleapis.com/css?family=Varela+Round'
	rel='stylesheet' type='text/css'>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
	type="text/css" />
	
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="jquery-1.11.3.min.js"></script>

<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/timepicki.css' />">
<script src="<c:url value='/resources/js/timepicki.js' />"></script>


<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />

<link href="<c:url value="/resources/css/sign.css" />" rel="stylesheet">
<script src="<c:url value='/resources/js/signup.js' />"></script>



<!-- REGISTRATION FORM -->
<div class="text-center" style="padding: 40px" >
	<div class="logo">profile</div>
	<!-- Main Form -->
	<div class="login-form-1" style= "width: 1800px">
		<form id="register-form" class="text-left" action="/user/modify"
			method="post">
			<div class="login-form-main-message"></div>
			<div class="main-login-form">
				<div class="login-group">

					<div class="form-group">
						<label for="reg_email" class="sr-only">Email</label> <input
							type="text" class="form-control" id="reg_email" name="email"
							placeholder="이메일 : ${user.email}" readonly>
					</div>

					<div class="form-group">
						<label for="reg_username" class="sr-only">name</label> <input
							type="text" class="form-control" id="reg_username" name="name"
							placeholder="이름 : ${user.name}" readonly>
					</div>
					
					<c:choose>
					<c:when test="${user.solarlunar eq 0}"> 
					<div class="form-group">
						<label for="reg_username" class="sr-only">name</label> <input
							type="text" class="form-control" id="reg_username" name="name"
							placeholder="생일: ${user.birthday} (음력/평달)" readonly>		 
					</div>
					
					</c:when>
					<c:when test="${user.solarlunar eq 1}"> 
					<div class="form-group">
						<label for="reg_username" class="sr-only">name</label> <input
							type="text" class="form-control" id="reg_username" name="name"
							placeholder="생일: ${user.birthday} (음력/윤달)" readonly>		 
					</div>
					
					</c:when>
					<c:otherwise>
					<div class="form-group">
						<label for="reg_username" class="sr-only">name</label> <input
							type="text" class="form-control" id="reg_username" name="name"
							placeholder="생일: ${user.birthday} (양력/평달)" readonly>		 
					</div>
					
					</c:otherwise>
					</c:choose>

					<div class="form-group">
						<label for="reg_username" class="sr-only">name</label> <input
							type="text" class="form-control" id="reg_username" name="name"
							placeholder="출생 시간: ${user.birthday_detail}" readonly>
					</div>
					<c:choose>
					<c:when test="${user.gender eq 0}"> 
					<div class="form-group">
						<label for="reg_username" class="sr-only">name</label> <input
							type="text" class="form-control" id="reg_username" name="name"
							placeholder="성별: 남자" readonly>		 
					</div>
					
					</c:when>
					<c:otherwise>
					<div class="form-group">
						<label for="reg_username" class="sr-only">name</label> <input
							type="text" class="form-control" id="reg_username" name="name"
							placeholder="성별: 여자" readonly>		 
					</div>
					
					</c:otherwise>
					</c:choose>
					
				</div>
				<button type="submit" class="login-button"  title="개인 정보 변경">
					<i class="fa fa-chevron-right"></i>
				</button>
			</div>
		</form>
	</div>
	<!-- end:Main Form -->
</div>
