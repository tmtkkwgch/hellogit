function changeBmkKbn(tab) {
    document.forms[0].bmk010mode.value = tab;

    document.forms[0].CMD.value='changeTab';
    document.forms[0].submit();
    return false;
}

function buttonPushAdd(bmuSid) {
    document.forms[0].procMode.value = 0;
    document.forms[0].editBmuSid.value = bmuSid;
    document.forms[0].returnPage.value = 'bmk010';

    document.forms[0].CMD.value='registUrl';
    document.forms[0].submit();
    return false;
}

function buttonPushEdit(bmkSid) {
    document.forms[0].procMode.value = 1;
    document.forms[0].editBmkSid.value = bmkSid;
    document.forms[0].returnPage.value = 'bmk010';

    document.forms[0].CMD.value='registBookmark';
    document.forms[0].submit();
    return false;
}

function sortChange(sort, order) {
    document.forms[0].bmk010sortKey.value = sort;
    document.forms[0].bmk010orderKey.value = order;

    document.forms[0].CMD.value='init';
    document.forms[0].submit();
    return false;
}

function changePage(pageCmbName) {
    setPageParam('bmk010page', pageCmbName);

    document.forms[0].CMD.value='init';
    document.forms[0].submit();
    return false;
}

function selectLabel(blbSid) {
    document.forms[0].bmk010searchLabel.value = blbSid;

    document.forms[0].CMD.value='selectLabel';
    document.forms[0].submit();
    return false;
}

function selPerCount(bmuSid) {
    document.forms[0].editBmuSid.value = bmuSid;
    document.forms[0].returnPage.value = 'bmk010';

    document.forms[0].CMD.value='commentList';
    document.forms[0].submit();
    return false;
}