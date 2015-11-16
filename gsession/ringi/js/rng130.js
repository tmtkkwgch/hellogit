function selectPage(id){
    if (id == 1) {
        document.forms[0].rng130pageTop.value=document.forms[0].rng130pageBottom.value;
    }

    document.forms[0].CMD.value='init';
    document.forms[0].submit();
    return false;
}

function changeGroupCombo(){
    document.forms[0].submit();
    return false;
}

function clickRingi(cmd, cmdMode, sid) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].rngCmdMode.value=cmdMode;
    document.forms[0].rngSid.value=sid;
    document.forms[0].submit();
    return false;
}

function clickJyusinRingi(cmd, cmdMode, apprMode, sid) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].rngSid.value=sid;
    document.forms[0].rngCmdMode.value=cmdMode;
    document.forms[0].rngApprMode.value=apprMode;
    document.forms[0].submit();
    return false;
}

function clickTitle(sortKey, orderKey) {
    document.forms[0].CMD.value='init';
    document.forms[0].rng010sortKey.value=sortKey;
    document.forms[0].rng010orderKey.value=orderKey;
    document.forms[0].submit();
    return false;
}

function clickSortTitle(sortValue) {

    if (document.forms[0].sltSortKey1.value == sortValue) {

        if (document.forms[0].rng130orderKey1[0].checked == true) {
            document.forms[0].rng130orderKey1[0].checked = false;
            document.forms[0].rng130orderKey1[1].checked = true;
        } else {
            document.forms[0].rng130orderKey1[1].checked = false;
            document.forms[0].rng130orderKey1[0].checked = true;
        }
    } else {
        document.forms[0].sltSortKey1.value = sortValue;
    }

//    document.forms[0].CMD.value='init';
//    document.forms[0].submit();
    return false;
}

function changeProcType(){
    controlInput();

    document.forms[0].CMD.value='changeType';
    document.forms[0].submit();
    return false;
}

function controlInput(){

    if (document.forms[0].rng130Type[0].checked == true) {
        document.forms[0].sltApplYearFr.disabled=false;
        document.forms[0].sltApplMonthFr.disabled=false;
        document.forms[0].sltApplDayFr.disabled=false;
        document.forms[0].sltApplYearTo.disabled=false;
        document.forms[0].sltApplMonthTo.disabled=false;
        document.forms[0].sltApplDayTo.disabled=false;
        document.forms[0].applDateFrBtn.disabled=false;
        document.forms[0].applDateToBtn.disabled=false;

        document.forms[0].sltLastManageYearFr.disabled=true;
        document.forms[0].sltLastManageMonthFr.disabled=true;
        document.forms[0].sltLastManageDayFr.disabled=true;
        document.forms[0].sltLastManageYearTo.disabled=true;
        document.forms[0].sltLastManageMonthTo.disabled=true;
        document.forms[0].sltLastManageDayTo.disabled=true;
        document.forms[0].lastManageDateFrBtn.disabled=true;
        document.forms[0].lastManageDateToBtn.disabled=true;

    } else if (document.forms[0].rng130Type[3].checked == true) {
        document.forms[0].sltApplYearFr.disabled=true;
        document.forms[0].sltApplMonthFr.disabled=true;
        document.forms[0].sltApplDayFr.disabled=true;
        document.forms[0].sltApplYearTo.disabled=true;
        document.forms[0].sltApplMonthTo.disabled=true;
        document.forms[0].sltApplDayTo.disabled=true;
        document.forms[0].applDateFrBtn.disabled=true;
        document.forms[0].applDateToBtn.disabled=true;

        document.forms[0].sltLastManageYearFr.disabled=true;
        document.forms[0].sltLastManageMonthFr.disabled=true;
        document.forms[0].sltLastManageDayFr.disabled=true;
        document.forms[0].sltLastManageYearTo.disabled=true;
        document.forms[0].sltLastManageMonthTo.disabled=true;
        document.forms[0].sltLastManageDayTo.disabled=true;
        document.forms[0].lastManageDateFrBtn.disabled=true;
        document.forms[0].lastManageDateToBtn.disabled=true;
        
    } else {
        document.forms[0].sltApplYearFr.disabled=false;
        document.forms[0].sltApplMonthFr.disabled=false;
        document.forms[0].sltApplDayFr.disabled=false;
        document.forms[0].sltApplYearTo.disabled=false;
        document.forms[0].sltApplMonthTo.disabled=false;
        document.forms[0].sltApplDayTo.disabled=false;
        document.forms[0].applDateFrBtn.disabled=false;
        document.forms[0].applDateToBtn.disabled=false;

        document.forms[0].sltLastManageYearFr.disabled=false;
        document.forms[0].sltLastManageMonthFr.disabled=false;
        document.forms[0].sltLastManageDayFr.disabled=false;
        document.forms[0].sltLastManageYearTo.disabled=false;
        document.forms[0].sltLastManageMonthTo.disabled=false;
        document.forms[0].sltLastManageDayTo.disabled=false;
        document.forms[0].lastManageDateFrBtn.disabled=false;
        document.forms[0].lastManageDateToBtn.disabled=false;
    }
}