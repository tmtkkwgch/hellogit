function sort(sortKey, orderKey) {
    getForm().CMD.value = 'init';
    getForm().wml070sortKey.value = sortKey;
    getForm().wml070order.value = orderKey;
    getForm().submit();
    return false;
}

function changePage(id){
    if (id == 1) {
        document.forms[0].wml070pageBottom.value = document.forms[0].wml070pageTop.value;
    } else {
        document.forms[0].wml070pageTop.value = document.forms[0].wml070pageBottom.value;
    }

    document.forms[0].CMD.value='pageChange';
    document.forms[0].submit();
    return false;
}

function sort(sortKey, orderKey) {
    document.forms[0].CMD.value = 'init';
    document.forms[0].wml070sortKey.value = sortKey;
    document.forms[0].wml070order.value = orderKey;
    document.forms[0].submit();
    return false;
}

function openDetail(mailNum) {
    var detailWidth = 700;
    var detailHeight = 500

    window.open('../webmail/wml080.do?wml080mailNum=' + mailNum, '_blank', 'width=' + detailWidth
              + ',height=' + detailHeight + ',titlebar=no,toolbar=no,scrollbars=yes'
              + ', left=' + getWml070CenterX(detailWidth) + ', top=' + getWml070CenterY(detailHeight));
    return false;
}

function getWml070CenterX(winWidth) {
  var x = (screen.width - winWidth) / 2;
  return x;
}

function getWml070CenterY(winHeight) {
  var y = (screen.height - winHeight) / 2;
  return y;
}

function setSearchDateView(type) {
    if (type == 4) {
        $('#wml070FromDate').show();
        $('#wml070ToDate').show();
    } else {
        $('#wml070FromDate').hide();
        $('#wml070ToDate').hide();
    }
}

function computeDate(year, month, day, addDays) {
    var screenDate = new Date(year, month - 1, day);
    screenDate.setTime(screenDate.getTime() + (addDays * 86400000));
    return screenDate;
}

function wml070prevDate(type) {
    var year = getComboValue(getSearchYearCombo(type));
    var month = getComboValue(getSearchMonthCombo(type));
    var day = getComboValue(getSearchDayCombo(type));

    var screenDate = computeDate(year, month, day, -1);
    setDateCombo(type, screenDate);
}

function wml070today(type) {
    setDateCombo(type, new Date());
}

function wml070nextDate(type) {
    var year = getComboValue(getSearchYearCombo(type));
    var month = getComboValue(getSearchMonthCombo(type));
    var day = getComboValue(getSearchDayCombo(type));

    var screenDate = computeDate(year, month, day, 1);
    setDateCombo(type, screenDate);
}

function getComboValue(combo) {
    for (i = 0; i < combo.options.length; i++) {
        if (combo.options[i].selected) {
            return combo.options[i].value;
        }
    }
}

function setComboSelected(combo, selectValue) {
    for (i = 0; i < combo.options.length; i++) {
        combo.options[i].selected = (combo.options[i].value == selectValue);
    }
}

function getSearchYearCombo(type) {
   if (type == 1) {
       return document.getElementsByName('wml070SendDateYearTo')[0];
   }

   return document.getElementsByName('wml070SendDateYear')[0];
}

function getSearchMonthCombo(type) {
   if (type == 1) {
       return document.getElementsByName('wml070SendDateMonthTo')[0];
   }

   return document.getElementsByName('wml070SendDateMonth')[0];
}

function getSearchDayCombo(type) {
   if (type == 1) {
       return document.getElementsByName('wml070SendDateDayTo')[0];
   }

   return document.getElementsByName('wml070SendDateDay')[0];
}

function setYearCombo(type, year) {
    setComboSelected(getSearchYearCombo(type), year);
}

function setMonthCombo(type, month) {
    setComboSelected(getSearchMonthCombo(type), month);
}

function setDayCombo(type, day) {
    setComboSelected(getSearchDayCombo(type), day);
}

function setDateCombo(type, date) {
    setYearCombo(type, date.getFullYear());
    setMonthCombo(type, date.getMonth() + 1);
    setDayCombo(type, date.getDate());
}
