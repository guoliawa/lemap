<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link href="/smarthome/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<script type="text/javascript"
	src="http://webapi.amap.com/maps?v=1.3&key=13d52242f585e518fa67626c3bf539e2">	
</script>
<script src="http://code.jquery.com/jquery.js"></script>
<script src="/smarthome/bootstrap/js/bootstrap.min.js"></script>

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
</style>

<title>Map</title>
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
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div id="mapContainer" class=""></div>

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
						
						function avg(arr) {
							var result;
							if (arr.length == 0) {
							    return 0;
							}
						    var i = 0;
						    var sum = 0;
						    while(arr[i]) {
						    	sum += arr[i];
						    	i ++;
						    }
						    
						    result = sum/arr.length;
						    return result;
						}
						
						$(document).ready(function() {
						    var longArr = new Array();
						    var laArr = new Array();
							var positions = '${positions}';
							$.each(JSON.parse(positions), function(index, position) {
								var longitude = position.longitude;
								var latitude = position.latitude;
								var date = new Date(position.timestamp);
								longArr.push(longitude);
								laArr.push(latitude);
							    var marker = new AMap.Marker({
		                            //复杂图标
		                            icon : new AMap.Icon({
		                                //图标大小
		                                size : new AMap.Size(28, 37),
		                                //大图地址
		                                image : "http://webapi.amap.com/images/marker_sprite.png",
		                                imageOffset : new AMap.Pixel(0, 0)
		                            }),
		                            //在地图上添加点
		                            position : new AMap.LngLat(longitude, latitude)
		                        }); 
							    marker.setMap(map);							    
							});
							
							map.panTo(new AMap.LngLat(avg(longArr),avg(laArr)));
						});
					</script>
				</div>
			</div>
		</div>
	</section>
</body>
</html>