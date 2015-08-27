<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css"
	rel="stylesheet">
<title>All Locations</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>User ID ${userid}</h1>
				<p>
					<a href="<spring:url value="/usermap?id=${userid}" />" class="btn btn-primary">
						<span class="glyphicon-map-marker glyphicon" /></span> View Map
					</a>
				</p>
			</div>
		</div>
	</section>

	<section>
		<div class="container">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>userid</th>
						<th>time</th>
						<th>ssid</th>
						<th>latitude</th>
						<th>longitude</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${locations}" var="location">

						<tr>
							<td>${location.userid}</td>
							<td><fmt:setTimeZone value="GMT-0" /> <fmt:formatDate
									pattern="yyyy-MM-dd HH:mm:ss SSSS"
									value="${location.timestamp}" /></td>
							<td>${location.connectedwifi.ssid}</td>
							<td>${location.latitude}</td>
							<td>${location.longitude}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>
</body>
</html>