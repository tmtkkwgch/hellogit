function posclose(innerflg){

    clearScreenParent(innerflg);

    parent.YAHOO.subbox.subPanel.hide();
    parentEnable();
}

function parentEnable(){
    setAllReadOnly(parent.document, false);
}

function parentReadOnly(){

    setAllReadOnly(parent.document, true);
}

function parentReload(sid){
    var CMD = parent.document.getElementsByName('CMD');
    CMD[0].value = 'copyClick';
    parent.document.forms[0].copyProjectSid.value = sid;

    parentEnable();
    parent.document.forms[0].submit();
}
