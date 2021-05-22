<%@page language='java' contentType='text/html; charset=UTF-8' pageEncoding='UTF-8'%>


<!DOCTYPE html>
<html>

<head>
<meta http-equiv='Pragma' content='no-cache'>
<meta http-equiv='Cache-Control' content='no-cache'>

<link href='/skdb/resources/css/jquery.mobile-1.4.5.css' media='all' rel='stylesheet' />
<link href='/skdb/resources/css/system.css?2020_01_27_2108' media='all' rel='stylesheet' />
<link rel='stylesheet' href='/skdb/resources/FlickSwipe/css/base.css' media='all' />
<link rel='stylesheet' href='/skdb/resources/FlickSwipe/css/common.css' media='all' />

<script src='/skdb/resources/FlickSwipe/js/jquery.min.js'></script>
<script src='/skdb/resources/js/system.js?2020_08_23_001'></script>

<link href='/skdb/resources/manga/css/myGamen.css?2020_02_11_2116'  media='all' rel='stylesheet' />
<link href='/skdb/resources/manga/css/mySlider.css?2020_08_23_001' media='all' rel='stylesheet' />

</head>
<body>
	<div id='i_table9' style='background-color:white;'>
		<h3 id='ui-title'>サーバーに接続できません<br><br><br>
			<a href='#' data-rel='back' class='ui-btn ui-corner-all ui-btn-inline ui-mini m16' onclick='doWindowClose()'>OK</a>
		</h3>
	</div>
	<div id='i_table1'>
		<div id='container'>
			<div id='flickscroll'>
				<ul id='mangaPic'>
				</ul>
			</div>
		</div>
    	<div id='MyTable'>
			<div id='i_2_3'>
				<input id='inp_3' type='text'  value='' readonly >
			</div>
			<div id='i_2_2'>
				<input id='inp_2' type='range' min='1' max='20' value='1' >
			</div>
			<div id='i_2_1'>
				<input id='inp_1' type='text'  style='font-size: 100%;' value='' readonly >
			</div>
		</div>
	</div>

	<div id='iLoading'>
		<img id='iLoadingImg' src=''>
	</div>

<script>

//ウインドウが開いた時に発動-------------------------------------------------------------------------------------------
$(document).ready( function(){
	//ローディング画像を読込む
	$('#iLoading').children('#iLoadingImg').attr('src', '/skdb/resources/jQuery-File-Upload-10.2.0/img/loading.gif');
	//ページ全枠作成
	setPageList(get('maxPage'));

	makeListener();


	set('startupFlag', '1');
});


//ページリスト設定-----------------------------------------------------------------------------------------------------
function setPageList(maxPage){
	let setMoji = '';

	for (let i = 1; i <= maxPage; i++) {
		let suuSt = zeroPadding(i, 4);
		setMoji = setMoji + '<li class="img_li" ><p id="img' + suuSt + '_p" ><img id="img' + suuSt +
		      '" src="/skdb/resources/jQuery-File-Upload-10.2.0/img/loading.gif"></p></li>';
	}

	document.getElementById('mangaPic').innerHTML = setMoji;
}

//マウスの右クリック時に発動-------------------------------------------------------------------------------------------
$(function(){
    $(document).on('contextmenu',function(e){
    	//処理を抜ける
        return false;
    });
});

//画面リサイズ時に発動-------------------------------------------------------------------------------------------------
var timeoutId;

$(window).resize(function () {
	//画像ファイルをリサイズする

	resize();

	//タイマーをクリアする
	clearTimeout( timeoutId ) ;
	//タイマー(1ミリ秒後に1回だけ処理を行う)
	timeoutId = setTimeout( function () {
		   console.log('picHeight=' + get('picHeight'))
		   //初期画面起動後に１回だけ実行
		   if (get('startupFlag') == '1') {
			   startupExecute();
		   }

	}, 500 ) ;
});

//スライダーの値が変更された後に発動-----------------------------------------------------------------------------------
$('#i_2_2').mousemove( function () {
	let maxPage = get('maxPage');
	let no = maxPage - document.getElementById('inp_2').value + 1;
	document.getElementById('inp_1').value = no;
});

