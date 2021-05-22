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
FT001F083
<spring:url value="/FT001F083/form" var="url" htmlEscape="true" />
<form:form action="${url}" method="post" modelAttribute="FT001F083Form">

    <div>
		ID：<form:input path="id" />
	</div>
    <div>
		名前:<form:input path="name"/>
	</div>
    <div>
		<form:input id="FT001F083_btn01" type="submit" path="back" name="back" value="戻る" />
	</div>
    <div>
		<form:input id="FT001F083_btn02" type="submit" path="doExecute"  name="doExecute" value="決定" />
	</div>

</form:form>
	${msg}
</body>
</html>