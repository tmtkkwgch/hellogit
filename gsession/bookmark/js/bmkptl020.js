function selectBmk(grepSid) {
    document.forms[0].CMD.value='selectBmk';
    document.forms[0].bmkptl020GrpSid.value = grepSid;
    document.forms[0].submit();
    return false;
}

function closeWindow() {

    var closeFlg = document.forms[0].bmkptl020selectFlg.value;

    if (closeFlg == 'true') {
        var parentDocument = window.opener.document;
        parentDocument.forms[0].CMD.value = 'init';
        parentDocument.forms[0].submit();
        window.close();
    }

    return false;
}

