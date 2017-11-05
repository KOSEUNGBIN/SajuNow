<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<meta content="IE=edge" http-equiv="X-UA-Compatible" />
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0"
	name="viewport" />
<meta content="yes" name="mobile-web-app-capable" />
<meta content="noindex" name="robots" />


<title>jQuery UI Accordion - Collapse content</title>
<link rel="stylesheet" media="all" href="<c:url value="/resources/css/badge.css" />">
<link rel="stylesheet" media="all" href="<c:url value="/resources/css/notice.css" />" rel="stylesheet">
<script src="<c:url value='/resources/js/notice.js' />"></script>
</head>

<body>
	<div class="page-container">
		<main class="webview" id="accordion">
		<c:set var='faqList' value="${faqList}" />
		<c:forEach var='item' items="${faqList}">
		<article class="list-group" >
			<header class="list-header">
				<div class="left-state"></div>
				<div class="right-title">
					<h1 class="title">
						${item.title}
					</h1>
					
				</div>
			</header>
			<main class="list-content">
			${item.content}
			<c:set var="string1" value= "${item.register_date}"/>
			<c:set var="string2" value="${fn:substring(string1, 0, 10)}" />
			<span class="badge badge-info"> ${string2} </span>
			</main>
		</article>
			</c:forEach>
		</main>
		
	

		<!-- <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script> -->

		<script>
		(function() {
			$(function() {
				$('#accordion').accordion(
						{
							collapsible : true,
							header : "header.list-header",
							heightStyle : "content",
							animate : 100,
							active : false,
							beforeActivate : function(event, ui) {
								ui.newHeader.children('.left-state')
										.toggleClass('open');
							},
							activate : function(event, ui) {
								ui.oldHeader.children('.left-state')
										.toggleClass('open');
							}
						});
			});

		}).call(this);
		</script>
		
</body>
</html>