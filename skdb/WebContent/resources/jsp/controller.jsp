<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.io.PrintWriter" %>
<%@page import="java.util.*" %>
<%@page import="jp.co.fm.businessLogic.system.SystemService"%>

<!DOCTYPE html>
<html>
<head>

<%
	//レスポンスヘッダを付ける（つけないとホスト名が変わる(localhose⇒直ID)とChromeでエラーになる）
	SystemService.getInstance().avoidCrossOrigin(request, response);

	String jsonData = SystemService.getInstance().syori(request);

	response.setContentType("application/json");

	PrintWriter pw = response.getWriter();

	pw.print(jsonData);

	pw.close();
%>

</head>

<body>

</body>
</html>
