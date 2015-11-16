
function changePage(id){
    if (id == 1) {
        document.forms[0].man050PageTop.value=document.forms[0].man050PageBottom.value;
    }
    document.forms[0].submit();
    return false;
}

function changeTab(cmd){

    document.forms[0].CMD.value=cmd;

    if (cmd == 'initsearch') {
        document.forms[0].man050cmdMode.value = 1;
        document.forms[0].man050SearchFlg.value = 1;
        document.forms[0].CMD.value='search';
    }


    document.forms[0].submit();
    return false;
}

function onTitleLinkSubmit(fid, order) {
    document.forms[0].CMD.value = 'sort';
    document.forms[0].man050SortKey.value = fid;
    document.forms[0].man050OrderKey.value = order;
    document.forms[0].submit();
    return false;
}

function changeGrp() {
    document.forms[0].CMD.value = '';
    document.forms[0].submit();
    return false;
}

function changeGrp2() {
    document.forms[0].CMD.value = 'search';
    document.forms[0].man050usrSid.value = -1;
    document.forms[0].submit();
    return false;
}

function buttonPush(cmd) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function moveDetail(usrSid) {
    document.forms[0].man050SelectedUsrSid.value = usrSid;
    document.forms[0].CMD.value='detail';
    document.forms[0].submit();
    return false;
}

function tarminalChange(kbn) {

    if (kbn == 1) {
        $('input:radio[name=man050Car]').val([0]);
    }

    buttonPush('search');
}

function tarminalChangeInit() {

    var terminalKbn = 0;
    for (i = 0; i < document.forms[0].man050Terminal.length; i++) {
        if (document.forms[0].man050Terminal[i].checked == true) {
            terminalKbn = i;
        }
    }
    var terminalKbnVal = document.forms[0].man050Terminal[terminalKbn].value;

    if (terminalKbnVal == 0 || terminalKbnVal == 2) {
        for (i = 0; i < document.forms[0].man050Car.length; i++) {
            document.forms[0].man050Car[i].disabled=false;
        }
    } else {
        document.forms[0].man050Car.value=0;
        for (i = 0; i < document.forms[0].man050Car.length; i++) {
            document.forms[0].man050Car[i].disabled=true;
        }
    }
}

function moveDay(frto, kbn) {

    var elmYear = null;
    var elmMonth = null;
    var elmDay = null;

    if (frto == 1) {
        elmYear = document.man050Form.man050FrYear;
        elmMonth = document.man050Form.man050FrMonth;
        elmDay = document.man050Form.man050FrDay;
    } else {
        elmYear = document.man050Form.man050ToYear;
        elmMonth = document.man050Form.man050ToMonth;
        elmDay = document.man050Form.man050ToDay;
    }

    systemDate = new Date();

    if (kbn == 2) {
        elmYear.value = convYear(systemDate.getYear());
        elmMonth.value = systemDate.getMonth() + 1;
        elmDay.value = systemDate.getDate();
        buttonPush('search');
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
                elmYear.value = '';
            } else {
                elmYear.value = newYear;
            }

            elmMonth.value = newDate.getMonth() + 1;
            elmDay.value = newDate.getDate();

        } else {

            if (elmYear.value == '' && elmMonth.value == '' && elmDay.value == '') {
                elmYear.value = convYear(systemDate.getYear());
                elmMonth.value = systemDate.getMonth() + 1;
                elmDay.value = systemDate.getDate();
            }
        }
    }
    buttonPush('search');
}