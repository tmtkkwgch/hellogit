/* 宛先 表示区分 0:スクロール表示 1:全て表示*/
var dsp_to_kbn = 0;
/* 宛先TO 表示区分 0:スクロール表示 1:全て表示*/
var dsp_cc_kbn = 0;
/* 宛先BCC 表示区分 0:スクロール表示 1:全て表示*/
var dsp_bcc_kbn = 0;

$(function() {

    //自動削除
    if ($('input:hidden[name=sml250autoDelKbn]').val() == 1
         && $('#sml250kn').attr('id') == null) {
          changeEnableDisable();
    }

    //転送設定
    if ($('input:hidden[name=smlAccountMode]').val() == 2
         && $('input:hidden[name=sml250tensoKbn]').val() == 1
         && $('#sml250kn').attr('id') == null) {

        changeEnableDisableTenso();

        if ($('input:radio[name=sml250tensoSetKbn]:checked').val() == 1) {
            changeTensoArea();
        }

    } else if ($('input:hidden[name=smlAccountMode]').val() == 2
            && $('input:hidden[name=sml250tensoKbn]').val() == 1
            && $('#sml250kn').attr('id') != null) {

        if ($('input:hidden[name=sml250tensoSetKbn]').val() == 1) {
            changeTensoArea2();
        }
    }



    /* タブ変更 */
    $('.sml_account_tab').live("click", function(){
        $(this).parent().addClass('display_none');

        var showId = $(this).attr('id');

        $('#normal_table').addClass('display_none');
        $('#auto_del_table').addClass('display_none');
        $('#tenso_table').addClass('display_none');
        $('#other_table').addClass('display_none');

        $('#' + showId + '_tab').removeClass('display_none');
        $('#' + showId + '_table').removeClass('display_none');

        if (showId == 'normal') {
            $('input:hidden[name=sml250SelTab]').val(0)
        } else if (showId == 'auto_del') {
            $('input:hidden[name=sml250SelTab]').val(1)
        } else if (showId == 'tenso') {
            $('input:hidden[name=sml250SelTab]').val(2)
        } else if (showId == 'other') {
            $('input:hidden[name=sml250SelTab]').val(3)
        }
        changeHelpPrm();

    });

    //転送選択時  タブ変更
    if ($('input:hidden[name=smlAccountMode]').val() == 2
            && $('input:hidden[name=sml250tensoKbn]').val() == 1
            && $('input:hidden[name=sml250SelTab]').val() == 2) {
        $('#tenso').click();
    }

    //宛先,CC,BCC選択ボタン
    $(".mail_send_sel_btn").live("click", function(){

        //選択ボタン名
        var functinBtnName = $("#" + $(this).attr('id') + "Val").val();
        var functinBtnKbn = $("#" + $(this).attr('id') + "Kbn").val();
        var paramStr = "CMD=getInitData";


        paramStr += getNowSelUsr(functinBtnKbn);

        //宛先一覧取得
        getSelAtesakiData(paramStr, functinBtnName, functinBtnKbn);

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
            if (3 < $("#atesaki_to_area div").length) {
                $('#alldsp_to_area').show();
            } else {
                $('#alldsp_to_area').hide();

                $("#atesaki_area").removeClass("atesaki_scroll_on");
                $("#atesaki_area").removeClass("atesaki_scroll_area_height");
            }
        } else if ($(this).parent().attr("class") == "atesaki_cc_user") {
            if (3 < $("#atesaki_cc_area div").length) {
                $('#alldsp_cc_area').show();
            } else {
                $('#alldsp_cc_area').hide();

                $("#cc_area").removeClass("atesaki_scroll_on");
                $("#cc_area").removeClass("atesaki_scroll_area_height");
            }
        } else if ($(this).parent().attr("class") == "atesaki_bcc_user") {
            if (3 < $("#atesaki_bcc_area div").length) {
                $('#alldsp_bcc_area').show();
            } else {
                $('#alldsp_bcc_area').hide();

                $("#bcc_area").removeClass("atesaki_scroll_on");
                $("#bcc_area").removeClass("atesaki_scroll_area_height");
            }
        }
    });

    //宛先 全て表示・スクロール表示をクリック
    $("#all_dsp_to_link").live("click", function(){

        //スクロール表示だった場合
        if (dsp_to_kbn == 0) {
            $("#all_dsp_to_link").html("閉じる");
            $("#atesaki_area").removeClass("atesaki_scroll_on");
            $("#atesaki_area").removeClass("atesaki_scroll_area_height");
            dsp_to_kbn = 1;
        } else {
            $("#all_dsp_to_link").html("全て表示");
            $("#atesaki_area").addClass("atesaki_scroll_on");
            $("#atesaki_area").addClass("atesaki_scroll_area_height");
            dsp_to_kbn = 0;
        }
    });

    //宛先CC 全て表示・スクロール表示をクリック
    $("#all_dsp_cc_link").live("click", function(){

        //スクロール表示だった場合
        if (dsp_cc_kbn == 0) {
            $("#all_dsp_cc_link").html("閉じる");
            $("#cc_area").removeClass("atesaki_scroll_on");
            $("#cc_area").removeClass("atesaki_scroll_area_height");
            dsp_cc_kbn = 1;
        } else {
            $("#all_dsp_cc_link").html("全て表示");
            $("#cc_area").addClass("atesaki_scroll_on");
            $("#cc_area").addClass("atesaki_scroll_area_height");
            dsp_cc_kbn = 0;
        }
    });

    //宛先BCC 全て表示・スクロール表示をクリック
    $("#all_dsp_bcc_link").live("click", function(){

        //スクロール表示だった場合
        if (dsp_bcc_kbn == 0) {
            $("#all_dsp_bcc_link").html("閉じる");
            $("#bcc_area").removeClass("atesaki_scroll_on");
            $("#bcc_area").removeClass("atesaki_scroll_area_height");
            dsp_bcc_kbn = 1;
        } else {
            $("#all_dsp_bcc_link").html("全て表示");
            $("#bcc_area").addClass("atesaki_scroll_on");
            $("#bcc_area").addClass("atesaki_scroll_area_height");
            dsp_bcc_kbn = 0;
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
            $('#cmn120DspKbn').val(3);
        }

        var paramStr = "CMD=changeTabData&";
        paramStr += getFormData($('#atesakiSelForm'));
        getSelAtesakiData(paramStr, $('#funcBtnName').val(), $('#funcBtnKbn').val());

    });

});

