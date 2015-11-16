function editPortlet(cmd, ptlSid, mode) {
    document.forms[0].ptlPortletSid.value=ptlSid;
    document.forms[0].ptlCmdMode.value=mode;
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function addCategory(cmd, procMode) {
    document.forms[0].ptlCmdMode.value=procMode;
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}
