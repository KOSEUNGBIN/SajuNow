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
	<h1>사용자 목록</h1>
	<p>
		<strong>해당 제목을 클릭하시면 사용자 정보로 이동할 수 있습니다.</strong>
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
							<th>Birth Day</th>
							<th>Birth Time</th>
							<th>Gender</th>
							<th>Solar/Lunar</th>
						</tr>
					</thead>
					<tbody>
						<c:set var='userList' value="${userList}" />
						<c:forEach var='item' items="${userList}">

							<c:set var="gender" value="${item.gender}" />
							<c:set var="solarlunar" value="${item.solarlunar}" />

							<tr>
								<th scope="row">${item.user_no}</th>
								<td>${item.name}</td>
								<td><a href="/user/detail/${item.user_no}">${item.email}</a></td>
								<td>${item.birthday}</td>
								<td>${item.birthday_detail}</td>
								<c:choose>
									<c:when test="${gender eq 0}">
										<td>남자</td>
									</c:when>
									<c:when test="${gender eq 1}">
										<td>여자</td>
									</c:when>
									<c:otherwise>
										<td>error</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${solarlunar eq 0}">
										<td>음력/평달</td>
									</c:when>
									<c:when test="${solarlunar eq 1}">
										<td>음력/윤달</td>
									</c:when>
									<c:when test="${solarlunar eq 2}">
										<td>양력/평달</td>
									</c:when>
									<c:otherwise>
										<td>error</td>
									</c:otherwise>
								</c:choose>
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
					<li><a href="/user/${pagegroup_no[2]}">&laquo;</a></li>
					<c:forEach begin="${pagegroup_no[0]}" end="${pagegroup_no[1]}"
						step="1" var="i">
						<li><a href="/user/${i}"><c:out value="${i}" /></a></li>
					</c:forEach>
					<li><a href="/user/${pagegroup_no[3]}">&raquo;</a></li>
				</ul>
			</div>
			<div class='col-md-4'></div>
		</div>
		<div style="width: 100%; height: 50px; border: none;"></div>
	</div>

	<div class='col-md-2'></div>

</div>