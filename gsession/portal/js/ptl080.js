function selectPlugin(pluginId, dspId) {
    document.forms[0].plt080pluginId.value=pluginId;
    document.forms[0].ptl080dspId.value=dspId;
    document.forms[0].CMD.value='selectPlugin';
    document.forms[0].submit();
    return false;
}

function closeWindow() {

    var closeFlg = document.forms[0].ptl080selectFlg.value;

    if (closeFlg == 'true') {
        var parentDocument = window.opener.document;
        parentDocument.forms[0].CMD.value = 'init';
        parentDocument.forms[0].submit();
        window.close();
    }

    return false;
}

