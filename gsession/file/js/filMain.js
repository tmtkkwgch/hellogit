
function MoveToFolderList(cabSid, dirSid) {
    document.forms['filMainForm'].backDsp.value='main';
    document.forms['filMainForm'].fil010SelectCabinet.value=cabSid;
    document.forms['filMainForm'].fil010SelectDirSid.value=dirSid;
    document.forms['filMainForm'].CMD.value='filMainFolderList';
    document.forms['filMainForm'].submit();
    return false;
}

function MoveToFolderDetail(dirSid) {
    document.forms['filMainForm'].CMD.value='filMainFolderDetail';
    document.forms['filMainForm'].backDspLow.value='main';
    document.forms['filMainForm'].fil050DirSid.value=dirSid;
    document.forms['filMainForm'].submit();
    return false;
}

function MoveToFileDetail(cabSid, dirSid) {
    document.forms['filMainForm'].CMD.value='filMainFileDetail';
    document.forms['filMainForm'].backDspLow.value='main';
    document.forms['filMainForm'].fil070DirSid.value=dirSid;
    document.forms['filMainForm'].fil010SelectCabinet.value=cabSid;
    document.forms['filMainForm'].fil010SelectDirSid.value=dirSid;
    document.forms['filMainForm'].submit();
    return false;
}

function fileDow(cmd, binSid){
    document.forms['filMainForm'].CMD.value=cmd;
    document.forms['filMainForm'].fileSid.value=binSid
    document.forms['filMainForm'].submit();
    return false;
}

function MoveToCallList() {
    document.forms['filMainForm'].CMD.value='filMainCallList';
    document.forms['filMainForm'].backDspCall.value='main';
    document.forms['filMainForm'].submit();
    return false;
}
function MoveToPconf() {
    document.forms['filMainForm'].CMD.value='filMainPconf';
    document.forms['filMainForm'].backMainFlg.value='1';
    document.forms['filMainForm'].submit();
    return false;
}
