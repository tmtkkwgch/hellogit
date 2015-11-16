function changeGroupCombo(){
    document.forms[0].CMD.value='research';
    document.forms[0].sch100SltUser.value='-1';
    document.forms[0].submit();
    return false;
}
function buttonPush(cmd){
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}
function buttonPush(cmd, mod){
    document.forms[0].CMD.value=cmd;
    document.forms[0].cmd.value=mod;
    document.forms[0].submit();
    return false;
}
function onTitleLinkSubmit(fid, order) {
    document.forms[0].CMD.value='research';
    document.forms[0].sch100SortKey1.value = fid;
    document.forms[0].sch100OrderKey1.value = order;
    document.forms[0].submit();
    return false;
}

function clickTitle(sortKey, orderKey) {
    document.forms[0].CMD.value='research';
    document.forms[0].sch100SortKey1.value=sortKey;
    document.forms[0].sch100OrderKey1.value=orderKey;
    document.forms[0].submit();
    return false;
}

function clickSortTitle(sortValue) {

    if (document.forms[0].sch100SortKey1.value == sortValue) {

        if (document.forms[0].sch100OrderKey1[0].checked == true) {
            document.forms[0].sch100OrderKey1[0].checked = false;
            document.forms[0].sch100OrderKey1[1].checked = true;
        } else {
            document.forms[0].sch100OrderKey1[1].checked = false;
            document.forms[0].sch100OrderKey1[0].checked = true;
        }
    } else {
        document.forms[0].sch100SortKey1.value = sortValue;
    }

    document.forms[0].CMD.value='research';
    document.forms[0].submit();
    return false;
}

function editSchedule(cmd, ymd, sid, uid, ukbn){
    document.forms[0].cmd.value=cmd;
    document.forms[0].CMD.value=cmd;
    document.forms[0].sch010SelectDate.value=ymd;
    document.forms[0].sch010SelectUsrSid.value=uid;
    document.forms[0].sch010SelectUsrKbn.value=ukbn;
    document.forms[0].sch010SchSid.value=sid;
    document.forms[0].listMod.value='5';
    document.forms[0].submit();
    return false;
}

function changePage1() {
    document.forms[0].CMD.value='research';
    for (i = 0; i < document.forms[0].sch100Slt_page1.length; i++) {
        if (document.forms[0].sch100Slt_page1[i].selected) {
            document.forms[0].sch100Slt_page2.value=document.forms[0].sch100Slt_page1[i].value;
            document.forms[0].sch100PageNum.value=document.forms[0].sch100Slt_page1[i].value;
        }
    }
    document.forms[0].submit();
    return false;
}

function changePage2() {
    document.forms[0].CMD.value='research';
    for (i = 0; i < document.forms[0].sch100Slt_page2.length; i++) {
        if (document.forms[0].sch100Slt_page2[i].selected) {
            document.forms[0].sch100Slt_page1.value=document.forms[0].sch100Slt_page2[i].value;
            document.forms[0].sch100PageNum.value=document.forms[0].sch100Slt_page2[i].value;
        }
    }
    document.forms[0].submit();
    return false;
}
function moveMonthSchedule(cmd, uid, ukbn){
    document.forms[0].cmd.value=cmd;
    document.forms[0].CMD.value=cmd;
    document.forms[0].sch010SelectUsrSid.value=uid;
    document.forms[0].sch010SelectUsrKbn.value=ukbn;
    document.forms[0].submit();
    return false;
}
function moveDay(elmYear, elmMonth, elmDay, kbn) {

    systemDate = new Date();

    if (kbn == 2) {
        $(elmYear).val(convYear(systemDate.getYear()));
        $(elmMonth).val(systemDate.getMonth() + 1);
        $(elmDay).val(systemDate.getDate());
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
function openSch100Group(sch100SltGroup, sltGroup, flg){
    document.forms[0].CMD.value='research';
    document.forms[0].sch100SltUser.value='-1';
    openGroupWindow(sch100SltGroup, sltGroup, flg);
    return false;
}