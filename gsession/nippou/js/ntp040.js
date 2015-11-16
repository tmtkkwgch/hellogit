function buttonPush(cmd, mod){
    document.forms[0].CMD.value=cmd;
    document.forms[0].cmd.value=mod;
    document.forms[0].submit();
    return false;
}

//戻るボタン
function backButtonPush(cmd, mod){

    var mitourokuFlg = false;
    var mikakuteiFlg = false;
    var notAddPoint = 0;
    var notEditPoint = 0;

    $(".edit_touroku_class").each(function() {
        mitourokuFlg = true;
        notAddPoint = $(this).offset().top;
    });


    $("input[name=ntpCopyBtn]").each(function() {
        if ($(this).parent().css('display') == 'none') {
            mikakuteiFlg = true;
            notEditPoint = $(this).parent().parent().offset().top;
        }
    });

    //未登録,未確定の日報がある場合はメッセージを表示
    if (mitourokuFlg) {

        addDialogOpen(cmd, mod, mikakuteiFlg, notAddPoint, notEditPoint, -1);

    } else if (mikakuteiFlg, notEditPoint) {

        editDialogOpen(cmd, mod, notEditPoint, -1);

    } else {
        buttonPush(cmd, mod);
    }

    return false;
}

function addDialogOpen(cmd, mod, mikakuteiFlg, notAddPoint, notEditPoint, ntpSid) {

    $('#notAddOk').dialog({
        autoOpen: true,
        bgiframe: true,
        resizable: false,
        height: 160,
        width: 380,
        modal: true,
        closeOnEscape: false,
        overlay: {
            backgroundColor: '#000000',
            opacity: 0.5
        },
        buttons: {
            はい: function() {

                $(this).dialog('close');

                if (mikakuteiFlg) {
                    editDialogOpen(cmd, mod, notEditPoint, ntpSid);
                } else {

                    if (ntpSid != -1) {
                        document.forms[0].ntp010NipSid.value=ntpSid;
                    }

                    buttonPush(cmd, mod);
                }

            },
            いいえ: function() {
              $('html,body').animate({scrollTop: notAddPoint - 140},'slow');
              $(this).dialog('close');
            }
        }
    });

}

function editDialogOpen(cmd, mod, notEditPoint, ntpSid) {

    $('#notEditOk').dialog({
        autoOpen: true,
        bgiframe: true,
        resizable: false,
        height: 160,
        width: 390,
        modal: true,
        closeOnEscape: false,
        overlay: {
            backgroundColor: '#000000',
            opacity: 0.5
        },
        buttons: {
            はい: function() {
                $(this).dialog('close');

                if (ntpSid != -1) {
                    document.forms[0].ntp010NipSid.value=ntpSid;
                }

                buttonPush(cmd, mod);
            },
            いいえ: function() {
              $('html,body').animate({scrollTop: notEditPoint - 140},'slow');
              $(this).dialog('close');
            }
        }
    });

}

