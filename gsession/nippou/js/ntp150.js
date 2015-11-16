function buttonSubmit(mode, ngpsid, ngysid) {
    document.forms[0].ntp150NgpSid.value=ngpsid;
    document.forms[0].ntp150NgySid.value=ngysid;
    document.forms[0].ntp150ProcMode.value=mode;
    buttonPush(mode);
}

function changeCmbo(cmd){
    document.forms[0].CMD.value=cmd;
    $("input[name=ntp150SortProcess]").val("");
    document.forms[0].submit();
    return false;
}