<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="/smarthome/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<title>Users</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>All Users!</h1>
			</div>
		</div>
	</section>

	<section>
		<div class="container">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Name</th>
						<th>ID</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${users}" var="user">
						<tr>
							<td>${user.username}</td>
							<td>${user.userid}</td>
							<td><p>
									<a
										href=" <spring:url value= "/locations/user?id=${user.userid}"/>"
										class="btn btn-primary"> <span
										class="glyphicon-info-sign glyphicon" /></span> View
									</a>
								</p></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>
</body>
</html>