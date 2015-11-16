function buttonPush(cmd){
    document.forms[0].cmd.value=cmd;
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function changeGroupCombo(){
    document.forms[0].cmd.value='';
    document.forms[0].CMD.value='';
    document.forms[0].submit();
    return false;
}

function addNippou(cmd, ymd, uid, ukbn){
    document.forms[0].cmd.value=cmd;
    document.forms[0].CMD.value=cmd;
    document.forms[0].ntp010SelectDate.value=ymd;
    document.forms[0].ntp010SelectUsrSid.value=uid;
    document.forms[0].ntp010SelectUsrKbn.value=ukbn;
    document.forms[0].submit();
    return false;
}

function editNippou(cmd, ymd, sid, uid, ukbn){
    document.forms[0].cmd.value=cmd;
    document.forms[0].CMD.value=cmd;
    document.forms[0].ntp010SelectDate.value=ymd;
    document.forms[0].ntp010SelectUsrSid.value=uid;
    document.forms[0].ntp010SelectUsrKbn.value=0;
    document.forms[0].ntp010NipSid.value=sid;
    document.forms[0].submit();
    return false;
}

function moveMonthNippou(cmd, uid, ukbn){
    document.forms[0].cmd.value=cmd;
    document.forms[0].CMD.value=cmd;
    document.forms[0].ntp010SelectUsrSid.value=uid;
    document.forms[0].ntp010SelectUsrKbn.value=ukbn;
    document.forms[0].submit();
    return false;
}

function moveDailyNippou(cmd, ymd, uid, ukbn){
    document.forms[0].cmd.value=cmd;
    document.forms[0].CMD.value=cmd;
    document.forms[0].ntp010SelectUsrSid.value=uid;
    document.forms[0].ntp010SelectUsrKbn.value=ukbn;
    document.forms[0].ntp010SelectDate.value=ymd;
    document.forms[0].ntp010DspDate.value=ymd;
    document.forms[0].submit();
    return false;
}

function moveCreateMsg(cmd, uid, ukbn){
    document.forms[0].cmd.value=cmd;
    document.forms[0].CMD.value=cmd;
    document.forms[0].ntp010SelectUsrSid.value=uid;
    document.forms[0].ntp010SelectUsrKbn.value=ukbn;
    document.forms[0].submit();
    return false;
}

function setZaiseki(uid){
    document.forms[0].CMD.value='ntp010Zaiseki';
    document.forms[0].ntp010SelectUsrSid.value=uid;
    document.forms[0].submit();
    return false;
}

function setFuzai(uid){
    document.forms[0].CMD.value='ntp010Fuzai';
    document.forms[0].ntp010SelectUsrSid.value=uid;
    document.forms[0].submit();
    return false;
}

function setSonota(uid){
    document.forms[0].CMD.value='ntp010Sonota';
    document.forms[0].ntp010SelectUsrSid.value=uid;
    document.forms[0].submit();
    return false;
}
function moveListNippou(cmd, uid, ukbn){
    document.forms[0].cmd.value=cmd;
    document.forms[0].CMD.value=cmd;
    document.forms[0].ntp010SelectUsrSid.value=uid;
    document.forms[0].ntp010SelectUsrKbn.value=ukbn;
    document.forms[0].submit();
    return false;
}
function keyPress(keycode) {
    if (keycode == 13) {
        document.forms[0].CMD.value='search';
        document.forms[0].submit();
        return false;
    }
}
function backGroundSetting(key, typeNo) {
    if (key.checked) {
        document.getElementById(key.value).className='td_type_selected';
    } else {
        var cssName = 'td_type' + typeNo;
        document.getElementById(key.value).className=cssName;
    }
}

function setToUser() {

        window.location.hash='USR_AREA_' + document.forms[0].ntp010SelectUsrAreaSid.value;

}

$(function() {

    $(".sml_send_link").live("click", function(){

        $('#smlCreateArea').children().remove();
        $('#smlCreateArea').append('<iframe src=\"../smail/sml010.do?sml010scriptFlg=1&sml010scriptKbn=2&sml010scriptSelUsrSid='
                                   + $(this).attr('id')
                                   + '\" name=\"sample\" width=\"1000\" height=\"690\"></iframe>');
        /* ショートメールダイアログポップアップ */
        $('#smlPop').dialog({
            autoOpen: true,  // hide dialog
            bgiframe: true,   // for IE6
            resizable: false,
            height: 768,
            width: 1024,
            modal: true,
            overlay: {
              backgroundColor: '#000000',
              opacity: 0.5
            },
            buttons: {
              閉じる: function() {
                  $(this).dialog('close');
              }
            }
        });

    });

});


function callSmailWindowClose() {
    $('#smlPop').dialog('close');
}