//スライダーの値が変更された後に発動-----------------------------------------------------------------------------------
$('#i_2_2').change( function () {

	let pageNo = Number(document.getElementById('inp_2').value);

	//画面が横で、かつ、最終ページ場合、最終ページを左に寄せる
	if (pageNo == 1) {
		pageNo = getAddPageSuu(isAvailTateYoko());
	}

    getPagesData(get('bookNo'), pageNo, getNeedPageInfo(pageNo, -1, 2));

    scrollTimer(pageNo, get('scrollSpeed'));
});

//初期画面起動後に１回だけ実行したい処理 -----------------------------------------------------------------------------
function startupExecute() {
    set('startupFlag', '0');
	//取得していないページデータをサーバーより取得する(起動時に表示するページ以外のデータを取得していない為)
    let wNeedPageInfo = getNeedPageInfo(get('pageNo') , -3, 4)
	getPagesData(get('bookNo'), get('pageNo'), wNeedPageInfo);;
}

//マウス（スクロールホイール）を動かした場合 -------------------------------------------------------------------------
function callbackFn(event) {

	event.preventDefault();

	//クリックされたマウスを離した時、ここを通過
	if(event.deltaY < 0){
		//→スクロールの場合
		if (get('pageNo') < get('maxPage')) {
			//スクロール先に画像がある場、スクロールする
			if (isExistData(get('pageNo'))){
				set('left', 1);
				set('leftBegin', 2);
				doSlide(get('bookNo'), get('pageNo')); //doSlideは、left、leftBeginの設定値で、スクロールの向きを決めている
			}
		}
	}else{
		//←スクロールの場合
		if (get('pageNo') > getAddPageSuu(isAvailTateYoko())) {
			//スクロール先に画像がある場、スクロールする
			if (isExistData(get('pageNo'))){
				set('left', 2);
				set('leftBegin', 1);
				doSlide(get('bookNo'), get('pageNo')); //doSlideは、left、leftBeginの設定値で、スクロールの向きを決めている
			}
		}
	}
}

//必要画像情報取得-----------------------------------------------------------------------------------------------------
function getNeedPageInfo(iPage ,iStart, iEnd){
	//７ページ分(配列の4つ目の要素が現在ページ)
	arr = ['0','0','0','0','0','0','0'];

	for (let i = iStart; i < iEnd; i++) {
		if ((isExistData(iPage + i) == true || (iPage + i < 1) || (iPage + i > get('maxPage')))) {
			continue;
		} else {
			arr[i + 3] = '1';
		}
	}
	//配列を連結して、','を削除して返却する
	return arr.join().replaceAll(',', '');
}

//----------------------------------------------------------------------------------------------------------------------
function isNeedPageInfoAllZero(iPage){
	for (let i = -3; i < 4; i++) {
		if ((isExistData(iPage + i) == true || (iPage + i < 1) || (iPage + i > get('maxPage')))) {
			continue;
		} else {
			return false;
		}
	}

	return true;
}
//スクロールバーの高さを返却する---------------------------------------------------------------------------------------
function getMyTableHeight(iWindowHeight){
	let height1 = iWindowHeight / 20;
	let rtnHeight2 = Math.round(height1); //ウインドウ高の5%
	return rtnHeight2;
}

//スクロールバーの高さをリサイズし返却する------------------------------------------------------------------------------
function resizeMyTableHeight(iHeight, iPicHeight, iPicWidth){

	let suu = iHeight / 3.33;
	let height3_px = Math.round(suu) + 'px'; //ウインドウ高の10%

	$('#MyTable').css('height' , iHeight + 'px');

	$('#inp_1').css('height' , height3_px);
	$('#inp_3').css('height' , height3_px);
}

//縦横可否判定（横幅の可能サイズが縦の2倍以上の場合は YOKO それ以外は TATE---------------------------------------------
function isAvailTateYoko(){
	if (Number($(window).width()) > Number($(window).height()) * 1.1) {
		return 'YOKO';
	}
	return 'TATE';
}

