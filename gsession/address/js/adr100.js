function addCompany(procMode) {
    document.forms[0].adr110ProcMode.value = procMode;
    document.forms[0].adr110editAcoSid.value = 0;

    buttonPush('addCompany');
}

function editCompany(acoSid, procMode) {
    document.forms[0].adr110ProcMode.value = procMode;
    document.forms[0].adr110editAcoSid.value = acoSid;

    buttonPush('editCompany');
}

function selectCompany(acoSid) {
    document.forms[0].adr020selectCompany.value = acoSid;
    buttonPush('backInputAddress');
}

function changePage(pageCmbName) {
    setPageParam('adr100page', pageCmbName);

    buttonPush('init');
}

function onTitleLinkSubmit(fid, order) {
    document.forms[0].adr100SortKey.value = fid;
    document.forms[0].adr100OrderKey.value = order;
    document.forms[0].submit();
    return false;
}

function changeTab(tab) {
    document.forms[0].adr100mode.value = tab;

    document.forms[0].CMD.value='changeTab';
    document.forms[0].submit();
    return false;
}

function searchToKana(kana) {
    document.forms[0].adr100SearchKana.value = kana;
    buttonPush('search50tab');
}

function changeChk(){
   var chkFlg;
   if (document.forms[0].all_Check.checked) {
       chkFlg = true;
   } else {
       chkFlg = false;
   }
   delAry = document.getElementsByName("adr100SelectCom");
   for(i = 0; i < delAry.length; i++) {
       delAry[i].checked = chkFlg;
   }
}

