function changeDisable() {
  document.forms[0].bbs120AtdelYear.disabled = true;
  document.forms[0].bbs120AtdelMonth.disabled = true;
  return false;
}

function changeEnable() {
  document.forms[0].bbs120AtdelYear.disabled = false;
  document.forms[0].bbs120AtdelMonth.disabled = false;

  return false;
}

function initSetting() {
  if (document.forms[0].bbs120AtdelFlg[0].checked) {
    changeDisable();
  } else {
    changeEnable();
  }
  return false;
}