//画像ファイルをリサイズする-------------------------------------------------------------------------------------------
function resize(){

	let beforWindowHeight = get('beforWindowHeight');

	let wGamenMuki = isAvailTateYoko();
	//画面の向き（縦横）が変化した場合
	if(beforWindowHeight != $(window).height()){
		//現画面の向きが横の場合
		if(wGamenMuki == 'YOKO' && get('picWidth') < get('picHeight')){
			//次の画面（２画面分）を先行取得する
			getPagesData(get('bookNo'), get('pageNo'), getNeedPageInfo(get('pageNo'), -3, 4));
		}
	}

	let windowHeight = Number($(window).height());
	let windowWidth  = Number($(window).width());

	let hirutu    = get('hirutu');	 //比率（横/縦）

	//スクロールバーの高さ取得する
	let myTableHeight = getMyTableHeight(windowHeight);

	let newHeight = windowHeight - myTableHeight;
	let newWidth  = Math.round(newHeight * hirutu);
	set('picWidth' , newWidth);
	set('picHeight', newHeight);

	let flickscrollWidth = get('picWidth');

	//縦画像の画面の向きが横の場合
	if(wGamenMuki == 'YOKO' && get('picWidth') < get('picHeight')){
		//画面のスクロール幅を２ページ分にする
		flickscrollWidth = flickscrollWidth * 2;
	}

	//スクロールコンテナの表示幅をリサイズ------
	$('#container').css('width' , get('picWidth')  + 'px');
	$('#container').css('height', get('picHeight') + 'px');

	//スクロール範囲の表示幅&高さをリサイズ------
	$('#flickscroll').css('width' , flickscrollWidth + 'px');
	$('#flickscroll').css('height', get('picHeight') + 'px');

	//-------------------------------------------
	$('#i_table1').css('width', flickscrollWidth + 'px');

	//---------------------------------
	let newLeft  = -get('picWidth') * (get('pageNo') - getAddPageSuu(isAvailTateYoko()));
	$('#mangaPic').css('left', newLeft  + 'px');

	//スクロールバーの高さをリサイズ
	resizeMyTableHeight(myTableHeight, get('picHeight'), get('picWidth'));

	//リサイズ-------------------
	$('.img_li').css('width', get('picWidth')  + 'px');

	let setMainUl = $('#flickscroll').children('ul');

	//スクロール画像全体の幅(listWidth)
	let listWidth = parseInt(setMainUl.children('li').css('width'));
	set('listWidth', listWidth);

	let leftMax = -get('listWidth') * (get('maxPage') - 1);
	set('leftMax', leftMax);

	if(beforWindowHeight != windowHeight){
		for(let cnt = 1; cnt <= get('maxPage'); cnt++){
			resizePic(cnt, get('picWidth'), get('picHeight'));
		}
		set('beforWindowHeight', windowHeight);
	}

	setMainUl.each(function(){
		$(this).css({width: get('picWidth') * get('maxPage') });
	});
}

function resizePic(cnt, newWidth, newHeight){
	//ページリサイズ-------------------
	let pageNo = '#img' + zeroPadding(cnt, 4);
	$(pageNo).css('height', newHeight + 'px');
	$(pageNo).css('width' , newWidth  + 'px');

	//リサイズ-------------------
	pageNo_p = pageNo + '_p';
	$(pageNo_p).css('height', newHeight + 'px');
	$(pageNo_p).css('width' , newWidth  + 'px');
}

//ページデータ取得処理（サーバーより取得前）----------------------------------------------------------------------------
function getPagesData(inBookNo, inPageNo, needPageInfo){
	//必要なページデータがある場合
	if (needPageInfo != '0000000') {
		//サーバーに送信するデータを用意
		let json = {
			className	: 'FT001F02',
			methodName	: 'getPages',
			id			: 1,
			bookNo		: inBookNo,
			pageNo		: String(inPageNo),
			picCount	: String(get('maxPage')),
			needPageInfo: needPageInfo,
		}

		//サーバー処理フラグ 0:処理なし 1:処理中
		set('processFlg', '1');

		executeJson_new(json);
	}
}

//ページデータ取得処理（サーバーより取得後）----------------------------------------------------------------------------
function retGetPages(resData, textStatus, jqXHR){

	let jsonMapDataInfo = JSON.parse(resData.jsonStringMap);

	let pageNo = Number(jsonMapDataInfo.pageNo);

	let pageNo2 = pageNo;

	//取得した画像データをHTMLに設定する
	for(let cnt = 1; cnt <= 7; cnt ++){
		let gazouData = jsonMapDataInfo[cnt];
		if(gazouData != ''){
			pageNo2 = pageNo + cnt - 4;
			setData(pageNo2, gazouData);
			resizePic(pageNo2, get('picWidth'), get('picHeight'));
		}
	}

	//サーバー処理フラグ 0:処理なし 1:処理中
	set('processFlg', '0');
}

