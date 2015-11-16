function getForm() {
    return document.forms['wml030Form'];
}

function changePage(id){
    if (id == 1) {
        getForm().wml030pageTop.value=document.forms[0].wml030pageBottom.value;
    }

    getForm().CMD.value='init';
    getForm().submit();
    return false;
}

function editAccount(mode, accountId) {
    getForm().wmlCmdMode.value = mode;
    return wml030confBtn('accountDetail', accountId);
}

function confLabel(accountId) {
    return wml030confBtn('confLabel', accountId);
}

function confFilter(accountId) {
    return wml030confBtn('confFilter', accountId);
}

function confTemplate(accountId) {
    return wml030confBtn('confMailTemplate', accountId);
}

function wml030confBtn(cmd, accountId) {
    getForm().CMD.value = cmd;
    getForm().wmlAccountSid.value = accountId;
    getForm().submit();
    return false;
}

function sort(sortKey, orderKey) {
    getForm().CMD.value = 'init';
    getForm().wml030sortKey.value = sortKey;
    getForm().wml030order.value = orderKey;
    getForm().submit();
    return false;
}

function chgCheckAllChange(allChkName, chkName) {
    if (document.getElementsByName(allChkName)[0].checked) {
        $(".td_line_color1").addClass("td_type_selected");
        $(".td_line_color2").addClass("td_type_selected2");
    } else {
        $(".td_type_selected").addClass("td_line_color1");
        $(".td_type_selected2").addClass("td_line_color2");
        $(".td_line_color1").removeClass("td_type_selected");
        $(".td_line_color2").removeClass("td_type_selected2");
    }
}

function backGroundSetting(key, typeNo) {
    if (key.checked) {
        if (typeNo == 1) {
          document.getElementById(key.value).className='td_type_selected';
        } else {
          document.getElementById(key.value).className='td_type_selected2';
        }
    } else {
        var cssName = 'td_line_color' + typeNo;
        document.getElementById(key.value).className=cssName;
    }
}