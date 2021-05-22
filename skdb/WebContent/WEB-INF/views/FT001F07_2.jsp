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

<script src="/skdb/resources/FlickSwipe/js/jquery.min.js"></script>
	<script src="/skdb/resources/js/system.js?2020_02_17_0001"></script>
	<title>ttttteeeee</title>

</head>


<body>
	FT001F07_2AAA333
	<spring:url value="/FT001F07_2/form" var="url" htmlEscape="true" />
	<form:form action="${url}" method="post" modelAttribute="FT001F07_2Form">

	<div class="container777">

	 	<div class="item box-big771">
	 	     0000
			<input type="range" id="id_slider_001" value="0" min="0" max="100" step="1" onchange="range000(this.value)">
			<p id="id_slider_002">0</p>
		</div>





		<div id="i_3_2">
			<input id="inp_1112" type="range" min="1" max="20" value="1" >
		</div>
		<div id="i_3_1">
			<input id="inp_1111" type="text"  value="" readonly >
		</div>



	 	<div id="i_2_2">
	 	     0001AAA
			<input id="inp_001" type="range" min="1" max="20" value="1" >
		</div>
		<div class="item777">
			0002
			<input id=inp_002 type="tel">
		</div>
		<div class="item777">
			0003 search
			<input id="inp_003" type="search">
		</div>
		<div class="item777">
			0004 url
			<input id="inp_004" type="url">
		</div>
		<div class="item777">
			0006 button
			<input id="inp_005" type="button" value="OK">
		</div>
		<div class="item777 box-big772">
	 	     006 range
			<input id="id_006" type="range" min="1" max="50" value="1" onchange="range006(this.value)">
		</div>

		<div class="item777 box-big772">
	 	     0007 range AAA
			<input id="inp_range_020" type="range" min="1" max="50" value="1" oninput="showVal(this.value)" onmousemove="range007(this.value)">
		</div>


		<div class="item777 box-big772">
	 	     0007_2
			<select id="select_001" name="select">
			    <option value="値1">選択肢１</option>
			    <option value="値2">選択肢２</option>
			    <option value="値3">選択肢３</option>
			</select>
		</div>


		<div class="item777 box-big773">
			0008 text
			<input id="inp_007" type="text"  >
		</div>
		<div class="item777">
			0009 mail
			<input id="inp_008" type="email" >
		</div>
		<div class="item777">
			0010 password
			<input id="inp_009" type="password" >
		</div>
		<div class="item777">
			0011 date
			<input id="inp_010" type="date" >
		</div>

		<div id="out_010"></div>

		<div class="item777 box-big774">
			0012
			<input id="inp_011" type="image" >
		</div>
	</div>




	<div class="containerAAA">
		<div class="itemAAA">
			<input id="FT001F07_btn001_1" type="button" name="test_001" value="01" onclick="ft001f07_btn001_1('1_1')" />
		</div>
		<div class="itemAAA">
			<input id="FT001F07_btn001_2" type="button" name="test_001" value="02" onclick="ft001f07_btn001_1('1_2')" />
		</div>
		<div class="itemAAA">
			<input id="FT001F07_btn001_3" type="button" name="test_001" value="03" onclick="ft001f07_btn001_1('1_3')" />
		</div>



		<div class="itemAAA">04</div>
		<div class="itemAAA">05</div>
		<div class="itemAAA">06</div>
		<div class="itemAAA">
			<input id="FT001F07_btn001_4" type="button" name="test_001" value="07" onclick="ft001f07_btn001_1('1_7')" />
		</div>
		<div class="itemAAA">
			<input id="FT001F07_btn001_5" type="button" name="test_001" value="08" onclick="ft001f07_btn001_1('1_8')" />
		</div>
		<div class="itemAAA">09</div>
		<div class="itemAAA">10</div>
		<div class="itemAAA">11</div>
		<div class="itemAAA">12</div>
	</div>
	<div class="containerBBB">
		<div class="itemBBB"><input id="inp_005" type="button" value="01"></div>
		<div class="itemBBB"><input id="inp_005" type="button" value="02"></div>
		<div class="itemBBB"><input id="inp_005" type="button" value="03"></div>
		<div class="itemBBB">04</div>
		<div class="itemBBB"><input id="inp_005" type="button" value="05"></div>
		<div class="itemBBB"><input id="inp_005" type="button" value="06"></div>
		<div class="itemBBB">07</div>
		<div class="itemBBB">08</div>
		<div class="itemBBB">09</div>
		<div class="itemBBB">10</div>
		<div class="itemBBB">11</div>
		<div class="itemBBB">12</div>
	</div>
    <div>
		<form:input id="FT001F07_btn01" type="submit" path="back" name="back" value="戻る" />
	</div>
