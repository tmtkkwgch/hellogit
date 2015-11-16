function selectGroup(grpSid) {
    document.forms[0].schptl020selectGrpSid.value=grpSid;
    document.forms[0].CMD.value='selectGroup';
    document.forms[0].submit();
    return false;
}

function closeWindow() {

    var closeFlg = document.forms[0].schptl020selectFlg.value;

    if (closeFlg == 'true') {
        var parentDocument = window.opener.document;
        parentDocument.forms[0].CMD.value = 'init';
        parentDocument.forms[0].submit();
        window.close();
    }

    return false;
}

