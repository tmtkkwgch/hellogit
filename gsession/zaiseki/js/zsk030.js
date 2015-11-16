
function zasekiEdit(cmd, sid) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].editZifSid.value=sid;
    document.forms[0].submit();
    return false;
}
