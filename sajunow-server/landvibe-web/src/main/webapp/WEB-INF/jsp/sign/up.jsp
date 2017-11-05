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
<div class="text-center" style="padding: 40px 0">
	<div class="logo">가입하기</div>
	<!-- Main Form -->
	<div class="login-form-1">
		<form id="register-form" class="text-left" action="up/submit"
			method="post">
			<div class="login-form-main-message"></div>
			<div class="main-login-form">
				<div class="login-group">

					<div class="form-group">
						<label for="reg_email" class="sr-only">이메일</label> <input
							type="text" class="form-control" id="reg_email" name="email"
							placeholder="이메일">
					</div>

					<div class="form-group">
						<label for="reg_password" class="sr-only">비밀번호</label> <input
							type="password" class="form-control" id="reg_password"
							name="password" placeholder="비밀번호">
					</div>
					<div class="form-group">
						<label for="reg_password_confirm" class="sr-only">비밀번호 확인
							Confirm</label> <input type="password" class="form-control"
							id="reg_password_confirm" name="password_confirm"
							placeholder="비밀번호 확인">
					</div>

					<div class="form-group">
						<label for="reg_username" class="sr-only">이름</label> <input
							type="text" class="form-control" id="reg_username" name="name"
							placeholder="이름 (본명)">
					</div>

					<div class="form-group">
						<label for="reg_birthday" class="sr-only">생년월일</label> <input
							type="text" class="form-control" id="reg_birthday"
							name="birthday" placeholder="생년월일">
					</div>

					<div class="form-group">
						<label for="reg_birthday_detail" class="sr-only">태어난 시간</label>
						<input type="text" class="form-control time_element" id="reg_birthday_detail"
							name="birthday_detail" placeholder="태어난 시간" style = "border:none">
					</div>
					
					
					<div class="form-group login-group-checkbox">
						<input type="radio" class="form-control" name="solarlunar" id="reg_solarlunar1"
							placeholder="음력/평달" value='0'> <label for="reg_solarlunar1">음력/평달</label> <input
							type="radio" class="form-control" name="solarlunar" id="reg_solarlunar2"
							placeholder="음력/윤달" value='1'> <label for="reg_solarlunar2">음력/윤달</label>
							<input
							type="radio" class="form-control" name="solarlunar" id="reg_solarlunar3"
							placeholder="양력/평달" value='2'> <label for="reg_solarlunar3">양력/평달</label>
					</div>
					

					<div class="form-group login-group-checkbox">
						<input type="radio" class="form-control" name="gender" id="male"
							placeholder="male" value='0'> <label for="male">남자</label> <input
							type="radio" class="form-control" name="gender" id="female"
							placeholder="female" value='1'> <label for="female">여자</label>
					</div>

					<div class="form-group login-group-checkbox">
						<input type="checkbox" class="form-control" id="reg_agree" name="reg_agree">
						<label for="reg_agree">약관을 확인했고 동의합니다. <a href="agreement">약관보기</a></label>
					</div>
				</div>
				<button type="submit" class="login-button">
					<i class="fa fa-chevron-right"></i>
				</button>
			</div>
			<div class="etc-login-form">
				<p>
					이미 가입하셨나요? <a href="<c:url value="in" />">로그인 화면으로 이동</a>
				</p>
			</div>
		</form>
	</div>
	<!-- end:Main Form -->
</div>
