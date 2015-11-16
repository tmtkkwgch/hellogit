function CreateCabinet() {
    document.forms[0].cmnMode.value='0';
    document.forms[0].backDsp.value='fil010';
    document.forms[0].CMD.value='fil010addCabinet';
    document.forms[0].submit();
    return false;
}

function CabinetDetail(cabSid) {
    document.forms[0].cmnMode.value='1';
    document.forms[0].backDsp.value='fil010';
    document.forms[0].fil010SelectCabinet.value=cabSid;
    document.forms[0].CMD.value='fil010cabinetDetail';
    document.forms[0].submit();
    return false;
}

function MoveToRootFolderList(cabSid, dirSid) {
    document.forms[0].backDsp.value='fil010';
    document.forms[0].fil010SelectCabinet.value=cabSid;
    document.forms[0].fil010SelectDirSid.value=dirSid;
    document.forms[0].CMD.value='fil010folderList';
    document.forms[0].submit();
    return false;
}
function MoveToFolderList(cabSid, dirSid) {
    document.forms[0].backDsp.value='fil010';
    document.forms[0].fil010SelectCabinet.value=cabSid;
    document.forms[0].fil010SelectDirSid.value=dirSid;
    document.forms[0].CMD.value='fil010folderList';
    document.forms[0].submit();
    return false;
}

function DeleteToShortCut() {
    document.forms[0].CMD.value='fil010scDelete';
    document.forms[0].submit();
    return false;
}

function MoveToSearch() {
    document.forms[0].CMD.value='fil010search';
    document.forms[0].backDsp.value='fil010';
    document.forms[0].submit();
    return false;
}

function MoveToFolderDetail(dirSid) {
    document.forms[0].CMD.value='fil010folderDetail';
    document.forms[0].backDspLow.value='fil010';
    document.forms[0].fil050DirSid.value=dirSid;
    document.forms[0].submit();
    return false;
}

function MoveToFileDetail(cabSid, dirSid) {
    document.forms[0].CMD.value='fil010fileDetail';
    document.forms[0].backDspLow.value='fil010';
    document.forms[0].fil070DirSid.value=dirSid;
    document.forms[0].fil010SelectCabinet.value=cabSid;
    document.forms[0].fil010SelectDirSid.value=dirSid;
    document.forms[0].submit();
    return false;
}

function MoveToCallList() {
    document.forms[0].CMD.value='fil010callList';
    document.forms[0].backDspCall.value='fil010';
    document.forms[0].submit();
    return false;
}