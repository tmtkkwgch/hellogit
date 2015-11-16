function enq970searchDetail() {
    var dspVal = document.forms[0].enq970searchDetailFlg.value;

    if (dspVal == 1) {
        $('#enq970searchDetailArea').show();
    } else {
        $('#enq970searchDetailArea').hide();
    }
}

function enq970changeSearch() {
    var dspVal = document.forms[0].enq970searchDetailFlg.value;

    if (dspVal == 0) {
        document.forms[0].enq970searchDetailFlg.value='1';
        document.forms[0].helpPrm.value='1';
    } else {
        document.forms[0].enq970searchDetailFlg.value='0';
        document.forms[0].helpPrm.value='0';
    }
    enq970searchDetail();
}

function enq970chkSrhDate(dateType) {
    if (dateType == 1) {
        enq970ChangeDateArea('enq970makeDateKbn', 'enq970makeDateArea');
    } else if (dateType == 2) {
        enq970ChangeDateArea('enq970pubDateKbn', 'enq970pubDateArea');
    } else if (dateType == 3) {
        enq970ChangeDateArea('enq970ansDateKbn', 'enq970ansDateArea');
    } else if (dateType == 4) {
        enq970ChangeDateArea('enq970resPubDateKbn', 'enq970resPubDateArea');
    }
}

function enq970ChangeDateArea(paramName, areaId) {
    if ($('input[name="' + paramName + '"]:checked').val() == 1) {
        $('#' + areaId).show();
    } else {
        $('#' + areaId).hide();
    }
}

function enq970changePage(id){
    if (id == 0) {
        document.forms[0].enq970pageBottom.value=document.forms[0].enq970pageTop.value;
    } else {
        document.forms[0].enq970pageTop.value=document.forms[0].enq970pageBottom.value;
    }

    document.forms[0].CMD.value='init';
    document.forms[0].submit();
    return false;
}

function enq970ClickTitle(sortKey, order) {
    document.forms[0].CMD.value='init';
    document.forms[0].enq970sortKey.value=sortKey;
    document.forms[0].enq970order.value=order;
    document.forms[0].submit();
    return false;
}

function enq970viewDetail(enqSid) {
    document.forms[0].CMD.value='enq970detail';
    document.forms[0].enqEditMode.value='1';
    document.forms[0].editEnqSid.value=enqSid;
    document.forms[0].submit();
}

$(function() {
    $('#enq970searchDetailArea').hide();
    enq970chkSrhDate(1);
    enq970chkSrhDate(2);
    enq970chkSrhDate(3);
    enq970chkSrhDate(4);

    if (document.forms[0].enq970searchDetailFlg.value == '1') {
        enq970searchDetail();
    }

    $("label").inFieldLabels();

});
