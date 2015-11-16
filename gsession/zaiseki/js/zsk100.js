function enableDisable() {

  if (document.forms[0].zsk100maingrpDspFlg[1].checked) {
    document.forms[0].zsk100DspGpSid.disabled = true;
    document.forms[0].zsk100SortOrder1[0].disabled = true;
    document.forms[0].zsk100SortOrder1[1].disabled = true;
    document.forms[0].zsk100SortOrder2[0].disabled = true;
    document.forms[0].zsk100SortOrder2[1].disabled = true;
    document.forms[0].zsk100SortKey1.disabled = true;
    document.forms[0].zsk100SortKey2.disabled = true;
    document.forms[0].zsk100SchViewDf[0].disabled = true;
    document.forms[0].zsk100SchViewDf[1].disabled = true;

    document.forms[0].zsk100SortOrder1[0].checked = true;
    document.forms[0].zsk100SortOrder2[0].checked = true;
    document.forms[0].zsk100SortKey1.value = 1;
    document.forms[0].zsk100SortKey2.value = 1;
    document.forms[0].zsk100SchViewDf[0].checked = true;

  } else {
    document.forms[0].zsk100DspGpSid.disabled = false;
    document.forms[0].zsk100SortOrder1[0].disabled = false;
    document.forms[0].zsk100SortOrder1[1].disabled = false;
    document.forms[0].zsk100SortOrder2[0].disabled = false;
    document.forms[0].zsk100SortOrder2[1].disabled = false;
    document.forms[0].zsk100SortKey1.disabled = false;
    document.forms[0].zsk100SortKey2.disabled = false;
    document.forms[0].zsk100SchViewDf[0].disabled = false;
    document.forms[0].zsk100SchViewDf[1].disabled = false;

  }
  return;
}
