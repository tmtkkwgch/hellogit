function clearDate(day, month, year){
    $("#" + day)[0].value = '';
    $("#" + month)[0].value = '';
    $("#" + year)[0].value = '';
}

function memberEdit(cmd, dspId) {
    document.forms[0].CMD.value = cmd;
    document.forms[0].movedDspId.value = dspId;
    document.forms[0].submit();
    return false;
}

YAHOO.namespace("subbox");
function init() {
  YAHOO.subbox.subPanel = new YAHOO.widget.Panel("subPanel", { width:"450px", fixedcenter:true, visible:false, constraintoviewport:true, close:false } );
  YAHOO.subbox.subPanel.render();
}
YAHOO.util.Event.addListener(window, "load", init);

function openpos(){
  pos.location='../project/prj120.do';
  YAHOO.subbox.subPanel.show();
  return false;
}

function useTemplate(cmd, mode) {
    document.forms[0].CMD.value = cmd;
    document.forms[0].prjTmpMode.value = mode;
    document.forms[0].submit();
    return false;
}

function moveDay(elmYear, elmMonth, elmDay, kbn) {

    systemDate = new Date();

    //「今日」ボタン押下
    if (kbn == 2) {
        $(elmYear).val(convYear(systemDate.getYear()));
        $(elmMonth).val(systemDate.getMonth() + 1);
        $(elmDay).val(systemDate.getDate());
        return;
    }

    //「前日」or 「翌日」ボタン押下
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

function convYear(yyyy) {

  if(yyyy < 1900) {
    yyyy = 1900 + yyyy;
  }
  return yyyy;
}

function deleteCompany(usrDel) {
    document.forms['prj020Form'].CMD.value = 'deleteCompany';
    document.forms['prj020Form'].prj020UsrDel.value = usrDel;
    document.forms['prj020Form'].submit();
    return false;
}

function scroll() {
    if (document.forms[0].prj020ScrollFlg.value=='1') {
        window.location.hash='add_user';
    }
}
