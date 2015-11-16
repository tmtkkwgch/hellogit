function changeEnableDisable() {

    var dustKbn = 0;
    var dustKbnVal = '';

    for (i = 0; i < document.forms[0].wml050dustKbn.length; i++) {
        if (document.forms[0].wml050dustKbn[i].checked == true) {
            dustKbn = i;
        }
    }
    dustKbnVal = document.forms[0].wml050dustKbn[dustKbn].value;

    if (dustKbnVal == 0 || dustKbnVal == 1) {
        document.forms[0].wml050dustYear.disabled = true;
        document.forms[0].wml050dustYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].wml050dustMonth.disabled = true;
        document.forms[0].wml050dustMonth.style.backgroundColor = '#e0e0e0';
        document.forms[0].wml050dustDay.disabled = true;
        document.forms[0].wml050dustDay.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].wml050dustYear.disabled = false;
        document.forms[0].wml050dustYear.style.backgroundColor = '#ffffff';
        document.forms[0].wml050dustMonth.disabled = false;
        document.forms[0].wml050dustMonth.style.backgroundColor = '#ffffff';
        document.forms[0].wml050dustDay.disabled = false;
        document.forms[0].wml050dustDay.style.backgroundColor = '#ffffff';
    }

    var sendKbn = 0;
    var sendKbnVal = '';

    for (i = 0; i < document.forms[0].wml050sendKbn.length; i++) {
        if (document.forms[0].wml050sendKbn[i].checked == true) {
            sendKbn = i;
        }
    }
    sendKbnVal = document.forms[0].wml050sendKbn[sendKbn].value;

    if (sendKbnVal == 0 || sendKbnVal == 1) {
        document.forms[0].wml050sendYear.disabled = true;
        document.forms[0].wml050sendYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].wml050sendMonth.disabled = true;
        document.forms[0].wml050sendMonth.style.backgroundColor = '#e0e0e0';
        document.forms[0].wml050sendDay.disabled = true;
        document.forms[0].wml050sendDay.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].wml050sendYear.disabled = false;
        document.forms[0].wml050sendYear.style.backgroundColor = '#ffffff';
        document.forms[0].wml050sendMonth.disabled = false;
        document.forms[0].wml050sendMonth.style.backgroundColor = '#ffffff';
        document.forms[0].wml050sendDay.disabled = false;
        document.forms[0].wml050sendDay.style.backgroundColor = '#ffffff';
    }

    var draftKbn = 0;
    var draftKbnVal = '';

    for (i = 0; i < document.forms[0].wml050draftKbn.length; i++) {
        if (document.forms[0].wml050draftKbn[i].checked == true) {
            draftKbn = i;
        }
    }
    draftKbnVal = document.forms[0].wml050draftKbn[draftKbn].value;

    if (draftKbnVal == 0 || draftKbnVal == 1) {
        document.forms[0].wml050draftYear.disabled = true;
        document.forms[0].wml050draftYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].wml050draftMonth.disabled = true;
        document.forms[0].wml050draftMonth.style.backgroundColor = '#e0e0e0';
        document.forms[0].wml050draftDay.disabled = true;
        document.forms[0].wml050draftDay.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].wml050draftYear.disabled = false;
        document.forms[0].wml050draftYear.style.backgroundColor = '#ffffff';
        document.forms[0].wml050draftMonth.disabled = false;
        document.forms[0].wml050draftMonth.style.backgroundColor = '#ffffff';
        document.forms[0].wml050draftDay.disabled = false;
        document.forms[0].wml050draftDay.style.backgroundColor = '#ffffff';
    }

    var resvKbn = 0;
    var resvKbnVal = '';

    for (i = 0; i < document.forms[0].wml050resvKbn.length; i++) {
        if (document.forms[0].wml050resvKbn[i].checked == true) {
            resvKbn = i;
        }
    }
    resvKbnVal = document.forms[0].wml050resvKbn[resvKbn].value;

    if (resvKbnVal == 0 || resvKbnVal == 1) {
        document.forms[0].wml050resvYear.disabled = true;
        document.forms[0].wml050resvYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].wml050resvMonth.disabled = true;
        document.forms[0].wml050resvMonth.style.backgroundColor = '#e0e0e0';
        document.forms[0].wml050resvDay.disabled = true;
        document.forms[0].wml050resvDay.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].wml050resvYear.disabled = false;
        document.forms[0].wml050resvYear.style.backgroundColor = '#ffffff';
        document.forms[0].wml050resvMonth.disabled = false;
        document.forms[0].wml050resvMonth.style.backgroundColor = '#ffffff';
        document.forms[0].wml050resvDay.disabled = false;
        document.forms[0].wml050resvDay.style.backgroundColor = '#ffffff';
    }

    var keepKbn = 0;
    var keepKbnVal = '';

    for (i = 0; i < document.forms[0].wml050keepKbn.length; i++) {
        if (document.forms[0].wml050keepKbn[i].checked == true) {
            keepKbn = i;
        }
    }
    keepKbnVal = document.forms[0].wml050keepKbn[keepKbn].value;

    if (keepKbnVal == 0 || keepKbnVal == 1) {
        document.forms[0].wml050keepYear.disabled = true;
        document.forms[0].wml050keepYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].wml050keepMonth.disabled = true;
        document.forms[0].wml050keepMonth.style.backgroundColor = '#e0e0e0';
        document.forms[0].wml050keepDay.disabled = true;
        document.forms[0].wml050keepDay.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].wml050keepYear.disabled = false;
        document.forms[0].wml050keepYear.style.backgroundColor = '#ffffff';
        document.forms[0].wml050keepMonth.disabled = false;
        document.forms[0].wml050keepMonth.style.backgroundColor = '#ffffff';
        document.forms[0].wml050keepDay.disabled = false;
        document.forms[0].wml050keepDay.style.backgroundColor = '#ffffff';
    }
}

function setDispState(kbnElem, yearElem, monthElem, dayElem) {

    for (i = 0; i < kbnElem.length; i++) {
        if (kbnElem[i].checked == true) {
            batchKbn = i;
        }
    }
    batchKbnVal = kbnElem[batchKbn].value;

    if (batchKbnVal == 0 || batchKbnVal == 1) {
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