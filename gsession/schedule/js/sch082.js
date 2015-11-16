function changeDisable() {
  document.forms[0].sch082AtdelYear.disabled = true;
  document.forms[0].sch082AtdelMonth.disabled = true;
  return false;
}

function changeEnable() {
  document.forms[0].sch082AtdelYear.disabled = false;
  document.forms[0].sch082AtdelMonth.disabled = false;

  return false;
}

function initSetting() {
  if (document.forms[0].sch082AtdelFlg[0].checked) {
    changeDisable();
  } else {
    changeEnable();
  }
  return false;
}
