function clickRingi(cmd, cmdMode, apprMode, sid) {
    document.forms['rngmainForm'].CMD.value=cmd;
    document.forms['rngmainForm'].rngCmdMode.value=cmdMode;
    document.forms['rngmainForm'].rngApprMode.value=apprMode;
    document.forms['rngmainForm'].rngSid.value=sid;
    document.forms['rngmainForm'].submit();
    return false;
}
