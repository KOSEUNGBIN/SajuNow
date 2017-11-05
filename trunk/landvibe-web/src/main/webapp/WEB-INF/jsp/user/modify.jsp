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

<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/css/timepicki.css' />">
<script src="<c:url value='/resources/js/timepicki.js' />"></script>


<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />

<link href="<c:url value="/resources/css/sign.css" />" rel="stylesheet">
<script src="<c:url value='/resources/js/user.js' />"></script>



<!-- REGISTRATION FORM -->
<div class="text-center" style="padding: 40px 0">
	<div class="logo">개인 정보 변경</div>
	<!-- Main Form -->
	<div class="login-form-1">
		<form id="update-form" class="text-left" action="/user/update"
			method="post" >
			<div class="login-form-main-message"></div>
			<div class="main-login-form">
				<div class="login-group">

					<div class="form-group">
						<label for="reg_email" class="sr-only">Email</label> <input
							type="text" class="form-control" id="reg_email" name="email"
							placeholder="email" value=${user.email} disabled>
					</div>

					<div class="form-group">
						<label for="reg_password" class="sr-only">Password</label> <input
							type="password" class="form-control" id="reg_password"
							name="password" placeholder="password">
					</div>

					<div class="form-group">
						<label for="reg_username" class="sr-only">name</label> <input
							type="text" class="form-control" id="reg_username" name="name"
							placeholder="username" value=${user.name}>
					</div>

					<div class="form-group">
						<label for="reg_birthday" class="sr-only">birthday</label> <input
							type="text" class="form-control" id="reg_birthday"
							name="birthday" placeholder="birthday" value=${user.birthday}>
					</div>

					<div class="form-group">
						<label for="reg_birthday_detail" class="sr-only">birthday_detail</label>
						<input type="text" class="form-control time_element"
							id="reg_birthday_detail" name="birthday_detail"
							placeholder="birthday_detail" style="border: none"
							value=${user.birthday_detail}>
					</div>


					<div class="form-group login-group-checkbox">
						<c:choose>
							<c:when test="${user.solarlunar eq 0}">
								<input type="radio" class="form-control" name="solarlunar"
									id="reg_solarlunar1" placeholder="음력/평달" value=0 checked="true">
								<label for="reg_solarlunar1">음력/평달</label>
								<input type="radio" class="form-control" value=1
									name="solarlunar" id="reg_solarlunar2" placeholder="음력/윤달">
								<label for="reg_solarlunar2">음력/윤달</label>
								<input type="radio" class="form-control" value=2
									name="solarlunar" id="reg_solarlunar3" placeholder="양력/평달">
								<label for="reg_solarlunar3">양력/평달</label>
							</c:when>
							<c:when test="${user.solarlunar eq 1}">
								<input type="radio" class="form-control" value=0
									name="solarlunar" id="reg_solarlunar1" placeholder="음력/평달">
								<label for="reg_solarlunar1">음력/평달</label>
								<input type="radio" class="form-control" value=1
									name="solarlunar" id="reg_solarlunar2" placeholder="음력/윤달"
									checked="true">
								<label for="reg_solarlunar2">음력/윤달</label>
								<input type="radio" class="form-control" value=2
									name="solarlunar" id="reg_solarlunar3" placeholder="양력/평달">
								<label for="reg_solarlunar3">양력/평달</label>
							</c:when>
							<c:otherwise>
								<input type="radio" class="form-control" value=0
									name="solarlunar" id="reg_solarlunar1" placeholder="음력/평달">
								<label for="reg_solarlunar1">음력/평달</label>
								<input type="radio" class="form-control" value=1
									name="solarlunar" id="reg_solarlunar2" placeholder="음력/윤달">
								<label for="reg_solarlunar2">음력/윤달</label>
								<input type="radio" class="form-control" value=2
									name="solarlunar" id="reg_solarlunar3" placeholder="양력/평달"
									checked="true">
								<label for="reg_solarlunar3">양력/평달</label>
							</c:otherwise>
						</c:choose>
					</div>


					<div class="form-group login-group-checkbox">
						<c:choose>
							<c:when test="${user.gender eq 0}">
								<input type="radio" class="form-control" name="gender" id="male"
									placeholder="male" value=0 checked="true">
								<label for="male">male</label>
								<input type="radio" class="form-control" name="gender"
									id="female" value=1 placeholder="female">
								<label for="female">female</label>
							</c:when>
							<c:otherwise>
								<input type="radio" class="form-control" value=0 name="gender"
									id="male" placeholder="male">
								<label for="male">male</label>
								<input type="radio" class="form-control" value=1 name="gender"
									id="female" placeholder="female" checked="true">
								<label for="female">female</label>
							</c:otherwise>
						</c:choose>
					</div>

				</div>
				<button type="submit" class="login-button">
					<i class="fa fa-chevron-right"></i>
				</button>
			</div>
			<div class="etc-login-form">
				<p>
					<a href="<c:url value="/sign/in" />"> 비밀번호 변경할래요?</a>
				</p>
			</div>
		</form>
	</div>
	<!-- end:Main Form -->
</div>

