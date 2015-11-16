function buttonPush(cmd) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function changeGroupCombo(){
    document.forms[0].CMD.value='comboChange';
    document.forms[0].submit();
    return false;
}

function sort(sortKey, orderKey){
    document.forms[0].CMD.value='init';
    document.forms[0].rsv100OrderKey.value=orderKey;
    document.forms[0].rsv100SortKey.value=sortKey;
    document.forms[0].submit();
    return false;
}
function changePage(id){
    if (id == 1) {
        document.forms[0].rsv100PageTop.value=document.forms[0].rsv100PageBottom.value;
    }
    document.forms[0].CMD.value='pageChange';
    document.forms[0].submit();
    return false;
}

function selectChange(kbn) {
    if (document.forms[0].rsv100SelectedKey1.value == kbn) {
        if (document.forms[0].rsv100SelectedKey1Sort[0].checked) {
            document.forms[0].rsv100SelectedKey1Sort[0].checked = false;
            document.forms[0].rsv100SelectedKey1Sort[1].checked = true;
        } else {
            document.forms[0].rsv100SelectedKey1Sort[0].checked = true;
            document.forms[0].rsv100SelectedKey1Sort[1].checked = false;
        }
    } else {
        document.forms[0].rsv100SelectedKey1.value = kbn;
    }
    return false;
}

function keyPress(keycode) {
    if (keycode == 13) {
        document.forms[0].CMD.value='kensaku';
        document.forms[0].submit();
        return false;
    }
}

function moveDay(elmYear, elmMonth, elmDay, kbn) {

    systemDate = new Date();

    if (kbn == 2) {
        $(elmYear).val(convYear(systemDate.getYear()));
        $(elmMonth).val(systemDate.getMonth() + 1);
        $(elmDay).val(systemDate.getDate());
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
}

function rsv100ChgDateKbn() {
    var dateElName = [
      'rsv100selectedFromYear', 'rsv100selectedFromMonth', 'rsv100selectedFromDay',
      'rsv100selectedToYear', 'rsv100selectedToMonth', 'rsv100selectedToDay',
      'rsv100FromCalBtn', 'rsv100ToCalBtn'
    ];

    var dateDisabled = document.forms[0].rsv100dateKbn.checked;

    for (idx = 0; idx < dateElName.length; idx++) {
      var delName = dateElName[idx];
      document.getElementsByName(dateElName[idx])[0].disabled=dateDisabled;
    }

    if (dateDisabled) {
      $("#rsv100DateFromBtnAreaOn").hide();
      $("#rsv100DateToBtnAreaOn").hide();
      $("#rsv100DateFromBtnAreaOff").show();
      $("#rsv100DateToBtnAreaOff").show();
    } else {
      $("#rsv100DateFromBtnAreaOn").show();
      $("#rsv100DateToBtnAreaOn").show();
      $("#rsv100DateFromBtnAreaOff").hide();
      $("#rsv100DateToBtnAreaOff").hide();
    }
}

$(function() {

    $(".rsv_yoyaku_link").live("click", function(){

        $('#rsvCreateArea').children().remove();
        $('#rsvCreateArea').append('<iframe src=\"../reserve/rsv110.do?rsv110ProcMode=2&rsv110RsySid='
                                   + $(this).attr('id')
                                   + '\" name=\"sample\" width=\"768\" height=\"690\"></iframe>');

        /* 施設予約ポップ */
        $('#reservePop').dialog({
            autoOpen: true,  // hide dialog
            bgiframe: true,   // for IE6
            resizable: false,
            height: 768,
            width: 800,
            modal: true,
            overlay: {
              backgroundColor: '#000000',
              opacity: 0.5
            },
            buttons: {
              閉じる: function() {
                  $(this).dialog('close');
              }
            }
        });
    });

    //施設区分よりCSVの表示を切り替える
    var sisKbn = $("input:hidden[name='rsv100svSelectSisKbn']").val();
    dispChangeCsvField(sisKbn)
});

function callYokyakuWindowClose() {
    $('#reservePop').dialog('close');
}

function dispChangeCsvField(sisKbn) {
    //部屋
    if (sisKbn == 1) {
        $(".csvOutFieldHeya").show();
        $(".csvOutFieldCar").hide();
        $(".csvOutFieldHeyaCar").show();
    //車
    } else if (sisKbn == 3) {
        $(".csvOutFieldHeya").hide();
        $(".csvOutFieldCar").show();
        $(".csvOutFieldHeyaCar").show();
    } else {
        $(".csvOutFieldHeya").hide();
        $(".csvOutFieldCar").hide();
        $(".csvOutFieldHeyaCar").hide();
    }
}