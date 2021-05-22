<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %>

<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">

<script src="/skdb/resources/FlickSwipe/js/jquery.min.js"></script>
<script src="/skdb/resources/js/system.js?2020_02_28_002"></script>

</head>

<body>

<script>


//ウインドウが開いた時に実行----------------------------------------------------------------------------
$(document).ready( function(){
	syori003();
});


function syori003(){

	let map = new Map();
	map.set('className'  , "FT001F02");
	map.set('methodName' , "syori003");
	map.set('start'		 , '100');
	map.set('end'		 , '200');
	map.set('age'		 , '30');

	executeJson2(map);
}

//-------------------------------------------
function retSyori003(resData, textStatus, jqXHR){

	let imgString = resData.imgString;

	let comDateTime	= resData.comDateTime;
	let dataList	= resData.list;
	let pdfFileName = resData.pdfFileName;

	for (let i = 0; i < dataList.length; i++ ) {
		let info = dataList[i];
		 console.log("1 start=" + info.start);
		 console.log("2 end="   + info.end);
	}

	let elem = document.getElementById("test001");
	elem.innerHTML = '<img src="data:image/png;base64,' + imgString + '" />';
}

</script>





<form name="f">
	<table class="sample">
		<tr>
			<td>
				PDF画像 popup003
			</td>
		</tr>
		<tr>
			<td>
				<p id="test001"></p>
			</td>
		</tr>
	</table>
</form>


</body>
</html>
