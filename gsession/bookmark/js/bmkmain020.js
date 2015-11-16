function bmkmain020buttonPush(cmd){
    document.forms['bmkmain020Form'].CMD.value=cmd;
    document.forms['bmkmain020Form'].returnPage.value = 'main';
    document.forms['bmkmain020Form'].submit();
    return false;
}

function bmkmain020buttonPushAdd(bmuSid) {
    document.forms['bmkmain020Form'].procMode.value = 0;
    document.forms['bmkmain020Form'].editBmuSid.value = bmuSid;
    document.forms['bmkmain020Form'].returnPage.value = 'main';

    document.forms['bmkmain020Form'].CMD.value='bmkmain020add';
    document.forms['bmkmain020Form'].submit();
    return false;
}

function bmkmain020selPerCount(bmuSid) {
    document.forms['bmkmain020Form'].editBmuSid.value = bmuSid;
    document.forms['bmkmain020Form'].returnPage.value = 'main';

    document.forms['bmkmain020Form'].CMD.value='bmkmain020cmt';
    document.forms['bmkmain020Form'].submit();
    return false;
}