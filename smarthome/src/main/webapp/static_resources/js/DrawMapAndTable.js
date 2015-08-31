var userid = $("#userid").html();
var locations = new Array();
var markers = new Array();
var longitudeDefault = 116.397428, latitudeDefault = 39.90923;
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

/*
AMap.event.addListener(map,'complete',function(){
   alert("地图图块加载完毕！当前地图中心点为：");
});*/

function avg(arr) {
	var result;
	if (arr.length == 0) {
		return 0;
	}
	var i = 0;
	var sum = 0;
	while (arr[i]) {
		sum += arr[i];
		i++;
	}

	result = sum / arr.length;
	return result;
}


/* $(document).ready(function() {
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
         marker.setTitle("Please set title");
     });
     
     map.panTo(new AMap.LngLat(avg(longArr),avg(laArr)));
 }); */

function isPositionEffective(longitude, latitude) {
	if (longitude == 0 && latitude == 0) return false;
	return true;
}

function showMap(locations) {
	$('#mapdiv').show();
	$('#tablediv').hide();
	var longArr = new Array();
	var laArr = new Array();

	markers.forEach(function(marker) {
		marker&&marker.setMap(null);
	})
	
	markers = [];
	
	$.each(locations, function(index, location) {
		var longitude = location.longitude;
		var latitude = location.latitude;

		if (isPositionEffective(longitude, latitude)) {
			longArr.push(longitude);
			laArr.push(latitude);

			var marker = new AMap.Marker({
				// 复杂图标
				icon : new AMap.Icon({
					// 图标大小
					size : new AMap.Size(28, 37),
					// 大图地址
					image : "http://webapi.amap.com/images/marker_sprite.png",
					imageOffset : new AMap.Pixel(0, 0)
				}),
				// 在地图上添加点
				position : new AMap.LngLat(longitude, latitude)
			});
			marker.setMap(map);
			marker.setTitle(location.timestamp);
			markers.push(marker);
		}
	});
	var avg_long = avg(longArr);
	var avg_la = avg(laArr);

	if (avg_long == 0 && avg_la == 0) {
		map.panTo(new AMap.LngLat(longitudeDefault, latitudeDefault));
	} else {
		map.panTo(new AMap.LngLat(avg(longArr), avg(laArr)));
	}
}

function showTable(locations) {
	$('#tablediv').show();
	$('#mapdiv').hide();
	$("tbody").empty();
	var tr_index = 1;
	locations.forEach(function(location) {
		var tr_start = "<tr id=\"tr_" + tr_index + "\">";
		var tr_id = "#tr_" + tr_index;
		var td_userid = "<td>" + userid + "</td>";
		var td_time = "<td>" + location.timestamp + "</td>";
		if (location.connectedwifi == null) {
			location.connectedwifi = {
				"ssid" : "no ssid"
			};
		}
		var td_ssid = "<td>" + location.connectedwifi.ssid + "</td>";
		var td_latitude = "<td>" + location.latitude + "</td>";
		var td_longitude = "<td>" + location.longitude + "</td>";
		$("tbody").append(tr_start);
		$(tr_id).append(td_userid);
		$(tr_id).append(td_time);
		$(tr_id).append(td_ssid);
		$(tr_id).append(td_latitude);
		$(tr_id).append(td_longitude);
		$("tbody").append("</tr>");
		tr_index++;
	})
}

$(function() {

	function cb(start, end) {
		$('#reportrange span').html(
				start.format('MMMM D, YYYY') + ' - '
						+ end.format('MMMM D, YYYY'));
		var startString = start.format('YYYYMMDD');
		var endString = end.format('YYYYMMDD');

		$.ajax({
			type : "get",
			url : "/smarthome/locations/get/data",
			data : "id=" + userid + "&start=" + startString + "&end="
					+ endString,
			success : function(msg) {
				locations = msg;
				if ($('li.active a').html() == "View Map") {
					showMap(locations);
				} else {
					showTable(locations);
				}
			}
		}

		)

	}

	$('li').click(function() {
		if ($('li.active a').html() == "View Table") {
			showMap(locations);
		} else {
			showTable(locations);
		}
	});

	cb(moment(), moment());

	$('#reportrange')
			.daterangepicker(
					{
						ranges : {
							'Today' : [ moment(), moment() ],
							'Yesterday' : [ moment().subtract(1, 'days'),
									moment().subtract(1, 'days') ],
							'Last 7 Days' : [ moment().subtract(6, 'days'),
									moment() ],
							'Last 30 Days' : [ moment().subtract(29, 'days'),
									moment() ],
							'This Month' : [ moment().startOf('month'),
									moment().endOf('month') ],
							'Last Month' : [
									moment().subtract(1, 'month').startOf(
											'month'),
									moment().subtract(1, 'month')
											.endOf('month') ]
						}
					}, cb);

});
