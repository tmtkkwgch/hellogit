function changePage(id){
    if (id == 1) {
        document.forms[0].bbs010page1.value=document.forms[0].bbs010page2.value;
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

function clickForum(sid) {
    document.forms[0].CMD.value='moveThreadList';
    document.forms[0].bbs010forumSid.value=sid;
    document.forms[0].submit();
    return false;
}

function clickMemBtn(sid) {
    document.forms[0].CMD.value='moveMemList';
    document.forms[0].bbs010forumSid.value=sid;
    document.forms[0].submit();
    return false;
}

function clickRsvThread(sid) {
    document.forms[0].CMD.value='moveRsvThreadList';
    document.forms[0].bbs010forumSid.value=sid;
    document.forms[0].submit();
    return false;
}