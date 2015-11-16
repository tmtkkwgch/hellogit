function getWml010Form() {
//    return document.forms['wml010Form'];
    return document.forms[0];
}

function changeAccount() {
    getWml010Form().CMD.value='init';
    getWml010Form().wml010viewDirectory.value=0;
    getWml010Form().wml010viewDirectoryType.value=0;
    getWml010Form().wml010viewLabel.value=0;
    getWml010Form().wml010selectPage.value=0;
    getWml010Form().wml010sortKey.value=0;
    getWml010Form().wml010order.value=0;
    getWml010Form().submit();
    return false;
}

function clickNotSelectAccount(accountSid) {
    var paramList = document.getElementsByName('wmlViewAccount')[0];
    for (index = 0; index < paramList.length; index++) {
        paramList[index].selected = (paramList[index].value == accountSid);
    }

    return changeAccount();
}

function fileDownload(messageNum, fileId) {
    var url = "../webmail/wml010.do";
    url += "?CMD=downloadFile";
    url += "&wmlViewAccount=" + getWml010ViewAccount();
    url += "&wml010downloadMessageNum=" + messageNum;
    url += "&wml010downloadFileId=" + fileId;
    window.open(url);
}

function sendMailFileDownload(fileName) {
    var url = "../webmail/wml010.do";
    url += "?CMD=sendFileDownload";
    url += "&wmlViewAccount=" + getWml010ViewAccount();
    url += "&wml010sendMailDownloadFile=" + fileName;
    window.open(url);
}

function sendMailFileDelete(fileName) {
    $('#sendFile_' + fileName).remove();
    $('#sendFile_confirm_' + fileName).remove();

    if (document.getElementsByName('sendFile_Links').length == 0) {
        document.getElementById('composeTempFile').innerHTML = '';
    }

    var url = "../webmail/wml010.do";
    url += "?CMD=sendFileDelete";
    url += "&wmlViewAccount=" + getWml010ViewAccount();
    url += "&wml010sendMailDownloadFile=" + fileName;
    YAHOO.util.Connect.asyncRequest('get', url);

}

function moveAccountConf() {
    getWml010Form().CMD.value='accountConf';
    getWml010Form().wmlAccountMode.value=0;
    getWml010Form().submit();
    return false;
}

function changeSearchDateType() {
   var searchDate = document.getElementsByName('wml010searchDateType');

   var checkValue = 0;
   for (index = 0; index < searchDate.length; index++) {
       if (searchDate[index].checked) {
           checkValue = searchDate[index].value;
           break;
       }
   }

   if (checkValue == 0) {
       $('#searchDateArea').hide();
   } else {
       $('#searchDateArea').show();
   }
}

function viewSearchDetail() {
    var detailSearchFlg = document.forms[0].detailSearchFlg;
    if (detailSearchFlg.value == 1) {
        $('#top_detailSearch').show();
        $('#wmlSearchKeywordKbn').hide();
        $('#wmlSearchKeywordKbn').show();
        detailSearchFlg.value = 2;
    } else {
        $('#top_detailSearch').hide();
        detailSearchFlg.value = 1;
    }

    return false;
}

function getWml010ViewAccount() {
    var value = 0;
    var paramList = document.getElementsByName('wmlViewAccount')[0];
    if (paramList != null) {
        for (index = 0; index < paramList.length; index++) {
            if (paramList[index].selected) {
                value = paramList[index].value;
                break;
            }
        }
    }

    return value;
}