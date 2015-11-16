function changeEnableDisable() {

    var jbatchKbn = 0;
    var jbatchKbnVal = '';

    for (i = 0; i < document.forms[0].sml160JdelKbn.length; i++) {
        if (document.forms[0].sml160JdelKbn[i].checked == true) {
            jbatchKbn = i;
        }
    }
    jbatchKbnVal = document.forms[0].sml160JdelKbn[jbatchKbn].value;

    if (jbatchKbnVal == 0) {
        document.forms[0].sml160JYear.disabled = true;
        document.forms[0].sml160JYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].sml160JMonth.disabled = true;
        document.forms[0].sml160JMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].sml160JYear.disabled = false;
        document.forms[0].sml160JYear.style.backgroundColor = '#ffffff';
        document.forms[0].sml160JMonth.disabled = false;
        document.forms[0].sml160JMonth.style.backgroundColor = '#ffffff';
    }

    var sbatchKbn = 0;
    var sbatchKbnVal = '';

    for (i = 0; i < document.forms[0].sml160SdelKbn.length; i++) {
        if (document.forms[0].sml160SdelKbn[i].checked == true) {
            sbatchKbn = i;
        }
    }
    sbatchKbnVal = document.forms[0].sml160SdelKbn[sbatchKbn].value;

    if (sbatchKbnVal == 0) {
        document.forms[0].sml160SYear.disabled = true;
        document.forms[0].sml160SYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].sml160SMonth.disabled = true;
        document.forms[0].sml160SMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].sml160SYear.disabled = false;
        document.forms[0].sml160SYear.style.backgroundColor = '#ffffff';
        document.forms[0].sml160SMonth.disabled = false;
        document.forms[0].sml160SMonth.style.backgroundColor = '#ffffff';
    }

    var wbatchKbn = 0;
    var wbatchKbnVal = '';

    for (i = 0; i < document.forms[0].sml160WdelKbn.length; i++) {
        if (document.forms[0].sml160WdelKbn[i].checked == true) {
            wbatchKbn = i;
        }
    }
    wbatchKbnVal = document.forms[0].sml160WdelKbn[wbatchKbn].value;

    if (wbatchKbnVal == 0) {
        document.forms[0].sml160WYear.disabled = true;
        document.forms[0].sml160WYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].sml160WMonth.disabled = true;
        document.forms[0].sml160WMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].sml160WYear.disabled = false;
        document.forms[0].sml160WYear.style.backgroundColor = '#ffffff';
        document.forms[0].sml160WMonth.disabled = false;
        document.forms[0].sml160WMonth.style.backgroundColor = '#ffffff';
    }

    var dbatchKbn = 0;
    var dbatchKbnVal = '';

    for (i = 0; i < document.forms[0].sml160DdelKbn.length; i++) {
        if (document.forms[0].sml160DdelKbn[i].checked == true) {
            dbatchKbn = i;
        }
    }
    dbatchKbnVal = document.forms[0].sml160DdelKbn[dbatchKbn].value;

    if (dbatchKbnVal == 0) {
        document.forms[0].sml160DYear.disabled = true;
        document.forms[0].sml160DYear.style.backgroundColor = '#e0e0e0';
        document.forms[0].sml160DMonth.disabled = true;
        document.forms[0].sml160DMonth.style.backgroundColor = '#e0e0e0';
    } else {
        document.forms[0].sml160DYear.disabled = false;
        document.forms[0].sml160DYear.style.backgroundColor = '#ffffff';
        document.forms[0].sml160DMonth.disabled = false;
        document.forms[0].sml160DMonth.style.backgroundColor = '#ffffff';
    }
}

function submitStyleChange() {
    document.forms[0].sml160JYear.disabled=false;
    document.forms[0].sml160JMonth.disabled=false;
    document.forms[0].sml160SYear.disabled=false;
    document.forms[0].sml160SMonth.disabled=false;
    document.forms[0].sml160WYear.disabled=false;
    document.forms[0].sml160WMonth.disabled=false;
    document.forms[0].sml160DYear.disabled=false;
    document.forms[0].sml160DMonth.disabled=false;
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

    if ($('input[name=sml160SelKbn]:checked').val() == 0) {
        $('#accountSelArea').removeClass('display_none');
    } else {
        $('#accountSelArea').addClass('display_none');
    }

});