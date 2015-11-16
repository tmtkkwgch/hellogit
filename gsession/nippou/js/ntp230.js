function buttonSubmit(mode, sid) {
    document.forms[0].ntp230NtgSid.value=sid;
    document.forms[0].ntp230ProcMode.value=mode;
    buttonPush(mode);
}
