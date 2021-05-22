<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.io.File"%>
<%@page import="jp.co.fm.businessLogic.system.SystemInfo"%>

<%!//共通JSP
	public static Map<String, Object> commonMap;

	public void jspInit() {

		System.out.println("common.jsp jspInit");

		SystemInfo.getInstance().putValue("1111", "A11111");

		File file = new File(getServletContext().getRealPath("/WEB-INF/counter.dat"));

		commonMap = new HashMap<String, Object>();
	}%>

<html>
<head>

</head>
<body>

</body>
</html>