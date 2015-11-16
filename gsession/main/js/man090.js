function buttonPush(cmd) {

    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}
function onSubmitLog(cmd, fileName){
    document.forms[0].CMD.value=cmd;
    document.forms[0].logName.value = fileName;
    document.forms[0].submit();
    return false;
}

