<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<head>
<link href="<c:url value='/resources/css/link.css'/>" rel="stylesheet" type="text/css" />

</head>
<!-- Page Content -->

<div style="width: 100%; height: 22px; border: none; "></div>

<!------------------------------------------------   Advertisement    ------------------------------------- -->
<div="row">
	<div class="col-sm-2 col-lg-2 col-md-2 col-xs-0"></div>

	<div class="container col-sm-8 col-lg-8 col-md-8 col-xs-12">
		<hr style="border: none" />
		<hr style="border: none" />



		<div class="container">

			<!------------------------------------------------   Intoduce Company    ------------------------------------- -->

			<c:set var='address' value="${company.possible_time}" />
			<c:set var='address_split' value="${fn:split(address,',')}" />

			<div class="thumbnail">
				<div class='row'>
					<div class="col-sm-3 col-lg-3 col-md-3 col-xs-1"></div>
					<img class="col-sm-6 col-lg-6 col-md-6 col-xs-10 img-rounded"
						src="http://landvibe.oursoccer.co.kr/company/image/${company.company_no}"
						alt="Cinque Terre" width="500" height='500'>
					<div class="col-sm-3 col-lg-3 col-md-3 col-xs-1"></div>
				</div>

				<hr />

				<div class='row'>
					<div class="col-sm-5 col-lg-5 col-md-5 col-xs-2"></div>
					<div class="col-sm-2 col-lg-2 col-md-2 col-xs-8 detail_company_name">${company.nick_name}</div>
					<div class="col-sm-5 col-lg-5 col-md-5 col-xs-2"></div>
				</div>

				<hr />

				<div class='row'>
					<div class="col-sm-3 col-lg-3 col-md-3 col-xs-12 detail_company_name">역술인 소개</div>
					<div class="col-sm-9 col-lg-9 col-md-9 col-xs-0"></div>
				</div>

				<div class='row'>
					<div class='detail_company_intro'>
						<hr style="border: none" />
						<div class="row">
							<div class="col-sm-1 col-lg-1 col-md-1 col-xs-0"></div>
							<div class="col-sm-2 col-lg-2 col-md-2 col-xs-6 detail_company_intor_subject">약력 :</div>
							<div class="col-sm-8 col-lg-8 col-md-8 col-xs-6 detail_company_intor_content">${company.experience}</div>
							<div class="col-sm-1 col-lg-1 col-md-1 col-xs-0"></div>
						</div>
						<hr style="border: none" />
						<div class="row">
							<div class="col-sm-1 col-lg-1 col-md-1 col-xs-0"></div>
							<div class="col-sm-2 col-lg-2 col-md-2 col-xs-6 detail_company_intor_subject">인사말 :</div>
							<div class="col-sm-8 col-lg-8 col-md-8 col-xs-6 detail_company_intor_content">${company.greeting}</div>
							<div class="col-sm-1 col-lg-1 col-md-1 col-xs-0"></div>
						</div>
						<hr style="border: none" />
						<div class="row">
							<div class="col-sm-1 col-lg-1 col-md-1 col-xs-0"></div>
							<div class="col-sm-2 col-lg-2 col-md-2 col-xs-6 detail_company_intor_subject">역술인 소개 :</div>
							<div class="col-sm-8 col-lg-8 col-md-8 col-xs-6 detail_company_intor_content">${company.introduce}</div>
							<div class="col-sm-1 col-lg-1 col-md-1 col-xs-0"></div>
						</div>
						<hr style="border: none" />
						<div class="row">
							<div class="col-sm-1 col-lg-1 col-md-1 col-xs-0"></div>
							<div class="col-sm-2 col-lg-2 col-md-2 col-xs-6 detail_company_intor_subject">영업시간 :</div>
							<div class="col-sm-8 col-lg-8 col-md-8 col-xs-6">
								<div class="detail_company_intor_content">${address_split[0]}</div>
								<div class="detail_company_intor_content">${address_split[1]}</div>
							</div>
							<hr style="border: none" />
							<div class="col-sm-1 col-lg-1 col-md-1 col-xs-0"></div>
						</div>
						<hr style="border: none" />
						<div class="row">
							<div class="col-sm-1 col-lg-1 col-md-1 col-xs-0"></div>
							<div class="col-sm-2 col-lg-2 col-md-2 col-xs-6 detail_company_intor_subject">역술인 주소 :</div>
							<div class="col-sm-8 col-lg-8 col-md-8 col-xs-6 detail_company_intor_content">${company.address}</div>
							<div class="col-sm-1 col-lg-1 col-md-1 col-xs-0"></div>
						</div>
						<hr style="border: none" />
					</div>
				</div>

			</div>

			<!------------------------------------------------   report    ------------------------------------- -->
			<div class='row'>
				<div class="col-sm-3 col-lg-3 col-md-3 col-xs-12 detail_company_name">후기 작성</div>
				<div class="col-sm-9 col-lg-9 col-md-9 col-xs-0"></div>
			</div>

			<div class="detail_report">
				<div class='row'>
					<hr style="border: 1px solid #f5f5f5" />

					<div class="col-sm-1 col-lg-1 col-md-1 col-xs-0"></div>
					<div class="col-sm-2 col-lg-2 col-md-2 col-xs-2 detail_company_intor_subject">총 평점 :</div>
					<div class="col-sm-5 col-lg-5 col-md-5 col-xs-6">
						<div class='row'>
							<div class="col-sm-8 col-lg-8 col-md-8 col-xs-8">
								<c:set var='score_average'
									value="${fn:split(company.score_average,'.')}" />
								<c:choose>
									<c:when test="${score_average[1] ne '5'}">
										<c:forEach begin="1" end="${score_average[0]}">
											<img src="<c:url value='/resources/images/full_star.png'/>"
												class="detail_total_star_size">
										</c:forEach>
										<c:forEach begin="${score_average[0]+1}" end="5">
											<img src="<c:url value='/resources/images/empty_star.png'/>"
												class="detail_total_star_size">
										</c:forEach>
									</c:when>
									<c:when test="${score_average[1] eq '5'}">
										<c:forEach begin="1" end="${score_average[0]}">
											<img src="<c:url value='/resources/images/full_star.png'/>"
												class="detail_total_star_size">
										</c:forEach>
										<img src="<c:url value='/resources/images/half_star.png'/>"
											class="detail_total_star_size">
										<c:forEach begin="${score_average[0]+2}" end="5">
											<img src="<c:url value='/resources/images/empty_star.png'/>"
												class="detail_total_star_size">
										</c:forEach>
									</c:when>
									<c:otherwise></c:otherwise>
								</c:choose>
							</div>
							<div class="col-sm-4 col-lg-4 col-md-4 col-xs-4">
								<div class="detail_label">${company.score_average}stars</div>
							</div>
						</div>
					</div>

					<div class="col-sm-3 col-lg-3 col-md-3 col-xs-4" style="font-size: 20px">
						<p class="pull-right">${company.report_count}reviews</p>
					</div>
					<div class="col-sm-1 col-lg-1 col-md-1 col-xs-0"></div>
				</div>

				<hr style="border: 1px solid #000000" />

				<c:set var="report_list" value="${company.companyToReport}" />
				<c:forEach items="${report_list}" var='list'>
					<div class="row">
						<div class="col-sm-3 col-lg-3 col-md-3 col-xs-3 detail_company_intor_subject">${list.user_nickname}</div>
						<div class="col-sm-4 col-lg-4 col-md-4 col-xs-4 detail_company_intor_content">${list.comment}</div>
						<div class="col-sm-3 col-lg-3 col-md-3 col-xs-3">


							<c:set var='score' value="${fn:split(list.score,'.')}" />
							<c:choose>
								<c:when test="${score[1] ne '5'}">
									<c:forEach begin="1" end="${score[0]}">
										<img src="<c:url value='/resources/images/full_star.png'/>"
											class="detail_total_star_size">
									</c:forEach>
									<c:forEach begin="${score[0]+1}" end="5">
										<img src="<c:url value='/resources/images/empty_star.png'/>"
											class="detail_total_star_size">
									</c:forEach>
								</c:when>
								<c:when test="${score[1] eq '5'}">
									<c:forEach begin="1" end="${score[0]}">
										<img src="<c:url value='/resources/images/full_star.png'/>"
											class="detail_total_star_size">
									</c:forEach>
									<img src="<c:url value='/resources/images/half_star.png'/>"
										class="detail_total_star_size">
									<c:forEach begin="${score[0]+2}" end="5">
										<img src="<c:url value='/resources/images/empty_star.png'/>"
											class="detail_total_star_size">
									</c:forEach>
								</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>

							<span class="label label-success">${list.score} stars</span>
						</div>

						<div class="col-sm-2 col-lg-2 col-md-2 col-xs-2">
							<span class="pull-right">${list.register_date}</span>
						</div>

					</div>
					<hr style="border: 1px solid #ffffff" />
				</c:forEach>

			</div>


		</div>

	</div>
	<form name="pay_button" class="text-left" method="post" action = "/pay/post" onsubmit="return loginCheck()" >
	<div class="col-sm-2 col-lg-2 col-md-2 col-xs-0">
	<input type ="hidden" name="nick_name" value = "${company.nick_name}">

				<button type="submit"
			class="btn btn-primary btn-lg btn-block"
			style="position: fixed; top: 500px; width: 15%;">상담하기</button>
			</div>
			
			</form>
	
</div>



<script type="text/javascript">
function loginCheck(){
var name = "${user.name}"
 if(name==""){
  alert("상담을 하기 위해서는 로그인이 필요합니다.");
  location.replace("/sign/in");
  return false;
 }

}
</script>
