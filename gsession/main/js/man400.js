function changeEnableDisable() {
  if ($('input[name="man400CtrlFlgWml"]').val() == 'true') {
    var wmlKbnVal = getRadioValue('man400WmlKbn');
    if (wmlKbnVal == 0) {
        document.forms[0].man400WmlYear.disabled = true;
        document.forms[0].man400WmlYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].man400WmlMonth.disabled = true;
        document.forms[0].man400WmlMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].man400WmlYear.disabled = false;
        document.forms[0].man400WmlYear.style.backgroundColor = '#ffffff';
        document.forms[0].man400WmlMonth.disabled = false;
        document.forms[0].man400WmlMonth.style.backgroundColor = '#ffffff';
    }
  }

  if ($('input[name="man400CtrlFlgSml"]').val() == 'true') {
    var smlKbnVal = getRadioValue('man400SmlKbn');
    if (smlKbnVal == 0) {
        document.forms[0].man400SmlYear.disabled = true;
        document.forms[0].man400SmlYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].man400SmlMonth.disabled = true;
        document.forms[0].man400SmlMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].man400SmlYear.disabled = false;
        document.forms[0].man400SmlYear.style.backgroundColor = '#ffffff';
        document.forms[0].man400SmlMonth.disabled = false;
        document.forms[0].man400SmlMonth.style.backgroundColor = '#ffffff';
    }
  }

  if ($('input[name="man400CtrlFlgCir"]').val() == 'true') {
    var cirKbnVal = getRadioValue('man400CirKbn');
    if (cirKbnVal == 0) {
        document.forms[0].man400CirYear.disabled = true;
        document.forms[0].man400CirYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].man400CirMonth.disabled = true;
        document.forms[0].man400CirMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].man400CirYear.disabled = false;
        document.forms[0].man400CirYear.style.backgroundColor = '#ffffff';
        document.forms[0].man400CirMonth.disabled = false;
        document.forms[0].man400CirMonth.style.backgroundColor = '#ffffff';
    }
  }

  if ($('input[name="man400CtrlFlgFil"]').val() == 'true') {
    var filKbnVal = getRadioValue('man400FilKbn');
    if (filKbnVal == 0) {
        document.forms[0].man400FilYear.disabled = true;
        document.forms[0].man400FilYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].man400FilMonth.disabled = true;
        document.forms[0].man400FilMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].man400FilYear.disabled = false;
        document.forms[0].man400FilYear.style.backgroundColor = '#ffffff';
        document.forms[0].man400FilMonth.disabled = false;
        document.forms[0].man400FilMonth.style.backgroundColor = '#ffffff';
    }
  }

  if ($('input[name="man400CtrlFlgBbs"]').val() == 'true') {
    var bbsKbnVal = getRadioValue('man400BbsKbn');
    if (bbsKbnVal == 0) {
        document.forms[0].man400BbsYear.disabled = true;
        document.forms[0].man400BbsYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].man400BbsMonth.disabled = true;
        document.forms[0].man400BbsMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].man400BbsYear.disabled = false;
        document.forms[0].man400BbsYear.style.backgroundColor = '#ffffff';
        document.forms[0].man400BbsMonth.disabled = false;
        document.forms[0].man400BbsMonth.style.backgroundColor = '#ffffff';
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