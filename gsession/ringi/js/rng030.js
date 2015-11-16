function fileNameClick(fileId) {

    document.forms[0].CMD.value='fileDownload';
    document.forms[0].rng030fileId.value=fileId;
    document.forms[0].submit();
    return false;

}

function copyApply() {
    document.forms[0].rng020copyApply.value = 'true';
    return buttonPush('copyApply');
}