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
<c:set var="user" value="${user}" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="jquery-1.11.3.min.js"></script>

</head>

<div style="width: 100%; height: 22px; border: none;"></div>

<div class="container-fluid"
	style="background-color: #2196F3; color: #fff; height: 200px;">
	<h1>사용자 ${user.name}님의 정보</h1>
	<p>
		<strong>사용자 ${user.name}님의 정보를 열람하실 수 있습니다.</strong>
	</p>
</div>

<div style="width: 100%; height: 100px; border: none;"></div>

<div class='row'>
	<div class='col-md-2'></div>

	<div class='col-md-8' style="overflow-x: auto;">
		<div class='row'>
			<div class="col-md-2">
				<div class="alert alert-success" role="alert"
					style="text-align: center;">
					<h3>상담 내역</h3>
				</div>
			</div>
			<div class='col-md-8'></div>
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
							<th colspan="3">Realm</th>
						</tr>
					</thead>
					<tbody>
						<c:set var='companylist' value="${CompanyList}" />
						<c:forEach var='item' items="${companylist}">
							<tr>
								<th scope="row">${item.history_no}</th>
								<td>${item.nick_name}</td>
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
								<c:set var='historylist' value="${item.historyList}" />
								<td colspan="3"><c:forEach var='historyitem'
										items="${historylist}">
									${historyitem.category_name}/
								</c:forEach></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div style="width: 100%; height: 50px; border: none;"></div>
	</div>

	<div class='col-md-2'>
		<c:choose>
			<c:when test="${user.user_reg_id ne 'BLOCK'}">
				<button type="button" class="btn btn-success"
					style="position: fixed; top: 500px; width: 15%;" id="user_pause"
					value=false>
					<h5>${user.name}님은 활성화되어 있습니다.</h5>
				</button>
			</c:when>
			<c:when test="${user.user_reg_id eq 'BLOCK'}">
				<button type="button" class="btn btn-danger"
					style="position: fixed; top: 500px; width: 15%;" id="user_pause"
					value=true>
					<h5>${user.name}님은 비활성화되어 있습니다.</h5>
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
						$('#user_pause')
								.click(
										function() {
											if ($('#user_pause').val() == 'false') {
												if (confirm('${user.name}'
														+ ' 사용자를 비활성화시키시 겠습니까?')) {
													$
															.ajax({
																url : "/user/pause",
																datatype : "String",
																async : false,
																type : "POST",
																data : {
																	"pause" : true,
																	"userNo" : '${user.user_no}'
																},
																success : function(
																		data) {
																	if (data) {
																		$(
																				'#user_pause')
																				.html(
																						'<h5>${user.name} 비활성화 상태가 되었습니다.</h5>')
																				.toggleClass(
																						"btn-success  btn-danger");
																		$(
																				'#user_pause')
																				.val(
																						true);
																	} else {
																		alert('${user.name}'
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
												if (confirm('${user.name}'
														+ ' 사용자의 활성화시키시 겠습니까?')) {
													$
															.ajax({
																url : "/user/pause",
																datatype : "String",
																async : false,
																type : "POST",
																data : {
																	"pause" : false,
																	"userNo" : '${user.user_no}'
																},
																success : function(
																		data) {
																	if (data) {
																		$(
																				'#user_pause')
																				.html(
																						'<h4>${user.name} 활성화되었습니다.</h4>')
																				.toggleClass(
																						"btn-danger btn-success");
																		$(
																				'#user_pause')
																				.val(
																						false);
																	} else {
																		alert('${user.name}'
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

