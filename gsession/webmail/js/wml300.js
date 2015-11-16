$(function() {

    //グラフの描画
    drawTotalizationGraph();

    /* datepicker */
    var dates = jQuery( '#jquery-ui-datepicker-from, #jquery-ui-datepicker-to' ) . datepicker( {
        showAnim: 'blind',
        changeMonth: true,
        numberOfMonths: 2,
        showCurrentAtPos: 1,
        showButtonPanel: true,
        onSelect: function( selectedDate ) {
            var option = this . id == 'jquery-ui-datepicker-from' ? 'minDate' : 'maxDate',
                instance = jQuery( this ) . data( 'datepicker' ),
                date = jQuery . datepicker . parseDate(
                    instance . settings . dateFormat ||
                    jQuery . datepicker . _defaults . dateFormat,
                    selectedDate, instance . settings );
            dates . not( this ) . datepicker( 'option', option, date );

            /* 日付変更  */
            changeGraphDate($("#jquery-ui-datepicker-from").val(), $("#jquery-ui-datepicker-to").val());
        }
    });

    /* メニュー  hover*/
    $('.sel_menu_title').hover(
        function () {
            $(this).addClass("sel_menu_title_on");
        },
        function () {
            $(this).removeClass("sel_menu_title_on");
        }
    );

    /* メニュー 格納用縦線 hover*/
    $('.menu_length_border').hover(
            function () {
                $(this).addClass("menu_length_border_on");
              },
              function () {
                  $(this).removeClass("menu_length_border_on");
              }
    );


    /* メニュー 格納用縦線 click*/
    $(".menu_length_border").live("click", function(){
        if ($("#sel_menu_wrapper").css('display') == "none") {
            $('#sel_menu_wrapper').removeClass('display_none');
            $('#menu_length_area').removeClass("menu_length_border_none");
        } else {
            $('#sel_menu_wrapper').addClass('display_none');
            $('#menu_length_area').addClass("menu_length_border_none");
        }
        drawTotalizationGraph();
    });

    /* 選択中グラフ名の強調 */
    var currItem = $('input:hidden[name=wml300GraphItem]').val();
    $('#' + currItem).addClass('text_bg_index');

    /*統計情報画面選択メニュー*/
    var adminFlg = $('input:hidden[name=wml300GsAdminFlg]').val();
    if (adminFlg == 'false') {
      $('.menu_select_text').addClass('menu_select_text_no_top');
      $('.menu_select_text').removeClass('menu_select_text');
      $('.menu_select_icon').addClass('menu_select_icon_no_top');
      $('.menu_select_icon').removeClass('menu_select_icon');
    }

    /*月週日切り替え*/
    $('input[name="wml300DateUnit"]:checked').attr('onclick','').unbind('click');

    /*
      メニュー格納
     $('#sel_menu_wrapper').addClass('display_none');
     $('#menu_length_area').addClass("menu_length_border_none");*/
});

//日付変更
function changeGraphDate(frdate, todate) {
  var dateUnit = $('input[name=wml300DateUnit]:checked').val();
  if (dateUnit == 1) {
    $("input[name=wml300DateWeeklyFrStr]").val(frdate);
    $("input[name=wml300DateWeeklyToStr]").val(todate);
  } else if (dateUnit == 0) {
    $("input[name=wml300DateDailyFrStr]").val(frdate);
    $("input[name=wml300DateDailyToStr]").val(todate);
  }
  document.forms[0].CMD.value='pageDate';
  document.forms[0].submit();
  return false;
}

//年月コンボ変更
function changeYearMonthCombo(flg) {

  var frYear = Number(getDateComboValue('wml300DateMonthlyFrYear'));
  var frMonth = Number(getDateComboValue('wml300DateMonthlyFrMonth'));
  var toYear = Number(getDateComboValue('wml300DateMonthlyToYear'));
  var toMonth = Number(getDateComboValue('wml300DateMonthlyToMonth'));

  if (frYear > toYear || ((frYear == toYear && frMonth > toMonth))) {
    if (flg == 'from') {
      $('select[name=wml300DateMonthlyToYear]').val(getDateComboValue('wml300DateMonthlyFrYear'));
      $('select[name=wml300DateMonthlyToMonth]').val(getDateComboValue('wml300DateMonthlyFrMonth'));
    } else {
      $('select[name=wml300DateMonthlyFrYear]').val(getDateComboValue('wml300DateMonthlyToYear'));
      $('select[name=wml300DateMonthlyFrMonth]').val(getDateComboValue('wml300DateMonthlyToMonth'));
    }
  }

  document.forms[0].CMD.value='pageDate';
  document.forms[0].submit();
  return false;
}

function getDateComboValue(paramName) {
  return $("select[name='" + paramName + "']").val();
}

