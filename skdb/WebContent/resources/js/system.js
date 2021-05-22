
function sendRequest(form) {
    //var url = "http://localhost:18080/eeee/b001/bbb/abcde";
    var url = "http://localhost:1323/v1/add/fasfasdfasd";

    var params = "name=Sam";
    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    //Send the proper header information along with the request
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhr.send(params);
}

//メソッド実行-----------------------------------------------------------------------------------------------------
function done(resData, textStatus, jqXHR){
	let methodName  = resData.methodName;
	eval(methodName + "(resData, textStatus, jqXHR)");
}

//---------------------------------------------------------------------------------------------------------------
function zeroPadding(num, length){
    return ('0000000000' + num).slice(-length);
}

//-----------------------------------------------------------------------------------------------------
function executeJson2(inMap){


console.log("--------★★★★------ executeJson2 -22-------★★★★-----");

	let arraySt = makeArraySt(inMap);

	let b64St = utf8_to_b64(arraySt);

	let encodedData = window.btoa(b64St);

	let sendJsonData = "sendJsonData=" + encodedData;

	let executeJspUrl = localStorage.getItem("sysUrl") + '/resources/jsp/controller.jsp';

	$.ajax({
		url : executeJspUrl,
		type: 'POST',
		timeout:10000,
		data: sendJsonData
	}).done(function(resData, textStatus, jqXHR) {
		done(resData, textStatus, jqXHR);
	}).fail(function(jqXHR, textStatus, errorThrown) {
		doFail(jqXHR, textStatus, errorThrown);
	}).always(function() {

	});
	return true;
}



//-----------------------------------------------------------------------------------------------------
function executeJson_new(inJson){

//console.log("--------☆☆☆☆------ executeJson_new --------☆☆☆☆-----");




	let utf8_hex_string = json_to_utf8_hex_string(inJson);

	let sendJsonData = "sendJsonData=" + utf8_hex_string;

	let executeJspUrl = localStorage.getItem("sysUrl") + '/resources/jsp/controller.jsp';

	$.ajax({
		url : executeJspUrl,
		type: 'POST',
		timeout:10000,
		data: sendJsonData
	}).done(function(resData, textStatus, jqXHR) {
		done(resData, textStatus, jqXHR);
	}).fail(function(jqXHR, textStatus, errorThrown) {
		doFail(jqXHR, textStatus, errorThrown);
	}).always(function() {

	});
	return true;
}

//２つの数を足して返す------------------------------------------------------------------------------------------------
function addSuu(inSuuSt, inAddNum){
	let suu = Number(inSuuSt);
	suu = suu + inAddNum;
	return suu;
}
//-----------------------------------------------------------------------------------------------------------------
function utf8_to_b64( str ) {
    return window.btoa(encodeURIComponent( escape( str )));
}
//Ｍａｐからサーバーに渡す文字列に変換------------------------------------------------------------------------------
function makeArraySt(iMap){
	let array = new Array();

	//マップはキーと値をタブで区切り、その内容をリストに出力する
	for (var [key, value] of iMap) {

		console.log(key + ':' + value);
		//「タブ(\t)+キャリッジリターン(\r)+タブ(\t)」で区切った文字列にする-------------------------------------
		array.push(key + '\t\r\t' + value);
	}

	//配列を、「タブ(\t)+バックスラッシュ(\b)+タブ(\t)」で区切った文字列にする-------------------------------------
	let arraySt = array.join('\t\b\t');

	return arraySt;
}

//------------------------------------------------------------------------------------------------------------------
function retWindowClose(resData, textStatus, jqXHR, inChild_win){
	window.open('','_self').close();
	sessionStorage.clear();
}

//---------------------------------------------------------------------------------------------------------------------
function get(iKey){
	let value = sessionStorage.getItem(iKey);

	//値の型が数値の場合
	if(isNaN(value) == false){
		return Number(value);
	}
	return value;
}

//---------------------------------------------------------------------------------------------------------------------
function set(iKey, iValue){
	return sessionStorage.setItem(iKey, iValue);
}

