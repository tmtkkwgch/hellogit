function changePage1() {
    document.forms[0].CMD.value='init';
    for (i = 0; i < document.forms[0].fil240Slt_page1.length; i++) {
        if (document.forms[0].fil240Slt_page1[i].selected) {
            document.forms[0].fil240Slt_page2.value=document.forms[0].fil240Slt_page1[i].value;
            document.forms[0].fil240PageNum.value=document.forms[0].fil240Slt_page1[i].value;
        }
    }
    document.forms[0].submit();
    return false;
}

function changePage2() {
    document.forms[0].CMD.value='init';
    for (i = 0; i < document.forms[0].fil240Slt_page2.length; i++) {
        if (document.forms[0].fil240Slt_page2[i].selected) {
            document.forms[0].fil240Slt_page1.value=document.forms[0].fil240Slt_page2[i].value;
            document.forms[0].fil240PageNum.value=document.forms[0].fil240Slt_page2[i].value;
        }
    }
    document.forms[0].submit();
    return false;
}

function MoveToFileDetail(dirSid) {
    document.forms[0].CMD.value='fil240fileDetail';
    document.forms[0].backDspLow.value='fil240';
    document.forms[0].fil070DirSid.value=dirSid;
    document.forms[0].submit();
    return false;
}