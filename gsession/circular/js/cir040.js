/* スクロール時宛先表示件数  値変更字circular.css atesaki_scroll_area_heightのheightも調整*/
var DSP_SCROLL_NUM = 10;
/* 宛先 表示区分 0:スクロール表示 1:全て表示*/
var dsp_to_kbn = 0;

function moveDay(elmYear, elmMonth, elmDay, kbn) {

    systemDate = new Date();

    //「今日ボタン押下」
    if (kbn == 2) {
        $(elmYear).val(convYear(systemDate.getYear()));
        $(elmMonth).val(systemDate.getMonth() + 1);
        $(elmDay).val(systemDate.getDate());
        return;
    }

    //「前日」or 「翌日」ボタン押下
    if (kbn == 1 || kbn == 3) {

        var ymdf = escape(elmYear.value + '/' + elmMonth.value + "/" + elmDay.value);
        re = new RegExp(/(\d{4})\/(\d{1,2})\/(\d{1,2})/);
        if (ymdf.match(re)) {

            newDate = new Date(elmYear.value, elmMonth.value - 1, elmDay.value)

            if (kbn == 1) {
                newDate.setDate(newDate.getDate() - 1);
            } else if (kbn == 3) {
                newDate.setDate(newDate.getDate() + 1);
            }

            var newYear = convYear(newDate.getYear());
            var systemYear = convYear(systemDate.getYear());

            if (newYear < systemYear - 5 || newYear > systemYear + 5) {
                $(elmYear).val('');
            } else {
                $(elmYear).val(newYear);
            }

            $(elmMonth).val(newDate.getMonth() + 1);
            $(elmDay).val(newDate.getDate());

        } else {

            if (elmYear.value == '' && elmMonth.value == '' && elmDay.value == '') {
                $(elmYear).val(convYear(systemDate.getYear()));
                $(elmMonth).val(systemDate.getMonth() + 1);
                $(elmDay).val(systemDate.getDate());
            }
        }
    }
}
function clickPeriod(cmd, pValue) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].cir040memoPeriod.value=pValue;
    document.forms[0].submit();
    return false;
}

function fileLinkClick(binSid) {
    document.forms[0].CMD.value='fileDownload';
    document.forms[0].cir040knBinSid.value=binSid;
    document.forms[0].submit();
    return false;
}