//---------------------------------------------------------------------------------------------------------------------
function doFail(jqXHR, textStatus, errorThrown){
	console.log("textStatus=" + textStatus);
}

//--------------------------------------------------------------------------
function string_to_utf8_bytes(text) {
  var result = [];
  if (text == null)
      return result;
  for (var i = 0; i < text.length; i++) {
      var c = text.charCodeAt(i);
      if (c <= 0x7f) {
          result.push(c);
      } else if (c <= 0x07ff) {
          result.push(((c >> 6) & 0x1F) | 0xC0);
          result.push((c & 0x3F) | 0x80);
      } else {
          result.push(((c >> 12) & 0x0F) | 0xE0);
          result.push(((c >> 6) & 0x3F) | 0x80);
          result.push((c & 0x3F) | 0x80);
      }
  }
  return result;
}

//------------------------------------------------------------------------------
function bytes_to_hex_string(bytes) {
	var	result = "";

	for (var i = 0; i < bytes.length; i++) {
		result += byte_to_hex(bytes[i]);
	}
	return result;
}

//------------------------------------------------------------------------------
function byte_to_hex(byte_num) {
	var digits = (byte_num).toString(16);
  if (byte_num < 16) return '0' + digits;
  return digits;
}

//------------------------------------------------------------------------------
function hex_string_to_bytes(hex_str) {
	var	result = [];

	for (var i = 0; i < hex_str.length; i+=2) {
		result.push(hex_to_byte(hex_str.substr(i,2)));
	}
	return result;
}

//16進文字列をバイト値に変換------------------------------------------------------------------------------------------
function hex_to_byte(hex_str) {
	return parseInt(hex_str, 16);
}


//UTF8のバイト配列を文字列に変換---------------------------------------------------------------------------------------
function utf8_bytes_to_string(arr) {
  if (arr == null)
      return null;
  var result = "";
  var i;
  while (i = arr.shift()) {
      if (i <= 0x7f) {
          result += String.fromCharCode(i);
      } else if (i <= 0xdf) {
          var c = ((i&0x1f)<<6);
          c += arr.shift()&0x3f;
          result += String.fromCharCode(c);
      } else if (i <= 0xe0) {
          var c = ((arr.shift()&0x1f)<<6)|0x0800;
          c += arr.shift()&0x3f;
          result += String.fromCharCode(c);
      } else {
          var c = ((i&0x0f)<<12);
          c += (arr.shift()&0x3f)<<6;
          c += arr.shift() & 0x3f;
          result += String.fromCharCode(c);
      }
  }
  return result;
}

// バイト配列を16進文字列に変換----------------------------------------------------------------------------------------
bytes_to_hex_string = function (bytes) {
    let	result = "";

    for (let i = 0; i < bytes.length; i++) {
		result += byte_to_hex(bytes[i]);
	}
	return result;
};

// 文字列をUTF8のバイト配列に変換--------------------------------------------------------------------------------------
string_to_utf8_bytes = function (text) {
    let result = [];
    if (text == null)
        return result;
    for (let i = 0; i < text.length; i++) {
        let c = text.charCodeAt(i);
        if (c <= 0x7f) {
            result.push(c);
        } else if (c <= 0x07ff) {
            result.push(((c >> 6) & 0x1F) | 0xC0);
            result.push((c & 0x3F) | 0x80);
        } else {
            result.push(((c >> 12) & 0x0F) | 0xE0);
            result.push(((c >> 6) & 0x3F) | 0x80);
            result.push((c & 0x3F) | 0x80);
        }
    }
    return result;
};

// 文字列をUTF8の16進文字列に変換--------------------------------------------------------------------------------------
string_to_utf8_hex_string = function (text) {
	let bytes   = string_to_utf8_bytes(text);
	let hex_str = bytes_to_hex_string(bytes);
	return hex_str;
};

// JSONをUTF8の16進文字列に変換--------------------------------------------------------------------------------------
json_to_utf8_hex_string = function (json) {
    let jsf = JSON.stringify(json);
    return string_to_utf8_hex_string(jsf);
};
