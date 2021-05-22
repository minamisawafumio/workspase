<%@page import="jp.co.fm.businessLogic.system.SystemInfo"%>
<%@page import="jp.co.fm.businessLogic.common.Const"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">


<%
String sysUrl = (String) SystemInfo.getInstance().getValue(Const.URL);
String sysJson = (String) SystemInfo.getInstance().getValue(Const.SYS_JSON);
%>

<title>メニュー画面</title>
<link href="/skdb/resources/css/jquery.mobile-1.4.5.css" media="all" rel="stylesheet" />
<link href="/skdb/resources/css/system.css" media="all" rel="stylesheet" />
<link href="/skdb/resources/css/myGrid.css" media="all" rel="stylesheet" />

<!--  -->
<script src="/skdb/resources/js/jquery-1.9.1.min.js"></script>
<script src="/skdb/resources/js/jquery.mobile-1.4.5.min.js"></script>
<script src="/skdb/resources/js/system.js"></script>
<script src="/skdb/resources/fancybox-master/dist/jquery.fancybox.min.js" type="text/javascript"></script>
<script src="/skdb/resources/webjars/jquery-migrate/1.4.0/jquery-migrate.min.js" type="text/javascript"></script>
<script src="/skdb/resources/js/system.js?2020_10_14_001"></script>

<script>
function printMsg(msg){
	  //console.log("printMsg =" + msg);

	$("#common_pop_msg").html(msg);
	$("#common_pop_link").click();
}
</script>

<style>
  table#tbl_01 { width: 100%; background-color:pink; }
  table#tbl_02 { width: 100%; background-color:pink; }
  table#tbl_03 { width: 100%; background-color:pink; }
  table#tbl_04 { width: 100%; background-color:pink; }
  table#tbl_05 { width: 100%; background-color:pink; }

  #logoutDialog{
	max-width:400px;
  }
</style>

</head>

<body>

メニュー画面
<spring:url value="/FT001F02/form" var="url" htmlEscape="true" />
<form:form name="f" action="${url}" method="post" modelAttribute="FT001F02Form">

    <div id="i_0101">
		<input type="text" name="name_oya_332" id="id_oya_332" style="width:1300px;" />
	</div>
    <div id="i_0103">
		<p><span id="shogi_msg"></span></p>
	</div>
    <div id="i_0104">
		<p><span id="common_pop_msg999"></span></p>
	</div>
    <div>
		<a href="#logoutDialog" data-rel="popup" data-position-to="window" data-role="button"
		    data-inline="true" data-transition="pop">ログアウト</a>
	</div>

	<div>
		<input id="FT001F02_btn034" type="submit" name="goto034" value="ビデオ" tabindex='7' />
	</div>

	<div>
		<input id="FT001F02_btn033" type="submit" name="goto033" value="マンガ" tabindex='7' />
	</div>
	<div>
		<input id="FT001F02_btn001_11" type="button" name="test_001" value="テスト" onclick="button_test01()" />
	</div>
	<div>
		<input id="FT001F02_btn05_2" type="submit" name="goto07_2" value="箱2" tabindex='8' />
	</div>
    <div>
		<input id="FT001F02_btn01" type="submit" name="goto03" value="03画面へ" tabindex='1' />
	</div>
    <div>
		<input id="FT001F02_btn03_2" type="button" name="goto03_2" value="03_2画面へ" onclick="button_exe03_2()" />
	</div>
    <div>
		<input id="FT001F02_btn03" type="submit" name="goto05" value="05画面へ" tabindex='3' />
	</div>
	<div>
		<input id="FT001F02_btn08" type="submit" name="goto08" value="08画面へ" tabindex='3' />
	</div>
	<div>
		<input id="FT001F02_shogi" type="submit" name="gotoShogi" value="将棋画面へ" tabindex='4' />
	</div>
    <div>
		<input id="FT001F02_exe01" type="button" name="exe01" value="実行00000" onclick="button_exe01()" />
	</div>
	<div>
		<input type="text" name="name_exe01_txt" id="id_exe01_01" style="width:300px;" />
	</div>

    <div>
		<input id="FT001F02_exe02" type="button" name="exe02" value="実行exe"
		onclick="button_exe()" />
	</div>
	<div>
		<input type="text" name="name_exe02_txt" id="id_exe02_01" style="width:300px;" />
	</div>
    <div>
		<input id="FT001F02_exe03" type="button" name="exe03" value="実行exe2"
		onclick="button_exe()" />
	</div>
	<div>
		<input type="text" name="name_exe03_txt" id="id_exe03_01" style="width:300px;" />
	</div>
	<div>
		<input type="hidden" id="hidden_exe" name="hiddenName_exe" value="<%=sysJson%>" />
	</div>
    <div>
    	<input id="FT001F02_btn04" type="submit" name="goto06" value="06画面へ" tabindex='5' />
	</div>
	<div>
			<input id="button_01" type="button" name="button_001" value="ボタン001" onclick="button1_click()" />
	</div>
	<div>
			<input id="button_02" type="button" name="button_002" value="ボタン002222" onclick="button2_click()" />
	</div>
	<div>
			<input id="button_03" type="button" name="button_003" value="ボタン003" onclick="button3_click()" />
	</div>
	<div>
			<input id="button_05" type="button" name="button_005" value="ボタン005" onclick="button5_click()" />
	</div>


