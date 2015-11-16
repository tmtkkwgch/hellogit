function buttonSubmit(mode, sid) {
    document.forms[0].ntp180NkhSid.value=sid;
    document.forms[0].ntp180ProcMode.value=mode;
    buttonPush(mode);
}