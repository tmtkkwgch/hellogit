function selectCompany(code) {

    window.opener.document.forms[0].adr110coCode.value = code;
    window.close();
}

function changePage(id){
    if (id == 1) {
        document.forms[0].adr230PageTop.value=document.forms[0].adr230PageBottom.value;
    }
    document.forms[0].CMD.value='pageChange';
    document.forms[0].submit();
    return false;
}

function onTitleLinkSubmit(fid, order) {
    document.forms[0].adr230SortKey.value = fid;
    document.forms[0].adr230OrderKey.value = order;
    document.forms[0].submit();
    return false;
}