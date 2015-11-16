function moveCreateMsg(cmd, uid){
    document.forms['zskmaingrpForm'].CMD.value=cmd;
    document.forms['zskmaingrpForm'].zskSelectUsrSid.value=uid;
    document.forms['zskmaingrpForm'].submit();
    return false;
}

/* Discription disp and hide */
function dispDescriptionZsk(fdid) {
  var ctext = $('#' + fdid)[0];
  var buttonName = '#resesSwitch' + fdid;

  if (ctext.className == 'zsk_description_text_dsp') {
      $(ctext).addClass('zsk_description_text_notdsp');
      $(ctext).removeClass('zsk_description_text_dsp');
      $(buttonName)[0].src="../zaiseki/images/btn_sch_dsp.gif";
      $(buttonName)[0].alt='schedule show';
  } else {
      $(ctext).addClass('zsk_description_text_dsp');
      $(ctext).removeClass('zsk_description_text_notdsp');
      $(buttonName)[0].src="../zaiseki/images/btn_sch_not_dsp.gif";
      $(buttonName)[0].alt='schedule hide';
  }
}

function zskeditSchedule(cmd, sid, uid){
    document.forms['zskmaingrpForm'].CMD.value=cmd;
    document.forms['zskmaingrpForm'].zskSelectUsrSid.value=uid;
    document.forms['zskmaingrpForm'].zskSelectSchSid.value=sid;
    document.forms['zskmaingrpForm'].submit();
    return false;
}

function zskaddSchedule(cmd, uid){
    document.forms['zskmaingrpForm'].CMD.value=cmd;
    document.forms['zskmaingrpForm'].zskSelectUsrSid.value=uid;
    document.forms['zskmaingrpForm'].submit();
    return false;
}

function zskPriConf(cmd){
    document.forms['zskmaingrpForm'].CMD.value=cmd;
    document.forms['zskmaingrpForm'].submit();
    return false;
}

function updateZskGrp(cmd) {
  var url = '../zaiseki/zskmaingrp.do';
  document.forms[0].CMD.value=cmd;
  var pars = '';//getHidden();
  pars= '?CMD=' + cmd;


  var formNode;

  formNode = document.forms['zskmaingrpForm'];

  if (formNode.zaiGrpSid == undefined) {
    formNode = document.zskmaingrpForm[0];
  }

  pars= pars + '&' + 'zaiGrpSid=' + formNode.zaiGrpSid.value;
  url= url + pars;
  $('#zaiseki_zskmain_div').load(url);
  return false;
}