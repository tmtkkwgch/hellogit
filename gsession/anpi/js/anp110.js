function selectUser(usrSid, usrName) {
    document.forms[0].anp110SelectUserSid.value=usrSid;
    document.forms[0].anp110SelectUserNm.value=usrName;
    document.forms[0].CMD.value='anp110edit';
    document.forms[0].submit();
}

function changePage(cmbObj) {
    document.forms[0].anp110NowPage.value=cmbObj.options[cmbObj.selectedIndex].value;
    document.forms[0].CMD.value='anp110pageChange';
    document.forms[0].submit();
}

function sortList(colIndex, order) {
    document.forms[0].anp110SortKeyIndex.value=colIndex;
    document.forms[0].anp110OrderKey.value=order;
    document.forms[0].CMD.value='anp110sortList';
    document.forms[0].submit();
}