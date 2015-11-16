function buttonPush(cmd) {
    document.forms[0].CMD.value=cmd;
    document.forms[0].submit();
    return false;
}

function man070load() {

    var pxyUseKbn = 0;
    for (i = 0; i < document.forms[0].man070pxyUseKbn.length; i++) {
        if (document.forms[0].man070pxyUseKbn[i].checked == true) {
            pxyUseKbn = i;
        }
    }
    var pxyUseKbnVal = document.forms[0].man070pxyUseKbn[pxyUseKbn].value;
    radioChkChange(pxyUseKbnVal);

    chkUserAuth();
}

function radioChkChange(chkVal) {

    if (chkVal == '0') {
        document.forms[0].man070address.readOnly=true;
        document.forms[0].man070address.style.backgroundColor = '#e0e0e0';
        document.forms[0].man070address.value='';
        document.forms[0].man070portnum.readOnly=true;
        document.forms[0].man070portnum.style.backgroundColor = '#e0e0e0';
        document.forms[0].man070portnum.value='';
        document.getElementById("proxyAuth").style.display="none";
        document.getElementById("proxyAuthElement").style.display="none"; 
    } else if (chkVal == '1') {
        document.forms[0].man070address.readOnly=false;
        document.forms[0].man070address.style.backgroundColor = '#ffffff';
        document.forms[0].man070portnum.readOnly=false;
        document.forms[0].man070portnum.style.backgroundColor = '#ffffff';
        document.getElementById("proxyAuth").style.display="";
        document.getElementById("proxyAuthElement").style.display=""; 
    }
}


function chkUserAuth() {

    if ($('#userAuth')[0].checked) {
         $('#userAuthElement').show();
    } else {
        $('#userAuthElement').hide();
    }
}
