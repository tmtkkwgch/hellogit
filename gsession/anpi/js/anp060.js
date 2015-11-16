function selectUsersList(chkObj, chkTargetName) {

    var flg = chkObj.checked;
    var defUserAry = document.forms[0].elements[chkTargetName].options;
    var defLength = defUserAry.length;
    for (i = defLength - 1; i >= 0; i--) {
        if (defUserAry[i].value != -1) {
            defUserAry[i].selected = flg;
        }
    }
}

function setfocus(){
    if (document.forms[0].anp060ScrollFlg.value == '1') {
        window.location.hash='label_sender';
    }
}

function setKnrenMode() {

    var checked = $('#knrenFlg').attr('checked');
    if (checked) {
        $('#lmtinput').show();
        $('#lmtinput2').show();
        $('#knren_top').show();
    } else {
        $('#lmtinput').hide();
        $('#lmtinput2').hide();
        $('#knren_top').hide();
    }
}

$(function() {
    setKnrenMode();
});