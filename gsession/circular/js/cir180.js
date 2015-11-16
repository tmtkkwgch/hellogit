function accountConf(mode, accountSid) {
    var thisForm = document.forms['cir180Form'];
    thisForm.CMD.value = 'confAccount';
    thisForm.cirCmdMode.value = mode;
    thisForm.cirAccountSid.value = accountSid;
    thisForm.submit();
    return false;
}

function accountEdit(mode, accountSid) {
    var thisForm = document.forms['cir180Form'];
    thisForm.CMD.value = 'editAccount';
    thisForm.cirCmdMode.value = mode;
    thisForm.cirAccountSid.value = accountSid;
    thisForm.submit();
    return false;
}

function confLabel(accountId) {
    var thisForm = document.forms['cir180Form'];
    thisForm.CMD.value = 'confLabel';
    thisForm.cirAccountSid.value = accountId;
    thisForm.submit();
    return false;
}

function confFilter(accountId) {
    var thisForm = document.forms['cir180Form'];
    thisForm.CMD.value = 'confFilter';
    thisForm.cirAccountSid.value = accountId;
    thisForm.submit();
    return false;
}
