function file230TreeClick(cmd, sid) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].fil230DeleteDirSid.value=sid;
    document.forms[0].submit();
    return false;
}
