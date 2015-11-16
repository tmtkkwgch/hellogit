function changeEnableDisable() {

    var jbatchKbn = 0;
    var jbatchKbnVal = '';

    for (i = 0; i < document.forms[0].cir120JdelKbn.length; i++) {
        if (document.forms[0].cir120JdelKbn[i].checked == true) {
            jbatchKbn = i;
        }
    }
    jbatchKbnVal = document.forms[0].cir120JdelKbn[jbatchKbn].value;

    if (jbatchKbnVal == 0) {
        document.forms[0].cir120JYear.disabled = true;
        document.forms[0].cir120JYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].cir120JMonth.disabled = true;
        document.forms[0].cir120JMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].cir120JYear.disabled = false;
        document.forms[0].cir120JYear.style.backgroundColor = '#ffffff';
        document.forms[0].cir120JMonth.disabled = false;
        document.forms[0].cir120JMonth.style.backgroundColor = '#ffffff';
    }

    var sbatchKbn = 0;
    var sbatchKbnVal = '';

    for (i = 0; i < document.forms[0].cir120SdelKbn.length; i++) {
        if (document.forms[0].cir120SdelKbn[i].checked == true) {
            sbatchKbn = i;
        }
    }
    sbatchKbnVal = document.forms[0].cir120SdelKbn[sbatchKbn].value;

    if (sbatchKbnVal == 0) {
        document.forms[0].cir120SYear.disabled = true;
        document.forms[0].cir120SYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].cir120SMonth.disabled = true;
        document.forms[0].cir120SMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].cir120SYear.disabled = false;
        document.forms[0].cir120SYear.style.backgroundColor = '#ffffff';
        document.forms[0].cir120SMonth.disabled = false;
        document.forms[0].cir120SMonth.style.backgroundColor = '#ffffff';
    }

    var dbatchKbn = 0;
    var dbatchKbnVal = '';

    for (i = 0; i < document.forms[0].cir120DdelKbn.length; i++) {
        if (document.forms[0].cir120DdelKbn[i].checked == true) {
            dbatchKbn = i;
        }
    }
    dbatchKbnVal = document.forms[0].cir120DdelKbn[dbatchKbn].value;

    if (dbatchKbnVal == 0) {
        document.forms[0].cir120DYear.disabled = true;
        document.forms[0].cir120DYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].cir120DMonth.disabled = true;
        document.forms[0].cir120DMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].cir120DYear.disabled = false;
        document.forms[0].cir120DYear.style.backgroundColor = '#ffffff';
        document.forms[0].cir120DMonth.disabled = false;
        document.forms[0].cir120DMonth.style.backgroundColor = '#ffffff';
    }
}

function submitStyleChange() {
    document.forms[0].cir120JYear.disabled=false;
    document.forms[0].cir120JMonth.disabled=false;
    document.forms[0].cir120SYear.disabled=false;
    document.forms[0].cir120SMonth.disabled=false;
    document.forms[0].cir120DYear.disabled=false;
    document.forms[0].cir120DMonth.disabled=false;
}

function setDispState(kbnElem, yearElem, monthElem) {

    for (i = 0; i < kbnElem.length; i++) {
        if (kbnElem[i].checked == true) {
            batchKbn = i;
        }
    }
    batchKbnVal = kbnElem[batchKbn].value;

    if (batchKbnVal == 0) {
        yearElem.disabled = true;
        yearElem.style.backgroundColor = '#e0e0e0';
        monthElem.disabled = true;
        monthElem.style.backgroundColor = '#e0e0e0';
    } else {
        yearElem.disabled = false;
        yearElem.style.backgroundColor = '#ffffff';
        monthElem.disabled = false;
        monthElem.style.backgroundColor = '#ffffff';
    }
}


$(function() {

    //アカウント選択ボタン
    $("#accountSelBtn").live("click", function(){

        /* アカウント選択ポップアップ */
        $('#accountSelPop').dialog({
            autoOpen: true,  // hide dialog
            bgiframe: true,   // for IE6
            resizable: false,
            height: 600,
            width: 800,
            modal: true,
            overlay: {
              backgroundColor: '#000000',
              opacity: 0.5
            },
            buttons: {
              閉じる: function() {
                  $(this).dialog('close');
              }
            }
        });
    });

    //対象アカウントラジオボタン
    $(".accountSelKbn").live("change", function(){
        $('#accountSelArea').toggle();
    });

    if ($('input[name=cir120SelKbn]:checked').val() == 0) {
        $('#accountSelArea').removeClass('display_none');
    } else {
        $('#accountSelArea').addClass('display_none');
    }

});