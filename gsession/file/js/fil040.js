function fil040TitleClick(sortKey, orderKey) {

    document.forms[0].fil040OrderKey.value=orderKey;
    document.forms[0].fil040SortKey.value=sortKey;
    document.forms[0].CMD.value='titleClick';
    document.forms[0].submit();
    return false;
}

function CreateFolder() {
    document.forms[0].backDspLow.value='fil040';
    document.forms[0].CMD.value='fil040createFolder';
    document.forms[0].fil050ParentDirSid.value = document.forms[0].fil010SelectDirSid.value;
    document.forms[0].submit();
    return false;
}

function CreateFile() {
    document.forms[0].backDspLow.value='fil040';
    document.forms[0].CMD.value='fil040addFile';
    document.forms[0].fil070ParentDirSid.value = document.forms[0].fil010SelectDirSid.value;
    document.forms[0].submit();
    return false;
}

function DeleteDirectory() {
    document.forms[0].CMD.value='fil040delete';
    document.forms[0].submit();
    return false;
}

function MovePlural() {
    document.forms[0].fil090SelectPluralKbn.value=1;
    document.forms[0].CMD.value='fil040movePlural';
    document.forms[0].submit();
    return false;
}

function FileRockOn() {
    document.forms[0].CMD.value='fil040lockPlural';
    document.forms[0].submit();
    return false;
}

function FileRockOff() {
    document.forms[0].CMD.value='fil040unlockPlural';
    document.forms[0].submit();
    return false;
}

function MoveToSearch() {
    document.forms[0].CMD.value='fil040search';
    document.forms[0].backDsp.value='fil040';
    document.forms[0].submit();
    return false;
}

function MoveToFolderDetail(dirSid,fdrParentSid) {
    document.forms[0].CMD.value='fil040folderDetail';
    document.forms[0].backDspLow.value='fil040';
    document.forms[0].fil050DirSid.value=dirSid;
    document.forms[0].fil050ParentDirSid.value=fdrParentSid;
    document.forms[0].submit();
    return false;
}

function MoveToFileDetail(dirSid) {
    document.forms[0].CMD.value='fil040fileDetail';
    document.forms[0].backDspLow.value='fil040';
    document.forms[0].fil070DirSid.value=dirSid;
    document.forms[0].submit();
    return false;
}

function MoveToFileMove(dirSid) {
    document.forms[0].CMD.value='fil040move';
    document.forms[0].fil090DirSid.value=dirSid;
    document.forms[0].fil090SelectPluralKbn.value=0;
    document.forms[0].submit();
    return false;
}

function MoveToAconf() {
    document.forms[0].CMD.value='fil040aconf';
    document.forms[0].backDsp.value='fil040';
    document.forms[0].submit();
    return false;
}

function MoveToPconf() {
    document.forms[0].CMD.value='fil040pconf';
    document.forms[0].backDsp.value='fil040';
    document.forms[0].submit();
    return false;
}

function UnLock(dirSid,verSid) {
    document.forms[0].CMD.value='fil040unlock';
    document.forms[0].fil040SelectUnlock.value=dirSid;
    document.forms[0].fil040SelectUnlockVer.value=verSid;
    document.forms[0].submit();
    return false;
}

function changeChk() {
   var chkFlg;

   if (document.forms[0].fil040SelectDelAll.checked) {

       chkFlg = true;

   } else {

       chkFlg = false;

   }
   delAry = document.getElementsByName("fil040SelectDel");

   for(i = 0; i < delAry.length; i++) {

       delAry[i].checked = chkFlg;

   }
}