<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1 user-scalable=no">
<link href="/smarthome/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<title>Welcome</title>

<style type="text/css">
body {
    margin: 0;
    height: 100%;
    width: 100%;
    position: relative;
}

#mapContainer {
    height: 100%;
    width: 100%;
    padding-right: 15px;
    padding-left: 15px;
    margin-right: 15px;
    margin-left: 15px;
}
</style>

</head>
<body>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="header">
                    <ul class="nav navbar-nav pull-right">
                        <li class="active"><a href="#"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                        <li><a href="<spring:url value="/download" />"><span class="glyphicon glyphicon-download"></span> Download</a></li>
                        <li><a href="#">Contact</a></li>
                    </ul>
                    <h3 class="text-muted">Lenovo</h3>
                </div>
                <div class="jumbotron">
                    <h1>Welcome to Lenovo SmartHome</h1>
                    <p class="lead">Intelligent platform</p>
                    <p>
                        <a href="<spring:url value="/users" />" class="btn btn-primary"> 
                            <span class="glyphicon-info-sign glyphicon" /></span> View Users
                        </a>
                    </p>
                </div>
            </div>
        </div>
    </div>

    <div id="mapContainer" class=""></div>
	<script type="text/javascript"
		src="http://webapi.amap.com/maps?v=1.3&key=13d52242f585e518fa67626c3bf539e2">
		
	</script>
	<script type="text/javascript">
        var map = new AMap.Map("mapContainer", {
            resizeEnable : true,
            //二维地图显示视口
            //设定地图中心点
            //设置地图显示的缩放级别
            view : new AMap.View2D({
                center : new AMap.LngLat(116.397428, 39.90923),
                zoom : 13
            })
        });
        //地图类型切换
        map.plugin([ "AMap.MapType" ], function() {
            var type = new AMap.MapType({
                defaultType : 0
            });//初始状态使用2D地图
            map.addControl(type);
        });
    </script>
</body>
</html>
