function changeEnableDisable() {
  if ($('input[name="man410CtrlFlgWml"]').val() == 'true') {
    var wmlKbnVal = getRadioValue('man410WmlKbn');
    if (wmlKbnVal == 0) {
        document.forms[0].man410WmlYear.disabled = true;
        document.forms[0].man410WmlYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].man410WmlMonth.disabled = true;
        document.forms[0].man410WmlMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].man410WmlYear.disabled = false;
        document.forms[0].man410WmlYear.style.backgroundColor = '#ffffff';
        document.forms[0].man410WmlMonth.disabled = false;
        document.forms[0].man410WmlMonth.style.backgroundColor = '#ffffff';
    }
  }

  if ($('input[name="man410CtrlFlgSml"]').val() == 'true') {
    var smlKbnVal = getRadioValue('man410SmlKbn');
    if (smlKbnVal == 0) {
        document.forms[0].man410SmlYear.disabled = true;
        document.forms[0].man410SmlYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].man410SmlMonth.disabled = true;
        document.forms[0].man410SmlMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].man410SmlYear.disabled = false;
        document.forms[0].man410SmlYear.style.backgroundColor = '#ffffff';
        document.forms[0].man410SmlMonth.disabled = false;
        document.forms[0].man410SmlMonth.style.backgroundColor = '#ffffff';
    }
  }

  if ($('input[name="man410CtrlFlgCir"]').val() == 'true') {
    var cirKbnVal = getRadioValue('man410CirKbn');
    if (cirKbnVal == 0) {
        document.forms[0].man410CirYear.disabled = true;
        document.forms[0].man410CirYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].man410CirMonth.disabled = true;
        document.forms[0].man410CirMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].man410CirYear.disabled = false;
        document.forms[0].man410CirYear.style.backgroundColor = '#ffffff';
        document.forms[0].man410CirMonth.disabled = false;
        document.forms[0].man410CirMonth.style.backgroundColor = '#ffffff';
    }
  }

  if ($('input[name="man410CtrlFlgFil"]').val() == 'true') {
    var filKbnVal = getRadioValue('man410FilKbn');
    if (filKbnVal == 0) {
        document.forms[0].man410FilYear.disabled = true;
        document.forms[0].man410FilYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].man410FilMonth.disabled = true;
        document.forms[0].man410FilMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].man410FilYear.disabled = false;
        document.forms[0].man410FilYear.style.backgroundColor = '#ffffff';
        document.forms[0].man410FilMonth.disabled = false;
        document.forms[0].man410FilMonth.style.backgroundColor = '#ffffff';
    }
  }

  if ($('input[name="man410CtrlFlgBbs"]').val() == 'true') {
    var bbsKbnVal = getRadioValue('man410BbsKbn');
    if (bbsKbnVal == 0) {
        document.forms[0].man410BbsYear.disabled = true;
        document.forms[0].man410BbsYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].man410BbsMonth.disabled = true;
        document.forms[0].man410BbsMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].man410BbsYear.disabled = false;
        document.forms[0].man410BbsYear.style.backgroundColor = '#ffffff';
        document.forms[0].man410BbsMonth.disabled = false;
        document.forms[0].man410BbsMonth.style.backgroundColor = '#ffffff';
    }
  }
}

function getRadioValue(paramName) {
  return $('input[name="' + paramName + '"]:checked').val();
}

function setDispState(kbnElem, yearElem, monthElem) {

    for (i = 0; i < kbnElem.length; i++) {
        if (kbnElem[i].checked == true) {
            batchKbn = i;
        }
    }
    batchKbnVal = kbnElem[batchKbn].value;

    if (batchKbnVal == 0) {
        yearElem.disabled = true;
        yearElem.style.backgroundColor = '#e0e0e0';
        monthElem.disabled = true;
        monthElem.style.backgroundColor = '#e0e0e0';

    } else {
        yearElem.disabled = false;
        yearElem.style.backgroundColor = '#ffffff';
        monthElem.disabled = false;
        monthElem.style.backgroundColor = '#ffffff';
    }
}