//年月週コンボ変更
function changeWeeklyDateCombo(flg) {

  var frYear = Number(getDateComboValue('wml300DateWeeklyFrYear'));
  var frMonth = Number(getDateComboValue('wml300DateWeeklyFrMonth'));
  var frWeek = Number(getDateComboValue('wml300DateWeeklyFrWeek'));
  var toYear = Number(getDateComboValue('wml300DateWeeklyToYear'));
  var toMonth = Number(getDateComboValue('wml300DateWeeklyToMonth'));
  var toWeek = Number(getDateComboValue('wml300DateWeeklyToWeek'));

  if (frYear > toYear
      || (frYear == toYear && frMonth > toMonth)
      || (frMonth == toMonth && frWeek > toWeek)) {
    if (flg == 'from') {
      $('select[name=wml300DateWeeklyToYear]').val(getDateComboValue('wml300DateWeeklyFrYear'));
      $('select[name=wml300DateWeeklyToMonth]').val(getDateComboValue('wml300DateWeeklyFrMonth'));
      $('select[name=wml300DateWeeklyToWeek]').val(getDateComboValue('wml300DateWeeklyFrWeek'));
    } else {
      $('select[name=wml300DateWeeklyFrYear]').val(getDateComboValue('wml300DateWeeklyToYear'));
      $('select[name=wml300DateWeeklyFrMonth]').val(getDateComboValue('wml300DateWeeklyToMonth'));
      $('select[name=wml300DateWeeklyFrWeek]').val(getDateComboValue('wml300DateWeeklyToWeek'));
    }
  }

  document.forms[0].CMD.value='pageDate';
  document.forms[0].submit();
  return false;
}

//表示件数変更
function changeDspNumCombo() {
    document.forms[0].CMD.value='dspNumChange';
    document.forms[0].submit();
    return false;
}

function changePage(cmbObj) {
    document.forms[0].wml300NowPage.value=cmbObj.options[cmbObj.selectedIndex].value;
    document.forms[0].CMD.value='pageChange';
    document.forms[0].submit();
}

//表示項目変更
function changeDspItem(nextItem) {
  var currItem = $('input:hidden[name=wml300GraphItem]').val();
  if (currItem != nextItem) {
    $('#' + currItem).removeClass('text_bg_index');
    $('#' + nextItem).addClass('text_bg_index');
    document.forms[0].wml300GraphItem.value=nextItem;
    drawTotalizationGraph();
  }
}

//集計グラフ
function drawTotalizationGraph() {

    var tmp = document.getElementById('wmlCntGraph');
    for (var i=tmp.childNodes.length-1; i>=0; i--) {
      tmp.removeChild(tmp.childNodes[i]);
    }

    var cntData = null;

    var animateFlg = false;

    var sumKadouTime = 0;

    //IE判定  6 7 8 9
    if ($.browser.msie && $.browser.version < 10) {
          animateFlg = false;
    } else {
          animateFlg = true;
    }

    var jsonDateData = $('input:hidden[name=jsonDateData]').val();
    var jsonJmailData = $('input:hidden[name=jsonJmailData]').val();
    var jsonSmailData = $('input:hidden[name=jsonSmailData]').val();

    var jwmlObject = eval(jsonJmailData);
    var swmlObject = eval(jsonSmailData);
    var tick = eval(jsonDateData);

    var dayAddFlg = 0;
    var ticksOpsAngle = 0;
    if (tick.length > 8) {
      ticksOpsAngle = -30;
    }
    var ticksOpsSize = '7pt';

    var graphItem = $('input:hidden[name=wml300GraphItem]').val();
    var wmlObject = null;
    var graphLabel = null;
    var graphColor = null;
    if (graphItem == 'wml_graph_smail') {
      wmlObject = [swmlObject];
      graphLabel = msglist["numTransmitWml"];
      graphColor = ['#eaa228'];
    } else {
      wmlObject = [jwmlObject];
      graphLabel = msglist["numReceiveWml"];
      graphColor = ['#4bb2c5'];
    }

    var wmlSeries = [];
    for (var i=0; i<wmlObject.length; i++) {
      var srsElm = {label:graphLabel, yaxis:'yaxis', rendererOptions: {animation: {speed:1000}}};
      wmlSeries.push(srsElm);
    }

    var ankenPlot = $.jqplot('wmlCntGraph', wmlObject, {
       animate: animateFlg,
       animateReplot: animateFlg,
       seriesColors:graphColor,
       legend: {
         show: true,
         location: 'nw',
         renderer: jQuery . jqplot . EnhancedLegendRenderer
       },

       highlighter: {
         show: true,
         showMarker: false,
         sizeAdjust: 0,
         tooltipLocation: 'n',
         tooltipAxes: 'y',
         formatString: '%s'
       },

       series:wmlSeries,
       axes: {
         xaxis: {
           renderer: $.jqplot.CategoryAxisRenderer,
           ticks: tick,
           label: '',
           tickRenderer: jQuery . jqplot . CanvasAxisTickRenderer ,
           tickOptions: {angle:ticksOpsAngle,fontSize:ticksOpsSize}
         },
         yaxis: {
           label: '',
           min:0
         }
       }
    });
}