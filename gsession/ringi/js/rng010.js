function selectPage(id){
    if (id == 1) {
        document.forms[0].rng010pageTop.value=document.forms[0].rng010pageBottom.value;
    }

    document.forms[0].CMD.value='init';
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

function changeMode(mode, sortKey, orderKey) {
    document.forms[0].CMD.value='init';
    document.forms[0].rngProcMode.value=mode;
    document.forms[0].rng010sortKey.value=sortKey;
    document.forms[0].rng010orderKey.value=orderKey;
    document.forms[0].submit();
    return false;
}

function changeChk(){

   var chkFlg;
   if (document.forms[0].allChk.checked) {
       checkAll('rng010DelSidList');

   } else {
       nocheckAll('rng010DelSidList');
   }
}
