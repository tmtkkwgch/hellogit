function buttonPush(cmd) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function moveSisetuGroup(selectedSid){
    document.forms[0].CMD.value='sisetu_group_hensyu';
    document.forms[0].rsv050EditGrpSid.value=selectedSid;
    document.forms[0].submit();
    return false;
}

function moveSisetu(selectedSid){
    document.forms[0].CMD.value='sisetu_zyoho_settei';
    document.forms[0].rsv050EditGrpSid.value=selectedSid;
    document.forms[0].submit();
    return false;
}