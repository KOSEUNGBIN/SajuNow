<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Bootstrap Core CSS -->
<head>
<link href="<c:url value="/resources/css/link.css" />" rel="stylesheet">
<style>

</style>
</head>

	<div style="width: 100%; height: 22px; border: none; "></div>


<div class="container-fluid">
  <div class="row content" >
    <div class="col-sm-2 sidenav" id ="" align="center">
     <img alt="" width="200px" height="40px" src="http://oursoccer.co.kr/study/reserve/img/company_list.png">
      <ul class="nav nav-pills nav-stacked">
        <li><a href="/main/all/0"  class="list-group-item"> 전체보기</a></li>
        <li><a href="/main/4/0" class="list-group-item">관상, 손금</a></li>
        <li><a href="/main/6/0" class="list-group-item">해몽</a></li>
        <li><a href="/main/7/0" class="list-group-item">궁합</a></li>
          <li><a href="/main/8/0" class="list-group-item">작명</a></li>
      </ul><br>
      <div class="input-group">
        <input type="text" class="form-control" placeholder="Search Blog..">
        <span class="input-group-btn">
          <button class="btn btn-default" type="button">
            <span class="glyphicon glyphicon-search"></span>
          </button>
        </span>
      </div>
    </div>


<!-- Page Content -->
<div class="container">

	<div class="col-md-20">
		<div class="row carousel-holder">

				<div class="col-md-12">
					<div id="carousel-example-generic" class="carousel slide"
						data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#carousel-example-generic" data-slide-to="0"
								class="active"></li>
							<li data-target="#carousel-example-generic" data-slide-to="1"></li>
							<li data-target="#carousel-example-generic" data-slide-to="2"></li>
						</ol>
						<div class="carousel-inner">
							<div class="item active">
								<img class="slide-image"
									src="http://oursoccer.co.kr/study/reserve/img/ad_1.jpg"
									alt="Cinque Terre">
							</div>
							<div class="item">
								<img class="slide-image"
									src="http://oursoccer.co.kr/study/reserve/img/ad_2.jpg"
									alt="">
							</div>
							<div class="item">
								<img class="slide-image"
									src="http://oursoccer.co.kr/study/reserve/img/ad_3.jpg"
									alt="">
							</div>
						</div>
						<a class="left carousel-control" href="#carousel-example-generic"
							data-slide="prev"> <span
							class="glyphicon glyphicon-chevron-left"></span>
						</a> <a class="right carousel-control"
							href="#carousel-example-generic" data-slide="next"> <span
							class="glyphicon glyphicon-chevron-right"></span>
						</a>
					</div>
				</div>

			</div></div>



	<!------------------------------------------------   Category    ------------------------------------- -->







		<!------------------------------------------------   Advertisement    ------------------------------------- -->

		<div class="col-md-20">

			

			<!------------------------------------------------   Company list    ------------------------------------- -->


			<div style="width: 500px; height: 500; border: none;"></div>


			<div class="row">
				<c:forEach items="${companys}" var="company" varStatus="status">
					<%-- <c:if test="${(status.index) > 4}">
					<div style="width: 500px; height: 50px; border: none;"></div>
					</c:if> --%>
					<div class="col-sm-4 col-lg-4 col-md-4">
						<div class="company-list">
							<div class="thumbnail">

								<a href="/profile/${company.company_no}"> <img
									src="http://landvibe.oursoccer.co.kr/company/image/${company.company_no}"
									class="img-circle" alt="Cinque Terre" width="320" height="500">
								</a>

								<div class="caption">
									<h4 class="pull-right">${company.category_name}</h4>
									<h4>
										<a href="/profile/${company.company_no}">
											${company.nick_name} </a>
									</h4>
									<p style="min-height: 100px;">${company.greeting}</p>
								</div>
								<div class="ratings">
									<p class="pull-right">${company.report_count} reviews</p>
									<p>
										<c:forEach begin="1" end="${company.score_average}">
											<span class="glyphicon glyphicon-star"></span>
										</c:forEach>
										<c:forEach begin="${company.score_average + 1}" end="5">
											<span class="glyphicon glyphicon-star-empty"></span>
										</c:forEach>
										 ${company.score_average} stars
									</p>
								</div>
							</div>
						</div>
					</div>

				</c:forEach>

			</div>


		</div>

	</div>



</div>
<!-- /.container -->

<div class="container">

	<hr>
	
	<script>
$(document).ready(function () {
    $('.nav li a').click(function(e) {

        $('.nav li').removeClass('active');

        var $parent = $(this).parent();
        if (!$parent.hasClass('active')) {
            $parent.addClass('active');
        }
    });
});
</script>
