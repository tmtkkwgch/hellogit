function buttonPushWithFileName(cmd, fileName) {
    document.forms[0].CMD.value = cmd;
    document.forms[0].man081backupFile.value = fileName;
    document.forms[0].submit();
    return false;
}