</form:form>

<script>

//メニュー画面開始時に実行-------------------------------------------------------------------------------------------
//function ft001f07_btn001_1(iData){
//
//	let map = new Map();
//	map.set('className' , 'FT001F07');
//	map.set('methodName', 'ft001f07_btn001_1');
//	map.set('iData', iData);
//
//	executeJson2(map);
//}

//--------------------------------------------------------------------------------------------------------------------
function retFt001f07_btn001_1(resData, textStatus, jqXHR){
	//console.log('----retFt001f07_btn001_1---------');

	let jsonMap = JSON.parse(resData.jsonStringMap);

	let serverStartTime	 = jsonMap.serverStartTime;
	//console.log('serverStartTime=' + serverStartTime);

	let userId = jsonMap.userId;
	//console.log('userId=' + userId);
}


	function myfunc001() {
		alert('myfunc001');
		let id006Value = document.getElementById("id_006").value;
		//console.log('id006Value = ' + id006Value);
	}


	function range000(inData){
		//console.log('showVal = ' + inData);
	}

	function range001(inData){
		//console.log('showVal = ' + inData);
	}

	function range006(inData){
		//console.log('showVal = ' + inData);
	}

	function range007(inData){
		//console.log('range007 = ' + inData);

     	let map = new Map();
		map.set('className' , 'FT001F07');
     	map.set('methodName', 'FT001F07_range007');

    	executeJson2(map);
	}


    function outputSelectedValueAndText(obj){
        /*
         * obj は select タグであり、selectedIndex プロパティには
         * 変更後の選択項目のインデックスが格納されています
         */
        var idx = obj.selectedIndex;
        var value = obj.options[idx].value; // 値
        var text  = obj.options[idx].text;  // 表示テキスト

        // 値とテキストをコンソールに出力
        console.log('value = ' + value + ', ' + 'text = ' + text);

        /*
         * 出力例）選択肢２に変更された場合
         * ----------------------------------------------
         * value = 値2, text = 選択肢２
         */

     	//-------------------------------------
     	let map = new Map();
		map.set('className' , 'FT001F07');
     	map.set('methodName', 'FT001F07_2_outputSelectedValueAndText');
     	map.set('value', value);
     	map.set('text', text);

    	executeJson2(map);
    }

	//---------------------------------------------------------------------------------------------------------------------
	function retFT001F07_2_outputSelectedValueAndText(resData, textStatus, jqXHR){
		let jsonMapDataInfo = JSON.parse(resData.jsonStringMap);

		let comDateTime = jsonMapDataInfo.comDateTime;

		console.log(comDateTime);
	}

	//---------------------------------------------------------------------------------------------------------------------
	function retFT001F07_range007(resData, textStatus, jqXHR){
		let jsonMapDataInfo = JSON.parse(resData.jsonStringMap);

		let comDateTime = jsonMapDataInfo.comDateTime;

		console.log(comDateTime);
	}



	//スライダーの値が変更された後に発動-----------------------------------------------------------------------------------
//	$('#i_2_2').mousemove( function () {
// 		let no = document.getElementById("inp_1112").value + 1;
// 		document.getElementById("inp_1111").value = no;
// 	});

	$('#i_2_2').change( function () {
 		let no = document.getElementById("inp_1112").value + 1;
 		document.getElementById("inp_1111").value = no;
 	});



	//---------------------------------------------------------------------------------------------------------------------
    $(function(){






    	//選択枝------------------------------------
        $("#select_001").change(function(){
        	let str = "";
            str = $(this).val();

        	let map = new Map();
        	map.set('className' , 'FT001F07');
        	map.set('methodName', 'ft001f07_btn001_1');
        	map.set('iData', str);
        	executeJson2(map);
        });

		//------------------------------------
        $("#inp_010").change(function(){
        	let str = "";
            str = $(this).val();
            $("#out_010").html(str);

         	let map = new Map();
    		map.set('className' , 'FT001F07');
         	map.set('methodName', "FT001F07_2_date");
         	map.set('date', str);

        	executeJson2(map);
        });







    });

	//---------------------------------------------------------------------------------------------------------------------
	function retFT001F07_2_date(resData, textStatus, jqXHR){
		let jsonMapDataInfo = JSON.parse(resData.jsonStringMap);

		let comDateTime = jsonMapDataInfo.comDateTime;

		console.log(comDateTime);
	}



</script>
</body>
</html>