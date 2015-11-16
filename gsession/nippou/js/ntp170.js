function buttonSubmit(mode, sid) {
    document.forms[0].ntp170NkbSid.value=sid;
    document.forms[0].ntp170ProcMode.value=mode;
    buttonPush(mode);
}