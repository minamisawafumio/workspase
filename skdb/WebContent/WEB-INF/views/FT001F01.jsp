<%@page import="jp.co.fm.businessLogic.service.FT001F01"%>
<%@page import="jp.co.fm.presentation.form.FT001F01Form"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">

<title>ログイン画面222</title>

<link href="/skdb/resources/css/jquery.mobile-1.4.5.css" media="all" rel="stylesheet"/>
<script src="/skdb/resources/js/jquery-1.9.1.min.js"></script>
<script src="/skdb/resources/js/jquery.mobile-1.4.5.min.js"></script>
<link href="/skdb/resources/css/system.css" media="all" rel="stylesheet" />
<link href="/skdb/resources/css/myGrid.css" media="all" rel="stylesheet" />
<script src="/skdb/resources/js/system.js?2020_10_14_001"></script>

</head>
<body>
ログイン画面

<spring:url value="/FT001F01/form" var="url" htmlEscape="true" />
<form:form name="form_ft002f01" action="${url}" method="post" modelAttribute="FT001F01Form">
	<%
	FT001F01Form fT001F01Form = (FT001F01Form) session.getAttribute("fT001F01Form");
	%>
    <div>
    <!--
       ID：<form:input path="id" value="<%=fT001F01Form.getId()%>"/>
	-->
		ID：<form:input path="id" value="a"/>
    </div>
    <div>
    <!--
       パスワード:<form:input path="password" type="password" maxlength='8' value="<%=fT001F01Form.getPassword()%>"/>
	-->
		パスワード:<form:input path="password" type="password" maxlength='8' value="a"/>
    </div>

    <div>
		<%=fT001F01Form.getMessage()%>
    </div>
	<div>
		<input id="FT001F01_btn1" type="submit" name="login" value="ログイン" />
    </div>
    <div>
		<input id="FT001F01_btn2" type="submit" name="userAdd" value="ユーザ登録" />
    </div>
    <div>
		テストNo：<form:input path="testNo" value="<%=fT001F01Form.getTestNo()%>"/>
    </div>
    <div>
		<input id="FT001F01_testBtn" type="submit" name="test" value="実行" />
    </div>
</form:form>
</body>
</html>