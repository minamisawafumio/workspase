<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.io.PrintWriter" %>
<%@page import="java.util.*" %>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="jp.co.fm.businessLogic.common.DateUtil"%>
<%@page import="jp.co.fm.businessLogic.common.Const"%>
<%@page import="jp.co.fm.businessLogic.system.SystemService"%>


<!DOCTYPE html>
<html>
<head>



<%
	System.out.println("+++++++++ common.jsp 111+++++++++++++++++++++++++");

	//レスポンスヘッダを付ける（つけないとホスト名が変わる(localhose⇒直ID)とChromeでエラーになる）
	SystemService.getInstance().avoidCrossOrigin(request, response);

	Map<String, Object> rtnMap = new HashMap<String, Object>();

	String comDateTime = DateUtil.getInstance().getComDateTime(5);

	rtnMap.put("comDateTime", comDateTime);

	String jsonData = "";

	try{
		jsonData = new ObjectMapper().writeValueAsString(rtnMap);
	}catch(Exception e){

	}

	response.setContentType("application/json");
	PrintWriter pw = response.getWriter();
	pw.print(jsonData);
	pw.close();
%>

</head>

<body>



</body>
</html>
