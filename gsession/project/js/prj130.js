function selectTemplate(cmd, prtSid) {
    document.forms[0].CMD.value = cmd;
    document.forms[0].prtSid.value = prtSid;
    document.forms[0].submit();
    return false;
}