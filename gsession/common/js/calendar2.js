/********************************************************************
 * カレンダーによる日付入力スクリプト
 *
 * ( 下記スクリプトは改造も可能ですがまったくいじらずにそのままペース
 *   トするだけでもご利用いただけるように書いてあります )
 *
 *  Syntax : wrtCalendar( formElementObject[,moveMonthFlg][,winOpenFlg] )
 *  例     : wrtCalendar( this )
 *
 *  使いたいINPUT入力タグにonFocus="wrtCalendar(this)"を ペーストし
 *  ます。それぞれのタグに違う名前(NAME属性)を忘れずに付けておいてく
 *  ださい。
 *
 *  Example :受付日:<INPUT NAME=e1 TYPE=text
 *                         onFocus="wrtCalendar(this)">
 *
 * ------------------------------------------------------------------
 * calendar.js Copyright(c)1999 Toshirou Takahashi tato@fureai.or.jp
 * Support http://www.fureai.or.jp/~tato/JS/BOOK/INDEX.HTM
 * ------------------------------------------------------------------
 *
 *■更新履歴
 * GroupSession向けカスタマイズ
 *
 * ---- TODO 未実装 ----
 * 2007/02/20 CSS対応(60%対応済)
 * 2007/02/20 祝祭日対応(外部データを取得)
 *
 * ---- 実装済み ----
 * 2007/02/20 Windowのサイズを変更
 * 2007/02/20 入力エラーチェックを追加
 *            エラーの場合現在日をセット(警告ダイアログも表示する)
 * 2007/02/20 Mac用のCloseボタンは削除
 * 2007/02/20 現在日時をセットするメソッドを追加
 * 2007/02/20 年取得ロジックの関数化
 * 2007/02/20 コメント追記
 * ----
 */

var now = new Date();
var absnow = now;
var Win=navigator.userAgent.indexOf('Win')!=-1;
var Mac=navigator.userAgent.indexOf('Mac')!=-1;
var X11=navigator.userAgent.indexOf('X11')!=-1;
var Moz=navigator.userAgent.indexOf('Gecko')!=-1;
var Opera=!!window.opera;
var winflg=1;


/**
 * 引数のオブジェクト(フォーム部品)に現在日時をセットする。
 *
 * @param dd 日
 * @param mm 月
 * @param yyyy 年
 */
function setNow(dd, mm, yyyy) {
  var nowyear = now.getYear();
  dd.value = now.getDate();
  mm.value = now.getMonth() + 1;
  yyyy.value = convYear(nowyear);
}

/**
 * Date#getgetYearで取得できる値をyyyy形式に変換する
 * @param yyyy 年
 */
function convYear(yyyy) {
  if(yyyy<1900) {
    yyyy=1900+yyyy;
  }
  return yyyy;
}

/**
 * カレンダーを表示する。
 *
 * @param ojymd
 * @param btnId カレンダーボタンのid
 */
function wrtCalendarByBtn(ojymd, btnId) {
	wrtCalendar(ojymd ,'', false, btnId)
}

/**
 * カレンダーを表示する。
 *
 * @param oj 日
 * @param ojmm 月
 * @param oyyyyy 年
 * @param arg1 当日の日付をセットする場合「today」の文字列をセットする。
 * @param arg2 未使用
 * @param btnId カレンダーボタンのid
 */
