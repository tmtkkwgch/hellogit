function selectPage(id){
    if (id == 1) {
        $('#wml230mailListPageTop')[0].value = $('#wml230mailListPageBottom')[0].value;
    }

    $('#CMD')[0].value = 'init';
    document.forms[0].submit();
    return false;
}

function changeFilterInput() {
    var index;
    for (index = 1; index <= 5; index++) {
        if (getElement('wml230condition' + index).checked) {
            getElement('wml230conditionType' + index).disabled = false;
            getElement('wml230conditionExs' + index).disabled = false;
            getElement('wml230conditionText' + index).disabled = false;
        } else {
            getElement('wml230conditionType' + index).disabled = true;
            getElement('wml230conditionExs' + index).disabled = true;
            getElement('wml230conditionText' + index).disabled = true;
        }
    }
}
function getElement(name) {
    return document.getElementsByName(name)[0];
}

function wml230Sort(sortKey, order) {
    document.getElementsByName('wml230mailListSortKey')[0].value = sortKey;
    document.getElementsByName('wml230mailListOrder')[0].value = order;
    document.forms[0].submit();
    return false;
}

function addFwAddress() {
    var fwAddressTbl = document.getElementById('wml230fwAddressArea');
    fwAddressTbl.innerHTML
        += '<tr>'
        + '<td><img src=\"../common/images/delete.gif\" alt=\"' + msglist['delet'] + '" border=\"0\" onClick="\deleteFwAddress(' + fwAddressTbl.rows.length + ');\"></td>'
        +'<td><input type=\"text\" name=\"wml230actionSendValue\" value=\"\"  maxlength=\"256\" style=\"width:60%;\"></td></tr>';
}

function deleteFwAddress(rowIdx) {
    document.forms['wml230Form'].wml230actionSendValueDelIdx.value=rowIdx;
    return buttonPush('delFwAddress');
}