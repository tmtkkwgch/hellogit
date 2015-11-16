function addPortal() {
    document.forms[0].ptlPortalSid.value=-1;
    document.forms[0].CMD.value='ptl030add';
    document.forms[0].submit();
    return false;
}

function editPortal(ptlSid) {
    document.forms[0].ptlPortalSid.value=ptlSid;
    document.forms[0].CMD.value='ptl030editPortal';
    document.forms[0].submit();
    return false;
}