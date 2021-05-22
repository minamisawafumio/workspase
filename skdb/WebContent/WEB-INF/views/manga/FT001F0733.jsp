<%@page import="jp.co.fm.businessLogic.system.SystemInfo"%>
<%@page import="jp.co.fm.businessLogic.common.Const"%>
<%@page import="jp.co.fm.businessLogic.system.SystemService"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">

<%
String sysJson = (String) SystemInfo.getInstance().getValue(Const.SYS_JSON);

List<String> tittleList = (List<String>) session.getAttribute("tittleList");
%>

<title>CCCBBBBBBAjaxテストデータ表示画面 マンガ3ボタン</title>

<script src="/skdb/resources/js/jquery-3.4.1.min.js"></script>
<script src="/skdb/resources/js/jquery.mobile-1.6.2.min.js"></script>
<link href="/skdb/resources/css/jquery.mobile-1.4.5.css" media="all" rel="stylesheet" />
<link href="/skdb/resources/css/system.css?2020_02_11_2116" media="all" rel="stylesheet" />
<script src="/skdb/resources/js/system.js?2020_02_23_001"></script>

	<style>
	  #logoutDialog{
		max-width:400px;
	  }
	</style>
</head>
<body>

FT001F07_456
<spring:url value="/FT001F0733/form" var="url" htmlEscape="true" />



<form:form action="${url}" method="post" modelAttribute="FT001F0733Form">

    <div>
		<a href="#logoutDialog" data-rel="popup" data-position-to="window" data-role="button"
		    data-inline="true" data-transition="pop">ログアウト</a>
	</div>




    <div id="i_0701_1">
		検索キー:<input type='text' name='name_searchKey' id='id_searchKey' value='' style='width:1300px;' />
	</div>
    <div id="i_0701_2">
		<input type='text' name='name_kensuuKey' id='i_ijyouKensuuKey' value='' />件以上
	</div>
	<div id="i_0701_3">
		検索結果（ <input type='text' name='name_kensuuResult' id='i_kensuuResult' disabled /> ）件
	</div>

	<div>
		<input id="FT001F0733_back" type="button" name="back01" value="検索" onclick="doSearch()" />
	</div>
    <div>
		<form:input id="FT001F0733_btn01" type="submit" path="back" name="back" value="戻る" />
	</div>
	<div class="container">
		<p id="test00222" ></p>
	</div>

	<div id="iLoading">
		<img id="iLoadingImg" src="">
	</div>


</form:form>
	${msg}



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

let child_win;

$(document).bind('mobileinit', function(){
    $.mobile.pageLoadErrorMessage = '表示できませんでした';
});

//ウインドウが開いた時に実行 ------------------------------------------------------------------------------------------
$(document).ready( function(){
	let maxPage = get('maxPage');
	//console.log(maxPage);
});

//----------------------------------------------------------------------------------------------------------------------
function retSyori_f0733(resData, textStatus, jqXHR){
	//ローディング画像を消す
	$('#iLoading').children('#iLoadingImg').attr('src', '');//ローディング画像のリンクを空にする
	$("#iLoadingImg").css("height"	, "0px");				//ローディング画像の高さを0にする

	let jsonMapDataInfo = JSON.parse(resData.jsonStringMap);

	let sessionId	= jsonMapDataInfo.sessionId;
	let count		= jsonMapDataInfo.count;

	//検索結果件数を画面に表示する
	document.getElementById("i_kensuuResult").value = count;

	//------------------------------------------------------
	let mapInfo = JSON.parse(jsonMapDataInfo.jsonString);

	//console.log('mapInfo.methodName='  + mapInfo.methodName);
	//console.log('mapInfo.array000='    + mapInfo.array000);
	//console.log('mapInfo.array000[0]=' + mapInfo.array000[0]);
	//console.log('mapInfo.array000[1]=' + mapInfo.array000[1]);
	//console.log('mapInfo.array000[2]=' + mapInfo.array000[2]);

	//------------------------------------------------------------
	let listInfo = JSON.parse(jsonMapDataInfo.jsonRtnList);

	let stData = "";

	for(let cnt = 0 ; cnt < listInfo.length; cnt++){
		stData = stData + '<div class="itemAA">';
		stData = stData + '<button class="ui-btn ui-input-btn ui-corner-all ui-shadow" type="button" onclick="doMangaPopup(' + listInfo[cnt].bookNoSt + ', ' + listInfo[cnt].mangaNo + ')">';
		stData = stData + listInfo[cnt].mangaTitle + ' (' + listInfo[cnt].mangaNo + 'P)';
		stData = stData + '</button>';
		stData = stData + '</div>';
	}

	// ページ読み込み時に実行したい処理----------------------------------
	let elem   = document.getElementById("test00222");
	elem.innerHTML = stData;
}

