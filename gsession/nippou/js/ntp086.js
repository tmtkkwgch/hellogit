var checkBoxClickFlg = 0;

function clickMulti() {
    checkBoxClickFlg = 1;
    return false;
}

function clickShohinName(typeNo, itmSid) {

    if (checkBoxClickFlg == 0) {
        //tr押下時
        if (!$('#tr_' + itmSid).children().children().attr('checked')) {
            $('#tr_' + itmSid).children().children().attr('checked','checked');
            if (typeNo == 1) {
                $('#tr_' + itmSid)[0].className = 'td_type_selected';
            } else {
                $('#tr_' + itmSid)[0].className = 'td_type_selected2';
            }
        } else {
            $('#tr_' + itmSid).children().children().attr('checked','');
            var cssName = 'td_line_color' + typeNo;
            $('#tr_' + itmSid)[0].className = cssName;
        }
    } else {
        //checkBox押下時
        if ($('#tr_' + itmSid).children().children().attr('checked')) {
            $('#tr_' + itmSid).children().children().attr('checked','checked');
            if (typeNo == 1) {
                $('#tr_' + itmSid)[0].className = 'td_type_selected';
            } else {
                $('#tr_' + itmSid)[0].className = 'td_type_selected2';
            }
        } else {
            $('#tr_' + itmSid).children().children().attr('checked','');
            var cssName = 'td_line_color' + typeNo;
            $('#tr_' + itmSid)[0].className = cssName;
        }
        checkBoxClickFlg = 0;
    }

    return false;
}

function resetShohinName(typeNo, itmSid) {

    $('#tr_' + itmSid).children().children().attr('checked','');
    var cssName = 'td_line_color' + typeNo;
    $('#tr_' + itmSid)[0].className = cssName;

    return false;
}