$(function() {

    //宛先選択ボタン
    $(".cir_send_sel_btn").live("click", function(){

        //選択ボタン名
        var functinBtnName = $(this).attr('id');
        var paramStr = "CMD=getInitData";

        paramStr += getNowSelUsr();


        //宛先一覧取得
        getSelAtesakiData(paramStr, functinBtnName, 0);

        /* テンプレートポップアップ */
        $('#atesakiSelPop').dialog({
            autoOpen: true,  // hide dialog
            bgiframe: true,   // for IE6
            resizable: false,
            height: 560,
            width: 750,
            modal: true,
            overlay: {
              backgroundColor: '#000000',
              opacity: 0.5
            },
            buttons: {
              閉じる: function() {
                  $(this).dialog('close');
              }
            }
        });
    });


    //ユーザ選択  グループコンボ変更
    $("#fakeButton").live("click", function(){
        var paramStr = "CMD=changeGrpData&";
        paramStr += getFormData($('#atesakiSelForm'));
        getSelAtesakiData(paramStr, $('#funcBtnName').val(), $('#funcBtnKbn').val());
    });

    //ユーザ選択  グループコンボ変更
    $("#cmn120ChangeGrp").live("change", function(){
        var paramStr = "CMD=changeGrpData&";
        paramStr += getFormData($('#atesakiSelForm'));
        getSelAtesakiData(paramStr, $('#funcBtnName').val(), $('#funcBtnKbn').val());
    });

    //ユーザ選択  ユーザ追加
    $("#adduserBtn").live("click", function(){
        var paramStr = "CMD=addUserData&";
        paramStr += getFormData($('#atesakiSelForm'));
        getSelAtesakiData(paramStr, $('#funcBtnName').val(), $('#funcBtnKbn').val());
    });

    //ユーザ選択  ユーザ削除
    $("#deluserBtn").live("click", function(){
        var paramStr = "CMD=removeUserData&";
        paramStr += getFormData($('#atesakiSelForm'));
        getSelAtesakiData(paramStr, $('#funcBtnName').val(), $('#funcBtnKbn').val());
    });


    //ユーザ選択  ユーザ削除
    $("#myGrpAddBtn").live("click", function(){
        var paramStr = "CMD=addMyGrpUserData&";
        paramStr += getFormData($('#atesakiSelForm'));
        getSelAtesakiData(paramStr, $('#funcBtnName').val(), $('#funcBtnKbn').val());
    });

    //ユーザ選択  再読み込み
    $("#fakeButton2").live("click", function(){
        var paramStr = "CMD=getInitData&";
        paramStr += getFormData($('#atesakiSelForm'));
        getSelAtesakiData(paramStr, $('#funcBtnName').val(), $('#funcBtnKbn').val());
    });

    //ユーザ選択  選択ボタンクリック
    $("#sel_usr_pop_btn").live("click", function(){
        drawPopUsr();
    });

    //選択ユーザ  削除
    $(".add_usr_del").live("click", function(){
        $(this).parent().remove();

        if ($(this).parent().attr("class") == "atesaki_to_user") {
            if (DSP_SCROLL_NUM < $("#atesaki_to_area div").length) {
                $('#alldsp_to_area').show();
            } else {
                $('#alldsp_to_area').hide();

                $('#atesaki_area').removeClass('atesaki_scroll_on');
                $('#atesaki_area').removeClass('atesaki_scroll_area_height');
            }
        }
    });

    /* ユーザ選択   グループ区分  hover*/
    jQuery('.sel_group_notcheck').live({
        mouseenter:function(){
          $(this).addClass("sel_group_notcheck_on");
        },
        mouseleave:function(){
          $(this).removeClass("sel_group_notcheck_on");
        }
    });

    /* ユーザ選択  グループ区分  変更*/
    $('.sel_group_check').live("click", function(){

        var grpKbn = $(this).attr('id');

        if (grpKbn == "groupTab") {
            $('#cmn120MyGroupSidSel').addClass("display_none");
            $('#cmn120ChangeGrpBtn').removeClass("display_none");
            $('#cmn120ChangeGrpBtn2').removeClass("display_none");
            $('#cmn120ChangeGrp').removeClass("display_none");
            $(this).removeClass("sel_group_notcheck_on");
            $(this).removeClass("sel_group_notcheck");
            $('#mygroupTab').addClass("sel_group_notcheck");
            $('#deptAccountTab').addClass("sel_group_notcheck");
            $('#cmn120DspKbn').val(1);
        } else if  (grpKbn == "mygroupTab") {
            $('#cmn120MyGroupSidSel').removeClass("display_none");
            $('#cmn120ChangeGrpBtn').addClass("display_none");
            $('#cmn120ChangeGrpBtn2').addClass("display_none");
            $('#cmn120ChangeGrp').addClass("display_none");
            $(this).removeClass("sel_group_notcheck_on");
            $(this).removeClass("sel_group_notcheck");
            $('#groupTab').addClass("sel_group_notcheck");
            $('#deptAccountTab').addClass("sel_group_notcheck");
            $('#cmn120DspKbn').val(2);
        } else if  (grpKbn == "deptAccountTab") {
            $('#cmn120MyGroupSidSel').addClass("display_none");
            $('#cmn120ChangeGrpBtn').addClass("display_none");
            $('#cmn120ChangeGrpBtn2').addClass("display_none");
            $('#cmn120ChangeGrp').addClass("display_none");
            $(this).removeClass("sel_group_notcheck_on");
            $(this).removeClass("sel_group_notcheck");
            $('#groupTab').addClass("sel_group_notcheck");
            $('#mygroupTab').addClass("sel_group_notcheck");
            $('#cmn120DspKbn').val(4);
        }

        var paramStr = "CMD=changeTabData&";
        paramStr += getFormData($('#atesakiSelForm'));
        getSelAtesakiData(paramStr, $('#funcBtnName').val(), $('#funcBtnKbn').val());

    });

    //宛先 全て表示・スクロール表示をクリック
    $('#all_dsp_to_link').live("click", function(){

        //スクロール表示だった場合
        if (dsp_to_kbn == 0) {
            $('#all_dsp_to_link').html('閉じる');
            $('#atesaki_area').removeClass('atesaki_scroll_on');
            $('#atesaki_area').removeClass('atesaki_scroll_area_height');
            dsp_to_kbn = 1;
        } else {
            $('#all_dsp_to_link').html('全て表示');
            $('#atesaki_area').addClass('atesaki_scroll_on');
            $('#atesaki_area').addClass('atesaki_scroll_area_height');
            dsp_to_kbn = 0;
        }
    });


    dispAllDspToLink();

});