function changeGroupCombo(cmd){
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function moveUser(cmd){
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function setToDay() {
    if (document.forms[0].ntp040ScrollFlg.value=='1') {
        window.location.hash='add_user';
    }
    var frYear = document.forms[0].ntp040FrYear.value;
    var frMonth = document.forms[0].ntp040FrMonth.value;
    var frDay = document.forms[0].ntp040FrDay.value;
    var frHour = document.forms[0].ntp040FrHour.value;
    var frMinute = document.forms[0].ntp040FrMin.value;
    var toYear = document.forms[0].ntp040ToYear.value;
    var toMonth = document.forms[0].ntp040ToMonth.value;
    var toDay = document.forms[0].ntp040ToDay.value;
    var toHour = document.forms[0].ntp040ToHour.value;
    var toMinute = document.forms[0].ntp040ToMin.value;
    var toMinute = document.forms[0].ntp040ToMin.value;
    var daySameFlg = false;

    if (parseInt(frYear) > parseInt(toYear)) {
        toYear = frYear;
        toMonth = frMonth;
        toDay = frDay;
        document.forms[0].ntp040ToYear.value = frYear;
        document.forms[0].ntp040ToMonth.value = frMonth;
        document.forms[0].ntp040ToDay.value = frDay;
        document.getElementById("betWeenDays").innerHTML = msglist.period + '1' + msglist.days;
    }

    if (parseInt(frYear) == parseInt(toYear)) {
        if (parseInt(frMonth) > parseInt(toMonth)) {
            toMonth = frMonth;
            toDay = frDay;
            document.forms[0].ntp040ToMonth.value = frMonth;
            document.forms[0].ntp040ToDay.value = frDay;
            document.getElementById("betWeenDays").innerHTML = msglist.period + '1' + msglist.days;
        }
    }

    if (parseInt(frYear) == parseInt(toYear)) {
        if (parseInt(frMonth) == parseInt(toMonth)) {
            if (parseInt(frDay) > parseInt(toDay)) {
                toDay = frDay;
                document.forms[0].ntp040ToDay.value = frDay;
                document.getElementById("betWeenDays").innerHTML = msglist.period + '1' + msglist.days;
            }

            if (parseInt(frDay) == parseInt(toDay)) {
                daySameFlg = true;
            }
        }
    }

    if (parseInt(frHour) > -1
     && parseInt(toHour) > -1
     && parseInt(frMinute) > -1
     && parseInt(toMinute) > -1) {

        if (daySameFlg) {

            if (parseInt(frHour) > parseInt(toHour)) {
                toHour = frHour;
                document.forms[0].ntp040ToHour.value = frHour;

            }
            if (parseInt(frHour) == parseInt(toHour)) {
                if (parseInt(frMinute) > parseInt(toMinute)) {
                    document.forms[0].ntp040ToMin.value = frMinute;
                }
            }
        }
    }
}

function setBetweenFromToDayCount() {
    var frYear = document.forms[0].ntp040FrYear.value;
    var frMonth = document.forms[0].ntp040FrMonth.value;
    var frDay = document.forms[0].ntp040FrDay.value;
    var toYear = document.forms[0].ntp040ToYear.value;
    var toMonth = document.forms[0].ntp040ToMonth.value;
    var toDay = document.forms[0].ntp040ToDay.value;
    frDate = new Date(frYear, frMonth - 1, frDay -1, 0, 0, 0);
    toDate = new Date(toYear, toMonth - 1, toDay, 0, 0, 0);
    times = parseInt((toDate.getTime() - frDate.getTime()));

    if (parseInt(times) > 0) {
      days = Math.floor(times / (1000*60*60*24));
      document.getElementById("betWeenDays").innerHTML = msglist.period + days + msglist.days;
    } else {
      document.getElementById("betWeenDays").innerHTML = '';
    }
}

function moveDay(elmYear, elmMonth, elmDay, kbn) {

    systemDate = new Date();

    //「今日ボタン押下」
    if (kbn == 2) {
        $(elmYear).val(convYear(systemDate.getYear()));
        $(elmMonth).val(systemDate.getMonth() + 1);
        $(elmDay).val(systemDate.getDate());
        setToDay();
        setBetweenFromToDayCount();
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

    setToDay();
    setBetweenFromToDayCount();
}

function setTimeStatus() {

        //時間指定無し
        if (document.forms[0].ntp040TimeKbn.checked) {

            //開始時刻
            document.forms[0].ntp040FrHour.disabled=true;
            document.forms[0].ntp040FrMin.disabled=true;

            //終了時刻
            document.forms[0].ntp040ToHour.disabled=true;
            document.forms[0].ntp040ToMin.disabled=true;

            //施設予約を無効
            setReserveInactive();
       } else {
            //開始時刻
            document.forms[0].ntp040FrHour.disabled=false;
            document.forms[0].ntp040FrMin.disabled=false;

            //終了時刻
            document.forms[0].ntp040ToHour.disabled=false;
            document.forms[0].ntp040ToMin.disabled=false;

            //施設予約を有効
            setReserveActive();
       }
}


function selectUsersList() {

    var flg = true;
   if (document.forms[0].ntp040SelectUsersKbn.checked) {
       flg = true;
   } else {
       flg = false;
   }
   oElements = document.getElementsByName("users_l");
   var defUserAry = document.forms[0].users_l.options;
   var defLength = defUserAry.length;
   for (i = defLength - 1; i >= 0; i--) {
       if (defUserAry[i].value != -1) {
           defUserAry[i].selected = flg;
       }
   }
}
function selectResList() {

    var flg = true;
   if (document.forms[0].sch040SelectResKbn.checked) {
       flg = true;
   } else {
       flg = false;
   }
   oElements = document.getElementsByName("reserve_l");
   var defResAry = document.forms[0].reserve_l.options;
   var defLength = defResAry.length;
   for (i = defLength - 1; i >= 0; i--) {
       if (defResAry[i].value != -1) {
           defResAry[i].selected = flg;
       }
   }
}

function deleteCompany(companyId, companyBaseId) {
    document.forms['ntp040Form'].CMD.value = 'deleteCompany';
    document.forms['ntp040Form'].ntp040delCompanyId.value = companyId;
    document.forms['ntp040Form'].ntp040delCompanyBaseId.value = companyBaseId;
    document.forms['ntp040Form'].submit();
    return false;
}

var row_number = 1;
var delBtnId = '';
var ntpDelBtnId = '';
var delCmtNtpId = '';
$(function() {

    //IE6用左側日付、ユーザ名固定
    $('#fix_content').exFixed();

    //ENTERボタンsubmit禁止
    $("#ntpTitleTextBox").keypress(function(ev) {
        if ((ev.which && ev.which === 13) || (ev.keyCode && ev.keyCode === 13)) {
            return false;
        }
    });

    /* 目標 hover */
    $('.target_link_area').hover(
        function () {
            $(this).removeClass("target_link_area").addClass("target_link_area_hover, 1000");
          },
          function () {
              $(this).removeClass("target_link_area_hover").addClass("target_link_area, 1000");
          }
    );


    /* 目標リセットボタン */
    $(".resetTrgBtn").live("click", function(){
        var trgParam = $(this).attr('id').split("_");
        resetTargetRec(trgParam[1], trgParam[2], trgParam[3], trgParam[4]);
    });

    /* 目標リセットボタン */
    $(".changeTrgBtn").live("click", function(){
        var chTrgId = $(this).attr('id');
        $('.recordArea_' + chTrgId).toggle();
        $('.trgBtnArea_' + chTrgId).toggle();
    });

    /* 目標キャンセルボタン */
    $(".target_cansel_btn").live("click", function(){
        var caTrgId = $(this).attr('id');
        var trgCaParam = $(this).attr('id').split("_");
        resetTargetRec2(trgCaParam[0], trgCaParam[1], trgCaParam[2], trgCaParam[3]);
        $('.recordArea_' + caTrgId).toggle();
        $('.trgBtnArea_' + caTrgId).toggle();
    });


    /* 目標確定ボタンクリック */
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
                はい: function() {

                    var recordVal = $('#trgRecord_' + targetId).val();
                    var targetVal = $('#valTrg_'    + targetId).text();

                    //データ送信
                    $.ajaxSetup({async:false});
                    $.post('../nippou/ntp040.do', {"cmd":"trgEdit",
                                                   "CMD":"trgEdit",
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
                                var trgIdParam = targetId.split("_");
                                resetTargetRec2(trgIdParam[0], trgIdParam[1], trgIdParam[2], trgIdParam[3]);
                                $('.recordArea_' + targetId).toggle();
                                $('.trgBtnArea_' + targetId).toggle();
                            }
                        } else {

                        }
                    });

                    $(this).dialog('close');
                },
                いいえ: function() {

                  $(this).dialog('close');

                }
            }
        });

    });

    /* 前の日・今日・次の日 hover */
    $('.date_change_area').hover(
        function () {
            $(this).removeClass("date_change_area").addClass("date_change_area_hover");
          },
        function () {
            $(this).removeClass("date_change_area_hover").addClass("date_change_area");
        }
    );

    $('.ntpPrevBtn').hover(
        function () {
            $('.prevArrowClass').toggle();
          },
        function () {
            $('.prevArrowClass').toggle();
        }
    );

    $('.ntpNextBtn').hover(
        function () {
            $('.nextArrowClass').toggle();
          },
        function () {
            $('.nextArrowClass').toggle();
        }
    );


    /* 前日ボタン */
    $(".ntpPrevBtn").live("click", function(){

        var prevNtpSid = $(this).attr('id');
        document.forms[0].CMD.value='edit';
        document.forms['ntp040Form'].ntp040FrYear.value = '';
        document.forms['ntp040Form'].ntp040FrMonth.value = '';
        document.forms['ntp040Form'].ntp040FrDay.value = '';
        document.forms['ntp040Form'].ntp010DspDate.value = $('input:hidden[name=ntp040PrevNtpDate]').val();
        document.forms['ntp040Form'].ntp010SelectDate.value = $('input:hidden[name=ntp040PrevNtpDate]').val();
        document.forms['ntp040Form'].ntp010NipSid.value = prevNtpSid;

        var mitourokuFlg = false;
        var mikakuteiFlg = false;
        var notAddPoint = 0;
        var notEditPoint = 0;

        $(".edit_touroku_class").each(function() {
            mitourokuFlg = true;
            notAddPoint = $(this).offset().top;
        });


        $("input[name=ntpCopyBtn]").each(function() {
            if ($(this).parent().css('display') == 'none') {
                mikakuteiFlg = true;
                notEditPoint = $(this).parent().parent().offset().top;
            }
        });

        //未登録,未確定の日報がある場合はメッセージを表示
        if (mitourokuFlg) {

            addDialogOpen('edit', 'edit', mikakuteiFlg, notAddPoint, notEditPoint, prevNtpSid);

        } else if (mikakuteiFlg, notEditPoint) {

            editDialogOpen('edit', 'edit', notEditPoint, prevNtpSid);

        } else {
            buttonPush('edit', 'edit');
        }

//        document.forms['ntp040Form'].submit();
        return false;
    });


    /* 今日ボタン */
    $(".ntpTodayBtn").live("click", function(){

        var todayNtpSid = $(this).attr('id');
        document.forms[0].CMD.value='edit';
        document.forms['ntp040Form'].ntp040FrYear.value = '';
        document.forms['ntp040Form'].ntp040FrMonth.value = '';
        document.forms['ntp040Form'].ntp040FrDay.value = '';
        document.forms['ntp040Form'].ntp010DspDate.value = $('input:hidden[name=ntp040TodayNtpDate]').val();
        document.forms['ntp040Form'].ntp010SelectDate.value = $('input:hidden[name=ntp040TodayNtpDate]').val();
        document.forms['ntp040Form'].ntp010NipSid.value = todayNtpSid;

        var mitourokuFlg = false;
        var mikakuteiFlg = false;
        var notAddPoint = 0;
        var notEditPoint = 0;

        $(".edit_touroku_class").each(function() {
            mitourokuFlg = true;
            notAddPoint = $(this).offset().top;
        });


        $("input[name=ntpCopyBtn]").each(function() {
            if ($(this).parent().css('display') == 'none') {
                mikakuteiFlg = true;
                notEditPoint = $(this).parent().parent().offset().top;
            }
        });

        //未登録,未確定の日報がある場合はメッセージを表示
        if (mitourokuFlg) {

            addDialogOpen('edit', 'edit', mikakuteiFlg, notAddPoint, notEditPoint, todayNtpSid);

        } else if (mikakuteiFlg, notEditPoint) {

            editDialogOpen('edit', 'edit', notEditPoint, todayNtpSid);

        } else {
            buttonPush('edit', 'edit');
        }

//        document.forms['ntp040Form'].submit();
        return false;
    });


    /* 翌日ボタン */
    $(".ntpNextBtn").live("click", function(){
        var nextNtpSid = $(this).attr('id');
        document.forms[0].CMD.value='edit';
        document.forms['ntp040Form'].ntp040FrYear.value = '';
        document.forms['ntp040Form'].ntp040FrMonth.value = '';
        document.forms['ntp040Form'].ntp040FrDay.value = '';
        document.forms['ntp040Form'].ntp010DspDate.value = $('input:hidden[name=ntp040NextNtpDate]').val();
        document.forms['ntp040Form'].ntp010SelectDate.value = $('input:hidden[name=ntp040NextNtpDate]').val();
        document.forms['ntp040Form'].ntp010NipSid.value = nextNtpSid;

        var mitourokuFlg = false;
        var mikakuteiFlg = false;
        var notAddPoint = 0;
        var notEditPoint = 0;

        $(".edit_touroku_class").each(function() {
            mitourokuFlg = true;
            notAddPoint = $(this).offset().top;
        });


        $("input[name=ntpCopyBtn]").each(function() {
            if ($(this).parent().css('display') == 'none') {
                mikakuteiFlg = true;
                notEditPoint = $(this).parent().parent().offset().top;
            }
        });

        //未登録,未確定の日報がある場合はメッセージを表示
        if (mitourokuFlg) {

            addDialogOpen('edit', 'edit', mikakuteiFlg, notAddPoint, notEditPoint, nextNtpSid);

        } else if (mikakuteiFlg, notEditPoint) {

            editDialogOpen('edit', 'edit', notEditPoint, nextNtpSid);

        } else {
            buttonPush('edit', 'edit');
        }

//document.forms['ntp040Form'].submit();
        return false;
    });

    /* 投稿ボタン hover */
    $('.btn_base_toukou').live({
          mouseenter:function () {
            $(this).addClass("btn_base_toukou_hover");
          },
          mouseleave:function () {
              $(this).removeClass("btn_base_toukou_hover");
          }
    });

    /* 投稿ボタン */
    $("input[name=commentBtn]").live("click", function(){

        var rownum = $(this).parent().attr("id");
        var trId = "";

        /* ntp010DspGpSid */
        var ntp010DspGpSid       = document.forms['ntp040Form'].ntp010DspGpSid.value;

        if (rownum != null && rownum != "") {
            trId = "_" + rownum;
        }
        var commentStr = $("textarea[name=ntp040Comment" + trId + "]").val();


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
            $.post('../nippou/ntp040.do', {"cmd":"addComment",
                                           "CMD":"addComment",
                                           "commentNtpSid":commentNtpSid,
                                           "commentStr":commentStr,
                                           "rowNum":rownum,
                                           "ntp010DspGpSid":ntp010DspGpSid},
              function(data) {
                if (data != null || data != "") {
                    setComment(trId, commentNtpSid, rownum, data);
                }
            });
         }
    });



    /* コメント削除リンク */
    $(".commentDel").live("click", function(){

        var rownum = $(this).parent().attr("id");
        var trId = "";
        if (rownum != null && rownum != "") {
            trId = "_" + rownum;
        }

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
          はい: function() {

            //コメント送信,最新データ取得,画面書き換え
            $.ajaxSetup({async:false});
            $.post('../nippou/ntp040.do', {"cmd":"delComment",
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
          いいえ: function() {
            $(this).dialog('close');
          }
        }
    });




    /* 行追加ボタン */
    $("#trAddBtn").live("click", function(){
        addNewRow();
    });

    /* 行追加ボタン(編集時) */
    $("#trAddBtnInEdit").live("click", function(){


        row_number = $("#editLastRowNum").val();

        //前の日報で設定されている終了時を取得
        var toRowId = "";
        var nextFromHour = "";
        var nextToHour = "";


        toRowId = "_" + row_number;


        nextFromHour = parseInt($("select[name=ntp040ToHour" + toRowId + "]").val());

        nextToHour   = parseInt(nextFromHour) + 1;

        if (nextFromHour >= 33) {
            nextFromHour = 33;
        }

        if (nextToHour >= 33) {
            nextToHour = 33;
        }

        var td_class = 'td_wt';
        row_number++;


        /* option 時 */
        var frhourOptionStr = "";
        var tohourOptionStr = "";
        var frHourSelect    = "";
        var toHourSelect    = "";

        if (nextFromHour == "" || isNaN(nextFromHour)) {
            frHourSelect = $('#frhourhide').val();
        } else {
            frHourSelect = nextFromHour;
        }

        if (nextToHour == "" || isNaN(nextToHour)) {
            toHourSelect = $('#tohourhide').val();
        } else {
            toHourSelect = nextToHour;
        }


        $('.hourclass').each(function() {
            var hourStr = $(this).attr('value').split("_");
            if (hourStr[0] == frHourSelect) {
                frhourOptionStr += "<option value=\"" + hourStr[0] + "\" selected=\"selected\">" + hourStr[1] + "</option>";
            } else {
                frhourOptionStr += "<option value=\"" + hourStr[0] + "\">" + hourStr[1] + "</option>";
            }

            if (hourStr[0] == toHourSelect) {
                tohourOptionStr += "<option value=\"" + hourStr[0] + "\" selected=\"selected\">" + hourStr[1] + "</option>";
            } else {
                tohourOptionStr += "<option value=\"" + hourStr[0] + "\">" + hourStr[1] + "</option>";
            }
        });

        /* option 分 */
        var frminOptionStr = "";
        var tominOptionStr = "";
        var frMinSelect = $('#frminhide').val();
        var toMinSelect = $('#tominhide').val();

        $('.minclass').each(function() {
            var minStr = $(this).attr('value').split("_");
            if (minStr[0] == frMinSelect) {
                frminOptionStr += "<option value=\"" + minStr[0] + "\" selected=\"selected\">" + minStr[1] + "</option>";
            } else {
                frminOptionStr += "<option value=\"" + minStr[0] + "\">" + minStr[1] + "</option>";
            }

            if (minStr[0] == toMinSelect) {
                tominOptionStr += "<option value=\"" + minStr[0] + "\" selected=\"selected\">" + minStr[1] + "</option>";
            } else {
                tominOptionStr += "<option value=\"" + minStr[0] + "\">" + minStr[1] + "</option>";
            }
        });


        /* ユーザ情報・タイトル・時間 */
        var usrInfPhotoStr = "";
        var ntpUsrBinSid  = $('input:hidden[name=ntp040UsrBinSid]').val();
        var ntpUsrPctKbn  = $('input:hidden[name=ntp040UsrPctKbn]').val();
        var ntpUsrName    = $('input:hidden[name=ntp040UsrName]').val();

        if (ntpUsrBinSid == "0") {
            //写真なし
            usrInfPhotoStr += "<img class=\"comment_Img\" src=\"../user/images/photo.gif\" name=\"userImage\" alt=\"写真\" border=\"1\" width=\"50px\">";
        } else {
            if (ntpUsrPctKbn == "0") {
                //写真あり 公開
                usrInfPhotoStr += "<img class=\"comment_Img\" src=\"../common/cmn100.do?CMD=getImageFile&cmn100binSid="
                           +  ntpUsrBinSid + "\""
                           +  " alt=\"写真\" width=\"50px\">";
            } else {
                //写真あり 非公開
                usrInfPhotoStr += "<div class=\"photo_hikokai2\" align=\"center\"><span style=\"color: rgb(252, 41, 41);\">非公開</span></div>";
            }
        }

        var usrInfAreaStr = "";
        usrInfAreaStr = "<tr class=\"usrInfArea" + row_number + "\" style=\"display:none;\">"
                      +  "<td class=\"td_wt\" style=\"background-color:#fafafa;\" colspan=\"5\">"
                      +  "<table>"
                      +  "<tr>"
                      +  "<td>"
                      +  usrInfPhotoStr
                      +  "</td>"
                      +  "<td style=\"padding-left:10px;\">"
                      +  "<span style=\"font-size:12px;font-weight:bold;\">"
                      +  ntpUsrName
                      +  "</span>"
                      +  "<div>"
                      +  "<span class=\"dsp_frhour_" + row_number + "\"></span>"
                      +  " 時 "
                      +  "<span class=\"dsp_frminute_" + row_number + "\"></span>"
                      +  " 分 "
                      +  " ～ "
                      +  "<span class=\"dsp_tohour_" + row_number + "\"></span>"
                      +  " 時 "
                      +  "<span class=\"dsp_tominute_" + row_number + "\"></span>"
                      +  " 分 "
                      +  "&nbsp;&nbsp;&nbsp;&nbsp;<span id=\"betWeenDays\" class=\"text_base\"></span>"
                      +  "</div>"
                      +  "<div>"
                      +  "<span style=\"font-weight:bold;\" class=\"dsp_title_" + row_number + "\"></span>"
                      +  "</div>"
                      +  "</td>"
                      +  "</tr>"
                      +  "</table>"
                      +  "</td>"
                      +  "</tr>";


        /* 案件    企業・顧客 */
        var ankenCompanyStr = "";
        var noAnkenCompanyStr= "";
        if ($('input:hidden[name=ntp040AnkenCompanyUse]').val() == 0) {
            //両方
            ankenCompanyStr = "<tr>"
                            + "<td class=\"table_bg_A5B4E1\" nowrap=\"\"><span class=\"text_bb1\">案件</span><span class=\"text_r2\"></span></td>"
                            + "<td class=\"td_wt ntp_add_td\" align=\"left\" width=\"30%\" nowrap>"
                            + "<div style=\"display: none;\" class=\"ankenDataArea" + row_number + "\">"
                            + "<img src=\"../schedule/images/spacer.gif\" border=\"0\" height=\"10px\" width=\"1px\">"
                            + "<span class=\"text_anken_code\">案件コード：<span class=\"anken_code_name_" + row_number + "\"></span></span><br>"
                            + "<div class=\"text_anken\">"
                            + "<a id=\"\" class=\"sc_link anken_click\">"
                            + "<span class=\"anken_name_" + row_number + "\"></span>"
                            + "</a>"
                            + "</div>"
                            + "</div>"
                            + "<div class=\"ankenDataArea" + row_number + "\" style=\"\">"
                            + "<input class=\"btn_search_n1\" value=\"案件検索\" onclick=\"return openAnkenWindow('ntp040','" + row_number + "')\" type=\"button\">&nbsp;"
                            + "<input type=\"button\" class=\"ankenHistoryPop btn_history\" id=\"" + row_number + "\" value=\"履歴\" /><br>"
                            + "<img src=\"../schedule/images/spacer.gif\" border=\"0\" height=\"10px\" width=\"1px\">"
                            + "<div id=\"ntp040AnkenIdArea_" + row_number + "\">"
                            + "</div>"
                            + "<div id=\"ntp040AnkenCodeArea_" + row_number + "\">"
                            + "<span class=\"text_anken_code\">案件コード：<span class=\"anken_code_name_" + row_number + "\"></span></span>"
                            + "</div>"
                            + "<div id=\"ntp040AnkenNameArea_" + row_number + "\">"
                            + "<div class=\"text_anken\">"
                            + "<a id=\"\" class=\"sc_link anken_click\">"
                            + "<span class=\"anken_name_" + row_number + "\"></span>"
                            + "</a>"
                            + "</div>"
                            //+ "<a href=\"javascript:void(0);\" onclick=\"delAnken('ntp040','_" + row_number + "');\"><img src=\"../common/images/delete.gif\" class=\"img_bottom\" alt=\"\" width=\"15\"></a>"
                            + "</div>"
                            + "</div>"
                            + "</td>"
                            + "<td class=\"table_bg_A5B4E1\" nowrap=\"\"><span class=\"text_bb1\">企業・顧客</span><span class=\"text_r2\"></span></td>"
                            + "<td class=\"td_wt ntp_add_td\" align=\"left\">"
                            + "<div style=\"display: none;\" class=\"kigyouDataArea" + row_number + "\">"
                            + "<img src=\"../schedule/images/spacer.gif\" border=\"0\" height=\"10px\" width=\"1px\">"
                            + "<span class=\"text_anken_code\">企業コード：<span class=\"comp_code_name_" + row_number + "\"></span></span>"
                            + "<div class=\"text_company\">"
                            + "<a id=\"\" class=\"sc_link comp_click comp_name_link_" + row_number + "\">"
                            + "<span class=\"comp_name_" + row_number + "\"></span>"
                            + "</a>"
                            + "</div>"
                            + "</div>"
                            + "<div class=\"kigyouDataArea" + row_number + "\" style=\"\">"
                            + "<input class=\"btn_address_n2\" value=\"アドレス帳\" onclick=\"return openCompanyWindow2('ntp040'," + row_number + ")\" type=\"button\">&nbsp;"
                            + "<input class=\"adrHistoryPop btn_history\" id=\"" + row_number + "\" value=\"履歴\" type=\"button\"><br>"
                            + "<img src=\"../schedule/images/spacer.gif\" border=\"0\" height=\"10px\" width=\"1px\">"
                            + "<div id=\"ntp040CompanyIdArea_" + row_number + "\">"
                            + "</div>"
                            + "<div id=\"ntp040CompanyBaseIdArea_" + row_number + "\">"
                            + "</div>"
                            + "<div id=\"ntp040CompanyCodeArea_" + row_number + "\">"
                            + "<span class=\"text_anken_code\">企業コード：<span class=\"comp_code_name_" + row_number + "\"></span></span>"
                            + "</div>"
                            + "<div id=\"ntp040CompNameArea_" + row_number + "\">"
                            + "<div class=\"text_company\">"
                            + "<a id=\"\" class=\"sc_link comp_click \">"
                            + "<span class=\"comp_name_" + row_number + "\"></span>"
                            + "</a>"
                            //+ "<a href=\"javascript:void(0);\" onclick=\"delCompany('ntp040','_" + row_number + "');\"><img src=\"../common/images/delete.gif\ class=\"img_bottom\" alt=\"\" width=\"15\"></a>"
                            + "</div>"
                            + "</div>"
                            + "<div id=\"ntp040AddressIdArea_" + row_number + "\">"
                            + "</div>"
                            + "<div id=\"ntp040AddressNameArea_" + row_number + "\">"
                            + "</div>"
                            + "</div>"
                            + "</td>"
                            + "</tr>";

        } else if ($('input:hidden[name=ntp040AnkenCompanyUse]').val() == 1) {
            //案件のみ
            ankenCompanyStr = "<tr>"
                            + "<td class=\"table_bg_A5B4E1\" nowrap><span class=\"text_bb1\">案件</span></td>"
                            + "<td width=\"30%\" align=\"left\" class=\"td_ntp_wt2 ntp_add_td\" nowrap>"
                            + "<div style=\"display: none;\" class=\"ankenDataArea"   + row_number + "\">"
                            + "<img src=\"../schedule/images/spacer.gif\" border=\"0\" height=\"10px\" width=\"1px\">"
                            + "<span class=\"text_anken_code\">案件コード：<span class=\"anken_code_name_"   + row_number + "\"></span></span><br>"
                            + "<div class=\"text_anken\">"
                            + "<a id=\"\" class=\"sc_link anken_click\">"
                            + "<span class=\"anken_name_" + row_number + "\"></span>"
                            + "</a>"
                            + "</div>"
                            + "</div>"
                            + "<div class=\"ankenDataArea" + row_number + "\" style=\"\">"
                            + "<input class=\"btn_search_n1\" value=\"案件検索\" onclick=\"return openAnkenWindow('ntp040','" + row_number + "')\" type=\"button\">&nbsp"
                            + "<input type=\"button\" class=\"ankenHistoryPop btn_history\" id=\"" + row_number + "\" value=\"履歴\" /><br>"
                            + "<img src=\"../schedule/images/spacer.gif\" border=\"0\" height=\"10px\" width=\"1px\">"
                            + "<div id=\"ntp040AnkenIdArea_" + row_number + "\">"
                            + "</div>"
                            + "<div id=\"ntp040AnkenCodeArea_" + row_number + "\">"
                            + "<span class=\"text_anken_code\">案件コード：<span class=\"anken_code_name_" + row_number + "\"></span></span>"
                            + "</div>"
                            + "<div id=\"ntp040AnkenNameArea_" + row_number + "\">"
                            + "<div class=\"text_anken\">"
                            + "<a id=\"\" class=\"sc_link anken_click\">"
                            + "<span class=\"anken_name_" + row_number + "\"></span>"
                            + "</a>"
                            + "</div>"
                            //+ "<a href=\"javascript:void(0);\" onclick=\"delAnken('ntp040','_" + row_number + "');\"><img src=\"../common/images/delete.gif\" class=\"img_bottom\" alt=\"\" width=\"15\"></a>"
                            + "</div>"
                            + "</div>"
                            + "</td>"
                            + "<td class=\"td_ntp_wt4\"></td><td align=\"left\" class=\"td_ntp_wt3\" ></td>"
                            + "</tr>";
        } else if ($('input:hidden[name=ntp040AnkenCompanyUse]').val() == 2) {
            //企業・顧客のみ
            ankenCompanyStr = "<tr>"
                            + "<td class=\"table_bg_A5B4E1\" nowrap><span class=\"text_bb1\">企業・顧客</span></td>"
                            + "<td align=\"left\" class=\"td_ntp_wt2 ntp_add_td\" >"
                            + "<div style=\"display: none;\" class=\"kigyouDataArea" + row_number + "\">"
                            + "<img src=\"../schedule/images/spacer.gif\" border=\"0\" height=\"10px\" width=\"1px\">"
                            + "<span class=\"text_anken_code\">企業コード：<span class=\"comp_code_name_" + row_number + "\"></span></span>"
                            + "<div class=\"text_company\">"
                            + "<a id=\"\" class=\"sc_link comp_click comp_name_link_" + row_number + "\">"
                            + "<span class=\"comp_name_" + row_number + "\"></span>"
                            + "</a>"
                            + "</div>"
                            + "</div>"
                            + "<div class=\"kigyouDataArea" + row_number + "\" style=\"\">"
                            + "<input class=\"btn_address_n2\" value=\"アドレス帳\" onclick=\"return openCompanyWindow2('ntp040'," + row_number + ")\" type=\"button\">&nbsp;"
                            + "<input class=\"adrHistoryPop btn_history\" id=\"" + row_number + "\" value=\"履歴\" type=\"button\"><br>"
                            + "<img src=\"../schedule/images/spacer.gif\" border=\"0\" height=\"10px\" width=\"1px\">"
                            + "<div id=\"ntp040CompanyIdArea_" + row_number + "\">"
                            + "</div>"
                            + "<div id=\"ntp040CompanyBaseIdArea_" + row_number + "\">"
                            + "</div>"
                            + "<div id=\"ntp040CompanyCodeArea_" + row_number + "\">"
                            + "<span class=\"text_anken_code\">企業コード：<span class=\"comp_code_name_" + row_number + "\"></span></span>"
                            + "</div>"
                            + "<div id=\"ntp040CompNameArea_" + row_number + "\">"
                            + "<div class=\"text_company\">"
                            + "<a id=\"\" class=\"sc_link comp_click\">"
                            + "<span class=\"comp_name_" + row_number + "\"></span>"
                            + "</a>"
                            //+ "<a href=\"javascript:void(0);\" onclick=\"delCompany('ntp040','_" + row_number + "');\"><img src=\"../common/images/delete.gif\ class=\"img_bottom\" alt=\"\" width=\"15\"></a>"
                            + "</div>"
                            + "</div>"
                            + "<div id=\"ntp040AddressIdArea_" + row_number + "\">"
                            + "</div>"
                            + "<div id=\"ntp040AddressNameArea_" + row_number + "\">"
                            + "</div>"
                            + "</div>"
                            + "</td>"
                            + "<td class=\"td_ntp_wt4\"></td><td align=\"left\" class=\"td_ntp_wt3\" ></td>"
                            + "</tr>";
        } else if ($('input:hidden[name=ntp040AnkenCompanyUse]').val() == 3) {
            //なし
            noAnkenCompanyStr = "<tr><td></td><td></td><td></td><td></td></tr>";
        }



        /* 年コンボ */
        var yearOptionStr = "";
        var yearSelect    = $('input:hidden[name=ntp040InitYear]').val();
        $('.yearclass').each(function() {
            var yearStr = $(this).attr('value').split("_");
            if (yearStr[0] == yearSelect) {
                yearOptionStr += "<option value=\"" + yearStr[0] + "\" selected=\"selected\">" + replaceHtmlTag(yearStr[1]) + "</option>";
            } else {
                yearOptionStr += "<option value=\"" + yearStr[0] + "\">" + replaceHtmlTag(yearStr[1]) + "</option>";
            }
        });

        /* 月コンボ */
        var monthOptionStr = "";
        var monthSelect    = $('input:hidden[name=ntp040InitMonth]').val();
        $('.monthclass').each(function() {
            var monthStr = $(this).attr('value').split("_");
            if (monthStr[0] == monthSelect) {
                monthOptionStr += "<option value=\"" + monthStr[0] + "\" selected=\"selected\">" + replaceHtmlTag(monthStr[1]) + "</option>";
            } else {
                monthOptionStr += "<option value=\"" + monthStr[0] + "\">" + replaceHtmlTag(monthStr[1]) + "</option>";
            }
        });

        /* 日コンボ */
        var dayOptionStr = "";
        var daySelect    = $('input:hidden[name=ntp040InitDay]').val();
        $('.dayclass').each(function() {
            var dayStr = $(this).attr('value').split("_");
            if (dayStr[0] == daySelect) {
                dayOptionStr += "<option value=\"" + dayStr[0] + "\" selected=\"selected\">" + replaceHtmlTag(dayStr[1]) + "</option>";
            } else {
                dayOptionStr += "<option value=\"" + dayStr[0] + "\">" + replaceHtmlTag(dayStr[1]) + "</option>";
            }
        });

        /* 活動分類 */
        var ktbunruiHouhouStr = "";
        if ($('input:hidden[name=ntp040KtBriHhuUse]').val() == 0) {

            /* option 活動分類 */
            var ktbunruiOptionStr = "";
            //var frBunruiSelect = $('#ktbunruihide').val();
            var frBunruiSelect = "";

            $('.ktbunruiclass').each(function() {
                var bunruiStr = $(this).attr('value').split("_");
                if (bunruiStr[0] == frBunruiSelect) {
                    ktbunruiOptionStr += "<option value=\"" + bunruiStr[0] + "\" selected=\"selected\">" + replaceHtmlTag(bunruiStr[1]) + "</option>";
                } else {
                    ktbunruiOptionStr += "<option value=\"" + bunruiStr[0] + "\">" + replaceHtmlTag(bunruiStr[1]) + "</option>";
                }
            });


            /* option 活動方法 */
            var kthouhouOptionStr = "";
            //var houhouSelect = $('#kthouhouhide').val();
            var houhouSelect = "";

            $('.kthouhouclass').each(function() {
                var houhouStr = $(this).attr('value').split("_");
                if (houhouStr[0] == houhouSelect) {
                    kthouhouOptionStr += "<option value=\"" + houhouStr[0] + "\" selected=\"selected\">" + replaceHtmlTag(houhouStr[1]) + "</option>";
                } else {
                    kthouhouOptionStr += "<option value=\"" + houhouStr[0] + "\">" + replaceHtmlTag(houhouStr[1]) + "</option>";
                }
            });

            ktbunruiHouhouStr = "<tr>"
                              + "<td class=\"table_bg_A5B4E1\" nowrap=\"\"><span class=\"text_bb1\">活動分類/方法</span></td>"
                              + "<td colspan=\"3\" class=\"td_wt ntp_add_td\" align=\"left\">"
                              + "<div style=\"display: none;\" class=\"ktBunruiArea" + row_number + "\">"
                              + "<span class=\"dsp_ktbunrui_" + row_number + "\"></span>&nbsp;"
                              + "<span class=\"dsp_kthouhou_" + row_number + "\"></span>"
                              + "</div>"
                              + "<div class=\"ktBunruiArea" + row_number + "\" style=\"\">"
                              + "<select name=\"ntp040Ktbunrui_" + row_number + "\">"
                              + ktbunruiOptionStr
                              + "</select>&nbsp;"
                              + "<select name=\"ntp040Kthouhou_" + row_number + "\">"
                              + kthouhouOptionStr
                              + "</select>"
                              + "</div>"
                              + "</td>"
                              + "</tr>";
        }


        /* 見込み度 */
        var mikomidoStr = "";
        var mikomidoStandStr ="";
        if ($('input:hidden[name=ntp040MikomidoUse]').val() == 0) {

            //見込み度基準
            if ($('input:hidden[name=ntp040MikomidoFlg]').val() == 1) {
                mikomidoStandStr = "<br><input class=\"mikomido_btn mikomido_back\" type=\"button\" value=\"基 準\" />";
            }

            mikomidoStr ="<tr>"
                        + "<td class=\"table_bg_A5B4E1\" nowrap=\"nowrap\"><span class=\"text_bb1\">見込み度</span></td>"
                        + "<td class=\"td_wt ntp_add_td\" colspan=\"3\" align=\"left\">"
                        + "<div style=\"display: none;\" class=\"mikomidoArea" + row_number + "\">"
                        + "<span class=\"text_base\">"
                        + "<span class=\"dsp_mikomido_" + row_number + "\">10</span>％"
                        + "</span>"
                        + "</div>"
                        + "<div class=\"mikomidoArea" + row_number + "\" style=\"\">"
                        + "<span class=\"text_base\">"
                        + "<input name=\"ntp040Mikomido_" + row_number + "\" value=\"0\" checked=\"checked\" id=\"ntp040Mikomido0" + row_number + "\" type=\"radio\"><label for=\"ntp040Mikomido0" + row_number + "\">10%</label>"
                        + "<input name=\"ntp040Mikomido_" + row_number + "\" value=\"1\" id=\"ntp040Mikomido1" + row_number + "\" type=\"radio\"><label for=\"ntp040Mikomido1" + row_number + "\">30%</label>"
                        + "<input name=\"ntp040Mikomido_" + row_number + "\" value=\"2\" id=\"ntp040Mikomido2" + row_number + "\" type=\"radio\"><label for=\"ntp040Mikomido2" + row_number + "\">50%</label>"
                        + "<input name=\"ntp040Mikomido_" + row_number + "\" value=\"3\" id=\"ntp040Mikomido3" + row_number + "\" type=\"radio\"><label for=\"ntp040Mikomido3" + row_number + "\">70%</label>"
                        + "<input name=\"ntp040Mikomido_" + row_number + "\" value=\"4\" id=\"ntp040Mikomido4" + row_number + "\" type=\"radio\"><label for=\"ntp040Mikomido4" + row_number + "\">100%</label>"
                        + "</span>"
                        + mikomidoStandStr
                        + "</div>"
                        + "</td>"
                        + "</tr>";

        }


        /* 添付ファイル */
        var tempFileStr = "";
        if ($('input:hidden[name=ntp040TmpFileUse]').val() == 0) {
            tempFileStr = "<tr>"
                        + "<td class=\"table_bg_A5B4E1\" nowrap=\"\"><span class=\"text_bb1\">添付<a id=\"naiyou\" name=\"naiyou\"></a></span></td>"
                        + "<td class=\"td_wt ntp_add_td\" colspan=\"3\" align=\"left\">"
                        + "<div style=\"display: none;\" class=\"tempFileArea" + row_number + " dsp_tmp_file_area_" + row_number + "\"></div>"
                        + "<div class=\"tempFileArea" + row_number + "\" style=\"\">"
                        + "<select id=\"ntp040selFile" + row_number + "\" name=\"ntp040selectFiles" + row_number + "\" multiple=\"multiple\" size=\"3\" class=\"select01\"></select>"
                        + "<input class=\"btn_attach\" value=\"添付\" name=\"attacheBtn\" onclick=\"opnTempPlus('ntp040selectFiles" + row_number + "', 'nippou', '0', '0', 'row" + row_number + "');\" type=\"button\">&nbsp;"
                        + "<input class=\"btn_delete\" name=\"tempDelBtn\" id=\"" + row_number + "\" value=\"削除\" type=\"button\">"
                        + "</div>"
                        + "</td>"
                        + "</tr>"
        }

        //内容初期値
        var defaultValue = "";
        if ($('input:hidden[name=ntp040DefaultValue]').val() != null) {
            defaultValue = $('input:hidden[name=ntp040DefaultValue]').val();
        }

        var defaultLength = "0";
        if ($('input:hidden[name=ntp040DefaultValue2]').val() != null) {
            var defaultValue2 = "";
            defaultValue2 = $('input:hidden[name=ntp040DefaultValue2]').val();
            defaultLength = defaultValue2.length;
        }


        //カラーコメント
        var titileColStr1 = replaceHtmlTag($('#msgCol1').val());
        var titileColStr2 = replaceHtmlTag($('#msgCol2').val());
        var titileColStr3 = replaceHtmlTag($('#msgCol3').val());
        var titileColStr4 = replaceHtmlTag($('#msgCol4').val());
        var titileColStr5 = replaceHtmlTag($('#msgCol5').val());



        /* 次のアクション */
        var actionAreaStr = "";
        if ($('input:hidden[name=ntp040NextActionUse]').val() == 0) {

            /* 年コンボ */
            var yearActionOptionStr = "";
            var yearActionSelect    = $('input:hidden[name=ntp040InitYear]').val();
            $('.yearclass').each(function() {
                var yearActionStr = $(this).attr('value').split("_");
                if (yearActionStr[0] == yearActionSelect) {
                    yearActionOptionStr += "<option value=\"" + yearActionStr[0] + "\" selected=\"selected\">" + replaceHtmlTag(yearActionStr[1]) + "</option>";
                } else {
                    yearActionOptionStr += "<option value=\"" + yearActionStr[0] + "\">" + replaceHtmlTag(yearActionStr[1]) + "</option>";
                }
            });

            /* 月コンボ */
            var monthActionOptionStr = "";
            var monthActionSelect    = $('input:hidden[name=ntp040InitMonth]').val();
            $('.monthclass').each(function() {
                var monthActionStr = $(this).attr('value').split("_");
                if (monthActionStr[0] == monthActionSelect) {
                    monthActionOptionStr += "<option value=\"" + monthActionStr[0] + "\" selected=\"selected\">" + replaceHtmlTag(monthActionStr[1]) + "</option>";
                } else {
                    monthActionOptionStr += "<option value=\"" + monthActionStr[0] + "\">" + replaceHtmlTag(monthActionStr[1]) + "</option>";
                }
            });

            /* 日コンボ */
            var dayActionOptionStr = "";
            var dayActionSelect    = $('input:hidden[name=ntp040InitDay]').val();
            $('.dayclass').each(function() {
                var dayActionStr = $(this).attr('value').split("_");
                if (dayActionStr[0] == dayActionSelect) {
                    dayActionOptionStr += "<option value=\"" + dayActionStr[0] + "\" selected=\"selected\">" + replaceHtmlTag(dayActionStr[1]) + "</option>";
                } else {
                    dayActionOptionStr += "<option value=\"" + dayActionStr[0] + "\">" + replaceHtmlTag(dayActionStr[1]) + "</option>";
                }
            });


            actionAreaStr = "<tr>"
                         + "<td class=\"table_bg_A5B4E1\" nowrap=\"nowrap\"><span class=\"text_bb1\">次のアクション<a id=\"nextAction\" name=\"nextAction\"></a></span></td>"
                         + "<td class=\"td_wt ntp_add_td\" colspan=\"3\" align=\"left\">"
                         + "<div style=\"display: none;\" class=\"nextActionArea" + row_number + "\">"
                         + "<span id=\"actionSelDateArea_" + row_number + "\" style=\"color:#000000;font-size:12px;font-weight:bold;\">"
                         + "</span>"
                         + "<span class=\"dsp_nextaction_" + row_number + "\"></span>"
                         + "</div>"
                         + "<div class=\"nextActionArea" + row_number + "\" style=\"\">"
                         + "<span style=\"color:#000000;font-size:12px;font-weight:bold;\">&nbsp;日付指定：</span>"
                         + "<input name=\"ntp040ActDateKbn_" + row_number + "\" value=\"1\" onchange=\"toggleActionAreaShow('nxtActDateArea_" + row_number + "');\" id=\"actDate1_" + row_number + "\" type=\"radio\">"
                         + "<label for=\"actDate1_" + row_number + "\" class=\"text_base\" style=\"color:#000000;font-size:12px;\">する</label>"
                         + "<input name=\"ntp040ActDateKbn_" + row_number + "\" value=\"0\" checked=\"checked\" onchange=\"toggleActionAreaHide('nxtActDateArea_" + row_number + "');\" id=\"actDate0_" + row_number + "\" type=\"radio\">"
                         + "<label for=\"actDate0_" + row_number + "\" class=\"text_base\" style=\"color:#000000;font-size:12px;\">しない</label>"
                         + "<br>"
                         + "<div id=\"nxtActDateArea_" + row_number + "\" style=\"display:none;\">"
                         + "<select name=\"selActionYear" + row_number + "\" id=\"selActionYear" + row_number + "\">"
                         + yearActionOptionStr
                         + "</select>&nbsp;"
                         + "<select name=\"selActionMonth" + row_number + "\" id=\"selActionMonth" + row_number + "\">"
                         + monthActionOptionStr
                         + "</select>&nbsp;"
                         + "<select name=\"selActionDay" + row_number + "\" id=\"selActionDay" + row_number + "\">"
                         + dayActionOptionStr
                         + "</select>&nbsp;"
                         + "<input class=\"btn_arrow_l\" value=\"&nbsp;\" onclick=\"return moveDay($('#selActionYear" + row_number + "')[0], $('#selActionMonth" + row_number + "')[0], $('#selActionDay" + row_number + "')[0], 1)\" type=\"button\">&nbsp;"
                         + "<input class=\"btn_today\" value=\"今日\" onclick=\"return moveDay($('#selActionYear" + row_number + "')[0], $('#selActionMonth" + row_number + "')[0], $('#selActionDay" + row_number + "')[0], 2)\" type=\"button\">&nbsp;"
                         + "<input class=\"btn_arrow_r\" value=\"&nbsp;\" onclick=\"return moveDay($('#selActionYear" + row_number + "')[0], $('#selActionMonth" + row_number + "')[0], $('#selActionDay" + row_number + "')[0], 3)\" type=\"button\">&nbsp;"
                         + "<input value=\"Cal\" onclick=\"wrtCalendarByBtn(this.form.selActionDay" + row_number + ", this.form.selActionMonth" + row_number + ", this.form.selActionYear" + row_number + ", 'ntp040ActionCalBtn" + row_number + "')\" class=\"calendar_btn\" id=\"ntp040ActionCalBtn" + row_number + "\" type=\"button\">"
                         + "<br>"
                         + "</div>"
                         + "<textarea id=\"actionstr_" + row_number + "\" name=\"ntp040NextAction_" + row_number + "\" style=\"width:431px;\" rows=\"2\" onkeyup=\"showLengthStr(value, 1000, 'actionlength" + row_number + "');\"></textarea>"
                         + "<br>"
                         + "<span class=\"font_string_count\">現在の文字数:</span><span id=\"actionlength" + row_number + "\" class=\"font_string_count\">0</span>&nbsp;<span class=\"font_string_count_max\">/&nbsp;1000&nbsp;文字</span>"
                         + "</div>"
                         + "</td>"
                         + "</tr>";
        }

        /* 日報行追加 */
        $('#nippoutable').append("<tr id=\"nippou_data_" + row_number + "\">"
                + "<td colspan=\"6\">"
                + "<table width=\"100%\"><tbody><tr><td>"
                + "</td></tr><tr>"
                + "<td colspan=\"6\" height=\"25px\"></td>"
                + "</tr>"
                + "<tr id=\"" + -1 + "\" class=\"nip_head_area_" + row_number + "\">"
                + "<td colspan=\"3\" class=\"nippou_info_bg_left\" id=\"" + row_number + "\">"
                + "<div id=\"pos" + row_number + "\">"
                + "</div>NO," + row_number + ""
                + "</td>"
                + "<td id=\"-1\" colspan=\"2\" class=\"nippou_info_bg nip_header_" + row_number + "\" align=\"right\">"
                + "<input name=\"ntpEditKakuteiBtn\" class=\"btn_edit_n4 edit_touroku_class\" id=\""
                + row_number
                + "\" value=\"登録\" type=\"button\">&nbsp;"
                + "<input class=\"close_btn1\" id=\"trDellBtn\" value=\"削除\" type=\"button\">"
                + "</td>"
                + "<td colspan=\"6\" height=\"25px\"></td>"
                + "</tr>"
                + usrInfAreaStr
                + "<tr class=\"titleArea" + row_number + "\">"
                + "<td class=\"table_bg_A5B4E1\" nowrap=\"nowrap\" width=\"10%\"><span class=\"text_bb1\">タイトル</span><span class=\"titleArea" + row_number + " text_r2\" style=\"\">※</span></td>"
                + "<td class=\"td_wt ntp_add_td\" colspan=\"3\" align=\"left\" width=\"90%\">"
//                + "<div style=\"display: none;\" class=\"titleArea" + row_number + "\">"
//                + "<span class=\"dsp_title_" + row_number + "\"></span>"
//                + "</div>"
                + "<div style=\"\">"
                + "<input name=\"ntp040Title_" + row_number + "\" maxlength=\"100\" value=\"\" id=\"ntpTitleTextBox\" class=\"text_base\" type=\"text\" style=\"width:337px;\">"
                + "&nbsp;<span class=\"sc_block_color_1\"><input name=\"ntp040Bgcolor_" + row_number + "\" value=\"1\" id=\"bg_color1\" type=\"radio\"></span>"
                + "<label for=\"bg_color1\" class=\"text_base\">" + titileColStr1 + "</label>"
                + "&nbsp;<span class=\"sc_block_color_2\"><input name=\"ntp040Bgcolor_" + row_number + "\" value=\"2\" id=\"bg_color2\" type=\"radio\"></span>"
                + "<label for=\"bg_color2\" class=\"text_base\">" + titileColStr2 + "</label>"
                + "&nbsp;<span class=\"sc_block_color_3\"><input name=\"ntp040Bgcolor_" + row_number + "\" value=\"3\" id=\"bg_color3\" type=\"radio\"></span>"
                + "<label for=\"bg_color3\" class=\"text_base\">" + titileColStr3 + "</label>"
                + "&nbsp;<span class=\"sc_block_color_4\"><input name=\"ntp040Bgcolor_" + row_number + "\" value=\"4\" id=\"bg_color4\" type=\"radio\"></span>"
                + "<label for=\"bg_color4\" class=\"text_base\">" + titileColStr4 + "</label>"
                + "&nbsp;<span class=\"sc_block_color_5\"><input name=\"ntp040Bgcolor_" + row_number + "\" value=\"5\" id=\"bg_color5\" type=\"radio\"></span>"
                + "<label for=\"bg_color5\" class=\"text_base\">" + titileColStr5 + "</label>"
                + "</div>"
                + "</td>"
                + "</tr>"
                + "<tr style=\"\" class=\"ntpDateAreaTr" + row_number + "\">"
                + "<td class=\"table_bg_A5B4E1\" nowrap=\"\"><span class=\"text_bb1\">報告日付</span><span class=\"ntpTimeArea" + row_number + " text_r2\" style=\"\">※</span></td>"
                + "<td class=\"td_wt ntp_add_td\" colspan=\"3\" align=\"left\">"
                + "<select name=\"selYear" + row_number + "\" id=\"selYear" + row_number + "\">"
                + yearOptionStr
                + "</select>&nbsp;"
                + "<select name=\"selMonth" + row_number + "\" id=\"selMonth" + row_number + "\">"
                + monthOptionStr
                + "</select>&nbsp;"
                + "<select name=\"selDay" + row_number + "\" id=\"selDay" + row_number + "\">"
                + dayOptionStr
                + "</select>&nbsp;"
                + "<input class=\"btn_arrow_l\" value=\"&nbsp;\" onclick=\"return moveDay($('#selYear" + row_number + "')[0], $('#selMonth" + row_number + "')[0], $('#selDay" + row_number + "')[0], 1)\" type=\"button\">&nbsp;"
                + "<input class=\"btn_today\" value=\"今日\" onclick=\"return moveDay($('#selYear" + row_number + "')[0], $('#selMonth" + row_number + "')[0], $('#selDay" + row_number + "')[0], 2)\" type=\"button\">&nbsp;"
                + "<input class=\"btn_arrow_r\" value=\"&nbsp;\" onclick=\"return moveDay($('#selYear" + row_number + "')[0], $('#selMonth" + row_number + "')[0], $('#selDay" + row_number + "')[0], 3)\" type=\"button\">&nbsp;"
                + "<input value=\"Cal\" onclick=\"wrtCalendarByBtn(this.form.selDay" + row_number + ", this.form.selMonth" + row_number + ", this.form.selYear" + row_number + ", 'ntp040FrCalBtn" + row_number + "')\" class=\"calendar_btn\" id=\"ntp040FrCalBtn" + row_number + "\" type=\"button\">"
                + "</td>"
                + "</tr>"
                + "<tr class=\"ntpTimeArea" + row_number + "\">"
                + "<td class=\"table_bg_A5B4E1\" nowrap=\"\"><span class=\"text_bb1\">時間</span><span class=\"ntpTimeArea" + row_number + " text_r2\" style=\"\">※</span></td>"
                + "<td class=\"td_wt ntp_add_td\" colspan=\"3\" align=\"left\">"
//                + "<div style=\"display: none;\" class=\"ntpTimeArea" + row_number + "\">"
//                + "<span class=\"dsp_frhour_" + row_number + "\"></span>"
//                + "時"
//                + "<span class=\"dsp_frminute_" + row_number + "\"></span>"
//                + "分"
//                + "～"
//                + "<span class=\"dsp_tohour_" + row_number + "\"></span>"
//                + "時"
//                + "<span class=\"dsp_tominute_" + row_number + "\"></span>"
//                + "分"
//                + "&nbsp;&nbsp;&nbsp;&nbsp;<span id=\"betWeenDays\" class=\"text_base\"></span>"
//                + "</div>"
                + "<div style=\"\">"
                + "<select name=\"ntp040FrHour_" + row_number + "\" onchange=\"setToDay();\">"
                + frhourOptionStr
                + "</select>"
                + " 時 "
                + "<select name=\"ntp040FrMin_" + row_number + "\" onchange=\"setToDay();\">"
                + frminOptionStr
                + "</select>"
                + " 分 "
                + " ～ "
                + "<select name=\"ntp040ToHour_" + row_number + "\" onchange=\"setToDay();\">"
                + tohourOptionStr
                + "</select>"
                + " 時 "
                + "<select name=\"ntp040ToMin_" + row_number + "\" onchange=\"setToDay();\">"
                + tominOptionStr
                + "</select>"
                + " 分 "
                + "&nbsp;&nbsp;&nbsp;&nbsp;<span id=\"betWeenDays\" class=\"text_base\"></span>"
                + "</div>"
                + "</td>"
                + "</tr>"
                +  ankenCompanyStr
                + "<tr>"
                + "<td class=\"table_bg_A5B4E1\" nowrap=\"nowrap\"><span class=\"text_bb1\">内　容<a id=\"naiyou\" name=\"naiyou\"></a></span></td>"
                + "<td class=\"td_wt ntp_add_td\" colspan=\"3\" align=\"left\">"
                + "<div style=\"display: none;\" class=\"naiyouArea" + row_number + "\">"
                + "<span class=\"dsp_naiyou_" + row_number + "\"></span>"
                + "</div>"
                + "<div class=\"naiyouArea" + row_number + "\" style=\"\">"
                + "<textarea id=\"inputstr_2\" name=\"ntp040Value_" + row_number + "\" style=\"width:431px;\" rows=\"5\" onkeyup=\"showLengthStr(value, 1000, 'inputlength" + row_number + "');\">" + defaultValue + "</textarea>"
                + "<br>"
                + "<span class=\"font_string_count\">現在の文字数:</span><span id\=\"inputlength" + row_number + "\" class=\"font_string_count\">" + defaultLength + "</span>&nbsp;<span class=\"font_string_count_max\">/&nbsp;1000&nbsp;文字</span>"
                + "</div>"
                + "</td>"
                + "</tr>"
                +  ktbunruiHouhouStr
                +  mikomidoStr
                +  tempFileStr
                +  actionAreaStr
                + "<tr style=\"display: none;\" class=\"ntpAddUNameDate" + row_number + "\">"
                +"<td class=\"table_bg_A5B4E1\" nowrap><span class=\"text_bb1\">登録者<a id=\"addUser\" name=\"addUser\"></a></span></td>"
                +"<td class=\"td_wt ntp_add_td\" colspan=\"3\" align=\"left\">"
                +"<table class=\"tl0\" width=\"100%\">"
                +"<tr>"
                +"<td width=\"60%\" nowrap>"
                +"<span class=\"text_base\">"
                +"<span class=\"addUserName_" + row_number + "\">"
                +"<del>"
                +"</del>"
                +"</span>"
                +"</div>"
                +"</td>"
                +"<td width=\"40%\" align=\"left\" nowrap>"
                +"<span class=\"text_base\">"
                +"<span class=\"addDate_" + row_number + "\">"
                +"</span>"
                +"</td>"
                +"</tr>"
                +"</table>"
                +"</td>"
                +"</tr>"
                +  noAnkenCompanyStr
                + "<tr style=\"display:none;\">"
                + "<td class=\"td_wt\" colspan=\"4\" style=\"padding-left:350px;background-color:#f6f6f6;\">"
                + "<span id=\"goodBtnArea_-1\">"
                + "<span class=\"text_already_good\">いいね!しています</span>"
                + "</span>"
                + "<span class=\"text_good\" id=\"-1\">&nbsp;&nbsp;<span id=\"goodCntArea_-1\"></span>&nbsp;&nbsp;"
                + "</span>"
                + "</td>"
                + "</tr>"
                + "<tr class=\"ntp040DspComment_tr_" + row_number + " ntp040DspComment_tr_none\">"
                + "<td id=\"ntp040DspComment_" + row_number + "\" class=\"td_wt\ colspan=\"4\"></td>"
                + "</tr>"
                + "<tr style=\"display: none;\" class=\"commentArea" + row_number + "\">"
                + "<td style=\"display: none;\" class=\"td_wt\" colspan=\"4\">"
                + "<table  style=\"display: none;\">"
                + "<tbody><tr>"
                + "<td>"
                + "<img class=\"comment_Img\" src=\"../user/images/photo.gif\" name=\"userImage\" alt=\"写真\" border=\"1\" width=\"50px\">"
                + "</td>"
                + "<td style=\"padding-left:10px;\" valign=\"middle\">"
                + "<div class=\"textfield\">"
                + "<label class=\"label_area\" style=\"opacity:1;font-size:12px;color:a3a3a3;\" for=\"field_id" + row_number + "\">コメントする</label>"
                + "<textarea name=\"ntp040Comment_" + row_number + "\" rows=\"3\" style=\"height:50px;width:431px;\" id=\"field_id" + row_number + "\"></textarea>"
                + "</div>"
                + "</td>"
                + "<td id=\"" + row_number + "\" valign=\"middle\"><input id=\"-1\" name=\"commentBtn\" value=\"投稿\" type=\"button\"></td>"
                + "</tr>"
                + "</tbody></table>"
                + "</td>"
                + "</tr>"
                + "<tr class=\"commentArea" + row_number + "\" style=\"\"></tr>"
                + "</tbody></table>"
                + "</td>"
                + "</tr>"
            );


        //ラジオボタン初期値設定
        $('input[name="ntp040Bgcolor_' + row_number + '"]').val([$("input:hidden[name='ntp040BgcolorInit']").val()]);

        $("#editLastRowNum").val(row_number);

        footerStart2("#footdiv");

    });



    /* 削除ボタン(新規登録時) */
    $("#trDellBtn").live("click", function(){
        delBtnId = $(this);
        $('#dialog').dialog('open');
    });

    /* 確認ボタン */
    $('#dialog').dialog({
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
          はい: function() {

            //行番号
            var rownum = delBtnId.parent().parent().attr("id");

            //行ID
            var rowId = "";

            if (rownum == -1) {
                //編集画面で行追加時
                rowId = delBtnId.parent().parent().parent().parent().parent().parent().attr("id");
                if (rowId != null && rowId != "") {
                    rownum = rowId.split("nippou_data_")[1];
                }
            }


            //添付ファイル削除
            var selectfiles = "";
            if ($('input:hidden[name=ntp040TmpFileUse]').val() == 0) {
                var sel = $('select[name=ntp040selectFiles' + rownum + ']')[0];
                for (var i = 0; i < sel.length; i++) {
                    selectfiles += sel.options[i].value + ",";
                }
                if (selectfiles != null && selectfiles != "") {
                    jsonStr = '[' + selectfiles + ']';

                    //データ送信
                    $.ajaxSetup({async:false});
                    $.post('../nippou/ntp040.do', {"cmd":"tempdel",
                                                   "CMD":"tempdel",
                                                   "rowNum":rownum,
                                                   "nippouTempData":jsonStr},
                      function(data) {
                        if (data == null || data == "") {

                        }
                    });
                }
            }

            //行削除
            if (rowId != null && rowId != "") {
                //編集画面
                $('#' + rowId).remove();
            } else {
                //新規登録画面
                delBtnId.parent().parent().parent().parent().parent().parent().remove();
            }


            $(this).dialog('close');
          },
          いいえ: function() {
            $(this).dialog('close');
          }
        }
    });



    /* 削除ボタン(編集時) */
    $("#ntpDellBtn").live("click", function(){
        ntpDelBtnId = $(this);
        $('#dialogNtpDel').dialog('open');
    });

    /* 確認ボタン */
    $('#dialogNtpDel').dialog({
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
          はい: function() {

            //日報SID
            var ntpSid = ntpDelBtnId.parent().parent().attr("id");


            //行番号
            var rownum = ntpDelBtnId.parent().parent().attr("id");

//            //添付ファイル削除
//            var sel = $('select[name=ntp040selectFiles' + rownum + ']')[0];
//            var selectfiles = "";
//            for (var i = 0; i < sel.length; i++) {
//                selectfiles += sel.options[i].value + ",";
//            }
//            var jsonStr="";
//            if (selectfiles != null && selectfiles != "") {
//                jsonStr = '[' + selectfiles + ']';
//            }

            //データ送信
            $.ajaxSetup({async:false});
            $.post('../nippou/ntp040.do', {"cmd":"dodelete",
                                           "CMD":"dodelete",
                                           "delNtpSid":ntpSid,
                                           "rowNum":rownum},
              function(data) {
                if (data == null || data == "") {

                }
            });

            //行削除
            ntpDelBtnId.parent().parent().parent().parent().parent().parent().remove();

            $(this).dialog('close');
          },
          いいえ: function() {
            $(this).dialog('close');
          }
        }
    });


    /* 登録ボタン */
    $(".ntpAddBtn").live("click", function(){

        //エラーメッセージ削除
        $("#error_msg").text("");

        /* json形成 */
        var nippouData = "[";
        var jsonStr = "";

        /* 登録日 */
        var ntpYear    = $("select[name=ntp040FrYear]").val();
        var ntpMonth   = $("select[name=ntp040FrMonth]").val();
        var ntpDay     = $("select[name=ntp040FrDay]").val();

        /* 選択ユーザ */
        var selectUsrSid        = document.forms['ntp040Form'].ntp010SelectUsrSid.value;
        /* listMod */
        var listMod             = document.forms['ntp040Form'].listMod.value;
        /* dspMod */
        var dspMod              = document.forms['ntp040Form'].dspMod.value;
        /* ntp010DspDate */
        var ntp010DspDate       = document.forms['ntp040Form'].ntp010DspDate.value;
        /* ntp010DspGpSid */
        var ntp010DspGpSid      = document.forms['ntp040Form'].ntp010DspGpSid.value;
        /* ntp010SelectUsrKbn */
        var ntp010SelectUsrKbn  = document.forms['ntp040Form'].ntp010SelectUsrKbn.value;
        /* ntp010SelectDate */
        var ntp010SelectDate    = document.forms['ntp040Form'].ntp010SelectDate.value;
        /* ntp020SelectUsrSid */
        var ntp020SelectUsrSid  = document.forms['ntp040Form'].ntp020SelectUsrSid.value;

        //日報データ取得
        $('.tr_nippou').each(function() {

            if (nippouData != "[") {
                nippouData += ",";
            }

            var ntpDspId = $(this).attr('id');

            var trId = "";

            if (ntpDspId != null && ntpDspId != "") {
                trId = "_" + ntpDspId;
            } else {
                ntpDspId = "1";
            }

            /* 時間 */
            var frhour         = $("select[name=ntp040FrHour"        + trId + "]").val();
            var frmin          = $("select[name=ntp040FrMin"         + trId + "]").val();
            var tohour         = $("select[name=ntp040ToHour"        + trId + "]").val();
            var tomin          = $("select[name=ntp040ToMin"         + trId + "]").val();

            /* 案件 */
            var ankenSid       = -1;
            if ($("input:hidden[name=ntp040AnkenSid" + trId + "]").val() !== undefined) {
                ankenSid = $("input[name=ntp040AnkenSid" + trId + "]").val();
            }

            /* 企業 */
            var companySid     = -1;
            if ($("input:hidden[name=ntp040CompanySid" + trId + "]").val() !== undefined) {
                companySid = $("input[name=ntp040CompanySid" + trId + "]").val();
            }

            /* 企業(拠点) */
            var companyBaseSid = -1;
            if ($("input:hidden[name=ntp040CompanyBaseSid" + trId + "]").val() !== undefined) {
                companyBaseSid = $("input[name=ntp040CompanyBaseSid" + trId + "]").val();
            }

            /* 活動分類・方法 */
            var ktbunruiSid = -1;
            var kthouhouSid = -1;
            if ($('input:hidden[name=ntp040KtBriHhuUse]').val() == 0) {
                /* 活動分類 */
                ktbunruiSid    = $("select[name=ntp040Ktbunrui"      + trId + "]").val();
                /* 活動方法 */
                kthouhouSid    = $("select[name=ntp040Kthouhou"      + trId + "]").val();
            }

            /* 見込み度 */
            var mikomido = 0;
            if ($('input:hidden[name=ntp040MikomidoUse]').val() == 0) {
                mikomido       = $("input[name=ntp040Mikomido"       + trId + "]:checked").val();
            }

            /* 背景色 */
            var bgColorStr     = $("input[name=ntp040Bgcolor"        + trId + "]:checked").val();
            /* タイトル */
            var titleStr       = encodeURIComponent($("input[name=ntp040Title"    + trId + "]").val());
            /* 内容 */
            var valueStr       = encodeURIComponent($("textarea[name=ntp040Value" + trId + "]").val());

            /* 次のアクション */
            var actDateKbn    = 0;
            var actionYear    = -1;
            var actionMonth   = -1;
            var actionDay     = -1;
            var actionStr     = "";
            if ($('input:hidden[name=ntp040NextActionUse]').val() == 0) {
                /* 時間指定（次のアクション） */
                actDateKbn     = $("input[name=ntp040ActDateKbn"        + trId + "]:checked").val();

                if (actDateKbn == 1) {
                    actionYear    = $("select[name=ntp040NxtActYear"  + trId + "]").val();
                    actionMonth   = $("select[name=ntp040NxtActMonth" + trId + "]").val();
                    actionDay     = $("select[name=ntp040NxtActDay"   + trId + "]").val();
                }

                /* 次のアクション */
                actionStr       = encodeURIComponent($("textarea[name=ntp040NextAction" + trId + "]").val());
            }

            jsonStr =  "{rowId:"         +  ntpDspId        + ","
                    +  "selectUsrSid:"   +  selectUsrSid    + ","
                    +  "ntpYear:"        +  ntpYear         + ","
                    +  "ntpMonth:"       +  ntpMonth        + ","
                    +  "ntpDay:"         +  ntpDay          + ","
                    +  "frHour:"         +  frhour          + ","
                    +  "frMin:"          +  frmin           + ","
                    +  "toHour:"         +  tohour          + ","
                    +  "toMin:"          +  tomin           + ","
                    +  "ankenSid:"       +  ankenSid        + ","
                    +  "companySid:"     +  companySid      + ","
                    +  "companyBaseSid:" +  companyBaseSid  + ","
                    +  "ktbunruiSid:"    +  ktbunruiSid     + ","
                    +  "kthouhouSid:"    +  kthouhouSid     + ","
                    +  "mikomido:"       +  mikomido        + ","
                    +  "title:\""        +  titleStr        + "\","
                    +  "bgcolor:"        +  bgColorStr      + ","
                    +  "valueStr:\""     +  valueStr        + "\","
                    +  "actDateKbn:"     +  actDateKbn      + ","
                    +  "actionYear:"     +  actionYear      + ","
                    +  "actionMonth:"    +  actionMonth     + ","
                    +  "actionDay:"      +  actionDay       + ","
                    +  "actionStr:\""    +  actionStr       +  "\"}";

            nippouData += jsonStr;
        });

        nippouData += "]";


        var targetData = "[";
        var targetJsonStr = "";

        $('.td_target').each(function() {

            if (targetData != "[") {
                targetData += ",";
            }

            var targetIdData  = $(this).attr('id').split("_");
            var targetYear    = targetIdData[0];
            var targetMonth   = targetIdData[1];
            var targetUsrSid  = targetIdData[2];
            var targetNtgSid  = targetIdData[3];
            var recordValue   = $('#val_'    + $(this).attr('id')).val();
            var targetValue   = $('#valTrg_' + $(this).attr('id')).text();

            targetJsonStr =  "{year:"         +  targetYear      + ","
                          +  "month:"         +  targetMonth     + ","
                          +  "usrSid:"        +  targetUsrSid    + ","
                          +  "ntgSid:"        +  targetNtgSid    + ","
                          +  "recordStr:\""   +  recordValue     + "\","
                          +  "targetStr:\""   +  targetValue     + "\"}";

            targetData += targetJsonStr;
        });


        targetData += "]";

        if (targetJsonStr = "") {
            targetData = "";
        }

        //データ送信
        $.ajaxSetup({async:false});
        $.post('../nippou/ntp040.do', {"cmd":"addNtp",
                                       "CMD":"addNtp",
                                       "listMod":listMod,
                                       "dspMod":dspMod,
                                       "ntp010DspDate":ntp010DspDate,
                                       "ntp010DspGpSid":ntp010DspGpSid,
                                       "ntp010SelectDate":ntp010SelectDate,
                                       "ntp020SelectUsrSid":ntp020SelectUsrSid,
                                       "nippouData":nippouData,
                                       "targetData":targetData},
          function(data) {

            if (data != null && data != "" && data.ntpSid == null) {
                var jsonArray = eval('(' + data + ')');
                var scrollId = "";

                if (jsonArray.length > 0) {
                    //入力エラー
                    for(var i in jsonArray){
                        if (scrollId == "") {
                            scrollId = jsonArray[i].rownum;
                        }

                        if (jsonArray[i].rownum != -1) {
                            //通常
                            $("#error_msg").append("<b>No," + jsonArray[i].rownum + "</b><br>");
                        } else {
                            //目標
                            $("#error_msg").append("<b>目標<br>");
                        }

                        var errormsgs = jsonArray[i].msg;
                        for(var j in errormsgs){
                            $("#error_msg").append("<span style=\"color:#ff0000;\"\">" + errormsgs[j] + "</span><br>");
                        }
                        $("#error_msg").append("<hr>");
                    }

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
                                footerStart("#pos" + scrollId);
                                $(this).dialog('close');
                            }
                        }
                    });
                }
            } else {
                //登録した値を削除
                $("select[name=ntp040FrHour]").val($('#frhourhide').val());
                $("select[name=ntp040FrMin]").val($('#frminhide').val());
                $("select[name=ntp040ToHour]").val($('#tohourhide').val());
                $("select[name=ntp040ToMin]").val($('#tominhide').val());
                $("input[name=ntp040Title]").val("");
                $("textarea[name=ntp040Value]").val("");
                $("select[name=ntp040Ktbunrui]").val("-1");
                $("select[name=ntp040Kthouhou]").val("-1");
                $("input[name=ntp040Mikomido]").val(["0"]);
                $("input[name=ntp040Bgcolor]").val(["1"]);

                //登録完了
                document.forms[0].CMD.value="comp";
                document.forms[0].submit();
                return false;
            }
        });
    });


    /* 編集ボタン */
    $("input[name=ntpEditBtn]").live("click", function(){

        var rownum = $(this).attr("id");
        var editNtpSid = $(this).parent().parent().attr("id");

        //現在のoption削除
        $('select[name=ntp040selectFiles' + rownum + ']').children().remove();

        //添付ファイルセット
        $.ajaxSetup({async:false});
        $.post('../nippou/ntp040.do', {"cmd":"editNtpData",
                                       "CMD":"editNtpData",
                                       "editNtpSid":editNtpSid,
                                       "rowNum":rownum},
          function(data) {
            if (data != null || data != "") {
                for (i = 0; i < data.length; i++) {
                    //添付ファイルがある場合はoptionに設定
                    var elem           = document.createElement("option");
                    var select_element = document.getElementById("ntp040selFile" + rownum);
                    elem.text  = data[i].label;
                    elem.value = data[i].value;
                    select_element.options[select_element.options.length] = elem;
                }
            }
        });

        //表示切替
        toggleDsp(rownum);

        //内容文字数カウント
        showLengthId($('#inputstr_' + rownum)[0], 1000, 'inputlength' + rownum);

        //次のアクション文字数カウント
        if ($('#actionstr_' + rownum)[0] != null) {
            showLengthId($('#actionstr_' + rownum)[0], 1000, 'actionlength' + rownum);
        }

    });


    /* 確定ボタン */
    $("input[name=ntpEditKakuteiBtn]").live("click", function(){

        var thisObj       = $(this);
        var rownum        = $(this).attr("id");
        var changeNtpSid  = $(this).parent().parent().attr("id");
        var trId          = "_" + rownum;
        var canselJsonStr = ""
        var dateChangeFlg = false;
        var prmCmd        = "editNtp";
        var dialogName    = "dialogEditOk";

        if (changeNtpSid == -1) {
            //確認画面で行追加した場合は新規登録処理にする
            prmCmd      = "addNtp";
            dialogName  = "dialogAddOk";
        }

        $('#' + dialogName).dialog({
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
                はい: function() {

                    /* listMod */
                    var listMod             = document.forms['ntp040Form'].listMod.value;
                    /* dspMod */
                    var dspMod              = document.forms['ntp040Form'].dspMod.value;
                    /* ntp010DspDate */
                    var ntp010DspDate       = document.forms['ntp040Form'].ntp010DspDate.value;
                    /* ntp010DspGpSid */
                    var ntp010DspGpSid      = document.forms['ntp040Form'].ntp010DspGpSid.value;
                    /* ntp010SelectUsrKbn */
                    var ntp010SelectUsrKbn  = document.forms['ntp040Form'].ntp010SelectUsrKbn.value;
                    /* ntp010SelectDate */
                    var ntp010SelectDate    = document.forms['ntp040Form'].ntp010SelectDate.value;
                    /* ntp020SelectUsrSid */
                    var ntp020SelectUsrSid  = document.forms['ntp040Form'].ntp020SelectUsrSid.value;

                    //データ送信
                    $.ajaxSetup({async:false});
                    $.post('../nippou/ntp040.do', {"cmd":prmCmd,
                                                   "CMD":prmCmd,
                                                   "changeNtpSid":changeNtpSid,
                                                   "listMod":listMod,
                                                   "dspMod":dspMod,
                                                   "ntp010DspDate":ntp010DspDate,
                                                   "ntp010DspGpSid":ntp010DspGpSid,
                                                   "ntp010SelectDate":ntp010SelectDate,
                                                   "ntp020SelectUsrSid":ntp020SelectUsrSid,
                                                   "nippouData":createJsonData(thisObj, 1)},
                      function(data) {

                        if (data != null && data != "" && data.ntpSid == null) {

                            var jsonArray = eval('(' + data + ')');
                            var scrollId = "";

                            if (jsonArray.length > 0) {
                                //入力エラー
                                for(var i in jsonArray){
                                    if (scrollId == "") {
                                        scrollId = jsonArray[i].rownum;
                                    }

                                    $("#error_msg").append("<b>No," + jsonArray[i].rownum + "</b><br>");
                                    var errormsgs = jsonArray[i].msg;
                                    for(var j in errormsgs){
                                        $("#error_msg").append("<span style=\"color:#ff0000;\"\">" + errormsgs[j] + "</span><br>");
                                    }
                                    $("#error_msg").append("<hr>");
                                }

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
                                            footerStart("#pos" + scrollId);
                                            $(this).dialog('close');
                                        }
                                    }
                                });
                            }

                        } else {

                            var changeNtpFlg = 0;

                            if (changeNtpSid == -1) {
                                changeNtpSid = data.ntpSid;
                                changeNtpFlg = 1;
                            }

                            //日付変更チェック
                            dateChangeFlg = ChangeDateCheck(rownum);

                            if (dateChangeFlg) {
                                //日付変更後画面遷移確認
                                $('#dspMoveOk').dialog({
                                    autoOpen: true,  // hide dialog
                                    bgiframe: true,   // for IE6
                                    resizable: false,
                                    height: 160,
                                    modal: true,
                                    overlay: {
                                      backgroundColor: '#000000',
                                      opacity: 0.5
                                    },
                                    buttons: {
                                      はい: function() {

                                        //遷移先の日付を取得
                                        var chYear  = $("#selYear"  + rownum).val();
                                        var chMonth = $("#selMonth" + rownum).val();
                                        var chDay   = $("#selDay"   + rownum).val();

                                        if (chMonth.length < 2) {
                                            chMonth = "0" + chMonth;
                                        }
                                        if (chDay.length < 2) {
                                            chDay = "0" + chDay;
                                        }

                                        var chNtpDate = chYear + chMonth + chDay;

                                        editNippou('edit', chNtpDate, changeNtpSid, 0);
                                        $(this).dialog('close');

                                      },
                                      いいえ: function() {
                                        //登録完了 データ再設定
                                        getResetData(trId, changeNtpSid, rownum, canselJsonStr);
                                        //表示切替
                                        toggleDsp(rownum);

                                        //画面から削除
                                        $('#nippou_data_' + rownum).remove();

                                        $(this).dialog('close');
                                      }
                                    }
                                });

                            } else {

                                if (changeNtpFlg == 1) {
                                    //新規登録時は日報SIDを取得
                                    changeNtpSid = data.ntpSid;
                                    //日報のヘッダー部分を書き換え
                                    rewriteNtpHeader(changeNtpSid, rownum);
                                }

                                //登録完了 データ再設定
                                getResetData(trId, changeNtpSid, rownum, canselJsonStr);
                                toggleDsp(rownum);

                                //日報未登録メッセージ削除
                                if ($('.ntpEmptyArea').attr('id') != null) {
                                    $('.ntpEmptyArea').remove();
                                }
                            }
                        }
                    });
                    $(this).dialog('close');
                },
                いいえ: function() {
                  $(this).dialog('close');
                }
            }
        });
    });


    /* 編集キャンセルボタン */
    $("input[name=ntpEditCancelBtn]").live("click", function(){

        var rownum = $(this).attr("id");
        var trId   = "_" + $(this).attr("id");

        //編集前データ取得
        //日報SID
        var ntpSid = $(this).parent().parent().attr("id");

        //行番号
        var rownum = $(this).parent().parent().parent().attr("id");


        //添付ファイル削除
        var selectfiles = "";
        if ($('input:hidden[name=ntp040TmpFileUse]').val() == 0) {
            var sel = $('select[name=ntp040selectFiles' + rownum + ']')[0];
            for (var i = 0; i < sel.length; i++) {
                selectfiles += sel.options[i].value + ",";
            }
            var canselJsonStr="";
            if (selectfiles != null && selectfiles != "") {
                canselJsonStr = '[' + selectfiles + ']';
            }
        }
        //データ再設定
        getResetData(trId, ntpSid, rownum, canselJsonStr);

        //表示切替
        toggleDsp(rownum);
    });


    /* 複写して登録ボタン */
    $("input[name=ntpCopyBtn]").live("click", function(){

        var copyNtpSid = $(this).attr("id");

        var mitourokuFlg = false;
        var mikakuteiFlg = false;
        var notAddPoint = 0;
        var notEditPoint = 0;

        $(".edit_touroku_class").each(function() {
            mitourokuFlg = true;
            notAddPoint = $(this).offset().top;
        });


        $("input[name=ntpCopyBtn]").each(function() {
            if ($(this).parent().css('display') == 'none') {
                mikakuteiFlg = true;
                notEditPoint = $(this).parent().parent().offset().top;
            }
        });

        //未登録,未確定の日報がある場合はメッセージを表示
        if (mitourokuFlg) {

            addDialogOpen('copy', 'copy', mikakuteiFlg, notAddPoint, notEditPoint, copyNtpSid);

        } else if (mikakuteiFlg, notEditPoint) {

            editDialogOpen('copy', 'copy', notEditPoint, copyNtpSid);

        } else {
            document.forms[0].ntp010NipSid.value=copyNtpSid;
            buttonPush('copy', 'copy');
        }

    });


    /* 添付ファイル削除ボタン */
    $("input[name=tempDelBtn]").live("click", function(){

        var rownum = $(this).attr("id");

//        if (rownum == '1') {
//            rownum = "";
//        }

        //選択ファイル取得
        var selectfiles = $('select[name=ntp040selectFiles' + rownum + ']').val();

        if (selectfiles != null && selectfiles != "") {
            jsonStr = '[' + selectfiles + ']';

            //データ送信
            $.ajaxSetup({async:false});
            $.post('../nippou/ntp040.do', {"cmd":"tempdel",
                                           "CMD":"tempdel",
                                           "rowNum":rownum,
                                           "nippouTempData":jsonStr},
              function(data) {
                if (data == null || data == "") {
                    //option要素削除
                    $('select[name=ntp040selectFiles' + rownum + '] option:selected').remove();
                }
            });
        }
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

    //スケジュール詳細ボタン
    $(".sch_detail_btn").live("click", function(){
        var schSid = $(this).attr("id");
        openSubWindow("../schedule/sch210.do?sch010SelectUsrKbn=0&sch010SchSid=" + schSid);
    });

    //TODO詳細ボタン
    $(".prj_detail_btn").live("click", function(){
        var paramStr = $(this).attr('id').split("_");
        var prjSid = paramStr[0];
        var todoSid = paramStr[1];
        openSubWindowSet("../project/prj230.do?prj060prjSid=" + prjSid + "&prj060todoSid=" + todoSid, 700, 790);
    });

    //見込み度基準ボタン
    $(".mikomido_btn").live("click", function(){
        $('#mikomidoPop').dialog({
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
    });

    /* 見込み度基準 hover */
    $('.mikomido_back').live({
          mouseenter:function () {
            $(this).addClass("mikomido_back_hover");
          },
          mouseleave:function () {
              $(this).removeClass("mikomido_back_hover");
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

    /* アドレス履歴ポップアップ */
    $(".adrHistoryPop").live("click", function(){

        var rowNumber = $(this).attr('id');
        var pageNum   = $("input:hidden[name='ntp040AdrHistoryPageNum']").val();

        $('#adrHistoryArea').children().remove();
        $('#adrHistoryArea').append("<div style=\"padding-top:220px;height:100%;width:100%;text-align:center;\"><img src=\"../nippou/images/ajax-loader.gif\" /></div>");

        /* ユーザ一覧ポップアップ */
        $('#adrHistoryPop').dialog({
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
        getAdrHistoryList(rowNumber, pageNum);
    });

    //アドレス履歴 次ページクリック
    $(".nextPageBtn").live("click", function(){
        var paramStr = $(this).attr('id').split(":");

        getAdrHistoryList(paramStr[0], parseInt(paramStr[1]) + 1);
    });

    //アドレス履歴 前ページクリック
    $(".prevPageBtn").live("click", function(){
        var paramStr = $(this).attr('id').split(":");


        getAdrHistoryList(paramStr[0], parseInt(paramStr[1]) - 1);
    });

    //アドレス履歴 コンボ変更
    $(".selchange").live("change", function(){
        getAdrHistoryList($(this).attr('id'), $(this).val());
    });

















    /* 案件履歴ポップアップ */
    $(".ankenHistoryPop").live("click", function(){

        var rowNumber = $(this).attr('id');
        var pageNum   = $("input:hidden[name='ntp040AnkenHistoryPageNum']").val();

        $('#ankenHistoryArea').children().remove();
        $('#ankenHistoryArea').append("<div style=\"padding-top:220px;height:100%;width:100%;text-align:center;\"><img src=\"../nippou/images/ajax-loader.gif\" /></div>");

        /* 案件一覧ポップアップ */
        $('#ankenHistoryPop').dialog({
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
        getAnkenHistoryList(rowNumber, pageNum);
    });

    //案件履歴 次ページクリック
    $(".nextAnkenPageBtn").live("click", function(){
        var paramStr = $(this).attr('id').split(":");

        getAnkenHistoryList(paramStr[0], parseInt(paramStr[1]) + 1);
    });

    //案件履歴 前ページクリック
    $(".prevAnkenPageBtn").live("click", function(){
        var paramStr = $(this).attr('id').split(":");


        getAnkenHistoryList(paramStr[0], parseInt(paramStr[1]) - 1);
    });

    //案件履歴 コンボ変更
    $(".selchangeAnken").live("change", function(){
        getAnkenHistoryList($(this).attr('id'), $(this).val());
    });




















    //スケジュールデータ取得
    $(".schDataGetBtn").live("click", function(){

        var schUsrSid = document.forms['ntp040Form'].ntp010SelectUsrSid.value;

        var year  = $('input:hidden[name=ntp040InitYear]').val();
        var month = $('input:hidden[name=ntp040InitMonth]').val();
        var day   = $('input:hidden[name=ntp040InitDay]').val();


        if (month.length < 2) {
            month = "0" + month;
        }

        if (day.length < 2) {
            day = "0" + day;
        }

        var schDspDate = year + month + day;


        //スケジュールデータ取得
        $.ajaxSetup({async:false});
        $.post('../nippou/ntp040.do', {
                       "cmd":"getSchDataList",
                       "CMD":"getSchDataList",
                       "schUsrSid":schUsrSid,
                       "schDspDate":schDspDate},
            function(data) {
                if (data != null || data != "") {

                    var schDataStr = "";

                    if (data.length > 0) {

                        for (i = 0; i < data.length; i++) {

                            var schTime = "";
                            if (data[i].scdTime == "") {
                                schTime = "時間指定なし";
                            } else {
                                schTime = data[i].scdTime;
                            }

                            //スケジュール区分
                            var grpStr ="";
                            if (data[i].scdKbn != "0") {
                                grpStr = "<span class=\"sc_link_g\">G</span>";
                            }

                            schDataStr += "<tr class=\"td_type1\" style=\"font-size:90%;cursor:pointer;\" id=\"tr_" + data[i].scdSid + "\">"
                                       + "<td onclick=\"clickSchName(3," + data[i].scdSid + ");\" width=\"7%\">"
                                       + "<input type=\"checkbox\" name=\"popSchedule\" value=\"" + data[i].scdSid + "\" onclick=\"clickMulti();\">"
                                       + "</td>"
                                       + "<td onclick=\"clickSchName(3," + data[i].scdSid + ");\" align=\"center\" width=\"10%\" nowrap=\"nowrap\">" + schTime + "</td>"
                                       + "<td onclick=\"clickSchName(3," + data[i].scdSid + ");\" align=\"left\" width=\"205\" nowrap=\"nowrap\">" + grpStr + data[i].scdTitle + "</td>"
                                       + "<td width=\"20%\" nowrap=\"nowrap\">"
                                       + "<input id=\"" + data[i].scdSid + "\" class=\"ntp_shousai_btn sch_detail_btn\" value=\"詳細\" type=\"button\">"
                                       + "</td>"
                                       + "</tr>";

                        }

                    } else {
                        schDataStr += "<tr>"
                        + "<td align=\"center\" width=\"100%\" colspan=\"4\" nowrap=\"nowrap\">"
                        + "<span style=\"font-size:14px;color:#ff0000;font-weight:bold;\">該当するスケジュールがありません。</span>"
                        + "</td>"
                        + "</tr>";
                    }

                    $('#schDataTrArea').html(schDataStr);

                    /* スケジュール選択画面ポップアップ */
                    $('#schDataPop').dialog({
                        autoOpen: true,  // hide dialog
                        bgiframe: true,   // for IE6
                        resizable: false,
                        height: 400,
                        width: 500,
                        modal: true,
                        overlay: {
                          backgroundColor: '#000000',
                          opacity: 0.5
                        },
                        buttons: {
                            選択: function() {

                                //選択値取得
                                var scheduleList   = $("input:checkbox[name='popSchedule']:checked");
                                //データ記述行番号(先頭)
                                var addRow = 0;
                                //データ記述行番号
                                var selAddRow = 0;
                                //データ記述行識別文字
                                var selRowNum = "";

                                //画面表示
                                for (var j = 0; j < scheduleList.length; j++) {


                                    //スケジュールデータ取得
                                    $.ajaxSetup({async:false});
                                    $.post('../nippou/ntp040.do', {
                                                   "cmd":"getSchSelectData",
                                                   "CMD":"getSchSelectData",
                                                   "selSchSid":scheduleList[j].value},
                                        function(schData) {
                                            if (schData != null || schData != "") {

                                                //データを入力する行をさがす

                                                for (i = 1; i <= row_number; i++) {

                                                    if (selAddRow == 0) {
                                                        if (i != 1) {
                                                            selRowNum = "_" + i;
                                                        }

                                                        if ($('input[name=ntp040Title' + selRowNum + ']').val() == "") {
                                                            //タイトルが入力されていない場合はデータ入力
                                                            if (selAddRow == 0) {
                                                                selAddRow = i;
                                                            }
                                                        } else {
                                                            //データを入力できる行がない場合は行追加
                                                            if (i == row_number && selAddRow == 0) {
                                                                addNewRow();
                                                                selAddRow = row_number;
                                                            }
                                                        }
                                                    }
                                                }

                                                var trId = "";
                                                if (selAddRow > 1) {
                                                    trId = "_" + selAddRow;
                                                }

                                                $('input[name=ntp040Title'   + trId + ']').val(schData.title);
                                                $('input[name=ntp040Bgcolor' + trId + '][value=\"' + schData.bgcolor + '\"]').attr("checked", true);
                                                $('select[name=ntp040FrHour' + trId + ']').val(schData.frHour);
                                                $('select[name=ntp040FrMin' + trId + ']').val(schData.frMin);
                                                $('select[name=ntp040ToHour' + trId + ']').val(schData.toHour);
                                                $('select[name=ntp040ToMin' + trId + ']').val(schData.toMin);

                                                //企業・顧客
                                                var comcodestr = "";
                                                var comdelstr = "";
                                                if (schData.companySid != null && schData.companySid != "") {
                                                    comcodestr = "企業コード：";
                                                    comdelstr = "<img src=\"../common/images/delete.gif\" class=\"img_bottom\" alt=\"\" width=\"15\">";


                                                    delCompany('ntp040',trId);
                                                    $('#ntp040CompanyIdArea' + trId).html(
                                                            "<input name=\"ntp040CompanySid"     + trId + "\" value=\"" + schData.companySid + "\" type=\"hidden\">");
                                                    $('#ntp040CompanyBaseIdArea' + trId).html(
                                                            "<input name=\"ntp040CompanyBaseSid" + trId + "\" value=\"" + schData.companyBaseSid + "\" type=\"hidden\">");

                                                    $('#ntp040CompanyCodeArea' + trId).html("<span class=\"text_anken_code\">" + comcodestr
                                                            + "<span class=\"comp_code_name" + trId + "\">"
                                                            + schData.companyCode
                                                            + "</span>"
                                                            + "</span>");

                                                    $('#ntp040CompNameArea' + trId).html("<div class=\"text_company\">"
                                                            + "<a id=\"" + schData.companySid + "\""
                                                            + "class=\"sc_link comp_click\">"
                                                            + "<span class=\"comp_name" + trId + "\">"
                                                            + schData.companyName + " " + schData.companyBaseName
                                                            + "</span>"
                                                            + "</a></div>"
                                                            + "<a href=\"javascript:void(0);\" onclick=\"delCompany('ntp040','" + trId + "');\">"
                                                            + comdelstr + "</a>");

                                                }
                                            }
                                        });

                                    if (addRow == 0) {
                                        addRow = selAddRow;
                                    }
                                    selAddRow = 0;

                                }
                                $(this).dialog('close');
                                footerStart("#pos" + addRow);
                            },
                            ｷｬﾝｾﾙ: function() {

                                //表示項目をリセット
                                resetScheduleObj();
                                $(this).dialog('close');
                            }
                        }
                    });
                }
            });
    });

    //TODOデータ取得
    $(".prjDataGetBtn").live("click", function(){

        var prjUsrSid = document.forms['ntp040Form'].ntp010SelectUsrSid.value;

        var year  = $('input:hidden[name=ntp040InitYear]').val();
        var month = $('input:hidden[name=ntp040InitMonth]').val();
        var day   = $('input:hidden[name=ntp040InitDay]').val();

        if (month.length < 2) {
            month = "0" + month;
        }

        if (day.length < 2) {
            day = "0" + day;
        }

        var schDspDate = year + month + day;


        var prjDspDate = year + month + day;

        //プロジェクトTODO取得
        $.ajaxSetup({async:false});
        $.post('../nippou/ntp040.do', {
                       "cmd":"getPrjDataList",
                       "CMD":"getPrjDataList",
                       "prjUsrSid":prjUsrSid,
                       "prjDspDate":prjDspDate},
            function(data) {
                if (data != null || data != "") {

                    var prjDataStr = "";

                    if (data.length > 0) {

                        for (i = 0; i < data.length; i++) {

                            prjDataStr += "<tr class=\"td_type1\" style=\"font-size:90%;cursor:pointer;\" id=\"tr_" + data[i].prjSid + "_" + data[i].todoSid + "\">"
                                       +  "<td onclick=\"clickSchName(3," + "'" + data[i].prjSid + "_" + data[i].todoSid + "'" + ");\" width=\"35\">"
                                       +  "<input type=\"checkbox\" name=\"popProject\" value=\"" + data[i].prjSid + "_" + data[i].todoSid + "\" onclick=\"clickMulti();\">"
                                       +  "</td>"
                                       +  "<td onclick=\"clickSchName(3," + "'" + data[i].prjSid + "_" + data[i].todoSid + "'" + ");\" align=\"center\" width=\"110\" nowrap=\"nowrap\">" + data[i].prjStartDate + "</td>"
                                       +  "<td onclick=\"clickSchName(3," + "'" + data[i].prjSid + "_" + data[i].todoSid + "'" + ");\" align=\"center\" width=\"205\" nowrap=\"nowrap\">" + data[i].prjTitle + "</td>"
                                       +  "<td width=\"70\" nowrap=\"nowrap\">"
                                       +  "<input id=\"" + data[i].prjSid + "_" + data[i].todoSid + "\" class=\"ntp_shousai_btn prj_detail_btn\" value=\"詳細\" type=\"button\">"
                                       +  "</td>"
                                       +  "</tr>";
                        }
                    } else {
                        prjDataStr += "<tr>"
                        + "<td align=\"center\" width=\"100%\" colspan=\"4\" nowrap=\"nowrap\">"
                        + "<span style=\"font-size:14px;color:#ff0000;font-weight:bold;\">該当するTODOがありません。</span>"
                        + "</td>"
                        + "</tr>";
                    }

                    $('#prjDataTrArea').html(prjDataStr);

                    /* TODO選択画面ポップアップ */
                    $('#prjDataPop').dialog({
                        autoOpen: true,  // hide dialog
                        bgiframe: true,   // for IE6
                        resizable: false,
                        height: 400,
                        width: 500,
                        modal: true,
                        overlay: {
                          backgroundColor: '#000000',
                          opacity: 0.5
                        },
                        buttons: {
                            選択: function() {

                                //選択値取得
                                var projectList   = $("input:checkbox[name='popProject']:checked");
                                //データ記述行番号(先頭)
                                var addRow = 0;
                                //データ記述行番号
                                var selAddRow = 0;
                                //データ記述行識別文字
                                var selRowNum = "";


                                //画面表示
                                for (var j = 0; j < projectList.length; j++) {


                                    //プロジェクトTODO取得
                                    $.ajaxSetup({async:false});
                                    $.post('../nippou/ntp040.do', {
                                                   "cmd":"getPrjSelectData",
                                                   "CMD":"getPrjSelectData",
                                                   "selPrjTodoSid":projectList[j].value},
                                        function(prjData) {
                                            if (prjData != null || prjData != "") {

                                                //データを入力する行をさがす
                                                for (i = 1; i <= row_number; i++) {

                                                    if (selAddRow == 0) {
                                                        if (i != 1) {
                                                            selRowNum = "_" + i;
                                                        }

                                                        if ($('input[name=ntp040Title' + selRowNum + ']').val() == "") {
                                                            //タイトルが入力されていない場合はデータ入力
                                                            if (selAddRow == 0) {
                                                                selAddRow = i;
                                                            }
                                                        } else {
                                                            //データを入力できる行がない場合は行追加
                                                            if (i == row_number && selAddRow == 0) {
                                                                addNewRow();
                                                                selAddRow = row_number;
                                                            }
                                                        }
                                                    }
                                                }

                                                var trId = "";
                                                if (selAddRow > 1) {
                                                    trId = "_" + selAddRow;
                                                }

                                                $('input[name=ntp040Title'   + trId + ']').val(prjData.title);

                                            }
                                        });

                                    if (addRow == 0) {
                                        addRow = selAddRow;
                                    }
                                    selAddRow = 0;

                                }
                                $(this).dialog('close');
                                footerStart("#pos" + addRow);
                            },
                            ｷｬﾝｾﾙ: function() {

                                //表示項目をリセット
                                resetProjectObj();
                                $(this).dialog('close');
                            }
                        }
                    });
                }
            });
    });

    footerStart("#initSelect");

    if ($('#inputstr')[0] != null) {
        showLengthId($('#inputstr')[0], 1000, 'inputlength');
    }

    if ($('#actionstr')[0] != null) {
        showLengthId($('#actionstr')[0], 1000, 'actionlength');
    }

});



//行追加(新規登録時)
function addNewRow(){


    //前の日報で設定されている終了時を取得
    var toRowId = "";
    var nextFromHour = "";
    var nextToHour = "";

    if ( row_number != 1) {
        toRowId = "_" + row_number;
    }

    nextFromHour = parseInt($("select[name=ntp040ToHour" + toRowId + "]").val());

    nextToHour   = parseInt(nextFromHour) + 1;

    if (nextFromHour >= 33) {
        nextFromHour = 33;
    }

    if (nextToHour >= 33) {
        nextToHour = 33;
    }

    var td_class = 'td_wt';
    row_number++;


    /* option 時 */
    var frhourOptionStr = "";
    var tohourOptionStr = "";
    var frHourSelect    = "";
    var toHourSelect    = "";

    if (nextFromHour == "" || isNaN(nextFromHour)) {
        frHourSelect = $('#frhourhide').val();
    } else {
        frHourSelect = nextFromHour;
    }

    if (nextToHour == "" || isNaN(nextToHour)) {
        toHourSelect = $('#tohourhide').val();
    } else {
        toHourSelect = nextToHour;
    }


    $('.hourclass').each(function() {
        var hourStr = $(this).attr('value').split("_");
        if (hourStr[0] == frHourSelect) {
            frhourOptionStr += "<option value=\"" + hourStr[0] + "\" selected=\"selected\">" + hourStr[1] + "</option>";
        } else {
            frhourOptionStr += "<option value=\"" + hourStr[0] + "\">" + hourStr[1] + "</option>";
        }

        if (hourStr[0] == toHourSelect) {
            tohourOptionStr += "<option value=\"" + hourStr[0] + "\" selected=\"selected\">" + hourStr[1] + "</option>";
        } else {
            tohourOptionStr += "<option value=\"" + hourStr[0] + "\">" + hourStr[1] + "</option>";
        }
    });

    /* option 分 */
    var frminOptionStr = "";
    var tominOptionStr = "";
    var frMinSelect = $('#frminhide').val();
    var toMinSelect = $('#tominhide').val();

    $('.minclass').each(function() {
        var minStr = $(this).attr('value').split("_");
        if (minStr[0] == frMinSelect) {
            frminOptionStr += "<option value=\"" + minStr[0] + "\" selected=\"selected\">" + minStr[1] + "</option>";
        } else {
            frminOptionStr += "<option value=\"" + minStr[0] + "\">" + minStr[1] + "</option>";
        }

        if (minStr[0] == toMinSelect) {
            tominOptionStr += "<option value=\"" + minStr[0] + "\" selected=\"selected\">" + minStr[1] + "</option>";
        } else {
            tominOptionStr += "<option value=\"" + minStr[0] + "\">" + minStr[1] + "</option>";
        }
    });



    /* 案件       企業・顧客 */
    var ankenCompanyStr = "";
    var noAnkenCompanyStr= "";
    if ($('input:hidden[name=ntp040AnkenCompanyUse]').val() == 0) {
        //両方
        ankenCompanyStr = "<tr>"
                        + "<td class=\"table_bg_A5B4E1\" nowrap><span class=\"text_bb1\">案件</span></td>"
                        + "<td width=\"30%\" align=\"left\" class=\"" + td_class + " ntp_add_td\" nowrap>"
                        + "<input type=\"button\" class=\"btn_search_n1\" value=\"案件検索\" onclick=\"return openAnkenWindow('ntp040','" + row_number + "')\" />&nbsp"
                        + "<input type=\"button\" class=\"ankenHistoryPop btn_history\" id=\"" + row_number + "\" value=\"履歴\" /><br>"
                        + "<img src=\"../schedule/images/spacer.gif\" width=\"1px\" border=\"0\" height=\"10px\">"
                        + "<div id=\"ntp040AnkenIdArea_"   + row_number + "\"></div>"
                        + "<div id=\"ntp040AnkenCodeArea_" + row_number + "\"></div>"
                        + "<div id=\"ntp040AnkenNameArea_" + row_number + "\"></div>"
                        + "</td>"
                        + "<td class=\"table_bg_A5B4E1\" nowrap><span class=\"text_bb1\">企業・顧客</span></td>"
                        + "<td align=\"left\" class=\"" + td_class + "\" >"
                        + "<input type=\"button\" class=\"btn_address_n2\" value=\"アドレス帳\" onclick=\"return openCompanyWindow2('ntp040'," + row_number + ")\" /> "
                        + "<input type=\"button\" class=\"adrHistoryPop btn_history\" id=\"" + row_number + "\" value=\"履歴\" /><br>"
                        + "<img src=\"../schedule/images/spacer.gif\" width=\"1px\" border=\"0\" height=\"10px\">"
                        + "<div id=\"ntp040CompanyIdArea_"     + row_number + "\"></div>"
                        + "<div id=\"ntp040CompanyBaseIdArea_" + row_number + "\"></div>"
                        + "<div id=\"ntp040CompanyCodeArea_"   + row_number + "\"></div>"
                        + "<div id=\"ntp040CompNameArea_"      + row_number + "\"></div>"
                        + "<div id=\"ntp040AddressIdArea_"     + row_number + "\"></div>"
                        + "<div id=\"ntp040AddressNameArea_"   + row_number + "\"></div>"
                        + "</td>"
                        + "</tr>";
    } else if ($('input:hidden[name=ntp040AnkenCompanyUse]').val() == 1) {
        //案件のみ
        ankenCompanyStr = "<tr>"
                        + "<td class=\"table_bg_A5B4E1\" nowrap><span class=\"text_bb1\">案件</span></td>"
                        + "<td width=\"30%\" align=\"left\" class=\"td_ntp_wt2 ntp_add_td\" nowrap>"
                        + "<input type=\"button\" class=\"btn_search_n1\" value=\"案件検索\" onclick=\"return openAnkenWindow('ntp040','" + row_number + "')\" />&nbsp"
                        + "<input type=\"button\" class=\"ankenHistoryPop btn_history\" id=\"" + row_number + "\" value=\"履歴\" /><br>"
                        + "<img src=\"../schedule/images/spacer.gif\" width=\"1px\" border=\"0\" height=\"10px\">"
                        + "<div id=\"ntp040AnkenIdArea_"   + row_number + "\"></div>"
                        + "<div id=\"ntp040AnkenCodeArea_" + row_number + "\"></div>"
                        + "<div id=\"ntp040AnkenNameArea_" + row_number + "\"></div>"
                        + "</td>"
                        + "<td class=\"td_ntp_wt4\"></td><td align=\"left\" class=\"td_ntp_wt3\" ></td>"
                        + "</tr>";
    } else if ($('input:hidden[name=ntp040AnkenCompanyUse]').val() == 2) {
        //企業・顧客のみ
        ankenCompanyStr = "<tr>"
                        + "<td class=\"table_bg_A5B4E1\" nowrap><span class=\"text_bb1\">企業・顧客</span></td>"
                        + "<td align=\"left\" class=\"td_ntp_wt2 ntp_add_td\" >"
                        + "<input type=\"button\" class=\"btn_address_n2\" value=\"アドレス帳\" onclick=\"return openCompanyWindow2('ntp040'," + row_number + ")\" /> "
                        + "<input type=\"button\" class=\"adrHistoryPop btn_history\" id=\"" + row_number + "\" value=\"履歴\" /><br>"
                        + "<img src=\"../schedule/images/spacer.gif\" width=\"1px\" border=\"0\" height=\"10px\">"
                        + "<div id=\"ntp040CompanyIdArea_"     + row_number + "\"></div>"
                        + "<div id=\"ntp040CompanyBaseIdArea_" + row_number + "\"></div>"
                        + "<div id=\"ntp040CompanyCodeArea_"   + row_number + "\"></div>"
                        + "<div id=\"ntp040CompNameArea_"      + row_number + "\"></div>"
                        + "<div id=\"ntp040AddressIdArea_"     + row_number + "\"></div>"
                        + "<div id=\"ntp040AddressNameArea_"   + row_number + "\"></div>"
                        + "</td>"
                        + "<td class=\"td_ntp_wt4\"></td><td align=\"left\" class=\"td_ntp_wt3\" ></td>"
                        + "</tr>";
    } else if ($('input:hidden[name=ntp040AnkenCompanyUse]').val() == 3) {
        //なし
        noAnkenCompanyStr = "<tr><td></td><td></td><td></td><td></td></tr>";
    }




    /* 活動分類 */
    var ktbunruiHouhouStr = "";
    if ($('input:hidden[name=ntp040KtBriHhuUse]').val() == 0) {

        /* option 活動分類 */
        var ktbunruiOptionStr = "";
        var frBunruiSelect = $('#ktbunruihide').val();

        $('.ktbunruiclass').each(function() {
            var bunruiStr = $(this).attr('value').split("_");
            if (bunruiStr[0] == frBunruiSelect) {
                ktbunruiOptionStr += "<option value=\"" + bunruiStr[0] + "\" selected=\"selected\">" + replaceHtmlTag(bunruiStr[1]) + "</option>";
            } else {
                ktbunruiOptionStr += "<option value=\"" + bunruiStr[0] + "\">" + replaceHtmlTag(bunruiStr[1]) + "</option>";
            }
        });


        /* option 活動方法 */
        var kthouhouOptionStr = "";
        var houhouSelect = $('#kthouhouhide').val();

        $('.kthouhouclass').each(function() {
            var houhouStr = $(this).attr('value').split("_");
            if (houhouStr[0] == houhouSelect) {
                kthouhouOptionStr += "<option value=\"" + houhouStr[0] + "\" selected=\"selected\">" + replaceHtmlTag(houhouStr[1]) + "</option>";
            } else {
                kthouhouOptionStr += "<option value=\"" + houhouStr[0] + "\">" + replaceHtmlTag(houhouStr[1]) + "</option>";
            }
        });

        ktbunruiHouhouStr = "<tr>"
                          + "<td class=\"table_bg_A5B4E1\" nowrap=\"nowrap\"><span class=\"text_bb1\">活動分類/方法</span></td>"
                          + "<td class=\"" + td_class + " ntp_add_td\" colspan=\"3\" align=\"left\">"
                          + "<select name=\"ntp040Ktbunrui_" + row_number + "\">"
                          + ktbunruiOptionStr
                          + "</select>&nbsp;"
                          + "<select name=\"ntp040Kthouhou_" + row_number + "\">"
                          + kthouhouOptionStr
                          + "</select>"
                          + "</td>"
                          + "</tr>";

    }


    /* 見込み度 */
    var mikomidoStr = "";
    var mikomidoStandStr = "";
    if ($('input:hidden[name=ntp040MikomidoUse]').val() == 0) {

        //見込み度基準
        if ($('input:hidden[name=ntp040MikomidoFlg]').val() == 1) {
            mikomidoStandStr = "<br><input class=\"mikomido_btn mikomido_back\" type=\"button\" value=\"基 準\" />";
        }

        mikomidoStr ="<tr>"
                    + "<td class=\"table_bg_A5B4E1\" nowrap=\"nowrap\"><span class=\"text_bb1\">見込み度</span></td>"
                    + "<td class=\"" + td_class + " ntp_add_td\" colspan=\"3\" align=\"left\">"
                    + "<span class=\"text_base\">"
                    + "<input name=\"ntp040Mikomido_" + row_number + "\" value=\"0\" checked=\"checked\" id=\"ntp040Mikomido0" + row_number + "\" type=\"radio\"><label for=\"ntp040Mikomido0" + row_number + "\">10%</label>"
                    + "<input name=\"ntp040Mikomido_" + row_number + "\" value=\"1\" id=\"ntp040Mikomido1" + row_number + "\" type=\"radio\"><label for=\"ntp040Mikomido1" + row_number + "\">30%</label>"
                    + "<input name=\"ntp040Mikomido_" + row_number + "\" value=\"2\" id=\"ntp040Mikomido2" + row_number + "\" type=\"radio\"><label for=\"ntp040Mikomido2" + row_number + "\">50%</label>"
                    + "<input name=\"ntp040Mikomido_" + row_number + "\" value=\"3\" id=\"ntp040Mikomido3" + row_number + "\" type=\"radio\"><label for=\"ntp040Mikomido3" + row_number + "\">70%</label>"
                    + "<input name=\"ntp040Mikomido_" + row_number + "\" value=\"4\" id=\"ntp040Mikomido4" + row_number + "\" type=\"radio\"><label for=\"ntp040Mikomido4" + row_number + "\">100%</label>"
                    + "</span>"
                    + mikomidoStandStr
                    + "</td>"
                    + "</tr>";
    }


    /* 添付ファイル */
    var tempFileStr = "";
    if ($('input:hidden[name=ntp040TmpFileUse]').val() == 0) {
        tempFileStr = "<tr>"
                    + "<td class=\"table_bg_A5B4E1\" nowrap><span class=\"text_bb1\">添付<a id=\"naiyou\" name=\"naiyou\"></a></span></td>"
                    + "<td align=\"left\"  class=\"" + td_class + " ntp_add_td\" colspan=\"3\">"
                    + "<select name=\"ntp040selectFiles" + row_number + "\" multiple=\"multiple\" size=\"3\" class=\"select01\"></select>"
                    + "<input type=\"button\" class=\"btn_attach\" value=\"添付\" name=\"attacheBtn\" onClick=\"opnTempPlus('ntp040selectFiles" + row_number + "', 'nippou', '0', '0', 'row" + row_number + "');\">"
                    + "&nbsp;  "
                    + "<input type=\"button\" class=\"btn_delete\" name=\"tempDelBtn\" id=\"" + row_number + "\" value=\"削除\">"
                    + "</td>"
                    + "</tr>";
    }



    /* 次のアクション */
    var actionAreaStr = "";
    if ($('input:hidden[name=ntp040NextActionUse]').val() == 0) {

        /* 年コンボ */
        var yearOptionStr = "";
        var yearSelect    = $('input:hidden[name=ntp040InitYear]').val();
        $('.yearclass').each(function() {
            var yearStr = $(this).attr('value').split("_");
            if (yearStr[0] == yearSelect) {
                yearOptionStr += "<option value=\"" + yearStr[0] + "\" selected=\"selected\">" + replaceHtmlTag(yearStr[1]) + "</option>";
            } else {
                yearOptionStr += "<option value=\"" + yearStr[0] + "\">" + replaceHtmlTag(yearStr[1]) + "</option>";
            }
        });

        /* 月コンボ */
        var monthOptionStr = "";
        var monthSelect    = $('input:hidden[name=ntp040InitMonth]').val();
        $('.monthclass').each(function() {
            var monthStr = $(this).attr('value').split("_");
            if (monthStr[0] == monthSelect) {
                monthOptionStr += "<option value=\"" + monthStr[0] + "\" selected=\"selected\">" + replaceHtmlTag(monthStr[1]) + "</option>";
            } else {
                monthOptionStr += "<option value=\"" + monthStr[0] + "\">" + replaceHtmlTag(monthStr[1]) + "</option>";
            }
        });

        /* 日コンボ */
        var dayOptionStr = "";
        var daySelect    = $('input:hidden[name=ntp040InitDay]').val();
        $('.dayclass').each(function() {
            var dayStr = $(this).attr('value').split("_");
            if (dayStr[0] == daySelect) {
                dayOptionStr += "<option value=\"" + dayStr[0] + "\" selected=\"selected\">" + replaceHtmlTag(dayStr[1]) + "</option>";
            } else {
                dayOptionStr += "<option value=\"" + dayStr[0] + "\">" + replaceHtmlTag(dayStr[1]) + "</option>";
            }
        });

        actionAreaStr = "<tr>"
                     + "<td class=\"table_bg_A5B4E1\" nowrap=\"\"><span class=\"text_bb1\">次のアクション<a id=\"nextaction\" name=\"nextaction\"></a></span></td>"
                     + "<td class=\"td_wt ntp_add_td\" colspan=\"3\" align=\"left\">"
                     + "<span style=\"color:#000000;font-size:12px;font-weight:bold;\">&nbsp;日付指定：</span>"
                     + "<input name=\"ntp040ActDateKbn_" + row_number + "\" value=\"1\" onchange=\"toggleActionAreaShow('nxtActDateArea_" + row_number + "');\" id=\"actDate1_" + row_number + "\" type=\"radio\">"
                     + "<label for=\"actDate1_" + row_number + "\" class=\"text_base\" style=\"color:#000000;font-size:12px;\">する</label>"
                     + "<input name=\"ntp040ActDateKbn_" + row_number + "\" value=\"0\" checked=\"checked\" onchange=\"toggleActionAreaHide('nxtActDateArea_" + row_number + "');\" id=\"actDate0_" + row_number + "\" type=\"radio\">"
                     + "<label for=\"actDate0_" + row_number + "\" class=\"text_base\" style=\"color:#000000;font-size:12px;\">しない</label>"
                     + "<div id=\"nxtActDateArea_" + row_number + "\" style=\"display:none;\">"
                     + "<select name=\"ntp040NxtActYear_" + row_number + "\" id=\"selActionYear" + row_number + "\">"
                     + yearOptionStr
                     + "</select>&nbsp;"
                     + "<select name=\"ntp040NxtActMonth_" + row_number + "\" id=\"selActionMonth" + row_number + "\">"
                     + monthOptionStr
                     + "</select>&nbsp;"
                     + "<select name=\"ntp040NxtActDay_" + row_number + "\" id=\"selActionDay" + row_number + "\">"
                     + dayOptionStr
                     + "</select>&nbsp;"
                     + "<input class=\"btn_arrow_l\" value=\"&nbsp;\" onclick=\"return moveDay($('#selActionYear" + row_number + "')[0], $('#selActionMonth" + row_number + "')[0], $('#selActionDay" + row_number + "')[0], 1)\" type=\"button\">&nbsp;"
                     + "<input class=\"btn_today\" value=\"今日\" onclick=\"return moveDay($('#selActionYear" + row_number + "')[0], $('#selActionMonth" + row_number + "')[0], $('#selActionDay" + row_number + "')[0], 2)\" type=\"button\">&nbsp;"
                     + "<input class=\"btn_arrow_r\" value=\"&nbsp;\" onclick=\"return moveDay($('#selActionYear" + row_number + "')[0], $('#selActionMonth" + row_number + "')[0], $('#selActionDay" + row_number + "')[0], 3)\" type=\"button\">&nbsp;"
                     + "<input value=\"Cal\" onclick=\"wrtCalendarByBtn(this.form.selActionDay" + row_number + ", this.form.selActionMonth" + row_number + ", this.form.selActionYear" + row_number + ", 'ntp040ActionCalBtn" + row_number + "')\" class=\"calendar_btn\" id=\"ntp040ActionCalBtn" + row_number + "\" type=\"button\">"
                     + "</div>"
                     + "<div>"
                     + "<textarea name=\"ntp040NextAction_" + row_number + "\" style=\"width:431px;\" rows=\"2\" class=\"text_base\" onkeyup=\"showLengthStr(value, 1000, 'actionlength_" + row_number + "');\" id=\"actionstr_" + row_number + "\"></textarea>"
                     + "<br>"
                     + "<span class=\"font_string_count\">現在の文字数:</span><span id=\"actionlength_" + row_number + "\" class=\"font_string_count\">0</span>&nbsp;<span class=\"font_string_count_max\">/&nbsp;1000&nbsp;文字</span>"
                     + "</div>"
                     + "</td>"
                     + "</tr>";
    }

    //内容初期値
    var defaultValue = "";
    if ($('input:hidden[name=ntp040DefaultValue]').val() != null) {
        defaultValue = $('input:hidden[name=ntp040DefaultValue]').val();
    }

    var defaultLength = "0";
    if ($('input:hidden[name=ntp040DefaultValue2]').val() != null) {
        var defaultValue2 = "";
        defaultValue2 = $('input:hidden[name=ntp040DefaultValue2]').val();
        defaultLength = defaultValue2.length;
    }


    //カラーコメント
    var titileColStr1 = replaceHtmlTag($('#msgCol1').val());
    var titileColStr2 = replaceHtmlTag($('#msgCol2').val());
    var titileColStr3 = replaceHtmlTag($('#msgCol3').val());
    var titileColStr4 = replaceHtmlTag($('#msgCol4').val());
    var titileColStr5 = replaceHtmlTag($('#msgCol5').val());

    /* 日報行追加 */
    $('#nippoutable').append("<tr id=\"" + row_number + "\" class=\"tr_nippou\">"
            + "<td colspan=\"6\">"
            + "<table width=\"100%\"><tr><td>"
            + "<tr>"
            + "<td colspan=\"6\" height=\"25px\"></td>"
            + "</tr>"
            + "<tr id=\"" + row_number + "\">"
            + "<td colspan=\"2\" class=\"nippou_info_bg_left\"><div id=\"pos" + row_number + "\"></div>NO," + row_number + "</td>"
            + "<td colspan=\"3\" align=\"right\" class=\"nippou_info_bg\"><input class=\"close_btn1\" id=\"trDellBtn\" value=\"削除\" type=\"button\"></td>"
            + "<td colspan=\"6\" height=\"25px\"></td>"
            + "</tr>"
            + "<tr>"
            + "<td class=\"table_bg_A5B4E1\" width=\"10%\" nowrap><span class=\"text_bb1\">時間</span><span class=\"text_r2\">※</span></td>"
            + "<td align=\"left\" width=\"90%\" class=\"" + td_class + " ntp_add_td\" colspan=\"3\">"
            + "<select name=\"ntp040FrHour_" + row_number + "\" onchange=\"setToDay();\">"
            + frhourOptionStr
            + "</select>"
            + "  時  "
            + "<select name=\"ntp040FrMin_" + row_number + "\" onchange=\"setToDay();\">"
            + frminOptionStr
            + "</select>"
            + "  分  "
            + "  ～  "
            + "<select name=\"ntp040ToHour_" + row_number + "\" onchange=\"setToDay();\">"
            + tohourOptionStr
            + "</select>"
            + "  時  "
            + "<select name=\"ntp040ToMin_" + row_number + "\" onchange=\"setToDay();\">"
            + tominOptionStr
            + "</select>"
            + "  分  "
            + "&nbsp;&nbsp;&nbsp;&nbsp;<span id=\"betWeenDays\" class=\"text_base\"></span>"
            + "</td>"
            + "</tr>"
            +  ankenCompanyStr
            +  ktbunruiHouhouStr
            +  mikomidoStr
            + "<tr>"
            + "<td class=\"table_bg_A5B4E1\" nowrap=\"nowrap\"><span class=\"text_bb1\">タイトル</span><span class=\"text_r2\">※</span></td>"
            + "<td class=\"" + td_class + " ntp_add_td\" colspan=\"3\" align=\"left\">"
            + "<input name=\"ntp040Title_" + row_number + "\" maxlength=\"100\" value=\"\" id=\"selectionSearchArea\" class=\"text_base\" type=\"text\" style=\"width:337px;\">"
            + "&nbsp;<span class=\"sc_block_color_1\"><input name=\"ntp040Bgcolor_" + row_number + "\" value=\"1\" id=\"bg_color1\" type=\"radio\"></span>"
            + "<label for=\"bg_color1\" class=\"text_base\">" + titileColStr1 + "</label>"
            + "&nbsp;<span class=\"sc_block_color_2\"><input name=\"ntp040Bgcolor_" + row_number + "\" value=\"2\" id=\"bg_color2\" type=\"radio\"></span>"
            + "<label for=\"bg_color2\" class=\"text_base\">" + titileColStr2 + "</label>"
            + "&nbsp;<span class=\"sc_block_color_3\"><input name=\"ntp040Bgcolor_" + row_number + "\" value=\"3\" id=\"bg_color3\" type=\"radio\"></span>"
            + "<label for=\"bg_color3\" class=\"text_base\">" + titileColStr3 + "</label>"
            + "&nbsp;<span class=\"sc_block_color_4\"><input name=\"ntp040Bgcolor_" + row_number + "\" value=\"4\" id=\"bg_color4\" type=\"radio\"></span>"
            + "<label for=\"bg_color4\" class=\"text_base\">" + titileColStr4 + "</label>"
            + "&nbsp;<span class=\"sc_block_color_5\"><input name=\"ntp040Bgcolor_" + row_number + "\" value=\"5\" id=\"bg_color5\" type=\"radio\"></span>"
            + "<label for=\"bg_color5\" class=\"text_base\">" + titileColStr5 + "</label>"
            + "</td>"
            + "</tr>"
            + "<tr>"
            + "<td class=\"table_bg_A5B4E1\" nowrap=\"nowrap\"><span class=\"text_bb1\">内　容<a id=\"naiyou\" name=\"naiyou\"></a></span></td>"
            + "<td class=\"" + td_class + " ntp_add_td\" colspan=\"3\" align=\"left\">"
            + "<textarea name=\"ntp040Value_" + row_number + "\" style=\"width:431px;\" rows=\"5\" styleclass=\"text_base\" onkeyup=\"showLengthStr(value, 1000, 'inputlength" + row_number + "');\" id=\"inputstr\">"
            + defaultValue2
            + "</textarea>"
            + "<br>"
            + "<span class=\"font_string_count\">現在の文字数:</span><span id=\"inputlength" + row_number + "\" class=\"font_string_count\">" + defaultLength + "</span>&nbsp;<span class=\"font_string_count_max\">/&nbsp;1000&nbsp;文字</span>"
            + "</td>"
            + "</tr>"
            + tempFileStr
            + actionAreaStr
            + noAnkenCompanyStr
            + "</td></tr></table>"
            + "</td>"
            + "</tr>"
        );


    //ラジオボタン初期値設定
    $('input[name="ntp040Bgcolor_' + row_number + '"]').val([$("input:hidden[name='ntp040BgcolorInit']").val()]);

    footerStart("#footdiv");

}

function footerStart(selector){
  if ($(selector).offset() != null) {
      $('html,body').animate({scrollTop: $(selector).offset().top - 140},'slow');
  }
}

function footerStart2(selector){
    if ($(selector).offset() != null) {
        $('html,body').animate({scrollTop: $(selector).offset().top - 730},'slow');
    }
  }

function fileLinkClick(ntpSid, binSid) {
    url = "../nippou/ntp040.do?CMD=fileDownload&ntp010NipSid=" + ntpSid + "&ntp040BinSid=" + binSid;
    navframe.location=url;
}


/* 日報のJsonデータを形成
 * mode:0 新規登録  1:編集
 */
function createJsonData(rowElm, mode){

    //エラーメッセージ削除
    $("#error_msg").text("");

    /* json形成 */
    var nippouData = "[";
    var jsonStr    = "";

    if (mode == 0) {
        /*新規登録 */
        var rownum = "";
        $('.tr_nippou').each(function() {
            //登録日
            var ntpYear  = $("select[name=ntp040FrYear]").val();
            var ntpMonth = $("select[name=ntp040FrMonth]").val();
            var ntpDay   = $("select[name=ntp040FrDay]").val();

            rownum = $(this).attr('id');
            nippouData = getNtpData(ntpYear, ntpMonth, ntpDay, rownum, nippouData);
        });
    } else {
        /*編集 */
        rownum = rowElm.attr("id");

        //登録日
        var ntpYear  = $("#selYear"  + rownum).val();
        var ntpMonth = $("#selMonth" + rownum).val();
        var ntpDay   = $("#selDay"   + rownum).val();

        nippouData = getNtpData(ntpYear, ntpMonth, ntpDay, rownum, nippouData);
    }
    nippouData += "]";

    return nippouData;
}

/* 行ごとのJsonデータを形成 */
function getNtpData(ntpYear, ntpMonth, ntpDay, rownum, nippouData){

    /* 選択ユーザ */
    var selectUsrSid = document.forms['ntp040Form'].ntp010SelectUsrSid.value;

    if (nippouData != "[") {
        nippouData += ",";
    }

    var trId = "";

    if (rownum != null && rownum != "") {
        trId = "_" + rownum;
    } else {
        rownum = "1";
    }

    /* 時間 */
    var frhour  = $("select[name=ntp040FrHour" + trId + "]").val();
    var frmin   = $("select[name=ntp040FrMin"  + trId + "]").val();
    var tohour  = $("select[name=ntp040ToHour" + trId + "]").val();
    var tomin   = $("select[name=ntp040ToMin"  + trId + "]").val();

    /* 案件 */
    var ankenSid       = -1;
    if ($("input:hidden[name=ntp040AnkenSid"     + trId + "]").val() !== undefined) {

        ankenSid = $("input[name=ntp040AnkenSid" + trId + "]").val();
    }

    /* 企業 */
    var companySid     = -1;
    if ($("input:hidden[name=ntp040CompanySid"       + trId + "]").val() !== undefined) {
        companySid = $("input[name=ntp040CompanySid" + trId + "]").val();
    }

    /* 企業(拠点) */
    var companyBaseSid = -1;
    if ($("input:hidden[name=ntp040CompanyBaseSid"           + trId + "]").val() !== undefined) {
        companyBaseSid = $("input[name=ntp040CompanyBaseSid" + trId + "]").val();
    }

    /* 活動分類・方法 */
    var ktbunruiSid = -1;
    var kthouhouSid = -1;
    if ($('input:hidden[name=ntp040KtBriHhuUse]').val() == 0) {
        /* 活動分類 */
        ktbunruiSid    = $("select[name=ntp040Ktbunrui" + trId + "]").val();
        /* 活動方法 */
        kthouhouSid    = $("select[name=ntp040Kthouhou" + trId + "]").val();
    }

    /* 見込み度 */
    var mikomido = 0;
    if ($('input:hidden[name=ntp040MikomidoUse]').val() == 0) {
        mikomido       = $("input[name=ntp040Mikomido"  + trId + "]:checked").val();
    }

    /* 背景色 */
    var bgColorStr     = $("input[name=ntp040Bgcolor"   + trId + "]:checked").val();
    /* タイトル */
    var titleStr       = encodeURIComponent($("input[name=ntp040Title"    + trId + "]").val());
    /* 内容 */
    var valueStr       = encodeURIComponent($("textarea[name=ntp040Value" + trId + "]").val());

    /* 次のアクション */
    var actDateKbn    = 0;
    var actionYear    = -1;
    var actionMonth   = -1;
    var actionDay     = -1;
    var actionStr     = "";
    if ($('input:hidden[name=ntp040NextActionUse]').val() == 0) {
        /* 時間指定（次のアクション） */
        actDateKbn     = $("input[name=ntp040ActDateKbn"        + trId + "]:checked").val();

        if (actDateKbn == 1) {
            actionYear    = $("select[name=selActionYear"  + rownum + "]").val();
            actionMonth   = $("select[name=selActionMonth" + rownum + "]").val();
            actionDay     = $("select[name=selActionDay"   + rownum + "]").val();
        }

        /* 次のアクション */
        actionStr       = encodeURIComponent($("textarea[name=ntp040NextAction" + trId + "]").val());
    }


    jsonStr =  "{rowId:"         +  rownum          + ","
            +  "selectUsrSid:"   +  selectUsrSid    + ","
            +  "ntpYear:"        +  ntpYear         + ","
            +  "ntpMonth:"       +  ntpMonth        + ","
            +  "ntpDay:"         +  ntpDay          + ","
            +  "frHour:"         +  frhour          + ","
            +  "frMin:"          +  frmin           + ","
            +  "toHour:"         +  tohour          + ","
            +  "toMin:"          +  tomin           + ","
            +  "ankenSid:"       +  ankenSid        + ","
            +  "companySid:"     +  companySid      + ","
            +  "companyBaseSid:" +  companyBaseSid  + ","
            +  "ktbunruiSid:"    +  ktbunruiSid     + ","
            +  "kthouhouSid:"    +  kthouhouSid     + ","
            +  "mikomido:"       +  mikomido        + ","
            +  "title:\""        +  titleStr        + "\","
            +  "bgcolor:"        +  bgColorStr      + ","
            +  "valueStr:\""     +  valueStr        + "\","
            +  "actDateKbn:"     +  actDateKbn      + ","
            +  "actionYear:"     +  actionYear      + ","
            +  "actionMonth:"    +  actionMonth     + ","
            +  "actionDay:"      +  actionDay       + ","
            +  "actionStr:\""    +  actionStr       + "\"}";

    nippouData += jsonStr;

    return nippouData;
}

/* データ再取得 設定 */
function getResetData(trId, ntpSid, rownum, canselJsonStr){
    //データ送信
    $.ajaxSetup({async:false});
    $.post('../nippou/ntp040.do', {"cmd":"resetData",
                                   "CMD":"resetData",
                                   "resetNtpSid":ntpSid,
                                   "rowNum":rownum,
                                   "nippouTempData":canselJsonStr},
      function(data) {
        if (data != null || data != "") {
            //データ再設定

            //報告日付
            $("select[name=selYear" + rownum + "]").val(data.ntpYear);
            $("select[name=selMonth" + rownum + "]").val(data.ntpMonth);
            $("select[name=selDay" + rownum + "]").val(data.ntpDay);

            //時間
            $("select[name=ntp040FrHour" + trId + "]").val(data.frHour);
            $("select[name=ntp040FrMin"  + trId + "]").val(data.frMin);
            $("select[name=ntp040ToHour" + trId + "]").val(data.toHour);
            $("select[name=ntp040ToMin"  + trId + "]").val(data.toMin);
            $(".dsp_frhour"              + trId).text(data.ntp040DspFrHour);
            $(".dsp_frminute"            + trId).text(data.ntp040DspFrMinute);
            $(".dsp_tohour"              + trId).text(data.ntp040DspToHour);
            $(".dsp_tominute"            + trId).text(data.ntp040DspToMinute);

            //案件
            delAnken('ntp040',trId);
            $('#ntp040AnkenIdArea' + trId).html(
                    "<input name=\"ntp040AnkenSid" + trId + "\" value=\"" + data.ankenSid + "\" type=\"hidden\">");

            var ankencodestr = "";
            var ankendelstr = "";
            if (data.ankenSid != "") {
                ankencodestr = "案件コード：";
                ankendelstr = "<img src=\"../common/images/delete.gif\" class=\"img_bottom\" alt=\"\" width=\"15\">";
            }

            $('#ntp040AnkenCodeArea' + trId).html("<span class=\"text_anken_code\">" + ankencodestr
                    + "<span class=\"anken_code_name" + trId + "\">"
                    + data.ankenCode
                    + "</span>"
                    + "</span>");
            $(".anken_code_name" + trId).text(data.ankenCode);

            $('#ntp040AnkenNameArea' + trId).html("<div class=\"text_anken\">"
                    + "<a id=\"" + data.ankenSid + "\" class=\"sc_link anken_click\">"
                    + "<span class=\"anken_name" + trId + "\">"
                    + data.ankenName
                    + "</span>"
                    + "</a></div>"
                    + "<a href=\"javascript:void(0);\" onclick=\"delAnken('ntp040','" + trId + "');\">"
                    + ankendelstr + "</a>");
            $(".anken_name" + trId).parent().attr('id', data.ankenSid);
            $(".anken_name" + trId).text(data.ankenName);

            //企業・顧客
            var comcodestr = "";
            var comdelstr = "";
            if (data.companySid != "") {
                comcodestr = "企業コード：";
                comdelstr = "<img src=\"../common/images/delete.gif\" class=\"img_bottom\" alt=\"\" width=\"15\">";
            }

            delCompany('ntp040',trId);
            $('#ntp040CompanyIdArea' + trId).html(
                    "<input name=\"ntp040CompanySid"     + trId + "\" value=\"" + data.companySid + "\" type=\"hidden\">");
            $('#ntp040CompanyBaseIdArea' + trId).html(
                    "<input name=\"ntp040CompanyBaseSid" + trId + "\" value=\"" + data.companyBaseSid + "\" type=\"hidden\">");

            $('#ntp040CompanyCodeArea' + trId).html("<span class=\"text_anken_code\">" + comcodestr
                    + "<span class=\"comp_code_name" + trId + "\">"
                    + data.companyCode
                    + "</span>"
                    + "</span>");
            $(".comp_code_name" + trId).text(data.companyCode);

            $('#ntp040CompNameArea' + trId).html("<div class=\"text_company\">"
                    + "<a id=\"" + data.companySid + "\""
                    + "class=\"sc_link comp_click\">"
                    + "<span class=\"comp_name" + trId + "\">"
                    + data.companyName + " " + data.companyBaseName
                    + "</span>"
                    + "</a></div>"
                    + "<a href=\"javascript:void(0);\" onclick=\"delCompany('ntp040','" + trId + "');\">"
                    + comdelstr + "</a>");
            $('.comp_name_link' + trId).attr('id', data.companySid);
            $(".comp_name" + trId).text(data.companyName + " " + data.companyBaseName);

            //活動分類
            $("select[name=ntp040Ktbunrui" + trId + "]").val(data.ktbunruiSid);
            $(".dsp_ktbunrui"              + trId).text(data.ntp040DspKtbunrui);

            //活動方法
            $("select[name=ntp040Kthouhou" + trId + "]").val(data.kthouhouSid);
            $(".dsp_kthouhou"              + trId).text(data.ntp040DspKthouhou);

            //見込み度
            $("input[name=ntp040Mikomido"  + trId + "][value=\"" + data.mikomido + "\"]").attr("checked", true);
            $(".dsp_mikomido"              + trId).text(data.ntp040DspMikomido);

            //タイトル
            $("input[name=ntp040Title"     + trId + "]").val(data.title);
            $(".dsp_title"                 + trId).text(data.title);

            //タイトル色
            $("input[name=ntp040Bgcolor"   + trId + "][value=\"" + data.bgcolor + "\"]").attr("checked", true);

            //内容
            $("textarea[name=ntp040Value"  + trId + "]").val(data.valueStr);
            $(".dsp_naiyou"                + trId).html(data.ntp040DspValueStr);

            //次のアクション
            if ($('input:hidden[name=ntp040NextActionUse]').val() == 0) {
                $("input[name=ntp040ActDateKbn"  + trId + "][value=\"" + data.actDateKbn + "\"]").attr("checked", true);

                $('#actionSelDateArea' + trId).html("");
                if (data.actDateKbn == 1) {
                    $('#actionSelDateArea' + trId).html("&nbsp;日付："
                                                        + "<span class=\"dsp_actionyear"   + trId + "\">" + data.actionYear  + "</span>年"
                                                        + "<span class=\"dsp_actionmonth"  + trId + "\">" + data.actionMonth + "</span>月"
                                                        + "<span class=\"dsp_actionday"    + trId + "\">" + data.actionDay   + "</span>日"
                                                        + "<br>");

                    $("select[name=selActionYear"  + rownum + "]").val(data.actionYear);
                    $("select[name=selActionMonth" + rownum + "]").val(data.actionMonth);
                    $("select[name=selActionDay"   + rownum + "]").val(data.actionDay);
                    $('#nxtActDateArea' + trId).css('display', 'block');
                } else {

                    $("select[name=selActionYear"  + rownum + "]").val($('input:hidden[name=ntp040InitYear]').val());
                    $("select[name=selActionMonth" + rownum + "]").val($('input:hidden[name=ntp040InitMonth]').val());
                    $("select[name=selActionDay"   + rownum + "]").val($('input:hidden[name=ntp040InitDay]').val());
                    $('#nxtActDateArea' + trId).css('display', 'none');

                }

                $("textarea[name=ntp040NextAction"  + trId + "]").val(data.actionStr);
                $(".dsp_nextaction"                + trId).html(data.ntp040DspActionStr);
            }

                //登録者
                $(".addUserName"                 + trId).text(data.ntp040NtpAddUsrName);

                //登録日時
                $(".addDate"                 + trId).text(data.ntp040NtpDate);

            //添付ファイル
            $(".dsp_tmp_file_area"         + trId).html("");
            if (data.ntp040FileList.length > 0) {
                var tmpStr = "";
                for (i=0; i<data.ntp040FileList.length; i++) {
                    if (tmpStr != "") {
                        tmpStr += "<br>";
                    }
                    tmpStr += "<a href=\"javascript:void(0);\" onclick=\"return fileLinkClick("
                           + ntpSid + ","
                           + data.ntp040FileList[i].binSid
                           + ");\"><span class=\"text_link_min\">"
                           + data.ntp040FileList[i].binFileName
                           + data.ntp040FileList[i].binFileSizeDsp
                           + "</span></a>";
                }
                $(".dsp_tmp_file_area"  + trId).html(tmpStr);
            }
        }
    });
}

/* 日報ヘッダー書き換え(編集画面新規登録時) */
function rewriteNtpHeader(ntpSid, rownum){

    $(".nip_header_" + rownum).html("");
    $(".nip_header_" + rownum).html("<span style=\"display: none;\" class=\"editButtonArea" + rownum + "\">"
                                  + "<input class=\"btn_copy_n2\" name=\"ntpCopyBtn\" id=\"" + ntpSid + "\" value=\"複写して登録\" type=\"button\">&nbsp;"
                                  + "</span>"
                                  + "<span style=\"display: none;\" class=\"editButtonArea" + rownum + "\">"
                                  + "<input class=\"btn_edit_n4\" name=\"ntpEditBtn\" id=\"" + rownum + "\" value=\"編集\" type=\"button\">&nbsp;"
                                  + "<input class=\"close_btn1\" id=\"ntpDellBtn\" value=\"削除\" type=\"button\">"
                                  + "</span>"
                                  + "<span class=\"editButtonArea" + rownum + "\" style=\"\">"
                                  + "<input class=\"btn_edit_n4\" id=\"" + rownum + "\" name=\"ntpEditKakuteiBtn\" value=\"確定\" type=\"button\">&nbsp;"
                                  + "<input class=\"close_btn1\" id=\"" + rownum + "\" name=\"ntpEditCancelBtn\" value=\"ｷｬﾝｾﾙ\" type=\"button\">"
                                  + "</span>");

    $(".nip_header_" + rownum).attr('id',ntpSid);
    $(".nip_head_area_" + rownum).attr('id',rownum);
}

/* コメント 再表示 */
function setComment(trId, ntpSid, rownum, cmtdata){

    if (cmtdata.length > 0) {
        $(".commentDspArea" + rownum).remove();
        var commentStr = "";
        for (i=0; i<cmtdata.length; i++) {
            commentStr += "<table class=\"commentDspAreaTable"
                       +  trId
                       +  "_"
                       +  cmtdata[i].commentSid
                       +  "\"><tbody><tr><td>";

            if (cmtdata[i].commentUserBinSid == "0") {
                //写真なし
                commentStr += "<img class=\"comment_Img\" src=\"../user/images/photo.gif\" name=\"userImage\" alt=\"写真\" border=\"1\" width=\"50px\">";
            } else {
                if (cmtdata[i].commentUsiPictKf == "0") {
                    //写真あり 公開
                    commentStr += "<img class=\"comment_Img\" src=\"../common/cmn100.do?CMD=getImageFile&cmn100binSid="
                               +  cmtdata[i].commentUserBinSid + "\""
                               +  " alt=\"写真\" width=\"50px\">";
                } else {
                    //写真あり 非公開
                    commentStr += "<div class=\"photo_hikokai2\" align=\"center\"><span style=\"color: rgb(252, 41, 41);\">非公開</span></div>";
                }
            }

            //削除
            var delComStr = "";
            if (cmtdata[i].commentDelKbn == 1) {
                delComStr = "&nbsp;&nbsp;&nbsp;<span class=\"commentDel\" id=\""
                +  cmtdata[i].commentSid
                +  "\"><img src=\"../nippou/images/delete_icon2.gif\" alt=\"削除\"></span>";
            }
            if (cmtdata[i].commentUsrJkbn == 9) {
                commentStr +=  "</td><td id=\"commentDspAreaTable"
                    +  trId
                    +  "_"
                    +  cmtdata[i].commentSid
                    +  "\" style=\"padding-left: 10px;\" valign=\"top\"><span style=\"font-size: 12px; color: rgb(51, 51, 51);\"><del><b>"
                    +  cmtdata[i].commentUserName
                    +  "</b></del></span>&nbsp;&nbsp;<span style=\"font-size: 12px; color: rgb(51, 51, 51);\">"
                    +  cmtdata[i].commentDate
                    +  "</span>"
                    +  delComStr
                    + "<br></span><span style=\"font-size: 13px; color: rgb(51, 51, 51);\">"
                    +  cmtdata[i].commentValue
                    +  "</span></td></tr></tbody></table><hr class=\"commentDspAreaTable"
                    +  trId
                    +  "_"
                    +  cmtdata[i].commentSid
                    +  "\" style=\"color: rgb(233, 233, 233);\" align=\"center\" width=\"90%\">";

            } else {
                commentStr +=  "</td><td id=\"commentDspAreaTable"
                    +  trId
                    +  "_"
                    +  cmtdata[i].commentSid
                    +  "\" style=\"padding-left: 10px;\" valign=\"top\"><span style=\"font-size: 12px; color: rgb(51, 51, 51);\"><b>"
                    +  cmtdata[i].commentUserName
                    +  "</b></span>&nbsp;&nbsp;<span style=\"font-size: 12px; color: rgb(51, 51, 51);\">"
                    +  cmtdata[i].commentDate
                    +  "</span>"
                    +  delComStr
                    + "<br></span><span style=\"font-size: 13px; color: rgb(51, 51, 51);\">"
                    +  cmtdata[i].commentValue
                    +  "</span></td></tr></tbody></table><hr class=\"commentDspAreaTable"
                    +  trId
                    +  "_"
                    +  cmtdata[i].commentSid
                    +  "\" style=\"color: rgb(233, 233, 233);\" align=\"center\" width=\"90%\">";

            }
        }
        //コメントテキストエリアを空にする
        $("textarea[name=ntp040Comment"  + trId + "]").val("");
        $("#ntp040DspComment"            + trId).html(commentStr);
        $(".ntp040DspComment_tr"         + trId).removeClass("ntp040DspComment_tr_none");
    }
}

/* 表示切替 */
function toggleDsp(rownum){
    $(".editButtonArea" + rownum).toggle();
    $(".usrInfArea"     + rownum).toggle();
    $(".ntpTimeArea"    + rownum).toggle();
    $(".ankenDataArea"  + rownum).toggle();
    $(".kigyouDataArea" + rownum).toggle();
    $(".ktBunruiArea"   + rownum).toggle();
    $(".mikomidoArea"   + rownum).toggle();
    $(".titleArea"      + rownum).toggle();
    $(".naiyouArea"     + rownum).toggle();
    $(".nextActionArea" + rownum).toggle();
    $(".tempFileArea"   + rownum).toggle();
    $(".commentArea"    + rownum).toggle();
    $(".ntpDateAreaTr"  + rownum).toggle();
    $(".ntpAddUNameDate"  + rownum).toggle();
}


/* 日付変更チェック */
function ChangeDateCheck(rownum){

    var changeDateFlg = false;

    //画面登録日
    var dspNtpYear  = $("input[name=ntp040FrYear]").val();
    var dspNtpMonth = $("input[name=ntp040FrMonth]").val();
    var dspNtpDay   = $("input[name=ntp040FrDay]").val();

    //編集データ登録日
    var ntpYear  = $("#selYear"  + rownum).val();
    var ntpMonth = $("#selMonth" + rownum).val();
    var ntpDay   = $("#selDay"   + rownum).val();

    if (dspNtpYear != ntpYear) {
        changeDateFlg = true;
    } else if (dspNtpMonth != ntpMonth) {
        changeDateFlg = true;
    } else if (dspNtpDay != ntpDay) {
        changeDateFlg = true;
    }

    return changeDateFlg;
}

function editNippou(cmd, ymd, sid, ukbn){
    document.forms[0].cmd.value=cmd;
    document.forms[0].CMD.value=cmd;
    document.forms[0].ntp010DspDate.value=ymd;
    document.forms[0].ntp010SelectDate.value=ymd;
    document.forms[0].ntp010SelectUsrKbn.value=0;
    document.forms[0].ntp010NipSid.value=sid;
    document.forms[0].ntp040DspMoveFlg.value=1;
    document.forms[0].submit();
    return false;
}

function replaceHtmlTag(s) {
    return s.replace(/&/g,"&amp;").replace(/"/g,"&quot;").replace(/'/g,"&#039;").replace(/</g,"&lt;").replace(/>/g,"&gt;") ;
}

/* 目標リンククリック */
var offsetTopVal = 0;
function scrollTarger(){

    //クリックした場所の座標を取得
    offsetTopVal = $('.targetSelBtnArea').offset().top;

    $('html,body').animate({scrollTop:0},'slow');
    $('.targetSelBtnArea').toggle();
}

/* ▼リンククリック */
function scrollInit(){

    if (offsetTopVal == 0 && offsetTopVal != null) {
        footerStart("#initSelect");
    } else {
        $('html,body').animate({scrollTop: offsetTopVal - 180},'slow');
    }

    $('.targetSelBtnArea').toggle();
}

//報告日付変更時
function changeNtpDate(){

    var year   = "";
    var month  = "";
    var usrSid = "";
    var nttSid = "";

    if ($('#selYear').val() != null
            && $('#selMonth').val()   != null
            && $('#hideUsrSid').val() != null
            && $('#hideNttSid').val() != null) {

        year   = $('#selYear').val();
        month  = $('#selMonth').val();
        usrSid = $('#hideUsrSid').val();
        nttSid = $('#hideNttSid').val();

        resetTarget(year, month, usrSid, nttSid);

    }
}

//目標再設定
function resetTarget(year, month, usrSid, nttSid){

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp040.do', {
                   "cmd":"getTrgData",
                   "CMD":"getTrgData",
                   "year":year,
                   "month":month,
                   "usrSid":usrSid,
                   "nttSid":nttSid},
        function(data) {
            if (data != null || data != "") {
                $('#hideYear').val("");
                $('#hideYear').val(data.year);
                $('#hideMonth').val("");
                $('#hideMonth').val(data.month);
                $('#hideUsrSid').val("");
                $('#hideUsrSid').val(data.usrSid);
                $('#hideNttSid').val("");
                $('#hideNttSid').val(data.nttSid);

                //目標データ
                var trgList = data.ntgList;

                var trgDataAreaStr = "";

                //目標タイトルTD
                var trgFirstTdstr = "";
                trgFirstTdstr = "<td class=\"table_bg_A5B4E1\" rowspan=\""
                              + trgList.length
                              + "\" width=\"10%\"><span class=\"text_bb1\">目標</span></td>";

                //目標データTD
                for (var r in trgList) {

                    trgDataAreaStr += "<tr>"
                                   +   trgFirstTdstr
                                   +   "<td class=\"table_bg_A5B4E1\" width=\"10%\"><span class=\"text_bb1\">"
                                   +     trgList[r].npgTargetName
                                   +   "</span></td>"
                                   +   "<td class=\"td_wt td_target\" align=\"left\" width=\"80%\" id=\""
                                   +   data.year + "_" + data.month + "_" + data.usrSid + "_" + trgList[r].ntgSid
                                   +   "\">"
                                   +     "<span class=\"text_base\">"
                                   +       "<input name=\"\" style=\"text-align:right;\" maxlength=\"15\" size=\"15\" value=\""
                                   +       trgList[r].npgRecord
                                   +       "\" id=\"val_"
                                   +       data.year + "_" + data.month + "_" + data.usrSid + "_" + trgList[r].ntgSid
                                   +       "\" class=\"text_base\" type=\"text\">&nbsp;"
                                   +       "/"
                                   +       "<span id=\"valTrg_"
                                   +       data.year + "_" + data.month + "_" + data.usrSid + "_" + trgList[r].ntgSid
                                   +       "\">"
                                   +         trgList[r].npgTarget
                                   +       "</span></span>&nbsp;"
                                   +     trgList[r].npgTargetUnit
                                   +     "&nbsp;&nbsp;"
                                   +     "<input class=\"target_settei_btn resetTrgBtn\" id=\"resetTrg_"
                                   +     data.year + "_" + data.month + "_" + data.usrSid + "_" + trgList[r].ntgSid
                                   +     "\" value=\"リセット\" type=\"button\" />"
                                   +   "</td>"
                                   + "</tr>";

                    trgFirstTdstr = "";

                }


                //目標データ出力
                var trgAreaStr = "";
                trgAreaStr     += "<div id=\"trgDataSetArea\">"
                               +  "<table class=\"tl0\" border=\"0\" cellpadding=\"5\" width=\"100%\">"
                               +  "<tbody>"
                               +  trgDataAreaStr
                               +  "</tbody>"
                               +  "</table>"
                               +  "</div>";
                $('#trgDataSetArea').remove();
                $('#trgSetArea').append(trgAreaStr);
            }
    });

}

//目標実績再設定(新規登録時)
function resetTargetRec(year, month, usrSid, ntgSid){

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp040.do', {
                   "cmd":"getTrgRecData",
                   "CMD":"getTrgRecData",
                   "year":year,
                   "month":month,
                   "usrSid":usrSid,
                   "ntgSid":ntgSid},
        function(data) {
            if (data != null || data != "") {
                //目標実績リセット
                $('#val_' + year + '_' + month + '_' + usrSid + '_' + ntgSid).val("");
                $('#val_' + year + '_' + month + '_' + usrSid + '_' + ntgSid).val(data.npgRecord);
            }
    });
}

//目標実績再設定(編集時)
function resetTargetRec2(year, month, usrSid, ntgSid){

    $.ajaxSetup({async:false});
    $.post('../nippou/ntp040.do', {
                   "cmd":"getTrgRecData",
                   "CMD":"getTrgRecData",
                   "year":year,
                   "month":month,
                   "usrSid":usrSid,
                   "ntgSid":ntgSid},
        function(data) {
            if (data != null || data != "") {
                //目標リセット
                $('#recordAreaText_' + year + '_' + month + '_' + usrSid + '_' + ntgSid).text("");
                $('#recordAreaText_' + year + '_' + month + '_' + usrSid + '_' + ntgSid).text(data.npgRecord);
                //目標実績リセット
                $('#trgRecord_' + year + '_' + month + '_' + usrSid + '_' + ntgSid).val("");
                $('#trgRecord_' + year + '_' + month + '_' + usrSid + '_' + ntgSid).val(data.npgRecord);

                if (data.npgTargetKbn == 0) {
                    $('#recordAreaText_' + year + '_' + month + '_' + usrSid + '_' + ntgSid).removeClass("record_comp");
                } else {
                    $('#recordAreaText_' + year + '_' + month + '_' + usrSid + '_' + ntgSid).addClass("record_comp");
                }

            }
    });
}

//アドレス履歴
function getAdrHistoryList(rowNumber, pageNum) {

    //ユーザSID
    var selUsrSid  = document.forms['ntp040Form'].ntp010SelectUsrSid.value;

    //アドレス履歴一覧取得
    $.ajaxSetup({async:false});
    $.post('../nippou/ntp040.do', {"cmd":"getAdrHistoryList",
                                   "CMD":"getAdrHistoryList",
                                   "adrHistoryUsrSid":selUsrSid,
                                   "adrPageNum":pageNum},
      function(data) {
        if (data != null || data != "") {

            $('#adrHistoryArea').children().remove();

            if (data.pageNum != null && data.adrList.length > 0) {

                var adrInfstr = "";
                var pageNum = data.pageNum;
                var maxpagesize = data.maxpagesize;

                //ページング
                if (parseInt(maxpagesize) > 1) {
                    adrInfstr += "<div style=\"width:100%;text-align:right;\">"
                              +  "<img alt=\"前頁\" src=\"../common/images/arrow2_l.gif\" id=\"" + rowNumber + ":" + pageNum + "\" class=\"prevPageBtn cursor_pointer\" border=\"0\" height=\"20\" width=\"20\">"
                              +  "<select name=\"usrInfChange\" id=\"" + rowNumber + "\" class=\"selchange text_i cursor_pointer\">";

                    for (p=1; p <= parseInt(maxpagesize); p++) {
                        if (pageNum == p) {
                            adrInfstr +=  "<option value=\"" + p + "\" selected=\"selected\">" + p + " / " + maxpagesize + "</option>";
                        } else {
                            adrInfstr +=  "<option value=\"" + p + "\">" + p + " / " + maxpagesize + "</option>";
                        }
                    }

                    adrInfstr +=  "</select>"
                              +   "<img src=\"../common/images/arrow2_r.gif\" name=\"btn_next\" alt=\"次頁\" id=\"" + rowNumber + ":" + pageNum + "\" class=\"nextPageBtn cursor_pointer\" border=\"0\" height=\"20\" width=\"20\">"
                              +   "</div>";
                }


                //ユーザ一覧
                adrInfstr += "<table class=\"tl0\" style=\"border-collapse:collapse;\" width=\"100%\">";

                for (i=0; i<data.adrList.length; i++) {

                    adrInfstr += "<tr height=\"40px\" class=\"cursor_pointer\" >"
                              +    "<td class=\"left_space adr_inf_area user_select_area\" align=\"left\" nowrap=\"nowrap\" width=\"100%\"  onClick=\"setParentAdrPop("
                              +        i + ",'" + rowNumber + "');\">"
                              +      "<input id=\"popCompanySid_"  + i + "\"  value=\"" + data.adrList[i].companySid + "\" type=\"hidden\" />"
                              +      "<input id=\"popCompanyCode_" + i + "\"  value=\"" + data.adrList[i].companyCode + "\" type=\"hidden\" />"
                              +      "<input id=\"popCompanyName_" + i + "\"  value=\"" + data.adrList[i].companyName + "\" type=\"hidden\" />"
                              +      "<input id=\"popBaseSid_"     + i + "\"  value=\"" + data.adrList[i].companyBaseSid + "\" type=\"hidden\" />"
                              +      "<input id=\"popBaseName_"    + i + "\"  value=\"" + data.adrList[i].companyBaseName + "\" type=\"hidden\" />"
                              +      "<span class=\"text_link2\">"
                              +        data.adrList[i].companyName + "&nbsp;&nbsp;" + data.adrList[i].companyBaseName
                              +      "</span>"
                              +    "</td>"
                              +  "</tr>";
                }

                adrInfstr += "</table>";


                //ページング
                if (parseInt(maxpagesize) > 1) {
                    adrInfstr += "<div style=\"width:100%;text-align:right;\">"
                              +  "<img alt=\"前頁\" src=\"../common/images/arrow2_l.gif\" id=\"" + rowNumber + ":" + pageNum + "\" class=\"prevPageBtn img_bottom cursor_pointer\" border=\"0\" height=\"20\" width=\"20\">"
                              +  "<select name=\"usrInfChange\" id=\"" + rowNumber + "\" class=\"selchange text_i cursor_pointer\">";

                    for (p=1; p <= parseInt(maxpagesize); p++) {
                        if (pageNum == p) {
                            adrInfstr +=  "<option value=\"" + p + "\" selected=\"selected\">" + p + " / " + maxpagesize + "</option>";
                        } else {
                            adrInfstr +=  "<option value=\"" + p + "\">" + p + " / " + maxpagesize + "</option>";
                        }
                    }

                    adrInfstr +=  "</select>"
                              +  "<img src=\"../common/images/arrow2_r.gif\" name=\"btn_next\" alt=\"次頁\" id=\"" + rowNumber + ":" + pageNum + "\" class=\"nextPageBtn img_bottom cursor_pointer\" border=\"0\" height=\"20\" width=\"20\">"
                              +  "</div>";
                }

                $('#adrHistoryArea').append(adrInfstr);

                /* ユーザリンク hover */
                $('.user_select_area').hover(
                    function () {
                        $(this).removeClass("user_select_area").addClass("user_select_area_hover");
                      },
                      function () {
                          $(this).removeClass("user_select_area_hover").addClass("user_select_area");
                      }
                );

            }  else {

                $('#adrHistoryArea').append("<span style=\"padding-top:220px;height:100%;width:100%;font-weight:bold;text-align:center;\">該当する履歴はありません。</span>");
            }
        }
    });
}

//アドレス履歴から会社選択
function setParentAdrPop(selectSid, rowNumber) {

    var parentId           = "ntp040";
    var companySid         = $('input#popCompanySid_'  + selectSid).val();
    var companyCode        = replaceHtmlTag($('input#popCompanyCode_' + selectSid).val());
    var companyName        = replaceHtmlTag($('input#popCompanyName_' + selectSid).val());
    var companyBaseSid     = $('input#popBaseSid_'     + selectSid).val();
    var companyBaseName    = replaceHtmlTag($('input#popBaseName_'    + selectSid).val());

    if (rowNumber != "") {
        rowNumber = "_" + rowNumber;
    }

    if (companySid != null && companySid != "" && companySid != 0 && companySid != -1
            && $('#' + parentId + 'CompanyIdArea'     + rowNumber).html() != null
            && $('#' + parentId + 'CompNameArea'      + rowNumber).html() != null
            && $('#' + parentId + 'CompanyBaseIdArea' + rowNumber).html() != null
            && $('#' + parentId + 'AddressIdArea'     + rowNumber).html() != null
            && $('#' + parentId + 'AddressNameArea'   + rowNumber).html() != null) {

        $('#' + parentId + 'CompanyIdArea'     + rowNumber).html("");
        $('#' + parentId + 'CompNameArea'      + rowNumber).html("");
        $('#' + parentId + 'CompanyBaseIdArea' + rowNumber).html("");
        $('#' + parentId + 'AddressIdArea'     + rowNumber).html("");
        $('#' + parentId + 'AddressNameArea'   + rowNumber).html("");

        addParentParamAdrPop(parentId + 'CompanyIdArea' + rowNumber, parentId + 'CompanySid' + rowNumber, companySid);
        addParentParamAdrPop(parentId + 'CompanyBaseIdArea' + rowNumber, parentId + 'CompanyBaseSid' + rowNumber, companyBaseSid);

        $('#' + parentId + 'CompanyCodeArea' + rowNumber).html("<span class=\"text_anken_code\">企業コード："
                + "<span class=\"comp_code_name" + rowNumber + "\">"
                + companyCode
                + "</span>"
                + "</span>");

        $('#' + parentId + 'CompNameArea' + rowNumber).html("<div class=\"text_company\">"
                     + "<a id=\"" + companySid + "\" class=\"sc_link comp_click\">"
                     + "<span class=\"comp_name" + rowNumber + "\">"
                     + companyName + " " + companyBaseName
                     + "</span>"
                     + "</a></div>"
                     + "<a href=\"javascript:void(0);\" onclick=\"delCompany('" + parentId + "','" + rowNumber + "');\">"
                     + "<img src=\"../common/images/delete.gif\" class=\"img_bottom\" alt=\"\" width=\"15\"></a>");
    }

    //タイトル設定
    if ($("input[name=" + parentId + "Title" + rowNumber + "]").val() != null) {
        var titlestr = $("input[name=" + parentId + "Title" + rowNumber + "]").val();
        if (titlestr == '') {
            $("input[name=" + parentId + "Title" + rowNumber + "]").val(companyName);
        }
    }

    $('#adrHistoryPop').dialog('close');

    return false;
}


























//案件履歴
function getAnkenHistoryList(rowNumber, pageNum) {

    //ユーザSID
    var selUsrSid  = document.forms['ntp040Form'].ntp010SelectUsrSid.value;

    //案件履歴一覧取得
    $.ajaxSetup({async:false});
    $.post('../nippou/ntp040.do', {"cmd":"getAnkenHistoryList",
                                   "CMD":"getAnkenHistoryList",
                                   "ankenHistoryUsrSid":selUsrSid,
                                   "ankenPageNum":pageNum},
      function(data) {
        if (data != null || data != "") {

            $('#ankenHistoryArea').children().remove();

            if (data.pageNum != null && data.ankenList.length > 0) {

                var ankenInfstr = "";
                var pageNum = data.pageNum;
                var maxpagesize = data.maxpagesize;


                //ページング
                if (parseInt(maxpagesize) > 1) {
                    ankenInfstr += "<div style=\"width:100%;text-align:right;\">"
                              +  "<img alt=\"前頁\" src=\"../common/images/arrow2_l.gif\" id=\"" + rowNumber + ":" + pageNum + "\" class=\"prevAnkenPageBtn cursor_pointer\" border=\"0\" height=\"20\" width=\"20\">"
                              +  "<select name=\"usrInfChange\" id=\"" + rowNumber + "\" class=\"selchangeAnken text_i cursor_pointer\">";

                    for (p=1; p <= parseInt(maxpagesize); p++) {
                        if (pageNum == p) {
                            ankenInfstr +=  "<option value=\"" + p + "\" selected=\"selected\">" + p + " / " + maxpagesize + "</option>";
                        } else {
                            ankenInfstr +=  "<option value=\"" + p + "\">" + p + " / " + maxpagesize + "</option>";
                        }
                    }

                    ankenInfstr +=  "</select>"
                              +   "<img src=\"../common/images/arrow2_r.gif\" name=\"btn_next\" alt=\"次頁\" id=\"" + rowNumber + ":" + pageNum + "\" class=\"nextAnkenPageBtn cursor_pointer\" border=\"0\" height=\"20\" width=\"20\">"
                              +   "</div>";
                }


                //案件一覧
                ankenInfstr += "<table class=\"tl0\" style=\"border-collapse:collapse;\" width=\"100%\">";

                for (i=0; i<data.ankenList.length; i++) {

                    ankenInfstr += "<tr height=\"40px\" class=\"cursor_pointer\" >"
                              +    "<td class=\"left_space anken_inf_area user_select_area\" align=\"left\" nowrap=\"nowrap\" width=\"100%\"  onClick=\"setParentAnkenPop("
                              +        i + ",'" + rowNumber + "');\">"
                              +      "<input id=\"popAnkenSid_"  + i + "\"  value=\"" + data.ankenList[i].nanSid + "\" type=\"hidden\" />"
                              +      "<input id=\"popAnkenCode_" + i + "\"  value=\"" + data.ankenList[i].nanCode + "\" type=\"hidden\" />"
                              +      "<input id=\"popAnkenName_" + i + "\"  value=\"" + data.ankenList[i].nanName + "\" type=\"hidden\" />"
                              +      "<span class=\"text_link2\">"
                              +        data.ankenList[i].nanName
                              +      "</span>"
                              +    "</td>"
                              +  "</tr>";
                }

                ankenInfstr += "</table>";


                //ページング
                if (parseInt(maxpagesize) > 1) {
                    ankenInfstr += "<div style=\"width:100%;text-align:right;\">"
                              +  "<img alt=\"前頁\" src=\"../common/images/arrow2_l.gif\" id=\"" + rowNumber + ":" + pageNum + "\" class=\"prevAnkenPageBtn img_bottom cursor_pointer\" border=\"0\" height=\"20\" width=\"20\">"
                              +  "<select name=\"usrInfChange\" id=\"" + rowNumber + "\" class=\"selchangeAnken text_i cursor_pointer\">";

                    for (p=1; p <= parseInt(maxpagesize); p++) {
                        if (pageNum == p) {
                            ankenInfstr +=  "<option value=\"" + p + "\" selected=\"selected\">" + p + " / " + maxpagesize + "</option>";
                        } else {
                            ankenInfstr +=  "<option value=\"" + p + "\">" + p + " / " + maxpagesize + "</option>";
                        }
                    }

                    ankenInfstr +=  "</select>"
                              +  "<img src=\"../common/images/arrow2_r.gif\" name=\"btn_next\" alt=\"次頁\" id=\"" + rowNumber + ":" + pageNum + "\" class=\"nextAnkenPageBtn img_bottom cursor_pointer\" border=\"0\" height=\"20\" width=\"20\">"
                              +  "</div>";
                }

                $('#ankenHistoryArea').append(ankenInfstr);

                /* 案件リンク hover */
                $('.user_select_area').hover(
                    function () {
                        $(this).removeClass("user_select_area").addClass("user_select_area_hover");
                      },
                      function () {
                          $(this).removeClass("user_select_area_hover").addClass("user_select_area");
                      }
                );

            }  else {

                $('#ankenHistoryArea').append("<span style=\"padding-top:220px;height:100%;width:100%;font-weight:bold;text-align:center;\">該当する履歴はありません。</span>");
            }
        }
    });
}

//案件履歴から案件選択
function setParentAnkenPop(selectSid, rowNumber) {

    var parentId           = "ntp040";
    var ankenSid         = $('input#popAnkenSid_'  + selectSid).val();
    var ankenCode        = replaceHtmlTag($('input#popAnkenCode_' + selectSid).val());
    var ankenName        = replaceHtmlTag($('input#popAnkenName_' + selectSid).val());

    if (rowNumber != "") {
        rowNumber = "_" + rowNumber;
    }

    if (ankenSid != null && ankenSid != "" && ankenSid != 0 && ankenSid != -1
            && $('#' + parentId + 'AnkenIdArea'     + rowNumber).html() != null
            && $('#' + parentId + 'AnkenNameArea'   + rowNumber).html() != null) {

        $('#' + parentId + 'AnkenIdArea'     + rowNumber).html("");
        $('#' + parentId + 'AnkenNameArea'      + rowNumber).html("");

        addParentParamAnkenPop(parentId + 'AnkenIdArea' + rowNumber, parentId + 'AnkenSid' + rowNumber, ankenSid);

        $('#' + parentId + 'AnkenCodeArea' + rowNumber).html("<span class=\"text_anken_code\">案件コード："
                + "<span class=\"comp_code_name" + rowNumber + "\">"
                + ankenCode
                + "</span>"
                + "</span>");

        $('#' + parentId + 'AnkenNameArea' + rowNumber).html("<span class=\"text_anken\">"
                     + "<a id=\"" + ankenSid + "\" class=\"sc_link anken_click\">"
                     + "<span class=\"comp_name" + rowNumber + "\">"
                     + ankenName
                     + "</span>"
                     + "</a></span>"
                     + "<a href=\"javascript:void(0);\" onclick=\"delAnken('" + parentId + "','" + rowNumber + "');\">"
                     + "<img src=\"../common/images/delete.gif\" class=\"img_bottom\" alt=\"\" width=\"15\"></a>");
    }

    //タイトル設定
    if ($("input[name=" + parentId + "Title" + rowNumber + "]").val() != null) {
        var titlestr = $("input[name=" + parentId + "Title" + rowNumber + "]").val();
        if (titlestr == '') {
            $("input[name=" + parentId + "Title" + rowNumber + "]").val(ankenName);
        }
    }

    $('#ankenHistoryPop').dialog('close');

    return false;
}
























function replaceHtmlTag(s) {
    return s.replace(/&/g,"&amp;").replace(/"/g,"&quot;").replace(/'/g,"&#039;").replace(/</g,"&lt;").replace(/>/g,"&gt;") ;
}

function addParentParamAdrPop(parentAreaId, paramName, value) {
    var parentArea = document.getElementById(parentAreaId);

    var paramHtml = parentArea.innerHTML;
    paramHtml += '<input type="hidden" name="' + paramName + '" value="' + value + '">';
    parentArea.innerHTML = paramHtml;
}

function addParentParamAnkenPop(parentAreaId, paramName, value) {
    var parentArea = document.getElementById(parentAreaId);

    var paramHtml = parentArea.innerHTML;
    paramHtml += '<input type="hidden" name="' + paramName + '" value="' + value + '">';
    parentArea.innerHTML = paramHtml;
}


function toggleActionAreaShow(actionAreaId) {
    $('#' + actionAreaId).show();
    return false;
}

function toggleActionAreaHide(actionAreaId) {
    $('#' + actionAreaId).hide();
    return false;
}

var checkBoxClickFlg = 0;
function clickMulti() {
    checkBoxClickFlg = 1;
    return false;
}

function clickSchName(typeNo, itmSid) {

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

function resetScheduleObj() {
    //チェックをすべてはずす
    var scheduleList   = $("input:checkbox[name='popSchedule']");
    scheduleList.attr('checked', false);
    return false;
}

function resetProjectObj() {
    //チェックをすべてはずす
    var projectList   = $("input:checkbox[name='popProject']");
    projectList.attr('checked', false);
    return false;
}
