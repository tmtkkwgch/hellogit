function unload(){
    document.forms[0].CMD.value = 'formUnload';
    document.forms[0].formUnload.value='true';
    document.forms[0].submit();
    return false;
}

function moveMonthSchedule(cmd, uid, ukbn){
    window.opener.document.forms[0].cmd.value=cmd;
    window.opener.document.forms[0].CMD.value=cmd;
    window.opener.document.forms[0].sch010SelectUsrSid.value=uid;
    window.opener.document.forms[0].sch010SelectUsrKbn.value=ukbn;
    window.opener.document.forms[0].submit();
    return false;
}