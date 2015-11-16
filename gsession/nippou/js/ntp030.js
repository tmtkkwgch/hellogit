function buttonPush(cmd){
    document.forms[0].cmd.value=cmd;
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function change030GroupCombo(){
    $("input[name=ntp030SelectUsrSid]").val('');
    document.forms[0].submit();
    return false;
}

function addNippou(cmd, ymd, uid, ukbn){
    document.forms[0].cmd.value=cmd;
    document.forms[0].CMD.value=cmd;
    document.forms[0].ntp010SelectDate.value=ymd;
    document.forms[0].ntp010SelectUsrSid.value=uid;
    document.forms[0].ntp010SelectUsrKbn.value=ukbn;
    document.forms[0].submit();
    return false;
}

function editNippou(cmd, ymd, sid, uid, ukbn){
    document.forms[0].cmd.value=cmd;
    document.forms[0].CMD.value=cmd;
    document.forms[0].ntp010SelectDate.value=ymd;
    document.forms[0].ntp010SelectUsrSid.value=uid;
    document.forms[0].ntp010SelectUsrKbn.value=ukbn;
    document.forms[0].ntp010NipSid.value=sid;
    document.forms[0].submit();
    return false;
}

function moveMonthNippou(cmd, uid, ukbn){
    document.forms[0].cmd.value=cmd;
    document.forms[0].CMD.value=cmd;
    document.forms[0].ntp010SelectUsrSid.value=uid;
    document.forms[0].ntp010SelectUsrKbn.value=ukbn;
    document.forms[0].submit();
    return false;
}

function moveCreateMsg(cmd, uid, ukbn){
    document.forms[0].cmd.value=cmd;
    document.forms[0].CMD.value=cmd;
    document.forms[0].ntp010SelectUsrSid.value=uid;
    document.forms[0].ntp010SelectUsrKbn.value=ukbn;
    document.forms[0].submit();
    return false;
}

var subWindow;
var flagSubWindow = false;

function windowClose(){
    if(subWindow != null){
        subWindow.close();
    }
}

function afterNewWinOpen(win){
    win.moveTo(0,0);
    subWindow.focus();
    return;
}

function setZaiseki(uid){
    document.forms[0].CMD.value='ntp030Zaiseki';
    document.forms[0].ntp010SelectUsrSid.value=uid;
    document.forms[0].submit();
    return false;
}

function setFuzai(uid){
    document.forms[0].CMD.value='ntp030Fuzai';
    document.forms[0].ntp010SelectUsrSid.value=uid;
    document.forms[0].submit();
    return false;
}

function setSonota(uid){
    document.forms[0].CMD.value='ntp030Sonota';
    document.forms[0].ntp010SelectUsrSid.value=uid;
    document.forms[0].submit();
    return false;
}
function moveListNippou(cmd){
    document.forms[0].cmd.value=cmd;
    document.forms[0].CMD.value=cmd;
    document.forms[0].ntp010SelectUsrKbn.value=0;
    document.forms[0].submit();
    return false;
}
function keyPress(keycode) {
    if (keycode == 13) {
        document.forms[0].CMD.value='search';
        document.forms[0].submit();
        return false;
    }
}


$(function() {

    /* IE6用 日付選択固定*/
    $('#time_line_fix').exFixed();

    /* ユーザ名リンク クリック */
    $(".ntp030usrLink").live("click", function(){
        $("input[name=ntp030SelectUsrSid]").val($(this).attr('id'));
        $("input[name=ntp010SelectUsrSid]").val($(this).attr('id'));
        document.forms[0].submit();
        return false;
    });

    /* ユーザリンク hover */
    $('.user_select_area').hover(
        function () {
            $(this).removeClass("user_select_area").addClass("user_select_area_hover");
          },
          function () {
              $(this).removeClass("user_select_area_hover").addClass("user_select_area");
          }
    );

    /* グループの日報を表示 クリック */
    $("#selGroupBtn").live("click", function(){
        $("input[name=ntp030SelectUsrSid]").val('');
        document.forms[0].submit();
        return false;
    });

    /* グループの日報を表示 hover */
    $('.time_line_group_btn').hover(
        function () {
            $(this).removeClass("time_line_group_btn").addClass("time_line_group_btn_hover");
          },
          function () {
              $(this).removeClass("time_line_group_btn_hover").addClass("time_line_group_btn");
          }
    );

    /* 投稿ボタン hover */
    $('.btn_base_toukou').live({
          mouseenter:function () {
            $(this).addClass("btn_base_toukou_hover");
          },
          mouseleave:function () {
              $(this).removeClass("btn_base_toukou_hover");
          }
    });


    /* コメントするボタン */
    var cmtClickFlg = 0;
    $("input[name=commentBtn]").live("click", function(){
        var cmtNtpSid = $(this).attr("id");
        var cmtId = "";
        if (cmtNtpSid != null && cmtNtpSid != "") {
            cmtId = "_" + cmtNtpSid;
        }
        var commentStr = $("textarea[name=ntp030Comment" + cmtId + "]").val();

        if (!commentStr.match(/\S/g)) {
            //何も入力されていない場合はメッセージを表示する
            $('#commentError').dialog('open');
            $('#commentError').dialog({
                autoOpen: true,
                bgiframe: true,
                resizable: false,
                width:300,
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
        } else if (commentStr.length > 1000) {
            //1000文字を超えていた場合
            $('#commentLengthError').dialog('open');
            $('#commentLengthError').dialog({
                autoOpen: true,
                bgiframe: true,
                resizable: false,
                width:350,
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
            commentStr = encodeURIComponent(commentStr);
            var commentNtpSid = $(this).attr("id");

            //コメント送信,最新データ取得,画面書き換え
            $.ajaxSetup({async:false});
            $.post('../nippou/ntp030.do', {"cmd":"addComment",
                                           "CMD":"addComment",
                                           "commentNtpSid":commentNtpSid,
                                           "commentStr":commentStr},
              function(data) {
                if (data != null || data != "") {
                    setComment(commentNtpSid, data);
                }
            });
         }
    });

    /* コメントテキストエリア フォーカス時*/
    $(".commentTextArea").live("focus", function(){
        $(this).animate({ height:"60px"}, 500);
    }).live("blur", function(){
        $(this).animate({ height:"20px"}, 500);
    });


    /* コメント削除リンク */
    $(".commentDel").live("click", function(){
        delCmtNtpId = $(this);
        $('#dialogCommentDel').dialog('open');
    });


    /* コメント削除確認 */
    $('#dialogCommentDel').dialog({
        autoOpen: false,  // hide dialog
        bgiframe: true,   // for IE6
        resizable: false,
        height: 160,
        modal: true,
        overlay: {
          backgroundColor: '#000000',
          opacity: 0.5
        },
        buttons: {
          OK: function() {

            //コメント送信,最新データ取得,画面書き換え
            $.ajaxSetup({async:false});
            $.post('../nippou/ntp030.do', {"cmd":"delComment",
                                           "CMD":"delComment",
                                           "commentNtpSid":delCmtNtpId.attr("id")
                                           },
              function(data) {
//                if (data != null || data != "") {
//
//                }
            });

              //行削除
              $("." + delCmtNtpId.parent().attr("id")).remove();
              $(this).dialog('close');
          },
          Cancel: function() {
            $(this).dialog('close');
          }
        }
    });

    /* いいねボタン */
    $(".goodLink").live("click", function(){

        var goodNtpSid = $(this).attr('id');

        //いいねをしているか確認
        $.ajaxSetup({async:false});
        $.post('../nippou/ntp030.do', {"cmd":"addGood",
                                     "CMD":"addGood",
                                     "goodNtpSid":goodNtpSid},
        function(data) {
            if (data != null || data != "") {
                if (data.cnt > 0) {
                    //すでに「いいね」していた場合
                    $('#goodError').dialog({
                    autoOpen: true,
                    bgiframe: true,
                    resizable: false,
                    width:350,
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
//                    //確認ダイアログを表示
//                    $('#goodDialog').dialog({
//                        autoOpen: true,  // hide dialog
//                        bgiframe: true,   // for IE6
//                        resizable: false,
//                        width:350,
//                        modal: true,
//                        overlay: {
//                          backgroundColor: '#000000',
//                          opacity: 0.5
//                        },
//                        buttons: {
//                          OK: function() {

                        //いいね登録、最新データ取得
                        $.ajaxSetup({async:false});
                        $.post('../nippou/ntp030.do', {
                                       "cmd":"commitGood",
                                       "CMD":"commitGood",
                                       "goodNtpSid":goodNtpSid},
                            function(gdata) {
                                if (gdata != null || gdata != "") {
                                   //いいね数の書き換え
                                    var goodBtnStr = "<span class=\"text_already_good\">いいね!しています</span>";
                                    $('#goodBtnArea_' + goodNtpSid).html('');
                                    $('#goodBtnArea_' + goodNtpSid).html(goodBtnStr);
                                    $('#goodCntArea_' + goodNtpSid).html('');
                                    $('#goodCntArea_' + goodNtpSid).html(gdata.cnt);
                                }
                            });
                            $(this).dialog('close');
//                            //いいね登録完了
//                            $('#goodDialogComp').dialog({
//                                autoOpen: true,
//                                bgiframe: true,
//                                resizable: false,
//                                width:250,
//                                modal: true,
//                                closeOnEscape: false,
//                                overlay: {
//                                    backgroundColor: '#000000',
//                                    opacity: 0.5
//                                },
//                                buttons: {
//                                    OK: function() {
//                                            $(this).dialog('close');
//                                        }
//                                    }
//                               });
//                          },
//                          Cancel: function() {
//                            $(this).dialog('close');
//                          }
//                        }
//                    });
                }
            }
        });
    });

    /* いいねしてる人リンク */
    $(".text_good").live("click", function(){
        var thisEle = $(this);
        var goodAddNtpSid = $(this).attr('id');
        var ntpGoodCnt = $(this).children().html();
        if ($(this).children().html() != 0) {
            $.ajaxSetup({async:false});
            $.post('../nippou/ntp030.do', {
                           "cmd":"goodAddUser",
                           "CMD":"goodAddUser",
                           "goodAddNtpSid":goodAddNtpSid},
                function(gudata) {

                    if (gudata != null || gudata != "") {
                        if (gudata.length > 0) {
                            var goodAddUsrInfstr = "";
                            for (u=0; u<gudata.length; u++) {

                                //imgタグ文字列
                                var goodAddUsrImgStr = "";
                                if (gudata[u].usrMdl.binSid == "0") {
                                    //写真なし
                                    goodAddUsrImgStr = "<img class=\"comment_Img\" src=\"../user/images/photo.gif\" name=\"userImage\" alt=\"写真\" border=\"1px\" width=\"50px\" />";
                                } else {
                                    if (gudata[u].usrMdl.usiPictKf == "0") {
                                        //写真あり 公開
                                        goodAddUsrImgStr = "<img class=\"comment_Img\" src=\"../common/cmn100.do?CMD=getImageFile&cmn100binSid="
                                               +  gudata[u].usrMdl.binSid + "\""
                                               +  " alt=\"写真\" width=\"50px\">";
                                    } else {
                                        //写真あり 非公開
                                        goodAddUsrImgStr = "<div class=\"photo_hikokai2\" align=\"center\"><span style=\"color: rgb(252, 41, 41);\">非公開</span></div>";
                                    }
                                }

                                //imgタグ文字列
                                var goodDelStr = "";
                                if (gudata[u].goodDelFlg == 1) {
                                    goodDelStr = "いいね!を取り消す";
                                }

                                goodAddUsrInfstr  += "<table class=\"tl0\" style=\"border-collapse:collapse;\" width=\"100%\">"
                                                  + "<tbody>"
                                                  +   "<tr height=\"70px\">"
                                                  +     "<td rowspan=\"2\" class=\"usr_inf_area_left user_select_area\" height=\"70px\" width=\"55px\">"
                                                  +       "<a href=\"javascript:void(0);\" onclick=\"return openUserInfoWindow(" + gudata[u].usrMdl.usrSid + ");\">"
                                                  +         goodAddUsrImgStr
                                                  +       "</a>"
                                                  +      "</td>"
                                                  +      "<td class=\"left_space usr_inf_area_top_bottom user_select_area\" align=\"left\" nowrap=\"nowrap\" width=\"100%\">"
                                                  +        "<span class=\"text_link2\">"
                                                  +          "<a href=\"javascript:void(0);\" onclick=\"return openUserInfoWindow(" + gudata[u].usrMdl.usrSid + ");\">" + gudata[u].usrMdl.usiSei + "&nbsp;&nbsp;" + gudata[u].usrMdl.usiMei + "</a>"
                                                  +        "</span>"
                                                  +       "</td>"
                                                  +      "<td class=\"left_space usr_inf_area_right user_select_area\" align=\"left\" nowrap=\"nowrap\" width=\"100%\">"
                                                  +        "<span class=\"text_link3 goodDelLink\" id=\"" + goodAddNtpSid + "\">"
                                                  +        goodDelStr
                                                  +        "</span>"
                                                  +       "</td>"
                                                  +    "</tr>"
                                                  +  "</tbody>"
                                                  + "</table>";
                            }
                            $("#goodUsrInfArea2").append(goodAddUsrInfstr);
                        }

                        $("#goodUsrInfArea").css("top",thisEle.offset().top - 200);
                        $("#goodUsrInfArea")[0].style.display="block";
                        Glayer.show();
                    }
            });
        } else {
            //いいねが0だった場合
            $('#goodZero').dialog({
                autoOpen: true,
                bgiframe: true,
                resizable: false,
                width:400,
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
        }
    });

    //いいね追加ユーザポップアップ閉じる
    $("#goodAddUsrClose").live("click", function(){
        Glayer.hide();
        $("#goodUsrInfArea")[0].style.display="none";
        $("#goodUsrInfArea2").html("");
    });

    //いいね取り消し
    $(".goodDelLink").live("click", function(){

        var goodNtpSid = $(this).attr('id');

        //いいね取り消し、最新データ取得
        $.ajaxSetup({async:false});
        $.post('../nippou/ntp030.do', {
                       "cmd":"deleteGood",
                       "CMD":"deleteGood",
                       "goodNtpSid":goodNtpSid},
            function(gdata) {
                if (gdata != null || gdata != "") {
                   //いいね数の書き換え
                    var goodBtnStr = "<input id=\"" + goodNtpSid + "\" style=\"border:0px;color:#000066;font-size:10px;font-weight:bold;width:60px;height:17px;\" class=\"ntp_good_btn goodLink\" value=\"いいね!\" type=\"button\">";
                    $('#goodBtnArea_' + goodNtpSid).html('');
                    $('#goodBtnArea_' + goodNtpSid).html(goodBtnStr);
                    $('#goodCntArea_' + goodNtpSid).html('');
                    $('#goodCntArea_' + goodNtpSid).html(gdata.cnt);
                }
            });

        Glayer.hide();
        $("#goodUsrInfArea")[0].style.display="none";
        $("#goodUsrInfArea2").html("");
    });

    //ヘッダーの日付選択表示判定
    $(window).scroll(function () {
        var pageTopArea = $('#pageTopArea'),
        offset = pageTopArea.offset();

        if($(window).scrollTop() > offset.top + 200) {
            $("#time_line_fix").removeClass('areanone');
            $("#time_line_fix").addClass('areablock');
        } else {
            $("#time_line_fix").removeClass('areablock');
            $("#time_line_fix").addClass('areanone');
        }
    });

    /* TOPボタンクリック*/
    $("#scTop").live("click", function(){
        $('html,body').animate({ scrollTop:0 }, 'normal');
    });


    /* データ読み込み処理 */
    $(window).bottom({proximity:0});
    $(window).bind('bottom', function() {

        var obj = $(this);
        if (!obj.data('loading')) {
            obj.data('loading', true);
            $('#pageBottom').html('<img src=\"../nippou/images/ajax-loader.gif\" />');

            //オフセット
            var offset        = parseInt($("input[name=ntp030Offset]").val()) + 1;
            //選択グループ
            var selGpSid      = $("select[name=ntp010DspGpSid]").val();
            //選択ユーザ
            var selUsrSid     = $("input[name=ntp030SelectUsrSid]").val();
            //セッションユーザbinSid
            var sUsrBinSid    = parseInt($("input[name=sUsrBinSid]").val());
            //セッションユーザusiPictKf
            var sUsiPictKf    = parseInt($("input[name=sUsrPictKf]").val());
            //最終表示日
            var lastLabelDate = $("input[name=ntp030LabelDate]").val();
            //ソート
            var sortLabel     = $("select[name=ntp030Sort]").val();

            //データ取得
            $.ajaxSetup({async:false});
            $.post('../nippou/ntp030.do', {"cmd":"getTimeLineData",
                                           "CMD":"getTimeLineData",
                                           "ntpOffset":offset,
                                           "ntpDspGpSid":selGpSid,
                                           "ntpSelectUsrSid":selUsrSid,
                                           "ntpSortLabel":sortLabel
                                           },
              function(data) {

                //取得したデータを画面に出力
                if (data.length > 0) {
                    for (i=0; i<data.length; i++) {

                        //日報ユーザ情報
                        var usrInfMdl   = data[i].ntp030UsrInfMdl;
                        //日報ユーザSID
                        var ntpUsrSid   = data[i].ntp030UsrSid;
                        //日報SID
                        var dataNtpSid  = data[i].ntp030NtpSid;
                        //編集可能区分
                        var editAuthKbn = data[i].ntp030AuthEditKbn;
                        //添付情報
                        var tmpMdlList  = data[i].ntp030FileList;
                        //コメント情報
                        var cmtdata     = data[i].ntp030CommentList;

                        //imgタグ文字列
                        var imgStr = "";
                        if (usrInfMdl.binSid == "0") {
                            //写真なし
                            imgStr = "<img class=\"comment_Img\" src=\"../user/images/photo.gif\" name=\"userImage\" alt=\"写真\" border=\"1px\" width=\"45px\" />";
                        } else {
                            if (usrInfMdl.usiPictKf == "0") {
                                //写真あり 公開
                                imgStr = "<img class=\"comment_Img\" src=\"../common/cmn100.do?CMD=getImageFile&cmn100binSid="
                                       +  usrInfMdl.binSid + "\""
                                       +  " alt=\"写真\" width=\"45px\">";
                            } else {
                                //写真あり 非公開
                                imgStr = "<div class=\"photo_hikokai2\" align=\"center\"><span style=\"color: rgb(252, 41, 41);\">非公開</span></div>";
                            }
                        }

                        //添付ファイル文字列
                        var tmpStr = "";
                        if (tmpMdlList.length > 0) {
                            for (n=0; n<tmpMdlList.length; n++) {
                                tmpStr += "<img src=\"../nippou/images/icon_attach.gif\" alt=\"添付\" />"
                                       + "<a href=\"javascript:void(0);\" onclick=\"return fileLinkClick("
                                       + dataNtpSid + ","
                                       + tmpMdlList[n].binSid
                                       + ");\"><span class=\"text_link_min\">"
                                       + tmpMdlList[n].binFileName + tmpMdlList[n].binFileSizeDsp
                                       + "</span></a><br>";
                            }

                            tmpStr += "<div><img src=\"../schedule/images/spacer.gif\" width=\"1px\" height=\"5px\" border=\"0\"></div>";
                        }

                        var sUsrBinSid = $("input[name=sUsrBinSid]").val();
                        //セッションユーザusiPictKf
                        var sUsiPictKf = $("input[name=sUsrPictKf]").val();

                        //コメント入力エリア文字列
                        var cmtAddAreaStr = "";
                        cmtAddAreaStr = "<tr id=\"commentAddArea_" + dataNtpSid + "\" class=\"commentArea2\">"
                                      + "<td class=\"td_wt8\" style=\"padding-top:5px;\" colspan=\"4\">"
                                      +   "<table>"
                                      +     "<tbody><tr>"
                                      +       "<td style=\"padding-left:60px;\">";

                                      if (sUsrBinSid == 0) {
                                          cmtAddAreaStr += "<img  class=\"comment_Img\" src=\"../user/images/photo.gif\" name=\"userImage\" alt=\"写真\" border=\"1\" width=\"30px\" />";
                                      } else {
                                          if (sUsiPictKf == 1) {
                                            cmtAddAreaStr += "<div align=\"center\" class=\"photo_hikokai2\"><span style=\"color:#fc2929;\">非公開</span></div>";
                                          } else {
                                            cmtAddAreaStr += "<img class=\"comment_Img\" src=\"../common/cmn100.do?CMD=getImageFile&cmn100binSid=" + sUsrBinSid + "\" alt=\"写真\" width=\"30px\" />";
                                          }
                                      }
                        cmtAddAreaStr += "</td>"
                                      +   "<td style=\"padding-left:10px;padding-top:10px;\" valign=\"middle\">"
                                      +    "<div class=\"textfield\">"
                                      +     "<label class=\"label_area\" style=\"opacity: 1; font-size: 12px; color: rgb(163, 163, 163);\" for=\"field_id" + dataNtpSid + "\">コメントする</label>"
                                      +     "<textarea name=\"ntp030Comment_" + dataNtpSid + "\" cols=\"45\" rows=\"2\" class=\"commentTextArea\" id=\"field_id" + dataNtpSid + "\"></textarea>"
                                      +    "</div>"
                                      +    "</td>"
                                      +   "<td id=\"2\" valign=\"middle\">"
                                      +     "&nbsp;<input id=\"" + dataNtpSid + "\" name=\"commentBtn\" class=\"btn_base_toukou\" value=\"投稿\" type=\"button\">"
                                      +   "</td>"
                                      + "</tr></tbody>"
                                      + "</table>"
                                      +"</td>"
                                      +"</tr>";


                        //コメント文字列
                        var commentStr = "";
                        if (cmtdata.length > 0) {
                            for (j=0; j<cmtdata.length; j++) {

                                var cmtUsrMdl = cmtdata[j].ntp030UsrInfMdl;
                                var cmtMdl    = cmtdata[j].ntp030CommentMdl;

                                commentStr += "<table class=\"commentDspAreaTable"
                                           +  dataNtpSid
                                           +  "_"
                                           +  cmtMdl.npcSid
                                           +  "\"><tbody><tr><td valign=\"top\" style=\"padding-left:60px;padding-top:12px;padding-bottom:20px;\">";

                                if (cmtUsrMdl.binSid == "0") {
                                    //写真なし
                                    commentStr += "<img class=\"comment_Img\" src=\"../user/images/photo.gif\" name=\"userImage\" alt=\"写真\" border=\"1\" width=\"30px\">";
                                } else {
                                    if (cmtUsrMdl.usiPictKf == "0") {
                                        //写真あり 公開
                                        commentStr += "<img class=\"comment_Img\" src=\"../common/cmn100.do?CMD=getImageFile&cmn100binSid="
                                                   +  cmtUsrMdl.binSid + "\""
                                                   +  " alt=\"写真\" width=\"30px\">";
                                    } else {
                                        //写真あり 非公開
                                        commentStr += "<div class=\"photo_hikokai2\" align=\"center\"><span style=\"color: rgb(252, 41, 41);\">非公開</span></div>";
                                    }
                                }

                                //削除
                                var delComStr = "";
                                if (cmtdata[j].ntp030CommentDelFlg == 1) {
                                    delComStr = "&nbsp;<span class=\"commentDel\" id=\""
                                    +  cmtdata[j].commentSid
                                    +  "\"><img src=\"../nippou/images/delete_icon2.gif\" alt=\"削除\"></span>";
                                }

                                commentStr +=  "</td><td id=\"commentDspAreaTable"
                                           +  dataNtpSid
                                           +  "_"
                                           +  cmtMdl.npcSid
                                           +  "\" style=\"padding-top:5px;padding-left:10px;\" valign=\"top\"><span style=\"font-size: 12px; color: rgb(51, 51, 51);\"><b>"
                                           +  cmtUsrMdl.usiSei + "&nbsp;" + cmtUsrMdl.usiMei
                                           +  "</b></span>&nbsp;&nbsp;<span style=\"font-size: 12px; color: rgb(51, 51, 51);\">"
                                           +  cmtdata[j].ntp030CommentDate
                                           +  "</span>"
                                           +  delComStr
                                           + "<br></span><span style=\"font-size: 13px; color: rgb(51, 51, 51);\">"
                                           +  cmtMdl.npcComment
                                           +  "</span></td></tr></tbody></table>";
                            }
                        }

                        //タイムライン日付表示エリア
                        var timeLineDateStr ="";
                        if (lastLabelDate != data[i].ntp030NtpDate) {
                            timeLineDateStr = "<span class=\"time_line_date_label dataDateArea\" id=\"" + data[i].ntp030NtpDate + "\">" + data[i].ntp030LabelDate + "</span>";
                            var scldata = data[i].ntp030NtpDate;
                            var sclOpDate = scldata.substring(0,8);
                            var sel = document.getElementById("select_fix_date");
                            var writeFlg = 0;

                            if (sel.length == 0) {
                                $('#select_fix_date').append($('<option>').html(sclOpDate).val(sclOpDate));
                                timeLineDateStr += "<span id=\"position_" + sclOpDate + "\"></span>";
                            } else {
                                for (var op = 0; op < sel.length; op++) {
                                    if (sel.options[op].text == sclOpDate) {
                                        writeFlg = 1;
                                    }
                                  }

                                if (writeFlg == 0) {
                                    $('#select_fix_date').append($('<option>').html(sclOpDate).val(sclOpDate));
                                    timeLineDateStr += "<span id=\"position_" + sclOpDate + "\"></span>";
                                }
                            }
                            writeFlg = 0;
                        } else {
                            timeLineDateStr += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                        }

                        //いいねボタン
                        var goodBtnStr = "";
                        if (data[i].ntp030GoodFlg == 0) {
                            goodBtnStr = "<input id=\"" + dataNtpSid + "\" style=\"border:0px;color:#000066;font-size:10px;font-weight:bold;width:60px;height:17px;\" class=\"ntp_good_btn goodLink\" value=\"いいね!\" type=\"button\">";
                        } else {
                            goodBtnStr = "<span class=\"text_already_good\">いいね!しています</span>";
                        }

                        //案件
                        var ankenAreaStr = "";
                        if (data[i].ankenName != "") {
                            ankenAreaStr =  "<a id=\"" + data[i].ankenSid + "\" class=\"sc_link anken_click\">"
                                         +  "<span class=\"text_anken\">" + replaceHtmlTag(data[i].ankenName) + "</span>"
                                         +  "</a>"
                                         +  "</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                        }

                        //企業
                        var companyAreaStr = "";
                        if (data[i].companyName != "") {
                            companyAreaStr += "<span>"
                                           + "<a id=\"" + data[i].companySid + "\" class=\"sc_link comp_click comp_name_link_" + dataNtpSid + "\">"
                                           + "<span class=\"comp_name_" + dataNtpSid + "\">"
                                           + "<span class=\"text_company\">"
                                           + replaceHtmlTag(data[i].companyName) + replaceHtmlTag(data[i].companyBaseName)
                                           + "</span>"
                                           + "</span>"
                                           + "</a>"
                                           + "</span>";
                        }

                        lastLabelDate = data[i].ntp030NtpDate;

                        //出力処理
                        $('#pageBottomArea').append("<tr>"
                        +   "<td width=\"100%\">"
                        +     "<table width=\"100%\">"
                        +       "<tr>"
                        +         "<td colspan=\"6\">"
                        +           "<table width=\"100%\">"
                        +             "<tbody>"
                        +               "<tr><td></td></tr>"
                        +               "<tr><td colspan=\"6\" height=\"25px\"></td></tr>"
                        +               "<tr>"
                        +                 "<td class=\"td_wt5 title_area_1\" valign=\"top\" nowrap=\"nowrap\" width=\"50px\">"
                        +                   "<div style=\"padding-left:5px;padding:right:0px;\">"
                        +                    imgStr
                        +                   "</div>"
                        +                 "</td>"
                        +                 "<td class=\"td_wt5\" style=\"border-left:0px;\" width=\"100%\" colspan=\"3\" align=\"left\">"
                        +                   "<div class=\"titleArea2 title_area_2\">"
                        +                     "<span style=\"font-color:#eeeeee;font-size:12px;line-height:1em;\">" + data[i].ntp030NtpDate + "</span>&nbsp;"
                        +                     "<span style=\"font-color:#eeeeee;font-size:12px;line-height:1em;\">" + data[i].ntp030DspFrHour + "時" + data[i].ntp030DspFrMinute + "分～" + data[i].ntp030DspToHour + "時" + data[i].ntp030DspToMinute + "分</span><br>"
                        +                     "<span style=\"font-color:#eeeeee;font-size:13px;line-height:1em;\">" + usrInfMdl.usiSei + "&nbsp;&nbsp;" + usrInfMdl.usiMei + "</span>"
                        +                     "<div style=\"padding-top:10px;padding-bottom:10px;\">"
                        +                     "<span class=\"timeline_title\" onClick=\"return editNippou('edit', " + dataNtpSid + "," + data[i].ntp030UsrSid + ");\">" + data[i].title + "</span>"
                        +                     "</div>"
                        +                       ankenAreaStr
                        +                       companyAreaStr
                        +                   "</div>"
                        +                 "</td>"
                        +               "</tr>"
                        +               "<tr>"
                        +                 "<td class=\"td_wt4\" colspan=\"4\" style=\"padding-top:5px;padding-left:60px;background-color:#f9f9f9;\" align=\"left\">"
                        +                   "<div class=\"naiyouArea" + dataNtpSid + "\">"
                        +                     "<span class=\"dsp_naiyou_" + dataNtpSid + "\">" + data[i].ntp030DspValueStr + "</span>"
                        +                   "</div>"
                        +                   "<div class=\"tempFileArea" + dataNtpSid + " dsp_tmp_file_area_" + dataNtpSid + "\">"
                        +                     tmpStr
                        +                   "</div>"
                        +                   "<div><img src=\"../schedule/images/spacer.gif\" width=\"1px\" height=\"5px\" border=\"0\"></div>"
                        +                 "</td>"
                        +               "</tr>"
                        +               "<tr class=\"commentArea" + dataNtpSid + "\">"
                        +                 "<td class=\"td_wt3\" colspan=\"4\" style=\"padding-left:60px;padding-bottom:5px;background-color:#f9f9f9;\">"
                        +                   "<div style=\"float:left;\">"
                        +                   "<span id=\"goodBtnArea_" + dataNtpSid + "\">"
                        +                     goodBtnStr
                        +                   "</span>"
                        +                   "</div>"
                        +                   "<div style=\"float:left;padding-left:4px;padding-top:2px;\">"
                        +                     "<table>"
                        +                       "<tr>"
                        +                         "<td align=\"center\" class=\"text_good\" id=\"" + dataNtpSid + "\">"
                        +                           "<span id=\"goodCntArea_" + dataNtpSid + "\">" + data[i].ntp030GoodCnt + "</span>"
                        +                         "</td>"
                        +                       "</tr>"
                        +                     "</table>"
                        +                    "</div>"
                        +                 "</td>"
                        +               "</tr>"
                        +               "<tr class=\"ntp030DspComment_tr_" + dataNtpSid + "\">"
                        +                 "<td id=\"ntp030DspComment_" + dataNtpSid + "\" class=\"td_wt4\" colspan=\"4\">"
                        +                   "<span class=\"commentDspArea" + dataNtpSid + "\">"
                        +                     commentStr
                        +                   "</span>"
                        +                 "</td>"
                        +               "</tr>"
                        +               cmtAddAreaStr
                        +            "</table>"
                        +         "</td>"
                        +       "</tr>"
                        +     "</table>"
                        +   "</td>"
                        +   "<td valign=\"top\" width=\"0%\" class=\"timeline\" nowrap >"
                        +     timeLineDateStr
                        +   "</td>"
                        + "</tr>");
                    }
                }


                //オフセットを更新
                $("input[name=ntp030Offset]").val(offset);
                //最終表示日を更新
                $("input[name=ntp030LabelDate]").val(lastLabelDate);

                //出力終了後処理
                obj.data('loading', false);
                $('#pageBottom').html('');

                //コメントエリアのスクリプトを設定
                $('.label_area').inFieldLabels();

                //日付スクロール位置イベント再設定
                $.waypoints('refresh')
                $('.dataDateArea').waypoint(function() {
                    var scldata = $(this).attr('id');
                    var sclOpDate = scldata.substring(0,8);
                    $('#select_fix_date').val(sclOpDate);
                });

            });
        }
    });


    /* 日付スクロール位置イベント */
    $('.dataDateArea').waypoint(function() {
        var scldata = $(this).attr('id');
        var sclOpDate = scldata.substring(0,8);
        var sel = document.getElementById("select_fix_date");

        var writeFlg = 0;

        if (sel.length == 0) {
            $('#select_fix_date').append($('<option>').html(sclOpDate).val(sclOpDate));
        } else {
            for (var op = 0; op < sel.length; op++) {
                if (sel.options[op].text == sclOpDate) {
                    writeFlg = 1;
                }
              }
            if (writeFlg == 0) {
                $('#select_fix_date').append($('<option>').html(sclOpDate).val(sclOpDate));
            }
        }
        writeFlg = 0;

        $('#select_fix_date').val(sclOpDate);

    });

    //案件名クリック
    $(".anken_click").live("click", function(){
        var ankenSid = $(this).attr("id");
        openSubWindow("../nippou/ntp210.do?ntp210NanSid=" + ankenSid);
    });

    //企業名クリック
    $(".comp_click").live("click", function(){
        var compSid = $(this).attr("id");
        openSubWindow("../address/adr250.do?adr250AcoSid=" + compSid);
    });

    //日付選択を隠す(IE対応)
    $("#time_line_fix").addClass('areanone');

    /* 再読み込み時はページの先頭を表示 */
    $('html,body').animate({ scrollTop: 0 }, 'normal');

});

//編集ボタン
function editNippou(cmd, sid, uid){
    document.forms[0].cmd.value=cmd;
    document.forms[0].CMD.value=cmd;
    document.forms[0].ntp010SelectUsrSid.value=uid;
    document.forms[0].ntp010SelectUsrKbn.value=0;
    document.forms[0].ntp010NipSid.value=sid;
    document.forms[0].submit();
    return false;
}

/* コメント 再表示 */
function selConbChange(){
    var selComVal = "#position_" + $('#select_fix_date').val();
    $('html,body').animate({scrollTop: $(selComVal).offset().top - 60},'slow');
}

/* コメント 再表示 */
function setComment(ntpSid, cmtdata){

    if (cmtdata.length > 0) {
        $(".commentDspArea" + ntpSid).remove();
        var commentStr = "";
        for (i=0; i<cmtdata.length; i++) {
            commentStr += "<table class=\"commentDspAreaTable_"
                       +  ntpSid
                       +  "_"
                       +  cmtdata[i].commentSid
                       +  "\"><tbody><tr><td valign=\"top\" style=\"padding-left:60px;padding-top:12px;padding-bottom:20px;\">";

            if (cmtdata[i].commentUserBinSid == "0") {
                //写真なし
                commentStr += "<img class=\"comment_Img\" src=\"../user/images/photo.gif\" name=\"userImage\" alt=\"写真\" border=\"1\" width=\"30px\">";
            } else {
                if (cmtdata[i].commentUsiPictKf == "0") {
                    //写真あり 公開
                    commentStr += "<img class=\"comment_Img\" src=\"../common/cmn100.do?CMD=getImageFile&cmn100binSid="
                               +  cmtdata[i].commentUserBinSid + "\""
                               +  " alt=\"写真\" width=\"30px\">";
                } else {
                    //写真あり 非公開
                    commentStr += "<div class=\"photo_hikokai2\" align=\"center\"><span style=\"color: rgb(252, 41, 41);\">非公開</span></div>";
                }
            }

            //削除
            var delComStr = "";
            if (cmtdata[i].commentDelKbn == 1) {
                delComStr = "&nbsp;<span class=\"commentDel\" id=\""
                +  cmtdata[i].commentSid
                +  "\"><img src=\"../nippou/images/delete_icon2.gif\" alt=\"削除\"></span>";
            }

            commentStr +=  "</td><td  valign=\"top\" style=\"padding-top:5px;padding-left:10px;\" id=\"commentDspAreaTable_"
                       +  ntpSid
                       +  "_"
                       +  cmtdata[i].commentSid
                       +  "\" style=\"padding-top:5px;padding-left:10px;\" valign=\"top\"><span style=\"font-size: 12px; color: rgb(51, 51, 51);\"><b>"
                       +  cmtdata[i].commentUserName
                       +  "</b></span>&nbsp;&nbsp;<span style=\"font-size: 12px; color: rgb(51, 51, 51);\">"
                       +  cmtdata[i].commentDate
                       +  "</span>"
                       +  delComStr
                       + "<br></span><span style=\"font-size: 13px; color: rgb(51, 51, 51);\">"
                       +  cmtdata[i].commentValue
                       +  "</span></td></tr></tbody></table>";
        }

        //コメントテキストエリアを空にする
        $("textarea[name=ntp030Comment_"  + ntpSid + "]").val("");
        $("#ntp030DspComment_"            + ntpSid).html(commentStr);
    }
}

function fileLinkClick(ntpSid, binSid) {
    url = "../nippou/ntp030.do?CMD=fileDownload&ntp010NipSid=" + ntpSid + "&ntp030BinSid=" + binSid;
    navframe.location=url;
}

function replaceHtmlTag(s) {
    return s.replace(/&/g,"&amp;").replace(/"/g,"&quot;").replace(/'/g,"&#039;").replace(/</g,"&lt;").replace(/>/g,"&gt;") ;
}

function changeGroupCombo(){
    document.forms[0].cmd.value='';
    document.forms[0].CMD.value='';
    document.forms[0].submit();
    return false;
}