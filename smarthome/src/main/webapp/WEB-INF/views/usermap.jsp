<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
	min-height: 1080px;
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
						
						var marker =  new AMap.Marker({   
				            //复杂图标
				            icon: new AMap.Icon({    
				                    //图标大小
				                    size:new AMap.Size(28,37),
				                    //大图地址
				                    image:"http://webapi.amap.com/images/1.png", 
				                    imageOffset:new AMap.Pixel(0,0)
				                }),
				            //在地图上添加点
				            position:new AMap.LngLat(116.298876, 40.054416 )
				        });
				        marker.setMap(map);							
					</script>
				</div>
			</div>
		</div>
	</section>
</body>
</html>