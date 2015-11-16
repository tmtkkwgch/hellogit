function editEnquete(enqSid) {
    document.forms[0].CMD.value='enq230edit';
    document.forms[0].enqEditMode.value='1';
    document.forms[0].editEnqSid.value=enqSid;
    document.forms[0].submit();
}

function changePage(id){
    if (id == 0) {
        document.forms[0].enq230pageBottom.value=document.forms[0].enq230pageTop.value;
    } else {
        document.forms[0].enq230pageTop.value=document.forms[0].enq230pageBottom.value;
    }

    document.forms[0].CMD.value='init';
    document.forms[0].submit();
    return false;
}