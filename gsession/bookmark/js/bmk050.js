function buttonSubmit(cmd, sid, mode) {
    document.forms[0].bmk050LblSid.value=sid;
    document.forms[0].bmk050ProcMode.value=mode;
    buttonPush(cmd);
}