function getSelAtesakiData(paramStr, functionName, functionKbn) {

    paramStr += "&cmn120BackUrl=smail"

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

            //
            //送信先制限設定
//            if (data.cmn120banGroupSid != null && data.cmn120banGroupSid != "") {
//                for (i = 0; i < data.cmn120banGroupSid.length; i++) {
//                    atesakiSelStr += "<input type=\"hidden\" name=\"cmn120banGroupSid\" value=\""
//                                  +   data.cmn120banGroupSid[i] + "\">";
//                }
//            }
//            if (data.cmn120disableGroupSid != null && data.cmn120disableGroupSid != "") {
//                for (i = 0; i < data.cmn120disableGroupSid.length; i++) {
//                    atesakiSelStr += "<input type=\"hidden\" name=\"cmn120disableGroupSid\" value=\""
//                                  +   data.cmn120disableGroupSid[i] + "\">";
//                }
//            }
//            if (data.cmn120banUserSid != null && data.cmn120banUserSid != "") {
//                for (i = 0; i < data.cmn120banUserSid.length; i++) {
//                    atesakiSelStr += "<input type=\"hidden\" name=\"cmn120banUserSid\" value=\""
//                                  +   data.cmn120banUserSid[i] + "\">";
//                }
//            }

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
                          +  "<td nowrap id=\"deptAccountTab\" class=\"sel_group_check sel_group_notcheck\"><span>"+ msglist["sml.189"] + "</span></td>"
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
                          +  "<input id=\"cmn120ChangeGrpBtn\" class=\"btn_group_n1\" onclick=\"return openAllGroup_no_submit(this.form.cmn120groupSid, 'cmn120groupSid', '-1', '0', 'changeGrp', 'cmn120userSid', '-1', '0', '0', '0', '0', 'atesakiSelForm', 'fakeButton2', 'cmn120banUserSid', 'cmn120banGroupSid', 'cmn120disableGroupSid')\" value=\"全グループから選択\"  type=\"button\"><br>";


            //グループ
            var grpSelStr = "";
            if (data.cmn120DspKbn == 0 || data.cmn120DspKbn == 1) {
                grpSelStr += "<select name=\"cmn120groupSid\" id=\"cmn120ChangeGrp\" class=\"select01\">";
                if (data.cmn120GroupList.length > 0) {
                    for (k = 0; k < data.cmn120GroupList.length; k++) {
                        var grpMdl = data.cmn120GroupList[k];

                        if (grpMdl.value != "sac") {
                            grpSelStr += "<option value=\""
                                +  grpMdl.value
                                +  "\"";
                            grpSelStr += ">"
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
            if (data.cmn120DspKbn == 3) {
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
                          +  "<input id=\"cmn120ChangeGrpBtn2\" type=\"button\" onclick=\"openGroupWindow(this.form.cmn120groupSid, 'cmn120groupSid', '0', 'changeGrp', '1', 'fakeButton', 'cmn120disableGroupSid')\" class=\"group_btn2\" value=\"&nbsp;&nbsp;\" id=\"cmn120GroupBtn\">"
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
             } else if (data.cmn120DspKbn == 3) {
                 $('#cmn120ChangeGrpBtn').addClass("display_none");
                 $('#cmn120ChangeGrpBtn2').addClass("display_none");
                 $('#deptAccountTab').removeClass("sel_group_notcheck");
                 $('#groupTab').addClass("sel_group_notcheck");
                 $('#mygroupTab').addClass("sel_group_notcheck");
                 $('#cmn120DspKbn').val(3);
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

function htmlEscape(s){
    s=s.replace(/&/g,'&amp;');
    s=s.replace(/>/g,'&gt;');
    s=s.replace(/</g,'&lt;');
    return s;
}


function changeHelpPrm() {
    var selTab = $('input:hidden[name=sml250SelTab]').val();

    if ($('input:hidden[name=smlAccountMode]').val() != 0) {
        if (selTab == 0) {
            $('input:hidden[name=helpPrm]').val("");
        } else if  (selTab == 1) {
            $('input:hidden[name=helpPrm]').val(0);
        } else if  (selTab == 2) {
            $('input:hidden[name=helpPrm]').val(1);
        } else if  (selTab == 3) {
            $('input:hidden[name=helpPrm]').val(2);
        }
    } else {
        if (selTab == 0) {
            $('input:hidden[name=helpPrm]').val(3);
        } else if  (selTab == 1) {
            $('input:hidden[name=helpPrm]').val(4);
        } else if  (selTab == 3) {
            $('input:hidden[name=helpPrm]').val(5);
        }
    }
}

function change(usrKbn, accountMode) {
//    if (accountMode == 2) {
//        if(usrKbn == 0){
//            $('#permissionGroup')[0].style.display="block";
//            $('#permissionUser')[0].style.display="none";
//        }else if(usrKbn == 1){
//            $('#permissionGroup')[0].style.display="none";
//            $('#permissionUser')[0].style.display="block";
//        }
//    }
}

function tensoCheck(usrKbn, mode, delmode) {

    if ($('input:hidden[name=smlAccountMode]').val() == 2
        && $('input:hidden[name=sml250tensoKbn]').val() == 1
        && $('input:radio[name=sml250tensoSetKbn]:checked').val() == 1
        && $('input:radio[name=sml250ObjKbn]:checked').val() == 1
        && $('input:hidden[name=sml250userSid]').length > 0) {

        clearTensoPop(usrKbn, mode, delmode);

    } else {

        if (usrKbn != -1) {
            change(usrKbn, mode);
            buttonPush();
        } else if (delmode == 0) {
            buttonPush('deleteUser');
        } else {
            buttonPush('deleteGroup');
        }

    }
}

function deleteSelUser() {

    document.forms[0].CMD.value='delUser';
    var paramStr = "";
    paramStr += getFormData($('#sml250Form'));

    $.ajax({
        async: true,
        url:  "../smail/sml250.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {
        if (data.errorsList.length > 0) {
            messagePop(data.errorsList[0]);
        } else {
            tensoCheck(-1, -1, 0);
        }
    }).fail(function(data){
        alert('error');
    });

}

function deleteSelGroup() {
    $('select[name=sml250groupSid]').children().remove();
    tensoCheck(-1, -1, 1);

}

function messagePop(msg) {

    $('#messageArea').html("");
    $('#messageArea').append(msg);

    $('#messagePop').dialog({
        autoOpen: true,  // hide dialog
        bgiframe: true,   // for IE6
        resizable: false,
        height: 160,
        width: 350,
        modal: true,
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
}


function clearTensoPop(usrKbn, mode, delmode) {

    $('#clearTensoPop').dialog({
        autoOpen: true,  // hide dialog
        bgiframe: true,   // for IE6
        resizable: false,
        height: 160,
        width: 400,
        modal: true,
        overlay: {
          backgroundColor: '#000000',
          opacity: 0.5
        },
        buttons: {
          はい: function() {

              $('input:hidden[name=sml250userSid]').remove();

              if (usrKbn != -1) {
                  change(usrKbn, mode);
                  buttonPush();
              } else if (delmode == 0) {
                  buttonPush('deleteUser');
              } else {
                  buttonPush('deleteGroup');
              }

              $(this).dialog('close');
          },
          いいえ: function() {

//              if (usrKbn != -1) {
//
//                  if ($('input:radio[name=sml250userKbn]:checked').val() == 0) {
//                      $('input:radio[name=sml250userKbn]').val(['1']);
//                  } else {
//                      $('input:radio[name=sml250userKbn]').val(['0']);
//                  }
//              }

              $(this).dialog('close');
          }
        }
    });
}
function drawPopUsr() {

    if ($('#funcBtnKbn').val() == 0) {
        $('.atesaki_to_user').remove();
        $('#alldsp_to_area').hide();
        $("#atesaki_area").removeClass("atesaki_scroll_on");
        $("#atesaki_area").removeClass("atesaki_scroll_area_height");
        $('#cmn120SelectRightUser option').each(function(){

            if ($(this).val() != null && $(this).val() != 0 && $(this).val() != -1 && $(this).val() != "") {
                $('#atesaki_to_area').append("<div class=\"atesaki_to_user\" id=\"0\">" + htmlEscape($(this).text())
                                                     + "<input type=\"hidden\" name=\"sml250AutoDestToUsrSid\" value=\""
                                                     + $(this).val()
                                                     + "\" />&nbsp;&nbsp;[<span class=\"add_usr_del\">削除</span>]</div>");

                if (3 < $('#atesaki_to_area div').length) {
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
        });
    } else if ($('#funcBtnKbn').val() == 1) {
        $('.atesaki_cc_user').remove();
        $('#alldsp_cc_area').hide();
        $('#cc_area').removeClass('atesaki_scroll_on');
        $('#cc_area').removeClass('atesaki_scroll_area_height');
        $('#cmn120SelectRightUser option').each(function(){

            if ($(this).val() != null && $(this).val() != 0 && $(this).val() != -1 && $(this).val() != "") {
                $('#atesaki_cc_area').append("<div class=\"atesaki_cc_user\" id=\"1\">" + htmlEscape($(this).text())
                                                     + "<input type=\"hidden\" name=\"sml250AutoDestCcUsrSid\" value=\""
                                                     + $(this).val()
                                                     + "\" />&nbsp;&nbsp;[<span class=\"add_usr_del\">削除</span>]</div>");

                if (3 < $('#atesaki_cc_area div').length) {
                    $('#alldsp_cc_area').show();
                    if (dsp_cc_kbn == 1) {
                        $('#all_dsp_cc_link').html('閉じる');
                    } else {
                        $('#all_dsp_cc_link').html('全て表示');
                        $('#cc_area').addClass('atesaki_scroll_on');
                        $('#cc_area').addClass('atesaki_scroll_area_height');

                        //IE9以下対応
                        //宛先がある一定数あると見た目上appendしていかない不具合対策
                        //クリックイベントを２回発生させて宛先のスクロールの開閉を実行する
                        var clickMe = document.getElementById('all_dsp_cc_link');
                        clickMe.click();
                        clickMe.click();
                    }
                } else {
                    $('#alldsp_cc_area').hide();
                    if (dsp_cc_kbn == 1) {
                        $('#all_dsp_cc_link').html('閉じる');
                    } else {
                        $('#all_dsp_cc_link').html('全て表示');
                    }
                }
            }
        });
    } else if ($('#funcBtnKbn').val() == 2) {
        $('.atesaki_bcc_user').remove();
        $('#alldsp_bcc_area').hide();
        $('#bcc_area').removeClass('atesaki_scroll_on');
        $('#bcc_area').removeClass('atesaki_scroll_area_height');

        $('#cmn120SelectRightUser option').each(function(){

            if ($(this).val() != null && $(this).val() != 0 && $(this).val() != -1 && $(this).val() != "") {
                $('#atesaki_bcc_area').append("<div class=\"atesaki_bcc_user\" id=\"2\">" + htmlEscape($(this).text())
                                                     + "<input type=\"hidden\" name=\"sml250AutoDestBccUsrSid\" value=\""
                                                     + $(this).val()
                                                     + "\" />&nbsp;&nbsp;[<span class=\"add_usr_del\">削除</span>]</div>");

                if (3 < $('#atesaki_bcc_area div').length) {
                    $('#alldsp_bcc_area').show();
                    if (dsp_bcc_kbn == 1) {
                        $('#all_dsp_bcc_link').html('閉じる');
                    } else {
                        $('#all_dsp_bcc_link').html('全て表示');
                        $('#bcc_area').addClass('atesaki_scroll_on');
                        $('#bcc_area').addClass('atesaki_scroll_area_height');

                        //IE9以下対応
                        //宛先がある一定数あると見た目上appendしていかない不具合対策
                        //クリックイベントを２回発生させて宛先のスクロールの開閉を実行する
                        var clickMe = document.getElementById('all_dsp_bcc_link');
                        clickMe.click();
                        clickMe.click();
                    }
                } else {
                    $('#alldsp_bcc_area').hide();
                    if (dsp_bcc_kbn == 1) {
                        $('#all_dsp_bcc_link').html('閉じる');
                    } else {
                        $('#all_dsp_bcc_link').html('全て表示');
                    }
                }
            }
        });
    } else if ($('#funcBtnKbn').val() == 3) {
        $('.atesaki_search_user').remove();
        $('#cmn120SelectRightUser option').each(function(){

            if ($(this).val() != null && $(this).val() != 0 && $(this).val() != -1 && $(this).val() != "") {
                $('#atesaki_search_area').append("<div class=\"atesaki_search_user\" id=\"3\">" + htmlEscape($(this).text())
                                                     + "<input type=\"hidden\" name=\"sml090Atesaki\" value=\""
                                                     + $(this).val()
                                                     + "\" />&nbsp;&nbsp;[<span class=\"add_search_usr_del\">削除</span>]</div>");
            }
        });
        $('#head_menu_search_btn2').click();
    }

    $('#atesakiSelPop').dialog('close');

}

function getNowSelUsr(functinBtnKbn) {

    var paramStr = "";

    if (functinBtnKbn == 0) {

        $('input:hidden[name=sml250AutoDestToUsrSid]').each(function(){
            paramStr += "&cmn120userSid=" + $(this).val();
        });

    } else if (functinBtnKbn == 1) {

        $('input:hidden[name=sml250AutoDestCcUsrSid]').each(function(){
            paramStr += "&cmn120userSid=" + $(this).val();
        });

    } else if (functinBtnKbn == 2) {

        $('input:hidden[name=sml250AutoDestBccUsrSid]').each(function(){
            paramStr += "&cmn120userSid=" + $(this).val();
        });

    }
    return paramStr;

}
function getFormData(formObj) {

    var formData = "";
    formData = formObj.serialize();

    return formData;
}

function changeEnableDisable() {

    var jbatchKbn = 0;
    var jbatchKbnVal = '';

    for (i = 0; i < document.forms[0].sml250JdelKbn.length; i++) {
        if (document.forms[0].sml250JdelKbn[i].checked == true) {
            jbatchKbn = i;
        }
    }

    jbatchKbnVal = document.forms[0].sml250JdelKbn[jbatchKbn].value;

    if (jbatchKbnVal == 0) {
        document.forms[0].sml250JYear.disabled = true;
        document.forms[0].sml250JYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].sml250JMonth.disabled = true;
        document.forms[0].sml250JMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].sml250JYear.disabled = false;
        document.forms[0].sml250JYear.style.backgroundColor = '#ffffff';
        document.forms[0].sml250JMonth.disabled = false;
        document.forms[0].sml250JMonth.style.backgroundColor = '#ffffff';
    }

    var sbatchKbn = 0;
    var sbatchKbnVal = '';

    for (i = 0; i < document.forms[0].sml250SdelKbn.length; i++) {
        if (document.forms[0].sml250SdelKbn[i].checked == true) {
            sbatchKbn = i;
        }
    }
    sbatchKbnVal = document.forms[0].sml250SdelKbn[sbatchKbn].value;

    if (sbatchKbnVal == 0) {
        document.forms[0].sml250SYear.disabled = true;
        document.forms[0].sml250SYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].sml250SMonth.disabled = true;
        document.forms[0].sml250SMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].sml250SYear.disabled = false;
        document.forms[0].sml250SYear.style.backgroundColor = '#ffffff';
        document.forms[0].sml250SMonth.disabled = false;
        document.forms[0].sml250SMonth.style.backgroundColor = '#ffffff';
    }

    var wbatchKbn = 0;
    var wbatchKbnVal = '';

    for (i = 0; i < document.forms[0].sml250WdelKbn.length; i++) {
        if (document.forms[0].sml250WdelKbn[i].checked == true) {
            wbatchKbn = i;
        }
    }
    wbatchKbnVal = document.forms[0].sml250WdelKbn[wbatchKbn].value;

    if (wbatchKbnVal == 0) {
        document.forms[0].sml250WYear.disabled = true;
        document.forms[0].sml250WYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].sml250WMonth.disabled = true;
        document.forms[0].sml250WMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].sml250WYear.disabled = false;
        document.forms[0].sml250WYear.style.backgroundColor = '#ffffff';
        document.forms[0].sml250WMonth.disabled = false;
        document.forms[0].sml250WMonth.style.backgroundColor = '#ffffff';
    }

    var dbatchKbn = 0;
    var dbatchKbnVal = '';

    for (i = 0; i < document.forms[0].sml250DdelKbn.length; i++) {
        if (document.forms[0].sml250DdelKbn[i].checked == true) {
            dbatchKbn = i;
        }
    }
    dbatchKbnVal = document.forms[0].sml250DdelKbn[dbatchKbn].value;

    if (dbatchKbnVal == 0) {
        document.forms[0].sml250DYear.disabled = true;
        document.forms[0].sml250DYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].sml250DMonth.disabled = true;
        document.forms[0].sml250DMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].sml250DYear.disabled = false;
        document.forms[0].sml250DYear.style.backgroundColor = '#ffffff';
        document.forms[0].sml250DMonth.disabled = false;
        document.forms[0].sml250DMonth.style.backgroundColor = '#ffffff';
    }
}

function submitStyleChange() {
    document.forms[0].sml250JYear.disabled=false;
    document.forms[0].sml250JMonth.disabled=false;
    document.forms[0].sml250SYear.disabled=false;
    document.forms[0].sml250SMonth.disabled=false;
    document.forms[0].sml250WYear.disabled=false;
    document.forms[0].sml250WMonth.disabled=false;
    document.forms[0].sml250DYear.disabled=false;
    document.forms[0].sml250DMonth.disabled=false;
}

function setDispState(kbnElem, yearElem, monthElem) {

    for (i = 0; i < kbnElem.length; i++) {
        if (kbnElem[i].checked == true) {
            batchKbn = i;
        }
    }
    batchKbnVal = kbnElem[batchKbn].value;

    if (batchKbnVal == 0) {
        yearElem.disabled = true;
        yearElem.style.backgroundColor = '#e0e0e0';
        monthElem.disabled = true;
        monthElem.style.backgroundColor = '#e0e0e0';
    } else {
        yearElem.disabled = false;
        yearElem.style.backgroundColor = '#ffffff';
        monthElem.disabled = false;
        monthElem.style.backgroundColor = '#ffffff';
    }
}



function changeEnableDisableTenso() {

    var bool1 = false;
    if (document.forms[0].sml250MailFw[0].checked) {
        bool1 = true;
        document.forms[0].sml250MailDf.value = '';
        document.forms[0].sml250SmailOp[0].checked = true;
        if (document.forms[0].sml250ZaisekiPlugin.value == 0) {
            document.forms[0].sml250HuriwakeKbn[0].checked = true;
            document.forms[0].sml250Zmail1.value = '';
            document.forms[0].sml250Zmail2.value = '';
            document.forms[0].sml250Zmail3.value = '';
        }
    }
    document.forms[0].sml250MailDf.disabled = bool1;
    document.forms[0].sml250MailDfSelected.disabled = bool1;

    if (!bool1) {
        if (document.forms[0].sml250MailDfSelected.value != 0) {
             document.forms[0].sml250MailDf.disabled = true;
        }
    }

    document.forms[0].sml250SmailOp[0].disabled = bool1;
    document.forms[0].sml250SmailOp[1].disabled = bool1;

    if (document.forms[0].sml250ZaisekiPlugin.value == 1) {
        return;
    }
    document.forms[0].sml250HuriwakeKbn[0].disabled = bool1;
    document.forms[0].sml250HuriwakeKbn[1].disabled = bool1;
    document.forms[0].sml250HuriwakeKbn[2].disabled = bool1;

    if (document.forms[0].sml250HuriwakeKbn[0].checked) {
        document.forms[0].sml250Zmail1.value = '';
        document.forms[0].sml250Zmail2.value = '';
        document.forms[0].sml250Zmail3.value = '';
        document.forms[0].sml250Zmail1Selected.disabled = true;
        document.forms[0].sml250Zmail2Selected.disabled = true;
        document.forms[0].sml250Zmail3Selected.disabled = true;
        document.forms[0].sml250Zmail1.disabled = true;
        document.forms[0].sml250Zmail2.disabled = true;
        document.forms[0].sml250Zmail3.disabled = true;
    } else if (document.forms[0].sml250HuriwakeKbn[1].checked) {
        document.forms[0].sml250Zmail1Selected.disabled = false;
        document.forms[0].sml250Zmail2Selected.disabled = false;
        document.forms[0].sml250Zmail3Selected.disabled = false;
        document.forms[0].sml250Zmail1.disabled = false;
        document.forms[0].sml250Zmail2.disabled = false;
        document.forms[0].sml250Zmail3.disabled = false;
    } else if (document.forms[0].sml250HuriwakeKbn[2].checked) {
        document.forms[0].sml250Zmail1.value = '';
        document.forms[0].sml250Zmail3.value = '';
        document.forms[0].sml250Zmail1Selected.disabled = true;
        document.forms[0].sml250Zmail2Selected.disabled = false;
        document.forms[0].sml250Zmail3Selected.disabled = true;
        document.forms[0].sml250Zmail1.disabled = true;
        document.forms[0].sml250Zmail2.disabled = false;
        document.forms[0].sml250Zmail3.disabled = true;
    }



    if (document.forms[0].sml250HuriwakeKbn[1].checked) {
        if (document.forms[0].sml250Zmail1Selected.value != 0) {
            document.forms[0].sml250Zmail1.disabled = true;
        }
        if (document.forms[0].sml250Zmail2Selected.value != 0) {
            document.forms[0].sml250Zmail2.disabled = true;
        }
        if (document.forms[0].sml250Zmail3Selected.value != 0) {
            document.forms[0].sml250Zmail3.disabled = true;
        }
    } else if (document.forms[0].sml250HuriwakeKbn[2].checked) {
        if (document.forms[0].sml250Zmail2Selected.value != 0) {
            document.forms[0].sml250Zmail2.disabled = true;
        }
    }
    return;
}


function changeTensoArea() {

    if ($('input:radio[name=sml250tensoSetKbn]:checked').val() == 1) {
        $('.sml_tenso_set').removeClass('display_none');
    } else {
        $('.sml_tenso_set').addClass('display_none');
    }

}

function changeTensoArea2() {

    if ($('input:hidden[name=sml250tensoSetKbn]').val() == 1) {
        $('.sml_tenso_set').removeClass('display_none');
    } else {
        $('.sml_tenso_set').addClass('display_none');
    }

}

function changeTensoUsrKbn() {
    $('input:hidden[name=sml250userSid]').remove();
    buttonPush('redraw');
}
