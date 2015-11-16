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


function showOrHide(){
var rsv110HeaderDspFlg = document.forms[0].rsv110HeaderDspFlg.value;
  if (rsv110HeaderDspFlg == '0') {
    showText();
  } else {
    hideText();
  }
}

function showText(){
    $('#longHeader').show();
    $('#shortHeader').hide();
    document.forms[0].rsv110HeaderDspFlg.value='0';
}

function hideText(){
    $('#longHeader').hide();
    $('#shortHeader').show();
    document.forms[0].rsv110HeaderDspFlg.value='1';
}

function selectUsersList() {

    var flg = true;
   if (document.forms[0].rsv110SelectUsersKbn.checked) {
       flg = true;
   } else {
       flg = false;
   }
   oElements = document.getElementsByName("users_l");
   var defUserAry = document.forms[0].users_l.options;
   var defLength = defUserAry.length;
   for (i = defLength - 1; i >= 0; i--) {
       if (defUserAry[i].value != -1) {
           defUserAry[i].selected = flg;
       }
   }
}


$(function() {
    $("#syoninbtn").live("click", function(){
        $('#rsvApproval').dialog({
            autoOpen: true,
            bgiframe: true,
            resizable: false,
            width:350,
            height: 180,
            modal: true,
            closeOnEscape: false,
            overlay: {
                backgroundColor: '#000000',
                opacity: 0.5
            },
            buttons: {
                はい: function() {
                    document.forms[0].CMD.value = "rsvApprovalOk";
                    document.forms[0].submit();

                    $(this).dialog('close');
                },
                キャンセル: function() {
                  $(this).dialog('close');
                }
            }
        });
    });
    $('textarea').each(function() {
        setTextareaAutoResize($(this).get(0));
    });
});

$(function() {
    $("#kyakkabtn").live("click", function(){
        $('#rsvcheck').dialog({
            autoOpen: true,
            bgiframe: true,
            resizable: false,
            width:350,
            height: 180,
            modal: true,
            closeOnEscape: false,
            overlay: {
                backgroundColor: '#000000',
                opacity: 0.5
            },
            buttons: {
                はい: function() {
                    document.forms[0].CMD.value = "rsvRejectionOk";

                    if ($('#rejectDel').attr("checked")){
                        document.forms[0].rsv110rejectDel.value = 1;
                    }
                    document.forms[0].submit();

                    $(this).dialog('close');
                },
                キャンセル: function() {
                    //キャンセル時チェックボックスを外す
                    $('#rejectDel').attr("checked",false);
                  $(this).dialog('close');
                }
            }
        });
    });
});