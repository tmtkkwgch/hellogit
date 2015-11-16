function changeEnableDisable() {

    var jbatchKbn = 0;
    var jbatchKbnVal = '';

    for (i = 0; i < document.forms[0].cir110JdelKbn.length; i++) {
        if (document.forms[0].cir110JdelKbn[i].checked == true) {
            jbatchKbn = i;
        }
    }
    jbatchKbnVal = document.forms[0].cir110JdelKbn[jbatchKbn].value;

    if (jbatchKbnVal == 0) {
        document.forms[0].cir110JYear.disabled = true;
        document.forms[0].cir110JYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].cir110JMonth.disabled = true;
        document.forms[0].cir110JMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].cir110JYear.disabled = false;
        document.forms[0].cir110JYear.style.backgroundColor = '#ffffff';
        document.forms[0].cir110JMonth.disabled = false;
        document.forms[0].cir110JMonth.style.backgroundColor = '#ffffff';
    }

    var sbatchKbn = 0;
    var sbatchKbnVal = '';

    for (i = 0; i < document.forms[0].cir110SdelKbn.length; i++) {
        if (document.forms[0].cir110SdelKbn[i].checked == true) {
            sbatchKbn = i;
        }
    }
    sbatchKbnVal = document.forms[0].cir110SdelKbn[sbatchKbn].value;

    if (sbatchKbnVal == 0) {
        document.forms[0].cir110SYear.disabled = true;
        document.forms[0].cir110SYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].cir110SMonth.disabled = true;
        document.forms[0].cir110SMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].cir110SYear.disabled = false;
        document.forms[0].cir110SYear.style.backgroundColor = '#ffffff';
        document.forms[0].cir110SMonth.disabled = false;
        document.forms[0].cir110SMonth.style.backgroundColor = '#ffffff';
    }

    var dbatchKbn = 0;
    var dbatchKbnVal = '';

    for (i = 0; i < document.forms[0].cir110DdelKbn.length; i++) {
        if (document.forms[0].cir110DdelKbn[i].checked == true) {
            dbatchKbn = i;
        }
    }
    dbatchKbnVal = document.forms[0].cir110DdelKbn[dbatchKbn].value;

    if (dbatchKbnVal == 0) {
        document.forms[0].cir110DYear.disabled = true;
        document.forms[0].cir110DYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].cir110DMonth.disabled = true;
        document.forms[0].cir110DMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].cir110DYear.disabled = false;
        document.forms[0].cir110DYear.style.backgroundColor = '#ffffff';
        document.forms[0].cir110DMonth.disabled = false;
        document.forms[0].cir110DMonth.style.backgroundColor = '#ffffff';
    }

//    changeDelKbn();
}

//function changeDelKbn() {
//
//    var delKbn = 0;
//    var delVal = '';
//
//    for (i = 0; i < document.forms[0].cir110DelKbn.length; i++) {
//        if (document.forms[0].cir110DelKbn[i].checked == true) {
//            delKbn = i;
//        }
//    }
//    delVal = document.forms[0].cir110DelKbn[delKbn].value;
//
//    if (delVal == 1) {
//
//        document.forms[0].cir110JdelKbn[0].disabled = true;
//        document.forms[0].cir110SdelKbn[0].disabled = true;
//        document.forms[0].cir110DdelKbn[0].disabled = true;
//        document.forms[0].cir110JdelKbn[1].disabled = true;
//        document.forms[0].cir110SdelKbn[1].disabled = true;
//        document.forms[0].cir110DdelKbn[1].disabled = true;
//
//        document.forms[0].cir110JYear.disabled = true;
//        document.forms[0].cir110JMonth.disabled = true;
//        document.forms[0].cir110SYear.disabled = true;
//        document.forms[0].cir110SMonth.disabled = true;
//        document.forms[0].cir110DYear.disabled = true;
//        document.forms[0].cir110DMonth.disabled = true;
//
//        document.forms[0].cir110JYear.style.backgroundColor = '#e0e0e0';
//        document.forms[0].cir110JMonth.style.backgroundColor = '#e0e0e0';
//        document.forms[0].cir110SYear.style.backgroundColor = '#e0e0e0';
//        document.forms[0].cir110SMonth.style.backgroundColor = '#e0e0e0';
//        document.forms[0].cir110DYear.style.backgroundColor = '#e0e0e0';
//        document.forms[0].cir110DMonth.style.backgroundColor = '#e0e0e0';
//
//    } else if (delVal == 0) {
//
//        document.forms[0].cir110JdelKbn[0].disabled = false;
//        document.forms[0].cir110JdelKbn[1].disabled = false;
//
//        if (document.forms[0].cir110JdelKbn[1].checked == true) {
//            document.forms[0].cir110JYear.disabled = false;
//            document.forms[0].cir110JMonth.disabled = false;
//            document.forms[0].cir110JYear.style.backgroundColor = '#ffffff';
//            document.forms[0].cir110JMonth.style.backgroundColor = '#ffffff';
//        }
//
//        document.forms[0].cir110SdelKbn[0].disabled = false;
//        document.forms[0].cir110SdelKbn[1].disabled = false;
//
//        if (document.forms[0].cir110SdelKbn[1].checked == true) {
//            document.forms[0].cir110SYear.disabled = false;
//            document.forms[0].cir110SMonth.disabled = false;
//            document.forms[0].cir110SYear.style.backgroundColor = '#ffffff';
//            document.forms[0].cir110SMonth.style.backgroundColor = '#ffffff';
//        }
//
//        document.forms[0].cir110DdelKbn[0].disabled = false;
//        document.forms[0].cir110DdelKbn[1].disabled = false;
//
//        if (document.forms[0].cir110DdelKbn[1].checked == true) {
//            document.forms[0].cir110DYear.disabled = false;
//            document.forms[0].cir110DMonth.disabled = false;
//            document.forms[0].cir110DYear.style.backgroundColor = '#ffffff';
//            document.forms[0].cir110DMonth.style.backgroundColor = '#ffffff';
//        }
//    }
//}

function submitStyleChange() {
    document.forms[0].cir110JdelKbn[0].disabled = false;
    document.forms[0].cir110SdelKbn[0].disabled = false;
    document.forms[0].cir110DdelKbn[0].disabled = false;
    document.forms[0].cir110JdelKbn[1].disabled = false;
    document.forms[0].cir110SdelKbn[1].disabled = false;
    document.forms[0].cir110DdelKbn[1].disabled = false;
    document.forms[0].cir110JYear.disabled=false;
    document.forms[0].cir110JMonth.disabled=false;
    document.forms[0].cir110SYear.disabled=false;
    document.forms[0].cir110SMonth.disabled=false;
    document.forms[0].cir110DYear.disabled=false;
    document.forms[0].cir110DMonth.disabled=false;
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