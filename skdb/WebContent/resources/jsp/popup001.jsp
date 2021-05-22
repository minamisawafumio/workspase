<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.io.PrintWriter" %>
<%@page import="java.util.*" %>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="jp.co.fm.businessLogic.system.SystemService"%>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">

<script src="/skdb/resources/FlickSwipe/js/jquery.min.js"></script>
<script src="/skdb/resources/js/system.js?2020_02_22_001"></script>

<%

%>

</head>

<body>

<script>


//ウインドウが開いた時に実行----------------------------------------------------------------------------
$(document).ready( function(){
	syori001();
});


//ボタン１をクリックした時の処理--------------------------
function syori001(){

	let map = new Map();
	map.set('className'  , 'FT001F02');
	map.set('methodName' , "syori001");
	map.set('start'		 , '100');
	map.set('end'		 , '200');
	map.set('name'		 , 'taro');
	map.set('age'		 , '30');
	map.set('tel'		 , '090-0123-4567');

	executeJson2(map);
}

</script>



<form name="f">
<%
	//レスポンスヘッダを付ける（つけないとホスト名が変わる(localhose⇒直ID)とChromeでエラーになる）
	SystemService.getInstance().avoidCrossOrigin(request, response);

	HttpSession httpSession = request.getSession();

	String bbb = (String) httpSession.getAttribute("img001");
%>
	<table class="sample">
		<tr>
			<td>
				<input type="text" name="name332" id="id_001" style="width:300px;" />
			</td>
		</tr>


		<tr>
			<td>
				<input type="text" name="name332" id="id_002" style="width:300px;" />
			</td>
		</tr>

		<tr>
			<td>
				<input type="text" name="name777" id="id_003" style="width:300px;" />
			</td>
		</tr>

		<tr>
			<td>
				<img id="id_004" />
			</td>
		</tr>


		<tr>
			<td>
					<input id="button_01" type="button" name="button_01" value="button" onclick="button1_click()" />
			</td>
		</tr>
	</table>

<script>
//-------------------------------------------
function retSyori001(resData, textStatus, jqXHR){
	//console.log('-------------retSyori001------------------');
}


  //ボタン１をクリックした時の処理
    function button1_click(){
    	let id001 = document.getElementById("id_001").value;
    	let id002 = document.getElementById("id_002").value;
    	let id003 = document.getElementById("id_003").value;

    	let all = id001 + id002 + id003;

    	doPopup001(id001, id002, id003);


    	let dateTime = new Date().getTime();

    	let dateTimeSt = String(dateTime);
    	window.opener.document.f.name_oya_332.value = '親へ:' + dateTimeSt + all;

	}

    function oya_to_ko_click(inPdfName){
    	document.write('<img src="../resources/pdf/' + inPdfName + '" width="104" height="91" />');
    }

    function oya_to_ko_click002(data){
    	document.write('<img src="' + data + '" />');
    }


	//ページデータ取得（スライダーの値が変更された場合に発動）----------------------------------------------------------
	function doPopup001(i1, i2, i3){

		let d1 = encodeURIComponent(i1);
		let d2 = encodeURIComponent(i2);
		let d3 = encodeURIComponent(i3);

    	let map = new Map();
    	map.set('className'  , 'FT001F02');
    	map.set('methodName' , 'doPopup001');
    	map.set('d1'		 , d1);
    	map.set('d2'		 , d2);
    	map.set('d3'		 , d3);

    	executeJson2(map);
    }

	//-----------------------------------------------------------------------------------------------------------------
	function retDoPopup001(resData, textStatus, jqXHR){

		let rtnSt = resData.rtnSt;

		//console.log("rtnSt=" + rtnSt);

	}

	//サーバと接続ができない場合 --------------------------------------------------------------------------------------
	function doFail(jqXHR, textStatus, errorThrown){
		return;
	}

</script>

</form>

</body>
</html>
