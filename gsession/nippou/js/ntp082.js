function changeDisable() {
  document.forms[0].ntp082AtdelYear.disabled = true;
  document.forms[0].ntp082AtdelMonth.disabled = true;
  return false;
}

function changeEnable() {
  document.forms[0].ntp082AtdelYear.disabled = false;
  document.forms[0].ntp082AtdelMonth.disabled = false;

  return false;
}

function initSetting() {
  if (document.forms[0].ntp082AtdelFlg[0].checked) {
    changeDisable();
  } else {
    changeEnable();
  }
  return false;
}