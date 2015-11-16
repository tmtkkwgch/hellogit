$(function() {

    /* 目標ポップアップ */
    $(".targetPopLink").live("click", function(){

        var yearDataId = $(this).parent().attr('id');
        $('#loadingArea').append("<div style=\"padding-top:220px;height:100%;width:100%;text-align:center;\"><img src=\"../nippou/images/ajax-loader.gif\" /></div>");

        //年間目標ポップアップ
        $('#targetPop').dialog({
            autoOpen: true,  // hide dialog
            bgiframe: true,  // for IE6
            resizable: false,
            height: 570,
            width: 850,
            modal: true,
            overlay: {
              backgroundColor: '#000000',
              opacity: 0.5
            },
            buttons: {
              閉じる: function() {
                  graphReset();
                  $(this).dialog('close');
              }
            }
        });

        //目標年間データ取得
        $.ajaxSetup({async:false});
        $.post('../nippou/ntp240.do', {"cmd":"yearData",
                                       "CMD":"yearData",
                                       "yearDataId":yearDataId},
          function(data) {
            if (data != null || data != "") {
                setPopData(data);
            }
        });

        buttonInit();
        $('#loadingArea').children().remove();

    });

    /* 目標達成ポップアップ  今年度ボタン*/
    $("#popThisYearBtn").live("click", function(){

        var selYear   = $('#popHideYear').val();
        var selMonth  = $('#popHideMonth').val();
        var selUsrSid = $('#popHideUsrSid').val();
        var selNtgSid = $('#popHideNtgSid').val();
        var selYearDataId = selYear + "_" + selMonth + "_" + selUsrSid + "_" + selNtgSid;

        $.post('../nippou/ntp240.do', {"cmd":"yearDataNow",
                                       "CMD":"yearDataNow",
                                       "yearDataId":selYearDataId},
        function(data) {
            if (data != null || data != "") {
                graphReset();
                setPopData(data);
            }
        });
        buttonInit();
    });

    /* 目標達成ポップアップ  次年度ボタン*/
    $("#popNextBtn").live("click", function(){

        var selYear   = $('#popHideYear').val();
        var selMonth  = $('#popHideMonth').val();
        var selUsrSid = $('#popHideUsrSid').val();
        var selNtgSid = $('#popHideNtgSid').val();
        var selYearDataId = (parseInt(selYear) + 1) + "_" + selMonth + "_" + selUsrSid + "_" + selNtgSid;

        $.post('../nippou/ntp240.do', {"cmd":"yearData",
                                       "CMD":"yearData",
                                       "yearDataId":selYearDataId},
        function(data) {
            if (data != null || data != "") {
                graphReset();
                setPopData(data);
            }
        });
        buttonInit();
    });

    /* 目標達成ポップアップ  前年度ボタン*/
    $("#popPrevBtn").live("click", function(){

        var selYear       = $('#popHideYear').val();
        var selMonth      = $('#popHideMonth').val();
        var selUsrSid     = $('#popHideUsrSid').val();
        var selNtgSid     = $('#popHideNtgSid').val();
        var selYearDataId = (parseInt(selYear) - 1) + "_" + selMonth + "_" + selUsrSid + "_" + selNtgSid;

        $.post('../nippou/ntp240.do', {"cmd":"yearData",
                                       "CMD":"yearData",
                                       "yearDataId":selYearDataId},
        function(data) {
            if (data != null || data != "") {
                graphReset();
                setPopData(data);
            }
        });
        buttonInit();
    });

    /* 変更ボタンクリック */
    $(".target_settei_btn").live("click", function(){
        $('.target_' + $(this).attr('id')).toggle();
    });

    /* 確定ボタンクリック */
    $(".target_kakutei_btn").live("click", function(){

        var targetId = $(this).attr('id');

        $('#dialogEditOk').dialog({
            autoOpen: true,
            bgiframe: true,
            resizable: false,
            height: 160,
            modal: true,
            closeOnEscape: false,
            overlay: {
                backgroundColor: '#000000',
                opacity: 0.5
            },
            buttons: {
                OK: function() {

                    var recordVal = $('#trgRecord_' + targetId).val();
                    var targetVal = $('#trgTarget_' + targetId).val();

                    //データ送信
                    $.ajaxSetup({async:false});
                    $.post('../nippou/ntp240.do', {"cmd":"edit",
                                                   "CMD":"edit",
                                                   "targetId":targetId,
                                                   "recordVal":recordVal,
                                                   "targetVal":targetVal},
                      function(data) {
                        if (data != null || data != "") {

                            if (data.length > 0) {

                                //エラー
                                $("#error_msg").html("");

                                for (var d in data) {
                                    $("#error_msg").append("<span style=\"color:#ff0000;\"\">" + data[d] + "</span><br>");
                                }

                                //エラーメッセージ
                                $('#dialog_error').dialog({
                                    autoOpen: true,
                                    bgiframe: true,
                                    resizable: false,
                                    width:500,
                                    modal: true,
                                    closeOnEscape: false,
                                    overlay: {
                                        backgroundColor: '#000000',
                                        opacity: 0.5
                                    },
                                    buttons: {
                                        OK: function() {
                                            $(this).dialog('close');
                                        }
                                    }
                                });
                            } else {
                                //データ設定
                                resetData(targetId, data);
                            }

                        } else {
                            $('.target_' + $(this).attr('id')).toggle();
                        }
                    });

                    $(this).dialog('close');
                },
                Cancel: function() {

                  $(this).dialog('close');

                }
            }
        });

    });

    /* キャンセルボタンクリック */
    $(".target_cansel_btn").live("click", function(){

        var canselId = $(this).attr('id');

        //データ再設定
        $.ajaxSetup({async:false});
        $.post('../nippou/ntp240.do', {"cmd":"cansel",
                                       "CMD":"cansel",
                                       "canselId":canselId},
          function(data) {
            if (data != null || data != "") {
                //データ設定
                resetData(canselId, data);

            } else {

                $('.target_' + $(this).attr('id')).toggle();

            }
        });
    });


    /* 達成率ボタンクリック */
    $("#changeLineGraph").live("click", function(){
        $('#linechart').css('display','');
        $('#barchart').css('display','none');
        $("#changeLineGraph").removeClass('graph-btn-unactive');
        $("#changeLineGraph").addClass('graph-btn-active');
        $("#changeLineGraph").removeClass('graph-btn-unactive-hover');
        $("#changeBarGraph").removeClass('graph-btn-active');
        $("#changeBarGraph").addClass('graph-btn-unactive');
        $("#changeBarGraph").removeClass('graph-btn-unactive-hover');
        $('#line_title').css('display','');
        $('#bar_title').css('display','none');
    });

    /* 実績ボタンクリック */
    $("#changeBarGraph").live("click", function(){
        $('#barchart').css('display','');
        $('#linechart').css('display','none');
        $("#changeBarGraph").removeClass('graph-btn-unactive');
        $("#changeBarGraph").addClass('graph-btn-active');
        $("#changeBarGraph").removeClass('graph-btn-unactive-hover');
        $("#changeLineGraph").removeClass('graph-btn-active');
        $("#changeLineGraph").addClass('graph-btn-unactive');
        $("#changeLineGraph").removeClass('graph-btn-unactive-hover');
        $('#bar_title').css('display','');
        $('#line_title').css('display','none');
    });

    /* グラフボタン hover */
    $('.graph-btn-unactive').live({
          mouseenter:function () {
            $(this).addClass("graph-btn-unactive-hover");
          },
          mouseleave:function () {
              $(this).removeClass("graph-btn-unactive-hover");
          }
    });
});

