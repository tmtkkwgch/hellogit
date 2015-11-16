function selectData(anpSid) {
    document.forms[0].anp130SelectAphSid.value = anpSid;
    document.forms[0].CMD.value='anp130syokai';
    document.forms[0].submit();
}

function changePage(cmbObj) {
    document.forms[0].anp130NowPage.value=cmbObj.options[cmbObj.selectedIndex].value;
    document.forms[0].CMD.value='anp130pageChange';
    document.forms[0].submit();
}

function changeCheckList() {
    if (document.getElementsByName("anp130allCheck")[0].checked == true) {
      checkAll("anp130DelSidList");
    } else {
      nocheckAll("anp130DelSidList");
    }
}

function checkAll(chkName){
   chkAry = document.getElementsByName(chkName);
   for(i = 0; i < chkAry.length; i++) {
       chkAry[i].checked = true;
   }
}

function nocheckAll(chkName){
   chkAry = document.getElementsByName(chkName);
   for(i = 0; i < chkAry.length; i++) {
       chkAry[i].checked = false;
   }
}