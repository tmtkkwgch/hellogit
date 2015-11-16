function changeGroupCombo(){
    document.forms[0].submit();
    return false;
}

function changeUserCombo(){
    document.forms[0].submit();
    return false;
}
function changePage(id){
    if (id == 1) {
        document.forms[0].rngAdminPageTop.value=document.forms[0].rngAdminPageBottom.value;
    }

    document.forms[0].CMD.value='init';
    document.forms[0].submit();
    return false;
}
function sorton(sortKey, orderKey){
    document.forms[0].CMD.value='init';
    document.forms[0].rngAdminOrderKey.value=orderKey;
    document.forms[0].rngAdminSortKey.value=sortKey;
    document.forms[0].submit();
    return false;
}

function selectPage(id){
    if (id == 1) {
        document.forms[0].rngAdminPageTop.value=document.forms[0].rngAdminPageBottom.value;
    }

    document.forms[0].CMD.value='init';
    document.forms[0].submit();
    return false;
}

function clickRingi(cmd, sid) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].rngSid.value=sid;
    document.forms[0].submit();
    return false;
}