//画像をスクロール-----------------------------------------------------------------------------------------------------
function scroll(iPageNo, iScrollSpeed){
	scrollCommon(iPageNo, iScrollSpeed);

    //スクロール後に必要な予備ページをサーバーより取得しておく
	let wNeedPageInfo = getNeedPageInfo(iPageNo, -3, 4)
	if (wNeedPageInfo != '0000000') {
		getPagesData(get('bookNo'), iPageNo, wNeedPageInfo);
	}

	atosyori(iPageNo);
}

//画像をスクロール(500ミリ秒後に予備画像をサーバーより取得する)---------------------------------------------------------
function scrollTimer(iPageNo, iScrollSpeed){
	scrollCommon(iPageNo, iScrollSpeed);

	//タイマーをクリアする
	clearTimeout( timeoutId ) ;
	//タイマー(500ミリ秒後に1回だけ処理を行う)
	timeoutId = setTimeout( function () {
	    //スクロール後に必要な予備ページをサーバーより取得しておく
		let wNeedPageInfo = getNeedPageInfo(iPageNo, -3, 4)
		if (wNeedPageInfo != '0000000') {
			getPagesData(get('bookNo'), iPageNo, wNeedPageInfo);
		}
		atosyori(iPageNo);
	}, 500 ) ;
}

//画像をスクロール(共通)------------------------------------------------------------------------------------------------
function scrollCommon(iPageNo, iScrollSpeed) {
	let scrollPageSuu = getAddPageSuu(isAvailTateYoko());

	let scrollNum = - get('picWidth') * (iPageNo - scrollPageSuu);

	//画像をスクロールさせる
	$('#flickscroll').children('ul').stop().animate({left: scrollNum}, iScrollSpeed);
}

//後処理---------------------------------------------------------------------------------------------------------------
function atosyori(pageNo){
	document.getElementById('inp_1').value = get('maxPage') - pageNo + 1; //現在ページ設定（画面右下表示）
	document.getElementById('inp_2').value = pageNo;                      //現在ページ設定（スライダー）
	set('pageNo', pageNo);		                                          //現在ページをセッションに設定
}

//サーバと接続ができない場合 ------------------------------------------------------------------------------------------
function doFail(jqXHR, textStatus, errorThrown){
	document.getElementById('i_table9').hidden = false; //「サーバーに接続できません」画面を表示する
}

//---------------------------------------------------------------------------------------------------------------------
function makeListener(){

	document.getElementById('i_table1').addEventListener('wheel',callbackFn) ;

	document.getElementById('i_table9').hidden = true;

	//（初期画像を読み込む迄）画像枠を非表示にする
	document.getElementById('i_table1').hidden = true;

	//サーバー処理フラグ 0:処理なし 1:処理中
	set('processFlg', '0');

	set('scrollSpeed', 300);

	let bookNo	= get('bookNo');

	let maxPage = get('maxPage');

	let needPageInfo = '';

	if (isAvailTateYoko() == 'YOKO'){
		needPageInfo = getNeedPageInfo(maxPage, -1, 1);
	} else {
		needPageInfo = getNeedPageInfo(maxPage, -0, 1);
	}

	let json = {
		className	: 'FT001F02',
		methodName	: 'makeListener',
		bookNo		: bookNo,
		pageNo		: String(maxPage),
		needPageInfo: needPageInfo,
	}

	executeJson_new(json);
}

