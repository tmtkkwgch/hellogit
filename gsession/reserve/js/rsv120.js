function buttonPush(cmd) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function changeEnableDisable() {
  var bool = false;
  if (document.forms[0].rsv120batchKbn[0].checked) {
    bool = true;
  }
  document.forms[0].rsv120year.disabled = bool;
  document.forms[0].rsv120month.disabled = bool;
  return;
}