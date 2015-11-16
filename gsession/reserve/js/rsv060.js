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

function bodyLoad() {

    var admIdx = 0;
    for (i = 0; i < document.forms[0].rsv060GrpAdmKbn.length; i++) {
        if (document.forms[0].rsv060GrpAdmKbn[i].checked == true) {
            admIdx = i;
        }
    }
    var admVal = document.forms[0].rsv060GrpAdmKbn[admIdx].value;

    admChange(admVal);
}

function admChange(admKbn) {

    if (admKbn == '0') {
        document.forms[0].rsv060SelectedLeft.disabled=false;
        document.forms[0].rsv060SelectedGrpComboSid.disabled=false;
        document.forms[0].rsv060SelectedRight.disabled=false;
        document.forms[0].rsv060SelectedGrpComboSid.style.backgroundColor='#ffffff';
        document.forms[0].rsv060SelectedLeft.style.backgroundColor='#ffffff';
        document.forms[0].rsv060SelectedRight.style.backgroundColor='#ffffff';

        document.forms[0].rsv060apprKbn[0].disabled=false;
        document.forms[0].rsv060apprKbn[1].disabled=false;
    } else if (admKbn == '1') {
        document.forms[0].rsv060SelectedLeft.disabled=true;
        document.forms[0].rsv060SelectedGrpComboSid.disabled=true;
        document.forms[0].rsv060SelectedRight.disabled=true;
        document.forms[0].rsv060SelectedGrpComboSid.style.backgroundColor='#e0e0e0';
        document.forms[0].rsv060SelectedLeft.style.backgroundColor='#e0e0e0';
        document.forms[0].rsv060SelectedRight.style.backgroundColor='#e0e0e0';

        document.forms[0].rsv060apprKbn[0].disabled=true;
        document.forms[0].rsv060apprKbn[1].disabled=true;
        document.forms[0].rsv060apprKbn[0].checked=false;
        document.forms[0].rsv060apprKbn[1].checked=true;
    }
}

function showOrHide(){
  if (document.forms[0].rsv060AccessDspFlg.value == 'true') {
      showText();
  } else {
      hideText();
  }
}

function changeShowOrHide(){
  if (document.forms[0].rsv060AccessDspFlg.value == 'true') {
      hideText();
      document.forms[0].rsv060AccessDspFlg.value = false;
  } else {
      showText();
      document.forms[0].rsv060AccessDspFlg.value = true;
  }
}

function showText(){
    var item1 = $('#show0');
    var item2 = $('#text1');
    var item3 = $('#text2');
    item1.show();
    item2.show();
    item3.hide();
}

function hideText(){
    var item1 = $('#show0');
    var item2 = $('#text1');
    var item3 = $('#text2');
    item1.hide();
    item2.hide();
    item3.show();
}

function showOrHideText(kbn){
  if (kbn == 0) {
      hideText2();
  } else {
      showText2();
  }
}

function changeShowOrHideText(kbn){
  if (kbn == 0) {
      hideText2();
  } else {
      showText2();
  }
}

function showText2(){
    var item1 = $('#showText0');
    var item2 = $('#showText1');
    var item3 = $('#showText2');
    var item4 = $('#showText3');
    var item5 = $('#showBtn0');
    var item6 = $('#showBtn1');
    item1.show();
    item2.hide();
    item3.show();
    item4.hide();
    item5.attr("style","display:none");
    item5.attr('disabled','disabled');
    item6.attr("style","display:block");
    item6.attr('disabled','');
}

function hideText2(){
    var item1 = $('#showText0');
    var item2 = $('#showText1');
    var item3 = $('#showText2');
    var item4 = $('#showText3');
    var item5 = $('#showBtn0');
    var item6 = $('#showBtn1');
    item1.hide();
    item2.show();
    item3.hide();
    item4.show();
    item5.attr("style","display:block");
    item5.attr('disabled','');
    item6.attr("style","display:none");
    item6.attr('disabled','disabled');
}
