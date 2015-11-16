function buttonPush(cmd) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}
function changeGroupCombo(){
    document.forms[0].CMD.value='back_to_import_input';
    document.forms[0].ntp110SltUser.value='-1';
    document.forms[0].submit();
    return false;
}