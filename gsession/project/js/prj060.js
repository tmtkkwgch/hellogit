function deleteCmt(cmd, cmtSid){
    $('#CMD')[0].value = cmd;
    document.forms[0].commentSid.value=cmtSid;
    document.forms[0].submit();
    return false;
}

function deleteHistory(cmd, hisSid){
    $('#CMD')[0].value = cmd;
    document.forms[0].historySid.value=hisSid;
    document.forms[0].submit();
    return false;
}

function fileDl(cmd, binSid){
    $('#CMD')[0].value = cmd;
    document.forms[0].binSid.value=binSid;
    document.forms[0].submit();
    return false;
}

function rateChange(rate) {

    if (rate==-1) {
        var idx = 0;
        for (i = 0; i < document.forms[0].prj060status.length; i++) {
            if (document.forms[0].prj060status[i].checked == true) {
                idx = i;
                break;
            }
        }
        rate = document.forms[0].prj060status[idx].value;
    }

    if (rate==2) {
        document.getElementById('zisseki_fr').style.display = 'block';
        document.getElementById('zisseki_to').style.display = 'block';
        document.getElementById('kosu').style.display = 'block';
    } else {
        document.getElementById('zisseki_fr').style.display = 'none';
        document.getElementById('zisseki_to').style.display = 'none';
        document.getElementById('kosu').style.display = 'none';
    }
}

function clearDate(day, month, year){
    $('#' + day)[0].value = '';
    $('#' + month)[0].value = '';
    $('#' + year)[0].value = '';
}

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

function convYear(yyyy) {

  if(yyyy < 1900) {
    yyyy = 1900 + yyyy;
  }
  return yyyy;
}

function moveComment() {
    var mvFlg = document.forms['prj060Form'].prjmvComment;
    if (mvFlg.value == 1) {
        location.hash = 'prj060CmtPnt';
        mvFlg.value = 0;
    }
}