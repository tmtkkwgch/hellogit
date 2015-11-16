function changeSendServerAuth(auth) {
    document.getElementById('sendUser').disabled = (auth != 1);
    document.getElementById('sendPassword').disabled = (auth != 1);
}

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

function changeUrlKbn() {
    var kbn = Number($("input:radio[name=anp080UrlSetKbn]:checked").val());
    if (kbn == 0) {
        $('#baseUrlForm').hide();
        $('#baseUrlAuto').show();
    } else {
        $('#baseUrlForm').show();
        $('#baseUrlAuto').hide();
    }
}

$(function() {
    //SMTP認証
    var smtpObj = document.getElementsByName("anp080SmtpAuth");
    var smtpChk = 0;
    for (var i = 0; i< smtpObj.length; i++) {
        if (smtpObj[i].checked) {
            smtpChk = smtpObj[i].value;
            break;
        }
    }
    changeSendServerAuth(smtpChk);
    //URL
    changeUrlKbn()
});