function changeGroupCombo(){
    document.forms[0].cmd.value='';
    document.forms[0].CMD.value='';
    document.forms[0].submit();
    return false;
}


function changeCmb(){
    document.forms[0].submit();
    return false;
}

function nextMonth(){
    document.forms[0].cmd.value='nextmonth';
    document.forms[0].CMD.value='nextmonth';
    document.forms[0].submit();
    return false;
}

function prevMonth(){
    document.forms[0].cmd.value='prevmonth';
    document.forms[0].CMD.value='prevmonth';
    document.forms[0].submit();
    return false;
}

function thisMonth(){
    document.forms[0].cmd.value='thismonth';
    document.forms[0].CMD.value='thismonth';
    document.forms[0].submit();
    return false;
}

function resetData(id, data){
    $('#spanRecord_' + id).html("");
    $('#spanRecord_' + id).append(data.npgRecord);
    $('#trgRecord_'  + id).val(data.npgRecord);
    $('#spanTarget_' + id).html("");
    $('#spanTarget_' + id).append(data.npgTarget);
    $('#trgTarget_'  + id).val(data.npgTarget);
    $('.target_'     + id).toggle();

    if (data.npgTargetKbn == 0) {
        $('#spanRecord_' + id).removeClass("record_comp");
        $('#barTargetRatio_' + id).addClass('target_bar_graph');
        $('#barTargetRatio_' + id).removeClass('target_bar_graph2');
    } else {
        $('#spanRecord_' + id).addClass("record_comp");
        $('#barTargetRatio_' + id).removeClass('target_bar_graph');
        $('#barTargetRatio_' + id).addClass('target_bar_graph2');
    }

    //棒グラフ
    $('#ratioStr_' + id).html("");
    $('#ratioStr_' + id).html(data.npgTargetRatioStr + "%");
    $('#barTargetRatio_' + id).css('width', data.npgTargetRatio + '%');
    $('#barTargetUnRatio_' + id).css('width', data.npgTargetUnRatio + '%');

    return false;
}