//リスナー作成(ウインドウが開いた時に１度だけ呼ばれる)-----------------------------------------------------------------
function retMakeListener(resData, textStatus, jqXHR){
	let jsonMapDataInfo = JSON.parse(resData.jsonStringMap);

	let bookNo = jsonMapDataInfo.bookNo;
	let pageNo = Number(jsonMapDataInfo.maxPage);

	//ローディング画像を消す
	$('#iLoading').children('#iLoadingImg').attr('src', '');//ローディング画像のリンクを空にする
	$('#iLoadingImg').css('height', '0px');					//ローディング画像の高さを0にする

	let windowWidth  = $(window).width();
	let windowHeight = $(window).height();

	set('beforWindowHeight', windowHeight);

	let picCount = jsonMapDataInfo.picCount;	// ページ数
	let picHeight = Number(jsonMapDataInfo.picHeight);	// 高さ／ページ
	let picWidth = Number(jsonMapDataInfo.picWidth);	// 幅／ページ

	let no_1 = jsonMapDataInfo[1]; //現ページ - 3
	let no_2 = jsonMapDataInfo[2]; //現ページ - 2
	let no_3 = jsonMapDataInfo[3]; //現ページ - 1
	let no_4 = jsonMapDataInfo[4]; //現ページ

	let hirutu = picWidth / picHeight;		// 比率

	set('picWidth', picWidth);
	set('picHeight', picHeight);
	set('hirutu'  , hirutu);

	//画像枠を表示する
	document.getElementById('i_table1').hidden = false;

	set('pageNo', pageNo);

	//スライダー設定値
	document.getElementById('inp_1').value = picCount;
	document.getElementById('inp_2').value = picCount;
	document.getElementById('inp_3').value = picCount;
	document.getElementById('inp_2').max   = picCount; //スライダーの最大値を書き換える

	setData(picCount-3, no_1);
	setData(picCount-2, no_2);
	setData(picCount-1, no_3);
	setData(picCount  , no_4);

	let myTableHeight = getMyTableHeight(windowHeight);
	let newHeight = windowHeight - myTableHeight;
	let newWidth  = Math.round(newHeight * hirutu);

	for(let cnt=picCount; cnt>=picCount - 3; cnt--){
		resizePic(cnt, newWidth, newHeight);
	}

	let isTouch = ('ontouchstart' in window);

	let setMainUl = $('#flickscroll').children('ul');

	//初期画面を整える
	window.resizeBy(newWidth, newWidth); // （横幅 , 高さ）
	//画像ファイルをリサイズする
	resize();

	scroll(picCount, 0);
	//---２点タッチを離した時に実行(iPhoneのみ実行される命令)----------------------------------------------------------
	setMainUl.bind(
		{'gestureend': function(e){
			//２点タッチモードを解除する（スクロール制御開始）
			if(get('nitenTachCounter') > 1){
				set('nitenTachCounter', 0);
			}

			//マウスクリックを離した状態にする
			set('touched', '0');
		}
	});

	//--２点タッチした時に実行(iPhoneのみ実行される命令)---------------------------------------------------------------
	setMainUl.bind(
		{'gesturestart': function(e){
			//サーバー処理中('1')の場合は動作をキャンセル
			if(get('processFlg') == '1'){
				return;
			}

			//２点タッチモード開始（スクロール制御を停止して、２点タッチができるようにする）
			nitenTachStart();
		}
	});

	//マウスを(１点)クリックした時、ここを通過--------------------------------------------------------------------------
	setMainUl.bind(
		{'touchstart mousedown': function(e){

			//２点タッチ(中)モードの場合はマウス動作をキャンセル
			if(isNitenTachMode() == true){
				return;
			}

			//ダブルクリックの場合、画面を閉じる
			isDoubleClick(e);

			let setMainUlNot = $('#flickscroll').children('ul:not(:animated)');

			setMainUlNot.each(function(){
				e.preventDefault(); // ページが動くのを止める
				set('pageX', (isTouch ? event.changedTouches[0].pageX : e.pageX));
				set('leftBegin', parseInt($(this).css('left')));
				set('left'     , parseInt($(this).css('left')));
				//マウスクリックした状態にする
				set('touched', '1');
			});
		}
	});

	//-----------------------------------------------------------------------------------------------------------------
	setMainUl.bind(
		{'touchmove mousemove': function(e){
			//２点タッチ(中)モードの場合はマウス動作をキャンセル
			if(isNitenTachMode() == true){
				return;
			}

			//マウスクリックをクリックしていない場合
			if(get('touched') == '0'){
				return;
			}

			set('left' , get('left') - (get('pageX') - (isTouch ? event.changedTouches[0].pageX : e.pageX)));

			let left    = get('left');

			set('pageX', (isTouch ? event.changedTouches[0].pageX : e.pageX));

			//ドラッグした画像が移動（左右どちらでもよい）している場合
			$(this).css({left: left});
		}
	});

	//クリックされたマウスを離した時 OR マウスが画像枠からはみ出た時----------------------------------------------------
	setMainUl.bind(
		{'touchend mouseup mouseout': function(e){
			//マウスクリックしていない場合、
 			if (get('touched') == '0') {
				return;
			}

			//クリックされたマウスを離した時、ここを通過
			//マウスクリックを離した状態にする
			set('touched', '0');

			doSlide(get('bookNo'), get('pageNo'));
		}
	});
}

