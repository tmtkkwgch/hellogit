function changeGroupCombo(){
    document.forms[0].submit();
    return false;
}

function changeGroupCombo2(){
    document.forms[0].CMD.value='changeGroup';
    document.forms[0].submit();
    return false;
}

function fileLinkClick(binSid) {
    document.forms[0].CMD.value='fileDownload';
    document.forms[0].rng020BinSid.value=binSid;
    document.forms[0].submit();
    return false;
}

function scroll() {
    if (document.forms[0].rng020ScrollFlg.value=='1') {
        window.location.hash='add_user';
    }
}

function templateFileLinkClick(fileId) {
    document.forms[0].CMD.value='templateFileDownload';
    document.forms[0].rng020TemplateFileId.value=fileId;
    document.forms[0].submit();
    return false;
}
