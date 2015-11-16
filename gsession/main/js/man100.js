function buttonSubmit(cmd, mode, sid) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].man100ProcMode.value=mode;
    document.forms[0].man100EditPosSid.value=sid;
    document.forms[0].submit();
    return false;
}
