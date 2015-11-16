function selectPortlet(potletSid) {
    document.forms[0].ptl081selectPortletSid.value=potletSid;
    document.forms[0].CMD.value='selectPortlet';
    document.forms[0].submit();
    return false;
}

function closeWindow() {

    var closeFlg = document.forms[0].ptl081selectFlg.value;

    if (closeFlg == 'true') {
        var parentDocument = window.opener.document;
        parentDocument.forms[0].CMD.value = 'init';
        parentDocument.forms[0].submit();
        window.close();
    }

    return false;
}