<input type="checkbox" name="example" value="選択肢1" onclick="checkboxClick('1')">選択肢1
<input type="checkbox" name="example" value="選択肢2" onclick="checkboxClick('2')">選択肢2
<input type="checkbox" name="example" value="選択肢3" onclick="checkboxClick('3')">選択肢3








	<form:select path="mapValues" multiple="false">
		<form:option value="-" label="--Please Select"/>
		<form:options items="${FT001F02Form.menuMap}" />
	</form:select>

	<input type="hidden" name="sample" value="test!!">

	<input id="FT001F02_kettei" type="submit" name="doExecute" value="決定" />
	<input id="FT001F02_btn08_1" type="submit" name="downLoadFile" value="ファイルダウンロード" />
	<input id="FT001F02_btn08_2" type="submit" name="downLoadsFile" value="ファイルダウンロード（複数）" />
	<input id="FT001F02_btn08_3" type="submit" name="downLoadZip" value="ＺＩＰファイルダウンロード" />
	<input id="FT001F02_btn09" type="submit" name="excelUpdate" value="エクセル更新" />
	<input id="FT001F02_btn10" type="submit" name="hash" value="文字列ハッシュ化" />
	<input id="FT001F02_btn18" type="submit" name="fileCopy" value="ファイルコピー" />
	<input id="FT001F02_btn19" type="submit" name="doZip" value="ZIP" />
	<input id="FT001F02_btn20" type="submit" name="doExp" value="例外発生" />

    <div>
		<button type="button" id="sampleButton" class="btn btn-primary btn-lg">
			モーダル・ダイアログ 呼び出し
		</button>
	</div>

	<input type="hidden" name="name_exe02_sysUrl"  id="id_exe02_sysUrl"  value="<%=sysUrl%>" />

</form:form>
    ${msg}

	<p><span id="common_pop_msg"></span></p>
	<div id="popup" data-role="popup">
		<a href="#common_pop" data-rel="popup" data-position-to="window" data-transition="pop" id="common_pop_link"></a>
	</div>
	<div data-role="popup" id="common_pop" data-overlay-theme="b" data-dismissible="false" data-histry="false"></div>

	<div data-role="popup" id="popupDialog" data-overlay-theme="a" data-theme="c" style="max-width:400px;" class="ui-corner-all">
		<div data-role="header" data-theme="a" class="ui-corner-top">
			<h1>メッセージ</h1>
		</div>
		<div data-role="content" data-theme="d" class="ui-corner-bottom ui-content">
			<h3 class="ui-title">どちらにしますか</h3>
			<p>あああああ</p>
			<a href="#" data-role="button" data-inline="true" data-rel="back" data-theme="c">はい</a>
			<a href="#" data-role="button" data-inline="true" data-rel="back" data-transition="flow" data-theme="b">いいえ</a>
		</div>
	</div>

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


//ウインドウが開いた時に実行------------------------------------------------------------------------------------------
$(document).ready( function(){
	let sysUrl = document.getElementById("id_exe02_sysUrl").value;

	localStorage.setItem("sysUrl", sysUrl);

	menuStart();
});

//ボタン１をクリックした時の処理-------------------------------------------------------------------------------------
function button1_click(){

	let popupJspUrl = localStorage.getItem("sysUrl") + "/resources/jsp/popup001.jsp";

	let option = "toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=400,height=300,left=312,top=234";

	let child_win= window.open(popupJspUrl,'popup', option);
}

//ボタン２をクリックした時の処理--------------------------------------------------------------------------------------
function button2_click(){

	let popupJspUrl = localStorage.getItem("sysUrl") + "/resources/jsp/popup002.jsp";
	let option = "toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=400,height=300,left=312,top=234";

	let child_win= window.open(popupJspUrl,'popup', option);
}

//ボタン３をクリックした時の処理--------------------------------------------------------------------------------------
function button3_click(){

	let popupJspUrl = localStorage.getItem("sysUrl") + "/resources/jsp/popup003.jsp";

	let option = "toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=400,height=300,left=312,top=234";

	let child_win= window.open(popupJspUrl,'popup', option);
}

