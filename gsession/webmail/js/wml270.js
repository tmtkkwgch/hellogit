function getForm() {
    return document.forms['wml270Form'];
}

function editDestlist(destlistId) {
    getForm().CMD.value = 'destListDetail';
    getForm().wmlCmdMode.value = 1;
    getForm().wmlEditDestList.value = destlistId;
    getForm().submit();
    return false;
}

function sort(sortKey, orderKey) {
    getForm().CMD.value = 'init';
    getForm().wml270sortKey.value = sortKey;
    getForm().wml270order.value = orderKey;
    getForm().submit();
    return false;
}

function changePage(id){
    if (id == 1) {
        getForm().wml270pageTop.value=document.forms[0].wml270pageBottom.value;
    }

    getForm().CMD.value='init';
    getForm().submit();
    return false;
}
