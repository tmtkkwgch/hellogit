function setDisabled(value) {

    if (document.forms[0].rsv111RsrKbn[1].checked == true) {
        document.forms[0].rsv111RsrDweek1.disabled=false;
        document.forms[0].rsv111RsrDweek2.disabled=false;
        document.forms[0].rsv111RsrDweek3.disabled=false;
        document.forms[0].rsv111RsrDweek4.disabled=false;
        document.forms[0].rsv111RsrDweek5.disabled=false;
        document.forms[0].rsv111RsrDweek6.disabled=false;
        document.forms[0].rsv111RsrDweek7.disabled=false;
        document.forms[0].rsv111RsrTranKbn[0].disabled=false;
        document.forms[0].rsv111RsrTranKbn[1].disabled=false;
        document.forms[0].rsv111RsrTranKbn[2].disabled=false;
        document.forms[0].rsv111RsrWeek.disabled=true;
        document.forms[0].rsv111RsrDay.disabled=true;
        document.forms[0].rsv111RsrWeek.value=0;
        document.forms[0].rsv111RsrDay.value=0;
        document.forms[0].rsv111RsrMonthOfYearly.disabled=true;
        document.forms[0].rsv111RsrDayOfYearly.disabled=true;

    } else if (document.forms[0].rsv111RsrKbn[2].checked == true) {
        document.forms[0].rsv111RsrDweek1.disabled=false;
        document.forms[0].rsv111RsrDweek2.disabled=false;
        document.forms[0].rsv111RsrDweek3.disabled=false;
        document.forms[0].rsv111RsrDweek4.disabled=false;
        document.forms[0].rsv111RsrDweek5.disabled=false;
        document.forms[0].rsv111RsrDweek6.disabled=false;
        document.forms[0].rsv111RsrDweek7.disabled=false;
        document.forms[0].rsv111RsrTranKbn[0].disabled=false;
        document.forms[0].rsv111RsrTranKbn[1].disabled=false;
        document.forms[0].rsv111RsrTranKbn[2].disabled=false;
        document.forms[0].rsv111RsrWeek.disabled=false;
        document.forms[0].rsv111RsrDay.disabled=false;
        document.forms[0].rsv111RsrMonthOfYearly.disabled=true;
        document.forms[0].rsv111RsrDayOfYearly.disabled=true;

        if (document.forms[0].rsv111RsrWeek.value==0) {
            document.forms[0].rsv111RsrDweek1.disabled=true;
            document.forms[0].rsv111RsrDweek2.disabled=true;
            document.forms[0].rsv111RsrDweek3.disabled=true;
            document.forms[0].rsv111RsrDweek4.disabled=true;
            document.forms[0].rsv111RsrDweek5.disabled=true;
            document.forms[0].rsv111RsrDweek6.disabled=true;
            document.forms[0].rsv111RsrDweek7.disabled=true;
        } else {
            document.forms[0].rsv111RsrDweek1.disabled=false;
            document.forms[0].rsv111RsrDweek2.disabled=false;
            document.forms[0].rsv111RsrDweek3.disabled=false;
            document.forms[0].rsv111RsrDweek4.disabled=false;
            document.forms[0].rsv111RsrDweek5.disabled=false;
            document.forms[0].rsv111RsrDweek6.disabled=false;
            document.forms[0].rsv111RsrDweek7.disabled=false;
        }
    } else if (document.forms[0].rsv111RsrKbn[3].checked == true) {
        document.forms[0].rsv111RsrDweek1.disabled=true;
        document.forms[0].rsv111RsrDweek2.disabled=true;
        document.forms[0].rsv111RsrDweek3.disabled=true;
        document.forms[0].rsv111RsrDweek4.disabled=true;
        document.forms[0].rsv111RsrDweek5.disabled=true;
        document.forms[0].rsv111RsrDweek6.disabled=true;
        document.forms[0].rsv111RsrDweek7.disabled=true;
        document.forms[0].rsv111RsrWeek.disabled=true;
        document.forms[0].rsv111RsrDay.disabled=true;
        document.forms[0].rsv111RsrTranKbn.disabled=true;
        document.forms[0].rsv111RsrDweek1.checked=false;
        document.forms[0].rsv111RsrDweek2.checked=false;
        document.forms[0].rsv111RsrDweek3.checked=false;
        document.forms[0].rsv111RsrDweek4.checked=false;
        document.forms[0].rsv111RsrDweek5.checked=false;
        document.forms[0].rsv111RsrDweek6.checked=false;
        document.forms[0].rsv111RsrDweek7.checked=false;
        document.forms[0].rsv111RsrWeek.value=0;
        document.forms[0].rsv111RsrDay.value=0;
        document.forms[0].rsv111RsrMonthOfYearly.disabled=false;
        document.forms[0].rsv111RsrDayOfYearly.disabled=false;
        document.forms[0].rsv111RsrTranKbn[0].disabled=false;
        document.forms[0].rsv111RsrTranKbn[1].disabled=false;
        document.forms[0].rsv111RsrTranKbn[2].disabled=false;
    } else {
        document.forms[0].rsv111RsrDweek1.disabled=true;
        document.forms[0].rsv111RsrDweek2.disabled=true;
        document.forms[0].rsv111RsrDweek3.disabled=true;
        document.forms[0].rsv111RsrDweek4.disabled=true;
        document.forms[0].rsv111RsrDweek5.disabled=true;
        document.forms[0].rsv111RsrDweek6.disabled=true;
        document.forms[0].rsv111RsrDweek7.disabled=true;
        document.forms[0].rsv111RsrTranKbn[0].disabled=true;
        document.forms[0].rsv111RsrTranKbn[1].disabled=true;
        document.forms[0].rsv111RsrTranKbn[2].disabled=true;
        document.forms[0].rsv111RsrWeek.disabled=true;
        document.forms[0].rsv111RsrDay.disabled=true;
        document.forms[0].rsv111RsrTranKbn.disabled=true;
        document.forms[0].rsv111RsrDweek1.checked=false;
        document.forms[0].rsv111RsrDweek2.checked=false;
        document.forms[0].rsv111RsrDweek3.checked=false;
        document.forms[0].rsv111RsrDweek4.checked=false;
        document.forms[0].rsv111RsrDweek5.checked=false;
        document.forms[0].rsv111RsrDweek6.checked=false;
        document.forms[0].rsv111RsrDweek7.checked=false;
        document.forms[0].rsv111RsrWeek.value=0;
        document.forms[0].rsv111RsrDay.value=0;
        document.forms[0].rsv111RsrMonthOfYearly.disabled=true;
        document.forms[0].rsv111RsrDayOfYearly.disabled=true;
        document.forms[0].rsv111RsrTranKbn[0].checked=true;
        document.forms[0].rsv111RsrTranKbn[1].checked=false;
        document.forms[0].rsv111RsrTranKbn[2].checked=false;
    }

}

