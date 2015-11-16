function changePage(id){
    if (id == 1) {
        document.forms[0].bbs080page1.value=document.forms[0].bbs080page2.value;
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

function buttnPushWrite(cmd, sid) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].bbs080writeSid.value=sid;
    document.forms[0].submit();
    return false;
}

function fileLinkClick2(binSid) {
    document.forms[0].CMD.value='fileDownload';
    document.forms[0].bbs080binSid.value=binSid;
    document.forms[0].submit();
    return false;
}

function fileLinkClick(forumSid, threadSid, writeSid, binSid){

    document.forms[0].CMD.value='fileDownload';
    document.forms[0].bbs010forumSid.value=forumSid;
    document.forms[0].threadSid.value=threadSid;
    document.forms[0].bbs080binSid.value=writeSid;
    document.forms[0].bbs080writeSid.value=binSid;
    document.forms[0].submit();

    return false;
}

function changeOrder() {
    document.forms[0].CMD.value='init';
    document.forms[0].submit();
    return false;
}