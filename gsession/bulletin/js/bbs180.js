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
    var currItem = $('input:hidden[name=bbs180GraphItem]').val();
    $('#' + currItem).addClass('text_bg_index');

    /*統計情報画面選択メニュー*/
    var adminFlg = $('input:hidden[name=bbs180GsAdminFlg]').val();
    var wmlCtrlFlg = $('input:hidden[name=bbs180CtrlFlgWml]').val();
    var smlCtrlFlg = $('input:hidden[name=bbs180CtrlFlgSml]').val();
    var cirCtrlFlg = $('input:hidden[name=bbs180CtrlFlgCir]').val();
    var filCtrlFlg = $('input:hidden[name=bbs180CtrlFlgFil]').val();
    if (adminFlg == 'false' && wmlCtrlFlg == 'false'
        && smlCtrlFlg == 'false' && cirCtrlFlg == 'false'
          && filCtrlFlg == 'false') {
      $('.menu_select_text').addClass('menu_select_text_no_top');
      $('.menu_select_text').removeClass('menu_select_text');
      $('.menu_select_icon').addClass('menu_select_icon_no_top');
      $('.menu_select_icon').removeClass('menu_select_icon');
    }

    /*月週日切り替え*/
    $('input[name=bbs180DateUnit]:checked').attr('onclick','').unbind('click');

/*
      メニュー格納
     $('#sel_menu_wrapper').addClass('display_none');
     $('#menu_length_area').addClass("menu_length_border_none");*/
});


//日付変更
function changeGraphDate(frdate, todate) {
  var dateUnit = $('input[name=bbs180DateUnit]:checked').val();
  if (dateUnit == 1) {
    $("input[name=bbs180DateWeeklyFrStr]").val(frdate);
    $("input[name=bbs180DateWeeklyToStr]").val(todate);
  } else if (dateUnit == 0) {
    $("input[name=bbs180DateDailyFrStr]").val(frdate);
    $("input[name=bbs180DateDailyToStr]").val(todate);
  }
    document.forms[0].CMD.value='pageDate';
    document.forms[0].submit();
    return false;
}

//年月コンボ変更
function changeYearMonthCombo(flg) {

  var frYear = Number(getDateComboValue('bbs180DateMonthlyFrYear'));
  var frMonth = Number(getDateComboValue('bbs180DateMonthlyFrMonth'));
  var toYear = Number(getDateComboValue('bbs180DateMonthlyToYear'));
  var toMonth = Number(getDateComboValue('bbs180DateMonthlyToMonth'));

  if (frYear > toYear || ((frYear == toYear && frMonth > toMonth))) {
    if (flg == 'from') {
      $('select[name=bbs180DateMonthlyToYear]').val(getDateComboValue('bbs180DateMonthlyFrYear'));
      $('select[name=bbs180DateMonthlyToMonth]').val(getDateComboValue('bbs180DateMonthlyFrMonth'));
    } else {
      $('select[name=bbs180DateMonthlyFrYear]').val(getDateComboValue('bbs180DateMonthlyToYear'));
      $('select[name=bbs180DateMonthlyFrMonth]').val(getDateComboValue('bbs180DateMonthlyToMonth'));
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

  var frYear = Number(getDateComboValue('bbs180DateWeeklyFrYear'));
  var frMonth = Number(getDateComboValue('bbs180DateWeeklyFrMonth'));
  var frWeek = Number(getDateComboValue('bbs180DateWeeklyFrWeek'));
  var toYear = Number(getDateComboValue('bbs180DateWeeklyToYear'));
  var toMonth = Number(getDateComboValue('bbs180DateWeeklyToMonth'));
  var toWeek = Number(getDateComboValue('bbs180DateWeeklyToWeek'));

  if (frYear > toYear
      || (frYear == toYear && frMonth > toMonth)
      || (frMonth == toMonth && frWeek > toWeek)) {
    if (flg == 'from') {
      $('select[name=bbs180DateWeeklyToYear]').val(getDateComboValue('bbs180DateWeeklyFrYear'));
      $('select[name=bbs180DateWeeklyToMonth]').val(getDateComboValue('bbs180DateWeeklyFrMonth'));
      $('select[name=bbs180DateWeeklyToWeek]').val(getDateComboValue('bbs180DateWeeklyFrWeek'));
    } else {
      $('select[name=bbs180DateWeeklyFrYear]').val(getDateComboValue('bbs180DateWeeklyToYear'));
      $('select[name=bbs180DateWeeklyFrMonth]').val(getDateComboValue('bbs180DateWeeklyToMonth'));
      $('select[name=bbs180DateWeeklyFrWeek]').val(getDateComboValue('bbs180DateWeeklyToWeek'));
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
    document.forms[0].bbs180NowPage.value=cmbObj.options[cmbObj.selectedIndex].value;
    document.forms[0].CMD.value='pageChange';
    document.forms[0].submit();
}

//表示項目変更
function changeDspItem(nextItem) {
    var currItem = $('input:hidden[name=bbs180GraphItem]').val();
    if (currItem != nextItem) {
        $('#' + currItem).removeClass('text_bg_index');
        $('#' + nextItem).addClass('text_bg_index');
        document.forms[0].bbs180GraphItem.value=nextItem;
        drawTotalizationGraph();
    }
}

//集計グラフ
function drawTotalizationGraph() {

    var tmp = document.getElementById('bbsCntGraph');
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
    var jsonVbbsData = $('input:hidden[name=jsonVbbsData]').val();
    var jsonWbbsData = $('input:hidden[name=jsonWbbsData]').val();

    var vbbsObject = eval(jsonVbbsData);
    var wbbsObject = eval(jsonWbbsData);
    var tick = eval(jsonDateData);

    var dayAddFlg = 0;
    var ticksOpsAngle = 0;
    if (tick.length > 8) {
      ticksOpsAngle = -30;
    }
    var ticksOpsSize = '7pt';

    var graphItem = $('input:hidden[name=bbs180GraphItem]').val();
    var bbsObject = null;
    var graphLabel = null;
    var graphColor = null;
    if (graphItem == 'bbs_graph_write') {
      bbsObject = [wbbsObject];
      graphLabel = msglist["numPost"];
      graphColor = ['#eaa228'];
    } else {
      bbsObject = [vbbsObject];
      graphLabel = msglist["numView"];
      graphColor = ['#4bb2c5'];
    }

    var bbsSeries = [];
    for (var i=0; i<bbsObject.length; i++) {
      var srsElm = {label:graphLabel, yaxis:'yaxis', rendererOptions: {animation: {speed:1000}}};
      bbsSeries.push(srsElm);
    }

    var ankenPlot = $.jqplot('bbsCntGraph', bbsObject, {
       animate: animateFlg,
       animateReplot: animateFlg,
       seriesColors:graphColor,
       legend: {
         show: true,
         location: 'nw',
         renderer: jQuery . jqplot . EnhancedLegendRenderer,
         rendererOptions:{ numberColumns: 3}
       },

       highlighter: {
         show: true,
         showMarker: false,
         sizeAdjust: 0,
         tooltipLocation: 'n',
         tooltipAxes: 'y',
         formatString: '%s'
       },

       series:bbsSeries,
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