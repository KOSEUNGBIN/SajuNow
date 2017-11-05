<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<head>
<style>
th {
	font-size: 18px;
}

td {
	font-size: 18px;
}
</style>
<c:set var="company" value="${company}" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="jquery-1.11.3.min.js"></script>

</head>

<div style="width: 100%; height: 22px; border: none;"></div>

<div class="container-fluid"
	style="background-color: #2196F3; color: #fff; height: 200px;">
	<h1>사용자 ${company.nick_name}님의 정보</h1>
	<p>
		<strong>사용자 ${company.nick_name}님의 정보를 열람하실 수 있습니다.</strong>
	</p>
</div>

<div style="width: 100%; height: 100px; border: none;"></div>

<div class='row'>
	<div class='col-md-2'></div>

	<div class='col-md-8' style="overflow-x: auto;">
		<div class='row thumbnail'>
			<div class="col-md-4"
				style="display: inline-block; text-align: center;">
				<img class="img-rounded"
					src="http://landvibe.oursoccer.co.kr/company/image/${company.company_no}"
					alt="Cinque Terre" width="300" height='300'>
			</div>
			<div class='col-md-6'>
				<p>이름 : ${company.nick_name}</p>
				<p>이메일 : ${company.email}</p>
				<p>영업 시간 : ${company.possible_time}</p>
				<p>자기소개: ${company.introduce}</p>
				<p>약력 : ${company.experience}</p>
				<p>인사말 : ${company.greeting}</p>
				<p>주소 : ${company.address}</p>
				<p>평점 : ${company.score_average}</p>
				<c:choose>
					<c:when test="${company.history_simple eq true}">
						<p>간단 상담 중인 횟수 : ${company.simple_chat_count}</p>
						<p>간단 상담 가능 횟수 : ${company.simple_history_possibility_count}</p>
						<p>
							간단 상담가능/불가능 :
							<c:choose>
								<c:when test="${company.simple_chat_possibility_result eq true}">
									<span class="label label-info"
										id='simple_chat_possibility_result'>상담가능</span>
								</c:when>
								<c:when
									test="${company.simple_chat_possibility_result eq false}">
									<span class="label label-danger"
										id='simple_chat_possibility_result'>상담불가능</span>
								</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
						</p>
					</c:when>
					<c:when test="${company.history_simple eq false}">
						<p>
							<span class="label label-danger">간단 사주를 이용하지 않는 역술인입니다.</span>
						</p>
					</c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
				<p>전문 상담 중인 횟수 : ${company.chat_count}</p>
				<p>전문 상담 가능 횟수 : ${company.history_possibility_count}</p>
				<p>
					전문 상담가능/불가능 :
					<c:choose>
						<c:when test="${company.chat_possibility_result eq true}">
							<span class="label label-info" id='chat_possibility_result'>상담가능</span>
						</c:when>
						<c:when test="${company.chat_possibility_result eq false}">
							<span class="label label-danger" id='chat_possibility_result'>상담불가능</span>
						</c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
			</div>
			<div class='col-md-2'>
				<div class='row'>
						<div class='col-md-8'>
							<div class="dropdown" style="top: 400px;">
								<button class="btn btn-primary dropdown-toggle" type="button"
									data-toggle="dropdown" name="premium" id="premium"
									value="${company.premium}">
									<c:choose>
										<c:when test="${company.premium eq 0}">
											일반 회원
										</c:when>
										<c:when test="${company.premium eq 1}">
											최고 프리미엄
										</c:when>
										<c:when test="${company.premium eq 2}">
											최상 프리미엄
										</c:when>
										<c:when test="${company.premium eq 3}">
											특급 프리미엄
										</c:when>
										<c:otherwise>프리미엄 선택 </c:otherwise>
									</c:choose>
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<li id="premium_1" value=0><a href="#">일반 회원</a></li>
									<li id="premium_2" value=1><a href="#">최고 프리미엄</a></li>
									<li id="premium_3" value=2><a href="#">최상 프리미엄</a></li>
									<li id="premium_4" value=3><a href="#">특급 프리미엄</a></li>
								</ul>
							</div>
						</div>
						<div class='col-md-4' style="top: 400px;">
							<button type="button" class="btn btn-primary btn-md"
								id="premium_submit">설정
								</button>
						</div>
				</div>
			</div>
		</div>
		<div class='row'>
			<div class="col-md-2">
				<div class="alert alert-success" role="alert"
					style="text-align: center;">
					<h3>상담 내역</h3>
				</div>
			</div>
			<div class='col-md-8'></div>
			<div class='col-md-2'>
				<button type="button" class="btn btn-warning btn-lg btn-block"
					onclick="location.href='/company/report/${company.company_no}'">
					<h4>리뷰 보기</h4>
				</button>
			</div>
		</div>
		<div class='row'>
			<div class='col-md-12 table-responsive'>
				<table class="table table-striped">
					<thead style="background-color: #000000; color: white;">
						<tr>
							<th>#</th>
							<th>Name</th>
							<th>simple/general</th>
							<th>Start Date</th>
							<th>End Date</th>
							<th>Consertting</th>
						</tr>
					</thead>
					<tbody>
						<c:set var='userlist' value="${UserList}" />
						<c:forEach var='item' items="${userlist}">
							<tr>
								<th scope="row">${item.history_no}</th>
								<td>${item.name}</td>
								<c:choose>
									<c:when test="${item.select_history eq 1}">
										<td><span class="label label-warning">간단상담</span></td>
									</c:when>
									<c:when test="${item.select_history eq 0}">
										<td><span class="label label-info">전문상담</span></td>
									</c:when>
									<c:otherwise></c:otherwise>
								</c:choose>
								<td>${item.start_date}</td>
								<c:choose>
									<c:when test="${item.end_yn eq false}">
										<td>/</td>
										<td><span class="label label-info">상담중</span></td>
									</c:when>
									<c:when test="${item.end_yn eq true}">
										<td>${item.end_date}</td>
										<td><span class="label label-danger">상담종료</span></td>
									</c:when>
									<c:otherwise></c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class='row'>
				<form action="/company/possibility/${company.company_no}"
					method="post">
					<c:choose>
						<c:when test="${company.history_simple eq true}">
							<div class='col-md-2 '></div>
							<div class='col-md-2 '>
								<h4>간단 상담 가능 개수 설정 :</h4>
							</div>
							<div class='col-md-2 '>
								<div class="input-group">
									<span class="input-group-btn">
										<button type="button" class="btn btn-danger btn-number"
											data-type="minus"
											data-field="simple_history_possibility_count">
											<span class="glyphicon glyphicon-minus"></span>
										</button>
									</span> <input type="text" name="simple_history_possibility_count"
										id="simple_history_possibility_count"
										class="form-control input-number"
										value="${company.simple_history_possibility_count}" min="1"
										max="100"> <span class="input-group-btn">
										<button type="button" class="btn btn-success btn-number"
											data-type="plus"
											data-field="simple_history_possibility_count">
											<span class="glyphicon glyphicon-plus"></span>
										</button>
									</span>
								</div>
							</div>
						</c:when>
						<c:when test="${company.history_simple eq false}">
							<div class='col-md-6 '></div>
						</c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
					<div class='col-md-2 '>
						<h4>전문 상담 가능 개수 설정 :</h4>
					</div>
					<div class='col-md-2 '>
						<div class="input-group">
							<span class="input-group-btn">
								<button type="button" class="btn btn-danger btn-number"
									data-type="minus" data-field="history_possibility_count">
									<span class="glyphicon glyphicon-minus"></span>
								</button>
							</span> <input type="text" name="history_possibility_count"
								id="history_possibility_count" class="form-control input-number"
								value="${company.history_possibility_count}" min="1" max="100">
							<span class="input-group-btn">
								<button type="button" class="btn btn-success btn-number"
									data-type="plus" data-field="history_possibility_count">
									<span class="glyphicon glyphicon-plus"></span>
								</button>
							</span>
						</div>
					</div>
					<div class='col-md-2 '>
						<input type="submit" class="btn btn-primary btn-md"
							id='history_count_config_btn' value="설정">
					</div>
				</form>
			</div>
			<div class='row'>
				<form action="/company/update/margin/${company.company_no}"
					method="post">
					<div class='col-md-6 '></div>
					<div class='col-md-2 '>
						<h4>margin 설정 :</h4>
					</div>
					<div class='col-md-2 '>
						<div class="input-group">
							<span class="input-group-btn">
								<button type="button" class="btn btn-danger btn-number"
									data-type="minus" data-field="margin">
									<span class="glyphicon glyphicon-minus"></span>
								</button>
							</span> <input type="text" name="margin"
								id="margin" class="form-control input-number"
								value="${company.margin}" min="1" max="100">
							<span class="input-group-btn">
								<button type="button" class="btn btn-success btn-number"
									data-type="plus" data-field="margin">
									<span class="glyphicon glyphicon-plus"></span>
								</button>
							</span>
						</div>
					</div>
					<div class='col-md-2 '>
						<input type="submit" class="btn btn-primary btn-md"
							id='margin_config_btn' value="설정">
					</div>
				</form>
			</div>
		</div>

		<div style="width: 100%; height: 50px; border: none;"></div>
	</div>

	<div class='col-md-2'>
		<c:choose>
			<c:when test="${company.company_reg_id ne 'BLOCK'}">
				<button type="button" class="btn btn-success"
					style="position: fixed; top: 500px; width: 15%;" id="company_pause"
					value=false>
					<h5>${company.nick_name}님은활성화되어있습니다.</h5>
				</button>
			</c:when>
			<c:when test="${company.company_reg_id eq 'BLOCK'}">
				<button type="button" class="btn btn-danger"
					style="position: fixed; top: 500px; width: 15%;" id="company_pause"
					value=true>
					<h5>${company.nick_name}님은비활성화되어있습니다.</h5>
				</button>
			</c:when>
			<c:otherwise></c:otherwise>
		</c:choose>
	</div>

