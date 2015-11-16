function buttonSubmit(mode, sid) {
    document.forms[0].ntp140NgySid.value=sid;
    document.forms[0].ntp140ProcMode.value=mode;
    buttonPush(mode);
}