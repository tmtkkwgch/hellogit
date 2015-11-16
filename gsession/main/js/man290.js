function buttonPush(cmd){
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function buttonPush2(cmd, mod){
    document.forms[0].CMD.value=cmd;
    document.forms[0].cmd.value=mod;
    document.forms[0].submit();
    return false;
}
function more(){
  if($('#moreSettings')[0].style.display == 'none'){
    $('#moreSettings')[0].style.display="";
    //通常設定のみ表示
    $('#more')[0].innerHTML="＜＜" + msglist.usualDisp;
    document.forms[0].man290elementKbn.value = 1;
    setDisabled();
  } else {
    $('#moreSettings')[0].style.display="none";
    //詳細設定を表示する
    $('#more')[0].innerHTML="＞＞" + msglist.detailDisp;
    document.forms[0].man290ExtKbn[0].checked=true;
    document.forms[0].man290elementKbn.value = 0;
    setDisabled();
  }
}

function moreRe(){
  if(($('#moreSettings')[0].style.display == 'none') && document.forms[0].man290elementKbn.value == 1){
    $('#moreSettings')[0].style.display="";
    //通常設定のみ表示
    $('#more')[0].innerHTML="＜＜" + msglist.usualDisp;;
  } else {
    $('#moreSettings')[0].style.display="none";
    //詳細設定を表示する
    $('#more')[0].innerHTML="＞＞" + msglist.detailDisp;
  }
}




function selectGroup(cmd){
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function setDisabled(value) {

    if (document.forms[0].man290ExtKbn[1].checked == true) {
        document.forms[0].man290Dweek[0].disabled=false;
        document.forms[0].man290Dweek[1].disabled=false;
        document.forms[0].man290Dweek[2].disabled=false;
        document.forms[0].man290Dweek[3].disabled=false;
        document.forms[0].man290Dweek[4].disabled=false;
        document.forms[0].man290Dweek[5].disabled=false;
        document.forms[0].man290Dweek[6].disabled=false;
        document.forms[0].man290Week.disabled=true;
        document.forms[0].man290Day.disabled=true;
        document.forms[0].man290Week.value=0;
        document.forms[0].man290Day.value=0;
        document.forms[0].man290HolidayKbn2.disabled=false;
        document.forms[0].man290HolidayKbn3.disabled=false;

    } else if (document.forms[0].man290ExtKbn[2].checked == true) {
        document.forms[0].man290Dweek[0].disabled=false;
        document.forms[0].man290Dweek[1].disabled=false;
        document.forms[0].man290Dweek[2].disabled=false;
        document.forms[0].man290Dweek[3].disabled=false;
        document.forms[0].man290Dweek[4].disabled=false;
        document.forms[0].man290Dweek[5].disabled=false;
        document.forms[0].man290Dweek[6].disabled=false;
        document.forms[0].man290Week.disabled=false;
        document.forms[0].man290Day.disabled=false;
        document.forms[0].man290HolidayKbn2.disabled=false;
        document.forms[0].man290HolidayKbn3.disabled=false;

        if (document.forms[0].man290Week.value==0) {
            document.forms[0].man290Dweek[0].disabled=true;
            document.forms[0].man290Dweek[1].disabled=true;
            document.forms[0].man290Dweek[2].disabled=true;
            document.forms[0].man290Dweek[3].disabled=true;
            document.forms[0].man290Dweek[4].disabled=true;
            document.forms[0].man290Dweek[5].disabled=true;
            document.forms[0].man290Dweek[6].disabled=true;
            document.forms[0].man290Dweek[0].checked=false;
            document.forms[0].man290Dweek[1].checked=false;
            document.forms[0].man290Dweek[2].checked=false;
            document.forms[0].man290Dweek[3].checked=false;
            document.forms[0].man290Dweek[4].checked=false;
            document.forms[0].man290Dweek[5].checked=false;
            document.forms[0].man290Dweek[6].checked=false;
        } else {
            document.forms[0].man290Dweek[0].disabled=false;
            document.forms[0].man290Dweek[1].disabled=false;
            document.forms[0].man290Dweek[2].disabled=false;
            document.forms[0].man290Dweek[3].disabled=false;
            document.forms[0].man290Dweek[4].disabled=false;
            document.forms[0].man290Dweek[5].disabled=false;
            document.forms[0].man290Dweek[6].disabled=false;
        }
    } else {
        document.forms[0].man290Dweek[0].disabled=true;
        document.forms[0].man290Dweek[1].disabled=true;
        document.forms[0].man290Dweek[2].disabled=true;
        document.forms[0].man290Dweek[3].disabled=true;
        document.forms[0].man290Dweek[4].disabled=true;
        document.forms[0].man290Dweek[5].disabled=true;
        document.forms[0].man290Dweek[6].disabled=true;
        document.forms[0].man290Week.disabled=true;
        document.forms[0].man290Day.disabled=true;
        document.forms[0].man290Dweek[0].checked=false;
        document.forms[0].man290Dweek[1].checked=false;
        document.forms[0].man290Dweek[2].checked=false;
        document.forms[0].man290Dweek[3].checked=false;
        document.forms[0].man290Dweek[4].checked=false;
        document.forms[0].man290Dweek[5].checked=false;
        document.forms[0].man290Dweek[6].checked=false;
        document.forms[0].man290Week.value=0;
        document.forms[0].man290Day.value=0;
        document.forms[0].man290HolidayKbn2.disabled=true;
        document.forms[0].man290HolidayKbn3.disabled=true;
        if (document.forms[0].man290HolidayKbn2.checked == true || document.forms[0].man290HolidayKbn3.checked == true) {
            document.forms[0].man290HolidayKbn0.checked=true;
        }
    }

}

function changeWeekCombo(){
    if (document.forms[0].man290ExtKbn[2].checked == true) {
        if (document.forms[0].man290Week.value==0) {
            document.forms[0].man290Dweek[0].disabled=true;
            document.forms[0].man290Dweek[1].disabled=true;
            document.forms[0].man290Dweek[2].disabled=true;
            document.forms[0].man290Dweek[3].disabled=true;
            document.forms[0].man290Dweek[4].disabled=true;
            document.forms[0].man290Dweek[5].disabled=true;
            document.forms[0].man290Dweek[6].disabled=true;
            document.forms[0].man290Dweek[0].checked=false;
            document.forms[0].man290Dweek[1].checked=false;
            document.forms[0].man290Dweek[2].checked=false;
            document.forms[0].man290Dweek[3].checked=false;
            document.forms[0].man290Dweek[4].checked=false;
            document.forms[0].man290Dweek[5].checked=false;
            document.forms[0].man290Dweek[6].checked=false;
        } else {
            document.forms[0].man290Dweek[0].disabled=false;
            document.forms[0].man290Dweek[1].disabled=false;
            document.forms[0].man290Dweek[2].disabled=false;
            document.forms[0].man290Dweek[3].disabled=false;
            document.forms[0].man290Dweek[4].disabled=false;
            document.forms[0].man290Dweek[5].disabled=false;
            document.forms[0].man290Dweek[6].disabled=false;
        }
    }
}

function moveDay(elmYear, elmMonth, elmDay, kbn) {

    systemDate = new Date();

    if (kbn == 2) {
        $(elmYear)[0].value = convYear(systemDate.getYear());
        $(elmMonth)[0].value = systemDate.getMonth() + 1;
        $(elmDay)[0].value = systemDate.getDate();
        return;
    }

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
                $(elmYear)[0].value = '';
            } else {
                $(elmYear)[0].value = newYear;
            }

            $(elmMonth)[0].value = newDate.getMonth() + 1;
            $(elmDay)[0].value = newDate.getDate();

        } else {

            if (elmYear.value == '' && elmMonth.value == '' && elmDay.value == '') {
                $(elmYear)[0].value = convYear(systemDate.getYear());
                $(elmMonth)[0].value = systemDate.getMonth() + 1;
                $(elmDay)[0].value = systemDate.getDate();
            }
        }
    }
}