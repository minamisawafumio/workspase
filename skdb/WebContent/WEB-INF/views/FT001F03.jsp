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


<script type="text/javascript">


    $( function() {
        $('#FT001F03_btn02111').click(
            function() {
            	if(! confirm('処理を続けますか？')){
            		return false;
            	}
            }
        );

    // Ajax通信テスト ボタンクリック
    $("#FT001F03_btn02").click(function() {
       	if(! confirm('処理を続けますか？？？')){
        		return false;
       	}
    });

    // Ajax通信テスト ボタンクリック
    $("#FT001F03_btn03").click(function() {



    	let fields = new Object();
		fields["abcd"] = "ああああああああ";




		$.ajax({
            type        : "POST",
            url         : "testDataA",
            data : fields,
            contentType : "application/x-www-form-urlencoded; charset=utf-8",
            success : function(msg){

            },
            error : function(msg){

            }
        });
    });

	$('#button_01').click(function() {
		$.ajax({
			type : 'POST',
			url : 'http://localhost:18080/skdb',
			dataType : 'html',
			data : {
				id : 'minamisawa',
				buttonName : 'bbnnmm',
					data : '<p>This is a test of the system that shows me an  !</p>'
				},
			success : function(XMLHttpRequest, textStatus, errorThrown) {

			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				//console.log('An Ajax error was thrown.');
				//console.log(XMLHttpRequest);
				//console.log(textStatus);
				//console.log(errorThrown);
			}
		});
	});
});

</script>



</head>
<body>
031
    <spring:url value="/FT001F03/form" var="url" htmlEscape="true" />
	<form:form action="${url}" method="post" modelAttribute="FT001F03Form">


		    <div>
				ID：<form:input path="id" />

				名前:<form:input path="name"/>

				<form:checkbox path="sampleCheckArray" name="xxx1" value="xxx" style="break-all"/>xxx &nbsp;
				<form:checkbox path="sampleCheckArray" name="yyy1" value="yyy"/>yyy &nbsp;
				<form:checkbox path="sampleCheckArray" name="zzz1" value="zzz"/>zzz

				<form:input id="FT001F03_btn01" type="submit" path="back" name="back" value="戻る" />
				<form:input id="FT001F03_btn02" type="submit" path="doExecute"  name="doExecute" value="決定" />
				<form:input id="FT001F03_btn03" type="submit" path="testData"  name="testData" value="データ取得" />

				<form:select path="selectedIsbn" multiple="true">
				<form:options items="${books}" itemLabel="title" itemValue="isbn" />
				</form:select>

				<!--
				       <form:input id="FT001F03_btn03" type="submit" path="testData"  name="testData" value="データ取得" />
				 -->

			</div>


	</form:form>
	${msg}
</body>
</html>