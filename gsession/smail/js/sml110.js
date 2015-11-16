function changeEnableDisable() {
  var bool = false;
//  var ctext = $('#lmtinput')[0];

  if (document.forms[0].sml110MailForward[0].checked) {
    bool = true;
    document.forms[0].sml110SmtpUrl.value = '';
    document.forms[0].sml110SmtpPort.value = '';
    document.forms[0].sml110SmtpUser.value = '';
    document.forms[0].sml110SmtpPass.value = '';
    document.forms[0].sml110FromAddress.value = '';
    document.forms[0].sml110FwlmtTextArea.value = '';
    document.forms[0].sml110FwLmtKbn[0].disabled = bool;
    document.forms[0].sml110FwLmtKbn[1].disabled = bool;

  } else {
    document.forms[0].sml110FwLmtKbn[0].disabled = false;
    document.forms[0].sml110FwLmtKbn[1].disabled = false;
  }
  document.forms[0].sml110SmtpUrl.disabled = bool;
  document.forms[0].sml110SmtpPort.disabled = bool;
  document.forms[0].sml110SmtpUser.disabled = bool;
  document.forms[0].sml110SmtpPass.disabled = bool;
  document.forms[0].sml110FromAddress.disabled = bool;
//  changeStyle(ctext, 'sml_description_text_notdsp');
  $('#lmtinput').hide();

  document.forms[0].sml110FwLmtKbn[1].checked = true;


  return;
}

function initEnableDisable() {
  var bool = false;

  if (document.forms[0].sml110MailForward[0].checked) {
    bool = true;
    document.forms[0].sml110SmtpUrl.value = '';
    document.forms[0].sml110SmtpPort.value = '';
    document.forms[0].sml110SmtpUser.value = '';
    document.forms[0].sml110SmtpPass.value = '';
    document.forms[0].sml110FwLmtKbn[0].disabled = bool;
    document.forms[0].sml110FwLmtKbn[1].disabled = bool;
  } else {
    document.forms[0].sml110FwLmtKbn[0].disabled = false;
    document.forms[0].sml110FwLmtKbn[1].disabled = false;
  }
  document.forms[0].sml110SmtpUrl.disabled = bool;
  document.forms[0].sml110SmtpPort.disabled = bool;
  document.forms[0].sml110SmtpUser.disabled = bool;
  document.forms[0].sml110SmtpPass.disabled = bool;
  document.forms[0].sml110FromAddress.disabled = bool;
  return;
}

function lmtEnableDisable() {
//  var ctext = $('#lmtinput')[0];
      if (document.forms[0].sml110FwLmtKbn[0].checked) {
          $('#lmtinput').show();
//          changeStyle(ctext, 'sml_description_text_dsp');
      } else {
          $('#lmtinput').hide();
          document.forms[0].sml110FwlmtTextArea.value = '';
//          changeStyle(ctext, 'sml_description_text_notdsp');
      }
}
