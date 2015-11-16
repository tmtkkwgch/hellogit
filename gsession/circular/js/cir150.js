function getForm() {
    return document.forms['cir150Form'];
}

function changePage(id){
    if (id == 1) {
        getForm().cir150pageTop.value=document.forms[0].cir150pageBottom.value;
    }

    getForm().CMD.value='init';
    getForm().submit();
    return false;
}

function editAccount(mode, accountId) {
    getForm().CMD.value = 'accountDetail';
    getForm().cirCmdMode.value = mode;
    getForm().cirAccountSid.value = accountId;
    getForm().submit();
    return false;
}

function sort(sortKey, orderKey) {
    getForm().CMD.value = 'init';
    getForm().cir150sortKey.value = sortKey;
    getForm().cir150order.value = orderKey;
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

function accountDelete() {


    document.forms[0].CMD.value='delAccount';
    var paramStr = "";
    paramStr += getFormData($('#cir150Form'));
    var height = 160;

    $.ajax({
        async: true,
        url:  "../circular/cir150.do",
        type: "post",
        data: paramStr
    }).done(function( data ) {
        if (data.errorsList.length > 0) {
            height += (data.errorsList.length * 30) - 30;

            var msg = "";

            for (i = 0; i < data.errorsList.length; i++) {

                if (i != 0) {
                    msg += "<br>";
                }

                msg += data.errorsList[i];
            }

            messagePop(msg, height);
        } else {
            buttonPush('accountDelete');
        }
    }).fail(function(data){
        alert('error');
    });

}

function messagePop(msg, height) {

    $('#messageArea').html("");
    $('#messageArea').append(msg);

    $('#messagePop').dialog({
        autoOpen: true,  // hide dialog
        bgiframe: true,   // for IE6
        resizable: false,
        height: height,
        width: 350,
        modal: true,
        overlay: {
          backgroundColor: '#000000',
          opacity: 0.5
        },
        buttons: {
          OK: function() {
              $(this).dialog('close');
          }
        }
    });
}

function getFormData(formObj) {

    var formData = "";
    formData = formObj.serialize();

    return formData;
}
