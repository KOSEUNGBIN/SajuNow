
$(document).ready(function() {
	$("#update-form").submit(function() {
		var validation = true;
	
		// form 태그 내의 모든 정보를 불러옴
		var register_content = {
			reg_password : [$('#reg_password').val().trim(), '패스워드'],
			reg_username : [$('#reg_username').val().trim(), '이름'],
			reg_birthday : [$('#reg_birthday').val().trim(), '생년월일'],
			reg_birthday_detail : [$('#reg_birthday_detail').val().trim().replace(/ /g, ''), '생년월일']
		};	
		
		
		// 불러온 모든 정보를 확인하는 작업
		for ( var i in register_content) {
			if (register_content[i][0] == "") {
				alert(register_content[i][1] +"에 기입되지 않은 정보가 있습니다.");
				validation = false;
				break;
			}
		}
		
		var reg_validation_specailchar = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-_])(?=.*[0-9]).{6,12}$/;
		
		if(validation && !reg_validation_specailchar.test(register_content.reg_password[0])){
			alert("패스워드 형식은 영어+숫자+특수문자 조합으로 6 자 이상 12 자 이내 이어야 합니다. ");
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
		
		if(validation){
			$.ajax({
				url: "/user/check/psw",
				datatype : "String",
				async:false,
				type: "POST",
				data: { "email" : $("#reg_email").val(), "password" : register_content.reg_password[0] },
				success: function(data) {
				      if(!data){
				    	  validation = false;
				    	 alert("기존의 패스워드와 다릅니다. 다시 작성해 주세요.");
				      }
				},
				error:function(request,status,error){
					validation = false;
				     alert("code:"+request.status+"\n"+"error:"+error);
				}
			});
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