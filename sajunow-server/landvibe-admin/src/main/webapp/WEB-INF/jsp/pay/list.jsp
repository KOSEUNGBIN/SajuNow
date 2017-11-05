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
	<h1>출금 요청 내역 </h1>
	<p>
		<strong> 출금 요청에 대한 처리를 완료하고 출금 완료 처리를 할 수 있습니다.</strong>
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
							<th># </th>
							<th>역술인</th>
							<th>출금요청액</th>
							<th>요청일 </th>
							<th> 상태  </th>
						</tr>
					</thead>
					<tbody>
						<c:set var='userList' value="${exchangeList}" />
						<c:forEach var='item' items="${exchangeList}">


							<tr>
								<th scope="row">${item.exchange_no}</th>
								<td><a href="/company/detail/${item.company_no}">${item.nick_name}</a></td>
								<td>${item.exchange_amount} 원 </td>
								<td>${item.exchange_date}</td>
								<td> 
								
								<form action="/pay/exchange/status/update/done/${item.exchange_no}"
					method="post">
				
					<div class='col-md-2 '>
						<input type="submit" class="btn btn-primary btn-md"
							id='margin_config_btn' value="입금 대기 " onclick="return confirm('이 출금 요청에 대한 완료 처리를 하시겠습니까?')">
					</div>
				</form>
								
			 </td>
								
							
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
		<div style="width: 100%; height: 50px; border: none;"></div>
	</div>

	<div class='col-md-2'></div>

</div>