function selectPage(id){
    if (id == 1) {
        $('#wml140mailListPageTop')[0].value = $('#wml140mailListPageBottom')[0].value;
    }

    $('#CMD')[0].value = 'init';
    document.forms[0].submit();
    return false;
}

function changeFilterInput() {
    var index;
    for (index = 1; index <= 5; index++) {
        if (getElement('wml140condition' + index).checked) {
            getElement('wml140conditionType' + index).disabled = false;
            getElement('wml140conditionExs' + index).disabled = false;
            getElement('wml140conditionText' + index).disabled = false;
        } else {
            getElement('wml140conditionType' + index).disabled = true;
            getElement('wml140conditionExs' + index).disabled = true;
            getElement('wml140conditionText' + index).disabled = true;
        }
    }
}
function getElement(name) {
    return document.getElementsByName(name)[0];
}

function wml140Sort(sortKey, order) {
    document.getElementsByName('wml140mailListSortKey')[0].value = sortKey;
    document.getElementsByName('wml140mailListOrder')[0].value = order;
    document.forms[0].submit();
    return false;
}

function addFwAddress() {
    var fwAddressTbl = document.getElementById('wml140fwAddressArea');
    fwAddressTbl.innerHTML
        += '<tr>'
        + '<td><img src=\"../common/images/delete.gif\" alt=\"' + msglist['delet'] + '" border=\"0\" onClick="\deleteFwAddress(' + fwAddressTbl.rows.length + ');\"></td>'
        +'<td><input type=\"text\" name=\"wml140actionSendValue\" value=\"\"  maxlength=\"256\" style=\"width:60%;\"></td></tr>';
}

function deleteFwAddress(rowIdx) {
    document.forms['wml140Form'].wml140actionSendValueDelIdx.value=rowIdx;
    return buttonPush('delFwAddress');
}