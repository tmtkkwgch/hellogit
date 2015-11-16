function changePage(id){
    if (id == 1) {
        document.forms[0].fil100pageNum2.value = document.forms[0].fil100pageNum1.value;
    } else {
        document.forms[0].fil100pageNum1.value = document.forms[0].fil100pageNum2.value;
    }

    document.forms[0].CMD.value='pageChange';
    document.forms[0].submit();
    return false;
}

function sort(sortKey, orderKey) {
    document.forms[0].fil100sortKey.value = sortKey;
    document.forms[0].fil100orderKey.value = orderKey;

    buttonPush('init');
}

function downLoad(cmd, binSid){

    document.forms[0].CMD.value = cmd;
    document.forms[0].binSid.value = binSid;
    document.forms[0].submit();
    return false;
}

function MoveToFolderDetail(dirSid, fcbSid, fdrParentSid) {
    document.forms[0].CMD.value='fil100folderDetail';
    document.forms[0].backDspLow.value='fil100';
    document.forms[0].fil050DirSid.value=dirSid;
    document.forms[0].fil050ParentDirSid.value=fdrParentSid;
    document.forms[0].fil010SelectCabinet.value=fcbSid;
    document.forms[0].fil010SelectDirSid.value=fdrParentSid;

    document.forms[0].submit();
    return false;
}

function MoveToFileDetail(dirSid, fcbSid, fdrParentSid) {
    document.forms[0].CMD.value='fil100fileDetail';
    document.forms[0].backDspLow.value='fil100';
    document.forms[0].fil070DirSid.value=dirSid;
    document.forms[0].fil070ParentDirSid.value=fdrParentSid;
    document.forms[0].fil010SelectCabinet.value=fcbSid;
    document.forms[0].fil010SelectDirSid.value=fdrParentSid;

    document.forms[0].submit();
    return false;
}

function showWarnDialog(count) {

    var widthStr = 500;
    var heightStr = 300;
    $('#delMailMsgArea').html("");
    $('#delMailMsgArea').append(msglist['fil.fil100.1'] + count + msglist['fil.fil100.2']);
    $('#delMailMsgPop').dialog({
        autoOpen: true,  // hide dialog
        bgiframe: true,   // for IE6
        resizable: false,
        height: heightStr,
        width: widthStr,
        modal: true,
        overlay: {
          backgroundColor: '#000000',
          opacity: 0.5
        },
        buttons: {
          はい: function() {
              $(this).dialog('close');
              document.forms[0].fil100WarnOk.value=1;
              document.forms[0].submit();
          },
          いいえ: function() {
              $(this).dialog('close');
          }
        }
    });
}