function wrtCalendar(ojymd, arg1, arg2, btnId ) {

  if(Opera)return
  ojymd.blur()

  if(!arguments[1])arg1=0
  if(!Moz)

  //winflg=0
  if(arguments[4] || arguments[4] == 0) {
    winflg=0
  }


  var today=false;

  if(arg1=='today'){
    today=true;
    arg1=0;
  }
    wkymd=ojymd.value;
    wkyyyy=wkymd.substring(0, 4);
    wkmm=wkymd.substring(4, 6);
    wkdd=wkymd.substring(6, 8);

    // 現在初期化
    if (arg1==0) {
       var paramvalue = wkdd;
       var parseDate = new Array(3);
       var ngval = false;
       if(today || paramvalue==""){
          now = new Date();
       } else {

		   //yyyy/mm/dd文字列を作成
		   var ymdf = escape(wkyyyy + '/' + wkmm + "/" + wkdd);
		   //RegExpオブジェクトの作成
		   re = new RegExp(/(\d{4})\/(\d{1,2})\/(\d{1,2})/);
		   if (ymdf.match(re)) {
			   now   = new Date(wkyyyy ,wkmm -1,wkdd)
           } else {
			   alert("フォーマットが不正なため現在月を表示します。");
			   now   = new Date()
		   }
		   /*
		   tmpnow = new Date();

		   var yyyy = escape(wkyyyy);
		   var mm = escape(wkmm);
		   var dd = escape(wkdd);
		   alert (yyyy + ":" + mm + ":" + dd);

		   //年チェック
		   yyyyre = new RegExp(/(\d{4})/);
		   if (!yyyy.match(yyyyre)) {
			   alert("年エラー");
			   yyyy = tmpnow.getYear();
		   }

		   //月チェック
		   mmre = new RegExp(/[^0-9]/g);
		   if (mm.match(mmre)) {
			   alert("月エラー");
			   mm = tmpnow.getMonth() + 1;
		   }

		   //日チェック
		   ddre = new RegExp(/(\d{1,2})/);
		   if (!dd.match(ddre)) {
			   alert("日エラー");
			   dd = tmpnow.getDate();
		   }

		   now   = new Date(yyyy, mm -1 , dd);
		   alert ("その２　＝" + yyyy + ":" + mm + ":" + dd);
		   alert(now);
		   */
       }
    } else {
    	argdate = arg1.toString();
        arg1year = argdate.substring(0, 4);
        arg1month = argdate.substring(4, 6);
        arg1day = '01';
        now   = new Date(arg1year ,arg1month -1,arg1day)
    }

  //-年月日取得
  nowdate  = now.getDate();
  nowmonth = now.getMonth();
  nowyear  = now.getYear();


//  //-月移動処理
//  if(arg1 > 0){        //12月でarg1が+なら
//    nowmonth = -1 + arg1 ; nowyear++   //月はarg1-1;1年加算
//  } else if(nowmonth==0 && arg1 < 0){  //1月でarg1が-なら
//    nowmonth = 12 + arg1 ; nowyear--   //月はarg1+12;1年減算
//  } else {
//    nowmonth +=  arg1                  //2-11月なら月は+arg1
//  }
//
//  //年移動処理
//  if (nowmonth > 11) {
//    nowmonth = nowmonth - 12;
//    nowyear = nowyear + 1;
//  } else if (nowmonth < 0) {
//    nowmonth = nowmonth + 12;
//    nowyear = nowyear - 1;
//  }

  //-2000年問題対応
  nowyear = convYear(nowyear);

  //-現在月を確定
  now   = new Date(nowyear,nowmonth,1)

  //-YYYYMM作成
  nowyyyymm=nowyear*100+nowmonth

  // YYYY/MM作成
  nowmonth = nowmonth +1
  if(nowmonth<10) nowmonth = '0' + nowmonth
  nowtitleyyyymm=nowyear + msglist.year + nowmonth + msglist.month

  // YYYY MM コンボボックス作成
  var startyear = nowyear - 3;   //前後3年表示なので最初は現在年-3年
  var startmonth = 1;
  combyear = new Array(7);
  combmonth = new Array(12);
  for (var i = 0; i < 7; i++){
  	combyear[i] = startyear + i;
  }
  combmonth[0] = '01';
  combmonth[1] = '02';
  combmonth[2] = '03';
  combmonth[3] = '04';
  combmonth[4] = '05';
  combmonth[5] = '06';
  combmonth[6] = '07';
  combmonth[7] = '08';
  combmonth[8] = '09';
  combmonth[9] = '10';
  combmonth[10] = '11';
  combmonth[11] = '12';

  //-週設定
  week = new Array(msglist.sunday,msglist.monday,msglist.tuesday,msglist.wednesday,msglist.thursday,msglist.friday,msglist.saturday);

  //-カレンダー表示用サブウインドウオープン
  if(winflg){

    var w=450
    var h=430

    //-calendar用OS別サイズ微調整
    if(Moz)     { w+=15 ; h+=40 }
    else if(Win){ w+=0  ; h+=0  }
    else if(Mac){ w+=8  ; h+=22 }
    else if(X11){ w+=5  ; h+=46 }

    var x=100
    var y=20

    var btnPosition = "#" + btnId;
    if (btnId && document.getElementById(btnId)) {
      x = $(btnPosition).offset().left  - w;
      if (x < 0) { x = 0; }
      y = $(btnPosition).offset().top;
    }

/*
    if(document.all){
        x=window.event.screenX+30
        y=window.event.screenY-180
    } else if (document.layers || document.getElementById){
        x+=window.screenX
        y+=window.screenY
    }
	*/

    mkSubWin('','calendar',x,y,w,h)

  }

  //-カレンダー構築用基準日の取得
  fstday   = now;                                           //今月の1日
  startday = fstday - ( fstday.getDay() * 1000*60*60*24 );  //最初の日曜日
  startday = new Date(startday);

  paramDay  = new Date(startday);

  paramDate = paramDay.getDate();
  paramMonth= paramDay.getMonth();
  paramYear = paramDay.getYear();
  paramYear = convYear(paramYear);
  paramyyyymm = paramYear * 100 + paramMonth;
  paramClmonth= paramMonth+1;
  if(paramClmonth<10) paramClmonth = '0' + paramClmonth;
  if(paramDate<10) paramDate = '0' + paramDate;
  paramyymd= paramYear.toString() + (paramClmonth.toString()) + paramDate.toString();

  //休日設定を取得
  var holMap = new Object();
  $.ajaxSetup({async:false});
  $.post('../common/cmn200.do', {"cmn200dateStr":paramyymd}, function(data){
    var jsonArray = eval('(' + data + ')');
    if (jsonArray != null) {
      for (n=0;n<jsonArray.length;n++) {
        //休日を格納したMAPを作成
        holMap[jsonArray[n].date] = jsonArray[n].holidayName;
      }
    }
  });

  //-カレンダー構築用HTML
  ddata = ''
  ddata += '<html>\n'
  ddata += '<head>'
  if(!Moz)
  ddata += '<meta http-equiv="Content-Type" content="text/html;charset=SHIFT_JIS">\n'
  ddata += '<title>Calendar</title>\n'
  ddata += '<style>\n'
  ddata += ' body  { font-size:12px ; margin : 7px ; background-color: #FFFFFF;}\n'
  ddata += ' th  { font-size:12px ; font-weight : bold }\n'
  //ddata += ' td  { font-size:12px ; }\n'

  ddata += ' table  { '
  ddata += '        border-collapse: collapse;'
  ddata += '     }\n'


  ddata += ' .days  { '
  ddata += '      padding:0;'
  ddata += '      font-size:12px ;'
  ddata += '      border-top: 0px;'
  ddata += '      border-bottom: 1px;'
  ddata += '      border-left: 1px;'
  ddata += '      border-right: 1px;'
  ddata += '      border-color: #cccccc;'
  ddata += '      border-top-style: solid;'
  ddata += '      border-bottom-style: solid;'
  ddata += '      border-left-style: solid;'
  ddata += '      border-right-style: solid;'
  ddata += '     }\n'

  ddata += ' .days2  { '
  ddata += '      padding:0;'
  ddata += '      font-size:12px ;'
  ddata += '      border-top: 1px;'
  ddata += '      border-bottom: 1px;'
  ddata += '      border-left: 1px;'
  ddata += '      border-right: 1px;'
  ddata += '      border-color: #cccccc;'
  ddata += '      border-top-style: solid;'
  ddata += '      border-bottom-style: solid;'
  ddata += '      border-left-style: solid;'
  ddata += '      border-right-style: solid;'
  ddata += '     }\n'

  ddata += ' .holDays  { '
  ddata += '      padding:0;'
  ddata += '      font-size:12px ;'
  ddata += '      border-top: 1px;'
  ddata += '      border-bottom: 0px;'
  ddata += '      border-left: 1px;'
  ddata += '      border-right: 1px;'
  ddata += '      border-color: #cccccc;'
  ddata += '      border-top-style: solid;'
  ddata += '      border-left-style: solid;'
  ddata += '      border-right-style: solid;'
  ddata += '     }\n'


  ddata += ' a  { text-decoration:none;}\n'
  ddata += ' input  { font-size:10px ; padding:3px;}\n'

  /* 画面上部の「yyyy年mm月」 */
  ddata += ' .ttl_yyyymm  {font-size: 130%;color: #333333; font-family: Verdana,Arial,sans-serif; font-weight: bold;}\n'

  /* 日付のフォント */
  ddata += ' .text_dd  { font-size: 130%;color: #43596c;}\n'
  ddata += ' .text_dd_2  {font-size: 130%;color: #d0c4c4;}\n'
  ddata += ' .text_dd_3  {font-size: 130%;color: #bca6a6;}\n'
  ddata += ' .text_dd_4  {font-size: 130%;color: #ff0000;}\n'
  ddata += ' .text_holday  {font-size: 80%;color: #ff0000;}\n'
  ddata += ' .text_holday2  {font-size: 80%;color: #bca6a6;}\n'
  ddata += ' .small  {font-size: 100%; color: #43596c;}\n'

  ddata += '</style>\n'
  ddata += '<link rel="stylesheet" type="text/css" href="../common/css/default.css">'
  ddata += '</head>\n'
  ddata += '<body>\n'

  ddata += '<form name="calendar2">\n'
  ddata += '<table BORDER="0" BGCOLOR="#FFFFFF"  BORDERCOLOR="#dddddd" WIDTH="100%" cellpadding="3">\n'

  //-MONTH
    ddata += '   <TR id="trmonth" class="cal_bg_image" BORDERCOLOR=#999999 WIDTH=140 HEIGHT="40px">\n'
    ddata += '   <TH COLSPAN="7" class="days2">\n'

    ddata += '<a href="javascript:self.opener.wrtCalendar(self.opener.document.'+ojymd.form.name+'.'+ojymd.name + ' ,' + (nowyear-1) + nowmonth +',0);"><span class="small">&lt;</span></a>\n'
    ddata += '<a href="javascript:self.opener.wrtCalendar(self.opener.document.'+ojymd.form.name+'.'+ojymd.name + ' ,' + (nowyear-1) + nowmonth +',0);"><span class="small">' + msglist.prevYear +'</span></a>&nbsp;&nbsp;&nbsp;&nbsp;\n'

    ddata += '   <select name="selectyear" onchange="self.opener.wrtCalendar(self.opener.document.'+ojymd.form.name+'.'+ojymd.name + ', document.forms[0].selectyear.value , 0)">\n>\n'
    for (var y = 0; y < combyear.length; y++ ) {
        var dspyear = startyear + y;
        ddata += '   <option value="' + dspyear + nowmonth + '"';
            if (dspyear == nowyear) {
                ddata += ' selected="selected">';
            } else {
                ddata += '>';
            }
        ddata += dspyear + msglist.year + '</option>\n';
    }
    ddata += '   </select>\n';

    ddata += '   <select name="selectmonth" onchange="self.opener.wrtCalendar(self.opener.document.'+ojymd.form.name+'.'+ojymd.name + ', document.forms[0].selectmonth.value , 0)">\n';
    for (var m = 0; m < combmonth.length; m++ ) {
        ddata += '   <option value="' + nowyear + combmonth[m] + '"';
            if (combmonth[m] == nowmonth) {
                ddata += ' selected="selected">';
            } else {
                ddata += '>';
            }
        ddata += combmonth[m] + msglist.month + '</option>\n';
    }
    ddata += '   </select>\n';

    ddata += '&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:self.opener.wrtCalendar(self.opener.document.'+ojymd.form.name+'.'+ojymd.name + ',' + (nowyear+1) + nowmonth + ',0);"><span class="small">' + msglist.nextYear + '</span></a>\n'

    ddata += '<a href="javascript:self.opener.wrtCalendar(self.opener.document.'+ojymd.form.name+'.'+ojymd.name + ',' + (nowyear+1) + nowmonth + ',0);"><span class="small">&gt;</span></a>\n'

	ddata += '   <table width="100%">\n'
	ddata += '   <tr>\n'

	nummonth = new Number(nowmonth);

    var beforemonth = nummonth - 1;

    if (beforemonth < 10) {
    	beforemonth = '0' + beforemonth;
    } else if (beforemonth < 1) {
    	nowyear = nowyear - 1;
    	beforemonth = 12;
    }

    ddata += '   <td width="0%">\n'
    ddata += '      <input class="pn_btn" type=button VALUE="&lt;&nbsp;&nbsp;' + msglist.prevMonth + '" \n'
    ddata += '          onClick="self.opener.wrtCalendar(self.opener.document.'+ojymd.form.name+'.'+ojymd.name + ' ,' + nowyear + beforemonth + ',0)"\n'
    ddata += '   > \n'
    ddata += '   </td>\n'


	ddata += '   <td align="center" width="100%">\n'
    ddata += '   <input class="now_btn" type="button" VALUE="' + msglist.thisMonth + '" \n'
    ddata += 'onClick="self.opener.wrtCalendar(self.opener.document.'+ojymd.form.name+'.'+ojymd.name+',\'today\',0)"\n'
    ddata += '>'
    ddata += '   </td>\n'

    var nextmonth = nummonth + 1;
    if (nextmonth < 10) {
    	nextmonth = '0' + nextmonth;
    } else if (nextmonth > 12) {
    	nowyear = nowyear + 1;
    	nextmonth = 01;
    }

	ddata += '   <td width="0%">\n'
    ddata += '     <input class="pn_btn" type=button VALUE="' + msglist.nextMonth + '&nbsp;&nbsp;&gt;" \n'
    ddata += 'onClick="self.opener.wrtCalendar(self.opener.document.'+ojymd.form.name+'.'+ojymd.name + ',' + nowyear + nextmonth + ',0)"\n'
    ddata += '>'
    ddata += '   </td>\n'

	ddata += '   </tr>\n'
	ddata += '   </table>\n'

    ddata += '</TH>\n'
    ddata += '   </TR>\n'

  //-WEEK
  ddata += '   <TR BGCOLOR="#f9f2f2">\n'

  for (i=0;i<7;i++){


	if (i == 0) {
        ddata += '   <TH WIDTH="14%" class="days" BGCOLOR="pink">\n'
	} else if (i == 6) {
		ddata += '   <TH WIDTH="14%" class="days" BGCOLOR="#D9F0FF">\n'
	} else {
        ddata += '   <TH WIDTH="14%" class="days">\n'
	}
    ddata +=       week[i]


    ddata += '   </TH>\n'
  }
  ddata += '   </TR>\n'

  //-DATE
  for(j=0;j<6;j++){

    //祝日TR
    ddata += '   <TR BGCOLOR="#ffffff">\n'
    for(i=0;i<7;i++){
      nextday = startday.getTime() + (i * 1000*60*60*24)
      wrtday  = new Date(nextday)

      wrtdate = wrtday.getDate()
      wrtmonth= wrtday.getMonth()
      wrtyear = wrtday.getYear()
      wrtyear = convYear(wrtyear);
      wrtyyyymm = wrtyear * 100 + wrtmonth
      wrtclmonth= wrtmonth+1;
      if(wrtclmonth<10) wrtclmonth = '0' + wrtclmonth
      if(wrtdate<10) wrtdate = '0' + wrtdate
      wrtyymd= ''+wrtyear + (wrtclmonth) + wrtdate

	  if (i == 0) {
        ddata += ' <TD BGCOLOR="pink" height="15px" class="holDays" align="center">\n'
        if(holMap[wrtyymd] != null && wrtyyyymm == nowyyyymm){
          ddata += '<span class="text_holday">\n' + holMap[wrtyymd] + '</span>\n'
        } else if (holMap[wrtyymd] != null && wrtyyyymm != nowyyyymm){
          ddata += '<span class="text_holday2">\n' + holMap[wrtyymd] + '</span>\n'
        }
	  } else if (i == 6) {
        ddata += ' <TD BGCOLOR="#D9F0FF" height="15px" class="holDays" align="center">\n'
        if(holMap[wrtyymd] != null && wrtyyyymm == nowyyyymm){
          ddata += '<span class="text_holday">\n' + holMap[wrtyymd] + '</span>\n'
        } else if (holMap[wrtyymd] != null && wrtyyyymm != nowyyyymm){
          ddata += '<span class="text_holday2">\n' + holMap[wrtyymd] + '</span>\n'
        }
      } else if(wrtyyyymm != nowyyyymm){
        ddata += ' <TD BGCOLOR="#eeeeee" height="15px" class="holDays" align="center">\n'
        if(holMap[wrtyymd] != null){
          ddata += '<span class="text_holday2">\n' + holMap[wrtyymd] + '</span>\n'
        }
      } else if( wrtdate  == absnow.getDate()  &&
                 wrtmonth == absnow.getMonth() &&
                 wrtday.getYear() == absnow.getYear()){
        ddata += ' <TD BGCOLOR="FFFFCC" height="15px" class="holDays" align="center">\n'
        if(holMap[wrtyymd] != null){
          ddata += '<span class="text_holday">\n' + holMap[wrtyymd] + '</span>\n'
        }
      } else if(holMap[wrtyymd] != null){
        ddata += ' <TD height="15px" class="holDays" align="center">\n'
        ddata += '<span class="text_holday">\n' + holMap[wrtyymd] + '</span>\n'
      } else {
        ddata += ' <TD class="holDays" height="15px" align="center">\n'
        if(holMap[wrtyymd] != null){
          ddata += '<span class="text_holday">\n' + holMap[wrtyymd] + '</span>\n'
        }
      }
      ddata += '   </TD>\n'
    }
    ddata += '   </TR>\n'

  　    //日TR
    ddata += '   <TR BGCOLOR="#ffffff">\n'
    for(i=0;i<7;i++){
      nextday = startday.getTime() + (i * 1000*60*60*24)
      wrtday  = new Date(nextday)

      wrtdate = wrtday.getDate()
      wrtmonth= wrtday.getMonth()
      wrtyear = wrtday.getYear()
      wrtyear = convYear(wrtyear);
      wrtyyyymm = wrtyear * 100 + wrtmonth
      wrtclmonth= wrtmonth+1;
      if(wrtclmonth<10) wrtclmonth = '0' + wrtclmonth
      if(wrtdate<10) wrtdate = '0' + wrtdate
      wrtyymd= ''+wrtyear + (wrtclmonth) + wrtdate

      wrtdateA  = '<b><A HREF="javascript:function v(){'
	  //日
      wrtdateA += '   self.opener.document.'+ojymd.form.name
      wrtdateA += '.'+ojymd.name+'.value=(\''+wrtyymd+'\');'
	  wrtdateA += 'opener.document.'+ojymd.form.name
	  wrtdateA += '.submit();'
	  wrtdateA += 'self.close()};v()" '
      wrtdateA += '>\n'
      if(wrtyyyymm != nowyyyymm){
        if (i == 0 || i == 6) {
          wrtdateA += '<span class="text_dd_3">\n'
        } else{
          wrtdateA += '<span class="text_dd_2">\n'
        }
      } else if(holMap[wrtyymd] != null){
        wrtdateA += '<span class="text_dd_4">\n'
      } else {
        wrtdateA += '<span class="text_dd">\n'
      }
      wrtdateA += wrtdate
      wrtdateA += '</span>\n'
      wrtdateA += '</A></b>\n'

	  if (i == 0) {
        ddata += ' <TD BGCOLOR="pink" height="35px" class="days" align="center" valign="top">\n'
        ddata += wrtdateA

	  } else if (i == 6) {
        ddata += ' <TD BGCOLOR="#D9F0FF" height="35px" class="days" align="center" valign="top">\n'
        ddata += wrtdateA

      } else if(wrtyyyymm != nowyyyymm){
        ddata += ' <TD BGCOLOR="#eeeeee" height="35px" class="days" align="center" valign="top">\n'
        ddata += wrtdateA

      } else if( wrtdate  == absnow.getDate()  &&
                 wrtmonth == absnow.getMonth() &&
                 wrtday.getYear() == absnow.getYear()){
        ddata += ' <TD BGCOLOR="FFFFCC" height="35px" class="days" align="center" valign="top">\n'
        ddata += '<FONT COLOR="#ffffff">'+wrtdateA+'</FONT>\n'
      } else if(holMap[wrtyymd] != null){
        ddata += ' <TD height="35px" class="days" align="center" valign="top">\n'
        ddata += wrtdateA
      } else {
        ddata += ' <TD class="days" height="35px" align="center" valign="top">\n'
        ddata += wrtdateA
      }
      ddata += '   </TD>\n'
    }
    ddata += '   </TR>\n'

    startday = new Date(nextday)
    startday = startday.getTime() + (1000*60*60*24)
    startday = new Date(startday)
  }

  //-mac用クローズボタン
//  if(Mac){
//    ddata += '   <TR>\n'
//      ddata += '   <TD COLSPAN=7 ALIGN=center>\n'
//      ddata += '   <INPUT TYPE=button VALUE="CLOSE" '
//       ddata += '          onClick="self.close();return false">\n'
//      ddata += '   </TD>\n'
//    ddata += '   </TR>\n'
//  }
    ddata += '   <TR>\n'
    ddata += '   <TD COLSPAN="7" ALIGN="center">\n'
    ddata += '   <input class="close_btn2" type="button" value="' + msglist.close + '" '
    ddata += '          onClick="self.close();return false">\n'
    ddata += '   </TD>\n'
    ddata += '   </TR>\n'

  ddata += '</table>\n'
  ddata += '</form>\n'

  ddata += '</body>\n'
  ddata += '</html>\n'


  calendarwin.document.write(ddata)
  calendarwin.document.close()
  calendarwin.focus()

  winflg=1
}


/********************************************************************
 * 簡易サブウインドウ開き
 *  Syntax : mkSubWin(URL,winName,x,y,w,h)
 *  例     : mkSubWin(winIndex,'test.htm','win0',100,200,150,300)
 * ------------------------------------------------------------------
 */

var calendarwin;

function mkSubWin(URL,winName,x,y,w,h){

    var para =""
//             +" left="        +350
             +" left="        + x
             +",screenX="     +x
             +",top="         +y
             +",screenY="     +y
             +",toolbar="     +0
             +",location="    +0
             +",directories=" +0
             +",status="      +0
             +",menubar="     +0
             +",scrollbars="  +0
             +",resizable="   +1
             +",innerWidth="  +w
             +",innerHeight=" +h
             +",width="       +w
             +",height="      +h

        calendarwin=window.open(URL,winName,para);
        calendarwin.focus();
}
function calWindowClose(){
    if(calendarwin && calendarwin != null){
        calendarwin.close();
    }
}

function afterNewWinOpen(win){
	win.moveTo(0,0);
	calendarwin.focus();
	return;
}
  /*--/////////////ここまで///////////////////////////////////////--*/
