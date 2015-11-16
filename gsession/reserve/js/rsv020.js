function buttonPush(cmd) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function changeGroupCombo(){
    document.forms[0].CMD.value='';
    document.forms[0].submit();
    return false;
}

function moveGekkanSchedule(sid){
    document.forms[0].CMD.value='gekkan';
    document.forms[0].rsvSelectedSisetuSid.value=sid;
    document.forms[0].submit();
    return false;
}

function moveSisetuAdd(ymd, sisetuSid) {

    document.forms[0].CMD.value='sisetu_add';
    document.forms[0].rsvSelectedDate.value=ymd;
    document.forms[0].rsvSelectedSisetuSid.value=sisetuSid;
    document.forms[0].submit();
    return false;
}

function moveSisetuEdit(yoyakuSid) {

    document.forms[0].CMD.value='sisetu_edit';
    document.forms[0].rsvSelectedYoyakuSid.value=yoyakuSid;
    document.forms[0].submit();
    return false;
}

function moveItiran(cmd){
    document.forms[0].CMD.value='riyo_zyokyo_syokai';
    document.forms[0].rsvSelectedSisetuSid.value=-1;
    document.forms[0].submit();
    return false;
}

function init() {
    var keyList = document.getElementsByName('rsvIkkatuTorokuKey');
    if (keyList != null && keyList.length > 0) {
        for (i = 0; i < keyList.length; i++) {
            if (keyList[i].checked) {
                var key = keyList[i].value;
                document.getElementById(key).className='td_type_selected_reserve';
            }
        }
    }
}

function backGroundSetting(key, typeNo) {
    if (key.checked) {
        document.getElementById(key.value).className='td_type_selected_reserve';
    } else {
        var cssName = 'td_type' + typeNo;
        document.getElementById(key.value).className=cssName;
    }
}

function clearSisetu(sisetuSid) {
    document.forms[0].CMD.value='clearHidSisetu';
    document.forms[0].rsv020ClearTargetKey.value = sisetuSid;
    document.forms[0].submit();
    return false;
}

function keyPress(keycode) {
    if (keycode == 13) {
        document.forms[0].CMD.value='gotosearch';
        document.forms[0].submit();
        return false;
    }
}

function resetCmd() {
    document.forms[0].CMD.value='';
}