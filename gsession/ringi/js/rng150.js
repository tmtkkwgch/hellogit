function sortUp(templateMode) {
    document.forms[0].rngTemplateMode.value = templateMode;
    return buttonPush('sortUp');
}
function sortDown(templateMode) {
    document.forms[0].rngTemplateMode.value = templateMode;
    return buttonPush('sortDown');
}

function addEditCategoryWithFlg(catSid, kbn, cmd){
    document.forms[0].rng140ProcMode.value=kbn;
    document.forms[0].rng140CatSid.value=catSid;
    document.forms[0].rng140SeniFlg.value=1;
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}