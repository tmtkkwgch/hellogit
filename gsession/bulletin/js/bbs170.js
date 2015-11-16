function changePage(id){
    if (id == 1) {
        document.forms[0].bbs170page1.value=document.forms[0].bbs170page2.value;
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
    document.forms[0].bbs170sortKey.value = fid;
    document.forms[0].bbs170orderKey.value = order;
    document.forms[0].submit();
    return false;
}

function buttnPushWrite(cmd, btiSid) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].threadSid.value=btiSid;
    document.forms[0].submit();
    return false;
}

function buttonPushDelete(btiSid) {
    document.forms[0].CMD.value='delThread';
    document.forms[0].threadSid.value=btiSid;
    document.forms[0].submit();
    return false;
}