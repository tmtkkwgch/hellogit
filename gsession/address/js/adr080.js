function buttonSubmit(cmd, mode, sid) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].adr080ProcMode.value=mode;
    document.forms[0].adr080EditAtiSid.value=sid;
    document.forms[0].submit();
    return false;
}