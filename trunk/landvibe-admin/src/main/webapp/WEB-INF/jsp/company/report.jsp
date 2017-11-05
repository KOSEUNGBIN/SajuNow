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

input[type=checkbox] {
	width: 20px;
	height : 20px;
	right : 0dp;
	
}
</style>
<c:set var="company" value="${company}" />
<c:set var="companyToReport" value="${company.companyToReport}" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="jquery-1.11.3.min.js"></script>

</head>

<div style="width: 100%; height: 80px; border: none;"></div>

<!-- Page Content -->
<div class="container">


	<!------------------------------------------------   Advertisement    ------------------------------------- -->

	<div class="col-md-20">



		<!------------------------------------------------   Company list    ------------------------------------- -->
		
		<form action="/company/report/delete/${company.company_no}" method="post">
		<div style="width: 500px; height: 500; border: none;"></div>

		<div class="row">
			<c:forEach items="${companyToReport}" var='list'>
				<%-- <c:if test="${(status.index) > 4}">
					<div style="width: 500px; height: 50px; border: none;"></div>
					</c:if> --%>
				<div class="col-sm-4 col-lg-4 col-md-4">
					<div class="company-list">	
						<div class="thumbnail">
							<input type="checkbox" class="pull-right" name="report_no_list" id="report_no_list" value="${list.report_no}">
							<div class="caption">
								<h4>${list.comment}</h4>
							</div>
							<div class='row'>
								<div class="col-sm-3 col-lg-3 col-md-3 pull-left" style="min-height: 100px;">${list.user_nickname}</div>
								<div class="col-sm-3 col-lg-3 col-md-3"><span class="label label-success">${list.score} stars</span></div>
								<div class="col-sm-6 col-lg-6 col-md-6 pull-right">${list.register_date}</div>
							</div>
						</div>
					</div>
				</div>

			</c:forEach>
		
		</div>
		
		<div style="width: 500px; height: 24px; border: none;"></div>
		
		<div class="row">
		<div class="col-sm-9 col-lg-9 col-md-9"></div>
		<div class="col-sm-2 col-lg-2 col-md-2">
			<button type="button" class="btn btn-primary btn-lg pull-right" id='report_all_select_btn' value=false>전체선택</button>
		</div>
		<div class="col-sm-1 col-lg-1 col-md-1">
			<input type="submit" class="btn btn-primary btn-lg pull-right"
							id='report_delete_btn' value="삭제">
		</div>
		</div>	
	</form>

	</div>

</div>
<!-- /.container -->



<script type="text/javascript">
	$("#report_all_select_btn").click(function() {
		if($(this).val() != "true"){
			$("input[type=checkbox]").prop("checked", true);
			$(this).val(true);
		}
		else{
			$("input[type=checkbox]").prop("checked", false);
			$(this).val(false);
		}
	});
</script>