$(function() {

    $(".apcUserBtn").live("click", function(){

        $('#tmpUsrArea').children().remove();
        $('#tmpUsrArea').append("<div style=\"padding-top:220px;height:100%;width:100%;text-align:center;\"><img src=\"../nippou/images/ajax-loader.gif\" /></div>");

        //ユーザ一覧取得
        var usrTmpSid = $(this).attr('id');
        var pageNum   = $("input:hidden[name='ntp086pageNum']").val();

        /* ユーザ一覧ポップアップ */
        $('#apcUserPop').dialog({
            autoOpen: true,  // hide dialog
            bgiframe: true,   // for IE6
            resizable: false,
            height: 550,
            width: 400,
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

        //ユーザ一覧成形
        getUsrInfList(usrTmpSid, pageNum);
    });

    //ユーザ一覧 次ページクリック
    $(".nextPageBtn").live("click", function(){
        var paramStr = $(this).attr('id').split(":");
        getUsrInfList(paramStr[0], parseInt(paramStr[1]) + 1);
    });

    //ユーザ一覧 前ページクリック
    $(".prevPageBtn").live("click", function(){
        var paramStr = $(this).attr('id').split(":");
        getUsrInfList(paramStr[0], parseInt(paramStr[1]) - 1);
    });

    //ユーザ一覧 コンボ変更
    $(".selchange").live("change", function(){
        getUsrInfList($(this).attr('id'), $(this).val());
    });

});

function buttonSubmit(mode, sid) {
    document.forms[0].ntp086NttSid.value=sid;
    buttonPush(mode);
}

//ユーザ一覧成形
function getUsrInfList(tmpSid, pageNum) {

    //ユーザ一覧取得
    $.ajaxSetup({async:false});
    $.post('../nippou/ntp086.do', {"cmd":"getUsrList",
                                   "CMD":"getUsrList",
                                   "tmpSid":tmpSid,
                                   "pageNum":pageNum},
      function(data) {
        if (data != null || data != "") {

            $('#tmpUsrArea').children().remove();

            if (data.usrInfDataList != null && data.usrInfDataList.length > 0) {

                var usrInfstr = "";
                var maxpagesize = data.maxPageSize;
                pageNum = data.pageNum;

                //ページング
                if (parseInt(maxpagesize) > 1) {
                    usrInfstr += "<div style=\"width:100%;text-align:right;\">"
                              +  "<img alt=\"前頁\" src=\"../common/images/arrow2_l.gif\" id=\"" + tmpSid + ":" + pageNum + "\" class=\"prevPageBtn cursor_pointer\" border=\"0\" height=\"20\" width=\"20\">"
                              +  "<select name=\"usrInfChange\" id=\"" + tmpSid + "\" class=\"selchange text_i cursor_pointer\">";

                    for (p=1; p <= parseInt(maxpagesize); p++) {
                        if (pageNum == p) {
                            usrInfstr +=  "<option value=\"" + p + "\" selected=\"selected\">" + p + " / " + maxpagesize + "</option>";
                        } else {
                            usrInfstr +=  "<option value=\"" + p + "\">" + p + " / " + maxpagesize + "</option>";
                        }
                    }

                    usrInfstr +=  "</select>"
                              +   "<img src=\"../common/images/arrow2_r.gif\" name=\"btn_next\" alt=\"次頁\" id=\"" + tmpSid + ":" + pageNum + "\" class=\"nextPageBtn cursor_pointer\" border=\"0\" height=\"20\" width=\"20\">"
                              +   "</div>";
                }

                //ユーザ一覧
                usrInfstr += "<table class=\"tl0\" style=\"border-collapse:collapse;\" width=\"100%\">";

                for (i=0; i<data.usrInfDataList.length; i++) {

                    usrInfstr += "<tr height=\"40px\" class=\"cursor_pointer\" onclick=\"return openUserInfoWindow("
                              +  data.usrInfDataList[i].usrSid + ");\">"
                              +  "<td class=\"usr_inf_area_left user_select_area\" height=\"40px\" width=\"55px\"><a href=\"javascript:void(0);\" onclick=\"return openUserInfoWindow("
                              +  data.usrInfDataList[i].usrSid + ");\">";

                    if (data.usrInfDataList[i].binSid == "0") {
                        //写真なし
                        usrInfstr += "<img class=\"comment_Img\" src=\"../user/images/photo.gif\" name=\"userImage\" alt=\"写真\" border=\"1\" width=\"30px\">";
                    } else {
                        if (data.usrInfDataList[i].usiPictKf == "0") {
                            //写真あり 公開
                            usrInfstr  += "<img class=\"comment_Img\" src=\"../common/cmn100.do?CMD=getImageFile&cmn100binSid="
                                       +  data.usrInfDataList[i].binSid + "\""
                                       +  " alt=\"写真\" width=\"30px\">";
                        } else {
                            //写真あり 非公開
                            usrInfstr  += "<div class=\"photo_hikokai3\" align=\"center\"><span style=\"color: rgb(252, 41, 41);\">非公開</span></div>";
                        }
                    }

                    usrInfstr += "</a>"
                              +  "</td>"
                              +  "<td class=\"left_space usr_inf_area_top_bottom2 user_select_area\" align=\"left\" nowrap=\"nowrap\" width=\"100%\">"
                              +  "<span class=\"text_link2\"><a href=\"javascript:void(0);\" onclick=\"return openUserInfoWindow("
                              +  data.usrInfDataList[i].usrSid + ");\">"
                              +  data.usrInfDataList[i].usiSei + "&nbsp;&nbsp;" + data.usrInfDataList[i].usiMei + "\</a></span>"
                              +  "</td>"
                              +  "</tr>";
                }

                usrInfstr += "</table>";


                //ページング
                if (parseInt(maxpagesize) > 1) {
                    usrInfstr += "<div style=\"width:100%;text-align:right;\">"
                              +  "<img alt=\"前頁\" src=\"../common/images/arrow2_l.gif\" id=\"" + tmpSid + ":" + pageNum + "\" class=\"prevPageBtn img_bottom cursor_pointer\" border=\"0\" height=\"20\" width=\"20\">"
                              +  "<select name=\"usrInfChange\" id=\"" + tmpSid + "\" class=\"selchange text_i cursor_pointer\">";

                    for (p=1; p <= parseInt(maxpagesize); p++) {
                        if (pageNum == p) {
                            usrInfstr +=  "<option value=\"" + p + "\" selected=\"selected\">" + p + " / " + maxpagesize + "</option>";
                        } else {
                            usrInfstr +=  "<option value=\"" + p + "\">" + p + " / " + maxpagesize + "</option>";
                        }
                    }

                    usrInfstr +=  "</select>"
                              +  "<img src=\"../common/images/arrow2_r.gif\" name=\"btn_next\" alt=\"次頁\" id=\"" + tmpSid + ":" + pageNum + "\" class=\"nextPageBtn img_bottom cursor_pointer\" border=\"0\" height=\"20\" width=\"20\">"
                              +  "</div>";
                }

                $('#tmpUsrArea').append(usrInfstr);

                /* ユーザリンク hover */
                $('.user_select_area').hover(
                    function () {
                        $(this).removeClass("user_select_area").addClass("user_select_area_hover");
                      },
                      function () {
                          $(this).removeClass("user_select_area_hover").addClass("user_select_area");
                      }
                );
            } else {
                $('#tmpUsrArea').append("<span style=\"padding-top:220px;height:100%;width:100%;font-weight:bold;text-align:center;\">適用するユーザは設定されていません。</span>");
            }
        }
    });
}