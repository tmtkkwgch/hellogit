function addButton(kbn){
    document.forms[0].CMD.value='tcd060_add';
    document.forms[0].addTimezoneKbn.value=kbn;
    document.forms[0].editTimezoneSid.value='';
    document.forms[0].submit();
    return false;
}
function editButton(sid){
    document.forms[0].CMD.value='tcd060_edit';
    document.forms[0].addTimezoneKbn.value='';
    document.forms[0].editTimezoneSid.value=sid;
    document.forms[0].submit();
    return false;
}
