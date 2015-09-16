<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script src="http://code.jquery.com/jquery.js"></script>
<script src="/smarthome/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="http://webapi.amap.com/maps?v=1.3&key=13d52242f585e518fa67626c3bf539e2">
<!-- Include Required Prerequisites -->
<script type="text/javascript"
	src="//cdn.bootcss.com/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="/smarthome/datepicker/moment.min.js"></script>

<!-- Include Date Range Picker -->
<script type="text/javascript"
	src="/smarthome/datepicker/daterangepicker.js"></script>
<link rel="stylesheet" type="text/css"
	href="/smarthome/datepicker/daterangepicker.css" />

<title>All Locations</title>

<style type="text/css">
#mapContainer {
	height: 100%;
	width: 100%;
	min-height: 650px;
	padding-right: 15px;
	padding-left: 15px;
	margin-right: auto;
	margin-left: auto;
}

.paddingTop {
	padding-top: 5px;
}
</style>


</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<h1>User ID </h1> 
				<h2 id="userid">${userid}</h2>
			</div>
		</div>
	</section>
	<section>
		<div class="container">

			<div class="tabbable">
				<!-- Only required for left/right tabs -->
				<ul class="nav nav-tabs">
					<li class="active"><a href="#tab1" data-toggle="tab">View Map</a></li>
					<li><a href="#tab2" data-toggle="tab">View Table</a></li>
				</ul>
			</div>

			<div class="row paddingTop">
				<div class="col-xs-0 col-md-6">&nbsp;</div>

				<div id="reportrange" class="pull-right col-xs-12 col-md-4"
					style="background: #fff; cursor: pointer; padding: 5px 10px; border: 2px solid #ccc">
					<i class="glyphicon glyphicon-calendar fa fa-calendar"></i>&nbsp; <span></span>
					<b class="caret"></b>
				</div>

				<div class="col-xs-12 col-md-2">
					<button type="button" class="btn btn-info pull-right disabled">
						<span class="label label-info glyphicon glyphicon-time"></span>
						Select Time
					</button>
				</div>
			</div>
		</div>

		<div class="container paddingTop" id="tablediv">
			<table id="table" class="table table-striped">
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
				</tbody>
			</table>
		</div>
		
		<div class="container container-fluid paddingTop" id="mapdiv">
			<div class="row-fluid">
				<div class="span12">
					<div id="mapContainer" class=""></div>
					<script src="/smarthome/js/DrawMapAndTable.js"></script>
				</div>
			</div>
		</div>
	</section>
	
</body>
</html>