//メニュー画面開始時に実行-------------------------------------------------------------------------------------------
function menuStart(){

	let json = {
		className	: "FT001F02",
		methodName	: "menuStart",
	}

	executeJson_new(json);
}


//--------------------------------------------------------------------------------------------------------------------
function retMenuStart(resData, textStatus, jqXHR){
	//console.log('----retMenuStart---------');

	let jsonMap = JSON.parse(resData.jsonStringMap);

	let serverStartTime	 = jsonMap.serverStartTime;
	//console.log('serverStartTime=' + serverStartTime);

	let userId = jsonMap.userId;
	//console.log('userId=' + userId);
}

//--------------------------------------------------------------------------------------------------------------------
function button_test01(){

	let array1 = new Array();
	array1.push('1777777aがああ22222');
	array1.push('133333ｇｇｇｇ3333');
	array1.push('144444いいいい＆eeee,,,4444');

	let array3 = new Array();
	array3.push('3777777aがああ22222');
	array3.push('333333ｇｇｇｇ3333');
	array3.push('344444いいいい＆eeee,,,4444');

	let map = new Map();
	map.set('className' , 'FT001F02');
	map.set('methodName', 'button_test01');
	map.set('map2'		, array1);
	map.set('userId'	, 'aaaabbbb');
	map.set('map3'		, array3);
	map.set('pass'		, 'ppppppppp');

	let arraySt = makeArraySt(map);
	console.log('arraySt=' + arraySt);

	executeJson2(map);
}


//--------------------------------------------------------------------------------------------------------------------
function retButton_test01(resData, textStatus, jqXHR){
	//console.log('----retMenuStart---------');

	let jsonMap = JSON.parse(resData.jsonStringMap);

	let serverStartTime	 = jsonMap.serverStartTime;
	//console.log('serverStartTime=' + serverStartTime);

	let userId	 = jsonMap.userId;
	//console.log('userId=' + userId);

	let map_2 = jsonMap.map2;

	//console.log('map_2=' + map_2);

	let data000 = jsonMap.methodName;
	//console.log('data000=' + data000);

	let map2333St = jsonMap.map2333;

	//console.log('map2333=' + map2333St);

	let jsonMap2 = JSON.parse(map2333St);

	//console.log('key02=' + jsonMap2.key02);
	//console.log('key03=' + jsonMap2.key03 * 11);
}
//--------------------------------------------------------------------------------------------------------------------
function mapToArray(iMap){
	let rtnList = new Array();

	iMap.forEach(function(value, key){
		rtnList.push(key);

		if(value.constructor === Map){
			let ary = mapToArray(value);
			rtnList.push(ary);
		}else{
			rtnList.push(value);
		}
	});

	return rtnList;
}

//チェックボックスをクリックした時の処理------------------------------------------------------------------------------
function checkboxClick(inKey){

	let json = {
		className	: "FT001F02",
		methodName	: "checkboxClick",
		inKey		: inKey,
	}

	executeJson_new(json);
}

//--------------------------------------------------------------------------------------------------------------------
function retCheckboxClick(resData, textStatus, jqXHR){
	console.log('----retCheckboxClick---------');

	let jsonMap = JSON.parse(resData.jsonStringMap);
}

//ボタン005をクリックした時の処理-------------------------------------------
function button5_click(){

	let popupJspUrl = localStorage.getItem("sysUrl") + "/resources/jsp/popup005.jsp";

	let option = "toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0,width=400,height=300,left=312,top=234";

	let child_win = window.open(popupJspUrl,'popup', option);
}

//ボタン３をクリックした時の処理--------------------------------------------------------------------------------------
function button_exe01(){

	let exe01Data = document.getElementById("id_exe01_01").value;

	let json = {
		className	: "FT001F02",
		methodName	: "exe01",
		start		: "100",
		end			: "200",
		exe01Text	: exe01Data,
		age			: "30",
		tel			: "090-0123-4567",
	}

	executeJson_new(json);
}

//--------------------------------------------------------------------------------------------------------------------
function button_exe(){

	let exeData = document.getElementById("id_exe02_01").value;

	let jsonSt = document.getElementById("hidden_exe").value;

	let dateTime = new Date().getTime();

	let json = {
		className	: "FT001F02",
		methodName	: "abcde",
		start		: "100",
		end			: "200",
		name		: "taro",
		age			: "30",
		tel			: "090-0123-4567",
	}

	executeJson_new(json);
}


