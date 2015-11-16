function editReserve(yoyakuSid) {
    document.forms['rsvmainForm'].CMD.value='sisetu_edit';
    document.forms['rsvmainForm'].rsvmainSselectedYoyakuSid.value=yoyakuSid;
    document.forms['rsvmainForm'].submit();
    return false;
}
