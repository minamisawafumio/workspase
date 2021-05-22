<%@page import="jp.co.fm.businessLogic.system.SystemInfo"%>
<%@page import="jp.co.fm.businessLogic.common.Const"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">

<title>FT001F06</title>

<link href="/skdb/resources/css/system.css"              media="all" rel="stylesheet" />
<link href="/skdb/resources/css/jquery.mobile-1.4.5.css" media="all" rel="stylesheet" />
<script src="/skdb/resources/js/jquery-3.4.1.min.js"></script>
<script src="/skdb/resources/js/jquery.mobile-1.4.5.min.js"></script>
<script src="/skdb/resources/js/system.js?2020_02_28_001"></script>



</head>
<%
String sysUrl = (String) SystemInfo.getInstance().getValue(Const.URL);
%>

<body>
    <a href="http://localhost:18080" id="myUrl"></a>
FT001F06画面
<spring:url value="/FT001F06/form" var="url" htmlEscape="true" />
<form:form name="f" action="${url}" method="post" modelAttribute="FT001F06Form">

	<input type="hidden" name="button" value="" />
    <div>
		<a href="#logoutDialog" data-rel="popup" data-position-to="window" data-role="button"
		    data-inline="true" data-transition="pop">ログアウト</a>
	</div>
	<div>
		<input type="button" id="btnA" value="ボタンA" onclick="myfunc1('<%=sysUrl%>');" />
	</div>
	<div>
		<form:input id="FT001F06_btn02" type="submit" path="doExecute" value="決定" />
	</div>
	<div>
		<form:input id="FT001F06_btn01" type="submit" path="back" name="back" value="戻る" />
	</div>

</form:form>
    ${msg}

	<p><span id="common_pop_msg"></span></p>
	<div id="popup" data-role="popup">
		<a href="#common_pop" data-rel="popup" data-position-to="window" data-transition="pop" id="common_pop_link"></a>
	</div>
	<div data-role="popup" id="common_pop" data-overlay-theme="b" data-dismissible="false" data-histry="false"></div>

	<div data-role="popup" id="logoutDialog" data-overlay-theme="a" data-theme="c" style="max-width:400px;" class="ui-corner-all">
		<div data-role="header" data-theme="a" class="ui-corner-top">
			<h1>メッセージ</h1>
		</div>
		<div class="ui-content" style="background-color:white;">
			<h3 class="ui-title">ログアウトしますか</h3>
			<a href="#" data-rel="back" class="ui-btn ui-corner-all ui-btn-inline ui-mini m16" onclick="button_logout('yes')">はい</a>
			<a href="#" data-rel="back" class="ui-btn ui-corner-all ui-btn-inline ui-mini m16" onclick="button_logout('no')">いいえ</a>
		</div>
	</div>

<script>

//------------------------------------------------------------------------------------
function button_logout(inMsg){
	if(inMsg=='yes'){
		let map = new Map();
		map.set('className'  , 'FT001F02');
		map.set('methodName' , 'logout');
		map.set('start'		 , '100');
		map.set('end'		 , '200');
		map.set('name'		 , 'taro');
		map.set('age'		 , '30');
		map.set('tel'		 , '090-0123-4567');

		executeJson2(map);
	}
}

//ログアウト（executeJsonの戻り(done)の次に実行される）----------------------------------------------------------------
function retLogout(resData, textStatus, jqXHR){
	let jsonMapDataInfo = JSON.parse(resData.jsonStringMap);

	let comDateTime	= jsonMapDataInfo.comDateTime;
	let nextUrl		= jsonMapDataInfo.nextUrl;

	//console.log('comDateTime=' + comDateTime);
	//console.log('nextUrl=' + nextUrl);

	window.location.href = nextUrl;
}

  //ボタン１をクリックした時の処理
function myfunc1(sysUrl) {

  let map = new Map();
	map.set('className'  , 'FT001F02');
	map.set('methodName' , 'syori006');
	map.set('start'		 , '100');
	map.set('end'		 , '200');
	map.set('name'		 , 'taro');
	map.set('age'		 , '30');
	map.set('tel'		 , '090-0123-4567');

	executeJson2(map);

}


function retSyori006(resData, textStatus, jqXHR){
	let jsonMapDataInfo = JSON.parse(resData.jsonStringMap);

	let dt = jsonMapDataInfo.comDateTime;

	console.log(dt);

}

</script>






</body>
</html>