</div>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('#company_pause')
								.click(
										function() {
											if ($('#company_pause').val() == 'false') {
												if (confirm('${company.nick_name}'
														+ ' 사용자를 비활성화시키시 겠습니까?')) {
													$
															.ajax({
																url : "/company/pause",
																datatype : "String",
																async : false,
																type : "POST",
																data : {
																	"pause" : true,
																	"companyNo" : '${company.company_no}'
																},
																success : function(
																		data) {
																	if (data) {
																		$(
																				'#company_pause')
																				.html(
																						'<h5>${company.nick_name} 비활성화 상태가 되었습니다.</h5>')
																				.toggleClass(
																						"btn-success  btn-danger");
																		$(
																				'#company_pause')
																				.val(
																						true);
																	} else {
																		alert('${company.nick_name}'
																				+ ' 사용자의  비활성화시키지 못하였습니다.');
																	}
																},
																error : function(
																		request,
																		status,
																		error) {
																	alert("code:"
																			+ request.status
																			+ "\n"
																			+ "error:"
																			+ error);
																}
															});
												}
											} else {
												if (confirm('${company.nick_name}'
														+ ' 사용자의 활성화시키시 겠습니까?')) {
													$
															.ajax({
																url : "/company/pause",
																datatype : "String",
																async : false,
																type : "POST",
																data : {
																	"pause" : false,
																	"companyNo" : '${company.company_no}'
																},
																success : function(
																		data) {
																	if (data) {
																		$(
																				'#company_pause')
																				.html(
																						'<h4>${company.nick_name} 활성화되었습니다.</h4>')
																				.toggleClass(
																						"btn-danger btn-success");
																		$(
																				'#company_pause')
																				.val(
																						false);
																	} else {
																		alert('${company.nick_name}'
																				+ ' 사용자의 활성화시키지 못하였습니다.');
																	}
																},
																error : function(
																		request,
																		status,
																		error) {
																	alert("code:"
																			+ request.status
																			+ "\n"
																			+ "error:"
																			+ error);
																}
															});
												}
											}
										});
					});
