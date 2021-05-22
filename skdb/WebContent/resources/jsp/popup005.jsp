<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.io.PrintWriter" %>
<%@page import="java.util.*" %>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="javax.servlet.http.HttpSession"%>

<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-cache">

	<script src="/skdb/resources/FlickSwipe/js/jquery.min.js"></script>
	<script src="/skdb/resources/js/system.js?2020_10_14_001"></script>

</head>

<body>

<script>
//ウインドウが開いた時に実行----------------------------------------------------------------------------
$(document).ready( function(){
	syori005();
});

//ボタン005をクリックした時の処理-------------------------------------------
function syori005(){
	let dateTime = new Date().getTime();

	let pdfFileName = "pdf_" + dateTime + ".pdf";

	let map = new Map();
	map.set('className'  , 'FT001F02');
	map.set('methodName' , "syori005");
	map.set('start'		 , '100');
	map.set('end'		 , '200');
	map.set('pdfFileName', pdfFileName);
	map.set('age'		 , '30');
	map.set('tel'		 , '090-0123-4567');

	executeJson2(map);
}

</script>

<form name="f">
	<table class="sample">
		<tr>
			<td>
				PDF画像
			</td>
		</tr>
		<tr>
			<td>
				<p id="test001"></p>
			</td>
		</tr>
	</table>
</form>

<script>

//-------------------------------------------
function retSyori005(resData, textStatus, jqXHR){

	let imgString = resData.imgString;

	var elem = document.getElementById("test001");
	elem.innerHTML = '<object data="data:application/pdf;base64,' + imgString + '" type="application/pdf"></object>';

}


</script>

</body>
</html>
