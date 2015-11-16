function buttonPush(cmd) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function changeGroupCombo(){
    document.forms[0].CMD.value='rsv260_redsp';
    document.forms[0].submit();
    return false;
}

function changeShisetsuCombo(){
    document.forms[0].CMD.value='rsv260_redsp';
    document.forms[0].submit();
    return false;
}

function bodyLoad() {

    var admIdx = 0;
    for (i = 0; i < document.forms[0].rsv260GrpAdmKbn.length; i++) {
        if (document.forms[0].rsv260GrpAdmKbn[i].checked == true) {
            admIdx = i;
        }
    }
    var admVal = document.forms[0].rsv260GrpAdmKbn[admIdx].value;

    admChange(admVal);
}

function admChange(admKbn) {

    if (admKbn == '0') {
        document.forms[0].rsv260SelectedLeft.disabled=false;
        document.forms[0].rsv260SelectedGrpComboSid.disabled=false;
        document.forms[0].rsv260SelectedRight.disabled=false;
        document.forms[0].rsv260SelectedGrpComboSid.style.backgroundColor='#ffffff';
        document.forms[0].rsv260SelectedLeft.style.backgroundColor='#ffffff';
        document.forms[0].rsv260SelectedRight.style.backgroundColor='#ffffff';
    } else if (admKbn == '1') {
        document.forms[0].rsv260SelectedLeft.disabled=true;
        document.forms[0].rsv260SelectedGrpComboSid.disabled=true;
        document.forms[0].rsv260SelectedRight.disabled=true;
        document.forms[0].rsv260SelectedGrpComboSid.style.backgroundColor='#e0e0e0';
        document.forms[0].rsv260SelectedLeft.style.backgroundColor='#e0e0e0';
        document.forms[0].rsv260SelectedRight.style.backgroundColor='#e0e0e0';
    }
}