//--------------------------------------------------------------------------------------------------------------------
function button_exe_001(){
	console.log('----------minamisawa-----');
	let exeData = document.getElementById("id_exe02_01").value;

	let jsonSt = document.getElementById("hidden_exe").value;

	let dateTime = new Date().getTime();

	let json = {
		className	: "FT001F02",
		methodName	: "abcde",
		start		: "100",
		end			: "200",
		name		: "taro",
		age			: "30",
		tel			: "090-0123-4567",
	}

	executeJson_new(map);
}


function retAbcde(resData, textStatus, jqXHR){
	console.log('----------retAbcde-----');
	alert('----------retAbcde-----');
}

//--------------------------------------------------------------------------------------------------------------------
function button_logout(inMsg){

	if(inMsg=='yes'){
		let json = {
			className	: "FT001F02",
			methodName	: "logout",
			start		: "100",
			end			: "200",
			name		: "taro",
			age			: "30",
			tel			: "090-0123-4567",
		}

		executeJson_new(json);
	}
}

//ログアウト（executeJsonの戻り(done)の次に実行される）----------------------------------------------------------------
function retLogout(resData, textStatus, jqXHR){
	let jsonMap = JSON.parse(resData.jsonStringMap);
	let comDateTime = jsonMap.comDateTime;
	let nextUrl	= jsonMap.nextUrl;
	window.location.href = nextUrl;
}

//01処理（executeJsonの戻り(done)の次に実行される）-------------------------------------------------------------------
function retExe01(resData, textStatus, jqXHR){

	let jsonMap = JSON.parse(resData.jsonStringMap);
	let imgString = jsonMap.imgString;
	console.log('done resData=' + resData);

	let comDateTime	= jsonMap.comDateTime;

	$(in_1.document).find('#id_oya_332').val('A=' + comDateTime);

	alert('retExe01');

}

//ボタン３をクリックした時の処理---------------------------------------------------------------------------------------
function button_exe03_2(){

	let json = {
		className: "FT001F02",
		methodName: "exe03_2",
	}

	executeJson_new(json);
}

//03_2処理（executeJsonの戻り(done)の次に実行される）----------------------------------------------------------------
function retExe03_2(resData, textStatus, jqXHR){

	let inKey = resData.inKey;
	//console.log('done inKey=' + inKey);

	//配列の内容をコンソールに出力---------
	let retArray = resData.array;

	for(let i = 0; i < retArray.length; i++) {
		  //console.log(retArray[i])
	}

	alert('retExe03_2');
}

//002処理（executeJsonの戻り(done)の次に実行される）----------------------------------------------------------------
function retSyori002(resData, textStatus, jqXHR){
	let imgString = resData.imgString;

	let comDateTime	= resData.comDateTime;
	let dataList	= resData.list;
	let pdfFileName = resData.pdfFileName;

	for (let i = 0; i < dataList.length; i++ ) {
		let info = dataList[i];
		 //console.log("1 start=" + info.start);
		 //console.log("2 end="   + info.end);
	}

	$("#common_pop_msg999").html(comDateTime);

	$(in_1.document).find('#id_332').val('これを入れる' + comDateTime);
	$(in_1.document).find('#id_333').val('pdfFileName=' + pdfFileName);

	//ポップアップウィンドウの関数を実行する
	in_1.oya_to_ko_click777(imgString);
}

//001処理（executeJsonの戻り(done)の次に実行される）----------------------------------------------------------------
function retSyori001(resData, textStatus, jqXHR){
	let imgString	= resData.imgString;
	let comDateTime	= resData.comDateTime;
	let dataList	= resData.list;

	for (let i = 0; i < dataList.length; i++ ) {
		let info = dataList[i];
		 //console.log("1 start=" + info.start);
		 //console.log("2 end="   + info.end);
	}

	$(in_1.document).find('#id_777').val(imgString);

	$("#common_pop_msg999").html(comDateTime);
}

//executeJsonの戻りより呼ばれる--------------------------------------------------------------------------------------
//function done(resData, textStatus, jqXHR){
//	let methodName  = resData.methodName;
//   eval(methodName + "(resData, textStatus, jqXHR)");
//}

//-------------------------------------------------------------------------------------------------------------------
function addzero(text){
    text = String(text);
    if(text.length==1){
        text = "0"+text;
    }
    return text;
}

//-------------------------------------------------------------------------------------------------------------------
function EncodeHTMLForm(inData) {
	let rtnSt = '';
	for (let name in inData) {
		let value = inData[name];
		let param = encodeURIComponent(name) + '=' + encodeURIComponent(value);
		rtnSt = rtnSt + param + '&';
	}
	rtnSt = rtnSt.substr(0, rtnSt.length - 1);
	rtnSt = rtnSt.replace(/%20/g,'+');
	return 	rtnSt;
}
</script>
</body>
</html>