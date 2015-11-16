function selectCabinet(cabSid) {
    document.forms[0].filptl020selectFcbSid.value=cabSid;
    document.forms[0].CMD.value='selectCabinet';
    document.forms[0].submit();
    return false;
}

function closeWindow() {

    var closeFlg = document.forms[0].filptl020selectFlg.value;

    if (closeFlg == 'true') {
        var parentDocument = window.opener.document;
        parentDocument.forms[0].CMD.value = 'init';
        parentDocument.forms[0].submit();
        window.close();
    }

    return false;
}

