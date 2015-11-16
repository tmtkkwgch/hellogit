function changePage(id){
    if (id == 1) {
        document.forms[0].bbs060page1.value=document.forms[0].bbs060page2.value;
    }

    document.forms[0].CMD.value='init';
    document.forms[0].submit();
    return false;
}

function buttonPush(cmd){

    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function clickThread(sid) {
    document.forms[0].CMD.value='moveThreadDtl';
    document.forms[0].threadSid.value=sid;
    document.forms[0].submit();
    return false;
}

function onTitleLinkSubmit(fid, order) {
    document.forms[0].CMD.value='init';
    document.forms[0].bbs060sortKey.value = fid;
    document.forms[0].bbs060orderKey.value = order;
    document.forms[0].submit();
    return false;
}