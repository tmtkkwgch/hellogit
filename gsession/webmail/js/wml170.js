function changeEnableDisable() {

    var delKbn = 0;
    var delKbnVal = '';

    for (i = 0; i < document.forms[0].wml170delKbn.length; i++) {
        if (document.forms[0].wml170delKbn[i].checked) {
            delKbn = i;
        }
    }
    delKbnVal = document.forms[0].wml170delKbn[delKbn].value;

    if (delKbnVal == 0) {
        document.forms[0].wml170delYear.disabled = true;
        document.forms[0].wml170delYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].wml170delMonth.disabled = true;
        document.forms[0].wml170delMonth.style.backgroundColor = '#e0e0e0';
        document.forms[0].wml170delDay.disabled = true;
        document.forms[0].wml170delDay.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].wml170delYear.disabled = false;
        document.forms[0].wml170delYear.style.backgroundColor = '#ffffff';
        document.forms[0].wml170delMonth.disabled = false;
        document.forms[0].wml170delMonth.style.backgroundColor = '#ffffff';
        document.forms[0].wml170delDay.disabled = false;
        document.forms[0].wml170delDay.style.backgroundColor = '#ffffff';
    }

}

function setDispState(kbnElem, yearElem, monthElem, dayElem) {

    for (i = 0; i < kbnElem.length; i++) {
        if (kbnElem[i].checked) {
            batchKbn = i;
        }
    }
    batchKbnVal = kbnElem[batchKbn].value;

    if (batchKbnVal == 0) {
        yearElem.disabled = true;
        yearElem.style.backgroundColor = '#e0e0e0';
        monthElem.disabled = true;
        monthElem.style.backgroundColor = '#e0e0e0';
        dayElem.disabled = true;
        dayElem.style.backgroundColor = '#e0e0e0';

    } else {
        yearElem.disabled = false;
        yearElem.style.backgroundColor = '#ffffff';
        monthElem.disabled = false;
        monthElem.style.backgroundColor = '#ffffff';
        dayElem.disabled = false;
        dayElem.style.backgroundColor = '#ffffff';
    }
}