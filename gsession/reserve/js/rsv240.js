function changeChk() {
   var chkFlg;
   if (document.forms[0].rsv240AllCheck.checked) {
       checkAll('rsv240RsgSids');
   } else {
       nocheckAll('rsv240RsgSids');
   }
}

function lmtEnableDisable() {
    var ctext = $('#lmtinput')[0];
    var allCheckFlg = document.getElementsByName("rsv240AllCheck");
    var rsgSidFlg = document.getElementsByName("rsv240RsgSids");
    var dspRsgSidFlg = false;

    for(i = 0; i < rsgSidFlg.length; i++) {
        dspRsgSidFlg = rsgSidFlg[i].checked;
        if (dspRsgSidFlg) {
            break;
        }
    }

    if (allCheckFlg[0].checked || dspRsgSidFlg) {
        changeStyle(ctext, 'rsv_description_dsp');
    } else {
        changeStyle(ctext, 'rsv_description_notdsp');
    }
}