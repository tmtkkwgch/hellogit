function buttonSubmit(cmd, sid) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].usr044ProcMode.value=1;
    document.forms[0].usr043EditSid.value=sid;
    document.forms[0].submit();
    return false;
}

function buttonPush(cmd){
    document.forms[0].CMD.value=cmd;
    document.forms[0].usr044ProcMode.value=0;
    document.forms[0].submit();
    return false;
}

function changeChk(){
    var chkFlg;
    if (document.forms[0].allCheck.checked) {
        chkFlg = true;
    } else {
        chkFlg = false;
    }
    delAry = document.getElementsByName("selectLabel");
    for(i = 0; i < delAry.length; i++) {
        delAry[i].checked = chkFlg;
    }
}

function buttonPushWithSid(cmd, sid) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].labelEditSid.value=sid;
    document.forms[0].usr044ProcMode.value=1;
    document.forms[0].submit();
    return false;
}
