<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
    content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
<title>All Locations</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>Your locations!</h1>
			</div>
		</div>
	</section>

	<section>
		<div class="container">
			<table class="table table-striped">
				<thead>
					<tr>
					    <th>用户</th>
						<th>时间</th>
						<th>地点</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${locations}" var="location">
						<tr>
						    <td>${location.username}</td>
							<td>${location.timestamp}</td>
							<td>${location.address}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>
</body>
</html>