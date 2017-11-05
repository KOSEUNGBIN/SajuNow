


$(document).ready(function() {
	$("#register-form").submit(function() {
		var validation = true;
	
		// form 태그 내의 모든 정보를 불러옴
		var register_content = {
			reg_email : [ $('#reg_email').val().trim(), '이메일'],
			reg_password : [$('#reg_password').val().trim(), '패스워드'],
			reg_password_confirm : [$('#reg_password_confirm').val().trim(), '패스워드 확인'],
			reg_username : [$('#reg_username').val().trim(), '이름'],
			reg_birthday : [$('#reg_birthday').val().trim(), '생년월일'],
			reg_birthday_detail : [$('#reg_birthday_detail').val().trim().replace(/ /g, ''), '생년월일']
		};
		
		var register_content_checkbox = {
			reg_solarlunar1 : [$('#reg_solarlunar1'), '음력/평달'],
			reg_solarlunar2 : [$('#reg_solarlunar2'), '음력/윤달'],
			reg_solarlunar3 : [$('#reg_solarlunar3'), '양력/평달'],
			male : [$('#male'), '성별'],
			female : [$('#female'), '성별'],
			reg_agree : [$('#reg_agree') ,'이용약관 동의']
		};
		
		// 불러온 모든 정보를 확인하는 작업
		for ( var i in register_content) {
			if (register_content[i][0] == "") {
				alert(register_content[i][1] +"에 기입되지 않은 정보가 있습니다.");
				validation = false;
				break;
			}
		}
		
		if (validation && (!register_content_checkbox.reg_solarlunar1[0].is(':checked')  && !register_content_checkbox.reg_solarlunar2[0].is(':checked') && !register_content_checkbox.reg_solarlunar3[0].is(':checked'))) {
			alert("음력/양력/윤달 중 하나를 체크해주세요.");
			validation = false;	
		}
		
		if (validation && (!register_content_checkbox.male[0].is(':checked')  && !register_content_checkbox.female[0].is(':checked'))) {
			alert("성별을 체크하지 해주세요.");
			validation = false;	
		}
		if (validation && !register_content_checkbox.reg_agree[0].is(':checked')) {
			alert("이용약관을 체크하지 않으셨습니다.");
			validation = false;		
		}
		
		var reg_validation_empty = /\s/g;
		
		for ( var i in register_content) {
			if (validation && reg_validation_empty.test(register_content[i][0])) {
				alert(register_content[i][1]+"에 빈칸이 있습니다.");
				validation = false;
				break;
			}
		}
		
		var reg_validation_email = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i; 

		if(validation && !reg_validation_email.test(register_content.reg_email[0])){
			alert("이메일의 형식이 올지 않습니다. ");
			validation = false;
		}
				
		var reg_validation_specailchar = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-_])(?=.*[0-9]).{6,12}$/;
		
		if(validation && !reg_validation_specailchar.test(register_content.reg_password[0])){
			alert("패스워드 형식은 영어+숫자+특수문자 조합으로 6 자 이상 12 자 이내 이어야 합니다. ");
			validation = false;
		}
		
		if(validation && (register_content.reg_password[0] != register_content.reg_password_confirm[0])){
			alert("패스워드 확인이 기존의 패스워드와 같지 않습니다. ");
			validation = false;
		}
		
		
		
		return validation;
			
		
		
	});
});

$(document).ready(function() {
	$("#reg_birthday").datepicker({
		changeMonth: true, 
		changeYear: true,
		nextText: '다음 달',
		prevText: '이전 달',
		dateFormat : 'yy-mm-dd',
		closeText: '닫기' 
	});
});


$(document).ready(function(){
    $(".time_element").timepicki({
    	show_meridian:false,
		min_hour_value:0,
		max_hour_value:23,
		step_size_minutes:1,
		overflow_minutes:true,
		increase_direction:'up',
		disable_keyboard_mobile: true
    });
  });