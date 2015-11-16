
function changeGroupCombo() {
    document.forms[0].CMD.value='research';
    document.forms[0].man250SltUser.value='-1';
    document.forms[0].submit();
    return false;
}

function changePage1() {
    document.forms[0].CMD.value='research';
    for (i = 0; i < document.forms[0].man250SltPage1.length; i++) {
        if (document.forms[0].man250SltPage1[i].selected) {
            document.forms[0].man250SltPage2.value=document.forms[0].man250SltPage1[i].value;
            document.forms[0].man250PageNum.value=document.forms[0].man250SltPage1[i].value;
        }
    }
    document.forms[0].submit();
    return false;
}

function changePage2() {
    document.forms[0].CMD.value='research';
    for (i = 0; i < document.forms[0].man250SltPage2.length; i++) {
        if (document.forms[0].man250SltPage2[i].selected) {
            document.forms[0].man250SltPage1.value=document.forms[0].man250SltPage2[i].value;
            document.forms[0].man250PageNum.value=document.forms[0].man250SltPage2[i].value;
        }
    }
    document.forms[0].submit();
    return false;
}

function clickSortTitle(sortValue) {

    if (document.forms[0].man250SortKey1.value == sortValue) {

        if (document.forms[0].man250OrderKey1[0].checked == true) {
            document.forms[0].man250OrderKey1[0].checked = false;
            document.forms[0].man250OrderKey1[1].checked = true;
        } else {
            document.forms[0].man250OrderKey1[1].checked = false;
            document.forms[0].man250OrderKey1[0].checked = true;
        }
    } else {
        document.forms[0].man250SortKey1.value = sortValue;
    }
    return false;
}
function moveDay(frto, kbn) {

    var elmYear = null;
    var elmMonth = null;
    var elmDay = null;

    if (frto == 1) {
        elmYear = document.man250Form.man250SltYearFr;
        elmMonth = document.man250Form.man250SltMonthFr;
        elmDay = document.man250Form.man250SltDayFr;
    } else {
        elmYear = document.man250Form.man250SltYearTo;
        elmMonth = document.man250Form.man250SltMonthTo;
        elmDay = document.man250Form.man250SltDayTo;
    }

    systemDate = new Date();

    if (kbn == 2) {
        elmYear.value = convYear(systemDate.getYear());
        elmMonth.value = systemDate.getMonth() + 1;
        elmDay.value = systemDate.getDate();
        return;
    }

    if (kbn == 1 || kbn == 3) {

        var ymdf = escape(elmYear.value + '/' + elmMonth.value + "/" + elmDay.value);
        re = new RegExp(/(\d{4})\/(\d{1,2})\/(\d{1,2})/);
        if (ymdf.match(re)) {

            newDate = new Date(elmYear.value, elmMonth.value - 1, elmDay.value)

            if (kbn == 1) {
                newDate.setDate(newDate.getDate() - 1);
            } else if (kbn == 3) {
                newDate.setDate(newDate.getDate() + 1);
            }

            var newYear = convYear(newDate.getYear());
            var systemYear = convYear(systemDate.getYear());

            if (newYear < systemYear - 5 || newYear > systemYear + 5) {
                elmYear.value = '';
            } else {
                elmYear.value = newYear;
            }

            elmMonth.value = newDate.getMonth() + 1;
            elmDay.value = newDate.getDate();

        } else {

            if (elmYear.value == '' && elmMonth.value == '' && elmDay.value == '') {
                elmYear.value = convYear(systemDate.getYear());
                elmMonth.value = systemDate.getMonth() + 1;
                elmDay.value = systemDate.getDate();
            }
        }
    }
}

/**
 * �e��ʂɖ߂�ۂɃA�N�V�����ɃR�}���h��n���ꍇ
 * cmd �R�}���h
 */
function openGroupWindowForMan250(formOj, selBoxName, myGpFlg, cmd) {

    document.forms[0].man250SltUser.value='-1';
    if (cmd != "") {
        document.forms[0].CMD.value=cmd;
    }
    openGroup(formOj, selBoxName, myGpFlg, "");
    return;
}