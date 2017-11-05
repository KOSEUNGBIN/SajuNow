<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<head>
<style>
th {font-size: 18px;}
td {font-size: 18px;}
</style>
</head>

<div style="width: 100%; height: 22px; border: none;"></div>

<div class="container-fluid"
	style="background-color: #2196F3; color: #fff; height: 200px;">
	<h1>사용자 공지사항 목록</h1>
	<p>
		<strong>해당 제목을 클릭하시면 사용자 공지사항으로 이동할 수 있습니다.</strong>
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
							<th>Title</th>
							<th>Upload Date</th>
						</tr>
					</thead>
					<tbody>
						<c:set var='questionList' value="${questionList}" />
						<c:forEach var='item' items="${questionList}">
							<tr>
								<th scope="row">${item.question_no}</th>
								<td>관리자</td>
								<td><a href="/inform/user/modify/${item.question_no}">${item.title}</a></td>
								<td>${item.register_date}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class='row'>
			<div class='col-md-3'></div>
			<div class='col-md-6'></div>
			<div class='col-md-3'
				style="display: inline-block; text-align: center;">
				<a type="button" class="btn btn-primary btn-lg" href='/inform/user/add'>추가하기</a>
			</div>
		</div>
		<div style="width: 100%; height: 50px; border: none;"></div>
	</div>

	<div class='col-md-2'></div>

</div>