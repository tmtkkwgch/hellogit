function selectChannelTemplate(rctSid, mode){
    document.forms[0].CMD.value='editkeiro';
    document.forms[0].rctSid.value=rctSid;
    document.forms[0].rngRctCmdMode.value=mode;
    document.forms[0].submit();
    return false;
}
function addTemplate(mode){
    document.forms[0].CMD.value='addkeiro';
    document.forms[0].rngRctCmdMode.value=mode;
    document.forms[0].submit();
    return false;
}