//目標達成ポップアップの設定
function setPopData(data){

    //年
    $('#popTrgYear').html("");
    $('#popTrgYear').append(data.year);

    //ユーザ名
    $('#popTrgUsr').html("");
    $('#popTrgUsr').append(data.usrName);

    //目標名
    $('#popTrgTarget').html("");
    $('#popTrgTarget').append(data.targetName);

    //年平均
    $('#popTrgRatio').html("");
    $('#popTrgRatio').append(data.targetRatio);

    //単位
    $('#bar_unit').html("");
    $('#bar_unit').html(data.targetUnit);

    var monthArray = [];
    var targetArray = [];
    var recordArray = [];
    var recordKbnArray = [];
    var ratioArray = [];

    //データ格納
    var priData = data.ntgList;
    if (priData.length > 0) {
        for (var p in priData) {
            monthArray.push(priData[p].npgMonth);
            targetArray.push(priData[p].npgTarget);
            recordArray.push(priData[p].npgRecord);
            recordKbnArray.push(priData[p].npgTargetKbn);
            ratioArray.push(priData[p].npgTargetRatio);
        }
    }

    //hiddenデータ設定
    $('#popHideYear').val(data.year);
    $('#popHideMonth').val(data.month);
    $('#popHideUsrSid').val(data.usrSid);
    $('#popHideNtgSid').val(data.targetSid);

    //月データ成形
    var tdMonthData = "";

    for (var m in monthArray) {
        tdMonthData += "<td align=\"center\" class=\"table_bg_7D91BD\"><span class=\"text_tlw\">"
                    +  monthArray[m]
                    +  "月</span></td>";
    }



    //目標データ成形
    var tdTargetData = "";
    for (var t in targetArray) {
        tdTargetData += "<td align=\"center\">"
                     +  targetArray[t]
                     +  "</td>";
    }

    //実績データ成形
    var tdRecordData = "";
    var recordClass = "";
    for (var r in recordArray) {
        recordClass = "";
        if (recordKbnArray[r] == 1) {
            recordClass = "record_comp";
        }

        tdRecordData += "<td class=\"" + recordClass + "\" align=\"center\"><b>"
                     +  recordArray[r]
                     +  "</b></td>";
    }

    //達成率データ成形
    var tdRatioData = "";
    for (var r in ratioArray) {
        tdRatioData += "<td align=\"center\">"
                    +  ratioArray[r]
                    +  "</td>";
    }

    var yearTrgStr = "";


    yearTrgStr = "<div style=\"overflow-x:scroll;width:815px;\" id=\"yearTargetDataArea\"><table class=\"tl0 table_td_border2\" style=\"border-collapse:collapse;\" width=\"100%\">"
               + "<tbody>"
               +  "<tr>"
               +    "<td align=\"center\" class=\"table_bg_7D91BD\"><span class=\"text_tlw\"></span></td>"
               +      tdMonthData
               +  "</tr>"
               +  "<tr>"
               +    "<td align=\"center\" class=\"table_bg_7D91BD\" nowrap><span class=\"text_tlw\">目標</span></td>"
               +     tdTargetData
               +  "</tr>"
               +  "<tr>"
               +    "<td align=\"center\" class=\"table_bg_7D91BD\" nowrap><span class=\"text_tlw\">実績</span></td>"
               +     tdRecordData
               +  "</tr>"
               +  "<tr>"
               +    "<td align=\"center\" class=\"table_bg_7D91BD\" nowrap><span class=\"text_tlw\">達成率(%)</span></td>"
               +     tdRatioData
               +  "</tr>"
               + "</tbody>"
               + "</table></div>";

    $('#yearTargetDataArea').remove();
    $('#yearTargetArea').append(yearTrgStr);


    //グラフ描画(折れ線グラフ)
    var month1  = monthArray[0]  + "月";
    var month2  = monthArray[1]  + "月";
    var month3  = monthArray[2]  + "月";
    var month4  = monthArray[3]  + "月";
    var month5  = monthArray[4]  + "月";
    var month6  = monthArray[5]  + "月";
    var month7  = monthArray[6]  + "月";
    var month8  = monthArray[7]  + "月";
    var month9  = monthArray[8]  + "月";
    var month10 = monthArray[9]  + "月";
    var month11 = monthArray[10] + "月";
    var month12 = monthArray[11] + "月";

    $.jqplot('linechart', [
            [[1, ratioArray[0]],
             [2, ratioArray[1]],
             [3, ratioArray[2]],
             [4, ratioArray[3]],
             [5, ratioArray[4]],
             [6, ratioArray[5]],
             [7, ratioArray[6]],
             [8, ratioArray[7]],
             [9, ratioArray[8]],
             [10,ratioArray[9]],
             [11,ratioArray[10]],
             [12,ratioArray[11]]]
            ],
            {
            axes:{
                // X軸
                xaxis:{
                label: "",
                ticks: [[0,  ''],
                    [1,  month1],
                    [2,  month2],
                    [3,  month3],
                    [4,  month4],
                    [5,  month5],
                    [6,  month6],
                    [7,  month7],
                    [8,  month8],
                    [9,  month9],
                    [10, month10],
                    [11, month11],
                    [12, month12],
                    [13, '']]
            },
                // Y軸
                yaxis:{
                label: ""
                }
            },
                highlighter: {
                    // マウスオーバー時の数値表示
                    show: true,
                    // Y軸の値のみ
                    tooltipAxes: 'y'
                }
            }
    );




    $.jqplot('barchart', [
                           [[1, recordArray[0]],
                            [2, recordArray[1]],
                            [3, recordArray[2]],
                            [4, recordArray[3]],
                            [5, recordArray[4]],
                            [6, recordArray[5]],
                            [7, recordArray[6]],
                            [8, recordArray[7]],
                            [9, recordArray[8]],
                            [10,recordArray[9]],
                            [11,recordArray[10]],
                            [12,recordArray[11]]]
                           ],
                           {

                       seriesDefaults: {
                       renderer: jQuery.jqplot.BarRenderer,
                       rendererOptions: {
                           barPadding: 8,
                           barMargin: 10,
                           barWidth: 25,
                           shadowOffset: 2,
                           shadowDepth: 5,
                           shadowAlpha: 0.08
                       }
                   },
                           axes:{
                               // X軸
                               xaxis:{
                               label: "",
                               ticks: [[0,  ''],
                                   [1,  month1],
                                   [2,  month2],
                                   [3,  month3],
                                   [4,  month4],
                                   [5,  month5],
                                   [6,  month6],
                                   [7,  month7],
                                   [8,  month8],
                                   [9,  month9],
                                   [10, month10],
                                   [11, month11],
                                   [12, month12],
                                   [13, '']]
                           },
                               // Y軸
                               yaxis:{
                               label: ""
                               }
                           },
                               highlighter: {
                                   // マウスオーバー時の数値表示
                                   show: true,
                                   // Y軸の値のみ
                                   tooltipAxes: 'y'
                               }
                           }
                   );



}

//グラフクリア
function graphReset(){
  $('#graph_area').html("");
  $('#graph_area').html("<div id=\"linechart\" class=\"targetLineGraphArea\" style=\"z-index:500;width:650px;height:200px;\"></div><div id=\"barchart\" class=\"targetBarGraphArea\" style=\"z-index:200;width:650px;height:200px;\"></div>");
}

//ボタン初期設定
function buttonInit() {
    $('#linechart').css('display','');
    $('#barchart').css('display','none');
    $("#changeLineGraph").removeClass('graph-btn-unactive');
    $("#changeLineGraph").addClass('graph-btn-active');
    $("#changeBarGraph").removeClass('graph-btn-active');
    $("#changeBarGraph").addClass('graph-btn-unactive');
    $('#line_title').css('display','');
    $('#bar_title').css('display','none');
}