</script>
<script type="text/javascript">
	$('.btn-number').click(function(e) {
		e.preventDefault();

		fieldName = $(this).attr('data-field');
		type = $(this).attr('data-type');
		var input = $("input[name='" + fieldName + "']");
		var currentVal = parseInt(input.val());
		if (!isNaN(currentVal)) {
			if (type == 'minus') {

				if (currentVal > input.attr('min')) {
					input.val(currentVal - 1).change();
				}
				if (parseInt(input.val()) == input.attr('min')) {
					$(this).attr('disabled', true);
				}

			} else if (type == 'plus') {

				if (currentVal < input.attr('max')) {
					input.val(currentVal + 1).change();
				}
				if (parseInt(input.val()) == input.attr('max')) {
					$(this).attr('disabled', true);
				}

			}
		} else {
			input.val(0);
		}
	});
	$('.input-number').focusin(function() {
		$(this).data('oldValue', $(this).val());
	});
	$('.input-number').change(
			function() {

				minValue = parseInt($(this).attr('min'));
				maxValue = parseInt($(this).attr('max'));
				valueCurrent = parseInt($(this).val());

				name = $(this).attr('name');
				if (valueCurrent >= minValue) {
					$(
							".btn-number[data-type='minus'][data-field='"
									+ name + "']").removeAttr('disabled')
				} else {
					alert('Sorry, the minimum value was reached');
					$(this).val($(this).data('oldValue'));
				}
				if (valueCurrent <= maxValue) {
					$(
							".btn-number[data-type='plus'][data-field='" + name
									+ "']").removeAttr('disabled')
				} else {
					alert('Sorry, the maximum value was reached');
					$(this).val($(this).data('oldValue'));
				}

			});
</script>

<script type="text/javascript">
	$("li").click(function() {
		$("#premium").val($(this).val());
		$("#premium").text($(this).text());
	});
	
	$(document).ready(function() {
	$("#premium_submit").click(function(){
		if (confirm('${company.nick_name}'
				+ ' 에게 '+$("#premium").text()+'을 주시겠습니까?')) {
			$.ajax({
						url : "/company/update/premium/${company.company_no}",
						datatype : "String",
						async : false,
						type : "POST",
						data : {
							"premium" : $("#premium").val()
						},
						success : function(){
								alert('${company.nick_name}'
										+ ' 사용자에게  '+$("#premium").text()+'을 주었습니다.');
						},
						error : function(
								request,
								status,
								error) {
							alert("code:"
									+ request.status
									+ "\n"
									+ "error:"
									+ error);
						}
					});
		}
	});
});
	
</script>

