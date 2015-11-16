$(function() {
    /* 画面切り替え  hover*/
    $('.td_todo_add_not_sel').hover(
        function () {
            $(this).addClass("td_todo_add_not_sel_on");
        },
        function () {
            $(this).removeClass("td_todo_add_not_sel_on");
        }
    );
});


function clearDate(day, month, year){
    $('#' + day)[0].value = '';
    $('#' + month)[0].value = '';
    $('#' + year)[0].value = '';
}

function moveDay(elmYear, elmMonth, elmDay, kbn, itemKbn) {

    systemDate = new Date();

    if (kbn == 2) {
        $(elmYear).val(convYear(systemDate.getYear()));
        $(elmMonth).val(systemDate.getMonth() + 1);
        $(elmDay).val(systemDate.getDate());

        if (itemKbn == 0) {
            setToDayYotei();
        } else if (itemKbn == 1) {
            setToDayJisseki();
        }

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
                $(elmYear).val('');
            } else {
                $(elmYear).val(newYear);
            }
            $(elmMonth).val(newDate.getMonth() + 1);
            $(elmDay).val(newDate.getDate());

        } else {

            if (elmYear.value == '' && elmMonth.value == '' && elmDay.value == '') {
                $(elmYear).val(convYear(systemDate.getYear()));
                $(elmMonth).val(systemDate.getMonth() + 1);
                $(elmDay).val(systemDate.getDate());
            }
        }
    }

    if (itemKbn == 0) {
        setToDayYotei();
    } else if (itemKbn == 1) {
        setToDayJisseki();
    }
}

function convYear(yyyy) {

  if(yyyy < 1900) {
    yyyy = 1900 + yyyy;
  }
  return yyyy;
}

function setToDayYotei() {

    var frYear = document.forms[0].prj050kaisiYoteiYear.value;
    var frMonth = document.forms[0].prj050kaisiYoteiMonth.value;
    var frDay = document.forms[0].prj050kaisiYoteiDay.value;
    var toYear = document.forms[0].prj050kigenYear.value;
    var toMonth = document.forms[0].prj050kigenMonth.value;
    var toDay = document.forms[0].prj050kigenDay.value;

    if (parseInt(frYear) > parseInt(toYear)) {
        toYear = frYear;
        toMonth = frMonth;
        toDay = frDay;
        document.forms[0].prj050kigenYear.value = frYear;
        document.forms[0].prj050kigenMonth.value = frMonth;
        document.forms[0].prj050kigenDay.value = frDay;
    }

    if (parseInt(frYear) == parseInt(toYear)) {
        if (parseInt(frMonth) > parseInt(toMonth)) {
            toMonth = frMonth;
            toDay = frDay;
            document.forms[0].prj050kigenMonth.value = frMonth;
            document.forms[0].prj050kigenDay.value = frDay;
        }
    }

    if (parseInt(frYear) == parseInt(toYear)) {
        if (parseInt(frMonth) == parseInt(toMonth)) {
            if (parseInt(frDay) > parseInt(toDay)) {
            toDay = frDay;
                document.forms[0].prj050kigenDay.value = frDay;
            }
        }
    }
}

function setToDayJisseki() {

    var frYear = document.forms[0].prj050kaisiJissekiYear.value;
    var frMonth = document.forms[0].prj050kaisiJissekiMonth.value;
    var frDay = document.forms[0].prj050kaisiJissekiDay.value;
    var toYear = document.forms[0].prj050syuryoJissekiYear.value;
    var toMonth = document.forms[0].prj050syuryoJissekiMonth.value;
    var toDay = document.forms[0].prj050syuryoJissekiDay.value;

    if (parseInt(frYear) > parseInt(toYear)) {
        toYear = frYear;
        toMonth = frMonth;
        toDay = frDay;
        document.forms[0].prj050syuryoJissekiYear.value = frYear;
        document.forms[0].prj050syuryoJissekiMonth.value = frMonth;
        document.forms[0].prj050syuryoJissekiDay.value = frDay;
    }

    if (parseInt(frYear) == parseInt(toYear)) {
        if (parseInt(frMonth) > parseInt(toMonth)) {
            toMonth = frMonth;
            toDay = frDay;
            document.forms[0].prj050syuryoJissekiMonth.value = frMonth;
            document.forms[0].prj050syuryoJissekiDay.value = frDay;
        }
    }

    if (parseInt(frYear) == parseInt(toYear)) {
        if (parseInt(frMonth) == parseInt(toMonth)) {
            if (parseInt(frDay) > parseInt(toDay)) {
            toDay = frDay;
                document.forms[0].prj050syuryoJissekiDay.value = frDay;
            }
        }
    }
}

function clickLabel(label) {
    var lab = document.getElementById(label.htmlFor);
    if (lab != null) {
        if (lab.tagName == "INPUT") {
            lab.checked = true;
        }
    }
    return false;
}