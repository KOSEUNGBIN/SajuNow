<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">

<head>
<style>
tr:nth-child(even) {
	background-color: #f2f2f2;
	text-align: center;
}

td {
	text-align: center;
	color: #000000;
}
</style>



</head>

<body>




	<!-- Header -->
	<a name="about"></a>
	<div class="intro-header"
		style="background: url(https://raw.githubusercontent.com/BlackrockDigital/startbootstrap-landing-page/gh-pages/img/intro-bg.jpg) no-repeat center center; padding-top: 50px; /* If you're making other pages, make sure there is 50px of padding to make sure the navbar doesn't overlap content! */ padding-bottom: 50px; text-align: center; color: #f8f8f8; background: url(https://raw.githubusercontent.com/BlackrockDigital/startbootstrap-landing-page/gh-pages/img/intro-bg.jpg) no-repeat center center; background-size: cover;">
		<div class="container">
			<div class="col">
				<div class="row">
					<div class="col-lg-12">
						<div class="intro-message">
							<h1>사주나우 이용자</h1>
							<h3>${user_count}명</h3>
						</div>
					</div>
					<div class="col-lg-12">
						<div class="intro-message">
							<h1>등록된 역술인</h1>
							<h3>${company_count}명</h3>
						</div>
					</div>
					<div class="col-lg-12">
						<div class="intro-message">
							<h1>진행 중인 상담</h1>
							<h3>${live_history_count}개</h3>
						</div>
					</div>
					<div class="col-lg-12">
						<div class="intro-message">
							<h1>누적 상담</h1>
							<h3>${history_count}개</h3>
						</div>
					</div>
				</div>
			</div>

		</div>
		<!-- /.container -->

	</div>

	<div style="width: 100%; height: 100px; border: none;"></div>
	<!-- /.intro-header -->

	<!-- Page Content -->

	<a name="services"></a>
	<div class="content-section-a">

		<div class="container">

			<hr class="section-heading-spacer">
			<div class="row">

				<h2 class="section-heading" align="center">누적 상담 상위권</h2>
				<table class="table-responsive" style="width: 100%; font-size: 30";>
					<!-- class table-condensed -->
<div style="width: 100%; height: 20px; border: none;"></div>

					<tbody>
						<tr background-color="#3498db">
							<td align="center" , font-style="bold">Rank</td>
							<td align="center">역술인명</td>
							<td align="center">상담 횟수</td>

						</tr>
						<c:set var='company_history_rank' value="${company_history_rank}" />

						<c:forEach items="${company_history_rank}"
							var="company_history_rank" varStatus="status">

							<tr style="height: 20px;">
								<td>${status.index+1}</td>
								<td>${company_history_rank.nick_name}</td>
								<td>${company_history_rank.count} 회</td>
							</tr>


						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>
		<!-- /.container -->

	</div>
	
	<div style="width: 100%; height: 50px; border: none;"></div>
	<!-- /.content-section-a -->

	<div class="content-section-a">
	<div class="container">

			<hr class="section-heading-spacer">
			<div class="row">
		<h2 class="section-heading" align="center">후기 수 상위권</h2>
		<div style="width: 100%; height: 20px; border: none;"></div>
		<table class="table-responsive" style="width: 100%; font-size: 30";>
			<!-- class table-condensed -->


			<tbody>
				<tr background-color="#3498db">
					<td align="center" , font-style="bold">Rank</td>
					<td align="center">역술인명</td>
					<td align="center">후기 갯수</td>

				</tr>
				<c:set var='company_report_rank' value="${company_report_rank}" />

				<c:forEach items="${company_report_rank}"
					var="company_report_rank" varStatus="status">

					<tr style="height: 20px;">
						<td>${status.index+1}</td>
						<td>${company_report_rank.nick_name}</td>
						<td>${company_report_rank.count} 개</td>
					</tr>


				</c:forEach>
			</tbody>
		</table>
<div style="width: 100%; height: 50px; border: none;"></div>
<hr class="section-heading-spacer">

	</div>
	
	</div>
	
	</div>
	
	<!-- /.content-section-b -->

	<div class="content-section-a"></div>
	<div style="width: 100%; height: 70px; border: none;"></div>

</body>

</html>
