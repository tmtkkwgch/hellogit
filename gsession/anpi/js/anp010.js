function selectSyosai(usrSid) {
    document.forms[0].userSid.value=usrSid;
    document.forms[0].CMD.value='anp010syosai';
    document.forms[0].submit();
}

function changePage(cmbObj) {
    document.forms[0].anp010NowPage.value=cmbObj.options[cmbObj.selectedIndex].value;
    document.forms[0].CMD.value='anp010pageChange';
    document.forms[0].submit();
}

function sortList(colIndex, order) {
    if (document.forms[0].anp010SortKeyIndex.value != colIndex) {
        document.forms[0].anp010OrderKey.value=1;
    }
    document.forms[0].anp010SortKeyIndex.value=colIndex;
    document.forms[0].CMD.value='anp010sortList';
    document.forms[0].submit();
}

function dispSearch() {
    var searchFlg = $('input:hidden[name=anp010SearchKbn]').val();
    if (searchFlg == 1) {
        $('#top_Search').hide();
        $('input:hidden[name=anp010SearchKbn]').val('0');
    } else {
        $('#top_Search').show();
        $('input:hidden[name=anp010SearchKbn]').val('1');
    }
}

function changeDispRadio() {

    var kbn = Number($("input:radio[name=anp010SearchAnsKbn]:checked").val());

    if (kbn == 1) {
        $('.top_Search_anp').hide();
        $('.top_Search_plc').hide();
        $('.top_Search_syu').hide();
    } else {
        $('.top_Search_anp').show();
        $('.top_Search_plc').show();
        $('.top_Search_syu').show();
    }

}

$(function() {
    var searchFlg = $('input:hidden[name=anp010SearchKbn]').val();
    if (searchFlg == 1) {
        $('#top_Search').show();
    } else {
        $('#top_Search').hide();
    }

    changeDispRadio();
});