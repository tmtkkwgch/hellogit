function fileLinkClick(fileId) {
    document.forms[0].CMD.value='fileDownload';
    document.forms[0].rng061TmpFileId.value=fileId;
    document.forms[0].submit();
    return false;
}

function selectTpl() {
    document.forms[0].CMD.value='optbtn';
    document.forms[0].rng020Title.value=document.forms[0].rng061rngTitle.value;
    document.forms[0].rng020Content.value=document.forms[0].rng061content.value;
    document.getElementById('users').innerHTML = '';

    document.forms[0].submit();
    return false;
}
