function ptlmoveCreateMsg(cmd, uid, formId){
    var frm = document.getElementById(formId);
    frm.CMD.value=cmd;
    frm.zskSelectUsrSid.value=uid;
    frm.submit();
    return false;
}

/* Discription disp and hide */
function ptldispDescriptionZsk(fdid) {
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

function ptlzskeditSchedule(cmd, sid, uid, formId){
    var frm = document.getElementById(formId);
    frm.CMD.value=cmd;
    frm.zskSelectUsrSid.value=uid;
    frm.zskSelectSchSid.value=sid;
    frm.submit();
    return false;
}

function ptlzskaddSchedule(cmd, uid, formId){
    var frm = document.getElementById(formId);
    frm.CMD.value=cmd;
    frm.zskSelectUsrSid.value=uid;
    frm.submit();
    return false;
}
