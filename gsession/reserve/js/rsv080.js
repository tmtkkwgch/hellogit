function buttonPush(cmd) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function moveSisetuEdit(sisetuSid){
    document.forms[0].CMD.value='sisetu_edit';
    document.forms[0].rsv080EditSisetuSid.value=sisetuSid;
    document.forms[0].submit();
    return false;
}