function dispAllDspToLink() {
    if (DSP_SCROLL_NUM < $('#atesaki_to_area div').length) {
        $('#alldsp_to_area').show();
        if (dsp_to_kbn == 1) {
            $('#all_dsp_to_link').html('閉じる');
        } else {
            $('#all_dsp_to_link').html('全て表示');
            $('#atesaki_area').addClass('atesaki_scroll_on');
            $('#atesaki_area').addClass('atesaki_scroll_area_height');

            //IE9以下対応
            //宛先がある一定数あると見た目上appendしていかない不具合対策
            //クリックイベントを２回発生させて宛先のスクロールの開閉を実行する
            var clickMe = document.getElementById('all_dsp_to_link');
            clickMe.click();
            clickMe.click();
        }
    } else {
        $('#alldsp_to_area').hide();
        if (dsp_to_kbn == 1) {
            $('#all_dsp_to_link').html('閉じる');
        } else {
            $('#all_dsp_to_link').html('全て表示');
        }
    }
}

function getNowSelUsr() {

    var paramStr = "";

    $('input:hidden[name=cir040userSid]').each(function(){
        paramStr += "&cmn120userSid=" + $(this).val();
    });

    return paramStr;

}

function getSelAtesakiData(paramStr, functionName, functionKbn) {

    paramStr += "&cmn120BackUrl=circular"

    $.ajax({
        async: true,
        url:"../common/cmn120.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {

        var atesakiSelStr = "";

        try {
            atesakiSelStr += "<form id=\"atesakiSelForm\" name=\"atesakiSelForm\">";

            atesakiSelStr += "<input type=\"button\" class=\"display_none\" id=\"fakeButton\" name=\"fakeButton\" />";
            atesakiSelStr += "<input type=\"button\" class=\"display_none\" id=\"fakeButton2\" name=\"fakebutton2\" />";

            if (data.cmn120userSid != null && data.cmn120userSid != "") {
                for (i = 0; i < data.cmn120userSid.length; i++) {
                    atesakiSelStr += "<input type=\"hidden\" name=\"cmn120userSid\" value=\""
                                  +   data.cmn120userSid[i] + "\">";
                }
            }

            if (data.cmn120userSidOld != null && data.cmn120userSidOld != "") {
                for (i = 0; i < data.cmn120userSidOld.length; i++) {
                    atesakiSelStr += "<input type=\"hidden\" name=\"cmn120userSidOld\" value=\""
                                  +   data.cmn120userSidOld[i] + "\">";
                }
            }

            if (data.cmn120paramName != null && data.cmn120paramName != "") {
                if (data.cmn120userSid != null && data.cmn120userSid.length > 0) {
                    for (i = 0; i < data.cmn120userSid.length; i++) {

                        atesakiSelStr += "<input type=\"hidden\" name=\""
                                      +  data.cmn120paramName
                                      + "/>\" value=\""
                                      +  data.cmn120userSid[i]
                                      +  "/>\">";
                    }
                }
            }


            atesakiSelStr += "<input type=\"hidden\" id=\"funcBtnName\" value=\""
                          +  functionName
                          +  "\" />"
                          +  "<input type=\"hidden\" id=\"funcBtnKbn\" value=\""
                          +  functionKbn
                          +  "\" />"
                          +  "<input type=\"hidden\" id=\"cmn120DspKbn\" name=\"cmn120DspKbn\" value=\""
                          +  data.cmn120DspKbn
                          +  "\" />"
                          +  "<table width=\"100%\" border=\"0\">"
                          +  "<tr>"
                          +  "<td width=\"100%\" align=\"center\">"
                          +  "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"650px\">"
                          +  "<tr>"
                          +  "<td>"
                          +  "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">"
                          +  "<tr>"
                          +  "<td width=\"50%\">"
                          + "</td>"
                          +  "<td width=\"0%\"></td>"
                          +  "<td class=\"\" align=\"right\" width=\"50%\">"
                          +  "<input type=\"button\" id=\"sel_usr_pop_btn\" value=\"選択\" class=\"btn_base1\">"
                          +  "<td width=\"0%\"></td>"
                          +  "</tr>"
                          +  "</table>"
                          +  "<img src=\"../common/images/spacer.gif\" alt=\"\" height=\"10px\" width=\"10px\">"
                          +  "<table style=\"padding:0px;margin:0px;\" width=\"100%\">"
                          +  "<tr>"
                          +  "<td nowrap align=\"left\" class=\"sel_group_space\" width=\"350px\"></td>"
                          +  "<td nowrap id=\"groupTab\" class=\"group_select_state sel_group_check\"><span>グループ</span></td>"
                          +  "<td nowrap id=\"mygroupTab\" class=\"sel_group_check sel_group_notcheck\"><span>マイグループ</span></td>"
                          +  "<td nowrap id=\"deptAccountTab\" class=\"sel_group_check sel_group_notcheck\"><span>代表アカウント</span></td>"
                          +  "<td nowrap align=\"left\" class=\"sel_group_space\"></td>"
                          +  "</tr>"
                          +  "</table>"
                          +  "<table width=\"100%\" class=\"\" border=\"0\" cellpadding=\"5\">"
                          +  "<tr>"
                          +  "<td align=\"left\" class=\"td_smail_wt\">"
                          +  "<table width=\"0%\" border=\"0\">"
                          +  "<tr>"
                          +  "<td width=\"35%\" class=\"table_bg_A5B4E1\" align=\"center\"><span class=\"text_bb1\">" + functionName + "</span></td>"
                          +  "<td width=\"20%\" align=\"center\">&nbsp;</td>"
                          +  "<td width=\"35%\" align=\"left\">"
                          +  "<input id=\"cmn120ChangeGrpBtn\" class=\"btn_group_n1\" onclick=\"return openAllGroup_no_submit(this.form.cmn120groupSid, 'cmn120groupSid', '-1', '0', 'changeGrp', 'cmn120userSid', '-1', '0', '0', '0', '0', 'atesakiSelForm', 'fakeButton2')\" value=\"全グループから選択\"  type=\"button\"><br>";


            //グループ
            var grpSelStr = "";
            if (data.cmn120DspKbn == 0 || data.cmn120DspKbn == 1) {
                grpSelStr += "<select name=\"cmn120groupSid\" id=\"cmn120ChangeGrp\" class=\"select01\">";
                if (data.cmn120GroupList.length > 0) {
                    for (k = 0; k < data.cmn120GroupList.length; k++) {
                        var grpMdl = data.cmn120GroupList[k];

                        if (grpMdl.value != "cac") {
                            grpSelStr += "<option value=\""
                                +  grpMdl.value
                                +  "\">"
                                +  grpMdl.label
                                +  "</option>";
                        } else {
                            grpSelStr += "<option class=\"select03\" value=\""
                                +  grpMdl.value
                                +  "\">"
                                +  grpMdl.label
                                +  "</option>";
                        }

                    }
                }
                grpSelStr += "</select>";
                grpSelStr += "<input type=\"hidden\" name=\"cmn120MyGroupSid\" value=\""
                          +  data.cmn120MyGroupSid
                          +  "\" />";
            }


            //マイグループ
            var myGrpSelStr = "";
            if (data.cmn120DspKbn == 2) {
                if (data.cmn120GroupList.length > 0) {
                myGrpSelStr += "<select  id=\"cmn120ChangeGrp\" name=\"cmn120MyGroupSid\" class=\"select01\">";

                    for (j = 0; j < data.cmn120GroupList.length; j++) {
                        var myGrpMdl = data.cmn120GroupList[j];
                        myGrpSelStr += "<option value=\""
                                    +  myGrpMdl.value
                                    +  "\">"
                                    +  myGrpMdl.label
                                    +  "</option>";
                    }
                    myGrpSelStr += "</select>";
                } else {
                    myGrpSelStr += "<input id=\"cmn120ChangeGrp\" type=\"hidden\" name=\"cmn120MyGroupSid\" value=\""
                                +  data.cmn120MyGroupSid
                                +  "\" />";
                    myGrpSelStr += "<span class=\"text_r1\">マイグループが作成されていません。</span>";
                }
                myGrpSelStr += "<input type=\"hidden\" name=\"cmn120groupSid\" value=\""
                            +  data.cmn120groupSid
                            +  "\" />";
            }

            //代表アカウント
            var deptAccountSelStr = "";
            if (data.cmn120DspKbn == 4) {

                deptAccountSelStr += "<input type=\"hidden\" name=\"cmn120groupSid\" value=\""
                                  +  data.cmn120groupSid
                                  +  "\" />";
                deptAccountSelStr += "<input type=\"hidden\" name=\"cmn120MyGroupSid\" value=\""
                                  +  data.cmn120MyGroupSid
                                  +  "\" />";

                if (data.cmn120GroupList.length == 0) {
                    deptAccountSelStr += "<span class=\"text_r1\">代表アカウントが作成されていません。</span>";
                }
            }

            atesakiSelStr += myGrpSelStr;
            atesakiSelStr += grpSelStr;
            atesakiSelStr += deptAccountSelStr;

            atesakiSelStr += "</td>"
                          +  "<td width=\"10%\" align=\"center\" valign=\"bottom\">"
                          +  "<input id=\"cmn120ChangeGrpBtn2\" type=\"button\" onclick=\"openGroupWindow(this.form.cmn120groupSid, 'cmn120groupSid', '0', 'changeGrp', '1', 'fakeButton')\" class=\"group_btn2\" value=\"&nbsp;&nbsp;\" id=\"cmn120GroupBtn\">"
                          +  "</td>"
                          +  "</tr>"
                          +  "<tr>"
                          +  "<td>";


            //グループ ユーザ 右
            var selUsrRightStr = "";
            selUsrRightStr += "<select id=\"cmn120SelectRightUser\" name=\"cmn120SelectRightUser\" multiple=\"multiple\" size=\"15\" class=\"select01\">";
            if (data.cmn120RightUserList.length > 0) {

                for (l = 0; l < data.cmn120RightUserList.length; l++) {
                    var selGrpMdl = data.cmn120RightUserList[l];
                    selUsrRightStr += "<option value=\""
                                   +  selGrpMdl.value
                                   +  "\">"
                                   +  htmlEscape(selGrpMdl.label)
                                   +  "</option>";
                }
                selUsrRightStr += "<option value=\"-1\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>";
            }
            selUsrRightStr += "</select>";
            atesakiSelStr  += selUsrRightStr;


            atesakiSelStr += "</td>"
                          +  "<td align=\"center\">"
                          +  "<input type=\"button\" class=\"btn_base1add\" value=\"追加\" name=\"adduserBtn\" id=\"adduserBtn\">"
                          +  "<br><br>"
                          +  "<input type=\"button\" class=\"btn_base1del\" value=\"削除\" name=\"deluserBtn\" id=\"deluserBtn\">"
                          +  "<br>"
                          +  "</td>"
                          +  "<td align=\"center\">";


              //グループ ユーザ 左
              var selUsrLeftStr = "";
              selUsrLeftStr += "<select name=\"cmn120SelectLeftUser\" multiple=\"multiple\" size=\"15\" class=\"select01\">";
              if (data.cmn120LeftUserList.length > 0) {
                  for (m = 0; m < data.cmn120LeftUserList.length; m++) {
                      var selGrpLeftMdl = data.cmn120LeftUserList[m];
                      selUsrLeftStr += "<option value=\""
                                +  selGrpLeftMdl.value
                                +  "\">"
                                +  htmlEscape(selGrpLeftMdl.label)
                                +  "</option>";
                  }
                  selUsrLeftStr += "<option value=\"-1\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>";
              }

              selUsrLeftStr += "</select>";
              atesakiSelStr += selUsrLeftStr;

              atesakiSelStr += "</td>"
                            +  "</tr>"
                            +  "</table>"
                            +  "</td>"
                            +  "</tr>"
                            +  "</table>"
                            +  "<img src=\"../common/images/spacer.gif\" alt=\"\" height=\"10px\" width=\"10px\">"
                            +  "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\">"
                            +  "<tr>"
                            +  "<td align=\"right\">"
                            +  "<input type=\"button\" id=\"sel_usr_pop_btn\" value=\"選択\" class=\"btn_base1\">"
                            +  "</td>"
                            +  "</tr>"
                            +  "</table>"
                            +  "</td>"
                            +  "</tr>"
                            +  "</table>"
                            +  "</td>"
                            +  "</tr>"
                            +  "</table>"
                            +  "</form>";

             $("#atesakiSelArea").children().remove();
             $("#atesakiSelArea").append(atesakiSelStr);

             if (data.cmn120DspKbn == 0 || data.cmn120DspKbn == 1) {
                 $('#cmn120ChangeGrpBtn').removeClass("display_none");
                 $('#cmn120ChangeGrpBtn2').removeClass("display_none");
                 $('#groupTab').removeClass("sel_group_notcheck");
                 $('#mygroupTab').addClass("sel_group_notcheck");
                 $('#deptAccountTab').addClass("sel_group_notcheck");
                 $('#cmn120DspKbn').val(1);
             } else if (data.cmn120DspKbn == 2) {
                 $('#cmn120ChangeGrpBtn').addClass("display_none");
                 $('#cmn120ChangeGrpBtn2').addClass("display_none");
                 $('#mygroupTab').removeClass("sel_group_notcheck");
                 $('#groupTab').addClass("sel_group_notcheck");
                 $('#deptAccountTab').addClass("sel_group_notcheck");
                 $('#cmn120DspKbn').val(2);
             } else if (data.cmn120DspKbn == 4) {
                 $('#cmn120ChangeGrpBtn').addClass("display_none");
                 $('#cmn120ChangeGrpBtn2').addClass("display_none");
                 $('#deptAccountTab').removeClass("sel_group_notcheck");
                 $('#groupTab').addClass("sel_group_notcheck");
                 $('#mygroupTab').addClass("sel_group_notcheck");
                 $('#cmn120DspKbn').val(4);
             }

             //コンボ設定
             if (data.cmn120DspKbn == 0 || data.cmn120DspKbn == 1) {
                 $("#cmn120ChangeGrp").val(data.cmn120groupSid);
             } else if (data.cmn120DspKbn == 2) {
                 $("#cmn120ChangeGrp").val(data.cmn120MyGroupSid);
             }

        } catch (ae) {
            alert(ae);
        }

    }).fail(function(data){
        alert('error');
    });

}

function drawPopUsr() {

    if ($('#funcBtnKbn').val() == 0) {
        $('.atesaki_to_user').remove();
        $('#alldsp_to_area').hide();
        $('#atesaki_area').removeClass('atesaki_scroll_on');
        $('#atesaki_area').removeClass('atesaki_scroll_area_height');

        $('#cmn120SelectRightUser option').each(function(){

            if ($(this).val() != null && $(this).val() != 0 && $(this).val() != -1 && $(this).val() != "") {
                $('#atesaki_to_area').append("<div class=\"atesaki_to_user\" id=\"0\">" + htmlEscape($(this).text())
                                                     + "<input type=\"hidden\" name=\"cir040userSid\" value=\""
                                                     + $(this).val()
                                                     + "\" />&nbsp;&nbsp;[<span class=\"add_usr_del\">削除</span>]</div>");

                dispAllDspToLink();
            }
        });
    }

    $('#atesakiSelPop').dialog('close');

}

function getFormData(formObj) {

    var formData = "";
    formData = formObj.serialize();

    return formData;
}

function htmlEscape(s){
    s=s.replace(/&/g,'&amp;');
    s=s.replace(/>/g,'&gt;');
    s=s.replace(/</g,'&lt;');
    return s;
}