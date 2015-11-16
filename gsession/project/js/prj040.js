function viewPoject(cmd, id){
    $('#CMD')[0].value = cmd;
    document.forms[0].prj030prjSid.value=id;
    document.forms[0].submit();
    return false;
}

function clearUserList(){
    $('#selectMember')[0].innerHTML = '';

    var userSid = document.getElementsByName('prj040scMemberSid');
    for (i = 0; i < userSid.length; i++) {
        userSid[i].value = '';
        userSid[i].disabled = true;
    }
}

function clearDate(day, month, year){
    $('#' + day)[0].value = '';
    $('#' + month)[0].value = '';
    $('#' + year)[0].value = '';
}

function changePage(id){
    if (id == 1) {
        document.forms[0].prj040page2.value=document.forms[0].prj040page1.value;
    } else {
        document.forms[0].prj040page1.value=document.forms[0].prj040page2.value;
    }

    document.forms[0].submit();
    return false;
}

function onTitleLinkSubmit(sortKey, order) {
    document.forms[0].prj040sort.value = sortKey;
    document.forms[0].prj040order.value = order;
    document.forms[0].submit();
    return false;
}