function changeWeekCombo(){
    if (document.forms[0].rsv111RsrKbn[2].checked == true) {
        if (document.forms[0].rsv111RsrWeek.value==0) {
            document.forms[0].rsv111RsrDweek1.disabled=true;
            document.forms[0].rsv111RsrDweek2.disabled=true;
            document.forms[0].rsv111RsrDweek3.disabled=true;
            document.forms[0].rsv111RsrDweek4.disabled=true;
            document.forms[0].rsv111RsrDweek5.disabled=true;
            document.forms[0].rsv111RsrDweek6.disabled=true;
            document.forms[0].rsv111RsrDweek7.disabled=true;
            document.forms[0].rsv111RsrDweek1.checked=false;
            document.forms[0].rsv111RsrDweek2.checked=false;
            document.forms[0].rsv111RsrDweek3.checked=false;
            document.forms[0].rsv111RsrDweek4.checked=false;
            document.forms[0].rsv111RsrDweek5.checked=false;
            document.forms[0].rsv111RsrDweek6.checked=false;
            document.forms[0].rsv111RsrDweek7.checked=false;
        } else {
            document.forms[0].rsv111RsrDweek1.disabled=false;
            document.forms[0].rsv111RsrDweek2.disabled=false;
            document.forms[0].rsv111RsrDweek3.disabled=false;
            document.forms[0].rsv111RsrDweek4.disabled=false;
            document.forms[0].rsv111RsrDweek5.disabled=false;
            document.forms[0].rsv111RsrDweek6.disabled=false;
            document.forms[0].rsv111RsrDweek7.disabled=false;
        }
    }
}

function moveDay(elmYear, elmMonth, elmDay, kbn) {

    systemDate = new Date();

    //「今日」ボタン押下
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

function showOrHide(){
var rsv111HeaderDspFlg = document.forms[0].rsv111HeaderDspFlg.value;
  if (rsv111HeaderDspFlg == '0') {
    showText();
  } else {
    hideText();
  }
}

function showText(){
    $('#longHeader').show();
    $('#shortHeader').hide();
    document.forms[0].rsv111HeaderDspFlg.value='0';
}

function hideText(){
    $('#longHeader').hide();
    $('#shortHeader').show();
    document.forms[0].rsv111HeaderDspFlg.value='1';
}

function selectUsersList() {

    var flg = true;
   if (document.forms[0].rsv111SelectUsersKbn.checked) {
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
