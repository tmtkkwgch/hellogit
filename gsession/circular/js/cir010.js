function changeTab(cmd){
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function changePage(id){
    if (id == 1) {
        document.forms[0].cir010pageNum2.value=document.forms[0].cir010pageNum1.value;
    } else {
        document.forms[0].cir010pageNum1.value=document.forms[0].cir010pageNum2.value;
    }

    document.forms[0].CMD.value='init';
    document.forms[0].submit();
    return false;
}

function buttonPush(cmd, id){

    document.forms[0].CMD.value=cmd;
    document.forms[0].cir010selectInfSid.value=id;
    document.forms[0].submit();
    return false;
}

function gomiView(cmd, id, kbn){

    document.forms[0].CMD.value=cmd;
    document.forms[0].cir010selectInfSid.value=id;
    document.forms[0].cir010sojuKbn.value=kbn;
    document.forms[0].submit();
    return false;
}

function changeChk(){

   var chkFlg;
   if (document.forms[0].allChk.checked) {
       checkAll('cir010delInfSid');

   } else {
       nocheckAll('cir010delInfSid');
   }
}

function onTitleLinkSubmit(fid, order) {
    document.forms[0].CMD.value='init';
    document.forms[0].cir010sortKey.value = fid;
    document.forms[0].cir010orderKey.value = order;
    document.forms[0].submit();
    return false;
}


$(function() {

    /* アカウント変更 */
    $('#account_comb_box').live("change", function(){
        document.forms[0].CMD.value='';
        document.forms[0].cir010cmdMode.value='0';
        document.forms[0].submit();
    });

});
