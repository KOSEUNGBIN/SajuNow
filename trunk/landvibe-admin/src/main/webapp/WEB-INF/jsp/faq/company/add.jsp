<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<head>
<!-- ckeditor -->
<script src="//cdn.ckeditor.com/4.5.8/full/ckeditor.js"></script>
</head>

<div style="width: 100%; height: 22px; border: none;"></div>

<div class="container-fluid"
	style="background-color: #2196F3; color: #fff; height: 200px;">
	<h1>역술인 FAQ 등록</h1>
	<p>
		<strong>등록버튼을 누르시면 역술인 FAQ 어플상의 FAQ에 등록이 됩니다.</strong>
	</p>
</div>

<div style="width: 100%; height: 100px; border: none;"></div>

<div class='row'>
	<div class='col-md-2'></div>
	<div class='col-md-8'>
		<form id="register-form" class="text-left" action="/faq/company/submit"
			method="post">
			<div class='row form-group'>
				<div class='col-md-1'
					style="display: inline-block; text-align: center;">
					<label for="title"><h4>제목:</h4></label>
				</div>
				<div class='col-md-11'>
					<input type="text" class="form-control" name="title">
				</div>
			</div>
			<div class='row'>
				<div class='col-md-12'>
					<textarea class="form-control" name="content"></textarea>
				</div>
			</div>

			<hr style="border: none" />
			<hr style="border: none" />

			<div class='row'>
				<div class='col-md-7'></div>
				<div class='col-md-2'
					style="display: inline-block; text-align: right;">
					<a type="button" class="btn btn-primary btn-lg" href='/faq/company'>목록보기</a>
				</div>
				<div class='col-md-2'
					style="display: inline-block; text-align: right;">
					<button type="submit" class="btn btn-primary btn-lg">등록하기</button>
				</div>
				<div class='col-md-1'></div>
			</div>

			<div style="width: 100%; height: 50px; border: none;"></div>
		</form>
	</div>
	<div class='col-md-2'></div>
</div>



<script type="text/javascript">
	CKEDITOR.replace('content');
</script>