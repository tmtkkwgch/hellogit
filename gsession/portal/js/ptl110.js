function addEditCategoryWithFlg(catSid, kbn, cmd){
    document.forms[0].ptlCmdMode.value=kbn;
    document.forms[0].ptlPtlCategorytSid.value=catSid;
    document.forms[0].ptlPlcBack.value=1;
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}
