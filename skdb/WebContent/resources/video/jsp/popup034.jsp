<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="jp.co.fm.businessLogic.system.SystemInfo"%>
<%@page import="jp.co.fm.businessLogic.common.Const"%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">

<link href="/skdb/resources/css/jquery.mobile-1.4.5.css" media="all" rel="stylesheet" />
<link href="/skdb/resources/css/system.css?2020_01_27_2108" media="all" rel="stylesheet" />
<link rel="stylesheet" href="/skdb/resources/FlickSwipe/css/base.css" media="all" />
<link rel="stylesheet" href="/skdb/resources/FlickSwipe/css/common.css" media="all" />

<script src="/skdb/resources/FlickSwipe/js/jquery.min.js"></script>
<script src="/skdb/resources/js/fitty.min.js"></script>
<script src="/skdb/resources/js/system.js?2020_02_16_001"></script>

<link href="/skdb/resources/manga/css/myGamen.css?2020_02_11_001"  media="all" rel="stylesheet" />
<link href="/skdb/resources/manga/css/mySlider.css?2020_02_11_001" media="all" rel="stylesheet" />

</head>
<body>
	<div id="i_table9" style="background-color:white;">
		<h3 id="ui-title">サーバーに接続できません<br><br><br>
			<a href="#" data-rel="back" class="ui-btn ui-corner-all ui-btn-inline ui-mini m16" onclick="doWindowClose()">OK</a>
		</h3>
	</div>


	 <div id="i_v_001">
		<p id="test001"></p>
	</div>

<script>

//ウインドウが開いた時に発動-------------------------------------------------------------------------------------
$(document).ready( function(){
	watchVideo();
});

//マウスの右クリック時に発動-------------------------------------------------------------------------------------------
$(function(){
    $(document).on('contextmenu',function(e){
    	//処理を抜ける
        return false;
    });
});

//画面リサイズ時に発動-------------------------------------------------------------------------------------------------
$(window).resize(function () {
	//画像ファイルをリサイズする
    resize();
});

//画像ファイルをリサイズする-------------------------------------------------------------------------------------------
function resize(){

	let windowHeight = Number($(window).height());
	let windowWidth  = Number($(window).width());

	//リサイズ-------------------
	$('#v_01').css("width",  windowWidth  + "px");
	$('#v_01').css("height", windowHeight + "px");
}

//サーバと接続ができない場合 ------------------------------------------------------------------------------------------
function doFail(jqXHR, textStatus, errorThrown){
	document.getElementById("i_table9").hidden = false; //「サーバーに接続できません」画面を表示する
}

//---------------------------------------------------------------------------------------------------------------------
function watchVideo(){

	document.getElementById("i_table9").hidden = true;

	let map = new Map();
	map.set('className'    , "FT001F02");
	map.set('methodName'   , "watchVideo");

	executeJson2(map);
}

//-----------------------------------------------------------------------------------------------------
function makeVideoDom(iVideoInfo){
	let setMoji = '';
	setMoji = setMoji + "<video id='v_01' width height autoplay controls  poster='img/poster.png'>";
	setMoji = setMoji + "<source src='http://<%=SystemInfo.getInstance().getValue(Const.IP) %>:18080/skdb/resources/video/data/";
	setMoji = setMoji + iVideoInfo;
	setMoji = setMoji + "'>";
	setMoji = setMoji + "</video>";
	return setMoji
}

//リスナー作成(ウインドウが開いた時に１度だけ呼ばれる)-----------------------------------------------------------------
function retWatchVideo(resData, textStatus, jqXHR){
	let jsonMapDataInfo = JSON.parse(resData.jsonStringMap);

	let pathFile = 	get('pathFile'); //jsonMapDataInfo.pathFile;

	let elem = document.getElementById("test001");
	elem.innerHTML = makeVideoDom(pathFile);

	resize();
}

//ウインドウを閉じられた場合に発動-------------------------------------------------------------------------------------
function doWindowClose(){
	window.open('','_self').close();
	sessionStorage.clear();
}

</script>
</body>
</html>
