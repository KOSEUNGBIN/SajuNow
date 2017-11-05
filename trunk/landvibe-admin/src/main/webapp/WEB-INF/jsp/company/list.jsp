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
</head>

<div style="width: 100%; height: 22px; border: none;"></div>

<div class="container-fluid"
	style="background-color: #2196F3; color: #fff; height: 200px;">
	<h1>역술인 목록</h1>
	<p>
		<strong>해당 이메일을 클릭하시면 역술인 정보로 이동할 수 있습니다.</strong>
	</p>
</div>

<div style="width: 100%; height: 100px; border: none;"></div>

<div class='row'>
	<div class='col-md-2'></div>

	<div class='col-md-8' style="overflow-x: auto;">
		<div class='row'>
			<div class='col-md-12 table-responsive'>
				<table class="table table-striped">
					<thead style="background-color: #000000; color: white;">
						<tr>
							<th>#</th>
							<th>Name</th>
							<th>Email</th>
							<th>간단상담 개수</th>
							<th>간단상담 가능 개수</th>
							<th>간단상담 가능/불가능</th>
							<th>간단상담 이용여부</th>
							<th>전문상담 개수</th>
							<th>전문상담 가능 개수</th>
							<th>전문상담 가능/불가능</th>
							<th>평점</th>
						</tr>
					</thead>
					<tbody>
						<c:set var='companyList' value="${companyList}" />
						<c:forEach var='item' items="${companyList}">
							<tr>
								<th scope="row">${item.company_no}</th>
								<td>${item.nick_name}</td>
								<td><a href="/company/detail/${item.company_no}">${item.email}</a></td>
								<c:choose>
									<c:when test="${item.history_simple eq true}">
										<td>${item.simple_chat_count}</td>
										<td>${item.simple_history_possibility_count}</td>
										<c:choose>
											<c:when test="${item.simple_chat_possibility_result eq true}">
												<td><span class="label label-pill label-success">가능</span></td>
											</c:when>
											<c:when
												test="${item.simple_chat_possibility_result eq false}">
												<td><span class="label label-pill label-danger">불가능</span></td>
											</c:when>
											<c:otherwise></c:otherwise>
										</c:choose>
										<td><span class="label label-pill label-info">O</span></td>
									</c:when>
									<c:when test="${item.history_simple eq false}">
										<td>-</td>
										<td>-</td>
										<td>-</td>
										<td><span class="label label-pill label-warning">X</span></td>
									</c:when>
									<c:otherwise></c:otherwise>
								</c:choose>

								<td>${item.chat_count}</td>
								<td>${item.history_possibility_count}</td>
								<c:choose>
									<c:when test="${item.chat_possibility_result eq true}">
										<td><span class="label label-pill label-success">가능</span></td>
									</c:when>
									<c:when test="${item.chat_possibility_result eq false}">
										<td><span class="label label-pill label-danger">불가능</span></td>
									</c:when>
									<c:otherwise>
										<td>error</td>
									</c:otherwise>
								</c:choose>
								<td><span class="label label-info">${item.score_average}</span></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class='row'>
			<div class='col-md-4'></div>
			<div class='col-md-4'
				style="display: inline-block; text-align: center;">
				<ul class="pagination pagination-lg">
					<c:set var="pagegroup_no" value="${pagegroup_no}" />
					<li><a href="/company/${pagegroup_no[2]}">&laquo;</a></li>
					<c:forEach begin="${pagegroup_no[0]}" end="${pagegroup_no[1]}"
						step="1" var="i">
						<li><a href="/company/${i}"><c:out value="${i}" /></a></li>
					</c:forEach>
					<li><a href="/company/${pagegroup_no[3]}">&raquo;</a></li>
				</ul>
			</div>
			<div class='col-md-4'></div>
		</div>
		<div style="width: 100%; height: 50px; border: none;"></div>
	</div>

	<div class='col-md-2'></div>

</div>