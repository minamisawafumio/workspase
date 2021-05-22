<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">

<title>ERROR</title>

<link href="/fm/resources/css/system.css" media="all" rel="stylesheet" />

</head>
<body>
エラー
<spring:url value="/ERROR001/form" var="url" htmlEscape="true" />
<form:form name="f" action="${url}" method="post" modelAttribute="ERROR001Form">

	<table class="sample">
		<tr>
			<td>
				<input id="ERROR001_btn01" type="submit" name="gotoLogin" value="ログイン画面へ" tabindex='1' />
			</td>
		</tr>
	</table>

</form:form>

</body>
</html>