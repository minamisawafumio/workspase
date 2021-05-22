<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="jp.co.fm.businessLogic.system.SystemService"%>
<%@page import="jp.co.fm.businessLogic.system.SystemInfo"%>
<%@page import="jp.co.fm.businessLogic.common.Const"%>
<%@page import="jp.co.fm.presentation.form.FT00ShogiForm"%>


<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<title>Ajaxテストデータ表示画面aaaaaaaaaaaaaaaaaaaaa</title>

<link href="/skdb/resources/css/jquery.mobile-1.4.5.css" media="all" rel="stylesheet" />
<script src="/skdb/resources/js/jquery-3.4.1.min.js"></script>
<script src="/skdb/resources/js/jquery.mobile-1.4.5.min.js"></script>
<link href="/skdb/resources/css/system.css" media="all" rel="stylesheet" />
<script src="/skdb/resources/js/system.js"></script>


<html>
<head>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">


<%!public void jspInit() {
		System.out.println("-----------jspInit-----------------");
	}%>

<%
String sysUrl = (String) SystemInfo.getInstance().getValue(Const.URL);

System.out.println("-------------------------sysUrl" + sysUrl);


FT00ShogiForm fT00ShogiForm = (FT00ShogiForm) session.getAttribute("FT00ShogiForm");
%>
<script>
alert("<%=sysUrl%>");
</Script>

<%

%>
</head>

<body>

FT00Shogi

	<spring:url value="/FT00Shogi/form" var="url" htmlEscape="true" />
	<form name="f" action="${url}" method="post" >
	    <div>
			<input id="FT00Shogi_btn01" type="submit" name="back" value="戻る" />
		</div>
	    <div>
			<input id="button_s66" type="button" name="button_66" value="66" onclick="ft00shogi_button_s_click66()" />
		</div>
	    <div>
			<input id="button_s22" type="button" name="button_22" value="将棋" onclick="ft00shogi_button_s_click22(this.form, '<%=sysUrl%>')" />
		</div>
	    <div>
			<input id="button_01" type="button" name="button_001" value="ボタン001" onclick="ft00shogi_button1_click(this.form, '<%=sysUrl%>')" />
		</div>

	    <div>
			<p><span id="shogi_msg"></span></p>
		</div>

	    <div>
			<input id="FT00Shogi_shogiBtn01" type="submit" name="shogiBtn01" value="将棋リセット" />
		</div>
	    <div>
			<input id="FT00Shogi_shogiBtn02" type="submit" name="shogiBtn02" value="将棋02" />
		</div>
	    <div>
			<input id="FT00Shogi_shogiBtn03" type="submit" name="shogiBtn02_2" value="将棋02_2" />
	    </div>
	    <div>
			<input id="FT00Shogi_shogiBtn04" type="submit" name="shogiBtn03" value="将棋03" />
		</div>
	    <div>
			<input id="FT00Shogi_shogiBtn05" type="submit" name="shogiBtn04" value="将棋04" />
		</div>
	    <div>
			<output name="result1" onforminput="value=manzoku.value"><%=fT00ShogiForm.getShogiBan()%></output>
		</div>
	    <div>
			<output name="result2" onforminput="value=manzoku.value"><%=fT00ShogiForm.getShogiBan2()%></output>
		</div>
	    <div>
			<A HREF="" onclick="ft00shogi_button1_click(this.form, '<%=sysUrl%>')">  テスト</A>
		</div>
	    <div>
			<p><span id="shogi_mojiretu"></span></p>
		</div>
	    <div>
			<p><span id="shogi_mojiretu2"></span></p>
		</div>
	    <div>
			<p><span id="shogi_mojiretu3"></span></p>
		</div>
	</form>
<script>

window.addEventListener('DOMContentLoaded', function() {
	alert('**addEventListener**');
})

function ft00shogi_button_s_click66(){

	alert('6666666666666666666666666666666666666');


}

//将棋ボタンをクリックした時の処理
function ft00shogi_button_s_click22(inForm, sysUrl){

	alert('6666666666666666666666666666666666666');


}

//将棋ボタンをクリックした時の処理
function ft00shogi_button1_click(inForm, sysUrl){

	let map = new Map();
	map.set('className'  , 'FT00Shogi');
	map.set('methodName' , "shogi1");
	map.set('start'		 , '100');
	map.set('end'		 , '200');
	map.set('age'		 , '30');
	map.set('tel'		 , '090-0123-4567');

	executeJson2(map);
}

function retShogi1(resData, textStatus, jqXHR){
	//console.log('----------retShogi1-----');
}


function ft00shogi_button1_click2(inForm, sysUrl, inSt){

	let map = new Map();
	map.set('className'  , 'FT00Shogi');
	map.set('methodName' , "shogi2");
	map.set('start'		 , '100');
	map.set('end'		 , '200');
	map.set('age'		 , '30');
	map.set('tel'		 , '090-0123-4567');

	executeJson2(map);
}

function retShogi2(resData, textStatus, jqXHR){
	//console.log('----------retShogi2-----');
}



function ft00shogi_button1_click3(inForm, sysUrl, inSt){

	let json = {
		"methodName": "shogi3",
		"start": "100",
		"end": "200",
		"name": "taro",
		"age": "30",
		"inSt": inSt,
		"tel": "090-0123-4567"
	}

	let jsonSt = JSON.stringify(json);

	let encodedData = window.btoa(jsonSt);

	let sendJsonData = "sendJsonData=" + encodedData;

	let executeJspUrl = sysUrl + '/resources/jsp/controller.jsp';

	$.ajax({
		url: executeJspUrl,
		type: 'POST',
		data: sendJsonData
	}).done(function(resData, textStatus, jqXHR) {
		let comDateTime		= resData.comDateTime;
		let shogiMojiretu	= resData.shogiMojiretu;
		let shogiMojiretu2	= resData.shogiMojiretu2;
		let shogiMojiretu3	= resData.shogiMojiretu3;
		$("#shogi_msg").html(comDateTime);
		$("#shogi_mojiretu").html(shogiMojiretu);
		$("#shogi_mojiretu2").html(shogiMojiretu2);
		$("#shogi_mojiretu3").html(shogiMojiretu3);
	}).fail(function(jqXHR, textStatus, errorThrown) {

	}).always(function() {

	});
	return true;
}


function bodyOnLoad(){


	alert('**bodyOnLoad**');

	ft00shogi_button1_click(this.form, 'http://<%=SystemInfo.getInstance().getValue(Const.IP)%>:18080/skdb/');
}


</script>

</body>
</html>