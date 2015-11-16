function getForm() {
    return document.forms['sch230Form'];
}

function editAccess(ssaSid) {
    getForm().CMD.value = 'spAccessDetail';
    getForm().sch230editMode.value = 1;
    getForm().sch230editData.value = ssaSid;
    getForm().submit();
    return false;
}

function sort(sortKey, orderKey) {
    getForm().CMD.value = 'init';
    getForm().sch230sortKey.value = sortKey;
    getForm().sch230order.value = orderKey;
    getForm().submit();
    return false;
}

function changePage(id){
    if (id == 1) {
        getForm().sch230pageTop.value=document.forms[0].sch230pageBottom.value;
    }

    getForm().CMD.value='init';
    getForm().submit();
    return false;
}