//検索をクリックした時の処理----------------------------------------------------------------------------------------
function doSearch(){

	//ローディング画像を読込む
	$('#iLoading').children('#iLoadingImg').attr('src', '/skdb/resources/jQuery-File-Upload-10.2.0/img/loading.gif');
	$("#iLoadingImg").css("height", "100%");	//ローディング画像の高さを100%にする

	//検索結果表示を初期化
	let elem = document.getElementById("test00222");
	elem.innerHTML = "";

	//検索キー
	let searchKey		= document.getElementById('id_searchKey').value;

	//件数キー（以上）
	let ijyouKensuuKey	= document.getElementById('i_ijyouKensuuKey').value;

	let map = new Map();
	map.set('className' 	, "FT001F0733");
	map.set('methodName'	, "syori_f0733");

	map.set('searchKey'		, searchKey);
	map.set('ijyouKensuuKey', ijyouKensuuKey);
	map.set('thisGamenId'	, "FT001F0733");


	executeJson2(map);
}

//-- ft001f07Btn33Befor -- doMangaPopup ---------------------------------------------------------------------------------------------------------------
function doMangaPopup(iBookNo, iMaxPage){


	sessionStorage.clear();
	set('bookNo'	, String(iBookNo));
	set('pageNo'	, "1");
	set('maxPage'	, String(iMaxPage));


	let popupJspUrl = localStorage.getItem('sysUrl') + "/resources/manga/jsp/popup033.jsp";
	let option = "toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0";

   if(child_win && !child_win.closed ) {
    	/* 子画面が開いている場合 */
    	child_win.open('about:blank','_self').close();		//子画面を閉じる
    }

	child_win = window.open(popupJspUrl,'popup', option);	//子画面を開く

	//子画面が閉じた時にchildfuncを動かす
	$(child_win).on("unload", childUnload);

	let json = {
		className	: "FT001F0733",
		methodName	: "doMangaPopup",
		bookNo		: iBookNo,
		maxPage		: iMaxPage,
	}

	executeJson_new(json);
}

function retDoMangaPopup(resData, textStatus, jqXHR){

	let jsonMapDataInfo = JSON.parse(resData.jsonStringMap);

	let openJudgment = jsonMapDataInfo.openJudgment;

	if(openJudgment == 'FALSE'){
		child_win.close();
		return;
	}

	let bookNo  = jsonMapDataInfo.bookNo;
    let maxPage = jsonMapDataInfo.maxPage;

    //console.log('retDoMangaPopup');
}




//子画面が閉じた時に発動-------------------------------------------------------------------------------------------
function childUnload(){

	let json = {
		className	: "FT001F0733",
		methodName	: "doWindowClose",
	}

	executeJson_new(json);
}

//-------------------------------------------------------------------------------------------------------------------
function retDoWindowClose(resData, textStatus, jqXHR){
	//console.log('#####retDoWindowClose##########');

	let map = new Map();
	map.set('className' 	, "FT001F0733");
	map.set('methodName'	, "isExistSession");

	executeJson2(map);
}

//-------------------------------------------------------------------------------------------------------------------
function retIsExistSession(resData, textStatus, jqXHR){
	//console.log('#####retIsExistSession##########');

	let jsonMapDataInfo = JSON.parse(resData.jsonStringMap);

	let wResult = jsonMapDataInfo.result;

	if(wResult == 'NotExist'){
		button_logout('yes');
		return;
	}
}

//--------------------------------------------------------------------------------------------------------------------
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

//サーバと接続ができない場合 ------------------------------------------------------------------------------------------
function doFail(jqXHR, textStatus, errorThrown){
	return;
}


</script>

</body>

</html>