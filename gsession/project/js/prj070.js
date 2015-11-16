function clearUserList(){
    $('#selectMember')[0].innerHTML = '';

    var userSid = document.getElementsByName('prj070scTantou');
    for (i = 0; i < userSid.length; i++) {
        userSid[i].value = '';
        userSid[i].disabled = true;
    }
}

function clearAddUserList(){
    $('#selectAddUser')[0].innerHTML = '';

    var userSid = document.getElementsByName('prj070scTourokusya');
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
        document.forms[0].prj070page2.value=document.forms[0].prj070page1.value;
    } else {
        document.forms[0].prj070page1.value=document.forms[0].prj070page2.value;
    }

    document.forms[0].submit();
    return false;
}

function onTitleLinkSubmit(sortKey, order) {
    $('#CMD')[0].value = '';
    document.forms[0].prj070sort.value = sortKey;
    document.forms[0].prj070order.value = order;
    document.forms[0].submit();
    return false;
}

function viewTodo(cmd, prjSid, todoSid){
    $('#CMD')[0].value = cmd;
    document.forms[0].prj060prjSid.value=prjSid;
    document.forms[0].prj060todoSid.value=todoSid;
    document.forms[0].submit();
    return false;
}

function changeProject() {
    $('#CMD')[0].value = '';
    document.forms['prj070Form'].submit();
}

function clickLabel(label) {
    var lab = document.getElementById(label.htmlFor);
    if (lab != null) {
        if (lab.tagName == "INPUT") {
            lab.checked = !lab.checked;
        }
    }
    return false;
}