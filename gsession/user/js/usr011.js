function usr011ChahgeCbx(form, exekbn) {
    document.forms[0].CMD.value = "change";
    document.forms[0].submit()
}

function pushDell(setMode) {
   document.forms[0].usr011DelKbn.value = setMode;
}

function getChgctg(){

    sizeAry = ctgFrame.document.getElementsByName('c1');
    for(i=0;i<sizeAry.length;i++) {
        if(sizeAry[i].checked){
        document.forms[0].selectgroup.value = sizeAry[i].value;
        }
    }
}

/**
 * �e��ʂɖ߂�ۂɃA�N�V�����ɃR�}���h��n���ꍇ
 * cmd �R�}���h
 */
function openGroupWindowForUsr011(formOj, selBoxName, myGpFlg, cmd) {
    if (cmd != "") {
        document.forms[0].CMD.value=cmd;
    }
    openGroup(formOj, selBoxName, myGpFlg, "");
    getChgctg();
    return;
}
function getBfsids(){
    var svName = "sv_users";
    var svBfName ="sv_Bfusers";
    $("[name='" + svBfName + "']").remove();
    $("[name='" + svName + "']").each(function(){
        if (this.value != null && this.value != "") {
            $('<input type="hidden" name="' + svBfName + '" value="' + this.value + '">').appendTo("form");
        }
    });
    return;
}