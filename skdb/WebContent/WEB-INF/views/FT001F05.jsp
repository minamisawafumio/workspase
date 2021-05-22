<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">

<title>Ajaxテストデータ表示画面22</title>

<link href="/skdb/resources/css/jquery.mobile-1.4.5.css" media="all" rel="stylesheet" />
<script src="/skdb/resources/js/05jquery-3.4.1.min.js"></script>
<script src="/skdb/resources/js/jquery.mobile-1.4.5.min.js"></script>
<link href="/skdb/resources/css/system.css" media="all" rel="stylesheet" />
<script src="/skdb/resources/js/system.js?2020_10_14_001"></script>

<script type="text/javascript" >
function sendRequest() {

}
</script>

<script type="text/javascript" >
function abcde() {

}
</script>

<script type="text/javascript">

$(function() {
    // Ajax通信テスト ボタンクリック
    $("#FT001F05_btn06").click(function() {
    	let fields = new Object();
        fields["abcd"] = "ああああああああ";

        $.ajax({
            type        : "POST",
            url         : "http://localhost:18080/skdb/abcd",
            data : fields,
            contentType : "application/x-www-form-urlencoded; charset=utf-8",
            success : function(msg){

            },
            error : function(msg){

            }
        });
    });
});

<script type="text/javascript" >
//<![CDATA[
$(function(){
    $("#FT001F05_btn02").click(function(){

    });
});
//]]>
</script>

</head>
<body>
FT001F05_222
    <spring:url value="/FT001F05/form" var="url" htmlEscape="true" />
	<form:form action="${url}" method="post" modelAttribute="FT001F05Form">
	    <div>
			ID：<form:input path="id" />
		</div>
	    <div>
			名前:<form:input path="name"/>
		</div>
	    <div>
			<form:input id="FT001F05_btn01" type="submit" path="back" name="back" value="戻る" />
		</div>
	    <div>
			<form:input id="FT001F05_btn02" type="submit" path="doExecute" value="決定" />
		</div>
	    <div>
			<form:input id="FT001F05_btn03" type="submit" path="doExecuteAa" value="決定AA" onClick="sendRequest(this.form)" />
		</div>
	    <div>
			<form:input id="FT001F05_btn05" type="submit" path="doExecuteBb" value="決定BB"  />
		</div>

		<select id="FT001F05_btn06" >
		<option value=""></option>
		<option value="50067950">仮想マシンの作成</option>
		<option value="50076580">インストール CD の起動</option>
		<option value="50077769">HDD のフォーマット</option>
		<option value="50078623">システムファイルのインストール</option>
		<option value="50079003">Portage の環境設定</option>
		<option value="50080053">カーネルコンパイル</option>
		<option value="50089919">環境設定</option>
		<option value="50098197">デーモン、ブートローダーのインストール</option>
		<option value="50109765">ユーザーの追加</option>
		<option value="50123244">VMwareTools のインストール</option>
		</select>
	</form:form>
	${msg}
</body>
</html>