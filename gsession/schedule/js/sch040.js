function buttonPush(cmd, mod){
    document.forms[0].CMD.value=cmd;
    document.forms[0].cmd.value=mod;
    document.forms[0].submit();
    return false;
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
function setDisabled(value) {

    elm = document.getElementsByName("sch040BatchRef");

    if (elm.length > 2) {
      if (document.forms[0].sch040BatchRef[1].checked == true) {
          document.forms[0].adduserBtn.disabled=true;
          document.forms[0].deluserBtn.disabled=true;
          document.forms[0].sch040GroupSid.disabled=true;
          document.forms[0].users_r.disabled=true;
          document.forms[0].users_l.disabled=true;
      } else {
          document.forms[0].adduserBtn.disabled=false;
          document.forms[0].deluserBtn.disabled=false;
          document.forms[0].sch040GroupSid.disabled=false;
          document.forms[0].users_r.disabled=false;
          document.forms[0].users_l.disabled=false;
      }
    }


//    if (document.forms[0].sch040ResBatchRef[1].checked == true) {
    var resBatchRef = document.forms[0].sch040ResBatchRef;
if (resBatchRef != null) {
    if (resBatchRef[1].checked == true) {

        document.forms[0].addresBtn.disabled=true;
        document.forms[0].addresBtn.style.color = '#e0e0e0';
        document.forms[0].delresBtn.disabled=true;
        document.forms[0].delresBtn.style.color = '#e0e0e0';
        document.forms[0].sch040ReserveGroupSid.disabled=true;
        document.forms[0].reserve_r.disabled=true;
        document.forms[0].reserve_r.style.backgroundColor = '#e0e0e0';
        document.forms[0].reserve_l.disabled=true;
        document.forms[0].reserve_l.style.backgroundColor = '#e0e0e0';

    } else {
        document.forms[0].addresBtn.disabled=false;
        document.forms[0].addresBtn.style.color = '#000066';
        document.forms[0].delresBtn.disabled=false;
        document.forms[0].delresBtn.style.color = '#000066';
        document.forms[0].sch040ReserveGroupSid.disabled=false;
        document.forms[0].reserve_r.disabled=false;
        document.forms[0].reserve_r.style.backgroundColor = '#ffffff';
        document.forms[0].reserve_l.disabled=false;
        document.forms[0].reserve_l.style.backgroundColor = '#ffffff';
    }
}

}

function setToDay() {
    if (document.forms[0].sch040ScrollFlg.value=='1') {
        window.location.hash='add_user';
    }
    var frYear = document.forms[0].sch040FrYear.value;
    var frMonth = document.forms[0].sch040FrMonth.value;
    var frDay = document.forms[0].sch040FrDay.value;
    var frHour = document.forms[0].sch040FrHour.value;
    var frMinute = document.forms[0].sch040FrMin.value;
    var toYear = document.forms[0].sch040ToYear.value;
    var toMonth = document.forms[0].sch040ToMonth.value;
    var toDay = document.forms[0].sch040ToDay.value;
    var toHour = document.forms[0].sch040ToHour.value;
    var toMinute = document.forms[0].sch040ToMin.value;
    var toMinute = document.forms[0].sch040ToMin.value;
    var daySameFlg = false;

    if (parseInt(frYear) > parseInt(toYear)) {
        toYear = frYear;
        toMonth = frMonth;
        toDay = frDay;
        document.forms[0].sch040ToYear.value = frYear;
        document.forms[0].sch040ToMonth.value = frMonth;
        document.forms[0].sch040ToDay.value = frDay;
        document.getElementById("betWeenDays").innerHTML = msglist.period + '1' + msglist.days;
    }

    if (parseInt(frYear) == parseInt(toYear)) {
        if (parseInt(frMonth) > parseInt(toMonth)) {
            toMonth = frMonth;
            toDay = frDay;
            document.forms[0].sch040ToMonth.value = frMonth;
            document.forms[0].sch040ToDay.value = frDay;
            document.getElementById("betWeenDays").innerHTML = msglist.period + '1' + msglist.days;
        }
    }

    if (parseInt(frYear) == parseInt(toYear)) {
        if (parseInt(frMonth) == parseInt(toMonth)) {
            if (parseInt(frDay) > parseInt(toDay)) {
                toDay = frDay;
                document.forms[0].sch040ToDay.value = frDay;
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
                document.forms[0].sch040ToHour.value = frHour;

            }
            if (parseInt(frHour) == parseInt(toHour)) {
                if (parseInt(frMinute) > parseInt(toMinute)) {
                    document.forms[0].sch040ToMin.value = frMinute;
                }
            }
        }
    }
}

function setBetweenFromToDayCount() {
    var frYear = document.forms[0].sch040FrYear.value;
    var frMonth = document.forms[0].sch040FrMonth.value;
    var frDay = document.forms[0].sch040FrDay.value;
    var toYear = document.forms[0].sch040ToYear.value;
    var toMonth = document.forms[0].sch040ToMonth.value;
    var toDay = document.forms[0].sch040ToDay.value;
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

    //「今日」ボタン押下
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

function changeTimeStatus() {

       //時間指定無し
       if (document.forms[0].sch040TimeKbn.checked) {

            //施設予約チェック
            oElements = document.getElementsByName("svReserveUsers");
            if (oElements.length > 0) {
              if (window.confirm(msglist.cantRsv + "\r\n" + msglist.delRsv)) {

                    //施設予約を解除にする
                    var defValue = document.forms[0].reserve_r.value;
                    var defGroupAry = document.forms[0].reserve_r.options;
                    var defLength = defGroupAry.length;
                    for (i = defLength - 1; i >= 0; i--) {
                        if (defGroupAry[i].value != -1) {
                            defGroupAry[i].selected = true;
                        }
                    }
                    moveUser('040_res_leftarrow');

                } else {
                    document.forms[0].sch040TimeKbn.checked = false;
                }

            } else {
              //施設予約を無効
                setReserveInactive();
            }

       } else {
            //施設予約を有効
            setReserveActive();
       }
       setTimeStatus();
}
function setTimeStatus() {

        //時間指定無し
        if (document.forms[0].sch040TimeKbn.checked) {

            //開始時刻
            document.forms[0].sch040FrHour.disabled=true;
            document.forms[0].sch040FrMin.disabled=true;

            //終了時刻
            document.forms[0].sch040ToHour.disabled=true;
            document.forms[0].sch040ToMin.disabled=true;

            //施設予約を無効
            setReserveInactive();
       } else {
            //開始時刻
            document.forms[0].sch040FrHour.disabled=false;
            document.forms[0].sch040FrMin.disabled=false;

            //終了時刻
            document.forms[0].sch040ToHour.disabled=false;
            document.forms[0].sch040ToMin.disabled=false;

            //施設予約を有効
            setReserveActive();
       }
}

function setReserveActive() {
    var cmd = document.forms[0].cmd.value;
    var timeKbn = document.forms[0].sch040TimeKbn.value;

    if (document.forms[0].reservePluginKbn.value == 0) {
        if (cmd != 'add') {
            document.forms[0].sch040ResBatchRef[0].disabled=false;
            document.forms[0].sch040ResBatchRef[1].disabled=false;
        }
        //施設予約を有効
        document.forms[0].addresBtn.disabled=false;
        document.forms[0].addresBtn.style.color = '#000066';
        document.forms[0].delresBtn.disabled=false;
        document.forms[0].delresBtn.style.color = '#000066';
        document.forms[0].sch040ReserveGroupSid.disabled=false;
        document.forms[0].reserve_r.disabled=false;
        document.forms[0].reserve_r.style.backgroundColor = '#ffffff';
        document.forms[0].reserve_l.disabled=false;
        document.forms[0].reserve_l.style.backgroundColor = '#ffffff';
    }
}

function setReserveInactive() {
    var cmd = document.forms[0].cmd.value;
    var timeKbn = document.forms[0].sch040TimeKbn.value;

    if (document.forms[0].reservePluginKbn.value == 0) {
        if (cmd != 'add') {
            $('#sch040ResBatchRef0').attr("checked",false);
            $('#sch040ResBatchRef1').attr("checked", true);
            document.forms[0].sch040ResBatchRef[0].disabled=true;
            document.forms[0].sch040ResBatchRef[1].disabled=true;
        }
        //施設予約を無効
        document.forms[0].addresBtn.disabled=true;
        document.forms[0].addresBtn.style.color = '#e0e0e0';
        document.forms[0].delresBtn.disabled=true;
        document.forms[0].delresBtn.style.color = '#e0e0e0';
        document.forms[0].sch040ReserveGroupSid.disabled=true;
        document.forms[0].reserve_r.disabled=true;
        document.forms[0].reserve_r.style.backgroundColor = '#e0e0e0';
        document.forms[0].reserve_l.disabled=true;
        document.forms[0].reserve_l.style.backgroundColor = '#e0e0e0';
    }
}

function selectUsersList() {

    var flg = true;
   if (document.forms[0].sch040SelectUsersKbn.checked) {
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
    document.forms['sch040Form'].CMD.value = 'deleteCompany';
    document.forms['sch040Form'].sch040delCompanyId.value = companyId;
    document.forms['sch040Form'].sch040delCompanyBaseId.value = companyBaseId;
    document.forms['sch040Form'].submit();
    return false;
}

function changeEditDsp(attendKbn) {
    if (attendKbn == 1) {
        $('#editRadioArea').hide();
        $('#editOnlyArea').show();
        $('#addedUsrArea').hide();
    } else {
        $('#editRadioArea').show();
        $('#editOnlyArea').hide();
        $('#addedUsrArea').show();
    }
}

$(function () {
    //初期表示
    changeEditDsp($("input:radio[name=sch040AttendKbn]:checked").val());

    //出欠確認 変更時
    $("input:radio[name=sch040AttendKbn]").live('change', function(){
        changeEditDsp($(this).val());
    });

    $("#editbtn").live("click", function(){
        var dspMode = $('input:hidden[name=sch040EditDspMode]').val();
        var attendKbn = $("input:radio[name=sch040AttendKbn]:checked").val()
        if (dspMode == 1 && attendKbn == 1) {
            $('#schEditMail').dialog({
                autoOpen: true,
                bgiframe: true,
                resizable: false,
                width:350,
                height: 180,
                modal: true,
                closeOnEscape: false,
                overlay: {
                    backgroundColor: '#000000',
                    opacity: 0.5
                },
                buttons: {
                    はい: function() {
                        document.forms[0].CMD.value = "040_ok";

                        if ($('#sch040EditMailSendKbn').attr("checked")){
                            document.forms[0].sch040EditMailSendKbn.value = 1;
                        }
                        document.forms[0].submit();

                        $(this).dialog('close');
                    },
                    キャンセル: function() {
                        //キャンセル時チェックボックスを外す
                        $('#sch040EditMailSendKbn').attr("checked",false);
                        $(this).dialog('close');
                    }
                }
            });
        } else {
            document.forms[0].CMD.value = "040_ok";
            document.forms[0].submit();
        }
    });

    $('#all_disp').live('click', function(){
        var winWidth=580;
        var winHeight=600;
        var winx = getCenterX(winWidth);
        var winy = getCenterY(winHeight);
        var schSid = $('input:hidden[name=sch010SchSid]').val();

        var newWinOpt = "width=" + winWidth + ", height=" + winHeight + ", toolbar=no ,scrollbars=yes, resizable=yes, left=" + winx + ", top=" + winy;
        var url = '../schedule/sch220.do?schSid=' + schSid;

        if (!flagSubWindow || (flagSubWindow && subWindow.closed)) {
            subWindow = window.open(url, 'thissite', newWinOpt);
            flagSubWindow = true;

        } else {
            subWindow.location.href=url;
            subWindow.focus();
            return;
        }
    });
    $('textarea').each(function() {
        setTextareaAutoResize($(this).get(0));
    });
})


function getCenterX(winWidth) {
    var x = (screen.width - winWidth) / 2;
    return x;
  }

  function getCenterY(winHeight) {
    var y = (screen.height - winHeight) / 2;
    return y;
  }
