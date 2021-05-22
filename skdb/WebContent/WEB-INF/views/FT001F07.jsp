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


System.out.println("--------------0---------------------------------0----------");
%>

<title>AAAAAAjaxテストデータ表示画面000001</title>

<link href="/skdb/resources/css/jquery.mobile-1.4.5.css" media="all" rel="stylesheet" />
<script src="/skdb/resources/js/jquery-3.4.1.min.js"></script>
<script src="/skdb/resources/js/jquery.mobile-1.6.2.min.js"></script>
<link href="/skdb/resources/css/system.css" media="all" rel="stylesheet" />
<script src="/skdb/resources/js/system.js"></script>

</head>
<body>




FT001F07_123
<spring:url value="/FT001F07/form" var="url" htmlEscape="true" />
<form:form action="${url}" method="post" modelAttribute="FT001F07Form">
    <div>
		ID：<form:input path="id" />
	</div>
    <div>
		名前:<form:input path="name"/>
	</div>
    <div>
		<form:input id="FT001F07_btn01" type="submit" path="back" name="back" value="戻る" />
	</div>
	<div class="container">
		<p id="test00222" ></p>
	</div>

</form:form>
	${msg}

<script>

$(document).bind('mobileinit', function(){
    $.mobile.pageLoadErrorMessage = '表示できませんでした';
});


//ウインドウが開いた時に実行
$(document).ready( function(){
	// ページ読み込み時に実行したい処理
	let elem   = document.getElementById("test00222");

	let stData = "";
    <%
	for(Integer i = 0; i < tittleList.size(); i++){
        String  tittle = tittleList.get(i);
        String bookNoSt = "'" + i.toString() + "'";
    %>

	stData = stData + '<div class="itemAA">';
    stData = stData + '<button type="button" onclick="ft001f07_button2_click(' + <%=bookNoSt%> + ',1)">';
	stData = stData + '<%=tittle%>';
	stData = stData + '</button>';
	stData = stData + '</div>';

    <%
	}
    %>
    elem.innerHTML = stData;
});

let child_win;
//ボタン２をクリックした時の処理
function ft001f07_button2_click(inBookNo, inPageNo){
	sessionStorage.clear();
	sessionStorage.setItem('bookNo', inBookNo);
	sessionStorage.setItem('pageNo', inPageNo);
	let popupJspUrl = localStorage.getItem("sysUrl") + "/resources/jsp/popup002.jsp";
	let option = "toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=0";

    if( !child_win || child_win.closed ) {
        /* 子画面が開いていない場合 */
    	child_win = window.open(popupJspUrl,'popup', option);	//子画面を開く
    }else{
        /* 子画面が開いている場合 */
    	child_win.open('about:blank','_self').close();			//子画面を閉じる
	   	child_win = window.open(popupJspUrl,'popup', option);	//子画面を開く
    }
}

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