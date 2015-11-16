function editDetail(sid) {
    document.forms[0].anp090SelectSid.value=sid;
    document.forms[0].CMD.value='anp090edit';
    document.forms[0].submit();
    return false;
}
