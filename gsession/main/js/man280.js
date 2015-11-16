function buttonPush(cmd){

    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function selectGroup() {
    document.forms[0].CMD.value = 'changeGrp';
    document.forms[0].submit();
    return false;
}

function selectUseKbn() {

    if ($('#useKbn1')[0].checked) {
        $('#pluginUseMember').show();
        $('#pluginUseMember2').show();
    } else {
        $('#pluginUseMember').hide();
        $('#pluginUseMember2').hide();
    }
}

function selectLimitType() {

    if ($('#limitType1')[0].checked) {
        $('#limit').hide();
        $('#permit').show();
        $('#limit2').hide();
        $('#permit2').show();

    } else {
        $('#limit').show();
        $('#permit').hide();
        $('#limit2').show();
        $('#permit2').hide();
    }
}

function defaultImg(imgName) {
    document.getElementById(imgName).src="../common/images/spacer.gif";
}
