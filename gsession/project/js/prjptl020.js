function selectProject(prjSid) {
    document.forms[0].prjptl020selectPrjSid.value=prjSid;
    document.forms[0].CMD.value='selectProject';
    document.forms[0].submit();
    return false;
}

function closeWindow() {

    var closeFlg = document.forms[0].prjptl020selectFlg.value;

    if (closeFlg == 'true') {
        var parentDocument = window.opener.document;
        parentDocument.forms[0].CMD.value = 'init';
        parentDocument.forms[0].submit();
        window.close();
    }

    return false;
}

function changePageBottom() {
    document.forms[0].prjptl020pageTop.value=document.forms[0].prjptl020pageBottom.value;
    document.forms[0].submit();
    return false;
}
