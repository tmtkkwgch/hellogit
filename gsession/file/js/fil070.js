function fil070TitleClick(sortKey, orderKey) {

    document.forms[0].fil070OrderKey.value=orderKey;
    document.forms[0].fil070SortKey.value=sortKey;
    document.forms[0].CMD.value='titleClick';
    document.forms[0].submit();
    return false;
}

function fil070RepairClick(dirSid, version) {
    document.forms[0].fil070SltDirSid.value=dirSid;
    document.forms[0].fil070SltDirVer.value=version;
    document.forms[0].CMD.value='repairClick';
    document.forms[0].submit();
    return false;
}

function fil070TabChange(cmd, mode) {

    document.forms[0].CMD.value=cmd;
    document.forms[0].fil070DspMode.value=mode
    if (mode == '0') {
    	document.forms[0].fil070OrderKey.value='1';
    	document.forms[0].fil070SortKey.value='0';
    } else {
    	document.forms[0].fil070OrderKey.value='0';
    	document.forms[0].fil070SortKey.value='1';
    }
    document.forms[0].submit();
    return false;
}
function fil070changePage(id){
    if (id == 1) {
        document.forms[0].fil070PageNum2.value = document.forms[0].fil070PageNum1.value;
    } else {
        document.forms[0].fil070PageNum1.value = document.forms[0].fil070PageNum2.value;
    }

    document.forms[0].CMD.value='pageChange';
    document.forms[0].submit();
    return false;
}

function MoveToFileEdit(dirSid) {
    document.forms[0].CMD.value='fil070edit';
    document.forms[0].fil070DirSid.value=dirSid;
    document.forms[0].submit();
    return false;
}
