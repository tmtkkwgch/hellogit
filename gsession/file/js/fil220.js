function changeChk() {
   var chkFlg;
   if (document.forms[0].fil220allCheck.checked) {
       checkAll('fil220sltCheck');

   } else {
       nocheckAll('fil220sltCheck');
   }
}

function CabinetDetail(cabSid) {
    document.forms[0].cmnMode.value='1';
    document.forms[0].fil030SelectCabinet.value=cabSid;
    document.forms[0].CMD.value='fil220editCabinet';
    document.forms[0].submit();
    return false;
}

function CabinetDetailMulti() {
    document.forms[0].cmnMode.value='2';
    document.forms[0].CMD.value='fil220togetherEdit';
    document.forms[0].submit();
    return false;
}