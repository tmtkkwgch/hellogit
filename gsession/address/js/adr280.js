function buttonSubmit(cmd, mode, sid) {
    document.forms[0].CMD.value=cmd;
    if (mode == 'edit') {
        document.forms[0].adr280ProcMode.value=1;
    } else if (mode == 'add') {
        document.forms[0].adr280ProcMode.value=0;
    }
    document.forms[0].adr280EditSid.value=sid;
    document.forms[0].submit();
    return false;
}

function buttonPush(cmd){
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}