//画像をスライドさせる--------------------------------------------------------------------------------------------------
function doSlide(bookNo, pageNo){
	let wPageNo = 0;

	let wScrollSpeed = get('scrollSpeed');

	let wGamenMuki = isAvailTateYoko();
	// → （戻る）方向のスライドの場合
	if(get('leftBegin') > get('left') && (!(get('leftBegin') === get('leftMax')))){

		let wAddPageSuu = getAddPageSuu(wGamenMuki);

		pageNo = pageNo + wAddPageSuu;

		let rightEndPage = document.getElementById('inp_3').value;

		if(pageNo > rightEndPage){
			wPageNo = rightEndPage;
			wScrollSpeed = 10;
		}else{
			wPageNo = pageNo;
		}
	// ← （進む）方向のスライドの場合
	} else if(get('leftBegin') < get('left') && (!(get('leftBegin') === 0))) {

		let wAddPageSuu = getAddPageSuu(wGamenMuki);

		pageNo = pageNo - wAddPageSuu;

		if(pageNo < 1){
			wPageNo = wAddPageSuu;
			wScrollSpeed = 10;
		}else if((pageNo != 1) || (wGamenMuki == 'TATE')){
			wPageNo = pageNo;
		}else if(pageNo == 1 && wGamenMuki == 'YOKO' && getAddPageSuu(wGamenMuki) == 1){
			wPageNo = 1;
		}else{
			wPageNo = 2;
		}
	//上記以外
	}else if(get('leftBegin') == 0) {
		wPageNo = getAddPageSuu(isAvailTateYoko());
		wScrollSpeed = 10;
	}else if(get('leftBegin') <= get('leftMax')) {
		wPageNo = get('maxPage');
		wScrollSpeed = 10;
	}

	if (wPageNo != 0) {
		scroll(wPageNo, wScrollSpeed);
	}
}

//加算ページ数を取得する------------------------------------------------------------------------------------------------
function getAddPageSuu(iGamenMuki){
	//画面の向きが縦、または、横画像の場合
	if(iGamenMuki == 'TATE' || get('picWidth') > get('picHeight')) {
		return  1;
	}
	return 2;
}
//２点タッチモードを開始する-------------------------------------------------------------------------------------------
function nitenTachStart(){
	//２点タッチ制御に使うカウンターを１加算する
	let counter1 = get('nitenTachCounter');
	if(counter1 == null){
		counter1 = 0;
	}
	counter1 = counter1 + 1;
	set('nitenTachCounter', counter1);
}
//２点タッチモード判定--------------------------------------------------------------------------------------------------
function isNitenTachMode(){
	wCounter = get('nitenTachCounter')

	if(wCounter == null){
		set('nitenTachCounter', 0);
	} else 	if(wCounter > 0){
		return true;
	}
	return false;
}
//--ダブルクリック検出(検出した場合、画面を閉じる) ---------------------------------------------------------------------
var clickCount = 0;

function isDoubleClick(e){
	if( !clickCount ) {
		++clickCount ;
		setTimeout( function() {clickCount = 0 ;}, 400 ) ;
	// ダブルクリックの場合
	} else if (get('left') == get('leftBegin')){
		e.preventDefault() ;
		clickCount = 0 ;
		doWindowClose();
	}
}

//---------------------------------------------------------------------------------------------------------------------
function isExistData(inPageNo){
	let elem_id = document.getElementById('img' + zeroPadding(inPageNo, 4));

	if(elem_id != null){
		if(elem_id.currentSrc.length > 7000){
			return true;
		}
	}
	return false;
}

//---------------------------------------------------------------------------------------------------------------------
function setData(pageNo, inData){
	let imgName = 'img' + zeroPadding(pageNo, 4);
	document.getElementById(imgName+'_p').innerHTML='<img id="'+imgName+'" src="data:image/png;base64,'+inData+'";>';
}

//ウインドウを閉じられた場合に発動-------------------------------------------------------------------------------------
function doWindowClose(){
	window.open('','_self').close();
	sessionStorage.clear();
}

</script>
</body>
</html>
