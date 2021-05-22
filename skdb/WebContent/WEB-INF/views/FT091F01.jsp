<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">


<title>Ajaxテストデータ表示画面</title>

<link href="/skdb/resources/css/jquery.mobile-1.4.5.css" media="all" rel="stylesheet" />
<script src="/skdb/resources/js/jquery-3.4.1.min.js"></script>
<script src="/skdb/resources/js/jquery.mobile-1.4.5.min.js"></script>
<link href="/skdb/resources/css/system.css" media="all" rel="stylesheet" />
<script src="/skdb/resources/js/system.js"></script>

</head>
<body>
ユーザ登録
FT091F01 ______
<spring:url value="/FT091F01/form" var="url" htmlEscape="true" />
<form:form action="${url}" method="post" modelAttribute="FT091F01Form">

   	<div>
		ID：<form:input  id="submit_button"  path="id" maxlength="12"/>
	</div>
   	<div>
		パスワード:<form:input path="password" type="password" maxlength='8' />
	</div>
   	<div>
		名前:<form:input path="name" maxlength="20"/>
	</div>
   	<div>
		<form:input path="msg" size="100" disabled="true" />
	</div>
   	<div>
		<input id="FT091F01_btn01" type="submit" name="back" value="戻る" />
	</div>
   	<div>
		<input id="FT091F01_btn02" type="submit" name="h001" value="決定" />
	</div>

${msg}
</form:form>
</body>
</html>